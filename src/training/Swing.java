package training;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Swing {

	private JFrame frame;
	private JPanel panel;
	private JLabel practiceLabel;
	private JLabel readyLabel;
	private JButton startButton;
	private JPanel panelButton;
	private JComboBox<String> listBox;

	public void createPanel() {

		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		practiceLabel = new JLabel("Java Practice");
		readyLabel = new JLabel("Ready");
		startButton = new JButton("Start");

		panel.add(practiceLabel, BorderLayout.NORTH);
		panel.add(readyLabel, BorderLayout.CENTER);
		panel.add(startButton, BorderLayout.SOUTH);
	}

	public JComboBox<String> createJComboBox() {

		String[] list = { "Easy", "Normal", "Hard" };
		listBox = new JComboBox<String>(list);
		listBox.addActionListener(e -> showDifficulty());

		return listBox;
	}

	public void showDifficulty() {

		if (listBox.getSelectedItem() != null) {
			String selected = (String) listBox.getSelectedItem();

			JOptionPane.showMessageDialog(frame, "Difficulty " + selected.toLowerCase() + " selected");
		}
	}

	public void createButtonPanel() {

		panelButton = new JPanel();
		JButton one = new JButton("One");
		JButton two = new JButton("Two");
		JButton three = new JButton("Three");
		JButton four = new JButton("Four");

		panelButton.setLayout(new GridLayout(2, 2));
		panelButton.add(one);
		panelButton.add(two);
		panelButton.add(three);
		panelButton.add(four);
	}

	public void createFrame() {

		frame = new JFrame();
		frame.add(panel, BorderLayout.NORTH);
		frame.add(panelButton, BorderLayout.CENTER);
		frame.add(createJComboBox(), BorderLayout.SOUTH);

		frame.setSize(800, 600);
		frame.setVisible(true);

	}
}
