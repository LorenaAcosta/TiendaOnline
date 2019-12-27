<%@page import="py.una.pol.par.entities.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="py.una.pol.par.entities.Productos"%>
<%@page import="py.una.pol.par.entities.Item"%>
<%@page import="py.una.pol.par.model.ProductoManager"%>
 <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="../scripts/Style.css">
        <link href="media-queries.css" rel="stylesheet" type="text/css">
          <script type="text/javascript" src="scripts/myScript.js"></script>

 </head>
 
 
 <body>
  
       
<%
    //0 es admin 1 es normal
   HttpSession sesion = request.getSession(true);
   ArrayList<Item> items =(ArrayList) sesion.getAttribute("carrito");
   Usuario user =  (Usuario) sesion.getAttribute("usuario");
   if (user== null){ %>
   
<header id="main-header"> 
   <nav>
         <ul>
            <li> <img src="<%=request.getContextPath()%>/images/carrito.png"  height="50" width="50"> </li>
            <li><a class="active"  href="index.jsp">TiendaOnline</a></li>
            <li><a href="carrito.jsp">Mi carrito </a></li> 
            <li><a href="compras.jsp">Mis compras </a></li> 
            <%  
                int subCant= 0;
                if (items!= null){
                     for(Item i : items){
                        subCant = subCant+ i.getCantidad();
                     }
                  }
             %>
            <li><a href="carrito.jsp">Cant. Articulos: <%= subCant %> </a></li>
            <li style="float:right"><a href="login.jsp">Registrarse </a></li>
            <li style="float:right"><a  href="login.jsp" >Login</a></li>
            <br><br>
            
          </ul> 
             <ul>
         <form action="/TiendaOnline/ProductosServlet" method="POST">
           <input type="hidden" name="vaccion" value="filtrar">
           <input type="text" name="searchterm" placeholder="Que estas buscando?" > 
           <input type="submit" value="Buscar" /> 
         </form><ul>
   </nav>       
</header>
   
 <%  }else if (user.getTipo_usuario()==1){ //es normal %> 
    <header id="main-header"> 
        <nav>  
            <ul id="navbar">
                <li> <img src="<%=request.getContextPath()%>/images/carrito.png"  height="50" width="50"> </li>
                <li><a href="index.jsp">Catálogo</a></li>
                <li><a href="carrito.jsp">Mi carrito </a></li>
                <li><a href="compras.jsp">Mis compras </a></li> 
                <%  
                   int subCant= 0;
                   if (items!= null){
                        for(Item i : items){
                           subCant = subCant+ i.getCantidad();
                        }
                   }
                %>
               <li><a href="carrito.jsp">Cant. Articulos: <%= subCant %> </a></li>
               <li style="float:right" ><a href="index.jsp?cerrar=true">Cerrar Sesion <%= user.getNombre() %> </a></li>
            <br><br>
         </ul> 
          <ul>
         <form action="/TiendaOnline/ProductosServlet" method="POST">
           <input type="hidden" name="vaccion" value="filtrar">
           <input type="text" name="searchterm" placeholder="Que estas buscando?" > 
           <input type="submit" value="Buscar" /> 
         </form><ul>
    </header>
   <%   } else{
            if (user.getTipo_usuario()==0){//admin %> 
    <header id="main-header"> 
            <nav> <ul id="navbar"> 
                <li> <img src="<%=request.getContextPath()%>/images/carrito.png"  height="50" width="50"> </li>
                <li><a href="/TiendaOnline/index.jsp">Catálogo</a></li>
                <li><a href="/TiendaOnline/admin/usuario.jsp">Usuarios </a></li>
                <li><a href="/TiendaOnline/admin/categoria.jsp">Categoria </a></li>
                <li><a href="/TiendaOnline/admin/producto.jsp">Productos </a></li>
                <li><a href="/TiendaOnline/carrito.jsp">Mi carrito </a></li>
                <li><a href="/TiendaOnline/compras.jsp">Mis compras </a></li> 
     <%  
        int subCant= 0;
        if (items!= null){
             for(Item i : items){
                subCant = subCant+ i.getCantidad();
             }
        }
     %>
           <li><a href="/TiendaOnline/carrito.jsp">Cant. Articulos: <%= subCant %> </a></li>
           <li style="float:right"><a href="/TiendaOnline/index.jsp?cerrar=true">Cerrar Sesion <%= user.getNombre() %> </a></li>
             </ul> 
             <ul>
         <form action="/TiendaOnline/ProductosServlet" method="POST">
           <input type="hidden" name="vaccion" value="filtrar">
           <input type="text" name="searchterm" placeholder="Que estas buscando?" > 
           <input type="submit" value="Buscar" /> 
         </form><ul>
         
        </nav>
         
   <%   }  }%>
    </header>
        
 



    
 </body>
