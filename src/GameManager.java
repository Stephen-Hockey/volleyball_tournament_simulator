package senggui;

public class GameManager {
	
	public void launchSetUpScreen() {
		SetUpScreen setUpWindow = new SetUpScreen(this);
	}
	
	public void closeSetUpScreen(SetUpScreen setUpWindow) {
		setUpWindow.closeSetUpScreen();
	}
	
	public void launchDraftScreen() {
		DraftScreen draftWindow = new DraftScreen(this);
	}
	
	public void closeDraftScreen(DraftScreen draftWindow) {
		draftWindow.closeWindow();
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
		ClubScreen clubWindow = new ClubScreen(this);
	}
	
	public void closeClubScreen(ClubScreen clubWindow) {
		clubWindow.closeWindow();
	}
	
	public void launchMatchScreen() {
		MatchScreen matchScreen = new MatchScreen(this);
	}
	
	public void closeMatchScreen(MatchScreen matchScreen) {
		matchScreen.closeWindow();
	}
	
	public void launchNickNameScreen() {
		NickNameScreen nickNameScreen = new NickNameScreen(this);
	}
	
	public void closeNickNameScreen(NickNameScreen nickNameScreen) {
		nickNameScreen.closeWindow();
	}
	
	public static void main(String[] args) {
		
		GameManager manager = new GameManager();
		manager.launchSetUpScreen();
	}
		
}
    
