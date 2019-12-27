
package py.una.pol.par.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import py.una.pol.par.entities.Cabecera;
import py.una.pol.par.entities.Detalle;
import py.una.pol.par.entities.Item;
import py.una.pol.par.entities.Productos;
import py.una.pol.par.entities.Usuario;
import py.una.pol.par.model.ProductoManager;
import py.una.pol.par.model.TransaccionManager;

/**
 *
 * @author Lore
 */
@WebServlet(name = "PreConfirm", urlPatterns = {"/PreConfirm"})
public class PreConfirm extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        HttpSession sesion = request.getSession(true);
        Usuario usu =(Usuario)sesion.getAttribute("usuario");
        String accion=  request.getParameter("vaccion");
        
        
       if ("preconfirmar".equals(accion)){

            if (usu== null){
                RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                if (rd != null) {
                    rd.forward(request, response);
                }
            }else{
                int total= Integer.parseInt(request.getParameter("total"));
                sesion.setAttribute("total", total);
                ArrayList<Item> items =(ArrayList) sesion.getAttribute("carrito");
                    RequestDispatcher rd = request.getRequestDispatcher("/confirmacion.jsp");
                    if (rd != null) {
                        rd.forward(request, response);
                    }
           
            }    
       }
       if ("confirmar".equals(accion)){
           int id_transaccion;
        
           try{
               if (usu!= null){
                   String fecha = request.getParameter("fecha");
                    int total= Integer.parseInt(request.getParameter("total"));
                    
                   String address = request.getParameter("address");
                   String medio_pago = request.getParameter("medio");
                   int nro_tarjeta;
                   if ("1".equals(medio_pago)){
                       nro_tarjeta = Integer.parseInt(request.getParameter("nrotarjeta")); 
                   }else{nro_tarjeta= 0; }
                   String estado = "I";
                   
                   TransaccionManager tm = new TransaccionManager();
                   ProductoManager pm= new ProductoManager();
                  ArrayList<Cabecera> lista = TransaccionManager.getAll();
                  if (lista.isEmpty()){
                      id_transaccion=11100;
                  }else{
                      int id=0;
                      for (Cabecera c: lista){
                          id= c.getId_transaccion();
                      }
                      id_transaccion=id+100;
                  }
                   Cabecera cab = new  Cabecera(id_transaccion, usu.getIdusuario(),fecha, total, address, estado, Integer.parseInt(medio_pago), nro_tarjeta); 
                   tm.insertarCabecera(cab);
                
                    ArrayList<Item> items = (ArrayList<Item>) sesion.getAttribute("carrito");
                    Detalle det= new Detalle();
                    
                    int id_detalle = tm.getIdDetalle();
                    
                    if(!items.isEmpty()){
                        for (Item i: items){
                            det = new Detalle(id_transaccion, i.getIdProducto(), i.getCantidad(), i.getPrecio(), i.getCantidad()*i.getPrecio(), id_detalle );
                            tm.insertarDetalle(det);
                            pm.modificarCantidad(det.getCantidad(), det.getId_producto());
                            id_detalle++;
                        }
                    }
                    items=null;
                    sesion.setAttribute("carrito", items);
                    request.getRequestDispatcher("/index.jsp").include(request, response); // Include en la página actual
                        PrintWriter out = response.getWriter();
                        // Escribimos un mensaje de error
                        out.println("Pedido guardado");
               }
           }catch(Exception e){
               
                  request.getRequestDispatcher("/index.jsp").include(request, response); // Include en la página actual
                        PrintWriter out = response.getWriter();
                        // Escribimos un mensaje de error
                        out.println(" Ha habido un error");
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
