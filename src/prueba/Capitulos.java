/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prueba;

import database.AnimeDAO;
import database.CapituloDAO;
import database.UsuarioDAO;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.Capitulo;
import model.Comentario;

public class Capitulos extends javax.swing.JFrame {
    
    private UsuarioDAO usuarioDAO;
    private AnimeDAO animeDAO;
    private CapituloDAO capituloDAO;
    private int idUsuario;
    private int codAnime;
    private int codCap;
    private JScrollPane scrollPaneles;

    public Capitulos() {
        initComponents();
        usuarioDAO = new UsuarioDAO();
        animeDAO = new AnimeDAO();
        capituloDAO = new CapituloDAO();
        
        
    }

    public Capitulos(int idUsuario,int codCap, int codAnime) {
        
        
        this();
        this.idUsuario = idUsuario;
        this.codAnime = codAnime;
        this.codCap = codCap;
        mostrarInformacionCapitulo();
        
    
    }
    

    private void mostrarInformacionCapitulo() {
        Capitulo capitulo = capituloDAO.obtenerCapituloPorId(codCap, codAnime); // Obtiene el capítulo por su identificación
        if (capitulo != null) {
            lblNombre.setText(capitulo.getAnime().getNombre());
            lblNumeroCapitulo.setText(String.valueOf(capitulo.getNumeroCapitulo()));
            lblTituloCapitulo.setText(capitulo.getTitulo());
            lblDuracion.setText(String.valueOf(capitulo.getDuracion()));
            
            // Muestra los comentarios
            mostrarComentarios(capitulo.getComentarios());
        } else {
            // Manejar el caso en que no se encuentre el capítulo
            System.out.println("El capítulo no se encontró en la base de datos.");
        }
    }
    

    private void mostrarComentarios(List<Comentario> comentarios) {
        panelComentarios.removeAll();
        panelComentarios.setLayout(new BoxLayout(panelComentarios, BoxLayout.Y_AXIS));

        // Variable para verificar si es el primer comentario
        boolean primerComentario = true;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Recorre los comentarios y agrega cada uno al panel
        for (Comentario comentario : comentarios) {
            // Si no es el primer comentario, agrega un espacio en blanco
            if (!primerComentario) {
                panelComentarios.add(Box.createVerticalStrut(10)); // Espacio vertical
            } else {
                primerComentario = false;
            }

            String usuarioFecha = comentario.getNombreUsuario() != null ? 
                comentario.getNombreUsuario() + " - " + dateFormat.format(comentario.getFechaComentario()) : "";
            String comentarioTexto = comentario.getComentario();
            // Verifica si el comentario es null, y si lo es, establece una cadena vacía
            if (comentarioTexto == null) {
                comentarioTexto = "";
            }

            // Crea un panel para contener el nombre de usuario y la fecha
            JPanel panelUsuarioFecha = new JPanel();
            panelUsuarioFecha.setLayout(new BoxLayout(panelUsuarioFecha, BoxLayout.X_AXIS));
            panelUsuarioFecha.add(new JLabel(usuarioFecha));

            // Crea un label para el comentario
            JLabel labelComentario = new JLabel(comentarioTexto);

            // Agrega el panel con el nombre de usuario y la fecha, y el label del comentario al panel principal
            panelComentarios.add(panelUsuarioFecha);
            panelComentarios.add(labelComentario);
        }

        // Refresca el panel de comentarios
        panelComentarios.revalidate();
        panelComentarios.repaint();
    }
    
    private void comentarCapitulo(String comentarioTexto) {
    // Verificar si el texto del comentario no está vacío
    if (!comentarioTexto.isEmpty()) {
        // Crear un nuevo comentario con el texto proporcionado y el ID del usuario
        Comentario comentario = new Comentario();
        comentario.setComentario(comentarioTexto);
        comentario.setUsuarioId(idUsuario);

        // Obtener la fecha actual
        java.util.Date fechaActual = new java.util.Date();
        comentario.setFechaComentario(new java.sql.Date(fechaActual.getTime()));

        // Agregar el comentario al capítulo
        capituloDAO.agregarComentario(codCap, codAnime, comentario);

        // Actualizar la visualización de comentarios
        mostrarInformacionCapitulo();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        areaComentario = new javax.swing.JTextArea();
        lblNombre = new javax.swing.JLabel();
        lblNumeroCapitulo = new javax.swing.JLabel();
        lblTituloCapitulo = new javax.swing.JLabel();
        lblDuracion = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        panelComentarios = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Numero Capitulo:");

        jLabel2.setText("Titulo:");

        jLabel3.setText("Duracion:");

        jLabel4.setText("Comentario:");

        jLabel5.setText("Nombre Anime:");

        areaComentario.setColumns(20);
        areaComentario.setRows(5);
        jScrollPane1.setViewportView(areaComentario);

        lblNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel6.setText("Comentarios:");

        jButton1.setText("Publicar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        panelComentarios.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout panelComentariosLayout = new javax.swing.GroupLayout(panelComentarios);
        panelComentarios.setLayout(panelComentariosLayout);
        panelComentariosLayout.setHorizontalGroup(
            panelComentariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelComentariosLayout.setVerticalGroup(
            panelComentariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNumeroCapitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTituloCapitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(panelComentarios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                                .addGap(37, 37, 37)
                                .addComponent(jButton1)))))
                .addGap(147, 147, 147))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(lblNumeroCapitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(48, 48, 48)
                            .addComponent(jLabel2))
                        .addComponent(lblTituloCapitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3)
                        .addComponent(lblDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelComentarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String textoComentario = areaComentario.getText();
    
    // Llamar al método comentarCapitulo para agregar el comentario
    comentarCapitulo(textoComentario);
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
            java.util.logging.Logger.getLogger(Capitulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Capitulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Capitulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Capitulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Capitulos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaComentario;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNumeroCapitulo;
    private javax.swing.JLabel lblTituloCapitulo;
    private javax.swing.JPanel panelComentarios;
    // End of variables declaration//GEN-END:variables
}
