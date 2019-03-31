/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainingLog.domain;

import trainingLog.dao.UserDao;

public class TrainingLogService {
    private UserDao userDao;
    private User user;
    
    
    public TrainingLogService() {
        this.userDao = new UserDao();
    }
    
    public boolean searchUser(String username, String password) {
        if (userDao.search(username+","+password)) {
            user = new User(username, password);
            return true;
        }
        return false;
    }
    
    public boolean createUser(String username, String password) {
        System.out.println("päästiin serviceen");
        if (userDao.create(username+","+password)) {
            return true;
        }
        return false;
    }
}
