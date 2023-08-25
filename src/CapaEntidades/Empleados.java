/**
 * Eddy Campos Jiménez
 * 16 Agosto 2023
 */
package CapaEntidades;

import java.sql.Date;


public class Empleados extends Personas {
    //Atributos
    private int idEmpleado;
    private String tipoEmpleado;

    //Constructor vacio
    public Empleados() {    
        super();
        idEmpleado = 0;
        tipoEmpleado = "";
    }

    //Constructor con paramteros
    public Empleados(int idEmpleado, String tipoEmpleado, String nombre, String apellido01, String apellido02, String cedula, String provincia, String estadoCivil, String genero, Date fechaNacimiento, String email, String telefono, boolean existe) {    
        super(nombre, apellido01, apellido02, cedula, provincia, estadoCivil, genero, fechaNacimiento, email, telefono, existe);
        this.idEmpleado = idEmpleado;
        this.tipoEmpleado = tipoEmpleado;
    }

    //Métodos de acceso
    //Get
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }
    
    //Set
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

}
