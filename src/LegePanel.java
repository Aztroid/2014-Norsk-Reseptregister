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
    private final String VISDATA = "1";
    private final String NY_PASIENT = "2";
    private final String NY_RESEPT = "3";
    private Lytter lytteren;
    private TreeMap<Integer,Resept> reseptliste;
    private TreeMap<Integer,Resept> spesifikkreseptliste;
    private TreeMap<String,Pasient> pasientliste;
    private String legensautnr;
    private String reseptgodkjennelse;
    private Hovedramme hovedrammekopi;
    
    //Sidepanel datafelter
    private JPanel sidepanel;
    private JTextArea infofelt;
    private Border sidepanelgrense;
    private JScrollPane infoscroll;
    private JButton nypasient,nyresept,gåtilbake,visdata;
    
    //Senterpanel datafelter
    private JPanel senterpanel,senterpanelvisdata, senterpaneltop, senterpanelnypasient,
            senterpanelnyresept;
    private TabellVindu tabellen;
    private Border senterpanelgrense;
    private JButton søk,regpasient,regresept;
    private JTextField søkpasientid,søkreseptid,
           /*REGPASIENT*/ fornavnpasient, etternavnpasient, fødselsnr,
            
           /*REGRESEPT*/ fødselsnrresept, medisinnøkkel, mengde,
            defdøgndosering, kategori;
    private JTextArea anvisning;
    private String regreseptbev;
    private JRadioButton gra, grb, grc;
    private ButtonGroup resteptgruppealt;
    private char reseptensgruppe = 'C';
    private static Integer reseptnøkkel;
    
    public LegePanel(String autnr,String reseptgodkjennelse, TreeMap<String,Pasient> pasientliste,
            TreeMap<Integer,Resept> reseptliste){
        
        super.setLayout(new BorderLayout());
        lytteren = new Lytter();
        legensautnr = autnr;
        this.pasientliste = pasientliste;
        this.reseptliste = reseptliste;
        spesifikkreseptliste = new TreeMap<>();
        this.reseptgodkjennelse = reseptgodkjennelse;
        
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
        
        //SENTERPANEL ramme
        senterpanel = new JPanel(new CardLayout());
        senterpanelgrense = BorderFactory.createTitledBorder("Reseptoversikt");
        senterpanel.setBorder(senterpanelgrense);
        senterpanelvisdata = new JPanel(new BorderLayout());
        senterpaneltop = new JPanel(new FlowLayout());
        senterpanelnypasient = new JPanel(new GridLayout(0,2));
        senterpanelnyresept = new JPanel(new GridLayout(0,2));
         
        //SENTERPANELTOP content:
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
        
        //SENTERPANEL Ny Pasient
        senterpanelnypasient.add(new JLabel("Fødselsnr:"));
        fødselsnr = new JTextField(15);
        fødselsnr.addActionListener(lytteren);
        senterpanelnypasient.add(fødselsnr);

        senterpanelnypasient.add(new JLabel("Fornavn:"));
        fornavnpasient = new JTextField(15);
        fornavnpasient.addActionListener(lytteren);
        senterpanelnypasient.add(fornavnpasient);

        senterpanelnypasient.add(new JLabel("Etternavn Pasient: "));
        etternavnpasient = new JTextField(15);
        etternavnpasient.addActionListener(lytteren);
        senterpanelnypasient.add(etternavnpasient);
        
        regpasient = new JButton("Register Pasient");
        regpasient.addActionListener(lytteren);
        senterpanelnypasient.add(regpasient);
        
        //SENTERPANEL Ny Resept
        senterpanelnyresept.add(new JLabel("Pasient(Fnr): "));
        fødselsnrresept = new JTextField(30);
        fødselsnrresept.addActionListener(lytteren);
        senterpanelnyresept.add(fødselsnrresept);

        senterpanelnyresept.add(new JLabel("ACT-Nr: "));
        medisinnøkkel = new JTextField(30);
        medisinnøkkel.addActionListener(lytteren);
        senterpanelnyresept.add(medisinnøkkel);
        
        senterpanelnyresept.add(new JLabel("Mengde(gr): "));
        mengde = new JTextField(30);
        mengde.addActionListener(lytteren);
        senterpanelnyresept.add(mengde);
        
        senterpanelnyresept.add(new JLabel("Diag.Døgn: "));
        defdøgndosering = new JTextField(30);
        defdøgndosering.addActionListener(lytteren);
        senterpanelnyresept.add(defdøgndosering);

        senterpanelnyresept.add(new JLabel("Kategori: "));
        kategori = new JTextField(30);
        kategori.addActionListener(lytteren);
        senterpanelnyresept.add(kategori);
        
        senterpanelnyresept.add(new JLabel("Reseptgruppe(felleskatalogen): "));
        resteptgruppealt = new ButtonGroup();
        gra = new JRadioButton("Gruppe A", false);
        gra.addActionListener(lytteren);
        grb = new JRadioButton("Gruppe B", false);
        grb.addActionListener(lytteren);
        grc = new JRadioButton("Gruppe C", true);
        grc.addActionListener(lytteren);
        resteptgruppealt.add(gra);
        resteptgruppealt.add(grb);
        resteptgruppealt.add(grc);
        senterpanelnyresept.add(gra);
        senterpanelnyresept.add(grb);
        senterpanelnyresept.add(grc);
        
        senterpanelnyresept.add(new JLabel("Legens anv:"));
        anvisning = new JTextArea(10, 30);
        anvisning.setEditable(true);
        senterpanelnyresept.add(new JScrollPane(anvisning));
        
        regresept = new JButton("Register Pasient");
        regresept.addActionListener(lytteren);
        senterpanelnyresept.add(regresept);
        regreseptbev = "[ABC]|AB|BC|AC|ABC";
        reseptnøkkel = 1;
        
        //LEGGER ALLE PANELER TIL
        senterpanel.add(senterpanelvisdata, VISDATA);
        senterpanel.add(senterpanelnypasient, NY_PASIENT);
        senterpanel.add(senterpanelnyresept, NY_RESEPT);
        super.add(sidepanel, BorderLayout.LINE_START);
        super.add(senterpanel, BorderLayout.CENTER);
                
        //SENTERPANEL tabell
        /*filtrerLegelisten();/*Finner kun de reseptene den 
                            aktuelle legen har skrevet utr*/
    }
    
    public void visFørste(){
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.first(senterpanel);
    }
    
    public void filtrerLegelisten(){
        Resept løper;
        for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
            løper = entry.getValue();
            if(legensautnr.equalsIgnoreCase(løper.getLege())){
                spesifikkreseptliste.put(løper.getReseptnr(),løper);
            }
        }
    }
    
    public void tilbakeTilMeny(){
        hovedrammekopi = (Hovedramme) SwingUtilities.getWindowAncestor(this);
        hovedrammekopi.visFørste();
    }
    
    public void nyPasient(){
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.show(senterpanel,NY_PASIENT);
    }
    
    public void nyResept(){
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.show(senterpanel,NY_RESEPT);
    }
    
    private void lagTabellen(){
        TabellVindu tabell = new TabellVindu(spesifikkreseptliste);
        tabell.setOpaque(true);
        senterpanelvisdata.add(tabell,BorderLayout.CENTER);
    }
    
    public void fylltabellen(){
        for(int i = 0; i < 100000; i++){
            spesifikkreseptliste.put(i, new Resept(i,legensautnr,"P"+i,"med"+i,"mengde"+i,
                    "DDD"+i,"Kat"+i,'A',"Anv"+i));
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
                matches("")||kategori.
                getText().matches(""));
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
            infofelt.setText("Person registrert.");
            return;
        }
        else{
            infofelt.setText("Pasienten finnes\ni registeret fra før");
        }
        return;
    }
    
    private void regResept(){
        /*Metoden registerer en resept gitt at alle parametere er riktig
        utfyllt*/
        if(blankeReseptfelter()){
            infofelt.setText("Et eller fler av feltene er tomme");
            return;
        }
        String fødselsnrrregex = "\\d{11}";
        //String actkoderegex = "^([A-C])";
        //String medisinnrrregex = "\\d{9}";
        String pasientnøkkel = fødselsnrresept.getText();
        String legenøkkel = legensautnr;
        String medisinnr = medisinnøkkel.getText();
        String medisinmengde = mengde.getText();
        String DDD = defdøgndosering.getText();
        String medisinkategori = kategori.getText();
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
            int testen = reseptgodkjennelse.indexOf(reseptensgruppe);
            if(testen==-1){
                infofelt.setText("Legen er ikke godkjet for denne\nresepten");
                return;
            }
            else{
                reseptaktivert();
                Resept ny = new Resept(reseptnøkkel++, pasientnøkkel,legenøkkel,
                        medisinnr, medisinmengde,DDD,medisinkategori,
                        reseptensgruppe,legensanvisning);
                reseptliste.put(ny.getReseptnr(),ny);
                infofelt.setText("Resept registrert.");
            return;
            }
        }
    }
    
    private void reseptaktivert(){
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
    
    private class Lytter implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==gåtilbake){
                tilbakeTilMeny();
            }
            else if(e.getSource()==visdata){
                visFørste();
                filtrerLegelisten();
                lagTabellen();
                revalidate();
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
        }
    }
}
