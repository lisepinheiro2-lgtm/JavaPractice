package training;

import java.util.Comparator;

public class Basics {

	public static int limitScore(int score) {

		int score1 = Math.min(score, 0);
		int score2 = Math.max(score, 100);

		if (score2 == 0) {
			return 0;
		} else if (score1 == 100) {
			return score;
		} else {
			return 100;
		}
	}

	public static boolean isAbsenceCovered(int[][] absences, int startDay, int endDay) {

		boolean found = false;

		for (int currentDay = startDay; currentDay <= endDay; currentDay++) {

			found = false;

			for (int[] row : absences) {

				int startAbsence = row[0];
				int endAbsence = row[1];

				if (currentDay >= startAbsence && currentDay <= endAbsence) {
					found = true;
				}
			}

			if (!found) {
				return false;
			}
		}

		return true;
	}
}
