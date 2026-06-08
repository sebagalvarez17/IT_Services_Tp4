package com.itservices.controller;

import com.itservices.model.*;
import java.util.ArrayList;

/**
 * Clase Controladora Principal (Patrón MVC).
 * Centraliza la lógica de negocio, las validaciones del sistema
 * y la gestión de colecciones en memoria de IT Services S.R.L.
 */
public class GestorTickets {
    // Colecciones primarias para almacenar los datos operativos en memoria
    private ArrayList<EmpresaCliente> listaClientes;
    private ArrayList<Tecnico> listaTecnicos;
    private ArrayList<Ticket> listaTickets;

    /**
     * Constructor del Controlador.
     * Inicializa las listas y ejecuta la precarga de datos de prueba (Mock Data)
     * para simular la existencia de infraestructura preexistente.
     */
    public GestorTickets() {
        this.listaClientes = new ArrayList<>();
        this.listaTecnicos = new ArrayList<>();
        this.listaTickets = new ArrayList<>();

        // Ejecutamos la precarga automática al instanciar el gestor
        cargarDatosIniciales();
    }

    /**
     * Método interno para poblar el sistema con datos consistentes con los TP anteriores.
     */
    private void cargarDatosIniciales() {
        // 1. Creamos y agregamos Técnicos de soporte con sus especialidades
        listaTecnicos.add(new Tecnico(1, "Álvarez Sebastian", "Redes y Telecomunicaciones"));
        listaTecnicos.add(new Tecnico(2, "Gómez Mariana", "Administración Cloud / Azure"));
        listaTecnicos.add(new Tecnico(3, "Rodríguez Alan", "Sistemas Operativos y Servidores"));

        // 2. Creamos Organizaciones Clientes (Parte de las 28 organizaciones de tu alcance)
        EmpresaCliente c1 = new EmpresaCliente(101, "Coca-Cola S.A.", "30-12345678-9");
        EmpresaCliente c2 = new EmpresaCliente(102, "Techint S.A.", "30-87654321-9");
        EmpresaCliente c3 = new EmpresaCliente(103, "Banco Galicia", "30-11223344-9");

        // 3. Aplicamos Polimorfismo y Herencia cargando Activos (Hardware y Software) a las empresas
        c1.agregarActivo(new Hardware(1, "Router Cisco ISR 4331", "SN-CISCO-9982"));
        c1.agregarActivo(new Software(2, "Licencia Windows Server 2022", "WS22-STND-XXXX"));

        c2.agregarActivo(new Hardware(3, "Switch Catalyst 9300", "SN-CATALYST-1122"));

        c3.agregarActivo(new Software(4, "Base de Datos Oracle 19c", "ORCL-EE-2026"));

        // Guardamos las empresas en la lista general del controlador
        listaClientes.add(c1);
        listaClientes.add(c2);
        listaClientes.add(c3);
    }

    /**
     * CU002 / RFS02: Registro de Incidencias.
     * Valida las reglas de negocio antes de instanciar un nuevo Ticket.
     */
    public String registrarIncidencia(int id, EmpresaCliente cliente, Activo activo, Tecnico tecnico, String prioridad, String descripcion) {
        // Regla de Validación del Plan de Pruebas: Mínimo 10 caracteres en la descripción
        if (descripcion == null || descripcion.trim().length() < 10) {
            return "Error de validación: La descripción del problema debe contener al menos 10 caracteres.";
        }

        // Si pasa la validación, instanciamos el objeto puro de negocio
        Ticket nuevoTicket = new Ticket(id, cliente, activo, tecnico, prioridad, descripcion);

        // Lo añadimos a la colección global de tickets
        listaTickets.add(nuevoTicket);

        return "Éxito: Ticket N° " + id + " registrado correctamente para el cliente " + cliente.getRazonSocial();
    }

    /**
     * CU003 / RFS05: Cierre e Imputación de Tiempos.
     * Localiza un ticket abierto, audita el tiempo y le asocia su detalle de labor técnica.
     */
    /**
     * CU003 / RFS05: Cierre e Imputación de Tiempos.
     * Actualizado para recibir fecha específica, hora de inicio y tiempos de labor.
     */
    public String imputarLaborYCerrar(int idTicket, String fechaLabor, String horaInicio, int horas, int minutos, String resolucionTecnica) {
        // Validaciones obligatorias de negocio
        if (fechaLabor == null || fechaLabor.trim().isEmpty()) {
            return "Error: La fecha en que se realizó la labor es obligatoria.";
        }
        if (horaInicio == null || horaInicio.trim().isEmpty()) {
            return "Error: La hora de inicio es obligatoria.";
        }
        if (horas == 0 && minutos == 0) {
            return "Error: Debe ingresar un tiempo de labor válido (horas o minutos mayores a cero).";
        }
        if (resolucionTecnica == null || resolucionTecnica.trim().isEmpty()) {
            return "Error: La descripción de la resolución técnica es obligatoria.";
        }

        // Buscamos el ticket por ID usando un bucle iterativo
        for (Ticket t : listaTickets) {
            if (t.getTicketId() == idTicket) {
                t.setEstado("Resuelto");

                // Instanciamos el DetalleLabor con la fecha y hora manual del técnico
                DetalleLabor labor = new DetalleLabor(idTicket, fechaLabor, horaInicio, horas, minutos, resolucionTecnica);

                t.setDetalleLabor(labor);
                return "Éxito: El Ticket N° " + idTicket + " ha sido cerrado. Labor registrada el " + fechaLabor + " desde las " + horaInicio + " hs.";
            }
        }
        return "Error: No se encontró ningún ticket activo con el número " + idTicket;
    }

    // Métodos Getters para que la capa de la Vista (Swing) pueda consultar las listas
    public ArrayList<EmpresaCliente> getListaClientes() { return listaClientes; }
    public ArrayList<Tecnico> getListaTecnicos() { return listaTecnicos; }
    public ArrayList<Ticket> getListaTickets() { return listaTickets; }
}