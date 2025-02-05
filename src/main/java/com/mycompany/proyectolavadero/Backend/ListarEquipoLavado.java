/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectolavadero.Backend;

import com.mycompany.proyectolavadero.ConexionSQLServer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.Statement;
/**
 *
 * @author Windows
 */
public class ListarEquipoLavado {
    public void cargarDatosEnTabla(JTable tablaEquipo1, JTable tablaEquipo2, JTable tablaEquipo3) {
        // Definir los modelos de las tablas
        DefaultTableModel modeloEquipo1 = new DefaultTableModel(new String[]{"Nombre", "Apellido"}, 0);
        DefaultTableModel modeloEquipo2 = new DefaultTableModel(new String[]{"Nombre", "Apellido"}, 0);
        DefaultTableModel modeloEquipo3 = new DefaultTableModel(new String[]{"Nombre", "Apellido"}, 0);

        // Consulta SQL para obtener los empleados y sus respectivos equipos
        String sql = "SELECT Nombre_empleado, Apellidos_empleado, EquipoLavado FROM Empleados";

        try (Connection conexion = new ConexionSQLServer().obtenerConexion();
             Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Recorrer los resultados de la consulta
            while (rs.next()) {
                String nombre = rs.getString("Nombre_empleado");
                String apellido = rs.getString("Apellidos_empleado");
                String equipo = rs.getString("EquipoLavado");

                Object[] fila = {nombre, apellido};

                // Verificar y agregar cada empleado a la tabla correspondiente según su equipo
                switch (equipo) {
                    case "E1":
                        modeloEquipo1.addRow(fila);
                        break;
                    case "E2":
                        modeloEquipo2.addRow(fila);
                        break;
                    case "E3":
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
