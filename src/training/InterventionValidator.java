package training;

import java.time.LocalTime;

public class InterventionValidator {

	public static boolean isValidName(String name) {

		return (name != null && !name.isBlank() && !name.contains(";") && !name.contains("\\") && !name.contains("\n")
				&& !name.contains("\r") && name.trim().length() <= 40);
	}

	public static boolean hasTimeOverlap(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {

		if (end1 == null || start1 == null || end2 == null || start2 == null || end2.isBefore(start2)
				|| end2.equals(start2) || end1.isBefore(start1) || end1.equals(start1)) {
			return false;
		}

		return !(start1.isAfter(end2) || start1.equals(end2) || end1.isBefore(start2) || end1.equals(start2));
	}
}
