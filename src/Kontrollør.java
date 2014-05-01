/*Hovedprosjekt Dats-1600
William B. Wold, s183670, HIINGDATA13H1AA
Tom-Andre Tostrup, s193083, HIINGDATA13H1AA
Vegar Nyg�rd, s193362, HIINGDATA13H1AA
 */

public class Kontrollør extends Person{
    String[] logginn;
    Integer kontrollørnr;
    String arbeidssted;
    
    public Kontrollør(String f, String e,String b, String p, Integer k, String ar){
        super(f,e);
        logginn = new String[2];
        logginn[0] = b;
        logginn[1] = p;
        kontrollørnr = k;
        arbeidssted = ar;
    }
    
    public String getArbeidssted(){
        return arbeidssted;
    }

    public Integer getKontnøkkel() {
        return kontrollørnr;
    }
    
    public String toString(){
        return super.toString() + "Arbeidssted: " + arbeidssted
                + "\nKontrollørnr: " + kontrollørnr;
    }
}
