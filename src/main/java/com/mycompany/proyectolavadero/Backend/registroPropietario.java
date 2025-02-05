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
public class registroPropietario {
    
    public String validarNombre(String nombre) {
        if (nombre.isEmpty()) {
            return "Debes ingresar un nombre.";
        }
        if (nombre.matches(".*\\d.*") || nombre.matches(".*[!@#$%^&*()_+=\\-{}|:;\"'<>,.?/`~].*")) {
            return "El nombre no debe contener números ni símbolos.";
        }
        if (!nombre.matches("^[A-ZÁÉÍÓÚÑ].*")) {
            return "El nombre debe comenzar con mayúsculas.";
        }
        if (!nombre.matches("^([A-ZÁÉÍÓÚÑ][a-záéíóúñ]* )(.* )*[A-ZÁÉÍÓÚÑ][a-záéíóúñ]*$")) {
            return "El nombre y los apellidos deben estar separados por espacios.";
        }
        return "";
    }

    public String validarCI(String ci) {
        if (ci.isEmpty()) {
            return "Debes ingresar el C.I. o NIT.";
        }
        if (ci.length() < 8) {
            return "El C.I. o NIT debe tener al menos 8 dígitos.";
        }
        if (ci.length() > 10) {
            return "El C.I. o NIT no puede tener más de 10 dígitos.";
        }
        if (!ci.matches("\\d+")) {
            return "El C.I. o NIT solo puede contener números.";
        }
        if (ci.contains(" ")) {
            return "El C.I. o NIT no debe contener espacios.";
        }
        return "";
    }

    public String validarTelefono(String telefono) {
        if (telefono.isEmpty()) {
            return "Debes ingresar un número de teléfono.";
        }
        if (telefono.length() != 8) {
            return "El número de teléfono debe tener exactamente 8 dígitos.";
        }
        if (!telefono.matches("\\d+")) {
            return "El número de teléfono solo puede contener números.";
        }
        if (telefono.contains(" ")) {
            return "El número de teléfono no debe contener espacios.";
        }
        return "";
    }

    public String validarDireccion(String direccion) {
        if (direccion.isEmpty()) {
            return "Debes ingresar una dirección.";
        }
        if (direccion.length() > 250) {
            return "La dirección no puede superar los 250 caracteres.";
        }
        return "";
    }

    public String validarCorreo(String correo) {
        if (correo.isEmpty()) {
            return "Debes ingresar un correo electrónico.";
        }
        if (!correo.contains("@") || !correo.contains(".")) {
            return "El correo debe contener un '@' y un '.' (punto).";
        }
        if (!correo.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return "El correo ingresado está incompleto o tiene un formato incorrecto.";
        }
        return "";
    }

    public boolean esNombreDuplicado(String nombre) {
        return verificarDuplicado("Nombre_Completo", nombre, "El nombre ya existe en la base de datos.");
    }

    public boolean esCIDuplicado(String ci) {
        return verificarDuplicado("Ci", ci, "El C.I. o NIT ya está registrado.");
    }

    public boolean esTelefonoDuplicado(String telefono) {
        return verificarDuplicado("telefono", telefono, "El número de teléfono ya está registrado.");
    }

    public boolean esCorreoDuplicado(String correo) {
        return verificarDuplicado("email", correo, "El correo electrónico ya está registrado.");
    }

    private boolean verificarDuplicado(String campo, String valor, String mensajeError) {
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean duplicado = false;

        try {
            ConexionSQLServer conexionDB = new ConexionSQLServer();
            conexion = conexionDB.obtenerConexion();

            String sql = "SELECT COUNT(*) FROM cliente WHERE " + campo + " = ?";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, valor);

            rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                duplicado = true;
                JOptionPane.showMessageDialog(null, mensajeError);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al verificar duplicado: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return duplicado;
    }

    public boolean registrarCliente(String nombre, String ci, String telefono, String direccion, String correo) {
        boolean registrado = false;
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
}

