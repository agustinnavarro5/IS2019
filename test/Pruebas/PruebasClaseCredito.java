/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import financiera.dominio.*;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author usuario
 */
public class PruebasClaseCredito {
    
    public PruebasClaseCredito() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCalcularTotalCreditoConPlanCuotaVencida(){
        //Configuracion
        
        Cliente c1 = new Cliente("33000000","Navarro, Pablo Agustín","4341848","Lomas de Tafí",2000);
        Empleado e = new Empleado("1","empleado ");
        PlanCuotaVencida p1 = new PlanCuotaVencida((float) 0.02, 1,3, (float) 0.05,"Cuota Vencida");
        Credito c = new Credito(new Date(),0,e,c1,p1);
        float porcentajeMensual = p1.getPorcentajeMensualInteres();
        int nroCuotas = p1.getNroCuotas();
        c.setMontoSolicitado(10000);
        //Ejecucion
        double total = c.calcularTotal(porcentajeMensual, nroCuotas);
        //Verificacion
        assertEquals(11500, total, 0.1);
        
    }
    @Test
    public void testCalcularTotalDeCuotaVencidaPorCincuentaDiasAlMomentoDePagarla(){
        //Configuracion
        Financiera.getInstance().setValoresPorDefecto();
        Cliente c1 = new Cliente("33000000","Navarro, Pablo Agustín","4341848","Lomas de Tafí",2000);
        Empleado e = new Empleado("1","empleado ");
        PlanCuotaAdelantada p3 = new PlanCuotaAdelantada(3,3, (float) 0.05,"Cuota Adelantada");
        Date fechaCreacionCredito = new Date(2019,8,15);
        Credito c = new Credito(fechaCreacionCredito,0,e,c1,p3);
        int nroCuotas = p3.getNroCuotas();
        c.setMontoSolicitado(1000);
        float porcentajeMensual = p3.getPorcentajeMensualInteres();
        double montoTotal = c.calcularTotal(porcentajeMensual, nroCuotas);
        double montoCuota = c.calcularMontoCuota(montoTotal, nroCuotas);
        c.setCuotas(new ArrayList<>());
        for(int i = 0 ; i < nroCuotas ; i++){
            Cuota cuo = c.crearCuota(i+1,fechaCreacionCredito,montoCuota);
            c.agregarCuota(cuo);
        }
        //Ejecucion
            //Si el credito fue creado el 15 de Agosto de 2019, la primera cuota ya fue pagada y la segunda vencería el 10 de Octubre.
            //El total se calculará sobre la segunda cuota del crédito de cuota adelantada y con respecto al día en curso de ejecución del test(30/11/2019)
            
            Cuota segundaCuota = c.getCuotas().get(1);
            //En este metodo se ingresa la fecha actual de pago(que será el día en curso de la ejecución del test) y la última fecha de pago, que en este caso, nunca se pagó la segunda cuota, por lo que sería igual a la fecha de vencimiento de la cuota
            segundaCuota.calcularTotal(new Date(2019,11,30),segundaCuota.getFechaVencimiento());
            //La cantidad de dias de diferencia entre el 10 de Octubre(fecha de vencimiento de segunda cuota) al dia corriente de la realización del test (30/11)
            //es de 50 días completos. Por lo que este valor multiplicado por el interés diario(0.05) del crédito y por el monto adeudado de la cuota (383) daría el interés supuesto
            // por la demora del pago en 50 días
            //Se sabe que el monto adeudado es de  383, y sumado al interes sería de = 1341.6 aproximadamente.
            double total = segundaCuota.getMontoNeto();
        //Verificacion
        assertEquals(1341.6, total, 0.1);
        
    }
    @Test
    public void testCalcularMontoAEntregarCreditoConPlanCuotaVencida(){
        //Configuracion
        
        Cliente c1 = new Cliente("33000000","Navarro, Pablo Agustín","4341848","Lomas de Tafí",2000);
        Empleado e = new Empleado("1","empleado ");
        PlanCuotaVencida p1 = new PlanCuotaVencida((float) 0.02, 1,3, (float) 0.05,"Cuota Vencida");
        Credito c = new Credito(new Date(),0,e,c1,p1);
        float porcentajeGastos = p1.getPorcentajeGastosAdministrativo();
        c.setMontoSolicitado(10000);
        double importeGastos = c.calcularImporteGastos(c.getMontoSolicitado(), porcentajeGastos);
        //Ejecucion
        double montoEntregado = c.calcularMontoEntregado(c.getMontoSolicitado(), importeGastos);
        //Verificacion
        assertEquals(9800, montoEntregado, 0.1);
        
    }
    @Test
    public void testCalcularMontoAEntregarCreditoConPlanCuotaAdelantada(){
        //Configuracion
        
        Cliente c1 = new Cliente("33000000","Navarro, Pablo Agustín","4341848","Lomas de Tafí",2000);
        Empleado e = new Empleado("1","empleado ");
        PlanCuotaAdelantada p3 = new PlanCuotaAdelantada(3,3, (float) 0.05,"Cuota Adelantada");
        Credito c = new Credito(new Date(),0,e,c1,p3);
        int nroCuotas = p3.getNroCuotas();
        c.setMontoSolicitado(10000);
        float porcentajeMensual = p3.getPorcentajeMensualInteres();
        double montoTotal = c.calcularTotal(porcentajeMensual, nroCuotas);
        double montoCuota = c.calcularMontoCuota(montoTotal, nroCuotas);
        //Ejecucion
        double montoEntregado = c.calcularMontoEntregado(c.getMontoSolicitado(),montoCuota);
        //Verificacion
        assertEquals(6166.6, montoEntregado, 0.1);
        
    }
    
     @Test
    public void testVerificarEstadoFinalizadoDeCredito(){
        //Configuracion
        Cliente c1 = new Cliente("33000000","Navarro, Pablo Agustín","4341848","Lomas de Tafí",2000);
        Empleado e = new Empleado("1","empleado ");
        PlanCuotaAdelantada p3 = new PlanCuotaAdelantada(3,3, (float) 0.05,"Cuota Adelantada");
        Credito c = new Credito(new Date(),0,e,c1,p3);
        int nroCuotas = p3.getNroCuotas();
        float porcentajeMensual = p3.getPorcentajeMensualInteres();
        double montoTotal = c.calcularTotal(porcentajeMensual, nroCuotas);
        double montoCuota = c.calcularMontoCuota(montoTotal, nroCuotas);
        c.setCuotas(new ArrayList<>());
        for(int i = 0 ; i < nroCuotas ; i++){
            Cuota cuota = c.crearCuota(i+1,montoCuota);
            cuota.setMontoAdeudado(0);
            c.agregarCuota(cuota);
        }
        //Ejecucion
        c.actualizarEstado();
        EstadoCredito estado = c.getEstado();
        //Verificacion
        assertEquals(EstadoCredito.FINALIZADO, estado);
        
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
