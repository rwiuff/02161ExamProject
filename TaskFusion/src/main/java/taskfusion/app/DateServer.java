package taskfusion.app;

import java.util.Calendar;
import java.util.GregorianCalendar;

// Replaced by mock in testing
public class DateServer {
	public Calendar getDate() {
		Calendar calendar = new GregorianCalendar();
		Calendar c = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));
		return c;
	}

	public int getYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
}
