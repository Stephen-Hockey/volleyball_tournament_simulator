package senggui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MarketScreen {

	private JFrame frame;
	private GameManager manager;
	
	//money, team, inventory are accessed via GE
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MarketScreen window = new MarketScreen();
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
	public MarketScreen() {
		initialize();
	}

	public MarketScreen(GameManager incomingManager) {
		manager = incomingManager;
		initialize();
		frame.setVisible(true);
	}
	
	public void closeWindow() {
		frame.dispose();
	}
	
	public void finishedWindow() {
		manager.closeMarketScreen(this);
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
		
		JLabel lblMarket = new JLabel("Market");
		lblMarket.setBounds(80, 10, 70, 15);
		panelTop.add(lblMarket);
		
		JButton btnUserData = new JButton("i");
		btnUserData.setBounds(12, 5, 50, 25);
		panelTop.add(btnUserData);
		
		//test
		//GameEnvironment.setupWeek();
		//GameEnvironment.setInventory(new ArrayList<Item>());
		//GameEnvironment.getInventory().add(Item.generateItem());
		//GameEnvironment.setPlayerTeam(new Team("team"));
		//GameEnvironment.getPlayerTeam().add(Athlete.generateAthlete(5));
		//test
		JList<String> listMarket = new JList<String>();
		DefaultListModel<String> modelMarket = new DefaultListModel<String>();
		listMarket.setModel(modelMarket);
		for (Item item: GameEnvironment.getCurrentWeekMarketItems()) {
			modelMarket.addElement(item.getName());
		}
		listMarket.setBounds(180, 44, 150, 256);
		frame.getContentPane().add(listMarket);
		
		JList<String> listUser = new JList<String>();
		DefaultListModel<String> modelUser = new DefaultListModel<String>();
		listUser.setModel(modelUser);
		for (Item item : GameEnvironment.getInventory()) {
			modelUser.addElement(item.getName());
		}
		listUser.setBounds(12, 160, 150, 249);
		frame.getContentPane().add(listUser);
		
		JPanel panelAthleteInfoBox = new JPanel();
		panelAthleteInfoBox.setLayout(null);
		panelAthleteInfoBox.setBackground(Color.WHITE);
		panelAthleteInfoBox.setBounds(342, 44, 246, 200);
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
		txtDescription.setBackground(new Color(222, 221, 218));
		txtDescription.setBounds(12, 104, 222, 45);
		panelAthleteInfoBox.add(txtDescription);
		
		JLabel lblPrice = new JLabel("");
		lblPrice.setBounds(12, 173, 222, 15);
		panelAthleteInfoBox.add(lblPrice);
		
		JLabel lblUserList = new JLabel("Your Inventory");
		lblUserList.setBounds(12, 133, 150, 15);
		frame.getContentPane().add(lblUserList);
		
		JToggleButton tglbtnItems = new JToggleButton("Items");
		tglbtnItems.setSelected(true);
		tglbtnItems.setBounds(180, 307, 150, 45);
		frame.getContentPane().add(tglbtnItems);
		
		JToggleButton tglbtnAthletes = new JToggleButton("Athletes");
		tglbtnAthletes.setBounds(180, 364, 150, 45);
		frame.getContentPane().add(tglbtnAthletes);
		
		JButton btnBuySell = new JButton("BUY");
		btnBuySell.setFont(new Font("Dialog", Font.BOLD, 40));
		btnBuySell.setBounds(342, 256, 246, 153);
		frame.getContentPane().add(btnBuySell);
		
		JButton btnBack = new JButton("Go Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchHomeScreen();
				finishedWindow();
			}
		});
		btnBack.setBounds(10, 44, 117, 25);
		frame.getContentPane().add(btnBack);
		
		JLabel lblMoney = new JLabel("$" + GameEnvironment.getMoney());
		lblMoney.setFont(new Font("Dialog", Font.BOLD, 20));
		lblMoney.setBounds(12, 81, 150, 40);
		frame.getContentPane().add(lblMoney);
		
		tglbtnItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnItems.isSelected()) {
					tglbtnAthletes.setSelected(false);
					
					lblPurchaseName.setText("");	
					pBarStamina.setValue(0);
					pBarOffence.setValue(0);
					pBarDefence.setValue(0);
					txtDescription.setText("");
					lblPrice.setText("");
					
					lblUserList.setText("Your Inventory");
					
					modelUser.clear();
					modelMarket.clear();
					for (Item item : GameEnvironment.getInventory()) {
						modelUser.addElement(item.getName());
					}
					for (Item item: GameEnvironment.getCurrentWeekMarketItems()) {
						modelMarket.addElement(item.getName());
					}
					
					
				}
				else {
					tglbtnItems.setSelected(true);
				}
			}
		});
		
		tglbtnAthletes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnAthletes.isSelected()) {
					tglbtnItems.setSelected(false);
					
					lblPurchaseName.setText("");
					pBarStamina.setValue(0);
					pBarOffence.setValue(0);
					pBarDefence.setValue(0);
					txtDescription.setText("");
					lblPrice.setText("");
					
					lblUserList.setText("Your Team");
					
					modelUser.clear();
					modelMarket.clear();
					for (Athlete athlete : GameEnvironment.getPlayerTeam().getPlayers()) {
						modelUser.addElement(athlete.getName());
					}
					for (Athlete athlete : GameEnvironment.getCurrentWeekMarketAthletes()){
						modelMarket.addElement(athlete.getName());
					}
				}
				else {
					tglbtnAthletes.setSelected(true);
				}
			}
		});
		
		listUser.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				if (listUser.isSelectionEmpty()) {
					return;
				}
				if (tglbtnItems.isSelected()) {
					Item selectedItem = GameEnvironment.getInventory().get(listUser.getSelectedIndex());
					lblPurchaseName.setText(selectedItem.getName());
					txtDescription.setText(selectedItem.getDescription());
					pBarStamina.setValue(selectedItem.getEffect()[0]);
					pBarOffence.setValue(selectedItem.getEffect()[1]);
					pBarDefence.setValue(selectedItem.getEffect()[2]);
					lblPrice.setText("Sell Price: $" + selectedItem.getSellPrice());
				} else {
					Athlete selectedAthlete = GameEnvironment.getPlayerTeam().get(listUser.getSelectedIndex());
					lblPurchaseName.setText(selectedAthlete.getName());
					txtDescription.setText(selectedAthlete.getDescription());
					pBarStamina.setValue(selectedAthlete.getStats()[0]);
					pBarOffence.setValue(selectedAthlete.getStats()[1]);
					pBarDefence.setValue(selectedAthlete.getStats()[2]);
					lblPrice.setText("Sell Price: $" + selectedAthlete.getSellPrice());
				}
				listMarket.clearSelection();
				btnBuySell.setText("SELL");
			}
		});
		
		listMarket.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				if (listMarket.isSelectionEmpty()) {
					return;
				}
				if (tglbtnItems.isSelected()) {
					Item selectedItem = GameEnvironment.getCurrentWeekMarketItems().get(listMarket.getSelectedIndex());
					lblPurchaseName.setText(selectedItem.getName());
					txtDescription.setText(selectedItem.getDescription());
					pBarStamina.setValue(selectedItem.getEffect()[0]);
					pBarOffence.setValue(selectedItem.getEffect()[1]);
					pBarDefence.setValue(selectedItem.getEffect()[2]);
					lblPrice.setText("Cost: $" + selectedItem.getPrice());
				} else {
					Athlete selectedAthlete = GameEnvironment.getCurrentWeekMarketAthletes().get(listMarket.getSelectedIndex());
					lblPurchaseName.setText(selectedAthlete.getName());
					txtDescription.setText(selectedAthlete.getDescription());
					pBarStamina.setValue(selectedAthlete.getStats()[0]);
					pBarOffence.setValue(selectedAthlete.getStats()[1]);
					pBarDefence.setValue(selectedAthlete.getStats()[2]);
					lblPrice.setText("Cost: $" + selectedAthlete.getPrice());
				}
				listUser.clearSelection();
				btnBuySell.setText("BUY");
			}
		});
		
		btnBuySell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listUser.isSelectionEmpty() && listMarket.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "You haven't selected anything to " + btnBuySell.getText(), "Error", 0);
					return;
				}
				
				if (tglbtnItems.isSelected()) {
					if (!listMarket.isSelectionEmpty()) {
						//buying an item
						Item selectedItem = GameEnvironment.getCurrentWeekMarketItems().get(listMarket.getSelectedIndex());
						
						if (GameEnvironment.getMoney() < selectedItem.getPrice()) {
							JOptionPane.showMessageDialog(null, "You don't have enough money", "Error", 0);			
							return;
						}
						
						if (GameEnvironment.getInventory().size() >= GameEnvironment.MAX_ITEMS) {
							JOptionPane.showMessageDialog(null, "You don't have any space in your inventory", "Error", 0);			
							return;
						}
						
						GameEnvironment.setMoney(GameEnvironment.getMoney() - selectedItem.getPrice());
						GameEnvironment.getInventory().add(selectedItem);
						GameEnvironment.getCurrentWeekMarketItems().remove(listMarket.getSelectedIndex());
						
					} else {
						//selling an item
						Item selectedItem = GameEnvironment.getInventory().get(listUser.getSelectedIndex());
						GameEnvironment.setMoney(GameEnvironment.getMoney() + selectedItem.getSellPrice());
						GameEnvironment.getInventory().remove(listUser.getSelectedIndex());
					}
					
					modelUser.clear();
					modelMarket.clear();
					for (Item item : GameEnvironment.getInventory()) {
						modelUser.addElement(item.getName());
					}
					for (Item item: GameEnvironment.getCurrentWeekMarketItems()) {
						modelMarket.addElement(item.getName());
					}
					
				} 
				else if (tglbtnAthletes.isSelected()) {
					if (!listMarket.isSelectionEmpty()) {
						//buying an athlete
						Athlete selectedAthlete = GameEnvironment.getCurrentWeekMarketAthletes().get(listMarket.getSelectedIndex());
						
						if (GameEnvironment.getMoney() < selectedAthlete.getPrice()){
							JOptionPane.showMessageDialog(null, "You don't have enough money", "Error", 0);			
							return;
						}
						
						if (GameEnvironment.getPlayerTeam().size() >= GameEnvironment.MAX_PLAYERS) {
							JOptionPane.showMessageDialog(null, "You don't have any space in your team", "Error", 0);
							return;
						}
						
						GameEnvironment.setMoney(GameEnvironment.getMoney() - selectedAthlete.getPrice());
						GameEnvironment.getPlayerTeam().add(selectedAthlete);
						GameEnvironment.getCurrentWeekMarketAthletes().remove(listMarket.getSelectedIndex());
					} else {
						//selling an athlete
						Athlete selectedAthlete = GameEnvironment.getPlayerTeam().get(listUser.getSelectedIndex());
						GameEnvironment.setMoney(GameEnvironment.getMoney() + selectedAthlete.getSellPrice());
						GameEnvironment.getPlayerTeam().remove(listUser.getSelectedIndex());
					}
					
					modelUser.clear();
					modelMarket.clear();
					for (Athlete athlete : GameEnvironment.getPlayerTeam().getPlayers()) {
						modelUser.addElement(athlete.getName());
					}
					for (Athlete athlete : GameEnvironment.getCurrentWeekMarketAthletes()){
						modelMarket.addElement(athlete.getName());
					}
				}
				
				lblPurchaseName.setText("");
				pBarStamina.setValue(0);
				pBarOffence.setValue(0);
				pBarDefence.setValue(0);
				txtDescription.setText("");
				lblPrice.setText("");
				lblMoney.setText("$" + GameEnvironment.getMoney());
			}
		});
	}
}
