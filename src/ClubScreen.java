
import java.util.*;
import java.awt.Color;
import java.awt.EventQueue;
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
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;

public class ClubScreen {

	private JFrame frame;
	private GameManager manager;
	private boolean substitution = false;
	private boolean givingItem = false;
	private int selectedAthleteIndex = -1;
	private int selectedItemIndex = -1;
	private ArrayList<Athlete> userPlayers = GameEnvironment.getPlayerTeam().getPlayers();
	private ArrayList<Item> userItems = GameEnvironment.getInventory();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClubScreen window = new ClubScreen();
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
	public ClubScreen() {
		initialize();
	}
	public ClubScreen(GameManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
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
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Welcome to your Clubhouse from here you can substitute players, \ngive players special items and nickname your players.", "Info", 1);
			}
		});
		btnHelp.setBounds(538, 5, 50, 25);
		panelTop.add(btnHelp);
		
		String teamName = GameEnvironment.getPlayerTeam().getTeamName();
		JLabel lblHome = new JLabel(teamName + "'s Clubhouse");
		lblHome.setBounds(12, 10, 366, 15);
		panelTop.add(lblHome);
		
		JButton btnBack = new JButton("Go Back");
		btnBack.setBounds(10, 44, 117, 25);
		frame.getContentPane().add(btnBack);
		
		
		String playerName = GameEnvironment.getPlayerName();
		JLabel playerNameLabel = new JLabel("Manager: " + playerName);
		playerNameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		playerNameLabel.setBackground(new Color(255, 255, 255));
		playerNameLabel.setOpaque(true);
		playerNameLabel.setBounds(10, 75, 260, 25);
		frame.getContentPane().add(playerNameLabel);
		
		JList<String> teamList = new JList<String>();
		teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel<String> teamModel = new DefaultListModel<String>();
		teamList.setModel(teamModel);
		for (int i=0; i < userPlayers.size(); i++) {
			String playerText = "";
			if (GameEnvironment.getPlayerTeam().get(i).getStats()[0] == 0) {
				playerText += "Injured";
			} else if (i < Team.POSITION_STRINGS.length) {
				playerText += Team.POSITION_STRINGS[i];
			} else {
					playerText += "Sub";
			}
			teamModel.addElement(playerText + ": " + userPlayers.get(i));
		}
		teamList.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		teamList.setBounds(10, 112, 260, 255);
		frame.getContentPane().add(teamList);
		
	
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
		
		JProgressBar pBarStamina = new JProgressBar();
		pBarStamina.setMaximum(99);
		pBarStamina.setBounds(86, 35, 150, 15);
		panelAthleteInfoBox.add(pBarStamina);
		
		JLabel lblOffence = new JLabel("Offence");
		lblOffence.setBounds(12, 55, 70, 15);
		panelAthleteInfoBox.add(lblOffence);
		
		JProgressBar pBarOffence = new JProgressBar();
		pBarOffence.setMaximum(99);
		pBarOffence.setBounds(86, 55, 150, 15);
		panelAthleteInfoBox.add(pBarOffence);
		
		JLabel lblDefence = new JLabel("Defence");
		lblDefence.setBounds(12, 75, 70, 15);
		panelAthleteInfoBox.add(lblDefence);
		
		JProgressBar pBarDefence = new JProgressBar();
		pBarDefence.setMaximum(99);
		pBarDefence.setBounds(86, 75, 150, 15);
		panelAthleteInfoBox.add(pBarDefence);
		
		JLabel descriptionLabel = new JLabel("");
		descriptionLabel.setBounds(12, 95, 328, 15);
		panelAthleteInfoBox.add(descriptionLabel);
		
		
		
		JButton moveButton = new JButton();
		moveButton.setBounds(10, 370, 260, 30);
		moveButton.setVisible(false);
		frame.getContentPane().add(moveButton);

		JLabel moveInfoBox = new JLabel();
		moveInfoBox.setHorizontalAlignment(SwingConstants.CENTER);
		moveInfoBox.setBounds(10, 400, 260, 20);
		frame.getContentPane().add(moveInfoBox);
		
		
		
		JPanel ItemInfoBox = new JPanel();
		ItemInfoBox.setLayout(null);
		ItemInfoBox.setBackground(Color.WHITE);
		ItemInfoBox.setBounds(281, 167, 307, 249);
		frame.getContentPane().add(ItemInfoBox);
		
		JLabel clubItemsLabel = new JLabel("Your Inventory");
		clubItemsLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		clubItemsLabel.setBounds(6, 6, 289, 15);
		ItemInfoBox.add(clubItemsLabel);
		
		JList<String> clubItemsList = new JList<String>();
		clubItemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel<String> clubItemsModel = new DefaultListModel<String>();
		clubItemsList.setModel(clubItemsModel);
		ArrayList<Item> teamItems = userItems;
		for (Item item: teamItems) {
			clubItemsModel.addElement(item.getName());
		}
		clubItemsList.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		clubItemsList.setBounds(6, 25, 295, 224);
		ItemInfoBox.add(clubItemsList);
		
		JButton renamePlayersButton = new JButton("Nickname");
		renamePlayersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manager.launchNickNameScreen();
				finishedWindow();
			}
		});
		renamePlayersButton.setBounds(139, 44, 131, 25);
		frame.getContentPane().add(renamePlayersButton);
		
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchHomeScreen();
				finishedWindow();
			}
		});
		
		moveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!substitution && (selectedAthleteIndex != -1)) {
					clubItemsList.clearSelection();
					moveButton.setText("Cancel Subsitution");
					moveInfoBox.setText("Select Player to Substitute");
					substitution = true;
					givingItem = false;
					
				} else if (!givingItem && (selectedItemIndex != -1)) {
					teamList.clearSelection();
					moveButton.setText("Cancel Item Give");
					moveInfoBox.setText("Select Receiving Player");
					givingItem = true;
					substitution = false;
					
				} else {
					substitution = false;
					moveInfoBox.setText("");
					moveButton.setVisible(false);
				}
			}
		});
		
		teamList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (teamList.isSelectionEmpty()) {
					return ;
				}
				clubItemsList.clearSelection();
				userPlayers = GameEnvironment.getPlayerTeam().getPlayers();
				int selectedIndex = teamList.getSelectedIndex();
				if (selectedIndex > userPlayers.size()) {
					return ;
				}
				if (substitution) {
					int subIndex = selectedIndex;
					Collections.swap(userPlayers, subIndex, selectedAthleteIndex);
					GameEnvironment.getPlayerTeam().setPlayers(userPlayers);
					teamModel.clear();
					for (int i=0; i < userPlayers.size(); i++) {
						String playerText = "";
						if (GameEnvironment.getPlayerTeam().get(i).getStats()[0] == 0) {
							playerText += "Injured";
						} else if (i < Team.POSITION_STRINGS.length) {
							playerText += Team.POSITION_STRINGS[i];
						} else {
								playerText += "Sub";
						}
						teamModel.addElement(playerText + ": " + userPlayers.get(i));
					}
					substitution = false;
					moveInfoBox.setText("");
					moveButton.setVisible(false);
				
					
				} else if (givingItem) {
					int receiverIndex = selectedIndex;
					Athlete selectedAthlete = userPlayers.get(receiverIndex);
					Item selectedItem = userItems.get(selectedItemIndex);
					GameEnvironment.getPlayerTeam().getPlayers().get(receiverIndex).addItem(selectedItem);
					GameEnvironment.getInventory().remove(selectedItemIndex);
					userPlayers = GameEnvironment.getPlayerTeam().getPlayers();
					userItems = GameEnvironment.getInventory();
					clubItemsModel.clear();
					for (Item item: teamItems) {
						clubItemsModel.addElement(item.getName());
					}
					teamModel.clear();
					for (int i=0; i < userPlayers.size(); i++) {
						String playerText = "";
						if (GameEnvironment.getPlayerTeam().get(i).getStats()[0] == 0) {
							playerText += "Injured";
						} else if (i < Team.POSITION_STRINGS.length) {
							playerText += Team.POSITION_STRINGS[i];
						} else {
								playerText += "Sub";
						}
						teamModel.addElement(playerText + ": " + userPlayers.get(i));
					}
					givingItem = false;
					moveInfoBox.setText("");
					moveButton.setVisible(false);
					JOptionPane.showMessageDialog(null, "Successfully used " + selectedItem + " on " + selectedAthlete + ".");
				}
				
				else {
					selectedAthleteIndex = selectedIndex;
					selectedItemIndex = -1;
					Athlete selectedAthlete = userPlayers.get(selectedAthleteIndex);
					String selectAthleteName = selectedAthlete.getName();
					lblName.setText(selectAthleteName);
					descriptionLabel.setText(selectedAthlete.getDescription().replaceAll("\n", " "));
					pBarStamina.setValue(selectedAthlete.getStats()[0]);
					pBarOffence.setValue(selectedAthlete.getStats()[1]);
					pBarDefence.setValue(selectedAthlete.getStats()[2]);
					String moveButtonPre = "";
					if (selectedAthleteIndex > 6) {
						moveButtonPre += "Sub on ";
					} else {
						moveButtonPre += "Sub off ";
					}
					moveButton.setText(moveButtonPre + selectAthleteName);
					moveButton.setVisible(true);
				}
			}
		});
		
		clubItemsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (clubItemsList.isSelectionEmpty()) {
					return ;
				}
				teamList.clearSelection();
				selectedItemIndex = clubItemsList.getSelectedIndex();
				selectedAthleteIndex = -1;
				ArrayList<Item> teamItems = GameEnvironment.getInventory();
				Item selectedItem = teamItems.get(selectedItemIndex);
				lblName.setText(selectedItem.getName());
				descriptionLabel.setText(selectedItem.getDescription().replaceAll("\n", " "));
				int[] effects = selectedItem.getStats();
				pBarStamina.setValue(effects[0]);
				pBarOffence.setValue(effects[1]);
				pBarDefence.setValue(effects[2]);
				moveButton.setText("Use Item on Player");
				moveButton.setVisible(true);
			}
		});
	}
}
