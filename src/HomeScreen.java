package senggui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class HomeScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeScreen window = new HomeScreen();
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
	public HomeScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton VisitClubhouseButton = new JButton("Visit teamname's clubhouse");
		VisitClubhouseButton.setBounds(150, 130, 300, 50);
		frame.getContentPane().add(VisitClubhouseButton);
		
		JButton visitMarketButton = new JButton("Visit the Market");
		visitMarketButton.setBounds(150, 190, 300, 50);
		frame.getContentPane().add(visitMarketButton);
		
		JButton visitStadiumButton = new JButton("Visit the Stadium");
		visitStadiumButton.setBounds(150, 250, 300, 50);
		frame.getContentPane().add(visitStadiumButton);
		
		JButton takeByeButton = new JButton("Take a Bye ");
		takeByeButton.setBounds(150, 310, 300, 50);
		frame.getContentPane().add(takeByeButton);
		
		JLabel weekDisplayLabel = new JLabel("num of weeks");
		weekDisplayLabel.setBounds(456, 29, 100, 50);
		frame.getContentPane().add(weekDisplayLabel);
		
		JLabel teamNameLabel = new JLabel("the teams name");
		teamNameLabel.setBounds(150, 29, 200, 50);
		frame.getContentPane().add(teamNameLabel);
		
		JLabel moneyLabel = new JLabel("players money");
		moneyLabel.setBounds(438, 93, 108, 16);
		frame.getContentPane().add(moneyLabel);
		
		JLabel scoreLabel = new JLabel("players score");
		scoreLabel.setBounds(438, 129, 108, 16);
		frame.getContentPane().add(scoreLabel);
	}
}
