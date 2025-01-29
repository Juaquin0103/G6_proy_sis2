/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectolavadero;

import com.mycompany.proyectolavadero.Interfaces.listaDePropietariosInterfaz;
import com.mycompany.proyectolavadero.Interfaces.registroPropietarioInterfaz;

/**
 *
 * @author juaco
 */
public class ProyectoLavadero {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                registroPropietarioInterfaz ventana = new registroPropietarioInterfaz();
                ventana.setVisible(true); // Mostrar la ventana
                ventana.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
                /*
                listaDePropietariosInterfaz ventanaLista = new listaDePropietariosInterfaz();
                ventanaLista.setVisible(true);
                ventanaLista.setLocationRelativeTo(null);  // Centrar la ventana*/
            }
        });
    }
}
