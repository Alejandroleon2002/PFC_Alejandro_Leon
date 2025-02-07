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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import model.Anime;
import model.Capitulo;

/**
 *
 * @author Usuario
 */
public class ModificarCap extends javax.swing.JFrame {

    
    private UsuarioDAO usuarioDAO;
    private AnimeDAO animeDAO;
    private CapituloDAO capituloDAO;
    private CategoriaDAO categoriaDAO;
    private GeneroDAO generoDAO;
    private int idUsuario;
    private int idCapitulo;
    private List<Anime> animes;
    private int codAnime;
    private Animes animesInstance;
    private AñadirCap añadirCapInstance;

    /**
     * Creates new form ModificarCap
     */
    public ModificarCap() {
        initComponents();
        usuarioDAO = new UsuarioDAO();
        animeDAO = new AnimeDAO();
        capituloDAO = new CapituloDAO();
        categoriaDAO = new CategoriaDAO();
        generoDAO = new GeneroDAO();
        animes = new ArrayList<>();
        cargarComboAnimes();
    }

    public ModificarCap(int idUsuario, int idCapitulo, int codAnime, Animes animesInstance, AñadirCap añadirCapInstance){
        initComponents();
        this.idUsuario = idUsuario;
        this.idCapitulo = idCapitulo;
        this.codAnime = codAnime;
        this.animesInstance = animesInstance;
        this.añadirCapInstance = añadirCapInstance;
        usuarioDAO = new UsuarioDAO();
        animeDAO = new AnimeDAO();
        capituloDAO = new CapituloDAO();
        categoriaDAO = new CategoriaDAO();
        generoDAO = new GeneroDAO();
        animes = new ArrayList<>();
        cargarComboAnimes();
        cargarDatosCapitulo(idCapitulo);
    }

   
    public void cargarComboAnimes() {
        AnimeCombox.removeAllItems();

        AnimeCombox.addItem("Elija uno");

        animes = animeDAO.obtenerAnimes();
        for (Anime anime : animes) {
            AnimeCombox.addItem(anime.getNombre());
        }
    }

    private int obtenerAnimeIdSeleccionado(JComboBox<String> comboBox) {
        String selectedAnimeName = (String) comboBox.getSelectedItem();
        if (selectedAnimeName != null && !selectedAnimeName.equals("Elija uno")) {
            for (Anime anime : animes) {
                if (anime.getNombre().equals(selectedAnimeName)) {
                    return anime.getIdAnime();
                }
            }
        }
        return -1;
    }

    public void cargarDatosCapitulo(int idCapitulo) {
        Capitulo capitulo = capituloDAO.obtenerCapituloPorId(idCapitulo);

        if (capitulo != null) {

            String nombreAnimeCapitulo = capitulo.getAnime().getNombre();

            for (Anime anime : animes) {
                if (anime.getNombre().equals(nombreAnimeCapitulo)) {

                    AnimeCombox.setSelectedItem(anime.getNombre());
                    break;
                }
            }

            numeroCapitulo.setText(String.valueOf(capitulo.getNumeroCapitulo()));
            tituloCap.setText(capitulo.getTitulo());
            duracionCap.setText(String.valueOf(capitulo.getDuracion()));
        } else {

            JOptionPane.showMessageDialog(this, "El capítulo no se encontró.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
      private void actualizarDatosCapitulo(int idCapitulo) {
          
        String nombreCapitulo = tituloCap.getText();
        int numeroCap = Integer.parseInt(numeroCapitulo.getText());
        int duracion = Integer.parseInt(duracionCap.getText());
        int nuevoAnimeId = obtenerAnimeIdSeleccionado(AnimeCombox);

        Capitulo capituloActualizado = new Capitulo(idCapitulo, nuevoAnimeId, nombreCapitulo, numeroCap, duracion);

        boolean actualizacionExitosa = capituloDAO.actualizarCapitulo(capituloActualizado);

        if (actualizacionExitosa) {
            JOptionPane.showMessageDialog(this, "Datos del capítulo actualizados correctamente.");
            if (animesInstance != null) {
                animesInstance.actualizarTable();
            }
            if (añadirCapInstance != null && añadirCapInstance.isVisible()) {
                añadirCapInstance.actualizarTable();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar los datos del capítulo.", "Error", JOptionPane.ERROR_MESSAGE);
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

        numeroCapitulo = new javax.swing.JTextField();
        tituloCap = new javax.swing.JTextField();
        duracionCap = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        AnimeCombox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        numeroCapitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroCapituloActionPerformed(evt);
            }
        });

        jButton1.setText("Modificar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Modificar Capítulo");

        jLabel2.setText("Anime:");

        jLabel4.setText("Nº de capítulo:");

        jLabel5.setText("Duración:");

        jLabel8.setText("Titulo:");

        AnimeCombox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(numeroCapitulo)
                            .addComponent(AnimeCombox, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(93, 93, 93)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(duracionCap, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tituloCap, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(405, 405, 405)
                        .addComponent(jButton1)))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(AnimeCombox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tituloCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(duracionCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(numeroCapitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(100, 100, 100)
                .addComponent(jButton1)
                .addContainerGap(93, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       actualizarDatosCapitulo(idCapitulo);
       this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void numeroCapituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroCapituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeroCapituloActionPerformed

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
            java.util.logging.Logger.getLogger(ModificarCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificarCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificarCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificarCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModificarCap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> AnimeCombox;
    private javax.swing.JTextField duracionCap;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField numeroCapitulo;
    private javax.swing.JTextField tituloCap;
    // End of variables declaration//GEN-END:variables
}
