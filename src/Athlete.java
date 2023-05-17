import java.util.ArrayList;

public class Athlete extends Purchasable {
    private String nickname;
    private int[] stats;
    private ArrayList<Item> items;
    private static final int MAXITEMS = 3;

    public Athlete() {
        super();
    }

    public Athlete(String _name, int _price, String _description, String _nickname, int[] _stats,
            ArrayList<Item> _items) {
        super(_name, _price, _description);
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

    public String getMarketString() {
        // TODO
        return getDraftString() + String.format("|%6s", "$" + getPrice());
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

    public void showItems() {
        for (Item item : items) {
            System.out.println(item);
        }
    }

    public String getNameWithNickname() {
        if (nickname == "") {
            return getName();
        }
        return getName().split(" ")[0] + " \"" + nickname + "\" " + getName().split(" ")[1];
    }

    public String getDraftString() {
        return String.format("%24s|%7s|%7s|%7s", getName(), stats[0], stats[1], stats[2]);
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

    @Override
    public String toString() {
        return "(Athlete) name=\"" + getName() + "\"";
    }

    public static void main(String[] args) {
        Athlete a = Market.generateAthlete(5);
        System.out.println(a.getDetails());
        System.out.println(a.getDraftString());
        System.out.println(a.getMarketString());
    }

}