package training;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.format.SignStyle;

public final class FormatUtils {

	private static final DateTimeFormatter HOUR_FORMATTER = new DateTimeFormatterBuilder()
			.appendValue(ChronoField.HOUR_OF_DAY, 1, 2, SignStyle.NEVER).optionalStart().appendLiteral(':')
			.appendValue(ChronoField.MINUTE_OF_HOUR, 2).optionalEnd().parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
			.toFormatter().withResolverStyle(ResolverStyle.STRICT);

	private FormatUtils() {
	}

	public static LocalTime parseHour(String text) {
		String normalized = text.trim().toLowerCase().replace('h', ':');

		if (normalized.endsWith(":")) {
			normalized += "00";
		}

		return LocalTime.parse(normalized, HOUR_FORMATTER);
	}
}
