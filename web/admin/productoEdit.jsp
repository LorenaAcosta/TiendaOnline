<%-- 
    Document   : productoEdit
    Created on : 29/10/2017, 06:48:56 PM
    Author     : Lore
--%>

<%@page import="py.una.pol.par.model.ProductoManager"%>
<%@page import="py.una.pol.par.entities.Productos"%>
<%@page import="py.una.pol.par.entities.Productos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
   <body>
   <jsp:include page="../menubar.jsp"/>
    <section id="main-content">    
    <h2 align="center">Menu de Productos</h2> 
        
         <%
            Productos p = ProductoManager.getProductoById(request.getParameter("id"));
         %>
         <h2 align="center">Formulario de Edicion </h2>
         <br>
         <h2>Producto <%=p.getIdProducto() %></h2>
         <br>
        <form action="/TiendaOnline/ProductosServlet">
            <table>
                <tr>
                <th>Codigo </th> <th> <input type="text" name="txtIdProducto" value="<%= p.getIdProducto()%>" readonly> </th><tr>
                <th>Categoria </th> <th> <input type="text" name="txtCategoria"value="<%= p.getCategoria() %>" readonly> </th><tr>
                <th>Nombre </th> <th>  <input type="text" name="txtDescripcion"value="<%= p.getDescripcion() %>"></th><tr>
                <th>Precio Unitario </th> <th> <input type="number" value="<%= p.getPrecioUnit() %>" min="0" name="txtPrecio"value="" ></th> <tr>
                <th>Cant. Disponible </th> <th> <input type="number" value="<%= p.getCantidad()%>" min="0" name="txtCantidad"value=" " ></th> <tr> 
                </tr>
                 <input type="hidden" name="vaccion" value="GrabarModificado"/>
            <th><input type="submit" value="Modificar"/><th>
        </form>
            </table>
           
                
    </section>
    </body>
</html>
