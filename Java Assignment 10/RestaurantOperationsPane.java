import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

public class RestaurantOperationsPane extends VBox {
    
    private Runnable onBack;
    private TableView<Restaurant> restaurantTable;
    private ObservableList<Restaurant> restaurantData;
    
    public RestaurantOperationsPane(Runnable onBack) {
        this.onBack = onBack;
        this.restaurantData = FXCollections.observableArrayList();
        initializeComponents();
        loadData();
    }
    
    private void initializeComponents() {
        this.setSpacing(15);
        this.setPadding(new Insets(20));
        this.setAlignment(Pos.TOP_CENTER);
        
        // Title
        Label titleLabel = new Label("Restaurant Operations");
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
        
        // Table for displaying restaurants
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
        restaurantTable.setPrefHeight(300);
        
        this.getChildren().addAll(titleLabel, buttonBox, restaurantTable);
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
            List<Restaurant> restaurants = RestaurantDAO.getAllRestaurants();
            restaurantData.clear();
            restaurantData.addAll(restaurants);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load restaurants: " + e.getMessage());
        }
    }
    
    private void showInsertDialog() {
        Dialog<Restaurant> dialog = new Dialog<>();
        dialog.setTitle("Insert Restaurant");
        dialog.setHeaderText("Enter restaurant details:");
        
        // Set the button types
        ButtonType insertButtonType = new ButtonType("Insert", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(insertButtonType, ButtonType.CANCEL);
        
        // Create the form fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField nameField = new TextField();
        nameField.setPromptText("Restaurant Name");
        
        TextArea addressArea = new TextArea();
        addressArea.setPromptText("Address");
        addressArea.setPrefRowCount(2);
        
        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Address:"), 0, 1);
        grid.add(addressArea, 1, 1);
        
        dialog.getDialogPane().setContent(grid);
        
        // Convert the result to a restaurant object when the insert button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == insertButtonType) {
                return new Restaurant(0, nameField.getText(), addressArea.getText());
            }
            return null;
        });
        
        // Show the dialog and process the result
        dialog.showAndWait().ifPresent(restaurant -> {
            try {
                int result = RestaurantDAO.insertRestaurant(restaurant);
                if (result > 0) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Restaurant inserted successfully!");
                    loadData(); // Refresh the table
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to insert restaurant.");
                }
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to insert restaurant: " + e.getMessage());
            }
        });
    }
    
    private void showUpdateDialog() {
        if (restaurantTable.getSelectionModel().getSelectedItem() == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a restaurant to update.");
            return;
        }
        
        Restaurant selectedRestaurant = restaurantTable.getSelectionModel().getSelectedItem();
        
        Dialog<Restaurant> dialog = new Dialog<>();
        dialog.setTitle("Update Restaurant");
        dialog.setHeaderText("Update restaurant details:");
        
        // Set the button types
        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);
        
        // Create the form fields with existing data
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        
        TextField idField = new TextField(String.valueOf(selectedRestaurant.getId()));
        idField.setEditable(false);
        
        TextField nameField = new TextField(selectedRestaurant.getName());
        nameField.setPromptText("Restaurant Name");
        
        TextArea addressArea = new TextArea(selectedRestaurant.getAddress());
        addressArea.setPromptText("Address");
        addressArea.setPrefRowCount(2);
        
        grid.add(new Label("ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("Address:"), 0, 2);
        grid.add(addressArea, 1, 2);
        
        dialog.getDialogPane().setContent(grid);
        
        // Convert the result to a restaurant object when the update button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                return new Restaurant(
                    selectedRestaurant.getId(),
                    nameField.getText(),
                    addressArea.getText()
                );
            }
            return null;
        });
        
        // Show the dialog and process the result
        dialog.showAndWait().ifPresent(restaurant -> {
            try {
                boolean result = RestaurantDAO.updateRestaurant(restaurant);
                if (result) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Restaurant updated successfully!");
                    loadData(); // Refresh the table
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to update restaurant.");
                }
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update restaurant: " + e.getMessage());
            }
        });
    }
    
    private void showDeleteDialog() {
        if (restaurantTable.getSelectionModel().getSelectedItem() == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a restaurant to delete.");
            return;
        }
        
        Restaurant selectedRestaurant = restaurantTable.getSelectionModel().getSelectedItem();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Restaurant");
        alert.setHeaderText("Delete restaurant confirmation");
        alert.setContentText("Are you sure you want to delete restaurant: " + selectedRestaurant.getName() + "?");
        
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    boolean result = RestaurantDAO.deleteRestaurant(selectedRestaurant.getId());
                    if (result) {
                        showAlert(Alert.AlertType.INFORMATION, "Success", "Restaurant deleted successfully!");
                        loadData(); // Refresh the table
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete restaurant.");
                    }
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete restaurant: " + e.getMessage());
                }
            }
        });
    }
    
    private void showViewDialog() {
        loadData();
        if (restaurantData.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Information", "No restaurants found.");
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