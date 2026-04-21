import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDAO {
    
    // Insert a new menu item
    public static int insertMenuItem(MenuItem item) {
        String query = "INSERT INTO MenuItem (Name, Price, ResId) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, item.getName());
            pstmt.setDouble(2, item.getPrice());
            pstmt.setInt(3, item.getResId());
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    // Get all menu items
    public static List<MenuItem> getAllMenuItems() {
        List<MenuItem> items = new ArrayList<>();
        String query = "SELECT * FROM MenuItem";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                MenuItem item = new MenuItem(
                    rs.getInt("Id"),
                    rs.getString("Name"),
                    rs.getDouble("Price"),
                    rs.getInt("ResId")
                );
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
    
    // Get menu item by ID
    public static MenuItem getMenuItemById(int id) {
        String query = "SELECT * FROM MenuItem WHERE Id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new MenuItem(
                        rs.getInt("Id"),
                        rs.getString("Name"),
                        rs.getDouble("Price"),
                        rs.getInt("ResId")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Update menu item
    public static boolean updateMenuItem(MenuItem item) {
        String query = "UPDATE MenuItem SET Name = ?, Price = ?, ResId = ? WHERE Id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, item.getName());
            pstmt.setDouble(2, item.getPrice());
            pstmt.setInt(3, item.getResId());
            pstmt.setInt(4, item.getId());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Delete menu item
    public static boolean deleteMenuItem(int id) {
        String query = "DELETE FROM MenuItem WHERE Id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Get menu items by price (price <= specified price)
    public static List<MenuItem> getMenuItemsByPrice(double maxPrice) {
        List<MenuItem> items = new ArrayList<>();
        String query = "SELECT * FROM MenuItem WHERE Price <= ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setDouble(1, maxPrice);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    MenuItem item = new MenuItem(
                        rs.getInt("Id"),
                        rs.getString("Name"),
                        rs.getDouble("Price"),
                        rs.getInt("ResId")
                    );
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
    
    // Get menu items by restaurant name
    public static List<MenuItem> getMenuItemsByRestaurantName(String restaurantName) {
        List<MenuItem> items = new ArrayList<>();
        String query = "SELECT m.* FROM MenuItem m " +
                       "JOIN Restaurant r ON m.ResId = r.Id " +
                       "WHERE r.Name = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, restaurantName);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    MenuItem item = new MenuItem(
                        rs.getInt("Id"),
                        rs.getString("Name"),
                        rs.getDouble("Price"),
                        rs.getInt("ResId")
                    );
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
    
    // Update menu items by price
    public static int updateMenuItemsByPrice(double targetPrice, double newPrice) {
        String query = "UPDATE MenuItem SET Price = ? WHERE Price <= ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setDouble(1, newPrice);
            pstmt.setDouble(2, targetPrice);
            
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    // Delete menu items by name starting with a character
    public static int deleteMenuItemsByNameStarting(String startingChar) {
        String query = "DELETE FROM MenuItem WHERE Name LIKE ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, startingChar + "%");
            
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
