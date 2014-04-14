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
import javax.swing.border.Border;

public class LogginnPanel extends JPanel implements ActionListener{
    private JButton lege, kontrollør;
    private Border border;
    
    public LogginnPanel(){
        //Setter layouten på hovedpanelet
        super(new GridLayout(2,1,5,5));
        border = BorderFactory.createTitledBorder("Logg inn");
        setBorder(border);
        
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
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == lege){
            //Legeloggin
        }
        else{
            //Kontrollør Logginn
        }
    }
}
