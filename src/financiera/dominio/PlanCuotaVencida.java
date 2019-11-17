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
public class PlanCuotaVencida extends Plan{
    private float porcentajeGastosAdministrativos;
    public PlanCuotaVencida(float porcentajeGastosAdministrativos, int nroPlan, int nroCuotas, float porcentajeMensualInteres, String descripcion) {
        super(nroPlan, nroCuotas, porcentajeMensualInteres, descripcion);
        this.porcentajeGastosAdministrativos = porcentajeGastosAdministrativos;
    }

    @Override
    public float getPorcentajeGastosAdministrativo() {
        return porcentajeGastosAdministrativos;
    }

    public void setPorcentajeGastosAdministrativos(float porcentajeGastosAdministrativos) {
        this.porcentajeGastosAdministrativos = porcentajeGastosAdministrativos;
    }
    
}
