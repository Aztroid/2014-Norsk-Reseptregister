/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TabellVindu extends JPanel{
    private boolean DEBUG;
    private Object [][] tabelldata;
    private String[] kolonnenavn;
    private JButton kapp;
    
    public TabellVindu(Object [][] t, String[] k){
        super(new GridLayout(0,1));
        tabelldata = t;
        kolonnenavn = k;
    
        final JTable tabellen = new JTable(tabelldata,kolonnenavn);
        tabellen.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tabellen.setFillsViewportHeight(true);
        
        JScrollPane skrollefeltet = new JScrollPane(tabellen);
        add(skrollefeltet);
    }
}
