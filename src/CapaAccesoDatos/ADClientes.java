//Eddy Campos Jiménez
//19 Agosto 2023
package CapaAccesoDatos;

import CapaEntidades.Clientes;
import Config.Config;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class ADClientes {
    //Atributos
    private Connection _conexion;
    private String _mensaje;
    
    //Constructor
    public ADClientes() throws Exception{
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
    public ResultSet ListarClientes(String condicion, String orden) throws Exception{
        ResultSet datos = null;
        
        try {
            Statement stm = _conexion.createStatement();
            String sentencia = "SELECT ID_CLIENTE, NOMBRE, APELLIDO01, APELLIDO02, CEDULA, PROVINCIA, ESTADO_CIVIL, GENERO, FECHA_NACIMIENTO, EMAIL, TELEFONO FROM CLIENTES";
            
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
    
    //Método insertar un cliente
    public int InsertarCliente(Clientes cliente) throws Exception{
        int resultado = -1;
        
        try {
            CallableStatement csp = _conexion.prepareCall("{call SP_AGREGAR_CLIENTE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            
            //Parametros de entrada
            csp.setInt(1, cliente.getIdCLiente());
            csp.setString(2, cliente.getNombre());
            csp.setString(3, cliente.getApellido01());
            csp.setString(4, cliente.getApellido02());
            csp.setString(5, cliente.getCedula());
            csp.setString(6, cliente.getProvincia());
            csp.setString(7, cliente.getEstadoCivil());
            csp.setString(8, cliente.getGenero());
            csp.setDate(9, cliente.getFechaNacimiento());
            csp.setString(10, cliente.getEmail());
            csp.setString(11, cliente.getTelefono());
            csp.setString(12, "");
            
            //Parametros de salida
            csp.registerOutParameter(12, Types.VARCHAR);
            
            //Ejecución
            resultado = csp.executeUpdate();
            
            //Recupera parametro de salida
            _mensaje = csp.getString(12);
            
        } 
        catch (Exception e) {
            throw e;
        }
        finally{
            _conexion = null;
        }
        
        return resultado;
    }
    
    //Método insertar un cliente
    public int ActualizarCliente(Clientes cliente) throws Exception{
        int resultado = -1;
        
        try {
            CallableStatement csp = _conexion.prepareCall("{call SP_ACTUALIZAR_CLIENTE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            
            //Parametros de entrada
            csp.setInt(1, cliente.getIdCLiente());
            csp.setString(2, cliente.getNombre());
            csp.setString(3, cliente.getApellido01());
            csp.setString(4, cliente.getApellido02());
            csp.setString(5, cliente.getCedula());
            csp.setString(6, cliente.getProvincia());
            csp.setString(7, cliente.getEstadoCivil());
            csp.setString(8, cliente.getGenero());
            csp.setDate(9, cliente.getFechaNacimiento());
            csp.setString(10, cliente.getEmail());
            csp.setString(11, cliente.getTelefono());
            csp.setString(12, "");
            
            //Parametros de salida
            csp.registerOutParameter(12, Types.VARCHAR);
            
            //Ejecución
            resultado = csp.executeUpdate();
            
            //Recupera parametro de salida
            _mensaje = csp.getString(12);
            
        } 
        catch (Exception e) {
            throw e;
        }
        finally{
            _conexion = null;
        }
        
        return resultado;
    }
    
    //Método eliminar un cliente
    public int EliminarCliente(Clientes cliente) throws Exception{
        int resultado = -1;
        
        try {
            CallableStatement csp = _conexion.prepareCall("{call SP_ELIMINAR_CLIENTE(?, ?)}");
            
            //Parametros de entrada
            csp.setInt(1, cliente.getIdCLiente());
            
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
    
    //Método para obtener un cliente especifico
    public Clientes ObtenerCliente(String condicion) throws SQLException{
        Clientes cliente = new Clientes();
        ResultSet rs = null;
        
        try {
            Statement stm = _conexion.createStatement();
            String sentencia = "SELECT ID_CLIENTE, NOMBRE, APELLIDO01, APELLIDO02, CEDULA, PROVINCIA, ESTADO_CIVIL, GENERO, FECHA_NACIMIENTO, EMAIL, TELEFONO FROM CLIENTES";
            
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            
            rs = stm.executeQuery(sentencia);
            
            if (rs.next()) {
                cliente.setIdCLiente(rs.getInt(1));
                cliente.setNombre(rs.getString(2));
                cliente.setApellido01(rs.getString(3));
                cliente.setApellido02(rs.getString(4));
                cliente.setCedula(rs.getString(5));
                cliente.setProvincia(rs.getString(6));
                cliente.setEstadoCivil(rs.getString(7));
                cliente.setGenero(rs.getString(8));
                cliente.setFechaNacimiento(rs.getDate(9));
                cliente.setEmail(rs.getString(10));
                cliente.setTelefono(rs.getString(11));
                
            }
        } catch (Exception e) {
            throw  e;
        }
        finally{
            _conexion = null;
        }
        
        return cliente;
    }

    
    
}
