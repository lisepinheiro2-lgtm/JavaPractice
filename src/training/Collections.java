package training;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
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


}
