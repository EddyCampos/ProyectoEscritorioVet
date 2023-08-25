//Eddy Campos Jiménez
//22 Agosto 2023
package CapaAccesoDatos;

import CapaEntidades.FacturaTemporal;
import Config.Config;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class ADFacturaTemporal {
    //Atributos
    private Connection _conexion;
    private String _mensaje;
    
    //Constructor
    public ADFacturaTemporal() throws Exception{
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
    
    //Método insertar una factura temporal
    public int InsertarFacturaTemporal(FacturaTemporal factura) throws Exception{
        int resultado = -1;
        
        try {
            CallableStatement csp = _conexion.prepareCall("{call SP_AGREGAR_FACTURA_TEMPORAL(?, ?, ?, ?, ?, ?, ?, ?)}");
            
            //Parametros de entrada
            csp.setInt(1, factura.getIdProducto());
            csp.setString(2, factura.getNombre());
            csp.setInt(3, factura.getPeso());
            csp.setInt(4, factura.getPrecio());
            csp.setInt(5, factura.getCantidad());
            csp.setDouble(6, factura.getImpuesto());
            csp.setDouble(7, factura.getTotal());
            csp.setString(8, "");
            
            //Parametros de salida
            csp.registerOutParameter(8, Types.VARCHAR);
            
            //Ejecución
            resultado = csp.executeUpdate();
            
            //Recupera parametro de salida
            _mensaje = csp.getString(8);
            
        } 
        catch (Exception e) {
            throw e;
        }
        finally{
            _conexion = null;
        }
        
        return resultado;
    }
    
    //Método eliminar una factura temporal
    public int EliminarFacturaTemporal(FacturaTemporal factura) throws Exception{
        int resultado = -1;
        
        try {
            CallableStatement csp = _conexion.prepareCall("{call SP_ELIMINAR_FACTURA_TEMPORAL(?, ?)}");
            
            //Parametros de entrada
            csp.setInt(1, factura.getIdProducto());
            csp.setString(2, "");
            
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
    
    //Método limpia la tabla completa
    public int LimpiarFacturaTemporal() throws Exception{
        int resultado = -1;
        
         try {
            String sentencia = "DELETE FROM FACTURA_TEMPORAL";
            PreparedStatement pstm = _conexion.prepareStatement(sentencia);
            
            resultado = pstm.executeUpdate();
            
            if (resultado > 0) {
                _mensaje = "Se limpió la tabla";
            }
        } 
        catch (Exception ex) {
            throw ex;
        }
        finally{
            _conexion = null;
        }
        
        return resultado;
    }
    
     //Método listar los productos
    public ResultSet ListarProductosFactura(String condicion, String orden) throws Exception{
        ResultSet datos = null;
        
        try {
            Statement stm = _conexion.createStatement();
            String sentencia = "SELECT ID_PRODUCTO, NOMBRE, PESO, PRECIO, CANTIDAD, IMPUESTO, TOTAL FROM FACTURA_TEMPORAL";
            
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
    
    //Método para obtener un empelado especifico
    public FacturaTemporal ObtenerProducto(String condicion) throws SQLException{
        FacturaTemporal producto = new FacturaTemporal();
        ResultSet rs = null;
        
        try {
            Statement stm = _conexion.createStatement();
            String sentencia = "SELECT P.ID_PRODUCTO, NOMBRE, COALESCE(PESO, 0) AS PESO, PRECIO_VENTA, FV.CANTIDAD,	CAST((PRECIO_VENTA - (PRECIO_VENTA / 1.13)) AS decimal(10,2)) AS IMPUESTO, (FV.CANTIDAD * CAST(PRECIO_VENTA + (PRECIO_VENTA - (PRECIO_VENTA / 1.13)) AS decimal(10,2))) AS TOTAL FROM PRODUCTOS P INNER JOIN FACTURAS_VENTAS FV ON P.ID_PRODUCTO = FV.ID_PRODUCTO";
            
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            
            rs = stm.executeQuery(sentencia);
            
            if (rs.next()) {
                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setPeso(rs.getInt(3));
                producto.setPrecio(rs.getInt(4));
                producto.setCantidad(rs.getInt(5));
                producto.setImpuesto(rs.getDouble(6));
                producto.setTotal(rs.getDouble(7));
            }
        } catch (Exception e) {
            throw  e;
        }
        finally{
            _conexion = null;
        }
        
        return producto;
    }
}
