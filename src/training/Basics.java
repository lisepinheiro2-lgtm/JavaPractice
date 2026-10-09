package training;

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
}
