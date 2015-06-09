/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.service.presentacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dos
 */
public class Error extends HttpServlet {
    

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        UtilidadHTML utilidadHTML = new UtilidadHTML();
        ResourceBundle resourceImagenes = ResourceBundle.getBundle("com/inventario/presentacion/imagenes");
        ResourceBundle resourceMensaje = ResourceBundle.getBundle("com/inventario/presentacion/mensajes");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //TODO output your page here
            out.println("<html>");
            out.println(utilidadHTML.darHeader( resourceMensaje.getString("titulo.error") ));
            out.println(utilidadHTML.darCuerpo( resourceMensaje.getString("titulo.error") ));
            out.println(utilidadHTML.crearImagen(
                        resourceImagenes.getString("imagen.error"),
                        "index.jsp",
                        resourceMensaje.getString("error.generico"),
                        resourceMensaje.getString("error.generico.full") )  );
            out.println(utilidadHTML.darCuerpoFinal());
            out.println(utilidadHTML.darPiePagina());
            out.println("</html>");        
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
