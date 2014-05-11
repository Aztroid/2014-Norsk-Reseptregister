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
    private final String VISREG = "10";
    private final String VISSTATISTIKK = "11";
    private final String VISMEDISIN = "12";
    private int VALG = 1;
    
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
    private JButton gåtilbake,visreseptreg,vispersonreg,statistikk,varsling;
    
    //Semterpanel datafelter
    private JPanel senterpanel;
    private TitledBorder senterpanelgrense;
    private String[] leger,pasienter,medisiner;
    private MedisinBibliotek medisinbiblioteket;
    
    //Senterpanel visdata
    private JPanel senterpanelvisdata, visdatanorth;
    private TabellVindu visdatatabell;
    private JComboBox velglege, velgpasient, velgReseptKat;
    private JButton visalle, vispasient,vislege;
    
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
        
        super.setLayout(new BorderLayout());
        lytteren = new Lytter();
        this.pasientliste = pasientliste;
        this.legeliste = legeliste;
        this.reseptliste = reseptliste;
        medisinbiblioteket = new MedisinBibliotek();
        tegnlytteren = new Tegnlytter();
        
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
        grafikk = new Statistikkpanel(kordinateren);
        
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
        
        visgrafto = new JButton("Vis år 2");
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
            
    public String[] pasientlisten(){
        if(pasientliste!=null){
            Pasient løper;
            pasienter = new String[pasientliste.size()];
            int i = 0;
            for(Map.Entry<String,Pasient> entry:pasientliste.entrySet()){
                løper = entry.getValue();
                pasienter[i]=løper.toString();
                i++;
            }
            return pasienter;
        }
        leger = new String[]{"Ingen pasienter opprettet"};
        return pasienter;
    }
    
    public void visPasientCombo(){
        velglege.setVisible(false); 
        velgpasient.setVisible(true);
        vislege.setVisible(true);
        vispasient.setVisible(false);
        VALG=2;
    }
            
    public void visLegeCombo(){
        velgpasient.setVisible(false); 
        velglege.setVisible(true);
        vislege.setVisible(false);
        vispasient.setVisible(true);
        VALG=1;
    }
    
    public String[] medisinlisten(){
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
            }
            i=0;
            medisiner = new String[utskrvedemedisiner.size()];
            for(Map.Entry<String,String> entry:utskrvedemedisiner.entrySet()){
            medisinnøkkel = entry.getKey();
            medisinnavn = entry.getValue();
            medisiner[i] = medisinnøkkel + ", " + medisinnavn;
            i++;
            }
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
        }
        visdatatabell.nyInnDataResept(spesifikklegereseptliste);
    }
 
    private void oppDaterTabelenKategori(int id){
        /*Denne metoden generer en ny tabell bassert på legen som blir valgt i
        comboboxen, og legger tabellen til i "vis data" panelet*/
        TreeMap<Integer,Resept> spesifikklegereseptliste = new TreeMap<>();
        TreeMap<Integer,Resept> spesifikkReseptgruppreseptliste = new TreeMap<>();
        if(id==1){
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
            for(Map.Entry<Integer,Resept> entry:spesifikklegereseptliste.entrySet()){
                løper = entry.getValue();
                if(reseptgruppe==(løper.getReseptgruppe())){
                    spesifikkReseptgruppreseptliste.put(løper.getReseptnr(),løper);
                }
            }
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
            }
            int m = velgReseptKat.getSelectedIndex();
            String reseptgruppeid = (String)velgReseptKat.getItemAt(m);
            char reseptgruppe = reseptgruppeid.charAt(13);
            for(Map.Entry<Integer,Resept> entry:spesifikklegereseptliste.entrySet()){
                løper = entry.getValue();
                if(reseptgruppe==(løper.getReseptgruppe())){
                    spesifikkReseptgruppreseptliste.put(løper.getReseptnr(),løper);
                }
            }
            visdatatabell.nyInnDataResept(spesifikkReseptgruppreseptliste);
        }
    }
    
    private void oppDaterTabelenPasient(){
        /*Denne metoden generer en ny tabell bassert på den innloggede legens 
        utskrevende resepter, og legger tabellen til i "vis data" panelet*/
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
        }
        visdatatabell.nyInnDataResept(spesifikkpasientreseptliste);
    }
    
    public void visPersonReg(){
        /*Denne metoden viser "vis data" panelet som inneholder tabellen og
        søkefeltene for tabellen*/
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
        /*Denne metoden generer en ny tabell bassert på utskrevende resepter, 
        og legger tabellen til i "vis data" panelet*/
        vispersonregtabell.nyInnDataLege(legeliste);
    }
    
    private void enLegeiTabelen(){
        /*Denne metoden generer en ny tabell bassert på utskrevende resepter, 
        og legger tabellen til i "vis data" panelet*/
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
        /*Denne metoden generer en ny tabell bassert på utskrevende resepter, 
        og legger tabellen til i "vis data" panelet*/
        vispersonregtabell.nyInnDataPasient(pasientliste);
    }
    
    private void enPasientiTabelen(){
        /*Denne metoden generer en ny tabell bassert på utskrevende resepter, 
        og legger tabellen til i "vis data" panelet*/
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
            }
        vispersonregtabell.nyInnDataLege(medisinlegeliste);
        }
        else{
            infofelt.setText("Ingen lege funnet");
        }
    }
    
    public void medisinPasientiTabelen(){
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
            }
        vispersonregtabell.nyInnDataPasient(medisinpasientliste);
        }
        else{
            infofelt.setText("Ingen pasient funnet");
        }
    }
    
    public void visEndreReseptBev(){
        vispersonregsouth.setVisible(true);
        infofelt.setText("Reseptendring for lege:\n" + 
                "\nSkriv ny reseptbevilgning inn\ni feltet (ABC,BC evt)");
    }
    
    public void endreResept(){
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
        infofelt.setText("Dette panelet viser en statistisk\n"
                + "representasjon av resepter\nutskrevet i et "
                + "gitt år, her kan også to\når sammenliknes");
        senterpanelgrense.setTitle("Statistikk");
        repaint();
            CardLayout c = (CardLayout)senterpanel.getLayout();
            c.show(senterpanel,VISSTATISTIKK);
    }
    
    public String[] finnår(){
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
            }
            aktuelleår = new String[årsliste.size()];
            for(Map.Entry<Integer,String> entry:årsliste.entrySet()){
                årsløper = "" + entry.getKey();
                aktuelleår[i]= årsløper;
            }
        }
        return aktuelleår;
    }
    
    public void genererKordinatlisteEn(){
        /*Denne metoden skal generere kordinater til grafen avhengig 
        av hva man har klikket seg frem til i comboboxene*/
        try{
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
                    case 1: kordinateren[0]++;//Jan
                        break;
                    case 2: kordinateren[1]++;//Feb
                        break;
                    case 3: kordinateren[2]++;//Mar
                        break;
                    case 4: kordinateren[3]++;//Apr
                        break;
                    case 5: kordinateren[4]++;//Mai
                        break;
                    case 6: kordinateren[5]++;//Jun
                        break;
                    case 7: kordinateren[6]++;//Jul
                        break;
                    case 8: kordinateren[7]++;//Aug
                        break;
                    case 9: kordinateren[8]++;//Sep
                        break;
                    case 10: kordinateren[9]++;//Okt
                        break;
                    case 11: kordinateren[10]++;//Nov
                        break;
                    case 12: kordinateren[11]++;//Des
                        break;
                    }
                }
                
            }
        }
        catch(NullPointerException np){
            
        }
        //grafikk.setNyeKordinater(kordinater);
    }
    
    public void visVarsling(){
        senterpanelgrense.setTitle("Varsling");
        repaint();
        oppDaterTabelenMedisin();
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.show(senterpanel,VISMEDISIN);
    }
    
    private void oppDaterTabelenMedisin(){
        /*Denne metoden generer en ny tabell bassert på utskrevende resepter, 
        og legger tabellen til i "vis data" panelet*/
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
            }
        vismedisintabell.nyInnDataMedisin(årsreseptliste);
    }
    
    private class Tegnlytter implements ItemListener{
        public void itemStateChanged(ItemEvent e){
            gruppeA = Abox.isSelected();
            gruppeB = Bbox.isSelected();
            gruppeC = Cbox.isSelected();
        }
    }
    
    private class Lytter implements ActionListener{
        
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
            else if(e.getSource()==varsling){
                visVarsling();
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
            else if(e.getSource()==visgrafen){
                //genererKordinatlisteTo();
            }
            else if(e.getSource()==medisinår){
                //genererKordinatlisteTo();
            }
        }   
 
    }
}