/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA*/

/*Dette er superklassen person som skal gi sine datafelter/metoder til
Lege og Pasient
*/

public class Person{
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
    
    public String toString(){
        //Metode som gir f.eks "Hansen, Ola
        return "Navn: " + etternavn + ", " + fornavn + "\n";
    }
}
