
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
   	 * @return the Player/User's name as a string
   	 */
    public static String getPlayerName() {
        return playerName;
    }

    /**
   	 * Sets the Player/User's name
   	 * @param the Player/User's new name
   	 */
    public static void setPlayerName(String _playerName) {
        playerName = _playerName;
    }

    /**
   	 * Returns the current in game week
   	 * @return the current game week as an integer
   	 */
    public static int getWeek() {
        return week;
    }

    /**
   	 * Sets the current in game week
   	 * @param the new current in game week
   	 */
    public static void setWeek(int _week) {
        week = _week;
    }

    /**
   	 * Returns the chosen game length
   	 * @return the chosen game length as an integer
   	 */
    public static int getFinalWeek() {
        return finalWeek;
    }

    /**
   	 * Sets the chosen game length
   	 * @param the new chosen game length
   	 */
    public static void setFinalWeek(int _finalWeek) {
        finalWeek = _finalWeek;
    }

    /**
   	 * Returns the Player/User's Team
   	 * @return the Player/User's Team as a Team object
   	 */
    public static Team getPlayerTeam() {
        return playerTeam;
    }

    /**
   	 * Sets the Player/User's Team
   	 * @param the new Player/User's Team
   	 */
    public static void setPlayerTeam(Team _playerTeam) {
        playerTeam = _playerTeam;
    }
    
    /**
   	 * Returns the chosen difficulty
   	 * @return the chosen difficulty as an integer
   	 */
    public static int getDifficulty() {
        return difficulty;
    }

    /**
   	 * Sets the chosen difficulty
   	 * @param the new chosen difficulty
   	 */
    public static void setDifficulty(int _difficulty) {
        difficulty = _difficulty;
    }

    /**
   	 * Returns the Player/User's inventory items
   	 * @return the Player/User's inventory items as an ArrayList of Item objects
   	 */
    public static ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
   	 * Sets the Player/User's inventory items
   	 * @param the new Player/User's inventory items
   	 */
    public static void setInventory(ArrayList<Item> _inventory) {
        inventory = _inventory;
    }

    /**
   	 * Returns the Player/User's money
   	 * @return the Player/User's money as an integer
   	 */
    public static int getMoney() {
        return money;
    }

    /**
   	 * Sets the Player/User's inventory items
   	 * @param the new Player/User's inventory items
   	 */
    public static void setMoney(int _money) {
        money = _money;
    }

    /**
   	 * Returns the Player/User's rating
   	 * @return the Player/User's rating as an integer
   	 */
    public static int getPlayerRating() {
        return playerRating;
    }

    /**
   	 * Sets the Player/User's rating
   	 * @param the new Player/User's rating
   	 */
    public static void setPlayerRating(int _playerRating) {
        playerRating = _playerRating;
    }
    
    /**
   	 * Returns the current game's wins, losses and byes
   	 * @return the current game's wins, losses and byes as an integer list of the form {wins, losses, byes}
   	 */
    public static int[] getRecord() {
        return record;
    }
    
    /**
   	 * Sets the current game's wins, losses and byes
   	 * @param the new current game's wins, losses and byes
   	 */
    public static void setRecord(int[] _record) {
        record = _record;
    }
    
    /**
   	 * Returns the Player/User's past match record
   	 * @return the Player/User's past match record as an ArrayList of Match objects
   	 */
    public static ArrayList<Match> getMatches() {
		return matches;
	}
    
    /**
   	 * Sets the Player/User's past match record
   	 * @param the new Player/User's past match record
   	 */
    public static void setMatches(ArrayList<Match> _matches) {
		GameEnvironment.matches = _matches;
	}
    
    /**
   	 * appends a match to the Player/User's past match record
   	 * @param the new match to append to the Player/User's past match record
   	 */
    public static void addMatch(Match match) {
    	GameEnvironment.matches.add(match);
    }
 
    /**
   	 * Returns the value of the weekly game played
   	 * @return the value of the weekly game played as a boolean
   	 */
    public static boolean getWeeklyGamePlayed() {
    	return weeklyGamePlayed;
    }
    
    /**
   	 * Sets the value of the weekly game played
   	 * @param the new value of the weekly game played
   	 */
    public static void setWeeklyGamePlayed(boolean _weeklyGamePlayed) {
    	weeklyGamePlayed = _weeklyGamePlayed;
    }
    
    /**
   	 * Returns the Player/User successfully completed the game
   	 * @return the Player/User successfully completed the game as a boolean
   	 */
    public static boolean getGameSuccess() {
    	return gameSuccess;
    }
    
    /**
   	 * Sets the Player/User successfully completed the game
   	 * @param the new game success value
   	 */
    public static void setGameSuccess(boolean _gameSuccess) {
    	gameSuccess = _gameSuccess;
    }
    
    /**
   	 * Returns the current athletes available for purchase from the marketplace
   	 * @return the current athletes available for purchase from the marketplace as an ArrayList of Athlete objects
   	 */
    public static ArrayList<Athlete> getCurrentWeekMarketAthletes() {
		return currentWeekMarketAthletes;
	}
    
    /**
   	 * Returns the current items available for purchase from the marketplace
   	 * @return the current items available for purchase from the marketplace as an ArrayList of Item objects
   	 */
    public static ArrayList<Item> getCurrentWeekMarketItems() {
		return currentWeekMarketItems;
	}
    
    /**
   	 * Returns the Opponents currently available to play in the Stadium
   	 * @return the Opponents currently available to play in the Stadium as an ArrayList of Team objects
   	 */
    public static ArrayList<Team> getCurrentWeekOpposingTeams() {
		return currentWeekOpposingTeams;
    }
    
    /**
   	 * If the Player/User can make a valid team with their current athletes
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
   	 * If the Player/User's current line up is valid
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
