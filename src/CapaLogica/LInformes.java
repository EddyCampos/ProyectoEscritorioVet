//Eddy Campos Jiménez
//21 Agosto 2023
package CapaLogica;

import CapaAccesoDatos.ADInformes;
import java.sql.ResultSet;

public class LInformes {
    //Atributos
    private String _mensaje;

    //Métodos de acceso
    public String getMensaje() {
        return _mensaje;
    }
    
    //Llamar al metodo para listar los informes mensuales de la capa de acceso
    public ResultSet ListarInformesMensuales(String condicion, String orden) throws Exception{
        ResultSet datos = null;
        ADInformes acceso;
        
        try {
            acceso = new ADInformes();
            datos = acceso.ListarInformesMensuales(condicion, orden);
        } 
        catch (Exception e) {
            throw e;
        }
        
        return datos;
    }
    
    //Llamar al metodo para listar los informes de los empleados de la capa de acceso
    public ResultSet ListarInformesEmpleados(String condicion, String orden) throws Exception{
        ResultSet datos = null;
        ADInformes acceso;
        
        try {
            acceso = new ADInformes();
            datos = acceso.ListarInformesEmpleados(condicion, orden);
        } 
        catch (Exception e) {
            throw e;
        }
        
        return datos;
    }
}
