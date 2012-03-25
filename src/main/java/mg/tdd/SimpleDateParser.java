package mg.tdd;

import static java.util.Calendar.DECEMBER;
import static java.util.Calendar.JANUARY;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SimpleDateParser {

	public static final String MAXIMUM_DATE_ALIAS = "999999";
	public static final String MINIMUM_DATE_ALIAS = "000000";

	/**
	 * Every two-digit year is prefixed with 20, limiting the possible dates to
	 * be in the range Jan 1st 2000 to Dec 31rd 2099.
	 */
	protected static final String FIXED_CENTURY = "20";

	public static final Calendar MAXIMUM_DATE;
	public static final Calendar MINIMUM_DATE;

	static {
		MINIMUM_DATE = Calendar.getInstance();
		MINIMUM_DATE.set(1970, JANUARY, 1);

		MAXIMUM_DATE = Calendar.getInstance();
		MAXIMUM_DATE.set(2099, DECEMBER, 31);
	}

	/**
	 * Parses the {@link String} object passed in as a date on the
	 * <code>DDMMYY</code> format.
	 * 
	 * @param dateAsString
	 * @return
	 */
	public Calendar parseDate(String dateAsString)
			throws IllegalArgumentException {

		validateInputParameterAndThrowException(dateAsString);
		dateAsString = dateAsString.trim();

		if (dateAsString.equals(MAXIMUM_DATE_ALIAS)) {
			return MAXIMUM_DATE;
		} else if (dateAsString.equals(MINIMUM_DATE_ALIAS)) {
			return MINIMUM_DATE;
		}

		GregorianCalendar newDate = new GregorianCalendar();
		newDate.set(parseYear(dateAsString), parseMonth(dateAsString),
				parseDay(dateAsString));

		validate(newDate);

		return newDate;
	}

	private void validateInputParameterAndThrowException(String dateAsString) {
		if (dateAsString == null) {
			throw new IllegalArgumentException(
					"Cannot parse a null reference as a date! Expected a 6-digit String object describing a date on the following format: \"DDMMYY\".");
		}
		dateAsString = dateAsString.trim();
		if (dateAsString.length() != 6) {
			StringBuilder errorMsg = new StringBuilder(
					"dateAsString parameter must be of length 6, but was: ");
			errorMsg.append(dateAsString.length());

			throw new IllegalArgumentException(errorMsg.toString());
		}

		return;
	}

	private void validate(GregorianCalendar newDate)
			throws IllegalArgumentException {
		/*
		 * Good example! Must run an extra getTime to force the "lenient"
		 * exception on illegal dates!
		 */
		newDate.setLenient(false);
		newDate.getTime();
	}

	private int parseDay(String sixDigitString) {
		return Integer.parseInt(sixDigitString.substring(0, 2));
	}

	private int parseMonth(String sixDigitString) {
		return Integer.parseInt(sixDigitString.substring(2, 4)) - 1;
	}

	private int parseYear(String sixDigitString) {
		return Integer.parseInt(FIXED_CENTURY + sixDigitString.substring(4, 6));
	}
}
