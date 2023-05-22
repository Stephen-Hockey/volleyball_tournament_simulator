package screens;


import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionListener;

import main.Athlete;
import main.GameEnvironment;
import main.GameManager;

import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;

/**
 * The NicknameScreen is where the user can nickname their players<br>
 * Launched from ClubScreen<br>
 * Launches ClubScreen upon closing
 * 
 * @author Lachlan Stewart and Stephen Hockey
 * @version 1.1, May 2023.
 */
public class NicknameScreen {

	/**
	 * The frame on which elements are placed
	 */
	private JFrame frame;
	
	/**
	 * The manager of the current instance of club screen
	 */
	private GameManager manager;
	
	/**
	 * Create the window with a GameManager object to oversee closing and launching the window
	 * @param incomingManager the GameManager object
	 */
	public NicknameScreen(GameManager incomingManager) {
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
	 * Runs the closeNicknameScreen method of its manager to close itself
	 */
	public void finishedWindow() {
		manager.closeNicknameScreen(this);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
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
		
		JButton btnBack = new JButton("Go Back");
		btnBack.setBounds(12, 44, 117, 25);
		frame.getContentPane().add(btnBack);
		
		JLabel lblTopText = new JLabel("Name Editor");
		lblTopText.setBounds(12, 10, 329, 15);
		panelTop.add(lblTopText);
		
		JList<String> listTeam = new JList<String>();
		listTeam.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel<String> teamModel = new DefaultListModel<String>();
		listTeam.setModel(teamModel);
		for (Athlete athlete : GameEnvironment.getPlayerTeam().getPlayers()) {
			teamModel.addElement(athlete.getName());
		}
		if (teamModel.size() > 0) {
			listTeam.setSelectedIndex(0);
		}
		listTeam.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		listTeam.setBounds(328, 46, 260, 323);
		frame.getContentPane().add(listTeam);
		
		JTextField textRename = new JTextField();
		textRename.setBounds(22, 98, 260, 46);
		frame.getContentPane().add(textRename);
		textRename.setColumns(10);
		if (teamModel.size() > 0) {
			textRename.setText(GameEnvironment.getPlayerTeam().getPlayers().get(0).getName());
		}
		
		JButton btnRename = new JButton("Rename");
		btnRename.setBounds(93, 213, 117, 29);
		frame.getContentPane().add(btnRename);
		
		JLabel lblDescription = new JLabel();
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setBounds(22, 141, 260, 60);
		frame.getContentPane().add(lblDescription);
		if (teamModel.size() > 0) {
			lblDescription.setText(GameEnvironment.getPlayerTeam().getPlayers().get(0).getDescription().replaceAll("\n", " "));
		}
		
		//Events
		
		listTeam.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (listTeam.isSelectionEmpty()) {
					return ;
				}
				int selectedIndex = listTeam.getSelectedIndex();
				Athlete selectedAthlete = GameEnvironment.getPlayerTeam().getPlayers().get(selectedIndex);
				lblDescription.setText(selectedAthlete.getDescription().replaceAll("\n", " "));
				textRename.setText(selectedAthlete.getName());
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchClubScreen();
				finishedWindow();
			}
		});
		
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nickName = textRename.getText();
				if (!nickName.matches(".*\\w.*")) {
					JOptionPane.showMessageDialog(null, "Your player's Nickname cannot be empty.", "Error", 0);
				}
				int selectedIndex = listTeam.getSelectedIndex();
				GameEnvironment.getPlayerTeam().getPlayers().get(selectedIndex).setName(nickName);
				teamModel.clear();
				for (Athlete athlete : GameEnvironment.getPlayerTeam().getPlayers()) {
					teamModel.addElement(athlete.getName());
				}
				listTeam.setSelectedIndex(selectedIndex);
			}
		});

		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Select a player and give them a sick new nickname.", "Info", 1);
			}
		});
	}
}
