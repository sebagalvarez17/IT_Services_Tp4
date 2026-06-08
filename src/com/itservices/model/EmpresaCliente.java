package com.itservices.model;

import java.util.ArrayList;

/**
 * Entidad que representa a las organizaciones cliente asistidas por IT Services S.R.L.
 * Permite cumplir con el control estructurado de las organizaciones del sistema.
 */
public class EmpresaCliente {
    // Atributos privados para garantizar el encapsulamiento de datos
    private int idEmpresa;
    private String razonSocial;
    private String cuit;

    // Relación de Agregación: Una empresa posee un conjunto de activos informáticos asociados
    private ArrayList<Activo> listaActivos;

    // Constructor para inicializar los datos de la organización y su lista vacía de infraestructura
    public EmpresaCliente(int idEmpresa, String razonSocial, String cuit) {
        this.idEmpresa = idEmpresa;
        this.razonSocial = razonSocial;
        this.cuit = cuit;
        this.listaActivos = new ArrayList<>(); // Se crea la lista en memoria al dar de alta la empresa
    }

    /**
     * Método operativo para vincular un nuevo hardware o software a la empresa contratante.
     * @param activo Objeto de la clase padre Activo (acepta Hardware o Software por polimorfismo)
     */
    public void agregarActivo(Activo activo) {
        this.listaActivos.add(activo); // Añade el objeto a la colección interna de la empresa
    }

    // Métodos Getters obligatorios para recuperar información desde el Controlador y las Vistas
    public int getIdEmpresa() { return idEmpresa; }
    public String getRazonSocial() { return razonSocial; }
    public String getCuit() { return cuit; }
    public ArrayList<Activo> getListaActivos() { return listaActivos; }

    /**
     * Sobrescritura de toString para que los componentes visuales de la interfaz (JComboBox)
     * listen el nombre comercial directamente en la pantalla de selección del formulario.
     */
    @Override
    public String toString() { return razonSocial; }
}