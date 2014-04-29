/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA
 */

//Dette panelet er vinduet der kontrolløren kan se/registrere ny data.

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class KontrollørPanel extends JPanel{
    private final String VISDATA = "0";
    private final String VISSTATISTIKK = "1";
    private final String VISVARSLING = "3";
    
    private Hovedramme hovedrammekopi;
    private Lytter lytteren;
    private TreeMap<String,Pasient> pasientliste;
    private TreeMap<Integer,Resept> reseptliste;
    private TreeMap<String,Lege> legeliste;
    private static int reseptnøkkel = 0;
    
    //Sidepanel datafelter
    private JPanel sidepanel;
    private JTextArea infofelt;
    private Border sidepanelgrense;
    private JScrollPane infoscroll;
    private JButton gåtilbake,visdata,statistikk,varsling;
    
    //Senterpanel datafelter
    private JPanel senterpanel,senterpanelvisdata, senterpaneltop,
            senterpanelstatistikk,senterpanelvarsling;
    private TabellVindu tabellen;
    private Border senterpanelgrense;
    private JButton søk;
    private JTextField søkpasientid,søkreseptid,søklegeid;
    
    public KontrollørPanel(TreeMap<String,Pasient> pasientliste,
            TreeMap<String,Lege> legeliste,
            TreeMap<Integer,Resept> reseptliste){
        
        super.setLayout(new BorderLayout());
        lytteren = new Lytter();
        this.pasientliste = pasientliste;
        this.legeliste = legeliste;
        this.reseptliste = reseptliste;
        
        //SIDEPANEL ramme
        sidepanel = new JPanel();
        sidepanel.setLayout(new GridLayout(8,1,3,3));
        sidepanelgrense = BorderFactory.createTitledBorder("Navigering");
        sidepanel.setBorder(sidepanelgrense);
        
        //SIDEPANEL infofelt
        infofelt = new JTextArea(4,18);
        infoscroll = new JScrollPane(infofelt);
        infofelt.setText("Oversikt over alle resepter");
        
        infofelt.setEditable(false);
        sidepanel.add(infoscroll);
        
        //SIDEPANEL knapper
        
        gåtilbake = new JButton("Tilbake til meny");
        gåtilbake.addActionListener(lytteren);
        sidepanel.add(gåtilbake);
        
        visdata = new JButton("Vis Reseptregister");
        visdata.addActionListener(lytteren);
        sidepanel.add(visdata);
        
        statistikk = new JButton("Vis Statistikk");
        statistikk.addActionListener(lytteren);
        sidepanel.add(statistikk);
        
        varsling = new JButton("Gå til Varslingsenter");
        varsling.addActionListener(lytteren);
        sidepanel.add(varsling);
        
        //SENTERPANEL ramme
        senterpanel = new JPanel(new CardLayout());
        senterpanelgrense = BorderFactory.createTitledBorder("Reseptoversikt");
        senterpanel.setBorder(senterpanelgrense);
        
        //Senterpanel Visdata
        senterpanelvisdata = new JPanel(new BorderLayout());
        senterpaneltop = new JPanel(new FlowLayout());
        
        søkpasientid = new JTextField(15);
        senterpaneltop.add(new JLabel("Søk Pasient"));
        senterpaneltop.add(søkpasientid);
        
        søklegeid = new JTextField(15);
        senterpaneltop.add(new JLabel("Søk Lege"));
        senterpaneltop.add(søklegeid);
        
        søkreseptid = new JTextField(15);
        senterpaneltop.add(new JLabel("Søk Resept"));
        senterpaneltop.add(søkreseptid);
        
        søk = new JButton("Søk");
        søk.addActionListener(lytteren);
        senterpaneltop.add(søk);
         
        //SENTERPANEL Regpasient:
        senterpanelstatistikk = new JPanel(new FlowLayout());
        senterpanelvarsling = new JPanel(new FlowLayout());
        
        //SENTERPANEL 
        senterpanelvisdata.add(senterpaneltop,BorderLayout.PAGE_START);
        senterpanel.add(senterpanelvisdata,VISDATA);
        senterpanel.add(senterpanelstatistikk, VISSTATISTIKK);
        senterpanel.add(senterpanelvarsling, VISVARSLING);
        
        super.add(sidepanel, BorderLayout.LINE_START);
        super.add(senterpanel, BorderLayout.CENTER);
                
        //SENTERPANEL tabell
    }
    
    public void visFørste(){
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.first(senterpanel);
    }
    
    private void lagTabellen(){
        TabellVindu tabell = new TabellVindu(reseptliste);
        tabell.setOpaque(true);
        senterpanelvisdata.add(tabell,BorderLayout.CENTER);
    }
    
    public void regPasient(){
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.show(senterpanel,VISVARSLING);
    }
    
    public void fylltabellen(){
        for(int i = 0; i < 100000; i++){
            reseptliste.put(i, new Resept(i,"L"+i,"P"+i,"med"+i,"mengde"+i,
                    "DDD"+i,"Kat"+i,'A',"Anv"+i));
        }   
    }
    
    public void tilbakeTilMeny(){
        hovedrammekopi = (Hovedramme) SwingUtilities.getWindowAncestor(this);
        hovedrammekopi.visFørste();
    }
    
    private class Lytter implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==visdata){
                fylltabellen();
                lagTabellen();
                revalidate();
            }
            else if(e.getSource()==gåtilbake){
                tilbakeTilMeny();
            }
        }
    }
}

