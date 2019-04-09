/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.dao;


public interface UserDao {
    
    boolean create(String newUser);
    
    boolean search(String username); 
    
}
