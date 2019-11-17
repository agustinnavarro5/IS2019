/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiera.presentacion.interfaces;

import financiera.dominio.Cliente;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public interface IGestionCliente {

    public void cargarTablaClientes(ArrayList<Cliente> clientes);

    public void setCantidadRegistrosAFiltrar(int size);

    public void limpiarFiltroBusqueda();
    
}
