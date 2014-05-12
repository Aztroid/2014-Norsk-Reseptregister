/*Hovedprosjekt Dats-1600
GRUPPE 6
William B. Wold, s183670, HIINGDATA13H1AA
Vegar Nygård, s193362, HIINGDATA13H1AA
 */

//Sist redigert: 12.05.2014

//Mainmetode for Norsk Reseptregister

import java.awt.*;
import java.util.TreeMap;
import javax.swing.*;

public class MainDriver{
    
    public static void main(String []args){
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                Hovedramme ramme = new Hovedramme();
                ramme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
