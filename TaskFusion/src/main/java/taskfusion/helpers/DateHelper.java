package taskfusion.helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateHelper {

    public static int twoDigitYearFromDate(Calendar date) {
        return date.get(Calendar.YEAR) % 100;
    }

    public static boolean compareDates(Calendar date1, Calendar date2) {

        if (date1 == null || date2 == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateString1 = sdf.format(date1.getTime());
        String dateString2 = sdf.format(date2.getTime());

        return dateString1.equals(dateString2);
    }
}
