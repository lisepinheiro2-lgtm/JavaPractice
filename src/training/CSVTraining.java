package training;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVTraining {

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

				if (data.length == 3 && data[0].equalsIgnoreCase(firstName)) {
					String updatedLine = data[0] + ";" + data[1] + ";" + newEndHour;
					writer.write(updatedLine + System.lineSeparator());
				} else {
					writer.write(intervention + System.lineSeparator());
				}
			}
		}
	}

	public static int updateEndHours(ArrayList<String> records, String targetName, String newEndHour) throws IOException {

		int count = 0;

		for (int i = 0; i < records.size(); i++) {

			String[] parts = records.get(i).split(";");

			if (parts.length != 3) {
				continue;
			}

			String name = parts[0];

			if (name.equalsIgnoreCase(targetName)) {
				records.set(i, name + ";" + parts[1] + ";" + newEndHour);
				count++;
			}
		}

		if (count > 0) {
			saveReport("interventions-training.csv", records);
		}
		return count;
	}

	public static int importInterventions(ArrayList<String> existingRecords, ArrayList<String> newRecords) {

		int count = 0;

		for (String newRecord : newRecords) {

			boolean result = false;
			String[] newParts = newRecord.split(";");

			if (newParts.length != 4) {
				continue;
			}

			String newName = newParts[0];
			String newDate = newParts[1];
			String newStartHour = newParts[2];
			String newEndHour = newParts[3];

			for (String record : existingRecords) {

				String[] parts = record.split(";");

				if (parts.length != 4) {
					continue;
				}

				String name = parts[0];
				String date = parts[1];
				String startHour = parts[2];
				String endHour = parts[3];

				result = (newName.equalsIgnoreCase(name) && newDate.equals(date) && newStartHour.equals(startHour)
						&& newEndHour.equals(endHour));

				if (result) {
					break;
				}
			}

			if (!result) {
				existingRecords.add(newRecord);
				count++;
			}
		}

		return count;
	}
}
