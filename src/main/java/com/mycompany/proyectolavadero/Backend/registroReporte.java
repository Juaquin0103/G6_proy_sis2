/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectolavadero.Backend;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.proyectolavadero.ConexionSQLServer;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Windows
 */
public class registroReporte {
    // Validación de la fecha
    public String validarFecha(String fecha) {
        if (fecha.isEmpty()) {
            return "Debes ingresar una fecha.";
        }
        if (!fecha.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return "La fecha debe tener el formato 'YYYY-MM-DD'.";
        }
        return "";
    }

    // Validación del detalle del reporte
    public String validarDetalleReporte(String detalle) {
        if (detalle.isEmpty()) {
            return "Debes ingresar un detalle para el reporte.";
        }
        if (detalle.length() > 200) {
            return "El detalle del reporte no puede superar los 200 caracteres.";
        }
        return "";
    }

    // Registro del reporte en la base de datos
    public boolean registrarReporte(String fecha, String periodicidad, String tipoInforme, String detalle) {
        Connection conexion = null;
        PreparedStatement stmt = null;
        boolean registrado = false;

        try {
            ConexionSQLServer conexionDB = new ConexionSQLServer();
            conexion = conexionDB.obtenerConexion();

            String sql = "INSERT INTO Reportes (Fecha_Reporte, Periodicidad_Informe, Tipo_Informe, Detalle_Reporte) VALUES (?, ?, ?, ?)";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, fecha);
            stmt.setString(2, periodicidad);
            stmt.setString(3, tipoInforme);
            stmt.setString(4, detalle);

            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                registrado = true;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return registrado;
    }

    // Generación del PDF con datos dinámicos
    public void generarPDFReporte(String fecha, String periodicidad, String tipoInforme, String detalle) throws DocumentException, IOException {
        Document documento = new Document();
        PdfWriter.getInstance(documento, new FileOutputStream("Reporte.pdf"));

        documento.open();
        documento.add(new Paragraph("Reporte generado"));
        documento.add(new Paragraph("Fecha: " + fecha));
        documento.add(new Paragraph("Periodicidad: " + periodicidad));
        documento.add(new Paragraph("Tipo de Informe: " + tipoInforme));
        documento.add(new Paragraph("Detalle: " + detalle));
        documento.add(new Paragraph("\n"));

        if (tipoInforme.equals("Clientes frecuentes")) {
            agregarClientesFrecuentes(documento, fecha);
        } else if (tipoInforme.equals("Autos lavados")) {
            agregarAutosLavados(documento, fecha);
        }

        documento.close();
    }

    private void agregarClientesFrecuentes(Document documento, String fecha) throws DocumentException {
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            ConexionSQLServer conexionDB = new ConexionSQLServer();
            conexion = conexionDB.obtenerConexion();

            String sql = "SELECT c.Nombre_Completo, COUNT(co.Fecha_Cotizacion) AS DiasAsistidos " +
                         "FROM cliente c " +
                         "JOIN Cotizaciones co ON c.Ci = co.Ci_cliente " +
                         "WHERE MONTH(co.Fecha_Cotizacion) = MONTH(?) AND YEAR(co.Fecha_Cotizacion) = YEAR(?) " +
                         "GROUP BY c.Nombre_Completo " +
                         "HAVING COUNT(co.Fecha_Cotizacion) > 3";

            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, fecha);
            stmt.setString(2, fecha);
            rs = stmt.executeQuery();

            documento.add(new Paragraph("Clientes frecuentes (asistieron más de 3 días en el mes):"));

            while (rs.next()) {
                String nombreCliente = rs.getString("Nombre_Completo");
                int diasAsistidos = rs.getInt("DiasAsistidos");
                documento.add(new Paragraph("- " + nombreCliente + " (Días asistidos: " + diasAsistidos + ")"));
            }

        } catch (SQLException e) {
            documento.add(new Paragraph("Error al obtener los clientes frecuentes: " + e.getMessage()));
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

    private void agregarAutosLavados(Document documento, String fecha) throws DocumentException {
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            ConexionSQLServer conexionDB = new ConexionSQLServer();
            conexion = conexionDB.obtenerConexion();

            String sql = "SELECT v.Placa, v.Modelo, v.Marca, COUNT(co.Fecha_Cotizacion) AS LavadosRealizados " +
                         "FROM Vehiculos v " +
                         "JOIN Cotizaciones co ON v.Placa = co.Placa " +
                         "WHERE MONTH(co.Fecha_Cotizacion) = MONTH(?) AND YEAR(co.Fecha_Cotizacion) = YEAR(?) " +
                         "GROUP BY v.Placa, v.Modelo, v.Marca";

            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, fecha);
            stmt.setString(2, fecha);
            rs = stmt.executeQuery();

            documento.add(new Paragraph("Vehículos lavados en el mes:"));

            while (rs.next()) {
                String placa = rs.getString("Placa");
                String modelo = rs.getString("Modelo");
                String marca = rs.getString("Marca");
                int lavadosRealizados = rs.getInt("LavadosRealizados");
                documento.add(new Paragraph("- Placa: " + placa + ", Modelo: " + modelo + ", Marca: " + marca + ", Lavados: " + lavadosRealizados));
            }

        } catch (SQLException e) {
            documento.add(new Paragraph("Error al obtener los vehículos lavados: " + e.getMessage()));
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
}
