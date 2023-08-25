/**
 * Eddy Campos Jiménez
 * 16 Agosto 2023
 */
package CapaEntidades;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class DetallesFacturas {
    //Atributos
    private int idDetalle;
    private int idEmpleado;
    private String tipoPago;
    private LocalDate fecha;
    private double total;
    private boolean existe;
    
    //Constructor vacio
    public DetallesFacturas() {
        idDetalle = 0;
        idEmpleado = 0;
        tipoPago = "";
        fecha = LocalDate.now();
        total = 0.0;
        existe = false;
    }
    
    //Constructor con parametros
    public DetallesFacturas(int idDetalle, int idEmpleado, String tipoPago, LocalDate fecha, double total, boolean existe) {
        this.idDetalle = idDetalle;
        this.idEmpleado = idEmpleado;
        this.tipoPago = tipoPago;
        this.fecha = fecha;
        this.total = total;
        this.existe = existe;
    }
    
    //Métodos de accceso
    //Get
    public int getIdDetalle() {
        return idDetalle;
    }
    
    public int getIdEmpleado() {
        return idEmpleado;
    }
    
    public String getTipoPago() {
        return tipoPago;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }
    
    public double getTotal() {
        return total;
    }
    
    public boolean isExiste() {
        return existe;
    }
    
    //Set
    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }
    
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
}
