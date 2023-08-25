//Eddy Campos Jiménez
//19 Agosto 2023
package CapaAccesoDatos;

import CapaEntidades.Servicios;
import Config.Config;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class ADServicios {
    //Atributos
    private Connection _conexion;
    private String _mensaje;
    
    //Constructor
    public ADServicios() throws Exception{
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
    
    //Método listar los servicios
    public ResultSet ListarServicios(String condicion, String orden) throws Exception{
        ResultSet datos = null;
        
        try {
            Statement stm = _conexion.createStatement();
            String sentencia = "SELECT ID_SERVICIO, NOMBRE, TIPO, NOMBRE_RESPONSABLE, TELEFONO_RESPONSABLE, PRECIO FROM SERVICIOS";
            
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
    
    //Método insertar un servicio
    public int InsertarServicio(Servicios servicio) throws Exception{
        int resultado = -1;
        
        try {
            CallableStatement csp = _conexion.prepareCall("{call SP_AGREGAR_SERVICIO(?, ?, ?, ?, ?, ?, ?)}");
            
            //Parametros de entrada
            csp.setInt(1, servicio.getIdServicio());
            csp.setString(2, servicio.getNombre());
            csp.setString(3, servicio.getTipo());
            csp.setString(4, servicio.getNombreResponsable());
            csp.setString(5, servicio.getTelefonoResponsable());
            csp.setInt(6, servicio.getPrecio());
            csp.setString(7, "");
            
            //Parametros de salida
            csp.registerOutParameter(7, Types.VARCHAR);
            
            //Ejecución
            resultado = csp.executeUpdate();
            
            //Recupera parametro de salida
            _mensaje = csp.getString(7);
            
        } 
        catch (Exception e) {
            throw e;
        }
        finally{
            _conexion = null;
        }
        
        return resultado;
    }
    
    //Método insertar un servicio
    public int ActualizarServicio(Servicios servicio) throws Exception{
        int resultado = -1;
        
        try {
            CallableStatement csp = _conexion.prepareCall("{call SP_ACTUALIZAR_SERVICIO(?, ?, ?, ?, ?, ?, ?)}");
            
            //Parametros de entrada
            csp.setInt(1, servicio.getIdServicio());
            csp.setString(2, servicio.getNombre());
            csp.setString(3, servicio.getTipo());
            csp.setString(4, servicio.getNombreResponsable());
            csp.setString(5, servicio.getTelefonoResponsable());
            csp.setInt(6, servicio.getPrecio());
            csp.setString(7, "");
            
            //Parametros de salida
            csp.registerOutParameter(7, Types.VARCHAR);
            
            //Ejecución
            resultado = csp.executeUpdate();
            
            //Recupera parametro de salida
            _mensaje = csp.getString(7);
            
        } 
        catch (Exception e) {
            throw e;
        }
        finally{
            _conexion = null;
        }
        
        return resultado;
    }
    
    //Método para obtener un servicio especifico
    public Servicios ObtenerServicio(String condicion) throws SQLException{
        Servicios servicio = new Servicios();
        ResultSet rs = null;
        
        try {
            Statement stm = _conexion.createStatement();
            String sentencia = "SELECT ID_SERVICIO, NOMBRE, TIPO, NOMBRE_RESPONSABLE, TELEFONO_RESPONSABLE, PRECIO FROM SERVICIOS";
            
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            
            rs = stm.executeQuery(sentencia);
            
            if (rs.next()) {
                servicio.setIdServicio(rs.getInt(1));
                servicio.setNombre(rs.getString(2));
                servicio.setTipo(rs.getString(3));
                servicio.setNombreResponsable(rs.getString(4));
                servicio.setTelefonoResponsable(rs.getString(5));
                servicio.setPrecio(rs.getInt(6));
            }
        } catch (Exception e) {
            throw  e;
        }
        finally{
            _conexion = null;
        }
        
        return servicio;
    }
}
