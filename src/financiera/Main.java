/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiera;

import financiera.dominio.Financiera;
import financiera.persistencia.Repositorio;
import financiera.presentacion.vistas.VAutenticarUsuario;
import java.net.MalformedURLException;

/**
 *
 * @author usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException {
        Financiera.getInstance().setValoresPorDefecto();
        Repositorio.iniciar();
        new VAutenticarUsuario(null,true);
    }
    
}
