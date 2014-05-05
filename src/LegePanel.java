/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA*/

/*Dette panelet er vinduet hvor den innloggede legen får oversikt/kan registrere
  nye resepter, det er kun her resepter kan registreres*/

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;

public class LegePanel extends JPanel{
    
    //Super Datafelter
    private final String VISDATA = "1";
    private final String NY_PASIENT = "2";
    private final String NY_RESEPT = "3";
    private Lytter lytteren;
    private TreeMap<Integer,Resept> reseptliste;
    private TreeMap<Integer,Resept> spesifikkreseptliste;
    private TreeMap<String,Pasient> pasientliste;
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
    private JPanel senterpanel;
    private Border senterpanelgrense;
    
    //Senterpanel "visdata" datafelter
    private JPanel senterpanelvisdata, senterpaneltop;
    private JTextField søkpasientid,søkreseptid;
    private JButton søk;
    private TabellVindu tabell;
    
    //Senterpanel "ny pasient" datafelter
    private JPanel senterpanelnypasient;
    private GridBagConstraints c;
    private JTextField fornavnpasient, etternavnpasient, fødselsnr;
    private JButton regpasient;
    
    //Senterpanel "ny resept" datafelter
    private JPanel senterpanelnyresept;
    private static Integer reseptnøkkel; //Reseptnummeret
    private RadioLytteren radiolytteren;
    private JTextField fødselsnrresept, medisinnøkkel, mengde, defdøgndosering;
    private JTextArea anvisning;
    private JButton regresept;
    private String kontrollresept;
    private JRadioButton gra, grb, grc;
    private ButtonGroup resteptgruppealt;
    private JComboBox reseptkategorier,undergruppe;
    private String[] items,ATCliste,A,B,C,D,G,H,J,L,M,N,P,R,S,V;
    private char reseptensgruppe = 'C';
    
    /*Konstruktøren får kun to lister da det ikke skal registreres annet av 
    legen*/
    public LegePanel(String autnr,String reseptgodkjennelse, 
            Integer sisteresept, 
            TreeMap<String,Pasient> pasientliste, 
            TreeMap<Integer,Resept> reseptliste){
        
        super.setLayout(new BorderLayout());
        lytteren = new Lytter();
        legensautnr = autnr;
        this.pasientliste = pasientliste;
        this.reseptliste = reseptliste;
        spesifikkreseptliste = new TreeMap<>();
        reseptbevilgning = reseptgodkjennelse;
        reseptnøkkel = ++sisteresept;
        
        //SIDEPANEL
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
        senterpanelgrense = BorderFactory.createTitledBorder("Reseptoversikt");
        senterpanel.setBorder(senterpanelgrense);
         
        //Senterpanel "visdata"
        senterpanelvisdata = new JPanel(new BorderLayout());
        
        senterpaneltop = new JPanel(new FlowLayout());
        søkpasientid = new JTextField(15);
        senterpaneltop.add(new JLabel("Søk Pasient"));
        senterpaneltop.add(søkpasientid);
        
        søkreseptid = new JTextField(15);
        senterpaneltop.add(new JLabel("Søk Resept"));
        senterpaneltop.add(søkreseptid);
        
        søk = new JButton("Søk");
        søk.addActionListener(lytteren);
        senterpaneltop.add(søk);
        senterpanelvisdata.add(senterpaneltop,BorderLayout.PAGE_START);
        
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
        radiolytteren = new RadioLytteren();
        senterpanelnyresept = new JPanel(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 5;
        c.ipady = 5;
        
        senterpanelnyresept.add(new JLabel("Pasient(Fnr): "),c);
        c.gridx = 1;
        fødselsnrresept = new JTextField(30);
        fødselsnrresept.addActionListener(lytteren);
        senterpanelnyresept.add(fødselsnrresept,c);
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
        senterpanelnyresept.add(new JLabel("Mengde(gr): "),c);
        c.gridx = 1;
        mengde = new JTextField(30);
        mengde.addActionListener(lytteren);
        senterpanelnyresept.add(mengde,c);
        c.gridx = 0;
        
        c.gridy = 3;
        senterpanelnyresept.add(new JLabel("Diag.Døgn: "),c);
        c.gridx = 1;
        defdøgndosering = new JTextField(30);
        defdøgndosering.addActionListener(lytteren);
        senterpanelnyresept.add(defdøgndosering,c);
        c.gridx = 0;
        
        c.gridy = 4;
        senterpanelnyresept.add(new JLabel("ATC-Nr: "),c);
        c.gridx = 1;
        undergruppe = new JComboBox(ATCliste);
        medisinnøkkel.addActionListener(lytteren);
        senterpanelnyresept.add(medisinnøkkel,c);
        c.gridx = 2;
        medisinnøkkel.addActionListener(lytteren);
        senterpanelnyresept.add(medisinnøkkel,c);
        c.gridx = 0;
        
        c.gridy = 5;
        senterpanelnyresept.add(new JLabel("Reseptgruppe: "),c);
        resteptgruppealt = new ButtonGroup();
        gra = new JRadioButton("Gruppe A", false);
        gra.addActionListener(radiolytteren);
        grb = new JRadioButton("Gruppe B", false);
        grb.addActionListener(radiolytteren);
        grc = new JRadioButton("Gruppe C", true);
        grc.addActionListener(radiolytteren);
        resteptgruppealt.add(gra);
        resteptgruppealt.add(grb);
        resteptgruppealt.add(grc);
        c.gridx = 1;
        c.gridy = 6;
        senterpanelnyresept.add(gra,c);
        c.gridy = 7;
        senterpanelnyresept.add(grb,c);
        c.gridy = 8;
        senterpanelnyresept.add(grc,c);
        c.gridx = 0;
        
        c.gridy = 9;
        senterpanelnyresept.add(new JLabel("Legens anv:"),c);
        c.gridx = 1;
        anvisning = new JTextArea(10, 30);
        anvisning.setEditable(true);
        senterpanelnyresept.add(new JScrollPane(anvisning),c);
        
        c.gridy = 11;
        regresept = new JButton("Register Resept");
        c.gridx = 1;
        regresept.addActionListener(lytteren);
        senterpanelnyresept.add(regresept,c);
        kontrollresept = "[ABC]|AB|BC|AC|ABC";
        
        //Tabellvinduet
        filtrerReseptlisten();
        tabell = new TabellVindu(spesifikkreseptliste);
        tabell.setOpaque(true);
        senterpanelvisdata.add(tabell,BorderLayout.CENTER);
        
        //LEGGER ALLE PANELER TIL
        senterpanel.add(senterpanelvisdata, VISDATA);
        senterpanel.add(senterpanelnypasient, NY_PASIENT);
        senterpanel.add(senterpanelnyresept, NY_RESEPT);
        super.add(sidepanel, BorderLayout.LINE_START);
        super.add(senterpanel, BorderLayout.CENTER);       
    }
    
    public void visFørste(){
        /*Metode som viser det første panelet i lagt til i senterpanelet
        dette er visdatapanelet før tabellen er generert*/
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.first(senterpanel);
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
    
    public void tilbakeTilMeny(){
        /*Denne metoden leder brukeren tilbake til startvinduet for en ny 
        innlogging*/
        hovedrammekopi = (Hovedramme) SwingUtilities.getWindowAncestor(this);
        hovedrammekopi.visFørsteLege(pasientliste,reseptliste);
    }
    
    public void nyPasient(){
        /*Denne metoden viser "ny pasient" panelet som inneholder alle datafelter 
        for å registrere nye pasienter for den innloggede legen*/
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.show(senterpanel,NY_PASIENT);
    }
    
    public void nyResept(){
        /*Denne metoden viser "ny resept" panelet som inneholder alle datafelter 
        for å registrere nye resepter for den innloggede legen*/
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.show(senterpanel,NY_RESEPT);
    }
    
    public void visData(){
        /*Denne metoden viser "vis data" panelet som inneholder tabellen og
        søkefeltene for tabellen*/
        oppDaterTabelen();
        CardLayout c = (CardLayout)senterpanel.getLayout();
        tabell.repaint();
        senterpanelvisdata.revalidate();
        super.revalidate();
        c.show(senterpanel,VISDATA);
    }
    
    private void oppDaterTabelen(){
        /*Denne metoden generer en ny tabell bassert på den innloggede legens 
        utskrevende resepter, og legger tabellen til i "vis data" panelet*/
        tabell.nyInnData(spesifikkreseptliste);
    }
    
    public void fylltabellen(){
    //Dette er en hjelpemetode for å teste programmets kapasitet
        for(int i = 0; i < 100000; i++){
            spesifikkreseptliste.put(i, new Resept(i,legensautnr,"P"+i,"med"+i,
                    "mengde"+i,"DDD"+i,"Kat"+i,'A',"Anv"+i));
        } 
    }
    
    private boolean blankePersonfelter(){
        //Sjekker for blanke felter ved registrering av person
        return (fødselsnr.getText().matches("")||fornavnpasient.getText().
                matches("")||etternavnpasient.getText().matches(""));
    }
    
    private boolean blankeReseptfelter(){
        //Sjekker for blanke felter ved registrering av resept
        return (fødselsnrresept.getText().matches("")||legensautnr.
                matches("")||medisinnøkkel.getText().matches("")||
                mengde.getText().matches("")||defdøgndosering.getText().
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
        if(!pasientnøkkel.matches(fødselsnrrregex)){
            infofelt.setText("Fødselsnummeret du har skrevet \ninn er ikke et "
                    + "gyldig fødselsnummer");
            return;
        }
        else if(pasientliste.get(pasientnøkkel)==null){
            Pasient ny = new Pasient(fornavn, etternavn, pasientnøkkel);
            pasientliste.put(pasientnøkkel,ny);
            lagreLegeListene(PASIENT);
            infofelt.setText("Person registrert." + pasientliste.values());
            return;
        }
        else{
            infofelt.setText("Pasienten finnes\ni registeret fra før");
        }
        return;
    }
    
    private void regResept(){
        /*Metoden registerer en resept gitt at alle parametere er riktig
        utfyllt, at legen har riktig reseptbevilgning for preparatet*/
        if(blankeReseptfelter()){
            infofelt.setText("Et eller fler av feltene er tomme");
            return;
        }
        String fødselsnrrregex = "\\d{11}";
        String pasientnøkkel = fødselsnrresept.getText();
        String legenøkkel = legensautnr;
        String medisinnr = medisinnøkkel.getText();
        String medisinmengde = mengde.getText();
        String DDD = defdøgndosering.getText();
        int n = reseptkategorier.getSelectedIndex();
        String medisinkategori = (String)reseptkategorier.getItemAt(n);
        String legensanvisning = anvisning.getText();
        if(!pasientnøkkel.matches(fødselsnrrregex)){
            infofelt.setText("Fødselsnummeret du har skrevet\ninn er ikke et "
                    + "gyldig fødselsnummer");
            return;
        }
        else if(pasientliste.get(pasientnøkkel)==null){
            infofelt.setText("Pasienten er ikke registrert");
            return;
        }
        else{
            int testen = reseptbevilgning.indexOf(reseptensgruppe);
            if(testen==-1){
                infofelt.setText("Legen er ikke godkjet for denne\nresepten");
                return;
            }
            else{
                Resept ny = new Resept(reseptnøkkel, pasientnøkkel,legenøkkel,
                        medisinnr, medisinmengde,DDD,medisinkategori,
                        reseptensgruppe,legensanvisning);
                reseptliste.put(ny.getReseptnr(),ny);
                infofelt.setText("Resept registrert.");
                reseptnøkkel++;
                lagreLegeListene(RESEPT);
                filtrerReseptlisten();
            }
        }
    }
    
    public void listeVelger(){
        int n = reseptkategorier.getSelectedIndex();
        String medisinkategori = (String)reseptkategorier.getItemAt(n);
        char gruppe = medisinkategori.charAt(0);
        //G,H,J,L,M,N,P,R,S,V;
        switch(gruppe){
                case 'A': kordinater[0]++;//Dato
                    break;
                case 'B': kordinater[1]++;//Reseptnr
                    break;
                case 'C': kordinater[2]++;//Lege autnr
                    break;
                case 'D': kordinater[3]++;//Pasient fnr
                    break;
                case 'G': kordinater[4]++;//Medisin(ACTnr)
                    break;
                case 'H': kordinater[5]++;//Mengde
                    break;
                case 'J': kordinater[6]++;//DDD
                    break;
                case 'L': kordinater[7]++;//Kategori
                    break;
                case 'M': kordinater[8]++;//Reseptgruppe
                    break;
                case 'N': kordinater[9]++;//Reseptgruppe
                    break;
                case 'P': kordinater[10]++;//Reseptgruppe
                    break;
                case 'R': kordinater[11]++;//Reseptgruppe
                    break;
                case 'S': kordinater[11]++;//Reseptgruppe
                    break;
                case 'V': kordinater[11]++;//Reseptgruppe
                    break;
            }
    }
    
    public void lagreLegeListene(int n){
        if(n==PASIENT){
            try(ObjectOutputStream utfil = new ObjectOutputStream(
                    new FileOutputStream("src/pasientliste.data"))){
                utfil.writeObject(pasientliste);
            }
            catch(NotSerializableException ns){
                JOptionPane.showMessageDialog(null,"Objektet er ikke serialisert");
            }
            catch(IOException ioe){
                JOptionPane.showMessageDialog(null,"Problem med utskrift til fil");
            }
        }
        else if(n==RESEPT){
            try(ObjectOutputStream utfil = new ObjectOutputStream(
                    new FileOutputStream("src/reseptliste.data"))){
                utfil.writeObject(reseptliste);
            }
            catch(NotSerializableException ns){
                JOptionPane.showMessageDialog(null,"Objektet er ikke serialisert");
            }
            catch(IOException ioe){
                JOptionPane.showMessageDialog(null,"Problem med utskrift til fil");
            }
        }
    }
    
    public void skrivUtlisten(int n){
        if(n==PASIENT){
            for(Map.Entry<String,Pasient> entry:pasientliste.entrySet()){
                    Pasient løper = entry.getValue();
                    infofelt.append("\nNøkkel : " + løper.getFødselsnr()+"\n");
            }
        }
        else if(n==RESEPT){
            for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
                    Resept løper = entry.getValue();
                    infofelt.append("\nNøkkel : " + løper.getReseptnr()+"\n");
            }
        }
    }
    
    private class Lytter implements ActionListener{
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
                listeVelger();
            }
        }
    }
    
    private class RadioLytteren implements ActionListener{
         public void actionPerformed(ActionEvent e) {
         //Metode for å endre reseptgruppen preparatet befinner seg i
            if(gra.isSelected()){
                reseptensgruppe = 'A';
            }
            else if(grb.isSelected()){
                reseptensgruppe = 'B';
            }
            else{
                reseptensgruppe = 'C';
            }
         }
    }
}//End of Class LegePanel
