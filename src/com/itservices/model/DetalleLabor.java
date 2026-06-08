package com.itservices.model;

/**
 * Representa la documentación técnica obligatoria realizada al solucionar un incidente.
 * Almacena métricas de tiempo exactas para el control de ANS (Acuerdos de Nivel de Servicio).
 */
public class DetalleLabor {
    private int nroTicket;         // Conecta con el ticket correspondiente
    private String fechaLabor;     // Guarda la fecha ingresada (Ej: "03/06/2026")
    private String horaInicio;     // Hora en que empezó (Ej: "14:00")
    private int horasInvertidas;   // Cuántas horas le tomó (Ej: 1)
    private int minutosInvertidos; // Minutos adicionales (Ej: 0)
    private String resolucion;     // Descripción técnica de la solución

    // Constructor actualizado para recibir todos los parámetros temporales manuales
    public DetalleLabor(int nroTicket, String fechaLabor, String horaInicio, int horasInvertidas, int minutosInvertidos, String resolucion) {
        this.nroTicket = nroTicket;
        this.fechaLabor = fechaLabor;
        this.horaInicio = horaInicio;
        this.horasInvertidas = horasInvertidas;
        this.minutosInvertidos = minutosInvertidos;
        this.resolucion = resolucion;
    }

    // Getters para que la interfaz o futuros reportes lean los datos
    public String getFechaLabor() { return fechaLabor; }
    public String getHoraInicio() { return horaInicio; }
    public int getHorasInvertidas() { return horasInvertidas; }
    public int getMinutosInvertidos() { return minutosInvertidos; }
    public String getResolucion() { return resolucion; }
}