/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectolavadero;

import java.sql.Connection;
import java.sql.DriverManager;
//import javax.swing.JOptionPane;
import java.sql.SQLException;

/**
 *
 * @author juaco
 */
public class ConexionSQLServer {
    private Connection conexion = null;
    
    private final String usuario = "admin";
    private final String contrasena = "root";
    private final String db = "dbOriginal";
    private final String ip = "localhost";
    private final String puerto = "1433";
    
    public Connection obtenerConexion() {
        try {
            // Cargar el driver (opcional en versiones recientes de Java)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Cadena de conexión
            String cadena = "jdbc:sqlserver://" + ip + ":" + puerto + ";"
                          + "databaseName=" + db + ";"
                          + "encrypt=true;"
                          + "trustServerCertificate=true;";

            // Intentar conectar
            conexion = DriverManager.getConnection(cadena, usuario, contrasena);
            System.out.println("Conexión exitosa a la base de datos.");

        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver de SQL Server.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }

        return conexion;
    }
}
