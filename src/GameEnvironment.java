package senggui;

import java.util.ArrayList;

public class GameEnvironment {
    private static String playerName;
    private static String teamName; 
    private static int week;
    private static int finalWeek;
    private static Team playerTeam;
    private static int difficulty;
    private static int money;
    private static int playerRating;
    private static int[] record; // wins, losses, byes without a game
    private static ArrayList<Item> inventory;
    private static ArrayList<Athlete> currentWeekMarketAthletes;
    private static ArrayList<Item> currentWeekMarketItems;
    private static ArrayList<Team> currentWeekOpposingTeams;
    private static ArrayList<Match> matches;
    private static boolean gameRunning;
    private static boolean weeklyGamePlayed;
    
    public static final int MAX_PLAYERS = 12, MAX_ITEMS= 12;

    public static String getPlayerName() {
        return playerName;
    }

    public static void setPlayerName(String _playerName) {
        playerName = _playerName;
    }

    public static String getTeamName() {
		return teamName;
	}
    
    public static void setTeamName(String _teamName) {
		GameEnvironment.teamName = _teamName;
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
    
    public static ArrayList<Match> getMatches() {
		return matches;
	}
    
    public static void setMatches(ArrayList<Match> _matches) {
		GameEnvironment.matches = _matches;
	}
    
    public static void addMatch(Match match) {
    	GameEnvironment.matches.add(match);
    }

    public static boolean getGameRunning() {
        return gameRunning;
    }

    public static void setGameRunning(boolean _gameRunning) {
        gameRunning = _gameRunning;
    }
    
    public static boolean getWeeklyGamePlayed() {
    	return weeklyGamePlayed;
    }
    
    public static void setWeeklyGamePlayed(boolean _weeklyGamePlayed) {
    	weeklyGamePlayed = _weeklyGamePlayed;
    }
    
    public static ArrayList<Athlete> getCurrentWeekMarketAthletes() {
		return currentWeekMarketAthletes;
	}
    
    public static ArrayList<Item> getCurrentWeekMarketItems() {
		return currentWeekMarketItems;
	}
    
    public static ArrayList<Team> getCurrentWeekOpposingTeams() {
		return currentWeekOpposingTeams;
    }
    
    public static void setUpWeek() {
    	currentWeekMarketAthletes = new ArrayList<Athlete>();
    	currentWeekMarketItems = new ArrayList<Item>();
    	currentWeekOpposingTeams = new ArrayList<Team>();
    	weeklyGamePlayed = false;
    	week += 1;
    	
    	for (int i = 0; i < 5; i++) {
            currentWeekMarketAthletes.add(Athlete.generateAthlete(GameEnvironment.getWeek()));
        }
    	
    	for (int i = 0; i < 5; i++) {
            currentWeekMarketItems.add(Item.generateItem());
        }
    	
        for (int i = 0; i < 3; i++) {
            currentWeekOpposingTeams.add(Team.generateTeam(GameEnvironment.getWeek() * (GameEnvironment.getDifficulty() + 1)));
        }        
    }
}
