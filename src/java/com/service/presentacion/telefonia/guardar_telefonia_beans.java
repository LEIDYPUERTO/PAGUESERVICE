/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.presentacion.telefonia;

import com.service.db.dao.ClienteDao;
import com.service.db.dao.TelefoniaDao;
import com.service.logica.mapeos.Telefonia;
import com.service.logica.mapeos.TelefoniaId;
import com.service.presentacion.agua.Fecha;
import com.service.presentacion.cliente.Comun;
import java.util.Date;
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
public class guardar_telefonia_beans {
    
    @Min(value = 10000)
    @Max(value = 1999999999)
    private int recibo_telefonia;
    @Size(min = 4, max = 45)
    private String detalle_telefonia;

    public int getRecibo_telefonia() {
        return recibo_telefonia;
    }

    public void setRecibo_telefonia(int recibo_telefonia) {
        this.recibo_telefonia = recibo_telefonia;
    }

    public String getDetalle_telefonia() {
        return detalle_telefonia;
    }

    public void setDetalle_telefonia(String detalle_telefonia) {
        this.detalle_telefonia = detalle_telefonia;
    }

        public void guardarTelefonia(ActionEvent actionEvent){
       int valor_telefonia=(int)(Math.random() * 200000 ) + 25000;
        Date fechaV = Fecha.fecha((int) (Math.random() * 8));
        
        TelefoniaDao daot = new TelefoniaDao();
        ClienteDao daoc = new ClienteDao();
        daot.guardaTelefonia(new Telefonia(new TelefoniaId(recibo_telefonia, Comun.getCedula()), fechaV, valor_telefonia, detalle_telefonia));
       
    }

    /**
     * Creates a new instance of guardar_telefonia_beans
     */
    public guardar_telefonia_beans() {
    }
    
}
