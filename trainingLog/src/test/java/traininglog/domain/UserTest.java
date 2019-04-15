/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ktatu
 */
public class UserTest {
    
    User testUser;
    
    public UserTest() {
        testUser = new User("test", "password");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void UsersNotSame() {
        User compareTo = new User("another", "user");
        
        assertFalse(testUser.equals(compareTo));
    }
    
    @Test
    public void UsersAreSame() {
        User test = new User("test", "password");
        assertTrue(testUser.equals(test));
        
        assertTrue(testUser.equals(testUser));
        
        test = null;
        assertFalse(testUser.equals(test));
        
        int testNumber = 1;
        assertFalse(testUser.equals(testNumber));
    }
    
    @Test
    public void getPasswordWorks() {
        assertEquals("password", testUser.getPassword());
    }
    
    @Test
    public void getUsernameWorks() {
        assertEquals("test", testUser.getUsername());
    }
}
