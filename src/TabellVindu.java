/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TabellVindu extends JPanel{
    private boolean DEBUG;
    Object [][] tabelldata;
    String[] kolonnenavn;
    
    public TabellVindu(Object [][] t, String[] k){
        super(new GridLayout(1,0));
        tabelldata = t;
        kolonnenavn = k;
    
        final JTable tabellen = new JTable(tabelldata,kolonnenavn);
        tabellen.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tabellen.setFillsViewportHeight(true);
        
        if (DEBUG) {
            tabellen.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    skrivUtDebugdata(tabellen);
                }
            });
        }
        
        JScrollPane skrollefeltet = new JScrollPane(tabellen);
        
        add(skrollefeltet);
    }
    
    private void skrivUtDebugdata(JTable t){
        int antrader = t.getRowCount();
        int antkolonner = t.getColumnCount();
        javax.swing.table.TableModel modell = t.getModel();
        
        System.out.println("Value of data: ");
        for (int i=0; i < antrader; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < antkolonner; j++) {
                System.out.print("  " + modell.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}
