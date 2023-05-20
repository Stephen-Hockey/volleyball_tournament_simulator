import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

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
		
		JLabel lblTopText = new JLabel("Stadium");
		lblTopText.setBounds(80, 10, 70, 15);
		panelTop.add(lblTopText);
		
		JButton btnUserData = new JButton("i");
		btnUserData.setBounds(12, 5, 50, 25);
		panelTop.add(btnUserData);
		
		JList<String> listOpposingTeam = new JList<String>();
		listOpposingTeam.setBounds(170, 191, 150, 212);
		frame.getContentPane().add(listOpposingTeam);
		
		JPanel panelAthleteInfoBox = new JPanel();
		panelAthleteInfoBox.setLayout(null);
		panelAthleteInfoBox.setBackground(Color.WHITE);
		panelAthleteInfoBox.setBounds(330, 42, 246, 162);
		frame.getContentPane().add(panelAthleteInfoBox);
		
		JLabel lblPurchaseName = new JLabel("");
		lblPurchaseName.setBounds(12, 12, 222, 15);
		panelAthleteInfoBox.add(lblPurchaseName);
		
		JLabel lblDefence = new JLabel("Defence");
		lblDefence.setBounds(12, 78, 70, 15);
		panelAthleteInfoBox.add(lblDefence);
		
		JProgressBar pBarStamina = new JProgressBar();
		pBarStamina.setMaximum(99);
		pBarStamina.setBounds(86, 39, 150, 14);
		panelAthleteInfoBox.add(pBarStamina);
		
		JLabel lblStamina = new JLabel("Stamina");
		lblStamina.setBounds(12, 38, 70, 15);
		panelAthleteInfoBox.add(lblStamina);
		
		JProgressBar pBarOffence = new JProgressBar();
		pBarOffence.setMaximum(99);
		pBarOffence.setBounds(86, 58, 150, 14);
		panelAthleteInfoBox.add(pBarOffence);
		
		JLabel lblOffence = new JLabel("Offence");
		lblOffence.setBounds(12, 58, 70, 15);
		panelAthleteInfoBox.add(lblOffence);
		
		JProgressBar pBarDefence = new JProgressBar();
		pBarDefence.setMaximum(99);
		pBarDefence.setBounds(86, 78, 150, 14);
		panelAthleteInfoBox.add(pBarDefence);
		
		JTextArea txtDescription = new JTextArea();
		txtDescription.setEditable(false);
		txtDescription.setBackground(new Color(222, 221, 218));
		txtDescription.setBounds(12, 104, 222, 45);
		panelAthleteInfoBox.add(txtDescription);
		
		JButton btnBack = new JButton("Go Back");
		btnBack.setBounds(10, 39, 117, 25);
		frame.getContentPane().add(btnBack);
		
		JButton btnOpposingTeam1 = new JButton("");
		btnOpposingTeam1.setBounds(10, 191, 150, 64);
		frame.getContentPane().add(btnOpposingTeam1);
		
		JTextArea txtStadium = new JTextArea();
		txtStadium.setText("Choose the team you want to play a match against");
		txtStadium.setBounds(10, 73, 310, 108);
		frame.getContentPane().add(txtStadium);
		
		JButton btnOpposingTeam2 = new JButton("");
		btnOpposingTeam2.setBounds(10, 265, 150, 64);
		frame.getContentPane().add(btnOpposingTeam2);
		
		JButton btnOpposingTeam3 = new JButton("");
		btnOpposingTeam3.setBounds(10, 339, 150, 64);
		frame.getContentPane().add(btnOpposingTeam3);
		
		JButton btnPlayMatch = new JButton("");
		btnPlayMatch.setBounds(330, 214, 246, 189);
		frame.getContentPane().add(btnPlayMatch);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnOpposingTeam1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnOpposingTeam2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnOpposingTeam3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPlayMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		listOpposingTeam.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (listOpposingTeam.isSelectionEmpty()) {
					return;
				}
				
				
			}
		});
		
	}
}
