/**
 * Eddy Campos Jiménez
 * 16 Agosto 2023
 */
package CapaEntidades;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class ExpedientesMascotas {
    //Atributos
    private int idExpediente;
    private int idCliente;
    private String nombre;
    private String especie;
    private String raza;
    private String sexo;
    private LocalDate fechaNacimiento;
    private int peso;
    private boolean existe;
    
    //Constructor vacio
    public ExpedientesMascotas() {
        idExpediente = 0;
        idCliente = 0;
        nombre = "";
        especie = "";
        raza = "";
        sexo = "";
        fechaNacimiento = LocalDate.now();
        peso = 0;
        existe = false;
    }
    
    //Constructor con parametros
    public ExpedientesMascotas(int idExpediente, int idCliente, String nombre, String especie, String raza, String sexo, LocalDate fechaNacimiento, int       peso, boolean existe) {
        this.idExpediente = idExpediente;
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.existe = existe;
    }
    
    //Métodos de acceso
    //Get
    public int getIdExpediente() {
        return idExpediente;
    }

    public int getIdCliente() {
        return idCliente;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getEspecie() {
        return especie;
    }
    
    public String getRaza() {
        return raza;
    }
    
    public String getSexo() {
        return sexo;
    }
    
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public int getPeso() {
        return peso;
    }
    
    public boolean isExiste() {
        return existe;
    }
    
    //Set
    public void setIdExpediente(int idExpediente) {
        this.idExpediente = idExpediente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    
}
