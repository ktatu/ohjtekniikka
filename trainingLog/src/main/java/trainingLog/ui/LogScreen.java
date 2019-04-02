/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainingLog.ui;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author ktatu
 */
public class LogScreen {
    
    public Parent getLogView() {
        GridPane logView = new GridPane();
        
        Label test = new Label("test");
        
        logView.setAlignment(Pos.CENTER);
        logView.add(test, 0, 0);
        
        return logView;
    }
}
