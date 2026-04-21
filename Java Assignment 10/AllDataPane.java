import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

public class AllDataPane extends VBox {
    
    private Runnable onBack;
    private TableView<Restaurant> restaurantTable;
    private TableView<MenuItem> menuItemTable;
    private ObservableList<Restaurant> restaurantData;
    private ObservableList<MenuItem> menuItemData;
    
    public AllDataPane(Runnable onBack) {
        this.onBack = onBack;
        this.restaurantData = FXCollections.observableArrayList();
        this.menuItemData = FXCollections.observableArrayList();
        initializeComponents();
        loadData();
    }
    
    private void initializeComponents() {
        this.setSpacing(20);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.TOP_CENTER);
        
        // Title
        Label titleLabel = new Label("All Data");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        
        // Back button
        Button backBtn = new Button("Back");
        backBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        backBtn.setPrefSize(100, 35);
        backBtn.setStyle(
            "-fx-background-color: #2196F3; " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 5;"
        );
        backBtn.setOnAction(e -> onBack.run());
        
        HBox backBox = new HBox(backBtn);
        backBox.setAlignment(Pos.CENTER_RIGHT);
        
        // Restaurant section
        Label restaurantLabel = new Label("Restaurants");
        restaurantLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        restaurantTable = new TableView<>();
        restaurantTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        TableColumn<Restaurant, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.ReadOnlyIntegerWrapper(cellData.getValue().getId()).asObject());
        
        TableColumn<Restaurant, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.ReadOnlyStringWrapper(cellData.getValue().getName()));
        
        TableColumn<Restaurant, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.ReadOnlyStringWrapper(cellData.getValue().getAddress()));
        
        restaurantTable.getColumns().addAll(idColumn, nameColumn, addressColumn);
        restaurantTable.setItems(restaurantData);
        restaurantTable.setPrefHeight(200);
        
        VBox restaurantBox = new VBox(10);
        restaurantBox.getChildren().addAll(restaurantLabel, restaurantTable);
        
        // Menu Item section
        Label menuItemLabel = new Label("Menu Items");
        menuItemLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        
        menuItemTable = new TableView<>();
        menuItemTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        TableColumn<MenuItem, Integer> itemIdColumn = new TableColumn<>("ID");
        itemIdColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.ReadOnlyIntegerWrapper(cellData.getValue().getId()).asObject());
        
        TableColumn<MenuItem, String> itemNameColumn = new TableColumn<>("Name");
        itemNameColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.ReadOnlyStringWrapper(cellData.getValue().getName()));
        
        TableColumn<MenuItem, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.ReadOnlyDoubleWrapper(cellData.getValue().getPrice()).asObject());
        
        TableColumn<MenuItem, Integer> resIdColumn = new TableColumn<>("Restaurant ID");
        resIdColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.ReadOnlyIntegerWrapper(cellData.getValue().getResId()).asObject());
        
        menuItemTable.getColumns().addAll(itemIdColumn, itemNameColumn, priceColumn, resIdColumn);
        menuItemTable.setItems(menuItemData);
        menuItemTable.setPrefHeight(200);
        
        VBox menuItemBox = new VBox(10);
        menuItemBox.getChildren().addAll(menuItemLabel, menuItemTable);
        
        // Refresh button
        Button refreshBtn = new Button("Refresh Data");
        refreshBtn.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        refreshBtn.setPrefSize(120, 35);
        refreshBtn.setStyle(
            "-fx-background-color: #FF9800; " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 5;"
        );
        refreshBtn.setOnAction(e -> loadData());
        
        HBox refreshBox = new HBox(refreshBtn);
        refreshBox.setAlignment(Pos.CENTER);
        
        this.getChildren().addAll(titleLabel, backBox, restaurantBox, menuItemBox, refreshBox);
    }
    
    private void loadData() {
        try {
            // Load restaurants
            List<Restaurant> restaurants = RestaurantDAO.getAllRestaurants();
            restaurantData.clear();
            restaurantData.addAll(restaurants);
            
            // Load menu items
            List<MenuItem> items = MenuItemDAO.getAllMenuItems();
            menuItemData.clear();
            menuItemData.addAll(items);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load data: " + e.getMessage());
        }
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}