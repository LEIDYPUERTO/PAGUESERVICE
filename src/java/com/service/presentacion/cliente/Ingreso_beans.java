/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.presentacion.cliente;

import com.service.db.dao.ClienteDao;
import com.service.logica.mapeos.Cliente;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
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
public class Ingreso_beans {

    @Min(value = 1000000)
    @Max(value = 1999999999)
    private int cedula;
    @Size(min = 4, max = 45)
    private String password;

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
   
    public Ingreso_beans() {
    }
   
    public void Ingreso(ActionEvent actionEvent) throws IOException{
         
        ClienteDao daoc = new ClienteDao();
        Cliente cl = daoc.obtenCliente(cedula);
        if (cl.getContrasena().equals(password)) {
            Comun.setCedula(cedula);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().redirect("http://localhost:8080/Pague_Service2.0/faces/principal.xhtml");
        }
        
    }
}
