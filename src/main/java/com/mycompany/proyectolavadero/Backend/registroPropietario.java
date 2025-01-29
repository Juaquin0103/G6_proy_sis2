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
import java.sql.Statement;
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
    
    
    /*
    /////////////////////////////////////////////////
    // Método para cargar datos en la tabla
    public void cargarDatosEnTabla(JTable tabla) {
        // Obtener el modelo actual de la tabla
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);  // Limpiar la tabla antes de llenarla

        Connection conexion = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            ConexionSQLServer conexionDB = new ConexionSQLServer();
            conexion = conexionDB.obtenerConexion();

            String sql = "SELECT Ci, Nombre_Completo, telefono, direccion, email FROM cliente";
            stmt = conexion.createStatement();
            rs = stmt.executeQuery(sql);

            // Llenar el modelo de la tabla con los datos obtenidos
            while (rs.next()) {
                String ci = rs.getString("Ci");
                String nombre = rs.getString("Nombre_Completo");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                String email = rs.getString("email");

                modelo.addRow(new Object[]{ci, nombre, telefono, direccion, email});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos: " + e.getMessage());
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
    }

    // Método para actualizar los datos del cliente desde la tabla
    public boolean actualizarClienteDesdeTabla(JTable tabla) {
        int filaSeleccionada = tabla.getSelectedRow();

    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para editar.");
        return false;
    }

    // Obtener los datos de la fila seleccionada
    String ci = (String) tabla.getValueAt(filaSeleccionada, 0);
    String nombre = (String) tabla.getValueAt(filaSeleccionada, 1);
    String telefono = (String) tabla.getValueAt(filaSeleccionada, 2);
    String direccion = (String) tabla.getValueAt(filaSeleccionada, 3);
    String correo = (String) tabla.getValueAt(filaSeleccionada, 4);

    // Actualizar en la base de datos
    Connection conexion = null;
    PreparedStatement stmt = null;

    try {
        ConexionSQLServer conexionDB = new ConexionSQLServer();
        conexion = conexionDB.obtenerConexion();

        String sql = "UPDATE cliente SET Nombre_Completo=?, telefono=?, direccion=?, email=? WHERE Ci=?";
        stmt = conexion.prepareStatement(sql);
        stmt.setString(1, nombre);
        stmt.setString(2, telefono);
        stmt.setString(3, direccion);
        stmt.setString(4, correo);
        stmt.setString(5, ci);

        int filasActualizadas = stmt.executeUpdate();
        if (filasActualizadas > 0) {
            JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el cliente. Verifica los datos.");
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
    }*/
}

