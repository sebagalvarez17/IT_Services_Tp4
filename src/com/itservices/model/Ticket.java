package com.itservices.model;

import java.util.Date;

/**
 * Entidad principal del sistema que unifica todos los elementos del dominio.
 * Modela el ciclo de vida de un incidente reportado en IT Services S.R.L.
 */
public class Ticket {
    // Atributos privados para cumplir con el encapsulamiento
    private int ticketId;
    private EmpresaCliente cliente;     // Asociación: Empresa que reporta la falla
    private Activo activoAfectado;      // Asociación: Hardware o Software con problemas
    private Tecnico tecnicoAsignado;    // Asociación: Técnico a cargo de resolverlo
    private Date fechaApertura;         // Almacena el momento exacto del registro (RFS03)
    private String prioridad;           // BAJA, MEDIA o ALTA (Afecta alertas visuales RFS09)
    private String estado;              // Abierto, En Proceso, Resuelto
    private String descripcionFalla;    // Detalle del problema (Mínimo 10 caracteres por validación)

    // Relación 1 a 1: Contiene el detalle de la solución una vez que el ticket se cierra
    private DetalleLabor detalleLabor;

    /**
     * Constructor para dar de alta una incidencia en el sistema.
     * Por regla de negocio, todo ticket nace en estado "Abierto" y con fecha automática.
     */
    public Ticket(int ticketId, EmpresaCliente cliente, Activo activoAfectado, Tecnico tecnicoAsignado, String prioridad, String descripcionFalla) {
        this.ticketId = ticketId;
        this.cliente = cliente;
        this.activoAfectado = activoAfectado;
        this.tecnicoAsignado = tecnicoAsignado;
        this.fechaApertura = new Date(); // Captura automática del reloj del sistema (RFS03)
        this.prioridad = prioridad;
        this.estado = "Abierto";         // Estado inicial por defecto
        this.descripcionFalla = descripcionFalla;
        this.detalleLabor = null;        // Al crearse, todavía no posee tiempos de resolución cargados
    }

    // Métodos de acceso (Getters y Setters) requeridos por el controlador y la interfaz visual
    public int getTicketId() { return ticketId; }
    public EmpresaCliente getCliente() { return cliente; }
    public Activo getActivoAfectado() { return activoAfectado; }
    public Tecnico getTecnicoAsignado() { return tecnicoAsignado; }
    public Date getFechaApertura() { return fechaApertura; }
    public String getPrioridad() { return prioridad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; } // Permite cambiar el estado en las transiciones

    public String getDescripcionFalla() { return descripcionFalla; }

    public DetalleLabor getDetalleLabor() { return detalleLabor; }
    /**
     * Vincula el detalle de la labor técnica al ticket.
     * Se invoca de forma obligatoria durante el proceso de cierre e imputación de tiempos (CU003).
     */
    public void setDetalleLabor(DetalleLabor detalleLabor) {
        this.detalleLabor = detalleLabor;
    }
}