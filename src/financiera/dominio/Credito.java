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
public class Credito {
    private int nroCredito;
    private Date fecha;
    private double montoSolicitado;
    private double montoTotal;
    private double interes;
    private double montoEntregado;
    //Asociaciones
    private Empleado empleado;
    private Cliente cliente;
    private EstadoCredito estado;
    private Plan plan;
    private ArrayList<Cuota> cuotas;

   
    public Credito(Date fecha, double montoSolicitado, Empleado empleado, Cliente cliente,  Plan plan) {
        this.nroCredito = Financiera.getInstance().getContadorNumeroCredito();
        Financiera.getInstance().setContadorNumeroCredito(Financiera.getInstance().getContadorNumeroCredito()+1);
        this.fecha = fecha;
        this.montoSolicitado = montoSolicitado;
        this.montoTotal = montoSolicitado;
        this.interes = 0;
        this.montoEntregado = montoSolicitado;
        this.empleado = empleado;
        this.cliente = cliente;
        this.estado = EstadoCredito.PENDIENTE;
        this.plan = plan;
        this.cuotas = new ArrayList<>();
    }

    public Credito(Cliente c, Empleado e) {
        this.nroCredito = Financiera.getInstance().getContadorNumeroCredito();
        Financiera.getInstance().setContadorNumeroCredito(Financiera.getInstance().getContadorNumeroCredito()+1);
        this.cliente = c;
        this.empleado = e;
        this.estado = EstadoCredito.PENDIENTE;
        this.cuotas = new ArrayList<>();
        this.montoSolicitado = 0;
        this.montoTotal = 0;
        this.interes = 0;
        this.montoEntregado = 0;
    }
    
    //Responsabilidades
        public double calcularTotal(float porcentajeMensual, int nroCuotas) {
            return this.montoSolicitado + (this.montoSolicitado*(porcentajeMensual*nroCuotas));
    }
        public double calcularMontoCuota(double montoTotal, int nroCuotas) {
            return montoTotal/nroCuotas;
    }
    public void agregarCuota(Cuota c){
        this.cuotas.add(c);
    }
    public Cuota crearCuota(int nroCuota, double monto){
        return new Cuota(nroCuota,monto);
    }
     public Cuota crearCuota(int nroCuota, Date fechaCreacionCredito, double monto){
        return new Cuota(nroCuota,fechaCreacionCredito,monto);
    }
    public void actualizarEstado(){
        int cantidadCuotasImpagas = 0;
        int cantidadCuotasPagas = 0;
        int limiteCantidadCuotasMorosas = Financiera.getInstance().getLimiteCantidadCuotasMorosas();
        Date fechaActual = new Date();
            for(Cuota cuo : this.getCuotas()){
                if(cuo.getFechaVencimiento().before(fechaActual) && cuo.getMontoAdeudado()!=0){
                    cantidadCuotasImpagas++;
                }else{
                    if(cuo.getMontoAdeudado()==0){
                        cantidadCuotasPagas++;
                    }
                }
            }
            if(cantidadCuotasImpagas>limiteCantidadCuotasMorosas){
                this.setEstado(EstadoCredito.MOROSO);
            }else{
                if(cantidadCuotasImpagas==0 && cantidadCuotasPagas==this.getCuotas().size()){
                    this.setEstado(EstadoCredito.FINALIZADO);
                }else
                    if(cantidadCuotasPagas==0) this.setEstado(EstadoCredito.PENDIENTE);
                    else this.setEstado(EstadoCredito.ACTIVO);
                
            }
    }
     public double calcularImporteGastos(Double montoSolicitado, float porcentajeGastos) {
        return montoSolicitado*porcentajeGastos;
    }

    public double calcularMontoEntregado(Double montoSolicitado, double importeGastos) {
            return montoSolicitado - importeGastos;
    }
    
    //gets and setters
    public int getNroCredito() {
        return nroCredito;
    }

    public void setNroCredito(int nroCredito) {
        this.nroCredito = nroCredito;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMontoSolicitado() {
        return montoSolicitado;
    }

    public void setMontoSolicitado(double montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public double getMontoEntregado() {
        return montoEntregado;
    }

    public void setMontoEntregado(double montoEntregado) {
        this.montoEntregado = montoEntregado;
    }

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

    public EstadoCredito getEstado() {
        return estado;
    }

    public void setEstado(EstadoCredito estado) {
        this.estado = estado;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public ArrayList<Cuota> getCuotas() {
        return cuotas;
    }

    public void setCuotas(ArrayList<Cuota> cuotas) {
        this.cuotas = cuotas;
    }

   

    


    
    
}
