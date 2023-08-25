//Eddy Campos Jiménez
//22 Agosto 2023
package CapaLogica;

import CapaAccesoDatos.ADFacturaTemporal;
import CapaAccesoDatos.ADFacturasVenta;
import CapaEntidades.FacturaTemporal;
import java.sql.ResultSet;

public class LFacturaTemporal {
    //Atributos
    private String _mensaje;

    //Métodos de acceso
    public String getMensaje() {
        return _mensaje;
    }
    
    //Llamar método para insertar factura temporal de la capa de acceso
    public int InsertarFacturaTemporal(FacturaTemporal factura) throws Exception{
        int resultado = -1;
        ADFacturaTemporal acceso;
        
        try {
            acceso = new ADFacturaTemporal();
            resultado = acceso.InsertarFacturaTemporal(factura);
            _mensaje = acceso.getMensaje();
        } 
        catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    
    //Llamar método para eliminar factura temporal de la capa de acceso
    public int EliminarFacturaTemporal(FacturaTemporal factura) throws Exception{
        int resultado = -1;
        ADFacturaTemporal acceso;
        
        try {
            acceso = new ADFacturaTemporal();
            resultado = acceso.EliminarFacturaTemporal(factura);
            _mensaje = acceso.getMensaje();
        } 
        catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    
    //Llamar al metodo para listar los productos de la capa de acceso
    public ResultSet ListarProductosFactura(String condicion, String orden) throws Exception{
        ResultSet datos = null;
        ADFacturaTemporal acceso;
        
        try {
            acceso = new ADFacturaTemporal();
            datos = acceso.ListarProductosFactura(condicion, orden);
        } 
        catch (Exception e) {
            throw e;
        }
        
        return datos;
    }
    
    //Llamar obtener un producto de acceso a datos
    public FacturaTemporal ObtenerProducto(String condicion) throws Exception{
        FacturaTemporal producto;
        ADFacturaTemporal accesoDatos;
        
        try {
            accesoDatos = new ADFacturaTemporal();
            producto = accesoDatos.ObtenerProducto(condicion);
            _mensaje = accesoDatos.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        
        return producto;
    }
    
    //Llamar al metodo para listar los productos de la capa de acceso
    public int LimpiarFacturaTemporal() throws Exception{
        int resultado = 0;
        ADFacturaTemporal acceso;
        
        try {
            acceso = new ADFacturaTemporal();
            resultado = acceso.LimpiarFacturaTemporal();
        } 
        catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
}
