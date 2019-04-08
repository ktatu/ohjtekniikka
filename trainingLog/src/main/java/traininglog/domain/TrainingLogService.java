/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.domain;

import java.util.ArrayList;
import javafx.scene.control.TextField;
import traininglog.dao.UserDao;

public class TrainingLogService {
    private UserDao userDao;
    private String username;
    private Validation validator;
    
    public TrainingLogService() {
        this.userDao = new UserDao();
        this.validator = new Validation();
    }
    
    public boolean searchUser(String username) {
        if (userDao.search(username)) {
            this.username = username;
            return true;
        }
        return false;
    }
    
    public String createUser(String username, String password) {
        String validation = validator.validateCreateUserInput(username, password);
        if (!(validation.equals(""))) {
            return validation;
        }
        if (userDao.create(username, password)) {
            return "Registration succesful";
        } else {
            return "User already exists";
        }
    }
    
    public String createLog(ArrayList<String> exerciseNames, ArrayList<ArrayList<TextField>> setData) {
        String validation = validator.validateLogInput(exerciseNames, setData);
        if (!(validation.equals(""))) {
            return validation;
        }
        return "New log created";
    } 
    
}
