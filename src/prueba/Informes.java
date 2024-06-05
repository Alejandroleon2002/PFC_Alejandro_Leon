/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prueba;

import database.AnimeDAO;
import database.CapituloDAO;
import database.CategoriaDAO;
import database.GeneroDAO;
import database.MeGustaDAO;
import database.UsuarioDAO;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Usuario
 */
public class Informes extends javax.swing.JFrame {
    
    private int idUsuario;
    private UsuarioDAO usuarioDAO;
    private AnimeDAO animeDAO;
    private CategoriaDAO categoriaDAO;
    private GeneroDAO generoDAO;
    private CapituloDAO capituloDAO;
    private MeGustaDAO meGustaDAO;
   

    /**
     * Creates new form Informes
     */
    public Informes() {
        initComponents();
        
         usuarioDAO = new UsuarioDAO();
        animeDAO = new AnimeDAO();
        categoriaDAO = new CategoriaDAO();
        generoDAO = new GeneroDAO();
        capituloDAO = new CapituloDAO();
        meGustaDAO = new MeGustaDAO();
        combox();
    }
    
    public void combox() {
        try {
            
            ResultSet result3 = categoriaDAO.obtenerNombresCategorias();
            ResultSet result4 = generoDAO.obtenerNombresGeneros();

            

            comboCategoria.removeAllItems();
            comboCategoria.addItem("Elija uno");

            comboGenero.removeAllItems();
            comboGenero.addItem("Elija uno");

            

            while (result3.next()) {
                comboCategoria.addItem(result3.getString(1));
            }

            while (result4.next()) {
                comboGenero.addItem(result4.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        comboCategoria = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        comboGenero = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton2.setText("Informe 1");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Categoria:");

        comboCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Genero:");

        comboGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton3.setText("Informe 2");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Informes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(comboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(69, 69, 69))
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addGap(42, 42, 42))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
          Connection conexion = null;
    try {
        Class.forName("org.hsqldb.jdbcDriver");
        conexion = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost", "SA", "SA");
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Informes.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(Informes.class.getName()).log(Level.SEVERE, null, ex);
    } 

    // Load JRXML file
    File archivoJRXML = new File("C:/Users/Usuario/Documents/NetBeansProjects/PFC_Alejandro_Leon/src/informes/Informe2.jrxml");
    JasperReport jr = null;
    try {
        jr = JasperCompileManager.compileReport(archivoJRXML.getAbsolutePath());
        
        // Create map for parameters
        Map<String, Object> map = new HashMap<>();
        String ruta_imagen = "informes/AnimeCheck.png";
        map.put("imagen1", ruta_imagen); 
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(jr, map, conexion);
        JasperViewer visor = new JasperViewer(jasperPrint,false) ;
        visor.setVisible(true);
    } catch (JRException ex) {
        Logger.getLogger(Informes.class.getName()).log(Level.SEVERE, null, ex);
    }

                
        /*
         Map<String, Object> map = new HashMap<>();
        
        String ruta_imagen = "informes/AnimeCheck.png";
           
        map.put("imagen1", ruta_imagen); 
        
  
        //InputStream vinculoarchivo = getClass().getResourceAsStream("informes/Informe2.jrxml");

        InputStream vinculoarchivo = getClass().getResourceAsStream("C:/Users/Usuario/Documents/NetBeansProjects/PFC_Alejandro_Leon/src/informes/Informe2.jrxml");



       
        JasperReport jr = null;
        try {
            jr = JasperCompileManager.compileReport(vinculoarchivo);
            Connection conexion = Conexion.obtenerConexion();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jr, null, conexion);
            JasperViewer visor = new JasperViewer(jasperPrint,false) ;
            visor.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Informes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.cerrarConexion();
        }              
            
            */    
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Informes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Informes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Informes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Informes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Informes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboCategoria;
    private javax.swing.JComboBox<String> comboGenero;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
