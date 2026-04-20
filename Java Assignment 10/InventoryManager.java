// InventoryManager.java
// Singleton Pattern — ensures only one inventory instance exists

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InventoryManager {
    private static InventoryManager instance;
    private List<Product> inventory;

    private InventoryManager() {
        inventory = new ArrayList<>();
    }

    public static InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    public void addProduct(Product product) {
        inventory.add(product);
    }

    // Iterator Pattern — returns an iterator over the product list
    public Iterator<Product> returnInventory() {
        return inventory.iterator();
    }
}
