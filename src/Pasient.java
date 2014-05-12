/*Hovedprosjekt Dats-1600
GRUPPE 6
William B. Wold, s183670, HIINGDATA13H1AA
Vegar Nygård, s193362, HIINGDATA13H1AA
 */

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
        return fødselsnr;
    }
    
    public String getLege(){
        return lege;
    }
    
    public Object[] getTabelllinje(){
    /*"Dato", "Reseptnr.", "Personnr.", "Lege(Autnr.)", 
    "Medisin(ACTnr.)", "Mengde", "DDD", "Kategori", "Reseptgruppe"*/
        Object [] linjen = {
            fødselsnr, super.toString(), lege};
        return linjen;
    }
    
    public String toString(){
        return fødselsnr + ", " + super.toString();
    }
}
