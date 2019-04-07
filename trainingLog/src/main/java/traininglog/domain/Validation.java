/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.domain;

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
    
    public String validateWorkoutInput(String name, String sets) {
        if (!(validateString(name).equals(""))) {
            return validateString(name);
        } else if (!(validateInteger(sets).equals(""))) {
            return validateInteger(sets);
        } else if (Integer.valueOf(sets) > 10) {
            return "No more than 10 sets per workout";
        }
        return "";
    }
    
    
}

