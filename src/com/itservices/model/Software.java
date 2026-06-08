package com.itservices.model;

/**
 * Subclase especializada que hereda de Activo.
 * Representa los elementos lógicos y sistemas operativos instalados.
 */
public class Software extends Activo {
    // Atributo específico para controlar las licencias y vencimientos
    private String licencia;

    // Constructor que reutiliza la estructura del padre mediante 'super'
    public Software(int idActivo, String nombre, String licencia) {
        super(idActivo, nombre); // Invoca los atributos compartidos de la herencia
        this.licencia = licencia;
    }

    // Getter para validar si la licencia del cliente está vigente al momento del soporte
    public String getLicencia() { return licencia; }
}