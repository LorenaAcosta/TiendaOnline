<%-- 
    Document   : registrarVenta
    Created on : 19/10/2017, 08:59:06 PM
    Author     : Lore
--%>

<%@page import="py.una.pol.par.entities.Item"%>
<%@page import="py.una.pol.par.model.ProductoManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="py.una.pol.par.entities.Productos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="scripts/Style.css">
    </head>
    <body> 
         <jsp:include page="menubar.jsp"/>
         <section id="main-content">
<%  
    HttpSession sesion = request.getSession(true);
    ArrayList<Item> items =(ArrayList) sesion.getAttribute("carrito");
    
   if (items == null){ %>
    <h2 id= "title" align="center"> Su carrito esta vacio actualmente. </th> 
    <a href="index.jsp">Volver al Menu Principal</a>
       
  <% }else{    
        ProductoManager pm = new ProductoManager();
        int subtotal;
        int total=0;
        int numProd=0;
        int subcantidad=0;
        
        for(Item i : items){
            Productos p = pm.getProductoById(i.getIdProducto());
            subtotal= i.getCantidad()* i.getPrecio();
            subcantidad= subcantidad + i.getCantidad();
            total = total + subtotal;
            numProd++;
     %>
    <table id="productos_descripcion" align="center" width="500">
        <h2 id= "title" align="center"> Item Nro. <%= numProd %> </h2><br>
            <tr>   
                <th rowspan="5"><img src="<%=p.getImagen()%>" width="160" height="200"> </th>
                <th>Codigo </th> <th> <%= p.getIdProducto() %> </th><tr>
                <th>Nombre </th> <th> <%= p.getDescripcion() %> </th> <tr>
                <th>Precio Unitario </th> <th> <%= p.getPrecioUnit() %> </th> <tr>
                <th> Cantidad </th> <th> <%= i.getCantidad()%> </th> <tr>
                <th> Sub-Total</th> <th> Gs.<%= subtotal %> </th> <tr>
               
            <td>    
            <form action="/TiendaOnline/ItemCarrito">
                <input type="hidden" name="vaccion" value="Eliminar"/>
                <input type="hidden" name="vid" value="<%=p.getIdProducto() %>"/>
                <input type="submit" value="Eliminar"/>
             </form>
             </td>
            </form>
        </table>
         <%   }  
         %>
        <table border ="0" align="center" width="500">
           <form align="center" action="/TiendaOnline/PreConfirm" method="POST">
            <h2 align="center"> Total a pagar Gs.<%= total %> </h2> <tr>
            <td>
                   <input type="hidden" name="vaccion" value="preconfirmar">
                   <input type="hidden" name="total" value="<%= total%>">
                   <input type="submit" value="Confirmar Compra"/>
            </td> 
          </table>
         </section>
    </body>
</html>

<% } 
%>
