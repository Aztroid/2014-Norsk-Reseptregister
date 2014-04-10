/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA*/

//Denne klassen skal teste hvordan jeg får datafeltene i en tabell

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class TabellVindu extends JPanel{
    private boolean DEBUG = false;
    private Object[][] tabelldata;
    private String[] kolonnenavn = {"Dato", "Reseptnr.", "Personnr.", "Lege(Autnr.)", 
            "Medisin(ACTnr.)", "Mengde", "DDD", "Kategori", "Reseptgruppe"};
    
    public TabellVindu(Object [][] t){
        super(new GridLayout(1,0));
        tabelldata = t;
        
        final JTable tabell = new JTable(tabelldata,kolonnenavn);
    } 
}

