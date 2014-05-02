/*Hovedprosjekt Dats-1600
William B. Wold, s183670, HIINGDATA13H1AA
Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
Vegar Nyg�rd, s193362, HIINGDATA13H1AA
 */

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
        return super.toString() + "Arbeidssted: " + arbeidssted
                + "\nKontrollørnr: " + kontrollørnr;
    }
}
