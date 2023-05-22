package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
/**
 * The Team class describes a named team of 0-12 athletes
 * It contains a static method for randomly generating a new team.
 * @author Lachlan Stewart and Stephen Hockey
 * @version 1.1, May 2023.
 */
public class Team {

	/**
	 * The name of the team
	 */
    private String teamName;
    
    /**
     * The athletes in the team
     */
    private ArrayList<Athlete> players;
        
    /**
     * A constant String array of the names of the 7 starting volleyball positions
     */
    public static final String[] POSITION_STRINGS = new String[] { "Setter", "Outside 1", "Middle 1", "Opposite",
            "Outside 2", "Middle 2", "Libero" };

    /**
     * Constructs a new Team object with an initialized default team name and empty players ArrayList
     */
    public Team() {
        teamName = "Default Team Name";
        players = new ArrayList<Athlete>();
    }
    
    /**
     * Constructs a new Team object with an initialized team name and empty players ArrayList
     * @param _teamName the new team name
     */
    public Team(String _teamName) {
        teamName = _teamName;
        players = new ArrayList<Athlete>();

    }
    
    /**
     * Constructs a new Team object with an initialized team name and a given players ArrayList
     * @param _teamName the new team name
     * @param _players the new players ArrayList
     */
    public Team(String _teamName, ArrayList<Athlete> _players) {
        teamName = _teamName;
        players = _players;
    }

    /**
     * Getter for the team name
     * @return the team name
     */
    public String getTeamName() {
        return teamName;
    }
    
    /**
     * Setter for the team name
     * @param _teamName the new team name
     */
    public void setTeamName(String _teamName) {
        teamName = _teamName;
    }
    
    /**
     * Getter for the players
     * @return the players
     */
    public ArrayList<Athlete> getPlayers() {
        return players;
    }
    
    /**
     * Setter for the players
     * @param _players the new players
     */
    public void setPlayers(ArrayList<Athlete> _players) {
        players = _players;
    }

    /**
     * Replicates the ArrayList.add method, allows for more concise code
     * @param athlete the athlete to be added to the team
     */
    public void add(Athlete athlete) {
        players.add(athlete);
    }
    
    /**
     * Replicates the ArrayList.get method, allows for more concise code
     * @param index the index of the desired player
     * @return the athlete at the given index
     */
    public Athlete get(int index) {

        return players.get(index);
    }

    /**
     * Replicates the ArrayList.size() method, allows for more concise code
     * @return the size of the team
     */
    public int size() {
        return players.size();
    }
    
    /**
     * Replicates the ArrayList.isEmpty() method, allows for more concise code
     * @return true if team is empty
     */
    public boolean isEmpty() {
        return players.isEmpty();
    }
    
    /**
     * Replicates the ArrayList.remove(Athlete athlete) method, allows for more concise code
     * @param athlete the athlete to be removed
     */
    public void remove(Athlete athlete) {
        players.remove(athlete);
    }
    
    /**
     * Replicates the ArrayList.remove(int index) method, allows for more concise code
     * @param index the index of the athlete to be removed
     */
    public void remove(int index) {
        players.remove(index);
    }
    
    /**
     * Replicates the Collections.swap method, allows for more concise code
     * @param index1 the index of one of the athletes to be swapped
     * @param index2 the index of the other athlete to be swapped
     */
    public void swap(int index1, int index2) {
        Collections.swap(players, index1, index2);
    }
    
    /**
     * Finds the best athlete in the team based on average stat total
     * @return the best athlete in the team
     */
    public Athlete getBestAthlete() {
    	if (players.size() == 0) {
    		return null;
    	}
        Athlete currBestAthlete = players.get(0);
    	int currBestStats = currBestAthlete.getAvgStat();
        for (Athlete athlete: players) {
        	if (currBestStats < athlete.getAvgStat()) {
        		currBestAthlete = athlete;
        	}
        }
        return currBestAthlete;
    }
    
    /**
     * Gets the team's stat averages
     * @return the integer array of stat averages
     */
    public int[] getTeamAverageStats() {
    	
    	int avgStamina = 0, avgOffence = 0, avgDefence = 0;
    	
		for (Athlete athlete : players) {
			avgStamina += athlete.getStats()[0];
			avgOffence += athlete.getStats()[1];
			avgDefence += athlete.getStats()[2];
		}
		
		avgStamina = (int) avgStamina/size();
		avgOffence = (int) avgOffence/size();
		avgDefence = (int) avgDefence/size();
		
		return new int[] {avgStamina, avgOffence, avgDefence};
		
	}

    /**
     * Randomly generates a team with a given level of strength (quality)
     * @param quality how good the team's stats should be, the higher number the better
     * @return a randomly generated Team object
     */
    public static Team generateTeam(int quality) {

        Random rand = new Random();
        int n = rand.nextInt(100);
        String animal = "";
        try {
            File animals = new File("src/data/animals.txt");
            Scanner animalScanner = new Scanner(animals);
            for (int i = 0; i <= n; i++) {
                animal = animalScanner.nextLine();
            }
            animalScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        
        int countryA = rand.nextInt(195);
        String country = "";
        try {
            File countries = new File("src/data/countries.txt");
            Scanner countryScanner = new Scanner(countries);
            for (int i = 0; i <= countryA; i++) {
                country = countryScanner.nextLine();
            }
            countryScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        
        String teamName = "The " + country + " " + animal;

        ArrayList<Athlete> players = Athlete.generateAthletes(10, quality);

        return new Team(teamName, players);
    }
}
