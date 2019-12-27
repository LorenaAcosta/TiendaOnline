/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.par.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lore
 */
public class conexion_bd {
    
    public static Connection getConnection() throws SQLException {
        String url= "jdbc:postgresql://localhost:8080/carrito_bd";
        String usuario = "postgres";
        String contrasenha = "123";
        try {
            // This loads an instance of the MySQL Driver.
            // The driver has to be in the classpath.
            Class.forName("org.postgresql.Driver");
            //nos permite abrir una conexion a base de datos
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(conexion_bd.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection conexion = DriverManager.getConnection(url, usuario, contrasenha);
        return conexion;
    }
            /*java.sql.Statement st= conexion.createStatement();
            String sql = 
                    "SELECT id_usuario, nombre, apellido FROM usuario";
            ResultSet result = st.executeQuery(sql);
            while (result.next()){
                String id = result.getString("id_usuario");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                System.out.println("ID: "+ id + " NOMBRE: " + nombre + " APELLIDO: " + apellido);
            } */
     public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(conexion_bd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
  
}
