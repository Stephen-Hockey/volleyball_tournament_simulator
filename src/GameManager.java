
public class GameManager {

	public void launchDraftScreen() {
		DraftScreen draftWindow = new DraftScreen(this);
	}
	
	public void closeDraftScreen(DraftScreen draftWindow) {
		draftWindow.closeWindow();
		//launchHomeScreen()
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
