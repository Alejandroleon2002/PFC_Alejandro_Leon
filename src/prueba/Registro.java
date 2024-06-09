/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prueba;

import database.UsuarioDAO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Registro extends javax.swing.JFrame {

    private UsuarioDAO usuarioDAO;
    
    /**
     * Creates new form Registrarse
     */
    public Registro() {
        initComponents();
        usuarioDAO = new UsuarioDAO();

    }
    
    private void registrarUsuario() {
        
        String nombreUsuario = txtNombreUsuario.getText();
        String correo = txtCorreo.getText();
        String contraseña = new String(txtContraseña.getPassword());

        if (nombreUsuario.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre de usuario y la contraseña son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (correo.isEmpty() || !correo.contains("@")) {
            JOptionPane.showMessageDialog(this, "El correo electrónico es obligatorio y debe contener el carácter '@'.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (contraseña.length() < 4) {
            JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 4 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (usuarioDAO.usuarioExistente(nombreUsuario)) {
            JOptionPane.showMessageDialog(this, "El nombre de usuario ya está en uso.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        usuarioDAO.guardarUsuario(nombreUsuario, correo, contraseña);
        JOptionPane.showMessageDialog(this, "Usuario registrado con éxito.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);

        txtNombreUsuario.setText("");
        txtCorreo.setText("");
        txtContraseña.setText("");
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtContraseña = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        passwordPanel = new javax.swing.JPanel();
        botonRegistro = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnpanelsalir = new javax.swing.JPanel();
        xSalir = new javax.swing.JLabel();
        inicioPanel = new javax.swing.JPanel();
        btnIniciarSesion = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(900, 650));
        setResizable(false);

        fondo.setForeground(new java.awt.Color(255, 255, 255));
        fondo.setPreferredSize(new java.awt.Dimension(900, 650));
        fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/AnimeCheckLogo.png"))); // NOI18N
        fondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, 340, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/animes2.jpg"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(350, 580));
        fondo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 340, 860));

        jLabel3.setFont(new java.awt.Font("Roboto Black", 0, 36)); // NOI18N
        jLabel3.setText("Registro");
        fondo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        jLabel4.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLabel4.setText("Correo");
        fondo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, -1, -1));

        jLabel5.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLabel5.setText("Contraseña");
        fondo.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, -1, -1));

        txtCorreo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });
        fondo.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 330, -1));

        txtContraseña.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fondo.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, 330, -1));
        fondo.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, 330, 10));
        fondo.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 330, 10));

        passwordPanel.setBackground(new java.awt.Color(218, 138, 67));
        passwordPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                passwordPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                passwordPanelMouseExited(evt);
            }
        });

        botonRegistro.setBackground(new java.awt.Color(255, 255, 255));
        botonRegistro.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        botonRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonRegistro.setText("Registrarse");
        botonRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonRegistroMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout passwordPanelLayout = new javax.swing.GroupLayout(passwordPanel);
        passwordPanel.setLayout(passwordPanelLayout);
        passwordPanelLayout.setHorizontalGroup(
            passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, passwordPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(botonRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        passwordPanelLayout.setVerticalGroup(
            passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, passwordPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(botonRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        fondo.add(passwordPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 500, 150, 40));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnpanelsalir.setBackground(new java.awt.Color(255, 255, 255));

        xSalir.setBackground(new java.awt.Color(255, 255, 255));
        xSalir.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        xSalir.setForeground(new java.awt.Color(0, 0, 0));
        xSalir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        xSalir.setText("X");
        xSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xSalirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                xSalirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                xSalirMouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnpanelsalirLayout = new javax.swing.GroupLayout(btnpanelsalir);
        btnpanelsalir.setLayout(btnpanelsalirLayout);
        btnpanelsalirLayout.setHorizontalGroup(
            btnpanelsalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnpanelsalirLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(xSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnpanelsalirLayout.setVerticalGroup(
            btnpanelsalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnpanelsalirLayout.createSequentialGroup()
                .addComponent(xSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnpanelsalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 870, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnpanelsalir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        fondo.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 30));

        inicioPanel.setBackground(new java.awt.Color(218, 138, 67));
        inicioPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                inicioPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                inicioPanelMouseExited(evt);
            }
        });

        btnIniciarSesion.setBackground(new java.awt.Color(255, 255, 255));
        btnIniciarSesion.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        btnIniciarSesion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnIniciarSesion.setText("Inicio de sesión");
        btnIniciarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIniciarSesionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout inicioPanelLayout = new javax.swing.GroupLayout(inicioPanel);
        inicioPanel.setLayout(inicioPanelLayout);
        inicioPanelLayout.setHorizontalGroup(
            inicioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
            .addGroup(inicioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(inicioPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        inicioPanelLayout.setVerticalGroup(
            inicioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
            .addGroup(inicioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(inicioPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        fondo.add(inicioPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 500, -1, -1));

        jLabel6.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLabel6.setText("Usuario");
        fondo.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, -1, -1));

        txtNombreUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreUsuarioActionPerformed(evt);
            }
        });
        fondo.add(txtNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 330, -1));
        fondo.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 450, 330, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, 652, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void xSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xSalirMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_xSalirMouseClicked

    private void xSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xSalirMouseEntered
        // TODO add your handling code here:
        
        xSalir.setForeground(Color.white);
        btnpanelsalir.setBackground(Color.red);
    }//GEN-LAST:event_xSalirMouseEntered

    private void xSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xSalirMouseExited
        // TODO add your handling code here:
        btnpanelsalir.setBackground(Color.white);
        xSalir.setForeground(Color.black);
    }//GEN-LAST:event_xSalirMouseExited

    private void passwordPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordPanelMouseEntered
        // TODO add your handling code here:
        passwordPanel.setBackground(new Color(255,203,145));
    }//GEN-LAST:event_passwordPanelMouseEntered

    private void inicioPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inicioPanelMouseEntered
        // TODO add your handling code here:
        inicioPanel.setBackground(new Color(255,203,145));
        
    }//GEN-LAST:event_inicioPanelMouseEntered

    private void passwordPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordPanelMouseExited
        // TODO add your handling code here:
        passwordPanel.setBackground(new Color(218,138,67));
    }//GEN-LAST:event_passwordPanelMouseExited

    private void inicioPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inicioPanelMouseExited
        // TODO add your handling code here:
        inicioPanel.setBackground(new Color(218,138,67));
    }//GEN-LAST:event_inicioPanelMouseExited

    private void btnIniciarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarSesionMouseClicked
        // TODO add your handling code here:
        InicioDeSesion is = new InicioDeSesion();
        is.setVisible(true);
        this.dispose();
       
    }//GEN-LAST:event_btnIniciarSesionMouseClicked

    private void botonRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegistroMouseClicked
        // TODO add your handling code here:
        registrarUsuario();
       
    }//GEN-LAST:event_botonRegistroMouseClicked

    private void txtNombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreUsuarioActionPerformed

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
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel botonRegistro;
    private javax.swing.JLabel btnIniciarSesion;
    private javax.swing.JPanel btnpanelsalir;
    private javax.swing.JPanel fondo;
    private javax.swing.JPanel inicioPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPanel passwordPanel;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JLabel xSalir;
    // End of variables declaration//GEN-END:variables
}
