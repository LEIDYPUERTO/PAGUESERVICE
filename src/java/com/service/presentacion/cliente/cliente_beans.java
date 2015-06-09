/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.presentacion.cliente;

import com.service.db.dao.BancoDao;
import com.service.db.dao.ClienteDao;
import com.service.logica.mapeos.Banco;
import com.service.logica.mapeos.BancoId;
import com.service.logica.mapeos.Cliente;
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
public class cliente_beans {

    @Size(min = 3, max = 45)
    private String nombre;
    @Min(value = 1000000)
    @Max(value = 1999999999)
    private int cedula;
    @Size(min = 1, max = 1)
    private String genero;
    @Size(min = 4, max = 45)
    private String password;
    @Min(value = 1000000)
    @Max(value = 1999999999)
    private int cuenta;

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public cliente_beans() {
    }

    public void agregarCliente(ActionEvent actionEvent) {
        int saldo=(int)(Math.random() * 4875000 ) + 125000;
        ClienteDao dao = new ClienteDao();
        dao.guardaCliente(new Cliente(cedula, nombre, genero, password));
        BancoDao daob = new BancoDao();
        daob.guardaBanco(new Banco(new BancoId(cuenta,dao.obtenCliente(cedula).getCedula()), saldo,"Bancolombia"));

    }
 
}
