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

	public int getYear() {
		this.dateServer.getDate();
		return Calendar.getInstance().get(Calendar.YEAR);
	}
}
