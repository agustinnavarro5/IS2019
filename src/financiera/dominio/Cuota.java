/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiera.dominio;

import java.util.Date;

/**
 *
 * @author usuario
 */
public class Cuota {
    private int nroCuota;
    private Date fechaVencimiento;
    private double montoTotal;
    private double montoNeto;
    private double montoAdeudado;
    private double interes;
    //Asociaciones
    private Pago pago;

    public Cuota(int nroCuota, Date fechaVencimiento, double montoTotal) {
        this.nroCuota = nroCuota;
        this.fechaVencimiento = fechaVencimiento;
        this.montoTotal = montoTotal;
        this.montoNeto = montoTotal;
        this.montoAdeudado = montoTotal;
    }

    public Cuota(int nroCuota, double monto) {
          this.nroCuota = nroCuota;
        this.montoTotal = monto;
        this.montoNeto = monto;
        this.montoAdeudado = monto;
        this.fechaVencimiento = this.calcularFechaVencimiento(nroCuota);
    }

    public Cuota() {
    }

    //Responsabilidades
    public void crearPago(Empleado e, Cliente c, double monto, Date fecha){
        this.pago = new Pago(e,c,monto,fecha);
    }
    //
    public int getNroCuota() {
        return nroCuota;
    }

    public void setNroCuota(int nroCuota) {
        this.nroCuota = nroCuota;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public double getMontoAdeudado() {
        return montoAdeudado;
    }

    public void setMontoAdeudado(double montoAdeudado) {
        this.montoAdeudado = montoAdeudado;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public Date calcularFechaVencimiento(int nroCuota) {
        Date fechaActual = new Date();
        Date fechaVenc = fechaActual;
        fechaVenc.setDate(10);
        if((fechaActual.getMonth()+1) == 13){
            fechaVenc.setMonth(1);
            fechaVenc.setYear(fechaActual.getYear()+1);
        }else{
        fechaVenc.setMonth(fechaActual.getMonth()+nroCuota);
        fechaVenc.setYear(fechaActual.getYear());
        }
        return fechaVenc;
    }

    public void calcularTotal(Date ultimaFechaPago) {
        int cantidadDiasUltimoPago = this.cantidadDias(ultimaFechaPago,this.fechaVencimiento);
        float porcentajeInteresDiario = Financiera.getInstance().getInteresDiario();
        int cantidadDias = this.cantidadDias(new Date(),this.fechaVencimiento);
        if((cantidadDias-cantidadDiasUltimoPago)>0){
             this.interes= porcentajeInteresDiario*this.montoAdeudado*(cantidadDias-cantidadDiasUltimoPago);
        }else{
              this.interes= 0;
        }
            this.montoNeto = this.montoAdeudado+this.interes;
    }

    public int cantidadDias(Date fechaActual,Date ultimaFecha){
        int cantidadDias = 0 ;
        if(ultimaFecha.getYear() == fechaActual.getYear()){
            if(ultimaFecha.getMonth() == fechaActual.getMonth()){
                   if(fechaActual.getDate() <= ultimaFecha.getDate()){
                      //Pago en condiciones ideales
                      return 0;
                   }else{
                       cantidadDias =  fechaActual.getDate() - ultimaFecha.getDate();
                   }
            }else{
                if(ultimaFecha.getMonth() < fechaActual.getMonth()){
                    int cantidadMeses =  fechaActual.getMonth() - ultimaFecha.getMonth();
                    if(ultimaFecha.getDate() == fechaActual.getDate()){
                        //el dia de la fecha de venc coincide con la fecha actual
                        cantidadDias = cantidadMeses*30;
                    }else{
                        int diasDiferencia = 30 - ultimaFecha.getDate() + fechaActual.getDate();
                        cantidadDias = ((cantidadMeses-1)*30) + diasDiferencia;
                    }
                }
            }
        }else{
            if(ultimaFecha.getYear()< fechaActual.getYear()){
                    int cantAños =  fechaActual.getYear()- ultimaFecha.getYear();
                    if(ultimaFecha.getMonth() == fechaActual.getMonth()){
                        if(ultimaFecha.getDate() == fechaActual.getDate()){
                        //el dia de la fecha de venc coincide con la fecha actual
                                cantidadDias = cantAños * 360;
                        }else{
                           int diasDiferencia = 30 - ultimaFecha.getDate() + fechaActual.getDate();
                           cantidadDias = 330*cantAños + diasDiferencia;
                        }
                    }else{
                        int mesesDiferencia = 12 - ultimaFecha.getMonth() + fechaActual.getMonth();
                           int diasDiferencia = 30 - ultimaFecha.getDate() + fechaActual.getDate();
                           cantidadDias = ((cantAños-1)*360) + (mesesDiferencia-1)*30 + diasDiferencia;
                    }
                    
                    
                }
        }
        return cantidadDias;
    }
    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public double getMontoNeto() {
        return montoNeto;
    }

    public void setMontoNeto(double montoNeto) {
        this.montoNeto = montoNeto;
    }
    
    
}
