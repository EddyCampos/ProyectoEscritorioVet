/**
 * Eddy Campos Jiménez
 * 16 Agosto 2023
 */
package CapaEntidades;

public class FacturasCompras {
    //Atributos
    private int idFactura;
    private int idDetalle;
    private int idProducto;
    private String nombreProveedor;
    private String telefonoProveedor;
    private int cantidad;
    private boolean existe;
    
    //Constructor vacio
    public FacturasCompras() {
        idFactura = 0;
        idProducto = 0;
        idDetalle = 0;
        nombreProveedor = "";
        telefonoProveedor = "";
        cantidad = 0;
        existe = false;
    }
    
    //Constructor con parametros
    public FacturasCompras(int idFactura, int idDetalle, int idProducto, String nombreProveedor, String telefonoProveedor, int cantidad, boolean existe) {
        this.idFactura = idFactura;
        this.idDetalle = idDetalle;
        this.idProducto = idProducto;
        this.nombreProveedor = nombreProveedor;
        this.telefonoProveedor = telefonoProveedor;
        this.cantidad = cantidad;
        this.existe = existe;
    }
    
    //Metodos de acceso
    //Get
    public int getIdFactura() {
        return idFactura;
    }
    
    public int getIdDetalle() {
        return idDetalle;
    }
    
    public int getIdProducto() {
        return idProducto;
    }
    
    public String getNombreProveedor() {
        return nombreProveedor;
    }
    
    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }
    
    public int getCantidad() {
        return cantidad;
    }

    public boolean isExiste() {
        return existe;
    }
    
    //Set
    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }
    
    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
}
