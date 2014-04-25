/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA*/

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
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
    private TreeMap<String,Pasient> pasientliste;
    private TreeMap<String,Lege> legeliste;
    private TreeMap<Integer,Resept> reseptliste;
    
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
        pasientliste = new TreeMap<>();
        legeliste = new TreeMap<>();
        reseptliste = new TreeMap<>();
        setTitle("Norsk Reseptregister");
        super.getContentPane().setLayout(new CardLayout());
        super.add(new LogginnPanel(pasientliste, legeliste,reseptliste),
                LOGG_INN);
        visPanel(LOGG_INN);
        setVisible(true);
    }
    public void visPanel(String panelid){
        CardLayout c = (CardLayout)super.getContentPane().getLayout();
        c.show(super.getContentPane(),panelid);
        System.out.println(c.toString());
    }
    
    public void visFørste(){
        CardLayout c = (CardLayout)super.getContentPane().getLayout();
        c.first(super.getContentPane());
    }
    
    public TreeMap<String,Pasient> getPasientliste(){
        return pasientliste;
    }
    
    public TreeMap<String,Lege> getLegeliste(){
        return legeliste;
    }
    
    public TreeMap<Integer,Resept> getReseptliste(){
        return reseptliste;
    }
}
