package com.itservices.model;

/**
 * Subclase especializada que hereda de Activo.
 * Representa los elementos físicos (Routers, Switches, Servidores).
 */
public class Hardware extends Activo {
    // Atributo específico para identificar componentes físicos según el diseño aprobado
    private String nroSerie;

    // Constructor que invoca al constructor de la clase padre (super) e inicializa el atributo propio
    public Hardware(int idActivo, String nombre, String nroSerie) {
        super(idActivo, nombre); // Envía los datos obligatorios a la clase padre Activo
        this.nroSerie = nroSerie;
    }

    // Getter para obtener el número de serie en las auditorías de tickets
    public String getNroSerie() { return nroSerie; }
}