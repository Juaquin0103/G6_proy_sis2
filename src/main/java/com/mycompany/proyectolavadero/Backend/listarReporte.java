/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectolavadero.Backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Windows
 */
public class listarReporte {
    private final Connection connection;

    public listarReporte(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<String[]> obtenerReportes() throws SQLException {
        ArrayList<String[]> reportes = new ArrayList<>();
        String sql = "SELECT Fecha_Reporte, Periodicidad_Informe, Tipo_Informe FROM Reportes";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String fecha = rs.getString("Fecha_Reporte");
                String periodicidad = rs.getString("Periodicidad_Informe");
                String tipoInforme = rs.getString("Tipo_Informe");
                reportes.add(new String[]{fecha, periodicidad, tipoInforme});
            }
        }

        return reportes;
    }

    public byte[] obtenerPdfReporte(String fecha) throws SQLException {
        String sql = "SELECT Pdf_Reporte FROM Reportes WHERE Fecha_Reporte = ?";
        byte[] pdfData = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, fecha);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pdfData = rs.getBytes("Pdf_Reporte");
                }
            }
        }

        return pdfData;
    }
}
