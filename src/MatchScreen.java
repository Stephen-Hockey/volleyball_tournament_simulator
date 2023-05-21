
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

public class MatchScreen {

	private JFrame frame;
	private GameManager manager;
	private Match match;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MatchScreen window = new MatchScreen();
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
	public MatchScreen() {
		initialize();
	}
	
	public MatchScreen(GameManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		manager.closeMatchScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//Match match = GameEnvironment.getMatches().get(GameEnvironment.getMatches().size() - 1);
		GameEnvironment.setPlayerTeam(Team.generateTeam(9));
		match = new Match(Team.generateTeam(5));
		
		
		
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
		
		JLabel lblVersus = new JLabel("VS", SwingConstants.CENTER);
		lblVersus.setBounds(60, 98, 480, 15);
		frame.getContentPane().add(lblVersus);
		
		JLabel lblStamina = new JLabel("Stamina", SwingConstants.CENTER);
		lblStamina.setBounds(265, 125, 70, 15);
		frame.getContentPane().add(lblStamina);
		
		JProgressBar pBarDefenceR = new JProgressBar();
		pBarDefenceR.setBounds(0, 0, 150, 14);
		frame.getContentPane().add(pBarDefenceR);
		pBarDefenceR.setMaximum(99);
		
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
		
		JButton btnNextPoint = new JButton("Next Point");
		btnNextPoint.setBounds(240, 192, 120, 32);
		frame.getContentPane().add(btnNextPoint);
		
		JButton btnSkipSet = new JButton("Skip Set");
		btnSkipSet.setBounds(174, 385, 120, 25);
		frame.getContentPane().add(btnSkipSet);
		
		JButton btnSkipMatch = new JButton("Skip Match");
		btnSkipMatch.setBounds(306, 385, 120, 25);
		frame.getContentPane().add(btnSkipMatch);
		
		JButton btnMakeSub = new JButton("Make A Sub");
		btnMakeSub.setBounds(240, 236, 120, 32);
		frame.getContentPane().add(btnMakeSub);
		
		JList<String> listUserStarters = new JList<String>();
		DefaultListModel<String> modelUserStarters = new DefaultListModel<String>();
		listUserStarters.setModel(modelUserStarters);
		for (int i = 1; i < 7; i++) {
			modelUserStarters.addElement(GameEnvironment.getPlayerTeam().get(i).getName());
		}
		listUserStarters.setEnabled(false);
		listUserStarters.setBounds(12, 191, 150, 219);
		frame.getContentPane().add(listUserStarters);
		
		JList<String> listOpposingStarters = new JList<String>();
		DefaultListModel<String> modelOpposingStarters = new DefaultListModel<String>();
		listOpposingStarters.setModel(modelOpposingStarters);
		for (int i = 1; i < 7; i++) {
			modelOpposingStarters.addElement(match.getOpposingTeam().get(i).getName());
		}
		listOpposingStarters.setEnabled(false);
		listOpposingStarters.setBounds(438, 191, 150, 219);
		frame.getContentPane().add(listOpposingStarters);
		
		JList<String> listReserves = new JList<String>();
		DefaultListModel<String> modelReserves = new DefaultListModel<String>();
		listReserves.setModel(modelReserves);
		for(int i = 7; i < GameEnvironment.getPlayerTeam().size(); i++) {
			modelReserves.addElement(GameEnvironment.getPlayerTeam().get(i).getName());
		}
		listReserves.setEnabled(false);
		listReserves.setBounds(225, 280, 150, 93);
		//listReserves.setVisible(false);
		frame.getContentPane().add(listReserves);
		
		JButton btnBack = new JButton("Go Back");
		btnBack.setBounds(10, 39, 117, 25);
		btnBack.setVisible(false);
		frame.getContentPane().add(btnBack);
		
		JLabel lblAthleteL = new JLabel(GameEnvironment.getPlayerTeam().get(0).getName(), SwingConstants.LEFT);
		lblAthleteL.setBounds(60, 98, 480, 15);
		frame.getContentPane().add(lblAthleteL);
		
		JLabel lblAthleteR = new JLabel(match.getOpposingTeam().get(0).getName(), SwingConstants.RIGHT);
		lblAthleteR.setBounds(60, 98, 480, 15);
		frame.getContentPane().add(lblAthleteR);
		
		btnNextPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!match.playPoint()) {
					String injuryMessageString = GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getName() 
							+ " got injured facing off against " + match.getOpposingTeam().get(match.getFaceoffIndex()).getName() 
							+ "\nYou have to sub a new player on!";
					JOptionPane.showMessageDialog(null, injuryMessageString, "Oh no!", 0);
					listReserves.setVisible(true);
					listReserves.setEnabled(true);
					listUserStarters.setEnabled(true);
					
				}
				
				if (match.checkIfSetOver()) {
					if ((match.getSetScore()[0] == 3)||(match.getSetScore()[1] == 3)) {
						btnBack.setVisible(true);
						
						btnMakeSub.setVisible(false);
						btnNextPoint.setVisible(false);
						btnSkipMatch.setVisible(false);
						btnSkipSet.setVisible(false);
						barStaminaL.setVisible(false);
						barStaminaR.setVisible(false);
						barOffenceL.setVisible(false);
						barOffenceR.setVisible(false);
						barDefenceL.setVisible(false);
						barDefenceR.setVisible(false);
						lblStamina.setVisible(false);
						lblOffence.setVisible(false);
						lblDefence.setVisible(false);
						lblAthleteL.setVisible(false);
						lblAthleteR.setVisible(false);
						
						listUserStarters.setVisible(false);
						listOpposingStarters.setVisible(false);
						listReserves.setVisible(false);
						
						if (match.matchWon()) {
							lblVersus.setText("You won the match!");
						} else {
							lblVersus.setText("Darn, you lost the match.");
						}
						lblVersus.setHorizontalAlignment(SwingConstants.CENTER);
					}
				}
				
				lblAthleteL.setText(GameEnvironment.getPlayerTeam().get(match.getFaceoffIndex()).getName());
				lblVersus.setText("VS");
				lblAthleteR.setText(match.getOpposingTeam().get(match.getFaceoffIndex()).getName());
				//do stat bars
				
				
				
				modelUserStarters.clear();
				for (int i = 1; i < 7; i++) {
					modelUserStarters.addElement(GameEnvironment.getPlayerTeam().get((match.getFaceoffIndex() + i) % 7).getName());
				}
				modelOpposingStarters.clear();
				for (int i = 1; i < 7; i++) {
					modelOpposingStarters.addElement(match.getOpposingTeam().get((match.getFaceoffIndex() + i) % 7).getName());
				}
				lblScore.setText(match.getScoreString());
				lblSetScore.setText(match.getSetScoreString());
				
			}
		});
		btnMakeSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnSkipSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		

		btnSkipMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchHomeScreen();
				finishedWindow();
			}
		});
	}
}
