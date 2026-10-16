package training;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSV {

	private ArrayList<Intervention> interventions;

	private final String fileName = "interventions.csv";

	public CSV(ArrayList<Intervention> interventions) {

		this.interventions = interventions;

		loadInterventions();
	}

	public boolean saveIntervention(Intervention intervention) {

		try (FileWriter writer = new FileWriter(fileName, true)) {

			writer.write(intervention.getFirstName() + ";" + intervention.getStartHour() + ";"
					+ intervention.getEndHour() + System.lineSeparator());
			return true;

		} catch (IOException e) {
			System.err.println("Erreur à la sauvegarde du fichier Interventions");
			e.printStackTrace();
			return false;
		}
	}

	public void loadInterventions() {

		interventions.clear();

		File file = new File(fileName);

		if (!file.exists()) {
			return;
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

			String line;

			while ((line = reader.readLine()) != null) {

				String[] data = line.split(";");

				if (data.length == 3) {

					String firstName = data[0];
					String startHour = data[1];
					String endHour = data[2];

					interventions.add(new Intervention(firstName, startHour, endHour));
				}
			}

		} catch (IOException e) {
			System.err.println("Erreur de lecture du fichier Interventions");
			e.printStackTrace();

		}
	}
}