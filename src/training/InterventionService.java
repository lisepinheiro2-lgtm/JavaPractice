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

		if (!InterventionValidator.isValidName(name)) {
			return 4;
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

	public long calculateWorkMinutesFor(String name) {

		ArrayList<Intervention> interventionsAtName = Collections.findInterventionsByName(interventions, name);

		return Dates.calculateTotalWorkMinutes(interventionsAtName);
	}

	public ArrayList<Intervention> getVisibleInterventions(String requesterName, String role) {

		ArrayList<Intervention> visibleInterventions = new ArrayList<Intervention>();

		for (Intervention intervention : interventions) {

			boolean result = AccessPolicy.canViewIntervention(requesterName, role, intervention);

			if (result) {
				visibleInterventions.add(intervention);
			}
		}

		return visibleInterventions;
	}

	public boolean updateInterventionHours(String requesterName, String role, Intervention intervention,
			LocalTime newStart, LocalTime newEnd) {

		if (requesterName == null || requesterName.isBlank() || intervention == null || role == null || newStart == null
				|| newEnd == null || !isValidSchedule(newStart, newEnd)) {
			return false;
		}

		if (interventions.contains(intervention)
				&& AccessPolicy.canEditIntervention(requesterName, role, intervention)) {

			intervention.setStartHour(newStart.toString());
			intervention.setEndHour(newEnd.toString());

			return true;
		}

		return false;
	}
}