/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.ui;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import traininglog.domain.TrainingLogService;
import traininglog.domain.Validation;

/**
 *
 * @author ktatu
 */
public class LogScreen {
    
    Validation validator = new Validation();
    TrainingLogService trainingLogService;
    
    // data stored for creating log
    ArrayList<ArrayList<TextField>> setData;
    ArrayList<String> exerciseNames;
    
    // trainingLogService created in TrainingLogUi given to LogScreen
    public LogScreen(TrainingLogService trainingLogService) {
        this.trainingLogService = trainingLogService;
    }
    
    public Parent getLogView() {
        
        setData = new ArrayList<>();
        exerciseNames = new ArrayList<>();
        
        GridPane logView = new GridPane();
        logView.setAlignment(Pos.TOP_LEFT);
        logView.setHgap(30);
        
        // adding new exercise field to log
        HBox addExercise = new HBox();
        addExercise.setSpacing(5);
        
        Label nameLabel = new Label("Workout:");
        TextField nameField = new TextField();
        
        Label setsLabel = new Label("Number of sets:");
        TextField setsField = new TextField();
        setsField.setPrefWidth(40);
        
        Button add = new Button("Add");
        Button createLog = new Button("Create log");
        
        Label userFeedback = new Label("");
        userFeedback.setText("Only one log per day!");
        
        addExercise.getChildren().addAll(nameLabel, nameField, setsLabel, setsField, add, createLog, userFeedback);
        
        // exercises = function for adding workouts and added fields
        VBox exercises = new VBox();
        exercises.getChildren().add(addExercise);
        exercises.setSpacing(15);
        
        add.setOnAction((event) -> {
            String nameOfWorkout = nameField.getText();
            String numberOfSets = setsField.getText();
            
            String validation = validator.validateExerciseInput(nameOfWorkout, numberOfSets);
            
            if (!(validation.equals(""))) {
                userFeedback.setText(validation);
            } else {
                exerciseNames.add(nameField.getText());
                
                exercises.getChildren().add(newExercise(nameField.getText(), Integer.valueOf(setsField.getText())));
                nameField.clear();
                nameField.requestFocus();
                userFeedback.setText("");
            }
        });
        createLog.setOnAction((event) -> {
/*            String outcome = trainingLogService.createLog(exerciseNames, setData);
            if (outcome.equals("New log created")) {
                exercises.getChildren().clear();
                setData.clear();
                exerciseNames.clear();
                
                exercises.getChildren().add(addExercise);
            }
            userFeedback.setText(outcome);*/
        });
        
        logView.add(exercises, 0, 0);
        
        return logView;
    }
    
    
    public HBox newExercise(String name, int sets) {
        HBox exercise = new HBox();
        exercise.setSpacing(10);
        
        Label nameOfExercise = new Label(name);
        
        Label setLabel = new Label("Sets:");
        exercise.getChildren().addAll(nameOfExercise, setLabel);
        
        ArrayList<TextField> setFields = new ArrayList<>();
        
        for (int i = 0; i < sets; i++) {
            TextField newField = new TextField();
            setFields.add(newField);
            newField.setPrefWidth(70);
            exercise.getChildren().add(newField);
        }
        
        setData.add(setFields);
        
        return exercise;
    }
}
