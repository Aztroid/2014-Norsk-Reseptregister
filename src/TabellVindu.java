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
    private TreeMap<String,String> medisinliste;
    private LegeTabellmodell modellen;
    final JTable tabellen;
    private final String[] kolonnenavnlege = {"Dato", "Reseptnr.", "Personnr.", 
        "Lege(Autnr.)", "Medisin(ACTnr.)", "Mengde", "DDD", "Kategori", 
        "Reseptgruppe"};
    private final String[] kolonnenavnmedisin = {"Dato", "Reseptnr.", "Personnr.", 
        "Lege(Autnr.)", "Medisin(ACTnr.)", "Mengde", "DDD", "Kategori", 
        "Reseptgruppe"};
    
    public TabellVindu(TreeMap<Integer,Resept> reseptliste){
        super(new GridLayout(0,1));
        this.reseptliste = reseptliste;
        modellen = new LegeTabellmodell(kolonnenavnlege,this.reseptliste);
        tabellen = new JTable(modellen);
        tabellen.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tabellen.setFillsViewportHeight(true);
        
        JScrollPane skrollefeltet = new JScrollPane(tabellen);
        add(skrollefeltet);
    }
    
    public void nyInnData(TreeMap<Integer,Resept> nyliste){
        modellen.nyInnData(nyliste);
        modellen.fireTableDataChanged();
    }
}
