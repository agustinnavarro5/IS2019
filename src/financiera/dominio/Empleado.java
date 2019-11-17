/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiera.dominio;

/**
 *
 * @author usuario
 */
public class Empleado {
    private String legajo;
    private String nombreYApellido;

    public Empleado(String legajo, String nombreYApellido) {
        this.legajo = legajo;
        this.nombreYApellido = nombreYApellido;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getNombreYApellido() {
        return nombreYApellido;
    }

    public void setNombreYApellido(String nombreYApellido) {
        this.nombreYApellido = nombreYApellido;
    }
    
    
}
