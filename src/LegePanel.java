/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class LegePanel extends JPanel{
    
    private Lytter lytteren;
    
    //Sidepanel datafelter
    private JPanel sidepanel;
    private JTextArea infofelt;
    private Border sidepanelgrense;
    private JScrollPane infoscroll;
    
    //Senterpanel datafelter
    private JPanel senterpanel;
    private Border senterpanelgrense;
    private JButton regpasient, reglege,regresept;
    
    public LegePanel(){
        super.setLayout(new BorderLayout());
        
        //SIDEPANEL
        sidepanel = new JPanel();
        sidepanel.setLayout(new BorderLayout());
        sidepanelgrense = BorderFactory.createTitledBorder("Navigering2");
        sidepanel.setBorder(sidepanelgrense);
        infofelt = new JTextArea(6,20);
        infoscroll = new JScrollPane(infofelt);
        infofelt.setText("Venligs velg rolle");
        
        infofelt.setEditable(false);
        sidepanel.add(infoscroll, BorderLayout.PAGE_START);
        
        //SENTERPANEL
        senterpanel = new JPanel();
        senterpanel.setLayout(new GridLayout(3,1,5,5));
        senterpanelgrense = BorderFactory.createTitledBorder("Logg inn2");
        senterpanel.setBorder(senterpanelgrense);
        
        //SENTERPANEL knapper:
        Icon legeikon = new ImageIcon(getClass().getResource("bildefiler/knapp1_lege.gif"));
        regpasient = new JButton("Ny Pasient", legeikon);
        regpasient.setVerticalTextPosition( AbstractButton.BOTTOM );
        regpasient.setHorizontalTextPosition( AbstractButton.CENTER );
        regpasient.addActionListener(lytteren);
        senterpanel.add(regpasient);
        
        Icon kontrollørikon = new ImageIcon(getClass().getResource
            ("bildefiler/knapp2_kontrolloer.gif"));
        reglege = new JButton("Ny Lege", kontrollørikon);
        reglege.setVerticalTextPosition( AbstractButton.BOTTOM );
        reglege.setHorizontalTextPosition( AbstractButton.CENTER );
        reglege.addActionListener(lytteren);
        senterpanel.add(reglege);
        
        regresept = new JButton("Ny Resept", kontrollørikon);
        regresept.setVerticalTextPosition( AbstractButton.BOTTOM );
        regresept.setHorizontalTextPosition( AbstractButton.CENTER );
        regresept.addActionListener(lytteren);
        senterpanel.add(regresept);
        
        super.add(sidepanel, BorderLayout.LINE_START);
        super.add(senterpanel, BorderLayout.CENTER);
    }
    
    private class Lytter implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==regresept){
                //fjernalt();
            }
        }
    }
}
