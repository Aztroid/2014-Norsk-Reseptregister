
import java.awt.Color;
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
                førstelinje[i] = førstelinje[i]*2;
       }
       if(andrelinje!=null){
           for(int i = 0; i<andrelinje.length; i++){
                andrelinje[i] = andrelinje[i]*2;
           }
       }   
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graph2 = (Graphics2D)g;
        graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
       
       this.setBackground(Color.WHITE);
               
        Shape drawLine = new Line2D.Float(55, 25, 55 , 255);
	Shape drawLine2 = new Line2D.Float(55, 255, 500, 255);
	Shape jan = new Line2D.Float(55, 255 , 95, 255 - førstelinje[0]);
	Shape feb = new Line2D.Float(95, 255 - førstelinje[0], 135, 255 - førstelinje[1]);
	Shape mar = new Line2D.Float(135, 255 - førstelinje[1], 175, 255 - førstelinje[2]);
	Shape apr = new Line2D.Float(175, 255 - førstelinje[2], 215, 255 - førstelinje[3]);
	Shape may = new Line2D.Float(215, 255 - førstelinje[3], 255, 255 - førstelinje[4]);
	Shape jun = new Line2D.Float(255, 255 - førstelinje[4], 295, 255 - førstelinje[5]);
	Shape jul = new Line2D.Float(295, 255 - førstelinje[5], 335, 255 - førstelinje[6]);		
	Shape aug = new Line2D.Float(335, 255 - førstelinje[6], 375, 255 - førstelinje[7]);
	Shape sep = new Line2D.Float(375, 255 - førstelinje[7], 415, 255 - førstelinje[8]);
	Shape oct = new Line2D.Float(415, 255 - førstelinje[8], 455, 255 - førstelinje[9]);	
	Shape nov = new Line2D.Float(455, 255 - førstelinje[9], 495, 255 - førstelinje[10]);
	Shape dec = new Line2D.Float(495, 255 - førstelinje[10], 535, 255 - førstelinje[11]);	
        graph2.setPaint(Color.BLACK);
	graph2.setPaint(Color.RED);
	graph2.draw(jan);
	graph2.setPaint(Color.BLACK);
	graph2.draw(feb);
	graph2.setPaint(Color.GREEN);
	graph2.draw(mar);
	graph2.setPaint(Color.RED);
	graph2.draw(apr);
	graph2.setPaint(Color.BLUE);
	graph2.draw(may);	
	graph2.setPaint(Color.GREEN);
	graph2.draw(jun);	
	graph2.setPaint(Color.BLACK);
	graph2.draw(jul);
	graph2.setPaint(Color.RED);
	graph2.draw(aug);
	graph2.setPaint(Color.GREEN);
	graph2.draw(sep);
	graph2.setPaint(Color.BLUE);
	graph2.draw(oct);	
	graph2.setPaint(Color.BLACK);
	graph2.draw(nov);
	graph2.setPaint(Color.GREEN);
	graph2.draw(dec);
	graph2.setPaint(Color.BLUE);
        g.drawLine(55, 45, 55 , 255);
	g.drawLine(55, 255, 523, 255);
	g.setColor(Color.BLACK);
	g.setColor(Color.RED);
	/*
        g.drawLine(55, 255 , 95, 255 - jan2);
	
	g.drawLine(95, 255 - feb1, 135, 255 - feb2);

	g.drawLine(135, 255 - mar1, 175, 255 - mar2);
	
	g.drawLine(175, 255 - apr1, 215, 255 - apr2);

	g.drawLine(215, 255 - may1, 255, 255 - may2);	
	
	g.drawLine(255, 255 - jun1, 270, 255 - jun2);	
	
	g.drawLine(295, 255 - jul1, 335, 255 - jul2);
	
	g.drawLine(335, 255 - aug1, 375, 255 - aug2);
	
	g.drawLine(375, 255 - sep1, 415, 255 - sep2);
	
	g.drawLine(415, 255 - oct1, 455, 255 - oct2);	
	
	g.drawLine(455, 255 - nov1, 495, 255 - nov2);
	
	g.drawLine(495, 255 - dec1, 535, 255 - dec2);
        */
	g.drawString("100-", 25, 55);
	g.drawString("90  -", 25, 75);
	g.drawString("80  -", 25, 95);
	g.drawString("70  -", 25, 115);
	g.drawString("60  -", 25, 135);
	g.drawString("50  -", 25, 155);
	g.drawString("40  -", 25, 175);
	g.drawString("30  -", 25, 195);
	g.drawString("20  -", 25, 215);
	g.drawString("10  -", 25, 235);
	g.drawString("0   ", 25, 255);
	g.drawString("| jan ", 55, 265);
	g.drawString("| feb ", 95, 265);
	g.drawString("| mar ", 135, 265);
	g.drawString("| apr ", 175, 265);
	g.drawString("| mai ", 215, 265);
	g.drawString("| jun ", 255, 265);
	g.drawString("| jul ", 295, 265);
	g.drawString("| aug ", 335, 265);
	g.drawString("| sep ", 375, 265);
	g.drawString("| okt ", 415, 265);
	g.drawString("| nov ", 455, 265);
	g.drawString("| des ", 495, 265);	
    }
}
