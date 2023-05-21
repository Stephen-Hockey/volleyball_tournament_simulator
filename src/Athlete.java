

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Athlete extends Purchasable {
    private int[] stats;

    public Athlete() {
        super();
    }

    public Athlete(String _name, int _price, int _sellPrice, String _description, int[] _stats,
            ArrayList<Item> _items) {
        super(_name, _price, _sellPrice, _description);
        stats = _stats;
    }
    public int[] getStats() {
        return stats;
    }

    public void setStats(int[] _stats) {
        stats = _stats;
    }

    public int getSellPrice() {
        return (int) (getPrice() * 0.8);
    }

    public void addItem(Item item) {
        int[] itemEffect = item.getEffect();
    	for (int i = 0; i < itemEffect.length; i++) {
    		if ((stats[i] + itemEffect[i]) > 99) {
    			stats[i] = 99;
    		} else {
        		stats[i] += itemEffect[i];
    		}
    	}
    }
 
    public static Athlete generateAthlete(int quality) {
    	
        /* name generation */
        Random rand = new Random();
        int nameA = rand.nextInt(1000);
        int nameB = rand.nextInt(1000);
        String name = "";
        String firstName = "";
        String lastName = "";
        try {

            File firstNames = new File("src/data/firstNames.txt");
            Scanner firstNameScanner = new Scanner(firstNames);

            for (int i = 0; i <= nameA; i++) {
                firstName = firstNameScanner.nextLine();
            }
            firstNameScanner.close();

            File lastNames = new File("src/data/lastNames.txt");
            Scanner lastNameScanner = new Scanner(lastNames);

            for (int i = 0; i <= nameB; i++) {
                lastName = lastNameScanner.nextLine();
            }
            lastNameScanner.close();

        } catch (FileNotFoundException e) {

            System.out.println("An error occurred.");
        }
        name = firstName + " " + lastName;

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
        int sellPrice = (int) (price*0.5);

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

        /* description generation */
        String description = age + " years old, \nfrom " + country;
        
        /* items generation */
        ArrayList<Item> items = new ArrayList<Item>();
        
        return new Athlete(name, price, sellPrice, description, stats, items );

    }
    
    public static ArrayList<Athlete> generateAthletes(int quantity, int quality) {
        ArrayList<Athlete> athletes = new ArrayList<Athlete>();
        for (int i = 0; i < quantity; i++) {
            athletes.add(Athlete.generateAthlete(quality));
        }
        return athletes;
    }
}