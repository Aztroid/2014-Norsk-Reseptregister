/*Hovedprosjekt Dats-1600
GRUPPE 6
William B. Wold, s183670, HIINGDATA13H1AA
Vegar Nygård, s193362, HIINGDATA13H1AA
 */

/*Dette panelet er vinduet der kontrolløren kan få en oversikt over resepter
som er skrevet ut, og mer data relatert til dette med statistisk verdi. 
Kontrolløren  har også muligheten til å endre en leges reseptbevilgning*/

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class KontrollørPanel extends JPanel{
    
    //Felles datafelter
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
    private JButton gåtilbake,visreseptreg,vispersonreg,statistikk,
            medisinovsikt;
    
    //Semterpanel datafelter
    private final String VISDATA = "9";
    private final String VISREG = "10";
    private final String VISSTATISTIKK = "11";
    private final String VISMEDISIN = "12";
    private JPanel senterpanel;
    private TitledBorder senterpanelgrense;
    private String[] leger,pasienter,medisiner;
    private MedisinBibliotek medisinbiblioteket;
    
    //Senterpanel visdata
    private JPanel senterpanelvisdata, visdatanorth;
    private TabellVindu visdatatabell;
    private JComboBox velglege, velgpasient, velgReseptKat;
    private JButton visalle, vispasient,vislege;
    private final int LEGEVALG = 1; 
    private int VALG;
    
    //Senterpanel vispersonreg
    private JPanel senterpanelvispersonreg, vispersonregnorth, vispersonregsouth;
    private GridBagConstraints c;
    private TabellVindu vispersonregtabell;
    private JComboBox velglegepreg, velgpasientpreg, legemedisin,
            pasientmedisin, endrelegeres;
    private JButton endreresept, visalleleger, visallepasienter, fullfør,tilbake;
    private Tegnlytter tegnlytteren;
    private JCheckBox Abox;
    private JCheckBox Bbox;
    private JCheckBox Cbox;
    private boolean gruppeA;
    private boolean gruppeB;
    private boolean gruppeC;
    
   //Sennterpanel Statistikk  
    private JPanel senterpanelstatistikk, statistikknorth;
    private JTextField legestatistikk;
    private JComboBox årførste, årandre;
    private JScrollPane statistikkscroll;
    private Statistikkpanel grafikk;
    int[] kordinateren = new int[12];
    int[] kordinaterto = new int[12];
    private JButton visgrafen, visgrafto;
    
    //Senterpanel Medisinoversikt
    private JPanel senterpanelmedisinoversikt,vismedisinnorth;
    private JComboBox medisinår;
    private TabellVindu vismedisintabell;
    
    public KontrollørPanel(TreeMap<String,Pasient> pasientliste,
            TreeMap<String,Lege> legeliste,
            TreeMap<Integer,Resept> reseptliste){
        //Felles datafelter
        super.setLayout(new BorderLayout());
        lytteren = new Lytter();
        this.pasientliste = pasientliste;
        this.legeliste = legeliste;
        this.reseptliste = reseptliste;
        medisinbiblioteket = new MedisinBibliotek();
        
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
        
        visreseptreg = new JButton("Vis Reseptregister");
        visreseptreg.addActionListener(lytteren);
        sidepanel.add(visreseptreg);
        
        vispersonreg = new JButton("Vis Personregister");
        vispersonreg.addActionListener(lytteren);
        sidepanel.add(vispersonreg);
        
        statistikk = new JButton("Vis Statistikk");
        statistikk.addActionListener(lytteren);
        sidepanel.add(statistikk);
        
        medisinovsikt = new JButton("Gå til Medisinoversikt");
        medisinovsikt.addActionListener(lytteren);
        sidepanel.add(medisinovsikt);
        
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
        visdatanorth.add(new JLabel("Søk i Listen"));
        velglege.addActionListener(lytteren);
        visdatanorth.add(velglege);
        
        velgpasient = new JComboBox(pasientlisten());
        velgpasient.addActionListener(lytteren);
        visdatanorth.add(velgpasient);
        velgpasient.setVisible(false);
        
        velgReseptKat = new JComboBox(new String[]{"Reseptgruppe A",
            "Reseptgruppe B","Reseptgruppe C"});
        velgReseptKat.addActionListener(lytteren);
        visdatanorth.add(velgReseptKat);
        
        vispasient = new JButton("Vis Pasientsøk");
        vispasient.addActionListener(lytteren);
        visdatanorth.add(vispasient);
        
        vislege = new JButton("Vis Legesøk");
        vislege.addActionListener(lytteren);
        visdatanorth.add(vislege);
        vislege.setVisible(false);
        
        //Reseptdata Tabell
        visdatatabell = new TabellVindu();
        visdatatabell.setOpaque(true);
        
        senterpanelvisdata.add(visdatanorth,BorderLayout.PAGE_START);
        senterpanelvisdata.add(visdatatabell,BorderLayout.CENTER);
        
        //Personregisteret
        tegnlytteren = new Tegnlytter();
        senterpanelvispersonreg = new JPanel(new BorderLayout());
        vispersonregnorth = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 5;
        c.ipady = 5;
        
        visalleleger = new JButton("Vis alle Leger");
        visalleleger.addActionListener(lytteren);
        vispersonregnorth.add(visalleleger,c);
        
        c.gridx = 1;
        velglegepreg = new JComboBox(legelisten());
        velglegepreg.addActionListener(lytteren);
        vispersonregnorth.add(velglegepreg,c);
        
        c.gridx = 0;
        c.gridy = 1;
        visallepasienter = new JButton("Vis alle pasienter");
        visallepasienter.addActionListener(lytteren);
        vispersonregnorth.add(visallepasienter,c);
        
        c.gridx = 1;
        velgpasientpreg = new JComboBox(pasientlisten());
        velgpasientpreg.addActionListener(lytteren);
        vispersonregnorth.add(velgpasientpreg,c);
        
        c.gridy = 2;
        c.gridx = 0;
        vispersonregnorth.add(new JLabel("Leger som har utskrevet medisin:"),c);
        c.gridx = 1;
        legemedisin = new JComboBox(medisinlisten());
        legemedisin.addActionListener(lytteren);
        vispersonregnorth.add(legemedisin,c);
        
        c.gridy = 3;
        c.gridx = 0;
        vispersonregnorth.add(new JLabel("Pasienter som har fått resept på medisin:"),c);
        c.gridx = 1;
        pasientmedisin = new JComboBox(medisinlisten());
        pasientmedisin.addActionListener(lytteren);
        vispersonregnorth.add(pasientmedisin,c);
        
        c.gridy = 4;
        c.gridx = 0;
        endreresept = new JButton("Vis meny for reseptendring");
        endreresept.addActionListener(lytteren);
        vispersonregnorth.add(endreresept,c);
        
        //Visdata Tabell
        vispersonregtabell = new TabellVindu();
        senterpanelvispersonreg.add(vispersonregnorth,BorderLayout.PAGE_START);
        senterpanelvispersonreg.add(vispersonregtabell,BorderLayout.CENTER);
        
        //EndreResept 
        vispersonregsouth = new JPanel(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 5;
        c.ipady = 5;
        
        vispersonregsouth.add(new JLabel("Endrer Resept for lege:"),c);
        c.gridx = 1;
        endrelegeres = new JComboBox(legelisten());
        vispersonregsouth.add(endrelegeres,c);
        
        c.gridx = 0;
        c.gridy = 1;
        vispersonregsouth.add(new JLabel("Legens ny bevilgning:"),c);
        c.gridx = 1;
        c.gridy = 2;
        Abox = new JCheckBox("Gruppe A");
        Abox.addItemListener(tegnlytteren);
        vispersonregsouth.add(Abox,c);
        c.gridy = 3;
        Bbox = new JCheckBox("Gruppe B");
        Bbox.addItemListener(tegnlytteren);
        vispersonregsouth.add(Bbox,c);
        c.gridy = 4;
        Cbox = new JCheckBox("Gruppe C");
        Cbox.addItemListener(tegnlytteren);
        vispersonregsouth.add(Cbox,c);
        
        c.gridx = 0;
        c.gridy = 5;
        tilbake = new JButton("Tilbake");
        tilbake.addActionListener(lytteren);
        vispersonregsouth.add(tilbake,c);
        
        c.gridx = 1;
        fullfør = new JButton("Fullfør");
        fullfør.addActionListener(lytteren);
        vispersonregsouth.add(fullfør,c);
        senterpanelvispersonreg.add(vispersonregsouth,BorderLayout.PAGE_END);
        
        //SENTERPANEL VISSTATISTIKK
        senterpanelstatistikk = new JPanel(new BorderLayout());
        senterpanelstatistikk.setSize(300, 1500);
        statistikknorth = new JPanel(new FlowLayout());
        grafikk = new Statistikkpanel();
        
        statistikkscroll = new JScrollPane(grafikk);
        statistikkscroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        statistikkscroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        statistikkscroll.setVisible(true);        
        grafikk.setPreferredSize(new Dimension(1000,1000));
        
        statistikknorth.add(new JLabel("År 1:"));
        årførste = new JComboBox(finnår());
        statistikknorth.add(årførste);
        
        statistikknorth.add(new JLabel("År 2:"));
        årandre = new JComboBox(finnår());
        statistikknorth.add(årandre);
        
        visgrafen = new JButton("Vis år 1");
        visgrafen.addActionListener(lytteren);
        statistikknorth.add(visgrafen);
        
        visgrafto = new JButton("Vis Begge");
        visgrafto.addActionListener(lytteren);
        statistikknorth.add(visgrafto);
      
        senterpanelstatistikk.add(statistikknorth,BorderLayout.PAGE_START);
        senterpanelstatistikk.add(statistikkscroll,BorderLayout.CENTER);
        
        //SENTERPANEL Medisinoversikt:
        vismedisinnorth = new JPanel(new FlowLayout());
        senterpanelmedisinoversikt = new JPanel(new BorderLayout());
        
        medisinår = new JComboBox(finnår());
        medisinår.addActionListener(lytteren);
        vismedisinnorth.add(medisinår);
        
        //Medisinoversikt Tabell
        vismedisintabell = new TabellVindu();
        vismedisintabell.setOpaque(true);
        
        senterpanelmedisinoversikt.add(vismedisinnorth,BorderLayout.PAGE_START);
        senterpanelmedisinoversikt.add(vismedisintabell,BorderLayout.CENTER);
        
        //LEGGER ALLE PANELER TIL
        senterpanel.add(senterpanelvisdata,VISDATA);
        senterpanel.add(senterpanelvispersonreg,VISREG);
        senterpanel.add(senterpanelstatistikk, VISSTATISTIKK);
        senterpanel.add(senterpanelmedisinoversikt, VISMEDISIN);
        super.add(sidepanel, BorderLayout.LINE_START);
        super.add(senterpanel, BorderLayout.CENTER);
        oppDaterTabelen();
    }
    
    public void tilbakeTilMeny(){
        /*Sender kontrollør tilbake til startmenyen med en oppdatert liste over
        legene i tilfelle kontrolløren har endret reseptgodkjennelsen*/
        hovedrammekopi = (Hovedramme) SwingUtilities.getWindowAncestor(this);
        hovedrammekopi.visFørsteKontrollør(legeliste);
    }
    
    public void visDataReseptreg(){
        /*Denne metoden viser "vis data" panelet som inneholder tabellen og
        søkefeltene for tabellen*/
        senterpanelgrense.setTitle("Reseptregister");
        repaint();
        oppDaterTabelen();
        CardLayout c = (CardLayout)senterpanel.getLayout();
        visdatatabell.repaint();
        senterpanelvisdata.revalidate();
        super.revalidate();
        c.show(senterpanel,VISDATA);
    }
    
    public String[] legelisten(){
        /*Denne metoden returnerer en array med alle legene som er i legelisten 
        på formen "legensautnr, legensnavn"*/
        if(legeliste!=null){
            Lege løper;
            leger = new String[legeliste.size()];
            int i = 0;
            for(Map.Entry<String,Lege> entry:legeliste.entrySet()){
                løper = entry.getValue();
                leger[i]=løper.toString();
                i++;
            }//End of for-løkke
            return leger;
        }
        leger = new String[]{"Ingen pasienter opprettet"};
        return leger;
    }
            
    public String[] pasientlisten(){
        /*Denne metoden returnerer en array med alle pasientene som er i 
        pasientlisten på formen "pasientensfnr, pasientenssnavn"*/
        if(pasientliste!=null){
            Pasient løper;
            pasienter = new String[pasientliste.size()];
            int i = 0;
            for(Map.Entry<String,Pasient> entry:pasientliste.entrySet()){
                løper = entry.getValue();
                pasienter[i]=løper.toString();
                i++;
            }//End of for-løkke
            return pasienter;
        }
        leger = new String[]{"Ingen pasienter opprettet"};
        return pasienter;
    }
    
    public void visPasientCombo(){
        //Denne metoden viser alle kombobokser for å kunne søke i pasienter
        velglege.setVisible(false); 
        velgpasient.setVisible(true);
        vislege.setVisible(true);
        vispasient.setVisible(false);
        VALG=2;
    }
            
    public void visLegeCombo(){
        //Denne metoden viser alle kombobokser for å kunne søke i leger
        velgpasient.setVisible(false); 
        velglege.setVisible(true);
        vislege.setVisible(false);
        vispasient.setVisible(true);
        VALG=1;
    }
    
    public String[] medisinlisten(){
        /*Denne metoden returnerer en array med alle medisiner som er i 
        reseptregisteret på formen "medisinensnøkkel, medisinensnavn"*/
        medisiner = new String[]{"Ingen resepter"};
        Resept løper;
        TreeMap<String,String> utskrvedemedisiner = new TreeMap<>();
        if(!reseptliste.isEmpty()||reseptliste!=null){
            int i=0;
            String medisinnøkkel ="";
            String medisinnavn ="";
            for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
                løper = entry.getValue();
                medisinnøkkel = løper.getMedisin();
                medisinnavn = medisinbiblioteket.getBibliotek().get(medisinnøkkel);
                utskrvedemedisiner.put(medisinnøkkel, medisinnavn);
                i++;
            }//End of for-løkke
            i=0;
            medisiner = new String[utskrvedemedisiner.size()];
            for(Map.Entry<String,String> entry:utskrvedemedisiner.entrySet()){
                medisinnøkkel = entry.getKey();
                medisinnavn = entry.getValue();
                medisiner[i] = medisinnøkkel + ", " + medisinnavn;
                i++;
            }//End of for-løkke
        }
        return medisiner;
    }
    
    private void oppDaterTabelen(){
        /*Denne metoden generer en ny tabell bassert på utskrevende resepter, 
        og legger tabellen til i "vis data" panelet*/
        visdatatabell.nyInnDataResept(reseptliste);
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
        }//End of for-løkke
        visdatatabell.nyInnDataResept(spesifikklegereseptliste);
    }
 
    private void oppDaterTabelenKategori(int id){
        /*Denne metoden generer en ny tabell bassert på legen/Pasienten 
        som blir valgt i comboboxen, og reseptgruppen valgt. Deretter legges 
        tabellen til i "vis data" panelet. Om det er lege eller pasient
        velges ved å sende ved en int parameter spesifisert. 1 er lege, noe 
        annet er pasient*/
        TreeMap<Integer,Resept> spesifikklegereseptliste = new TreeMap<>();
        TreeMap<Integer,Resept> spesifikkReseptgruppreseptliste = 
                new TreeMap<>();
        if(id==LEGEVALG){
            int m = velglege.getSelectedIndex();
            String fullid = (String)velglege.getItemAt(m);
            String legenøkkel = fullid.substring(0, 9);
            Resept løper;
            for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
                løper = entry.getValue();
                if(legenøkkel.equalsIgnoreCase(løper.getLege())){
                    spesifikklegereseptliste.put(løper.getReseptnr(),løper);
                }
            }//End of for-løkke
            int n = velgReseptKat.getSelectedIndex();
            String reseptgruppeid = (String)velgReseptKat.getItemAt(n);
            char reseptgruppe = reseptgruppeid.charAt(13);
            for(Map.Entry<Integer,Resept> 
                    entry:spesifikklegereseptliste.entrySet()){
                løper = entry.getValue();
                if(reseptgruppe==(løper.getReseptgruppe())){
                    spesifikkReseptgruppreseptliste.put(
                            løper.getReseptnr(),løper);
                }
            }//End of for-løkke
            visdatatabell.nyInnDataResept(spesifikkReseptgruppreseptliste);
            return;
        }
        else{
            int n = velgpasient.getSelectedIndex();
            String fullid = (String)velgpasient.getItemAt(n);
            String pasientnøkkel = fullid.substring(0, 11);
            Resept løper;
            for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
                løper = entry.getValue();
                if(pasientnøkkel.equalsIgnoreCase(løper.getPasient())){
                    spesifikklegereseptliste.put(løper.getReseptnr(),løper);
                }
            }//End of for-løkke
            int m = velgReseptKat.getSelectedIndex();
            String reseptgruppeid = (String)velgReseptKat.getItemAt(m);
            char reseptgruppe = reseptgruppeid.charAt(13);
            for(Map.Entry<Integer,Resept> entry:spesifikklegereseptliste.entrySet()){
                løper = entry.getValue();
                if(reseptgruppe==(løper.getReseptgruppe())){
                    spesifikkReseptgruppreseptliste.put(løper.getReseptnr(),løper);
                }
            }//End of for-løkke
            visdatatabell.nyInnDataResept(spesifikkReseptgruppreseptliste);
        }
    }
    
    private void oppDaterTabelenPasient(){
        /*Denne metoden generer en ny tabell med en pasient bassert på valgt 
        pasient i comboboksen*/
        TreeMap<Integer,Resept> spesifikkpasientreseptliste = new TreeMap<>();
        int n = velgpasient.getSelectedIndex();
        String fullid = (String)velgpasient.getItemAt(n);
        String pasientnøkkel = fullid.substring(0, 11);
        Resept løper;
        for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
            løper = entry.getValue();
            if(pasientnøkkel.equalsIgnoreCase(løper.getPasient())){
                spesifikkpasientreseptliste.put(løper.getReseptnr(),løper);
            }
        }//End of for-løkke
        visdatatabell.nyInnDataResept(spesifikkpasientreseptliste);
    }
    
    public void visPersonReg(){
        /*Denne metoden viser "Personregisteret" som inneholder all info om 
        leger og pasienter registrert i systemet*/
        infofelt.setText("I dette panelet ser du detaljinfo om\nleger"
                + " og pasienter. \nDu kan også endre en leges \n"
                + "reseptbevilgning her ved å klikke på\n" +
                  "\"Vis meny for reseptendring\"");
        senterpanelgrense.setTitle("Personregister");
        vispersonregsouth.setVisible(false);
        repaint();
        oppDaterLegeTabelen();
        CardLayout c = (CardLayout)senterpanel.getLayout();
        vispersonregtabell.repaint();
        senterpanelvispersonreg.revalidate();
        super.revalidate();
        c.show(senterpanel,VISREG);
    }
    
    private void oppDaterLegeTabelen(){
        /*Denne metoden generer en ny tabell av typen LegeTabellModell 
        bassert på legelisten, og legger tabellen til i Personregister panelet*/
        vispersonregtabell.nyInnDataLege(legeliste);
    }
    
    private void enLegeiTabelen(){
        /*Denne metoden generer en ny tabell bassert på valgt lege i 
        comboboxen*/
        try{
            TreeMap<String,Lege> enlegeliste = new TreeMap<>();
            int n = velglegepreg.getSelectedIndex();
            String fullid = (String)velglegepreg.getItemAt(n);
            String legenøkkel = fullid.substring(0, 9);
            Lege legen = legeliste.get(legenøkkel);
                enlegeliste.put(legen.getAutorisasjonsnr(),legen);
            vispersonregtabell.nyInnDataLege(enlegeliste);
        }
        catch(NullPointerException np){
            System.out.println("Ingen lege funnet");
        }
    }
    
    private void oppDaterPasientTabelen(){
        /*Denne metoden generer en ny tabell av typen PasientTabellModell 
        bassert på pasientlisten, og legger tabellen til i Personregister 
        panelet*/
        vispersonregtabell.nyInnDataPasient(pasientliste);
    }
    
    private void enPasientiTabelen(){
         /*Denne metoden generer en ny tabell bassert på valgt pasient i 
        comboboxen*/
        try{
            TreeMap<String,Pasient> enPasientliste = new TreeMap<>();
            int n = velgpasientpreg.getSelectedIndex();
            String fullid = (String)velgpasientpreg.getItemAt(n);
            String pasientnøkkel = fullid.substring(0, 11);
            Pasient pasienten = pasientliste.get(pasientnøkkel);
                enPasientliste.put(pasienten.getFødselsnr(),pasienten);
            vispersonregtabell.nyInnDataPasient(enPasientliste);
        }
        catch(NullPointerException np){
            System.out.println("Ingen lege funnet");
        }
    }
    
    public void medisinLegeiTabelen(){
        /*Viser en tabell av typen LegeTabellModell med alle leger som har 
        utskrevet en spesifikk medisin*/
        Resept løper;
        Lege legen;
        TreeMap<String,Lege> medisinlegeliste = new TreeMap<>();
        if(!reseptliste.isEmpty()||reseptliste!=null){
            int n = legemedisin.getSelectedIndex();
            String fullid = (String)legemedisin.getItemAt(n);
            String medisinnøkkel = fullid.substring(0, 8);
            for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
                løper = entry.getValue();
                if(løper.getMedisin().matches(medisinnøkkel)){
                    legen = legeliste.get(løper.getLege());
                    medisinlegeliste.put(legen.getAutorisasjonsnr(), legen); 
                } 
            }//End of for-løkke
        vispersonregtabell.nyInnDataLege(medisinlegeliste);
        }
        else{
            infofelt.setText("Ingen lege funnet");
        }
    }
    
    public void medisinPasientiTabelen(){
        /*Viser en tabell av typen PasientTabellModell med alle pasienter som 
        har fått resept på en spesifikk medisin*/
        Resept løper;
        Pasient pasienten;
        TreeMap<String,Pasient> medisinpasientliste = new TreeMap<>();
        if(!reseptliste.isEmpty()||reseptliste!=null){
            int n = pasientmedisin.getSelectedIndex();
            String fullid = (String)pasientmedisin.getItemAt(n);
            String medisinnøkkel = fullid.substring(0, 8);
            for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
                løper = entry.getValue();
                if(løper.getMedisin().matches(medisinnøkkel)){
                    pasienten = pasientliste.get(løper.getPasient());
                    medisinpasientliste.put(pasienten.getFødselsnr(), pasienten); 
                } 
            }//End of for-løkke
        vispersonregtabell.nyInnDataPasient(medisinpasientliste);
        }
        else{
            infofelt.setText("Ingen pasient funnet");
        }
    }
    
    public void visEndreReseptBev(){
        /*Denne metoden viser panelet i Personregisteret der du kan gjøre 
        endringer på reseptbevilgningen til en lege*/
        vispersonregsouth.setVisible(true);
        infofelt.setText("Reseptendring for lege:\n" + 
                "\nSkriv ny reseptbevilgning inn\ni feltet (ABC,BC evt)");
    }
    
    public void endreResept(){
        /*Denne metoden gir valgt lege i comboboxen en ny resept bassert på
        det kontrolløren har valgt*/
        try{
            Lege legennyresept;
            String reseptbev="";
            int n = endrelegeres.getSelectedIndex();
            String fullid = (String)endrelegeres.getItemAt(n);
            String legenøkkel = fullid.substring(0, 9);
            legennyresept = legeliste.get(legenøkkel);
            if(gruppeA){
                reseptbev += "A";
            }
            if(gruppeB){
                reseptbev += "B";
            }
            if(gruppeC){
                reseptbev += "C";
            }
            legennyresept.setBevilgning(reseptbev);
            oppDaterLegeTabelen();
            System.out.println(reseptbev);
            visPersonReg();
        }
        catch(NullPointerException np){
            System.out.println("Ingen lege funnet");
        }
    }
    
    public void visStatistikk(){
        /*Denne metoden viser statistikkpanelet hvor bargrafer kan presenteres*/
        infofelt.setText("Dette panelet viser en statistisk\n"
                + "representasjon av resepter\nutskrevet i et "
                + "gitt år, her kan også to\når sammenliknes");
        senterpanelgrense.setTitle("Statistikk");
        repaint();
            CardLayout c = (CardLayout)senterpanel.getLayout();
            c.show(senterpanel,VISSTATISTIKK);
    }
    
    public String[] finnår(){
        /*Denne metoden returnerer en array av hvilke år resepter har blitt 
        skrevet ut*/
        String[] aktuelleår = new String[]{"ingen resepter i registeret"};
        TreeMap<Integer,String> årsliste = new TreeMap<>();
        int i=0;
        Resept løper;
        String årsløper;
        if(!reseptliste.isEmpty()||reseptliste!=null){
            for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
                løper = entry.getValue();
                Calendar reseptkalenderformat = løper.getKalenderformat();
                int år = reseptkalenderformat.get(Calendar.YEAR);
                årsliste.put(år, "");
            }//End of for-løkke
            aktuelleår = new String[årsliste.size()];
            for(Map.Entry<Integer,String> entry:årsliste.entrySet()){
                årsløper = "" + entry.getKey();
                aktuelleår[i]= årsløper;
            }//End of for-løkke
        }
        return aktuelleår;
    }
    
    public void genererKordinatlisteEn(){
        /*Denne metoden generer kordinater for det året valgt i comboboxen*/
        try{
            kordinateren = new int[12];
            kordinaterto = new int[12];
            grafikk.setNyeKordinaterFørste(kordinateren);
            grafikk.setNyeKordinaterAndre(kordinaterto);
            grafikk.genererPunkter();
            Resept løper;
            TreeMap<Integer,String> årsliste = new TreeMap<>();
            int n = årførste.getSelectedIndex();
            int år = Integer.parseInt((String)årførste.getItemAt(n));
            for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
                løper = entry.getValue();
                Calendar reseptkalenderformat = løper.getKalenderformat();
                if(år==reseptkalenderformat.get(Calendar.YEAR)){
                    int mnd = reseptkalenderformat.get(Calendar.MONTH);
                    switch(mnd){
                    case 0: kordinateren[0]++;//Jan
                        break;
                    case 1: kordinateren[1]++;//Feb
                        break;
                    case 2: kordinateren[2]++;//Mar
                        break;
                    case 3: kordinateren[3]++;//Apr
                        break;
                    case 4: kordinateren[4]++;//Mai
                        break;
                    case 5: kordinateren[5]++;//Jun
                        break;
                    case 6: kordinateren[6]++;//Jul
                        break;
                    case 7: kordinateren[7]++;//Aug
                        break;
                    case 8: kordinateren[8]++;//Sep
                        break;
                    case 9: kordinateren[9]++;//Okt
                        break;
                    case 10: kordinateren[10]++;//Nov
                        break;
                    case 11: kordinateren[11]++;//Des
                        break;
                    }
                }
                
            }//End of for-løkke
        }
        catch(NullPointerException np){
            
        }
        grafikk.setNyeKordinaterFørste(kordinateren);
        grafikk.genererPunkter();
        grafikk.repaint();
        grafikk.revalidate();
    }
    
    public void genererKordinatlisteTo(){
        /*Denne metoden generer kordinater for begge årene valgt i comboboxene*/
        try{
            kordinateren = new int[12];
            kordinaterto = new int[12];
            grafikk.setNyeKordinaterFørste(kordinateren);
            grafikk.setNyeKordinaterAndre(kordinaterto);
            grafikk.genererPunkter();
            Resept løper;
            int n = årførste.getSelectedIndex();
            int år = Integer.parseInt((String)årførste.getItemAt(n));
            int m = årandre.getSelectedIndex();
            int årto = Integer.parseInt((String)årandre.getItemAt(n));
            for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
                løper = entry.getValue();
                Calendar reseptkalenderformat = løper.getKalenderformat();
                if(år==reseptkalenderformat.get(Calendar.YEAR)){
                    int mnd = reseptkalenderformat.get(Calendar.MONTH);
                    System.out.println(mnd);
                    switch(mnd){
                    case 0: kordinateren[0]++;//Jan
                        break;
                    case 1: kordinateren[1]++;//Feb
                        break;
                    case 2: kordinateren[2]++;//Mar
                        break;
                    case 3: kordinateren[3]++;//Apr
                        break;
                    case 4: kordinateren[4]++;//Mai
                        break;
                    case 5: kordinateren[5]++;//Jun
                        break;
                    case 6: kordinateren[6]++;//Jul
                        break;
                    case 7: kordinateren[7]++;//Aug
                        break;
                    case 8: kordinateren[8]++;//Sep
                        break;
                    case 9: kordinateren[9]++;//Okt
                        break;
                    case 10: kordinateren[10]++;//Nov
                        break;
                    case 11: kordinateren[11]++;//Des
                        break;
                    }
                }
                
            }//End of for-løkke
            for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
                løper = entry.getValue();
                Calendar reseptkalenderformat = løper.getKalenderformat();
                if(årto==reseptkalenderformat.get(Calendar.YEAR)){
                    int mnd = reseptkalenderformat.get(Calendar.MONTH);
                    System.out.println(mnd);
                    switch(mnd){
                    case 0: kordinaterto[0]++;//Jan
                        break;
                    case 1: kordinaterto[1]++;//Feb
                        break;
                    case 2: kordinaterto[2]++;//Mar
                        break;
                    case 3: kordinaterto[3]++;//Apr
                        break;
                    case 4: kordinaterto[4]++;//Mai
                        break;
                    case 5: kordinaterto[5]++;//Jun
                        break;
                    case 6: kordinaterto[6]++;//Jul
                        break;
                    case 7: kordinaterto[7]++;//Aug
                        break;
                    case 8: kordinaterto[8]++;//Sep
                        break;
                    case 9: kordinaterto[9]++;//Okt
                        break;
                    case 10: kordinaterto[10]++;//Nov
                        break;
                    case 11: kordinaterto[11]++;//Des
                        break;
                    }
                }
                
            }//End of for-løkke
        }
        catch(NullPointerException np){
            
        }
        grafikk.setNyeKordinaterFørste(kordinateren);
        grafikk.setNyeKordinaterAndre(kordinaterto);
        grafikk.genererPunkter();
        grafikk.repaint();
        grafikk.revalidate();
    }
    
    public void visMedisinoversikten(){
        //Denne metoden viser med
        senterpanelgrense.setTitle("Varsling");
        repaint();
        oppDaterTabelenMedisin();
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.show(senterpanel,VISMEDISIN);
    }
    
    private void oppDaterTabelenMedisin(){
        /*Denne metoden legger til data i tabellen av typen 
        MedisinTabellModell ved å sende inn en årsreseptliste bassert 
        på året valgt i comboboxen*/
        TreeMap<Integer,Resept> årsreseptliste = new TreeMap<>();
        Resept løper;
        int n = medisinår.getSelectedIndex();
        int året = Integer.parseInt((String)medisinår.getItemAt(n));
        for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
                løper = entry.getValue();
                Calendar reseptkalenderformat = løper.getKalenderformat();
                int løperår = reseptkalenderformat.get(Calendar.YEAR);
                if(året==løperår){
                    årsreseptliste.put(løper.getReseptnr(),løper);
                }
        }//End of for-løkke
        vismedisintabell.nyInnDataMedisin(årsreseptliste);
    }
    
    private class Tegnlytter implements ItemListener{
        /*Denne lytteren sjekker til en hver tid hva bruker velger av checkboxer
        og setter riktige boolske verdier til hjelpefeltene for å sette riktig
        reseptbevilgning på leger som skal få ny reseptbevilgning*/
        public void itemStateChanged(ItemEvent e){
            gruppeA = Abox.isSelected();
            gruppeB = Bbox.isSelected();
            gruppeC = Cbox.isSelected();
        }
    }
    
    private class Lytter implements ActionListener{
        //Denne lytteren finner ut hva brukeren klikker på i panelet
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==gåtilbake){
                tilbakeTilMeny();
            }
            else if (e.getSource()==visreseptreg){
                visDataReseptreg();
            }
            else if(e.getSource()==statistikk){
                 visStatistikk();
            }
            else if(e.getSource()==medisinovsikt){
                visMedisinoversikten();
            }
            else if(e.getSource()==velglege){
                oppDaterTabelenLege();
            }
            else if(e.getSource()==velgpasient){
                oppDaterTabelenPasient();
            }
            else if(e.getSource()==visalle){
                oppDaterTabelen();
            }
            else if(e.getSource()==velgReseptKat){
                oppDaterTabelenKategori(VALG);
            }
            else if(e.getSource()==vispasient){
                visPasientCombo();
            }
            else if(e.getSource()==vislege){
                visLegeCombo();
            }
            else if(e.getSource()==vispersonreg){
                visPersonReg();
            }
            else if(e.getSource()==visalleleger){
                visPersonReg();
            }
            else if(e.getSource()==velglegepreg){
                enLegeiTabelen();
            }
            else if(e.getSource()==visallepasienter){
                oppDaterPasientTabelen();
            }
            else if(e.getSource()==velgpasientpreg){
                enPasientiTabelen();
            }
            else if(e.getSource()==pasientmedisin){
                medisinPasientiTabelen();
            }
            else if(e.getSource()==legemedisin){
                medisinLegeiTabelen();
            }
            else if(e.getSource()==endreresept){
                visEndreReseptBev();
            }
            else if(e.getSource()==fullfør){
                endreResept();
            }
            else if(e.getSource()==tilbake){
                visPersonReg();
            }
            else if(e.getSource()==visgrafen){
                genererKordinatlisteEn();
            }
            else if(e.getSource()==visgrafto){
                genererKordinatlisteTo();
            }
            else if(e.getSource()==medisinår){
                oppDaterTabelenMedisin();
            }
        }   
 
    }
}//End of Class KntrollørPanel