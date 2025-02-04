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

public class listarVehiculo {
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
            String sql = "SELECT c.Nombre_Completo, v.Placa, v.Tipo_Vehiculo FROM cliente c " +
                         "JOIN vehiculos v ON c.Ci = v.Ci";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("Nombre_Completo"),
                    rs.getString("Placa"),
                    rs.getString("Tipo_Vehiculo"),
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

    // Actualizar cliente y vehículo en la base de datos desde la tabla
    public boolean actualizarVehiculoDesdeTabla(JTable tabla) {
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para editar.");
            return false;
        }

        // Obtener los datos modificados de la fila seleccionada
        String Nombre_Completo = (String) tabla.getValueAt(filaSeleccionada, 0); // Nombre del cliente
        String Placa = (String) tabla.getValueAt(filaSeleccionada, 1); // Placa del vehículo
        String Tipo_Vehiculo = (String) tabla.getValueAt(filaSeleccionada, 2); // Tipo de vehículo

        // Recuperar el Ci (clave primaria)
        String Ci = obtenerCiPorPlaca(Placa);

        if (Ci == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el cliente asociado a esta placa.");
            return false;
        }

        // Validaciones
        if (!Nombre_Completo.isEmpty() && !Placa.isEmpty() && !Tipo_Vehiculo.isEmpty()) {
            Connection conexion = null;
            PreparedStatement stmt = null;

            try {
                ConexionSQLServer conexionDB = new ConexionSQLServer();
                conexion = conexionDB.obtenerConexion();

                String sql = "UPDATE cliente SET Nombre_Completo = ? WHERE Ci = ?; " +
                             "UPDATE vehiculos SET Placa = ?, Tipo_Vehiculo = ? WHERE Ci = ?";

                stmt = conexion.prepareStatement(sql);
                stmt.setString(1, Nombre_Completo);
                stmt.setString(2, Ci);
                stmt.setString(3, Placa);
                stmt.setString(4, Tipo_Vehiculo);
                stmt.setString(5, Ci);

                int filasActualizadas = stmt.executeUpdate();
                if (filasActualizadas > 0) {
                    JOptionPane.showMessageDialog(null, "Vehículo actualizado exitosamente.");
                    return true;
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al actualizar vehículo: " + e.getMessage());
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

    // Método auxiliar para obtener el Ci del cliente a partir de la placa del vehículo
    private String obtenerCiPorPlaca(String placa) {
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String Ci = null;

        try {
            ConexionSQLServer conexionDB = new ConexionSQLServer();
            conexion = conexionDB.obtenerConexion();
            String sql = "SELECT Ci FROM vehiculos WHERE Placa = ?";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, placa);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Ci = rs.getString("Ci");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener el Ci: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Ci;
    }
}
