/*Hovedprosjekt Dats-1600
GRUPPE 6
William B. Wold, s183670, HIINGDATA13H1AA
Vegar Nygård, s193362, HIINGDATA13H1AA
 */

//Tabellmodell for Reseptinformasjon

import java.util.*;
import javax.swing.table.*;

public class ReseptTabellModell extends AbstractTableModel{
    private String[] kolonnenavnresept = {"Dato", "Reseptnr.", 
        "Personnr.", "Lege(Autnr.)", "Medisin(ACTnr.)", "Mengde", 
        "DDD", "Kategori", "Reseptgruppe"};
    private TreeMap<Integer,Resept> listen;
    private LinkedList<Resept> data;
    
    public ReseptTabellModell(TreeMap<Integer,Resept> listen){
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
