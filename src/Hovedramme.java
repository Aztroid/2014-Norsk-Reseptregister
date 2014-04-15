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
    private Container hovedcontainer;
    
    //Sidepanelets datafelter
    private JPanel sidepanel;
    private SidePanelLytteren sidelytter;
    private Border sidepanelgrense;
    private JTextArea sidepanelinfofelt;
    private JButton logginn, skriftbruker,visdatakont;
    
    //Logginn Panel
    private JPanel logginnvindu;
    private LogginnLytter logglytter;
    private JButton lege, kontrollør;
    private Border logginngrense;
    private RegistreringsPanel registervindu;
    
    //DataPanel
    private JPanel data;
    
    //RegisterPanel
    private JPanel reg;
    
    public Hovedramme(){
        
        //Oppretter Toolkit for å kunne justere programmet autmatisk
        Toolkit verktøykasse = Toolkit.getDefaultToolkit();
        Dimension skjermdimensjon = verktøykasse.getScreenSize();
        double skjermbredde = skjermdimensjon.width/1.5;
        double skjermhøyde = skjermdimensjon.height/1.3;
        int bredde = (int)skjermbredde;
        int høyde = (int)skjermhøyde;
        hovedcontainer = this.getContentPane();
       
        /*Setter rammens bredde og høyde, lar plattformen velge plassering av
          rammen*/
        setSize(bredde,høyde); //Fullskjerm/2
        setLocationRelativeTo(null);//Midstiling
        hovedcontainer.setLayout(new BorderLayout());
        
        //Legger til logoen i venstre hjørnet
        String bildefil = "bildefiler/ModelS.gif";
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
        
        logginn = new JButton("Logg inn");
        logginn.addActionListener(sidelytter);
        sidepanel.add(logginn);
        
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
        Icon legeikon = new ImageIcon(getClass().getResource( "bildefiler/ModelS.gif" ));
        lege = new JButton("Lege", legeikon);
        lege.setVerticalTextPosition( AbstractButton.BOTTOM );
        lege.setHorizontalTextPosition( AbstractButton.CENTER );
        lege.addActionListener(logglytter);
        
        Icon kontrollørikon = new ImageIcon(getClass().getResource( "bildefiler/ModelS.gif" ));
        kontrollør = new JButton("Kontrollør", kontrollørikon);
        kontrollør.setVerticalTextPosition( AbstractButton.BOTTOM );
        kontrollør.setHorizontalTextPosition( AbstractButton.CENTER );
        kontrollør.addActionListener(logglytter);
        
        logginnvindu.add(lege);
        logginnvindu.add(kontrollør);
        
        //Initialiserer register og visdata panel
        reg = new JPanel();
        
        //Legger til SidePanelet i hovedrammen
        hovedcontainer.add(sidepanel, BorderLayout.LINE_START);
        sidepanelinfofelt.setText("Klikk Logg inn for å\nstarte");
    }
    
    public void LeggTilLoggInn(){
        hovedcontainer.add(logginnvindu, BorderLayout.CENTER);
        logginn.setVisible(false);
        skriftbruker.setVisible(true);
        sidepanelinfofelt.setText("Velg hva du ønsker\nlogge inn som");
        invalidate();
        validate();
    }
    
    public void visDataPanel(){
        //Kun Lege har tilgang til dette vinduet fra hovedrammen
    }
    
    public void visRegistreringsPanel(){
        //Kontrolløren kommer rett inn hit
        hovedcontainer.add(reg, BorderLayout.CENTER);
        visdatakont.setVisible(true);
        invalidate();
        validate();
    }
    
    private class SidePanelLytteren implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == logginn){
                LeggTilLoggInn();
            }
            else if(e.getSource() == skriftbruker){
                
            }
        }
    }
    
    private class LogginnLytter implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == lege) {
                visDataPanel();
            }
            else{
                visRegistreringsPanel();
            }
        }
    }
}
