/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiera.presentacion.presentadores;

import financiera.dominio.Cliente;
import financiera.dominio.Credito;
import financiera.dominio.Cuota;
import financiera.dominio.Empleado;
import financiera.dominio.EstadoCredito;
import financiera.dominio.Financiera;
import financiera.dominio.LineaPago;
import financiera.dominio.Plan;
import financiera.persistencia.Repositorio;
import financiera.presentacion.adaptador.AdaptadorSistemaExterno;
import financiera.presentacion.adaptador.IServicioPublicoCreditoInformarCreditoOtorgadoErrorServicioFaultFaultMessage;
import financiera.presentacion.adaptador.IServicioPublicoCreditoObtenerEstadoClienteErrorServicioFaultFaultMessage;
import financiera.presentacion.adaptador.ResultadoEstadoCliente;
import financiera.presentacion.adaptador.ResultadoOperacion;
import financiera.presentacion.interfaces.ISolicitarCredito;
import financiera.seguridad.Sesion;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class PSolicitarCredito {
    
    private ISolicitarCredito vista;
    private ArrayList<Plan> planes;
    private Credito credito;
    public PSolicitarCredito(ISolicitarCredito vista) {
        this.vista = vista;
        this.planes = Repositorio.getPlanes();
        this.vista.cargarTablaPlanes(this.planes);
        this.vista.cargarTablaCuotas(new ArrayList<>());
        this.vista.bloquearAccesoTablaYMonto();
    }

    public boolean validarCliente(String dni) throws IServicioPublicoCreditoObtenerEstadoClienteErrorServicioFaultFaultMessage {
        Cliente c = Repositorio.getCliente(dni);
        if(c==null){
            this.vista.crearCliente();
        }else{
            String codFinanciera = Financiera.getInstance().getCodigo();
            ResultadoEstadoCliente estado = AdaptadorSistemaExterno.getInstance().obtenerEstadoCliente(codFinanciera,Integer.parseInt(dni));
            int cantidadCreditosActivos = estado.getCantidadCreditosActivos();
            boolean tieneDeudas = estado.isTieneDeudas();
            Empleado e = Sesion.getInstancia().getUsuarioActivo().getEmpleado();
            if(!tieneDeudas && cantidadCreditosActivos<=Financiera.getInstance().getLimiteCantidadCreditosActivos()){
                this.credito = new Credito(c, e);
                return true;
            }else{
                if(tieneDeudas) this.vista.informarDeudas();
                else{
                    if(cantidadCreditosActivos>3) this.vista.informarCantidadCreditosActivosSuperado();
                }
            }
        }
        return false;
       
    }

    public void ingresarCredito(int nroPlan, Double montoSolicitado) {
        Plan p = this.getPlan(nroPlan);
        this.actualizarCredito(p, montoSolicitado);
        float porcentajeMensual = p.getPorcentajeMensualInteres();
        int nroCuotas = p.getNroCuotas();
        float porcentajeGastos = p.getPorcentajeGastosAdministrativo();
        double montoTotal = this.credito.calcularTotal(porcentajeMensual,nroCuotas);
        this.credito.setMontoTotal(montoTotal);
        double montoCuota = this.credito.calcularMontoCuota(montoTotal,nroCuotas);
        //Vacío el array de Cuotas por si el plan cambió
        this.credito.setCuotas(new ArrayList<>());
        for(int i = 0 ; i < nroCuotas ; i++){
            Cuota c = this.credito.crearCuota(i+1,montoCuota);
            this.credito.agregarCuota(c);
        }
        
        if(p.getPorcentajeGastosAdministrativo()!=0){
            //Plan Cuota Adelantada
            double importeGastos = this.credito.calcularImporteGastos(montoSolicitado,porcentajeGastos);
            this.credito.setInteres(importeGastos);
            double montoEntregado = this.credito.calcularMontoEntregado(montoSolicitado,importeGastos);
            this.credito.setMontoEntregado(montoEntregado);
        }else{
            this.credito.setInteres(0);
            double montoEntregado = this.credito.calcularMontoEntregado(montoSolicitado,montoCuota);
            this.credito.setMontoEntregado(montoEntregado);
            this.credito.getCuotas().get(0).crearPago(this.credito.getEmpleado(), this.credito.getCliente(), montoCuota, new Date());
            LineaPago l = this.credito.getCuotas().get(0).getPago().crearLineaPago(1,this.credito.getNroCredito(), montoCuota);
            this.credito.getCuotas().get(0).getPago().agregarLineaPago(l);
            this.credito.getCuotas().get(0).setMontoAdeudado(0);
           //Comprobante
        }
        //Carga la tabla con las cuotas derivadas
        this.vista.cargarTablaCuotas(this.credito.getCuotas());
        this.vista.actualizarDetalleCredito(this.credito);
    }

    private Plan getPlan(int nroPlan) {
        for(Plan p : this.planes){
            if(p.getNroPlan()==nroPlan){
                return p;
            }
        }
        return null;
    }

    private void actualizarCredito(Plan p, Double montoSolicitado) {
        this.credito.setPlan(p);
        this.credito.setMontoSolicitado(montoSolicitado);
    }

    public void finalizarCredito() throws IServicioPublicoCreditoInformarCreditoOtorgadoErrorServicioFaultFaultMessage {
        this.credito.setEstado(EstadoCredito.PENDIENTE);
        Repositorio.registrarCredito(this.credito);
        if(this.credito.getPlan().getPorcentajeGastosAdministrativo()==0){
            Repositorio.registrarPago( this.credito.getCuotas().get(0).getPago());
        }
      /* String codFinanciera = Financiera.getInstance().getCodigo();
       ResultadoOperacion resultado = AdaptadorSistemaExterno.getInstance().informarCreditoOtorgado(codFinanciera, Integer.parseInt(this.credito.getCliente().getDni()) , String.valueOf(this.credito.getNroCredito()), this.credito.getMontoTotal());
        this.vista.informarRealizacion(resultado.isOperacionValida());*/
    }
    
}
