
import java.util.Calendar;
/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA*/

//Denne klassen beskriver resepter

public class Resept {
    /*Merk! DDD = Definert døgndose, dette datafeltet er tatt med for å kunne 
    produisere mer reliabel statistikk*/
    private Calendar dato;
    private String fødselsnr;
    private String autorisasjonsnr;
    private String medisinnr; //ATC-nr
    private double mengde;
    private double DDD;
    private char kategori;
    private String anvisning;
    
    public Resept(String f, String a, String med, double m, double d, char k, String an){
        fødselsnr = f;
        autorisasjonsnr = a;
        medisinnr = med;
        mengde = m;
        DDD = d;
        anvisning = an;
        kategori = k; 
        dato = Calendar.getInstance();
    }
    
    public String getLege(){
        return autorisasjonsnr;
    }
    
    public String getPasient(){
        return fødselsnr;
    }
    
    public String getMedisin(){
        return medisin;
    }
}
