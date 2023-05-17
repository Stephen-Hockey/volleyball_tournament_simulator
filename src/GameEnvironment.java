import java.util.ArrayList;

public class GameEnvironment {
    private static String playerName;
    private static int week;
    private static int finalWeek;
    private static Team playerTeam;
    private static int difficulty;
    private static int money;
    private static int playerRating;
    private static int[] record; // wins, losses, byes without a game
    private static ArrayList<Item> inventory;
    private static boolean gameRunning;

    public static String getPlayerName() {
        return playerName;
    }

    public static void setPlayerName(String _playerName) {
        playerName = _playerName;
    }

    public static int getWeek() {
        return week;
    }

    public static void setWeek(int _week) {
        week = _week;
    }

    public static int getFinalWeek() {
        return finalWeek;
    }

    public static void setFinalWeek(int _finalWeek) {
        finalWeek = _finalWeek;
    }

    public static Team getPlayerTeam() {
        return playerTeam;
    }

    public static void setPlayerTeam(Team _playerTeam) {
        playerTeam = _playerTeam;
    }

    public static int getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(int _difficulty) {
        difficulty = _difficulty;
    }

    public static ArrayList<Item> getInventory() {
        return inventory;
    }

    public static void setInventory(ArrayList<Item> _inventory) {
        inventory = _inventory;
    }

    public static int getMoney() {
        return money;
    }

    public static void setMoney(int _money) {
        money = _money;
    }

    public static int getPlayerRating() {
        return playerRating;
    }

    public static void setPlayerRating(int _playerRating) {
        playerRating = _playerRating;
    }

    public static int[] getRecord() {
        return record;
    }

    public static void setRecord(int[] _record) {
        record = _record;
    }

    public static boolean getGameRunning() {
        return gameRunning;
    }

    public static void setGameRunning(boolean _gameRunning) {
        gameRunning = _gameRunning;
    }
}
