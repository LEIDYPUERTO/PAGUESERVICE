/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.service.presentacion;



public class UtilidadHTML {

    public  String darHeader( String accionPagina ){
        String cadena = "<head>";
        cadena += "<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>";
        cadena += "<title> Desoft - " + accionPagina +  "</title>";
        cadena += "<link href='css/inventario.css' rel='stylesheet' type='text/css'>";
        cadena += "<link href='css/jquery-ui.css' rel='stylesheet' type='text/css'>";
        
        cadena +="<script src='js/jquery-1.11.1.min.js'></script>";
        cadena +="<script src='js/jquery-ui.js'></script>";
        
        cadena += "</head>";
        return cadena;
    }

    public  String darCuerpo( String accionPagina ){
        String cadena = "<body>";
        cadena += "<div id='header_contenedor' ></div>";
        cadena += "<div id='contenido_arriba'></div>";
        cadena += "<div class='contenido contenido_home'>";
        cadena += "<h1> Desof Sistema de Control</h1>";
        cadena += "<h2>" + accionPagina + "</h2>";
        return cadena;
    }
    
    public  String darCuerpoFinal() {
        String cadena = "";
        cadena += "</div>";
        cadena += "</div>";
        return cadena;
    }

    public String darPiePagina() {
        String cadena = "";
        cadena += "<div id='contenido_abajo'>";
        cadena += "<a href='index.jsp'>Inicio</a>";
        cadena += " | ";
        cadena += "<a href='Producto'>Producto</a>";
        cadena += " | ";
        cadena += "<a href='Factura'>Reporte</a>";
        cadena += " | ";
        cadena += "<a href='Cliente'>Cliente</a>";
        cadena += " | ";
        cadena += "<a href='Empleado'>Empleado</a>";
        cadena += "</div>";
        cadena += "</body>";
        return cadena;
    }

    public  String crearMensaje( String mensaje, String subMensaje ){
        String cadena = "<h4>" + mensaje + "</h4>";
        cadena += "<p>" + subMensaje + "</p>";
        return cadena;
    }

    public  String crearImagen( String rutaImagen, String rutaEnlace, 
            String nombre, String mensaje ){
        String cadena = "<table width='100%' border='0'>";
        cadena += "<tr>";
        cadena += "<td width='40%'>";
        cadena += "<a href='" + rutaEnlace + "'>";
        cadena += "<img src='" + rutaImagen + "' height='100' border='0'>";
        cadena += "</a>";
        cadena += "<br />";
        cadena += "<a href='" + rutaEnlace + "'>" + nombre + "</a>";
        cadena += "</td>";
        cadena += "<td><p>" + mensaje + "</p></td>";
        cadena += "</tr>";
        cadena += "</table>";
        return cadena;
    }

    public  String crearIcono( String rutaImagen, String rutaEnlace, String nombre ) {
        String cadena = "";        
        cadena += "<a href='" + rutaEnlace + "'>";
        cadena += "<img src='" + rutaImagen + "' height='20' border='0' alt='" + nombre + "'>";
        cadena += "</a>";
        return cadena;
    }
}
