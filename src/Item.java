
import java.util.ArrayList;
import java.util.Random;

public class Item extends Purchasable {

    private int[] effect;

    public Item() {
        super();
    }

    public Item(String _name, int _price, int _sellPrice, String _description, int[] _effect) {
        super(_name, _price, _sellPrice, _description);
        effect = _effect;
    }

    public int[] getEffect() {
        return effect;
    }

    public void setEffect(int[] _effect) {
        effect = _effect;
    }

    public int getSellPrice() {
        return (int) (0.5 * getPrice());
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
        
        /* sellPrice Generation */
        int sellPrice = (int) (price * 0.5);

        /* Description generation */
        String description = descriptions[itemNum];

        return new Item(itemName, price, sellPrice, description, itemBuff);

    }
    
    public static ArrayList<Item> generateItems(int numItems) {
        ArrayList<Item> items = new ArrayList<Item>();
        for (int i = 0; i < numItems; i++) {
            Item item = Item.generateItem();
            items.add(item);
        }
        return items;
    }
}