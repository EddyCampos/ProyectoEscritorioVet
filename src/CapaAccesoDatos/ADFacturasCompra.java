//Eddy Campos Jiménez
//23 Agosto 2023
package CapaAccesoDatos;

import CapaEntidades.FacturasCompras;
import Config.Config;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.ArrayList;

public class ADFacturasCompra {
    //Atributos
    private Connection _conexion;
    private String _mensaje;
    
    //Constructor
    public ADFacturasCompra() throws Exception{
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
    
    //Método insertar una factura de un prodcuto
    public int InsertarFacturaCompra(ArrayList<FacturasCompras> factura) throws Exception{
        int resultado = -1;
        
        try {
            
            for (FacturasCompras item : factura) {
                CallableStatement csp = _conexion.prepareCall("{call SP_AGREGAR_FACTURA_COMPRA(?, ?, ?, ?, ?, ?, ?)}");
                
                //Parametros de entrada
                csp.setInt(1, item.getIdFactura());
                csp.setInt(2, item.getIdDetalle());
                csp.setInt(3, item.getIdProducto());
                csp.setString(4, item.getNombreProveedor());
                csp.setString(5, item.getTelefonoProveedor());
                csp.setInt(6, item.getCantidad());
                csp.setString(7, "");
                
                //Parametros de salida
                csp.registerOutParameter(7, Types.VARCHAR);

                //Ejecución
                resultado = csp.executeUpdate();
                
                //Recupera parametro de salida
                _mensaje = csp.getString(7);
            }
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
