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

/**
 *
 * @author Lore
 */
@WebServlet(name = "ItemCarrito", urlPatterns = {"/ItemCarrito"})
public class ItemCarrito extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String vaccion = request.getParameter("vaccion");
        request.setAttribute("vaccion", vaccion);
        
        HttpSession sesion = request.getSession(true);
        ArrayList<Item> items = sesion.getAttribute("carrito") == null ? new ArrayList<>(): (ArrayList) sesion.getAttribute("carrito");
        boolean flag = false;
        
        if ("Anadir".equals(vaccion)) {
            try{
            String idProducto =  request.getParameter("idProducto");
            int precio= Integer.parseInt(request.getParameter("txtPrecio"));
            int cantidad= Integer.parseInt(request.getParameter("txtCantidad"));


            if (items.size()>0 ){
               for (Item i: items){
                   if (idProducto.equals(i.getIdProducto())){
                       i.setCantidad(cantidad);
                       flag= true;
                       break;
                   }
               }
            }
           if (flag ==false){
               items.add(new Item(idProducto,cantidad, precio));
           }
          sesion.setAttribute("carrito", items); 
          request.getRequestDispatcher("/index.jsp").include(request, response); // Include en la página actual
                        PrintWriter out = response.getWriter();
                        // Escribimos un mensaje de error
                        out.println("Producto Agregado al Carrito");
            }catch(Exception e){
            request.getRequestDispatcher("/index.jsp").include(request, response); // Include en la página actual
                        PrintWriter out = response.getWriter();
                        // Escribimos un mensaje de error
                        out.println(" Ha habido un error");
        
            }
          
        }
        
        
        if ("Eliminar".equals(vaccion)) {
            String idItem = request.getParameter("vid");
            Item aux =null;
            for (Item i: items){
                if (idItem.equals(i.getIdProducto())  ){
                    aux= i;
                    break;
                   }
            }
            items.remove(aux);
            
            items =  sesion.getAttribute("carrito") == null ? new ArrayList<>(): (ArrayList) sesion.getAttribute("carrito");
            request.setAttribute("carrito", items);
                    
            RequestDispatcher rd = request.getRequestDispatcher("/carrito.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }
        
        if ("Editar".equals(vaccion)) {
            String idProd= request.getParameter("vid");
            RequestDispatcher rd = request.getRequestDispatcher("anadirProducto.jsp?id="+idProd);
            if (rd != null) {
                rd.forward(request, response);
            }
        }
        if ("seleccionar".equals(vaccion)){
            
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
