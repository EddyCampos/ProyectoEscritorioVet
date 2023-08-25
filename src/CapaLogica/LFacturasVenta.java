//Eddy Campos Jiménez
//21 Agosto 2023
package CapaLogica;

import CapaAccesoDatos.ADFacturasVenta;
import CapaEntidades.FacturaTemporal;
import CapaEntidades.FacturasVentas;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LFacturasVenta {
    //Atributos
    private String _mensaje;

    //Métodos de acceso
    public String getMensaje() {
        return _mensaje;
    }
    
    //Llamar método para insertar facturas de prodcutos de la capa de acceso
    public int InsertarFacturaVenta(ArrayList<FacturasVentas> factura) throws Exception{
        int resultado = -1;
        ADFacturasVenta acceso;
        
        try {
            acceso = new ADFacturasVenta();
            resultado = acceso.InsertarFacturaVenta(factura);
            _mensaje = acceso.getMensaje();
        } 
        catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
   
    
}
