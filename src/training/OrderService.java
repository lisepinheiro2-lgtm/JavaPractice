package training;

import java.util.ArrayList;

public class OrderService {

	private final ArrayList<Product> products;

	public OrderService(ArrayList<Product> products) {
		this.products = products;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public boolean canFulfillOrder(ArrayList<OrderLine> lines) {

		if (!OrderValidator.isValidOrder(lines)) {
			return false;
		}

		for (OrderLine line : lines) {

			boolean found = false;
			for (Product product : products) {

				if (product.getProductCode().trim().equalsIgnoreCase(line.getProductCode().trim())) {
					if (line.getQuantity() > product.getStock()) {
						return false;
					} else {
						found = true;
						break;
					}
				}
			}

			if (!found) {
				return false;
			}
		}

		return true;
	}

	private boolean isValidCatalog(ArrayList<Product> products) {

		if (products == null) {
			return false;
		}

		for (int i = 0; i < products.size(); i++) {

			if (products.get(i) == null) {
				return false;
			}

			int j = 0;

			while (j < i) {

				if (products.get(i).getProductCode().trim().equalsIgnoreCase(products.get(j).getProductCode().trim())) {
					return false;
				}

				j++;
			}
		}

		return true;
	}
}
