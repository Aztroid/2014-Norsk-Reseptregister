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
    private final String LOGG_INN = "0";
    private JPanel containerpanel;
    private double skjermbredde;
    private double skjermhøyde;
    private int bredde;
    private int høyde;
   
    
    public Hovedramme(){
        
        //Oppretter Toolkit for å kunne justere programmet autmatisk
        Toolkit verktøykasse = Toolkit.getDefaultToolkit();
        Dimension skjermdimensjon = verktøykasse.getScreenSize();
        skjermbredde = skjermdimensjon.width/1.5;
        skjermhøyde = skjermdimensjon.height/1.3;
        bredde = (int)skjermbredde;
        høyde = (int)skjermhøyde;
       
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
        super.getContentPane().setLayout(new CardLayout());
        super.add(new LogginnPanel(),LOGG_INN);
        CardLayout cl = (CardLayout)super.getContentPane().getLayout();
        cl.show(super.getContentPane(),LOGG_INN);
        setVisible(true);
    }
}
