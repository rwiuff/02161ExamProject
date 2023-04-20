package taskfusion.helpers;

import java.util.Calendar;

public class DateHelper {

    public static int twoDigitYearFromDate(Calendar date) {
        return date.get(Calendar.YEAR) % 100;
    }
}
