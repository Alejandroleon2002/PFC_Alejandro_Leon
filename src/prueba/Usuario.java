/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package prueba;



import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Usuario extends javax.swing.JFrame {

    private Connection conexion;
    private String bbdd = "jdbc:hsqldb:hsql://localhost/";
    private int userID;
    int filaSelect;
  
    /**
     * Creates new form Usuario
     */
    public Usuario() {
        initComponents();
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            conexion = DriverManager.getConnection(bbdd, "SA", "SA");
            if (conexion != null) {
                System.out.println("Conexión creada exitosamente");
            } else {
                System.out.println("Problema al crear la conexión");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }
        
        
    }
    
    public Usuario(int id) {
        initComponents();
         System.out.println(id);
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            conexion = DriverManager.getConnection(bbdd, "SA", "SA");
            if (conexion != null) {
                System.out.println("Conexión creada exitosamente");
            } else {
                System.out.println("Problema al crear la conexión");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }
        
        String nombreUsuario = obtenerNombreUsuario(id);
        System.out.println("Nombre de usuario obtenido: " + nombreUsuario);
        lblNombreUsuario.setText( nombreUsuario);
        
       actualizarTable(conexion);
       combox();
    }
    
    private String obtenerNombreUsuario(int id) {
        try {
            String sql = "SELECT nombre_usuario FROM Usuario WHERE usuario_id = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("nombre_usuario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Usuario no encontrado";
    }
    
    
    public void actualizarTable(Connection con)
    {
        
        
       DefaultTableModel tm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex) {
                   
                         case 7:
                        return ImageIcon.class;
                    default:
                        return Object.class;
                }
            }
        };
        
        
        
        tm.addColumn("Nombre");
        tm.addColumn("Año");
        tm.addColumn("Director");
        tm.addColumn("Estudio");
        tm.addColumn("Categoria");
        tm.addColumn("Genero");
        tm.addColumn("ID Anime");
        tm.addColumn("Detalles");
       
        table1.setModel(tm);
        
        
        Object [] datos = new Object[50];
     
             
         
                
                
                
                
        
        try
        {   
            
            
            
            String sql = "SELECT DISTINCT A.NOMBRE, A.ANYO , A.DIRECTOR ,A.ESTUDIO , C.NOMBRE , G.NOMBRE, A.ANIME_ID "
                    + "FROM Anime A, Categoria C, Genero G "+
            "WHERE A.ID_CATEGORIA = C.CATEGORIA_ID AND A.ID_GENERO = G.GENERO_ID " ;
            Statement lee= con.createStatement();
            ResultSet resultado = lee.executeQuery(sql);
            
            while(resultado.next())
            {
                datos[0]=resultado.getString(1);
                datos[1]=resultado.getInt(2);
                datos[2]=resultado.getString(3);
                datos[3]=resultado.getString(4);
                datos[4]=resultado.getString(5);
                datos[5]=resultado.getString(6);
                datos[6]=resultado.getInt(7);
                datos[7] = new ImageIcon(getClass().getResource("/imagenes/File-Text.jpg"));
                
                tm.addRow(datos);
            }           
            table1.setModel(tm);
            
            table1.getColumnModel().getColumn(6).setMaxWidth(0);
            table1.getColumnModel().getColumn(6).setMinWidth(0);
            table1.getColumnModel().getColumn(6).setPreferredWidth(0);

        }
        catch(Exception e){
        e.printStackTrace();}

    }
    
    
    
    
    public void combox()
    {
         try{
        
            ResultSet result = null;
            String sqlC="SELECT DISTINCT DIRECTOR from Anime";
            Statement stmt = conexion.createStatement();
            result=stmt.executeQuery(sqlC);
            
            
            jComboBox1.removeAllItems();
            jComboBox1.addItem("Elija uno");
            
            ResultSet result2 = null;
            String sqlC2="SELECT DISTINCT ESTUDIO from Anime";
            Statement stmt2 = conexion.createStatement();
            result2=stmt2.executeQuery(sqlC2);
            
            
            jComboBox2.removeAllItems();
            jComboBox2.addItem("Elija uno");
            
            ResultSet result3 = null;
            String sqlC3="SELECT DISTINCT NOMBRE from Categoria";
            Statement stmt3 = conexion.createStatement();
            result3=stmt3.executeQuery(sqlC3);
            
            
            jComboBox3.removeAllItems();
            jComboBox3.addItem("Elija uno");
            
            ResultSet result4 = null;
            String sqlC4="SELECT DISTINCT NOMBRE from Genero";
            Statement stmt4 = conexion.createStatement();
            result4=stmt4.executeQuery(sqlC4);
            
            
            jComboBox4.removeAllItems();
            jComboBox4.addItem("Elija uno");
            
            
            while(result.next())
            {
                jComboBox1.addItem(result.getString(1));
               
                
            }
            
           while(result2.next())
            {
                jComboBox2.addItem(result2.getString(1));
                
                
            }
           while(result3.next())
            {
                jComboBox3.addItem(result3.getString(1));
               
                
            }
           while(result4.next())
            {
                jComboBox4.addItem(result4.getString(1));
               
                
            }
           
            
        }catch(Exception e){}
    }
    
    public void Reiniciar(){
       
        jTextField1.setText("");
        jTextField2.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        actualizarTable(conexion);
     }
   
    public void Filtrar() {
        
    DefaultTableModel tm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class getColumnClass(int columnIndex) {
                switch (columnIndex) {
                   
                         case 7:
                        return ImageIcon.class;
                    default:
                        return Object.class;
                }
            }
        };

    StringBuilder condicion = new StringBuilder();

    if (jComboBox1.getSelectedIndex() > 0) {
        condicion.append("A.DIRECTOR = '").append(jComboBox1.getSelectedItem()).append("'");
    }

    if (jComboBox2.getSelectedIndex() > 0) {
        if (condicion.length() > 0) {
            condicion.append(" AND ");
        }
        condicion.append("A.ESTUDIO = '").append(jComboBox2.getSelectedItem()).append("'");
    }
    if (jComboBox3.getSelectedIndex() > 0) {
        if (condicion.length() > 0) {
            condicion.append(" AND ");
        }
        condicion.append("C.NOMBRE = '").append(jComboBox3.getSelectedItem()).append("'");
    }
    if (jComboBox4.getSelectedIndex() > 0) {
        if (condicion.length() > 0) {
            condicion.append(" AND ");
        }
        condicion.append("G.NOMBRE = '").append(jComboBox4.getSelectedItem()).append("'");
    }

    if (!jTextField1.getText().isEmpty()) {
        if (condicion.length() > 0) {
            condicion.append(" AND ");
        }
        condicion.append("A.NOMBRE LIKE '%").append(jTextField1.getText()).append("%'");
    }
    if (!jTextField2.getText().isEmpty()) {
        if (condicion.length() > 0) {
            condicion.append(" AND ");
        }
        condicion.append("A.ANYO LIKE '%").append(jTextField2.getText()).append("%'");
    }
        
        String clausula = condicion.toString();

        
       String consultaSQL = "SELECT DISTINCT A.NOMBRE, A.ANYO , A.DIRECTOR ,A.ESTUDIO , C.NOMBRE , G.NOMBRE , A.ANIME_ID  FROM Anime A, Categoria C, Genero G "+
            "WHERE A.ID_CATEGORIA = C.CATEGORIA_ID AND A.ID_GENERO = G.GENERO_ID" +(clausula.isEmpty() ? "" : " AND " + clausula);
        
       System.out.println(consultaSQL);
       
   
        tm.addColumn("Nombre");
        tm.addColumn("Año");
        tm.addColumn("Director");
        tm.addColumn("Estudio");
        tm.addColumn("Categoria");
        tm.addColumn("Genero");
        tm.addColumn("ID Anime");
        tm.addColumn("Detalles");
        
        
        table1.setModel(tm);
        
        
        Object [] datos = new Object[50];
        
     
                
        
        try
        {   
         
            Statement lee= conexion.createStatement();
            ResultSet resultado = lee.executeQuery(consultaSQL);
            
            while(resultado.next())
            {
                datos[0]=resultado.getString(1);
                datos[1]=resultado.getInt(2);
                datos[2]=resultado.getString(3);
                datos[3]=resultado.getString(4);
                datos[4]=resultado.getString(5);
                datos[5]=resultado.getString(6);
                datos[6]=resultado.getInt(7);
                datos[7] = new ImageIcon(getClass().getResource("/imagenes/File-Text.jpg"));
                
                tm.addRow(datos);
            }           
            table1.setModel(tm);
            
            table1.getColumnModel().getColumn(6).setMaxWidth(0);
            table1.getColumnModel().getColumn(6).setMinWidth(0);
            table1.getColumnModel().getColumn(6).setPreferredWidth(0);
            
        }
        catch(Exception e){
        
        e.printStackTrace();}
        
        
       
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
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblNombreUsuario = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

        jButton1.setText("Filtrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Director:");

        jLabel5.setText("Estudio:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel6.setText("Categoria:");

        jLabel1.setText("Nombre:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Genero:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Año:");

        jLabel2.setText("Usuario:");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton2.setText("Reiniciar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addComponent(jButton1)
                .addGap(52, 52, 52)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jLabel1)
                                .addGap(67, 67, 67))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(300, 300, 300)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(167, 167, 167))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel3)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Filtrar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Reiniciar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        // TODO add your handling code here:
        int fila = table1.getSelectedRow();
        
        filaSelect = fila + 1;
        
        jTextField1.setText(table1.getValueAt(fila, 0).toString());
        jTextField2.setText(table1.getValueAt(fila, 1).toString());
        
        jComboBox1.setSelectedItem(table1.getValueAt(fila, 2));
        jComboBox2.setSelectedItem(table1.getValueAt(fila, 3));
        jComboBox3.setSelectedItem(table1.getValueAt(fila, 4));
        jComboBox4.setSelectedItem(table1.getValueAt(fila, 5));
        


        if (table1.getSelectedColumn()==7){
            int codAnime = (int) table1.getValueAt(fila, 6);
            System.out.println(codAnime); 
        
            Anime A = new Anime(codAnime);
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
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JTable table1;
    // End of variables declaration//GEN-END:variables
}
