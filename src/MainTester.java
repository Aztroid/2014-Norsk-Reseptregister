/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA*/

import java.awt.event.*;

public class MainTester{
    public static void main(String []args){
        final TestGUI vindu = new TestGUI();
        vindu.addWindowListener(
  			new WindowAdapter() {
  				public void windowClosing( WindowEvent e )
  				{
  					System.exit( 0 );
  				}
  			} );
    }
}
