package main;

import java.util.ArrayList;

/**
 * The GameEnvironment class stores all game variables that made
 * sense to be static, such as the user's team and inventory. <br>
 * The class also contains static methods that made sense to be available to all classes
 * without creating an instance of the GameEnvironment class.
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
   	 * @param newPlayerName the Player/User's new name
   	 */
    public static void setPlayerName(String newPlayerName) {
        playerName = newPlayerName;
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
   	 * @param newWeek the new current in game week
   	 */
    public static void setWeek(int newWeek) {
        week = newWeek;
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
   	 * @param newFinalWeek the new chosen game length
   	 */
    public static void setFinalWeek(int newFinalWeek) {
        finalWeek = newFinalWeek;
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
   	 * @param newPlayerTeam the new Player/User's Team
   	 */
    public static void setPlayerTeam(Team newPlayerTeam) {
        playerTeam = newPlayerTeam;
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
   	 * @param newDifficulty the new chosen difficulty
   	 */
    public static void setDifficulty(int newDifficulty) {
        difficulty = newDifficulty;
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
   	 * @param newInventory the new Player/User's inventory items
   	 */
    public static void setInventory(ArrayList<Item> newInventory) {
        inventory = newInventory;
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
   	 * @param newMoney the new Player/User's inventory items
   	 */
    public static void setMoney(int newMoney) {
        money = newMoney;
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
   	 * @param newPlayerRating the new Player/User's rating
   	 */
    public static void setPlayerRating(int newPlayerRating) {
        playerRating = newPlayerRating;
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
   	 * @param newRecord the new current game's wins, losses and byes
   	 */
    public static void setRecord(int[] newRecord) {
        record = newRecord;
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
   	 * @param newMatches the new Player/User's past match record
   	 */
    public static void setMatches(ArrayList<Match> newMatches) {
		GameEnvironment.matches = newMatches;
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
   	 * @param newWeeklyGamePlayed the new value of the weekly game played
   	 */
    public static void setWeeklyGamePlayed(boolean newWeeklyGamePlayed) {
    	weeklyGamePlayed = newWeeklyGamePlayed;
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
   	 * @param newGameSuccess the new game success value
   	 */
    public static void setGameSuccess(boolean newGameSuccess) {
    	gameSuccess = newGameSuccess;
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
   	 * appends a match to the Player/User's past match record
   	 * @param match the new match to append to the Player/User's past match record
   	 */
    public static void addMatch(Match match) {
    	GameEnvironment.matches.add(match);
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
    	if (playerTeam.size() < 7) {
    		return false;
    	}
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
