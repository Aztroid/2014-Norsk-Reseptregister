/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA*/

/*Denne klassen definerer panelet som dukker opp i hovedrammen
by default
*/

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class LogginnPanel extends JPanel{
    private final String BAKGRUNN = "0";
    private final String LEGE_DATA = "1";
    private final String KONTROLL_DATA = "2";
    private final String REG_LEGE = "3";
    private Lytter lytteren;
    private Hovedramme hovedrammekopi;
    private String legeautnr;
    private TreeMap<String,Pasient> pasientliste;
    private TreeMap<String,Lege> legeliste;
    private TreeMap<Integer,Resept> reseptliste;
    
    //Logg inn
    private JTextField username;
    private JPasswordField password;

    //Sidepanel datafelter
    private JPanel sidepanel;
    private JTextArea infofelt;
    private Border sidepanelgrense;
    private JScrollPane infoscroll;
    
    //Senterpanel datafelter
    private JPanel senterpanel;
    private JPanel senterpanelreglege;
    private Border senterpanelgrense;
    private JButton logginn;
    
    //Senterpanel Bakgrunn
    private JPanel senterpanelbakgrunn;
    
    //SenterpanelLogginnlege
    private JTextField fornavnlege, etternavnlege, autorisasjonsnummer, 
            reseptbevilgning, arbeidssted;
    private JButton reglege;
    private Tegnlytter tegnlytteren;
    private JCheckBox Abox;
    private JCheckBox Bbox;
    private JCheckBox Cbox;
    private boolean gruppeA;
    private boolean gruppeB;
    private boolean gruppeC;
    
    public LogginnPanel(TreeMap<String,Pasient> pasientliste,
            TreeMap<String,Lege> legeliste,
            TreeMap<Integer,Resept> reseptliste){
        super(new BorderLayout());
        lytteren = new Lytter();
        
        this.pasientliste = pasientliste;
        this.legeliste = legeliste;
        this.reseptliste = reseptliste;
        
        //SIDEPANEL ramme
        sidepanel = new JPanel();
        sidepanel.setLayout(new GridLayout(8,1,3,3));
        sidepanelgrense = BorderFactory.createTitledBorder("Navigering");
        sidepanel.setBorder(sidepanelgrense);
        
        //SIDEPANEL infofelt
        infofelt = new JTextArea(4,18);
        infoscroll = new JScrollPane(infofelt);
        infofelt.setText("Logg inn");
        
        infofelt.setEditable(false);
        sidepanel.add(infoscroll);
        
        logginn = new JButton("Logg inn");
        logginn.setVerticalTextPosition( AbstractButton.BOTTOM );
        logginn.setHorizontalTextPosition( AbstractButton.CENTER );
        logginn.addActionListener(lytteren);
        sidepanel.add(logginn);

        //SENTERPANEL ramme
        senterpanel = new JPanel(new CardLayout());
        senterpanelgrense = BorderFactory.createTitledBorder("Logg inn");
        senterpanel.setBorder(senterpanelgrense);
        
        //SENTERPANEL Reglege:
        senterpanelreglege = new JPanel(new GridLayout(0,1));
        
        senterpanelreglege.add(new JLabel("Autorisasjonsnr: "));
        autorisasjonsnummer = new JTextField(30);
        autorisasjonsnummer.addActionListener(lytteren);
        senterpanelreglege.add(autorisasjonsnummer);

        senterpanelreglege.add(new JLabel("Fornavn Lege: "));
        fornavnlege = new JTextField(30);
        fornavnlege.addActionListener(lytteren);
        senterpanelreglege.add(fornavnlege);

        senterpanelreglege.add(new JLabel("Etternavn Lege: "));
        etternavnlege = new JTextField(30);
        etternavnlege.addActionListener(lytteren);
        senterpanelreglege.add(etternavnlege);
        
        senterpanelreglege.add(new JLabel("Godkjent Reseptbevilkning:"));
        Abox = new JCheckBox("Gruppe A");
        Abox.addItemListener(tegnlytteren);
        senterpanelreglege.add(Abox);
        Bbox = new JCheckBox("Gruppe B");
        Bbox.addItemListener(tegnlytteren);
        senterpanelreglege.add(Bbox);
        Cbox = new JCheckBox("Gruppe C");
        Cbox.addItemListener(tegnlytteren);
        senterpanelreglege.add(Cbox);
        
        senterpanelreglege.add(new JLabel("Arbeidssted: "));
        arbeidssted = new JTextField(30);
        arbeidssted.addActionListener(lytteren);
        senterpanelreglege.add(arbeidssted);
        
        reglege = new JButton("Register Lege");
        reglege.addActionListener(lytteren);
        senterpanelreglege.add(reglege);
        
        //Senterpanel Bakgrunn
        senterpanelbakgrunn = new JPanel();
        
        //Legger til panelene
        senterpanel.add(senterpanelbakgrunn,BAKGRUNN);
        senterpanel.add(senterpanelreglege,REG_LEGE);
        super.add(sidepanel, BorderLayout.LINE_START);
        super.add(senterpanel, BorderLayout.CENTER);
    }
    
    public void sjekk() {
        String autorisasjonsnrrregex = "\\d{9}";
        JLabel jUserName = new JLabel("Aut-Nr");
        JTextField username = new JTextField();
        JLabel jPassword = new JLabel("Passord");
        JTextField password = new JPasswordField();
        Object[] ob = {jUserName, username, jPassword, password};
        int result = JOptionPane.showConfirmDialog(null, ob, "Logg inn", JOptionPane.OK_CANCEL_OPTION);
 
        if (result == JOptionPane.OK_OPTION){
            String un = username.getText();
            String pw = password.getText();
            if (un.equalsIgnoreCase("Admin") && pw.equals("guest")) {
                visKontrollørLogginn();
            } 
            else if (username.getText().matches(autorisasjonsnrrregex)){
                visLegeVindu(username.getText());
                /*Lege finnes = legeliste.get(username.getText());
                if(finnes!=null){
                    visLegeVindu(username.getText());
                }
                else{
                    visRegLege();
                }*/
            }
        }
    }
    
    public void visLegeVindu(String autnr){
        hovedrammekopi = (Hovedramme) SwingUtilities.getWindowAncestor(this);
        hovedrammekopi.add(new LegePanel(autnr,"ABC",pasientliste,reseptliste)
                ,LEGE_DATA);
        hovedrammekopi.visPanel(LEGE_DATA);
    }
    
    public void visKontrollørLogginn(){
        hovedrammekopi = (Hovedramme) SwingUtilities.getWindowAncestor(this);
        hovedrammekopi.add(new KontrollørPanel(pasientliste,legeliste,reseptliste),KONTROLL_DATA);
        hovedrammekopi.visPanel(KONTROLL_DATA);
    }
    
    public void visRegLege(){
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.show(senterpanel,REG_LEGE);
    }
    
    private boolean blankeLegefelter(){
        //Sjekker for blanke felter ved registrering av lege
        return (fornavnlege.getText().matches("")||etternavnlege.getText().
                matches("")||autorisasjonsnummer.getText().matches("")||
                autorisasjonsnummer.getText().matches(""));
    }
    
    private void regNyLege(){
        if(blankeLegefelter()){
            infofelt.setText("Et eller fler av feltene er tomme");
            return;
        }
        String autorisasjonsnrrregex = "\\d{9}";
        String legenøkkel = autorisasjonsnummer.getText();
        String fornavn = fornavnlege.getText();
        String etternavn = etternavnlege.getText();
        String adresse = arbeidssted.getText();
        String reseptbev = "";
        if(!legenøkkel.matches(autorisasjonsnrrregex)){
            infofelt.setText("Autorisasjonsnummeret du har skrevet inn er "
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
            Lege ny = new Lege(fornavn, etternavn, legenøkkel, adresse, 
                    reseptbev);
            legeliste.put(legenøkkel,ny);
            //LAGRE LEGELISTEN
            infofelt.setText("Lege registrert.");
            CardLayout c = (CardLayout)senterpanel.getLayout();
            c.show(senterpanel,BAKGRUNN);
            sjekk();
        }
        else{
            infofelt.setText("Legen finnes i registeret fra før");
        }
    }
    
    private class Lytter implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==logginn){
                sjekk();  
            }
            else if(e.getSource()==reglege){
                regNyLege();
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
