/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.domain;

import java.util.ArrayList;
import traininglog.dao.UserDao;

/**
 *
 * @author ktatu
 */
public class FakeUserDao implements UserDao {
    
    ArrayList<User> userList;
    
    public FakeUserDao() {
        this.userList = new ArrayList<>();
        userList.add(new User("searchUsername", "searchUserPassword"));
    }

    @Override
    public boolean create(String newUser) {
        
        String[] split = newUser.split(" ");
        String username = split[0];
        String password = split[1];
        
        if (searchUsername(username)) {
            return false;
        }
        userList.add(new User(username, password));
        return true;
    }

    @Override
    public boolean searchUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean searchUser(User user) {
        if (userList.contains(user)) {
            return true;
        }
        return false;
    }
}
