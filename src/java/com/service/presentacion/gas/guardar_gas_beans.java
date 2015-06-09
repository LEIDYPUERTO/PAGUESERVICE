/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.presentacion.gas;

import com.service.db.dao.ClienteDao;
import com.service.db.dao.GasDao;
import com.service.logica.mapeos.EGas;
import com.service.logica.mapeos.EGasId;
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
public class guardar_gas_beans {
    
    @Min(value = 10000)
    @Max(value = 1999999999)
    private int recibo_gas;
    private String fecha;
    @Size(min = 4, max = 45)
    private String detalle_gas;
    public int getRecibo_gas() {
        return recibo_gas;
    }

    public void setRecibo_gas(int recibo_gas) {
        this.recibo_gas = recibo_gas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDetalle_gas() {
        return detalle_gas;
    }

    public void setDetalle_gas(String detalle_gas) {
        this.detalle_gas = detalle_gas;
    }
    
    public void guardarGas(ActionEvent actionEvent){
        int valor_gas=(int)(Math.random() * 200000 ) + 25000;
        Date fechaV = Fecha.fecha((int) (Math.random() * 8));
        
        GasDao daog = new GasDao();
        ClienteDao daoc = new ClienteDao();
        daog.guardaGas(new EGas(new EGasId(recibo_gas, Comun.getCedula()), fechaV, valor_gas, detalle_gas));
        //daol.guardaLuz(new ELuz(new ELuzId(recibo_luz, Comun.getCedula()), fechaV, valor_luz, detalle_luz));
    }

    /**
     * Creates a new instance of guardar_gas_beans
     */
    public guardar_gas_beans() {
    }
    
}
