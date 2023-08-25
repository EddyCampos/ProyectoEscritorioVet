/**
 * Eddy Campos Jiménez
 * 16 Agosto 2023
 */
package CapaEntidades;

import java.sql.Date;

public class Personas {
    //Atributos
    private String nombre;
    private String apellido01;
    private String apellido02;
    private String cedula;
    private String provincia;
    private String estadoCivil;
    private String genero;
    private Date fechaNacimiento;
    private String email;
    private String telefono;
    private boolean existe;

    //Constructor vacio
    public Personas() {
        nombre = "";
        apellido01 = "";
        apellido02 = "";
        cedula = "";
        provincia = "";
        estadoCivil = "";
        genero = "";
        fechaNacimiento = new Date(0);
        email = "";
        telefono = "";
        existe = false;
    }
    
    //Constructor con parametros
    public Personas(String nombre, String apellido01, String apellido02, String cedula, String provincia, String estadoCivil, String genero,            Date fechaNacimiento, String email, String telefono, boolean existe) {
        this.nombre = nombre;
        this.apellido01 = apellido01;
        this.apellido02 = apellido02;
        this.cedula = cedula;
        this.provincia = provincia;
        this.estadoCivil = estadoCivil;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.telefono = telefono;
        this.existe = existe;
    }

    //Métodos de acceso
    //Get
    public String getNombre() {
        return nombre;
    }
    
    public String getApellido01() {
        return apellido01;
    }

    public String getApellido02() {
        return apellido02;
    }
    
    public String getCedula() {
        return cedula;
    }
    
    public String getProvincia() {
        return provincia;
    }
    
    public String getEstadoCivil() {
        return estadoCivil;
    }
    
    public String getGenero() {
        return genero;
    }
    
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
     
    public String getTelefono() {
        return telefono;
    }
    
    public String getEmail() {
        return email;
    }

    public boolean isExiste() {
        return existe;
    }
    
    //Set
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setApellido01(String apellido01) {
        this.apellido01 = apellido01;
    }

    public void setApellido02(String apellido02) {
        this.apellido02 = apellido02;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    
    
}
