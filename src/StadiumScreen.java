package senggui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class StadiumScreen {

	private JFrame frame;
	private GameManager manager;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StadiumScreen window = new StadiumScreen();
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
	public StadiumScreen() {
		initialize();
	}

	public StadiumScreen(GameManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		manager.closeStadiumScreen(this);
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
		
		JLabel lblHome = new JLabel("Stadium");
		lblHome.setBounds(12, 10, 70, 15);
		panelTop.add(lblHome);
		
		JButton btnBack = new JButton("Go Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchHomeScreen();
				finishedWindow();
			}
		});
		
		btnBack.setBounds(10, 44, 117, 25);
		frame.getContentPane().add(btnBack);
		JList<String> oppoTeamJList = new JList<String>();		
		DefaultListModel<String> oppoTeamModel = new DefaultListModel<String>();
		oppoTeamJList.setModel(oppoTeamModel);		
		for (Team team: GameEnvironment.getCurrentWeekOpposingTeams()) {
			oppoTeamModel.addElement(team.getTeamName());
		}
		oppoTeamJList.setBounds(388, 71, 195, 200);
		frame.getContentPane().add(oppoTeamJList);
		
		
		boolean weeklyGamePlayed = GameEnvironment.getWeeklyGamePlayed();
		JButton playOppoButton = new JButton("Play");
		playOppoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!oppoTeamJList.isSelectionEmpty()) {
					Integer n = oppoTeamJList.getSelectedIndex();
					Team oppoTeam = GameEnvironment.getCurrentWeekOpposingTeams().get(n);
					GameEnvironment.addMatch(new Match(oppoTeam));
					manager.launchMatchScreen();
					finishedWindow();
				}
			}
		});
		if (weeklyGamePlayed) {
			playOppoButton.setVisible(false);
		}
		playOppoButton.setBounds(430, 283, 117, 29);
		frame.getContentPane().add(playOppoButton);
		
		
		
	}
}
