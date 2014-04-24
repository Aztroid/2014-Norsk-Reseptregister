/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AAVegar 
 Nygård, s193362, HIINGDATA13H1AA
 */

//Dette vinduet er vinduet hvor kontrolløren velger mellom 
//registering og datavisning

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class KontrollørLogginnPanel extends JPanel{
    private final String LOGG_INN = "0";
    private Lytter lytteren;
    private Hovedramme hovedrammekopi;
    private TreeMap<String,Pasient> pasientliste;
    private TreeMap<String,Lege> legeliste;
    private TreeMap<Integer,Resept> reseptliste;
    
    //Sidepanel datafelter
    private JPanel sidepanel;
    private JTextArea infofelt;
    private Border sidepanelgrense;
    private JScrollPane infoscroll;
    private JButton skiftbruker;
    
    //Senterpanel datafelter
    private JPanel senterpanel;
    private Border senterpanelgrense;
    private JButton regpasient, reglege, regresept;
    
    public KontrollørLogginnPanel(){
        super.setLayout(new BorderLayout());
        
        //SIDEPANEL
        sidepanel = new JPanel();
        sidepanel.setLayout(new BorderLayout());
        sidepanelgrense = BorderFactory.createTitledBorder("Navigering");
        sidepanel.setBorder(sidepanelgrense);
        infofelt = new JTextArea(6,20);
        infoscroll = new JScrollPane(infofelt);
        infofelt.setText("Oversikt over dine resepter");
        
        infofelt.setEditable(false);
        sidepanel.add(infoscroll, BorderLayout.PAGE_START);
        
        //SIDEPANELSKNAPPER
        skiftbruker = new JButton("Skift Bruker");
        skiftbruker.setVerticalTextPosition( AbstractButton.BOTTOM );
        skiftbruker.setHorizontalTextPosition( AbstractButton.CENTER );
        skiftbruker.addActionListener(lytteren);
        sidepanel.add(skiftbruker);
        
        //SENTERPANEL
        senterpanel = new JPanel();
        senterpanel.setLayout(new BorderLayout());
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
    
    public void skiftbruker(){
        hovedrammekopi.visFørste();
    }
    
    private class Lytter implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==skiftbruker){
                skiftbruker();
            }
        }
    }
}