public abstract class Purchasable {
    private String name;
    private int price;
    private String description;

    public static final String[] statNames = new String[] { "STAMINA", "OFFENSE", "DEFENSE" }; // im
    // confused about
    // calling it private
    // static final etc

    public Purchasable() {
    }

    public Purchasable(String _name, int _price, String _description) {
        name = _name;
        price = _price;
        description = _description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String _description) {
        description = _description;
    }

    public String[] getStatNames() {
        return statNames;
    }

    public abstract int getSellPrice();

    public abstract String getMarketString();
}