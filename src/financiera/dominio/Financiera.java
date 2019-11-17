/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financiera.dominio;

import java.util.ArrayList;

/**
 *
 * @author usuario
 */
public class Financiera {
    //Clase creada por el patrón Fabricación Pura y con Singleton
    private static final Financiera financiera = new Financiera();
    public static Financiera getInstance()
    {
        return Financiera.financiera;
    }
    
    //Atributos
    private String codigo;
    private String cuit;
    private String razonSocial;
    private String domicilio;
    private String nombreComercial;
    private int limiteCantidadCuotasMorosas;
    private int limiteCantidadCreditosActivos;
    private double montoSolicitadoMaximo;
    private float interesDiario;
    private int cantidadEmpleados;
    private int contadorNumeroCredito=1;
  

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public int getLimiteCantidadCuotasMorosas() {
        return limiteCantidadCuotasMorosas;
    }

    public void setLimiteCantidadCuotasMorosas(int limiteCantidadCuotasMorosas) {
        this.limiteCantidadCuotasMorosas = limiteCantidadCuotasMorosas;
    }

    public int getLimiteCantidadCreditosActivos() {
        return limiteCantidadCreditosActivos;
    }

    public void setLimiteCantidadCreditosActivos(int limiteCantidadCreditosActivos) {
        this.limiteCantidadCreditosActivos = limiteCantidadCreditosActivos;
    }

    public double getMontoSolicitadoMaximo() {
        return montoSolicitadoMaximo;
    }

    public void setMontoSolicitadoMaximo(double montoSolicitadoMaximo) {
        this.montoSolicitadoMaximo = montoSolicitadoMaximo;
    }

    public float getInteresDiario() {
        return interesDiario;
    }

    public void setInteresDiario(float interesDiario) {
        this.interesDiario = interesDiario;
    }

    public int getCantidadEmpleados() {
        return cantidadEmpleados;
    }

    public void setCantidadEmpleados(int cantidadEmpleados) {
        this.cantidadEmpleados = cantidadEmpleados;
    }
    public int getContadorNumeroCredito() {
        return contadorNumeroCredito;
    }

    public void setContadorNumeroCredito(int contadorNumeroCredito) {
        this.contadorNumeroCredito = contadorNumeroCredito;
    }
    public void setValoresPorDefecto() {
       this.cantidadEmpleados = 3;
       this.codigo = "a3a3f1e5-3004-4872-bbbb-aef9e2fe954d";
       this.cuit = "12558886547";
       this.domicilio = "Avenida Perón 2000";
       this.interesDiario = (float) 0.05;
       this.limiteCantidadCreditosActivos = 3;
       this.limiteCantidadCuotasMorosas = 2;
       this.montoSolicitadoMaximo = 100000;
       this.nombreComercial = "La Financiera";
       this.razonSocial = "La Financiera SRL";
    }

    
    
    
    
}
