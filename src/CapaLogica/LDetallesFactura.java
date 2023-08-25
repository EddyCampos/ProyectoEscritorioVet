//Eddy Campos Jiménez
//19 Agosto 2023
package CapaLogica;

import CapaAccesoDatos.ADDetallesFactura;
import CapaAccesoDatos.ADFacturaTemporal;
import CapaEntidades.DetallesFacturas;
import java.sql.ResultSet;

public class LDetallesFactura {
    //Atributos
    private String _mensaje;

    //Métodos de acceso
    public String getMensaje() {
        return _mensaje;
    }
    
     //Llamar método para insertar detalles de la capa de acceso
    public int InsertarDetalleFacturas(DetallesFacturas detalle) throws Exception{
        int resultado = -1;
        ADDetallesFactura acceso;
        
        try {
            acceso = new ADDetallesFactura();
            resultado = acceso.InsertarDetalleFactura(detalle);
            _mensaje = acceso.getMensaje();
        } 
        catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    
    //Llamar obtener detalle de acceso a datos
    public int ObtenerDetalleFactura(String condicion) throws Exception{
        ADDetallesFactura accesoDatos;
        int resultado = 0;
        
        try {
            accesoDatos = new ADDetallesFactura();
            resultado = accesoDatos.ObtenerDetalleFactura(condicion);
            _mensaje = accesoDatos.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
    
     
}
