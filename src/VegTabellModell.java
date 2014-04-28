
import java.util.LinkedList;
import javax.swing.table.AbstractTableModel;

/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA
 */

public abstract class VegTabellModell<T> extends AbstractTableModel {

    private String[] kolonnenavn;
    private LinkedList<T> data;

    public VegTabellModell(String[] kolonnenavn, LinkedList<T> data) {
        this.kolonnenavn = kolonnenavn;
        this.data = data;
    }

    public String getColumnName(int kol) {
        return kolonnenavn[kol];
    }

    public int getRowCount() {
        return data.size();
    }

    public T getRow(int rad) {
        return data.get(rad);
    }

    public int getColumnCount() {
        return kolonnenavn.length;
    }

    
    public LinkedList<T> getData() {
        return data;
    }

    @Override
    public abstract Object getValueAt(int rad, int kol);

    /**
     * Setter inn et objekt i en rad i tabellen og oppdaterer modellen.
     *
     * @param obj Objektetsom skal settes inn.
     */
    public void addRow(T obj) {
        data.add(obj);
        this.fireTableDataChanged();
    }

    /**
     * Fjerner en rad fra tabellen og oppdaterer modellen.
     *
     * @param rad Indeksen til den raden som skal fjernes.
     */
    public void delRow(int rad) {
        data.remove(rad);
        this.fireTableDataChanged();
    }

    /**
     * Sletter all data og oppdaterer modellen.
     */
    public void delTabledata() {
        data.clear();
        this.fireTableDataChanged();
    }

    /**
     * Sletter all data, setter inn ny og oppdaterer modellen.
     *
     * @param d Den nye listen med data.
     */
    public void setTabledata(LinkedList<T> d) {
        data.clear();
        data.addAll(d);
        this.fireTableDataChanged();
    }

}//tabellModell klasse feridg

class Legemodell extends VegTabellModell<Lege>{
    public static final int NAVN = 0;
    public static final int AUT = 1;
    public static final int ADR = 2;
    public static final int BEV = 3;
    
    public Legemodell(String[] kolonnenavn, LinkedList<Lege> data) {
        super(kolonnenavn, data);
    }

    
    @Override
    public Object getValueAt(int rad, int kol) {
        Lege lege = super.getData().get(rad);
        
        switch(kol){
            case NAVN: return lege.getFornavn() + " " + lege.getEtternavn();
            case AUT: return lege.getAutorisasjonsnr();
            case ADR: return lege.getArbeidssted();
            case BEV: return lege.getBevilgning();
            default: return null;
        }  
    }
    
    public Lege getValueAt(int rad){
        return super.getData().get(rad);
    }
    
    
    
}

