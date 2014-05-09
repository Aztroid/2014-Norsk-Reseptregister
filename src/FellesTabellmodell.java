/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA
*/

import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

public abstract class FellesTabellmodell extends AbstractTableModel{
    
    private String[] kolonnenavn;
    private static final int linjeteller = 0;
    
    public FellesTabellmodell(String[] kol){
        kolonnenavn = kol;
    }
    
    public void setKolonnetittler(String[] nykol){
        kolonnenavn = nykol;
    }
    
    public String getColumnName(int kol){
        return kolonnenavn[kol];
    }
    
    public abstract Object getRow(int rad);
            
    @Override
    public int getColumnCount(){
        return kolonnenavn.length;
    }
    
    @Override
    public abstract int getRowCount();

    @Override
    public abstract Object getValueAt(int rowIndex, int columnIndex);
}
