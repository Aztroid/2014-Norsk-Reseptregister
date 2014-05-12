/*Hovedprosjekt Dats-1600
GRUPPE 6
William B. Wold, s183670, HIINGDATA13H1AA
Vegar Nygård, s193362, HIINGDATA13H1AA
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.table.*;

public class TabellVindu extends JPanel{
    private TreeMap<Integer,Resept> reseptliste;
    private TreeMap<String,Pasient> personliste;
    private TreeMap<String,Lege> legeliste;
    private TreeMap<String,int[]> medisinliste; //Medisinnavnet;
    private Object [][] medisindata;
    private JTable tabellen;
    
    public TabellVindu(){
        super(new GridLayout(0,1));
        tabellen = new JTable();
        tabellen.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tabellen.setFillsViewportHeight(true);
        JScrollPane skrollefeltet = new JScrollPane(tabellen);
        add(skrollefeltet);
    }
    
    public void nyInnDataResept(TreeMap<Integer,Resept> nyliste){
        reseptliste = nyliste;
        ReseptTabellModell nymodell = new ReseptTabellModell(reseptliste);
        tabellen.setModel(nymodell);
    }
    
    public void nyInnDataPasient(TreeMap<String,Pasient> nyliste){
        personliste = nyliste;
        PasientTabellmodell nymodell = new PasientTabellmodell(personliste);
        tabellen.setModel(nymodell);
    }
    
    public void nyInnDataLege(TreeMap<String,Lege> nyliste){
        legeliste = nyliste;
        LegeTabellmodell nymodell = new LegeTabellmodell(legeliste);
        tabellen.setModel(nymodell);
    }
    
    public void nyInnDataMedisin(TreeMap<Integer,Resept> nyliste){
        medisinliste = filtrer(nyliste);
        medisindata = initialiserData();
        MedisinTabellModell nymodell = new MedisinTabellModell(medisindata);
        tabellen.setModel(nymodell);
    }
    
    private TreeMap<String,int[]> filtrer(TreeMap<Integer,Resept> innliste){
        Resept løper;
        TreeMap<String,String> utskrvedemedisiner = new TreeMap<>();
        TreeMap<String,int[]> medisinliste = new TreeMap<>();
        try{
            String medisinnøkkel ="";
            String medisinnavn ="";
            for(Map.Entry<Integer,Resept> entry:innliste.entrySet()){
                løper = entry.getValue();
                medisinnøkkel = løper.getMedisin();
                utskrvedemedisiner.put(medisinnøkkel, medisinnavn);
            }
            for(Map.Entry<String,String> entry:utskrvedemedisiner.entrySet()){
                String medisinnøkkelto = entry.getKey();
                int[] mndliste = new int[12];
                for(Map.Entry<Integer,Resept> entrys:innliste.entrySet()){
                    løper = entrys.getValue();
                    medisinnøkkel = løper.getMedisin();
                    if(medisinnøkkel.matches(medisinnøkkelto)){
                        mndliste[løper.getKalenderformat()
                                .get(Calendar.MONTH)]++;
                    }
                }
                medisinliste.put(medisinnøkkelto,mndliste);
            }
            
        }
        catch(NullPointerException np){
            
        }
        return medisinliste;
    }
    
    private Object[][] initialiserData(){
        //System.out.println(medisinnøkkelto + ", " + mndliste[4]);
        Object[][] data;
        String medisinnavn;
        int[] mndoversikten;
        int radteller = 0;
        data=new Object[medisinliste.size()][(int)13];
        for(Map.Entry<String,int[]> entry:medisinliste.entrySet()){
            medisinnavn = entry.getKey();
            mndoversikten = entry.getValue();
            data[radteller][0]=(String)medisinnavn;
            int x = 0;
            for(int j = 1; j<=12;j++){
                data[radteller][j]=mndoversikten[x];
                x++;
            }
            radteller++;
        }
        return data;
    }
}
