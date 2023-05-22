
public class Purchasable {
    private String name;
    private int price;
    private int sellPrice;
    private String description;
    private int[] stats;

    public static final String[] statNames = new String[] { "STAMINA", "OFFENSE", "DEFENSE" };

    public Purchasable() {
    }

    public Purchasable(String _name, int _price, int _sellPrice, String _description, int[] _stats) {
        name = _name;
        price = _price;
        sellPrice = _sellPrice;
        description = _description;
        stats = _stats;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int _price) {
        price = _price;
    }
    
    public int getSellPrice() {
		return sellPrice;
	}
    
    public void setSellPrice(int _sellPrice) {
		sellPrice = _sellPrice;
	}

    public String getDescription() {
        return description;
    }

    public void setDescription(String _description) {
        description = _description;
    }
    
    public int[] getStats() {
		return stats;
	}
    
    public void setStats(int[] _stats) {
		stats = _stats;
	}
    
    @Override
    public String toString() {
    	return name;
    }
}