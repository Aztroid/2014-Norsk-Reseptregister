/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA*/

import java.awt.*;
import javax.swing.*;

public class MainDriver{
    public static void main(String []args){
        /*final TestGUI vindu = new TestGUI();
        vindu.addWindowListener(
  			new WindowAdapter() {
  				public void windowClosing( WindowEvent e )
  				{
  					System.exit( 0 );
  				}
  			} );*/
    
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                Hovedramme ramme = new Hovedramme();
                ramme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ramme.setVisible(true);
            }
        });
    }
}
