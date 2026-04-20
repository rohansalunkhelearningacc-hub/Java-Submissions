// LegacyItem.java
// Adaptee — existing legacy class that does not implement Product

public class LegacyItem {
    private int itemId;
    private String description;

    public LegacyItem(int itemId, String description) {
        this.itemId = itemId;
        this.description = description;
    }

    public void print() {
        System.out.println("Legacy Item [ID: " + itemId + "] - " + description);
    }
}
