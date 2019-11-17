/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiera.dominio;

import financiera.persistencia.Repositorio;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class Cliente {
    private String dni;
    private String nombreYApellido;
    private String telefono;
    private String domicilio;
    private double sueldo;
   //Asociaciones
    private ArrayList<Credito> creditos;
    //Artificial
    private boolean seFiltra=true;

    public Cliente(String dni, String nombreYApellido, String telefono, String domicilio, double sueldo) {
        this.dni = dni;
        this.nombreYApellido = nombreYApellido;
        this.telefono = telefono;
        this.domicilio = domicilio;
        this.sueldo = sueldo;
        this.creditos = new ArrayList<>();
    }
    //Responsabilidades
   public void crearCredito(Cliente c, Empleado e){
        new Credito(c,e);
    }
    public void agregarCredito(Credito c){
        this.creditos.add(c);
    }
    //gets amd setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreYApellido() {
        return nombreYApellido;
    }

    public void setNombreYApellido(String nombreYApellido) {
        this.nombreYApellido = nombreYApellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public ArrayList<Credito> getCreditos() {
        return creditos;
    }

    public void setCreditos(ArrayList<Credito> creditos) {
        this.creditos = creditos;
    }

    public ArrayList<Credito> getCreditosConCuotasImpagas() {
        ArrayList<Credito> creditos = new ArrayList<>();
        for(Credito c : this.creditos){
            System.out.println(c.getEstado());
            //Calcula el total de cada cuota
            if(!c.getEstado().equals(EstadoCredito.FINALIZADO)){
                for(Cuota cuo : c.getCuotas()){
                    if(cuo.getMontoAdeudado()!=0){
                        Date ultimaFechaPagoCuota = Repositorio.ultimaFechaPagoCuota(c.getNroCredito(),cuo.getNroCuota());
                        if(ultimaFechaPagoCuota!=null){
                            cuo.calcularTotal(ultimaFechaPagoCuota);
                        }else{
                            //Se envia la fecha de vencimiento ya que la cuota no está incluída en ninguna línea de pago
                            cuo.calcularTotal(cuo.getFechaVencimiento());
                        }
                    }
                }
                creditos.add(c);
            }
            
        }
        return creditos;
    }

    public void actualizarEstadoCreditos() {
        for(Credito c : this.creditos){
            c.actualizarEstado();
        }
    }

    public void actualizarCreditos(ArrayList<Credito> creditosConCuotasImpagas) {
        for(Credito c : this.creditos){
            for(Credito cred : creditosConCuotasImpagas){
                if(c.getNroCredito()==cred.getNroCredito()){
                    c = cred;
                }
            }
        }
    }

    public boolean isSeFiltra() {
        return seFiltra;
    }

    public void setSeFiltra(boolean seFiltra) {
        this.seFiltra = seFiltra;
    }
    
    
}
