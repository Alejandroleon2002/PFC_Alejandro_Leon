/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;


/**
 *
 * @author Usuario
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
      try {


                FlatArcDarkOrangeIJTheme.setup();
            } catch( Exception ex ) {
                  System.err.println( "Failed to initialize LaF" );
        }
        
        InicioDeSesion in = new InicioDeSesion();
        in.setVisible(true);
  
        
         
    }
    
}
