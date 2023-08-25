/**
 * Eddy Campos Jiménez
 * 16 Agosto 2023
 */
package CapaEntidades;

import java.sql.Date;

public class Veterinarios extends Empleados{
    private int idVeterinario;
    private int idEmpleado;
    private String especialidad;
    
    //Constructor vacio
    public Veterinarios() {
        idVeterinario = 0;
        idEmpleado = 0;
        especialidad = "";
    }
    //Constructor con parametros
    public Veterinarios(int idVeterinario, String especialidad, int idEmpleado, String tipoEmpleado, String nombre, String apellido01, String apellido02, String cedula, String provincia, String estadoCivil, String genero, Date fechaNacimiento, String email, String telefono, boolean existe) {
        super(idEmpleado, tipoEmpleado, nombre, apellido01, apellido02, cedula, provincia,              estadoCivil, genero, fechaNacimiento, email, telefono, existe);
        this.idVeterinario = idVeterinario;
        this.idEmpleado = idEmpleado;
        this.especialidad = especialidad;
    }
    
    //Métodos de acceso
    //Get
    public int getIdVeterinario() {
        return idVeterinario;
    }
    
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public String getEspecialidad() {
        return especialidad;
    }
    
    //Set
    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    
    
    
}
