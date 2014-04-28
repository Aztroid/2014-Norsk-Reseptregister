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
    private final String LEGE_DATA = "0";
    private final String KONTROLL_DATA = "1";
    private Lytter lytteren;
    private Hovedramme hovedrammekopi;
    private String legeautnr;
    private TreeMap<String,Pasient> pasientliste;
    private TreeMap<String,Lege> legeliste;
    private TreeMap<Integer,Resept> reseptliste;
    
    //Logg inn
    private JTextField username;
    private JPasswordField password;
    private JButton submit;
    
    
    //Sidepanel datafelter
    private JPanel sidepanel;
    private JTextArea infofelt;
    private Border sidepanelgrense;
    private JScrollPane infoscroll;
    
    //Senterpanel datafelter
    private JPanel senterpanel;
    private Border senterpanelgrense;
    private JButton lege, kontrollør;
    private JTextField legebrukernavn;
    
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
        infofelt.setText("Vennligst velg rolle");
        
        infofelt.setEditable(false);
        sidepanel.add(infoscroll);
        
        //SENTERPANEL ramme
        senterpanel = new JPanel();
        senterpanel.setLayout(new GridLayout(0,1));
        senterpanelgrense = BorderFactory.createTitledBorder("Logg inn");
        senterpanel.setBorder(senterpanelgrense);
        
        //SENTERPANEL content:
        legebrukernavn = new JTextField(15);
        senterpanel.add(new JLabel("Søk Pasient"));
        senterpanel.add(legebrukernavn);
        //setLayout(new FlowLayout);
        username = new JTextField(10);
        password = new JPasswordField(10);
        submit = new JButton("Login");
       
        add(username);
        add(password);
        add(submit);

        Icon legeikon = new ImageIcon(getClass().getResource("bildefiler/knapp1_lege.gif"));
        lege = new JButton("Lege", legeikon);
        lege.setVerticalTextPosition( AbstractButton.BOTTOM );
        lege.setHorizontalTextPosition( AbstractButton.CENTER );
        lege.addActionListener(lytteren);
        senterpanel.add(lege);
        
        Icon kontrollørikon = new ImageIcon(getClass().getResource
            ("bildefiler/knapp2_kontrolloer.gif"));
        kontrollør = new JButton("Kontrollør", kontrollørikon);
        kontrollør.setVerticalTextPosition( AbstractButton.BOTTOM );
        kontrollør.setHorizontalTextPosition( AbstractButton.CENTER );
        kontrollør.addActionListener(lytteren);
        senterpanel.add(kontrollør);
        
        super.add(sidepanel, BorderLayout.LINE_START);
        super.add(senterpanel, BorderLayout.CENTER);
    }
    
    public void visLegeVindu(){
        hovedrammekopi = (Hovedramme) SwingUtilities.getWindowAncestor(this);
        legeautnr = legebrukernavn.getText();
        hovedrammekopi.add(new LegePanel(legeautnr,pasientliste,reseptliste)
                ,LEGE_DATA);
        hovedrammekopi.visPanel(LEGE_DATA);
    }
    
    public void visKontrollørLogginn(){
        hovedrammekopi = (Hovedramme) SwingUtilities.getWindowAncestor(this);
        legeautnr = legebrukernavn.getText();
        hovedrammekopi.add(new KontrollørPanel(pasientliste,legeliste,reseptliste),KONTROLL_DATA);
        hovedrammekopi.visPanel(KONTROLL_DATA);
    }
    
    private class Lytter implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==lege){
                visLegeVindu();
            }
            else{
                visKontrollørLogginn();
            }
        }
    }
}
