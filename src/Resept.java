
import java.text.*;
import java.util.Calendar;
/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA*/

//Denne klassen beskriver resepter

public class Resept {
    /*Merk! DDD = Definert døgndose, dette datafeltet er tatt med for å kunne 
    produisere mer reliabel statistikk*/
    private Calendar dato;
    private Integer reseptnr = 0;
    private String fødselsnr;
    private String autorisasjonsnr;
    private String medisinnr; //ATC-nr (Unikt nummer for norske medisiner)
    private String mengde;
    private String DDD; //Definert Døgndose
    private String kategori;
    private char reseptgruppe;
    private String anvisning;//Dukker opp i eget vindu over tabell??
    
    public Resept(Integer n, String f, String a, String med, String m, String d, 
            String k, char rg, String an){
        reseptnr = n;
        fødselsnr = f;
        autorisasjonsnr = a;
        medisinnr = med;
        mengde = m;
        DDD = d;
        anvisning = an;
        kategori = k;
        reseptgruppe = rg;
        dato = Calendar.getInstance(); //Formatet lagres i klasse som kaller på
    }
    
    public Integer getReseptnr(){
        //Metode som returnerer autorisasjonsnummeret til legen
        return reseptnr;
    }
    
    public String getDato(){
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return df.format(dato.getTime());
    }
    
    public String getLege(){
        //Metode som returnerer autorisasjonsnummeret til legen
        return autorisasjonsnr;
    }
    
    public String getPasient(){
        //Metode som returnerer personnummeret til pasienten
        return fødselsnr;
    }
    
    public String getMedisin(){
        //Metode som returnerer ACT-nr
        return medisinnr;
    }
    
    public String getDDD(){
        //Metode som returnerer Definert Døgndose
        return DDD;
    }
    
    public String getMengde(){
        //Metode som returnerer Definert Døgndose
        return mengde;
    }

    public String getKategori(){
        //Metode som returnerer Definert Døgndose
        return kategori;
    } 
   
    public char getReseptgruppe(){
        //Metode som returnerer reseptgruppen (A,B eller C)
        return reseptgruppe;
    }
    
    public Object[] getTabelllinje(){
    /*"Dato", "Reseptnr.", "Personnr.", "Lege(Autnr.)", 
    "Medisin(ACTnr.)", "Mengde", "DDD", "Kategori", "Reseptgruppe"*/
        Object [] linjen = {
            getDato(), reseptnr, fødselsnr, autorisasjonsnr, medisinnr, mengde, 
            DDD, kategori, reseptgruppe
        };
        return linjen;
    }
    
    public String toString(){
        //Metode som returnerer all informasjon om den aktuelle resepten
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        return "Dato utksrevet: " + df.format(dato.getTime()) + "\nReseptnr: " + reseptnr
                +"\nFødselsnr. Pasient: " + fødselsnr
                + "\nAutorisasjonsnr: " + autorisasjonsnr + "\nATC-nr: " 
                + medisinnr + "\nMengde: " + mengde + "\nDefinert Døgndose: "
                + DDD + "\nKategori: " + kategori + "\nReseptgruppe: "
                + reseptgruppe + "\nAnvisning: " + anvisning;
    }
}
