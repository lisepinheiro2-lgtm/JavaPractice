package training;

public class OrderLine {

	private final String productCode;
	private int quantity;
	
	public OrderLine(String productCode, int quantity) {

		this.productCode = productCode;
		this.quantity = quantity;
	}

	public String getProductCode() {
		return productCode;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
