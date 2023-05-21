
import java.awt.EventQueue;

import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
	private Integer week;
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
		VisitClubhouseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchClubScreen();
				finishedWindow();
			}
		});
		VisitClubhouseButton.setBounds(150, 130, 300, 50);
		frame.getContentPane().add(VisitClubhouseButton);
		
		JButton visitMarketButton = new JButton("Visit the Market");
		visitMarketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchMarketScreen();
				finishedWindow();
			}
		});
		visitMarketButton.setBounds(150, 190, 300, 50);
		frame.getContentPane().add(visitMarketButton);
		
		JButton visitStadiumButton = new JButton("Visit the Stadium");
		visitStadiumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!GameEnvironment.hasFullTeam()) {
					JOptionPane.showMessageDialog(null, "You need at least 7 healthy players to visit the Stadium.", "Error", 0);
					return;
				} else if (GameEnvironment.getWeeklyGamePlayed()) {
					JOptionPane.showMessageDialog(null, "You have already played your weekly match.", "Error", 0);
					return;
				}
				manager.launchStadiumScreen();
				finishedWindow();
			}
		});
		visitStadiumButton.setBounds(150, 250, 300, 50);
		frame.getContentPane().add(visitStadiumButton);
		
		JPanel panelTop = new JPanel();
		panelTop.setLayout(null);
		panelTop.setBackground(new Color(153, 193, 241));
		panelTop.setBounds(0, 0, 600, 32);
		frame.getContentPane().add(panelTop);
		
		JButton btnHelp = new JButton("?");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "From the Home Screen you can, \nvisit your Clubhouse to see your team, \nvisit the Market to find new additions for your team, \nvisit the Stadium to play against other teams, \nor choose to move on to the next week.", "Info", 1);
			}
		});
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
		if (week == GameEnvironment.getFinalWeek()){
			takeByeButton.setText("Finish Game");
		} else if (GameEnvironment.getWeeklyGamePlayed()) {
			takeByeButton.setText("Go to Next Week");
		}
		takeByeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isFinalWeek = (week == GameEnvironment.getFinalWeek());
				String textPrompt = "Would you like to ";
				if (isFinalWeek) {
					textPrompt += "finish your game";
				} else {
					textPrompt += "advance to week " + (week + 1);
				}
				if (!GameEnvironment.getWeeklyGamePlayed()) {
					textPrompt += ", without playing your weekly match";
				}
			
				if (!GameEnvironment.hasFullTeam()) {
					JOptionPane.showMessageDialog(null, "You need at least 7 healthy players to progress.", "Error", 0);
					JOptionPane.showMessageDialog(null, "If you cannot afford enough new players, you can admit defeat and Quit.", "Error", 0);
					return;
				}
				
				int goNextWeek = JOptionPane.showConfirmDialog(null, textPrompt+"?", "Confirm", 0);
				
				if (goNextWeek == 0) {
					
					if (!GameEnvironment.getWeeklyGamePlayed()) {
						GameEnvironment.getRecord()[2] += 1;
					}
					if (isFinalWeek) {
						GameEnvironment.setGameSuccess(true);
						manager.launchEndScreen();
						finishedWindow();
					} else {
						GameEnvironment.setUpWeek();
						
						week = GameEnvironment.getWeek();
						weekDisplayLabel.setText("Week: " + week);
					}
					if (week == GameEnvironment.getFinalWeek()) {
						takeByeButton.setText("Finish Game");
					} else {
						takeByeButton.setText("Take a Bye");
					}
					
					Random rand = new Random();
			        int eventProb = rand.nextInt(9);
			        int numPlayers = GameEnvironment.getPlayerTeam().size();
			        if (eventProb == 0) {
				        int statIndex = rand.nextInt(3);
				        int statInc = rand.nextInt(30, 100);
				        int[] newStat = {0, 0, 0};
				        newStat[statIndex] += statInc;
				        Item freeItem = new Item("", 0, 0, "", newStat);

				        int playerToBuff = rand.nextInt(numPlayers);
			        	GameEnvironment.getPlayerTeam().getPlayers().get(playerToBuff).addItem(freeItem);
			        	JOptionPane.showMessageDialog(null, "Random Event: " + GameEnvironment.getPlayerTeam().getPlayers().get(playerToBuff).getName() + " got a " + statInc + " point increase to their " + Purchasable.statNames[statIndex] + " Stat.", "Random Event", 1);
			        } else if (eventProb == 1) {
			        	if (numPlayers != 12) {
			        		Athlete newAthlete = Athlete.generateAthlete(9);
			        		GameEnvironment.getPlayerTeam().getPlayers().add(newAthlete);
			        		JOptionPane.showMessageDialog(null, "Random Event: " + newAthlete.getName() + " has joined your team!","Random Event", 1);
			        	}
			        } else if (eventProb == 2) {
			        	if (numPlayers != 0) {
			        		int minStamina = 100;
					        Athlete minAthlete = GameEnvironment.getPlayerTeam().getPlayers().get(0);
					        for (Athlete athlete:GameEnvironment.getPlayerTeam().getPlayers()) {
					        	int stamina = athlete.getStats()[0];
					        	if (stamina <= minStamina) {
					        		minAthlete = athlete;
					        	}
					        }
					        GameEnvironment.getPlayerTeam().getPlayers().remove(minAthlete);
					        JOptionPane.showMessageDialog(null, "Random Event: " + minAthlete.getName() + " has left your team!","Random Event", 1);
			        	}
				        
			        }
					
				}
				
			}
		});
		takeByeButton.setBounds(150, 310, 300, 50);
		frame.getContentPane().add(takeByeButton);
		
		JButton quitButton = new JButton("Quit Game");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int quitCheck = JOptionPane.showConfirmDialog(null, "Are you sure you want to Quit?", "Confirm", 0);
				if (quitCheck == 0) {
					GameEnvironment.setGameSuccess(false);
					manager.launchEndScreen();
					finishedWindow();
				}
			}
		});
		quitButton.setBounds(481, 310, 107, 50);
		frame.getContentPane().add(quitButton);
	}
	
}
