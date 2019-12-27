<%-- 
    Document   : usuario
    Created on : 30/10/2017, 03:58:53 AM
    Author     : Lore
--%>

<%@page import="py.una.pol.par.entities.Usuario"%>
<%@page import="py.una.pol.par.model.UsuarioManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="scripts/Style.css">
        <title>Productos</title>
    </head>
     <body>
     <jsp:include page="../menubar.jsp"/>
    <section id="main-content">
        

     <h2 align="center">Lista de Usuarios</h2>
     <table border ="0" align="center" width="auto">
         <form align="center" action="/TiendaOnline/UsuarioServlet" method="POST">
            <input type="hidden" name="vaccion" value="GrabarNuevo"/>
            <th>Id de Usuario </th> <th> <input type="int" name="txtId"value="" > </th><tr> <br>
            <th>Nombre </th> <th>  <input type="text" name="txtNombre"value="" ></th><tr>
            <th>Apellido </th> <th>  <input type="text" name="txtApellido"value="" ></th><tr>
            <th>Correo </th> <th>  <input type="email" name="txtCorreo"value="" ></th><tr>  
            <th>Nombre de Usuario </th> <th>  <input type="text" name="txtLogin_name"value="" ></th><tr>
            <th>Contraña </th> <th> <input type="password" name="txtPassword"value="" ></th><tr>     
            <th><input type="submit" value="Guardar"/> </th><tr>
       </form>
      </table>
       
        <br>
        <table border ="0" align="center" width="auto">
            <tr>
                <td>Nombre </td>
                <td>Apellido </td>
                <td>Correo </td>
                <td align="center" > Opciones <td>
            </tr>
       <% 
           ArrayList<Usuario> listaUsu = UsuarioManager.getAll();
          for (Usuario p: listaUsu){
        %>   
        <tr>
             <td> <%= p.getNombre() %> </td>
             <td> <%= p.getApellido()  %> </td>
             <td> <%= p.getCorreo() %> </td>
             <td>
                 <button><a href="usuarioEdit.jsp?id=<%=p.getIdusuario() %>">Editar</a></button> 
             <td> 
                  <form align="center" action="/TiendaOnline/UsuarioServlet" method="POST">
                   <input type="hidden" name="txtId" value="<%=p.getIdusuario() %>" >
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
