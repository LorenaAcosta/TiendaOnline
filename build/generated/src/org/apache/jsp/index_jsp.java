package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import py.una.pol.par.entities.Categoria;
import py.una.pol.par.entities.Productos;
import py.una.pol.par.model.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
   
    HttpSession sesion = request.getSession(true);
 
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"scripts/Style.css\">\n");
      out.write("        <script type=\"text/javascript\" src=\"scripts/myScript.js\"></script>\n");
      out.write("        <title>JSP</title>\n");
      out.write("    </head>\n");
      out.write("    ");
  if (request.getParameter("cerrar")!=null){
               session.invalidate();
            }
        
      out.write("\n");
      out.write("    <body> \n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "menubar.jsp", out, false);
      out.write("\n");
      out.write("    <section id=\"main-content\">\n");
      out.write("        <br>\n");
      out.write("        <br>\n");
      out.write("         <table align=\"center\">\n");
      out.write("            ");

            ArrayList<Categoria> cat_list = CategoriaManager.getAll();
            int salto = 0;
            for (Categoria c: cat_list){ 
      out.write("\n");
      out.write("                    <td> \n");
      out.write("                      <a href=\"catalogo.jsp?id=");
      out.print(c.getId_categoria());
      out.write("\" >  <button>\n");
      out.write("                      <img src=\"");
      out.print(c.getImagen());
      out.write("\" height=\"200\" width=\"200\" > <br>\n");
      out.write("                      ");
      out.print(c.getDescripcion() );
      out.write(" <br></button>\n");
      out.write("                      </a>\n");
      out.write("                    </td>\n");
      out.write("            ");

              salto++;
              if (salto==3){
             
      out.write("  \n");
      out.write("            <tr>  \n");
      out.write("            ");
  salto=0;
                }         
            }  
      out.write(" \n");
      out.write("          <table>\n");
      out.write("\n");
      out.write("    \n");
      out.write("</section>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
