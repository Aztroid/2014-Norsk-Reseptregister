/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA*/

//Denne klassen skal beskrive pasienter, arver person

public class Pasient extends Person{
    String fødselsnr;
    
    public Pasient(String f, String e, String n){
        super(f, e);
        fødselsnr = n;
    }
    
    public String getFødselsnr(){
        return fødselsnr;
    }
}
