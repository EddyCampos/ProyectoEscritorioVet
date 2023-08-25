//Eddy Campos Jiménez
//21 Agosto 2023
package CapaAccesoDatos;

import CapaEntidades.ExpedientesMascotas;
import Config.Config;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class ADExpedientes {
    //Atributos
    private Connection _conexion;
    private String _mensaje;
    
    //Constructor
    public ADExpedientes() throws Exception{
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
    
    //Método listar los Expedientes
    public ResultSet ListarExpedientes(String condicion, String orden) throws Exception{
        ResultSet datos = null;
        
        try {
            Statement stm = _conexion.createStatement();
            String sentencia = "SELECT ID_EXPEDIENTE, ID_CLIENTE, NOMBRE, ESPECIE, RAZA, SEXO, FECHA_NACIMIENTO, PESO FROM EXPEDIENTES_MASCOTAS";
            
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
    
    //Método insertar un expediente
    public int InsertarExpediente(ExpedientesMascotas expediente) throws Exception{
        int resultado = -1;
        
        try {
            //Convierte la fecha de date a localDate
            java.sql.Date fechaBaseDatos = java.sql.Date.valueOf( expediente.getFechaNacimiento());
            
            CallableStatement csp = _conexion.prepareCall("{call SP_AGREGAR_EXPEDIENTE(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            
            //Parametros de entrada
            csp.setInt(1, expediente.getIdExpediente());
            csp.setInt(2, expediente.getIdCliente());
            csp.setString(3, expediente.getNombre());
            csp.setString(4, expediente.getEspecie());
            csp.setString(5, expediente.getRaza());
            csp.setString(6, expediente.getSexo());
            csp.setDate(7, fechaBaseDatos);
            csp.setInt(8, expediente.getPeso());
            csp.setString(9, "");

            //Parametros de salida
            csp.registerOutParameter(9, Types.VARCHAR);
            
            //Ejecución
            resultado = csp.executeUpdate();
            
            //Recupera parametro de salida
            _mensaje = csp.getString(9);
            
        } 
        catch (Exception e) {
            throw e;
        }
        finally{
            _conexion = null;
        }
        
        return resultado;
    }
    
    //Método insertar un expediente
    public int ActualizarExpediente(ExpedientesMascotas expediente) throws Exception{
        int resultado = -1;
        
        try {
            //Convierte la fecha de date a localDate
            java.sql.Date fechaBaseDatos = java.sql.Date.valueOf( expediente.getFechaNacimiento());
            
            CallableStatement csp = _conexion.prepareCall("{call SP_ACTUALIZAR_EXPEDIENTE(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            
            //Parametros de entrada
            csp.setInt(1, expediente.getIdExpediente());
            csp.setInt(2, expediente.getIdCliente());
            csp.setString(3, expediente.getNombre());
            csp.setString(4, expediente.getEspecie());
            csp.setString(5, expediente.getRaza());
            csp.setString(6, expediente.getSexo());
            csp.setDate(7, fechaBaseDatos);
            csp.setInt(8, expediente.getPeso());
            csp.setString(9, "");

            //Parametros de salida
            csp.registerOutParameter(9, Types.VARCHAR);
            
            //Ejecución
            resultado = csp.executeUpdate();
            
            //Recupera parametro de salida
            _mensaje = csp.getString(9);
            
        } 
        catch (Exception e) {
            throw e;
        }
        finally{
            _conexion = null;
        }
        
        return resultado;
    }
    
    //Método para obtener un cliente especifico
    public ExpedientesMascotas ObtenerExpediente(String condicion) throws SQLException{
        ExpedientesMascotas expediente = new ExpedientesMascotas();
        ResultSet rs = null;
        
        try {
            Statement stm = _conexion.createStatement();
            String sentencia = "SELECT ID_EXPEDIENTE, ID_CLIENTE, NOMBRE, ESPECIE, RAZA, SEXO, FECHA_NACIMIENTO, PESO FROM EXPEDIENTES_MASCOTAS";
            
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            
            rs = stm.executeQuery(sentencia);
            
            if (rs.next()) {
                expediente.setIdExpediente(rs.getInt(1));
                expediente.setIdCliente(rs.getInt(2));
                expediente.setNombre(rs.getString(3));
                expediente.setEspecie(rs.getString(4));
                expediente.setRaza(rs.getString(5));
                expediente.setSexo(rs.getString(6));
                expediente.setFechaNacimiento(rs.getDate(7).toLocalDate());
                expediente.setPeso(rs.getInt(8));
                
            }
        } catch (Exception e) {
            throw  e;
        }
        finally{
            _conexion = null;
        }
        
        return expediente;
    }
    
}
