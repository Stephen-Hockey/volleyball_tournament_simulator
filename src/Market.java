import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Market {

    private static final int QUALITY_CONTROl = 4;

    public static Athlete generateAthlete(int quality) {

        /* quality is int 0-9 which influences athlere stats */
        /* name generation */
        Random rand = new Random();
        int nameA = rand.nextInt(1000);
        int nameB = rand.nextInt(1000);
        String name = "";
        String firstName = "";
        String lastName = "";
        try {

            File firstNames = new File("data/firstNames.txt");
            Scanner firstNameScanner = new Scanner(firstNames);

            for (int i = 0; i <= nameA; i++) {
                firstName = firstNameScanner.nextLine();
            }
            firstNameScanner.close();

            File lastNames = new File("data/lastNames.txt");
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
            int stat = (int) (a * 33 / 2) + (quality - 4) * QUALITY_CONTROl;
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

        /* age generation */
        double a = rand.nextGaussian() + 3;
        if (a < 0) {
            a = 0;
        } else if (a > 6) {
            a = 6;
        }
        int age = (int) (a * 10 / 3 + 16);

        /* country generation */
        int countryA = rand.nextInt(196);
        String country = "";
        try {
            File countries = new File("data/countries.txt");
            Scanner countryScanner = new Scanner(countries);
            for (int i = 0; i <= countryA; i++) {
                country = countryScanner.nextLine();
            }
            countryScanner.close();

        } catch (FileNotFoundException e) {

            System.out.println(e);
        }

        /* description generation */
        String description = age + " years old, from " + country;

        /*
         * System.out.println("Name: " + name);
         * System.out.println("Description: " + description);
         * System.out.println("Price: " + price);
         * System.out.println("Stats: ");
         * for (int stat : stats) {
         * System.out.println(stat);
         * }
         */

        // return new Athlete(name, price, description, lastName, stats, new
        // ArrayList<Item>());
        return new Athlete(name, price, description, "", stats, new ArrayList<Item>());

    }

    public static Item generateItem() {
        String[] names = { "Shoes", "Headband", "Playbook", "Tape", "Water Bottle", "Ankle Brace", "Preworkout" };
        int[][] buffs = { { 1, 1, 1 }, { 1, 0, 1 }, { 0, 1, 1 }, { 1, 1, 0 }, { 1, 0, 0 }, { 0, 0, 1 }, { 0, 1, 0 } };
        String[] descriptions = { "Da shoes", "Sick headband dude", "Good to know", "Very sticky", "Stay hydrated",
                "For those glass ankles", "Get Hype" };

        Random rand = new Random();
        int n = names.length;
        int itemNum = rand.nextInt(n);

        /* Name Generation */
        String itemName = names[itemNum];

        /* Buff Generation */
        int[] itemBuff = buffs[itemNum];
        for (int i = 0; i < itemBuff.length; i++) {
            if (itemBuff[i] == 1) {
                itemBuff[i] = rand.nextInt(20) + 1;
            }
        }

        /* Price Generation */
        int total = 0;
        for (int buff : itemBuff) {
            total += buff;
        }
        int price = (int) (total * 5 / 3);

        /* Description generation */
        String description = descriptions[itemNum];

        /*
         * System.out.println(itemName);
         * for (int buff : itemBuff) {
         * System.out.println(buff);
         * }
         * System.out.println(price);
         */

        return new Item(itemName, price, description, itemBuff);

    }

    public static ArrayList<Athlete> generateAthletes(int quantity, int quality) {
        ArrayList<Athlete> athletes = new ArrayList<Athlete>();
        for (int i = 0; i < quantity; i++) {
            athletes.add(Market.generateAthlete(quality));
        }
        return athletes;
    }

    public static ArrayList<Item> generateMarketItems(int numItems) {
        ArrayList<Item> items = new ArrayList<Item>();
        for (int i = 0; i < numItems; i++) {
            Item item = Market.generateItem();
            items.add(item);
        }
        return items;
    }

    public static void main(String[] args) {
        ArrayList<Athlete> l = generateAthletes(3, 9);
        for (Athlete a : l) {
            System.out.println(a.getDetails());
        }
    }
}