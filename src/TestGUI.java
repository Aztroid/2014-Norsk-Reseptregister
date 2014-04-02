/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class TestGUI extends JFrame {

    private JTextField/*Pasient*/fornavnpasient, etternavnpasient, fødselsnr,
            
            /*Lege*/fornavnlege, etternavnlege, autorisasjonsnummer, 
            reseptbevilgning, arbeidssted,
    
            /*Resept*/fødselsnrresept, autnrresept, medisinnøkkel, mengde,
            defdøgndosering, kategori, reseptgruppe,søkefelt;
    
    private JTextArea infosjerm;
    private JTextArea anvisning;
    private JButton regpasient, reglege, regresept, søkperson, søklege, 
            søkresept;
    private TreeMap<String,Person> pasientliste = new TreeMap<>();
    private TreeMap<String,Person> legeliste = new TreeMap<>();
    private TreeMap<Integer,Resept> reseptliste = new TreeMap<>();
    private Kommandolytter lytteren;

    public TestGUI() {
        super("ReseptTestvindu");
        lytteren = new Kommandolytter();

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        c.add(new JLabel("Personnr: "));
        fødselsnr = new JTextField(30);
        fødselsnr.addActionListener(lytteren);
        c.add(fødselsnr);

        c.add(new JLabel("Fornavn Pasient: "));
        fornavnpasient = new JTextField(30);
        fornavnpasient.addActionListener(lytteren);
        c.add(fornavnpasient);

        c.add(new JLabel("Etternavn Pasient: "));
        etternavnpasient = new JTextField(30);
        etternavnpasient.addActionListener(lytteren);
        c.add(etternavnpasient);
        
        regpasient = new JButton("Register Pasient");
        regpasient.addActionListener(lytteren);
        c.add(regpasient);
        
        c.add(new JLabel("**************************************************"
                + "********************************************"));
        
        c.add(new JLabel("Autorisasjonsnr: "));
        autorisasjonsnummer = new JTextField(30);
        autorisasjonsnummer.addActionListener(lytteren);
        c.add(autorisasjonsnummer);

        c.add(new JLabel("Fornavn Lege: "));
        fornavnlege = new JTextField(30);
        fornavnlege.addActionListener(lytteren);
        c.add(fornavnlege);

        c.add(new JLabel("Etternavn Lege: "));
        etternavnlege = new JTextField(30);
        etternavnlege.addActionListener(lytteren);
        c.add(etternavnlege);
        
        c.add(new JLabel("Reseptbevilkning: "));
        reseptbevilgning = new JTextField(30);
        reseptbevilgning.addActionListener(lytteren);
        c.add(reseptbevilgning);
        
        c.add(new JLabel("Arbeidssted: "));
        arbeidssted = new JTextField(30);
        arbeidssted.addActionListener(lytteren);
        c.add(arbeidssted);
        
        reglege = new JButton("Register Lege");
        reglege.addActionListener(lytteren);
        c.add(reglege);

        c.add(new JLabel("**************************************************"
                + "********************************************"));
        /*Resept fødselsnrresept, autnrresept, medisinnøkkel, mengde,
            defdøgndosering, kategori, reseptgruppe, anvisning*/
        
        c.add(new JLabel("Pasient(Fnr): "));
        fødselsnrresept = new JTextField(30);
        fødselsnrresept.addActionListener(lytteren);
        c.add(fødselsnrresept);

        c.add(new JLabel("Leg(Aut.nr): "));
        autnrresept = new JTextField(30);
        autnrresept.addActionListener(lytteren);
        c.add(autnrresept);

        c.add(new JLabel("ACT-Nr: "));
        medisinnøkkel = new JTextField(30);
        medisinnøkkel.addActionListener(lytteren);
        c.add(medisinnøkkel);
        
        c.add(new JLabel("Mengde(gr): "));
        mengde = new JTextField(30);
        mengde.addActionListener(lytteren);
        c.add(mengde);
        
        c.add(new JLabel("Diag.Døgn: "));
        defdøgndosering = new JTextField(30);
        defdøgndosering.addActionListener(lytteren);
        c.add(defdøgndosering);

        c.add(new JLabel("Kategori: "));
        kategori = new JTextField(30);
        kategori.addActionListener(lytteren);
        c.add(kategori);

        c.add(new JLabel("Reseptgruppe: "));
        reseptgruppe = new JTextField(30);
        reseptgruppe.addActionListener(lytteren);
        c.add(reseptgruppe);
        
        c.add(new JLabel("Legens anv:"));
        anvisning = new JTextArea(10, 30);
        anvisning.setEditable(true);
        c.add(new JScrollPane(anvisning));
        
        regresept = new JButton("Register Resept");
        regresept.addActionListener(lytteren);
        c.add(regresept);
        
        c.add(new JLabel("Informasjonsfelt:"));
        infosjerm = new JTextArea(10, 40);
        infosjerm.setEditable(false);
        c.add(new JScrollPane(infosjerm));
        
        søkperson = new JButton("Søk person");
        søkperson.addActionListener(lytteren);
        c.add(søkperson);
        
        søklege = new JButton("Søk lege");
        søklege.addActionListener(lytteren);
        c.add(søklege);
        
        søkresept = new JButton("Søk resept");
        søkresept.addActionListener(lytteren);
        c.add(søkresept);
        
        c.add(new JLabel("nr: "));
        søkefelt = new JTextField(2);
        søkefelt.addActionListener(lytteren);
        c.add(søkefelt);
        
        setSize(475, 950);
        setVisible(true);
        
    }
    
    private boolean blankePersonfelter(){
        //Sjekker for blanke felter ved registrering av person
        return (fødselsnr.getText().matches("")||fornavnpasient.getText().
                matches("")||etternavnpasient.getText().matches(""));
    }
    
    private boolean blankeLegefelter(){
        //Sjekker for blanke felter ved registrering av lege
        return (fornavnlege.getText().matches("")||etternavnlege.getText().
                matches("")||autorisasjonsnummer.getText().matches("")||
                reseptbevilgning.getText().matches("")||autorisasjonsnummer.
                getText().matches(""));
    }
    
    private boolean blankeReseptfelter(){
        //Sjekker for blanke felter ved registrering av resept
        return (fødselsnrresept.getText().matches("")||autnrresept.getText().
                matches("")||medisinnøkkel.getText().matches("")||
                mengde.getText().matches("")||defdøgndosering.getText().
                matches("")||kategori.getText().matches("")||reseptgruppe.
                getText().matches(""));
    }
    
    private void blankUtfelter() {
        /*Hjelpemetode for å blanke ut alle feltene*/
        fødselsnr.setText("");
        fornavnpasient.setText("");
        etternavnpasient.setText("");
        
        fornavnlege.setText("");
        etternavnlege.setText("");
        autorisasjonsnummer.setText("");
        reseptbevilgning.setText("");
        arbeidssted.setText("");
        
        fødselsnrresept.setText("");
        autnrresept.setText("");
        medisinnøkkel.setText("");
        mengde.setText("");
        defdøgndosering.setText("");
        kategori.setText("");
        reseptgruppe.setText("");
        anvisning.setText("");
    }
    
    private void RegPasient(){
        /*Metoden registerer en ny pasient om pasientnøkkel er gyldig, alle 
        felter er fylt ut og om pasienten ikke eksisterer fra før*/
        if(blankePersonfelter()){
            infosjerm.setText("Et eller fler av feltene er tomme");
            return;
        }
        String fødselsnrrregex = "\\d{11}";
        String pasientnøkkel = fødselsnr.getText();
        String fornavn = fornavnpasient.getText();
        String etternavn = etternavnpasient.getText();
        if(!pasientnøkkel.matches(fødselsnrrregex)){
            infosjerm.setText("Fødselsnummeret du har skrevet inn er ikke et "
                    + "gyldig fødselsnummer");
            return;
        }
        else if(pasientliste.get(pasientnøkkel)==null){
            Pasient ny = new Pasient(fornavn, etternavn, pasientnøkkel);
            pasientliste.put(pasientnøkkel,ny);
            infosjerm.setText("Person registrert.");
            return;
        }
        else{
            infosjerm.setText("Pasienten finnes i registeret fra før");
        }
        return;
    }
    
    private void finnPerson(){
        String pasientnøkkel = fødselsnr.getText();
        Person finnes = pasientliste.get(pasientnøkkel);
        if(finnes==null){
            infosjerm.setText("Personen finnes ikke");
            return;
        }
        else{
            infosjerm.setText(finnes.toString());
        }
    }
    
    private void RegLege(){
        if(blankeLegefelter()){
            infosjerm.setText("Et eller fler av feltene er tomme");
            return;
        }
        String autorisasjonsnrrregex = "\\d{9}";
        String legenøkkel = autorisasjonsnummer.getText();
        String fornavn = fornavnlege.getText();
        String etternavn = etternavnlege.getText();
        String reseptbev = reseptbevilgning.getText();
        String adresse = arbeidssted.getText();
        
        if(!legenøkkel.matches(autorisasjonsnrrregex)){
            infosjerm.setText("Autorisasjonsnummeret du har skrevet inn er "
                    + "ikke gyldig");
            return;
        }
        else if(legeliste.get(legenøkkel)==null){
            Lege ny = new Lege(fornavn, etternavn, legenøkkel, adresse, 
                    reseptbev);
            legeliste.put(legenøkkel,ny);
            infosjerm.setText("Lege registrert.");
            return;
        }
        else{
            infosjerm.setText("Legen finnes i registeret fra før");
        }
        return;
    }
    
    private void finnLege(){
        String legenøkkel = autorisasjonsnummer.getText();
        Person finnes = legeliste.get(legenøkkel);
        if(finnes==null){
            infosjerm.setText("Legen finnes ikke");
            return;
        }
        else{
            infosjerm.setText(finnes.toString());
        }
    }
    
    private void RegResept(){
        /*Resept fødselsnrresept, autnrresept, medisinnøkkel, mengde,
        defdøgndosering, kategori, reseptgruppe;*/
        if(blankeReseptfelter()){
            infosjerm.setText("Et eller fler av feltene er tomme");
            return;
        }
        String fødselsnrrregex = "\\d{11}";
        String autorisasjonsnrrregex = "\\d{9}";
        //String medisinnrrregex = "\\d{9}";
        //String mengdenrrregex = "\\d{9}";
        //String DDDregex = "\\d{9}";
        //String reseptgruppe = "\\d{9}";
        String pasientnøkkel = fødselsnrresept.getText();
        String legenøkkel = autnrresept.getText();
        String medisinnr = medisinnøkkel.getText();
        String medisinmengde = mengde.getText();
        String DDD = defdøgndosering.getText();
        String medisinkategori = kategori.getText();
        String reseptgruppen = reseptgruppe.getText();
        String legensanvisning = anvisning.getText();
        if(!pasientnøkkel.matches(fødselsnrrregex)){
            infosjerm.setText("Fødselsnummeret du har skrevet inn er ikke et "
                    + "gyldig fødselsnummer");
            return;
        }
        else if(!legenøkkel.matches(autorisasjonsnrrregex)){
            infosjerm.setText("Autorisasjonsnummeret du har skrevet inn er "
                    + "ikke gyldig");
            return;
        }
        /*else if(!medisinnr.matches(medisinnrrregex)){
            infosjerm.setText("ACT-nr til medisinen du har skrevet inn er "
                    + "ikke gyldig");
            return;
        }LEGG INN FLERE HER SENERE*/
        else if(pasientliste.get(pasientnøkkel)==null){
            infosjerm.setText("Pasienten er ikke registrert");
            return;
        }
        else if(legeliste.get(legenøkkel)==null){
            infosjerm.setText("Legen er ikke registrert");
            return;
        }
        else{
            Resept ny = new Resept(pasientnøkkel,legenøkkel,medisinnr,
                    medisinmengde,DDD,medisinkategori,reseptgruppen,
                    legensanvisning);
            reseptliste.put(ny.getReseptnr(),ny);
            infosjerm.setText("Resept registrert.");
            return;
        }
    }
    
    private void finnResept(){
        Integer nr = Integer.parseInt(søkefelt.getText());
        Resept finnes = reseptliste.get(nr);
        if(finnes==null){
            infosjerm.setText("Resepten finnes ikke");
            return;
        }
        else{
            infosjerm.setText(finnes.toString());
        }
    }

    private class Kommandolytter implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == regpasient) {
                RegPasient();
                blankUtfelter();
            }
            else if(e.getSource() == reglege) {
                RegLege();
                blankUtfelter();
            }
            else if(e.getSource() == regresept) {
                RegResept();
                blankUtfelter();
            }
            else if(e.getSource() == søkperson){
                finnPerson();
                blankUtfelter();
            }
            else if(e.getSource() == søklege){
                finnLege();
                blankUtfelter();
            }
            else if(e.getSource() == søkresept){
                finnResept();
                blankUtfelter();
            }
        }
    }
}
