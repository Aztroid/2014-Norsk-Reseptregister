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
    private final String BAKGRUNN = "2";
    private final String LEGE_DATA = "3";
    private final String KONTROLL_DATA = "4";
    private final String LOGG_INN = "5";
    private Lytter lytteren;
    private Hovedramme hovedrammekopi;
    private TreeMap<String,Pasient> pasientliste;
    private TreeMap<String,Lege> legeliste;
    private TreeMap<Integer,Resept> reseptliste;
    private TreeMap<Integer,Kontrollør> kontrollørliste;

    //Sidepanel datafelter
    private JPanel sidepanel;
    private JTextArea infofelt;
    private Border sidepanelgrense;
    private JScrollPane infoscroll;
    private JButton logginn,admin;
    
    //Senterpanel datafelter
    private JPanel senterpanel;
    private Border senterpanelgrense;
    
    //Senterpanel Bakgrunn
    private JPanel senterpanelbakgrunn;
    
    //Senterpanel Logginn
    private JPanel senterpanellogginn;
    private GridBagConstraints c;
    private JTextField username;
    private JPasswordField password;
    private JButton okknapp;
   
    public LogginnPanel( 
            TreeMap<String,Pasient> pasientliste,
            TreeMap<String,Lege> legeliste,
            TreeMap<Integer,Resept> reseptliste,
            TreeMap<Integer,Kontrollør> kontrollørliste){
        super(new BorderLayout());
        lytteren = new Lytter();
        
        this.pasientliste = pasientliste;
        this.legeliste = legeliste;
        this.reseptliste = reseptliste;
        this.kontrollørliste = kontrollørliste;
        
        //SIDEPANEL
        sidepanel = new JPanel();
        sidepanel.setLayout(new GridLayout(8,1,3,3));
        sidepanelgrense = BorderFactory.createTitledBorder("Navigering");
        sidepanel.setBorder(sidepanelgrense);
        
        infofelt = new JTextArea(4,18);
        infoscroll = new JScrollPane(infofelt);
        infofelt.setText("Velkommen, klikk på en av\nknappene");
        
        infofelt.setEditable(false);
        sidepanel.add(infoscroll);
        
        logginn = new JButton("Logg inn");
        logginn.setVerticalTextPosition( AbstractButton.BOTTOM );
        logginn.setHorizontalTextPosition( AbstractButton.CENTER );
        logginn.addActionListener(lytteren);
        sidepanel.add(logginn);
        
        admin = new JButton("Administrator");
        admin.addActionListener(lytteren);
        sidepanel.add(admin);

        //SENTERPANEL ramme
        senterpanel = new JPanel(new CardLayout());
        senterpanelgrense = BorderFactory.createTitledBorder("Logg inn");
        senterpanel.setBorder(senterpanelgrense);
           
        //Senterpanel Bakgrunn
        senterpanelbakgrunn = new JPanel();
        
        //Senterpanel Logginn
        senterpanellogginn = new JPanel(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = 5;
        c.ipady = 5;
        
        senterpanellogginn.add(new JLabel("Aut-Nr / Brukernavn"),c);
        c.gridx = 1;
        username = new JTextField(15);
        senterpanellogginn.add(username,c);
        c.gridx = 0;
        
        c.gridy = 1;
        senterpanellogginn.add(new JLabel("Passord"),c);
        c.gridx = 1;
        password = new JPasswordField(15);
        senterpanellogginn.add(password,c);
        
        c.gridy = 2;
        okknapp = new JButton("Ok");
        okknapp.addActionListener(lytteren);
        senterpanellogginn.add(okknapp,c);
        
        //Legger til panelene
        senterpanel.add(senterpanelbakgrunn,BAKGRUNN);
        senterpanel.add(senterpanellogginn,LOGG_INN);
        super.add(sidepanel, BorderLayout.LINE_START);
        super.add(senterpanel, BorderLayout.CENTER);
    }
    
    public void visLogginn(){
        //Viser Logginn Panelet
        infofelt.setText("Logg inn med brukernavn/passord\ntildelt av "
                + "administrator");
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.show(senterpanel,LOGG_INN);
    }
    
    public void sjekk(){
        /*Finner ut hvilken type bruker som prøver å logge seg inn, og om denne
        brukeren er registrert*/
        String autorisasjonsnrrregex = "\\d{9}";
        try{
        if (username.getText().matches(autorisasjonsnrrregex)){
            Lege bruker = legeliste.get(username.getText());
            if(bruker!=null){
                if(password.getText().matches(bruker.getPword())){
                    visLegeVindu(bruker.getAutorisasjonsnr(),
                            bruker.getBevilgning());
                    blankelogginnfelter();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Feil Passord",
                            "Advarsel",JOptionPane.WARNING_MESSAGE);
                    blankelogginnfelter();
                }
            }
            else{
                infofelt.setText("Legen er ikke i systemet\n"
                        + "kontakt administrator for\nopprettelse av ny lege");
                blankelogginnfelter();
            }
        }
        else{
            Kontrollør bruker = kontrollørliste.get(Integer.parseInt(
                    username.getText()));
            if(bruker!=null){
                if(password.getText().matches(bruker.getPword())){
                    visKontrollørLogginn();
                    blankelogginnfelter();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Feil Passord",
                            "Advarsel",JOptionPane.WARNING_MESSAGE);
                    blankelogginnfelter();
                }
            }
            else{
                infofelt.setText("Kontrolløren er ikke i systemet\n"
                        + "kontakt administrator for\nopprettelse av ny "
                        + "kontrollør");
                blankelogginnfelter();
            }
        }
        }
        /*catch(NullPointerException NPE){
            infofelt.setText("Feil innlogginsdata, prøv igjen");
        }*/
        catch(NumberFormatException nfe){
            infofelt.setText("Feil innlogginsdata, prøv igjen");
        }
    }
    
    public void sjekkAdmin(){
        /*Må skrive inn adminnøkkelen i logginn for å komme inn*/
        infofelt.setText("Tast inn administratornøkkel\n for å komme inn");
        String adminnøkkel = "guest";
        
        try{
            String input = JOptionPane.showInputDialog
            (null,"Skriv inn adminnøkkel: ");
            if(input.matches(adminnøkkel)){
                visAdminPanel();
            }
            else{
                JOptionPane.showMessageDialog(null, "Feil Passord",
                                "Advarsel",JOptionPane.WARNING_MESSAGE);
            }
        }
        catch(NullPointerException NPE){
            
        }
        catch(NumberFormatException nfe){
            
        }
    }
    
    public void visFørste(){
        /*Metode som viser det første panelet i lagt til i senterpanelet
        dette er visdatapanelet før tabellen er generert*/
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.first(senterpanel);
    }
    
    public void visAdminPanel(){
        hovedrammekopi = (Hovedramme) SwingUtilities.getWindowAncestor(this);
        hovedrammekopi.visAdmin();
    }
    
    public void visLegeVindu(String autnr, String reseptbev){
        hovedrammekopi = (Hovedramme) SwingUtilities.getWindowAncestor(this);
        hovedrammekopi.add(new LegePanel(autnr,reseptbev,
                pasientliste,reseptliste),LEGE_DATA);
        hovedrammekopi.visPanel(LEGE_DATA);
    }
    
    public void visKontrollørLogginn(){
        hovedrammekopi = (Hovedramme) SwingUtilities.getWindowAncestor(this);
        hovedrammekopi.add(new KontrollørPanel(pasientliste,legeliste,
                reseptliste),KONTROLL_DATA);
        hovedrammekopi.visPanel(KONTROLL_DATA);
    }
    
    private void blankelogginnfelter(){
        username.setText("");
        password.setText("");    
    }
    
    private class Lytter implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==logginn){
                visLogginn();  
            }
            else if(e.getSource()==okknapp){
                sjekk();
            }
            else if(e.getSource()==admin){
                sjekkAdmin();
            }
        }
    }
}//End of class LogginnPanel
