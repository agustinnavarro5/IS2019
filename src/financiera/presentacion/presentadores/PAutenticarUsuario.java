/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiera.presentacion.presentadores;

import financiera.persistencia.Repositorio;
import financiera.presentacion.interfaces.IAutenticarUsuario;
import financiera.presentacion.vistas.VAutenticarUsuario;
import financiera.seguridad.Sesion;
import financiera.seguridad.Usuario;

/**
 *
 * @author usuario
 */
public class PAutenticarUsuario {

    private IAutenticarUsuario vista;
    public PAutenticarUsuario(IAutenticarUsuario vista) {
       this.vista = vista;
    }

    public boolean validarUsuario(String usuario, String contraseña) {
       return Repositorio.existeUsuario(usuario,contraseña);
    }


    public void asignarUsuarioActivo(String usuario, String contraseña) {
        Usuario u = Repositorio.getUsuario(usuario,contraseña);
        Sesion.getInstancia().setUsuarioActivo(u);
    }
    
}
