import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA
 */

public class Statistikkpanel extends JPanel{
    
    //Kordinatdata
    private int[] førstelinje;
    private int[] andrelinje;

            
    public Statistikkpanel(int[] førstekord){
        førstelinje = førstekord;
        genererPunkter();
    }
    
    public Statistikkpanel(int[] førstekord, int[] andrekord ){
        førstelinje = førstekord;
        andrelinje = andrekord;
        genererPunkter();
    }
    
    public void genererPunkter(){
        //Gir statistikk grafen korrekte verdier for månedene sine resept utskrift.
        for(int i = 0; i<førstelinje.length; i++){
            førstelinje[i] = førstelinje[i]*4;
        }
        if(andrelinje!=null){
            for(int i = 0; i<andrelinje.length; i++){
                    andrelinje[i] = andrelinje[i]*4;
            }
        }
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graph2 = (Graphics2D)g;
        graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        Graphics2D graph3 = (Graphics2D)g;
        graph3.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
       
       this.setBackground(Color.WHITE);
               
        Shape drawLine = new Line2D.Float(55, 25, 55 , 455);
	Shape drawLine2 = new Line2D.Float(55, 455, 1000, 455);
        
        //førstelinje
	Shape jan = new Line2D.Float(55, 455, 135, 455 - førstelinje[0]);
	Shape feb = new Line2D.Float(135, 455 - førstelinje[0], 215, 455 - førstelinje[1]);
	Shape mar = new Line2D.Float(215, 455 - førstelinje[1], 295, 455 - førstelinje[2]);
	Shape apr = new Line2D.Float(295, 455 - førstelinje[2], 375, 455 - førstelinje[3]);
	Shape may = new Line2D.Float(375, 455 - førstelinje[3], 455, 455 - førstelinje[4]);
	Shape jun = new Line2D.Float(455, 455 - førstelinje[4], 535, 455 - førstelinje[5]);
	Shape jul = new Line2D.Float(535, 455 - førstelinje[5], 615, 455 - førstelinje[6]);		
	Shape aug = new Line2D.Float(615, 455 - førstelinje[6], 695, 455 - førstelinje[7]);
	Shape sep = new Line2D.Float(695, 455 - førstelinje[7], 775, 455 - førstelinje[8]);
	Shape oct = new Line2D.Float(775, 455 - førstelinje[8], 855, 455 - førstelinje[9]);	
	Shape nov = new Line2D.Float(855, 455 - førstelinje[9], 935, 455 - førstelinje[10]);
	Shape dec = new Line2D.Float(935, 455 - førstelinje[10], 1015, 455 - førstelinje[11]);
        
        //andrelinje
        
        /*
        Shape jan2 = new Line2D.Float(55, 455, 135, 455 - andrelinje[0]);
	Shape feb2 = new Line2D.Float(135, 455 - andrelinje[0], 215, 455 - andrelinje[1]);
	Shape mar2 = new Line2D.Float(215, 455 - andrelinje[1], 295, 455 - andrelinje[2]);
	Shape apr2 = new Line2D.Float(295, 455 - andrelinje[2], 375, 455 - andrelinje[3]);
	Shape may2 = new Line2D.Float(375, 455 - andrelinje[3], 455, 455 - andrelinje[4]);
	Shape jun2 = new Line2D.Float(455, 455 - andrelinje[4], 535, 455 - andrelinje[5]);
	Shape jul2 = new Line2D.Float(535, 455 - andrelinje[5], 615, 455 - andrelinje[6]);		
	Shape aug2 = new Line2D.Float(615, 455 - andrelinje[6], 695, 455 - andrelinje[7]);
	Shape sep2 = new Line2D.Float(695, 455 - andrelinje[7], 775, 455 - andrelinje[8]);
	Shape oct2 = new Line2D.Float(775, 455 - andrelinje[8], 855, 455 - andrelinje[9]);	
	Shape nov2 = new Line2D.Float(855, 455 - andrelinje[9], 935, 455 - andrelinje[10]);
	Shape dec2 = new Line2D.Float(935, 455 - andrelinje[10], 1015, 455 - andrelinje[11]);	
        */
        graph2.setPaint(Color.BLACK);
	
        //x og y linjen
        graph2.draw(drawLine);
        graph2.draw(drawLine2);
        //førstelinje
        graph2.setPaint(Color.RED);
	graph2.draw(jan);
	graph2.draw(feb);
	graph2.draw(mar);
	graph2.draw(apr);
	graph2.draw(may);	
	graph2.draw(jun);	
	graph2.draw(jul);
	graph2.draw(aug);
	graph2.draw(sep);
	graph2.draw(oct);	
	graph2.draw(nov);
	graph2.draw(dec);
        
        //andrelinje
      /*  graph3.setPaint(Color.BLUE);
	graph3.draw(dec2);
        graph3.draw(jan2);
        graph3.draw(feb2);
	graph3.draw(mar2);
	graph3.draw(apr2);
	graph3.draw(may2);	
	graph3.draw(jun2);
	graph3.draw(jul2);
        graph3.draw(aug2);
	graph3.draw(sep2);
	graph3.draw(oct2);
        graph3.draw(nov2);
	graph3.draw(dec2);
        */
        
     
	
	
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.BLACK);
	g.drawString("100 -", 10, 55);
	g.drawString("90  -", 10, 95);
	g.drawString("80  -", 10, 135);
	g.drawString("70  -", 10, 175);
	g.drawString("60  -", 10, 215);
	g.drawString("50  -", 10, 255);
	g.drawString("40  -", 10, 295);
	g.drawString("30  -", 10, 335);
	g.drawString("20  -", 10, 375);
	g.drawString("10  -", 10, 415);
	g.drawString("0   ", 10, 475);
	g.drawString("  jan ", 55, 475);
	g.drawString("| feb ", 135, 475);
	g.drawString("| mar ", 215, 475);
	g.drawString("| apr ", 295, 475);
	g.drawString("| mai ", 375, 475);
	g.drawString("| jun ", 455, 475);
	g.drawString("| jul ", 535, 475);
	g.drawString("| aug ", 615, 475);
	g.drawString("| sep ", 695, 475);
	g.drawString("| okt ", 775, 475);
	g.drawString("| nov ", 855, 475);
	g.drawString("| des ", 935, 475);	
    }
}

