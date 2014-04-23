/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA*/

/*Denne klassen definerer panelet som dukker opp i hovedrammen
by default
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class LogginnPanel extends JPanel{
    private final String LEGE_DATA = "1";
    private Lytter lytteren;
    
    //Sidepanel datafelter
    private JPanel sidepanel;
    private JTextArea infofelt;
    private Border sidepanelgrense;
    private JScrollPane infoscroll;
    
    //Senterpanel datafelter
    private JPanel senterpanel;
    private Border senterpanelgrense;
    private JButton lege, kontrollør;
    
    public LogginnPanel(){
        super(new BorderLayout());
        lytteren = new Lytter();
        
        //SIDEPANEL ramme
        sidepanel = new JPanel();
        sidepanel.setLayout(new GridLayout(8,1,3,3));
        sidepanelgrense = BorderFactory.createTitledBorder("Navigering");
        sidepanel.setBorder(sidepanelgrense);
        
        //SIDEPANEL infofelt
        infofelt = new JTextArea(4,18);
        infoscroll = new JScrollPane(infofelt);
        infofelt.setText("Venligs velg rolle");
        
        infofelt.setEditable(false);
        sidepanel.add(infoscroll);
        
        //SENTERPANEL ramme
        senterpanel = new JPanel();
        senterpanel.setLayout(new GridLayout(2,1,5,5));
        senterpanelgrense = BorderFactory.createTitledBorder("Logg inn");
        senterpanel.setBorder(senterpanelgrense);
        
        //SENTERPANEL knapper:
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
        JFrame hovedrammen = (JFrame) SwingUtilities.getWindowAncestor(this);
        hovedrammen.add(new LegePanel(),LEGE_DATA);
        CardLayout c = (CardLayout)hovedrammen.getContentPane().getLayout();
        c.show(hovedrammen.getContentPane(),LEGE_DATA);
    }
    
    public void visKontrollørLogginn(){
        JFrame hovedrammen = (JFrame) SwingUtilities.getWindowAncestor(this);
        hovedrammen.add(new LegePanel(),LEGE_DATA);
        CardLayout c = (CardLayout)hovedrammen.getContentPane().getLayout();
        c.show(hovedrammen.getContentPane(),LEGE_DATA);
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
