/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA
 */

//Dette panelet er vinduet hvor legen får se oversikt over sine pasienter

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class LegePanel extends JPanel{
    private final String LOGG_INN = "0";
    private Lytter lytteren;
    
    //Sidepanel datafelter
    private JPanel sidepanel;
    private JTextArea infofelt;
    private Border sidepanelgrense;
    private JScrollPane infoscroll;
    private JButton skiftbruker;
    
    //Senterpanel datafelter
    private JPanel senterpanel;
    private TabellVindu tabellen;
    private Border senterpanelgrense;
    private JButton søkpasient;
    
    public LegePanel(){
        super.setLayout(new BorderLayout());
        lytteren = new Lytter();
        
        //SIDEPANEL ramme
        sidepanel = new JPanel();
        sidepanel.setLayout(new GridLayout(8,1,3,3));
        sidepanelgrense = BorderFactory.createTitledBorder("Navigering");
        sidepanel.setBorder(sidepanelgrense);
        
        //SIDEPANEL infofelt
        infofelt = new JTextArea(4,18);
        infoscroll = new JScrollPane(infofelt);
        infofelt.setText("Oversikt over dine resepter");
        
        infofelt.setEditable(false);
        sidepanel.add(infoscroll);
        
        //SIDEPANEL knapper
        skiftbruker = new JButton("Skift Bruker");
        skiftbruker.addActionListener(lytteren);
        sidepanel.add(skiftbruker);
        
        //SENTERPANEL ramme
        senterpanel = new JPanel();
        senterpanel.setLayout(new BorderLayout());
        senterpanelgrense = BorderFactory.createTitledBorder("Reseptoversikt");
        senterpanel.setBorder(senterpanelgrense);
        
        //SENTERPANEL knapper:
        Icon kontrollørikon = new ImageIcon(getClass().getResource
            ("bildefiler/knapp2_kontrolloer.gif"));
        søkpasient = new JButton("Kontrollør", kontrollørikon);
        søkpasient.setVerticalTextPosition( AbstractButton.BOTTOM );
        søkpasient.setHorizontalTextPosition( AbstractButton.CENTER );
        søkpasient.addActionListener(lytteren);
        senterpanel.add(søkpasient);

        //SENTERPANEL tabell
        
        
        super.add(sidepanel, BorderLayout.LINE_START);
        super.add(senterpanel, BorderLayout.CENTER);
    }
    
    public void skiftbruker(){
        JFrame hovedrammen = (JFrame) SwingUtilities.getWindowAncestor(this);
        hovedrammen.add(new LogginnPanel(),LOGG_INN);
        CardLayout c = (CardLayout)hovedrammen.getContentPane().getLayout();
        c.show(hovedrammen.getContentPane(),LOGG_INN);
    }
    
    private class Lytter implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==skiftbruker){
                skiftbruker();
            }
        }
    }
}
