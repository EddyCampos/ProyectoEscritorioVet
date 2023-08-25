//Eddy Campos Jiménez
//21 Agosto 2023
package CapaLogica;

import CapaAccesoDatos.ADProductos;
import CapaEntidades.Productos;
import java.sql.ResultSet;

public class LProductos {
    //Atributos
    private String _mensaje;

    //Métodos de acceso
    public String getMensaje() {
        return _mensaje;
    }
    
    //Llamar al metodo para listar los productos de la capa de acceso
    public ResultSet ListarProductos(String condicion, String orden) throws Exception{
        ResultSet datos = null;
        ADProductos acceso;
        
        try {
            acceso = new ADProductos();
            datos = acceso.ListarProductos(condicion, orden);
        } 
        catch (Exception e) {
            throw e;
        }
        
        return datos;
    }
    
    //Llamar al metodo para listar los alimentos de la capa de acceso
    public ResultSet ListarAlimentos(String condicion, String orden) throws Exception{
        ResultSet datos = null;
        ADProductos acceso;
        
        try {
            acceso = new ADProductos();
            datos = acceso.ListarAlimentos(condicion, orden);
        } 
        catch (Exception e) {
            throw e;
        }
        
        return datos;
    }
    
    //Llamar método para insertar producto de la capa de acceso
    public int InsertarProducto(Productos producto) throws Exception{
        int resultado = -1;
        ADProductos acceso;
        
        try {
            acceso = new ADProductos();
            resultado = acceso.InsertarProducto(producto);
            _mensaje = acceso.getMensaje();
        } 
        catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    
    //Llamar método para actualizar producto de la capa de acceso
    public int ActualizarProducto(Productos producto) throws Exception{
        int resultado = -1;
        ADProductos acceso;
        
        try {
            acceso = new ADProductos();
            resultado = acceso.ActualizarProducto(producto);
            _mensaje = acceso.getMensaje();
        } 
        catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    
    //Llamar obtener producto de acceso a datos
    public Productos ObtenerProducto(String condicion) throws Exception{
        Productos producto;
        ADProductos accesoDatos;
        
        try {
            accesoDatos = new ADProductos();
            producto = accesoDatos.ObtenerProducto(condicion);
            
            if (producto.isExiste()) {
                _mensaje = "Cliente recuperado";
            }
            else{
                _mensaje = "Cliente no existe en la BD";
            }
        } catch (Exception e) {
            throw e;
        }
        
        return producto;
    }
    
    //Llamar al metodo para listar los productos de la capa de acceso
    public ResultSet ListarProductosFactura(String condicion, String orden) throws Exception{
        ResultSet datos = null;
        ADProductos acceso;
        
        try {
            acceso = new ADProductos();
            datos = acceso.ListarProductosFactura(condicion, orden);
        } 
        catch (Exception e) {
            throw e;
        }
        
        return datos;
    }
    
}
