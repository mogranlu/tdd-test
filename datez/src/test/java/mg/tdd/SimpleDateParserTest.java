package mg.tdd;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.DECEMBER;
import static java.util.Calendar.JANUARY;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import static org.fest.assertions.Assertions.assertThat;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

public class SimpleDateParserTest {

	private SimpleDateParser simpleDateParser;

	@Before
	public void setup() {
		simpleDateParser = new SimpleDateParser();
	}

	@Test
	public void shouldReturnValidDateFor6DigitString() {
		Calendar date = simpleDateParser.parseDate("111213");
		Calendar expectedDate = new GregorianCalendar(2013, DECEMBER, 11);
		assertThat(date).isNotNull().isInstanceOf(Calendar.class);
		assertCalendarEquality(date, expectedDate);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowNullPointerExceptionIfParameterIsANullReference() {
		simpleDateParser.parseDate(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionForIllegalDates() {
		simpleDateParser.parseDate("331500");
	}

	@Test
	public void shouldTreatValue_999999_AsASpecialDate() {
		Calendar almostMaximumIntoTheFuture = Calendar.getInstance();
		almostMaximumIntoTheFuture.set(2099, DECEMBER, 30);

		Calendar futureDate = simpleDateParser.parseDate("999999");
		assertThat(futureDate.after(almostMaximumIntoTheFuture));
	}

	@Test
	public void shouldAutomaticallyTrimAwayLeadingAndTrailingWhiteSpaces() {
		Calendar date = simpleDateParser.parseDate(" 010203  ");
		Calendar expectedDate = Calendar.getInstance();
		expectedDate.set(2003, Calendar.FEBRUARY, 1);

		assertThat(date).isNotNull();
		assertCalendarEquality(date, expectedDate);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionOnEmptyString() {
		simpleDateParser.parseDate("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionOnStringThatIsEmptyOnlyAfterTrim() {
		simpleDateParser.parseDate("   ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionOnStringThatContainsAnythingElseThanDigits() {
		simpleDateParser.parseDate("12E45678");
	}

	@Test
	public void shouldTreatValue_000000_AsASpecialDate() {
		Calendar parsedDate = simpleDateParser.parseDate("000000");
		Calendar expectedDate = new GregorianCalendar(1970, JANUARY, 1);
		assertCalendarEquality(expectedDate, parsedDate);
	}

	private void assertCalendarEquality(Calendar date1, Calendar date2) {
		assertThat(date1.get(YEAR)).isEqualTo(date2.get(YEAR));
		assertThat(date1.get(MONTH)).isEqualTo(date2.get(MONTH));
		assertThat(date1.get(DAY_OF_MONTH)).isEqualTo(date2.get(DAY_OF_MONTH));
	}
}
