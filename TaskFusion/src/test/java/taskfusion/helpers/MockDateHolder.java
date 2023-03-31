package taskfusion.helpers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.GregorianCalendar;
import java.util.Calendar;

import taskfusion.app.DateServer;
import taskfusion.app.TaskFusion;

public class MockDateHolder {
	DateServer dateServer = mock(DateServer.class);

	public MockDateHolder(TaskFusion taskFusion) {
		GregorianCalendar calendar = new GregorianCalendar();
		setDate(calendar);
		taskFusion.setDateServer(dateServer);
	}

	public void setDate(Calendar calendar) {
		Calendar c = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));
		when(this.dateServer.getDate()).thenReturn(c);
	}

	public void setYear(int year) {
		Calendar currentDate = dateServer.getDate();
		Calendar nextDate = new GregorianCalendar();
		nextDate.setTime(currentDate.getTime());
		nextDate.set(Calendar.YEAR, year);
		setDate(nextDate);
	}

	// public int getYear() {
	// 	System.out.println("getYear in mock!");
	// 	System.out.println("returns: " + dateServer.getDate().get(Calendar.YEAR));
	// 	return dateServer.getDate().get(Calendar.YEAR);
	// }
}
