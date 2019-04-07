/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.domain;

import traininglog.dao.UserDao;

public class TrainingLogService {
    private UserDao userDao;
    private String username;
    
    
    public TrainingLogService() {
        this.userDao = new UserDao();
    }
    
    public boolean searchUser(String username) {
        if (userDao.search(username)) {
            this.username = username;
            return true;
        }
        return false;
    }
    
    public String createUser(String username, String password) {
        if (!(validateUserInput(username, password))) {
            return "All fields must be filled";
        }
        if (userDao.create(username, password)) {
            return "Registration succesful";
        } else {
            return "User already exists";
        }
    }
    
    public boolean testi() {
        return true;
    }
    
    public boolean validateUserInput(String username, String password) {
        if (username.trim().equals("") || password.trim().equals("")) {
            return false;
        }
        return true;
    }
}
