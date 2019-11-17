/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiera.presentacion.presentadores;

import financiera.dominio.Cliente;
import financiera.persistencia.Repositorio;
import financiera.presentacion.interfaces.IGestionCliente;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public class PGestionCliente {

    IGestionCliente vista;
    ArrayList<Cliente> clientes ;
    public PGestionCliente(IGestionCliente vista) {
      this.vista=vista;
      this.clientes = Repositorio.getClientes();
      this.vista.setCantidadRegistrosAFiltrar(this.clientes.size());
      this.vista.cargarTablaClientes(this.clientes);
    }

    public void filtrarCliente(String nomYAp, String dni) {
        int coincidencias = 0;
        for (Cliente c : this.clientes) {
                    if (c.getNombreYApellido().toUpperCase().startsWith(nomYAp)
                            && c.getDni().startsWith(dni)) {
                        c.setSeFiltra(true);
                        coincidencias++;
                    }else{
                        c.setSeFiltra(false);
                    }

            }
         if (coincidencias==0) {
                this.vista.limpiarFiltroBusqueda();
           //     JOptionPane.showMessageDialog((Component) this.vista, "No se encontraron coincidencias", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                this.vista.setCantidadRegistrosAFiltrar(coincidencias);
            }
        this.vista.cargarTablaClientes(clientes);
    }

    public void filtrarTodos() {
       this.vista.setCantidadRegistrosAFiltrar(this.clientes.size());
        for(Cliente c : this.clientes){
            c.setSeFiltra(true);
        }
        this.vista.cargarTablaClientes(clientes);
    }

    public void eliminarCliente(String dni) {
       Repositorio.eliminarCliente(dni);
       this.clientes = Repositorio.getClientes();
       this.vista.setCantidadRegistrosAFiltrar(this.clientes.size());
       this.vista.cargarTablaClientes(clientes);
    }
    
}
