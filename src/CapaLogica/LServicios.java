//Eddy Campos Jiménez
//19 Agosto 2023
package CapaLogica;

import CapaAccesoDatos.ADServicios;
import CapaEntidades.Servicios;
import java.sql.ResultSet;

public class LServicios {
    //Atributos
    private String _mensaje;

    //Métodos de acceso
    public String getMensaje() {
        return _mensaje;
    }
    
    //Llamar al metodo para listar los servicios de la capa de acceso
    public ResultSet ListarServicios(String condicion, String orden) throws Exception{
        ResultSet datos = null;
        ADServicios acceso;
        
        try {
            acceso = new ADServicios();
            datos = acceso.ListarServicios(condicion, orden);
        } 
        catch (Exception e) {
            throw e;
        }
        
        return datos;
    }
    
    //Llamar método para insertar servicio de la capa de acceso
    public int InsertarServicio(Servicios servicio) throws Exception{
        int resultado = -1;
        ADServicios acceso;
        
        try {
            acceso = new ADServicios();
            resultado = acceso.InsertarServicio(servicio);
            _mensaje = acceso.getMensaje();
        } 
        catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    
    //Llamar método para insertar servicio de la capa de acceso
    public int ActualizarServicio(Servicios servicio) throws Exception{
        int resultado = -1;
        ADServicios acceso;
        
        try {
            acceso = new ADServicios();
            resultado = acceso.ActualizarServicio(servicio);
            _mensaje = acceso.getMensaje();
        } 
        catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    
    //Llamar obtener servicio de acceso a datos
    public Servicios ObtenerServicio(String condicion) throws Exception{
        Servicios servicio;
        ADServicios accesoDatos;
        
        try {
            accesoDatos = new ADServicios();
            servicio = accesoDatos.ObtenerServicio(condicion);
            
            if (servicio.isExiste()) {
                _mensaje = "Servicio recuperado";
            }
            else{
                _mensaje = "Servicio no existe en la BD";
            }
        } catch (Exception e) {
            throw e;
        }
        
        return servicio;
    }
}
