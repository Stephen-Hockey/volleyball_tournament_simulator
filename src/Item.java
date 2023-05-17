import java.util.Arrays;

public class Item extends Purchasable {

    private int[] effect;

    public Item() {
        super();
    }

    public Item(String _name, int _price, String _description, int[] _effect) {
        super(_name, _price, _description);
        effect = _effect;
    }

    public int[] getEffect() {
        return effect;
    }

    public void setEffect(int[] _effect) {
        effect = _effect;
    }

    public int getSellPrice() {
        // TODO was thinking just a 0.5 x price type deal.
        throw new UnsupportedOperationException("Unimplemented method 'getSellPrice'");
    }

    public String getMarketString() {
        String inMarketString = getDescription();
        for (int i = 0; i < effect.length; i++) {
            if (effect[i] > 0) {
                inMarketString += ", +" + effect[i] + " " + statNames[i];
            } else if (effect[i] < 0) {
                inMarketString += ", " + effect[i] + " " + statNames[i];
            }
        }
        inMarketString.replace(", ", "");
        return String.format("%-25s |%6s| %s", getName(), "$" + getPrice(), inMarketString);
    }

    @Override
    public String toString() {
        return "(Item) name=\"" + getName() + "\" effect=" + Arrays.toString(effect);
    }
}