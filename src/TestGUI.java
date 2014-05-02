/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class TestGUI extends JFrame {

    private JTextField
            
            /*Pasient*/fornavnpasient, etternavnpasient, fødselsnr,
            
            /*Lege*/fornavnlege, etternavnlege, autorisasjonsnummer, 
            reseptbevilgning, arbeidssted,
    
            /*Resept*/fødselsnrresept, autnrresept, medisinnøkkel, mengde,
            defdøgndosering, kategori,søkefelt;
    
    private JTextArea infosjerm;
    private JTextArea anvisning;
    private JButton regpasient, reglege, regresept, søkperson, søklege, 
            søkresept, søkmedisinlege, søkmedisinpasient,navnalleleger,
            endreresept, vistabellen;
    private TreeMap<String,Pasient> pasientliste = new TreeMap<>();
    private TreeMap<String,Lege> legeliste = new TreeMap<>();
    private TreeMap<Integer,Resept> reseptliste = new TreeMap<>();
    private boolean gruppeA;
    private boolean gruppeB;
    private boolean gruppeC;
    private JCheckBox Abox;
    private JCheckBox Bbox;
    private JCheckBox Cbox;
    private Kommandolytter lytteren;
    private Tegnlytter tegnlytteren;
    private String regreseptbev;
    private ButtonGroup resteptgruppealt;
    private JRadioButton gra, grb, grc;
    private char reseptensgruppe = 'C';
    private static Integer reseptnøkkel;

    public TestGUI() {
        super("ReseptTestvindu");
        lytteren = new Kommandolytter();
        tegnlytteren = new Tegnlytter();

        Container c = getContentPane();
        c.setLayout(new FlowLayout());
        
        /*Pasientfelter*/
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
        /*Legefelter*/
        
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
        
        c.add(new JLabel("Godkjent Reseptbevilkning:"));
        Abox = new JCheckBox("Gruppe A");
        Abox.addItemListener(tegnlytteren);
        c.add(Abox);
        Bbox = new JCheckBox("Gruppe B");
        Bbox.addItemListener(tegnlytteren);
        c.add(Bbox);
        Cbox = new JCheckBox("Gruppe C");
        Cbox.addItemListener(tegnlytteren);
        c.add(Cbox);
        
        c.add(new JLabel("Arbeidssted: "));
        arbeidssted = new JTextField(30);
        arbeidssted.addActionListener(lytteren);
        c.add(arbeidssted);
        
        reglege = new JButton("Register Lege");
        reglege.addActionListener(lytteren);
        c.add(reglege);

        c.add(new JLabel("**************************************************"
                + "********************************************"));
        /*Resteptfelter*/
        
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
        
        c.add(new JLabel("Reseptgruppe(felleskatalogen): "));
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
        c.add(gra);
        c.add(grb);
        c.add(grc);
        
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
 
        søkmedisinlege = new JButton("Søk: lege medisin");
        søkmedisinlege.addActionListener(lytteren);
        c.add(søkmedisinlege);
        
        søkmedisinpasient = new JButton("Søk: Pasient medisin");
        søkmedisinpasient.addActionListener(lytteren);
        c.add(søkmedisinpasient);
        
        navnalleleger = new JButton("Søk:Alle legenavn");
        navnalleleger.addActionListener(lytteren);
        c.add(navnalleleger);
        
        endreresept = new JButton("Endre Resept");
        endreresept.addActionListener(lytteren);
        c.add(endreresept);
        //vistabellen
        
        vistabellen = new JButton("Vis Tabellen");
        vistabellen.addActionListener(lytteren);
        c.add(vistabellen);
        
        setSize(1500, 1000);
        setVisible(true);
        regreseptbev = "[ABC]|AB|BC|AC|ABC";
        reseptnøkkel = 1;
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
                autorisasjonsnummer.getText().matches(""));
    }
    
    private boolean blankeReseptfelter(){
        //Sjekker for blanke felter ved registrering av resept
        return (fødselsnrresept.getText().matches("")||autnrresept.getText().
                matches("")||medisinnøkkel.getText().matches("")||
                mengde.getText().matches("")||defdøgndosering.getText().
                matches("")||kategori.
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
        arbeidssted.setText("");
        
        fødselsnrresept.setText("");
        autnrresept.setText("");
        medisinnøkkel.setText("");
        mengde.setText("");
        defdøgndosering.setText("");
        kategori.setText("");
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
    
    private void finnPasient(){
    /*Metoden finner en pasient med den inntastede nøkkelenr*/
        Pasient løper;
        if(fornavnpasient.getText().matches("")&&etternavnpasient.getText().
                matches("")&&fødselsnr.getText().matches("")){
            infosjerm.setText("Pasienter i registeret:\n\n");
            for(Map.Entry<String,Pasient> entry:pasientliste.entrySet()){
                løper = entry.getValue();
                infosjerm.append(løper.toString()+"\n\n");
            }
        }
        else if(!fødselsnr.getText().matches("")){
            String personnøkkel = fødselsnr.getText();
            løper = pasientliste.get(personnøkkel);
            if(løper!=null){
                infosjerm.setText("Treff på Autorisasjonsnr!\n\n");
                infosjerm.append(løper.toString());
                return;
            }
        }
        else if(!fornavnpasient.getText().matches("")&&!etternavnpasient.getText().matches("")){
            String fornavn = fornavnpasient.getText();
            String etternavn = etternavnpasient.getText();
            infosjerm.setText("Treff på Fornavn og Etternavn:\n\n");
            for(Map.Entry<String,Pasient> entry:pasientliste.entrySet()){
                løper = entry.getValue();
                if(løper.getFornavn().equalsIgnoreCase(fornavn)&&
                        løper.getEtternavn().equalsIgnoreCase(etternavn)){
                    infosjerm.append(løper.toString()+"\n\n");
                }
            }
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
        String adresse = arbeidssted.getText();
        String reseptbev = "";
        if(!legenøkkel.matches(autorisasjonsnrrregex)){
            infosjerm.setText("Autorisasjonsnummeret du har skrevet inn er "
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
            Lege ny = new Lege(fornavn, etternavn, "vinter09", legenøkkel, adresse, 
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
        Lege løper;
        if(fornavnlege.getText().matches("")&&etternavnlege.getText().
                matches("")&&autorisasjonsnummer.getText().matches("")){
            infosjerm.setText("Leger i registeret:\n\n");
            for(Map.Entry<String,Lege> entry:legeliste.entrySet()){
                løper = entry.getValue();
                infosjerm.append(løper.toString()+"\n\n");
            }
        }
        else if(!autorisasjonsnummer.getText().matches("")){
            String legenøkkel = autorisasjonsnummer.getText();
            Lege finnes = legeliste.get(legenøkkel);
            if(finnes!=null){
                infosjerm.setText("Treff på Autorisasjonsnr!\n\n");
                infosjerm.append(finnes.toString());
                return;
            }
        }
        else if(!fornavnlege.getText().matches("")&&!etternavnlege.getText().matches("")){
            String fornavn = fornavnlege.getText();
            String etternavn = etternavnlege.getText();
            infosjerm.setText("Treff på Fornavn og Etternavn:\n\n");
            for(Map.Entry<String,Lege> entry:legeliste.entrySet()){
                løper = entry.getValue();
                if(løper.getFornavn().equalsIgnoreCase(fornavn)&&
                        løper.getEtternavn().equalsIgnoreCase(etternavn)){
                    infosjerm.append(løper.toString()+"\n\n");
                }
            }
        }
        else if(!fornavnlege.getText().matches("")){
            String fornavn = fornavnlege.getText();
            infosjerm.setText("Treff på Fornavn:\n\n");
            for(Map.Entry<String,Lege> entry:legeliste.entrySet()){
                løper = entry.getValue();
                if(løper.getFornavn().equalsIgnoreCase(fornavn)){
                    infosjerm.append(løper.toString()+"\n\n");
                }
            }
        }
        else{//SØK PÅ ETTERNAVN
            String etternavn = etternavnlege.getText();
            infosjerm.setText("Treff på Etternavn:\n\n");
            for(Map.Entry<String,Lege> entry:legeliste.entrySet()){
                løper = entry.getValue();
                if(løper.getEtternavn().equalsIgnoreCase(etternavn)){
                    infosjerm.append(løper.toString()+"\n\n");
                }
            }
        }
    }
    
    private void RegResept(){
        /*Metoden registerer en resept gitt at alle parametere er riktig
        utfyllt*/
        if(blankeReseptfelter()){
            infosjerm.setText("Et eller fler av feltene er tomme");
            return;
        }
        String fødselsnrrregex = "\\d{11}";
        String autorisasjonsnrrregex = "\\d{9}";
        //String actkoderegex = "^([A-C])";
        //String medisinnrrregex = "\\d{9}";
        String pasientnøkkel = fødselsnrresept.getText();
        String legenøkkel = autnrresept.getText();
        String medisinnr = medisinnøkkel.getText();
        String medisinmengde = mengde.getText();
        String DDD = defdøgndosering.getText();
        String medisinkategori = kategori.getText();
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
        else if(pasientliste.get(pasientnøkkel)==null){
            infosjerm.setText("Pasienten er ikke registrert");
            return;
        }
        else if(legeliste.get(legenøkkel)==null){
            infosjerm.setText("Legen er ikke registrert");
            return;
        }
        else{
            Lege krevergodkjenning = legeliste.get(legenøkkel);
            String godkjentresept = krevergodkjenning.getBevilgning();
            int testen = godkjentresept.indexOf(reseptensgruppe);
            if(testen==-1){
                infosjerm.setText("Legen er ikke godkjet for denne resepten");
                return;
            }
            else{
                Resept ny = new Resept(reseptnøkkel++, pasientnøkkel,legenøkkel,medisinnr,
                    medisinmengde,DDD,medisinkategori,reseptensgruppe,
                    legensanvisning);
                reseptliste.put(ny.getReseptnr(),ny);
                infosjerm.setText("Resept registrert.");
            return;
            }
        }
    }
    
    private void finnResept(){
        Resept løper;
        if(!søkefelt.getText().matches("")){
            infosjerm.setText("Treff på Resept i reseptregistert:\n\n");
            Integer nøkkelen = Integer.parseInt(søkefelt.getText());
            løper = reseptliste.get(nøkkelen);
            infosjerm.append(løper.toString()+"\n\n");
            return;
        }
        else if(fødselsnrresept.getText().matches("")&&autnrresept.
                getText().matches("")){
            infosjerm.setText("Resepter i registeret:\n\n");
            for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
                løper = entry.getValue();
                infosjerm.append(løper.toString()+"\n\n");
            }
            return;
        }
        else if(!fødselsnrresept.getText().matches("")){
            String personnøkkelen = fødselsnrresept.getText();
            infosjerm.setText("Treff på Personnr i reseptregister:\n\n");
            for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
                løper = entry.getValue();
                if(fødselsnrresept.getText().equalsIgnoreCase(løper.getPasient())){
                    infosjerm.append(løper.toString()+"\n\n");
                }
            }
            return;
        }
        else if(!autnrresept.getText().matches("")){
            infosjerm.setText("Treff på Autnr i reseptregister:\n\n");
            for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
                løper = entry.getValue();
                if(autnrresept.getText().equalsIgnoreCase(løper.getLege())){
                    infosjerm.append(løper.toString()+"\n\n");
                }
            }
            return;
        }
        else if(!autnrresept.getText().matches("")){
            infosjerm.setText("Treff på Autnr i reseptregister:\n\n");
            for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
                løper = entry.getValue();
                if(autnrresept.getText().equalsIgnoreCase(løper.getLege())){
                    infosjerm.append(løper.toString()+"\n\n");
                }
            }
            return;
        }
    }
    
    private void finnMedisinPasient(){
        Pasient pasienter;
        Resept løper;
        if(medisinnøkkel.getText().matches("")){
            infosjerm.setText("Medesinnøkkelen er tom");
            return;
        }
        else{
            infosjerm.setText("Treff på Resept i Pasientregister:\n\n");
            String medisinen = medisinnøkkel.getText();
            for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
                løper = entry.getValue();
                if(løper.getMedisin().equalsIgnoreCase(medisinen)){
                    String pasientnøkkelen = løper.getPasient();
                    pasienter = pasientliste.get(pasientnøkkelen);
                    infosjerm.append(pasienter.toString()+"\n\n");
                }
            }
            return;
        }
    }
    
    private void finnMedisinLege(){
        Lege leger;
        Resept løper;
        if(medisinnøkkel.getText().matches("")){
            infosjerm.setText("Medesinnøkkelen er tom");
            return;
        }
        else{
            infosjerm.setText("Treff på Resept i Legeregisteret:\n\n");
            String medisinen = medisinnøkkel.getText();
            for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
                løper = entry.getValue();
                if(løper.getMedisin().equalsIgnoreCase(medisinen)){
                    String legenøkkelen = løper.getLege();
                    leger = legeliste.get(legenøkkelen);
                    infosjerm.append(leger.toString()+"\n\n");
                }
            }
            return;
        }
    }
    
    private void visallelegenavn(){
        Lege løper;
        infosjerm.append("Alle legenavn\n\n");
        for(Map.Entry<String,Lege> entry:legeliste.entrySet()){
            løper = entry.getValue();
            infosjerm.append(løper.getFornavn()+" "+
                    løper.getEtternavn()+"\n\n");
        }
    }
    
    private void endreResepter(){
        Lege bevilgningsendring;
        if((autorisasjonsnummer.getText().matches(""))||(!gruppeA&&!gruppeB&&!gruppeC)){
            infosjerm.setText("Skriv inn autorisasjonsnr/Merk av Reseptendring");
        }
        else if(legeliste.get(autorisasjonsnummer.getText())==null){
            infosjerm.setText("Legen finnes ikke i registeret");
        }
        else{
            bevilgningsendring = legeliste.get(autorisasjonsnummer.getText());
            String reseptbev = "";
            if(gruppeA){
                reseptbev += "A";
            }
            if(gruppeB){
                reseptbev += "B";
            }
            if(gruppeC){
                reseptbev += "C";
            }
            bevilgningsendring.setBevilgning(reseptbev);
            infosjerm.setText("Endret Bevilgning for \n\n" + 
                    bevilgningsendring.toString());
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
            }
            else if(e.getSource() == søkperson){
                finnPasient();
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
            else if(e.getSource() == søkmedisinlege){
                finnMedisinLege();
                blankUtfelter();
            }//søkmedisinpasient
            else if(e.getSource() == søkmedisinpasient){
                finnMedisinPasient();
                blankUtfelter();
            }
            else if(e.getSource() == navnalleleger){
                visallelegenavn();
                blankUtfelter();
            }
            else if(e.getSource() == endreresept){
                endreResepter();
                blankUtfelter();
            }
            else if(gra.isSelected()){
                reseptensgruppe = 'A';
            }
            else if(grb.isSelected()){
                reseptensgruppe = 'B';
            }
            else if(grc.isSelected()){
                reseptensgruppe = 'C';
            }
        }
    }
    
    private class Tegnlytter implements ItemListener{
        public void itemStateChanged(ItemEvent e){
            gruppeA = Abox.isSelected();
            gruppeB = Bbox.isSelected();
            gruppeC = Cbox.isSelected();
        }
    }
}
