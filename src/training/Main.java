package training;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Main {

	private static ArrayList<Intervention> interventions;
	private static CSV csv;

	public static void main(String[] args) {

		Swing practice = new Swing();
		practice.createPanel();
		practice.createButtonPanel();
		practice.createFrame();
		interventions = new ArrayList<>();
		csv = new CSV(interventions);

		try {
			ArrayList<String> records = new ArrayList<>();

			records.add("Anna;09:00;09:45");
			records.add("Lucas;10:00;11:00");

			CSV.saveReport("rapport.csv", records);

		} catch (IOException e) {
			JOptionPane.showInputDialog("Échec de la sauvegarde");
		}

		for (Intervention intervention : interventions) {

			System.out.println(
					intervention.getFirstName() + " " + intervention.getStartHour() + " " + intervention.getEndHour());
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

		int[] interventions = { 4, 3, 2, 5 };

		System.out.println(Collections.assignInterventions(employees, interventions));

		ArrayList<String> records = new ArrayList<>();

		records.add("Alice;09:00;10:00");
		records.add("Lucas;10:30;12:00");
		records.add("Emma;14:00;15:30");
		records.add("Alice;16:00;17:00");

		try {

			int count = csv.updateEndHours(records, "Alice", "11:00");

			System.out.println(count + " intervention(s) modifiée(s) et sauvegardée(s).");

		} catch (IOException e) {
			System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
		}
		
		int[] movements = {5, -8, 12, -6, -30, 15};
		
		System.out.println(Basics.findFirstStockAnomaly(movements, 20));
		
		ArrayList<String> employees1 =
		        new ArrayList<>(List.of("Alice", "Lucas", "Emma", "Hugo"));

		ArrayList<Integer> assignedHours =
		        new ArrayList<>(List.of(12, 8, 15, 6));
		
		System.out.println(Collections.transferHours(employees1, assignedHours, "Lucas", "Hugo", 5));
	}
	
	

}
