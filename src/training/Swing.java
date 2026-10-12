package training;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Swing {

	private JFrame frame;
	private JPanel panel;
	private JLabel practiceLabel;
	private JLabel readyLabel;
	private JButton startButton;
	private JButton resetButton;
	private JPanel panelButton;
	private JComboBox<String> difficultysBox;
	private JPanel selectMod;
	private JComboBox<String> modsBox;
	private JPanel searchPanel;
	private JPanel panelNorth;
	private JPanel difficultyPanel;
	private JTextField pseudoField;
	private JPanel homePanel;
	private JPanel homePanelIntervention;
	private JPanel homeButtonPanel;
	private JButton accueilHomeButton;
	private JButton interventionHomeButton;
	private JPanel cardsPanel;
	private CardLayout cardLayout;

	String[] pseudos = { "Charlotte", "Anna", };

	public void createPanel() {

		panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1));
		panel.setForeground(Color.DARK_GRAY);

		practiceLabel = new JLabel("Java Practice");
		practiceLabel.setHorizontalAlignment(JLabel.CENTER);

		readyLabel = new JLabel("Ready");
		readyLabel.setHorizontalAlignment(JLabel.CENTER);

		startButton = new JButton("Start");
		resetButton = new JButton("Reset");

		JLabel homeLabel = new JLabel("<html><center>Accueil<br>Bienvenue dans JavaPractice</center></html>");
		homeLabel.setHorizontalAlignment(JLabel.CENTER);

		JPanel startReset = new JPanel();
		startReset.setLayout(new GridLayout(1, 0));

		startButton.addActionListener(e -> start());
		resetButton.addActionListener(e -> reset());

		panel.add(practiceLabel);
		panel.add(readyLabel);
		panel.add(startReset);
		startReset.add(startButton);
		startReset.add(resetButton);
		panel.add(homeLabel);
	}

	public JPanel createPanelNorth() {

		panelNorth = new JPanel();
		panelNorth.setLayout(new GridLayout(3, 0));
		panelNorth.setForeground(Color.DARK_GRAY);

		panelNorth.add(selectMod());
		panelNorth.add(createJComboBox());
		panelNorth.add(createSearchPanel());

		return panelNorth;
	}

	public void search(String[] pseudos) {

		String pseudoName = pseudoField.getText();

		for (String pseudo : pseudos) {

			if (pseudoName.trim().equalsIgnoreCase(pseudo)) {
				JOptionPane.showMessageDialog(frame, "Recherche de " + pseudo);
				return;
			}
		}

		JOptionPane.showMessageDialog(frame, "Pseudo introuvable");
	}

	public void reset() {

		int result = JOptionPane.showConfirmDialog(frame, "Réinitialiser tous les paramètres ? ", "Confirmation",
				JOptionPane.YES_NO_OPTION);

		if (result == JOptionPane.YES_OPTION) {
			pseudoField.setText("");
			modsBox.setSelectedItem("Solo");
			difficultysBox.setSelectedItem("Easy");
			modsBox.setEnabled(true);
			difficultysBox.setEnabled(true);
			labelJComboBox();

		}
	}

	public void start() {

		if (modsBox.getSelectedItem() != null) {

			String selected = (String) modsBox.getSelectedItem();

			int result = JOptionPane.showConfirmDialog(frame, "Démarrer en mode  " + selected.toLowerCase() + " ?",
					"Confirmation", JOptionPane.YES_NO_OPTION);

			if (result == JOptionPane.YES_OPTION) {
				pseudoField.setText("");
				readyLabel.setText("Started - " + modsBox.getSelectedItem() + " / " + difficultysBox.getSelectedItem());
				startButton.setEnabled(false);
				modsBox.setEnabled(false);
				difficultysBox.setEnabled(false);

			}
		}
	}

	public void labelJComboBox() {

		startButton.setEnabled(true);

		if (modsBox.getSelectedItem() != null && difficultysBox.getSelectedItem() != null) {

			readyLabel.setText("Ready - " + modsBox.getSelectedItem() + " / " + difficultysBox.getSelectedItem());

			if (modsBox.getSelectedItem().toString().equalsIgnoreCase("solo")
					&& difficultysBox.getSelectedItem().toString().equalsIgnoreCase("hard")) {

				startButton.setEnabled(false);
			}
		}
	}

	public void showDifficulty() {

		if (difficultysBox.getSelectedItem() != null) {
			String selected = (String) difficultysBox.getSelectedItem();

			int result = JOptionPane.showConfirmDialog(frame, "Démarrer en mod  " + selected.toLowerCase() + " ?",
					"Confirmation", JOptionPane.YES_NO_OPTION);

			if (result == JOptionPane.YES_OPTION) {
				readyLabel.setText("Started");
			}
		}
	}

	public JPanel createJComboBox() {

		difficultyPanel = new JPanel();
		difficultyPanel.setLayout(new BorderLayout(0, 0));
		difficultyPanel.setForeground(Color.DARK_GRAY);

		JLabel difficultyLabel = new JLabel("Difficultée ");

		String[] list = { "Easy", "Normal", "Hard" };
		difficultysBox = new JComboBox<String>(list);

		difficultysBox.addActionListener(e -> labelJComboBox());

		difficultyPanel.add(difficultyLabel, BorderLayout.WEST);
		difficultyPanel.add(difficultysBox, BorderLayout.CENTER);

		return difficultyPanel;

	}

	public JPanel createSearchPanel() {

		searchPanel = new JPanel();
		searchPanel.setLayout(new BorderLayout(8, 0));
		searchPanel.setForeground(Color.DARK_GRAY);

		JLabel pseudoLabel = new JLabel("Pseudo : ");

		pseudoField = new JTextField();

		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(e -> search(pseudos));

		searchPanel.add(pseudoLabel, BorderLayout.WEST);
		searchPanel.add(pseudoField, BorderLayout.CENTER);
		searchPanel.add(searchButton, BorderLayout.EAST);

		return searchPanel;

	}

	public JPanel selectMod() {

		selectMod = new JPanel();
		selectMod.setLayout(new BorderLayout(29, 0));
		selectMod.setForeground(Color.DARK_GRAY);

		JLabel title = new JLabel("Mode");

		String[] modsListText = { "Solo", "Duo", "Team" };
		modsBox = new JComboBox<String>(modsListText);

		modsBox.addActionListener(e -> labelJComboBox());

		selectMod.add(title, BorderLayout.WEST);
		selectMod.add(modsBox, BorderLayout.CENTER);

		return selectMod;
	}

	public JPanel createHomePanel() {

		homePanel = new JPanel();

		homePanel.setLayout(new BorderLayout());

		homePanel.add(createPanelNorth(), BorderLayout.NORTH);
		homePanel.add(panelButton, BorderLayout.CENTER);
		homePanel.add(panel, BorderLayout.SOUTH);

		return homePanel;
	}

	public void createCardsPanel() {

		cardLayout = new CardLayout();
		cardsPanel = new JPanel(cardLayout);

		cardsPanel.add(createHomePanel(), "HOME");
		cardsPanel.add(createHomePanelIntervention(), "INTERVENTIONS");
	}

	public JPanel createHomePanelIntervention() {

		homePanelIntervention = new JPanel();
		JLabel interventionLabel = new JLabel("<html><center>Interventions<br>Liste des interventions</center></html>");

		homePanelIntervention.add(interventionLabel);

		return homePanelIntervention;
	}

	public JPanel createHomeButtonPanel() {

		homeButtonPanel = new JPanel();
		accueilHomeButton = new JButton("Accueil");
		interventionHomeButton = new JButton("Interventions");

		homeButtonPanel.setLayout(new GridLayout(1, 2, 8, 0));

		homeButtonPanel.add(accueilHomeButton, BorderLayout.WEST);
		homeButtonPanel.add(interventionHomeButton, BorderLayout.EAST);

		accueilHomeButton.addActionListener(e -> cardLayout.show(cardsPanel, "HOME"));
		interventionHomeButton.addActionListener(e -> cardLayout.show(cardsPanel, "INTERVENTIONS"));

		return homeButtonPanel;
	}

	public void createButtonPanel() {

		panelButton = new JPanel();
		JButton one = new JButton("One");
		JButton two = new JButton("Two");
		JButton three = new JButton("Three");
		JButton four = new JButton("Four");

		panelButton.setLayout(new GridLayout(2, 2));
		panelButton.setForeground(Color.DARK_GRAY);
		panelButton.add(one);
		panelButton.add(two);
		panelButton.add(three);
		panelButton.add(four);
	}

	public void createFrame() {

		frame = new JFrame();
		frame.setLayout(new BorderLayout());

		createCardsPanel();

		frame.add(cardsPanel, BorderLayout.CENTER);
		frame.add(createHomeButtonPanel(), BorderLayout.SOUTH);

		frame.setSize(300, 320);
		frame.setLocationRelativeTo(null);
		frame.setForeground(Color.DARK_GRAY);
		frame.setVisible(true);
	}
}
