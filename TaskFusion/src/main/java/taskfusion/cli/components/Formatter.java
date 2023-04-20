package taskfusion.cli.components;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Formatter {

    public static String dateToString(Calendar date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM-yyyy");
        return dateFormat.format(date.getTime());

    }
}
