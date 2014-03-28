
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*Hovedprosjekt Dats-1600
 William B. Wold, s183670, HIINGDATA13H1AA
 Tom-Andre Tostrup, s193083, HIINGDATA13H1AA*/

public class Test {
    public static void main(String[]args){
        DateFormat df = new SimpleDateFormat("MM.dd.yyyy HH:mm");
        Calendar dato = Calendar.getInstance();
        System.out.println(df.format(dato.getTime()));
    }
}
