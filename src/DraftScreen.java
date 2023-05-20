import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class DraftScreen {

	private JFrame frame;
	private GameManager manager;
	
	int round = 1;
	ArrayList<Athlete> usersTeam = new ArrayList<Athlete>();
	ArrayList<Athlete> draftRound = Athlete.generateAthletes(10, 9);
	
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
	
	public DraftScreen(GameManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		manager.closeDraftScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JList<String> listUsersTeam = new JList<String>();
		DefaultListModel<String> modelUsersTeam = new DefaultListModel<String>();
		listUsersTeam.setModel(modelUsersTeam);
		listUsersTeam.setBounds(12, 71, 150, 200);		
		frame.getContentPane().add(listUsersTeam);
		
		JList<String> listDraftRound = new JList<String>();		
		DefaultListModel<String> modelDraftRound = new DefaultListModel<String>();
		listDraftRound.setModel(modelDraftRound);		
		for (Athlete athlete : draftRound) {
			modelDraftRound.addElement(athlete.getName());
		}
		listDraftRound.setBounds(433, 71, 150, 200);
		frame.getContentPane().add(listDraftRound);
		
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
		
		
		
		JLabel lblDraftRound = new JLabel("Round " + round);
		lblDraftRound.setBounds(433, 44, 150, 15);
		frame.getContentPane().add(lblDraftRound);
		
		JPanel panelAthleteInfoBox = new JPanel();
		panelAthleteInfoBox.setBackground(new Color(255, 255, 255));
		panelAthleteInfoBox.setBounds(174, 165, 246, 245);
		frame.getContentPane().add(panelAthleteInfoBox);
		panelAthleteInfoBox.setLayout(null);
		
		JLabel lblAthleteName = new JLabel("");
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
		
		JButton btnDraftAthlete = new JButton("DRAFT");
		
		btnDraftAthlete.setBounds(12, 161, 224, 72);
		panelAthleteInfoBox.add(btnDraftAthlete);
		
		JLabel lblDescription = new JLabel("");
		lblDescription.setBounds(12, 104, 222, 45);
		panelAthleteInfoBox.add(lblDescription);
		
		JButton btnFinish = new JButton("AUTO-DRAFT");
		btnFinish.setBounds(433, 370, 150, 40);
		frame.getContentPane().add(btnFinish);
		
		JLabel lblDraftWelcome = new JLabel("<html><body style='text-align: center'>Welcome to The Draft!<br>You can pick 1 athlete per round, for 10 rounds. So choose carefully!");
		lblDraftWelcome.setBounds(174, 44, 246, 109);
		frame.getContentPane().add(lblDraftWelcome);
		
		listUsersTeam.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				if (listUsersTeam.isSelectionEmpty()) {
					return;
				}
				
				Athlete selectedDraftAthlete = usersTeam.get(listUsersTeam.getSelectedIndex());
				lblAthleteName.setText(selectedDraftAthlete.getName());
				lblDescription.setText(selectedDraftAthlete.getDescription());
				pBarStamina.setValue(selectedDraftAthlete.getStats()[0]);
				pBarOffence.setValue(selectedDraftAthlete.getStats()[1]);
				pBarDefence.setValue(selectedDraftAthlete.getStats()[2]);
				listDraftRound.clearSelection();
				btnDraftAthlete.setEnabled(false);
			}
		});
		
		listDraftRound.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				if (listDraftRound.isSelectionEmpty()) {
					return;
				}
				
				Athlete selectedDraftAthlete = draftRound.get(listDraftRound.getSelectedIndex());
				lblAthleteName.setText(selectedDraftAthlete.getName());
				lblDescription.setText("<html>" + selectedDraftAthlete.getDescription().replaceAll("\n", "<br>"));
				pBarStamina.setValue(selectedDraftAthlete.getStats()[0]);
				pBarOffence.setValue(selectedDraftAthlete.getStats()[1]);
				pBarDefence.setValue(selectedDraftAthlete.getStats()[2]);
				listUsersTeam.clearSelection();
				btnDraftAthlete.setEnabled(true);
			}
		});
		
		btnDraftAthlete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listDraftRound.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "You haven't selected an athlete from the draft!", "Error", 0);
					return;
				}
				
				
				usersTeam.add(draftRound.get(listDraftRound.getSelectedIndex()));
				modelUsersTeam.addElement(draftRound.get(listDraftRound.getSelectedIndex()).getName());
				if (round < 10) {
					round++;
					lblDraftRound.setText("Round " + round);
					draftRound = Athlete.generateAthletes(10, 10-round);
					modelDraftRound.clear();
					for (Athlete athlete : draftRound) {
						modelDraftRound.addElement(athlete.getName());
					}
				} else {
					modelDraftRound.clear();
					btnFinish.setText("Finish");
					btnDraftAthlete.setVisible(false);
				}
			}
		});
		

		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (usersTeam.size() < 10) {
					if(JOptionPane.showConfirmDialog(null, "Stop drafting and randomly select " + (10 - usersTeam.size()) + " players for your team?", "Confirm", 0) == 1) {
						return;
					}
					while (usersTeam.size() < 10) {						
						Athlete generatedAthlete = Athlete.generateAthlete(10-round);
						usersTeam.add(generatedAthlete);
						modelUsersTeam.addElement(generatedAthlete.getName());
						if (round < 10) {
							round++;
							lblDraftRound.setText("Round 10");
						}
					}
					modelDraftRound.clear();
					btnFinish.setText("Finish");
					btnDraftAthlete.setVisible(false);
					return;
				}
				GameEnvironment.getPlayerTeam().setPlayers(usersTeam);
				GameEnvironment.setUpWeek();
				GameEnvironment.setInventory(new ArrayList<Item>());
				GameEnvironment.setRecord(new int[]{0,0,0});
				manager.launchHomeScreen();
				finishedWindow();
			}
		});
	}
}
