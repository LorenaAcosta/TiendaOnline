<%-- 
    Document   : compras
    Created on : 16/11/2017, 08:34:23 PM
    Author     : Lore
--%>

<%@page import="py.una.pol.par.entities.Usuario"%>
<%@page import="py.una.pol.par.entities.Cabecera"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="py.una.pol.par.model.TransaccionManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    HttpSession sesion = request.getSession(true);
    Usuario user =  (Usuario) sesion.getAttribute("usuario");
   if (user!= null){ 
    TransaccionManager tm = new TransaccionManager();
    ArrayList<Cabecera> cab = tm.getCabeceraById(user.getIdusuario());
   
    
   if (cab == null){ %>
    <h2 id= "title" align="center"> Su carrito esta vacio actualmente. </th> 
    <a href="index.jsp">Volver al Menu Principal</a>
       
  <% }else{  
        for(Cabecera i : cab){
            
     %>
    <table id="productos_descripcion" align="center" width="500">
        <h2 id= "title" align="center"> Pedido Nro: <%= i.getId_transaccion() %> </h2><br>
            <tr> 
                 <th>Nro. del Pedido: </th> <th> <%=  i.getId_transaccion() %> </th><tr>
                <th>Fecha del Pedido: </th> <th> <%=  i.getFecha() %> </th><tr>
                <th>Total de Gs. </th> <th> <%= i.getTotal() %> </th> <tr> 
                
                
        </table>
         <%   }
        }
      %>
      
         </section>
    </body>
</html>

<% } 
%>
        
        
    </section>
    </body>
</html>
