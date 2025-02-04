/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectolavadero.Backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
/**
 *
 * @author Windows
 */
public class registroReporte {
    private final Connection connection;

    public registroReporte(Connection connection) {
        this.connection = connection;
    }

    public boolean validarFecha(String fecha) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(fecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public boolean guardarReporte(String fecha, String periodicidad, String tipoInforme, String detalle) throws Exception {
        if (!validarFecha(fecha)) {
            throw new Exception("La fecha es inválida. Debe tener el formato 'YYYY-MM-DD'.");
        }

        if (detalle.length() > 200) {
            throw new Exception("El detalle del reporte excede los 200 caracteres.");
        }

        ArrayList<String> datosAdicionales = obtenerDatosAdicionales(tipoInforme);
        byte[] pdfBytes = generarPDF(fecha, periodicidad, tipoInforme, detalle, datosAdicionales);

        String sql = "INSERT INTO Reportes (Fecha_Reporte, Periodicidad_Informe, Tipo_Informe, Detalle_Reporte, Pdf_Reporte) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(fecha));
            stmt.setString(2, periodicidad);
            stmt.setString(3, tipoInforme);
            stmt.setString(4, detalle);
            stmt.setBytes(5, pdfBytes);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al guardar el reporte en la base de datos: " + e.getMessage(), e);
        }

        return true;
    }

    private ArrayList<String> obtenerDatosAdicionales(String tipoInforme) throws SQLException {
        ArrayList<String> datos = new ArrayList<>();
        String sql;

        if (tipoInforme.equals("Clientes frecuentes")) {
            sql = "SELECT Nombre_Completo FROM cliente";
        } else if (tipoInforme.equals("Autos lavados")) {
            sql = "SELECT Placa, Marca FROM Vehiculos";
        } else {
            return datos;
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                if (tipoInforme.equals("Clientes frecuentes")) {
                    datos.add("Cliente: " + rs.getString("Nombre_Completo"));
                } else if (tipoInforme.equals("Autos lavados")) {
                    datos.add("Vehículo: " + rs.getString("Placa") + " - Marca: " + rs.getString("Marca"));
                }
            }
        }

        return datos;
    }

    private byte[] generarPDF(String fecha, String periodicidad, String tipoInforme, String detalle, ArrayList<String> datosAdicionales) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);

        document.open();
        document.add(new Paragraph("Reporte Generado"));
        document.add(new Paragraph("Fecha: " + fecha));
        document.add(new Paragraph("Periodicidad: " + periodicidad));
        document.add(new Paragraph("Tipo de Informe: " + tipoInforme));
        document.add(new Paragraph("Detalle: " + detalle));
        document.add(new Paragraph(" "));

        document.add(new Paragraph("Datos adicionales:"));
        for (String dato : datosAdicionales) {
            document.add(new Paragraph(dato));
        }

        document.close();
        return outputStream.toByteArray();
    }
}
