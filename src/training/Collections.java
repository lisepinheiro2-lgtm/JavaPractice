package training;

import java.io.IOException;
import java.lang.classfile.instruction.NewMultiArrayInstruction;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Collections {

	public static void positvsList(ArrayList<Integer> numbers) {

		numbers.removeIf(number -> number < 0);
	}

	public static ArrayList<String> shortToLong(ArrayList<String> names) {

		names.sort((name1, name2) -> name1.length() - name2.length());

		return names;
	}

	public static void prepareNames(ArrayList<String> names) {

		names.removeIf(name -> name.length() < 4);
		names.sort((name1, name2) -> name1.length() - name2.length());

	}

	public static int cleanList(ArrayList<String> words) {

		int before = words.size();

		words.removeIf(word -> word.length() < 3);

		int after = words.size();

		return before - after;
	}

	public static void prepareScores(ArrayList<Integer> scores) {

		scores.removeIf(score -> score < 0 || score > 100);
		scores.sort((score1, score2) -> Integer.compare(score2, score1));
	}

	public static int prepareKeywords(ArrayList<String> keywords) {

		int before = keywords.size();

		keywords.removeIf(keyword -> keyword.isBlank() || keyword.equalsIgnoreCase("todo"));

		int after = before - keywords.size();

		keywords.sort((keyword1, keyword2) -> Integer.compare(keyword2.length(), keyword1.length()));

		return after;
	}

	public static void prepareInterventions(ArrayList<Integer> durations) {

		durations.removeIf(duration -> duration < 15);
		durations.sort((duration1, duration2) -> Integer.compare(duration2, duration1));
	}

	public static void cleanPseudos(ArrayList<String> pseudos) {

		pseudos.removeIf(pseudo -> pseudo.isBlank());

		for (int i = 0; i < pseudos.size(); i++) {

			pseudos.set(i, pseudos.get(i).strip());
			String testedPseudo = pseudos.get(i);
			int j = i - 1;

			while (j >= 0) {

				if (testedPseudo.equalsIgnoreCase(pseudos.get(j))) {
					pseudos.remove(i);
					i -= 1;
					break;
				}

				j--;
			}

		}

	}

	public void durations(ArrayList<Integer> durations) {

		durations.removeIf(duration -> duration <= 0);

		int i = 0;

		while (i < durations.size()) {

			if (durations.get(i) > 120) {
				durations.set(i, 120);
			}

			i++;
		}
	}

	public static void cleanEvents(ArrayList<String> events) {

		for (int j = events.size() - 1; j >= 1; j--) {
			String currentEvent = events.get(j);
			String previousEvent = events.get(j - 1);

			if (currentEvent.equals(previousEvent)) {
				events.remove(j);
			}
		}

	}

	public static void prepareHistory(ArrayList<Integer> durations) {

		durations.removeIf(duration -> duration <= 0);

		for (int i = durations.size() - 1; i >= 1; i--) {
			int currentDuration = durations.get(i);
			int previousDuration = durations.get(i - 1);

			if (currentDuration == previousDuration) {
				durations.remove(i);
			}
		}

		durations.sort((duration1, duration2) -> Integer.compare(duration2, duration1));
	}

	public static int longestIncreasingSeries(ArrayList<Integer> durations) {

		int maxCount = 0;
		int count = 0;

		for (int i = 0; i < durations.size(); i++) {

			int currentDuration = durations.get(i);

			if (currentDuration <= 0) {
				count = 0;

			} else if (i > 0 && durations.get(i - 1) > 0 && durations.get(i - 1) < currentDuration) {
				count++;

			} else {
				count = 1;
			}

			if (maxCount < count) {
				maxCount = count;
			}
		}

		return maxCount;
	}

	public static int countIsolatedDays(ArrayList<Integer> interventions) {

		int maxCount = 0;
		int count = 0;

		for (int i = 0; i < interventions.size(); i++) {

			boolean currentWorked = interventions.get(i) > 0;
			boolean previousRested = i == 0 || interventions.get(i - 1) <= 0;
			boolean nextRested = i == interventions.size() - 1 || interventions.get(i + 1) <= 0;

			if (currentWorked && previousRested && nextRested) {
				count++;
			}

			if (maxCount < count) {
				maxCount = count;
			}

		}

		return maxCount;
	}

	public static void organizeFiles(ArrayList<Integer> fileNumbers) {

		fileNumbers.removeIf(number -> number <= 0);

		fileNumbers.sort((number1, number2) -> {

			boolean even1 = number1 % 2 == 0;
			boolean even2 = number2 % 2 == 0;

			if (even1 && !even2) {
				return -1;
			}

			if (!even1 && even2) {
				return 1;
			}

			return Integer.compare(number1, number2);
		});
	}

	public static void sortByPriority(ArrayList<Integer> durations) {

		durations.sort((duration1, duration2) -> {

			boolean priority1 = duration1 >= 60;
			boolean priority2 = duration2 >= 60;

			if (priority1 && !priority2) {
				return -1;
			}

			if (!priority1 && priority2) {
				return 1;
			}

			return Integer.compare(duration1, duration2);
		});
	}

	public static void sortDurations(ArrayList<Integer> durations) {

		durations.sort((duration1, duration2) -> {

			boolean priority1 = duration1 < 60;
			boolean priority2 = duration2 < 60;

			if (priority1 && !priority2) {
				return -1;
			}

			if (!priority1 && priority2) {
				return 1;
			}

			if (priority1 && priority2) {
				return Integer.compare(duration1, duration2);
			}

			return Integer.compare(duration2, duration1);
		});
	}

	public static void sortAdvanced(ArrayList<Integer> durations) {

		durations.sort((duration1, duration2) -> {

			boolean priority1 = duration1 < 60;
			boolean priority2 = duration2 < 60;

			boolean even1 = duration1 % 2 == 0;
			boolean even2 = duration2 % 2 == 0;

			if ((priority1 && !priority2) || (priority1 == priority2 && even1 && !even2)) {
				return -1;
			}

			if ((!priority1 && priority2) || (priority1 == priority2 && !even1 && even2)) {
				return 1;
			}

			return Integer.compare(duration1, duration2);
		});
	}

	public static void sortFiles(ArrayList<Integer> fileNumbers) {

		fileNumbers.sort((number1, number2) -> {

			boolean priority1 = number1 % 5 == 0;
			boolean priority2 = number2 % 5 == 0;

			boolean notEven1 = number1 % 2 != 0;
			boolean notEven2 = number2 % 2 != 0;

			if ((priority1 && !priority2) || (priority1 == priority2 && notEven1 && !notEven2)) {
				return -1;
			}

			if ((!priority1 && priority2) || (priority1 == priority2 && !notEven1 && notEven2)) {
				return 1;
			}

			return Integer.compare(number2, number1);
		});
	}

	public static long calculateEmployeeMinutes(ArrayList<String> records, String employee) {

		long totalDuration = 0;

		for (String record : records) {

			String[] parts = record.split(";");

			if (employee.equalsIgnoreCase(parts[0])) {

				LocalTime startHour = LocalTime.parse(parts[1]);
				LocalTime endHour = LocalTime.parse(parts[2]);

				totalDuration += Duration.between(startHour, endHour).toMinutes();
			}
		}

		return totalDuration;
	}

	public static void sortInterventions(ArrayList<String> records) {

		records.sort((record1, record2) -> {

			String[] parts1 = record1.split(";");
			String[] parts2 = record2.split(";");

			String employee1 = parts1[0];
			LocalTime startHour1 = LocalTime.parse(parts1[1]);
			LocalTime endHour1 = LocalTime.parse(parts1[2]);

			String employee2 = parts2[0];
			LocalTime startHour2 = LocalTime.parse(parts2[1]);
			LocalTime endHour2 = LocalTime.parse(parts2[2]);

			long totalDuration1 = Duration.between(startHour1, endHour1).toMinutes();
			long totalDuration2 = Duration.between(startHour2, endHour2).toMinutes();

			int comparison = Long.compare(totalDuration2, totalDuration1);

			if (comparison != 0) {
				return comparison;
			}

			return employee1.compareTo(employee2);
		});

	}

	public static ArrayList<String> findInvalidRecords(ArrayList<String> records) {

		ArrayList<String> invalidRecords = new ArrayList<String>();

		for (String record : records) {

			boolean isValid = false;
			String[] parts = record.split(";");

			try {

				String name = parts[0];
				LocalTime startHour = LocalTime.parse(parts[1]);
				LocalTime endHour = LocalTime.parse(parts[2]);

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
				boolean validFormat = startHour.format(formatter).equals(parts[1]);
				boolean validFormat2 = endHour.format(formatter).equals(parts[2]);

				if (!name.isBlank() && startHour.isBefore(endHour) && validFormat && validFormat2) {
					isValid = true;
				}

			} catch (DateTimeParseException e) {
			}

			if (!isValid) {
				invalidRecords.add(record);
			}
		}

		return invalidRecords;
	}

	public static String findBestReplacement(ArrayList<String[]> replacements, int requiredHours) {

		int maxNoAttribuateHours = 0;
		String replacentName = null;

		for (String[] replacment : replacements) {

			int totalHours = Integer.parseInt(replacment[1]);
			int workingHours = Integer.parseInt(replacment[2]);
			String currentName = replacment[0];
			int noAttribuateHours = totalHours - workingHours;

			if (replacentName == null && noAttribuateHours >= requiredHours
					|| noAttribuateHours >= requiredHours && noAttribuateHours > maxNoAttribuateHours) {

				maxNoAttribuateHours = noAttribuateHours;
				replacentName = replacment[0];

			} else if (noAttribuateHours >= requiredHours && maxNoAttribuateHours == noAttribuateHours) {

				int result = replacentName.compareTo(currentName);

				if (result > 0) {
					replacentName = currentName;
				}
			}
		}

		return replacentName;
	}

	public static int findFirstAvailable(int[][] interventions, int[][] availabilities) {

		int index = -1;

		for (int i = 0; i < interventions.length; i++) {

			for (int[] availability : availabilities) {

				if (availability[0] <= interventions[i][0] && availability[1] >= interventions[i][1]) {
					return index = i;
				}
			}
		}

		return index;
	}

	public static void reorganizePriorities(ArrayList<String[]> tasks) {

		tasks.sort((task1, task2) -> {

			String name1 = task1[0];
			int priority1 = Integer.parseInt(task1[1]);
			int duration1 = Integer.parseInt(task1[2]);

			String name2 = task2[0];
			int priority2 = Integer.parseInt(task2[1]);
			int duration2 = Integer.parseInt(task2[2]);

			int comparison = Integer.compare(priority1, priority2);

			if (comparison != 0) {
				return comparison;
			}

			int finalComparison = Integer.compare(duration2, duration1);

			if (finalComparison != 0) {
				return finalComparison;
			}

			return name1.compareTo(name2);
		});
	}

	public static ArrayList<String> assignInterventions(ArrayList<String[]> employees, int[] interventions) {

		ArrayList<String> interventionsAssignedAt = new ArrayList<String>();

		for (int intervention : interventions) {

			String name = "Non attribuée";
			boolean attribuate = false;

			for (String[] employee : employees) {

				int inatribuateHours = Integer.parseInt(employee[1]);

				if (!attribuate && inatribuateHours >= intervention) {
					name = employee[0];
					attribuate = true;
					interventionsAssignedAt.add(name);
					employee[1] = String.valueOf(inatribuateHours -= intervention);
					break;
				}
			}

			if (!attribuate) {
				interventionsAssignedAt.add(name);
			}
		}

		return interventionsAssignedAt;
	}

	public static String findBestSlot(ArrayList<int[]> slots, int requiredMinutes) {

		String bestSlotID = null;
		int minGap = -1;
		int bestDistance = -1;

		for (int[] slot : slots) {

			int id = slot[0];
			int duration = slot[1];
			int distance = slot[2];
			int gap = duration - requiredMinutes;

			if (gap < 0) {
				continue;
			}

			if (minGap == -1 || gap < minGap || gap == minGap && distance < bestDistance) {
				bestSlotID = String.valueOf(id);
				minGap = gap;
				bestDistance = distance;
			}
		}

		return bestSlotID;
	}

	public static int transferHours(ArrayList<String> employees, ArrayList<Integer> assignedHours, String fromEmployee,
			String toEmployee, int hours) {

		boolean fromEmployeeFound = false;
		boolean toEmployeeFound = false;
		int indexFrom = -1;
		int indexTo = -1;

		if (hours <= 0 || fromEmployee.equalsIgnoreCase(toEmployee)) {
			return -1;
		}

		for (int i = 0; i < employees.size(); i++) {

			if (employees.get(i).equalsIgnoreCase(fromEmployee)) {

				if (assignedHours.get(i) - hours < 0) {
					return -1;

				} else {
					fromEmployeeFound = true;
					indexFrom = i;
				}
			}

			if (employees.get(i).equalsIgnoreCase(toEmployee)) {
				toEmployeeFound = true;
				indexTo = i;
			}
		}

		if (toEmployeeFound && fromEmployeeFound) {

			assignedHours.set(indexFrom, assignedHours.get(indexFrom) - hours);
			assignedHours.set(indexTo, assignedHours.get(indexTo) + hours);
			///test		
			// System.out.println(employees.get(indexFrom) + " : " +
			// assignedHours.get(indexFrom) + " --> "
			// + employees.get(indexTo) + " : " + assignedHours.get(indexTo));
			return 1;
		}
		return -1;
	}

	public static boolean canScheduleIntervention(ArrayList<LocalTime[]> interventions, LocalTime newStart,
			LocalTime newEnd) {

		if (newEnd.isBefore(newStart) || newEnd.equals(newStart)) {
			return false;
		}

		if (interventions.isEmpty()) {
			return true;
		}

		for (LocalTime[] intervention : interventions) {

			LocalTime startHour = intervention[0];
			LocalTime endHour = intervention[1];

			boolean result = startHour.isAfter(newEnd) || startHour.equals(newEnd) || endHour.isBefore(newStart)
					|| endHour.equals(newStart);

			if (!result) {
				return false;
			}
		}

		return true;
	}

	public static int removeCancelledBookings(ArrayList<String> bookings) {

		int countBefore = bookings.size();

		bookings.removeIf(booking -> booking.split(";")[1].equalsIgnoreCase("canceled"));

		int countAfter = bookings.size();

		return countBefore - countAfter;
	}

	public static boolean canMoveIntervention(ArrayList<String[]> interventions, String employee, String oldDate,
			String newDate) {

		if (oldDate.equals(newDate)) {
			return false;
		}

		ArrayList<String[]> interventionsAtOldDate = new ArrayList<String[]>();

		for (String[] intervention : interventions) {

			if (intervention.length == 4) {

				String name = intervention[0];
				String dateText = intervention[1];

				if (name.equalsIgnoreCase(employee) && dateText.equals(oldDate)) {
					interventionsAtOldDate.add(intervention);
				}
			}
		}

		if (interventionsAtOldDate.isEmpty()) {
			return false;
		}

		for (String[] intervention : interventions) {

			String name = intervention[0];
			String dateText = intervention[1];
			String startHourText = intervention[2];
			String endHourText = intervention[3];

			if (name.equalsIgnoreCase(employee) && dateText.equals(newDate)) {

				for (String[] oldIntervention : interventionsAtOldDate) {

					String oldStartHourText = oldIntervention[2];
					String oldEndHourText = oldIntervention[3];

					try {

						LocalTime startHour = LocalTime.parse(startHourText);
						LocalTime endHour = LocalTime.parse(endHourText);
						LocalTime oldEndHour = LocalTime.parse(oldEndHourText);
						LocalTime oldStartHour = LocalTime.parse(oldStartHourText);

						if (startHour.isBefore(oldEndHour) && endHour.isAfter(oldStartHour)) {
							return false;
						}

					} catch (DateTimeParseException e) {
						return false;
					}
				}
			}
		}

		return true;
	}

	public static ArrayList<Intervention> findInterventionsByName(ArrayList<Intervention> interventions, String name) {

		ArrayList<Intervention> foundInterventions = new ArrayList<Intervention>();
		
		
		for (Intervention intervention : interventions) {
			
			if (intervention.getFirstName().equalsIgnoreCase(name)) {
				foundInterventions.add(intervention);
			}
		}
		
		return foundInterventions;
	}
}
