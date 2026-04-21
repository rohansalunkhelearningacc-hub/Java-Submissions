import java.sql.*;

public class DatabaseConnection {
    private static final String HOST = System.getenv().getOrDefault("DB_HOST", "localhost");
    private static final int PORT = parseIntEnv("DB_PORT", 3306);
    private static final String DATABASE = System.getenv().getOrDefault("DB_NAME", "restaurant_db");
    private static final String USER = System.getenv().getOrDefault("DB_USER", "root");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "system"); // Set via environment if needed
    
    private static final String URL_WITHOUT_DB = "jdbc:mysql://" + HOST + ":" + PORT;
    private static final String URL_WITH_DB = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
    
    static {
        loadDriver();
        initializeDatabase();
    }
    
    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            System.out.println("Please ensure mysql-connector-java-8.0.26.jar is in the lib folder.");
            System.out.println("And add it to your classpath.");
            System.out.println("Or add MySQL Connector/J dependency to your project.");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private static void initializeDatabase() {
        try {
            // Connect to MySQL (without database)
            Connection conn = DriverManager.getConnection(URL_WITHOUT_DB, USER, PASSWORD);
            System.out.println("Connected to MySQL Server.");
            
            // Create database if not exists
            String createDBSQL = "CREATE DATABASE IF NOT EXISTS " + DATABASE;
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(createDBSQL);
                System.out.println("Database '" + DATABASE + "' is ready.");
            }
            conn.close();
            
            // Connect to the database
            conn = DriverManager.getConnection(URL_WITH_DB, USER, PASSWORD);
            System.out.println("Connected to database '" + DATABASE + "'.");
            
            // Create tables
            createTables(conn);
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("Database initialization failed.");
            System.out.println("Please ensure MySQL is running and credentials are correct.");
            System.out.println("DB connection details: host=" + HOST + ", port=" + PORT + ", user=" + USER + ", db=" + DATABASE);
            System.out.println("Update DB_USER and DB_PASSWORD environment variables or edit DatabaseConnection.java if needed.");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private static void createTables(Connection conn) throws SQLException {
        // Create Restaurant table
        String createRestaurantTable = 
            "CREATE TABLE IF NOT EXISTS Restaurant (" +
            "    Id INT PRIMARY KEY AUTO_INCREMENT," +
            "    Name VARCHAR(100) NOT NULL," +
            "    Address VARCHAR(255) NOT NULL" +
            ")";
        
        // Create MenuItem table
        String createMenuItemTable = 
            "CREATE TABLE IF NOT EXISTS MenuItem (" +
            "    Id INT PRIMARY KEY AUTO_INCREMENT," +
            "    Name VARCHAR(100) NOT NULL," +
            "    Price DECIMAL(10, 2) NOT NULL," +
            "    ResId INT NOT NULL," +
            "    FOREIGN KEY (ResId) REFERENCES Restaurant(Id) ON DELETE CASCADE" +
            ")";
        
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createRestaurantTable);
            System.out.println("Restaurant table is ready.");
            
            stmt.execute(createMenuItemTable);
            System.out.println("MenuItem table is ready.");
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL_WITH_DB, USER, PASSWORD);
    }
    
    private static int parseIntEnv(String name, int defaultValue) {
        String value = System.getenv(name);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    // Method to update credentials if needed
    public static void setCredentials(String user, String password) {
        System.out.println("To change credentials, set DB_USER or DB_PASSWORD environment variables,");
        System.out.println("or update USER and PASSWORD in DatabaseConnection.java.");
    }
}
