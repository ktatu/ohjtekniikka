/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.dao;

import traininglog.domain.User;


public interface UserDao {
    
    boolean create(String newUser);
    boolean searchUsername(String username);
    boolean searchUser(User user);
    
}
