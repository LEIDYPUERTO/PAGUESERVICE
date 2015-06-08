/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.presentacion.soat;

import com.service.db.dao.SoatDao;
import com.service.logica.mapeos.Soat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.Size;

/**
 *
 * @author YEFFERSON FABIAN
 */
@ManagedBean
@RequestScoped
public class recibo_soat_beans {
    @Size(min = 6, max = 9)
    private String placa;
    private int valor_auto=1250000;
    private String fecha;
    private double valor_soat=125000;
    private int cedula;

    public int getValor_auto() {
        return valor_auto;
    }

    public void setValor_auto(int valor_auto) {
        this.valor_auto = valor_auto;
    }

    public double getValor_soat() {
        return valor_soat;
    }

    public void setValor_soat(double valor_soat) {
        this.valor_soat = valor_soat;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
    
    public void guardarSoat(ActionEvent actionEvent){
        //mes dia año
        String fecha = "05/25/2015";
        String[] strings = fecha.split("/");
        Date fechaV= new Date(Integer.parseInt(strings[2])-1900, Integer.parseInt(strings[0])-1, Integer.parseInt(strings[1]));
        
        SoatDao daos = new SoatDao();
       // guardar
          daos.guardaSoat(new Soat(placa, valor_auto, fechaV, valor_soat));
       
    }

    /**
     * Creates a new instance of recibo_soat_beans
     */
    public recibo_soat_beans() {
    }
    
}
