//Eddy Campos Jiménez
//19 Agosto 2023
package Config;

public class Config {
    public static String getConnectionString() throws ClassNotFoundException{
        //Registrar el Controlador
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        //Retornamos la cadena de conexion
        return "jdbc:sqlserver://localhost;databaseName=VETERINARIA_EDDY;user=sa;password=sa";
    }
}
