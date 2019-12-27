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
import py.una.pol.par.entities.Usuario;
import py.una.pol.par.util.conexion_bd;

/**
 *
 * @author Lore
 */
public class UsuarioManager {

    public UsuarioManager() {
    }
    
    
    public boolean insertar(Usuario u) {
        boolean resul = true;

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = conexion_bd.getConnection();
            st = conn.prepareStatement("INSERT INTO usuario (id_usuario,login_name, contrasena, "
                    + "apellido, nombre, tipo_usuario, correo) values (?, ?, ?, ?, ?, ?, ?)");
            st.setInt(1, u.getIdusuario());
            st.setString(2, u.getLogin_name());
            st.setString(3, u.getContrasenha());
            st.setString(4, u.getApellido());
            st.setString(5, u.getNombre());
            st.setInt(6, u.getTipo_usuario());
            st.setString(7, u.getCorreo());
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
     
      public boolean update(Usuario u) {
        boolean resul = true;

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = conexion_bd.getConnection();
            st = conn.prepareStatement("update usuario set descripcion = ? where id_categoria = ?");
            st.setInt(1, u.getIdusuario());
            st.setString(2, u.getLogin_name());
            st.setString(3, u.getNombre());
            st.execute();

        } catch (SQLException ex) {
            resul = false;
            Logger.getLogger(CategoriaManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion_bd.closeConnection(conn);
        }

        return resul;
    }
      
      public boolean delete(Usuario u) {
        boolean retValue = true;

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = conexion_bd.getConnection();
            st = conn.prepareStatement("delete from usuario where id_usuario = ?");
            st.setInt(1, u.getIdusuario() );
            st.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion_bd.closeConnection(conn);
        }

        return retValue;
    }
    
   
   public static ArrayList<Usuario> getAll() {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = conexion_bd.getConnection();
            st = conn.prepareStatement("SELECT id_usuario, login_name, contrasena, apellido, nombre, tipo_usuario, correo FROM usuario");
            rs = st.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5), rs.getInt(6),rs.getString(7));
                lista.add(u);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion_bd.closeConnection(conn);
        }

        return lista;
    }       
   public Usuario getById(String param){
       Connection conn = null;
        PreparedStatement st=null;
        ResultSet rs=null;
        Usuario usu = null;
        
        try {
            conn = conexion_bd.getConnection();
            st = conn.prepareStatement("SELECT id_usuario, login_name, contrasena, apellido, nombre, tipo_usuario, correo FROM usuario where id_usuario = ?");
            st.setString(1, param);
            rs = st.executeQuery();
            if (rs.next()) {
                usu = new Usuario();
                usu.setIdusuario(rs.getInt(1));
                usu.setLogin_name(rs.getString(2));
                usu.setContrasenha(rs.getString(3));
                usu.setApellido(rs.getString(4));
                usu.setNombre(rs.getString(5));
                usu.setTipo_usuario(rs.getInt(6));
                usu.setCorreo(rs.getString(7));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion_bd.closeConnection(conn);
        }
       
       return usu;
   }
   public Usuario getByAlias_or_Mail(String param){
       
        Connection conn = null;
        PreparedStatement st=null;
        ResultSet rs=null;
        Usuario usu = null;
        
        try {
            conn = conexion_bd.getConnection();
            st = conn.prepareStatement("SELECT id_usuario, login_name, contrasena, apellido, nombre, tipo_usuario, correo FROM usuario where login_name = ?");
            st.setString(1, param);
            rs = st.executeQuery();
            if (rs.next()) {
                usu = new Usuario();
                usu.setIdusuario(rs.getInt(1));
                usu.setLogin_name(rs.getString(2));
                usu.setContrasenha(rs.getString(3));
                usu.setApellido(rs.getString(4));
                usu.setNombre(rs.getString(5));
                usu.setTipo_usuario(rs.getInt(6));
                usu.setCorreo(rs.getString(7));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion_bd.closeConnection(conn);
        }
       
       return usu;
   }
}
