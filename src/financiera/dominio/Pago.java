/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiera.dominio;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class Pago {
    private Empleado empleado;
    private Cliente cliente;
    private double montoTotal;
    private Date fechaRealizacion;
    private ArrayList<LineaPago> lineas;

    public Pago(Empleado empleado, Cliente cliente, double montoTotal, Date fechaRealizacion) {
        this.empleado = empleado;
        this.cliente = cliente;
        this.montoTotal = montoTotal;
        this.fechaRealizacion = fechaRealizacion;
        this.lineas = new ArrayList<>();
    }

    public Pago(Cliente c, Empleado e) {
        this.empleado = e;
        this.cliente = c;
        this.fechaRealizacion = new Date();
        this.lineas = new ArrayList<>();
        this.montoTotal = 0;
    }
    //responsabilidades
    public void agregarLineaPago(LineaPago l){
        this.lineas.add(l);
    }
    public LineaPago crearLineaPago(int nroCuota, int nroCredito, double monto){
        return new LineaPago(nroCuota,nroCredito,monto);
    }
    public void calcularTotal(){
        for(LineaPago l : this.lineas){
            this.montoTotal+=l.getMonto();
        }
    }
  
    //gets and setters
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public ArrayList<LineaPago> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<LineaPago> lineas) {
        this.lineas = lineas;
    }

   
}
