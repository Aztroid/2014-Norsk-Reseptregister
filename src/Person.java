/*Hovedprosjekt Dats-1600
GRUPPE 6
William B. Wold, s183670, HIINGDATA13H1AA
Vegar Nygård, s193362, HIINGDATA13H1AA
 */

/*Dette er superklassen person som skal gi sine datafelter/metoder til
Lege og Pasient
*/

import java.io.Serializable;

public class Person implements Serializable{
    
    private String fornavn, etternavn;
    
    public Person(String f, String e){
        fornavn = f;
        etternavn = e;
    }
    
    public String getEtternavn(){
        //Metode som gir personens etternavn
        return etternavn;
    }
    
    public String getFornavn(){
        //Metode som gir personens fornavn
        return fornavn;
    }
    
    public Object[] getTabelllinje(){
    /*"Dato", "Reseptnr.", "Personnr.", "Lege(Autnr.)", 
    "Medisin(ACTnr.)", "Mengde", "DDD", "Kategori", "Reseptgruppe"*/
        Object [] linjen = {
            "", toString(), "", ""};
        return linjen;
    }
    
    public String toString(){
        //Metode som gir f.eks "Hansen, Ola
        return etternavn + ", " + fornavn;
    }
}
