/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectolavadero.Backend;

import com.mycompany.proyectolavadero.ConexionSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Windows
 */
public class registroPropietario {
    
        public boolean registrarCliente(String nombre, String ci, String telefono, String direccion, String correo) {
        boolean registrado = false;

        // 🔍 VALIDACIONES
        if (!validarNombre(nombre)) {
            JOptionPane.showMessageDialog(null, "Error: El nombre debe iniciar con mayúsculas, sin números ni caracteres especiales");
            return false;
        }
        
        if (!validarCI(ci)) {
            JOptionPane.showMessageDialog(null, "Error: El CI debe tener exactamente 8 o 10 caracteres y no contener símbolos o letras.");
            return false;
        }
        
        if (!validarTelefono(telefono)) {
            JOptionPane.showMessageDialog(null, "Error: El teléfono debe contener solo 8 números.");
            return false;
        }

        if (!validarDireccion(direccion)) {
            JOptionPane.showMessageDialog(null, "Error: La dirección no puede superar los 250 caracteres.");
            return false;
        }

        if (!validarCorreo(correo)) {
            JOptionPane.showMessageDialog(null, "Error: El correo debe ser válido y contener '@'. Máximo 50 caracteres.");
            return false;
        }
        
        // 🔥 SI TODAS LAS VALIDACIONES SON EXITOSAS, REGISTRAR EN BD
        Connection conexion = null;
        PreparedStatement stmt = null;
        
        try {
            ConexionSQLServer conexionDB = new ConexionSQLServer();
            conexion = conexionDB.obtenerConexion();

            String sql = "INSERT INTO cliente (Nombre_Completo, Ci, telefono, direccion, email) VALUES (?, ?, ?, ?, ?)";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, ci);
            stmt.setString(3, telefono);
            stmt.setString(4, direccion);
            stmt.setString(5, correo);

            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                registrado = true;
                JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente.");
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

    // 🚀 VALIDACIONES
    
    // ✅ Validar Nombre (Empieza con mayúscula, sin números ni caracteres especiales, y <= 50 caracteres)
    boolean validarNombre(String nombre) {
        // Verificar longitud
        if (nombre.length() < 10 || nombre.length() > 50) {
            JOptionPane.showMessageDialog(null, "Error: El nombre completo debe tener entre 10 y 50 caracteres.");
            return false;
        }

        // Verificar formato (mayúscula al inicio de cada palabra, sin caracteres especiales ni números)
        if (!nombre.matches("^([A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)( [A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)+$")) {
            JOptionPane.showMessageDialog(null, "Error: El nombre completo debe comenzar con mayúsculas y no debe contener números o caracteres especiales.");
            return false;
        }

        return true;
    }

    // ✅ Validar CI (Solo números, exactamente 10 caracteres)
    boolean validarCI(String ci) {
        if (ci == null || ci.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Error: El CI no puede estar vacío.");
        return false;
        }
        if (!ci.matches("\\d{8,10}")) {
            JOptionPane.showMessageDialog(null, "Error: El CI debe contener exactamente  8 o 10 dígitos numéricos.");
            return false;
        }
        return true;
    }

    // ✅ Validar Teléfono (Solo números, exactamente 10 caracteres)
    boolean validarTelefono(String telefono) {
        if (telefono == null || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: El teléfono no puede estar vacío.");
            return false;
        }
        if (!telefono.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(null, "Error: El teléfono debe contener exactamente 8 dígitos.");
            return false;
        }
        return true;
    }

    // ✅ Validar Dirección (No más de 250 caracteres)
    boolean validarDireccion(String direccion) {
        return direccion.length() <= 50;
    }

    // ✅ Validar Correo Electrónico (Debe contener  un dominio valido y '@',  y no superar los 50 caracteres)
    boolean validarCorreo(String correo) {
        
        return (correo.endsWith("@gmail.com") || correo.endsWith("@hotmail.com") )&& correo.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$") && correo.length() <= 50 ;
    }
}

