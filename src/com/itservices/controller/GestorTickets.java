package com.itservices.controller;

import com.itservices.model.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Clase Controladora Principal (Patrón MVC).
 * Modificada para persistencia real en MySQL it_services_db y manejo estructurado de excepciones.
 */
public class GestorTickets {

    // Datos de conexión a la Base de Datos
    private static final String URL = "jdbc:mysql://localhost:3306/it_services_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "123456"; // <-- Cambialo por tu contraseña de MySQL

    public GestorTickets() {
        // Al usar base de datos en tiempo real, las listas se consultan bajo demanda
    }

    /**
     * Establece y retorna la conexión física con MySQL.
     * Centraliza el lanzamiento de excepciones de tipo SQLException.
     */
    private Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver JDBC no encontrado en el proyecto.");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Recupera la lista de empresas clientes directamente desde MySQL de forma dinámica.
     */
    public ArrayList<EmpresaCliente> getListaClientes() {
        ArrayList<EmpresaCliente> lista = new ArrayList<>();
        String sqlClientes = "SELECT * FROM empresa_cliente";
        String sqlActivos = "SELECT * FROM activos WHERE id_empresa = ?";

        Connection conn = null;
        PreparedStatement stmtClientes = null;
        PreparedStatement stmtActivos = null;
        ResultSet rsClientes = null;
        ResultSet rsActivos = null;

        try {
            conn = conectar();
            stmtClientes = conn.prepareStatement(sqlClientes);
            rsClientes = stmtClientes.executeQuery();

            while (rsClientes.next()) {
                int idEmp = rsClientes.getInt("id_empresa");
                String razon = rsClientes.getString("razon_social");
                String cuit = rsClientes.getString("cuit");

                EmpresaCliente empresa = new EmpresaCliente(idEmp, razon, cuit);

                // Carga de activos polimórficos de la empresa usando subconsulta parametrizada
                stmtActivos = conn.prepareStatement(sqlActivos);
                stmtActivos.setInt(1, idEmp);
                rsActivos = stmtActivos.executeQuery();

                while (rsActivos.next()) {
                    int idAct = rsActivos.getInt("id_activo");
                    String tipo = rsActivos.getString("tipo_activo");
                    String nombre = rsActivos.getString("marca_modelo");

                    if ("Hardware".equalsIgnoreCase(tipo)) {
                        empresa.agregarActivo(new Hardware(idAct, nombre, "SN-" + idAct));
                    } else {
                        empresa.agregarActivo(new Software(idAct, nombre, "LIC-" + idAct));
                    }
                }
                rsActivos.close();
                stmtActivos.close();

                lista.add(empresa);
            }
        } catch (SQLException ex) {
            System.err.println("Error grave en base de datos al listar clientes: " + ex.getMessage());
        } finally {
            // Cierre preventivo de recursos en bloque finally para evitar memory leaks
            try { if (rsClientes != null) rsClientes.close(); } catch (SQLException e) {}
            try { if (stmtClientes != null) stmtClientes.close(); } catch (SQLException e) {}
            try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
        return lista;
    }

    /**
     * Recupera el staff técnico desde la base de datos de manera limpia.
     */
    public ArrayList<Tecnico> getListaTecnicos() {
        ArrayList<Tecnico> lista = new ArrayList<>();
        String sql = "SELECT * FROM tecnicos";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Tecnico(
                        rs.getInt("id_tecnico"),
                        rs.getString("nombre_completo"),
                        rs.getString("especialidad")
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Error de base de datos al listar técnicos: " + ex.getMessage());
        }
        return lista;
    }

    /**
     * Recupera todos los tickets cruzando dinámicamente las referencias de los IDs.
     */
    public ArrayList<Ticket> getListaTickets() {
        ArrayList<Ticket> lista = new ArrayList<>();

        // La consulta t.* ya incluye la columna 'resolucion' automáticamente
        String sql = "SELECT t.*, c.razon_social, c.cuit, a.marca_modelo, a.tipo_activo " +
                "FROM tickets t " +
                "LEFT JOIN empresa_cliente c ON t.id_empresa = c.id_empresa " +
                "LEFT JOIN activos a ON t.id_activo = a.id_activo";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            ArrayList<Tecnico> listaTecnicos = getListaTecnicos();

            while (rs.next()) {
                int idTecnico = rs.getInt("id_tecnico");

                Tecnico tecnicoAsignado = listaTecnicos.stream()
                        .filter(t -> t.getIdTecnico() == idTecnico)
                        .findFirst()
                        .orElse(null);

                EmpresaCliente cliente = new EmpresaCliente(rs.getInt("id_empresa"), rs.getString("razon_social"), rs.getString("cuit"));
                Activo activo = new Activo(rs.getInt("id_activo"), rs.getString("marca_modelo")) {};

                if (rs.getInt("id_empresa") == 0) {
                    cliente = new EmpresaCliente(0, "Sin Cliente", "000");
                    activo = new Activo(0, " [ SIN ACTIVOS ASOCIADOS ] ") {};
                }

                Ticket t = new Ticket(rs.getInt("ticket_id"), cliente, activo, tecnicoAsignado, rs.getString("prioridad"), rs.getString("descripcion_falla"));
                t.setEstado(rs.getString("estado"));

                // --- ESTA ES LA LÍNEA QUE FALTABA ---
                t.setResolucion(rs.getString("resolucion"));

                lista.add(t);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    /**
     * CU002: Registro de Incidencias con persistencia transaccional directa en MySQL.
     */
    public String registrarIncidencia(EmpresaCliente cliente, Activo activo, Tecnico tecnico, String prioridad, String descripcion) {
        if (descripcion == null || descripcion.trim().length() < 10) {
            return "Error de validación: La descripción debe contener al menos 10 caracteres.";
        }

        String sql = "INSERT INTO tickets (id_activo, id_tecnico, id_empresa, fecha_apertura, prioridad, descripcion_falla, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Aquí está el cambio: forzamos el ID del técnico seleccionado
            int idTecnico = (tecnico != null) ? tecnico.getIdTecnico() : 0;

            stmt.setInt(1, activo != null ? activo.getIdActivo() : 0);
            stmt.setInt(2, idTecnico);
            stmt.setInt(3, cliente != null ? cliente.getIdEmpresa() : 0);
            stmt.setString(4, "2026-06-28");
            stmt.setString(5, prioridad);
            stmt.setString(6, descripcion);
            stmt.setString(7, "Abierto");

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return "Éxito: Ticket N° " + generatedKeys.getInt(1) + " registrado.";
                }
            }
            return "Éxito: Ticket registrado.";

        } catch (SQLException ex) {
            return "Error SQL: " + ex.getMessage();
        }
    }
    /**
     * CU003: Cierre de Caso y simulación transaccional.
     */

    public String imputarLaborYCerrar(int idTicket, String fechaLabor, String horaInicio, int horas, int minutos, String resolucionTecnica) {
        if (fechaLabor == null || fechaLabor.trim().isEmpty() || resolucionTecnica == null || resolucionTecnica.trim().isEmpty()) {
            return "Error: Faltan completar datos obligatorios de la labor técnica.";
        }
        if (horas == 0 && minutos == 0) {
            return "Error: Ingrese un tiempo de dedicación válido.";
        }

        // Consulta SQL REAL corregida con 'ticket_id'
        String sql = "UPDATE tickets SET fecha_labor = ?, hora_inicio = ?, horas_invertidas = ?, minutos_invertidos = ?, resolucion = ?, estado = 'Resuelto' WHERE ticket_id = ?";

        try (java.sql.Connection conn = conectar(); // Usamos tu método conectar() para mayor prolijidad
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, fechaLabor);
            pstmt.setString(2, horaInicio);
            pstmt.setInt(3, horas);
            pstmt.setInt(4, minutos);
            pstmt.setString(5, resolucionTecnica);
            pstmt.setInt(6, idTicket);

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                return "Éxito: El Ticket N° " + idTicket + " fue resuelto. Labor registrada exitosamente.";
            } else {
                return "Error: No se encontró el ticket en la base de datos.";
            }
        } catch (java.sql.SQLException ex) {
            ex.printStackTrace();
            return "Error grave en base de datos al cerrar el ticket: " + ex.getMessage();
        }
    }

    public String actualizarLabor(int idTicket, String fecha, String horaInicio, int horas, int minutos, String resolucion) {
        // Validaciones básicas de negocio
        if (resolucion == null || resolucion.trim().isEmpty()) {
            return "Error: La resolución técnica no puede estar vacía.";
        }
        if (horas < 0 || minutos < 0 || minutos >= 60) {
            return "Error: El tiempo invertido posee un formato inválido.";
        }

        // Consulta SQL corregida con 'ticket_id'
        String sql = "UPDATE tickets SET fecha_labor = ?, hora_inicio = ?, horas_invertidas = ?, minutos_invertidos = ?, resolucion = ? WHERE ticket_id = ?";

        try (java.sql.Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, fecha);
            pstmt.setString(2, horaInicio);
            pstmt.setInt(3, horas);
            pstmt.setInt(4, minutos);
            pstmt.setString(5, resolucion);
            pstmt.setInt(6, idTicket);

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                return "¡Labor técnica corregida con éxito en el sistema!";
            } else {
                return "Error: No se encontró el ticket especificado.";
            }
        } catch (java.sql.SQLException ex) {
            ex.printStackTrace();
            return "Error grave en base de datos al actualizar la labor: " + ex.getMessage();
        }
    }
}