/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import traininglog.domain.User;

/**
 *
 * @author ktatu
 */
public class FileUserDaoTest {
    
    static String testUsername;
    static String testPassword;
    
    User testUser;
    
    static FileUserDao testUserDao;
    static File fakeUsers;
    
    static String createUserTestName;
    static String createUserTestPassword;
    
    @BeforeClass
    public static void setUpClass() throws IOException {
        // UserDao-testausta varten luodaan tilapäinen tekstitiedosto
        fakeUsers = new File("fakeUsers.txt");
        
        testUserDao = new FileUserDao("fakeUsers.txt");
        testUsername = "P}#[6/g$g#yZ3bs";
        testPassword = "-QHkx<#@+g@f8E{";
        
        createUserTestName = "\\8R[Wh3d$KnU[Z";
        createUserTestPassword = "M,K+V<9h*27~ZS7";
        
        try (FileWriter writer = new FileWriter(fakeUsers, true)) {
                writer.append(testUsername + "," + testPassword);
                writer.append(System.getProperty("line.separator"));
                writer.close();
        }
    }
    
    @AfterClass
    public static void tearDownClass() throws IOException {
        
        fakeUsers.delete();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void searchUsernamesWorks() {
        assertTrue(testUserDao.searchUsername(testUsername));
        
        String searchName = "_7xW/zyJ!hJr;[R3";
        assertFalse(testUserDao.searchUsername(searchName));
    }
    
    @Test
    public void createNewUserWorks() {
        assertFalse(testUserDao.create(testUsername + " " + testPassword));
        
        assertTrue(testUserDao.create(createUserTestName + " " + createUserTestPassword));
    }
    
    @Test
    public void searchUsersWorks() {
        testUser = new User(testUsername, testPassword);
        
        assertTrue(testUserDao.searchUser(testUser));
        
        String notInFileUsername = ";\\8R[Wh3d$KnU[Z";
        String notInFilePassword = "M,K+V<9h*27~ZS7";
        User notInFile = new User(notInFileUsername, notInFilePassword);
        assertFalse(testUserDao.searchUser(notInFile));
    }
}
