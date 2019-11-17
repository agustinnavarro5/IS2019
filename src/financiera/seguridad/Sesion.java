/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiera.seguridad;

import financiera.dominio.Empleado;

/**
 *
 * @author usuario
 */
public class Sesion {
    
    private static Sesion instancia=null;
    private Usuario usuarioActivo=null;

    public Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    public void setUsuarioActivo(Usuario usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }

    
    
   

    /**
     * @return the instancia
     */
    public static Sesion getInstancia() {
        
        if(instancia == null)return Sesion.instancia= new Sesion();
        
        return instancia;
    }
    
    
}
