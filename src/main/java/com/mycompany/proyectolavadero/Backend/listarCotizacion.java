/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectolavadero.Backend;
//nada
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class listarCotizacion {
    private final Connection connection;

    public listarCotizacion(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<String[]> obtenerCotizacion() throws SQLException {
        ArrayList<String[]> cotizacion = new ArrayList<>();
        String sql = "SELECT cod_cotizacion, placa, tipo_servicio, Precio FROM Cotizaciones";

        try (PreparedStatement stmt = connection.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int cod_cotizacion = rs.getInt("cod_cotizacion"); 
                String placa = rs.getString("placa");
                String tipo_Servicio = rs.getString("tipo_Servicio");
                String Precio = rs.getString("Precio"); // ✅ Se agregó el punto y coma

                // Convertir cod_cotizacion a String antes de agregarlo a la lista
                cotizacion.add(new String[]{String.valueOf(cod_cotizacion), placa, tipo_Servicio, Precio});
            }
        }
        return cotizacion;
    }
    
}