//Eddy Campos Jiménez
//21 Agosto 2023
package CapaAccesoDatos;

import CapaEntidades.Productos;
import Config.Config;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class ADProductos {
    //Atributos
    private Connection _conexion;
    private String _mensaje;
    
    //Constructor
    public ADProductos() throws Exception{
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
    
    //Método listar los productos
    public ResultSet ListarProductos(String condicion, String orden) throws Exception{
        ResultSet datos = null;
        
        try {
            Statement stm = _conexion.createStatement();
            String sentencia = "SELECT ID_PRODUCTO, NOMBRE, MARCA, TIPO, CANTIDAD, FECHA_INGRESO, NOMBRE_PROVEEDOR, TELEFONO_PROVEEDOR, PRECIO_COMPRA, PRECIO_VENTA, DISPONIBLE FROM VISTA_PRODUCTOS";
            
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
    
    //Método listar los productos
    public ResultSet ListarAlimentos(String condicion, String orden) throws Exception{
        ResultSet datos = null;
        
        try {
            Statement stm = _conexion.createStatement();
            String sentencia = "SELECT ID_PRODUCTO, NOMBRE, MARCA, TIPO, CANTIDAD, FECHA_INGRESO, NOMBRE_PROVEEDOR, TELEFONO_PROVEEDOR, PRECIO_COMPRA, PRECIO_VENTA, DISPONIBLE, FECHA_CADUCIDAD, PESO FROM VISTA_ALIMENTOS";
            
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
    
    //Método insertar un producto
    public int InsertarProducto(Productos producto) throws Exception{
        int resultado = -1;
        
        try {
            //Convierte la fecha de date a localDate
            java.sql.Date fechaIngreso = java.sql.Date.valueOf( producto.getFechaIngreso());
            java.sql.Date fechaCaducidad = java.sql.Date.valueOf( producto.getFechaIngreso());
            
            
            CallableStatement csp = _conexion.prepareCall("{call SP_AGREGAR_PRODUCTOS(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            
            //Parametros de entrada
            csp.setInt(1, producto.getIdProducto());
            csp.setString(2, producto.getNombre());
            csp.setString(3, producto.getMarca());
            csp.setString(4, producto.getTipo());
            csp.setInt(5, producto.getCantidad());
            csp.setDate(6, fechaIngreso);
            csp.setString(7, producto.getNombreProveedor());
            csp.setString(8, producto.getTelefonoProveedor());
            csp.setInt(9, producto.getPrecioCompra());
            csp.setInt(10, producto.getPrecioVenta());
            csp.setString(11, producto.getDisponible());
            csp.setDate(12, fechaCaducidad);
            csp.setInt(13, producto.getPeso());
            csp.setString(14, "");
            
            //Parametros de salida
            csp.registerOutParameter(14, Types.VARCHAR);
            
            //Ejecución
            resultado = csp.executeUpdate();
            
            //Recupera parametro de salida
            _mensaje = csp.getString(14);
            
        } 
        catch (Exception e) {
            throw e;
        }
        finally{
            _conexion = null;
        }
        
        return resultado;
    }
    
    //Método actualizar un producto
    public int ActualizarProducto(Productos producto) throws Exception{
        int resultado = -1;
        
        try {
            //Convierte la fecha de date a localDate
            java.sql.Date fechaIngreso = java.sql.Date.valueOf( producto.getFechaIngreso());
            java.sql.Date fechaCaducidad = java.sql.Date.valueOf( producto.getFechaIngreso());
            
            CallableStatement csp = _conexion.prepareCall("{call SP_ACTUALIZAR_PRODUCTO(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            
            //Parametros de entrada
            csp.setInt(1, producto.getIdProducto());
            csp.setString(2, producto.getNombre());
            csp.setString(3, producto.getMarca());
            csp.setString(4, producto.getTipo());
            csp.setInt(5, producto.getCantidad());
            csp.setDate(6, fechaIngreso);
            csp.setString(7, producto.getNombreProveedor());
            csp.setString(8, producto.getTelefonoProveedor());
            csp.setInt(9, producto.getPrecioCompra());
            csp.setInt(10, producto.getPrecioVenta());
            csp.setString(11, producto.getDisponible());
            csp.setDate(12, fechaCaducidad);
            csp.setInt(13, producto.getPeso());            
            csp.setString(14, "");
            
            //Parametros de salida
            csp.registerOutParameter(14, Types.VARCHAR);
            
            //Ejecución
            resultado = csp.executeUpdate();
            
            //Recupera parametro de salida
            _mensaje = csp.getString(14);
            
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
    public Productos ObtenerProducto(String condicion) throws SQLException{
        Productos producto = new Productos();
        ResultSet rs = null;
        
        try {
            Statement stm = _conexion.createStatement();
            String sentencia = "SELECT ID_PRODUCTO, NOMBRE, MARCA, TIPO, CANTIDAD, FECHA_INGRESO, NOMBRE_PROVEEDOR, TELEFONO_PROVEEDOR, PRECIO_COMPRA, PRECIO_VENTA, DISPONIBLE FROM PRODUCTOS";
            
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            
            rs = stm.executeQuery(sentencia);
            
            if (rs.next()) {
                producto.setIdProducto(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setMarca(rs.getString(3));
                producto.setTipo(rs.getString(4));
                producto.setCantidad(rs.getInt(5));
                producto.setFechaIngreso(rs.getDate(6).toLocalDate());
                producto.setNombreProveedor(rs.getString(7));
                producto.setTelefonoProveedor(rs.getString(8));
                producto.setPrecioCompra(rs.getInt(9));
                producto.setPrecioVenta(rs.getInt(10));
                producto.setDisponible(rs.getString(11));
                
            }
        } catch (Exception e) {
            throw  e;
        }
        finally{
            _conexion = null;
        }
        
        return producto;
    }
    
    //Método listar los productos
    public ResultSet ListarProductosFactura(String condicion, String orden) throws Exception{
        ResultSet datos = null;
        
        try {
            Statement stm = _conexion.createStatement();
            String sentencia = "SELECT ID_PRODUCTO, NOMBRE, COALESCE(PESO, 0) AS PESO_KILO, PRECIO_VENTA, CAST((PRECIO_VENTA - (PRECIO_VENTA / 1.13)) AS decimal(10,2)) AS IMPUESTO, CAST(PRECIO_VENTA + (PRECIO_VENTA - (PRECIO_VENTA / 1.13)) AS decimal(10,2)) AS TOTAL FROM PRODUCTOS";
            
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
