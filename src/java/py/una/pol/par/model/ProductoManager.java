
package py.una.pol.par.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import py.una.pol.par.entities.Categoria;
import py.una.pol.par.entities.Detalle;
import py.una.pol.par.entities.Productos;
import py.una.pol.par.util.conexion_bd;

/**
 *
 * @author Lore
 */
public class ProductoManager {

    public ProductoManager() {
    }
    
    public boolean insertar(Productos p) {
        boolean retValue = true;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = conexion_bd.getConnection();
            pstmt = conn.prepareStatement
                ("INSERT INTO productos (id_producto, cantidad, descripcion, preciounit, nombre_img, id_categoria) values (?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, p.getIdProducto() );
            pstmt.setInt(2, p.getCantidad());
            pstmt.setString(3, p.getDescripcion());
            pstmt.setInt(4, p.getPrecioUnit());
            pstmt.setString(5, p.getImagen());
            pstmt.setString(6, p.getCategoria());
            pstmt.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(ProductoManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductoManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            conexion_bd.closeConnection(conn);
        }

        return retValue;
    }
    
      public boolean update(Productos p) {
        boolean resul = true;

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = conexion_bd.getConnection();
            st = conn.prepareStatement("UPDATE productos SET id_producto=?, cantidad = ?, descripcion = ?,"
                    + " preciounit= ?");
            st.setString(1, p.getIdProducto());
            st.setInt(2, p.getCantidad());
            st.setString(3, p.getDescripcion());
            st.setInt(4, p.getPrecioUnit());
            st.execute();

        } catch (SQLException ex) {
            resul = false;
            Logger.getLogger(CategoriaManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion_bd.closeConnection(conn);
        }

        return resul;
    }
      
      public boolean delete(Productos p) {
        boolean retValue = true;

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = conexion_bd.getConnection();
            st = conn.prepareStatement("delete from productos where id_producto = ?");
            st.setString(1, p.getIdProducto() );
            st.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(CategoriaManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion_bd.closeConnection(conn);
        }

        return retValue;
    }
    
     public static Productos getProductoById(String id) {
        Productos prod = null;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
       
        try {
            conn = conexion_bd.getConnection();
            st = conn.prepareStatement("SELECT id_producto, descripcion, cantidad, preciounit, nombre_img, id_categoria FROM productos where id_producto = ?");
            st.setString(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                prod = new Productos();
                prod.setIdProducto(rs.getString(1));
                prod.setDescripcion(rs.getString(2));
                prod.setCantidad(rs.getInt(3));
                prod.setPrecioUnit(rs.getInt(4));
                prod.setImagen(rs.getString(5));
                prod.setCategoria(rs.getString(6));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion_bd.closeConnection(conn);
        }
        return prod;
    }
  
    public static ArrayList<Productos> getAll(String filtro) {
        ArrayList<Productos> lista = new ArrayList<Productos>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean flag= false;
        try {
            conn = conexion_bd.getConnection();
            String sentencia = "select id_producto,descripcion, cantidad, preciounit, nombre_img, id_categoria from productos";
            if(! filtro.isEmpty() && ("todos".equals(filtro))){
                st = conn.prepareStatement(sentencia);
                rs = st.executeQuery();
                while (rs.next()) {
                Productos p = new Productos(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6));
                lista.add(p);
               }
            }else if (!filtro.isEmpty() && !("".equals(filtro))){
                CategoriaManager cm = new CategoriaManager();
                ArrayList<Categoria> cat = cm.getAll();
                for (Categoria c: cat){
                    if (c.getId_categoria().equals(filtro)){
                        sentencia= sentencia + " " + "WHERE id_categoria = ?";
                        st= conn.prepareStatement(sentencia);
                        st.setString(1, filtro);
                        rs = st.executeQuery();
                        flag=true;
                        while (rs.next()) {
                        Productos p = new Productos(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6));
                        lista.add(p);
                        }
                    }
                }
                if (!flag){
                    //no olvidar ordenar order by ASC
                    sentencia= sentencia + " " + "WHERE descripcion LIKE ? order by descripcion asc";
                    st= conn.prepareStatement(sentencia);
                    st.setString(1, "%"+filtro +"%");
                    rs = st.executeQuery();
                        while (rs.next()) {
                        Productos p = new Productos(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6));
                        lista.add(p);
                        }
                }
                 
              }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion_bd.closeConnection(conn);
        }
        return lista;
    }   

    public boolean modificarCantidad(int cantidad, String id_producto) {
        boolean resul = true;

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int cant=0;

        try {
            conn = conexion_bd.getConnection();
            String sentencia =("select  cantidad from productos where id_producto= ?");
            st = conn.prepareStatement(sentencia);
            rs = st.executeQuery();
            while (rs.next()) {
                cant = rs.getInt(1);
             }
            int nuevo = cant - cantidad;
            st = conn.prepareStatement("UPDATE productos SET  cantidad = ? where id_producto= ?");
           
            st.setInt(1, nuevo);
            st.setString(2, id_producto);
            st.execute();

        } catch (SQLException ex) {
            resul = false;
            Logger.getLogger(CategoriaManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion_bd.closeConnection(conn);
        }

        return resul;
    }


}
