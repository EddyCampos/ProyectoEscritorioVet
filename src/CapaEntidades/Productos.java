/**
 * Eddy Campos Jiménez
 * 16 Agosto 2023
 */
package CapaEntidades;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class Productos {
    //Atributos
    private int idProducto;
    private String nombre;
    private String marca;
    private String tipo;
    private int cantidad;
    private LocalDate fechaIngreso;
    private String nombreProveedor;
    private String telefonoProveedor;
    private int precioCompra;
    private int precioVenta;
    private String disponible;
    private LocalDate fechaCaducidad;
    private int peso;
    private boolean existe;
    
    //Constructor vacio
    public Productos() {
        idProducto = 0;
        nombre = "";
        marca = "";
        tipo = "";
        cantidad = 0;
        fechaIngreso = LocalDate.now();
        nombreProveedor = "";
        telefonoProveedor = "";
        precioCompra = 0;
        precioVenta = 0;
        disponible = "";
        fechaCaducidad = LocalDate.now();
        peso = 0;
        existe = false;
    }
    
    //Constructor con parametros
    public Productos(int idProducto, String nombre, String marca, String tipo, int cantidad, LocalDate fechaIngreso, String nombreProveedor, String telefonoProveedor, int precioCompra, int precioVenta, String disponible, LocalDate fechaCaducidad, int peso, boolean existe) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fechaIngreso = fechaIngreso;
        this.nombreProveedor = nombreProveedor;
        this.telefonoProveedor = telefonoProveedor;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.disponible = disponible;
        this.fechaCaducidad = fechaCaducidad;
        this.peso = peso;
        this.existe = existe;
    }
    
    //Métodos de acceso
    //Get
    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getMarca() {
        return marca;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }
    
    public String getNombreProveedor() {
        return nombreProveedor;
    }
    
    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }
    
    public int getPrecioCompra() {
        return precioCompra;
    }
    
    public int getPrecioVenta() {
        return precioVenta;
    }
    
    public String getDisponible() {
        return disponible;
    }
    
    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }
    
    public int getPeso() {
        return peso;
    }
    
    public boolean isExiste() {
        return existe;
    }
    
    //Set
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public void setPrecioCompra(int precioCompra) {
        this.precioCompra = precioCompra;
    }

    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }
    
    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
    
    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    @Override
    public String toString() {
        return "---------------------------------------"
                + "Nombre: " + nombre
                + "Marca: " + marca;
    }
    
    
    
}
