/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA
 */

//Dette panelet er vinduet hvor legen får se oversikt over sine pasienter

import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class LegePanel extends JPanel{
    private final String LOGG_INN = "0";
    private Lytter lytteren;
    private Hovedramme hovedrammekopi;
    private TreeMap<Integer,Resept> reseptliste;
    private TreeMap<Integer,Resept> spesifikkreseptliste;
    private String legensautnr;
    
    //Sidepanel datafelter
    private JPanel sidepanel;
    private JTextArea infofelt;
    private Border sidepanelgrense;
    private JScrollPane infoscroll;
    private JButton skiftbruker;
    
    //Senterpanel datafelter
    private JPanel senterpanel,senterpanelnorth,senterpanelnorthcenter,
            senterpanelnorthnylege,senterpanelsouth;
    private TabellVindu tabellen;
    private Border senterpanelgrense;
    private JButton søk,logginn;
    private JTextField søkpasientid,søkreseptid,legeid;
    
    public LegePanel(String autnr,TreeMap<Integer,Resept> reseptliste){
        super.setLayout(new BorderLayout());
        lytteren = new Lytter();
        legensautnr = autnr;
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
        
        //SENTERPANEL ramme
        senterpanel = new JPanel(new GridLayout(0,1));
        senterpanelgrense = BorderFactory.createTitledBorder("Reseptoversikt");
        senterpanel.setBorder(senterpanelgrense);
        senterpanelnorth = new JPanel(new BorderLayout());
        senterpanelnorthcenter = new JPanel(new FlowLayout());
        senterpanelnorthnylege = new JPanel(new FlowLayout());
        senterpanelsouth = new JPanel(new BorderLayout());
         
        //SENTERPANELNORTH content:
        søkpasientid = new JTextField(15);
        senterpanelnorthcenter.add(new JLabel("Søk Pasient"));
        senterpanelnorthcenter.add(søkpasientid);
        
        søkreseptid = new JTextField(15);
        senterpanelnorthcenter.add(new JLabel("Søk Resept"));
        senterpanelnorthcenter.add(søkreseptid);
        
        søk = new JButton("Søk");
        søk.addActionListener(lytteren);
        senterpanelnorthcenter.add(søk);
        
        //SENTERPANEL Ny lege
        legeid = new JTextField(15);
        senterpanelnorthnylege.add(new JLabel("Authorisasjons nr."));
        senterpanelnorthnylege.add(legeid);
        
        logginn = new JButton("Logg Inn");
        logginn.addActionListener(lytteren);
        senterpanelnorthnylege.add(logginn);
        
        senterpanelnorth.add(senterpanelnorthcenter, BorderLayout.CENTER);
        senterpanel.add(senterpanelnorth);
        super.add(sidepanel, BorderLayout.LINE_START);
        super.add(senterpanel, BorderLayout.CENTER);
                
        //SENTERPANEL tabell
        matTabellen();
        filtrerLegelisten(legensautnr);
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
        senterpanelsouth.add(tabell,BorderLayout.CENTER);
        senterpanel.add(senterpanelsouth);
    }
    
    public void skiftbruker(){
        infofelt.setText("Skriv inn ditt autorisasjonsnr");
        hovedrammekopi = (Hovedramme) SwingUtilities.getWindowAncestor(this);
        senterpanelnorthcenter.setVisible(false);
        senterpanelsouth.setVisible(false);
        senterpanelnorth.add(senterpanelnorthnylege, BorderLayout.CENTER);
    }
    
    public void loggInnNyLege(){
        String nyelegen = legeid.getText();
        if(nyelegen.matches("")){
            legensautnr = nyelegen;
            filtrerLegelisten(legensautnr);
            matTabellen();
            senterpanelnorth.remove(senterpanelnorthnylege);
            senterpanelnorthcenter.setVisible(true);
            senterpanelsouth.setVisible(true);
        }
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
        return;
    }
    
    private class Lytter implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==skiftbruker){
                skiftbruker();
            }
            else if(e.getSource()==logginn){
                loggInnNyLege();
            }
        }
    }
}
