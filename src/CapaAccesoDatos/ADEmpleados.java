//Eddy Campos Jiménez
//21 Agosto 2023
package CapaAccesoDatos;

import CapaEntidades.Empleados;
import Config.Config;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;

public class ADEmpleados {
     //Atributos
    private Connection _conexion;
    private String _mensaje;
    
    //Constructor
    public ADEmpleados() throws Exception{
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
    
    //Método listar los clientes
    public ResultSet ListarEmpleados(String condicion, String orden) throws Exception{
        ResultSet datos = null;
        
        try {
            Statement stm = _conexion.createStatement();
            String sentencia = "SELECT ID_EMPLEADO, TIPO, NOMBRE, APELLIDO01, APELLIDO02, CEDULA, PROVINCIA, ESTADO_CIVIL, GENERO, FECHA_NACIMIENTO, EMAIL, TELEFONO FROM EMPLEADOS";
            
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
    
    //Método insertar un empleado
    public int InsertarEmpleado(Empleados empleado) throws Exception{
        int resultado = -1;
        
        try {
            CallableStatement csp = _conexion.prepareCall("{call SP_AGREGAR_EMPLEADO(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            
            //Parametros de entrada
            csp.setInt(1, empleado.getIdEmpleado());
            csp.setString(2, empleado.getTipoEmpleado());
            csp.setString(3, empleado.getNombre());
            csp.setString(4, empleado.getApellido01());
            csp.setString(5, empleado.getApellido02());
            csp.setString(6, empleado.getCedula());
            csp.setString(7, empleado.getProvincia());
            csp.setString(8, empleado.getEstadoCivil());
            csp.setString(9, empleado.getGenero());
            csp.setDate(10, empleado.getFechaNacimiento());
            csp.setString(11, empleado.getEmail());
            csp.setString(12, empleado.getTelefono());
            csp.setString(13, "");
            
            //Parametros de salida
            csp.registerOutParameter(13, Types.VARCHAR);
            
            //Ejecución
            resultado = csp.executeUpdate();
            
            //Recupera parametro de salida
            _mensaje = csp.getString(13);
            
        } 
        catch (Exception e) {
            throw e;
        }
        finally{
            _conexion = null;
        }
        
        return resultado;
    }
    
    
    //Método insertar un empleado
    public int ActualizarEmpleado(Empleados empleado) throws Exception{
        int resultado = -1;
        
        try {
            CallableStatement csp = _conexion.prepareCall("{call SP_ACTUALIZAR_EMPLEADO(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            
            //Parametros de entrada
            csp.setInt(1, empleado.getIdEmpleado());
            csp.setString(2, empleado.getTipoEmpleado());
            csp.setString(3, empleado.getNombre());
            csp.setString(4, empleado.getApellido01());
            csp.setString(5, empleado.getApellido02());
            csp.setString(6, empleado.getCedula());
            csp.setString(7, empleado.getProvincia());
            csp.setString(8, empleado.getEstadoCivil());
            csp.setString(9, empleado.getGenero());
            csp.setDate(10, empleado.getFechaNacimiento());
            csp.setString(11, empleado.getEmail());
            csp.setString(12, empleado.getTelefono());
            csp.setString(13, "");
            
            //Parametros de salida
            csp.registerOutParameter(13, Types.VARCHAR);
            
            //Ejecución
            resultado = csp.executeUpdate();
            
            //Recupera parametro de salida
            _mensaje = csp.getString(13);
            
        } 
        catch (Exception e) {
            throw e;
        }
        finally{
            _conexion = null;
        }
        
        return resultado;
    }
    

    //Método para obtener un empelado especifico
    public Empleados ObtenerEmpleado(String condicion) throws SQLException{
        Empleados empleado = new Empleados();
        ResultSet rs = null;
        
        try {
            Statement stm = _conexion.createStatement();
            String sentencia = "SELECT ID_EMPLEADO, TIPO, NOMBRE, APELLIDO01, APELLIDO02, CEDULA, PROVINCIA, ESTADO_CIVIL, GENERO, FECHA_NACIMIENTO, EMAIL, TELEFONO FROM EMPLEADOS";
            
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            
            rs = stm.executeQuery(sentencia);
            Date fecha = new Date();
            
            if (rs.next()) {
                empleado.setIdEmpleado(rs.getInt(1));
                empleado.setTipoEmpleado(rs.getString(2));
                empleado.setNombre(rs.getString(3));
                empleado.setApellido01(rs.getString(4));
                empleado.setApellido02(rs.getString(5));
                empleado.setCedula(rs.getString(6));
                empleado.setProvincia(rs.getString(7));
                empleado.setEstadoCivil(rs.getString(8));
                empleado.setGenero(rs.getString(9));
                empleado.setFechaNacimiento(rs.getDate(10));
                empleado.setEmail(rs.getString(11));
                empleado.setTelefono(rs.getString(12));
                
            }
        } catch (Exception e) {
            throw  e;
        }
        finally{
            _conexion = null;
        }
        
        return empleado;
    }
    
    //Método eliminar un empleado
    public int EliminarEmpleado(Empleados empleado) throws Exception{
        int resultado = -1;
        
        try {
            CallableStatement csp = _conexion.prepareCall("{call SP_ELIMINAR_EMPLEADO(?, ?)}");
            
            //Parametros de entrada
            csp.setInt(1, empleado.getIdEmpleado());
            
            //Parametros de salida
            csp.registerOutParameter(2, Types.VARCHAR);
            
            //Ejecución
            resultado = csp.executeUpdate();
            
            //Recupera parametro de salida
            _mensaje = csp.getString(2);
            
        } 
        catch (Exception e) {
            throw e;
        }
        finally{
            _conexion = null;
        }
        
        return resultado;
    }
}
