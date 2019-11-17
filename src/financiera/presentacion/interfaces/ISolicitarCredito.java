/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiera.presentacion.interfaces;

import financiera.dominio.Credito;
import financiera.dominio.Cuota;
import financiera.dominio.Plan;
import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public interface ISolicitarCredito {
    public void cargarTablaPlanes(ArrayList<Plan> planes);
    public void cargarTablaCuotas(ArrayList<Cuota> cuotas);

    public void bloquearAccesoTablaYMonto();

    public void crearCliente();

    public void informarDeudas();

    public void informarCantidadCreditosActivosSuperado();

    public void actualizarDetalleCredito(Credito credito);

    public void informarRealizacion(Boolean operacionValida);
}
