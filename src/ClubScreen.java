
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ClubScreen {

	private JFrame frame;
	private GameManager manager;

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
		btnHelp.setBounds(538, 5, 50, 25);
		panelTop.add(btnHelp);
		
		String teamName = GameEnvironment.getTeamName();
		JLabel lblHome = new JLabel(teamName + "'s Clubhouse");
		lblHome.setBounds(12, 10, 366, 15);
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
	}

}
