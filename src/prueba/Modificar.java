/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prueba;

import database.AnimeDAO;
import database.CapituloDAO;
import database.CategoriaDAO;
import database.GeneroDAO;
import database.UsuarioDAO;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import model.Anime;
import model.Categoria;
import model.Genero;
import java.sql.ResultSet;

/**
 *
 * @author Usuario
 */
public class Modificar extends javax.swing.JFrame {

    private UsuarioDAO usuarioDAO;
    private AnimeDAO animeDAO;
    private CapituloDAO capituloDAO;
    private CategoriaDAO categoriaDAO;
    private GeneroDAO generoDAO;
    private int idUsuario;
    private int codAnime;
    /**
     * Creates new form Modificar
     */
    public Modificar() {
        initComponents();
        usuarioDAO = new UsuarioDAO();
        animeDAO = new AnimeDAO();
        capituloDAO = new CapituloDAO();
        categoriaDAO = new CategoriaDAO();
        generoDAO = new GeneroDAO(); // Asegúrate de inicializar generoDAO
}

    public Modificar(int idUsuario, int codAnime) {
        initComponents();
        this.codAnime = codAnime; 
        this.idUsuario = idUsuario;
        usuarioDAO = new UsuarioDAO();
        animeDAO = new AnimeDAO(); 
        capituloDAO = new CapituloDAO();
        categoriaDAO = new CategoriaDAO();
        generoDAO = new GeneroDAO(); // Asegúrate de inicializar generoDAO
        cargarDatosAnime(codAnime); 
        cargarComboBoxes();
        
    }
    
    
        public void cargarComboBoxes() {
        // Borrar los elementos existentes en los ComboBoxes
        datoCateg.removeAllItems();
        datoGenero.removeAllItems();

        // Obtener todas las categorías y géneros
        List<Categoria> categorias = categoriaDAO.obtenerTodasLasCategorias();
        List<Genero> generos = generoDAO.obtenerTodosLosGeneros();

        // Agregar las nuevas categorías al ComboBox datoCateg
        for (Categoria categoria : categorias) {
            datoCateg.addItem(categoria.getNombre());
        }

        // Agregar los nuevos géneros al ComboBox datoGenero
        for (Genero genero : generos) {
            datoGenero.addItem(genero.getNombre());
        }

        // Obtener el anime correspondiente al código y seleccionar su categoría y género en los ComboBoxes
        Anime anime = animeDAO.obtenerAnimePorId(codAnime);
        if (anime != null) {
            datoCateg.setSelectedItem(anime.getCategoria().getNombre());
            datoGenero.setSelectedItem(anime.getGenero().getNombre());
        }
    }
        private void cargarDatosAnime(int codAnime) {
        Anime anime = animeDAO.obtenerAnimePorId(codAnime);
        if (anime != null) {
            datoNombre.setText(anime.getNombre());
            datoAnyo.setText(String.valueOf(anime.getAnyo()));
            datoDescrip.setText(anime.getDescripcion());
            datoDirec.setText(anime.getDirector());
            datoEstud.setText(anime.getEstudio());

            // Seleccionar la categoría y el género correspondiente
            datoCateg.setSelectedItem(anime.getCategoria().getNombre());
            datoGenero.setSelectedItem(anime.getGenero().getNombre());
        } else {
            JOptionPane.showMessageDialog(this, "El anime no se encontró.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    
    private void actualizarDatosAnime(int codAnime) {
    String nombre = datoNombre.getText();
    int anyo = Integer.parseInt(datoAnyo.getText());
    String descripcion = datoDescrip.getText();
    String director = datoDirec.getText();
    String estudio = datoEstud.getText();
    String categoriaSeleccionada = (String) datoCateg.getSelectedItem();
    String generoSeleccionado = (String) datoGenero.getSelectedItem();

    // Obtener el ID de la categoría y el género seleccionados
    int idCategoria = categoriaDAO.obtenerIdCategoriaPorNombre(categoriaSeleccionada);
    int idGenero = generoDAO.obtenerIdGeneroPorNombre(generoSeleccionado);

    if (idCategoria == -1 || idGenero == -1) {
        JOptionPane.showMessageDialog(this, "Categoría o Género no válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Categoria categoria = new Categoria(idCategoria, categoriaSeleccionada);
    Genero genero = new Genero(idGenero, generoSeleccionado);

    // Crear un objeto Anime con los nuevos datos y el ID del anime
    Anime animeActualizado = new Anime(codAnime, nombre, anyo, descripcion, director, estudio, categoria, genero);

    // Actualizar los datos del anime en la base de datos
    boolean actualizacionExitosa = animeDAO.actualizarAnime(animeActualizado);

    if (actualizacionExitosa) {
        JOptionPane.showMessageDialog(this, "Datos del anime actualizados correctamente.");
    } else {
        JOptionPane.showMessageDialog(this, "Error al actualizar los datos del anime.", "Error", JOptionPane.ERROR_MESSAGE);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        datoCateg = new javax.swing.JComboBox<>();
        datoGenero = new javax.swing.JComboBox<>();
        datoNombre = new javax.swing.JTextField();
        datoAnyo = new javax.swing.JTextField();
        datoDirec = new javax.swing.JTextField();
        datoEstud = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        datoDescrip = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Modificar");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Descripcion:");

        jLabel4.setText("Año:");

        jLabel5.setText("Estudio:");

        jLabel6.setText("Categoria:");

        jLabel7.setText("Genero:");

        jLabel8.setText("Director:");

        datoCateg.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        datoGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        datoDescrip.setColumns(20);
        datoDescrip.setRows(5);
        jScrollPane1.setViewportView(datoDescrip);

        jButton1.setText("Modificar");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(datoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datoEstud, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(datoAnyo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(datoDirec, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(181, 181, 181)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(datoCateg, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(datoGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(95, 95, 95)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(datoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(datoAnyo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(datoDirec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(datoEstud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(88, 88, 88))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(datoGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datoCateg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(66, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(114, 114, 114))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        actualizarDatosAnime(codAnime);
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
            java.util.logging.Logger.getLogger(Modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Modificar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField datoAnyo;
    private javax.swing.JComboBox<String> datoCateg;
    private javax.swing.JTextArea datoDescrip;
    private javax.swing.JTextField datoDirec;
    private javax.swing.JTextField datoEstud;
    private javax.swing.JComboBox<String> datoGenero;
    private javax.swing.JTextField datoNombre;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
