//Eddy Campos Jiménez
//22 Agosto 2023
package CapaEntidades;

public class FacturaTemporal {

    //Atributos
    private int idProducto;
    private String nombre;
    private int peso;
    private int precio;
    private int cantidad;
    private double impuesto;
    private double total;
    private boolean esProducto;
    private boolean existe;
    
    //Constructor vacio    
    public FacturaTemporal() {
        idProducto = 0;
        nombre = "";
        peso = 0;
        precio = 0;
        cantidad = 0;
        impuesto = 0;
        total = 0;
        esProducto = false;        
        existe = false;
    }
    
    //Constructor con parametros
    public FacturaTemporal(int idProducto, String nombre, int peso, int precio, int cantidad, double impuesto, double total, boolean esProducto, boolean existe) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.peso = peso;
        this.precio = precio;
        this.cantidad = cantidad;
        this.impuesto = impuesto;
        this.total = total;
        this.esProducto = esProducto;
        this.existe = existe;
    }

    //Metodos de acceso
    //Get
    public int getIdProducto() {
        return idProducto;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public int getPeso() {
        return peso;
    }
    
    public int getPrecio() {
        return precio;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public double getTotal() {
        return total;
    }
    
    public double getImpuesto() {
        return impuesto;
    }
    
    public boolean getEsProducto() {
        return esProducto;
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

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public void setEsProducto(boolean existe) {
        this.esProducto = esProducto;
    }
    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    
    
}
