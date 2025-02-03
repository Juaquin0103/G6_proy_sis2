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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author juaco
 */
public class nuevaCotizacion{
    public boolean nuevaCotizacion(int cod_cotizacion,String tipo_Servicio,String fecha_Cotizacion, String detalles_preferencias,String metodo_Pago, int Ci_cliente, String placa, String Precio){
        boolean cotizacionRegistrada = false;
         if (!validarServicio(tipo_Servicio)) {
            JOptionPane.showMessageDialog(null, "Error: Seleccione un servicio");
            return false;
        }
        
        if (!validarFecha(fecha_Cotizacion)) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese la Fecha en formato AAAA/MM/DD");
            return false;
        }

        
        if (!validarDetallesPreferencias(detalles_preferencias)) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese detalles/preferencias");
            return false;
        }

        if (!validarCiCliente(Ci_cliente)) {
            JOptionPane.showMessageDialog(null, "Error: El cliente no existe");
            return false;
        }

        if (!validarPlaca(placa)) {
            JOptionPane.showMessageDialog(null, "Error: El Vehiculo no existe");
            return false;
        }
        
        if (!validarPrecio(Precio)) {
            JOptionPane.showMessageDialog(null, "Error: Añada el precio del Servicio");
            return false;
        }
        
        Connection conexion = null;
        PreparedStatement stmt = null;
        
        try{
            ConexionSQLServer conexionDB = new ConexionSQLServer();
            conexion = conexionDB.obtenerConexion();

            String sql = "INSERT INTO Cotizaciones (cod_cotizacion, tipo_Servicio,fecha_Cotizacion,detalles_preferencias,metodo_Pago,Ci_cliente,placa,Precio) VALUES (?, ?, ?, ?, ?, ?,?)";
            stmt = conexion.prepareStatement(sql);
            stmt.setInt(1,cod_cotizacion);
            stmt.setString(2,tipo_Servicio);
            stmt.setString(3,fecha_Cotizacion);
            stmt.setString(4,detalles_preferencias);
            stmt.setString(5, metodo_Pago);
            stmt.setInt(6,Ci_cliente);
            stmt.setString(7,placa);
            
            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                cotizacionRegistrada = true;
                JOptionPane.showMessageDialog(null, "Cotizacion registrada exitosamente.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar cotizacion" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        
        return cotizacionRegistrada;
    
    }
    private boolean validarServicio(String tipo_Servicio) {
    // Verificar si es nulo o está vacío
    return tipo_Servicio != null && !tipo_Servicio.trim().isEmpty();
    }
    
    private boolean validarFecha(String fecha_Cotizacion) {
    // Verificar si la fecha es nula o está vacía
        if (fecha_Cotizacion == null || fecha_Cotizacion.trim().isEmpty()) {
        return false;
    }

    // Definir el formato esperado
    SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
    formato.setLenient(false); // Desactiva la tolerancia de fechas inválidas (ej. 2024/02/30)

    try {
        // Intentar parsear la fecha
        formato.parse(fecha_Cotizacion);
        return true;
    } catch (ParseException e) {
        return false; // La fecha no cumple con el formato
    }
    }
    private boolean validarDetallesPreferencias(String detalles_preferencias) {
    // Verificar si es nulo o está vacío después de eliminar espacios en blanco
    return detalles_preferencias != null && !detalles_preferencias.trim().isEmpty();
    }
    
    public boolean validarPrecio(String precio) {
    try {
        double valor = Double.parseDouble(precio);
        return valor > 0;
    } catch (NumberFormatException e) {
        return false; // Retorna false si el precio no es un número válido
    }
    }
    
    private boolean validarCiCliente(int Ci_cliente) {
    Connection conexion = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    try {
        ConexionSQLServer conexionDB = new ConexionSQLServer();
        conexion = conexionDB.obtenerConexion();

        // Verificar si el cliente existe en la base de datos
        String sql = "SELECT COUNT(*) FROM Cliente WHERE Ci = ?";
        stmt = conexion.prepareStatement(sql);
        stmt.setInt(1, Ci_cliente);
        
        rs = stmt.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0;  // Si el número de clientes con ese Ci es mayor que 0, es válido
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al validar el CI del cliente: " + e.getMessage());
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
    
    return false; // Si no se encuentra el cliente
}
    private boolean validarPlaca(String placa) {
    Connection conexion = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        ConexionSQLServer conexionDB = new ConexionSQLServer();
        conexion = conexionDB.obtenerConexion();

        // Verificar si el vehículo con esa placa existe en la base de datos
        String sql = "SELECT COUNT(*) FROM Vehiculo WHERE placa = ?";
        stmt = conexion.prepareStatement(sql);
        stmt.setString(1, placa);
        
        rs = stmt.executeQuery();
        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0;  // Si el número de vehículos con esa placa es mayor que 0, es válido
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al validar la placa del vehículo: " + e.getMessage());
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

    return false; // Si no se encuentra el vehículo
}
    public int obtenerUltimoCodigoCotizacion() {
    Connection conexion = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    int ultimoCodigo = 0;

    try {
        ConexionSQLServer conexionDB = new ConexionSQLServer();
        conexion = conexionDB.obtenerConexion();

        // Obtener el último código de cotización de la base de datos
        String sql = "SELECT MAX(cod_cotizacion) FROM Cotizaciones";
        stmt = conexion.prepareStatement(sql);

        rs = stmt.executeQuery();
        if (rs.next()) {
            ultimoCodigo = rs.getInt(1); // Obtener el valor máximo del código
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al obtener el último código de cotización: " + e.getMessage());
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

    return ultimoCodigo;
}

}


