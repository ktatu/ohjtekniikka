/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.domain;

import java.sql.Date;
import java.util.ArrayList;
import javafx.scene.control.TextField;
import traininglog.dao.SQLLogDao;
import traininglog.dao.FileUserDao;
import traininglog.dao.LogDao;
import traininglog.dao.UserDao;

public class TrainingLogService {
    private UserDao userDao;
    private LogDao logDao;
    private String currentUser;
    private Validation validator;
    
    public TrainingLogService(UserDao userDao, LogDao logDao) {
        this.userDao = userDao;
        this.logDao = logDao;
        this.validator = new Validation();
    }
    
    // TODO: pitäis varmaan ottaa se salasanakin huomioon
    public boolean searchUser(String username) {
        if (userDao.search(username)) {
            this.currentUser = username;
            return true;
        }
        return false;
    }
    
    public String createUser(String username, String password) {
        String validation = validator.validateCreateUserInput(username, password);
        if (!(validation.equals(""))) {
            return validation;
        }
        if (userDao.create(username + " " + password)) {
            return "Registration succesful";
        } else {
            return "User already exists";
        }
    }
    
    public String createLog(ArrayList<String> exerciseNames, ArrayList<ArrayList<TextField>> setData) throws Exception {
        String validation = validator.validateLogInput(exerciseNames, setData);
        if (!(validation.equals(""))) {
            return validation;
        }
        // formatting data for database: all data into string
        String dataToString = "";
        
        int idx = 0;
        while (idx < exerciseNames.size()) {
            dataToString += exerciseNames.get(idx) + ":" + formatSetData(setData.get(idx)) + "\n";
            idx++;
        }
        
        Log log = new Log(this.currentUser, dataToString);
        // WIP: log LogDaon createlle
        boolean createLog = logDao.createLog(log);
        if (createLog) {
            return "New log created";
        }
        return "Log not created";
    } 
    
    // assisting method for createLog
    public String formatSetData(ArrayList<TextField> fieldList) {
        String returnString = "";
        if (fieldList.size() == 1) {
            returnString += fieldList.get(0).getText() + ";";
            return returnString;
        }
        
        int idx = 0;
        while (idx < fieldList.size() - 1) {
            returnString += fieldList.get(idx).getText() + ",";
            idx++;
        }
        returnString += fieldList.get(idx).getText() + ";";
        
        return returnString;
    }
    
    public ArrayList<String> searchLog(Date date) {
        if (validator.validateDate(date).equals("")) {
            return null;
        }
        return null;
    }
}
