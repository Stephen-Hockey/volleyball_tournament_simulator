package main;

/**
 * The Purchasable class describes an object that has a name, price, sell price, description, and associated stats.
 * It is designed to be a parent class of the Athlete and Item classes.
 * @author Lachlan Stewart and Stephen Hockey
 * @version 1.1, May 2023.
 */
public class Purchasable {
	
	/**
	 * The name of the thing available for purchase
	 */
    private String name;
    
    /**
     * How much money it costs to buy this thing
     */
    private int price;
    
    /**
     * How much money you get when you sell this thing
     */
    private int sellPrice;
    
    /**
     * A short description of the thing
     */
    private String description;
    
    /**
     * A 3-long integer array representing the stamina, offence, and defence stats related to this thing at the indices 0, 1, and 2 respectively
     *      
     */
    private int[] stats;

    /**
     * A constant String array containing the names of each stat in order, in uppercase
     */
    public static final String[] statNames = new String[] { "STAMINA", "OFFENSE", "DEFENSE" };

    /**
     * Constructs a new Purchasable object with no initialized properties
     */
    public Purchasable() {
    }
    
    /**
     * Constructs a new Purchasable object with fully initialized properties
     * @param _name the new name
     * @param _price the new price
     * @param _sellPrice the new sell price
     * @param _description the new description
     * @param _stats the new stats
     */
    public Purchasable(String _name, int _price, int _sellPrice, String _description, int[] _stats) {
        name = _name;
        price = _price;
        sellPrice = _sellPrice;
        description = _description;
        stats = _stats;
    }

    /**
     * Getter for the name
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Setter for the name
     * @param _name the new name
     */
    public void setName(String _name) {
        name = _name;
    }
    
    /**
     * Getter for the price
     * @return the price
     */
    public int getPrice() {
        return price;
    }
    
    /**
     * Setter for the price
     * @param _price the new price
     */
    public void setPrice(int _price) {
        price = _price;
    }
    
    /**
     * Getter for the sell price
     * @return the sell price
     */
    public int getSellPrice() {
		return sellPrice;
	}
    
    /**
     * Setter for the sell price
     * @param _sellPrice the new sell price
     */
    public void setSellPrice(int _sellPrice) {
		sellPrice = _sellPrice;
	}
    
    /**
     * Getter for the description
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Setter for the description
     * @param _description the new description
     */
    public void setDescription(String _description) {
        description = _description;
    }
    
    /**
     * Getter for the stats
     * @return the stats
     */
    public int[] getStats() {
		return stats;
	}
    
    /**
     * Setter for the stats
     * @param _stats the new stats
     */
    public void setStats(int[] _stats) {
		stats = _stats;
	}
    
    /**
     * Overrides the toString method to return the name String
     */
    @Override
    public String toString() {
    	return name;
    }
}