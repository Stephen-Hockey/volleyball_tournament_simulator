package senggui;

public class GameManager {
	
	public void launchSetUpScreen() {
		SetUpScreen setUpScreen = new SetUpScreen(this);
	}
	
	public void closeSetUpScreen(SetUpScreen setUpScreen) {
		setUpScreen.closeSetUpScreen();
	}
	
	/*
	public static void teamDraft() {
		//calls draft window and sets the games team as the drafted team
	}
	
	public static void giveNicknames() {
		//calls nickname window and updates players with nicknames
	}
	
	public static void mainMenu() {
		//shows main menu
	}*/
	
	
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
    
