
package takip_sistemi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpeedCalculate {
         
     public long getTimeDifference(String st1, String st2) { // iki tarih arasındaki zaman farkını bulur.
        
          long ret=0;
        String OLD_FORMAT = "yyyyMMddHHmmss";
        DateFormat formatter = new SimpleDateFormat(OLD_FORMAT);
        try {
            Date parsedDate1 = formatter.parse(st1);
            Date parsedDate2 = formatter.parse(st2);
            ret=Math.abs(parsedDate1.getTime()-parsedDate2.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(SpeedCalculate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret/1000;          
    }
}
