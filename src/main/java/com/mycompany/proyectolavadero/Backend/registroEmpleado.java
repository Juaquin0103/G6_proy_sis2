package com.mycompany.proyectolavadero.Backend;

import com.mycompany.proyectolavadero.ConexionSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class registroEmpleado {

    public boolean registrarEmpleado(String nombre, String apellido, String ci, String telefono, String direccion, String correo, String equipo) {
        if (!validarNombre(nombre) || !validarNombre(apellido) || !validarCI(ci) || 
            !validarTelefono(telefono) || !validarDireccion(direccion) || !validarCorreo(correo) || !validarEquipo(equipo)) {
            return false;
        }

        String sql = "INSERT INTO empleado (Nombre, Apellido, CI, Telefono, Direccion, Correo, Equipo) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = new ConexionSQLServer().obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, ci);
            stmt.setString(4, telefono);
            stmt.setString(5, direccion);
            stmt.setString(6, correo);
            stmt.setString(7, equipo);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al registrar empleado: " + e.getMessage());
            return false;
        }
    }

    public void cargarDatosEnTabla(JTable tabla) {
        String sql = "SELECT CI, Nombre, Apellido, Telefono, Direccion, Correo, Equipo FROM empleado";
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        try (Connection conexion = new ConexionSQLServer().obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("CI"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido"),
                    rs.getString("Telefono"),
                    rs.getString("Direccion"),
                    rs.getString("Correo"),
                    rs.getString("Equipo")
                });
            }
        } catch (SQLException e) {
            System.err.println("Error al cargar datos: " + e.getMessage());
        }
    }

    public boolean actualizarEmpleadoDesdeTabla(JTable tabla) {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) return false;

        String ci = tabla.getValueAt(filaSeleccionada, 0).toString();
        String nombre = tabla.getValueAt(filaSeleccionada, 1).toString();
        String apellido = tabla.getValueAt(filaSeleccionada, 2).toString();
        String telefono = tabla.getValueAt(filaSeleccionada, 3).toString();
        String direccion = tabla.getValueAt(filaSeleccionada, 4).toString();
        String correo = tabla.getValueAt(filaSeleccionada, 5).toString();
        String equipo = tabla.getValueAt(filaSeleccionada, 6).toString();

        if (!validarNombre(nombre) || !validarNombre(apellido) || !validarCI(ci) || 
            !validarTelefono(telefono) || !validarDireccion(direccion) || !validarCorreo(correo) || !validarEquipo(equipo)) {
            return false;
        }

        String sql = "UPDATE empleado SET Nombre=?, Apellido=?, Telefono=?, Direccion=?, Correo=?, Equipo=? WHERE CI=?";

        try (Connection conexion = new ConexionSQLServer().obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, telefono);
            stmt.setString(4, direccion);
            stmt.setString(5, correo);
            stmt.setString(6, equipo);
            stmt.setString(7, ci);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar empleado: " + e.getMessage());
            return false;
        }
    }

    // 🚀 Métodos de Validación
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
    }

    private boolean validarEquipo(String equipo) {
        return equipo.equals("Equipo de lavado 1") || 
               equipo.equals("Equipo de lavado 2") || 
               equipo.equals("Equipo de lavado 3");
    }
}
