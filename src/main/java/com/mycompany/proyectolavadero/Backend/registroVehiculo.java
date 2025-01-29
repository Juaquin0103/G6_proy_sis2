/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectolavadero.Backend;
import com.mycompany.proyectolavadero.ConexionSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Windows
 */
public class registroVehiculo {
    public boolean registrarVehiculo(String placa,String Chasis,String tipo_vehiculo,String color, String Modelo, String Marca,String id_cliente,String Preferencias){
        boolean registrado =false;
        if (!validarPlaca(placa)) {
            JOptionPane.showMessageDialog(null, "Error: La placa solo debe de tener Numeros y letras");
            return false;
        }
        
        if (!validarChasis(Chasis)) {
            JOptionPane.showMessageDialog(null, "Error: El Chasis debe tener solo Numeros y letras");
            return false;
        }
        
        if (!validarTipoVehiculo(tipo_vehiculo)) {
            JOptionPane.showMessageDialog(null, "Error: El tipo de vehiculo debe tener 50 caracteres");
            return false;
        }

        if (!validarColor(color)) {
            JOptionPane.showMessageDialog(null, "Error: El color no puede superar los 10 caracteres");
            return false;
        }

        if (!validarModelo(Modelo)) {
            JOptionPane.showMessageDialog(null, "Error: El modelo debe tener un máximo 50 caracteres.");
            return false;
        }
        
        if (!validarMarca(Marca)) {
            JOptionPane.showMessageDialog(null, "Error: La marca debe tener un máximo 50 caracteres.");
            return false;
        }
        
        if (!validarIdCliente(id_cliente)) {
            JOptionPane.showMessageDialog(null, "Error: El cliente no existe");
            return false;
        }
        
        if (!validarPreferencias(Preferencias)) {
            JOptionPane.showMessageDialog(null, "Error: El modelo debe tener un máximo 250 caracteres.");
            return false;
        }
        
        Connection conexion = null;
        PreparedStatement stmt = null;
        
        try{
            ConexionSQLServer conexionDB = new ConexionSQLServer();
            conexion = conexionDB.obtenerConexion();

            String sql = "INSERT INTO cliente (Placa,Chasis,Color,Modelo,Marca,id_cliente,Tipo_Vehiculo,Preferencias) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, placa);
            stmt.setString(2, Chasis);
            stmt.setString(3, color);
            stmt.setString(4, Modelo);
            stmt.setString(5, Marca);
            stmt.setString(6, id_cliente);
            stmt.setString(7, tipo_vehiculo);
            stmt.setString(8, Preferencias);
            
            
            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                registrado = true;
                JOptionPane.showMessageDialog(null, "Vehiculo registrado exitosamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar cliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return registrado;
    }
    
    public boolean validarPlaca(String placa) {
    // Expresión regular para verificar que la placa solo contenga letras y números
    String regex = "^[a-zA-Z0-9]+$";
    
    // Comprobar si la placa cumple con el patrón
    return placa.matches(regex);
    }
    
    public boolean validarChasis(String chasis) {
    // Expresión regular para verificar que el chasis solo contenga letras y números
    String regexChasis = "^[a-zA-Z0-9]+$";
    
    // Comprobar si el chasis cumple con el patrón
    return chasis.matches(regexChasis);
    }
    public boolean validarTipoVehiculo(String tipoVehiculo) {
    // Comprobar si el tipo de vehículo tiene 50 caracteres o menos
    return tipoVehiculo != null && tipoVehiculo.length() <= 50;
    }
    
    public boolean validarColor(String color) {
    // Comprobar si el color no supera los 10 caracteres
    return color != null && color.length() <= 10;
    }
    
    public boolean validarModelo(String modelo) {
    // Comprobar si el modelo no supera los 50 caracteres
    return modelo != null && modelo.length() <= 50;
    }

    public boolean validarMarca(String marca) {
    // Comprobar si la marca no supera los 50 caracteres
    return marca != null && marca.length() <= 50;
    }
    
    public boolean validarIdCliente(String idCliente) {
    
    ConexionSQLServer conexionSQL = new ConexionSQLServer();
    Connection conn = conexionSQL.obtenerConexion(); // Obtener la conexión
    
    String query = "SELECT COUNT(*) FROM clientes WHERE id_cliente = ?";
    
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, idCliente); // Establecer el ID del cliente en la consulta
        ResultSet rs = stmt.executeQuery(); // Ejecutar la consulta
        
        if (rs.next()) {
            return rs.getInt(1) > 0; // Si el resultado es mayor que 0, significa que el cliente existe
        }
    } catch (SQLException e) {
        System.err.println("Error en la consulta: " + e.getMessage());
        e.printStackTrace();
    } finally {
        try {
            if (conn != null) {
                conn.close(); // Cerrar la conexión
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    return false; // Si no encuentra el cliente o ocurre un error
    }
    
    public boolean validarPreferencias(String preferencias) {
    // Comprobar si las preferencias no superan los 250 caracteres
    return preferencias != null && preferencias.length() <= 250;
    }
}
