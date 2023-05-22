package main;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Athlete class describes an athlete with certain stats, name, age, and nationality.
 *
 * @author Lachlan Stewart and Stephen Hockey
 * @version 1.1, May 2023.
 */
public class Athlete extends Purchasable {
    
	/**
	 * Calls the constructor of the parent class Purchasable with no given parameters
	 */
    public Athlete() {
        super();
    }

    /**
	 * Calls the constructor of the parent class Purchasable with all parameters
	 * @param newName the new name
     * @param newPrice the new price
     * @param newSellPrice the new sell price
     * @param newDescription the new description
     * @param newStats the new stats
	 */
    public Athlete(String newName, int newPrice, int newSellPrice, String newDescription, int[] newStats) {
        super(newName, newPrice, newSellPrice, newDescription, newStats);
    }

    /**
	 * Applies an Item to the player, increasing the relevant stats while bounding the stat at 99
	 * @param item the item to be applied as an Item object
	 */
    public void addItem(Item item) {
        int[] itemEffect = item.getStats();
    	for (int i = 0; i < itemEffect.length; i++) {
    		if ((getStats()[i] + itemEffect[i]) > 99) {
    			getStats()[i] = 99;
    		} else {
    			getStats()[i] += itemEffect[i];
    		}
    	}
    }
    
    /**
     * Gets the average of the athlete's stats
     * @return the average of the athlete's stats
     */
    public int getAvgStat() {
    	double avg = 0;
    	for (int stat: getStats()) {
    		avg += stat;
    	}
    	return (int) avg/3;
    }
    
    /**
	 * Generates a new Athlete object with randomized properties based on the given quality
	 * @param quality how good the athlete's stats should be, the higher number the better
	 * @return randomly generated Athlete object
	 */
    public static Athlete generateAthlete(int quality) {
    	
    	if (quality > 9) {
    		quality = 9;
    	}
    	
        /* name generation */
        Random rand = new Random();
        int nameA = rand.nextInt(1000);
        int nameB = rand.nextInt(1000);

    	FileHandler fileHandler = new FileHandler();
    	String firstName = fileHandler.getLine("/data/firstNames.txt", nameA);
    	String lastName = fileHandler.getLine("/data/lastNames.txt", nameB);

    	String name = firstName + " " + lastName;

        /* stat generation */
        int[] stats = { 0, 0, 0 };
        for (int i = 0; i < 3; i++) {
            double a = rand.nextGaussian() + 3;
            if (a < 0) {
                a = 0;
            } else if (a > 6) {
                a = 6;
            }
            int stat = (int) (a * 33 / 2) + (quality - 4) * 4;
            if (stat > 99) {
                stat = 99;
            } else if (stat < 0) {
                stat = 0;
            }
            stats[i] = stat;

        }

        /* price generation */
        int total = 0;
        for (int stat : stats) {
            total += stat;
        }
        int price = (int) (total * 10 / 3);

        /* sellPrice generation */
        int sellPrice = (int) (price*0.8);

        /* age generation */
        double a = rand.nextGaussian() + 3;
        if (a < 0) {
            a = 0;
        } else if (a > 6) {
            a = 6;
        }
        int age = (int) (a * 10 / 3 + 16);

        /* country generation */
        int countryA = rand.nextInt(195);
        String country = fileHandler.getLine("/data/countries.txt", countryA);
        
        /* description generation */
        String description = age + " years old, \nfrom " + country;
        
        return new Athlete(name, price, sellPrice, description, stats);

    }
    
    /**
	 * Calls generateAthlete to make an ArrayList of random athletes
	 * @param quantity desired length of the Athlete ArrayList
	 * @param quality how good the athlete's stats should be, the higher number the better
	 * @return ArrayList of randomly generated Athletes
	 */
    public static ArrayList<Athlete> generateAthletes(int quantity, int quality) {
        ArrayList<Athlete> athletes = new ArrayList<Athlete>();
        for (int i = 0; i < quantity; i++) {
            athletes.add(Athlete.generateAthlete(quality));
        }
        return athletes;
    }
    
    
    
    
    
    
    
    
    
    
}