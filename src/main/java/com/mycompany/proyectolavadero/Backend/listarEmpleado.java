package com.mycompany.proyectolavadero.Backend;

import com.mycompany.proyectolavadero.ConexionSQLServer;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.*;

public class listarEmpleado {

    public void cargarDatosEnTabla(JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel(new String[]{
            "CI", "Nombre", "Apellido", "Telefono", "Direccion", "Correo", "Equipo_Lavado"
        }, 0);

        String sql = "SELECT CI, Nombre, Apellido, Telefono, Direccion, Correo, Equipo_Lavado FROM empleado";

        try (Connection conexion = new ConexionSQLServer().obtenerConexion();
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("CI"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido"),
                    rs.getString("Telefono"),
                    rs.getString("Direccion"),
                    rs.getString("Correo"),
                    rs.getString("Equipo_Lavado") // Se carga correctamente el equipo de lavado
                });
            }

            tabla.setModel(modelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos: " + e.getMessage());
        }
    }
}