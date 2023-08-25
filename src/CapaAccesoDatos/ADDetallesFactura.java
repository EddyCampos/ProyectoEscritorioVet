//Eddy Campos Jiménez
//22 Agosto 2023
package CapaAccesoDatos;

import CapaEntidades.DetallesFacturas;
import Config.Config;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class ADDetallesFactura {
    //Atributos
    private Connection _conexion;
    private String _mensaje;
    
    //Constructor
    public ADDetallesFactura() throws Exception{
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
    
    //Método insertar un producto
    public int InsertarDetalleFactura(DetallesFacturas detalle) throws Exception{
        int resultado = -1;
        
        try {
            //Convierte la fecha de date a localDate
            java.sql.Date fechaBaseDatos = java.sql.Date.valueOf( detalle.getFecha());
            
            CallableStatement csp = _conexion.prepareCall("{call SP_AGREGAR_DETALLE_FACTURA(?, ?, ?, ?, ?)}");
            
            //Parametros de entrada
            csp.setInt(1, detalle.getIdEmpleado());
            csp.setString(2, detalle.getTipoPago());
            csp.setDate(3, fechaBaseDatos);            
            csp.setDouble(4, detalle.getTotal());
            csp.setString(5, "");
            
            
            //Parametros de salida
            csp.registerOutParameter(5, Types.VARCHAR);
            
            //Ejecución
            resultado = csp.executeUpdate();
            
            //Recupera parametro de salida
            _mensaje = csp.getString(5);
            
        } 
        catch (Exception e) {
            throw e;
        }
        finally{
            _conexion = null;
        }
        
        return resultado;
    }
    
    //Método para obtener una factura especifica
    public int ObtenerDetalleFactura(String condicion) throws SQLException{
        ResultSet rs = null;
        int resultado = 0;
        
        try {
            Statement stm = _conexion.createStatement();
            String sentencia = "SELECT IDENT_CURRENT('DETALLE_FACTURAS')";
            
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            
            rs = stm.executeQuery(sentencia);
            
            if (rs.next()) {
                resultado = rs.getInt(1);
                
            }
        } catch (Exception e) {
            throw  e;
        }
        finally{
            _conexion = null;
        }
        
        return resultado;
    }
}
