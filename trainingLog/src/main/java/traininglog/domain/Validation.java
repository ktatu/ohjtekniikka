/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.domain;

import java.util.ArrayList;
import javafx.scene.control.TextField;

/**
 *
 * @author ktatu
 */
public class Validation {
    
    public String validateString(String input) {
        if (input.trim().length() == 0) {
            return "No input";
        } else if (input.length() > 30) {
            return "Input too long: max. characters 30";
        }
        return "";
    }
    
    public String validateInteger(String input) {
        if (!(validateString(input).equals(""))) {
            return validateString(input);
        }
        try {
            int number = Integer.valueOf(input);
            if (number < 1) {
                return "Positive integer required";
            }
            return "";
        } catch (NumberFormatException e) {
            return "Input not an integer";
        }
    }
    
    public String validateCreateUserInput(String username, String password) {
        if (!(validateString(username).equals(""))) {
            return validateString(username);
        } else if (!(validateString(password).equals(""))) {
            return validateString(password);
        }
        return "";
    }
    
    public String validateExerciseInput(String name, String sets) {
        if (!(validateString(name).equals(""))) {
            return validateString(name);
        } else if (!(validateInteger(sets).equals(""))) {
            return validateInteger(sets);
        } else if (Integer.valueOf(sets) > 10) {
            return "No more than 10 sets per workout";
        }
        return "";
    }
    
    public String validateLogInput(ArrayList<String> exerciseNames, ArrayList<ArrayList<TextField>> setData) {
        for (String exercise : exerciseNames) {
            String validation = validateString(exercise);
            if (!(validation.equals(""))) {
                return validation;
            }
        }
        for (int i = 0; i < setData.size(); i++) {
            ArrayList<TextField> data = setData.get(i);
            if (data.get(0).getText().trim().equals("")) {
                return "First set of every exercise must be filled";
            }
            for (TextField field : data) {
                if (field.getText().length() > 7) {
                    return "Please provide set input in form 'sets x repetitions' (ex. 5x10)";
                }
            }
        }
        return "";
    }
    
    
}

