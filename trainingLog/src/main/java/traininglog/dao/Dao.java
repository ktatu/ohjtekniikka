/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.dao;


public interface Dao<T, K> {
    boolean create(T object);
    boolean search(T object);
    
}
