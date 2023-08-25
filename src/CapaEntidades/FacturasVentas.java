/**
 * Eddy Campos Jiménez
 * 16 Agosto 2023
 */
package CapaEntidades;

public class FacturasVentas {
    //Atributos
    private int idFactura;
    private int idDetalle;
    private int idCliente;
    private int idProd;
    private int idServ;
    private int cantidad;
    private boolean existe;
    
    //Constructor vacio
    public FacturasVentas() {
        idDetalle = 0;
        idFactura = 0;
        idCliente = 0;
        idProd = 0;
        idServ = 0;
        cantidad = 0;
      
        existe = false;
    }
    
    //Constructor con parametros
    public FacturasVentas(int idDetalle, int idFactura, int idCliente, int idProd, int idServ, int cantidad, boolean esProducto, boolean existe) {
        this.idDetalle = idDetalle;
        this.idFactura = idFactura;
        this.idCliente = idCliente;
        this.idProd = idProd;
        this.idServ = idServ;
        this.cantidad = cantidad;
        
        this.existe = existe;
    }
    
    //Métodos de acceso
    //Get
    public int getIdFactura() {
        return idFactura;
    }
    
    public int getIdDetalle() {
        return idDetalle;
    }

    public int getIdCliente() {
        return idCliente;
    }
    
    public int getidProd() {
        return idProd;
    }
    
    public int getidServ() {
        return idServ;
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

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }
    
    public void setidServ(int idServ) {
        this.idServ = idServ;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
  

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

}
