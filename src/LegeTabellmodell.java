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

public class LegeTabellmodell extends FellesTabellmodell{
    
    private TreeMap<Integer,Resept> listen;
    private LinkedList<Resept> data;
    
    public LegeTabellmodell(String[] kol, TreeMap<Integer,Resept> listen){
        super(kol);
        data = new LinkedList<Resept>(listen.values());
    }
    
    public void nyInnData(TreeMap<Integer,Resept> nyliste){
        this.listen = nyliste;
        data = new LinkedList<>(listen.values());
    }
    
    public String getColonName(int kol){
        return super.getColonName(kol);
    }
    
    public Resept getRow(int rad){
        return data.get(rad);
    }
    
    public int getColumnCount(){
        return super.getColumnCount();
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
