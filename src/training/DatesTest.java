package training;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DatesTest {

	@ParameterizedTest
	@ValueSource(strings = { "17/10/2026", "18/10/2026" })
	void testWeekend(String date) {
		assertTrue(Dates.isWeekend(date));
	}

	@ParameterizedTest
	@ValueSource(strings = { "19/10/2026", "20/10/2026", "21/10/2026" })
	void testNotWeekend(String date) {
		assertFalse(Dates.isWeekend(date));
	}
}
