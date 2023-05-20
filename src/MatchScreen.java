
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MatchScreen {

	private JFrame frame;
	private GameManager manager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MatchScreen window = new MatchScreen();
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
	public MatchScreen() {
		initialize();
	}
	
	public MatchScreen(GameManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		manager.closeMatchScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		Match match = GameEnvironment.getMatches().get(GameEnvironment.getMatches().size() - 1);
		
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
		
		String teamName = GameEnvironment.getTeamName();
		JLabel lblHome = new JLabel(teamName + " vs. " + match.getOpposingTeam().getTeamName());
		lblHome.setBounds(12, 10, 366, 15);
		panelTop.add(lblHome);
		
		JButton btnBack = new JButton("Go Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchStadiumScreen();
				finishedWindow();
			}
		});
		btnBack.setBounds(10, 44, 117, 25);
		frame.getContentPane().add(btnBack);
		
		JLabel lblNewLabel = new JLabel("you played a match well done bozo");
		lblNewLabel.setBounds(219, 172, 239, 32);
		frame.getContentPane().add(lblNewLabel);
		
		JButton playMatch = new JButton("play match");
		playMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameEnvironment.setWeeklyGamePlayed(true);
			}
		});
		playMatch.setBounds(162, 241, 117, 29);
		frame.getContentPane().add(playMatch);
	}
}
