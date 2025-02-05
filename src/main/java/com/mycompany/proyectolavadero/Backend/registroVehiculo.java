/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectolavadero.Backend;

import com.mycompany.proyectolavadero.ConexionSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Windows
 */
public class registroVehiculo {
    public boolean registrarVehiculo(String Placa, String Chasis, String Color, String Modelo, String Marca, String Tipo_Vehiculo, String Preferencias, int Ci) {
        boolean registrado = false;

        // Validaciones
        String mensajeError = "";

        if (!validarColor(Color)) {
            mensajeError += "Error: Color inválido.\n";
        }
        if (!validarChasis(Chasis)) {
            mensajeError += "Error: Chasis inválido.\n";
        }
        if (!validarTipoVehiculo(Tipo_Vehiculo)) {
            mensajeError += "Error: Tipo de vehículo inválido.\n";
        }
        if (!validarPlaca(Placa)) {
            mensajeError += "Error: Placa inválida.\n";
        }
        if (!validarModelo(Modelo)) {
            mensajeError += "Error: Modelo inválido.\n";
        }
        if (!validarMarca(Marca)) {
            mensajeError += "Error: Marca inválida.\n";
        }
        if (!validarPreferencias(Preferencias)) {
            mensajeError += "Error: Observaciones inválidas.\n";
        }

        if (!mensajeError.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Errores encontrados:\n" + mensajeError, "Errores de validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Connection conexion = null;
        PreparedStatement stmt = null;

        try {
            ConexionSQLServer conexionDB = new ConexionSQLServer();
            conexion = conexionDB.obtenerConexion();

            String sql = "INSERT INTO Vehiculos (Placa, Chasis, Color, Modelo, Marca, Tipo_Vehiculo, Preferencias, Ci) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, Placa);
            stmt.setString(2, Chasis);
            stmt.setString(3, Color);
            stmt.setString(4, Modelo);
            stmt.setString(5, Marca);
            stmt.setString(6, Tipo_Vehiculo);
            stmt.setString(7, Preferencias);
            stmt.setInt(8, Ci);

            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                registrado = true;
                JOptionPane.showMessageDialog(null, "Vehículo registrado exitosamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar vehículo: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return registrado;
    }

    // Métodos de validación
    public boolean validarColor(String Color) {
        if (Color.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo 'Color' está vacío.");
            return false;
        }
        if (!Character.isUpperCase(Color.charAt(0))) {
            JOptionPane.showMessageDialog(null, "El color debe comenzar con mayúscula.");
            return false;
        }
        if (!Color.matches("^[A-Za-z]+$")) {
            JOptionPane.showMessageDialog(null, "El color no debe contener números o símbolos.");
            return false;
        }
        if (Color.length() > 10) {
            JOptionPane.showMessageDialog(null, "El color no debe superar los 10 caracteres.");
            return false;
        }
        return true;
    }

    public boolean validarChasis(String Chasis) {
        if (Chasis.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo 'Chasis' está vacío.");
            return false;
        }
        if (Chasis.length() > 15) {
            JOptionPane.showMessageDialog(null, "El chasis no debe superar los 15 caracteres.");
            return false;
        }
        if (Chasis.contains(" ")) {
            JOptionPane.showMessageDialog(null, "El chasis no debe contener espacios.");
            return false;
        }
        if (!Chasis.matches("^[A-Z0-9]+$")) {
            JOptionPane.showMessageDialog(null, "El chasis debe contener solo letras mayúsculas y números.");
            return false;
        }
        return true;
    }

    public boolean validarTipoVehiculo(String Tipo_Vehiculo) {
        if (Tipo_Vehiculo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo 'Tipo de Vehículo' está vacío.");
            return false;
        }
        if (Tipo_Vehiculo.length() > 50) {
            JOptionPane.showMessageDialog(null, "El tipo de vehículo no debe superar los 50 caracteres.");
            return false;
        }
        if (!Character.isUpperCase(Tipo_Vehiculo.charAt(0))) {
            JOptionPane.showMessageDialog(null, "El tipo de vehículo debe comenzar con mayúscula.");
            return false;
        }
        if (!Tipo_Vehiculo.matches("^[A-Za-z ]+$")) {
            JOptionPane.showMessageDialog(null, "El tipo de vehículo no debe contener números o símbolos.");
            return false;
        }
        return true;
    }

    public boolean validarPlaca(String Placa) {
        if (Placa.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo 'Placa' está vacío.");
            return false;
        }
        if (Placa.length() > 8) {
            JOptionPane.showMessageDialog(null, "La placa no debe superar los 8 caracteres.");
            return false;
        }
        if (Placa.contains(" ")) {
            JOptionPane.showMessageDialog(null, "La placa no debe contener espacios.");
            return false;
        }
        if (!Placa.matches("^[A-Z0-9]+$")) {
            JOptionPane.showMessageDialog(null, "La placa debe contener solo letras mayúsculas y números.");
            return false;
        }
        if (placaExiste(Placa)) {
            JOptionPane.showMessageDialog(null, "La placa ya está registrada.");
            return false;
        }
        return true;
    }

    private boolean placaExiste(String Placa) {
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existe = false;

        try {
            ConexionSQLServer conexionDB = new ConexionSQLServer();
            conexion = conexionDB.obtenerConexion();

            String sql = "SELECT COUNT(*) FROM Vehiculos WHERE Placa = ?";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, Placa);

            rs = stmt.executeQuery();
            if (rs.next()) {
                existe = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
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
        return existe;
    }

    public boolean validarModelo(String Modelo) {
        if (Modelo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo 'Modelo' está vacío.");
            return false;
        }
        if (Modelo.length() > 50) {
            JOptionPane.showMessageDialog(null, "El modelo no debe superar los 50 caracteres.");
            return false;
        }
        if (!Modelo.matches("^[A-Za-z0-9 ]+$")) {
            JOptionPane.showMessageDialog(null, "El modelo no debe contener símbolos.");
            return false;
        }
        return true;
    }

    public boolean validarMarca(String Marca) {
        if (Marca.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo 'Marca' está vacío.");
            return false;
        }
        if (Marca.length() > 20) {
            JOptionPane.showMessageDialog(null, "La marca no debe superar los 20 caracteres.");
            return false;
        }
        if (!Marca.matches("^[A-Za-z0-9 ]+$")) {
            JOptionPane.showMessageDialog(null, "La marca no debe contener símbolos.");
            return false;
        }
        return true;
    }

    public boolean validarPreferencias(String Preferencias) {
        if (Preferencias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo 'Observaciones' está vacío.");
            return false;
        }
        if (Preferencias.length() > 250) {
            JOptionPane.showMessageDialog(null, "Las observaciones no deben superar los 250 caracteres.");
            return false;
        }
        return true;
    }
}
