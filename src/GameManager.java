
public class GameManager {
	
	public void launchSetUpScreen() {
		SetUpScreen setUpScreen = new SetUpScreen(this);
	}
	
	public void closeSetUpScreen(SetUpScreen setUpScreen) {
		setUpScreen.closeSetUpScreen();
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
    
