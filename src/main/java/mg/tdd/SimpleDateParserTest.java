package mg.tdd;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.DECEMBER;
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
	public void shouldReturnValidDateFor6DigitString() throws Exception {
		Calendar date = simpleDateParser.parseDate("111213");
		Calendar expectedDate = new GregorianCalendar(2013, DECEMBER, 11);
		assertThat(date).isNotNull().isInstanceOf(Calendar.class);
		assertThat(date.get(MONTH)).isEqualTo(expectedDate.get(MONTH));
		assertThat(date.get(YEAR)).isEqualTo(expectedDate.get(YEAR));
		assertThat(date.get(DAY_OF_MONTH)).isEqualTo(
				expectedDate.get(DAY_OF_MONTH));
	}

	@Test(expected = NullPointerException.class)
	public void shouldThrowNullPointerExceptionIfParameterIsANullReference()
			throws Exception {
		simpleDateParser.parseDate(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionForIllegalDates() throws Exception {
		simpleDateParser.parseDate("331500");
	}

}
