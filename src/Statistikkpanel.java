/*Hovedprosjekt Dats-1600
GRUPPE 6
William B. Wold, s183670, HIINGDATA13H1AA
Vegar Nygård, s193362, HIINGDATA13H1AA
 */

//Sist redigert: 12.05.2014

/*Dette panelet tegener graframmen, og legger til grafer i det*/

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import static java.awt.PageAttributes.ColorType.COLOR;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

public class Statistikkpanel extends JPanel{
    
    //Koordinatdata
    private int[] førstelinje = new int[12];
    private int[] andrelinje = new int[12];
    
    public void genererPunkter(){
        /*Gir statistikk grafen korrekte verdier for månedene sine resept 
        utskrifter. De multipliseres slik at de passer den opptegnede tabellen*/
        int multipliserer = 4;
        for(int i = 0; i<førstelinje.length; i++){
                førstelinje[i] = førstelinje[i]*multipliserer;
        }
        if(andrelinje!=null){
            for(int i = 0; i<andrelinje.length; i++){
                  andrelinje[i] = andrelinje[i]*multipliserer;
            }
        }   
    }
    
    public void setNyeKordinaterFørste(int[] linjen){
        //Setter nye kordinater for førte bargraf til panelet
        førstelinje = linjen;
    }
    
    public void setNyeKordinaterAndre(int[] linjen){
        //Setter nye kordinater for andre bargraf til panelet
        andrelinje = linjen;
    }
    
    public void paintComponent(Graphics g){
        //Oppretter en en grafisk component
        super.paintComponent(g);
        Graphics2D graph2 = (Graphics2D)g; //Oppretter ett 2d grafisk objekt for tegning av grafen
        graph2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        
       //Setter bakgrunnsfargen til hvit
        this.setBackground(Color.WHITE);
        //oppretter x og y linjene       
        Shape drawLine = new Line2D.Float(55, 25, 55 , 455);
	Shape drawLine2 = new Line2D.Float(55, 455, 1000, 455);
        
        graph2.setPaint(Color.BLACK);
	
        //tegner x og y linjen
        graph2.draw(drawLine);
        graph2.draw(drawLine2);      
        
        //Her opprettes rektangel objekter og med koordinater for fremstilling av korrekte verdier for den første bar-grafen
        graph2.fillRect( 55, 455 - førstelinje[0], 40, førstelinje[0]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(55, 455 - førstelinje[0], 40, førstelinje[0], true);
	graph2.setPaint(Color.RED);
	

	graph2.fillRect( 135, 455 - førstelinje[1], 40, førstelinje[1]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(135, 455 - førstelinje[1], 40, førstelinje[1], true);
	graph2.setPaint(Color.RED);
        
       
        graph2.fillRect( 215, 455 - førstelinje[2], 40, førstelinje[2]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(215, 455 - førstelinje[2], 40, førstelinje[2], true);
	graph2.setPaint(Color.RED);
        
        graph2.fillRect(295, 455 - førstelinje[3], 40, førstelinje[3]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(295, 455 - førstelinje[3], 40, førstelinje[3], true);
	graph2.setPaint(Color.RED);
        
        graph2.fillRect( 375, 455 - førstelinje[4], 40, førstelinje[4]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(375, 455 - førstelinje[4], 40, førstelinje[4], true);
	graph2.setPaint(Color.RED);

        graph2.fillRect( 455, 455 - førstelinje[5], 40, førstelinje[5]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(455, 455 - førstelinje[5], 40, førstelinje[5], true);
	graph2.setPaint(Color.RED);
        
        graph2.fillRect( 535, 455 - førstelinje[6], 40, førstelinje[6]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(535, 455 - førstelinje[6], 40, førstelinje[6], true);
	graph2.setPaint(Color.RED);

        graph2.fillRect( 605, 455 - førstelinje[7], 40, førstelinje[7]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(605, 455 - førstelinje[7], 40, førstelinje[7], true);
	graph2.setPaint(Color.RED);

        graph2.fillRect( 685, 455 - førstelinje[8], 40, førstelinje[8]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(685, 455 - førstelinje[8], 40, førstelinje[8], true);
	graph2.setPaint(Color.RED);
        
        graph2.fillRect( 765, 455 - førstelinje[9], 40, førstelinje[9]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(765, 455 - førstelinje[9], 40, førstelinje[9], true);
	graph2.setPaint(Color.RED);

        graph2.fillRect( 845, 455 - førstelinje[10], 40, førstelinje[10]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(845, 455 - førstelinje[10], 40, førstelinje[10], true);
	graph2.setPaint(Color.RED);


        graph2.fillRect( 935, 455 - førstelinje[11], 40, førstelinje[11]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(935, 455 - førstelinje[11], 40, førstelinje[11], true);
	graph2.setPaint(Color.RED);

        
        //Her opprettes rektangel objekter og med koordinater for fremstilling av korrekte verdier for den andre bar-grafen
        if(andrelinje!=null){
        //if test for å sørge for at det er verdier i andrelinje arrayen
        graph2.fillRect( 95, 455 - andrelinje[0], 40, andrelinje[0]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(95, 455 - andrelinje[0], 40, andrelinje[0], true);
	graph2.setPaint(Color.BLUE);
	

	graph2.fillRect( 175, 455 - andrelinje[1], 40, andrelinje[1]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(175, 455 - andrelinje[1], 40, andrelinje[1], true);
	graph2.setPaint(Color.BLUE);
        
       
        graph2.fillRect( 255, 455 - andrelinje[2], 40, andrelinje[2]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(255, 455 - andrelinje[2], 40, andrelinje[2], true);
	graph2.setPaint(Color.BLUE);
        
        graph2.fillRect(335, 455 - andrelinje[3], 40, andrelinje[3]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(335, 455 - andrelinje[3], 40, andrelinje[3], true);
	graph2.setPaint(Color.BLUE);
        
        graph2.fillRect( 405, 455 - andrelinje[4], 40, andrelinje[4]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(405, 455 - andrelinje[4], 40, andrelinje[4], true);
	graph2.setPaint(Color.BLUE);

        graph2.fillRect( 495, 455 - andrelinje[5], 40, andrelinje[5]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(495, 455 - andrelinje[5], 40, andrelinje[5], true);
	graph2.setPaint(Color.BLUE);
        
        graph2.fillRect( 575, 455 - andrelinje[6], 40, andrelinje[6]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(575, 455 - andrelinje[6], 40, andrelinje[6], true);
	graph2.setPaint(Color.BLUE);

        graph2.fillRect( 645, 455 - andrelinje[7], 40, andrelinje[7]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(645, 455 - andrelinje[7], 40, andrelinje[7], true);
	graph2.setPaint(Color.BLUE);

        graph2.fillRect( 725, 455 - andrelinje[8], 40, andrelinje[8]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(725, 455 - andrelinje[8], 40, andrelinje[8], true);
	graph2.setPaint(Color.BLUE);
        
        graph2.fillRect( 805, 455 - andrelinje[9], 40, andrelinje[9]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(805, 455 - andrelinje[9], 40, andrelinje[9], true);
	graph2.setPaint(Color.BLUE);

        graph2.fillRect( 885, 455 - andrelinje[10], 40, andrelinje[10]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(885, 455 - andrelinje[10], 40, andrelinje[10], true);
	graph2.setPaint(Color.BLUE);


        graph2.fillRect( 965, 455 - andrelinje[11], 40, andrelinje[11]);
	graph2.setPaint(Color.BLACK);
	graph2.draw3DRect(965, 455 - andrelinje[11], 40, andrelinje[11], true);
	graph2.setPaint(Color.BLUE);
        }
	//Her settes fonten og det numeriske og mnds verdiene langs x og y linjen
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
}//End of class Statistikkpanel

