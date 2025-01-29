/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectolavadero;

import com.mycompany.proyectolavadero.Interfaces.registroPropietarioInterfaz;

/**
 *
 * @author juaco
 */
public class ProyectoLavadero {

    public static void main(String[] args) {
       ConexionSQLServer conexion = new ConexionSQLServer();
        conexion.obtenerConexion();
    }
}
