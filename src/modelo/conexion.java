/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Norman
 */
public class conexion {
    
    static String user = "norman";
    static String pass = "Programacion4";
    static String url = "jdbc:mysql://localhost/mydb";
    static Connection con;

    
    
    public static Connection Conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException cnfex) {
            JOptionPane.showMessageDialog(null, "ConexionMySQL:" + cnfex.getMessage());
        } catch (SQLException sqlex) {
            JOptionPane.showMessageDialog(null, "ConexionMySQL:" + sqlex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ConexionMySQL:" + ex.getMessage());
        }
        return con;
    }

    public void cerrarConexion() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
