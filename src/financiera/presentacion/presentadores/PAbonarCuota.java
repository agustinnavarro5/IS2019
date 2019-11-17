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
import financiera.dominio.Pago;
import financiera.persistencia.Repositorio;
import financiera.presentacion.adaptador.AdaptadorSistemaExterno;
import financiera.presentacion.adaptador.IServicioPublicoCreditoInformarCreditoFinalizadoErrorServicioFaultFaultMessage;
import financiera.presentacion.adaptador.ResultadoOperacion;
import financiera.presentacion.interfaces.IAbonarCuota;
import financiera.presentacion.vistas.VAbonarCuota;
import financiera.seguridad.Sesion;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class PAbonarCuota {

    private IAbonarCuota vista;
    private Pago pago;
    private Cliente cliente;
    private ArrayList<Credito> creditosConCuotasImpagas;
    public PAbonarCuota(IAbonarCuota vista) {
        this.vista = vista;
        this.vista.cargarTablaCuotas(new ArrayList<>(), false);
        this.vista.cargarComboBox();
        this.vista.bloquearAccesoTablaYMonto();
    }

    public void ingresarCliente(String dni) {
        this.cliente = Repositorio.getCliente(dni);
       if(this.cliente!=null){
           this.cliente.actualizarEstadoCreditos();
            this.creditosConCuotasImpagas = this.cliente.getCreditosConCuotasImpagas();
            this.vista.cargarTablaCuotas(this.creditosConCuotasImpagas, true);
            this.vista.designarModoPago();
            this.vista.permitirAccesoTablaYMonto();
            Empleado e = Sesion.getInstancia().getUsuarioActivo().getEmpleado();
            this.pago = new Pago(this.cliente,e);
       }else{
           this.vista.informarClienteInexistente();
       }
       
    }

    public void abonarCuota(double monto) {
        while(monto>0){
            Cuota cu = this.getCuotaMasAntiguaImpaga();
            if(cu.getMontoAdeudado()>0){
                double montoCuota = 0;
                if(cu.getMontoNeto()<=monto){
                     montoCuota = cu.getMontoNeto();
                    cu.setMontoAdeudado(0);
                    cu.setMontoNeto(0);
                    monto=monto-montoCuota;
                }else{
                    double montoNeto = cu.getMontoNeto();
                    montoCuota = montoNeto - monto;
                    cu.setMontoAdeudado(montoCuota);
                    cu.setMontoNeto(montoCuota);
                    monto=0;
                }
                int nroCreditoConCuotaMasAntigua = this.getNroCreditoCuotaMasAntigua(cu);
                this.actualizarCuota(nroCreditoConCuotaMasAntigua,cu);
                LineaPago l = this.pago.crearLineaPago(cu.getNroCuota(),nroCreditoConCuotaMasAntigua,montoCuota);
                this.pago.agregarLineaPago(l);
            }
        }
    }

    public void abonarCuota(int nroCredito, int nroCuota) {
        Cuota cu = this.getCuota(nroCredito, nroCuota);
        cu.setMontoAdeudado(0);
        double montoNeto = cu.getMontoNeto();
        LineaPago l = this.pago.crearLineaPago(cu.getNroCuota(),nroCredito,montoNeto);
        this.pago.agregarLineaPago(l);       

    }

    private Cuota getCuota(int nroCredito, int nroCuota) {
        for(Credito c : this.creditosConCuotasImpagas){
            if(c.getNroCredito() == nroCredito){
                 for(Cuota cuo : c.getCuotas()){
                    if(cuo.getNroCuota() == nroCuota){
                        return cuo;
                    }
                }
            }
           
        }
        return null;
    }

    public void finalizarPago() throws IServicioPublicoCreditoInformarCreditoFinalizadoErrorServicioFaultFaultMessage {
       this.pago.calcularTotal();
       Repositorio.registrarPago(this.pago);
       //ACTUALIZAR CREDITOS
       String codigoFinanciera = Financiera.getInstance().getCodigo();
       for(Credito c : this.creditosConCuotasImpagas){
           c.actualizarEstado();
           if(c.getEstado().equals(EstadoCredito.FINALIZADO)){
                ResultadoOperacion operacion = AdaptadorSistemaExterno.getInstance().informarCreditoFinalizado
        (codigoFinanciera, Integer.parseInt(this.cliente.getDni()), String.valueOf(c.getNroCredito()));
                if(!operacion.isOperacionValida()){
                    c.setEstado(EstadoCredito.PENDIENTEFINALIZACION);
                }
           }
       }
       this.cliente.actualizarCreditos(this.creditosConCuotasImpagas);
       Repositorio.actualizarCliente(this.cliente);
    }

    public Cuota getCuotaMasAntiguaImpaga() {
        Date ultimaFechaVenc =this.creditosConCuotasImpagas.get(this.creditosConCuotasImpagas.size()-1).getCuotas().get(this.creditosConCuotasImpagas.get(this.creditosConCuotasImpagas.size()-1).getCuotas().size()-1).getFechaVencimiento();
        Cuota cu = new Cuota();
        cu.setFechaVencimiento(ultimaFechaVenc);
        for(Credito c : this.creditosConCuotasImpagas){
            for(Cuota cuota : c.getCuotas()){
                if(cuota.getFechaVencimiento().before(cu.getFechaVencimiento()) && cuota.getMontoAdeudado()>0){
                    cu = cuota;
                }
            }
        }
        return cu;
    }

    private int getNroCreditoCuotaMasAntigua(Cuota cu) {
        for(Credito c : this.creditosConCuotasImpagas){
            for(Cuota cuo : c.getCuotas()){
                if(cuo == cu){
                   return c.getNroCredito();
                }
            }
        }
        return 0;
    }

    private void actualizarCuota(int nroCredito,Cuota cu) {
        for(Credito c : this.creditosConCuotasImpagas){
            if(c.getNroCredito()==nroCredito){
                for(Cuota cuo : c.getCuotas()){
                    if(cuo.getNroCuota() == cu.getNroCuota()){
                       cuo = cu;
                    }
                }
            }
        }
    }
    
    
}
