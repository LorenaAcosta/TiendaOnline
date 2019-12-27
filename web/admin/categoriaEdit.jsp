<%-- 
    Document   : editCategoria
    Created on : 29/10/2017, 05:32:40 PM
    Author     : Lore
--%>

<%@page import="py.una.pol.par.model.CategoriaManager"%>
<%@page import="py.una.pol.par.entities.Categoria"%>
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
        <h2 align="center">Menu de Categoria</h2> 
         <%
            Categoria c = CategoriaManager.getCategoriaById(request.getParameter("id"));
         %>
         <h2 align="center">Formulario de Edicion </h2>
         <br>
         <br>
        <table border ="0" align="center" width="auto">
             <form action="/TiendaOnline/CategoriaServlet">
                <tr>
                    <td>Codigo</td>
                    <td><input type="text" name="idCategoria" value="<%=c.getId_categoria()%>"/></td> </tr> 
                <tr>  
                    <td>Descripcion</td>
                    <td><input type="text" name="descripcion" value="<%=c.getDescripcion()%>"/></td></tr>
               
            <input type="hidden" name="idCategoria" value="<%=c.getId_categoria() %>"/>   
            <input type="hidden" name="descripcion" value="<%=c.getDescripcion() %>"/> 
            <input type="hidden" name="vaccion" value="GrabarModificado"/>
            <td><input type="submit" value="Grabar"/></td>
              </form>
        </table>
      
    </section>
    </body>
</html>
