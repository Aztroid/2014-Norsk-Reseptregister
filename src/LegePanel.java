/*Hovedprosjekt Dats-1600
GRUPPE 6
William B. Wold, s183670, HIINGDATA13H1AA
Vegar Nygård, s193362, HIINGDATA13H1AA
 */

//Sist redigert: 12.05.2014

/*Dette panelet er vinduet hvor den innloggede legen får oversikt/kan registrere
  nye resepter, det er kun her resepter kan registreres*/

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class LegePanel extends JPanel{
    
    //Felles Datafelter
    private Lytter lytteren;
    private TreeMap<Integer,Resept> reseptliste;
    private TreeMap<Integer,Resept> spesifikkreseptliste;
    private TreeMap<String,Pasient> pasientliste;
    private TreeMap<String,Pasient> spesifikkpasientliste;
    private String legensautnr; //Identifiserer legen som er logget inn
    private String reseptbevilgning; //Identifiserer legen reseptbevilgning
    private Hovedramme hovedrammekopi;
    private final int PASIENT = 1;
    private final int RESEPT = 2;
    
    //Sidepanel datafelter
    private JPanel sidepanel;
    private JTextArea infofelt;
    private Border sidepanelgrense;
    private JScrollPane infoscroll;
    private JButton nypasient,nyresept,gåtilbake,visdata;
    
    //Senterpanel datafelter
    
    //CardLayout identifikatorer
    private final String VISDATA = "1";
    private final String NY_PASIENT = "2";
    private final String NY_RESEPT = "3";
    
    private JPanel senterpanel;
    private TitledBorder senterpanelgrense;
    
    //Senterpanel "visdata" datafelter
    private JPanel senterpanelvisdata, senterpaneltop;
    private JButton visalle, velgpasienter;
    private JComboBox vislegenspasienter;
    private TabellVindu resepttabell;
    
    //Senterpanel "ny pasient" datafelter
    private JPanel senterpanelnypasient;
    private GridBagConstraints c;
    private JTextField fornavnpasient, etternavnpasient, fødselsnr;
    private JButton regpasient;
    
    //Senterpanel "ny resept" datafelter
    private JPanel senterpanelnyresept;
    private static Integer reseptnøkkel; //Reseptnummeret
    private JTextField mengde, defdøgndosering, reseptgruppefelt;
    private JTextArea anvisning;
    private JButton regresept,velgpasient;
    private JComboBox legenspasienter, reseptkategorier, medisinnøkkeler;
    private String[] items,pasienter;
    private char reseptgruppe;
    private MedisinBibliotek medisinbiblioteket;
    
    public LegePanel(String autnr,String reseptgodkjennelse,  
            TreeMap<String,Pasient> pasientliste, 
            TreeMap<Integer,Resept> reseptliste){
        
        super.setLayout(new BorderLayout());
        lytteren = new Lytter();
        legensautnr = autnr;
        this.pasientliste = pasientliste;
        this.reseptliste = reseptliste;
        spesifikkreseptliste = new TreeMap<>();
        spesifikkpasientliste = new TreeMap<>();
        reseptbevilgning = reseptgodkjennelse;
        filtrerReseptlisten();//se i metode for beskrivelse
        filtrerPasientlisten();//se i metode for beskrivelse
        
        //Sidepanel
        sidepanel = new JPanel();
        sidepanel.setLayout(new GridLayout(8,1,3,3));
        sidepanelgrense = BorderFactory.createTitledBorder("Navigering");
        sidepanel.setBorder(sidepanelgrense);
        
        //Sidepanel infofelt
        infofelt = new JTextArea(4,18);
        infoscroll = new JScrollPane(infofelt);
        infofelt.setText("Oversikt over resepter for:\n" + "Lege: " +
                legensautnr);
        infofelt.setEditable(false);
        sidepanel.add(infoscroll);
        
        //Sidepanel knapper
        gåtilbake = new JButton("Tilbake til meny");
        gåtilbake.addActionListener(lytteren);
        sidepanel.add(gåtilbake);
        
        visdata = new JButton("Dine Resepter");
        visdata.addActionListener(lytteren);
        sidepanel.add(visdata);
        
        nypasient = new JButton("Ny Pasient");
        nypasient.addActionListener(lytteren);
        sidepanel.add(nypasient);
        
        nyresept = new JButton("Ny Resept");
        nyresept.addActionListener(lytteren);
        sidepanel.add(nyresept);
        
        //Senterpanel hovedramme
        senterpanel = new JPanel(new CardLayout());
        senterpanelgrense = BorderFactory.createTitledBorder("Dine Resepter");
        senterpanel.setBorder(senterpanelgrense);
         
        //Senterpanel "visdata"
        senterpanelvisdata = new JPanel(new BorderLayout());
        senterpaneltop = new JPanel(new FlowLayout());
        
        visalle = new JButton("Vis Alle");
        visalle.addActionListener(lytteren);
        senterpaneltop.add(visalle);
           
        senterpaneltop.add(new JLabel("Pasient: "));
        velgpasienter = new JButton("Velg Pasient");
        velgpasienter.addActionListener(lytteren);
        senterpaneltop.add(velgpasienter);
        senterpanelvisdata.add(senterpaneltop,BorderLayout.PAGE_START);
        
        //"visdata" tabell
        resepttabell = new TabellVindu();
        senterpanelvisdata.add(resepttabell,BorderLayout.CENTER);
        
        //Senterpanel "ny Pasient"
        senterpanelnypasient = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 5;
        c.ipady = 5;
        
        senterpanelnypasient.add(new JLabel("Fødselsnr:"),c);
        c.gridx = 1;
        fødselsnr = new JTextField(15);
        fødselsnr.addActionListener(lytteren);
        senterpanelnypasient.add(fødselsnr,c);
        c.gridx = 0;
        
        c.gridy = 1;
        senterpanelnypasient.add(new JLabel("Fornavn:"),c);
        c.gridx = 1;
        fornavnpasient = new JTextField(15);
        fornavnpasient.addActionListener(lytteren);
        senterpanelnypasient.add(fornavnpasient,c);
        c.gridx = 0;
        
        c.gridy = 2;
        senterpanelnypasient.add(new JLabel("Etternavn Pasient: "),c);
        c.gridx = 1;
        etternavnpasient = new JTextField(15);
        etternavnpasient.addActionListener(lytteren);
        senterpanelnypasient.add(etternavnpasient,c);
        c.gridx = 0;
        
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        regpasient = new JButton("Register Pasient");
        regpasient.addActionListener(lytteren);
        senterpanelnypasient.add(regpasient,c);

        //Senterpanel "ny Resept"
        medisinbiblioteket = new MedisinBibliotek();
        senterpanelnyresept = new JPanel(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 5;
        c.ipady = 5;
        
        senterpanelnyresept.add(new JLabel("Pasient(Fnr): "),c);
        c.gridx = 0;
        
        c.gridy = 1;
        senterpanelnyresept.add(new JLabel("Kategori: "),c);
        c.gridx = 1;
        items = new String[] { "A - FORDØYELSESORGANER OG STOFFSKIFTE", 
            "B - BLOD OG BLODDANNENDE ORGANER", 
            "C - HJERTE OG KRETSLØP", 
            "D - DERMATOLOGISKE MIDLER", 
            "G - UROGENITALSYSTEM OG KJØNNSHORMONER",
            "H - HORMONER TIL SYSTEMISK BRUK, EKSKL. KJØNNSHORMONER OG INSULIN",
            "J - ANTIINFEKTIVER TIL SYSTEMISK BRUK",
            "L - ANTINEOPLASTISKE OG IMMUNMODULERENDE MIDLER",
            "M - MUSKLER OG SKJELETT",
            "N - NERVESYSTEMET",
            "P - ANTIPARASITÆRE MIDLER, INSEKTICIDER OG INSEKTMIDLER",
            "R - RESPIRASJONSORGANER",
            "S - SANSEORGANER",
            "V - VARIA" };
        reseptkategorier = new JComboBox(items);
        reseptkategorier.addActionListener(lytteren);
        senterpanelnyresept.add(reseptkategorier,c);
        c.gridx = 0;
        
        c.gridy = 2;
        senterpanelnyresept.add(new JLabel("ATC-Nr: "),c);
        c.gridx = 1;
        medisinnøkkeler = new JComboBox();
        medisinnøkkeler.addActionListener(lytteren);
        senterpanelnyresept.add(medisinnøkkeler,c);
        c.gridx = 0;
        
        c.gridy = 3;
        senterpanelnyresept.add(new JLabel("Mengde(gr): "),c);
        c.gridx = 1;
        mengde = new JTextField(30);
        senterpanelnyresept.add(mengde,c);
        c.gridx = 0;
        
        c.gridy = 4;
        senterpanelnyresept.add(new JLabel("Diag.Døgn: "),c);
        c.gridx = 1;
        defdøgndosering = new JTextField(30);
        senterpanelnyresept.add(defdøgndosering,c);
        c.gridx = 0;
        
        
        c.gridy = 5;
        senterpanelnyresept.add(new JLabel("Reseptgruppe: "),c);
        c.gridx = 1;
        reseptgruppefelt = new JTextField(5);
        reseptgruppefelt.setEditable(false);
        senterpanelnyresept.add(reseptgruppefelt,c);
        c.gridx = 0;
        
        c.gridy = 6;
        senterpanelnyresept.add(new JLabel("Legens anv:"),c);
        c.gridx = 1;
        anvisning = new JTextArea(10, 30);
        anvisning.setEditable(true);
        senterpanelnyresept.add(new JScrollPane(anvisning),c);
        
        c.gridy = 7;
        regresept = new JButton("Register Resept");
        c.gridx = 1;
        regresept.addActionListener(lytteren);
        senterpanelnyresept.add(regresept,c);
        
        //LEGGER ALLE PANELER TIL
        senterpanel.add(senterpanelvisdata, VISDATA);
        senterpanel.add(senterpanelnypasient, NY_PASIENT);
        senterpanel.add(senterpanelnyresept, NY_RESEPT);
        super.add(sidepanel, BorderLayout.LINE_START);
        super.add(senterpanel, BorderLayout.CENTER);
        oppDaterTabelen();
    }
    
    public void filtrerReseptlisten(){
        /*Denne metoden finner alle resepter den innloggede legen har skrevet
        ut, og legger de til i den spesifike reseptlisten, denne lages på nytt
        for hver lege som logger inn, og hver gang legen registrerer en ny
        resept*/
        Resept løper;
        for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
            løper = entry.getValue();
            if(legensautnr.equalsIgnoreCase(løper.getLege())){
                spesifikkreseptliste.put(løper.getReseptnr(),løper);
            }
        }
    }
    
    public void filtrerPasientlisten(){
        /*Denne metoden finner alle resepter den innloggede legen har skrevet
        ut, og henter pasientene samt putter de inn i en spesifik pasientliste 
        for den innloggede legen. Denne lages på nytt for hver lege som logger 
        inn, og hver gang legen registrerer en ny resept*/
        Pasient løper;
        for(Map.Entry<String,Pasient> entry:pasientliste.entrySet()){
            løper = entry.getValue();
            if(legensautnr.equalsIgnoreCase(løper.getLege())){
                spesifikkpasientliste.put(løper.getFødselsnr(),løper);
            }
        }
    }
    
    public void tilbakeTilMeny(){
        /*Denne metoden leder brukeren tilbake til startvinduet for en ny 
        innlogging, og sender med listene uansett om det har vørt forandringer
        eller ikke*/
        hovedrammekopi = (Hovedramme) SwingUtilities.getWindowAncestor(this);
        hovedrammekopi.visFørsteLege(pasientliste,reseptliste);
    }
    
    public void nyPasient(){
        /*Denne metoden viser "ny pasient" panelet som inneholder alle 
        datafelter for å registrere nye pasienter for den innloggede legen*/
        infofelt.setText("Registrer en ny pasient");
        senterpanelgrense.setTitle("Ny Pasient");
        repaint();
        if(legenspasienter!=null){
            senterpanelnyresept.remove(legenspasienter);
        }
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.show(senterpanel,NY_PASIENT);
    }
    
    public void nyResept(){
        regResepter();
        /*Denne metoden viser "ny resept" panelet som inneholder alle datafelter 
        for å registrere nye resepter for den innloggede legen*/
        infofelt.setText("Registrer en ny resept på en\nav dine pasienter");
        senterpanelgrense.setTitle("Ny Resept");
        leggTilCombobox();
        finnRiktigMedisinArray();
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.show(senterpanel,NY_RESEPT);
    }
    
    public void visData(){
        /*Denne metoden viser "vis data" panelet som inneholder tabellen og
        søkefeltene for tabellen*/
        senterpanelgrense.setTitle("Dine Resepter");
        oppDaterTabelen();
        repaint();
        if(vislegenspasienter!=null){
            senterpaneltop.remove(vislegenspasienter);
            repaint();
        }
        velgpasienter.setVisible(true);
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.show(senterpanel,VISDATA);
    }
    
    private void oppDaterTabelen(){
        /*Denne metoden generer en ny tabell bassert på den innloggede legens 
        utskrevende resepter, og legger tabellen til i "vis data" panelet*/
        try{
            resepttabell.nyInnDataResept(spesifikkreseptliste);
        }
        catch(NullPointerException np){
            System.out.println("Nullpointer");
        }
    }
    
    private void oppDaterTabelenPasient(){
        /*Denne metoden generer en ny tabell bassert på den innloggede legens 
        valg av pasient, og legger tabellen til i "vis data" panelet*/
        TreeMap<Integer,Resept> spesifikkpasientreseptliste = new TreeMap<>();
        int n = vislegenspasienter.getSelectedIndex();
        String fullid = (String)vislegenspasienter.getItemAt(n);
        String pasientnøkkel = fullid.substring(0, 11);
        Resept løper;
        for(Map.Entry<Integer,Resept> entry:spesifikkreseptliste.entrySet()){
            løper = entry.getValue();
            if(pasientnøkkel.equalsIgnoreCase(løper.getPasient())){
                spesifikkpasientreseptliste.put(løper.getReseptnr(),løper);
            }
        }
        resepttabell.nyInnDataResept(spesifikkpasientreseptliste);
    }
    
    private boolean blankePersonfelter(){
        //Sjekker for blanke felter ved registrering av person
        return (fødselsnr.getText().matches("")||fornavnpasient.getText().
                matches("")||etternavnpasient.getText().matches(""));
    }
    
    private boolean blankeReseptfelter(){
        //Sjekker for blanke felter ved registrering av resept
        return (mengde.getText().matches("")||defdøgndosering.getText().
                matches(""));
    }
    
    private void regPasient(){
        /*Metoden registerer en ny pasient om pasientnøkkel er gyldig, alle 
        felter er fylt ut og om pasienten ikke eksisterer fra før*/
        if(blankePersonfelter()){
            infofelt.setText("Et eller fler av feltene er tomme");
            return;
        }
        String fødselsnrrregex = "\\d{11}";
        String pasientnøkkel = fødselsnr.getText();
        String fornavn = fornavnpasient.getText();
        String etternavn = etternavnpasient.getText();
        if(pasientliste.get(pasientnøkkel)==null){
            if(pasientnøkkel.matches(fødselsnrrregex)){
                Pasient ny = new Pasient(fornavn, etternavn, pasientnøkkel,
                        legensautnr);
                pasientliste.put(pasientnøkkel,ny);
                lagreLegeListene(PASIENT);
                infofelt.setText("Person registrert.");
                filtrerPasientlisten();
            }
            else{
                infofelt.setText("Fødselsnummeret du har skrevet\ninn er ikke "
                        + "et gyldig fødselsnummer");
            }
        }
        else{
            infofelt.setText("Pasienten finnes\ni registeret fra før");
        }
    }
    
    private void regResept(){
        /*Metoden registerer en resept gitt at alle parametere er riktig
        utfyllt, at legen har riktig reseptbevilgning for preparatet*/
        if(blankeReseptfelter()){
            infofelt.setText("Et eller fler av feltene er tomme");
            return;
        }
        String fødselsnrrregex = "\\d{11}";
        int m = legenspasienter.getSelectedIndex();
        String fullid = (String)legenspasienter.getItemAt(m);
        String pasientnøkkel = fullid.substring(0, 11);
        String legenøkkel = legensautnr;
        int n = medisinnøkkeler.getSelectedIndex();
        String medisinnøkkel = (String)medisinnøkkeler.getItemAt(n);
        medisinnøkkel = medisinnøkkel.substring(0,8);
        String medisinmengde = mengde.getText();
        String DDD = defdøgndosering.getText();
        int o = reseptkategorier.getSelectedIndex();
        String medisinkategori = (String)reseptkategorier.getItemAt(o);
        String legensanvisning = anvisning.getText();
        if(reseptliste.isEmpty()){
            reseptnøkkel = 1;
        }
        else{
            reseptnøkkel = 1+reseptliste.lastKey();
        }
        if(!pasientnøkkel.matches(fødselsnrrregex)){
            infofelt.setText("Velg en pasient eller opprett en ny\npasient"
                    + " ved å klikke på \"Ny Pasient\"\n-knappen");
            return;
        }
        else if(pasientliste.get(pasientnøkkel)==null){
            infofelt.setText("Pasienten er ikke registrert");
            return;
        }
        else{
            int testen = reseptbevilgning.indexOf(reseptgruppe);
            if(testen==-1){
                infofelt.setText("Legen er ikke godkjet for denne\nresepten");
                return;
            }
            else{
                Resept ny = new Resept(reseptnøkkel, pasientnøkkel,legenøkkel,
                        medisinnøkkel, medisinmengde,DDD,medisinkategori,
                        reseptgruppe,legensanvisning);
                reseptliste.put(ny.getReseptnr(),ny);
                infofelt.setText("Resept registrert.");
                reseptnøkkel++;
                lagreLegeListene(RESEPT);
                filtrerReseptlisten();
                senterpanelnyresept.remove(legenspasienter);
                revalidate();
                repaint();
                nyResept();
            }
        }
    }
    
    public void regResepter(){
        /*Metoden registerer en resept gitt at alle parametere er riktig
        utfyllt, at legen har riktig reseptbevilgning for preparatet*/
        /*  (Integer n, String f, String a, String med, String m, String d,
        String k, char rg, String an)
         */
        int år = 2010;
        Integer reseptnr = 1;
        String fnr;
        String autnr;
        String medisinnøkkel;
        String mengde = "1g";
        String DDD = "2g";
        String medisinkategori = "";
        char reseptkategori;
        String legensanv = "Etter behov.";
        String [] medliste = medisinbiblioteket.getKodearrays();
        Calendar dato;
        Pasient løper;
        Resept nyresept;
        for(Map.Entry<String,Pasient> entry:pasientliste.entrySet()){
            int tilfeldig2 = (int) (Math.random() * 4);
            int tilfeldig1 = (int) (Math.random() * 12);            
            int tilfeldig = (int) (Math.random() * medliste.length);
            String fullid = medliste[tilfeldig];
            System.out.println(fullid);
            løper = entry.getValue();
            fnr = løper.getFødselsnr();
            autnr = løper.getLege();
            medisinnøkkel = fullid.substring(0, 8);
            int q = fullid.length() -1;
            reseptkategori = fullid.charAt(q);
            
            for(int i = 0; i < items.length; i++){
                char sammenligner = fullid.charAt(0);
                if(sammenligner == items[i].charAt(0))
                    medisinkategori = items[i];
            }

            nyresept = new Resept(reseptnr,  fnr,  autnr,  medisinnøkkel,  mengde,  DDD,  medisinkategori, reseptkategori,  legensanv);
            dato = nyresept.getKalenderformat();
            dato.set(Calendar.YEAR, (år + tilfeldig2));
            dato.set(Calendar.MONTH,(tilfeldig1));
            nyresept.setCalendar(dato);
            reseptnr++;
            reseptliste.put(nyresept.getReseptnr(),nyresept);
            infofelt.setText("Resept registrert.");
            lagreLegeListene(RESEPT);
            filtrerReseptlisten();
            }           
    }
    
    public String[] pasientListen(){
        /*Denne metoden generer en arrayliste av alle pasientene til den 
        innloggede legen*/
        if(!spesifikkpasientliste.isEmpty()){
            Pasient løper;
            pasienter = new String[spesifikkpasientliste.size()];
            int i = 0;
            for(Map.Entry<String,Pasient> entry:spesifikkpasientliste.
                    entrySet()){
                løper = entry.getValue();
                pasienter[i]=løper.toString();
                i++;
            }
            return pasienter;
        }
        pasienter = new String[]{"Ingen pasienter opprettet"};
        return pasienter;
    }
    
    public void leggTilCombobox(){
        /*Denne metoden lgger til en oppdatert combobox med legens pasienter
        i nyresept panelet*/
        filtrerPasientlisten();
        c.gridx = 1;
        c.gridy = 0;
        legenspasienter = new JComboBox(pasientListen());
        legenspasienter.addActionListener(lytteren);
        senterpanelnyresept.add(legenspasienter,c);
    }
    
    public void leggTilComboboxvisdata(){
        /*Denne metoden lgger til en oppdatert combobox med legens pasienter
        i visdata panelet*/
        filtrerPasientlisten();
        velgpasienter.setVisible(false);
        vislegenspasienter = new JComboBox(pasientListen());
        vislegenspasienter.addActionListener(lytteren);
        senterpaneltop.add(vislegenspasienter);
    }
    
    public void finnRiktigMedisinArray(){
        /*Denne metoden velger ut en preparatarray fra Medisinbiblioteket 
        ut ifra hvilken kategori som er valgt av legen*/
        senterpanelnyresept.remove(medisinnøkkeler);
        revalidate();
        repaint();
        int m = reseptkategorier.getSelectedIndex();
        String medisinkategori = (String)reseptkategorier.getItemAt(m);
        char sorteringsbokstav = medisinkategori.charAt(0);
        c.gridy = 2;
        c.gridx = 1;
        String[] lista = medisinbiblioteket.getKodearray(sorteringsbokstav);
        medisinnøkkeler = new JComboBox(lista);
        medisinnøkkeler.addActionListener(lytteren);
        senterpanelnyresept.add(medisinnøkkeler,c);
        finnReseptgruppen();
        revalidate();
        repaint();
    }
    
    public void finnReseptgruppen(){
        /*Denne metoden finner preparatets reseptgruppe utifra 
        Medisinbiblioteket, vi har lagt til reseptgruppe som siste bokstav 
        i stringen for medisinnavn*/
        int m = medisinnøkkeler.getSelectedIndex();
        String medisinnøkkelen = (String)medisinnøkkeler.getItemAt(m);
        medisinnøkkelen = medisinnøkkelen.substring(0,8);
        TreeMap<String,String> liste = medisinbiblioteket.getBibliotek();
        String medisin = liste.get(medisinnøkkelen);
        try{
        m = medisin.length()-1;
        reseptgruppe = medisin.charAt(m);
        reseptgruppefelt.setText(""+reseptgruppe);
        }
        catch(NullPointerException no){
            
        }
    }
    
    public void lagreLegeListene(int n){
        /*Skriver listene i legepanel til fil etter intparameteren*/
        if(n==PASIENT){
            try(ObjectOutputStream utfil = new ObjectOutputStream(
                    new FileOutputStream("src/pasientliste.data"))){
                utfil.writeObject(pasientliste);
            }
            catch(NotSerializableException ns){
                JOptionPane.showMessageDialog(null,"Objektet er ikke "
                        + "serialisert");
            }
            catch(IOException ioe){
                JOptionPane.showMessageDialog(null,"Problem med utskrift til "
                        + "fil");
            }
        }
        else if(n==RESEPT){
            try(ObjectOutputStream utfil = new ObjectOutputStream(
                    new FileOutputStream("src/reseptliste.data"))){
                utfil.writeObject(reseptliste);
            }
            catch(NotSerializableException ns){
                JOptionPane.showMessageDialog(null,"Objektet er ikke "
                        + "serialisert");
            }
            catch(IOException ioe){
                JOptionPane.showMessageDialog(null,"Problem med utskrift til "
                        + "fil");
            }
        }
    }
    
    private class Lytter implements ActionListener{
        //Denne lytteren finner ut hva brukeren klikker i panelet
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==gåtilbake){
                tilbakeTilMeny();
            }
            else if(e.getSource()==visdata){
                visData();
            }
            else if(e.getSource()==nypasient){
                nyPasient();
            }
            else if(e.getSource()==nyresept){
                nyResept();
            }
            else if(e.getSource()==regpasient){
                regPasient();
            }
            else if(e.getSource()==regresept){
                regResept();
            }
            else if(e.getSource()==reseptkategorier){
                finnRiktigMedisinArray();
            }
            else if(e.getSource()==medisinnøkkeler){
                finnReseptgruppen();
            }
            else if(e.getSource()==velgpasienter){
                leggTilComboboxvisdata();
            }
            else if(e.getSource()==vislegenspasienter){
                oppDaterTabelenPasient();
            }
            else if(e.getSource()==visalle){
                oppDaterTabelen();
            }
        }
    }
}//End of Class LegePanel
