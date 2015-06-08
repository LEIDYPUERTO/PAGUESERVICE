/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.presentacion.cliente;

/**
 *
 * @author YEFFERSON FABIAN
 */
public class Comun {
    
    private static int cedula;

    public static int getCedula() {
        return cedula;
    }

    public static void setCedula(int cedula) {
        Comun.cedula = cedula;
    }
    
    
}
