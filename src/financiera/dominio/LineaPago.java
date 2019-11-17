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
public class LineaPago {
    private int nroCuota;
    private int nroCredito;
    private double monto;

    public LineaPago(int nroCuota, int nroCredito, double monto) {
        this.nroCuota = nroCuota;
        this.nroCredito = nroCredito;
        this.monto = monto;
    }

    public int getNroCredito() {
        return nroCredito;
    }

    public void setNroCredito(int nroCredito) {
        this.nroCredito = nroCredito;
    }

    

    public int getNroCuota() {
        return nroCuota;
    }

    public void setNroCuota(int nroCuota) {
        this.nroCuota = nroCuota;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
}
