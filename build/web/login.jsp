<%-- 
    Document   : login
    Created on : 26/03/2011, 05:40:31 AM
    Author     : lore
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
        <%
           java.lang.String mensaje = (String) request.getAttribute("mensajeError");
           if (mensaje != null && !"".equals(mensaje.trim())) {
        %>
                <h1 style="color:red"><%=mensaje%></h1>
        <% }  
        %>
        
        <section id="main-content">
           <table border ="0" align="center" width="auto"> 
            <form action="/TiendaOnline/LogueoServlet" method="POST">
                <input type="hidden" name="accion" value="login"/>
                <tr><h2 align="center">Introduce tus datos</h2>
                <th>Usuario:</th>   <th><input type="text" name="alias_correo" id="alias_correo" required/> </th><tr>
                <th> Password:</th> <th><input type="password" name="password" id="password" required/> </th> <tr>
                    <th> <input type="submit" value="Ingresar"/></th><tr>
            </form>
           </table>
        </section>

    </body>
</html>
