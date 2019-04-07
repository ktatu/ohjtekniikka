package traininglog.ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import traininglog.domain.TrainingLogService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

/*
            try (Connection con = DriverManager.getConnection("jdbc:h2:./database", "sa", "")) {
                System.out.println("toimii");
            } catch (SQLException ex) {
                System.out.println("ei toimi");
            }
*/


public class TrainingLogUi extends Application {
    
    private TrainingLogService trainingLogService;
    private LogScreen logScreen;


    public static void main(String[] args) {
        launch(TrainingLogUi.class);
    }
    
    @Override
    public void init() {
        trainingLogService = new TrainingLogService();
        logScreen = new LogScreen();
    }

    @Override
    public void start(Stage login) throws Exception {
        login.setTitle("trainingLog");

        //login and registration
        GridPane userInfo = new GridPane();
        
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Label systemFeedback = new Label("");
        
        Button loginButton = new Button("Login");
        Button regButton = new Button("Create new user");
        
        userInfo.add(usernameLabel, 0, 0);
        userInfo.add(usernameField, 1, 0);
        userInfo.add(passwordLabel, 0, 1);
        userInfo.add(passwordField, 1, 1);
        userInfo.add(loginButton, 1, 3);
        userInfo.add(regButton, 1, 4);
        userInfo.add(systemFeedback, 1, 5);
        
        userInfo.setPrefSize(300, 200);
        userInfo.setAlignment(Pos.CENTER);
        userInfo.setVgap(5);
        userInfo.setHgap(5);
        
        //main view: log and history
        BorderPane mainView = new BorderPane();
        mainView.setPrefSize(800, 400);
        
        HBox topMenu = new HBox();
        topMenu.setStyle("-fx-background-color: #cc675f;");
        topMenu.setPadding(new Insets(20, 20, 20, 20));
        topMenu.setSpacing(10);
        
        Button newLog = new Button("New Log");
        Button history = new Button("History");
        
        topMenu.getChildren().addAll(newLog, history);
        mainView.setTop(topMenu);
        mainView.setCenter(logScreen.getLogView());
        
        //TODO: topMenu button actions
        
        //Scenes
        Scene loginScene = new Scene(userInfo);
        Scene mainScene = new Scene(mainView);
        
        //login screen actions
        loginButton.setOnAction((event) -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (trainingLogService.searchUser(username)) {
                login.setScene(mainScene);
            } else {
                systemFeedback.setText("User not found");
            }
        });        
        regButton.setOnAction((event) -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            
            systemFeedback.setText(trainingLogService.createUser(username, password));
        });
        
        login.setScene(loginScene);
        login.show();
        
    }
}
