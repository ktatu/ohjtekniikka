/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.domain;

import java.sql.Date;
import java.util.ArrayList;
import javafx.scene.control.TextField;
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
    
    public boolean searchUser(String username, String password) {
        User loginUser = new User(username, password);
        
        if (userDao.searchUser(loginUser)) {
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
    
    public String createLog(ArrayList<String> exerciseNames, ArrayList<ArrayList<TextField>> setData) {
        String validation = validator.validateLogInput(exerciseNames, setData);
        if (!(validation.equals(""))) {
            return validation;
        }
        // formatting data for database: all data into string
        String dataToString = "";
        
        int idx = 0;
        while (idx < exerciseNames.size()) {
            dataToString += exerciseNames.get(idx) + ":" + formatSetData(setData.get(idx));
            idx++;
        }
        // exercise1:set1,set2,set3;exercise2:set1,set2;
        
        Log log = new Log(this.currentUser, dataToString);
        // WIP: log LogDaon createlle
        try { 
            boolean createLog = logDao.createLog(log);
            if (createLog) {
                return "Log created";
            }
            return "Log not created";
        } catch(java.lang.Exception e) {
            return "Log not created";
        }
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
        Log log = logDao.searchLog(currentUser, date);

        System.out.println(log);
        return formatLogForUi(log);
    }
    
    public ArrayList<String> formatLogForUi(Log log) {
        ArrayList<String> formatted = new ArrayList<>();
        
        String[] splitLog = log.getData().split(";");
        for (String exercise : splitLog) {
            String toList = "";
            String[] nameAndData = exercise.split(":");
            
            toList += nameAndData[0]+": ";
            toList += nameAndData[1].replaceAll(",", ", ");
            formatted.add(toList);
        }
        
        return formatted;
    }
}
