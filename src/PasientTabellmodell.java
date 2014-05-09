/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA
 */

//Tabellmodell for Pasientinformasjon

import java.util.*;
import javax.swing.table.*;

public class PasientTabellmodell extends AbstractTableModel{
    private final String[] kolonnenavnresept = {"Fnr", "Navn", 
        "Lege"};
    private TreeMap<String,Pasient> listen;
    private LinkedList<Pasient> data;
    
    public PasientTabellmodell(TreeMap<String,Pasient> listen){
        this.listen=listen;
        data = new LinkedList<>(listen.values());
    }
    
    @Override
    public int getColumnCount(){
        return kolonnenavnresept.length;
    }
    
    @Override
    public String getColumnName(int kol){
        return kolonnenavnresept[kol];
    }
    
    public Object getRow(int rad){
        return data.get(rad);
    }
    
    @Override
    public int getRowCount(){
        return data.size();
    }
    
    @Override
    public Object getValueAt(int rad, int kol) {
        Pasient pasienten = data.get(rad);
        Object[] linjen = pasienten.getTabelllinje();
        switch(kol){
            case 0: return linjen[0];//Fnr
            case 1: return linjen[1];//Navn
            case 2: return linjen[2];//Lege
            default: return null;
        }  
    }
}

