/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyectolavadero.Interfaces;

import com.mycompany.proyectolavadero.Backend.registroEmpleado;
import javax.swing.JOptionPane;

/**
 *
 * @author Windows
 */
public class registroEmpleadoInterfaz extends javax.swing.JFrame {

    /**
     * Creates new form registroEmpleadoInterfaz
     */
    public registroEmpleadoInterfaz() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();
        BarraLateral = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        BarraSuperior = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        BarraCentral = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Background.setBackground(new java.awt.Color(255, 255, 255));

        BarraLateral.setBackground(new java.awt.Color(17, 17, 29));

        jButton1.setBackground(new java.awt.Color(17, 17, 29));
        jButton1.setBorder(null);

        jButton2.setBackground(new java.awt.Color(17, 17, 29));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Inicio");

        jButton3.setBackground(new java.awt.Color(17, 17, 29));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Cotizacion");

        jButton4.setBackground(new java.awt.Color(17, 17, 29));
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Vehiculos");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(17, 17, 29));
        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Clientes");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(17, 17, 29));
        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Equipo de lavado");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(17, 17, 29));
        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Inventario");

        jButton8.setBackground(new java.awt.Color(17, 17, 29));
        jButton8.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Reportes");

        jButton9.setBackground(new java.awt.Color(17, 17, 29));
        jButton9.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        //jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/proyectolavadero/Iconos/IconosListaDeVehiculos/catalogar.png"))); // NOI18N
        jButton9.setText("Catalogo");

        javax.swing.GroupLayout BarraLateralLayout = new javax.swing.GroupLayout(BarraLateral);
        BarraLateral.setLayout(BarraLateralLayout);
        BarraLateralLayout.setHorizontalGroup(
            BarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarraLateralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BarraLateralLayout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(BarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton9)
                    .addComponent(jButton8)
                    .addComponent(jButton7)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addGap(56, 56, 56))
        );
        BarraLateralLayout.setVerticalGroup(
            BarraLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarraLateralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(182, 182, 182)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addComponent(jButton8)
                .addGap(18, 18, 18)
                .addComponent(jButton9)
                .addContainerGap(205, Short.MAX_VALUE))
        );

        BarraSuperior.setBackground(new java.awt.Color(236, 240, 241));

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jTextField1.setText("Buscar");

        jButton10.setBackground(new java.awt.Color(236, 240, 241));
        jButton10.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jButton10.setText("Soporte");

        jButton11.setBackground(new java.awt.Color(236, 240, 241));
        jButton11.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N

        javax.swing.GroupLayout BarraSuperiorLayout = new javax.swing.GroupLayout(BarraSuperior);
        BarraSuperior.setLayout(BarraSuperiorLayout);
        BarraSuperiorLayout.setHorizontalGroup(
            BarraSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarraSuperiorLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(BarraSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BarraSuperiorLayout.createSequentialGroup()
                        .addComponent(jButton11)
                        .addGap(54, 54, 54))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BarraSuperiorLayout.createSequentialGroup()
                        .addComponent(jButton10)
                        .addGap(193, 193, 193))))
        );
        BarraSuperiorLayout.setVerticalGroup(
            BarraSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarraSuperiorLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jButton11)
                .addContainerGap(87, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BarraSuperiorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(BarraSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10))
                .addGap(37, 37, 37))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 40)); // NOI18N
        jLabel1.setText("Nuevo trabajador");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel2.setText("Nombres:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel3.setText("Apellidos:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel4.setText("Carnet de Identidad:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel5.setText("Telefono:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel6.setText("Direccion:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel7.setText("Correo:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel8.setText("Equipo de lavado:");

        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton12.setText("Cancelar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton13.setText("Guardar trabajador");
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton13MouseClicked(evt);
            }
        });
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "E1", "E2", "E3" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BarraCentralLayout = new javax.swing.GroupLayout(BarraCentral);
        BarraCentral.setLayout(BarraCentralLayout);
        BarraCentralLayout.setHorizontalGroup(
            BarraCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarraCentralLayout.createSequentialGroup()
                .addGroup(BarraCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BarraCentralLayout.createSequentialGroup()
                        .addGap(337, 337, 337)
                        .addComponent(jLabel1))
                    .addGroup(BarraCentralLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(BarraCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BarraCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(BarraCentralLayout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(BarraCentralLayout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextField2))
                                .addGroup(BarraCentralLayout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(BarraCentralLayout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(BarraCentralLayout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextField6))
                                .addGroup(BarraCentralLayout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(BarraCentralLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(BarraCentralLayout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(jButton12)
                        .addGap(213, 213, 213)
                        .addComponent(jButton13)))
                .addContainerGap(145, Short.MAX_VALUE))
        );
        BarraCentralLayout.setVerticalGroup(
            BarraCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarraCentralLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(BarraCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(BarraCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField3))
                .addGap(18, 18, 18)
                .addGroup(BarraCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField4))
                .addGap(18, 18, 18)
                .addGroup(BarraCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField5))
                .addGap(18, 18, 18)
                .addGroup(BarraCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField6))
                .addGap(18, 18, 18)
                .addGroup(BarraCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField7))
                .addGap(18, 18, 18)
                .addGroup(BarraCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1))
                .addGap(105, 105, 105)
                .addGroup(BarraCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12)
                    .addComponent(jButton13))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(BarraLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BarraSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackgroundLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addComponent(BarraCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80))))
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(BarraSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BarraCentral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(BarraLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        // Obtener los valores de los campos
        String nombre = jTextField2.getText().trim();
        String apellido = jTextField3.getText().trim();
        String ci = jTextField4.getText().trim();
        String telefono = jTextField5.getText().trim();
        String direccion = jTextField6.getText().trim();
        String correo = jTextField7.getText().trim();
        String equipoLavado = jComboBox1.getSelectedItem().toString();

        // Validar si hay campos vacíos
        if (nombre.isEmpty() || apellido.isEmpty() || ci.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error: Todos los campos deben ser llenados.");
            return;
        }

        // Instanciar la clase de registro y registrar los datos
        registroEmpleado registro = new registroEmpleado();
        boolean resultado = registro.registrarEmpleado(nombre, apellido, ci, telefono, direccion, correo, equipoLavado);

        // Si la operación fue exitosa, limpiar los campos
        if (resultado) {
            JOptionPane.showMessageDialog(null, "Datos guardados correctamente.");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            jTextField7.setText("");
            jComboBox1.setSelectedIndex(0);
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseClicked
        // TODO add your handling code here:
        String nombre = jTextField2.getText().trim();
        String apellido = jTextField3.getText().trim();
        String ci = jTextField4.getText().trim();
        String telefono = jTextField5.getText().trim();
        String direccion = jTextField7.getText().trim();  // Direccion debe ir antes de correo
        String correo = jTextField6.getText().trim();
        String equipoLavado = jComboBox1.getSelectedItem().toString(); 

        registroEmpleado registro = new registroEmpleado();

        boolean resultado = registro.registrarEmpleado(nombre, apellido, ci, telefono, direccion, correo, equipoLavado);

        if (resultado) {
            // Limpiar los campos si la operación fue exitosa
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            jTextField7.setText(""); // Aseguramos también limpiar la dirección
            jComboBox1.setSelectedIndex(0);  // Devolver el JComboBox al primer índice
        }
    }//GEN-LAST:event_jButton13MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        listaDeVehiculosInterfaz ventanaListaVehiculos = new listaDeVehiculosInterfaz();
        ventanaListaVehiculos.setVisible(true);
        ventanaListaVehiculos.setLocationRelativeTo(null);  // Centrar la ventana en la pantalla

        // Cerrar la ventana actual
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        listaDePropietariosInterfaz ventanaListaPropietarios = new listaDePropietariosInterfaz();
        ventanaListaPropietarios.setVisible(true);
        ventanaListaPropietarios.setLocationRelativeTo(null);  // Centrar la ventana en la pantalla

        // Cerrar la ventana actual
        this.dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        asignarEquipoLavado ventanaAsignarEquipoLavado = new asignarEquipoLavado();
        ventanaAsignarEquipoLavado.setVisible(true);
        ventanaAsignarEquipoLavado.setLocationRelativeTo(null);  // Centrar la ventana en la pantalla

        // Cerrar la ventana actual
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        asignarEquipoLavado ventanaAsignarEquipoLavado = new asignarEquipoLavado();
        ventanaAsignarEquipoLavado.setVisible(true);
        ventanaAsignarEquipoLavado.setLocationRelativeTo(null);  // Centrar la ventana en la pantalla

        // Cerrar la ventana actual
        this.dispose();
    }//GEN-LAST:event_jButton12ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(registroEmpleadoInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registroEmpleadoInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registroEmpleadoInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registroEmpleadoInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registroEmpleadoInterfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JPanel BarraCentral;
    private javax.swing.JPanel BarraLateral;
    private javax.swing.JPanel BarraSuperior;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
