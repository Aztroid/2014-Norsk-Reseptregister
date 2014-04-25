/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA
 */

//Dette panelet er vinduet hvor legen får se oversikt over sine pasienter

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;

public class LegePanel extends JPanel{
    private final String VISDATA = "0";
    private final String NY_LEGE = "0";
    private Lytter lytteren;
    private TreeMap<Integer,Resept> reseptliste;
    private TreeMap<Integer,Resept> spesifikkreseptliste;
    private TreeMap<String,Pasient> pasientliste;
    private String legensautnr;
    private Hovedramme hovedrammekopi;
    
    //Sidepanel datafelter
    private JPanel sidepanel;
    private JTextArea infofelt;
    private Border sidepanelgrense;
    private JScrollPane infoscroll;
    private JButton skiftbruker,gåtilbake;
    
    //Senterpanel datafelter
    private JPanel senterpanel,senterpanelvisdata, senterpaneltop,
            senterpanelnorthnylege,senterpanelsouth;
    private TabellVindu tabellen;
    private Border senterpanelgrense;
    private JButton søk,logginn;
    private JTextField søkpasientid,søkreseptid,legeid;
    
    public LegePanel(String autnr,TreeMap<String,Pasient> pasientliste,
            TreeMap<Integer,Resept> reseptliste){
        
        super.setLayout(new BorderLayout());
        lytteren = new Lytter();
        legensautnr = autnr;
        this.pasientliste = pasientliste;
        this.reseptliste = reseptliste;
        spesifikkreseptliste = new TreeMap<>();
        
        //SIDEPANEL ramme
        sidepanel = new JPanel();
        sidepanel.setLayout(new GridLayout(8,1,3,3));
        sidepanelgrense = BorderFactory.createTitledBorder("Navigering");
        sidepanel.setBorder(sidepanelgrense);
        
        //SIDEPANEL infofelt
        infofelt = new JTextArea(4,18);
        infoscroll = new JScrollPane(infofelt);
        infofelt.setText("Oversikt over dine resepter");
        
        infofelt.setEditable(false);
        sidepanel.add(infoscroll);
        
        //SIDEPANEL knapper
        skiftbruker = new JButton("Skift Lege");
        skiftbruker.addActionListener(lytteren);
        sidepanel.add(skiftbruker);
        
        gåtilbake = new JButton("Tilbake til meny");
        gåtilbake.addActionListener(lytteren);
        sidepanel.add(gåtilbake);
        
        //SENTERPANEL ramme
        senterpanel = new JPanel(new CardLayout());
        senterpanelgrense = BorderFactory.createTitledBorder("Reseptoversikt");
        senterpanel.setBorder(senterpanelgrense);
        senterpanelvisdata = new JPanel(new BorderLayout());
        senterpaneltop = new JPanel(new FlowLayout());
        senterpanelnorthnylege = new JPanel(new FlowLayout());
        senterpanelsouth = new JPanel(new FlowLayout());
         
        //SENTERPANELNORTH content:
        søkpasientid = new JTextField(15);
        senterpaneltop.add(new JLabel("Søk Pasient"));
        senterpaneltop.add(søkpasientid);
        
        søkreseptid = new JTextField(15);
        senterpaneltop.add(new JLabel("Søk Resept"));
        senterpaneltop.add(søkreseptid);
        
        søk = new JButton("Søk");
        søk.addActionListener(lytteren);
        senterpaneltop.add(søk);
        
        //SENTERPANEL Ny lege
        legeid = new JTextField(15);
        senterpanelnorthnylege.add(new JLabel("Authorisasjons nr."));
        senterpanelnorthnylege.add(legeid);
        
        logginn = new JButton("Logg Inn");
        logginn.addActionListener(lytteren);
        senterpanelnorthnylege.add(logginn);
        
        senterpanelvisdata.add(senterpaneltop,BorderLayout.PAGE_START);
        senterpanel.add(senterpanelvisdata,VISDATA);
        senterpanel.add(senterpanelnorthnylege, NY_LEGE);
        super.add(sidepanel, BorderLayout.LINE_START);
        super.add(senterpanel, BorderLayout.CENTER);
                
        //SENTERPANEL tabell
        matTabellen();
        filtrerLegelisten(legensautnr);
        visFørste();
    }
    
    public void visFørste(){
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.first(senterpanel);
    }
    
    private void matTabellen(){
        Resept løper;
        Object[][] tabelldata = new Object[spesifikkreseptliste.size()][9];
        Object[] linjen;
        String[] kolonnenavn = {"Dato", "Reseptnr.", "Personnr.", "Lege(Autnr.)", 
            "Medisin(ACTnr.)", "Mengde", "DDD", "Kategori", "Reseptgruppe"};
        for(int i = 0; i<spesifikkreseptliste.size(); i++){
            for(Map.Entry<Integer,Resept> entry:spesifikkreseptliste.entrySet()){
                løper = entry.getValue();
                linjen = løper.getTabelllinje();
                tabelldata[i][0]=linjen[0];
                tabelldata[i][1]=linjen[1];
                tabelldata[i][2]=linjen[2];
                tabelldata[i][3]=linjen[3];
                tabelldata[i][4]=linjen[4];
                tabelldata[i][5]=linjen[5];
                tabelldata[i][6]=linjen[6];
                tabelldata[i][7]=linjen[7];
                tabelldata[i][8]=linjen[8];
            }
        }
        TabellVindu tabell = new TabellVindu(tabelldata, kolonnenavn);
        senterpanelvisdata.add(tabell,BorderLayout.CENTER);
    }
    
    public void filtrerLegelisten(String autnr){
        Resept løper;
        if(!legensautnr.matches("")){
            for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
                løper = entry.getValue();
                if(legensautnr.equalsIgnoreCase(løper.getLege())){
                    spesifikkreseptliste.put(løper.getReseptnr(),løper);
                }
            }
        }
        else{
            infofelt.setText("Legenummeret var tomt\nAlle resepter vises\n"
                    + "Fordi jeg er lat");
        }
    }
    
    public void skiftbruker(){
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.show(senterpanel,NY_LEGE);
    }
    
    public void loggInnNyLege(){
        String nyelegen = legeid.getText();
        if(!nyelegen.matches("")){
            legensautnr = nyelegen;
            filtrerLegelisten(legensautnr);
            matTabellen();
            visFørste();
        }
        else{
            visFørste();
        }
    }
    
    public void tilbakeTilMeny(){
        hovedrammekopi = (Hovedramme) SwingUtilities.getWindowAncestor(this);
        hovedrammekopi.visFørste();
    }
    
    private class Lytter implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==skiftbruker){
                skiftbruker();
            }
            else if(e.getSource()==logginn){
                loggInnNyLege();
            }
            else{
                tilbakeTilMeny();
            }
        }
    }
}
