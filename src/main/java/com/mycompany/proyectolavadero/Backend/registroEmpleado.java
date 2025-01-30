

package com.mycompany.proyectolavadero.Backend;

import com.mycompany.proyectolavadero.ConexionSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class registroEmpleado {
    
    public boolean registrarEmpleado(String nombre, String apellido, String ci, String telefono, String direccion, String correo, String equipoLavado) {
        boolean registrado = false;

        if (!validarNombre(nombre)) {
            JOptionPane.showMessageDialog(null, "Error: El nombre debe iniciar con mayúsculas, sin números ni caracteres especiales y no superar los 50 caracteres.");
            return false;
        }
        
        if (!validarApellido(apellido)){
            JOptionPane.showMessageDialog(null,"Error: El apellido debe iniciar con mayúsculas, sin números ni caracteres especiales y no superar los 50 caracteres.");
            return false;
        }
        
        if (!validarCI(ci)) {
            JOptionPane.showMessageDialog(null, "Error: El CI debe tener exactamente 10 caracteres y no contener símbolos o letras.");
            return false;
        }
        
        if (!validarTelefono(telefono)) {
            JOptionPane.showMessageDialog(null, "Error: El teléfono debe contener solo 10 números.");
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

        if (!validarEquipoLavado(equipoLavado)) {
            JOptionPane.showMessageDialog(null, "Error: Seleccione un equipo de lavado válido.");
            return false;
        }
        
        Connection conexion = null;
        PreparedStatement stmt = null;
        
        try {
            ConexionSQLServer conexionDB = new ConexionSQLServer();
            conexion = conexionDB.obtenerConexion();

            String sql = "INSERT INTO empleado (Nombre, Apellido, CI, Telefono, Direccion, Correo, Equipo_Lavado) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, ci);
            stmt.setString(4, telefono);
            stmt.setString(5, direccion);
            stmt.setString(6, correo);
            stmt.setString(7, equipoLavado); // Tomamos el equipo seleccionado del JComboBox

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

    private boolean validarNombre(String nombre) {
        return nombre.matches("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+( [A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)*$") && nombre.length() <= 50;
    }

    private boolean validarApellido(String apellido){
        return apellido.matches("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+( [A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)*$") && apellido.length() <= 50;
    }

    private boolean validarCI(String ci) {
        return ci.matches("^[0-9]{10}$");
    }

    private boolean validarTelefono(String telefono) {
        return telefono.matches("^[0-9]{10}$");
    }

    private boolean validarDireccion(String direccion) {
        return direccion.length() <= 250;
    }

    private boolean validarCorreo(String correo) {
        return  correo.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$") && correo.length() <= 50;
    }

    private boolean validarEquipoLavado(String equipoLavado) {
        return equipoLavado.equals("Equipo de lavado 1") || 
               equipoLavado.equals("Equipo de lavado 2") || 
               equipoLavado.equals("Equipo de lavado 3");
    }
}