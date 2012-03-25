package mg.tdd;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SimpleDateParser {

	public Calendar parseDate(String dateAsString) {
		int dayOfMonth = Integer.parseInt(dateAsString.substring(0, 2));
		int month = Integer.parseInt(dateAsString.substring(2, 4));
		int year = Integer.parseInt("20" + dateAsString.substring(4, 6));

		GregorianCalendar newDate = new GregorianCalendar();
		newDate.setLenient(false);
		newDate.set(year, month - 1, dayOfMonth);

		/*
		 * Good example! Must run an extra getTime to force the "lenient"
		 * exception on illegal dates!
		 */
		newDate.getTime();

		return newDate;
	}

}
