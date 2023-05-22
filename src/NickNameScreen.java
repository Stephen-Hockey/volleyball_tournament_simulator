
import java.awt.EventQueue;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;

/**
 * This class implements the screen for the user to give nicknames to their 
 * athletes
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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NicknameScreen window = new NicknameScreen();
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
	public NicknameScreen() {
		initialize();
	}
	/**
	 * Create the application with a manager to oversee closing and launching the window
	 */
	public NicknameScreen(GameManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	/**
	 * Close the window instance
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
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Select a player and give them a sick new nickname.", "Info", 1);
			}
		});
		btnHelp.setBounds(538, 5, 50, 25);
		panelTop.add(btnHelp);
		
		JButton btnBack = new JButton("Go Back");
		btnBack.setBounds(12, 44, 117, 25);
		frame.getContentPane().add(btnBack);
		
		JLabel lblHome = new JLabel("Name Editor");
		lblHome.setBounds(12, 10, 329, 15);
		panelTop.add(lblHome);
		
		JList<String> teamList = new JList<String>();
		teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel<String> teamModel = new DefaultListModel<String>();
		teamList.setModel(teamModel);
		for (Athlete athlete : GameEnvironment.getPlayerTeam().getPlayers()) {
			teamModel.addElement(athlete.getName());
		}
		if (teamModel.size() > 0) {
			teamList.setSelectedIndex(0);
		}
		teamList.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		teamList.setBounds(328, 46, 260, 323);
		frame.getContentPane().add(teamList);
		
		JTextField renameTextField = new JTextField();
		renameTextField.setBounds(22, 98, 260, 46);
		frame.getContentPane().add(renameTextField);
		renameTextField.setColumns(10);
		if (teamModel.size() > 0) {
			renameTextField.setText(GameEnvironment.getPlayerTeam().getPlayers().get(0).getName());
		}
		
		JButton renameButton = new JButton("Rename");
		renameButton.setBounds(93, 213, 117, 29);
		frame.getContentPane().add(renameButton);
		
		JLabel descriptionLabel = new JLabel();
		descriptionLabel.setText("hello");
		descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionLabel.setBounds(22, 141, 260, 60);
		frame.getContentPane().add(descriptionLabel);
		if (teamModel.size() > 0) {
			descriptionLabel.setText(GameEnvironment.getPlayerTeam().getPlayers().get(0).getDescription().replaceAll("\n", " "));
		}
		
		teamList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (teamList.isSelectionEmpty()) {
					return ;
				}
				int selectedIndex = teamList.getSelectedIndex();
				Athlete selectedAthlete = GameEnvironment.getPlayerTeam().getPlayers().get(selectedIndex);
				descriptionLabel.setText(selectedAthlete.getDescription().replaceAll("\n", " "));
				renameTextField.setText(selectedAthlete.getName());
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchClubScreen();
				finishedWindow();
			}
		});
		
		renameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nickName = renameTextField.getText();
				if (!nickName.matches(".*\\w.*")) {
					JOptionPane.showMessageDialog(null, "Your player's Nickname cannot be empty.", "Error", 0);
				}
				int selectedIndex = teamList.getSelectedIndex();
				GameEnvironment.getPlayerTeam().getPlayers().get(selectedIndex).setName(nickName);
				teamModel.clear();
				for (Athlete athlete : GameEnvironment.getPlayerTeam().getPlayers()) {
					teamModel.addElement(athlete.getName());
				}
				teamList.setSelectedIndex(selectedIndex);
			}
		});
		
		
	}
}
