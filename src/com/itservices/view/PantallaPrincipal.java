package com.itservices.view;

import com.itservices.controller.GestorTickets;
import com.itservices.model.*;

        import javax.swing.*;
        import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
        import java.awt.event.ItemEvent;

/**
 * Interfaz Gráfica de Usuario Final (Capa de Vista - Swing Puro).
 * Se alinea al 100% con los mockups del TP2 (Registro, Bandeja e Inventario).
 */
public class PantallaPrincipal extends JFrame {
    private GestorTickets controlador;

    // Componentes del Formulario de Registro
    private JTextField txtIdTicket;
    private JComboBox<EmpresaCliente> cmbClientes;
    private JComboBox<Activo> cmbActivos;
    private JComboBox<Tecnico> cmbTecnicos;
    private JComboBox<String> cmbPrioridad;
    private JTextArea txtDescripcion;

    // Componentes de la Bandeja de Trabajo
    private JTable tablaTickets;
    private DefaultTableModel modeloTabla;

    public PantallaPrincipal() {
        this.controlador = new GestorTickets();

        setTitle("IT Services S.R.L. - Sistema de Gestión de Incidentes Help Desk v1.0");
        setSize(950, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // PANEL SUPERIOR: Encabezado con Nombre del Alumno
        JPanel panelHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelHeader.setBackground(new Color(0, 51, 102));
        JLabel lblTitulo = new JLabel(" Centro de Soporte Técnico - Técnico Activo: Alvarez Sebastian | Fecha: 07/06/2026");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        panelHeader.add(lblTitulo);
        add(panelHeader, BorderLayout.NORTH);

        // PANEL CENTRAL
        JPanel panelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        // --- SUB-PANEL: Formulario de Registro (Mockup 1) ---
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Formulario de Registro de Incidencias (CU002)"));
        GridBagConstraints fGbc = new GridBagConstraints();
        fGbc.insets = new Insets(4, 4, 4, 4);
        fGbc.anchor = GridBagConstraints.WEST;

        fGbc.gridx = 0; fGbc.gridy = 0; panelForm.add(new JLabel("Nro Ticket:"), fGbc);
        fGbc.gridx = 1;
        txtIdTicket = new JTextField("NUEVO", 5);
        txtIdTicket.setEditable(false);
        panelForm.add(txtIdTicket, fGbc);

        fGbc.gridx = 0; fGbc.gridy = 1; panelForm.add(new JLabel("Empresa Cliente:"), fGbc);
        fGbc.gridx = 1;
        cmbClientes = new JComboBox<>();
        for (EmpresaCliente cliente : controlador.getListaClientes()) { cmbClientes.addItem(cliente); }
        panelForm.add(cmbClientes, fGbc);

        fGbc.gridx = 0; fGbc.gridy = 2; panelForm.add(new JLabel("Activo Afectado:"), fGbc);
        fGbc.gridx = 1;
        cmbActivos = new JComboBox<>();
        actualizarComboActivos();
        panelForm.add(cmbActivos, fGbc);

        cmbClientes.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) { actualizarComboActivos(); }
        });

        fGbc.gridx = 0; fGbc.gridy = 3; panelForm.add(new JLabel("Técnico Asignado:"), fGbc);
        fGbc.gridx = 1;
        cmbTecnicos = new JComboBox<>();
        for (Tecnico t : controlador.getListaTecnicos()) { cmbTecnicos.addItem(t); }
        panelForm.add(cmbTecnicos, fGbc);

        fGbc.gridx = 0; fGbc.gridy = 4; panelForm.add(new JLabel("Prioridad:"), fGbc);
        fGbc.gridx = 1;
        cmbPrioridad = new JComboBox<>(new String[]{"BAJA", "MEDIA", "ALTA"});
        panelForm.add(cmbPrioridad, fGbc);

        fGbc.gridx = 0; fGbc.gridy = 5; panelForm.add(new JLabel("Descripción de la Falla:"), fGbc);
        fGbc.gridx = 1;
        txtDescripcion = new JTextArea(3, 40);
        txtDescripcion.setLineWrap(true);
        panelForm.add(new JScrollPane(txtDescripcion), fGbc);

        // BOTONERA DEL FORMULARIO (Agregado Limpiar Campos según Mockup 1)
        fGbc.gridx = 1; fGbc.gridy = 6; fGbc.anchor = GridBagConstraints.EAST;
        JPanel panelBotonesForm = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnLimpiar = new JButton("LIMPIAR CAMPOS");
        JButton btnRegistrar = new JButton("REGISTRAR TICKET");
        btnRegistrar.setBackground(new Color(0, 153, 76)); btnRegistrar.setForeground(Color.WHITE);
        panelBotonesForm.add(btnLimpiar);
        panelBotonesForm.add(btnRegistrar);
        panelForm.add(panelBotonesForm, fGbc);

        btnLimpiar.addActionListener(e -> txtDescripcion.setText(""));
        btnRegistrar.addActionListener(e -> accionRegistrarTicket());

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0; gbc.weighty = 0.4;
        panelCentral.add(panelForm, gbc);

        // --- SUB-PANEL: Bandeja de Pendientes (Mockup 3) ---
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder("Bandeja de Pendientes (Monitoreo de SLAs)"));

        String[] columnas = {"Nro Ticket", "Cliente", "Activo Afectado", "Técnico Asignado", "Prioridad", "Estado"};
        modeloTabla = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tablaTickets = new JTable(modeloTabla);
        tablaTickets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Renderizador de Alertas Visuales (RFS09 - Rojo si es ALTA)
        tablaTickets.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String prioridadFila = table.getValueAt(row, 4).toString();
                String estadoFila = table.getValueAt(row, 5).toString();

                if ("ALTA".equals(prioridadFila) && !"Resuelto".equals(estadoFila)) {
                    c.setBackground(new Color(255, 204, 204)); c.setForeground(new Color(153, 0, 0));
                    c.setFont(c.getFont().deriveFont(Font.BOLD));
                } else if ("Resuelto".equals(estadoFila)) {
                    c.setBackground(new Color(204, 255, 204)); c.setForeground(new Color(0, 102, 51));
                } else {
                    c.setBackground(Color.WHITE); c.setForeground(Color.BLACK);
                }
                if (isSelected) { c.setBackground(table.getSelectionBackground()); c.setForeground(table.getSelectionForeground()); }
                return c;
            }
        });

        panelTabla.add(new JScrollPane(tablaTickets), BorderLayout.CENTER);

        // BOTONERA DE LA BANDEJA (Agregados botones Ver Detalle e Inventario de Activos)
        JPanel panelAccionesTabla = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnInventario = new JButton("VER INVENTARIO DE ACTIVOS (Mockup 2)");
        JButton btnVerDetalle = new JButton("VER DETALLE (Mockup 3)");
        JButton btnCerrarTicket = new JButton("RESOLVER CASO (CU003)");

        btnInventario.setBackground(new Color(0, 102, 153)); btnInventario.setForeground(Color.WHITE);
        btnVerDetalle.setBackground(new Color(102, 102, 102)); btnVerDetalle.setForeground(Color.WHITE);
        btnCerrarTicket.setBackground(new Color(204, 102, 0)); btnCerrarTicket.setForeground(Color.WHITE);

        panelAccionesTabla.add(btnInventario);
        panelAccionesTabla.add(btnVerDetalle);
        panelAccionesTabla.add(btnCerrarTicket);
        panelTabla.add(panelAccionesTabla, BorderLayout.SOUTH);

        btnInventario.addActionListener(e -> abrirVentanaInventario());
        btnVerDetalle.addActionListener(e -> accionVerDetalleTicket());
        btnCerrarTicket.addActionListener(e -> accionCerrarTicket());

        gbc.gridy = 1; gbc.weighty = 0.6;
        panelCentral.add(panelTabla, gbc);

        add(panelCentral, BorderLayout.CENTER);
        actualizarGrilla();
    }


    /**
     * Actualiza dinámicamente el selector de activos según la empresa cliente seleccionada.
     * Sincroniza dinámicamente con los datos vivos de la Base de Datos.
     */
    private void actualizarComboActivos() {
        if (cmbActivos == null) return;

        cmbActivos.removeAllItems();

        // 1. Insertamos el comodín por defecto
        cmbActivos.addItem(new Activo(0, " [ SIN ACTIVOS ASOCIADOS ] ") {});

        // 2. Cargamos los activos específicos buscando al cliente real de la lista actualizada del controlador
        EmpresaCliente clienteSeleccionado = (EmpresaCliente) cmbClientes.getSelectedItem();
        if (clienteSeleccionado != null) {
            for (EmpresaCliente c : controlador.getListaClientes()) {
                if (c.getIdEmpresa() == clienteSeleccionado.getIdEmpresa()) {
                    for (Activo activo : c.getListaActivos()) {
                        cmbActivos.addItem(activo);
                    }
                    break;
                }
            }
        }
    }

    private void accionRegistrarTicket() {
        EmpresaCliente cliente = (EmpresaCliente) cmbClientes.getSelectedItem();
        Activo activo = (Activo) cmbActivos.getSelectedItem();
        Tecnico tecnico = (Tecnico) cmbTecnicos.getSelectedItem();
        String prioridad = (String) cmbPrioridad.getSelectedItem();
        String descripcion = txtDescripcion.getText();

        String resultado = controlador.registrarIncidencia(cliente, activo, tecnico, prioridad, descripcion);

        if (resultado.startsWith("Error")) {
            JOptionPane.showMessageDialog(this, resultado, "Validación", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, resultado, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            txtDescripcion.setText("");
            txtIdTicket.setText("N/A");
            actualizarGrilla();
        }
    }

    private void accionCerrarTicket() {
        int filaSeleccionada = tablaTickets.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un ticket de la bandeja.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idTicket = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        String estadoActual = (String) modeloTabla.getValueAt(filaSeleccionada, 5);

        // Buscamos el ticket en memoria para recuperar los datos actuales si es una edición
        Ticket ticketEncontrado = null;
        for (Ticket t : controlador.getListaTickets()) {
            if (t.getTicketId() == idTicket) {
                ticketEncontrado = t;
                break;
            }
        }


        // SI YA ESTÁ RESUELTO, CAMBIAMOS EL COMPORTAMIENTO A MODO EDICIÓN
        // Definimos los valores por defecto antes del condicional
        String fechaDefecto = "28/06/2026";
        String horaDefecto = "14:00";
        String horasDefecto = "1";
        String minutosDefecto = "0";
        String resolucionDefecto = "";
        String tituloVentana = "Imputación de Tiempos - Ticket N° " + idTicket;

// Verificamos si existe el objeto y si tiene labor cargada
        boolean esEdicion = (ticketEncontrado != null && ticketEncontrado.getDetalleLabor() != null);

        if (esEdicion) {
            // Si es edición, sobreescribimos los valores por defecto con los que están en la base de datos
            DetalleLabor labor = ticketEncontrado.getDetalleLabor();
            fechaDefecto = labor.getFechaLabor();
            horaDefecto = labor.getHoraInicio();
            horasDefecto = String.valueOf(labor.getHorasInvertidas());
            minutosDefecto = String.valueOf(labor.getMinutosInvertidos());
            resolucionDefecto = labor.getResolucion();
            tituloVentana = "CORREGIR LABOR TÉCNICA - Ticket N° " + idTicket;
        } else if ("Resuelto".equals(estadoActual)) {
            // Protección adicional por si hay inconsistencias en la base de datos
            JOptionPane.showMessageDialog(this, "El ticket figura como 'Resuelto' pero no se hallaron registros de labor.", "Alerta de Integridad", JOptionPane.WARNING_MESSAGE);
        }

        // Construimos los componentes visuales con los datos cargados correspondientes
        JTextField txtFechaLabor = new JTextField(fechaDefecto, 10);
        JTextField txtHoraInicio = new JTextField(horaDefecto, 5);
        JTextField txtHoras = new JTextField(horasDefecto, 3);
        JTextField txtMinutos = new JTextField(minutosDefecto, 3);
        JTextArea txtResolucion = new JTextArea(resolucionDefecto, 3, 25);
        txtResolucion.setLineWrap(true);

        JPanel panelInputLabor = new JPanel(new GridLayout(0, 1, 2, 4));
        panelInputLabor.add(new JLabel("Fecha de realización (DD/MM/AAAA):"));
        panelInputLabor.add(txtFechaLabor);
        panelInputLabor.add(new JLabel("Hora de inicio (HH:MM):"));
        panelInputLabor.add(txtHoraInicio);
        panelInputLabor.add(new JLabel("Horas invertidas:"));
        panelInputLabor.add(txtHoras);
        panelInputLabor.add(new JLabel("Minutos adicionales:"));
        panelInputLabor.add(txtMinutos);
        panelInputLabor.add(new JLabel("Resolución Técnica (Acciones Tomadas):"));
        panelInputLabor.add(new JScrollPane(txtResolucion));

        int opcion = JOptionPane.showConfirmDialog(this, panelInputLabor, tituloVentana, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (opcion == JOptionPane.OK_OPTION) {
            try {
                String fecha = txtFechaLabor.getText().trim();
                String horaIn = txtHoraInicio.getText().trim();
                int horas = Integer.parseInt(txtHoras.getText().trim());
                int minutos = Integer.parseInt(txtMinutos.getText().trim());
                String resolucion = txtResolucion.getText();

                String resultado;
                // Si estaba resuelto llama a actualizar, sino llama a cerrar por primera vez
                if (esEdicion) {
                    resultado = controlador.actualizarLabor(idTicket, fecha, horaIn, horas, minutos, resolucion);
                } else {
                    resultado = controlador.imputarLaborYCerrar(idTicket, fecha, horaIn, horas, minutos, resolucion);
                }

                if (resultado.startsWith("Error")) {
                    JOptionPane.showMessageDialog(this, resultado, "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, resultado, "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                    actualizarGrilla();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Campos numéricos inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * MOCKUP 3 - Botón "VER DETALLE":
     * Busca el ticket seleccionado y despliega una ventana emergente estructurada con la labor técnica completa.
     */
    private void accionVerDetalleTicket() {
        int filaSeleccionada = tablaTickets.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un ticket para auditar su auditoría.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idTicket = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        Ticket ticketEncontrado = null;

        for (Ticket t : controlador.getListaTickets()) {
            if (t.getTicketId() == idTicket) { ticketEncontrado = t; break; }
        }

        if (ticketEncontrado != null) {
            StringBuilder detalle = new StringBuilder();
            detalle.append("=== AUDITORÍA DETALLADA DE INCIDENCIA N° ").append(idTicket).append(" ===\n\n");
            detalle.append("• Cliente: ").append(ticketEncontrado.getCliente().getRazonSocial()).append("\n");
            detalle.append("• Activo Afectado: ").append(ticketEncontrado.getActivoAfectado().getNombre()).append("\n");
            detalle.append("• Especialista Asignado: ").append(ticketEncontrado.getTecnicoAsignado().getNombreCompleto()).append("\n");
            detalle.append("• Prioridad Operativa: ").append(ticketEncontrado.getPrioridad()).append("\n");
            detalle.append("• Estado Actual: ").append(ticketEncontrado.getEstado()).append("\n");
            detalle.append("• Falla Inicial Reportada: \n  \"").append(ticketEncontrado.getDescripcionFalla()).append("\"\n\n");

            DetalleLabor labor = ticketEncontrado.getDetalleLabor();
            if (labor != null) {
                detalle.append("=== SEGUIMIENTO TÉCNICO E IMPUTACIÓN DE HORAS ===\n");
                detalle.append("• Fecha Ejecución de Labor: ").append(labor.getFechaLabor()).append("\n");
                detalle.append("• Franja Horaria de Inicio: ").append(labor.getHoraInicio()).append(" hs\n");
                detalle.append("• Tiempo Invertido Acumulado: ").append(labor.getHorasInvertidas()).append(" hrs ").append(labor.getMinutosInvertidos()).append(" min\n");
                detalle.append("• Informe de Cierre Resolutivo: \n  \"").append(labor.getResolucion()).append("\"\n");
            } else if ("Resuelto".equals(ticketEncontrado.getEstado())) {
                // MODIFICACIÓN AQUÍ: Ahora mostramos la resolución que cargamos en el objeto Ticket
                detalle.append("=== RESOLUCIÓN TÉCNICA ===\n");
                detalle.append("• Acciones Tomadas: \n  \"").append(ticketEncontrado.getResolucion()).append("\"\n");
            } else {
                detalle.append("[!] Alerta: El ticket se encuentra pendiente de asignación y resolución en el Help Desk.");
            }

            JTextArea areaDetalle = new JTextArea(detalle.toString());
            areaDetalle.setEditable(false);
            areaDetalle.setFont(new Font("Monospaced", Font.PLAIN, 13));
            areaDetalle.setLineWrap(true);
            areaDetalle.setWrapStyleWord(true);

            JScrollPane scrollPane = new JScrollPane(areaDetalle);
            scrollPane.setPreferredSize(new Dimension(650, 450));

            JOptionPane.showMessageDialog(this, scrollPane, "Visor Técnico de Incidentes - IT Services", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * MOCKUP 2 - Interfaz de Inventario de Activos:
     * Levanta una ventana secundaria independiente mapeada exactamente con tu mockup de activos.
     */
    private void abrirVentanaInventario() {
        JDialog ventanaInv = new JDialog(this, "IT Services S.R.L. - Inventario de Activos v1.0", true);
        ventanaInv.setSize(600, 400);
        ventanaInv.setLocationRelativeTo(this);
        ventanaInv.setLayout(new BorderLayout(10, 10));

        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltro.add(new JLabel("Filtro por Empresa: "));
        JComboBox<EmpresaCliente> cmbFiltroEmpresa = new JComboBox<>();
        for (EmpresaCliente c : controlador.getListaClientes()) { cmbFiltroEmpresa.addItem(c); }
        panelFiltro.add(cmbFiltroEmpresa);
        ventanaInv.add(panelFiltro, BorderLayout.NORTH);

        String[] colsInv = {"ID", "Tipo Activo", "Nombre / Componente", "Identificador Técnico / Licencia"};
        DefaultTableModel modelInv = new DefaultTableModel(null, colsInv) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tablaInv = new JTable(modelInv);
        ventanaInv.add(new JScrollPane(tablaInv), BorderLayout.CENTER);

        // Método interno para poblar la grilla de activos según la empresa seleccionada
        Runnable actualizarTablaInventario = () -> {
            modelInv.setRowCount(0);
            EmpresaCliente emp = (EmpresaCliente) cmbFiltroEmpresa.getSelectedItem();
            if (emp != null) {
                for (Activo a : emp.getListaActivos()) {
                    String tipo = (a instanceof Hardware) ? "Hardware" : "Software";
                    String identificador = (a instanceof Hardware) ? ((Hardware) a).getNroSerie() : ((Software) a).getLicencia();
                    modelInv.addRow(new Object[]{a.getIdActivo(), tipo, a.getNombre(), identificador});
                }
            }
        };

        cmbFiltroEmpresa.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) { actualizarTablaInventario.run(); }
        });

        actualizarTablaInventario.run(); // Carga inicial

        JPanel panelBotonera = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnVolver = new JButton("VOLVER AL MENÚ");
        btnVolver.addActionListener(e -> ventanaInv.dispose());
        panelBotonera.add(btnVolver);
        ventanaInv.add(panelBotonera, BorderLayout.SOUTH);

        ventanaInv.setVisible(true);
    }

    private void actualizarGrilla() {
        modeloTabla.setRowCount(0);
        for (Ticket t : controlador.getListaTickets()) {
            // Validamos que los objetos existan antes de llamar a sus métodos
            String nombreCliente = (t.getCliente() != null) ? t.getCliente().getRazonSocial() : "Sin Cliente";
            String nombreActivo = (t.getActivoAfectado() != null) ? t.getActivoAfectado().getNombre() : "N/A";
            String nombreTecnico = (t.getTecnicoAsignado() != null) ? t.getTecnicoAsignado().getNombreCompleto() : "Sin Asignar";

            Object[] fila = {
                    t.getTicketId(),
                    nombreCliente,
                    nombreActivo,
                    nombreTecnico,
                    t.getPrioridad(),
                    t.getEstado()
            };
            modeloTabla.addRow(fila);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { new PantallaPrincipal().setVisible(true); });
    }
}