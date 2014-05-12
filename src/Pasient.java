/*Hovedprosjekt Dats-1600
GRUPPE 6
William B. Wold, s183670, HIINGDATA13H1AA
Vegar Nygård, s193362, HIINGDATA13H1AA
 */

//Sist redigert: 12.05.2014

//Denne klassen skal beskrive pasienter, arver person

public class Pasient extends Person{
    String fødselsnr;
    String lege;
    //LISTE
    
    public Pasient(String f, String e, String n,String l){
        super(f, e);
        fødselsnr = n;
        lege = l;
    }
    
    public String getFødselsnr(){
        //Metode som returnerer fødselsnr
        return fødselsnr;
    }
    
    public String getLege(){
        //Metode som returnerer pasientens lege
        return lege;
    }
    
    public Object[] getTabelllinje(){
    /*Denne metoden returnerer en linje med all pasient informasjon 
        til en JTabel med modellen PasientTabellModell*/
        Object [] linjen = {
            fødselsnr, super.toString(), lege};
        return linjen;
    }
    
    public String toString(){
        //Metoden som returnerer en string med pasientens fnr og navn
        return fødselsnr + ", " + super.toString();
    }
}//End of class Pasient
