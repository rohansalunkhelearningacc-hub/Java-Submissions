import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDAO {
    
    // Insert a new restaurant
    public static int insertRestaurant(Restaurant restaurant) {
        String query = "INSERT INTO Restaurant (Name, Address) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, restaurant.getName());
            pstmt.setString(2, restaurant.getAddress());
            
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
    
    // Get all restaurants
    public static List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        String query = "SELECT * FROM Restaurant";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Restaurant r = new Restaurant(
                    rs.getInt("Id"),
                    rs.getString("Name"),
                    rs.getString("Address")
                );
                restaurants.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurants;
    }
    
    // Get restaurant by ID
    public static Restaurant getRestaurantById(int id) {
        String query = "SELECT * FROM Restaurant WHERE Id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Restaurant(
                        rs.getInt("Id"),
                        rs.getString("Name"),
                        rs.getString("Address")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Update restaurant
    public static boolean updateRestaurant(Restaurant restaurant) {
        String query = "UPDATE Restaurant SET Name = ?, Address = ? WHERE Id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, restaurant.getName());
            pstmt.setString(2, restaurant.getAddress());
            pstmt.setInt(3, restaurant.getId());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Delete restaurant
    public static boolean deleteRestaurant(int id) {
        String query = "DELETE FROM Restaurant WHERE Id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Get restaurant by name
    public static Restaurant getRestaurantByName(String name) {
        String query = "SELECT * FROM Restaurant WHERE Name = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Restaurant(
                        rs.getInt("Id"),
                        rs.getString("Name"),
                        rs.getString("Address")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
