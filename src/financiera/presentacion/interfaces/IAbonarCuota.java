/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiera.presentacion.interfaces;

import financiera.dominio.Credito;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public interface IAbonarCuota {

    public void cargarTablaCuotas(ArrayList<Credito> creditos, boolean seSelecciona);

    public void bloquearAccesoTablaYMonto();

    public void cargarComboBox();

    public void permitirAccesoTablaYMonto();

    public void informarClienteInexistente();

    public void designarModoPago();
    
}
