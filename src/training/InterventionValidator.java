package training;

public class InterventionValidator {

	public static boolean isValidName(String name) {

		return (name != null && !name.isBlank() && !name.contains(";") && !name.contains("\\") && !name.contains("\n")
				&& !name.contains("\r") && name.trim().length() <= 40);
	}
}
