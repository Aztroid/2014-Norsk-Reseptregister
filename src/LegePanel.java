/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA
 */

//Dette panelet er vinduet hvor legen får se oversikt over sine pasienter

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;

public class LegePanel extends JPanel{
    private final String VISDATA = "0";
    private final String NY_PASIENT = "0";
    private final String NY_RESEPT = "1";
    private Lytter lytteren;
    private TreeMap<Integer,Resept> reseptliste;
    private TreeMap<Integer,Resept> spesifikkreseptliste;
    private TreeMap<String,Pasient> pasientliste;
    private String legensautnr;
    private Hovedramme hovedrammekopi;
    
    //Sidepanel datafelter
    private JPanel sidepanel;
    private JTextArea infofelt;
    private Border sidepanelgrense;
    private JScrollPane infoscroll;
    private JButton nypasient,nyresept,gåtilbake;
    
    //Senterpanel datafelter
    private JPanel senterpanel,senterpanelvisdata, senterpaneltop,
            senterpanelnorthnypasient, senterpanelnorthnyresept,
            senterpanelsouth;
    private TabellVindu tabellen;
    private Border senterpanelgrense;
    private JButton søk,regpasient,regresept;
    private JTextField søkpasientid,søkreseptid,
           /*REGPASIENT*/ fornavnpasient, etternavnpasient, fødselsnr,
            
           /*REGRESEPT*/ fødselsnrresept, autnrresept, medisinnøkkel, mengde,
            defdøgndosering, kategori,søkefelt;
    private JRadioButton gra, grb, grc;
    private ButtonGroup resteptgruppealt;
    
    public LegePanel(String autnr,TreeMap<String,Pasient> pasientliste,
            TreeMap<Integer,Resept> reseptliste){
        
        super.setLayout(new BorderLayout());
        lytteren = new Lytter();
        legensautnr = autnr;
        this.pasientliste = pasientliste;
        this.reseptliste = reseptliste;
        spesifikkreseptliste = new TreeMap<>();
        
        //SIDEPANEL ramme
        sidepanel = new JPanel();
        sidepanel.setLayout(new GridLayout(8,1,3,3));
        sidepanelgrense = BorderFactory.createTitledBorder("Navigering");
        sidepanel.setBorder(sidepanelgrense);
        
        //SIDEPANEL infofelt
        infofelt = new JTextArea(4,18);
        infoscroll = new JScrollPane(infofelt);
        infofelt.setText("Oversikt over dine resepter");
        
        infofelt.setEditable(false);
        sidepanel.add(infoscroll);
        
        //SIDEPANEL knapper
        gåtilbake = new JButton("Tilbake til meny");
        gåtilbake.addActionListener(lytteren);
        sidepanel.add(gåtilbake);
        
        nypasient = new JButton("Registrer Pasient");
        nypasient.addActionListener(lytteren);
        sidepanel.add(nypasient);
        
        nyresept = new JButton("Registrer Resept");
        nyresept.addActionListener(lytteren);
        sidepanel.add(nyresept);
        
        //SENTERPANEL ramme
        senterpanel = new JPanel(new CardLayout());
        senterpanelgrense = BorderFactory.createTitledBorder("Reseptoversikt");
        senterpanel.setBorder(senterpanelgrense);
        senterpanelvisdata = new JPanel(new BorderLayout());
        senterpaneltop = new JPanel(new FlowLayout());
        senterpanelnorthnypasient = new JPanel(new FlowLayout());
        senterpanelnorthnyresept = new JPanel(new FlowLayout());
        senterpanelsouth = new JPanel(new FlowLayout());
         
        //SENTERPANELTOP content:
        søkpasientid = new JTextField(15);
        senterpaneltop.add(new JLabel("Søk Pasient"));
        senterpaneltop.add(søkpasientid);
        
        søkreseptid = new JTextField(15);
        senterpaneltop.add(new JLabel("Søk Resept"));
        senterpaneltop.add(søkreseptid);
        
        søk = new JButton("Søk");
        søk.addActionListener(lytteren);
        senterpaneltop.add(søk);
        
        //SENTERPANEL Ny Pasient
        senterpanelnorthnypasient.add(new JLabel("Personnr: "));
        fødselsnr = new JTextField(30);
        fødselsnr.addActionListener(lytteren);
        senterpanelnorthnypasient.add(fødselsnr);

        senterpanelnorthnypasient.add(new JLabel("Fornavn Pasient: "));
        fornavnpasient = new JTextField(30);
        fornavnpasient.addActionListener(lytteren);
        senterpanelnorthnypasient.add(fornavnpasient);

        senterpanelnorthnypasient.add(new JLabel("Etternavn Pasient: "));
        etternavnpasient = new JTextField(30);
        etternavnpasient.addActionListener(lytteren);
        senterpanelnorthnypasient.add(etternavnpasient);
        
        regpasient = new JButton("Register Pasient");
        regpasient.addActionListener(lytteren);
        senterpanelnorthnypasient.add(regpasient);
        
        //SENTERPANEL Ny Resept
        senterpanelnorthnyresept.add(new JLabel("Pasient(Fnr): "));
        fødselsnrresept = new JTextField(30);
        fødselsnrresept.addActionListener(lytteren);
        senterpanelnorthnyresept.add(fødselsnrresept);

        senterpanelnorthnyresept.add(new JLabel("Leg(Aut.nr): "));
        autnrresept = new JTextField(30);
        autnrresept.addActionListener(lytteren);
        senterpanelnorthnyresept.add(autnrresept);

        senterpanelnorthnyresept.add(new JLabel("ACT-Nr: "));
        medisinnøkkel = new JTextField(30);
        medisinnøkkel.addActionListener(lytteren);
        senterpanelnorthnyresept.add(medisinnøkkel);
        
        senterpanelnorthnyresept.add(new JLabel("Mengde(gr): "));
        mengde = new JTextField(30);
        mengde.addActionListener(lytteren);
        senterpanelnorthnyresept.add(mengde);
        
        senterpanelnorthnyresept.add(new JLabel("Diag.Døgn: "));
        defdøgndosering = new JTextField(30);
        defdøgndosering.addActionListener(lytteren);
        senterpanelnorthnyresept.add(defdøgndosering);

        senterpanelnorthnyresept.add(new JLabel("Kategori: "));
        kategori = new JTextField(30);
        kategori.addActionListener(lytteren);
        senterpanelnorthnyresept.add(kategori);
        
        senterpanelnorthnyresept.add(new JLabel("Reseptgruppe(felleskatalogen): "));
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
        senterpanelnorthnyresept.add(gra);
        senterpanelnorthnyresept.add(grb);
        senterpanelnorthnyresept.add(grc);
        
        //LEGGER ALLE PANELER TIL
        senterpanelvisdata.add(senterpaneltop,BorderLayout.PAGE_START);
        senterpanel.add(senterpanelvisdata,VISDATA);
        senterpanel.add(senterpanelnorthnypasient, NY_PASIENT);
        senterpanel.add(senterpanelnorthnyresept, NY_RESEPT);
        super.add(sidepanel, BorderLayout.LINE_START);
        super.add(senterpanel, BorderLayout.CENTER);
                
        //SENTERPANEL tabell
        filtrerLegelisten(legensautnr);/*Finner kun de reseptene den 
                                        aktuelle legen har skrevet utr*/
        visFørste();//Viser det første panelet
    }
    
    public void visFørste(){
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.first(senterpanel);
    }
    
    private void matTabellen(){
        return;
    }
    
    public void filtrerLegelisten(String autnr){
        Resept løper;
        if(!legensautnr.matches("")){
            for(Map.Entry<Integer,Resept> entry:reseptliste.entrySet()){
                løper = entry.getValue();
                if(legensautnr.equalsIgnoreCase(løper.getLege())){
                    spesifikkreseptliste.put(løper.getReseptnr(),løper);
                }
            }
        }
        else{
            infofelt.setText("Legenummeret var tomt\nAlle resepter vises\n"
                    + "Fordi jeg er lat");
        }
    }
    
    public void nyPasient(){
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.show(senterpanel,NY_PASIENT);
    }
    
    public void nyResept(){
        CardLayout c = (CardLayout)senterpanel.getLayout();
        c.show(senterpanel,NY_RESEPT);
    }
    
    public void tilbakeTilMeny(){
        hovedrammekopi = (Hovedramme) SwingUtilities.getWindowAncestor(this);
        hovedrammekopi.visFørste();
    }
    
    private class Lytter implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==gåtilbake){
                tilbakeTilMeny();
            }
            else if(e.getSource()==nypasient){
                nyPasient();
            }
            else{
                nyResept();
            }
        }
    }
}
