package screens;

import java.awt.Color;
import java.awt.Font;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import main.Athlete;
import main.GameEnvironment;
import main.GameManager;


/**
 * The EndScreen is where the user views a summary of their season, 
 * before the program finishes<br>
 * Launched from HomeScreen<br>
 * Program ends upon closing
 * 
 * This class implements the end screen where the user views a summary of their game and 
 * their best athlete if one exists, and has the option to close the game
 *
 * @author Lachlan Stewart and Stephen Hockey
 * @version 1.1, May 2023.
 */
public class EndScreen {

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
	public EndScreen(GameManager incomingManager) {
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
	 * Runs the closeClubScreen method of its manager to close itself
	 */
	public void finishedWindow() {
		manager.closeEndScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		String teamName = GameEnvironment.getPlayerTeam().getTeamName();
		int gameDuration = GameEnvironment.getWeek();
		String playerName = GameEnvironment.getPlayerName();
		ArrayList<String> difficulties = new ArrayList<String>(Arrays.asList("Beginner", "Intermediate", "Advanced"));
		int difNum = GameEnvironment.getDifficulty();
		String difficulty = difficulties.get(difNum);
		boolean gameSuccess = GameEnvironment.getGameSuccess();
		int score = GameEnvironment.getPlayerRating();
		int money = GameEnvironment.getMoney();
		Athlete bestAthlete = GameEnvironment.getPlayerTeam().getBestAthlete();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
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
		
		JPanel panelAthleteInfoBox = new JPanel();
		panelAthleteInfoBox.setLayout(null);
		panelAthleteInfoBox.setBackground(Color.WHITE);
		panelAthleteInfoBox.setBounds(227, 67, 217, 165);
		frame.getContentPane().add(panelAthleteInfoBox);
		
		JLabel lblName = new JLabel();
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblName.setBounds(12, 6, 289, 15);
		panelAthleteInfoBox.add(lblName);
		
		JLabel lblStamina = new JLabel("Stamina");
		lblStamina.setBounds(12, 35, 70, 15);
		panelAthleteInfoBox.add(lblStamina);
		
		JProgressBar pbarStamina = new JProgressBar();
		pbarStamina.setMaximum(99);
		pbarStamina.setBounds(86, 35, 108, 15);
		panelAthleteInfoBox.add(pbarStamina);
		
		JLabel lblOffence = new JLabel("Offence");
		lblOffence.setBounds(12, 55, 70, 15);
		panelAthleteInfoBox.add(lblOffence);
		
		JProgressBar pbarOffence = new JProgressBar();
		pbarOffence.setMaximum(99);
		pbarOffence.setBounds(86, 55, 108, 15);
		panelAthleteInfoBox.add(pbarOffence);
		
		JLabel lblDefence = new JLabel("Defence");
		lblDefence.setBounds(12, 75, 70, 15);
		panelAthleteInfoBox.add(lblDefence);
		
		JProgressBar pbarDefence = new JProgressBar();
		pbarDefence.setMaximum(99);
		pbarDefence.setBounds(86, 75, 108, 15);
		panelAthleteInfoBox.add(pbarDefence);
		
		JLabel lblDescription = new JLabel();
		lblDescription.setBounds(12, 95, 199, 64);
		panelAthleteInfoBox.add(lblDescription);
		
		if (bestAthlete != null) {
			lblName.setText(bestAthlete.getName());
			pbarStamina.setValue(bestAthlete.getStats()[0]);
			pbarOffence.setValue(bestAthlete.getStats()[1]);
			pbarDefence.setValue(bestAthlete.getStats()[2]);
			lblDescription.setText(bestAthlete.getDescription().replaceAll("\n", " "));
		} else {
			lblName.setText("No Athlete");
			
		}
		
		JLabel lblTopText = new JLabel(playerName + "'s Journey");
		lblTopText.setBounds(12, 10, 366, 15);
		panelTop.add(lblTopText);
		
		String introText = "Well played ";
		if (!gameSuccess) {
			introText = "Unlucky but well played ";
		}
		introText += playerName + ",";
		String durText =  "you lasted " + gameDuration + " week";
		if (gameDuration != 1) {
			durText += "s,";
		} else {
			durText += ",";
		}
		String manText = "as manager of your team,";
		String teamText = "'" + teamName + "',";
		String difText = "on " + difficulty + " difficulty,";
		String scoreText = "with a score of " + score + ",";
		String moneyText = " and $" + money + ".";
		
		String gameInfoText = "<html>" + introText + "<br>" + durText + "<br>" + manText + "<br>" + teamText + "<br>" + difText + "<br>" + scoreText + "<br>" + moneyText + "</html>";
		JLabel lblGameInfo = new JLabel(gameInfoText);
		lblGameInfo.setVerticalAlignment(SwingConstants.TOP);
		lblGameInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameInfo.setBounds(10, 83, 205, 149);
		frame.getContentPane().add(lblGameInfo);
		
		JButton btnCloseGame = new JButton("Close Game");
		btnCloseGame.setBounds(165, 237, 117, 29);
		frame.getContentPane().add(btnCloseGame);
		
		JLabel lblBestAthlete = new JLabel("Star Athlete");
		lblBestAthlete.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblBestAthlete.setHorizontalAlignment(SwingConstants.CENTER);
		lblBestAthlete.setBounds(248, 44, 169, 16);
		frame.getContentPane().add(lblBestAthlete);
		
		JLabel lblGameSummary = new JLabel("Game Summary");
		lblGameSummary.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameSummary.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblGameSummary.setBounds(23, 45, 169, 16);
		frame.getContentPane().add(lblGameSummary);

		//Events
		
		btnCloseGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
	}
}
