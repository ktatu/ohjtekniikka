/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.ui;

import java.sql.Date;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import traininglog.domain.TrainingLogService;
import traininglog.domain.Validation;

public class HistoryScreen {
    
    TrainingLogService trainingLogService;
    Validation validator;
    
    public HistoryScreen(TrainingLogService trainingLogService) {
        this.trainingLogService = trainingLogService;
        this.validator = new Validation();
    }
    
    public Parent getHistoryView() {
        
        VBox historyView = new VBox();
        HBox searchLog = new HBox();
        VBox exercises = new VBox();
        
        DatePicker datePicker = new DatePicker();
        Label dateLabel = new Label("Select date: (dd/mm/yyyy)");
        Button search = new Button("Search");
        Label userFeedback = new Label("");
        
        searchLog.getChildren().addAll(dateLabel, datePicker, search, userFeedback);
        historyView.getChildren().addAll(searchLog, exercises);
        
        
        search.setOnAction((event) -> {
            try {
                Date selected = Date.valueOf(datePicker.getValue());
                System.out.println(selected);
                String validation = validator.validateDate(selected);
                
                if (!validation.equals("")) {
                    userFeedback.setText(validation);
                } else {
                    ArrayList<String> formattedLog = trainingLogService.searchLog(Date.valueOf(datePicker.getValue()));
                    if (formattedLog == null) {
                        userFeedback.setText(("Error in retrieving log"));
                    }   else {
                        String[] dateToFormat = selected.toString().split("-");
                        String dateToScreen = dateToFormat[2]+"."+dateToFormat[1]+"."+dateToFormat[0];
                        
                        exercises.getChildren().add(new Label(dateToScreen));
                        for (String lineInLog: formattedLog) {
                            exercises.getChildren().add(new Label(lineInLog));
                        }
                    }                    
                }
            } catch (NullPointerException e) {
                userFeedback.setText("No log for selected date");
            }
            
        });
        
        EventHandler<ActionEvent> selectDate = (ActionEvent e) -> {
            Date selected = Date.valueOf(datePicker.getValue());
            
        };
        
        datePicker.setShowWeekNumbers(true);
        
        historyView.setAlignment(Pos.TOP_CENTER);
        searchLog.setAlignment(Pos.CENTER);
        exercises.setAlignment(Pos.CENTER_LEFT);
        exercises.setSpacing(10);
        exercises.setPadding(new Insets(20, 0, 20, 20));
        searchLog.setSpacing(10);
        
        return historyView;
    }
    
}
