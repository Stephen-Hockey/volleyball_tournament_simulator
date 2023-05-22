
import java.awt.Color;
import java.awt.EventQueue;
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


public class EndScreen {

	private JFrame frame;
	private GameManager manager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EndScreen window = new EndScreen();
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
	public EndScreen() {
		initialize();
	}
	
	public EndScreen(GameManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
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
		
		JLabel lblName = new JLabel(bestAthlete.getName());
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblName.setBounds(12, 6, 289, 15);
		panelAthleteInfoBox.add(lblName);
		
		JLabel lblStamina = new JLabel("Stamina");
		lblStamina.setBounds(12, 35, 70, 15);
		panelAthleteInfoBox.add(lblStamina);
		
		JProgressBar pBarStamina = new JProgressBar();
		pBarStamina.setValue(bestAthlete.getStats()[0]);
		pBarStamina.setMaximum(99);
		pBarStamina.setBounds(86, 35, 108, 15);
		panelAthleteInfoBox.add(pBarStamina);
		
		JLabel lblOffence = new JLabel("Offence");
		lblOffence.setBounds(12, 55, 70, 15);
		panelAthleteInfoBox.add(lblOffence);
		
		JProgressBar pBarOffence = new JProgressBar();
		pBarOffence.setValue(bestAthlete.getStats()[1]);
		pBarOffence.setMaximum(99);
		pBarOffence.setBounds(86, 55, 108, 15);
		panelAthleteInfoBox.add(pBarOffence);
		
		JLabel lblDefence = new JLabel("Defence");
		lblDefence.setBounds(12, 75, 70, 15);
		panelAthleteInfoBox.add(lblDefence);
		
		
		JProgressBar pBarDefence = new JProgressBar();
		pBarDefence.setValue(bestAthlete.getStats()[2]);
		pBarDefence.setMaximum(99);
		pBarDefence.setBounds(86, 75, 108, 15);
		panelAthleteInfoBox.add(pBarDefence);
		
		JLabel descriptionLabel = new JLabel(bestAthlete.getDescription().replaceAll("\n", " "));
		descriptionLabel.setBounds(12, 95, 199, 64);
		panelAthleteInfoBox.add(descriptionLabel);
		
		
		
		JLabel lblHome = new JLabel(playerName + "'s Journey");
		lblHome.setBounds(12, 10, 366, 15);
		panelTop.add(lblHome);
		
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
		JLabel gameInfoLabel = new JLabel(gameInfoText);
		gameInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameInfoLabel.setBounds(10, 44, 205, 188);
		frame.getContentPane().add(gameInfoLabel);
		
		JButton closeGameButton = new JButton("Close Game");
		closeGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		closeGameButton.setBounds(165, 237, 117, 29);
		frame.getContentPane().add(closeGameButton);
		
		JLabel bestAthleteLabel = new JLabel("Star Athlete");
		bestAthleteLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		bestAthleteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bestAthleteLabel.setBounds(248, 44, 169, 16);
		frame.getContentPane().add(bestAthleteLabel);
		
	}
}
