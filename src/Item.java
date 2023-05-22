package main;

import java.util.ArrayList;
import java.util.Random;


/**
 * The Item class describes a usable item with certain stat boosts.
 * It contains some static methods to randomly generate these items.
 *
 * @author Lachlan Stewart and Stephen Hockey
 * @version 1.1, May 2023.
 */
public class Item extends Purchasable {

	/**
	 * Calls the constructor of the parent class Purchasable with no given parameters
	 */
    public Item() {
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
    public Item(String newName, int newPrice, int newSellPrice, String newDescription, int[] newStats) {
        super(newName, newPrice, newSellPrice, newDescription, newStats);
    }
    
    /**
     * Generates a new Item object with randomized properties
     * @return randomly generated Item object
     */
    public static Item generateItem() {
        String[] names = { "Shoes", "Headband", "Playbook", "Tape", "Water Bottle", "Ankle Brace", "Preworkout" };
        int[][] buffs = { { 1, 1, 1 }, { 1, 0, 1 }, { 0, 1, 1 }, { 1, 1, 0 }, { 1, 0, 0 }, { 0, 0, 1 }, { 0, 1, 0 } };
        String[] descriptions = { "A nice pair of kicks", "Absorbent AND rad", "Knowledge is power", "Very sticky", "Stay hydrated",
                "For those glass ankles", "Get hype" };

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
        
        /* sellPrice Generation */
        int sellPrice = (int) (price * 0.5);

        /* Description generation */
        String description = descriptions[itemNum];

        return new Item(itemName, price, sellPrice, description, itemBuff);

    }
    
    /**
     * Calls generateItem to make an ArrayList of random items
     * @param numItems desired length of the Item ArrayList
     * @return ArrayList of randomly generated Items
     */
    public static ArrayList<Item> generateItems(int numItems) {
        ArrayList<Item> items = new ArrayList<Item>();
        for (int i = 0; i < numItems; i++) {
            Item item = Item.generateItem();
            items.add(item);
        }
        return items;
    }
}