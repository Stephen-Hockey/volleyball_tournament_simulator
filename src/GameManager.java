
public class GameManager {
	
	public void launchSetUpScreen() {
		SetUpScreen setUpWindow = new SetUpScreen(this);
	}
	
	public void closeSetUpScreen(SetUpScreen setUpWindow) {
		setUpWindow.closeSetUpScreen();
		launchDraftScreen();
	}
	
	public void launchDraftScreen() {
		DraftScreen draftWindow = new DraftScreen(this);
	}
	
	public void closeDraftScreen(DraftScreen draftWindow) {
		draftWindow.closeWindow();
		launchHomeScreen();
	}
	
	public void launchHomeScreen() {
		HomeScreen homeWindow = new HomeScreen(this);
	}
	
	public void closeHomeScreen(HomeScreen homeWindow) {
		homeWindow.closeWindow();
	}

	public void launchMarketScreen() {
		MarketScreen marketWindow = new MarketScreen(this);
	}
	
	public void closeMarketScreen(MarketScreen marketWindow) {
		marketWindow.closeWindow();
	}
	
	public void launchStadiumScreen() {
		StadiumScreen stadiumWindow = new StadiumScreen(this);
	}
	
	public void closeStadiumScreen(StadiumScreen stadiumWindow) {
		stadiumWindow.closeWindow();
	}
	
	public void launchEndScreen() {
		EndScreen endWindow = new EndScreen(this);
	}
	
	public void closeEndScreen(EndScreen endWindow) {
		endWindow.closeWindow();
	}
	
	public void launchClubScreen() {
		EndScreen endWindow = new EndScreen(this);
	}
	
	public void closeClubScreen(ClubScreen clubWindow) {
		clubWindow.closeWindow();
	}
	
	
	public static void main(String[] args) {
		
		GameManager manager = new GameManager();
		manager.launchSetUpScreen();
		
		//ScreenManager.setUpGame()
		//ScreenManager.teamDraft()
		//ScreenManager.giveNicknames()
		/*while (Game.isRunning()) {
			Game.generateOpposingTeams();
			Game.generateMarketAthletes();
			Game.generateMarketItems();
			
			//ScreenManager.homeScreen()
			int week = Game.getWeek();
			Game.setWeek(week++);*/
	}
		
}
    
