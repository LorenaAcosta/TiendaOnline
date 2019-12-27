 /*<table border ="0" width="1000" align="center">
            <th><a href="index.jsp">Catálogo</a></th>
            <th><a href="regitrarProducto.jsp">Registrar Producto</a></th>
            <th><a href="registrarVenta.jsp">Registrar Venta</a></th>
            <th><a href="consultarVenta.jsp">Consultar Venta</a></th>
            <th><a href="ServletLogueo?accion=cerrar.jsp">Cerrar Sesión</a></th>
            <th width="200"></th>
            <tr>
        </table>
        <table border="0" width="500" align="center">
            <form method="post" action="servletControlador"> 
                <tr>
                <th rowspan="5"><img scr="<%= request.getContextPath() %>/images/<%=prod.getImagen()%>.jpg" width="140" height="140"></th>
                <th>Codigo</th>
                <th><input type="text" name="txtidProducto" value="<%= prod.getIdProducto() %>" readonly></th>
                </tr>
                <tr>  
                <th>Nombre</th>
                <th><input type="text" name="txtNombre" value="<%= prod.getDescripcion() %>" readonly></th>
                </tr>
                <tr>   
                <th>Precio</th>
                <th><input type="text" name="txtPrecio" value="<%= prod.getPrecioUnit() %>" readonly></th>
                </tr>
                <tr>
                <th>Cantidad</th>
                <th><input type="number" value="0" min="0" name="txtCantidad" value="0" /></th>
                </tr>
                <tr>
                <th colspan="3"><input type="button" value="Añadir" name="btnAnadir"></th>
                </tr>
                <input type="hidden" name="accion" value="AnadirProducto" />
            </form>     
        </table */