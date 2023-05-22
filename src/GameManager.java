package main;

import screens.ClubScreen;
import screens.DraftScreen;
import screens.EndScreen;
import screens.HomeScreen;
import screens.MarketScreen;
import screens.MatchScreen;
import screens.NicknameScreen;
import screens.SetUpScreen;
import screens.StadiumScreen;

/**
 * The GameManager class allows for easy control of the GUI <br>
 * i.e The launching and closing of various screens
 *
 * @author Lachlan Stewart and Stephen Hockey
 * @version 1.1, May 2023.
 */
public class GameManager {
	
	/**
	 * Launches the Set Up Screen
	 */
	public void launchSetUpScreen() {
		SetUpScreen setUpWindow = new SetUpScreen(this);
	}
	
	/**
	 * Closes the Set Up Screen
	 * @param setUpWindow window to close as a SetUpScreen object
	 */
	public void closeSetUpScreen(SetUpScreen setUpWindow) {
		setUpWindow.closeSetUpScreen();
	}
	
	/**
	 * Launches the Draft Screen
	 */
	public void launchDraftScreen() {
		DraftScreen draftWindow = new DraftScreen(this);
	}
	
	/**
	 * Closes the Draft Screen
	 * @param draftWindow window to close as a DraftScreen object
	 */
	public void closeDraftScreen(DraftScreen draftWindow) {
		draftWindow.closeWindow();
	}
	
	/**
	 * Launches the Home Screen
	 */
	public void launchHomeScreen() {
		HomeScreen homeWindow = new HomeScreen(this);
	}
	
	/**
	 * Closes the Home Screen
	 * @param homeWindow window to close as a HomeScreen object
	 */
	public void closeHomeScreen(HomeScreen homeWindow) {
		homeWindow.closeWindow();
	}

	/**
	 * Launches the Market Screen
	 */
	public void launchMarketScreen() {
		MarketScreen marketWindow = new MarketScreen(this);
	}
	
	/**
	 * Closes the Market Screen
	 * @param marketWindow window to close as a MarketScreen object
	 */
	public void closeMarketScreen(MarketScreen marketWindow) {
		marketWindow.closeWindow();
	}
	
	/**
	 * Launches the Stadium Screen
	 */
	public void launchStadiumScreen() {
		StadiumScreen stadiumWindow = new StadiumScreen(this);
	}
	
	/**
	 * Closes the Stadium Screen
	 * @param stadiumWindow window to close as a StadiumScreen object
	 */
	public void closeStadiumScreen(StadiumScreen stadiumWindow) {
		stadiumWindow.closeWindow();
	}
	
	/**
	 * Launches the End Screen
	 */
	public void launchEndScreen() {
		EndScreen endWindow = new EndScreen(this);
	}
	
	/**
	 * Closes the End Screen
	 * @param endWindow window to close as a EndScreen object
	 */
	public void closeEndScreen(EndScreen endWindow) {
		endWindow.closeWindow();
	}
	
	/**
	 * Launches the Club Screen
	 */
	public void launchClubScreen() {
		ClubScreen clubWindow = new ClubScreen(this);
	}
	
	/**
	 * Closes the Club Screen
	 * @param clubWindow window to close as a ClubScreen object
	 */
	public void closeClubScreen(ClubScreen clubWindow) {
		clubWindow.closeWindow();
	}
	
	/**
	 * Launches the Match Screen
	 */
	public void launchMatchScreen() {
		MatchScreen matchScreen = new MatchScreen(this);
	}
	
	/**
	 * Closes the Match Screen
	 * @param matchScreen window to close as a MatchScreen object
	 */
	public void closeMatchScreen(MatchScreen matchScreen) {
		matchScreen.closeWindow();
	}
	
	/**
	 * Launches the Nickname Screen
	 */
	public void launchNicknameScreen() {
		NicknameScreen nicknameWindow = new NicknameScreen(this);
	}
	
	/**
	 * Closes the Nickname Screen
	 * @param nicknameWindow window to close as a NickNameScreen object
	 */
	public void closeNicknameScreen(NicknameScreen nicknameWindow) {
		nicknameWindow.closeWindow();
	}
	
	/**
	 * Initialises the game manager and launches the set up screen
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		
		GameManager manager = new GameManager();
		manager.launchSetUpScreen();
	}
		
}
    
