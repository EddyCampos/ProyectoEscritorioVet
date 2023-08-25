/**
 * Eddy Campos Jiménez
 * 16 Agosto 2023
 */
package CapaEntidades;

public class Servicios {
    //Atributos
    private int idServicio;
    private String nombre;
    private String tipo;
    private String NombreResponsable;
    private String TelefonoResponsable;
    private int precio;
    private boolean existe;
    
    //Constructor vacio
    public Servicios() {
        idServicio = 0;
        nombre = "";
        tipo = "";
        NombreResponsable = "";
        TelefonoResponsable = "";
        precio = 0;
        existe = false;
    }
    
    //Constructor con parametros
    public Servicios(int idServicio, String nombre, String tipo, String NombreResponsable, String TelefonoResponsable, int precio, boolean existe) {
        this.idServicio = idServicio;
        this.nombre = nombre;
        this.tipo = tipo;
        this.NombreResponsable = NombreResponsable;
        this.TelefonoResponsable = TelefonoResponsable;
        this.precio = precio;
        this.existe = existe;
    }

    //Métodos de acceso
    //Get
    public int getIdServicio() {
        return idServicio;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getTipo() {
        return tipo;
    }

    public String getNombreResponsable() {
        return NombreResponsable;
    }

    public String getTelefonoResponsable() {
        return TelefonoResponsable;
    }
    
    public int getPrecio() {
        return precio;
    }
    
    public boolean isExiste() {
        return existe;
    }
    
    //Set
    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNombreResponsable(String NombreResponsable) {
        this.NombreResponsable = NombreResponsable;
    }

    public void setTelefonoResponsable(String TelefonoResponsable) {
        this.TelefonoResponsable = TelefonoResponsable;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    

}
