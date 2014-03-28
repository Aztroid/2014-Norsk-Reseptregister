
import java.util.Calendar;
/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA*/

//Denne klassen beskriver resepter

public class Resept {
    /*Merk! DDD = Definert døgndose, dette datafeltet er tatt med for å kunne 
    produisere mer reliabel statistikk*/
    private Calendar dato;
    private Pasient pasient;
    private Lege lege;
    private String medisin;
    private double mengde;
    private double DDD;
    private String anvisning;
    
    public Resept(int f, int a, String med, double m, double d, String an){
        fødselsnr = f;
        autorisasjonsnr = a;
        medisin = med;
        mengde = m;
        DDD = d;
        anvisning = an;
        dato = Calendar.getInstance();
    }
}
