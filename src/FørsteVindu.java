/*
Hovedprosjekt Dats-1600
William B. Wold, s183670, HIINGDATA13H1AA
Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
Vegar Nygård, s193362, HIINGDATA13H1AA
*/

//Denne klassen skal konstrusere det første panelet i hovedrammen

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class FørsteVindu extends JPanel implements ActionListener{
    private JButton lege, kontrollør;
    
    public FørsteVindu(){
        //Setter layouten på panelet
        super(new GridLayout(2,2,10,10));
        
        //Legger til knappene med ikoner på
        Icon legeikon = new ImageIcon(getClass().getResource( "bildefiler/ModelS.gif" ));
        lege = new JButton("Lege", legeikon);
        lege.setVerticalTextPosition( AbstractButton.BOTTOM );
        lege.setHorizontalTextPosition( AbstractButton.CENTER );
        lege.addActionListener(this);
        
        Icon kontrollørikon = new ImageIcon(getClass().getResource( "bildefiler/ModelS.gif" ));
        kontrollør = new JButton("Kontrollør", kontrollørikon);
        kontrollør.setVerticalTextPosition( AbstractButton.BOTTOM );
        kontrollør.setHorizontalTextPosition( AbstractButton.CENTER );
        kontrollør.addActionListener(this);
        
        add(lege);
        add(kontrollør);
    }
    
    public void ValgLege(){
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == lege){
            //Legeloggin
            super.setLayout(new FlowLayout());
            super.remove(lege);
            super.invalidate();
            super.validate();
        }
        else{
            //Kontrollør Logginn
            super.remove(kontrollør);
            super.invalidate();
            super.validate();
        }
    }
}
