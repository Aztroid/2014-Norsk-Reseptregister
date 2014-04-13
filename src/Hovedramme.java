/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA*/

import java.awt.*;
import java.net.*;
import javax.swing.*;

public class Hovedramme extends JFrame{
    
    public Hovedramme(){
        //Oppretter Toolkit for å kunne justere programmet autmatisk
        Toolkit verktøykasse = Toolkit.getDefaultToolkit();
        Dimension skjermdimensjon = verktøykasse.getScreenSize();
        int bredde = skjermdimensjon.width;
        int høyde = skjermdimensjon.height;
        
        /*Setter rammens bredde og høyde, lar plattformen velge plassering av
          rammen*/
        setSize(bredde/2,høyde/2); //Fullskjerm/2
        setLocationByPlatform(true);//Plassering etter oprativsystemets regler
        
        //Legger til logoen i venstre hjørnet
        String bildefil = "bildefiler/ModelS.gif";
        URL kilde = Hovedramme.class.getResource(bildefil);
        if(kilde != null){
            ImageIcon bilde = new ImageIcon(kilde);
            Image ikon = bilde.getImage();
            setIconImage(ikon);
        }
        setTitle("Norsk Reseptregister");
    }
}
