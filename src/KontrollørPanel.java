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
    private final String REGPASIENT = "1";
    private final String REGLEGE = "2";
    private final String REGRESEPT = "3";
    
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
    private JButton nypasient,nylege,nyresept,visdata;
    
    //Senterpanel datafelter
    private JPanel senterpanel,senterpanelvisdata, senterpaneltop,
            senterpanelregpasient,senterpanelreglege,senterpanelregresept;
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
        
        visdata = new JButton("Vis Reseptregister");
        visdata.addActionListener(lytteren);
        sidepanel.add(visdata);
        
        nypasient = new JButton("Registrer Pasient");
        nypasient.addActionListener(lytteren);
        sidepanel.add(nypasient);
        
        nylege = new JButton("Registrer Pasient");
        nylege.addActionListener(lytteren);
        sidepanel.add(nylege);
        
        nyresept = new JButton("Registrer Pasient");
        nyresept.addActionListener(lytteren);
        sidepanel.add(nyresept);
        
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
        senterpanelregpasient = new JPanel(new FlowLayout());
        senterpanelreglege = new JPanel(new FlowLayout());
        senterpanelregresept = new JPanel(new FlowLayout());
        
        //SENTERPANEL 
        senterpanelvisdata.add(senterpaneltop,BorderLayout.PAGE_START);
        senterpanel.add(senterpanelvisdata,VISDATA);
        senterpanel.add(senterpanelregpasient, REGPASIENT);
        senterpanel.add(senterpanelreglege, REGLEGE);
        senterpanel.add(senterpanelregresept, REGRESEPT);
        
        super.add(sidepanel, BorderLayout.LINE_START);
        super.add(senterpanel, BorderLayout.CENTER);
                
        //SENTERPANEL tabell
        fylllisten();
        matTabellen();
        visFørste();
    }
    
    public void visFørste(){
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.first(senterpanel);
    }
    
    private void matTabellen(){
        Resept løper;
        Object[][] tabelldata = new Object[reseptliste.size()][reseptliste.size()];
        Object[] linjen;
        String[] kolonnenavn = {"Dato", "Reseptnr.", "Personnr.", "Lege(Autnr.)", 
            "Medisin(ACTnr.)", "Mengde", "DDD", "Kategori", "Reseptgruppe"};
        TabellVindu tabell = new TabellVindu(tabelldata,kolonnenavn);
        //tabell.setOpaque(true);
        senterpanelvisdata.add(tabell,BorderLayout.CENTER);
    }
    
    public void regPasient(){
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.show(senterpanel,REGPASIENT);
    }
    
    public void fylllisten(){
        String personnr = "test";
        String legenr = "test";
        Resept ny;
        for(int i = 1; i<10000;i++){
            personnr = "test";
            legenr = "test";
            personnr+= i;
            legenr+= i;
            ny = new Resept(reseptnøkkel++,personnr,legenr,"test",
                    "test","test","test",'A',"test");
            reseptliste.put(ny.getReseptnr(),ny);
            System.out.println(ny.toString());
        }
    }
    
    private class Lytter implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==nypasient){
            }
            else if(e.getSource()==nylege){
                
            }
        }
    }
}

