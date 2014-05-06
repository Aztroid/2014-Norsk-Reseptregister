/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA*/

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

//Denne klassen er hovedrammen, det er her alle paneler blir lagt til

public class Hovedramme extends JFrame{
    //Hovedrammens datafelter
    private final String LOGG_INN = "0";
    private final String ADMIN = "1";
    private final int KONTROLLØR = 1;
    private final int LEGE = 2;
    private final int PASIENT = 3;
    private final int RESEPT = 4;
    private double skjermbredde;
    private double skjermhøyde;
    private int bredde;
    private int høyde;
    private TreeMap<String,Pasient> pasientliste;
    private TreeMap<String,Lege> legeliste;
    private TreeMap<Integer,Resept> reseptliste;
    private TreeMap<Integer,Kontrollør> kontrollørliste;
    private static Integer sisteresept;
    private static Integer sistekontrollør;
    
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
        lesListene(KONTROLLØR);
        lesListene(LEGE);
        lesListene(PASIENT);
        lesListene(RESEPT);
        setTitle("Norsk Reseptregister");
        super.getContentPane().setLayout(new CardLayout());
        super.add(new LogginnPanel(sisteresept, pasientliste, legeliste, 
                reseptliste, kontrollørliste),LOGG_INN);
        super.add(new AdminPanel(sistekontrollør,legeliste,kontrollørliste),
                ADMIN);
        setVisible(true);
    }
    
    public void visPanel(String panelid){
        //Metode som kan be hovedrammen om å vise et ønskelig panel
        CardLayout c = (CardLayout)super.getContentPane().getLayout();
        c.show(super.getContentPane(),panelid);
    }
    
    public void visAdmin(){
        //Metode som kan be hovedrammen om å vise et ønskelig panel
        CardLayout c = (CardLayout)super.getContentPane().getLayout();
        c.show(super.getContentPane(),ADMIN);
    }
    
    public void visFørsteKontrollør(TreeMap<String,Lege> nylegeliste){
        //Metode som viser hovedrammens første vindu, dvs, logginnvindu
        CardLayout c = (CardLayout)super.getContentPane().getLayout();
        c.first(super.getContentPane());
    }
    
    public void visFørsteLege(TreeMap<String,Pasient> nypasientliste,
            TreeMap<Integer,Resept> nyreseptliste){
        if(nypasientliste.isEmpty()||nyreseptliste.isEmpty()||
                nypasientliste==null||nyreseptliste==null){
            CardLayout c = (CardLayout)super.getContentPane().getLayout();
            c.first(super.getContentPane());
            return;
        }
        sisteresept = reseptliste.lastKey();
        pasientliste = nypasientliste;
        reseptliste = nyreseptliste;
        //Metode som viser hovedrammens første vindu, dvs, logginnvindu
        CardLayout c = (CardLayout)super.getContentPane().getLayout();
        c.first(super.getContentPane());
    }
    
    public void visFørsteAdmin(TreeMap<String,Lege> nylegeliste,
            TreeMap<Integer,Kontrollør> nykontrollørliste){
        legeliste = nylegeliste;
        kontrollørliste = nykontrollørliste;
        //Metode som viser hovedrammens første vindu, dvs, logginnvindu
        CardLayout c = (CardLayout)super.getContentPane().getLayout();
        c.first(super.getContentPane());
    }
    
    public void visFørsteKont(){
        //Metode som viser hovedrammens første vindu, dvs, logginnvindu
        CardLayout c = (CardLayout)super.getContentPane().getLayout();
        c.first(super.getContentPane());
    }
    
    private void lesListene(int n){
        /*Metode som Leser lister og static konstantene som blir brukt for
        å opprette objekter i listene, misslykkes lesningen opprettes nye 
        lister*/
        if(n==KONTROLLØR){
            try(ObjectInputStream innfil = new ObjectInputStream(
                new FileInputStream("src/kontrollørliste.data"))){
            kontrollørliste = (TreeMap<Integer,Kontrollør>) innfil.readObject();
            sistekontrollør = kontrollørliste.lastKey();
            }
            catch(ClassNotFoundException cnfe){
                JOptionPane.showMessageDialog(null,cnfe.getMessage() + 
                    "\nOppretter en tom liste\n");
                kontrollørliste = new TreeMap<>();
                sistekontrollør = 999;
            }
            catch(FileNotFoundException fnfe){
                JOptionPane.showMessageDialog(null,
                        "Finner ikke datafil. Oppretter tom Kontrollørliste");
                kontrollørliste = new TreeMap<>();
                sistekontrollør = 999;
            }
            catch(IOException ioe){
                JOptionPane.showMessageDialog(null,
                        "Innlesningsfeil. Oppretter tom Kontrollørliste");
                kontrollørliste = new TreeMap<>();
                sistekontrollør = 999;
            }
        }
        else if(n==LEGE){
            try(ObjectInputStream innfil = new ObjectInputStream(
                new FileInputStream("src/legeliste.data"))){
            legeliste = (TreeMap<String,Lege>) innfil.readObject();
            }
            catch(ClassNotFoundException cnfe){
                JOptionPane.showMessageDialog(null,cnfe.getMessage() + 
                    "\nOppretter en tom liste\n");
                legeliste = new TreeMap<>();
            }
            catch(FileNotFoundException fnfe){
                JOptionPane.showMessageDialog(null,
                        "Finner ikke datafil. Oppretter tom Legeliste");
                legeliste = new TreeMap<>();
            }
            catch(IOException ioe){
                JOptionPane.showMessageDialog(null,
                        "Innlesningsfeil. Oppretter tom Legeliste");
                legeliste = new TreeMap<>();
            }
        }
        else if(n==PASIENT){
            try(ObjectInputStream innfil = new ObjectInputStream(
                new FileInputStream("src/pasientliste.data"))){
            pasientliste = (TreeMap<String,Pasient>) innfil.readObject();
            }
            catch(ClassNotFoundException cnfe){
                JOptionPane.showMessageDialog(null,cnfe.getMessage() + 
                    "\nOppretter en tom liste\n");
                pasientliste = new TreeMap<>();
            }
            catch(FileNotFoundException fnfe){
                JOptionPane.showMessageDialog(null,
                        "Finner ikke datafil. Oppretter tom Pasientliste");
                pasientliste = new TreeMap<>();
            }
            catch(IOException ioe){
                JOptionPane.showMessageDialog(null,
                        "Innlesningsfeil. Oppretter tom Pasientliste");
                pasientliste = new TreeMap<>();
            }
        }
        else if(n==RESEPT){
            try(ObjectInputStream innfil = new ObjectInputStream(
                new FileInputStream("src/reseptliste.data"))){
            reseptliste = (TreeMap<Integer,Resept>) innfil.readObject();
            sisteresept = reseptliste.lastKey();
            }
            catch(ClassNotFoundException cnfe){
                JOptionPane.showMessageDialog(null,cnfe.getMessage() + 
                    "\nOppretter en tom liste\n");
                reseptliste = new TreeMap<>();
                sisteresept = 0;
            }
            catch(FileNotFoundException fnfe){
                JOptionPane.showMessageDialog(null,
                        "Finner ikke datafil. Oppretter tom Reseptliste");
                reseptliste = new TreeMap<>();
                sisteresept = 0;
            }
            catch(IOException ioe){
                JOptionPane.showMessageDialog(null,
                        "Innlesningsfeil. Oppretter tom Reseptliste");
                reseptliste = new TreeMap<>();
                sisteresept = 0;
            }
        }
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
}//End of Class Hovedramme
