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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import py.una.pol.par.entities.Categoria;
import py.una.pol.par.model.CategoriaManager;

/**
 *
 * @author Lore
 */
@WebServlet(name = "CategoriaServlet", urlPatterns = {"/CategoriaServlet"})
@MultipartConfig(fileSizeThreshold = 1024*1024*2, //2MB
        maxFileSize = 1024*1024*10,
        maxRequestSize = 1024*1024* 50)

public class CategoriaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String vaccion = request.getParameter("vaccion");
        request.setAttribute("vaccion", vaccion);

        CategoriaManager cm = new CategoriaManager();

        if (vaccion == null) {
            //modo grilla...se muestran todos los registros
            ArrayList<Categoria> categorias = cm.getAll();
            request.setAttribute("categorias", categorias);

            RequestDispatcher rd = request.getRequestDispatcher("/admin/categoria.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }

        if ("Eliminar".equals(vaccion)) {
            String idCat = request.getParameter("idCategoria");
            Categoria c = new Categoria();
            c.setId_categoria(idCat);

            cm.delete(c);

            ArrayList<Categoria> categorias = cm.getAll();
            request.setAttribute("categorias", categorias);

            RequestDispatcher rd = request.getRequestDispatcher("/admin/categoria.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }

        if ("GrabarNuevo".equals(vaccion)) {
            String id = request.getParameter("idCategoria");
            String desc = request.getParameter("descripcion");
            Part part= request.getPart("imagen");
            String fileName=  Paths.get(part.getSubmittedFileName()).getFileName().toString();
            String root= request.getContextPath();
            String savePath= root +"/images/categorias/" + fileName;
            File fileSaveDir = new File(savePath);
            
            Categoria c = new Categoria();
            c.setId_categoria(id);
            c.setDescripcion(desc);
            c.setImagen(savePath);

            cm.insertar(c);

            ArrayList<Categoria> categorias = cm.getAll();
            request.setAttribute("categorias", categorias);

            RequestDispatcher rd = request.getRequestDispatcher("/admin/categoria.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }

        if ("Editar".equals(vaccion)) {
            String idCat = request.getParameter("idCategoria");
            Categoria c = cm.getCategoriaById(idCat);

            request.setAttribute("categoria", c);

            RequestDispatcher rd = request.getRequestDispatcher("/admin/categoriaEdit.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }

        if ("GrabarModificado".equals(vaccion)) {
            String idCat = request.getParameter("idCategoria");
            String desc = request.getParameter("descripcion");
            Categoria c = new Categoria();
            c.setId_categoria(idCat);
            c.setDescripcion(desc);
            
            cm.update(c);

            ArrayList<Categoria> categorias = cm.getAll();
            request.setAttribute("categorias", categorias);

            RequestDispatcher rd = request.getRequestDispatcher("/admin/categoria.jsp");
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
