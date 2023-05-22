package screens;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionListener;

import main.GameEnvironment;
import main.GameManager;
import main.Match;
import main.Team;

import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;
/**
 * The MatchScreen is where the user can view and interact with a volleyball match<br>
 * Launched from StadiumScreen<br>
 * Launches HomeScreen upon closing
 *
 * @author Lachlan Stewart and Stephen Hockey
 * @version 1.1, May 2023.
 */
public class MatchScreen {

	/**
	 * The frame on which elements are placed
	 */
	private JFrame frame;
	
	/**
	 * The manager of the current instance of draft screen
	 */
	private GameManager manager;
	
	/**
	 * The match related to this MatchScreen
	 */
	private Match match;
	
	/**
	 * Create the window with a GameManager object to oversee closing and launching the window
	 * @param incomingManager the GameManager object
	 */
	public MatchScreen(GameManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	/**
	 * Closes the window
	 */
	public void closeWindow() {
		frame.dispose();
	}
	
	/**
	 * Runs the closeMatchScreen method of its manager to close itself
	 */
	public void finishedWindow() {
		manager.closeMatchScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		match = GameEnvironment.getMatches().get(GameEnvironment.getMatches().size() - 1);
		
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
		
		JLabel lblTopText = new JLabel("Match 1: " + GameEnvironment.getPlayerTeam().getTeamName() + " vs " + match.getOpposingTeam().getTeamName());
		lblTopText.setBounds(12, 10, 366, 15);
		panelTop.add(lblTopText);
		
		
		JLabel lblScore = new JLabel(match.getScoreString(), SwingConstants.CENTER);
		lblScore.setFont(new Font("Dialog", Font.BOLD, 20));
		lblScore.setBounds(225, 44, 150, 33);
		frame.getContentPane().add(lblScore);
		
		JLabel lblSetScore = new JLabel(match.getSetScoreString(), SwingConstants.CENTER);
		lblSetScore.setFont(new Font("Dialog", Font.BOLD, 12));
		lblSetScore.setBounds(225, 75, 150, 13);
		frame.getContentPane().add(lblSetScore);
		
		JLabel lblVersus = new JLabel("is facing off against", SwingConstants.CENTER);
		lblVersus.setBounds(200, 100, 200, 15);
		frame.getContentPane().add(lblVersus);
		
		JLabel lblStamina = new JLabel("Stamina", SwingConstants.CENTER);
		lblStamina.setBounds(265, 125, 70, 15);
		frame.getContentPane().add(lblStamina);
		
		JLabel lblOffence = new JLabel("Offence", SwingConstants.CENTER);
		lblOffence.setBounds(265, 145, 70, 15);
		frame.getContentPane().add(lblOffence);
		
		JLabel lblDefence = new JLabel("Defence", SwingConstants.CENTER);
		lblDefence.setBounds(265, 165, 70, 15);
		frame.getContentPane().add(lblDefence);
		
		JPanel barStaminaL = new JPanel();
		barStaminaL.setBackground(Color.BLACK);
		barStaminaL.setBounds(60, 125, 200, 14);
		frame.getContentPane().add(barStaminaL);
		
		JPanel barOffenceL = new JPanel();
		barOffenceL.setBounds(60, 145, 200, 14);
		frame.getContentPane().add(barOffenceL);
		
		JPanel barDefenceL = new JPanel();
		barDefenceL.setBounds(60, 165, 200, 14);
		frame.getContentPane().add(barDefenceL);
		
		JPanel barStaminaR = new JPanel();
		barStaminaR.setBackground(Color.BLACK);
		barStaminaR.setBounds(340, 125, 200, 14);
		frame.getContentPane().add(barStaminaR);
		
		JPanel barOffenceR = new JPanel();
		barOffenceR.setBounds(340, 145, 200, 14);
		frame.getContentPane().add(barOffenceR);
		
		JPanel barDefenceR = new JPanel();
		barDefenceR.setBounds(340, 165, 200, 14);
		frame.getContentPane().add(barDefenceR);
		
		barStaminaL.setBackground(Color.GREEN);
		barOffenceL.setBackground(Color.GREEN);
		barDefenceL.setBackground(Color.GREEN);
		barStaminaR.setBackground(Color.GREEN);
		barOffenceR.setBackground(Color.GREEN);
		barDefenceR.setBackground(Color.GREEN);
		barStaminaL.setBounds(260 - GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[0]*2, 125, GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[0]*2, 14);
		barStaminaR.setBounds(340, 125, match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[0]*2, 14);
		if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[0] > match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[0]) {
			barStaminaR.setBackground(Color.RED);
		} else if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[0] < match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[0]) {
			barStaminaL.setBackground(Color.RED);
		}
		barOffenceL.setBounds(260 - GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[1]*2, 145, GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[1]*2, 14);
		barOffenceR.setBounds(340, 145, match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[1]*2, 14);
		if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[1] > match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[1]) {
			barOffenceR.setBackground(Color.RED);
		} else if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[1] < match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[1]) {
			barOffenceL.setBackground(Color.RED);
		}
		barDefenceL.setBounds(260 - GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[2]*2, 165, GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[2]*2, 14);
		barDefenceR.setBounds(340, 165, match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[2]*2, 14);
		if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[2] > match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[2]) {
			barDefenceR.setBackground(Color.RED);
		} else if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[2] < match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[2]) {
			barDefenceL.setBackground(Color.RED);
		}
		
		JButton btnNextPoint = new JButton("Next Point");
		btnNextPoint.setBounds(174, 341, 120, 32);
		frame.getContentPane().add(btnNextPoint);
		
		JButton btnSkipSet = new JButton("Skip Set");
		btnSkipSet.setBounds(240, 385, 120, 25);
		frame.getContentPane().add(btnSkipSet);
		
		JButton btnMakeSub = new JButton("Make A Sub");
		btnMakeSub.setBounds(306, 341, 120, 32);
		frame.getContentPane().add(btnMakeSub);
		
		JList<String> listUserStarters = new JList<String>();
		listUserStarters.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listUserStarters.setFont(new Font("FreeMono", Font.BOLD, 12));
		DefaultListModel<String> modelUserStarters = new DefaultListModel<String>();
		listUserStarters.setModel(modelUserStarters);
		for (int i = 1; i < 7; i++) {
			modelUserStarters.addElement(String.format("%-12s%s", "(" + Team.POSITION_STRINGS[i] + ")", GameEnvironment.getPlayerTeam().get(i)));
		}
		listUserStarters.setEnabled(false);
		listUserStarters.setBounds(12, 204, 248, 116);
		frame.getContentPane().add(listUserStarters);
		
		JList<String> listOpposingStarters = new JList<String>();
		listOpposingStarters.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listOpposingStarters.setFont(new Font("FreeMono", Font.BOLD, 12));
		DefaultListModel<String> modelOpposingStarters = new DefaultListModel<String>();
		listOpposingStarters.setModel(modelOpposingStarters);
		for (int i = 1; i < 7; i++) {
			modelOpposingStarters.addElement(String.format("%-12s%s", "(" + Team.POSITION_STRINGS[i] + ")", match.getOpposingTeam().get(i)));
		}
		listOpposingStarters.setEnabled(false);
		listOpposingStarters.setBounds(340, 204, 248, 116);
		frame.getContentPane().add(listOpposingStarters);
		
		JList<String> listUserReserves = new JList<String>();
		listUserReserves.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listUserReserves.setFont(new Font("FreeMono", Font.BOLD, 12));
		DefaultListModel<String> modelReserves = new DefaultListModel<String>();
		listUserReserves.setModel(modelReserves);
		for (int i = 7; i < GameEnvironment.getPlayerTeam().size(); i++) {
			if (GameEnvironment.getPlayerTeam().get(i).getStats()[0] == 0) {
				modelReserves.addElement("(INJ) " + GameEnvironment.getPlayerTeam().get(i));
			} else {
				modelReserves.addElement(GameEnvironment.getPlayerTeam().get(i).getName());
			}
		}
		listUserReserves.setEnabled(false);
		listUserReserves.setBounds(12, 332, 150, 80);
		frame.getContentPane().add(listUserReserves);
		
		JList<String> listOpposingReserves = new JList<String>();
		listOpposingReserves.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listOpposingReserves.setFont(new Font("FreeMono", Font.BOLD, 12));
		DefaultListModel<String> modelOpposingReserves = new DefaultListModel<String>();
		listOpposingReserves.setModel(modelOpposingReserves);
		for(int i = 7; i < match.getOpposingTeam().size(); i++) {
			modelOpposingReserves.addElement(match.getOpposingTeam().get(i).getName());
		}
		listOpposingReserves.setEnabled(false);
		listOpposingReserves.setBounds(438, 330, 150, 80);
		frame.getContentPane().add(listOpposingReserves);
		
		JLabel lblAthleteL = new JLabel(GameEnvironment.getPlayerTeam().get(0).getName(), SwingConstants.RIGHT);
		lblAthleteL.setBounds(25, 100, 200, 15);
		frame.getContentPane().add(lblAthleteL);
		
		JLabel lblAthleteR = new JLabel(match.getOpposingTeam().get(0).getName(), SwingConstants.LEFT);
		lblAthleteR.setBounds(375, 100, 200, 15);
		frame.getContentPane().add(lblAthleteR);
		
		JLabel lblUserTeamList = new JLabel(GameEnvironment.getPlayerTeam().getTeamName());
		lblUserTeamList.setBounds(12, 185, 248, 15);
		frame.getContentPane().add(lblUserTeamList);
		
		JLabel lblOpposingTeamList = new JLabel(match.getOpposingTeam().getTeamName(), SwingConstants.RIGHT);
		lblOpposingTeamList.setBounds(340, 185, 248, 15);
		frame.getContentPane().add(lblOpposingTeamList);
			
		btnNextPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!match.playPoint()) {
					lblScore.setText(match.getScoreString());
					
					String injuryMessageString = GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()) 
							+ " got injured facing off against " + match.getOpposingTeam().get(match.getFaceoffIndex())
							+ "\nYou have to sub a new player on!";
					JOptionPane.showMessageDialog(null, injuryMessageString, "Oh no!", 0);
					
					boolean tooInjured = true;
					for (int i = 7; i < GameEnvironment.getPlayerTeam().size(); i++) {
						if (GameEnvironment.getPlayerTeam().get(i).getStats()[0] != 0) {
							tooInjured = false;
						}
					}
					
			        if (tooInjured) {
			        	injuryMessageString = "Sorry, you cannot sub "
			                            + GameEnvironment.getPlayerTeam().getPlayers().get(match.getFaceoffIndex())
			                            + " as you don't have any healthy reserves."
			                            + "\nYou lose this match.";
			        	JOptionPane.showMessageDialog(null, injuryMessageString, "Oh no!", 0);
			        	GameEnvironment.getRecord()[1] += 1;
			        	GameEnvironment.setWeeklyGamePlayed(true);
						manager.launchHomeScreen();
						finishedWindow();
						return;
			        }
			        
			        btnNextPoint.setEnabled(false);
			        btnSkipSet.setEnabled(false);
			        btnMakeSub.setEnabled(false);

					listUserReserves.setEnabled(true);
				}
				
				if (match.checkIfSetOver()) {
					if ((match.getSetScore()[0] == 3)||(match.getSetScore()[1] == 3)) {
						lblSetScore.setText(match.getSetScoreString());
						String matchOverMessage;
						if (match.matchWon()) {
							GameEnvironment.getRecord()[0] += 1;
							GameEnvironment.setMoney(GameEnvironment.getMoney() + match.getMoneyWon());
							GameEnvironment.setPlayerRating(GameEnvironment.getPlayerRating() + match.getMatchRating());
							matchOverMessage = "Yay! You won the match!"
									+ "\nYou get $" + match.getMoneyWon()
									+ ",\nand +" + match.getMatchRating() + " to your rating!";
						} else {
							GameEnvironment.getRecord()[1] += 1;
							matchOverMessage = "Darn. You lost the match."
									+ "\nYou get no money or rating points."
									+ "\nBetter luck next time!";
						}
						GameEnvironment.setWeeklyGamePlayed(true);
						JOptionPane.showMessageDialog(null, matchOverMessage, "Match Over", 1);
						manager.launchHomeScreen();
						finishedWindow();
						return;
					}
				}
				
				lblAthleteL.setText(GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getName());
				lblAthleteR.setText(match.getOpposingTeam().get(match.getFaceoffIndex()).getName());
				
				barStaminaL.setBackground(Color.GREEN);
				barOffenceL.setBackground(Color.GREEN);
				barDefenceL.setBackground(Color.GREEN);
				barStaminaR.setBackground(Color.GREEN);
				barOffenceR.setBackground(Color.GREEN);
				barDefenceR.setBackground(Color.GREEN);
				barStaminaL.setBounds(260 - match.getMatchStaminas().get(match.getFaceoffIndex())*2, 125, match.getMatchStaminas().get(match.getFaceoffIndex())*2, 14);
				barStaminaR.setBounds(340, 125, match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[0]*2, 14);
				if (match.getMatchStaminas().get(match.getFaceoffIndex()) > match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[0]) {
					barStaminaR.setBackground(Color.RED);
				} else if (match.getMatchStaminas().get(match.getFaceoffIndex()) < match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[0]) {
					barStaminaL.setBackground(Color.RED);
				}
				barOffenceL.setBounds(260 - GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[1]*2, 145, GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[1]*2, 14);
				barOffenceR.setBounds(340, 145, match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[1]*2, 14);
				if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[1] > match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[1]) {
					barOffenceR.setBackground(Color.RED);
				} else if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[1] < match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[1]) {
					barOffenceL.setBackground(Color.RED);
				}
				barDefenceL.setBounds(260 - GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[2]*2, 165, GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[2]*2, 14);
				barDefenceR.setBounds(340, 165, match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[2]*2, 14);
				if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[2] > match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[2]) {
					barDefenceR.setBackground(Color.RED);
				} else if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[2] < match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[2]) {
					barDefenceL.setBackground(Color.RED);
				}

				
				modelUserStarters.clear();
				for (int i = 1; i < 7; i++) {
					modelUserStarters.addElement(String.format("%-12s%s", "(" + Team.POSITION_STRINGS[(match.getFaceoffIndex() + i) % 7] + ")", GameEnvironment.getPlayerTeam().get((match.getFaceoffIndex() + i) % 7)));
				}
				modelOpposingStarters.clear();
				for (int i = 1; i < 7; i++) {
					modelOpposingStarters.addElement(String.format("%-12s%s", "(" + Team.POSITION_STRINGS[(match.getFaceoffIndex() + i) % 7] + ")", match.getOpposingTeam().get((match.getFaceoffIndex() + i) % 7)));
				}
				modelReserves.clear();
				for (int i = 7; i < GameEnvironment.getPlayerTeam().size(); i++) {
					if (GameEnvironment.getPlayerTeam().get(i).getStats()[0] == 0) {
						modelReserves.addElement("(INJ) " + GameEnvironment.getPlayerTeam().get(i));
					} else {
						modelReserves.addElement(GameEnvironment.getPlayerTeam().get(i).getName());
					}
				}
				lblScore.setText(match.getScoreString());
				lblSetScore.setText(match.getSetScoreString());
			}
		});

		btnSkipSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean injuryOccurred = false;
				while ((!match.checkIfSetOver()) && (!injuryOccurred)) {
					if (!match.playPoint()) {
						lblScore.setText(match.getScoreString());
					
						injuryOccurred = true;
						String injuryMessageString = GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex())
								+ " got injured facing off against " + match.getOpposingTeam().get(match.getFaceoffIndex()) 
								+ "\nYou have to sub a new player on!";
						JOptionPane.showMessageDialog(null, injuryMessageString, "Oh no!", 0);
						
						boolean tooInjured = true;
						for (int i = 7; i < GameEnvironment.getPlayerTeam().size(); i++) {
							if (GameEnvironment.getPlayerTeam().get(i).getStats()[0] != 0) {
								tooInjured = false;
							}
						}
						
				        if (tooInjured) {
				        	injuryMessageString = "Sorry, you cannot sub "
				                            + GameEnvironment.getPlayerTeam().getPlayers().get(match.getFaceoffIndex())
				                            + " as you don't have any healthy reserves."
				                            + "\nYou lose this match.";
				        	JOptionPane.showMessageDialog(null, injuryMessageString, "Oh no!", 0);
				        	GameEnvironment.getRecord()[1] += 1;
				        	GameEnvironment.setWeeklyGamePlayed(true);
							manager.launchHomeScreen();
							finishedWindow();
							return;
				        }
				        
				        btnNextPoint.setEnabled(false);
				        btnSkipSet.setEnabled(false);
				        btnMakeSub.setEnabled(false);

						listUserReserves.setEnabled(true);
					}
				}
				if ((match.getSetScore()[0] == 3)||(match.getSetScore()[1] == 3)) {
					lblSetScore.setText(match.getSetScoreString());
					String matchOverMessage;
					if (match.matchWon()) {
						GameEnvironment.getRecord()[0] += 1;
						GameEnvironment.setMoney(GameEnvironment.getMoney() + match.getMoneyWon());
						GameEnvironment.setPlayerRating(GameEnvironment.getPlayerRating() + match.getMatchRating());
						matchOverMessage = "Yay! You won the match!"
								+ "\nYou get $" + match.getMoneyWon()
								+ ",\nand +" + match.getMatchRating() + " to your rating!";
					} else {
						GameEnvironment.getRecord()[1] += 1;
						matchOverMessage = "Darn. You lost the match."
								+ "\nYou get no money or rating points."
								+ "\nBetter luck next time!";
					}
					GameEnvironment.setWeeklyGamePlayed(true);
					JOptionPane.showMessageDialog(null, matchOverMessage, "Match Over", 1);
					manager.launchHomeScreen();
					finishedWindow();
					return;
				}
				lblAthleteL.setText(GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getName());
				lblAthleteR.setText(match.getOpposingTeam().get(match.getFaceoffIndex()).getName());
				
				barStaminaL.setBackground(Color.GREEN);
				barOffenceL.setBackground(Color.GREEN);
				barDefenceL.setBackground(Color.GREEN);
				barStaminaR.setBackground(Color.GREEN);
				barOffenceR.setBackground(Color.GREEN);
				barDefenceR.setBackground(Color.GREEN);
				barStaminaL.setBounds(260 - GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[0]*2, 125, GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[0]*2, 14);
				barStaminaR.setBounds(340, 125, match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[0]*2, 14);
				if (match.getMatchStaminas().get(match.getFaceoffIndex()) > match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[0]) {
					barStaminaR.setBackground(Color.RED);
				} else if (match.getMatchStaminas().get(match.getFaceoffIndex()) < match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[0]) {
					barStaminaL.setBackground(Color.RED);
				}
				barOffenceL.setBounds(260 - GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[1]*2, 145, GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[1]*2, 14);
				barOffenceR.setBounds(340, 145, match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[1]*2, 14);
				if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[1] > match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[1]) {
					barOffenceR.setBackground(Color.RED);
				} else if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[1] < match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[1]) {
					barOffenceL.setBackground(Color.RED);
				}
				barDefenceL.setBounds(260 - GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[2]*2, 165, GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[2]*2, 14);
				barDefenceR.setBounds(340, 165, match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[2]*2, 14);
				if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[2] > match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[2]) {
					barDefenceR.setBackground(Color.RED);
				} else if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[2] < match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[2]) {
					barDefenceL.setBackground(Color.RED);
				}
				
				modelUserStarters.clear();
				for (int i = 1; i < 7; i++) {
					modelUserStarters.addElement(String.format("%-12s%s", "(" + Team.POSITION_STRINGS[(match.getFaceoffIndex() + i) % 7] + ")", GameEnvironment.getPlayerTeam().get((match.getFaceoffIndex() + i) % 7)));
				}
				modelOpposingStarters.clear();
				for (int i = 1; i < 7; i++) {
					modelOpposingStarters.addElement(String.format("%-12s%s", "(" + Team.POSITION_STRINGS[(match.getFaceoffIndex() + i) % 7] + ")", match.getOpposingTeam().get((match.getFaceoffIndex() + i) % 7)));
				}
				modelReserves.clear();
				for (int i = 7; i < GameEnvironment.getPlayerTeam().size(); i++) {
					if (GameEnvironment.getPlayerTeam().get(i).getStats()[0] == 0) {
						modelReserves.addElement("(INJ) " + GameEnvironment.getPlayerTeam().get(i));
					} else {
						modelReserves.addElement(GameEnvironment.getPlayerTeam().get(i).getName());
					}
				}
				lblScore.setText(match.getScoreString());
				lblSetScore.setText(match.getSetScoreString());
			}
		});
		
		listUserReserves.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (listUserReserves.isSelectionEmpty()) {
					return;
				}

				if (GameEnvironment.getPlayerTeam().get(7 + listUserReserves.getSelectedIndex()).getStats()[0] == 0) {
					JOptionPane.showMessageDialog(null, "This reserve is injured!", "Error", 0);
					listUserReserves.clearSelection();
					return;
				}
				
				if ((listUserStarters.isEnabled()) && (!listUserStarters.isSelectionEmpty())) {
					//normal sub
					GameEnvironment.getPlayerTeam().swap(listUserStarters.getSelectedIndex(), 7 + listUserReserves.getSelectedIndex());
					
				} else if (!listUserStarters.isEnabled()){
					//injury-forced sub
					GameEnvironment.getPlayerTeam().swap(match.getFaceoffIndex(), 7 + listUserReserves.getSelectedIndex());
					
					listUserReserves.setEnabled(false);
					
					btnNextPoint.setEnabled(true);
			        btnSkipSet.setEnabled(true);
			        btnMakeSub.setEnabled(true);
				} else {
					return;
				}
				lblAthleteL.setText(GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getName());				
				match.getMatchStaminas().set(match.getFaceoffIndex(), GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[0]);
				barStaminaL.setBackground(Color.GREEN);
				barStaminaR.setBackground(Color.GREEN);
				barOffenceL.setBackground(Color.GREEN);
				barOffenceR.setBackground(Color.GREEN);
				barDefenceL.setBackground(Color.GREEN);
				barDefenceR.setBackground(Color.GREEN);
				barStaminaL.setBounds(260 - match.getMatchStaminas().get(match.getFaceoffIndex())*2, 125, match.getMatchStaminas().get(match.getFaceoffIndex())*2, 14);
				if (match.getMatchStaminas().get(match.getFaceoffIndex()) > match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[0]) {
					barStaminaR.setBackground(Color.RED);
				} else if (match.getMatchStaminas().get(match.getFaceoffIndex()) < match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[0]) {
					barStaminaL.setBackground(Color.RED);
				}
				barOffenceL.setBounds(260 - GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[1]*2, 145, GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[1]*2, 14);
				if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[1] > match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[1]) {
					barOffenceR.setBackground(Color.RED);
				} else if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[1] < match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[1]) {
					barOffenceL.setBackground(Color.RED);
				}
				barDefenceL.setBounds(260 - GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[2]*2, 165, GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[2]*2, 14);
				if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[2] > match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[2]) {
					barDefenceR.setBackground(Color.RED);
				} else if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[2] < match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[2]) {
					barDefenceL.setBackground(Color.RED);
				}
				modelUserStarters.clear();
				for (int i = 0; i < 7; i++) {
					modelUserStarters.addElement(String.format("%-12s%s", "(" + Team.POSITION_STRINGS[(match.getFaceoffIndex() + i) % 7] + ")", GameEnvironment.getPlayerTeam().get((match.getFaceoffIndex() + i) % 7)));
				}
				modelReserves.clear();
				for (int i = 7; i < GameEnvironment.getPlayerTeam().size(); i++) {
					if (GameEnvironment.getPlayerTeam().get(i).getStats()[0] == 0) {
						modelReserves.addElement("(INJ) " + GameEnvironment.getPlayerTeam().get(i));
					} else {
						modelReserves.addElement(GameEnvironment.getPlayerTeam().get(i).getName());
					}
				}				
				listUserStarters.clearSelection();
				listUserReserves.clearSelection();
			}
		});
		
		listUserStarters.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (listUserStarters.isSelectionEmpty()) {
					return;
				}
				
				if (!listUserReserves.isSelectionEmpty()) {
					GameEnvironment.getPlayerTeam().swap(listUserStarters.getSelectedIndex(), 7 + listUserReserves.getSelectedIndex());
					
					lblAthleteL.setText(GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getName());				
					match.getMatchStaminas().set(match.getFaceoffIndex(), GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[0]);
					barStaminaL.setBackground(Color.GREEN);
					barStaminaR.setBackground(Color.GREEN);
					barOffenceL.setBackground(Color.GREEN);
					barOffenceR.setBackground(Color.GREEN);
					barDefenceL.setBackground(Color.GREEN);
					barDefenceR.setBackground(Color.GREEN);
					barStaminaL.setBounds(260 - match.getMatchStaminas().get(match.getFaceoffIndex())*2, 125, match.getMatchStaminas().get(match.getFaceoffIndex())*2, 14);
					if (match.getMatchStaminas().get(match.getFaceoffIndex()) > match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[0]) {
						barStaminaR.setBackground(Color.RED);
					} else if (match.getMatchStaminas().get(match.getFaceoffIndex()) < match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[0]) {
						barStaminaL.setBackground(Color.RED);
					}
					barOffenceL.setBounds(260 - GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[1]*2, 145, GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[1]*2, 14);
					if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[1] > match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[1]) {
						barOffenceR.setBackground(Color.RED);
					} else if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[1] < match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[1]) {
						barOffenceL.setBackground(Color.RED);
					}
					barDefenceL.setBounds(260 - GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[2]*2, 165, GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[2]*2, 14);
					if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[2] > match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[2]) {
						barDefenceR.setBackground(Color.RED);
					} else if (GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getStats()[2] < match.getOpposingTeam().get(match.getFaceoffIndex()).getStats()[2]) {
						barDefenceL.setBackground(Color.RED);
					}
					modelUserStarters.clear();
					for (int i = 0; i < 7; i++) {
						modelUserStarters.addElement(String.format("%-12s%s", "(" + Team.POSITION_STRINGS[(match.getFaceoffIndex() + i) % 7] + ")", GameEnvironment.getPlayerTeam().get((match.getFaceoffIndex() + i) % 7)));
					}
					modelReserves.clear();
					for (int i = 7; i < GameEnvironment.getPlayerTeam().size(); i++) {
						if (GameEnvironment.getPlayerTeam().get(i).getStats()[0] == 0) {
							modelReserves.addElement("(INJ) " + GameEnvironment.getPlayerTeam().get(i));
						} else {
							modelReserves.addElement(GameEnvironment.getPlayerTeam().get(i).getName());
						}
					}				
					listUserStarters.clearSelection();
					listUserReserves.clearSelection();
				}
				
			}
		});

		btnMakeSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnMakeSub.getText() == "Continue") {
					btnMakeSub.setText("Make A Sub");
					
					listUserStarters.clearSelection();
					listUserReserves.clearSelection();
					listUserStarters.setEnabled(false);
					listUserReserves.setEnabled(false);
					btnNextPoint.setEnabled(true);
			        btnSkipSet.setEnabled(true);
			        
					modelUserStarters.clear();
					for (int i = 1; i < 7; i++) {
						modelUserStarters.addElement(String.format("%-12s%s", "(" + Team.POSITION_STRINGS[(match.getFaceoffIndex() + i) % 7] + ")", GameEnvironment.getPlayerTeam().get((match.getFaceoffIndex() + i) % 7)));
					}
			        
					return;
				}
				
				modelUserStarters.clear();
				for (int i = 0; i < 7; i++) {
					modelUserStarters.addElement(String.format("%-12s%s", "(" + Team.POSITION_STRINGS[i] + ")", GameEnvironment.getPlayerTeam().get(i)));
				}
				
				btnNextPoint.setEnabled(false);
		        btnSkipSet.setEnabled(false);
		        listUserStarters.setEnabled(true);
				listUserReserves.setEnabled(true);
				btnMakeSub.setText("Continue");
			}
		});
		

		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Welcome to the fun part! Here, you see how your matches go.\nYou can click the NEXT POINT button to play the next point, the SKIP SET button to skip ahead\nuntil the current set is over or until an injury occurs, or the MAKE A SUB button to make your own strategic substitution.", "Info", 1);
			}
		});
	}
}
