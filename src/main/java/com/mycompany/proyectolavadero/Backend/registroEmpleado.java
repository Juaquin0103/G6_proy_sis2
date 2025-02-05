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
public class registroEmpleado {
    public boolean registrarEmpleado(String nombre, String apellido, String ci, String telefono, String direccion, String correo, String equipoLavado) {
        boolean registrado = false;

        // Conexión y registro en la base de datos
        Connection conexion = null;
        PreparedStatement stmt = null;

        try {
            ConexionSQLServer conexionDB = new ConexionSQLServer();
            conexion = conexionDB.obtenerConexion();

            String sql = "INSERT INTO Empleados (Nombre_empleado, Apellidos_empleado, Ci, Telefono, Direccion, Email, EquipoLavado) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, ci);
            stmt.setString(4, telefono);
            stmt.setString(5, direccion);
            stmt.setString(6, correo);
            stmt.setString(7, equipoLavado);

            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                registrado = true;
                JOptionPane.showMessageDialog(null, "Empleado registrado exitosamente.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar empleado: " + e.getMessage());
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

    // Validaciones
    public boolean validarNombre(String nombre) {
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: El nombre no puede estar vacío.");
            return false;
        }
        if (nombre.length() > 50) {
            JOptionPane.showMessageDialog(null, "Error: El nombre no puede superar los 50 caracteres.");
            return false;
        }
        if (!nombre.matches("^[A-Z][a-z]+( [A-Z][a-z]+)*$")) {
            JOptionPane.showMessageDialog(null, "Error: El nombre debe comenzar con mayúsculas y no contener números o símbolos.");
            return false;
        }
        return true;
    }

    public boolean validarApellido(String apellido) {
        if (apellido.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: El apellido no puede estar vacío.");
            return false;
        }
        if (apellido.length() > 50) {
            JOptionPane.showMessageDialog(null, "Error: El apellido no puede superar los 50 caracteres.");
            return false;
        }
        if (!apellido.matches("^[A-Z][a-z]+( [A-Z][a-z]+)*$")) {
            JOptionPane.showMessageDialog(null, "Error: El apellido debe comenzar con mayúsculas y no contener números o símbolos.");
            return false;
        }
        return true;
    }

    public boolean validarCI(String ci) {
        if (ci.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: El CI no puede estar vacío.");
            return false;
        }
        if (ci.contains(" ")) {
            JOptionPane.showMessageDialog(null, "Error: El CI no debe contener espacios.");
            return false;
        }
        if (!ci.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(null, "Error: El CI debe contener exactamente 8 dígitos y no contener letras o simbolos.");
            return false;
        }
        if (existeCIEnBD(ci)) {
            JOptionPane.showMessageDialog(null, "Error: El CI ingresado ya está registrado.");
            return false;
        }
        
        return true;
    }

    public boolean validarTelefono(String telefono) {
        if (telefono.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: El teléfono no puede estar vacío.");
            return false;
        }
        if (!telefono.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(null, "Error: El teléfono debe contener exactamente 8 dígitos y no contener letras o simbolos.");
            return false;
        }
        if (telefono.contains(" ")) {
            JOptionPane.showMessageDialog(null, "Error: El teléfono no debe contener espacios.");
            return false;
        }
        if (existeCIEnBD(telefono)) {
            JOptionPane.showMessageDialog(null, "Error: El telefono ingresado ya está registrado.");
            return false;
        }
        return true;
    }

    public boolean validarDireccion(String direccion) {
        if (direccion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: La dirección no puede estar vacía.");
            return false;
        }
        if (direccion.length() > 250) {
            JOptionPane.showMessageDialog(null, "Error: La dirección no puede superar los 250 caracteres.");
            return false;
        }
        return true;
    }

    public boolean validarCorreo(String correo) {
        if (correo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: El correo no puede estar vacío.");
            return false;
        }
        if (!correo.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            JOptionPane.showMessageDialog(null, "Error: El formato del correo es incorrecto.");
            return false;
        }
        if (correo.length() > 100) {
            JOptionPane.showMessageDialog(null, "Error: El correo no puede superar los 100 caracteres.");
            return false;
        }
        return true;
    }

    public boolean validarEquipoLavado(String equipoLavado) {
        if (!(equipoLavado.equals("E1") || equipoLavado.equals("E2") || equipoLavado.equals("E3"))) {
            JOptionPane.showMessageDialog(null, "Error: Seleccione un equipo de lavado válido.");
            return false;
        }
        return true;
    }

    private boolean existeCIEnBD(String ci) {
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            ConexionSQLServer conexionDB = new ConexionSQLServer();
            conexion = conexionDB.obtenerConexion();

            String sql = "SELECT COUNT(*) FROM Empleados WHERE Ci = ?";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, ci);
            rs = stmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                existe = true;
            }

        } catch (SQLException e) {
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

        return existe;
    }
    private boolean existeTelefonoEnBD(String telefono) {
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            ConexionSQLServer conexionDB = new ConexionSQLServer();
            conexion = conexionDB.obtenerConexion();

            String sql = "SELECT COUNT(*) FROM Empleados WHERE Telefono = ?";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, telefono);
            rs = stmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                existe = true;
            }

        } catch (SQLException e) {
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

        return existe;
    }
}
