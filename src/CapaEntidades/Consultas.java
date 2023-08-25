/**
 * Eddy Campos Jiménez
 * 16 Agosto 2023
 */
package CapaEntidades;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Date;

public class Consultas {
    //Atributos
    private int idConsulta;
    private int idExpediente;
    private int idVeterinario;
    private String tipoConsulta;
    private Date fecha;
    private Time hora;
    private String descripcion;
    private boolean existe;
    
    //Constructor vacio
    public Consultas() {
        idConsulta = 0;
        idExpediente = 0;
        idVeterinario = 0;
        tipoConsulta = "";
        fecha = Date.from(Instant.MIN);
        hora = Time.valueOf(LocalTime.MIN);
        descripcion = "";
        existe = false;
    }
    
    //Constructor con parametros
    public Consultas(int idConsulta, int idExpediente, int idVeterinario, String tipoConsulta, Date fecha, Time hora, String descripcion, boolean       existe) {
        this.idConsulta = idConsulta;
        this.idExpediente = idExpediente;
        this.idVeterinario = idVeterinario;
        this.tipoConsulta = tipoConsulta;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
        this.existe = existe;
    }
    
    //Métodos de acceso
    //Get
    public int getIdConsulta() {
        return idConsulta;
    }

    public int getIdExpediente() {
        return idExpediente;
    }
    
    public int getIdVeterinario() {
        return idVeterinario;
    }
    
    public String getTipoConsulta() {
        return tipoConsulta;
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public Time getHora() {
        return hora;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public boolean isExiste() {
        return existe;
    }
    
    //Set
    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public void setIdExpediente(int idExpediente) {
        this.idExpediente = idExpediente;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    
    
}
