//Eddy Campos Jiménez
//24 Agosto 2023
package CapaLogica;

import CapaAccesoDatos.ADFacturasCompra;
import CapaEntidades.FacturasCompras;
import java.util.ArrayList;

public class LFacturasCompra {
    //Atributos
    private String _mensaje;

    //Métodos de acceso
    public String getMensaje() {
        return _mensaje;
    }
    
    //Llamar método para insertar facturas de prodcutos de la capa de acceso
    public int InsertarFacturaCompra(ArrayList<FacturasCompras> factura) throws Exception{
        int resultado = -1;
        ADFacturasCompra acceso;
        
        try {
            acceso = new ADFacturasCompra();
            resultado = acceso.InsertarFacturaCompra(factura);
            _mensaje = acceso.getMensaje();
        } 
        catch (Exception e) {
            throw e;
        }
        
        return resultado;
    }
}
