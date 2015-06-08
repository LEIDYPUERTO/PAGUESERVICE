/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.presentacion.agua;

import com.service.db.dao.AguaDao;
import com.service.db.dao.ClienteDao;
import com.service.logica.mapeos.EAgua;
import com.service.logica.mapeos.EAguaId;
import com.service.presentacion.cliente.Comun;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 *
 * @author YEFFERSON FABIAN
 */
@ManagedBean
@RequestScoped
public class guardar_agua_beans {
    
    @Min(value = 10000)
    @Max(value = 1999999999)
    private int recibo_agua;
    private String fecha;
    @Size(min = 4, max = 45)
    private String detalle_agua;
    
    public int getRecibo_agua() {
        return recibo_agua;
    }

    public void setRecibo_agua(int recibo_agua) {
        this.recibo_agua = recibo_agua;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDetalle_agua() {
        return detalle_agua;
    }

    public void setDetalle_agua(String detalle_agua) {
        this.detalle_agua = detalle_agua;
    }
    

    /**
     * Creates a new instance of guardar_agua_beans
     */
    public guardar_agua_beans() {
    }
    
    public void agregarAgua(ActionEvent actionEvent){
         int valor_agua=(int)(Math.random() * 200000 ) + 25000;
         Date fechaV = Fecha.fecha((int) (Math.random() * 8));
        
        AguaDao daoa = new AguaDao();
        ClienteDao daoc = new ClienteDao();
        daoa.guardaAgua(new EAgua(new EAguaId(recibo_agua,Comun.getCedula()), fechaV, valor_agua, detalle_agua));
    }
    
//    public static void main(String[] args) {
//        System.out.println(Fecha.fecha(7));
//    }
}
