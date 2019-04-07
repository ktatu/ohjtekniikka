/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.ui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import traininglog.domain.Validation;

/**
 *
 * @author ktatu
 */
public class LogScreen {
    
    Validation validator = new Validation();
    
    public Parent getLogView() {
        
        GridPane logView = new GridPane();
        logView.setAlignment(Pos.TOP_LEFT);
        logView.setHgap(30);
        
        // adding new workout field to log
        HBox addWorkout = new HBox();
        addWorkout.setSpacing(5);
        
        Label nameLabel = new Label("Workout:");
        TextField nameField = new TextField();
        
        Label setsLabel = new Label("Number of sets:");
        TextField setsField = new TextField();
        setsField.setPrefWidth(40);
        
        Button add = new Button("Add");
        Button createLog = new Button("Create log");
        
        Label userFeedback = new Label("");
        
        addWorkout.getChildren().addAll(nameLabel, nameField, setsLabel, setsField, add, createLog, userFeedback);
        
        
        // node for adding workout fields and those that are added
        VBox workouts = new VBox();
        workouts.getChildren().add(addWorkout);
        workouts.setSpacing(15);
        
        add.setOnAction((event) -> {
            String nameOfWorkout = nameField.getText();
            String numberOfSets = setsField.getText();
            
            String validation = validator.validateWorkoutInput(nameOfWorkout, numberOfSets);
            
            if (!(validation.equals(""))) {
                userFeedback.setText(validation);
            }
            else {
                workouts.getChildren().add(newWorkout(nameField.getText(), Integer.valueOf(setsField.getText())));
                nameField.clear();
                nameField.requestFocus();
            }
        });
        
        
        logView.add(workouts, 0, 0);
        
        
        return logView;
    }
    
    
    public HBox newWorkout(String name, int sets) {
        HBox workout = new HBox();
        workout.setSpacing(10);
        
        Label nameOfWorkout = new Label(name);
        
        Label setFields = new Label("Sets:");
        workout.getChildren().addAll(nameOfWorkout, setFields);
        
        for (int i = 0; i < sets; i++) {
            TextField newField = new TextField();
            newField.setPrefWidth(70);
            workout.getChildren().add(newField);
        }
        return workout;
    }
}
