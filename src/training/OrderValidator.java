package training;

import java.util.ArrayList;

public class OrderValidator {

	public static boolean isValidOrder(ArrayList<OrderLine> lines) {

		if (lines == null || lines.isEmpty()) {
			return false;
		}

		for (int i = 0; i < lines.size(); i++) {

			if (lines.get(i) == null) {
				return false;
			}

			String code = lines.get(i).getProductCode();
			int quantity = lines.get(i).getQuantity();

			if (code == null || code.isBlank() || quantity <= 0) {
				return false;
			}

			for (int j = 0; j < i; j++) {

				String code2 = lines.get(j).getProductCode();

				if (code.trim().equalsIgnoreCase(code2.trim())) {
					return false;
				}
			}
		}

		return true;
	}
}
