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
    private JPanel panelet;
    private Border border;
    private JButton reglege, regpasient, regresept;
    private RegistervinduLytter lytteren = new RegistervinduLytter();
    
    public RegistreringsPanel(){
        panelet = new JPanel(new GridLayout(3,1,5,5));
        border = BorderFactory.createTitledBorder("Registering");
        setBorder(border);
        
        Icon reglegeikon = new ImageIcon(getClass().getResource( "bildefiler/ModelS.gif" ));
        reglege = new JButton("Lege", reglegeikon);
        reglege.setVerticalTextPosition( AbstractButton.BOTTOM );
        reglege.setHorizontalTextPosition( AbstractButton.CENTER );
        reglege.addActionListener(lytteren);
        panelet.add(reglege);
        
        Icon regpasientikon = new ImageIcon(getClass().getResource( "bildefiler/ModelS.gif" ));
        regpasient = new JButton("Lege", regpasientikon);
        regpasient.setVerticalTextPosition( AbstractButton.BOTTOM );
        regpasient.setHorizontalTextPosition( AbstractButton.CENTER );
        regpasient.addActionListener(lytteren);
        panelet.add(regpasient);
        
        Icon regreseptikon = new ImageIcon(getClass().getResource( "bildefiler/ModelS.gif" ));
        regresept = new JButton("Lege", regreseptikon);
        regresept.setVerticalTextPosition( AbstractButton.BOTTOM );
        regresept.setHorizontalTextPosition( AbstractButton.CENTER );
        regresept.addActionListener(lytteren);
        panelet.add(regresept);
        super.add(panelet);
    }
    
    private class RegistervinduLytter implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            /*if (e.getSource()) {

            }*/
        }
    }
}
