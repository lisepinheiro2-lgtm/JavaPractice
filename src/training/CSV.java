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

	public void addIntervention(String firstName, String startHour, String endHour) {

		Intervention intervention = new Intervention(firstName, startHour, endHour);

		if (saveIntervention(intervention)) {

			interventions.add(intervention);
		}
	}

	public boolean saveIntervention(Intervention intervention) {

		try {

			FileWriter writer = new FileWriter(fileName, true);

			writer.write(intervention.getFirstName() + ";" + intervention.getStartHour() + ";"
					+ intervention.getEndHour() + System.lineSeparator());

			writer.close();
			return true;

		} catch (IOException e) {
			System.out.println("Erreur à la sauvegarde du fichier Interventions");
			e.printStackTrace();
			return false;
		}
	}

	private void loadInterventions() {

		interventions.clear();

		File file = new File(fileName);

		if (!file.exists()) {
			return;
		}

		try {

			BufferedReader reader = new BufferedReader(new FileReader(file));
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

			reader.close();

		} catch (IOException e) {
			System.out.println("Erreur à la fermeture du fichier Interventions.");
			e.printStackTrace();

		}
	}

	public static void saveReport(String filePath, ArrayList<String> records) throws IOException {

		try (FileWriter writer = new FileWriter(filePath)) {

			for (String record : records) {

				writer.write(record + System.lineSeparator());
			}

		}
	}

	public void updateIntervention(String filePath, String firstName, String newEndHour) throws IOException {

		File file = new File(filePath);

		if (!file.exists()) {
			return;
		}

		String employeeName = null;
		String startHour = null;
		String line;
		ArrayList<String> newEndHours = new ArrayList<String>();

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

			while ((line = reader.readLine()) != null) {

				newEndHours.add(line);
			}
		}

		try (FileWriter writer = new FileWriter(filePath)) {

			for (String intervention : newEndHours) {

				String[] data = intervention.split(";");
				employeeName = data[0];
				startHour = data[1];

				if (data.length == 3 && employeeName.equalsIgnoreCase(firstName)) {

					String updatedLine = employeeName + ";" + startHour + ";" + newEndHour;
					writer.write(updatedLine + System.lineSeparator());
				} else {
					writer.write(intervention + System.lineSeparator());
				}
			}
		}
	}

	public int updateEndHours(ArrayList<String> records, String targetName, String newEndHour) throws IOException {

		int count = 0;

		for (int i = 0; i < records.size(); i++) {

			String[] parts = records.get(i).split(";");
			String name = parts[0];

			if (name.equalsIgnoreCase(targetName)) {
				records.set(i, name + ";" + parts[1] + ";" + newEndHour);
				count++;
			}
		}

		if (count > 0) {
			saveReport(fileName, records);
		}
		return count;
	}
}
