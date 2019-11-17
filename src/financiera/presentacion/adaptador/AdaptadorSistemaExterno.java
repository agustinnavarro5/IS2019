/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiera.presentacion.adaptador;

/**
 *
 * @author usuario
 */
public class AdaptadorSistemaExterno {
    private static ServicioPublicoCredito client = new ServicioPublicoCredito();
    public static IServicioPublicoCredito getInstance()
    {
        return AdaptadorSistemaExterno.client.getSGEBusService();
    }
}
