package training;

import java.time.LocalTime;
import java.util.ArrayList;

public class InterventionService {

	private final ArrayList<Intervention> interventions;

	public InterventionService(ArrayList<Intervention> interventions) {
		this.interventions = interventions;
	}

	public boolean exists(String name, String start, String end) {

		for (Intervention existing : interventions) {

			if (existing.getFirstName().equalsIgnoreCase(name) && existing.getStartHour().equals(start)
					&& existing.getEndHour().equals(end)) {
				return true;
			}
		}

		return false;
	}

	public int addIntervention(String name, LocalTime start, LocalTime end, CSV csv) {

		if (!isValidSchedule(start, end)) {
			return 3;
		}

		String startText = start.toString();
		String endText = end.toString();

		if (exists(name, startText, endText)) {
			return 1;
		}

		Intervention intervention = new Intervention(name, startText, endText);

		if (!csv.saveIntervention(intervention)) {
			return 2;
		}

		interventions.add(intervention);
		return 0;
	}

	public boolean isValidSchedule(LocalTime start, LocalTime end) {
		return end.isAfter(start);
	}
}