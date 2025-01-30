

package com.mycompany.proyectolavadero.Backend;

import com.mycompany.proyectolavadero.ConexionSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class registroEmpleado {
    
    public boolean registrarEmpleado(String nombre, String apellido, String ci, String telefono, String direccion, String correo, String equipoLavado) {
        boolean registrado = false;

        // Validaciones
        if (!validarNombre(nombre)) {
            JOptionPane.showMessageDialog(null, "Error: El nombre debe iniciar con mayúsculas y contener solo letras. Máximo 20 caracteres.");
            return false;
        }

        if (!validarApellido(apellido)) {
            JOptionPane.showMessageDialog(null, "Error: El apellido debe iniciar con mayúsculas y contener solo letras. Máximo 30 caracteres.");
            return false;
        }

        if (!validarCI(ci)) {
            return false;  // Los mensajes de error están en la función validarCI
        }

        if (!validarTelefono(telefono)) {
            return false;  // Los mensajes de error están en la función validarTelefono
        }

        if (!validarDireccion(direccion)) {
            JOptionPane.showMessageDialog(null, "Error: La dirección no puede superar los 250 caracteres.");
            return false;
        }

        if (!validarCorreo(correo)) {
            JOptionPane.showMessageDialog(null, "Error: El correo debe ser válido y contener '@'. Máximo 50 caracteres.");
            return false;
        }

        if (!validarEquipoLavado(equipoLavado)) {
            JOptionPane.showMessageDialog(null, "Error: Seleccione un equipo de lavado válido.");
            return false;
        }

        // Conexión y registro en la base de datos
        Connection conexion = null;
        PreparedStatement stmt = null;

        try {
            ConexionSQLServer conexionDB = new ConexionSQLServer();
            conexion = conexionDB.obtenerConexion();

            String sql = "INSERT INTO Empleados (Nombre_Empleado, Apellidos_Empleado, CI, Telefono, Direccion, Email, EquipoLavado) VALUES (?, ?, ?, ?, ?, ?, ?)";
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

    private boolean validarNombre(String nombre) {
        if (nombre.length() > 20) {
            JOptionPane.showMessageDialog(null, "Error: El nombre no puede superar los 20 caracteres.");
            return false;
        }
        if (!nombre.matches("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+( [A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)*$")) {
            JOptionPane.showMessageDialog(null, "Error: El nombre debe comenzar con mayúsculas y no contener números o caracteres especiales.");
            return false;
        }
        return true;
    }

    private boolean validarApellido(String apellido) {
        if (apellido.length() > 30) {
            JOptionPane.showMessageDialog(null, "Error: El apellido no puede superar los 30 caracteres.");
            return false;
        }
        if (!apellido.matches("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+( [A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)*$")) {
            JOptionPane.showMessageDialog(null, "Error: El apellido debe comenzar con mayúsculas y no contener números o caracteres especiales.");
            return false;
        }
        return true;
    }

    private boolean validarCI(String ci) {
        if (ci.contains(" ")) {
            JOptionPane.showMessageDialog(null, "Error: El CI no debe contener espacios.");
            return false;
        }
        if (!ci.matches("^[0-9]{10}$")) {
            JOptionPane.showMessageDialog(null, "Error: El CI debe contener exactamente 10 dígitos.");
            return false;
        }
        return true;
    }

    private boolean validarTelefono(String telefono) {
        if (telefono.contains(" ")) {
            JOptionPane.showMessageDialog(null, "Error: El teléfono no debe contener espacios.");
            return false;
        }
        if (!telefono.matches("^[0-9]{10}$")) {
            JOptionPane.showMessageDialog(null, "Error: El teléfono debe contener exactamente 10 dígitos.");
            return false;
        }
        return true;
    }

    private boolean validarDireccion(String direccion) {
        return direccion.length() <= 250;
    }

    private boolean validarCorreo(String correo) {
        if (correo.contains(" ")) {
            JOptionPane.showMessageDialog(null, "Error: El correo no debe contener espacios.");
            return false;
        }
        if (!correo.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            JOptionPane.showMessageDialog(null, "Error: El correo debe ser válido y contener '@'.");
            return false;
        }
        return correo.length() <= 50;
    }

    private boolean validarEquipoLavado(String equipoLavado) {
        return equipoLavado.equals("E1") || 
               equipoLavado.equals("E2") || 
               equipoLavado.equals("E3");
    }
}