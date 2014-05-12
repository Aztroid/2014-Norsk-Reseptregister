/*Hovedprosjekt Dats-1600
GRUPPE 6
William B. Wold, s183670, HIINGDATA13H1AA
Vegar Nygård, s193362, HIINGDATA13H1AA
 */

//Tabellmodell for Legetinformasjon

import java.util.*;
import javax.swing.table.*;

public class LegeTabellmodell extends AbstractTableModel{
    private String[] kolonnenavnresept = {"Autnr", "Navn", 
        "Resept.Bev.", "Adr"};
    private TreeMap<String,Lege> listen;
    private LinkedList<Lege> data;
    
    public LegeTabellmodell(TreeMap<String,Lege> listen){
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
        Lege legen = data.get(rad);
        Object[] linjen = legen.getTabelllinje();
        switch(kol){
            case 0: return linjen[0];//Autnr
            case 1: return linjen[1];//Navn
            case 2: return linjen[2];//Reseptbevilgning
            case 3: return linjen[3];//Arbeidsstedr
            default: return null;
        }  
    }
}


