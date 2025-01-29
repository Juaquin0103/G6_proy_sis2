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

import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Windows
 */
public class registroPropietario {
    
        public boolean registrarCliente(String nombre, String ci, String telefono, String direccion, String correo) {
        boolean registrado = false;

        // 🔍 VALIDACIONES
        if (!validarNombre(nombre)) {
            JOptionPane.showMessageDialog(null, "Error: El nombre debe iniciar con mayúsculas, sin números ni caracteres especiales y no superar los 50 caracteres.");
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
    private boolean validarNombre(String nombre) {
        return nombre.matches("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+( [A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)*$") && nombre.length() <= 50;
    }

    // ✅ Validar CI (Solo números, exactamente 10 caracteres)
    private boolean validarCI(String ci) {
        return ci.matches("^[0-9]{10}$");
    }

    // ✅ Validar Teléfono (Solo números, exactamente 10 caracteres)
    private boolean validarTelefono(String telefono) {
        return telefono.matches("^[0-9]{10}$");
    }

    // ✅ Validar Dirección (No más de 250 caracteres)
    private boolean validarDireccion(String direccion) {
        return direccion.length() <= 250;
    }

    // ✅ Validar Correo Electrónico (Debe contener '@' y no superar los 50 caracteres)
    private boolean validarCorreo(String correo) {
        return correo.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$") && correo.length() <= 50;
    }
    
    /////////////////////////////////////////////////
    // 1️⃣ Cargar datos en el JTable
    public void cargarDatosEnTabla(JTable tabla) {
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de cargar datos

        try {
            ConexionSQLServer conexionDB = new ConexionSQLServer();
            conexion = conexionDB.obtenerConexion();

            String sql = "SELECT Ci, Nombre_Completo, telefono, email, direccion FROM cliente";
            stmt = conexion.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] fila = {
                    rs.getString("Ci"),
                    rs.getString("Nombre_Completo"),
                    rs.getString("telefono"),
                    rs.getString("email"),
                    rs.getString("direccion")
                };
                modelo.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar datos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 2️⃣ Actualizar cliente seleccionado
    public boolean actualizarClienteDesdeTabla(JTable tabla) {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un cliente para editar.");
            return false;
        }

        // Obtener datos desde el JTable
        String ci = tabla.getValueAt(filaSeleccionada, 0).toString();
        String nombre = tabla.getValueAt(filaSeleccionada, 1).toString();
        String telefono = tabla.getValueAt(filaSeleccionada, 2).toString();
        String correo = tabla.getValueAt(filaSeleccionada, 3).toString();
        String direccion = tabla.getValueAt(filaSeleccionada, 4).toString();

        // Validaciones
        if (!validarNombre(nombre) || !validarCI(ci) || !validarTelefono(telefono) || !validarDireccion(direccion) || !validarCorreo(correo)) {
            JOptionPane.showMessageDialog(null, "Error en la validación de los datos. No se guardaron los cambios.");
            return false;
        }

        // Actualizar en la base de datos
        Connection conexion = null;
        PreparedStatement stmt = null;

        try {
            ConexionSQLServer conexionDB = new ConexionSQLServer();
            conexion = conexionDB.obtenerConexion();

            String sql = "UPDATE cliente SET Nombre_Completo=?, telefono=?, email=?, direccion=? WHERE Ci=?";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, telefono);
            stmt.setString(3, correo);
            stmt.setString(4, direccion);
            stmt.setString(5, ci);

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente.");
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar cliente: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    // Validaciones (Mismas que las del registro)
    /*
    private boolean validarNombre(String nombre) {
        return nombre.matches("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+( [A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)*$") && nombre.length() <= 50;
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
        return correo.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$") && correo.length() <= 50;
    }*/
}

