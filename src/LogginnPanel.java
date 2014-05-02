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
    private final String ADMIN = "3";
    private final String LOGG_INN = "4";
    private Lytter lytteren;
    private Hovedramme hovedrammekopi;
    private TreeMap<String,Pasient> pasientliste;
    private TreeMap<String,Lege> legeliste;
    private TreeMap<Integer,Resept> reseptliste;
    private TreeMap<Integer,Kontrollør> kontrollørliste;
    private Integer sisteresept;

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
   
    public LogginnPanel(Integer sisteresept, 
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
        this.sisteresept = sisteresept;
        
        //SIDEPANEL
        sidepanel = new JPanel();
        sidepanel.setLayout(new GridLayout(8,1,3,3));
        sidepanelgrense = BorderFactory.createTitledBorder("Navigering");
        sidepanel.setBorder(sidepanelgrense);
        
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
        infofelt.setText("Logg inn med brukernavn/passord\ntildelt av administrator");
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.show(senterpanel,LOGG_INN);
    }
    
    public void sjekk(){
        /*Finner ut hvilken type bruker som prøver å logge seg inn, og om denne
        brukeren er registrert*/
        Lege bruker;
        String autorisasjonsnrrregex = "\\d{9}";
        String passord = password.getText();
        if (username.getText().matches(autorisasjonsnrrregex)){
            bruker = legeliste.get(username.getText());
            if(bruker!=null){
                visLegeVindu(bruker.getAutorisasjonsnr(),
                        bruker.getBevilgning());
            }
            else{
                infofelt.setText("Kontakt administrator for\nopprettelse av"
                        + " ny lege");
                visFørste();
            }
        }
        else{
            visKontrollørLogginn();
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
        hovedrammekopi.add(new LegePanel(autnr,reseptbev,sisteresept,pasientliste,reseptliste)
                ,LEGE_DATA);
        hovedrammekopi.visPanel(LEGE_DATA);
    }
    
    public void visKontrollørLogginn(){
        hovedrammekopi = (Hovedramme) SwingUtilities.getWindowAncestor(this);
        hovedrammekopi.add(new KontrollørPanel(pasientliste,legeliste,reseptliste),KONTROLL_DATA);
        hovedrammekopi.visPanel(KONTROLL_DATA);
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
                visAdminPanel();
            }
        }
    }
}//End of class LogginnPanel
