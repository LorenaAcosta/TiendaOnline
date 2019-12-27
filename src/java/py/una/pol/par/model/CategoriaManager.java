/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.par.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import py.una.pol.par.entities.Categoria;
import py.una.pol.par.util.conexion_bd;

/**
 *
 * @author Lore
 */
public class CategoriaManager {

    public static String getCategoriaByDesc(String cat) {
        String retValue = null;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            st = conn.prepareStatement("SELECT  id_categoria FROM categorias WHERE nombre_categoria = (?)");
            st.setString(1, cat);
            rs = st.executeQuery();
            if (rs.next()) {
                retValue= rs.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion_bd.closeConnection(conn);
        }

        return retValue; 
    }
    
    
    public boolean insertar(Categoria c) {
        boolean resul = true;

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = conexion_bd.getConnection();
            st = conn.prepareStatement("INSERT INTO categorias (id_categoria,nombre_categoria, imagen) values (?, ?, ?)");
            st.setString(1, c.getId_categoria());
            st.setString(2, c.getDescripcion());
            st.setString(3, c.getImagen());
            st.execute();

        } catch (SQLException ex) {
            resul = false;
            Logger.getLogger(CategoriaManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoriaManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            conexion_bd.closeConnection(conn);
        }
        
        return resul;
    }
     
      public boolean update(Categoria c) {
        boolean resul = true;

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = conexion_bd.getConnection();
            st = conn.prepareStatement("UPDATE categorias set nombre_categoria = ? where id_categoria = ?");
            st.setString(1, c.getDescripcion());
            st.setString(2, c.getId_categoria());
            st.execute();

        } catch (SQLException ex) {
            resul = false;
            Logger.getLogger(CategoriaManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion_bd.closeConnection(conn);
        }

        return resul;
    }
      
      public boolean delete(Categoria c) {
        boolean retValue = true;

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = conexion_bd.getConnection();
            st = conn.prepareStatement("delete from categorias where id_categoria = ?");
            st.setString(1, c.getId_categoria());
            st.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(CategoriaManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion_bd.closeConnection(conn);
        }

        return retValue;
    }
    
     public static Categoria getCategoriaById(String id) {
        Categoria retValue = null;

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            st = conn.prepareStatement("SELECT nombre_categoria FROM categorias WHERE id_categoria = (?)");
            st.setString(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                retValue = new Categoria();
                retValue.setId_categoria(id);
                retValue.setDescripcion(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion_bd.closeConnection(conn);
        }

        return retValue;
    }
   public static ArrayList<Categoria> getAll() {
        ArrayList<Categoria> lista = new ArrayList<Categoria>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            st = conn.prepareStatement("SELECT id_categoria, nombre_categoria, imagen FROM categorias order by 1");
            rs = st.executeQuery();
            while (rs.next()) {
                Categoria c = new Categoria(rs.getString(1),rs.getString(2), rs.getString(3));
                lista.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion_bd.closeConnection(conn);
        }

        return lista;
    }       

}
