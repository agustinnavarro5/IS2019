/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiera.dominio;

/**
 *
 * @author usuario
 */
public class PlanCuotaAdelantada extends Plan{
    
    public PlanCuotaAdelantada(int nroPlan, int nroCuotas, float porcentajeMensualInteres, String descripcion) {
        super(nroPlan, nroCuotas, porcentajeMensualInteres, descripcion);
    }
}
