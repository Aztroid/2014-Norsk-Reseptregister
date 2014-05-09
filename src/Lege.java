/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
 Vegar Nygård, s193362, HIINGDATA13H1AA*/

//Denne klassen beskriver resepter

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
    /*"Dato", "Reseptnr.", "Personnr.", "Lege(Autnr.)", 
    "Medisin(ACTnr.)", "Mengde", "DDD", "Kategori", "Reseptgruppe"*/
        Object [] linjen = {
            autorisasjonsnr, super.toString(), reseptbevilgning, arbeidssted};
        return linjen;
    }
    
    public String toString(){
        return autorisasjonsnr + ", " + super.toString();
    }
}
