package training;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ProductTest {

	@Test
	void testInvalidProduct() {
		assertAll(() -> assertThrows(IllegalArgumentException.class, () -> new Product("CLAVIER", -5)),
				() -> assertThrows(IllegalArgumentException.class, () -> new Product("   ", 3)),
				() -> assertThrows(IllegalArgumentException.class, () -> new Product(null, 5)));
	}

	@Test
	void testZeroStock() {
		Product product = new Product("Clavier", 0);
		int result = product.getStock();
		assertEquals(0, result);
	}

}
