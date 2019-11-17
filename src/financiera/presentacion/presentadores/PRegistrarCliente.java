/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiera.presentacion.presentadores;

import financiera.dominio.Cliente;
import financiera.persistencia.Repositorio;
import financiera.presentacion.interfaces.IRegistrarCliente;

/**
 *
 * @author usuario
 */
public class PRegistrarCliente {
    IRegistrarCliente vista;
    Cliente c;
    public PRegistrarCliente(IRegistrarCliente vista, String dni) {
        this.vista = vista;
        if(!dni.isEmpty()){
            this.c = Repositorio.getCliente(dni);
            this.vista.cargarDatos(this.c);
        }else{
            this.vista.bloquearIngresoDeDatosAntesDeValidar();
        }
    }

    public boolean existeCliente(String dni) {
        return Repositorio.existeCliente(dni);
    }

    public void registrarCliente(boolean registrar,String domicilio, String telefono, String sueldo, String nombre, String dni) {
        if(registrar){
            Cliente c = new Cliente( dni,  nombre,  telefono,  domicilio,  Double.parseDouble(sueldo));
            Repositorio.registrarCliente(c);
        }else{
            this.c.setDomicilio(domicilio);
            this.c.setNombreYApellido(nombre);
            this.c.setSueldo(Double.parseDouble(sueldo));
            this.c.setTelefono(telefono);
            Repositorio.actualizarCliente(this.c);
        }
    }
    
}
