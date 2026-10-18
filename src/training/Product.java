package training;

public class Product {

	private final String productCode;
	private int stock;

	public Product(String productCode, int stock) {
		if (productCode == null || productCode.isBlank() || stock < 0) {
			throw new IllegalArgumentException("Produit invalide");
		}

		this.productCode = productCode;
		this.stock = stock;
	}

	public boolean removeStock(int quantity) {
		
		if (quantity > 0 && stock - quantity >= 0) {
			stock -= quantity;
			return true;
		}
		return false;
	}

	public int getStock() {
		return stock;
	}

	public String getProductCode() {
		return productCode;
	}

}
