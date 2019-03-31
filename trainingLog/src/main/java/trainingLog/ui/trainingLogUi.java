package trainingLog.ui;

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
import trainingLog.domain.TrainingLogService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class trainingLogUi extends Application {
    
    private TrainingLogService trainingLogService;


    public static void main(String[] args) {
        launch(trainingLogUi.class);
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
        
        Button loginButton = new Button("Login");
        Button regButton = new Button("Create new user");
        
        userInfo.add(usernameLabel, 0, 0);
        userInfo.add(usernameField, 1, 0);
        userInfo.add(passwordLabel, 0, 1);
        userInfo.add(passwordField, 1, 1);
        userInfo.add(loginButton, 1, 3);
        userInfo.add(regButton, 1, 5);
        
        userInfo.setPrefSize(300, 200);
        userInfo.setAlignment(Pos.CENTER);
        userInfo.setVgap(5);
        userInfo.setHgap(5);
        
        loginButton.setOnAction((event) -> {
            
        });
        regButton.setOnAction((event) -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            
        });
        
        
        
        
        
        
        
        
        Scene loginScene = new Scene(userInfo);
        login.setScene(loginScene);
        login.show();
        
        
        
        
    }
    
}
