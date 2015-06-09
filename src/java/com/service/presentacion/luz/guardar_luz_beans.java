/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.presentacion.luz;

import com.service.db.dao.ClienteDao;
import com.service.db.dao.LuzDao;
import com.service.logica.mapeos.ELuz;
import com.service.logica.mapeos.ELuzId;
import com.service.presentacion.agua.Fecha;
import com.service.presentacion.cliente.Comun;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionListener;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 *
 * @author YEFFERSON FABIAN
 */
@ManagedBean
@RequestScoped
public class guardar_luz_beans {
    
    @Min(value = 10000)
    @Max(value = 1999999999)
    private int recibo_luz;
    private String fecha;
    @Size(min = 4, max = 45)
    private String detalle_luz;
    public int getRecibo_luz() {
        return recibo_luz;
    }

    public void setRecibo_luz(int recibo_luz) {
        this.recibo_luz = recibo_luz;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getDetalle_luz() {
        return detalle_luz;
    }

    public void setDetalle_luz(String detalle_luz) {
        this.detalle_luz = detalle_luz;
    }
    
    public void guardarluz(ActionListener actionListener){
         int valor_luz=(int)(Math.random() * 200000 ) + 25000;
         Date fechaV = Fecha.fecha((int) (Math.random() * 8));
        
        LuzDao daol = new LuzDao();
        ClienteDao daoc = new ClienteDao();
        daol.guardaLuz(new ELuz(new ELuzId(recibo_luz, Comun.getCedula()), fechaV, valor_luz, detalle_luz));
    }

    /**
     * Creates a new instance of guardar_luz_beans
     */
    public guardar_luz_beans() {
    }
    
}
