/*Hovedprosjekt Dats-1600
GRUPPE 6
William B. Wold, s183670, HIINGDATA13H1AA
Vegar Nygård, s193362, HIINGDATA13H1AA
 */

/*Dette er et sikkerhetspanel der leger og kontrollører kan opprettes.
det er kun i dette panelet du kan opprette disse*/

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class AdminPanel extends JPanel{
    
    //Felles Datafelter
    private Lytter lytteren;
    private Hovedramme hovedrammekopi;
    private TreeMap<String,Lege> legeliste;
    private TreeMap<Integer,Kontrollør> kontrollørliste;
    
    //Sidepanel datafelter
    private JPanel sidepanel;
    private JTextArea infofelt;
    private Border sidepanelgrense;
    private JScrollPane infoscroll;
    private JButton tilbake,nylege,nykontrollør;
    
    //Senterpanel datafelter
    private JPanel senterpanel;
    private Border senterpanelgrense;
    private final String BAKGRUNN = "6";
    private final String NYLEGE= "7";
    private final String NYKONTROLLØR = "8";
    private final int KONTROLLØR = 1;
    private final int LEGE = 2;
    
    //Senterpanel Bakgrunn
    private JPanel senterpanelbakgrunn;
    
    //Senterpanelreglege
    private JTextArea infolege;
    private JScrollPane infoscrolllege;
    private JPanel senterpanelreglege;
    private GridBagConstraints c;
    private JTextField fornavnlege, etternavnlege, autorisasjonsnummer, 
            arbeidssted;
    private JButton reglege;
    private Tegnlytter tegnlytteren;
    private JCheckBox Abox;
    private JCheckBox Bbox;
    private JCheckBox Cbox;
    private boolean gruppeA;
    private boolean gruppeB;
    private boolean gruppeC;
    
    //SenterpanelregKontøllør
    private JTextArea infokont;
    private JScrollPane infoscrollkont;
    private static Integer kontnøkkel;
    private JPanel senterpanelregkont;
    private JTextField fornavnkont, etternavnkont, arbeidsstedkont;
    private JButton regkont;
    
    public AdminPanel(Integer sistekontrollør,TreeMap<String,Lege> legeliste,
            TreeMap<Integer,Kontrollør> kontrollørliste){
        //Initialiserer felles datafelter
        super(new BorderLayout());
        lytteren = new Lytter();
        kontnøkkel = ++sistekontrollør;
        this.legeliste = legeliste;
        this.kontrollørliste = kontrollørliste;
        
        //SIDEPANEL
        sidepanel = new JPanel();
        sidepanel.setLayout(new GridLayout(8,1,3,3));
        sidepanelgrense = BorderFactory.createTitledBorder("Navigering");
        sidepanel.setBorder(sidepanelgrense);
        
        infofelt = new JTextArea(4,18);
        infoscroll = new JScrollPane(infofelt);
        infofelt.setText("Registrer nye brukere");
        
        infofelt.setEditable(false);
        sidepanel.add(infoscroll);
        
        tilbake = new JButton("Ferdig med registrering");
        tilbake.addActionListener(lytteren);
        sidepanel.add(tilbake);
        
        nylege = new JButton("Ny Lege");
        nylege.addActionListener(lytteren);
        sidepanel.add(nylege);
        
        nykontrollør = new JButton("Ny Kontrollør");
        nykontrollør.addActionListener(lytteren);
        sidepanel.add(nykontrollør);
        
        //Senterpanel ramme
        senterpanel = new JPanel(new CardLayout());
        senterpanelgrense = BorderFactory.createTitledBorder("ADMINISTRATOR");
        senterpanel.setBorder(senterpanelgrense);
        
        //Senterpanel Bakgrunn
        senterpanelbakgrunn = new JPanel();
        
        //Senterpanel Reglege:
        tegnlytteren = new Tegnlytter();
        senterpanelreglege = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 5;
        c.ipady = 5;
        
        c.gridx = 1;
        infolege = new JTextArea(10,30);
        infoscrolllege = new JScrollPane(infolege);
        infolege.setText("Leger i registeret:\n" + skrivUtlisten(LEGE));
        senterpanelreglege.add(infoscrolllege,c);
        infolege.setEditable(false);
        c.gridx = 0;
        
        c.gridy = 1;
        senterpanelreglege.add(new JLabel("Autorisasjonsnr: "),c);
        c.gridx = 1;
        autorisasjonsnummer = new JTextField(30);
        autorisasjonsnummer.addActionListener(lytteren);
        senterpanelreglege.add(autorisasjonsnummer,c);
        c.gridx = 0;

        c.gridy = 2;
        senterpanelreglege.add(new JLabel("Fornavn Lege: "),c);
        c.gridx = 1;
        fornavnlege = new JTextField(30);
        fornavnlege.addActionListener(lytteren);
        senterpanelreglege.add(fornavnlege,c);
        c.gridx = 0;

        c.gridy = 3;
        senterpanelreglege.add(new JLabel("Etternavn Lege: "),c);
        c.gridx = 1;
        etternavnlege = new JTextField(30);
        etternavnlege.addActionListener(lytteren);
        senterpanelreglege.add(etternavnlege,c);
        c.gridx = 0;
        
        c.gridy = 4;
        senterpanelreglege.add(new JLabel("Arbeidssted: "),c);
        c.gridx = 1;
        arbeidssted = new JTextField(30);
        arbeidssted.addActionListener(lytteren);
        senterpanelreglege.add(arbeidssted,c);
        c.gridx = 0;
        
        c.gridy = 5;
        senterpanelreglege.add(new JLabel("Godkjent Reseptbevilkning:"),c);
        c.gridx = 1;
        c.gridy = 6;
        Abox = new JCheckBox("Gruppe A");
        Abox.addItemListener(tegnlytteren);
        senterpanelreglege.add(Abox,c);
        c.gridy = 7;
        Bbox = new JCheckBox("Gruppe B");
        Bbox.addItemListener(tegnlytteren);
        senterpanelreglege.add(Bbox,c);
        c.gridy = 8;
        Cbox = new JCheckBox("Gruppe C");
        Cbox.addItemListener(tegnlytteren);
        senterpanelreglege.add(Cbox,c);
        
        c.gridy = 9;
        reglege = new JButton("Register Lege");
        reglege.addActionListener(lytteren);
        senterpanelreglege.add(reglege,c);
        
        //Senterpanel nykontrollør
        senterpanelregkont = new JPanel(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 5;
        c.ipady = 5;
        
        c.gridx = 1;
        infokont = new JTextArea(10,30);
        infokont.setEditable(false);
        infoscrollkont = new JScrollPane(infokont);
        infokont.setText("Kontrollører i registeret:\n" + 
                skrivUtlisten(KONTROLLØR));
        senterpanelregkont.add(infoscrollkont,c);
        c.gridx = 0;
        
        c.gridy = 1;
        senterpanelregkont.add(new JLabel("Fornavn Kontrollør: "),c);
        c.gridx = 1;
        fornavnkont = new JTextField(30);
        fornavnkont.addActionListener(lytteren);
        senterpanelregkont.add(fornavnkont,c);
        c.gridx = 0;

        c.gridy = 2;
        senterpanelregkont.add(new JLabel("Etternavn Kontrollør: "),c);
        c.gridx = 1;
        etternavnkont = new JTextField(30);
        etternavnkont.addActionListener(lytteren);
        senterpanelregkont.add(etternavnkont,c);
        c.gridx = 0;

        c.gridy = 3;
        senterpanelregkont.add(new JLabel("Arbeidssted: "),c);
        c.gridx = 1;
        arbeidsstedkont = new JTextField(30);
        arbeidsstedkont.addActionListener(lytteren);
        senterpanelregkont.add(arbeidsstedkont,c);
        
        c.gridy = 4;
        regkont = new JButton("Register Kontrollør");
        regkont.addActionListener(lytteren);
        senterpanelregkont.add(regkont,c);
        
        //Legger til panelene
        senterpanel.add(senterpanelbakgrunn,BAKGRUNN);
        senterpanel.add(senterpanelreglege,NYLEGE);
        senterpanel.add(senterpanelregkont,NYKONTROLLØR);
        super.add(sidepanel, BorderLayout.LINE_START);
        super.add(senterpanel, BorderLayout.CENTER);
    }
    
    public void tilbakeTilMeny(){
        /*Denne metoden leder brukeren tilbake til startvinduet for en ny 
        innlogging*/
        hovedrammekopi = (Hovedramme) SwingUtilities.getWindowAncestor(this);
        hovedrammekopi.visFørsteAdmin(legeliste,kontrollørliste);
    }
    
    public void visNylege(){
        //Viser Panelet for registrering av lege
        infofelt.setText("Registrer ny lege");
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.show(senterpanel,NYLEGE);
    }
    
    public void visNyKont(){
        //Viser Panelet for registrering av Kontrollør
        infofelt.setText("Registrer ny Kontrollør");
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.show(senterpanel,NYKONTROLLØR);
    }
    
    private boolean blankeLegefelter(){
        //Sjekker for blanke felter ved registrering av lege
        return (fornavnlege.getText().matches("")||etternavnlege.getText().
                matches("")||autorisasjonsnummer.getText().matches("")||
                arbeidssted.getText().matches(""));
    }
    
    private void regNyLege(){
        /*Registerer en ny lege i systemet om bruker innskrevende informasjon
        er korrekt*/
        if(blankeLegefelter()){
            infofelt.setText("Et eller fler av feltene er tomme");
            return;
        }
        String autorisasjonsnrrregex = "\\d{9}";
        String legenøkkel = autorisasjonsnummer.getText();
        String fornavn = fornavnlege.getText();
        String etternavn = etternavnlege.getText();
        String adresse = arbeidssted.getText();
        String passord = tilfeldiString();
        String reseptbev = "";
        if(!legenøkkel.matches(autorisasjonsnrrregex)){
            infofelt.setText("Autorisasjonsnummeret du har\nskrevet inn er "
                    + "ikke gyldig");
            return;
        }
        else if(legeliste.get(legenøkkel)==null){
            if(gruppeA){
                reseptbev += "A";
            }
            if(gruppeB){
                reseptbev += "B";
            }
            if(gruppeC){
                reseptbev += "C";
            }
            Lege ny = new Lege(fornavn, etternavn, passord, legenøkkel, adresse, 
                    reseptbev);
            legeliste.put(legenøkkel,ny);
            infofelt.setText("Lege registrert.");
            lagreAdminListene(LEGE);
            JOptionPane.showMessageDialog(null, "Autnr: " + legenøkkel + 
                    "\nPassord: " + passord );
            infolege.setText("Leger i registeret:\n" + skrivUtlisten(LEGE));
        }
        else{
            infofelt.setText("Legen finnes i registeret fra før");
        }
    }
    
    private boolean blankekontfelter(){
        //Sjekker for blanke felter ved registrering av kontrollør
        return (fornavnkont.getText().matches("")||etternavnkont.getText().
                matches("")||arbeidsstedkont.getText().matches(""));
    }
    
    private void regNyKont(){
        /*Registerer en ny kontrollør i systemet om bruker innskrevende 
        informasjon er korrekt*/
        if(blankekontfelter()){
            infofelt.setText("Et eller fler av feltene er tomme");
            return;
        }
        String bruker = "" + kontnøkkel;
        String fornavn = fornavnkont.getText();
        String etternavn = etternavnkont.getText();
        String adresse = arbeidsstedkont.getText();
        String passord = tilfeldiString();
        if(kontrollørliste.get(kontnøkkel)==null){
            Kontrollør ny = new Kontrollør(fornavn, etternavn, 
                    passord,kontnøkkel, adresse);
            kontrollørliste.put(kontnøkkel,ny);
            infofelt.setText("Kontrollør registrert.");
            kontnøkkel++;
            lagreAdminListene(KONTROLLØR);
            JOptionPane.showMessageDialog(null, "KontNr: " + bruker + "\n" + 
                    passord );
            infokont.setText("Kontrollører i registeret:\n" + 
                skrivUtlisten(KONTROLLØR));
        }
        else{
            infofelt.setText("Kontrollør finnes i registeret fra før");
        }
    }
    
   private String tilfeldiString(){
       /*Denne klassen generer et høysikkerhets passord for innlogging som en
       lege eller kontrollør.*/
       /*
       StringBuilder passordGenerator = new StringBuilder();
       int n = 7;
       String tegnSett = "abcdefghijklmnopqrstuvwxyzæøåABCDEFGHIJKLMNOPQRS
       TUVWXYZÆØÅ0123456789<>?/;:/*-+.#
       %^&!´}][{£@]"; // Tilfeldige passordtegn
       int i = 0;

       while(i < n){
           int tilfeldig = (int) (Math.random() * tegnSett.length());
           passordGenerator.append(tegnSett.charAt(tilfeldig));
           i++;
       }
        
       String result = passordGenerator.toString();
       return result;*/
       return "a";
   }
    
    public void lagreAdminListene(int n){
        /*Denne metoden lagrer legelisten og kontrollørlisten slik de er på 
        tidspunktet for metodekallet*/
        if(n==LEGE){
            try(ObjectOutputStream utfil = new ObjectOutputStream(
                    new FileOutputStream("src/legeliste.data"))){
                utfil.writeObject(legeliste);
            }
            catch(NotSerializableException ns){
                JOptionPane.showMessageDialog(null,"Objektet er ikke "
                        + "serialisert");
            }
            catch(IOException ioe){
                JOptionPane.showMessageDialog(null,"Problem med utskrift "
                        + "til fil");
            }
        }
        else if(n==KONTROLLØR){
            try(ObjectOutputStream utfil = new ObjectOutputStream(
                    new FileOutputStream("src/kontrollørliste.data"))){
                utfil.writeObject(kontrollørliste);
            }
            catch(NotSerializableException ns){
                JOptionPane.showMessageDialog(null,"Objektet er ikke "
                        + "serialisert");
            }
            catch(IOException ioe){
                JOptionPane.showMessageDialog(null,"Problem med utskrift "
                        + "til fil");
            }
        }
    }
    
    public String skrivUtlisten(int n){
        /*Denne metoden skriver ut listen på leger og kontrollører slik at 
        administrator kan ha kontroll på hva som er i listene fra før*/
        String tekst = "";
        if(n==KONTROLLØR){
            for(Map.Entry<Integer,Kontrollør> entry:kontrollørliste.entrySet()){
                    Kontrollør løper = entry.getValue();
                    tekst+= "Nøkkel : " + løper.getKontnøkkel()+"\n";
            }
        }
        else if(n==LEGE){
            for(Map.Entry<String,Lege> entry:legeliste.entrySet()){
                    Lege løper = entry.getValue();
                    tekst+= "Nøkkel : " + løper.getAutorisasjonsnr()+"\n";
            }
        }
        return tekst;
    }
    
    private class Tegnlytter implements ItemListener{
        /*Denne lytteren sjekker til en hver tid hva bruker velger av checkboxer
        og setter riktige boolske verdier til hjelpefeltene for å sette riktig
        reseptbevilgning på leger som skal registreres*/
        public void itemStateChanged(ItemEvent e){
            gruppeA = Abox.isSelected();
            gruppeB = Bbox.isSelected();
            gruppeC = Cbox.isSelected();
        }
    }
    
    private class Lytter implements ActionListener{
        //Denne lytteren finner ut hva brukeren klikker på av knapper i panelet
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==tilbake){
                tilbakeTilMeny();
            }
            else if(e.getSource()==nylege){
                visNylege();
            }
            else if(e.getSource()==nykontrollør){
                visNyKont();
            }
            
            else if(e.getSource()==reglege){
                regNyLege();
            }
            else if(e.getSource()==regkont){
                regNyKont();
            }
        }
    }
}//End of class AdminPanel

