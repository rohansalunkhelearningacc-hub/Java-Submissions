import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;

public class RestaurantManagementApp extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Restaurant Management System");
        
        initRootLayout();
    }
    
    private void initRootLayout() {
        rootLayout = new BorderPane();
        rootLayout.setStyle("-fx-background-color: #f0f8ff;");
        
        // Create header
        Label headerLabel = new Label("Restaurant Management System");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        headerLabel.setTextFill(Color.DARKBLUE);
        headerLabel.setAlignment(Pos.CENTER);
        headerLabel.setMaxWidth(Double.MAX_VALUE);
        headerLabel.setPadding(new Insets(20));
        
        // Create main menu buttons
        VBox menuBox = new VBox(15);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setPadding(new Insets(30));
        
        Button restaurantBtn = createStyledButton("Restaurant Operations");
        restaurantBtn.setOnAction(e -> showRestaurantOperations());
        
        Button menuItemBtn = createStyledButton("Menu Item Operations");
        menuItemBtn.setOnAction(e -> showMenuItemOperations());
        
        Button viewAllBtn = createStyledButton("View All Data");
        viewAllBtn.setOnAction(e -> showAllData());
        
        Button exitBtn = createStyledButton("Exit");
        exitBtn.setOnAction(e -> System.exit(0));
        
        menuBox.getChildren().addAll(restaurantBtn, menuItemBtn, viewAllBtn, exitBtn);
        
        rootLayout.setTop(headerLabel);
        rootLayout.setCenter(menuBox);
        
        Scene scene = new Scene(rootLayout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private Button createStyledButton(String text) {
        Button btn = new Button(text);
        btn.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        btn.setPrefSize(250, 50);
        btn.setStyle(
            "-fx-background-color: #4CAF50; " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 10; " +
            "-fx-border-radius: 10; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 0);"
        );
        btn.setOnMouseEntered(e -> btn.setStyle(
            "-fx-background-color: #45a049; " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 10; " +
            "-fx-border-radius: 10; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 15, 0, 0, 0);"
        ));
        btn.setOnMouseExited(e -> btn.setStyle(
            "-fx-background-color: #4CAF50; " +
            "-fx-text-fill: white; " +
            "-fx-background-radius: 10; " +
            "-fx-border-radius: 10; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 0);"
        ));
        return btn;
    }
    
    private void showRestaurantOperations() {
        RestaurantOperationsPane restaurantPane = new RestaurantOperationsPane(this::refreshMainMenu);
        rootLayout.setCenter(restaurantPane);
    }
    
    private void showMenuItemOperations() {
        MenuItemOperationsPane menuItemPane = new MenuItemOperationsPane(this::refreshMainMenu);
        rootLayout.setCenter(menuItemPane);
    }
    
    private void showAllData() {
        AllDataPane allDataPane = new AllDataPane(this::refreshMainMenu);
        rootLayout.setCenter(allDataPane);
    }
    
    private void refreshMainMenu() {
        initRootLayout();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}