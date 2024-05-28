/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prueba;

import database.AnimeDAO;
import database.CapituloDAO;
import database.CapituloTableModel;
import database.MeGustaDAO;
import database.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import model.Anime;
import model.Capitulo;

/**
 *
 * @author Usuario
 * 
 * 
 */


public class AñadirCap extends javax.swing.JFrame {

    private UsuarioDAO usuarioDAO;
    private AnimeDAO animeDAO;
    private CapituloDAO capituloDAO;
    private MeGustaDAO meGustaDAO;
    private int idUsuario;
    private int codAnime;
    private List<Anime> animes;
    
    /**
     * Creates new form AñadirCap
     */
    public AñadirCap() {
        initComponents();
        usuarioDAO = new UsuarioDAO();
        animeDAO = new AnimeDAO();
        capituloDAO = new CapituloDAO();
        meGustaDAO = new MeGustaDAO();
        actualizarTable();
        cargarComboAnimes();
        
        AnimeCombox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obtener el ID del anime seleccionado
                int animeId = obtenerIdAnimeSeleccionado();
                // Filtrar y actualizar la tabla de capítulos por el anime seleccionado
                filtrarCapitulosPorAnime(animeId);
            }
        });
    }
    
    public AñadirCap(int  idUsuario) {
        initComponents();
        usuarioDAO = new UsuarioDAO();
        animeDAO = new AnimeDAO();
        capituloDAO = new CapituloDAO();
        meGustaDAO = new MeGustaDAO();
        actualizarTable();
        cargarComboAnimes();
    }
    
    
    private void filtrarCapitulosPorAnime(int animeId) {
        if (animeId != -1) {
            // Obtener los capítulos por el ID del anime
            List<Capitulo> capitulos = capituloDAO.listarCapitulosPorAnime(animeId);
            // Actualizar la tabla con los nuevos capítulos
            CapituloTableModel model = new CapituloTableModel();
            model.setCapitulos(capitulos);
            table1.setModel(model);
            // Ocultar la columnas
        table1.getColumnModel().getColumn(0).setMinWidth(0);
        table1.getColumnModel().getColumn(0).setMaxWidth(0);
        table1.getColumnModel().getColumn(0).setWidth(0);
        
        table1.getColumnModel().getColumn(1).setMinWidth(0);
        table1.getColumnModel().getColumn(1).setMaxWidth(0);
        table1.getColumnModel().getColumn(1).setWidth(0);
        }
    }
    
    
    public void actualizarTable() {
        CapituloTableModel model = new CapituloTableModel();
        List<Capitulo> capitulos = capituloDAO.listarTodosLosCapitulos();
        model.setCapitulos(capitulos);
        table1.setModel(model);


        // Ocultar la columnas
        table1.getColumnModel().getColumn(0).setMinWidth(0);
        table1.getColumnModel().getColumn(0).setMaxWidth(0);
        table1.getColumnModel().getColumn(0).setWidth(0);
        
        table1.getColumnModel().getColumn(1).setMinWidth(0);
        table1.getColumnModel().getColumn(1).setMaxWidth(0);
        table1.getColumnModel().getColumn(1).setWidth(0);
        
        
    }
   public void cargarComboAnimes() {
        try {
            List<Anime> animes = animeDAO.obtenerAnimes();

            AnimeCombox.removeAllItems();
            AnimeCombox.addItem("Elija uno");

            for (Anime anime : animes) {
                AnimeCombox.addItem(anime.getNombre());
            }

            // Guardar la lista de animes para usar sus IDs más tarde
            this.animes = animes;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
      private int obtenerIdAnimeSeleccionado() {
        String nombreSeleccionado = (String) AnimeCombox.getSelectedItem();
        if (nombreSeleccionado == null || nombreSeleccionado.equals("Elija uno")) {
            return -1; // Valor para indicar que no se ha seleccionado un anime válido
        }
        for (Anime anime : animes) {
            if (anime.getNombre().equals(nombreSeleccionado)) {
                return anime.getIdAnime();
            }
        }
        return -1; // Si no se encuentra el anime (no debería pasar)
    }


    
        private void añadirCapitulo() {
        try {
            int animeId = obtenerIdAnimeSeleccionado();
            if (animeId == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un anime.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int numeroCapi = Integer.parseInt(numeroCapitulo.getText());
            String titulo = tituloCap.getText();
            int duracion = Integer.parseInt(duracionCap.getText());

            Capitulo capitulo = new Capitulo();
            capitulo.setAnimeId(animeId);
            capitulo.setNumeroCapitulo(numeroCapi);
            capitulo.setTitulo(titulo);
            capitulo.setDuracion(duracion);

            capituloDAO.insertarCapitulo(capitulo);
            JOptionPane.showMessageDialog(this, "Capítulo añadido exitosamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al añadir capítulo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        AnimeCombox = new javax.swing.JComboBox<>();
        numeroCapitulo = new javax.swing.JTextField();
        tituloCap = new javax.swing.JTextField();
        duracionCap = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table1);

        jLabel2.setText("Anime:");

        jLabel4.setText("Nº de capítulo:");

        jLabel5.setText("Duración:");

        jLabel8.setText("Titulo:");

        AnimeCombox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton3.setText("Añadir");

        jButton1.setText("Reiniciar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(jButton3)
                .addGap(46, 46, 46)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(136, 136, 136)
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
                        .addComponent(tituloCap, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(136, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 253, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addGap(113, 113, 113))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(230, 230, 230)
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
                    .addContainerGap(231, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(AñadirCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AñadirCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AñadirCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AñadirCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AñadirCap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> AnimeCombox;
    private javax.swing.JTextField duracionCap;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField numeroCapitulo;
    private javax.swing.JTable table1;
    private javax.swing.JTextField tituloCap;
    // End of variables declaration//GEN-END:variables

    
}
