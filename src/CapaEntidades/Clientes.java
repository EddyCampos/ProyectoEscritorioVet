/**
 * Eddy Campos Jiménez
 * 16 Agosto 2023
 */
package CapaEntidades;

import java.sql.Date;

public class Clientes extends Personas{
    //Atributos
    private int idCLiente;

    //Constructor vacio
    public Clientes() {
        super();
        idCLiente = 0;
    }

    //Constructor con parametros
    public Clientes(String nombre, String apellido01, String apellido02, String cedula, String provincia, String estadoCivil, String genero,            Date fechaNacimiento, String email, String telefono, boolean existe, int idCliente) {
        super(nombre, apellido01, apellido02, cedula, provincia, estadoCivil, genero, fechaNacimiento, email, telefono, existe);
        this.idCLiente = idCliente;
    }

    //Métodos de acceso
    //Get
    public int getIdCLiente() {
        return idCLiente;
    }

    //Set
    public void setIdCLiente(int idCLiente) {
        this.idCLiente = idCLiente;
    }
    
    
}
