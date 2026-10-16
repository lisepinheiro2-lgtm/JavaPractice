package training;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class UiUtils {

	private UiUtils() {
	}

	public static JPanel createForm(String[] labels, JTextField[] fields) {

		if (labels.length != fields.length) {
			throw new IllegalArgumentException("Le nombre de labels et de champs doit être identique");
		}

		JPanel formPanel = new JPanel(new GridLayout(labels.length, 2, 8, 8));

		for (int i = 0; i < labels.length; i++) {
			formPanel.add(new JLabel(labels[i]));
			formPanel.add(fields[i]);
		}

		JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER));

		container.add(formPanel);

		return container;
	}
}