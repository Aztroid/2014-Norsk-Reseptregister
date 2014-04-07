/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA*/

//Denne klassen beskriver resepter

public class Lege extends Person{
    String autorisasjonsnr;
    String arbeidssted;//ADR om mulig
    String reseptbevilgning;
    //LISTE
    
    public Lege(String f, String e, String a, String ar, String b){
        super(f,e);
        autorisasjonsnr = a;
        arbeidssted = ar;
        reseptbevilgning = b;
    }
    
    public String getArbeidssted(){
        return arbeidssted;
    }
    
    public String getBevilgning(){
        return reseptbevilgning;
    }
    
    public void setBevilgning(String s){
        reseptbevilgning = s;
        return;
    }
    
    public String toString(){
        return super.toString() + ", Arbeidssted: " + arbeidssted
                + ", Reseptbevilkning: " + reseptbevilgning;
    }
}
