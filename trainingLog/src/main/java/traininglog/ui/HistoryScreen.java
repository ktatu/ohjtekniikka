/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.ui;

import java.sql.Date;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import traininglog.domain.TrainingLogService;

public class HistoryScreen {
    
    TrainingLogService trainingLogService;
    
    public HistoryScreen(TrainingLogService trainingLogService) {
        this.trainingLogService = trainingLogService;
    }
    
    public Parent getHistoryView() {
        
        VBox historyView = new VBox();
        HBox searchLog = new HBox();
        
        DatePicker datePicker = new DatePicker();
        Label dateLabel = new Label("Select date: (dd/mm/yyyy)");
        Button search = new Button("Search");
        Label userFeedback = new Label("");
        
        searchLog.getChildren().addAll(dateLabel, datePicker, search, userFeedback);
        historyView.getChildren().add(searchLog);
        
        
        search.setOnAction((event) -> {
            try {
                Date selected = Date.valueOf(datePicker.getValue());
            } catch (NullPointerException e) {
                userFeedback.setText("Invalid date format");
            }
            
        });
        
        EventHandler<ActionEvent> selectDate = (ActionEvent e) -> {
            Date selected = Date.valueOf(datePicker.getValue());
            
        };
        
        datePicker.setShowWeekNumbers(true);
        
        historyView.setAlignment(Pos.TOP_CENTER);
        searchLog.setAlignment(Pos.CENTER);
        searchLog.setSpacing(10);
        
        return historyView;
    }
    
}
