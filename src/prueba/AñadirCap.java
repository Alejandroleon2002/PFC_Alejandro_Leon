/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prueba;

import database.AnimeDAO;
import database.CapituloDAO;
import database.CapituloTableModel;
import database.CategoriaDAO;
import database.GeneroDAO;
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
    private CategoriaDAO categoriaDAO;
    private GeneroDAO generoDAO;
    private int idUsuario;
    private int codAnime;
    private List<Anime> animes;
    private Admin adminFrame;
    private Animes animesFrame;
    private AñadirCap añadirCapInstance;
    
    /**
     * Creates new form AñadirCap
     */
    public AñadirCap() {
        initComponents();
        usuarioDAO = new UsuarioDAO();
        animeDAO = new AnimeDAO();
        capituloDAO = new CapituloDAO();
        meGustaDAO = new MeGustaDAO();
        categoriaDAO = new CategoriaDAO();
        generoDAO = new GeneroDAO();
        actualizarTable();
        cargarComboAnimes();
        
        
        AnimeCombox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int animeId = obtenerIdAnimeSeleccionado();
                filtrarCapitulosPorAnime(animeId);
            }
        });
    }
        public AñadirCap(int idUsuario, int codAnime, Animes animesFrame) {
        initComponents();
        this.animesFrame = animesFrame;
        this.idUsuario = idUsuario;
        this.codAnime = codAnime;
        this.añadirCapInstance = añadirCapInstance;
        usuarioDAO = new UsuarioDAO();
        animeDAO = new AnimeDAO();
        categoriaDAO = new CategoriaDAO();
        generoDAO = new GeneroDAO();
        capituloDAO = new CapituloDAO();
        meGustaDAO = new MeGustaDAO();

        cargarComboAnimes();
        actualizarTable();
        
        AnimeCombox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int animeId = obtenerIdAnimeSeleccionado();
                filtrarCapitulosPorAnime(animeId);
            }
        });
        
    }

    public AñadirCap(int idUsuario, int codAnime, Admin adminFrame) {
        initComponents();
        this.adminFrame = adminFrame;
        this.idUsuario = idUsuario;
        this.codAnime = codAnime;
        
        usuarioDAO = new UsuarioDAO();
        animeDAO = new AnimeDAO();
        categoriaDAO = new CategoriaDAO();
        generoDAO = new GeneroDAO();
        capituloDAO = new CapituloDAO();
        meGustaDAO = new MeGustaDAO();

        cargarComboAnimes();
        actualizarTable();
        
        AnimeCombox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int animeId = obtenerIdAnimeSeleccionado();
                filtrarCapitulosPorAnime(animeId);
            }
        });
    }
    
    
    
    public int obtenerIdUsuario() {
        return idUsuario;
    }
    
    
    
    
    
    public void actualizarTable() {
        CapituloTableModel model = new CapituloTableModel();
        List<Capitulo> capitulos = capituloDAO.listarTodosLosCapitulos();
        model.setCapitulos(capitulos);
        table1.setModel(model);

        table1.getColumnModel().getColumn(0).setMinWidth(0);
        table1.getColumnModel().getColumn(0).setMaxWidth(0);
        table1.getColumnModel().getColumn(0).setWidth(0);
        
        table1.getColumnModel().getColumn(1).setMinWidth(0);
        table1.getColumnModel().getColumn(1).setMaxWidth(0);
        table1.getColumnModel().getColumn(1).setWidth(0);
        
        
    }
    
    
    
   public void cargarComboAnimes() {
       List<Anime> animes = animeDAO.obtenerAnimes();
       AnimeCombox.removeAllItems();
       AnimeCombox.addItem("Elija uno");
       for (Anime anime : animes) {
           AnimeCombox.addItem(anime.getNombre());
       }

       this.animes = animes;
    }
        
   
   
   
      private int obtenerIdAnimeSeleccionado() {
        String nombreSeleccionado = (String) AnimeCombox.getSelectedItem();
        if (nombreSeleccionado == null || nombreSeleccionado.equals("Elija uno")) {
            return -1;
        }
        for (Anime anime : animes) {
            if (anime.getNombre().equals(nombreSeleccionado)) {
                return anime.getIdAnime();
            }
        }
        return -1;
    }

      
      private void filtrarCapitulosPorAnime(int animeId) {
        if (animeId != -1) {
           
            List<Capitulo> capitulos = capituloDAO.listarCapitulosPorAnime(animeId);
           
            CapituloTableModel model = new CapituloTableModel();
            model.setCapitulos(capitulos);
            table1.setModel(model);
            
        table1.getColumnModel().getColumn(0).setMinWidth(0);
        table1.getColumnModel().getColumn(0).setMaxWidth(0);
        table1.getColumnModel().getColumn(0).setWidth(0);
        
        table1.getColumnModel().getColumn(1).setMinWidth(0);
        table1.getColumnModel().getColumn(1).setMaxWidth(0);
        table1.getColumnModel().getColumn(1).setWidth(0);
        }
    }
    
        private void añadirCapitulo() {
    try {
        int animeId = obtenerIdAnimeSeleccionado();
        if (animeId == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un anime.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String numeroCapiStr = numeroCapitulo.getText();
        String titulo = tituloCap.getText();
        String duracionStr = duracionCap.getText();

        if (numeroCapiStr.isEmpty() || titulo.isEmpty() || duracionStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int numeroCapi;
        try {
            numeroCapi = Integer.parseInt(numeroCapiStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El número de capítulo debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int duracion;
        try {
            duracion = Integer.parseInt(duracionStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La duración debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Capitulo capitulo = new Capitulo();
        capitulo.setAnimeId(animeId);
        capitulo.setNumeroCapitulo(numeroCapi);
        capitulo.setTitulo(titulo);
        capitulo.setDuracion(duracion);

        capituloDAO.insertarCapitulo(capitulo);
        JOptionPane.showMessageDialog(this, "Capítulo añadido exitosamente.");
        actualizarTable();
        
        numeroCapitulo.setText("");
        duracionCap.setText("");
        tituloCap.setText("");
        AnimeCombox.setSelectedIndex(0);
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
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table1);

        jLabel2.setText("Anime:");

        jLabel4.setText("Nº de capítulo:");

        jLabel5.setText("Duración:");

        jLabel8.setText("Titulo:");

        AnimeCombox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton3.setText("Añadir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText("Reiniciar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        añadirCapitulo();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        numeroCapitulo.setText("");
        duracionCap.setText("");
        tituloCap.setText("");
        AnimeCombox.setSelectedIndex(0);
        
        actualizarTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        // TODO add your handling code here:
         int fila = table1.getSelectedRow();
        

        if (table1.getSelectedColumn()==5){
            int codiCap = (int) table1.getValueAt(fila, 0);
            System.out.println(codiCap); 
            int codiAnime = (int) table1.getValueAt(fila, 1);
            System.out.println(codAnime); 
            
            int idUsuario = obtenerIdUsuario();
            System.out.println(idUsuario); 
        
            Capitulos C = new Capitulos(idUsuario, codiCap , codiAnime);
            C.setVisible(true);
        }
                if (table1.getSelectedColumn() == 6) { 
            int idCapitulo = (int) table1.getValueAt(fila, 0);
            System.out.println(idCapitulo); 

            ModificarCap mc = new ModificarCap(idUsuario, idCapitulo, codAnime, animesFrame,this);
            mc.setVisible(true);
        }
        if (table1.getSelectedColumn() == 7) {
    int idCapitulo = (int) table1.getValueAt(table1.getSelectedRow(), 0);

    // Preguntar al usuario si realmente desea eliminar el capítulo
    int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar este capítulo?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
    if (opcion == JOptionPane.YES_OPTION) {
        CapituloDAO capituloDAO = new CapituloDAO();
        boolean eliminado = capituloDAO.eliminarCapitulo(idCapitulo);
        if (eliminado) {
            JOptionPane.showMessageDialog(this, "Capítulo eliminado exitosamente.");

            actualizarTable();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar el capítulo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    }//GEN-LAST:event_table1MouseClicked

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
