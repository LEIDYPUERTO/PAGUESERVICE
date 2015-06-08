/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.presentacion.internet;

import com.service.db.dao.ClienteDao;
import com.service.db.dao.InternetvDao;
import com.service.logica.mapeos.InternetTv;
import com.service.logica.mapeos.InternetTvId;
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
public class guardar_internet_beans {
    
    @Min(value = 10000)
    @Max(value = 1999999999)
    private int recibo_internet;
    @Size(min = 4, max = 45)
    private String detalle_internet;

    public int getRecibo_internet() {
        return recibo_internet;
    }

    public void setRecibo_internet(int recibo_internet) {
        this.recibo_internet = recibo_internet;
    }

   
    public String getDetalle_internet() {
        return detalle_internet;
    }

    public void setDetalle_internet(String detalle_internet) {
        this.detalle_internet = detalle_internet;
    }

    
    public void guardarInternet(ActionEvent actionEvent){
        int valor_internetv=(int)(Math.random() * 200000 ) + 25000;
        Date fechaV = Fecha.fecha((int) (Math.random() * 8));
        
        InternetvDao daoi = new InternetvDao();
        ClienteDao daoc = new ClienteDao();
        daoi.guardaInternetv(new InternetTv(new InternetTvId(recibo_internet, Comun.getCedula()), fechaV, valor_internetv, detalle_internet));
        //daog.guardaGas(new EGas(new EGasId(recibo_gas, Comun.getCedula()), fechaV, valor_gas, detalle_gas));    
    }

    /**
     * Creates a new instance of guardar_internet_beans
     */
    public guardar_internet_beans() {
    }
    
}
