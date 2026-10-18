package training;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class InterventionValidatorTest {

	@Test
	public void testIsValidName() {
		assertAll(() -> assertTrue(InterventionValidator.isValidName("judfgtlqkoprhsldjaopmlnxvzhzlerpoirjhqmk")),
				() -> assertTrue(InterventionValidator.isValidName("Anna")));
	}

	@ParameterizedTest
	@ValueSource(strings = { "Anna;Admin", "   ", "Lucas\nEmma", "Lucas\rEmma", "Lucas\\Emma",
			"judfgtlqkoprhsldjaopmlnxvzhzlerpoirjhqmko" })
	@NullSource
	void testInvalidNames(String name) {
		assertFalse(InterventionValidator.isValidName(name));
	}

	@ParameterizedTest
	@CsvSource({ "09:00, 10:00, 09:30, 10:30, true", "09:00, 10:00, 10:00, 11:00, false",
			"09:00, 10:00, 14:00, 15:00, false", "09:00, 12:00, 10:00, 11:00, true",
			"09:00, 10:00, 08:00, 09:30, true" })
	void testTimeOverlap(String start1, String end1, String start2, String end2, boolean expected) {
		assertEquals(expected, InterventionValidator.hasTimeOverlap(LocalTime.parse(start1), LocalTime.parse(end1),
				LocalTime.parse(start2), LocalTime.parse(end2)));
	}
}
