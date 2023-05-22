package screens;

import java.util.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.Athlete;
import main.GameEnvironment;
import main.GameManager;
import main.Item;
import main.Team;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;


/**
 * The ClubScreen is where the user can substitute athletes, use items on players,
 * give nicknames to their players and view their players' stats<br>
 * Launched from HomeScreen<br>
 * Launches HomeScreen, or NicknameScreen
 *
 * @author Lachlan Stewart and Stephen Hockey
 * @version 1.1, May 2023.
 */
public class ClubScreen {

	/**
	 * The frame on which elements are placed
	 */
	private JFrame frame;
	
	/**
	 * The manager of the current instance of club screen
	 */
	private GameManager manager;
	
	/**
	 * Boolean for if the user is in the process of a substitution
	 */
	private boolean substitution = false;
	
	/**
	 * Boolean for if the user is in the process of giving an item to an athlete
	 */
	private boolean givingItem = false;
	
	/**
	 * index of the athlete selected by the user, if not selected is -1
	 */
	private int selectedAthleteIndex = -1;
	
	/**
	 * index of the item selected by the user, if not selected is -1
	 */
	private int selectedItemIndex = -1;
	
	/**
	 * The list of Athletes in the users team 
	 */
	private ArrayList<Athlete> userAthletes = GameEnvironment.getPlayerTeam().getPlayers();
	
	/**
	 * The list of Items in the users inventory 
	 */
	private ArrayList<Item> userItems = GameEnvironment.getInventory();
	
	/**
	 * Create the window with a GameManager object to oversee closing and launching the window
	 * @param incomingManager the GameManager object
	 */
	public ClubScreen(GameManager incomingManager) {
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
		manager.closeClubScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
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
		
		String teamName = GameEnvironment.getPlayerTeam().getTeamName();
		JLabel lblTopText = new JLabel(teamName + "'s Clubhouse");
		lblTopText.setBounds(12, 10, 366, 15);
		panelTop.add(lblTopText);
		
		JButton btnBack = new JButton("Go Back");
		btnBack.setBounds(10, 44, 117, 25);
		frame.getContentPane().add(btnBack);
		
		String playerName = GameEnvironment.getPlayerName();
		JLabel lblPlayerName = new JLabel("Manager: " + playerName);
		lblPlayerName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblPlayerName.setBackground(new Color(255, 255, 255));
		lblPlayerName.setOpaque(true);
		lblPlayerName.setBounds(10, 75, 260, 25);
		frame.getContentPane().add(lblPlayerName);
		
		JList<String> listTeam = new JList<String>();
		listTeam.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel<String> teamModel = new DefaultListModel<String>();
		listTeam.setModel(teamModel);
		for (int i=0; i < userAthletes.size(); i++) {
			String playerText = "";
			if (GameEnvironment.getPlayerTeam().get(i).getStats()[0] == 0) {
				playerText += "Injured";
			} else if (i < Team.POSITION_STRINGS.length) {
				playerText += Team.POSITION_STRINGS[i];
			} else {
					playerText += "Sub";
			}
			teamModel.addElement(playerText + ": " + userAthletes.get(i));
		}
		listTeam.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		listTeam.setBounds(10, 112, 260, 255);
		frame.getContentPane().add(listTeam);
	
		JPanel panelAthleteInfoBox = new JPanel();
		panelAthleteInfoBox.setLayout(null);
		panelAthleteInfoBox.setBackground(Color.WHITE);
		panelAthleteInfoBox.setBounds(281, 38, 307, 117);
		frame.getContentPane().add(panelAthleteInfoBox);
		
		JLabel lblName = new JLabel("");
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblName.setBounds(12, 6, 289, 15);
		panelAthleteInfoBox.add(lblName);
		
		JLabel lblStamina = new JLabel("Stamina");
		lblStamina.setBounds(12, 35, 70, 15);
		panelAthleteInfoBox.add(lblStamina);
		
		JProgressBar pbarStamina = new JProgressBar();
		pbarStamina.setMaximum(99);
		pbarStamina.setBounds(86, 35, 150, 15);
		panelAthleteInfoBox.add(pbarStamina);
		
		JLabel lblOffence = new JLabel("Offence");
		lblOffence.setBounds(12, 55, 70, 15);
		panelAthleteInfoBox.add(lblOffence);
		
		JProgressBar pbarOffence = new JProgressBar();
		pbarOffence.setMaximum(99);
		pbarOffence.setBounds(86, 55, 150, 15);
		panelAthleteInfoBox.add(pbarOffence);
		
		JLabel lblDefence = new JLabel("Defence");
		lblDefence.setBounds(12, 75, 70, 15);
		panelAthleteInfoBox.add(lblDefence);
		
		JProgressBar pbarDefence = new JProgressBar();
		pbarDefence.setMaximum(99);
		pbarDefence.setBounds(86, 75, 150, 15);
		panelAthleteInfoBox.add(pbarDefence);
		
		JLabel lblDescription = new JLabel("");
		lblDescription.setBounds(12, 95, 328, 15);
		panelAthleteInfoBox.add(lblDescription);
		
		JButton btnMove = new JButton();
		btnMove.setBounds(10, 370, 260, 30);
		btnMove.setVisible(false);
		frame.getContentPane().add(btnMove);

		JLabel lblMoveInfo = new JLabel();
		lblMoveInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoveInfo.setBounds(10, 400, 260, 20);
		frame.getContentPane().add(lblMoveInfo);
		
		JPanel panelItemInfo = new JPanel();
		panelItemInfo.setLayout(null);
		panelItemInfo.setBackground(Color.WHITE);
		panelItemInfo.setBounds(281, 167, 307, 249);
		frame.getContentPane().add(panelItemInfo);
		
		JLabel lblInventory = new JLabel("Your Inventory");
		lblInventory.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblInventory.setBounds(6, 6, 289, 15);
		panelItemInfo.add(lblInventory);
		
		JList<String> listClubItems = new JList<String>();
		listClubItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel<String> clubItemsModel = new DefaultListModel<String>();
		listClubItems.setModel(clubItemsModel);
		ArrayList<Item> teamItems = userItems;
		for (Item item: teamItems) {
			clubItemsModel.addElement(item.getName());
		}
		listClubItems.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		listClubItems.setBounds(6, 25, 295, 224);
		panelItemInfo.add(listClubItems);
		
		JButton btnRenamePlayers = new JButton("Nickname");
		btnRenamePlayers.setBounds(139, 44, 131, 25);
		frame.getContentPane().add(btnRenamePlayers);
		
		//Events

		btnRenamePlayers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manager.launchNicknameScreen();
				finishedWindow();
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchHomeScreen();
				finishedWindow();
			}
		});
		
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!substitution && (selectedAthleteIndex != -1)) {
					listClubItems.clearSelection();
					btnMove.setText("Cancel Subsitution");
					lblMoveInfo.setText("Select Player to Substitute");
					substitution = true;
					givingItem = false;
					
				} else if (!givingItem && (selectedItemIndex != -1)) {
					listTeam.clearSelection();
					btnMove.setText("Cancel Item Give");
					lblMoveInfo.setText("Select Receiving Player");
					givingItem = true;
					substitution = false;
					
				} else {
					substitution = false;
					lblMoveInfo.setText("");
					btnMove.setVisible(false);
				}
			}
		});
		
		listTeam.addListSelectionListener(new ListSelectionListener() {
			
			public void valueChanged(ListSelectionEvent e) {
				if (listTeam.isSelectionEmpty()) {
					return ;
				}
				listClubItems.clearSelection();
				userAthletes = GameEnvironment.getPlayerTeam().getPlayers();
				int selectedIndex = listTeam.getSelectedIndex();
				if (selectedIndex > userAthletes.size()) {
					return ;
				}
				if (substitution) {
					int subIndex = selectedIndex;
					Collections.swap(userAthletes, subIndex, selectedAthleteIndex);
					GameEnvironment.getPlayerTeam().setPlayers(userAthletes);
					teamModel.clear();
					for (int i=0; i < userAthletes.size(); i++) {
						String playerText = "";
						if (GameEnvironment.getPlayerTeam().get(i).getStats()[0] == 0) {
							playerText += "Injured";
						} else if (i < Team.POSITION_STRINGS.length) {
							playerText += Team.POSITION_STRINGS[i];
						} else {
								playerText += "Sub";
						}
						teamModel.addElement(playerText + ": " + userAthletes.get(i));
					}
					substitution = false;
					lblMoveInfo.setText("");
					btnMove.setVisible(false);
				
					
				} else if (givingItem) {
					int receiverIndex = selectedIndex;
					Athlete selectedAthlete = userAthletes.get(receiverIndex);
					Item selectedItem = userItems.get(selectedItemIndex);
					GameEnvironment.getPlayerTeam().getPlayers().get(receiverIndex).addItem(selectedItem);
					GameEnvironment.getInventory().remove(selectedItemIndex);
					userAthletes = GameEnvironment.getPlayerTeam().getPlayers();
					userItems = GameEnvironment.getInventory();
					clubItemsModel.clear();
					for (Item item: teamItems) {
						clubItemsModel.addElement(item.getName());
					}
					teamModel.clear();
					for (int i=0; i < userAthletes.size(); i++) {
						String playerText = "";
						if (GameEnvironment.getPlayerTeam().get(i).getStats()[0] == 0) {
							playerText += "Injured";
						} else if (i < Team.POSITION_STRINGS.length) {
							playerText += Team.POSITION_STRINGS[i];
						} else {
								playerText += "Sub";
						}
						teamModel.addElement(playerText + ": " + userAthletes.get(i));
					}
					givingItem = false;
					lblMoveInfo.setText("");
					btnMove.setVisible(false);
					JOptionPane.showMessageDialog(null, "Successfully used " + selectedItem + " on " + selectedAthlete + ".");
				}
				
				else {
					selectedAthleteIndex = selectedIndex;
					selectedItemIndex = -1;
					Athlete selectedAthlete = userAthletes.get(selectedAthleteIndex);
					String selectAthleteName = selectedAthlete.getName();
					lblName.setText(selectAthleteName);
					lblDescription.setText(selectedAthlete.getDescription().replaceAll("\n", " "));
					pbarStamina.setValue(selectedAthlete.getStats()[0]);
					pbarOffence.setValue(selectedAthlete.getStats()[1]);
					pbarDefence.setValue(selectedAthlete.getStats()[2]);
					String moveButtonPre = "";
					if (selectedAthleteIndex > 6) {
						moveButtonPre += "Sub on ";
					} else {
						moveButtonPre += "Sub off ";
					}
					btnMove.setText(moveButtonPre + selectAthleteName);
					btnMove.setVisible(true);
				}
			}
		});
		
		listClubItems.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (listClubItems.isSelectionEmpty()) {
					return ;
				}
				listTeam.clearSelection();
				selectedItemIndex = listClubItems.getSelectedIndex();
				selectedAthleteIndex = -1;
				ArrayList<Item> teamItems = GameEnvironment.getInventory();
				Item selectedItem = teamItems.get(selectedItemIndex);
				lblName.setText(selectedItem.getName());
				lblDescription.setText(selectedItem.getDescription().replaceAll("\n", " "));
				int[] effects = selectedItem.getStats();
				pbarStamina.setValue(effects[0]);
				pbarOffence.setValue(effects[1]);
				pbarDefence.setValue(effects[2]);
				btnMove.setText("Use Item on Player");
				btnMove.setVisible(true);
			}
		});

		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Welcome to your Clubhouse from here you can substitute players, \ngive players special items and nickname your players.", "Info", 1);
			}
		});
	}
}
