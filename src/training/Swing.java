package training;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Swing {

	private JFrame frame;

	private JPanel panel;
	private JPanel panelButton;
	private JPanel homePanelIntervention;
	private JPanel cardsPanel;

	private JLabel readyLabel;

	private JButton startButton;
	private JButton searchButton;

	private JComboBox<String> difficultysBox;
	private JComboBox<String> modsBox;

	private JTextField pseudoField;
	private JTextField nameField;
	private JTextField startField;
	private JTextField endField;

	private CardLayout cardLayout;

	String[] pseudos = { "Charlotte", "Anna", };

	private final CSV csv;
	private final InterventionService interventionService;

	public Swing(ArrayList<Intervention> interventions, CSV csv) {
		this.csv = csv;
		this.interventionService = new InterventionService(interventions);
	}

	public void createPanel() {

		panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1));
		panel.setForeground(Color.DARK_GRAY);

		JLabel practiceLabel = new JLabel("Java Practice");
		practiceLabel.setHorizontalAlignment(JLabel.CENTER);

		readyLabel = new JLabel("Ready");
		readyLabel.setHorizontalAlignment(JLabel.CENTER);

		startButton = new JButton("Start");
		JButton resetButton = new JButton("Reset");

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

		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new GridLayout(3, 0));
		panelNorth.setForeground(Color.DARK_GRAY);

		panelNorth.add(selectMod());
		panelNorth.add(createJComboBox());
		panelNorth.add(createSearchPanel());

		return panelNorth;
	}

	public void search(String[] pseudos) {

		String pseudoName = pseudoField.getText().trim();

		for (String pseudo : pseudos) {

			if (pseudoName.equalsIgnoreCase(pseudo)) {
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

	public JPanel createJComboBox() {

		JPanel difficultyPanel = new JPanel();
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

		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BorderLayout(8, 0));
		searchPanel.setForeground(Color.DARK_GRAY);

		JLabel pseudoLabel = new JLabel("Pseudo : ");

		searchButton = new JButton("Search");

		searchButton.addActionListener(e -> search(pseudos));

		searchPanel.add(pseudoLabel, BorderLayout.WEST);
		searchPanel.add(createPseudoField(), BorderLayout.CENTER);
		searchPanel.add(searchButton, BorderLayout.EAST);

		return searchPanel;

	}

	private JTextField createPseudoField() {

		DocumentListener listener = new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateSearchButton();

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateSearchButton();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateSearchButton();

			}
		};

		pseudoField = new JTextField();
		searchButton.setEnabled(false);
		pseudoField.getDocument().addDocumentListener(listener);
		return pseudoField;
	}

	private void updateSearchButton() {

		boolean empty = pseudoField.getText().isBlank();

		searchButton.setEnabled(!empty);
	}

	public JPanel selectMod() {

		JPanel selectMod = new JPanel();
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

		JPanel homePanel = new JPanel();

		homePanel.setLayout(new BorderLayout());

		createButtonPanel();
		createPanel();

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
		homePanelIntervention.setLayout(new BorderLayout(0, 10));

		nameField = new JTextField(10);
		startField = new JTextField(10);
		endField = new JTextField(10);

		JPanel formContainer = UiUtils.createForm(new String[] { "Nom", "Heure de début", "Heure de fin" },
				new JTextField[] { nameField, startField, endField });
		JLabel interventionLabel = new JLabel("<html><center>Interventions<br>Liste des interventions</center></html>");
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton addIntervention = new JButton("Ajouter l'intervention");

		buttonPanel.add(addIntervention);
		interventionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addIntervention.addActionListener(e -> addIntervention());

		homePanelIntervention.add(formContainer, BorderLayout.CENTER);
		homePanelIntervention.add(interventionLabel, BorderLayout.NORTH);
		homePanelIntervention.add(buttonPanel, BorderLayout.SOUTH);

		return homePanelIntervention;
	}

	public void addIntervention() {

		if (nameField.getText().isBlank()) {
			JOptionPane.showMessageDialog(homePanelIntervention, "Veuillez entrer un nom");
			return;
		}

		try {

			if (startField.getText().isBlank()) {
				JOptionPane.showMessageDialog(homePanelIntervention, "Veuillez entrer une heure de début");
				return;
			}

			else if (endField.getText().isBlank()) {
				JOptionPane.showMessageDialog(homePanelIntervention, "Veuillez entrer une heure de fin");
				return;
			}

			LocalTime startParssed = FormatUtils.parseHour(startField.getText());
			LocalTime endParssed = FormatUtils.parseHour(endField.getText());

			String name = nameField.getText();
			String start = startParssed.toString();
			String end = endParssed.toString();

			int result = interventionService.addIntervention(name, startParssed, endParssed, csv);

			if (result == 1) {
				JOptionPane.showMessageDialog(homePanelIntervention, "L'intervention existe déjà");
				return;
			}

			if (result == 2) {
				JOptionPane.showMessageDialog(homePanelIntervention, "Erreur lors de la sauvegarde");
				return;
			}

			if (result == 3) {
				JOptionPane.showMessageDialog(homePanelIntervention, "L'heure de début doit précéder l'heure de fin");
				return;
			}

			if (result == 4) {
				JOptionPane.showMessageDialog(homePanelIntervention, "Nom invalide");
				return;
			}
			
			if (result == 5) {
				JOptionPane.showMessageDialog(homePanelIntervention, "Conflit avec une intervention existante");
				return;
			}

			nameField.setText("");
			startField.setText("");
			endField.setText("");

			System.out.println(name + " - " + start + " - " + end);

		} catch (DateTimeParseException e) {
			JOptionPane.showMessageDialog(homePanelIntervention, "Veuillez entrer des heures valides");
		}

	}

	public JPanel createHomeButtonPanel() {

		JPanel homeButtonPanel = new JPanel();
		JButton accueilHomeButton = new JButton("Accueil");
		JButton interventionHomeButton = new JButton("Interventions");

		homeButtonPanel.setLayout(new GridLayout(1, 2, 8, 0));

		homeButtonPanel.add(accueilHomeButton);
		homeButtonPanel.add(interventionHomeButton);

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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
