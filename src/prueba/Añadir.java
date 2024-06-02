/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prueba;

import database.AnimeDAO;
import database.AnimeTableModel;
import database.CapituloDAO;
import database.CategoriaDAO;
import database.GeneroDAO;
import database.MeGustaDAO;
import database.UsuarioDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import model.Anime;
import model.Categoria;
import model.Genero;

/**
 *
 * @author Usuario
 */
public class Añadir extends javax.swing.JFrame {

    
    private int idUsuario;
    private UsuarioDAO usuarioDAO;
    private AnimeDAO animeDAO;
    private CategoriaDAO categoriaDAO;
    private GeneroDAO generoDAO;
    private CapituloDAO capituloDAO;
    private MeGustaDAO meGustaDAO;
    private int codAnime;
    private boolean meGusta;
    private Admin adminInstance;
    
    
    
    /**
     * Creates new form Añadir
     */
    public Añadir() {
       initComponents();
        usuarioDAO = new UsuarioDAO();
        animeDAO = new AnimeDAO();
        categoriaDAO = new CategoriaDAO();
        generoDAO = new GeneroDAO();
        capituloDAO = new CapituloDAO();
        meGustaDAO = new MeGustaDAO();
        
        
        actualizarTables();
       
    }
    
    
     public Añadir(int  idUsuario, int codAnime, Admin admin) {
         
        initComponents();
        this.codAnime = codAnime; // Almacena la identificación del anime
       
        this.adminInstance = admin;
        this.idUsuario = idUsuario;
       
        System.out.println("ID Usuario: " + idUsuario);
        System.out.println("Código Anime: " + codAnime);
        
  
        usuarioDAO = new UsuarioDAO();
        animeDAO = new AnimeDAO();
        categoriaDAO = new CategoriaDAO();
        generoDAO = new GeneroDAO();
        capituloDAO = new CapituloDAO();
        meGustaDAO = new MeGustaDAO();
       
        combox();
        actualizarTables();
        
     }
     
    
        public int obtenerIdUsuario() {
            return idUsuario;
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

   
    public void actualizarTablaAñadir() {
    AnimeTableModel model = new AnimeTableModel();
    List<Anime> animes = animeDAO.listarAnimesConDetalles();
    model.setAnimes(animes);
    table1.setModel(model);

    // Ocultar las columnas si es necesario
    table1.getColumnModel().getColumn(6).setMinWidth(0);
    table1.getColumnModel().getColumn(6).setMaxWidth(0);
    table1.getColumnModel().getColumn(6).setWidth(0);
    
    table1.getColumnModel().getColumn(7).setMinWidth(0);
    table1.getColumnModel().getColumn(7).setMaxWidth(0);
    table1.getColumnModel().getColumn(7).setWidth(0);
}
        
        private void guardarAnime() {
        String nombre = txtNombre.getText();
        String anyoStr = txtAnyo.getText();
        String descripcion = txtDescripcion.getText();
        String director = txtDirector.getText();
        String estudio = txtEstudio.getText();

        if (nombre.isEmpty() || anyoStr.isEmpty() || descripcion.isEmpty() || director.isEmpty() || estudio.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int anyo;
        try {
            anyo = Integer.parseInt(anyoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El año debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombreCategoria = (String) comboCategoria.getSelectedItem();
        String nombreGenero = (String) comboGenero.getSelectedItem();

        if (nombreCategoria == null || nombreGenero == null || nombreCategoria.equals("Elija uno") || nombreGenero.equals("Elija uno")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una categoría y un género.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idCategoria = categoriaDAO.obtenerIdCategoriaPorNombre(nombreCategoria);
        int idGenero = generoDAO.obtenerIdGeneroPorNombre(nombreGenero);

        if (idCategoria == -1 || idGenero == -1) {
            JOptionPane.showMessageDialog(this, "La categoría o el género seleccionados no son válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Anime anime = new Anime(nombre, anyo, descripcion, director, estudio, idCategoria, idGenero);

        boolean success = animeDAO.insertarAnime(anime);

        if (success) {
            JOptionPane.showMessageDialog(this, "Anime agregado exitosamente!");

            // Limpiar campos de texto
            txtAnyo.setText("");
            txtDirector.setText("");
            txtEstudio.setText("");
            txtNombre.setText("");
            txtDescripcion.setText("");
            comboCategoria.setSelectedIndex(0);
            comboGenero.setSelectedIndex(0);
            
            

            if (adminInstance != null) {
                adminInstance.actualizarTable();
                
            }


        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar anime.", "Error", JOptionPane.ERROR_MESSAGE);
        }
}

        public void actualizarTables() {
            
            System.out.println("Actualizando tabla en Añadir");
            AnimeTableModel model = new AnimeTableModel();
            List<Anime> animes = animeDAO.listarAnimesConDetalles();
            System.out.println("Número de animes obtenidos: " + animes.size());
            model.setAnimes(animes);
            table1.setModel(model);

            // Ocultar columnas no deseadas
            table1.getColumnModel().getColumn(6).setMinWidth(0);
            table1.getColumnModel().getColumn(6).setMaxWidth(0);
            table1.getColumnModel().getColumn(6).setWidth(0);

            table1.getColumnModel().getColumn(7).setMinWidth(0);
            table1.getColumnModel().getColumn(7).setMaxWidth(0);
            table1.getColumnModel().getColumn(7).setWidth(0);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtAnyo = new javax.swing.JTextField();
        txtDirector = new javax.swing.JTextField();
        txtEstudio = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        comboCategoria = new javax.swing.JComboBox<>();
        comboGenero = new javax.swing.JComboBox<>();

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

        jLabel3.setText("Nombre:");

        jLabel4.setText("Descripcion:");

        jLabel5.setText("Año:");

        jLabel6.setText("Director:");

        jLabel7.setText("Estudio:");

        jLabel8.setText("Categoria:");

        jLabel9.setText("Genero:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane2.setViewportView(txtDescripcion);

        jButton1.setText("Añadir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        jButton2.setText("Reiniciar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        comboCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                            .addComponent(txtDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEstudio, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel3))
                            .addGap(31, 31, 31)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtAnyo)
                                .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jButton1)
                        .addGap(26, 26, 26)
                        .addComponent(jButton2)))
                .addGap(121, 121, 121)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(91, 91, 91)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(comboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtAnyo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtDirector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtEstudio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(122, 122, 122)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))))
                .addGap(0, 139, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        // TODO add your handling code here:
        int fila = table1.getSelectedRow();
        
        
        txtNombre.setText(table1.getValueAt(fila, 0).toString());
        txtAnyo.setText(table1.getValueAt(fila, 1).toString());
        
        comboCategoria.setSelectedItem(table1.getValueAt(fila, 4));
        comboGenero.setSelectedItem(table1.getValueAt(fila, 5));
        txtDirector.setText(table1.getValueAt(fila, 2).toString());
        txtEstudio.setText(table1.getValueAt(fila, 3).toString());
        txtDescripcion.setText(table1.getValueAt(fila, 7).toString());
        
        

        if (table1.getSelectedColumn()==8){
            int codAnime = (int) table1.getValueAt(fila, 6);
            System.out.println(codAnime); 
            
            int idUsuario = obtenerIdUsuario();
            System.out.println(idUsuario); 
            
            Animes A = new Animes( idUsuario,codAnime);
            A.setVisible(true);
        }
        
        if (table1.getSelectedColumn()==9){
            int codAnime = (int) table1.getValueAt(fila, 6);
            System.out.println(codAnime); 
            
            int idUsuario = obtenerIdUsuario();
            System.out.println(idUsuario); 
            
            Modificar ventanaModificar = new Modificar(idUsuario, codAnime, adminInstance, this); // Pasar la instancia de "Añadir"
            ventanaModificar.setVisible(true);
            
            txtAnyo.setText("");
            txtDirector.setText("");
            txtEstudio.setText("");
            txtNombre.setText("");
            txtDescripcion.setText("");
            comboCategoria.setSelectedIndex(0);
            comboGenero.setSelectedIndex(0);
        }
        if (table1.getSelectedColumn() == 10) {
        int codAnime = (int) table1.getValueAt(fila, 6);
        
        int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar este anime, sus capítulos y comentarios?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean eliminacionExitosa = animeDAO.eliminarAnime(codAnime);
            if (eliminacionExitosa) {
                JOptionPane.showMessageDialog(null, "Anime, sus capítulos, comentarios y 'Me Gusta' eliminados correctamente.");
                actualizarTables();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el anime, sus capítulos, comentarios y 'Me Gusta'.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    }//GEN-LAST:event_table1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        txtAnyo.setText("");
        txtDirector.setText("");
        txtEstudio.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        comboCategoria.setSelectedIndex(0);
        comboGenero.setSelectedIndex(0);
        
        actualizarTables();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        guardarAnime();
        actualizarTables();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Añadir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Añadir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Añadir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Añadir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Añadir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboCategoria;
    private javax.swing.JComboBox<String> comboGenero;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table1;
    private javax.swing.JTextField txtAnyo;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtDirector;
    private javax.swing.JTextField txtEstudio;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
