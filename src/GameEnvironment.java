
import java.util.ArrayList;

/**
 * This class implements the environment for the game
 * all current game variables and methods are stored 
 *
 * @author Lachlan Stewart and Stephen Hockey
 * @version 1.1, May 2023.
 */

public class GameEnvironment {
	
	/**
	 * The name of the Player/User
	 */
    private static String playerName;
    /**
	 * The current week of the game
	 */
    private static int week;
    /**
	 * The number of weeks given by the user for game duration
	 */
    private static int finalWeek;
    /**
	 * The Player/User's Team
	 */
    private static Team playerTeam;
    /**
	 * The game difficulty
	 */
    private static int difficulty;
    /**
	 * The Player/User's money
	 */
    private static int money;
    /**
	 * The Player/User's current games rating
	 */
    private static int playerRating;
    /**
	 * A record of the current game's wins, losses and byes without a game {wins, losses, byes}
	 */
    private static int[] record;
    /**
	 * The Player/User's items
	 */
    private static ArrayList<Item> inventory;
    /**
	 * The Athletes currently available to purchase in the marketplace
	 */
    private static ArrayList<Athlete> currentWeekMarketAthletes;
    /**
	 * The Items currently available to purchase in the marketplace
	 */
    private static ArrayList<Item> currentWeekMarketItems;
    /**
   	 * The Opponents currently available to play in the Stadium
   	 */
    private static ArrayList<Team> currentWeekOpposingTeams;
    /**
   	 * A record of past matches against opponent teams
   	 */
    private static ArrayList<Match> matches;
    /**
   	 * A record of if the Player/User has played their weekly alloted game
   	 */
    private static boolean weeklyGamePlayed;
    /**
   	 * Represents if the Player/User successfully completed the game
   	 */
    private static boolean gameSuccess;
    /**
   	 * Sets the maximum number of players and items allowed in the Player/User's team
   	 */
    public static final int MAX_PLAYERS = 12, MAX_ITEMS= 12;

    
    
    /**
   	 * Returns the Player/User's name
   	 * @return the Player/User's name
   	 */
    public static String getPlayerName() {
        return playerName;
    }

    
    public static void setPlayerName(String _playerName) {
        playerName = _playerName;
    }

    /**
   	 * Returns the current in game week as an integer
   	 * @return the current game week as an integer
   	 */
    public static int getWeek() {
        return week;
    }

    public static void setWeek(int _week) {
        week = _week;
    }

    /**
   	 * Returns the chosen game length as an integer
   	 * @return game length as an integer
   	 */
    public static int getFinalWeek() {
        return finalWeek;
    }

    public static void setFinalWeek(int _finalWeek) {
        finalWeek = _finalWeek;
    }

    /**
   	 * Returns the Player/User's Team as a Team object
   	 * @return the Player/User's Team as a Team object
   	 */
    public static Team getPlayerTeam() {
        return playerTeam;
    }

    public static void setPlayerTeam(Team _playerTeam) {
        playerTeam = _playerTeam;
    }
    
    /**
   	 * Returns the chosen difficulty as an integer
   	 * @return the chosen difficulty as an integer
   	 */
    public static int getDifficulty() {
        return difficulty;
    }

    
    public static void setDifficulty(int _difficulty) {
        difficulty = _difficulty;
    }

    /**
   	 * Returns the Player/User's items as an ArrayList of Item objects
   	 * @return the Player/User's items as an ArrayList of Item objects
   	 */
    public static ArrayList<Item> getInventory() {
        return inventory;
    }

    public static void setInventory(ArrayList<Item> _inventory) {
        inventory = _inventory;
    }

    /**
   	 * Returns the Player/User's money as an integer
   	 * @return the Player/User's money as an integer
   	 */
    public static int getMoney() {
        return money;
    }

    public static void setMoney(int _money) {
        money = _money;
    }

    /**
   	 * Returns the Player/User's rating as an integer
   	 * @return the Player/User's rating as an integer
   	 */
    public static int getPlayerRating() {
        return playerRating;
    }

    public static void setPlayerRating(int _playerRating) {
        playerRating = _playerRating;
    }
    
    /**
   	 * Returns the current game's wins, losses and byes as an integer list of the form {wins, losses, byes}
   	 * @return the current game's wins, losses and byes as an integer list of the form {wins, losses, byes}
   	 */
    public static int[] getRecord() {
        return record;
    }
    public static void setRecord(int[] _record) {
        record = _record;
    }
    
    /**
   	 * Returns the Player/User's past match record as an ArrayList of Match objects
   	 * @return the Player/User's past match record as an ArrayList of Match objects
   	 */
    public static ArrayList<Match> getMatches() {
		return matches;
	}
    
    
    public static void setMatches(ArrayList<Match> _matches) {
		GameEnvironment.matches = _matches;
	}
    
    
    public static void addMatch(Match match) {
    	GameEnvironment.matches.add(match);
    }
 
    /**
   	 * Returns the value of the weekly game played as a boolean
   	 * @return the value of the weekly game played as a boolean
   	 */
    public static boolean getWeeklyGamePlayed() {
    	return weeklyGamePlayed;
    }
    
    public static void setWeeklyGamePlayed(boolean _weeklyGamePlayed) {
    	weeklyGamePlayed = _weeklyGamePlayed;
    }
    
    /**
   	 * Returns the Player/User successfully completed the game as a boolean
   	 * @return the Player/User successfully completed the game as a boolean
   	 */
    public static boolean getGameSuccess() {
    	return gameSuccess;
    }
    
    public static void setGameSuccess(boolean _gameSuccess) {
    	gameSuccess = _gameSuccess;
    }
    
    /**
   	 * Returns the current athletes available for purchase from the marketplace an ArrayList of Athlete objects
   	 * @return the current athletes available for purchase from the marketplace an ArrayList of Athlete objects
   	 */
    public static ArrayList<Athlete> getCurrentWeekMarketAthletes() {
		return currentWeekMarketAthletes;
	}
    
    /**
   	 * Returns the current items available for purchase from the marketplace an ArrayList of Item objects
   	 * @return the current items available for purchase from the marketplace an ArrayList of Item objects
   	 */
    public static ArrayList<Item> getCurrentWeekMarketItems() {
		return currentWeekMarketItems;
	}
    
    /**
   	 * Returns the Opponents currently available to play in the Stadium as an ArrayList of Team objects
   	 * @return the Opponents currently available to play in the Stadium as an ArrayList of Team objects
   	 */
    public static ArrayList<Team> getCurrentWeekOpposingTeams() {
		return currentWeekOpposingTeams;
    }
    
    /**
   	 * If the Player/User can make a valid team with their current athletes as a boolean
   	 * @return If the Player/User can make a valid team with their current athletes as a boolean
   	 */
    public static boolean hasFullTeam() {
    	int healthyPlayers = 0;
    	for (Athlete athlete : playerTeam.getPlayers()) {
    		if (athlete.getStats()[0] > 0) {
    			healthyPlayers++;
    		}
    	}
    	return healthyPlayers >= 7;
    }
    
    /**
   	 * If the Player/User's current line up is valid as a boolean
   	 * @return If the Player/User's current line up is valid as a boolean
   	 */
    public static boolean hasHealthyStarters() {
    	for (int i = 0; i < 7; i++) {
    		if (playerTeam.get(i).getStats()[0] == 0) {
    			return false;
    		}
    	}
    	return true;
    }
    
    /**
   	 * Sets up all values for the next week, such as, renewing the market, generating new opponents and increments the week counter
   	 */
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
