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
public class ListarEquipoLavado {
    public void cargarDatosEnTabla(JTable tablaEquipo1, JTable tablaEquipo2, JTable tablaEquipo3) {
        DefaultTableModel modeloEquipo1 = new DefaultTableModel(new String[]{"Nombre", "Apellido"}, 0);
        DefaultTableModel modeloEquipo2 = new DefaultTableModel(new String[]{"Nombre", "Apellido"}, 0);
        DefaultTableModel modeloEquipo3 = new DefaultTableModel(new String[]{"Nombre", "Apellido"}, 0);

        String sql = "SELECT Nombre, Apellido, Equipo_Lavado FROM empleado";

        try (Connection conexion = new ConexionSQLServer().obtenerConexion();
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nombre = rs.getString("Nombre");
                String apellido = rs.getString("Apellido");
                String equipo = rs.getString("Equipo_Lavado");

                Object[] fila = {nombre, apellido};

                // Agregar cada empleado a la tabla correspondiente según su equipo
                switch (equipo.toLowerCase()) {
                    case "e1":
                        modeloEquipo1.addRow(fila);
                        break;
                    case "e2":
                        modeloEquipo2.addRow(fila);
                        break;
                    case "e3":
                        modeloEquipo3.addRow(fila);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Equipo desconocido: " + equipo);
                }
            }

            // Asignar los modelos a las tablas
            tablaEquipo1.setModel(modeloEquipo1);
            tablaEquipo2.setModel(modeloEquipo2);
            tablaEquipo3.setModel(modeloEquipo3);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos en las tablas: " + e.getMessage());
        }
    }
}
