package com.service.logica.mapeos;
// Generated 18/04/2015 08:11:28 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * CamaraComercio generated by hbm2java
 */
public class CamaraComercio  implements java.io.Serializable {


     private int nitComercio;
     private int valorNegocio;
     private Date fechaPagoComercio;
     private double pagoCamaraComercio;

    public CamaraComercio() {
    }

    public CamaraComercio(int nitComercio, int valorNegocio, Date fechaPagoComercio, double pagoCamaraComercio) {
       this.nitComercio = nitComercio;
       this.valorNegocio = valorNegocio;
       this.fechaPagoComercio = fechaPagoComercio;
       this.pagoCamaraComercio = pagoCamaraComercio;
    }
   
    public int getNitComercio() {
        return this.nitComercio;
    }
    
    public void setNitComercio(int nitComercio) {
        this.nitComercio = nitComercio;
    }
    public int getValorNegocio() {
        return this.valorNegocio;
    }
    
    public void setValorNegocio(int valorNegocio) {
        this.valorNegocio = valorNegocio;
    }
    public Date getFechaPagoComercio() {
        return this.fechaPagoComercio;
    }
    
    public void setFechaPagoComercio(Date fechaPagoComercio) {
        this.fechaPagoComercio = fechaPagoComercio;
    }
    public double getPagoCamaraComercio() {
        return this.pagoCamaraComercio;
    }
    
    public void setPagoCamaraComercio(double pagoCamaraComercio) {
        this.pagoCamaraComercio = pagoCamaraComercio;
    }




}


