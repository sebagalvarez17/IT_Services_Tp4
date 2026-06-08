package com.itservices.model;

/**
 * Clase abstracta que representa la entidad base del inventario.
 * Aplica el concepto de Herencia (POO) para que no se puedan instanciar
 * activos genéricos, sino únicamente Hardware o Software específicos.
 */
public abstract class Activo {
    // Atributos protegidos para que las subclases puedan heredarlos directamente
    private int idActivo;
    private String nombre;

    // Constructor para inicializar los datos comunes de cualquier activo
    public Activo(int idActivo, String nombre) {
        this.idActivo = idActivo;
        this.nombre = nombre;
    }

    // Métodos de acceso (Getters) para recuperar la información de forma segura
    public int getIdActivo() { return idActivo; }
    public String getNombre() { return nombre; }

    /**
     * Sobrescritura del método toString para que cuando Swing muestre
     * este objeto en un Combo Box o Lista, imprima el nombre legible.
     */
    @Override
    public String toString() { return nombre; }
}