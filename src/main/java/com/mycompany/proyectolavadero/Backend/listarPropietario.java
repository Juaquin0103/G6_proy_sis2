/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectolavadero.Backend;

import com.mycompany.proyectolavadero.ConexionSQLServer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.*;
/**
 *
 * @author Windows
 */
public class listarPropietario {
    // Cargar datos desde la base de datos al JTable
    public void cargarDatosEnTabla(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);  // Limpiar la tabla antes de llenarla

        Connection conexion = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            ConexionSQLServer conexionDB = new ConexionSQLServer();
            conexion = conexionDB.obtenerConexion();
            stmt = conexion.createStatement();
            String sql = "SELECT Ci, Nombre_Completo, telefono, direccion, email FROM cliente";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("Ci"),
                    rs.getString("Nombre_Completo"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getString("email")
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos: " + e.getMessage());
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

    // Actualizar cliente en la base de datos desde la tabla
    public boolean actualizarClienteDesdeTabla(JTable tabla) {
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para editar.");
            return false;
        }

        // Obtener los datos modificados de la fila seleccionada
        String ci = (String) tabla.getValueAt(filaSeleccionada, 0);
        String nombre = (String) tabla.getValueAt(filaSeleccionada, 1);
        String telefono = (String) tabla.getValueAt(filaSeleccionada, 2);
        String direccion = (String) tabla.getValueAt(filaSeleccionada, 3);
        String correo = (String) tabla.getValueAt(filaSeleccionada, 4);

        // Validaciones (sin restricciones estrictas en este caso)
        if (!nombre.isEmpty() && !telefono.isEmpty() && !direccion.isEmpty() && !correo.isEmpty()) {
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
        } else {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.");
        }

        return false;
    }
}
