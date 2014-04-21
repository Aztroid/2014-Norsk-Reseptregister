/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA*/

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;
import javax.swing.border.*;

public class Hovedramme extends JFrame{
    //Hovedrammens datafelter
    private JPanel hovedpanel;
    
    //Sidepanelets datafelter
    private JPanel sidepanel;
    private SidePanelLytteren sidelytter;
    private Border sidepanelgrense;
    private JTextArea sidepanelinfofelt;
    private JButton skriftbruker,visdatakont;
    
    //Logginn Panel
    private JPanel logginnvindu;
    private LogginnLytter logglytter;
    private JButton lege, kontrollør;
    private Border logginngrense;
    private RegistreringsPanel registervindu;
    
    //DataPanelr
    private JPanel data;
    
    //RegisterPanel
    private RegistreringsPanel reg;
    
    public Hovedramme(){
        
        //Oppretter Toolkit for å kunne justere programmet autmatisk
        Toolkit verktøykasse = Toolkit.getDefaultToolkit();
        Dimension skjermdimensjon = verktøykasse.getScreenSize();
        double skjermbredde = skjermdimensjon.width/1.5;
        double skjermhøyde = skjermdimensjon.height/1.3;
        int bredde = (int)skjermbredde;
        int høyde = (int)skjermhøyde;
        hovedpanel = new JPanel(new BorderLayout());
       
        /*Setter rammens bredde og høyde, lar plattformen velge plassering av
          rammen*/
        setSize(bredde,høyde); //Fullskjerm/2
        setLocationRelativeTo(null);//Midstiling
        
        //Legger til logoen i venstre hjørnet
        String bildefil = "bildefiler/logo.gif";
        URL kilde = Hovedramme.class.getResource(bildefil);
        if(kilde != null){
            ImageIcon bilde = new ImageIcon(kilde);
            Image ikon = bilde.getImage();
            setIconImage(ikon);
        }
        setTitle("Norsk Reseptregister");
        
        //Oppretter SidePanelet
        /*FIKS SLIK AT KAPPENE FÅR SENTRERT TEKST OG AT ALLE HAR STØRELSEN
          TIL DEN STØRSTE KNAPPEN*/
        
        sidepanel = new JPanel(new GridLayout(20,1,5,5));
        sidepanelgrense = BorderFactory.createTitledBorder("Navigering");
        sidepanel.setBorder(sidepanelgrense);
        sidelytter = new SidePanelLytteren();
        
        skriftbruker = new JButton("Skrift Bruker");
        skriftbruker.addActionListener(sidelytter);
        skriftbruker.setVisible(false);
        sidepanel.add(skriftbruker);
        
        visdatakont = new JButton("Reseptoversikt");
        visdatakont.addActionListener(sidelytter);
        visdatakont.setVisible(false);
        sidepanel.add(visdatakont);
        
        sidepanel.add(new JLabel("Informasjon"));
        sidepanelinfofelt = new JTextArea(20,12);
        sidepanelinfofelt.setEditable(false);
        sidepanel.add(sidepanelinfofelt);
        
        //Initialiserer Logginnpanelet
        logginnvindu = new JPanel();
        logginnvindu.setLayout(new GridLayout(2,1,5,5));
        logginngrense = BorderFactory.createTitledBorder("Logg inn");
        logginnvindu.setBorder(logginngrense);
        logglytter = new LogginnLytter();
        
        //Legger til knappene med ikoner på
        Icon legeikon = new ImageIcon(getClass().getResource("bildefiler/knapp1_lege.gif"));
        lege = new JButton("Lege", legeikon);
        lege.setVerticalTextPosition( AbstractButton.BOTTOM );
        lege.setHorizontalTextPosition( AbstractButton.CENTER );
        lege.addActionListener(logglytter);
        
        Icon kontrollørikon = new ImageIcon(getClass().getResource("bildefiler/knapp2_kontrolloer.gif"));
        kontrollør = new JButton("Kontrollør", kontrollørikon);
        kontrollør.setVerticalTextPosition( AbstractButton.BOTTOM );
        kontrollør.setHorizontalTextPosition( AbstractButton.CENTER );
        kontrollør.addActionListener(logglytter);
        
        logginnvindu.add(lege);
        logginnvindu.add(kontrollør);
        
        //Initialiserer register og visdata panel
        reg = new RegistreringsPanel();
        
        //Legger til SidePanelet i hovedrammen
        hovedpanel.add(sidepanel, BorderLayout.LINE_START);
        hovedpanel.add(reg, BorderLayout.CENTER);
        reg.setVisible(false);
        hovedpanel.add(logginnvindu, BorderLayout.CENTER);
        sidepanelinfofelt.setText("Venligst Logg inn");
        super.add(hovedpanel);
    }
    public void getCenter(){
        //return hovedpanel.(BorderLayout());
        return;
    }
    
    public void skiftBruker(){
        logginnvindu.setVisible(true);
    }
    
    public void visDataPanel(){
        //Kun Lege har tilgang til dette vinduet fra hovedrammen
    }
    
    public void visRegistreringsPanel(){
        //Kontrolløren kommer rett inn hitrr
        logginnvindu.setVisible(false);
        hovedpanel.invalidate();
        hovedpanel.validate();
        reg.setVisible(true);
        skriftbruker.setVisible(true);
        visdatakont.setVisible(true);
    }
    
    private class SidePanelLytteren implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == skriftbruker){
                skiftBruker();
            }
        }
    }
    
    private class LogginnLytter implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == lege) {
                visDataPanel();
            }
            else if(e.getSource() == kontrollør){
                visRegistreringsPanel();
            }
        }
    }
}
