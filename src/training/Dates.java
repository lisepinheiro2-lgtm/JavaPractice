package training;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.TreeSet;

public class Dates {

	public int dateInt(String dateText) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");

		try {

			LocalDate date = LocalDate.parse(dateText, formatter);
			int dayInt = date.getDayOfMonth();

			return dayInt;

		} catch (DateTimeParseException e) {
			return -1;
		}
	}

	public boolean dateIsAfter(String dateText) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		String limitDateText = "18/11/2026";
		LocalDate date = LocalDate.parse(dateText, formatter);
		LocalDate limitDate = LocalDate.parse(limitDateText, formatter);

		return date.isAfter(limitDate);
	}

	public String latestDate(String date1, String date2) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		LocalDate firstDate = LocalDate.parse(date1, formatter);
		LocalDate secondDate = LocalDate.parse(date2, formatter);

		if (secondDate.isAfter(firstDate)) {
			return date2;
		} else if (firstDate.isAfter(secondDate)) {
			return date1;
		}

		return date1;
	}

	public boolean isWithinPeriod(String dateText, String startText, String endText) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

		LocalDate date = LocalDate.parse(dateText, formatter);
		LocalDate startDate = LocalDate.parse(startText, formatter);
		LocalDate endDate = LocalDate.parse(endText, formatter);

		return date.isAfter(startDate) && date.isBefore(endDate) || date.equals(startDate) || date.equals(endDate);
	}

	public static int countUpcomingAppointments(ArrayList<String> appointments, String referenceDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		int count = 0;

		for (String appointment : appointments) {

			try {
				LocalDate refDate = LocalDate.parse(referenceDate, formatter);
				LocalDate date = LocalDate.parse(appointment, formatter);

				if (date.equals(refDate) || date.isAfter(refDate)) {
					count++;
				}

			} catch (DateTimeParseException e) {

			}
		}
		return count;
	}

	public static long calculateDuration(String startTime, String endTime) {

		LocalTime startHour = LocalTime.parse(startTime);
		LocalTime endHour = LocalTime.parse(endTime);

		long betweenDuration = Duration.between(startHour, endHour).toMinutes();

		return betweenDuration;
	}

	public static int countWorkingDays(String startText, String endText) {

		int count = 0;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

		LocalDate endDate = LocalDate.parse(endText, formatter);
		LocalDate date = LocalDate.parse(startText, formatter);

		while (date.isBefore(endDate) || date.equals(endDate)) {

			DayOfWeek testedDay = date.getDayOfWeek();

			if (testedDay != DayOfWeek.SATURDAY && testedDay != DayOfWeek.SUNDAY) {
				count++;
			}

			date = date.plusDays(1);
		}
		return count;
	}

	public static long calculateBreak(String endText, String startText) {

		LocalTime endHour = LocalTime.parse(endText);
		LocalTime startHour = LocalTime.parse(startText);

		long betweenDuration = Duration.between(endHour, startHour).toMinutes();

		return betweenDuration;
	}

	public static String nextWorkingDay(String dateText) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

		LocalDate date = LocalDate.parse(dateText, formatter);
		LocalDate nextDay = date.plusDays(1);
		DayOfWeek nextDayOfWeek = nextDay.getDayOfWeek();

		while (nextDayOfWeek == DayOfWeek.SATURDAY || nextDayOfWeek == DayOfWeek.SUNDAY) {
			nextDay = nextDay.plusDays(1);
			nextDayOfWeek = nextDay.getDayOfWeek();
		}

		return nextDay.format(formatter);
	}

	public static boolean isWeekend(String dateText) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		LocalDate date = LocalDate.parse(dateText, formatter);
		DayOfWeek dateDay = date.getDayOfWeek();

		return (dateDay == DayOfWeek.SATURDAY || dateDay == DayOfWeek.SUNDAY);
	}

	public static boolean hasEnoughBreak(String endText, String startText, int minimumMinutes) {

		LocalTime endHour = LocalTime.parse(endText);
		LocalTime startHour = LocalTime.parse(startText);

		return Duration.between(endHour, startHour).toMinutes() >= minimumMinutes;
	}

	public static String nextMonday(String dateText) {

		LocalDate date = LocalDate.parse(dateText);
		LocalDate nextDay = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

		return nextDay.toString();
	}

	public static String startOfWeek(String dateText) {

		LocalDate date = LocalDate.parse(dateText);
		LocalDate previousDay = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

		return previousDay.toString();
	}

	public static String nextMondayMeeting(String dateText, String timeText) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		LocalDate date = LocalDate.parse(dateText, formatter);
		LocalTime hour = LocalTime.parse(timeText);
		DayOfWeek today = date.getDayOfWeek();

		LocalTime validHour = LocalTime.parse("09:00");
		LocalDate nextDay = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

		if (today == DayOfWeek.MONDAY && hour.isBefore(validHour)) {
			return date.format(formatter);

		} else {
			return nextDay.format(formatter);
		}
	}

	public static long totalInterventionMinutes(ArrayList<String> starts, ArrayList<String> ends) {

		long totalDuration = 0;

		for (int i = 0; i < starts.size(); i++) {

			LocalTime startHour = LocalTime.parse(starts.get(i));
			LocalTime endHour = LocalTime.parse(ends.get(i));

			long betweenDuration = Duration.between(startHour, endHour).toMinutes();

			totalDuration += betweenDuration;

		}

		return totalDuration;
	}

	public static ArrayList<String> organizeDates(ArrayList<String> dateTexts) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		TreeSet<LocalDate> datesList = new TreeSet<>();

		for (String dateText : dateTexts) {

			LocalDate date = LocalDate.parse(dateText, formatter);

			datesList.add(date);
		}

		ArrayList<String> organizeDates = new ArrayList<String>();

		for (LocalDate date : datesList) {

			String dateTxt = date.format(formatter);

			organizeDates.add(dateTxt);
		}

		return organizeDates;
	}

	public static String findNextIntervention(ArrayList<String> dateTexts, String referenceText) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		TreeSet<LocalDate> datesList = new TreeSet<>();

		for (String dateText : dateTexts) {

			LocalDate date = LocalDate.parse(dateText, formatter);

			datesList.add(date);
		}

		LocalDate referenceDate = LocalDate.parse(referenceText, formatter);
		LocalDate nextDate = datesList.higher(referenceDate);

		if (nextDate == null) {
			return null;
		}

		return nextDate.format(formatter);
	}

	public static ArrayList<String> findNeighborDates(ArrayList<String> dateTexts, String referenceText) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		TreeSet<LocalDate> datesList = new TreeSet<>();

		for (String dateText : dateTexts) {

			LocalDate date = LocalDate.parse(dateText, formatter);

			datesList.add(date);
		}

		LocalDate referenceDate = LocalDate.parse(referenceText, formatter);
		LocalDate previousDate = datesList.lower(referenceDate);
		LocalDate nextDate = datesList.higher(referenceDate);

		ArrayList<String> findNeighborDates = new ArrayList<String>();

		if (previousDate == null) {
			findNeighborDates.add(null);
		} else {
			findNeighborDates.add(previousDate.format(formatter));
		}

		if (nextDate == null) {
			findNeighborDates.add(null);
		} else {
			findNeighborDates.add(nextDate.format(formatter));
		}

		return findNeighborDates;
	}

	public static boolean isOldIntervention(String interventionText, String referenceText, int limitDays) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		LocalDate interventionDate = LocalDate.parse(interventionText, formatter);
		LocalDate referenceDate = LocalDate.parse(referenceText, formatter);

		long days = ChronoUnit.DAYS.between(interventionDate, referenceDate);

		return days >= limitDays && days >= 0;
	}

	public static LocalDate findNextAppointment(ArrayList<LocalDate> appointments, LocalDate today) {

		TreeSet<LocalDate> appointementsOrganized = new TreeSet<LocalDate>();
		appointementsOrganized.addAll(appointments);

		return appointementsOrganized.higher(today);
	}

	public static ArrayList<LocalDate> findNextTwoAppointments(ArrayList<LocalDate> appointments, LocalDate today) {

		TreeSet<LocalDate> appointementsOrganized = new TreeSet<LocalDate>();
		ArrayList<LocalDate> nextTwoAppointements = new ArrayList<LocalDate>();

		appointementsOrganized.addAll(appointments);

		LocalDate nextDate1 = appointementsOrganized.higher(today);

		if (nextDate1 != null) {
			LocalDate nextDate2 = appointementsOrganized.higher(nextDate1);

			nextTwoAppointements.add(nextDate1);

			if (nextDate2 != null) {

				nextTwoAppointements.add(nextDate2);
			}
		}

		return nextTwoAppointements;
	}

	public static int countOverlappingInterventions(ArrayList<LocalTime[]> interventions) {

		int count = 0;

		for (int i = 0; i < interventions.size(); i++) {

			LocalTime currentStartHour = interventions.get(i)[0];
			LocalTime currentEndHour = interventions.get(i)[1];

			int j = interventions.size() - 1;

			while (j > i) {

				LocalTime nextsStartHours = interventions.get(j)[0];
				LocalTime nextsEndHours = interventions.get(j)[1];

				if (nextsStartHours.isBefore(currentEndHour) && nextsEndHours.isAfter(currentStartHour)) {
					count++;
				}

				j--;
			}
		}

		return count;
	}

	public static ArrayList<LocalDate> findBusyDays(ArrayList<LocalDate> appointments, int minimum) {

		ArrayList<LocalDate> busyDays = new ArrayList<LocalDate>();

		for (int i = 0; i < appointments.size(); i++) {

			int count = 1;
			int j = appointments.size() - 1;
			LocalDate currentDate = appointments.get(i);

			while (j > i) {
				LocalDate otherDates = appointments.get(j);

				if (currentDate.equals(otherDates)) {
					count++;
				}
				j--;
			}

			boolean add = true;

			for (LocalDate busyDay : busyDays) {
				if (currentDate.equals(busyDay)) {
					add = false;
					break;
				}
			}

			if (add && count >= minimum) {
				busyDays.add(currentDate);
			}
		}

		busyDays.sort(null);
		return busyDays;
	}

	public static ArrayList<LocalDate> findFreeDays(ArrayList<LocalDate> appointments, LocalDate startDate,
			LocalDate endDate) {

		TreeSet<LocalDate> organisedAppointments = new TreeSet<LocalDate>();
		organisedAppointments.addAll(appointments);

		ArrayList<LocalDate> freeDays = new ArrayList<LocalDate>();

		LocalDate testedDate = startDate;

		while (testedDate.isBefore(endDate) || testedDate.equals(endDate)) {

			boolean hasSeen = false;

			for (LocalDate date : organisedAppointments) {

				if (testedDate.equals(date)) {
					hasSeen = true;
					break;
				}
			}

			if (!hasSeen) {
				freeDays.add(testedDate);

			}

			testedDate = testedDate.plusDays(1);
		}

		return freeDays;
	}

	public static int findLongestIntervention(ArrayList<LocalTime[]> interventions) {

		long maxWorkTime = 0;

		for (LocalTime[] intervention : interventions) {

			long workTime = 0;
			LocalTime startTime = intervention[0];
			LocalTime endTime = intervention[1];

			workTime = Duration.between(startTime, endTime).toMinutes();

			if (workTime > maxWorkTime) {
				maxWorkTime = workTime;
			}
		}

		return (int) maxWorkTime;
	}

}
