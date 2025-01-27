/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectolavadero;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author juaco
 */
public class ConexionSQLServer {
    Connection conexion = null;
    
    String usuario = "sqlUsuario";
    String contrasena = "root";
    String db = "dbEjemplo";
    String ip = "localhost";
    String puerto = "1433";
    
    public Connection obtenerConexion() {
        try {
            String cadena = "jdbc:sqlserver://" + ip + ":" + puerto + ";" +
                            "databaseName=" + db + ";" +
                            "encrypt=true;" +
                            "trustServerCertificate=true;";
            conexion = DriverManager.getConnection(cadena, usuario, contrasena);
            JOptionPane.showMessageDialog(null, "Conexion exitosa");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: "+e.toString());
        }
        return conexion;
        //hola
    }
}
