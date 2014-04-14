/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA*/

/*Denne klassen skal definere Panelet som dukker opp når man skal
  registrere nye resepter*/

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class RegistreringsPanel extends JPanel{
    private Border border;
    
    public RegistreringsPanel(){
        super(new GridLayout(2,1,5,5));
        border = BorderFactory.createTitledBorder("Registering");
        setBorder(border);
    }
    
    private class RegistervinduLytter implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            /*if (e.getSource()) {

            }*/
        }
    }
}
