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
            reseptbevilgning,
    
            /*Resept*/fødselsnrresept, autnrresept, medisinnøkkel, mengde,
            defdøgndosering, kategori, reseptgruppe;
    
    private JTextArea infosjerm;
    private JTextArea anvisning;
    private JButton regpasient, reglege, regresept, søk;
    private TreeMap<String,Person> pasientliste = new TreeMap<>();
    private TreeMap<String, Person> legeliste  = new TreeMap<>();
    private TreeMap<String, Resept> reseptlisteliste  = new TreeMap<>();
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
        anvisning.setEditable(false);
        c.add(new JScrollPane(anvisning));
        
        regresept = new JButton("Register Resept");
        regresept.addActionListener(lytteren);
        c.add(regresept);
        
        c.add(new JLabel("Informasjonsfelt:"));
        infosjerm = new JTextArea(10, 40);
        infosjerm.setEditable(false);
        c.add(new JScrollPane(infosjerm));
        
        søk = new JButton("SØK");
        søk.addActionListener(lytteren);
        c.add(søk);
        
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
                reseptbevilgning.getText().matches(""));
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
        
        fødselsnrresept.setText("");
        autnrresept.setText("");
        medisinnøkkel.setText("");
        mengde.setText("");
        defdøgndosering.setText("");
        kategori.setText("");
        reseptgruppe.setText("");
    }
    
    private void RegPasient(){
        String fnrregex = "\\d{11}";
        String pasientnøkkel = fødselsnr.getText();
        String fornavn = fornavnpasient.getText();
        String etternavn = etternavnpasient.getText();
        if(blankePersonfelter()){
            infosjerm.setText("Et eller fler av feltene er tomme");
            return;
        }
        else if(!pasientnøkkel.matches(fnrregex)){
            infosjerm.setText("Fødselsnummeret du har skrevet inn er ikke et "
                    + "gyldig fødselsnummer");
            return;
        }
        
        Pasient ny = new Pasient(fornavn, etternavn, pasientnøkkel);
        pasientliste.put(pasientnøkkel,ny);
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

    private class Kommandolytter implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == regpasient) {
                RegPasient();
                blankUtfelter();
            }
            else if(e.getSource() == søk){
                finnPerson();
                blankUtfelter();
            }
        }
    }
}
