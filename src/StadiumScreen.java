package screens;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JProgressBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;

import main.Athlete;
import main.GameEnvironment;
import main.GameManager;
import main.Match;
import main.Team;

import javax.swing.event.ListSelectionEvent;
import java.awt.Font;
import javax.swing.ListSelectionModel;
/**
 * The StadiumScreen is where the user chooses an opposing team to play against<br>
 * Launched from HomeScreen<br>
 * Launches HomeScreen upon closing
 *
 * @author Lachlan Stewart and Stephen Hockey
 * @version 1.1, May 2023.
 */
public class StadiumScreen {

	/**
	 * The frame on which elements are placed
	 */
	private JFrame frame;
	
	/**
	 * The manager of the current instance of draft screen
	 */
	private GameManager manager;
	
	/**
	 * The team that the user currently has selected
	 */
	private Team selectedTeam;

	/**
	 * Create the application with a GameManager object to oversee closing and launching the window
	 * @param incomingManager the GameManager object
	 */
	public StadiumScreen(GameManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Close the window
	 */
	public void closeWindow() {
		frame.dispose();
	}
	
	/**
	 * Runs the closeMarketScreen method of its manager to close itself
	 */
	public void finishedWindow() {
		manager.closeStadiumScreen(this);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelTop = new JPanel();
		panelTop.setLayout(null);
		panelTop.setBackground(new Color(153, 193, 241));
		panelTop.setBounds(0, 0, 600, 32);
		frame.getContentPane().add(panelTop);
		
		JButton btnHelp = new JButton("?");
		btnHelp.setBounds(538, 5, 50, 25);
		panelTop.add(btnHelp);
		
		JLabel lblTopText = new JLabel("The Stadium");
		lblTopText.setBounds(12, 10, 400, 15);
		panelTop.add(lblTopText);
		
		JList<String> listOpposingTeam = new JList<String>();
		listOpposingTeam.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel<String> modelOpposingTeam = new DefaultListModel<String>();
		listOpposingTeam.setModel(modelOpposingTeam);
		listOpposingTeam.setBounds(426, 216, 150, 189);
		frame.getContentPane().add(listOpposingTeam);
		
		JPanel panelAthleteInfoBox = new JPanel();
		panelAthleteInfoBox.setLayout(null);
		panelAthleteInfoBox.setBackground(Color.WHITE);
		panelAthleteInfoBox.setBounds(330, 42, 246, 162);
		frame.getContentPane().add(panelAthleteInfoBox);
		
		JLabel lblName = new JLabel("");
		lblName.setBounds(12, 12, 222, 15);
		panelAthleteInfoBox.add(lblName);
		
		JLabel lblDefence = new JLabel("Defence");
		lblDefence.setBounds(12, 78, 70, 15);
		panelAthleteInfoBox.add(lblDefence);
		
		JProgressBar pbarStamina = new JProgressBar();
		pbarStamina.setMaximum(99);
		pbarStamina.setBounds(86, 39, 150, 14);
		panelAthleteInfoBox.add(pbarStamina);
		
		JLabel lblStamina = new JLabel("Stamina");
		lblStamina.setBounds(12, 38, 70, 15);
		panelAthleteInfoBox.add(lblStamina);
		
		JProgressBar pbarOffence = new JProgressBar();
		pbarOffence.setMaximum(99);
		pbarOffence.setBounds(86, 58, 150, 14);
		panelAthleteInfoBox.add(pbarOffence);
		
		JLabel lblOffence = new JLabel("Offence");
		lblOffence.setBounds(12, 58, 70, 15);
		panelAthleteInfoBox.add(lblOffence);
		
		JProgressBar pbarDefence = new JProgressBar();
		pbarDefence.setMaximum(99);
		pbarDefence.setBounds(86, 78, 150, 14);
		panelAthleteInfoBox.add(pbarDefence);
		
		JLabel lblDescription = new JLabel("");
		lblDescription.setBounds(12, 105, 222, 45);
		panelAthleteInfoBox.add(lblDescription);
		
		JButton btnBack = new JButton("Go Back");
		btnBack.setBounds(10, 39, 117, 25);
		frame.getContentPane().add(btnBack);
		
		JButton btnOpposingTeam1 = new JButton("<html>" + GameEnvironment.getCurrentWeekOpposingTeams().get(0).getTeamName() + "</html>");
		btnOpposingTeam1.setBounds(10, 216, 150, 55);
		frame.getContentPane().add(btnOpposingTeam1);
		
		JButton btnOpposingTeam2 = new JButton("<html>" + GameEnvironment.getCurrentWeekOpposingTeams().get(1).getTeamName() + "</html>");
		btnOpposingTeam2.setBounds(10, 283, 150, 55);
		frame.getContentPane().add(btnOpposingTeam2);
		
		JButton btnOpposingTeam3 = new JButton("<html>" + GameEnvironment.getCurrentWeekOpposingTeams().get(2).getTeamName() + "</html>");
		btnOpposingTeam3.setBounds(10, 350, 150, 55);
		frame.getContentPane().add(btnOpposingTeam3);
		
		JButton btnPlayMatch = new JButton("");
		btnPlayMatch.setFont(new Font("Dialog", Font.BOLD, 30));
		btnPlayMatch.setBounds(168, 216, 246, 189);
		btnPlayMatch.setVisible(false);
		frame.getContentPane().add(btnPlayMatch);
		
		JLabel lblStadiumWelcome = new JLabel("<html>Welcome to The Stadium! Choose between three fierce opponents to play against. You can only play one match per week.</html>");
		lblStadiumWelcome.setBounds(20, 76, 140, 128);
		frame.getContentPane().add(lblStadiumWelcome);
		
		JLabel lblRecord = new JLabel("<html><body style='text-align: right'>Your Season Record:<br>Wins: " + GameEnvironment.getRecord()[0] + "<br>Losses: " + GameEnvironment.getRecord()[1] + "<br>Byes: " + GameEnvironment.getRecord()[2] + "</body></html>");
		lblRecord.setBounds(170, 44, 150, 64);
		frame.getContentPane().add(lblRecord);
		
		//Events
		
		btnOpposingTeam1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedTeam = GameEnvironment.getCurrentWeekOpposingTeams().get(0);
				btnPlayMatch.setText("<html>PLAY " + selectedTeam.getTeamName().toUpperCase() + "</html>");
				modelOpposingTeam.clear();
				for (Athlete athlete : GameEnvironment.getCurrentWeekOpposingTeams().get(0).getPlayers()) {
					modelOpposingTeam.addElement(athlete.getName());
				}
				lblName.setText("Team Averages");
				
				int[] teamAverageStats = selectedTeam.getTeamAverageStats();
				
				pbarStamina.setValue(teamAverageStats[0]);
				pbarOffence.setValue(teamAverageStats[1]);
				pbarDefence.setValue(teamAverageStats[2]);
				
				lblDescription.setText("");
				btnPlayMatch.setVisible(true);
			}
		});
		
		btnOpposingTeam2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedTeam = GameEnvironment.getCurrentWeekOpposingTeams().get(1);
				btnPlayMatch.setText("<html>PLAY " + selectedTeam.getTeamName().toUpperCase() + "</html>");
				modelOpposingTeam.clear();
				for (Athlete athlete : GameEnvironment.getCurrentWeekOpposingTeams().get(1).getPlayers()) {
					modelOpposingTeam.addElement(athlete.getName());
				}
				lblName.setText("Team Averages");
				
				int[] teamAverageStats = selectedTeam.getTeamAverageStats();
				
				pbarStamina.setValue(teamAverageStats[0]);
				pbarOffence.setValue(teamAverageStats[1]);
				pbarDefence.setValue(teamAverageStats[2]);								
				
				lblDescription.setText("");
				btnPlayMatch.setVisible(true);
			}
		});
		
		btnOpposingTeam3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedTeam = GameEnvironment.getCurrentWeekOpposingTeams().get(2);
				btnPlayMatch.setText("<html>PLAY " + selectedTeam.getTeamName().toUpperCase() + "</html>");
				modelOpposingTeam.clear();
				for (Athlete athlete : GameEnvironment.getCurrentWeekOpposingTeams().get(2).getPlayers()) {
					modelOpposingTeam.addElement(athlete.getName());
				}
				lblName.setText("Team Averages");
				
				int[] teamAverageStats = selectedTeam.getTeamAverageStats();
				
				pbarStamina.setValue(teamAverageStats[0]);
				pbarOffence.setValue(teamAverageStats[1]);
				pbarDefence.setValue(teamAverageStats[2]);								
				
				lblDescription.setText("");
				btnPlayMatch.setVisible(true);
			}
		});
		
		btnPlayMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameEnvironment.addMatch(new Match(selectedTeam));
				manager.launchMatchScreen();
				finishedWindow();
			}
		});
		
		listOpposingTeam.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (listOpposingTeam.isSelectionEmpty()) {
					return;
				}

				Athlete selectedAthlete = selectedTeam.get(listOpposingTeam.getSelectedIndex());
				lblName.setText(selectedAthlete.getName());
				lblDescription.setText("<html>" + selectedAthlete.getDescription().replaceAll("\n", "<br>") + "</html>");
				pbarStamina.setValue(selectedAthlete.getStats()[0]);
				pbarOffence.setValue(selectedAthlete.getStats()[1]);
				pbarDefence.setValue(selectedAthlete.getStats()[2]);
				
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchHomeScreen();
				finishedWindow();
			}
		});

		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Select a team to play against by clicking one of the buttons on the left.\nYou can then see that team's average stats, and their individual stats by using the list on the right.\nAfter selecting your opponent, you can then click the big button\nto play a match against them, when you're ready. You can only play one match per week.", "Info", 1);
			}
		});
	}
}
