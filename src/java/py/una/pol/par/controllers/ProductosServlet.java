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
import javax.servlet.http.Part;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import py.una.pol.par.entities.Categoria;
import py.una.pol.par.entities.Productos;
import py.una.pol.par.model.CategoriaManager;
import py.una.pol.par.model.ProductoManager;

/**
 *
 * @author Lore
 */

@WebServlet(name = "ProductosServlet", urlPatterns = {"/ProductosServlet"})

@MultipartConfig(fileSizeThreshold = 1024*1024*2, //2MB
        maxFileSize = 1024*1024*10,
        maxRequestSize = 1024*1024* 50)
        
public class ProductosServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String vaccion = request.getParameter("vaccion");
        request.setAttribute("vaccion", vaccion);

        ProductoManager pm = new ProductoManager();

        if (vaccion == null) {
            //modo grilla...se muestran todos los registros
            ArrayList<Productos> prod = pm.getAll("");
            request.setAttribute("productos", prod);
            RequestDispatcher rd = request.getRequestDispatcher("/admin/producto.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }
        if ("filtrar".equals(vaccion)){
           
            String condicion = request.getParameter("searchterm");
            if ("".equals(condicion)){
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                if (rd != null) {
                        rd.forward(request, response);
                }
            }else{
                response.sendRedirect("catalogo.jsp?id="+condicion); 
            }
        }

        if ("Eliminar".equals(vaccion)) {
            String idProd = request.getParameter("idProducto");
            Productos p = new Productos();
            p.setIdProducto(idProd);

            pm.delete(p);

            ArrayList<Productos> prod = pm.getAll("todos");
            request.setAttribute("productos", prod);

            RequestDispatcher rd = request.getRequestDispatcher("/admin/producto.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }

        if ("GrabarNuevo".equals(vaccion)) {
            String id = request.getParameter("txtId");
            String desc = request.getParameter("txtDescripcion");
            int precio= Integer.parseInt(request.getParameter("txtPrecio"));
            int stock = Integer.parseInt(request.getParameter("txtCantidad"));
            String cat= request.getParameter("txtCat");
            String idCat = CategoriaManager.getCategoriaByDesc(cat);
            Part part= request.getPart("txtImg");
            String fileName=  Paths.get(part.getSubmittedFileName()).getFileName().toString();
            String root= request.getContextPath();
            String savePath= root +"/images/" + fileName;
            File fileSaveDir = new File(savePath);
           
            Productos p = new Productos();
            p.setIdProducto(id);
            p.setDescripcion(desc);
            p.setPrecioUnit(precio);
            p.setCantidad(stock);
            p.setImagen(savePath);
            p.setCategoria(idCat);
            pm.insertar(p);

            ArrayList<Productos> productos = pm.getAll("todos");
            request.setAttribute("productos", productos);

            RequestDispatcher rd = request.getRequestDispatcher("/admin/producto.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }

        if ("Editar".equals(vaccion)) {
            String idProd = request.getParameter("idProducto");
            Productos p = pm.getProductoById(idProd);

            request.setAttribute("productos", p);

            RequestDispatcher rd = request.getRequestDispatcher("/admin/productoEdit.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }

        if ("GrabarModificado".equals(vaccion)) {
            String idProd = request.getParameter("txtId");
            String desc =  request.getParameter("txtDescripcion");
            int precio =  Integer.valueOf(request.getParameter("txtPrecio"));
            int cant =  Integer.valueOf(request.getParameter("txtCantidad"));
            Productos p = new Productos();
            p.setIdProducto(idProd);
            p.setDescripcion(desc);
            p.setPrecioUnit(precio);
            p.setCantidad(cant);
            
            pm.update(p);

            ArrayList<Productos> productos = pm.getAll("");
            request.setAttribute("productos", productos);

            RequestDispatcher rd = request.getRequestDispatcher("/admin/producto.jsp");
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
