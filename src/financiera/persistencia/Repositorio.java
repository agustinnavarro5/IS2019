/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiera.persistencia;

import financiera.dominio.Cliente;
import financiera.dominio.Credito;
import financiera.dominio.Cuota;
import financiera.dominio.Empleado;
import financiera.dominio.EstadoCredito;
import financiera.dominio.Financiera;
import financiera.dominio.LineaPago;
import financiera.dominio.Pago;
import financiera.dominio.Plan;
import financiera.dominio.PlanCuotaVencida;
import financiera.dominio.PlanCuotaAdelantada;
import financiera.presentacion.adaptador.AdaptadorSistemaExterno;
import financiera.seguridad.Sesion;
import financiera.seguridad.Usuario;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class Repositorio {
    private static final ArrayList<Empleado> empleados = new ArrayList<>();
    private static final ArrayList<Usuario> usuarios = new ArrayList<>();
    private static final ArrayList<Plan> planes = new ArrayList<>();
    private static final ArrayList<Cliente> clientes = new ArrayList<>();
    private static final ArrayList<Credito> creditos = new ArrayList<>();
    private static final ArrayList<Pago> pagos = new ArrayList<>();
    
    public static void iniciar() throws MalformedURLException{
        crearEmpleados();
        crearPlanes();
        crearClientes(); 
    }
    public static void crearEmpleados(){
        for(int i = 0 ; i < Financiera.getInstance().getCantidadEmpleados();i++){
            Empleado e = new Empleado(""+i,"empleado "+i);
            crearUsuario("empleado"+i,"empleado"+i,e);
        }
    }
    public static boolean existeUsuario(String usuario, String contraseña) {
        for(Usuario u : Repositorio.usuarios){
            if(u.getNombreUsuario().equals(usuario) && u.getPass().equals(contraseña)){
                return true;
            }
        }
        return false;
    }

    private static void crearUsuario(String usuario, String cont, Empleado e) {
        Repositorio.usuarios.add(new Usuario(usuario, cont,e));
    }

    public static Usuario getUsuario(String usuario, String contraseña) {
       for(Usuario u : Repositorio.usuarios){
            if(u.getNombreUsuario().equals(usuario) && u.getPass().equals(contraseña)){
                return u;
            }
        }
        return null;
    }

    public static ArrayList<Plan> getPlanes() {
        return Repositorio.planes;
    }

    private static void crearPlanes() {
       PlanCuotaVencida p1 = new PlanCuotaVencida((float) 0.02, 1,3, (float) 0.05,"Cuota Vencida");
       PlanCuotaVencida p2 = new PlanCuotaVencida((float) 0.02, 2,6, (float) 0.1,"Cuota Vencida");
       PlanCuotaAdelantada p3 = new PlanCuotaAdelantada(3,3, (float) 0.05,"Cuota Adelantada");
       PlanCuotaAdelantada p4 = new PlanCuotaAdelantada(4,6, (float) 0.1,"Cuota Adelantada");
       Repositorio.planes.add(p1);
       Repositorio.planes.add(p2);
       Repositorio.planes.add(p3);
       Repositorio.planes.add(p4);
    }

    public static Cliente getCliente(String dni) {
        for(Cliente c : Repositorio.clientes){
            if(c.getDni().equals(dni)){
                return c;
            }
        }
        return null;
    }

    private static void crearClientes() {
        Cliente c1 = new Cliente("33000000","Navarro, Pablo Agustín","4341848","Lomas de Tafí",2000);
        Cliente c2 = new Cliente("33000001","Guaymas, Luciana Anastacia","4341842","Concepción",10000);
        Cliente c3 = new Cliente("33000002","Chuky, Leonardo","4341843","Concepción",8000);
        Cliente c4 = new Cliente("33000003","Cordera, Gustavo","4341844","Concepción",850000);
        Cliente c5 = new Cliente("33000004","Cordera, Mariano","4341844","Concepción",820000);
        Cliente c6 = new Cliente("33000005","Cordera, Gerardo","4341844","Concepción",830000);
        Cliente c7 = new Cliente("33000006","Cordera, hugo","4341844","Concepción",830000);
        Cliente c8 = new Cliente("33000007","Cordera, mario","4341844","Concepción",830000);
        Cliente c9 = new Cliente("33000008","Cordera, agustin","4341844","Concepción",830000);
        crearCreditos(c8,Repositorio.usuarios.get(0).getEmpleado());
        Repositorio.clientes.add(c1);
        Repositorio.clientes.add(c2);
        Repositorio.clientes.add(c3);
        Repositorio.clientes.add(c4);
        Repositorio.clientes.add(c5);
        Repositorio.clientes.add(c6);
        Repositorio.clientes.add(c7);
        Repositorio.clientes.add(c8);
        Repositorio.clientes.add(c9);
    }
    public static int getNumeroCreditosPendientes(String dni){
        int nro = 0;
        Cliente c = Repositorio.getCliente(dni);
        for(Credito cr : c.getCreditos()){
            if(cr.getEstado().equals(EstadoCredito.PENDIENTE)){
                nro++;
            }
        }
        return nro;
    }
  /*   public static int getNumeroCreditosPendientes(String dni){
        int nro = 0;
        for(Credito cr : Repositorio.creditos){
            if(cr.getEstado().equals(EstadoCredito.PENDIENTE) && cr.getCliente().getDni().equals(dni)){
                nro++;
            }
        }
        return nro;
    }*/

    public static void registrarCredito(Credito credito) {
       Repositorio.creditos.add(credito);
      Cliente c = Repositorio.getCliente(credito.getCliente().getDni());
      c.agregarCredito(credito);
      Repositorio.actualizarCliente(c);
    }

    public static void actualizarCliente(Cliente cli) {
        for(Cliente c : Repositorio.clientes){
            if(c.getDni().equals(cli.getDni())){
                c = cli;
            }
        }
    }

    public static void registrarPago(Pago pago) {
        Repositorio.pagos.add(pago);
    }

    public static void crearCreditos(Cliente c6, Empleado empleado) {
        for(int i=0; i<4 ;i++){
            
            Plan p = Repositorio.getPlanes().get(0);
            Credito c = new Credito(new Date(),1000,empleado,c6,p);
            int nroCuotas = p.getNroCuotas();
            double montoCuota = c.calcularMontoCuota(c.getMontoSolicitado(), c.getPlan().getNroCuotas());
            for(int j = 0 ; j<p.getNroCuotas();j++){
                Cuota cuo = new Cuota(j+1,montoCuota);
                Date fechaVenc = new Date();
                fechaVenc.setMonth(j);
                cuo.setFechaVencimiento(fechaVenc);
                c.agregarCuota(cuo);
            }
            if(p.getPorcentajeGastosAdministrativo()!=0){
                //Plan Cuota vencida
                double importeGastos = c.calcularImporteGastos(c.getMontoSolicitado(),p.getPorcentajeGastosAdministrativo());
                c.setInteres(importeGastos);
                double montoEntregado = c.calcularMontoEntregado(c.getMontoSolicitado(),importeGastos);
                c.setMontoEntregado(montoEntregado);
            }else{
                c.setInteres(0);
                double montoEntregado = c.calcularMontoEntregado(c.getMontoSolicitado(),montoCuota);
                c.setMontoEntregado(montoEntregado);
                c.getCuotas().get(0).crearPago(c.getEmpleado(), c.getCliente(), montoCuota, new Date());
                LineaPago l = c.getCuotas().get(0).getPago().crearLineaPago(1,c.getNroCredito(), montoCuota);
                c.getCuotas().get(0).getPago().agregarLineaPago(l);
                c.getCuotas().get(0).setMontoAdeudado(0);
            }
            c6.agregarCredito(c);
            Repositorio.creditos.add(c);
        }
        Repositorio.actualizarCliente(c6);
    }

    public static Date ultimaFechaPagoCuota(int nroCredito, int nroCuota) {
        for(int i = Repositorio.pagos.size()-1 ; i>-1; i--){
           for(LineaPago l : Repositorio.pagos.get(i).getLineas()){
               if(l.getNroCuota() == nroCuota && l.getNroCredito() == nroCredito){
                   return Repositorio.pagos.get(i).getFechaRealizacion();
               }
           }
       }
       return null;
    }

    public static ArrayList<Cliente> getClientes() {
       return Repositorio.clientes;
    }

    public static boolean existeCliente(String dni) {
        for(Cliente c : Repositorio.clientes){
           if(c.getDni().equals(dni)){
               return true;
           }
       }
        return false;
    }

    public static void registrarCliente(Cliente c) {
        Repositorio.clientes.add(c);
    }

    public static void eliminarCliente(String dni) {
        for(Cliente c : Repositorio.clientes){
           if(c.getDni().equals(dni)){
               Repositorio.clientes.remove(c);
               return;
           }
       }
    }

}
