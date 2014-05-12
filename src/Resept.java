/*Hovedprosjekt Dats-1600
GRUPPE 6
William B. Wold, s183670, HIINGDATA13H1AA
Vegar Nygård, s193362, HIINGDATA13H1AA
 */

//Sist redigert: 12.05.2014

//Denne klassen beskriver resepter

import java.text.*;
import java.util.Calendar;
import java.io.Serializable;

public class Resept implements Serializable{
    /*Merk! DDD = Definert døgndose, dette datafeltet er tatt med for å kunne 
    geneere bedre statistikk om man skulle lagt til flere grafer i 
    statistikkpanelet*/
    private Calendar dato;
    private Integer reseptnr = 0;
    private String fødselsnr;
    private String autorisasjonsnr;
    private String medisinnr; //ATC-nr
    private String mengde;
    private String DDD; //Definert Døgndose
    private String kategori;
    private char reseptgruppe;
    private String anvisning;
    
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
        return reseptnr;
    }
    
    public String getDato(){
        //Returnere en string som repressenterer tiden resepten ble skrevet ut
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        return df.format(dato.getTime());
    }
    
    public Calendar getKalenderformat(){
        //Returnere selve Calendar objectet
        return dato;
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
    /*Denne metoden returnerer en linje med all reseptinformasjon 
        til en JTabel med modellen ReseptTabellModell*/
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
}//End of class Resept
