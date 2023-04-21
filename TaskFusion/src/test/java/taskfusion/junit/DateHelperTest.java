package taskfusion.junit;

import java.util.Calendar;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskfusion.app.DateServer;
import taskfusion.helpers.DateHelper;
import taskfusion.helpers.SingletonHelpers;

public class DateHelperTest {
    @BeforeEach
    public void resetSingletons() {
        SingletonHelpers.resetSingletons();
    }

    @Test
    public void testCompareDates() {
        Calendar date1 = null;
        Calendar date2 = null;
        assertFalse(DateHelper.compareDates(date1, date2));

        date1 = new DateServer().getDate();
        assertFalse(DateHelper.compareDates(date1, date2));

        date2 = new DateServer().getDate();
        assertTrue(DateHelper.compareDates(date1, date2));

    }

    @Test
    public void testConstructDateHelper() {
        DateHelper dateHelper = new DateHelper();
        assertTrue(dateHelper != null);
    }

}
