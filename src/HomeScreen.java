
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class HomeScreen {

	private JFrame frame;
	private GameManager manager;
	private int week;
	private String money;
	private String rating;
	private String teamName;

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
	
	public HomeScreen(GameManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		manager.closeHomeScreen(this);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		week = GameEnvironment.getWeek();
		money = Integer.toString(GameEnvironment.getMoney());
		rating = Integer.toString(GameEnvironment.getPlayerRating());
		teamName = GameEnvironment.getPlayerTeam().getTeamName();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton VisitClubhouseButton = new JButton("Visit " + teamName + "'s Clubhouse");
		VisitClubhouseButton.setBounds(150, 130, 300, 50);
		frame.getContentPane().add(VisitClubhouseButton);
		
		JButton visitMarketButton = new JButton("Visit the Market");
		visitMarketButton.setBounds(150, 190, 300, 50);
		frame.getContentPane().add(visitMarketButton);
		
		JButton visitStadiumButton = new JButton("Visit the Stadium");
		visitStadiumButton.setBounds(150, 250, 300, 50);
		frame.getContentPane().add(visitStadiumButton);
		
		JPanel panelTop = new JPanel();
		panelTop.setLayout(null);
		panelTop.setBackground(new Color(153, 193, 241));
		panelTop.setBounds(0, 0, 600, 32);
		frame.getContentPane().add(panelTop);
		
		JButton btnHelp = new JButton("?");
		btnHelp.setBounds(538, 5, 50, 25);
		panelTop.add(btnHelp);
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setBounds(12, 10, 70, 15);
		panelTop.add(lblHome);
		
		JLabel teamNameLabel = new JLabel(teamName);
		teamNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		teamNameLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		teamNameLabel.setBounds(150, 40, 300, 50);
		frame.getContentPane().add(teamNameLabel);
		
		JLabel moneyLabel = new JLabel("Money: $" + money);
		moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		moneyLabel.setBounds(150, 80, 100, 50);
		frame.getContentPane().add(moneyLabel);
		
		JLabel scoreLabel = new JLabel("Rating: " + rating);
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setBounds(250, 80, 100, 50);
		frame.getContentPane().add(scoreLabel);
		
		JLabel weekDisplayLabel = new JLabel("Week: " + week);
		weekDisplayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		weekDisplayLabel.setBounds(350, 80, 100, 50);
		frame.getContentPane().add(weekDisplayLabel);
		
		JButton takeByeButton = new JButton("Take a Bye");
		if (GameEnvironment.getWeeklyGamePlayed()) {
			takeByeButton.setText("Go to Next Week");
		}
		
		takeByeButton.setBounds(150, 310, 300, 50);
		frame.getContentPane().add(takeByeButton);
		
		VisitClubhouseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchClubScreen();
				finishedWindow();
			}
		});
		
		visitMarketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchMarketScreen();
				finishedWindow();
			}
		});
		
		visitStadiumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchStadiumScreen();
				finishedWindow();
			}
		});
		
		takeByeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (week == GameEnvironment.getFinalWeek()) {
					Integer finishGame = JOptionPane.showConfirmDialog(null, "Would you like to finish your game?", "Confirm", 0);
					if (finishGame == 0) {
						manager.launchEndScreen();
						finishedWindow();
					}
				}
				else {
					String nextWeekMessage = "Would you like to advance to week " + (week + 1);
					if (!GameEnvironment.getWeeklyGamePlayed()) {
						nextWeekMessage += ", without playing a game";
					}
					Integer goNextWeek = JOptionPane.showConfirmDialog(null, nextWeekMessage+"?", "Confirm", 0);
					if(goNextWeek == 0) {
						GameEnvironment.setUpWeek();
						if (!GameEnvironment.getWeeklyGamePlayed()) {
							GameEnvironment.getRecord()[2] += 1;
						}						
						week = GameEnvironment.getWeek();
						weekDisplayLabel.setText("Week: " + week);
						takeByeButton.setText("Take a Bye");
					}
				}
				
			}
		});
		
		
		
		
	}
}
