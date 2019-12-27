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
import py.una.pol.par.entities.Cabecera;
import py.una.pol.par.entities.Detalle;
import py.una.pol.par.entities.Productos;
import py.una.pol.par.util.conexion_bd;

/**
 *
 * @author Lore
 */
public class TransaccionManager {
    
     public boolean insertarCabecera(Cabecera c) {
        boolean resul = true;

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = conexion_bd.getConnection();
            st = conn.prepareStatement("insert into transaccioncab (id_transaccion, id_usuario, "
                    + "fecha, total, direccion_envio, estado, medio_de_pago, nro_tarjeta) "
                    + "values (?,?, ?, ?, ?, ?, ?, ?)");
            st.setInt(1, c.getId_transaccion());
            st.setInt(2, c.getId_usuario());
            st.setString(3, c.getFecha());
            st.setInt(4, c.getTotal());
            st.setString(5, c.getDireccion_envio());
            st.setString(6, c.getEstado());
            st.setInt(7, c.getMedio_pago());
            st.setInt(8, c.getNro_tarjeta());
            st.execute();
            
        } catch (SQLException ex) {
            resul = false;
            Logger.getLogger(TransaccionManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
             if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductoManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            conexion_bd.closeConnection(conn);
        }

        return resul;
    }

    public boolean insertarDetalle(Detalle detalle) {
       boolean resul = true;

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = conexion_bd.getConnection();
            st = conn.prepareStatement("INSERT INTO transacciondet (id_transaccion,id_producto,cantidad, precio, subtotal, id_detalle) values (?, ?, ?, ?, ?, ?)");
            st.setInt(1, detalle.getId_transaccion());
            st.setString(2, detalle.getId_producto());
            st.setInt(3, detalle.getCantidad());
            st.setInt(4, detalle.getPrecio());
            st.setInt(5, detalle.getSubtotal());
            st.setInt(6, detalle.getId_detalle());
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
       public boolean update(Cabecera c) {
        boolean resul = true;

        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = conexion_bd.getConnection();
            st = conn.prepareStatement("UPDATE transacioncab  direccion_envio = ?,"
                    + " estado= ?,  medio_pago=?,nro_tarjeta=?, estado_trans  where id_transaccion = ?");
            st.setString(5, c.getDireccion_envio());
            st.setString(6, c.getEstado());
            st.setInt(7, c.getMedio_pago());
            st.setInt(8, c.getNro_tarjeta());
            st.setString(9, c.getEstado_trans());
            st.setInt(1, c.getId_transaccion());
            st.execute();

        } catch (SQLException ex) {
            resul = false;
            Logger.getLogger(CategoriaManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion_bd.closeConnection(conn);
        }

        return resul;
    }
       
    public static  ArrayList<Cabecera> getCabeceraById(int id) {
       ArrayList<Cabecera> lista = new ArrayList<Cabecera>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = conexion_bd.getConnection();
            String sentencia = "select id_transaccion, id_usuario,fecha,total from transaccioncab";
            sentencia= sentencia + " " + "WHERE id_usuario = ?";
                        st= conn.prepareStatement(sentencia);
                        st.setInt(1, id);
                        rs = st.executeQuery();
                while (rs.next()) {
                Cabecera c = new Cabecera(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
                lista.add(c);   
              }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion_bd.closeConnection(conn);
        }
        return lista;
    }
      public static ArrayList<Cabecera> getAll() {
        ArrayList<Cabecera> lista = new ArrayList<Cabecera>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = conexion_bd.getConnection();
            String sentencia = "select id_transaccion from transaccioncab";
        
                st = conn.prepareStatement(sentencia);
                rs = st.executeQuery();
                while (rs.next()) {
                Cabecera c = new Cabecera(rs.getInt(1));
                lista.add(c);   
              }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion_bd.closeConnection(conn);
        }
        return lista;
    }   

    public int getIdDetalle() {
        ArrayList<Cabecera> lista = new ArrayList<Cabecera>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int size=0;
        try {
            conn = conexion_bd.getConnection();
            String sentencia = "select max(id_detalle) from transacciondet";
                st = conn.prepareStatement(sentencia);
                rs = st.executeQuery();
                while (rs.next()) {
                   size= rs.getInt(1);   
              }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion_bd.closeConnection(conn);
        }
        return size+1;
    }
}
