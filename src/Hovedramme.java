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
    private double skjermbredde;
    private double skjermhøyde;
    private int bredde;
    private int høyde;
    private AdminPanel adminpanel;
    private TreeMap<String,Pasient> pasientliste;
    private TreeMap<String,Lege> legeliste;
    private TreeMap<Integer,Resept> reseptliste;
    private TreeMap<Integer,Kontrollør> kontrollørliste;
    
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
        reseptliste = new TreeMap<>();
        //legeliste = new TreeMap<>();
        lesAdminVindu();
        //lesListene();
        setTitle("Norsk Reseptregister");
        super.getContentPane().setLayout(new CardLayout());
        super.add(new LogginnPanel(pasientliste, legeliste, reseptliste,
                kontrollørliste),LOGG_INN);
        super.add(new AdminPanel(legeliste,kontrollørliste),ADMIN);
        setVisible(true);
    }
    public void visPanel(String panelid){
        //Metode som kan be hovedrammen om å vise et ønskelig panel
        CardLayout c = (CardLayout)super.getContentPane().getLayout();
        c.show(super.getContentPane(),panelid);
    }
    
    public void visFørste(){
        //Metode som viser hovedrammens første vindu, dvs, logginnvindu
        CardLayout c = (CardLayout)super.getContentPane().getLayout();
        c.first(super.getContentPane());
    }
    
    public void visFørsteLege(TreeMap<String,Pasient> nypasientliste,
            TreeMap<Integer,Resept> nyreseptliste){
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
    
    private void lesListene(){
        /*Metode som Leser lister og static konstantene som blir brukt for
        å opprette objekter i listene, misslykkes lesningen opprettes nye 
        lister*/
       
        try(ObjectInputStream innfil = new ObjectInputStream(
                new FileInputStream("src/listene.data"))){
            kontrollørliste = (TreeMap<Integer,Kontrollør>) innfil.readObject();
        }
        catch(ClassNotFoundException cnfe){
            JOptionPane.showMessageDialog(null,cnfe.getMessage() + 
                    "\nOppretter en tom liste\n");
            kontrollørliste = new TreeMap<>();
        }
        catch(FileNotFoundException fnfe){
            JOptionPane.showMessageDialog(null,
                    "Finner ikke datafil. Oppretter tom liste");
            kontrollørliste = new TreeMap<>();
        }
        catch(IOException ioe){
            JOptionPane.showMessageDialog(null,
                    "Innlesningsfeil. Oppretter nye tomme lister");
            kontrollørliste = new TreeMap<>();
        }
    }
    
    public void lesAdminVindu(){
        try(ObjectInputStream innfil = new ObjectInputStream(
                new FileInputStream("src/listene.data"))){
            adminpanel = (AdminPanel) innfil.readObject();
        }
        catch(ClassNotFoundException cnfe){
            JOptionPane.showMessageDialog(null,cnfe.getMessage() + 
                    "\nOppretter en tom liste\n");
            kontrollørliste = new TreeMap<>();
        }
        catch(FileNotFoundException fnfe){
            JOptionPane.showMessageDialog(null,
                    "Finner ikke datafil. Oppretter tom liste");
            kontrollørliste = new TreeMap<>();
        }
        catch(IOException ioe){
            JOptionPane.showMessageDialog(null,
                    "Innlesningsfeil. Oppretter nye tomme lister");
            kontrollørliste = new TreeMap<>();
        }
    }
    
    public void lagreAdminVindu(){
        try(ObjectOutputStream utfil = new ObjectOutputStream(
                new FileOutputStream("src/listene.data"))){
            utfil.writeObject(kontrollørliste);
        }
        catch(NotSerializableException ns){
            JOptionPane.showMessageDialog(null,"Objektet er ikke serialisert");
        }
        catch(IOException ioe){
            JOptionPane.showMessageDialog(null,"Problem med utskrift til fil");
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
