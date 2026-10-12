package training;

import java.io.IOException;
import java.util.ArrayList;

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
	}

}
