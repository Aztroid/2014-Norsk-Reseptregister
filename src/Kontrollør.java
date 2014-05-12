/*Hovedprosjekt Dats-1600
GRUPPE 6
William B. Wold, s183670, HIINGDATA13H1AA
Vegar Nygård, s193362, HIINGDATA13H1AA
 */

//Sist redigert: 12.05.2014

//Denne klassen definerer objektet Kontrollør

public class Kontrollør extends Person{
    private String pword;
    private Integer kontrollørnr;
    private String arbeidssted;
    
    public Kontrollør(String f, String e, String p, Integer k, String ar){
        super(f,e);
        pword = p;
        kontrollørnr = k;
        arbeidssted = ar;
    }
    
    public String getArbeidssted(){
        return arbeidssted;
    }

    public Integer getKontnøkkel() {
        return kontrollørnr;
    }
    
    public String getPword() {
        return pword;
    }
    
    public String toString(){
        //Viser all data registrert om kontrolløren
        return super.toString() + "Arbeidssted: " + arbeidssted
                + "\nKontrollørnr: " + kontrollørnr;
    }
}//End of class Kontrollør
