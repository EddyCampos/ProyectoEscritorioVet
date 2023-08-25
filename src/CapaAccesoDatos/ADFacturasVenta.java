//Eddy Campos Jiménez
//21 Agosto 2023
package CapaAccesoDatos;

import CapaEntidades.FacturasVentas;
import Config.Config;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public class ADFacturasVenta {
    //Atributos
    private Connection _conexion;
    private String _mensaje;
    
    //Constructor
    public ADFacturasVenta() throws Exception{
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
    public int InsertarFacturaVenta(ArrayList<FacturasVentas> factura) throws Exception{
        int resultado = -1;
        
        try {
            
            for (FacturasVentas item : factura) {
                CallableStatement csp = _conexion.prepareCall("{call SP_AGREGAR_FACTURA_VENTA(?, ?, ?, ?, ?, ?, ?)}");
                
                //Parametros de entrada
                csp.setInt(1, item.getIdFactura());
                csp.setInt(2, item.getIdDetalle());
                csp.setInt(3, item.getIdCliente());
                csp.setInt(4, item.getidProd());
                csp.setInt(5, item.getidServ());
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
