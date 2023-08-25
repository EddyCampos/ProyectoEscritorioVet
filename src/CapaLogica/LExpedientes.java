//Eddy Campos Jiménez
//21Agosto 2023
package CapaLogica;

import CapaAccesoDatos.ADExpedientes;
import CapaEntidades.ExpedientesMascotas;
import java.sql.ResultSet;

public class LExpedientes {
    //Atributos
    private String _mensaje;

    //Métodos de acceso
    public String getMensaje() {
        return _mensaje;
    }
    
    //Llamar al metodo para listar los clientes de la capa de acceso
    public ResultSet ListarExpedientes(String condicion, String orden) throws Exception{
        ResultSet datos = null;
        ADExpedientes acceso;
        
        try {
            acceso = new ADExpedientes();
            datos = acceso.ListarExpedientes(condicion, orden);
        } 
        catch (Exception e) {
            throw e;
        }
        
        return datos;
    }
    
    //Llamar método para insertar expedientes de la capa de acceso
    public int InsertarExpediente(ExpedientesMascotas expediente) throws Exception{
        int resultado = -1;
        ADExpedientes acceso;
        
        try {
            acceso = new ADExpedientes();
            resultado = acceso.InsertarExpediente(expediente);
            _mensaje = acceso.getMensaje();
        } 
        catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    
    //Llamar método para actualizar expedientes de la capa de acceso
    public int ActualizarExpediente(ExpedientesMascotas expediente) throws Exception{
        int resultado = -1;
        ADExpedientes acceso;
        
        try {
            acceso = new ADExpedientes();
            resultado = acceso.ActualizarExpediente(expediente);
            _mensaje = acceso.getMensaje();
        } 
        catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    //Llamar obtener cliente de acceso a datos
    public ExpedientesMascotas ObtenerCliente(String condicion) throws Exception{
        ExpedientesMascotas expediente;
        ADExpedientes accesoDatos;
        
        try {
            accesoDatos = new ADExpedientes();
            expediente = accesoDatos.ObtenerExpediente(condicion);
            
            if (expediente.isExiste()) {
                _mensaje = "Cliente recuperado";
            }
            else{
                _mensaje = "Cliente no existe en la BD";
            }
        } catch (Exception e) {
            throw e;
        }
        
        return expediente;
    }
}
