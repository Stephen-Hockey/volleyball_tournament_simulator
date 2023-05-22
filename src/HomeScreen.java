package screens;

import java.util.Random;
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

import main.Athlete;
import main.GameEnvironment;
import main.GameManager;
import main.Item;
import main.Purchasable;

/**
 * The HomeScreen is where the user can choose between visiting the club house, 
 * visiting the market, visiting the stadium, moving to the next week, and quitting<br>
 * Launched from DraftScreen, or ClubScreen, or MarketScreen, or StadiumScreen, or MatchScreen<br>
 * Launches ClubScreen, or MarketScreen, or StadiumScreen, or EndScreen
 * 
 * @author Lachlan Stewart and Stephen Hockey
 * @version 1.1, May 2023.
 */
public class HomeScreen {

	/**
	 * The frame on which elements are placed
	 */
	private JFrame frame;
	
	/**
	 * The manager of the current instance of club screen
	 */
	private GameManager manager;
	
	/**
	 * The current in game week
	 */
	private Integer week;

	/**
	 * Create the window with a GameManager object to oversee closing and launching the window
	 * @param incomingManager the GameManager object
	 */
	public HomeScreen(GameManager incomingManager) {
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
	 * Runs the closeHomeScreen method of its manager to close itself
	 */
	public void finishedWindow() {
		manager.closeHomeScreen(this);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		week = GameEnvironment.getWeek();
		String money = Integer.toString(GameEnvironment.getMoney());
		String rating = Integer.toString(GameEnvironment.getPlayerRating());
		String teamName = GameEnvironment.getPlayerTeam().getTeamName();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnVisitClubhouse = new JButton("Visit " + teamName + "'s Clubhouse");
		btnVisitClubhouse.setBounds(150, 130, 300, 50);
		frame.getContentPane().add(btnVisitClubhouse);
		
		JButton btnVisitMarket = new JButton("Visit the Market");
		btnVisitMarket.setBounds(150, 190, 300, 50);
		frame.getContentPane().add(btnVisitMarket);
		
		JButton btnVisitStadium = new JButton("Visit the Stadium");
		btnVisitStadium.setBounds(150, 250, 300, 50);
		frame.getContentPane().add(btnVisitStadium);
		
		JPanel panelTop = new JPanel();
		panelTop.setLayout(null);
		panelTop.setBackground(new Color(153, 193, 241));
		panelTop.setBounds(0, 0, 600, 32);
		frame.getContentPane().add(panelTop);
		
		JButton btnHelp = new JButton("?");
		btnHelp.setBounds(538, 5, 50, 25);
		panelTop.add(btnHelp);
		
		JLabel lblTopText = new JLabel("Home");
		lblTopText.setBounds(12, 10, 70, 15);
		panelTop.add(lblTopText);
		
		JLabel lblTeamName = new JLabel(teamName);
		lblTeamName.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamName.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblTeamName.setBounds(150, 40, 300, 50);
		frame.getContentPane().add(lblTeamName);
		
		JLabel lblMoney = new JLabel("Money: $" + money);
		lblMoney.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoney.setBounds(150, 80, 100, 50);
		frame.getContentPane().add(lblMoney);
		
		JLabel lblPlayerRating = new JLabel("Rating: " + rating);
		lblPlayerRating.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerRating.setBounds(250, 80, 100, 50);
		frame.getContentPane().add(lblPlayerRating);
		
		JLabel lblWeek = new JLabel("Week: " + week);
		lblWeek.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeek.setBounds(350, 80, 100, 50);
		frame.getContentPane().add(lblWeek);
		
		JButton btnTakeBye = new JButton("Take a Bye");
		if (week == GameEnvironment.getFinalWeek()){
			btnTakeBye.setText("Finish Game");
		} else if (GameEnvironment.getWeeklyGamePlayed()) {
			btnTakeBye.setText("Go to Next Week");
		}
		btnTakeBye.setBounds(150, 310, 300, 50);
		frame.getContentPane().add(btnTakeBye);
		
		JButton btnQuit = new JButton("Quit Game");
		btnQuit.setBounds(481, 310, 107, 50);
		frame.getContentPane().add(btnQuit);
		
		//Events

		btnVisitClubhouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchClubScreen();
				finishedWindow();
			}
		});

		btnVisitMarket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchMarketScreen();
				finishedWindow();
			}
		});

		btnVisitStadium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!GameEnvironment.hasHealthyStarters()) {
					JOptionPane.showMessageDialog(null, "You need at least 7 healthy players in your starting lineup to visit the Stadium.", "Error", 0);
					return;
				} else if (GameEnvironment.getWeeklyGamePlayed()) {
					JOptionPane.showMessageDialog(null, "You have already played your weekly match.", "Error", 0);
					return;
				}
				manager.launchStadiumScreen();
				finishedWindow();
			}
		});
		
		btnTakeBye.addActionListener(new ActionListener() {
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
						lblWeek.setText("Week: " + week);
					}
					if (week == GameEnvironment.getFinalWeek()) {
						btnTakeBye.setText("Finish Game");
					} else {
						btnTakeBye.setText("Take a Bye");
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
			        	JOptionPane.showMessageDialog(null, "Random Event: " + GameEnvironment.getPlayerTeam().getPlayers().get(playerToBuff) + " got a " + statInc + " point increase to their " + Purchasable.statNames[statIndex] + " Stat.", "Random Event", 1);
			        } else if (eventProb == 1) {
			        	if (numPlayers != 12) {
			        		Athlete newAthlete = Athlete.generateAthlete(9);
			        		GameEnvironment.getPlayerTeam().getPlayers().add(newAthlete);
			        		JOptionPane.showMessageDialog(null, "Random Event: " + newAthlete + " has joined your team!","Random Event", 1);
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
					        JOptionPane.showMessageDialog(null, "Random Event: " + minAthlete + " has left your team!","Random Event", 1);
			        	}
				        
			        }
					
				}
				
			}
		});
		
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int quitCheck = JOptionPane.showConfirmDialog(null, "Are you sure you want to Quit?", "Confirm", 0);
				if (quitCheck == 0) {
					GameEnvironment.setGameSuccess(false);
					manager.launchEndScreen();
					finishedWindow();
				}
			}
		});

		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "From the Home Screen you can, \nvisit your Clubhouse to see your team, \nvisit the Market to find new additions for your team, \nvisit the Stadium to play against other teams, \nor choose to move on to the next week.", "Info", 1);
			}
		});
	}
}
