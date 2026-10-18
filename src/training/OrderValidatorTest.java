package training;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderValidatorTest {

	private ArrayList<OrderLine> lines;

	@BeforeEach
	void setUp() {

		lines = new ArrayList<>();

		lines.add(new OrderLine("Clavier", 2));
		lines.add(new OrderLine("SOURIS", 5));
		lines.add(new OrderLine("Ecran", 3));
	}

	@Test
	void testIsValidOrder() {

		assertTrue(OrderValidator.isValidOrder(lines));

	}

	@Test
	void testDuplicateProduct() {

		lines.add(new OrderLine(" souris ", 2));

		assertFalse(OrderValidator.isValidOrder(lines));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, -1, -10 })
	void testInvalidQuantity(int quantity) {

		lines.get(1).setQuantity(quantity);

		assertFalse(OrderValidator.isValidOrder(lines));
	}

	@Test
	void testNullOrder() {
		assertFalse(OrderValidator.isValidOrder(null));
	}

	@Test
	void testNullLine() {
		lines.add(null);
		assertFalse(OrderValidator.isValidOrder(lines));
	}
}
