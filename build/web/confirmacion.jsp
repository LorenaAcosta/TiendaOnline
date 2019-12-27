<%-- 
    Document   : confirmacion
    Created on : 24/10/2017, 05:18:27 PM
    Author     : Lore
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="py.una.pol.par.entities.Usuario"%>
<%@page import="py.una.pol.par.model.TransaccionManager"%>
<%@page import="py.una.pol.par.entities.Cabecera"%>
<%@page import="py.una.pol.par.entities.Productos"%>
<%@page import="py.una.pol.par.model.ProductoManager"%>
<%@page import="py.una.pol.par.entities.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%  
    HttpSession sesion = request.getSession(true);
    Usuario user =  (Usuario) sesion.getAttribute("usuario");
 
    ArrayList<Item> items =(ArrayList)sesion.getAttribute("carrito"); 
    int total =(int)sesion.getAttribute("total");
   %>
        
        
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="scripts/Style.css">
        <script type="text/javascript" src="scripts/myScript.js"></script>
        <title>JSP</title>
    </head>
    <% Date fecha = new Date();
    %>
    
    <body>
        <jsp:include page="menubar.jsp"/>
        <section id="main-content">
            <br>
            <header>Orden de Compra:</header> 
        <ul id="ticket" align="center" width="auto"> 
        <h2 > Orden para el Señor/a: <%= user.getNombre()%>  <%= user.getApellido() %> </h2>
        <h4> Fecha y Hora de Pedido: <%=fecha%> </h4>
        <h2> Total a pagar: <%= total %> Gs.</h2>
        <br>
        </ul>
        
        <table border ="0" align="center" width="auto"> 
            <form action="/TiendaOnline/PreConfirm" method="POST">
                <input type="hidden" name="vaccion" value="confirmar">
                <input type="hidden" name="total" value="<%= total %>"/>
                 <input type="hidden" name="fecha" value="<%= fecha %>"/>
                <tr><h2 align="center">Introduce algunos datos:</h2>
                <th>A dónde te lo llevamos?:</th><tr>
                <th><input type="text" name="address" id="address" value="" required/> </th><tr>
                <th>Cómo pagarás?:</th><tr> 
                <th><select  name="medio">
                   <option value="0" onclick="ocultar()">Efectivo</option>
                   <option value="1"  onclick="mostrar()">Tarjeta de Credito</option>
                </th></select>  
                <tr>
                 <td>
                 <div id='oculto' style='display:none;'>
                        <h4>Dejanos tu numero de tarjeta: </h4>
                        <input type="number" name="nrotarjeta" value=""/>
                 </div></td>
                <tr> 
                <th> <input type="submit" value="Aceptar"/></th><tr>
            </form>
        </table>
        
        
        
        
        </section>
           
    </body>
</html>
