package training;

import java.util.ArrayList;

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

	public static int limitScore1(int score) {

		return Math.max(0, Math.min(score, 100));
	}

	public static int findFirstStockAnomaly(int[] movements, int initialStock) {

		for (int i = 0; i < movements.length; i++) {

			initialStock += movements[i];

			if (initialStock < 0) {
				return i;
			}
		}

		return -1;
	}

	public static int findCriticalSequence(int[] results) {

		int index = -1;

		for (int i = 0; i < results.length - 2; i++) {

			if (results[i] == 0) {

				continue;

			} else if ((results[i + 1] > 0 && results[i + 2] > 0)
					&& (results[i] == 2 || results[i + 1] == 2 || results[i + 2] == 2)) {

				index = i;
				break;
			}
		}

		return index;
	}

	public static int calculateBalance(int initialBalance, int[] operations) {

		ArrayList<Integer> operationsList = new ArrayList<Integer>();

		for (int i = 0; i < operations.length; i++) {

			operationsList.add(operations[i]);
		}

		int numberToDelete = 0;

		for (int i = operationsList.size() - 1; i >= 0; i--) {

			if (operationsList.get(i) == 0) {

				numberToDelete++;
				operationsList.remove(i);

			} else if (numberToDelete > 0) {
				operationsList.remove(i);
				numberToDelete--;
			}
		}

		for (int operation : operationsList) {

			initialBalance += operation;
		}

		return initialBalance;
	}
}
