// InventoryManagementSystem.java
// Main class — entry point, demonstrates all three design patterns

import java.util.Iterator;

public class InventoryManagementSystem {
    public static void main(String[] args) {

        // Singleton Pattern: get the single shared instance
        InventoryManager manager = InventoryManager.getInstance();

        // Add NewProduct objects
        manager.addProduct(new NewProduct("Wireless Headphones"));
        manager.addProduct(new NewProduct("Mechanical Keyboard"));
        manager.addProduct(new NewProduct("USB-C Hub"));

        // Adapter Pattern: wrap LegacyItem objects as Products
        manager.addProduct(new ProductAdapter(new LegacyItem(101, "Old CRT Monitor")));
        manager.addProduct(new ProductAdapter(new LegacyItem(102, "Vintage Dot-Matrix Printer")));

        // Iterator Pattern: traverse inventory using Iterator
        System.out.println("===== Inventory Details =====");
        Iterator<Product> iterator = manager.returnInventory();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            product.displayDetails();
        }
        System.out.println("=============================");
    }
}
