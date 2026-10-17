package training;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TrainingMain {

	public static void main(String[] args) {

		try {

			ArrayList<String> records = new ArrayList<>();

			records.add("Anna;09:00;09:45");
			records.add("Lucas;10:00;11:00");

			CSVTraining.saveReport("rapport.csv", records);

		} catch (IOException e) {
			System.err.println("Échec de la sauvegarde : " + e.getMessage());
		}

		ArrayList<String[]> replacements = new ArrayList<>();

		replacements.add(new String[] { "Alice", "12", "8" });
		replacements.add(new String[] { "Lucas", "6", "2" });
		replacements.add(new String[] { "Emma", "10", "5" });
		replacements.add(new String[] { "Hugo", "4", "1" });

		System.out.println(Collections.findBestReplacement(replacements, 5));

		int[][] absences = { { 1, 3 }, { 5, 8 }, { 3, 5 }, { 12, 14 } };

		System.out.println(Basics.isAbsenceCovered(absences, 12, 14));

		ArrayList<String[]> employees = new ArrayList<>();

		employees.add(new String[] { "Alice", "8", "3" });
		employees.add(new String[] { "Lucas", "6", "2" });
		employees.add(new String[] { "Emma", "10", "7" });

		int[] interventionCounts = { 4, 3, 2, 5 };

		System.out.println(Collections.assignInterventions(employees, interventionCounts));

		ArrayList<String> records = new ArrayList<>();

		records.add("Alice;09:00;10:00");
		records.add("Lucas;10:30;12:00");
		records.add("Emma;14:00;15:30");
		records.add("Alice;16:00;17:00");

		try {

			int count = CSVTraining.updateEndHours(records, "Alice", "11:00");

			System.out.println(count + " intervention(s) modifiée(s) et sauvegardée(s).");

		} catch (IOException e) {
			System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
		}

		int[] movements = { 5, -8, 12, -6, -30, 15 };

		System.out.println(Basics.findFirstStockAnomaly(movements, 20));

		ArrayList<String> employees1 = new ArrayList<>(List.of("Alice", "Lucas", "Emma", "Hugo"));

		ArrayList<Integer> assignedHours = new ArrayList<>(List.of(12, 8, 15, 6));

		System.out.println(Collections.transferHours(employees1, assignedHours, "Lucas", "Hugo", 5));

		ArrayList<String[]> interventions1 = new ArrayList<>();

		interventions1.add(new String[] { "Alice", "2026-10-16", "09:00", "10:00" });
		interventions1.add(new String[] { "Alice", "2026-10-16", "14:00", "15:00" });
		interventions1.add(new String[] { "Alice", "2026-10-17", "11:00", "12:00" });
		interventions1.add(new String[] { "Lucas", "2026-10-17", "09:00", "10:00" });

		boolean result = Collections.canMoveIntervention(interventions1, "Alice", "2026-10-16", "2026-10-17");

		System.out.println(result);

		int[] operations = { 20, -5, 10, 0, -8, 0 };

		System.out.println(Basics.calculateBalance(100, operations));

		ArrayList<String> existingRecords = new ArrayList<>();
		existingRecords.add("Alice;2026-10-16;09:00;10:00");
		existingRecords.add("Lucas;2026-10-16;11:00;12:00");

		ArrayList<String> newRecords = new ArrayList<>();
		newRecords.add("alice;2026-10-16;09:00;10:00");
		newRecords.add("Emma;2026-10-17;14:00;15:00");
		newRecords.add("Emma;2026-10-17;14:00;15:00");
		newRecords.add("Alice;2026-10-16;15:00;16:00");

		System.out.println(CSVTraining.importInterventions(existingRecords, newRecords));

		ArrayList<Intervention> interventions = new ArrayList<Intervention>();
		interventions.add(new Intervention("Anna", "09:00", "10:00"));
		interventions.add(new Intervention("Lucas", "10:30", "12:00"));
		interventions.add(new Intervention("ANNA", "14:00", "16:00"));
		interventions.add(new Intervention("Emma", "17:15", "18:00"));

		InterventionService test = new InterventionService(interventions);

		System.out.println("Minutes Travaillées : " + System.lineSeparator() + "Anna : "
				+ test.calculateWorkMinutesFor("anna") + " - " + "Lucas : " + test.calculateWorkMinutesFor("Lucas")
				+ " - " + "Emma : " + test.calculateWorkMinutesFor("Emma"));

		ArrayList<String> workReport = new ArrayList<String>();
		workReport.add("Name;Minutes");
		workReport.add("Anna;" + test.calculateWorkMinutesFor("anna"));
		workReport.add("Lucas;" + test.calculateWorkMinutesFor("lucas"));
		workReport.add("Emma;" + test.calculateWorkMinutesFor("emma"));

//		try {
//		    CSVTraining.saveReport("work-report-training.csv", workReport);
//
//		} catch (IOException e) {
//		    System.err.println("Erreur de sauvegarde : " + e.getMessage());
//		}

		ArrayList<String> interventionsToCheck = new ArrayList<String>();
		interventionsToCheck.add("Anna;09:00;10:00");
		interventionsToCheck.add("Lucas;10:00;11:00");
		interventionsToCheck.add("Anna;Admin;09:00;10:00");
		interventionsToCheck.add(";08:00;09:00");
		interventionsToCheck.add("Emma;14:00");

		System.out.println(CSVTraining.findInvalidRecords(interventionsToCheck));

		InterventionService accessService = new InterventionService(interventions);
		System.out.println(accessService.getVisibleInterventions("Anna", "EMPLOYEE").size() + " - "
				+ accessService.getVisibleInterventions("Lucas", "EMPLOYEE").size() + " - "
				+ accessService.getVisibleInterventions("Emma", "ADMIN").size() + " - "
				+ accessService.getVisibleInterventions("Hugo", "VISITOR").size() + " - "
				+ accessService.getVisibleInterventions(null, "ADMIN").size());

		ArrayList<Intervention> interventions2 = new ArrayList<Intervention>();
		interventions2.add(new Intervention("Anna", "09:00", "10:00"));

		InterventionService accessService2 = new InterventionService(interventions2);

		for (Intervention intervention : interventions2) {

			LocalTime start = LocalTime.parse("11:00");
			LocalTime end = LocalTime.parse("12:00");

			boolean result1 = accessService2.updateInterventionHours("Anna", "employee", intervention, start, end);
			System.out.println(result1 + " → " + intervention.getStartHour() + " - " + intervention.getEndHour());

			boolean result2 = accessService2.updateInterventionHours("Emma", "admin", intervention, start, end);
			System.out.println(result2 + " → " + intervention.getStartHour() + " - " + intervention.getEndHour());

			boolean result3 = accessService2.updateInterventionHours("Emma", "admin", intervention,
					LocalTime.parse("15:00"), LocalTime.parse("14:00"));
			System.out.println(result3 + " → " + intervention.getStartHour() + " - " + intervention.getEndHour());
		}
	}
}
