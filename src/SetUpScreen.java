package senggui;
import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.util.ArrayList;

import java.util.ArrayList;
public class SetUpScreen {

	private JFrame frame;
	private GameManager manager;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetUpScreen window = new SetUpScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public SetUpScreen() {
		initialize();
	}
	
	public boolean validString(String input, int lowerBound, int upperBound) {
		return (input.length() >= lowerBound)&&(input.length() <= upperBound)&&(input.matches("[a-zA-Z0-9 ]*"));
	}
	
	public SetUpScreen(GameManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeSetUpScreen() {
		frame.dispose();
	}
	
	public void finishedSetUpScreen() {
		manager.closeSetUpScreen(this);
	}

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
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Welcome to 'Volleyball Mania' enter your preferences and hit 'Play' to begin!", "Error", 0);
			}
		});
		btnHelp.setBounds(388, 5, 50, 25);
		panelTop.add(btnHelp);
		
		JLabel lblSetup = new JLabel("Setup");
		lblSetup.setBounds(12, 10, 70, 15);
		panelTop.add(lblSetup);
		
		
		
		JLabel welcomeLabel = new JLabel("Kia ora new player and welcome to... ");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(75, 28, 300, 40);
		frame.getContentPane().add(welcomeLabel);
		

		JLabel titleLogo = new JLabel("Volleyball Mania");
		titleLogo.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		titleLogo.setHorizontalAlignment(SwingConstants.CENTER);
		titleLogo.setBounds(85, 62, 290, 50);
		frame.getContentPane().add(titleLogo);
		
		JLabel playerNameLabel = new JLabel("What is your name?");
		playerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerNameLabel.setBounds(75, 110, 300, 40);
		frame.getContentPane().add(playerNameLabel);
		
		JTextField playerNameField = new JTextField();
		playerNameField.setHorizontalAlignment(SwingConstants.CENTER);
		playerNameField.setBounds(75, 160, 300, 40);
		frame.getContentPane().add(playerNameField);
		playerNameField.setColumns(10);
		
		
		
		
		JLabel teamNameLabel = new JLabel("What would you like to call your team?");
		teamNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		teamNameLabel.setBounds(75, 210, 300, 40);
		frame.getContentPane().add(teamNameLabel);
		
		JTextField teamNameField = new JTextField();
		teamNameField.setHorizontalAlignment(SwingConstants.CENTER);
		teamNameField.setBounds(75, 260, 300, 40);
		frame.getContentPane().add(teamNameField);
		teamNameField.setColumns(10);
		
		
		
		
		JLabel weeksLabel = new JLabel("How many weeks will your season last?");
		weeksLabel.setHorizontalAlignment(SwingConstants.CENTER);
		weeksLabel.setBounds(75, 310, 300, 40);
		frame.getContentPane().add(weeksLabel);
		

		JLabel weeksSliderLabel = new JLabel("5");
		weeksSliderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		weeksSliderLabel.setBounds(75, 360, 300, 40);
		frame.getContentPane().add(weeksSliderLabel);
		
		JSlider weeksSlider = new JSlider();
		weeksSlider.setValue(5);
		weeksSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				String sliderValue = Integer.toString(weeksSlider.getValue());
				weeksSliderLabel.setText(sliderValue);
			}
		});
		weeksSlider.setSnapToTicks(true);
		weeksSlider.setMaximum(15);
		weeksSlider.setMinimum(5);
		weeksSlider.setBounds(75, 410, 300, 40);
		frame.getContentPane().add(weeksSlider);
		
		
		
		
		JLabel difficultyLabel = new JLabel("At what skill level are you going to play?");
		difficultyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		difficultyLabel.setBounds(75, 460, 300, 40);
		frame.getContentPane().add(difficultyLabel);
		
		ButtonGroup difficultyButtons = new ButtonGroup();
		
		JRadioButton beginnerDifficultyButton = new JRadioButton("Beginner");
		beginnerDifficultyButton.setMnemonic('0');
		beginnerDifficultyButton.setBounds(60, 510, 105, 40);
		frame.getContentPane().add(beginnerDifficultyButton);
		difficultyButtons.add(beginnerDifficultyButton);
		
		JRadioButton intermediateDifficultyButton = new JRadioButton("Intermediate");
		intermediateDifficultyButton.setSelected(true);
		intermediateDifficultyButton.setMnemonic('1');
		intermediateDifficultyButton.setBounds(165, 510, 120, 40);
		frame.getContentPane().add(intermediateDifficultyButton);
		difficultyButtons.add(intermediateDifficultyButton);
		
		JRadioButton advancedDifficultyButton = new JRadioButton("Advanced");
		advancedDifficultyButton.setMnemonic('2');
		advancedDifficultyButton.setBounds(285, 510, 105, 40);
		frame.getContentPane().add(advancedDifficultyButton);
		difficultyButtons.add(advancedDifficultyButton);
		
		
		
		JButton playerNameSubmitButton = new JButton("Play");
		playerNameSubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String playerName = playerNameField.getText();
				String teamName = teamNameField.getText();
				int numWeeks = weeksSlider.getValue();
				int difficulty;
				if (beginnerDifficultyButton.isSelected()) {
					difficulty = 0;
				} else if (intermediateDifficultyButton.isSelected()) {
					difficulty = 1;
				} else {
					difficulty = 2;
				}
				
				if (!validString(playerName, 3, 99)){
					playerNameField.setText("");
					JOptionPane.showMessageDialog(null, "Your name must comprise of " + 3 + " to " + 99 + " non-special characters");
					return;
				}
					
				if (!validString(teamName, 3, 15)){
					teamNameField.setText("");
					JOptionPane.showMessageDialog(null, "Your team's name must comprise of " + 3 + " to " + 15 + " non-special characters");
					return;
				}
				
				GameEnvironment.setPlayerName(playerName);
				GameEnvironment.setPlayerTeam(new Team(teamName));
				GameEnvironment.setFinalWeek(numWeeks);
				GameEnvironment.setDifficulty(difficulty);
				GameEnvironment.setWeek(0);
				GameEnvironment.setMatches(new ArrayList<Match>());
				manager.launchDraftScreen();
				finishedSetUpScreen();
			}
		});
		playerNameSubmitButton.setBounds(75, 600, 300, 50);
		frame.getContentPane().add(playerNameSubmitButton);
	
		
		
		
		
		
		
		

		
		
	
	}
}
