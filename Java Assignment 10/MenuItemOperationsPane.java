import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

public class MenuItemOperationsPane extends VBox {
    
    private Runnable onBack;
    private TableView<MenuItem> menuItemTable;
    private ObservableList<MenuItem> menuItemData;
    private ObservableList<Restaurant> restaurantData;
    
    public MenuItemOperationsPane(Runnable onBack) {
        this.onBack = onBack;
        this.menuItemData = FXCollections.observableArrayList();
        this.restaurantData = FXCollections.observableArrayList();
        initializeComponents();
        loadData();
    }
    
    private void initializeComponents() {
        this.setSpacing(15);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.TOP_CENTER);
        
        // Title
        Label titleLabel = new Label("Menu Item Operations");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        
        // Buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        
        Button insertBtn = createOperationButton("Insert");
        insertBtn.setOnAction(e -> showInsertDialog());
        
        Button updateBtn = createOperationButton("Update");
        updateBtn.setOnAction(e -> showUpdateDialog());
        
        Button deleteBtn = createOperationButton("Delete");
        deleteBtn.setOnAction(e -> showDeleteDialog());
        
        Button viewBtn = createOperationButton("View All");
        viewBtn.setOnAction(e -> showViewDialog());
        
        Button backBtn = createOperationButton("Back");
        backBtn.setOnAction(e -> onBack.run());
        
        buttonBox.getChildren().addAll(insertBtn, updateBtn, deleteBtn, viewBtn, backBtn);
        
        // Table for displaying menu items
        menuItemTable = new TableView<>();
        menuItemTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        TableColumn<MenuItem, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.ReadOnlyIntegerWrapper(cellData.getValue().getId()).asObject());
        
        TableColumn<MenuItem, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.ReadOnlyStringWrapper(cellData.getValue().getName()));
        
        TableColumn<MenuItem, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.ReadOnlyDoubleWrapper(cellData.getValue().getPrice()).asObject());
        
        TableColumn<MenuItem, Integer> resIdColumn = new TableColumn<>("Restaurant ID");
        resIdColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.ReadOnlyIntegerWrapper(cellData.getValue().getResId()).asObject());
        
        menuItemTable.getColumns().addAll(idColumn, nameColumn, priceColumn, resIdColumn);
        menuItemTable.setItems(menuItemData);
        menuItemTable.setPrefHeight(300);
        
        this.getChildren().addAll(titleLabel, buttonBox, menuItemTable);
    }
    
    private Button createOperationButton(String text) {
        Button btn = new Button(text);
        btn.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        btn.setPrefSize(100, 35);
        btn.setStyle(
            "-fx-background-color: #2196F3; " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 5;"
        );
        return btn;
    }
    
    private void loadData() {
        try {
            List<MenuItem> items = MenuItemDAO.getAllMenuItems();
            menuItemData.clear();
            menuItemData.addAll(items);
            
            List<Restaurant> restaurants = RestaurantDAO.getAllRestaurants();
            restaurantData.clear();
            restaurantData.addAll(restaurants);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load data: " + e.getMessage());
        }
    }
    
    private void showInsertDialog() {
        Dialog<MenuItem> dialog = new Dialog<>();
        dialog.setTitle("Insert Menu Item");
        dialog.setHeaderText("Enter menu item details:");
        
        // Set the button types
        ButtonType insertButtonType = new ButtonType("Insert", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(insertButtonType, ButtonType.CANCEL);
        
        // Create the form fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField nameField = new TextField();
        nameField.setPromptText("Menu Item Name");
        
        TextField priceField = new TextField();
        priceField.setPromptText("Price");
        
        ComboBox<Restaurant> restaurantCombo = new ComboBox<>(restaurantData);
        restaurantCombo.setPromptText("Select Restaurant");
        
        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Price:"), 0, 1);
        grid.add(priceField, 1, 1);
        grid.add(new Label("Restaurant:"), 0, 2);
        grid.add(restaurantCombo, 1, 2);
        
        dialog.getDialogPane().setContent(grid);
        
        // Convert the result to a menu item object when the insert button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == insertButtonType) {
                try {
                    double price = Double.parseDouble(priceField.getText());
                    Restaurant selectedRestaurant = restaurantCombo.getValue();
                    if (selectedRestaurant == null) {
                        showAlert(Alert.AlertType.WARNING, "Warning", "Please select a restaurant.");
                        return null;
                    }
                    return new MenuItem(0, nameField.getText(), price, selectedRestaurant.getId());
                } catch (NumberFormatException e) {
                    showAlert(Alert.AlertType.WARNING, "Warning", "Please enter a valid price.");
                    return null;
                }
            }
            return null;
        });
        
        // Show the dialog and process the result
        dialog.showAndWait().ifPresent(menuItem -> {
            try {
                int result = MenuItemDAO.insertMenuItem(menuItem);
                if (result > 0) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Menu item inserted successfully!");
                    loadData(); // Refresh the table
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to insert menu item.");
                }
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to insert menu item: " + e.getMessage());
            }
        });
    }
    
    private void showUpdateDialog() {
        if (menuItemTable.getSelectionModel().getSelectedItem() == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a menu item to update.");
            return;
        }
        
        MenuItem selectedMenuItem = menuItemTable.getSelectionModel().getSelectedItem();
        
        Dialog<MenuItem> dialog = new Dialog<>();
        dialog.setTitle("Update Menu Item");
        dialog.setHeaderText("Update menu item details:");
        
        // Set the button types
        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);
        
        // Create the form fields with existing data
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField idField = new TextField(String.valueOf(selectedMenuItem.getId()));
        idField.setEditable(false);
        
        TextField nameField = new TextField(selectedMenuItem.getName());
        nameField.setPromptText("Menu Item Name");
        
        TextField priceField = new TextField(String.valueOf(selectedMenuItem.getPrice()));
        priceField.setPromptText("Price");
        
        // Pre-select the restaurant in the combo box
        ComboBox<Restaurant> restaurantCombo = new ComboBox<>(restaurantData);
        Restaurant selectedRestaurant = null;
        for (Restaurant r : restaurantData) {
            if (r.getId() == selectedMenuItem.getResId()) {
                selectedRestaurant = r;
                break;
            }
        }
        if (selectedRestaurant != null) {
            restaurantCombo.setValue(selectedRestaurant);
        }
        restaurantCombo.setPromptText("Select Restaurant");
        
        grid.add(new Label("ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Price:"), 0, 2);
        grid.add(priceField, 1, 2);
        grid.add(new Label("Restaurant:"), 0, 3);
        grid.add(restaurantCombo, 1, 3);
        
        dialog.getDialogPane().setContent(grid);
        
        // Convert the result to a menu item object when the update button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                try {
                    double price = Double.parseDouble(priceField.getText());
                    Restaurant selectedRestaurantCombo = restaurantCombo.getValue();
                    if (selectedRestaurantCombo == null) {
                        showAlert(Alert.AlertType.WARNING, "Warning", "Please select a restaurant.");
                        return null;
                    }
                    return new MenuItem(
                        selectedMenuItem.getId(),
                        nameField.getText(),
                        price,
                        selectedRestaurantCombo.getId()
                    );
                } catch (NumberFormatException e) {
                    showAlert(Alert.AlertType.WARNING, "Warning", "Please enter a valid price.");
                    return null;
                }
            }
            return null;
        });
        
        // Show the dialog and process the result
        dialog.showAndWait().ifPresent(menuItem -> {
            try {
                boolean result = MenuItemDAO.updateMenuItem(menuItem);
                if (result) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Menu item updated successfully!");
                    loadData(); // Refresh the table
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to update menu item.");
                }
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update menu item: " + e.getMessage());
            }
        });
    }
    
    private void showDeleteDialog() {
        if (menuItemTable.getSelectionModel().getSelectedItem() == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a menu item to delete.");
            return;
        }
        
        MenuItem selectedMenuItem = menuItemTable.getSelectionModel().getSelectedItem();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Menu Item");
        alert.setHeaderText("Delete menu item confirmation");
        alert.setContentText("Are you sure you want to delete menu item: " + selectedMenuItem.getName() + "?");
        
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    boolean result = MenuItemDAO.deleteMenuItem(selectedMenuItem.getId());
                    if (result) {
                        showAlert(Alert.AlertType.INFORMATION, "Success", "Menu item deleted successfully!");
                        loadData(); // Refresh the table
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete menu item.");
                    }
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete menu item: " + e.getMessage());
                }
            }
        });
    }
    
    private void showViewDialog() {
        loadData();
        if (menuItemData.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Information", "No menu items found.");
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