/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA
 */

/*Dette panelet er vinduet der kontrolløren kan få en oversikt over resepter
som er skrevet ut, og mer data relatert til dette med statistisk verdi. 
Kontrolløren skal også ha muligheten til å endre en leges reseptbevilgning*/

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class KontrollørPanel extends JPanel{

    private final String VISDATA = "9";
    private final String VISSTATISTIKK = "10";
    private final String VISVARSLING = "11";
    
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
    
    //Semterpanel datafelter
    private JPanel senterpanel;
    private TitledBorder senterpanelgrense;
    
    //Senterpanel visdata
    private JPanel senterpanelvisdata, visdatanorth;
    private TabellVindu tabellen;
    private JComboBox velglege, velgReseptKat;
    private JButton visalle;
    private String[] leger;
    
   //Sennterpanel Statistikk  
    private JPanel senterpanelstatistikk, statistikknorth;
    private JTextField hentlegemiddelstatistikk, legestatistikk;
    private String[] items = { "2013", "2014", "2015", "2016", "2017", "2018" };
    private JComboBox cb;
    private JScrollPane statistikkscroll;
    private Statistikkpanel grafikk;
    int[] kordinater;
    
    //Senterpanel varsling
    private JPanel senterpanelvarsling;
    private JPanel varslingnorth;
    private MedisinBibliotek medisinbiblioteket;
    private JButton knapp1,knapp2,knapp3;
    
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
        infofelt.setText("Innlogget som kontrollør");
        
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
        senterpanelgrense = BorderFactory.createTitledBorder
            ("Vis Reseptregister");
        senterpanel.setBorder(senterpanelgrense);
        
        //Senterpanel VisReseptdata
        senterpanelvisdata = new JPanel(new BorderLayout());
        visdatanorth = new JPanel(new FlowLayout());
        
        visalle = new JButton("Vis Alle");
        visalle.addActionListener(lytteren);
        visdatanorth.add(visalle);
        
        velglege = new JComboBox(legelisten());
        visdatanorth.add(new JLabel("Finn Lege"));
        velglege.addActionListener(lytteren);
        visdatanorth.add(velglege);
        
        velgReseptKat = new JComboBox(new String[]{"Reseptgruppe A",
            "Reseptgruppe B","Reseptgruppe C"});
        visdatanorth.add(new JLabel("Finn Resept"));
        velgReseptKat.addActionListener(lytteren);
        visdatanorth.add(velgReseptKat);
        
        //Visdata Tabell
        tabellen = new TabellVindu(this.reseptliste);
        tabellen.setOpaque(true);
        senterpanelvisdata.add(tabellen,BorderLayout.CENTER);
        
        //SENTERPANEL VISSTATISTIKK
        senterpanelstatistikk = new JPanel(new BorderLayout());
        senterpanelstatistikk.setSize(300, 1500);
        statistikknorth = new JPanel(new FlowLayout());
        genererKordinatliste(reseptliste);
        grafikk = new Statistikkpanel(kordinater);
        statistikkscroll = new JScrollPane(grafikk);
        statistikkscroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        statistikkscroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        statistikkscroll.setVisible(true);        
        grafikk.setPreferredSize(new Dimension(1000,1000));
        
        hentlegemiddelstatistikk = new JTextField(15);
        statistikknorth.add(new JLabel("Legemiddel"));
        statistikknorth.add(hentlegemiddelstatistikk);
        
        legestatistikk = new JTextField(15);
        statistikknorth.add(new JLabel("Lege"));
        statistikknorth.add(legestatistikk);
        
        cb = new JComboBox(items);
        statistikknorth.add(cb);
      
        senterpanelstatistikk.add(statistikknorth,BorderLayout.PAGE_START);
        senterpanelstatistikk.add(statistikkscroll,BorderLayout.CENTER);
        
        //SENTERPANEL varsling:
        senterpanelvarsling = new JPanel(new BorderLayout());
        varslingnorth = new JPanel(new FlowLayout());
        medisinbiblioteket = new MedisinBibliotek();
        
        knapp1 = new JButton("Søk");
        knapp1.addActionListener(lytteren);
        varslingnorth.add(knapp1);
        
        knapp2 = new JButton("Søk");
        knapp2.addActionListener(lytteren);
        varslingnorth.add(knapp2);
        
        knapp3 = new JButton("Søk");
        knapp3.addActionListener(lytteren);
        varslingnorth.add(knapp3);
        
        senterpanelvarsling.add(varslingnorth,BorderLayout.PAGE_START);
        //senterpanelvarsling.add(statistikkscroll,BorderLayout.CENTER);
        
        //LEGGER ALLE PANELER TIL
        senterpanelvisdata.add(visdatanorth,BorderLayout.PAGE_START);
        senterpanel.add(senterpanelvisdata,VISDATA);
        senterpanel.add(senterpanelstatistikk, VISSTATISTIKK);
        senterpanel.add(senterpanelvarsling, VISVARSLING);
        
        super.add(sidepanel, BorderLayout.LINE_START);
        super.add(senterpanel, BorderLayout.CENTER);
    }
    
    public void tilbakeTilMeny(){
        /*Sender kontrollør tilbake til startmenyen med en oppdatert liste over
        legene i tilfelle kontrolløren har endret reseptgodkjennelsen*/
        hovedrammekopi = (Hovedramme) SwingUtilities.getWindowAncestor(this);
        hovedrammekopi.visFørsteKontrollør(legeliste);
    }
    
    public void visData(){
        senterpanelgrense.setTitle("Vis Reseptregister");
        repaint();
        /*Denne metoden viser "vis data" panelet som inneholder tabellen og
        søkefeltene for tabellen*/
        oppDaterTabelen();
        CardLayout c = (CardLayout)senterpanel.getLayout();
        tabellen.repaint();
        senterpanelvisdata.revalidate();
        super.revalidate();
        c.show(senterpanel,VISDATA);
    }
    
    public String[] legelisten(){
        if(legeliste!=null){
            Lege løper;
            leger = new String[legeliste.size()];
            int i = 0;
            for(Map.Entry<String,Lege> entry:legeliste.entrySet()){
                løper = entry.getValue();
                leger[i]=løper.toString();
                i++;
            }
            return leger;
        }
        leger = new String[]{"Ingen pasienter opprettet"};
        return leger;
    }
    
    private void oppDaterTabelen(){
        /*Denne metoden generer en ny tabell bassert på utskrevende resepter, 
        og legger tabellen til i "vis data" panelet*/
        tabellen.nyInnData(reseptliste);
    }
    
    private void oppDaterTabelenLege(){
        /*Denne metoden generer en ny tabell bassert på legen som blir valgt i
        comboboxen, og legger tabellen til i "vis data" panelet*/
        TreeMap<Integer,Resept> spesifikklegereseptliste = new TreeMap<>();
        int n = velglege.getSelectedIndex();
        String fullid = (String)velglege.getItemAt(n);
        String legenøkkel = fullid.substring(0, 9);
        Resept løper;
        for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
            løper = entry.getValue();
            if(legenøkkel.equalsIgnoreCase(løper.getLege())){
                spesifikklegereseptliste.put(løper.getReseptnr(),løper);
            }
        }
        tabellen.nyInnData(spesifikklegereseptliste);
    }
 
    private void oppDaterTabelenKategori(){
        /*Denne metoden generer en ny tabell bassert på legen som blir valgt i
        comboboxen, og legger tabellen til i "vis data" panelet*/
        TreeMap<Integer,Resept> spesifikklegereseptliste = new TreeMap<>();
        TreeMap<Integer,Resept> spesifikkReseptgruppreseptliste = new TreeMap<>();
        int m = velglege.getSelectedIndex();
        String fullid = (String)velglege.getItemAt(m);
        String legenøkkel = fullid.substring(0, 9);
        Resept løper;
        for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
            løper = entry.getValue();
            if(legenøkkel.equalsIgnoreCase(løper.getLege())){
                spesifikklegereseptliste.put(løper.getReseptnr(),løper);
            }
        }
        int n = velgReseptKat.getSelectedIndex();
        String reseptgruppeid = (String)velgReseptKat.getItemAt(n);
        char reseptgruppe = reseptgruppeid.charAt(13);
        System.out.println(reseptgruppe);
        for(Map.Entry<Integer,Resept> entry:spesifikklegereseptliste.entrySet()){
            løper = entry.getValue();
            if(reseptgruppe==(løper.getReseptgruppe())){
                spesifikkReseptgruppreseptliste.put(løper.getReseptnr(),løper);
            }
        }
        tabellen.nyInnData(spesifikkReseptgruppreseptliste);
    }
    
    public void visStatistikk(){
        senterpanelgrense.setTitle("Vis Statistikk");
        repaint();
            CardLayout c = (CardLayout)senterpanel.getLayout();
            c.show(senterpanel,VISSTATISTIKK);
    }
    
    public void visVarsling(){
        senterpanelgrense.setTitle("Vis Varsling");
        repaint();
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.show(senterpanel,VISVARSLING);
    }
    
    public void genererKordinatliste(TreeMap<Integer,Resept> liste){
        kordinater = new int[12];
        Resept løper;
        for(Map.Entry<Integer,Resept> entry:liste.entrySet()){
            løper = entry.getValue();
            Calendar reseptkalenderformat = løper.getKalenderformat();
            int mnd = reseptkalenderformat.get(Calendar.MONTH);
            switch(mnd){
                case 1: kordinater[0]++;//Dato
                    break;
                case 2: kordinater[1]++;//Reseptnr
                    break;
                case 3: kordinater[2]++;//Lege autnr
                    break;
                case 4: kordinater[3]++;//Pasient fnr
                    break;
                case 5: kordinater[4]++;//Medisin(ACTnr)
                    break;
                case 6: kordinater[5]++;//Mengde
                    break;
                case 7: kordinater[6]++;//DDD
                    break;
                case 8: kordinater[7]++;//Kategori
                    break;
                case 9: kordinater[8]++;//Reseptgruppe
                    break;
                case 10: kordinater[9]++;//Reseptgruppe
                    break;
                case 11: kordinater[10]++;//Reseptgruppe
                    break;
                case 12: kordinater[11]++;//Reseptgruppe
                    break;
            }
        }
    }

    
    private class Lytter implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==gåtilbake){
                tilbakeTilMeny();
            }
            else if (e.getSource()==visdata){
                visData();
            }
            else if(e.getSource()==statistikk){
                 visStatistikk();
            }
            else if(e.getSource()==varsling){
                visVarsling();
            }
            else if(e.getSource()==velglege){
                oppDaterTabelenLege();
            }
            else if(e.getSource()==visalle){
                oppDaterTabelen();
            }
            else if(e.getSource()==velgReseptKat){
                oppDaterTabelenKategori();
            }
        }
 
    }
}