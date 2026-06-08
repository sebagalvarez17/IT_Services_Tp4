package com.itservices.model;

/**
 * Entidad que representa al personal técnico de soporte del sistema.
 */
public class Tecnico {
    private int idTecnico;
    private String nombreCompleto;
    private String especialidad; // Ej: Redes, Servidores, Cloud, Ciberseguridad

    // Constructor para el alta del recurso técnico en el sistema
    public Tecnico(int idTecnico, String nombreCompleto, String especialidad) {
        this.idTecnico = idTecnico;
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
    }

    // Getters estándar de la POO para la lectura segura de atributos
    public int getIdTecnico() { return idTecnico; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getEspecialidad() { return especialidad; }

    /**
     * Devuelve una cadena con formato para facilitar la visualización del operario
     * y su especialización dentro de la lista de asignación de la interfaz gráfica.
     */
    @Override
    public String toString() {
        return nombreCompleto + " (" + especialidad + ")";
    }
}