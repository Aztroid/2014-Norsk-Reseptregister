/*Hovedprosjekt Dats-1600
GRUPPE 6
William B. Wold, s183670, HIINGDATA13H1AA
Vegar Nygård, s193362, HIINGDATA13H1AA
 */

//Denne klassen definerer objektet Lege

public class Lege extends Person{
    private String pword;
    private String autorisasjonsnr;
    private String arbeidssted;//ADR om mulig
    private String reseptbevilgning;
    
    public Lege(String f, String e, String p, String a, String ar, String r){
        super(f,e);
        pword = p;
        autorisasjonsnr = a;
        arbeidssted = ar;
        reseptbevilgning = r;
    }

    public String getArbeidssted(){
        return arbeidssted;
    }

    public String getAutorisasjonsnr() {
        return autorisasjonsnr;
    }
    
    public String getPword() {
        return pword;
    }
    
    public String getBevilgning(){
        
        return reseptbevilgning;
    }
    
    public void setBevilgning(String s){
        reseptbevilgning = s;
        return;
    }
    
    public Object[] getTabelllinje(){
    /*Denne metoden returnerer en linje med all lege informasjon 
        til en JTabel med modellen LegeTabellModell*/
        Object [] linjen = {
            autorisasjonsnr, super.toString(), reseptbevilgning, arbeidssted};
        return linjen;
    }
    
    public String toString(){
        return autorisasjonsnr + ", " + super.toString();
    }
}//End of class Lege
