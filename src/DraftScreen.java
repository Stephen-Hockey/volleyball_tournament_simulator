import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class DraftScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DraftScreen window = new DraftScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DraftScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JList<Athlete> listUsersTeam = new JList<Athlete>();
		listUsersTeam.setBounds(12, 71, 150, 170);
		frame.getContentPane().add(listUsersTeam);
		
		JPanel panelTop = new JPanel();
		panelTop.setBackground(new Color(153, 193, 241));
		panelTop.setBounds(0, 0, 600, 32);
		frame.getContentPane().add(panelTop);
		panelTop.setLayout(null);
		
		JButton btnHelp = new JButton("?");
		btnHelp.setBounds(538, 5, 50, 25);
		panelTop.add(btnHelp);
		
		JLabel lblTopText = new JLabel("The Draft");
		lblTopText.setBounds(12, 10, 70, 15);
		panelTop.add(lblTopText);
		
		JLabel lblYourTeam = new JLabel("Your Team");
		lblYourTeam.setLabelFor(listUsersTeam);
		lblYourTeam.setBounds(12, 44, 150, 15);
		frame.getContentPane().add(lblYourTeam);
		
		JTextArea txtDraftWelcome = new JTextArea();
		txtDraftWelcome.setText("Welcome to the draft!");
		txtDraftWelcome.setBounds(174, 44, 246, 109);
		frame.getContentPane().add(txtDraftWelcome);
		
		JList<Athlete> listDraftRound = new JList<Athlete>();
		
		DefaultListModel<Athlete> modelDraftRound = new DefaultListModel<Athlete>();
		listDraftRound.setModel(modelDraftRound);
		modelDraftRound.addElement(Athlete.generateAthlete(5));
		modelDraftRound.addElement(Athlete.generateAthlete(5));
		modelDraftRound.addElement(Athlete.generateAthlete(5));
		modelDraftRound.addElement(Athlete.generateAthlete(5));
		modelDraftRound.addElement(Athlete.generateAthlete(5));
		modelDraftRound.addElement(Athlete.generateAthlete(5));
		modelDraftRound.addElement(Athlete.generateAthlete(5));
		modelDraftRound.addElement(Athlete.generateAthlete(5));
		modelDraftRound.addElement(Athlete.generateAthlete(5));
		modelDraftRound.addElement(Athlete.generateAthlete(5));
		listDraftRound.setBounds(433, 71, 150, 170);
		frame.getContentPane().add(listDraftRound);
		
		JLabel lblDraftRound = new JLabel("Round X");
		lblDraftRound.setBounds(433, 44, 150, 15);
		frame.getContentPane().add(lblDraftRound);
		
		JPanel panelAthleteInfoBox = new JPanel();
		panelAthleteInfoBox.setBackground(new Color(255, 255, 255));
		panelAthleteInfoBox.setBounds(174, 165, 246, 245);
		frame.getContentPane().add(panelAthleteInfoBox);
		panelAthleteInfoBox.setLayout(null);
		
		JLabel lblAthleteName = new JLabel("(no athlete)");
		lblAthleteName.setBounds(12, 12, 222, 15);
		panelAthleteInfoBox.add(lblAthleteName);
		
		JLabel lblDefence = new JLabel("Defence");
		lblDefence.setBounds(12, 78, 70, 15);
		panelAthleteInfoBox.add(lblDefence);
		
		JProgressBar pBarStamina = new JProgressBar();
		pBarStamina.setMaximum(99);
		pBarStamina.setBounds(86, 39, 150, 14);
		panelAthleteInfoBox.add(pBarStamina);
		
		JLabel lblStamina = new JLabel("Stamina");
		lblStamina.setBounds(12, 38, 70, 15);
		panelAthleteInfoBox.add(lblStamina);
		
		JProgressBar pBarOffence = new JProgressBar();
		pBarOffence.setMaximum(99);
		pBarOffence.setBounds(86, 58, 150, 14);
		panelAthleteInfoBox.add(pBarOffence);
		
		JLabel lblOffence = new JLabel("Offence");
		lblOffence.setBounds(12, 58, 70, 15);
		panelAthleteInfoBox.add(lblOffence);
		
		JProgressBar pBarDefence = new JProgressBar();
		pBarDefence.setMaximum(99);
		pBarDefence.setBounds(86, 78, 150, 14);
		panelAthleteInfoBox.add(pBarDefence);
		
		JTextArea txtAthleteDescription = new JTextArea();
		txtAthleteDescription.setBackground(new Color(222, 221, 218));
		txtAthleteDescription.setBounds(12, 104, 222, 45);
		panelAthleteInfoBox.add(txtAthleteDescription);
		
		JButton btnDraftAthlete = new JButton("DRAFT");
		btnDraftAthlete.setBounds(12, 161, 224, 72);
		panelAthleteInfoBox.add(btnDraftAthlete);
		
		listDraftRound.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Athlete selectedDraftAthlete = listDraftRound.getSelectedValue();
				lblAthleteName.setText(selectedDraftAthlete.getName());
				txtAthleteDescription.setText(selectedDraftAthlete.getDescription());
				pBarStamina.setValue(selectedDraftAthlete.getStats()[0]);
				pBarOffence.setValue(selectedDraftAthlete.getStats()[1]);
				pBarDefence.setValue(selectedDraftAthlete.getStats()[2]);

			}
		});
		
		
	}
}
