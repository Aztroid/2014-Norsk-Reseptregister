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
    private Border sidepanelborder,infoborder;
    private JTextArea sidepanelinfofelt;
    private JButton logginn, skriftbruker;
    
    //CENTER Panel
    private LogginnPanel logginnvindu;
    
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
        sidepanelborder = BorderFactory.createTitledBorder("Navigering");
        sidepanel.setBorder(sidepanelborder);
        sidelytter = new SidePanelLytteren();
        
        logginn = new JButton("Logg inn");
        logginn.addActionListener(sidelytter);
        sidepanel.add(logginn);
        
        skriftbruker = new JButton("Skrift Bruker");
        skriftbruker.addActionListener(sidelytter);
        skriftbruker.setVisible(false);
        sidepanel.add(skriftbruker);
        
        sidepanel.add(new JLabel("Informasjon"));
        sidepanelinfofelt = new JTextArea(20,12);
        sidepanelinfofelt.setEditable(false);
        sidepanel.add(sidepanelinfofelt);
        
        //Initialiserer Paleler som blir lagt til
        logginnvindu = new LogginnPanel();
        
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
    
    private class SidePanelLytteren implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == logginn||e.getSource() ==skriftbruker) {
                LeggTilLoggInn();
            }
        }
    }
}
