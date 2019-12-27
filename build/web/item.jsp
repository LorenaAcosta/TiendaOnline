<%-- 
    Document   : index
    Created on : 14/10/2017, 11:24:37 PM
    Author     : Lore
--%>

<%@page import="py.una.pol.par.entities.Categoria"%>
<%@page import="py.una.pol.par.entities.Productos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.ArrayList"%>
<%@page import = "py.una.pol.par.model.*" %>
<%
    Productos p = ProductoManager.getProductoById(request.getParameter("id"));
 %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" type="text/css" href="scripts/Style.css">
    </head>
    <body>
       <jsp:include page="menubar.jsp"/>
       <section id="main-content">
       
       <h2 id="title" align="center">Descripcion del Producto </h2>
       
        <table id="productos" align="center"  >
              <th>
                  <img src="<%=p.getImagen()%>" width="500" height="500">
              </th>
              <th>
                <p align="left">
                 Codigo:  <%= p.getIdProducto() %> <br> 
                 Nombre: <%= p.getDescripcion()%> <br>
                 Precio: <%= p.getPrecioUnit()%> Gs.<br> 
                 Stock:  <%= p.getCantidad()%> unidades.<br>
                 Cantidad a Comprar:
                   <form action="/TiendaOnline/ItemCarrito">
                <select name="txtCantidad" required>  
                <%  int i=0;
                    for (i=1; i<=p.getCantidad(); i++){ 
                        if (i==0){
                    %>
                    <option value="<%=i%>" selected > <%=i%> </option>
                <%  }else{ %>
                   <option value="<%=i%>" > <%=i%> </option>
                 <% } }%>
                   </select> 
                </p>
                <br>
          
                <input type="hidden" name="vaccion" value="Anadir"/>
                <input type="hidden" name="idProducto" value="<%=p.getIdProducto() %>"/>
                <input type="hidden" name="txtPrecio" value="<%=p.getPrecioUnit() %>"/>
               
               <input type="submit" value="Anadir al carrito"/> 
            </form>
              </th><tr>
              
        </table>
       
       
       </section> 
    </body>
</html>
