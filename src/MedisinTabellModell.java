/*Hovedprosjekt Dats-1600
GRUPPE 6
William B. Wold, s183670, HIINGDATA13H1AA
Vegar Nygård, s193362, HIINGDATA13H1AA
 */

//Tabellmodell for Medisininformasjon 

import java.util.*;
import javax.swing.table.*;

public class MedisinTabellModell extends AbstractTableModel{
    private String[] kolonnenavnresept = {"Preparat", "Jan.", 
        "Feb.", "Mar", "Apr", "Mai", 
        "Jun", "Jul", "Aug", "Sep","Okt","Nov","Des"};
    private Object[][] data;
    
    public MedisinTabellModell(Object[][] data){
        this.data=data;
    }
    
    @Override
    public int getColumnCount(){
        return kolonnenavnresept.length;
    }
    
    @Override
    public String getColumnName(int kol){
        return kolonnenavnresept[kol];
    }
    
    
    @Override
    public int getRowCount(){
        return data.length;
    }
    
    @Override
    public Object getValueAt(int rad, int kol) {
        return data[ rad][ kol];
    }    
}
