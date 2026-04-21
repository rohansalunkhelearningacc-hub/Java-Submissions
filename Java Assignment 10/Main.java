import java.sql.*;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        // Check if we should run the console version or the JavaFX version
        if (args.length > 0 && args[0].equals("console")) {
            runConsoleVersion();
        } else {
            // Launch JavaFX application
            RestaurantManagementApp.main(args);
        }
    }
    
    public static void runConsoleVersion() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            if (conn != null) {
                clearTables(conn);
                conn.close();
            }
            
            System.out.println("Inserting Restaurants");
            insertRestaurants();
            System.out.println();
            
            System.out.println("Inserting Menu Items");
            insertMenuItems();
            System.out.println();
            
            System.out.println("All Restaurants");
            displayAllRestaurants();
            System.out.println();
            
            System.out.println("All Menu Items");
            displayAllMenuItems();
            System.out.println();
            
            System.out.println("Menu Items with Price <= 100");
            selectMenuItemsByPrice(100);
            System.out.println();
            
            System.out.println("Menu Items from Cafe Java");
            selectMenuItemsByRestaurant("Cafe Java");
            System.out.println();
            
            System.out.println("Updating Menu Items (Set Price to 200 where Price <= 100)");
            updateMenuItemsByPrice(100, 200);
            System.out.println();
            
            System.out.println("All Menu Items (After Update)");
            displayAllMenuItems();
            System.out.println();
            
            System.out.println("Deleting Menu Items (Names starting with P)");
            deleteMenuItemsByNameStarting('P');
            System.out.println();
            
            System.out.println("All Menu Items (Final)");
            displayAllMenuItems();
            System.out.println();
            
        } catch (SQLException e) {
            System.err.println("Database connection failed.");
            e.printStackTrace();
        }
    }
    
    private static void clearTables(Connection conn) {
        try {
            String deleteMenuItems = "DELETE FROM MenuItem";
            String deleteRestaurants = "DELETE FROM Restaurant";
            String resetRestaurantId = "ALTER TABLE Restaurant AUTO_INCREMENT = 1";
            String resetMenuItemId = "ALTER TABLE MenuItem AUTO_INCREMENT = 1";
            
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(deleteMenuItems);
                stmt.execute(deleteRestaurants);
                stmt.execute(resetRestaurantId);
                stmt.execute(resetMenuItemId);
            }
        } catch (SQLException e) {
            // Silently continue if there's any issue
        }
    }
    
    private static void insertRestaurants() {
        String[][] restaurants = {
            {"Cafe Java", "123 Main Street, Downtown"},
            {"Pizza Palace", "456 Oak Avenue, Midtown"},
            {"Sushi Station", "789 Elm Road, Riverside"},
            {"Burger Barn", "321 Pine Lane, Northside"},
            {"Pasta Perfetto", "654 Maple Drive, Westend"},
            {"Taco Tierra", "987 Cedar Court, Eastside"},
            {"Noodle Haven", "246 Birch Path, Central"},
            {"Steakhouse Supreme", "135 Walnut Way, Uptown"},
            {"Vegan Villa", "802 Spruce Street, Greenfield"},
            {"Seafood Shack", "579 Ash Boulevard, Harbor"}
        };
        
        for (String[] restaurant : restaurants) {
            Restaurant r = new Restaurant(restaurant[0], restaurant[1]);
            RestaurantDAO.insertRestaurant(r);
        }
    }
    
    private static void insertMenuItems() {
        String[][] menuItems = {
            {"Espresso", "45", "1"},
            {"Cappuccino", "55", "1"},
            {"Margherita Pizza", "150", "2"},
            {"California Roll", "280", "3"},
            {"Classic Burger", "120", "4"},
            {"Chicken Tacos", "85", "6"},
            {"Pad Thai", "95", "7"},
            {"Ribeye Steak", "350", "8"},
            {"Buddha Bowl", "110", "9"},
            {"Grilled Salmon", "280", "10"}
        };
        
        for (String[] item : menuItems) {
            MenuItem m = new MenuItem(item[0], Double.parseDouble(item[1]), Integer.parseInt(item[2]));
            MenuItemDAO.insertMenuItem(m);
        }
    }
    
    private static void displayAllRestaurants() {
        List<Restaurant> restaurants = RestaurantDAO.getAllRestaurants();
        if (restaurants.isEmpty()) {
            System.out.println("No restaurants found.");
            return;
        }
        
        System.out.printf("%-5s | %-25s | %-35s%n", "ID", "NAME", "ADDRESS");
        System.out.println("-".repeat(70));
        for (Restaurant r : restaurants) {
            System.out.printf("%-5d | %-25s | %-35s%n", 
                r.getId(), r.getName(), r.getAddress());
        }
    }
    
    private static void displayAllMenuItems() {
        List<MenuItem> items = MenuItemDAO.getAllMenuItems();
        if (items.isEmpty()) {
            System.out.println("No menu items found.");
            return;
        }
        
        System.out.printf("%-5s | %-30s | %-10s | %-8s%n", "ID", "NAME", "PRICE", "RES_ID");
        System.out.println("-".repeat(60));
        for (MenuItem item : items) {
            System.out.printf("%-5d | %-30s | %-10.2f | %-8d%n", 
                item.getId(), item.getName(), item.getPrice(), item.getResId());
        }
    }
    
    private static void selectMenuItemsByPrice(double maxPrice) {
        List<MenuItem> items = MenuItemDAO.getMenuItemsByPrice(maxPrice);
        if (items.isEmpty()) {
            System.out.println("No menu items found with price <= " + maxPrice);
            return;
        }
        
        System.out.printf("%-5s | %-30s | %-10s | %-8s%n", "ID", "NAME", "PRICE", "RES_ID");
        System.out.println("-".repeat(60));
        for (MenuItem item : items) {
            System.out.printf("%-5d | %-30s | %-10.2f | %-8d%n", 
                item.getId(), item.getName(), item.getPrice(), item.getResId());
        }
    }
    
    private static void selectMenuItemsByRestaurant(String restaurantName) {
        List<MenuItem> items = MenuItemDAO.getMenuItemsByRestaurantName(restaurantName);
        if (items.isEmpty()) {
            System.out.println("No menu items found for restaurant: " + restaurantName);
            return;
        }
        
        System.out.printf("%-5s | %-30s | %-10s | %-8s%n", "ID", "NAME", "PRICE", "RES_ID");
        System.out.println("-".repeat(60));
        for (MenuItem item : items) {
            System.out.printf("%-5d | %-30s | %-10.2f | %-8d%n", 
                item.getId(), item.getName(), item.getPrice(), item.getResId());
        }
    }
    
    private static void updateMenuItemsByPrice(double targetPrice, double newPrice) {
        MenuItemDAO.updateMenuItemsByPrice(targetPrice, newPrice);
    }
    
    private static void deleteMenuItemsByNameStarting(char startingChar) {
        MenuItemDAO.deleteMenuItemsByNameStarting(String.valueOf(startingChar));
    }
}
