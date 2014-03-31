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
            defdøgndosering, kategori, reseptgruppe,
            
            søkepersonnr, søkeautnrlege, søkeresept;
    
    private JTextArea infosjerm;
    private JTextArea anvisning;
    private JButton regpasient, reglege, regresept, søk;
    private TreeMap<String, Person> pasientliste;
    private TreeMap<String, Person> legeliste;
    private TreeMap<String, Resept> reseptlisteliste;
    private Kommandolytter lytteren;

    public TestGUI() {
        super("ReseptTestvindu");
        lytteren = new Kommandolytter();

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        c.add(new JLabel("Personnr: "));
        fødselsnr = new JTextField(8);
        fødselsnr.addActionListener(lytteren);
        c.add(fødselsnr);

        c.add(new JLabel("Fornavn Pasient: "));
        fornavnpasient = new JTextField(8);
        fornavnpasient.addActionListener(lytteren);
        c.add(fornavnpasient);

        c.add(new JLabel("Etternavn Pasient: "));
        etternavnpasient = new JTextField(8);
        etternavnpasient.addActionListener(lytteren);
        c.add(etternavnpasient);
        
        regpasient = new JButton("Register Pasient");
        regpasient.addActionListener(lytteren);
        c.add(regpasient);
        
        c.add(new JLabel("**************************************************"
                + "********************************************"));
        
        c.add(new JLabel("Autorisasjonsnr: "));
        autorisasjonsnummer = new JTextField(8);
        autorisasjonsnummer.addActionListener(lytteren);
        c.add(autorisasjonsnummer);

        c.add(new JLabel("Fornavn Lege: "));
        fornavnlege = new JTextField(8);
        fornavnlege.addActionListener(lytteren);
        c.add(fornavnlege);

        c.add(new JLabel("Etternavn Lege: "));
        etternavnlege = new JTextField(8);
        etternavnlege.addActionListener(lytteren);
        c.add(etternavnlege);
        
        c.add(new JLabel("Reseptbevilkning: "));
        reseptbevilgning = new JTextField(8);
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
        fødselsnrresept = new JTextField(8);
        fødselsnrresept.addActionListener(lytteren);
        c.add(fødselsnrresept);

        c.add(new JLabel("Leg(Aut.nr): "));
        autnrresept = new JTextField(8);
        autnrresept.addActionListener(lytteren);
        c.add(autnrresept);

        c.add(new JLabel("ACT-Nr: "));
        medisinnøkkel = new JTextField(8);
        medisinnøkkel.addActionListener(lytteren);
        c.add(medisinnøkkel);
        
        c.add(new JLabel("Mengde(gr): "));
        mengde = new JTextField(8);
        mengde.addActionListener(lytteren);
        c.add(mengde);
        
        c.add(new JLabel("Diag.Døgn: "));
        defdøgndosering = new JTextField(8);
        defdøgndosering.addActionListener(lytteren);
        c.add(defdøgndosering);

        c.add(new JLabel("Kategori: "));
        kategori = new JTextField(8);
        kategori.addActionListener(lytteren);
        c.add(kategori);

        c.add(new JLabel("Reseptgruppe: "));
        reseptgruppe = new JTextField(8);
        reseptgruppe.addActionListener(lytteren);
        c.add(reseptgruppe);
        
        c.add(new JLabel("Legens anv:"));
        anvisning = new JTextArea(8, 15);
        anvisning.setEditable(false);
        c.add(new JScrollPane(anvisning));
        
        regresept = new JButton("Register Resept");
        regresept.addActionListener(lytteren);
        c.add(regresept);
        
        c.add(new JLabel("Informasjonsfelt:"));
        infosjerm = new JTextArea(13, 15);
        infosjerm.setEditable(false);
        c.add(new JScrollPane(infosjerm));
        
        setSize(235, 950);
        setVisible(true);
        
    }
    
    private void RegPasient(){
        String pasientnøkkel = "";
    }

    private class Kommandolytter implements ActionListener {

        public void actionPerformed(ActionEvent e) {

        }
    }
}
