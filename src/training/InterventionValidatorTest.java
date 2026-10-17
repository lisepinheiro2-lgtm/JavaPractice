package training;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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
}
