//Eddy Campos Jiménez
//21 Agosto 2023
package CapaLogica;

import CapaAccesoDatos.ADClientes;
import CapaAccesoDatos.ADEmpleados;
import CapaEntidades.Empleados;
import java.sql.ResultSet;

public class LEmpleados {
      //Atributos
    private String _mensaje;

    //Métodos de acceso
    public String getMensaje() {
        return _mensaje;
    }
    
    //Llamar al metodo para listar los clientes de la capa de acceso
    public ResultSet ListarEmpleados(String condicion, String orden) throws Exception{
        ResultSet datos = null;
        ADEmpleados acceso;
        
        try {
            acceso = new ADEmpleados();
            datos = acceso.ListarEmpleados(condicion, orden);
        } 
        catch (Exception e) {
            throw e;
        }
        
        return datos;
    }
    
    //Llamar método para insertar un empleado de la capa de acceso
    public int InsertarEmpleados(Empleados empleado) throws Exception{
        int resultado = -1;
        ADEmpleados acceso;
        
        try {
            acceso = new ADEmpleados();
            resultado = acceso.InsertarEmpleado(empleado);
            _mensaje = acceso.getMensaje();
        } 
        catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    
     //Llamar método para insertar un empleado de la capa de acceso
    public int ActualizarEmpleado(Empleados empleado) throws Exception{
        int resultado = -1;
        ADEmpleados acceso;
        
        try {
            acceso = new ADEmpleados();
            resultado = acceso.ActualizarEmpleado(empleado);
            _mensaje = acceso.getMensaje();
        } 
        catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    
     //Llamar obtener empleado de acceso a datos
    public Empleados ObtenerCliente(String condicion) throws Exception{
        Empleados empleado;
        ADEmpleados accesoDatos;
        
        try {
            accesoDatos = new ADEmpleados();
            empleado = accesoDatos.ObtenerEmpleado(condicion);
            
            if (empleado.isExiste()) {
                _mensaje = "Cliente recuperado";
            }
            else{
                _mensaje = "Cliente no existe en la BD";
            }
        } catch (Exception e) {
            throw e;
        }
        
        return empleado;
    }
    
    //Llamar eliminar de acceso a datos
    public int EliminarEmpleado(Empleados empleados) throws Exception{
        int resultado = -1;
        ADEmpleados accesoDatos;
        
        try {
            accesoDatos = new ADEmpleados();
            resultado = accesoDatos.EliminarEmpleado(empleados);
            _mensaje = accesoDatos.getMensaje();
            
        } catch (Exception ex) {
            throw ex;
        }
        
        return resultado;
    }
}
