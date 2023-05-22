package screens;
import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ChangeListener;

import main.GameEnvironment;
import main.GameManager;
import main.Match;
import main.Team;

import javax.swing.event.ChangeEvent;

import java.util.ArrayList;

/**
 * The SetUpScreen is where the user enters their name, team name, 
 * preferred game length, and difficulty<br>
 * Launched upon execution<br>
 * Launches DraftScreen upon closing
 * 
 * @author Lachlan Stewart and Stephen Hockey
 * @version 1.1, May 2023.
 */
public class SetUpScreen {

	/**
	 * The frame on which elements are placed
	 */
	private JFrame frame;
	
	/**
	 * The manager of the current instance of club screen
	 */
	private GameManager manager;
	
	/**
	 * Create the window with a GameManager object to oversee closing and launching the window
	 * @param incomingManager the GameManager object
	 */
	public SetUpScreen(GameManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	/**
	 * Close the window
	 */
	public void closeSetUpScreen() {
		frame.dispose();
	}
	
	/**
	 * Runs the closeClubScreen method of its manager to close itself
	 */
	public void finishedSetUpScreen() {
		manager.closeSetUpScreen(this);
	}
	
	/**
	 * Checks if string is valid for given bounds and hard coded regex
	 * @param input string to test as a String
	 * @param lowerBound the smallest valid string size as an integer
	 * @param upperBound the largest valid string size as an integer
	 * @return if the string meets the given size and regex criteria as a boolean
	 */
	public boolean validString(String input, int lowerBound, int upperBound) {
		return (input.length() >= lowerBound)&&(input.length() <= upperBound)&&(input.matches("[a-zA-Z0-9 ]*"));
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelTop = new JPanel();
		panelTop.setLayout(null);
		panelTop.setBackground(new Color(153, 193, 241));
		panelTop.setBounds(0, 0, 450, 32);
		frame.getContentPane().add(panelTop);
		
		JButton btnHelp = new JButton("?");
		btnHelp.setBounds(388, 5, 50, 25);
		panelTop.add(btnHelp);
		
		JLabel lblTopText = new JLabel("Setup");
		lblTopText.setBounds(12, 10, 70, 15);
		panelTop.add(lblTopText);
		
		JLabel lblWelcome = new JLabel("Kia ora new player and welcome to... ");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(75, 28, 300, 40);
		frame.getContentPane().add(lblWelcome);

		JLabel lblTitle = new JLabel("Volleyball Mania");
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(85, 62, 290, 50);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblPlayerName = new JLabel("What is your name?");
		lblPlayerName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerName.setBounds(75, 110, 300, 40);
		frame.getContentPane().add(lblPlayerName);
		
		JTextField textPlayerName = new JTextField();
		textPlayerName.setHorizontalAlignment(SwingConstants.CENTER);
		textPlayerName.setBounds(75, 160, 300, 40);
		frame.getContentPane().add(textPlayerName);
		textPlayerName.setColumns(10);
		
		JLabel lblTeamName = new JLabel("What would you like to call your team?");
		lblTeamName.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamName.setBounds(75, 210, 300, 40);
		frame.getContentPane().add(lblTeamName);
		
		JTextField textTeamName = new JTextField();
		textTeamName.setHorizontalAlignment(SwingConstants.CENTER);
		textTeamName.setBounds(75, 260, 300, 40);
		frame.getContentPane().add(textTeamName);
		textTeamName.setColumns(10);
		
		JLabel lblWeeks = new JLabel("How many weeks will your season last?");
		lblWeeks.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeeks.setBounds(75, 310, 300, 40);
		frame.getContentPane().add(lblWeeks);
		

		JLabel weeksSliderLabel = new JLabel("5");
		weeksSliderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		weeksSliderLabel.setBounds(75, 360, 300, 40);
		frame.getContentPane().add(weeksSliderLabel);
		
		JSlider sliderWeeks = new JSlider();
		sliderWeeks.setValue(5);
		sliderWeeks.setSnapToTicks(true);
		sliderWeeks.setMaximum(15);
		sliderWeeks.setMinimum(5);
		sliderWeeks.setBounds(75, 410, 300, 40);
		frame.getContentPane().add(sliderWeeks);
		
		JLabel lblDifficulty = new JLabel("At what skill level are you going to play?");
		lblDifficulty.setHorizontalAlignment(SwingConstants.CENTER);
		lblDifficulty.setBounds(75, 460, 300, 40);
		frame.getContentPane().add(lblDifficulty);
		
		ButtonGroup difficultyButtons = new ButtonGroup();
		
		JRadioButton rdbtnBeginner = new JRadioButton("Beginner");
		rdbtnBeginner.setMnemonic('0');
		rdbtnBeginner.setBounds(60, 510, 105, 40);
		frame.getContentPane().add(rdbtnBeginner);
		difficultyButtons.add(rdbtnBeginner);
		
		JRadioButton rdbtnIntermediate = new JRadioButton("Intermediate");
		rdbtnIntermediate.setSelected(true);
		rdbtnIntermediate.setMnemonic('1');
		rdbtnIntermediate.setBounds(165, 510, 120, 40);
		frame.getContentPane().add(rdbtnIntermediate);
		difficultyButtons.add(rdbtnIntermediate);
		
		JRadioButton rdbtnAdvanced = new JRadioButton("Advanced");
		rdbtnAdvanced.setMnemonic('2');
		rdbtnAdvanced.setBounds(285, 510, 105, 40);
		frame.getContentPane().add(rdbtnAdvanced);
		difficultyButtons.add(rdbtnAdvanced);
		
		JButton btnSubmit = new JButton("Play");
		btnSubmit.setBounds(75, 600, 300, 50);
		frame.getContentPane().add(btnSubmit);

		//Events
		
		sliderWeeks.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				String sliderValue = Integer.toString(sliderWeeks.getValue());
				weeksSliderLabel.setText(sliderValue);
			}
		});
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String playerName = textPlayerName.getText();
				String teamName = textTeamName.getText();
				int numWeeks = sliderWeeks.getValue();
				int difficulty;
				if (rdbtnBeginner.isSelected()) {
					difficulty = 0;
				} else if (rdbtnIntermediate.isSelected()) {
					difficulty = 1;
				} else {
					difficulty = 2;
				}
				
				if (!validString(playerName, 3, 99)){
					textPlayerName.setText("");
					JOptionPane.showMessageDialog(null, "Your name must comprise of " + 3 + " to " + 99 + " non-special characters");
					return;
				}
					
				if (!validString(teamName, 3, 15)){
					textTeamName.setText("");
					JOptionPane.showMessageDialog(null, "Your team's name must comprise of " + 3 + " to " + 15 + " non-special characters");
					return;
				}
				
				GameEnvironment.setPlayerName(playerName);
				GameEnvironment.setPlayerTeam(new Team(teamName));
				GameEnvironment.setFinalWeek(numWeeks);
				GameEnvironment.setDifficulty(difficulty);
				GameEnvironment.setMoney(250*(2-difficulty));
				GameEnvironment.setWeek(0);
				GameEnvironment.setMatches(new ArrayList<Match>());
				manager.launchDraftScreen();
				finishedSetUpScreen();
			}
		});

		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Welcome to 'Volleyball Mania' enter your preferences and hit 'Play' to begin!", "Info", 1);
			}
		});
	}
}
