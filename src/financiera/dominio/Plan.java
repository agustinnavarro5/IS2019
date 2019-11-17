/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiera.dominio;

import financiera.dominio.interfaces.IObtenerPorcentajeGastosAdministrativo;

/**
 *
 * @author usuario
 */
public class Plan implements IObtenerPorcentajeGastosAdministrativo{
    private int nroPlan;
    private int nroCuotas;
    private float porcentajeMensualInteres;
    private String descripcion;

    public Plan(int nroPlan, int nroCuotas, float porcentajeMensualInteres, String descripcion) {
        this.nroPlan = nroPlan;
        this.nroCuotas = nroCuotas;
        this.porcentajeMensualInteres = porcentajeMensualInteres;
        this.descripcion = descripcion;
    }

    //responsabilidades
    public void agregarLineaPago(LineaPago l){
        System.out.println(""
                + "");
    }
    //gets and setters
    public int getNroPlan() {
        return nroPlan;
    }

    public void setNroPlan(int nroPlan) {
        this.nroPlan = nroPlan;
    }

    public int getNroCuotas() {
        return nroCuotas;
    }

    public void setNroCuotas(int nroCuotas) {
        this.nroCuotas = nroCuotas;
    }

    public float getPorcentajeMensualInteres() {
        return porcentajeMensualInteres;
    }

    public void setPorcentajeMensualInteres(float porcentajeMensualInteres) {
        this.porcentajeMensualInteres = porcentajeMensualInteres;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public float getPorcentajeGastosAdministrativo() {
        return 0;
    }
    
}
