<%-- 
    Document   : Categoria
    Created on : 29/10/2017, 03:59:38 PM
    Author     : Lore
--%>

<%@page import="py.una.pol.par.model.CategoriaManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="py.una.pol.par.entities.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="scripts/Style.css">
        <title>Categoria</title>
    </head>
    <body>
    <jsp:include page="../menubar.jsp"/>
    <section id="main-content">
        
        
        
        
    <h2 align="center">Menu de Categoria</h2> 
     <table border ="0" align="center" width="auto">
       <form align="center" action="/TiendaOnline/CategoriaServlet" method="POST" enctype="multipart/form-data">
           <input type="hidden" name="vaccion" value="GrabarNuevo">
            <th>Codigo </th> <th> <input type="text" name="idCategoria"value="" required> </th><tr> <br>
            <th>Nombre </th> <th>  <input type="text" name="descripcion"value="" required></th><tr>
            <th>Subir foto </th> <th><input type="file" name="imagen" required> </th><tr> 
            <th> <input type="submit" value="Guardar"/> </th><tr>
       </form>
      </table>
       
        <br>
        <table border ="0" align="center" width="auto">
            <tr>
                <td align="center">Codigo </td>
                <td align="center">Nombre </td>
            </tr>
       <% 
           ArrayList<Categoria> lista =  CategoriaManager.getAll();
          for (Categoria c: lista){
        %>   
        <tr>
             <td> <%= c.getId_categoria() %> </td>
             <td> <%= c.getDescripcion() %> </td>
             
             <td>
                <a href="/TiendaOnline/admin/categoriaEdit.jsp?id=<%=c.getId_categoria()%>">
                    <button type="button">Editar </button>
                </a>
                 
             <td> 
                  <form action="/TiendaOnline/CategoriaServlet" method="POST">
                   <input type="hidden" name="idCategoria" value="<%= c.getId_categoria() %>" >
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
