/**
 * Eddy Campos Jiménez
 * 24 Agosto 2023
 */
package CapaAccesoDatos;

import Config.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ADInformes {
    //Atributos
    private Connection _conexion;
    private String _mensaje;
    
    //Constructor
    public ADInformes() throws Exception{
        try {
            String url = Config.getConnectionString();
            //Abre la conexión
            _conexion = DriverManager.getConnection(url);
            _mensaje = "";
        } catch (Exception e) {
            throw e;
        }
    }
    
    //Método acceso mensaje
    public String getMensaje() {
        return _mensaje;
    }
    
    //Método listar los informes mensuales
    public ResultSet ListarInformesMensuales(String condicion, String orden) throws Exception{
        ResultSet datos = null;
        
        try {
            Statement stm = _conexion.createStatement();
            String sentencia = "SELECT NOMBRE, MES, CANTIDAD_VENTAS, TOTAL_VENDIDO FROM RESUMEN_MENSUAL";
            
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            
            if (!orden.equals("")) {
                sentencia = String.format("%s ORDER %s", sentencia, orden);
            }
            
            datos = stm.executeQuery(sentencia);
        } 
        catch (Exception ex) {
            throw ex;
        }
        finally{
            _conexion = null;
        }
        
        return datos;
    }
    
     //Método listar los informes mensuales
    public ResultSet ListarInformesEmpleados(String condicion, String orden) throws Exception{
        ResultSet datos = null;
        
        try {
            Statement stm = _conexion.createStatement();
            String sentencia = "SELECT NOMBRE, CEDULA, CANTIDAD_VENTAS, TOTAL_VENDIDO FROM RESUMEN_VENDEDOR";
            
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            
            if (!orden.equals("")) {
                sentencia = String.format("%s ORDER %s", sentencia, orden);
            }
            
            datos = stm.executeQuery(sentencia);
        } 
        catch (Exception ex) {
            throw ex;
        }
        finally{
            _conexion = null;
        }
        
        return datos;
    }
}
