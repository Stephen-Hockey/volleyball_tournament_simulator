
public class Main {

    public static void main(String[] args) {
        GameFunction.setupGame();
        while (GameEnvironment.getGameRunning()) {
            GameFunction.playWeek();
        }
        GameFunction.endGame();
    }
}
