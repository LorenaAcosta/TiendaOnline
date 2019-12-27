<%-- 
    Document   : producto
    Created on : 29/10/2017, 07:07:38 PM
    Author     : Lore
--%>

<%@page import="py.una.pol.par.model.ProductoManager"%>
<%@page import="py.una.pol.par.entities.Productos"%>
<%@page import="py.una.pol.par.entities.Categoria"%>
<%@page import="py.una.pol.par.model.CategoriaManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="scripts/Style.css">
        <title>JSP Page</title>
    </head>
    <body>
     <jsp:include page="../menubar.jsp"/>
     <section id="main-content">
     <% ArrayList<Categoria> lista =  CategoriaManager.getAll(); %>
     
     
     
     <h2 align="center">Menu de Productos</h2> 
     <table border ="0" align="center" width="auto">
         <form align="center" action="/TiendaOnline/ProductosServlet" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="vaccion" value="GrabarNuevo"/>
            <th>Codigo </th> 
            
            <th> <input type="text" name="txtId"value="" required> </th><tr> <br>
            <th>Nombre </th> 
            <th>  <input type="text" name="txtDescripcion"value="" required ></th><tr>
            <th>Precio Unitario </th> 
            <th>  <input type="number" name="txtPrecio"value="" min=1000  required></th><tr>
            <th>Stock </th> 
           <th>  <input type="number" name="txtCantidad"value="" min="10" required></th><tr>  
            <th>Categoria </th>
            <th> 
                 <select  name="txtCat" required>
                <%  for (Categoria c: lista){  %>
                <option value="<%= c.getDescripcion() %>"><%= c.getDescripcion() %></option>
                <%  } %>
                 </select> 
            </th> <tr>
            <th>Subir foto </th>
            <th><input type="file" name="txtImg" required> </th><tr> 
            <th><input type="submit" value="Guardar"/> </th><tr>
       </form>
      </table>
       
        <br>
        <table border ="0" align="center" width="auto">
            <tr>
                <td>Codigo </td>
                <td>Nombre </td>
                <td>Precio Unit. </td>
                <td>Stock </td>
            </tr>
       <% 
           ArrayList<Productos> listaProd = ProductoManager.getAll("todos");
          for (Productos p: listaProd){
        %>   
        <tr>
             <td> <%= p.getIdProducto() %> </td>
             <td> <%= p.getDescripcion() %> </td>
             <td> <%= p.getPrecioUnit() %> </td>
             <td> <%= p.getCantidad() %> </td>
             <td>
                 <a href="/TiendaOnline/admin/productoEdit.jsp?id=<%=p.getIdProducto()%>">
                  <button type="button">Editar </button>
                 </a></td>
             <td> 
                 <form align="center" action="/TiendaOnline/ProductosServlet" method="POST">
                      <input type="hidden" name="idProducto" value="<%=p.getIdProducto()%>" >
                      <input type="hidden" name="vaccion" value="Eliminar"/>
                      <input type="submit" value="Eliminar"/>
                 </form>
             </td>
        </tr>
        <% }%>
        </table>
     </section>
    </body>
    
</html>
