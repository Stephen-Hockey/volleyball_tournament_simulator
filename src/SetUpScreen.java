package senggui;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JRadioButton;

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
		

		
		JLabel welcomeLabel = new JLabel("Kia ora new player!");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setBounds(75, 20, 300, 50);
		frame.getContentPane().add(welcomeLabel);
		
		
		
		
		JLabel playerNameLabel = new JLabel("What is your name?");
		playerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerNameLabel.setBounds(75, 80, 300, 50);
		frame.getContentPane().add(playerNameLabel);
		
		JTextField playerNameField = new JTextField();
		playerNameField.setHorizontalAlignment(SwingConstants.CENTER);
		playerNameField.setBounds(75, 130, 300, 50);
		frame.getContentPane().add(playerNameField);
		playerNameField.setColumns(10);
		
		
		
		
		JLabel teamNameLabel = new JLabel("What would you like to call your team?");
		teamNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		teamNameLabel.setBounds(75, 190, 300, 50);
		frame.getContentPane().add(teamNameLabel);
		
		JTextField teamNameField = new JTextField();
		teamNameField.setHorizontalAlignment(SwingConstants.CENTER);
		teamNameField.setBounds(75, 240, 300, 50);
		frame.getContentPane().add(teamNameField);
		teamNameField.setColumns(10);
		
		
		
		
		JLabel weeksLabel = new JLabel("How many weeks will your season last?");
		weeksLabel.setHorizontalAlignment(SwingConstants.CENTER);
		weeksLabel.setBounds(75, 300, 300, 50);
		frame.getContentPane().add(weeksLabel);
		

		JLabel weeksSliderLabel = new JLabel("5");
		weeksSliderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		weeksSliderLabel.setBounds(75, 400, 300, 50);
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
		weeksSlider.setBounds(75, 350, 300, 50);
		frame.getContentPane().add(weeksSlider);
		
		
		
		
		JLabel difficultyLabel = new JLabel("At what skill level are you going to play?");
		difficultyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		difficultyLabel.setBounds(75, 460, 300, 50);
		frame.getContentPane().add(difficultyLabel);
		
		ButtonGroup difficultyButtons = new ButtonGroup();
		
		JRadioButton beginnerDifficultyButton = new JRadioButton("Beginner");
		beginnerDifficultyButton.setBounds(60, 510, 105, 50);
		frame.getContentPane().add(beginnerDifficultyButton);
		difficultyButtons.add(beginnerDifficultyButton);
		
		JRadioButton intermediateDifficultyButton = new JRadioButton("Intermediate");
		intermediateDifficultyButton.setBounds(165, 510, 120, 50);
		frame.getContentPane().add(intermediateDifficultyButton);
		difficultyButtons.add(intermediateDifficultyButton);
		
		JRadioButton advancedDifficultyButton = new JRadioButton("Advanced");
		advancedDifficultyButton.setBounds(285, 510, 105, 50);
		frame.getContentPane().add(advancedDifficultyButton);
		difficultyButtons.add(advancedDifficultyButton);
		
		
		
		JButton playerNameSubmitButton = new JButton("Submit");
		playerNameSubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String playerName = playerNameField.getText();
				boolean allValid = true;
				if (Input.validString(playerName, 3, 999)){
					Game.setPlayerName(playerName);
				} else {
					playerNameField.setText("");
					allValid = false;
				}
			}
		});
		playerNameSubmitButton.setBounds(75, 600, 300, 50);
		frame.getContentPane().add(playerNameSubmitButton);
		
		
		
		
		
		

		
		
	
	}
}
