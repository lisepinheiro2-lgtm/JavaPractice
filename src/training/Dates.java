package training;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Dates {

	public int dateInt(String dateText) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");

		try {

			LocalDate date = LocalDate.parse(dateText, formatter);
			int dayInt = date.getDayOfMonth();

			return dayInt;

		} catch (DateTimeParseException e){
			return -1;
		}
	}
}
