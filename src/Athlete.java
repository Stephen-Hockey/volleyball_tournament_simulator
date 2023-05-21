import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Athlete extends Purchasable {
    private String nickname;
    private int[] stats;
    private ArrayList<Item> items;
    private static final int MAXITEMS = 3;

    public Athlete() {
        super();
    }

    public Athlete(String _name, int _price, int _sellPrice, String _description, String _nickname, int[] _stats,
            ArrayList<Item> _items) {
        super(_name, _price, _sellPrice, _description);
        nickname = _nickname;
        stats = _stats;
        items = _items;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String _nickname) {
        nickname = _nickname;
    }

    public int[] getStats() {
        return stats;
    }

    public void setStats(int[] _stats) {
        stats = _stats;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> _items) {
        items = _items;
    }

    public int getSellPrice() {
        return (int) (getPrice() * 0.8);
    }

    public int[] getEffectiveStats() {
        int[] effectiveStats = Arrays.copyOf(stats, stats.length);

        for (Item item : items) {
            for (int i = 0; i < stats.length; i++) {
                effectiveStats[i] += item.getEffect()[i];
            }
        }

        return effectiveStats;
    }

    public String getNameWithNickname() {
        if (nickname == "") {
            return getName();
        }
        return getName().split(" ")[0] + " \"" + nickname + "\" " + getName().split(" ")[1];
    }

    public String getMarketString() {
        return String.format("%24s|%6s|%7s|%7s|%7s", getName(), "$" + getPrice(), stats[0], stats[1], stats[2]);
    }

    public String getDraftString() {
        return String.format("%24s|%7s|%7s|%7s", getName(), stats[0], stats[1], stats[2]);
    }

    public String getSellString() {
        return String.format("%24s| %s", getName(), "$" + getSellPrice());
    }

    public String getItemsListed() {
        if (items.isEmpty()) {
            return "";
        }
        String itemsListed = items.get(0).getName();
        for (int i = 1; i < items.size(); i++) {
            itemsListed += ", " + items.get(i).getName();
        }
        return itemsListed;
    }

    public String getDetails() {
        String details = "\nName: " + getName();
        if (nickname != "") {
            details += "\nNickname: " + nickname;
        }
        details += "\nDescription: " + getDescription();
        for (int i = 0; i < stats.length; i++) {
            details += "\n" + statNames[i] + ": " + stats[i];
        }

        if (!items.isEmpty()) {
            details += "\nItems:";
            for (Item i : items) {
                details += "\n" + i;
            }
        }

        return details;
    }

    public Item removeItem(int n) throws PlayerItemsException {

        /* removes item from index n */
        int length = items.size();
        if (n > length - 1) {
            throw new PlayerItemsException();
        }
        Item item = items.get(n);
        items.remove(n);
        return item;
    }

    public void addItem(Item item) {
        /* need to implement item cap with appropriate error handling */
        try {
            if (items.size() >= MAXITEMS) {
                throw new PlayerItemsException();
            }
            items.add(item);
        } catch (Exception e) {
            System.out.println(e);
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
        int countryA = rand.nextInt(196);
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
        String description = age + " years old,\nfrom " + country;

        /* nickname generation */
        String nickname = "";
        
        /* items generation */
        ArrayList<Item> items = new ArrayList<Item>();
        
        return new Athlete(name, price, sellPrice, description, nickname, stats, items );

    }
    
    public static ArrayList<Athlete> generateAthletes(int quantity, int quality) {
        ArrayList<Athlete> athletes = new ArrayList<Athlete>();
        for (int i = 0; i < quantity; i++) {
            athletes.add(Athlete.generateAthlete(quality));
        }
        return athletes;
    }
}