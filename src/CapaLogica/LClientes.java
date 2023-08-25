//Eddy Campos Jiménez
//19 Agosto 2023
package CapaLogica;

import CapaAccesoDatos.ADClientes;
import CapaEntidades.Clientes;
import java.sql.ResultSet;

public class LClientes {
    //Atributos
    private String _mensaje;

    //Métodos de acceso
    public String getMensaje() {
        return _mensaje;
    }
    
    //Llamar al metodo para listar los clientes de la capa de acceso
    public ResultSet ListarClientes(String condicion, String orden) throws Exception{
        ResultSet datos = null;
        ADClientes acceso;
        
        try {
            acceso = new ADClientes();
            datos = acceso.ListarClientes(condicion, orden);
        } 
        catch (Exception e) {
            throw e;
        }
        
        return datos;
    }

    //Llamar método para insertar cliente de la capa de acceso
    public int InsertarCliente(Clientes cliente) throws Exception{
        int resultado = -1;
        ADClientes acceso;
        
        try {
            acceso = new ADClientes();
            resultado = acceso.InsertarCliente(cliente);
            _mensaje = acceso.getMensaje();
        } 
        catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    
     //Llamar método para insertar cliente de la capa de acceso
    public int ActualizarCliente(Clientes cliente) throws Exception{
        int resultado = -1;
        ADClientes acceso;
        
        try {
            acceso = new ADClientes();
            resultado = acceso.ActualizarCliente(cliente);
            _mensaje = acceso.getMensaje();
        } 
        catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    
    //Llamar obtener cliente de acceso a datos
    public Clientes ObtenerCliente(String condicion) throws Exception{
        Clientes cliente;
        ADClientes accesoDatos;
        
        try {
            accesoDatos = new ADClientes();
            cliente = accesoDatos.ObtenerCliente(condicion);
            
            if (cliente.isExiste()) {
                _mensaje = "Cliente recuperado";
            }
            else{
                _mensaje = "Cliente no existe en la BD";
            }
        } catch (Exception e) {
            throw e;
        }
        
        return cliente;
    }
    
    //Llamar eliminar de acceso a datos
    public int EliminarCliente(Clientes cliente) throws Exception{
        int resultado = -1;
        ADClientes accesoDatos;
        
        try {
            accesoDatos = new ADClientes();
            resultado = accesoDatos.EliminarCliente(cliente);
            _mensaje = accesoDatos.getMensaje();
            
        } catch (Exception ex) {
            throw ex;
        }
        
        return resultado;
    }
    
}
