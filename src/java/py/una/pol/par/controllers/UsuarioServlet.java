/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.par.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import py.una.pol.par.entities.Productos;
import py.una.pol.par.entities.Usuario;
import py.una.pol.par.model.ProductoManager;
import py.una.pol.par.model.UsuarioManager;

/**
 *
 * @author Lore
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         String vaccion = request.getParameter("vaccion");
        request.setAttribute("vaccion", vaccion);

        UsuarioManager pm = new UsuarioManager();

        if (vaccion == null) {
            //modo grilla...se muestran todos los registros
            ArrayList<Usuario> usu = pm.getAll();
            request.setAttribute("usuarios", usu);

            RequestDispatcher rd = request.getRequestDispatcher("/admin/usuario.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }

        if ("Eliminar".equals(vaccion)) {
            int idUsu = Integer.parseInt(request.getParameter("txtId"));
            HttpSession sesion = request.getSession(true);
            Usuario user =  (Usuario) sesion.getAttribute("usuario");
           
            Usuario u = new Usuario();
            u.setIdusuario(idUsu);
            if (user.getIdusuario()!= idUsu ){
                pm.delete(u);
                ArrayList<Usuario> usu = pm.getAll();
                request.setAttribute("usuarios", usu);
            }

            RequestDispatcher rd = request.getRequestDispatcher("/admin/usuario.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }

        if ("GrabarNuevo".equals(vaccion)) {
                    
            int id = Integer.parseInt(request.getParameter("txtId"));
            String nom = request.getParameter("txtNombre");
            String ape = request.getParameter("txtApellido");
            String correo = request.getParameter("txtCorreo");
            String alias = request.getParameter("txtLogin_name");
            String pass = request.getParameter("txtPassword");
            
            Usuario u = new Usuario();
            u.setIdusuario(id);
            u.setNombre(nom);
            u.setApellido(ape);
            u.setCorreo(correo);
            u.setLogin_name(alias);
            u.setContrasenha(pass);
            u.setTipo_usuario(1); //Cliente normal por defecto
            pm.insertar(u);

            ArrayList<Usuario> usuarios = pm.getAll();
            request.setAttribute("usuarios", usuarios);

            RequestDispatcher rd = request.getRequestDispatcher("/admin/usuario.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }

        if ("Editar".equals(vaccion)) {
            String idUsu = request.getParameter("idUsuario");
            Usuario u = pm.getById(idUsu);
            request.setAttribute("usuarios", u);

            RequestDispatcher rd = request.getRequestDispatcher("/admin/usuarioEdit.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }

        if ("GrabarModificado".equals(vaccion)) {
            int id = Integer.parseInt(request.getParameter("txtId"));
            String nom = request.getParameter("txtNombre");
            String ape = request.getParameter("txtApellido");
            String correo = request.getParameter("txtCorreo");
            String alias = request.getParameter("txtLogin_name");
            String pass = request.getParameter("txtPassword");
            
            Usuario u = new Usuario();
            u.setIdusuario(id);
            u.setNombre(nom);
            u.setApellido(ape);
            u.setLogin_name(alias);
            u.setCorreo(correo);
            u.setLogin_name(alias);
            u.setContrasenha(pass);
 
            pm.update(u);

            ArrayList<Usuario> usuarios = pm.getAll();
            request.setAttribute("usuarios", usuarios);

            RequestDispatcher rd = request.getRequestDispatcher("/usuario.jsp");
            if (rd != null) {
                rd.forward(request, response);
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
