/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.par.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import py.una.pol.par.entities.Item;
import py.una.pol.par.entities.Usuario;
import py.una.pol.par.model.UsuarioManager;

/**
 *
 * @author Lore
 */
@WebServlet(name = "LogueoServlet", urlPatterns = {"/LogueoServlet"})
public class LogueoServlet extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession(true);
         ArrayList<Item> items =(ArrayList) sesion.getAttribute("carrito");
        String accion = request.getParameter("accion");
  
        if (accion == null) {
            request.setAttribute("mensajeError", "accion == null");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        } else {
            if ("login".equals(accion)) {
                String usuario = request.getParameter("alias_correo");
                String passwd = request.getParameter("password");

                if (usuario != null && passwd != null) {
                   
                    UsuarioManager um = new UsuarioManager();
                   try{
                    Usuario usu= um.getByAlias_or_Mail(usuario);
                    if ( usu.getLogin_name().equals(usuario) &&
                            usu.getContrasenha().equals(passwd)){
                        //Login Satisfactorio
                        sesion.setAttribute("usuario", usu);
                            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                            if (rd != null) {
                                rd.forward(request, response);
                            }
                        
                      }else{
                         request.getRequestDispatcher("/login.jsp").include(request, response); // Include en la página actual
                        PrintWriter out = response.getWriter();
                        // Escribimos un mensaje de error
                        out.println(" Ha habido un error");
                        }
                    } catch(Exception e) {
                        request.getRequestDispatcher("/index.jsp").include(request, response); // Include en la página actual
                        PrintWriter out = response.getWriter();
                        // Escribimos un mensaje de error
                        out.println(" Ha habido un error");
                        
                    }
                }
            }
            if ("Confirmar".equals(accion)){
                    RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                    if (rd != null) {
                        rd.forward(request, response);
                    }
            }else{
              if ("cerrar".equals(accion)){
                  sesion.setAttribute("usuario", null);
                    RequestDispatcher rd = request.getRequestDispatcher("/TiendaOnline/index.jsp");
                    if (rd != null) {
                        rd.forward(request, response);
                    }
                           
              }
            }

        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
