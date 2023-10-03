
package Reglas;

import java.util.Date;

public class Tarea {
  
    private String descripcion;
    private Date fechaLimite;
  
    public Tarea(String descripcion, Date fechaLimite) {
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }
    
    public void setFechaLimite(Date fechaLimite) {
    this.fechaLimite = fechaLimite;
    }
}

