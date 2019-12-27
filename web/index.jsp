<%-- 
    Document   : index
    Created on : 14/10/2017, 11:24:37 PM
    Author     : Lore
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="py.una.pol.par.entities.Categoria"%>
<%@page import="py.una.pol.par.entities.Productos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "py.una.pol.par.model.*" %>


<%   
    HttpSession sesion = request.getSession(true);
 %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="scripts/Style.css">
        <script type="text/javascript" src="scripts/myScript.js"></script>
        <title>JSP</title>
    </head>
    <%  if (request.getParameter("cerrar")!=null){
               session.invalidate();
            }
        %>
    <body> 
    <jsp:include page="menubar.jsp"/>
    <section id="main-content">
        <br>
        <br>
         <table align="center">
            <%
            ArrayList<Categoria> cat_list = CategoriaManager.getAll();
            int salto = 0;
            for (Categoria c: cat_list){ %>
                    <td> 
                      <a href="catalogo.jsp?id=<%=c.getId_categoria()%>" >  <button>
                      <img src="<%=c.getImagen()%>" height="200" width="200" > <br>
                      <%=c.getDescripcion() %> <br></button>
                      </a>
                    </td>
            <%
              salto++;
              if (salto==3){
             %>  
            <tr>  
            <%  salto=0;
                }         
            }  %> 
          <table>

    
</section>

    </body>
</html>
