
import java.awt.Color;
import java.awt.EventQueue;

import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		
		JLabel lblHome = new JLabel(playerName + "'s Journey");
		lblHome.setBounds(12, 10, 366, 15);
		panelTop.add(lblHome);
		
		String introText = "Well played ";
		if (!gameSuccess) {
			introText = "Unlucky but well played ";
		}
		introText += playerName + ",";
		String durText =  "you managed to last " + gameDuration + " week";
		if (gameDuration != 1) {
			durText += "s,";
		} else {
			durText += ",";
		}
		String manText = "as manager of you team, '" + teamName + "',";
		String difText = "on " + difficulty + " difficulty,";
		String scoreText = "with a score of " + score;
		
		String gameInfoText = "<html>" + introText + "<br>" + durText + "<br>" + manText + "<br>" + difText + "<br>" + scoreText +"</html>";
		JLabel gameInfoLabel = new JLabel(gameInfoText);
		gameInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameInfoLabel.setBounds(74, 73, 287, 136);
		frame.getContentPane().add(gameInfoLabel);
		
	}

}
