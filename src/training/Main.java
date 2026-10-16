package training;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {

			ArrayList<Intervention> interventions = new ArrayList<>();
			CSV csv = new CSV(interventions);

			Swing practice = new Swing(interventions, csv);
			practice.createFrame();
		});
	}
}