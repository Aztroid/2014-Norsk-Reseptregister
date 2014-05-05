/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class Tabellmodell extends AbstractTableModel{
    
    private TreeMap<Integer,Resept> listen;
    private final String[] kolonnenavn = {"Dato", "Reseptnr.", "Personnr.", 
        "Lege(Autnr.)", "Medisin(ACTnr.)", "Mengde", "DDD", "Kategori", 
        "Reseptgruppe"};
    
    private LinkedList<Resept> data;
    private static int linjeteller = 0;
    
    public Tabellmodell(TreeMap<Integer,Resept> listen){
        this.listen = listen;
        data = new LinkedList<>(listen.values());
    }
    
    public void nyInnData(TreeMap<Integer,Resept> nyliste){
        this.listen = nyliste;
        data = new LinkedList<>(listen.values());
    }
    
    public String getColonName(int kol){
        return kolonnenavn[kol];
    }
    
    public Resept getRow(int red){
        return data.get(red);
    }
    
    public int getColumnCount(){
        return kolonnenavn.length;
    }
    
    public int getRowCount(){
        return data.size();
    }
    
    public Object getValueAt(int rad, int kol) {
        Resept resepten = data.get(rad);
        Object[] linjen = resepten.getTabelllinje();
        switch(kol){
            case 0: return linjen[0];//Dato
            case 1: return linjen[1];//Reseptnr
            case 2: return linjen[2];//Lege autnr
            case 3: return linjen[3];//Pasient fnr
            case 4: return linjen[4];//Medisin(ACTnr)
            case 5: return linjen[5];//Mengde
            case 6: return linjen[6];//DDD
            case 7: return linjen[7];//Kategori
            case 8: return linjen[8];//Reseptgruppe
            default: return null;
        }  
    }
}
