/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prueba;

import database.AnimeDAO;
import database.AnimeTableModel;
import database.CapituloDAO;
import database.CapituloTableModel;
import database.MeGustaDAO;
import database.UsuarioDAO;
import java.awt.Image;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Anime;
import model.Capitulo;

/**
 *
 * @author Usuario
 */
public class Animes extends javax.swing.JFrame {

    private UsuarioDAO usuarioDAO;
    private AnimeDAO animeDAO;
    private CapituloDAO capituloDAO;
    private MeGustaDAO meGustaDAO;
    private int idUsuario;
    private int codAnime;
    private boolean meGusta;
    private AñadirCap añadirCapInstance;
    
    /**
     * Creates new form NewJFrame1
     */
    public Animes() {
        initComponents();
        usuarioDAO = new UsuarioDAO();
        animeDAO = new AnimeDAO();
        capituloDAO = new CapituloDAO();
        meGustaDAO = new MeGustaDAO();
        
    }
    public Animes(int  idUsuario, int codAnime) {

        initComponents();
        this.codAnime = codAnime; // Almacena la identificación del anime
        System.out.println(codAnime);
        
        this.idUsuario = idUsuario;
        System.out.println(idUsuario);
  
        usuarioDAO = new UsuarioDAO();
        animeDAO = new AnimeDAO(); // Asegúrate de inicializar animeDAO
        capituloDAO = new CapituloDAO();
        meGustaDAO = new MeGustaDAO();
        
        
        
        
        
        mostrarInformacionAnime();
        actualizarTable();
        mostrarEstadoMeGusta();
     
    }
    
    public int obtenerIdUsuario() {
        return idUsuario;
    }
    
   
    
    private void mostrarInformacionAnime() {
        Anime anime = animeDAO.obtenerAnimePorId(codAnime); // Obtiene el anime por su identificación
        if (anime != null) {
            lblNombre.setText(anime.getNombre());
            lblAnyo.setText(String.valueOf(anime.getAnyo()));
            lblDescripcion.setText(anime.getDescripcion());
            lblDirector.setText(anime.getDirector());
            lblEstudio.setText(anime.getEstudio());
            lblCategoria.setText(anime.getCategoria().getNombre());
            lblGenero.setText(anime.getGenero().getNombre());
           try { 
               
               
            String imagePath = "/imagenes/" + codAnime + ".jpg";
            System.out.println("Intentando cargar la imagen desde: " + imagePath);
            
            // Usar getResourceAsStream para cargar la imagen desde el directorio de recursos
            InputStream imageStream = getClass().getResourceAsStream(imagePath);
            if (imageStream == null) {
                throw new IllegalArgumentException("El archivo de imagen no se encontró: " + imagePath);
            }
            Image imagenOriginal = ImageIO.read(imageStream);
            
            Image imagenEscalada = imagenOriginal.getScaledInstance(226, 320, Image.SCALE_SMOOTH);
            ImageIcon imagenIcono = new ImageIcon(imagenEscalada);
            Imagen.setIcon(imagenIcono);
            Imagen.revalidate();
            Imagen.repaint();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        } else {
            // Manejar el caso en que no se encuentre el anime
            System.out.println("El anime no se encontró en la base de datos.");
        }
    }

    
    public void actualizarTable() {
        CapituloTableModel model = new CapituloTableModel();
        List<Capitulo> capitulos = capituloDAO.listarCapitulosPorAnime(codAnime); // Asegúrate de tener un método para obtener todos los capítulos en tu CapituloDAO
        model.setCapitulos(capitulos);
        table1.setModel(model);


        // Ocultar la columnas
        table1.getColumnModel().getColumn(0).setMinWidth(0);
        table1.getColumnModel().getColumn(0).setMaxWidth(0);
        table1.getColumnModel().getColumn(0).setWidth(0);
        
        table1.getColumnModel().getColumn(1).setMinWidth(0);
        table1.getColumnModel().getColumn(1).setMaxWidth(0);
        table1.getColumnModel().getColumn(1).setWidth(0);
        
        String nombreUsuario = usuarioDAO.obtenerNombreUsuario(idUsuario);

        if (!"admin".equalsIgnoreCase(nombreUsuario)) {
        // Ocultar las columnas 6 y 7 si el usuario no es administrador
        table1.getColumnModel().getColumn(6).setMinWidth(0);
        table1.getColumnModel().getColumn(6).setMaxWidth(0);
        table1.getColumnModel().getColumn(6).setWidth(0);

        table1.getColumnModel().getColumn(7).setMinWidth(0);
        table1.getColumnModel().getColumn(7).setMaxWidth(0);
        table1.getColumnModel().getColumn(7).setWidth(0);
        
        
       
        }
    }
    private void mostrarEstadoMeGusta() {
        meGusta = meGustaDAO.verificarSiLeGusta(idUsuario, codAnime);
        actualizarIconoMeGusta();
    }

    private void actualizarIconoMeGusta() {
        
        String imagePath;
        if (meGusta) {
            imagePath = "/imagenes/megustaLleno.jpg";
        } else {
            imagePath = "/imagenes/megustaVacio.jpg";
        }

        try {
            // Depuración: Imprimir la ruta del archivo
            System.out.println("Intentando cargar la imagen desde: " + imagePath);

            // Usar getResourceAsStream para cargar la imagen desde el directorio de recursos
            InputStream imageStream = getClass().getResourceAsStream(imagePath);
            if (imageStream == null) {
                throw new IllegalArgumentException("El archivo de imagen no se encontró: " + imagePath);
            }
            Image imagenOriginal = ImageIO.read(imageStream);

            ImageIcon imagenIcono = new ImageIcon(imagenOriginal);
            GustarImg.setIcon(imagenIcono);
            GustarImg.revalidate();
            GustarImg.repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
}


    private void actualizarEstadoMeGusta() {
        meGusta = !meGusta;
        actualizarIconoMeGusta();
        meGustaDAO.actualizarMeGusta(idUsuario, codAnime, meGusta);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        lblAnyo = new javax.swing.JLabel();
        lblDirector = new javax.swing.JLabel();
        lblEstudio = new javax.swing.JLabel();
        lblCategoria = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();
        Imagen = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        GustarImg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Detalles del anime");

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

        jLabel2.setText("Capitulos:");

        jLabel3.setText("Nombre:");

        jLabel4.setText("Descripcion:");

        jLabel5.setText("Año:");

        jLabel6.setText("Director:");

        jLabel7.setText("Estudio:");

        jLabel8.setText("Categoria:");

        jLabel9.setText("Genero:");

        jLabel10.setText("Me gusta:");

        GustarImg.setPreferredSize(new java.awt.Dimension(64, 64));
        GustarImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GustarImgMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(179, 179, 179))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 25, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(75, 75, 75)
                                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(147, 147, 147)
                                        .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel8)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel9))
                                                .addGap(93, 93, 93)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblAnyo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblEstudio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jLabel10)
                                            .addComponent(GustarImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(77, 77, 77)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(49, 49, 49))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(Imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(138, 138, 138)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel1)
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(lblAnyo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDirector, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(lblEstudio, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(lblCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblGenero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(jLabel10)
                        .addGap(28, 28, 28)
                        .addComponent(GustarImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(77, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
                if (table1.getSelectedColumn() == 6) { // Si se hace clic en el índice 6 (índice de la columna para modificar)
                int idCapitulo = (int) table1.getValueAt(fila, 0); // Suponiendo que el ID del capítulo está en la primera columna
                System.out.println(idCapitulo); 

                ModificarCap mc = new ModificarCap(idUsuario, idCapitulo, codAnime,this,añadirCapInstance); // Crear una instancia del frame ModificarCap
                mc.setVisible(true); // Mostrar el frame ModificarCap
            }
                if (table1.getSelectedColumn() == 7) { // 7 es el índice de la columna de la papelera
            int idCapitulo = (int) table1.getValueAt(table1.getSelectedRow(), 0); // Suponiendo que el ID del capítulo está en la primera columna

            // Preguntar al usuario si realmente desea eliminar el capítulo
            int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar este capítulo?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                CapituloDAO capituloDAO = new CapituloDAO(); // Crea una instancia del DAO
                boolean eliminado = capituloDAO.eliminarCapitulo(idCapitulo); // Llama al método para eliminar el capítulo
                if (eliminado) {
                    JOptionPane.showMessageDialog(this, "Capítulo eliminado exitosamente.");
                    // Actualiza la tabla de capítulos si es necesario
                    // Por ejemplo:
                    actualizarTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar el capítulo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }


    }//GEN-LAST:event_table1MouseClicked

    private void GustarImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GustarImgMouseClicked
        // TODO add your handling code here:
        actualizarEstadoMeGusta();
    }//GEN-LAST:event_GustarImgMouseClicked

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
            java.util.logging.Logger.getLogger(Animes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Animes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Animes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Animes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Animes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel GustarImg;
    private javax.swing.JLabel Imagen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnyo;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDirector;
    private javax.swing.JLabel lblEstudio;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTable table1;
    // End of variables declaration//GEN-END:variables
}
