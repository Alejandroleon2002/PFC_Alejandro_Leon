/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prueba;

import database.AnimeDAO;
import database.AnimeTableModel;
import database.CategoriaDAO;
import database.GeneroDAO;
import database.UsuarioDAO;
import java.util.List;
import model.Anime;

/**
 *
 * @author Usuario
 */
public class MeGustas extends javax.swing.JFrame {

    
    private int idUsuario;
    private UsuarioDAO usuarioDAO;
    private AnimeDAO animeDAO;
    private static MeGustas instance;
  
    /**
     * Creates new form MeGustas
     */
    public MeGustas() {
        initComponents();
        usuarioDAO = new UsuarioDAO();
        animeDAO = new AnimeDAO();
    }
    
    public MeGustas(int idUsuario) {
        this();
        this.idUsuario = idUsuario;
        System.out.println(idUsuario);

        String nombreUsuario = usuarioDAO.obtenerNombreUsuario(idUsuario);
        System.out.println("Nombre de usuario obtenido: " + nombreUsuario);
        

        actualizarTable();
       
    }
    
    public static MeGustas getInstance() {
        if (instance == null) {
            instance = new MeGustas();
        }
        return instance;
    }
    
    public int obtenerIdUsuario() {
        return idUsuario;
    }
    
    public void actualizarTable() {
        AnimeTableModel model = new AnimeTableModel();
        List<Anime> animes = animeDAO.listarAnimesConMeGusta(idUsuario);
        model.setAnimes(animes);
        table1.setModel(model);


        table1.getColumnModel().getColumn(6).setMinWidth(0);
        table1.getColumnModel().getColumn(6).setMaxWidth(0);
        table1.getColumnModel().getColumn(6).setWidth(0);
        
        table1.getColumnModel().getColumn(7).setMinWidth(0);
        table1.getColumnModel().getColumn(7).setMaxWidth(0);
        table1.getColumnModel().getColumn(7).setWidth(0);
        
        table1.getColumnModel().getColumn(9).setMinWidth(0);
        table1.getColumnModel().getColumn(9).setMaxWidth(0);
        table1.getColumnModel().getColumn(9).setWidth(0);
        
        table1.getColumnModel().getColumn(10).setMinWidth(0);
        table1.getColumnModel().getColumn(10).setMaxWidth(0);
        table1.getColumnModel().getColumn(10).setWidth(0);
       
    
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
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

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

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/AnimeCheck.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(274, 274, 274))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        // TODO add your handling code here:
        int fila = table1.getSelectedRow();
        
        if (table1.getSelectedColumn()==8){
            int codAnime = (int) table1.getValueAt(fila, 6);
            System.out.println(codAnime); 
            
            int idUsuario = obtenerIdUsuario();
            System.out.println(idUsuario); 
            
            Animes A = new Animes(this, idUsuario, codAnime);
            A.setVisible(true);
            
            
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
            java.util.logging.Logger.getLogger(MeGustas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MeGustas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MeGustas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MeGustas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MeGustas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel11;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table1;
    // End of variables declaration//GEN-END:variables
}
