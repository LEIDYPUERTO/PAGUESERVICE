package com.service.logica.mapeos;
// Generated 18/04/2015 08:11:28 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * EAgua generated by hbm2java
 */
public class EAgua  implements java.io.Serializable {


     private EAguaId id;
     private Date fechaPagoAgua;
     private double valorAgua;
     private String detalleAgua;

    public EAgua() {
    }

	
    public EAgua(EAguaId id, Date fechaPagoAgua, double valorAgua) {
        this.id = id;
        this.fechaPagoAgua = fechaPagoAgua;
        this.valorAgua = valorAgua;
    }
    public EAgua(EAguaId id, Date fechaPagoAgua, double valorAgua, String detalleAgua) {
       this.id = id;
       this.fechaPagoAgua = fechaPagoAgua;
       this.valorAgua = valorAgua;
       this.detalleAgua = detalleAgua;
    }
   
    public EAguaId getId() {
        return this.id;
    }
    
    public void setId(EAguaId id) {
        this.id = id;
    }
    public Date getFechaPagoAgua() {
        return this.fechaPagoAgua;
    }
    
    public void setFechaPagoAgua(Date fechaPagoAgua) {
        this.fechaPagoAgua = fechaPagoAgua;
    }
    public double getValorAgua() {
        return this.valorAgua;
    }
    
    public void setValorAgua(double valorAgua) {
        this.valorAgua = valorAgua;
    }
    public String getDetalleAgua() {
        return this.detalleAgua;
    }
    
    public void setDetalleAgua(String detalleAgua) {
        this.detalleAgua = detalleAgua;
    }




}


