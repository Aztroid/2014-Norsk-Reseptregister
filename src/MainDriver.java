/*Hovedprosjekt Dats-1600
GRUPPE 6
William B. Wold, s183670, HIINGDATA13H1AA
Vegar Nygård, s193362, HIINGDATA13H1AA
 */

import java.awt.*;
import java.util.TreeMap;
import javax.swing.*;

public class MainDriver{
    
    public static void main(String []args){
        //OPPRETT LISTENE HER OG SEND DE INN I ALT
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                Hovedramme ramme = new Hovedramme();
                ramme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
