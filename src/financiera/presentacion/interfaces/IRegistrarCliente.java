/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiera.presentacion.interfaces;

import financiera.dominio.Cliente;

/**
 *
 * @author usuario
 */
public interface IRegistrarCliente {

    public void cargarDatos(Cliente c);

    public void bloquearIngresoDeDatosAntesDeValidar();
    
}
