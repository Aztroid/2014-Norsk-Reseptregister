/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.table.*;

public class TabellVindu extends JPanel{
    private TreeMap<Integer,Resept> reseptliste;
    private TreeMap<String,Pasient> personliste;
    private TreeMap<String,Lege> legeliste;
    private TreeMap<String,String> medisinliste;
    private JTable tabellen;
    
    public TabellVindu(){
        super(new GridLayout(0,1));
        tabellen = new JTable();
        tabellen.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tabellen.setFillsViewportHeight(true);
        JScrollPane skrollefeltet = new JScrollPane(tabellen);
        add(skrollefeltet);
    }
    
    public void nyInnDataResept(TreeMap<Integer,Resept> nyliste){
        reseptliste = nyliste;
        ReseptTabellModell nymodell = new ReseptTabellModell(reseptliste);
        tabellen.setModel(nymodell);
    }
    
    public void nyInnDataPasient(TreeMap<String,Pasient> nyliste){
        personliste = nyliste;
        PasientTabellmodell nymodell = new PasientTabellmodell(personliste);
        tabellen.setModel(nymodell);
    }
    
    public void nyInnDataLege(TreeMap<String,Lege> nyliste){
        legeliste = nyliste;
        LegeTabellmodell nymodell = new LegeTabellmodell(legeliste);
        tabellen.setModel(nymodell);
    }
}
