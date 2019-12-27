
<%@page import="java.util.ArrayList"%>
<%@page import="py.una.pol.par.model.ProductoManager"%>
<%@page import="py.una.pol.par.entities.Categoria"%>
<%@page import="py.una.pol.par.entities.Productos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "py.una.pol.par.model.*" %>

<% 
    
    ArrayList<Productos> items = ProductoManager.getAll(request.getParameter("id"));
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
    <body> 
    <jsp:include page="menubar.jsp"/>
    <section id="main-content">
       
 <%
   if (items.isEmpty()){ %>
    <img align="center"  src="<%=request.getContextPath()%>/images/unnamed.jpg" width="400" height="400">
    <h2 id= "title" align="center"> No encontramos ningun resultado. </th> 
    <a href="index.jsp">Volver al Menu Principal</a><br>
  
    
  <% }else{     %>
     
    <table id="productos"align="center"> 
        <%  int salto = 0;
            for(Productos p : items){ %>
                 <th> 
                    <a href="item.jsp?id=<%=p.getIdProducto()%>">
                    <img src="<%=p.getImagen()%>" width="200" height="200">
                    </a>
                    <br>
                  
                    PROD: <%= p.getDescripcion() %> <br> 
                    Gs. <%= p.getPrecioUnit() %> <br>
                   <% if (p.getCantidad() <= 4){ %>
                    Sobran <%= p.getCantidad()%> unidades.<br>
                </th>
                    <%   }   %>    
        <%    salto++;
              if (salto==3){   %>  
               <tr>  
            <%  salto=0;
                }         
            }  %> 
          <table>
     <% }%>
    </section>

    </body>
</html>
