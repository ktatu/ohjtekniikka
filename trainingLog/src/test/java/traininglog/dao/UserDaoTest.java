/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
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
public class UserDaoTest {
    
    static String testUsername;
    static String testPassword;
    static FileUserDao testUserDao;
    
    static String createUserTestName;
    static String createUserTestPassword;
    
    @BeforeClass
    public static void setUpClass() throws IOException {
        testUserDao = new FileUserDao();
        testUsername = "P}#[6/g$g#yZ3bs";
        testPassword = "-QHkx<#@+g@f8E{";
        
        createUserTestName = "\\8R[Wh3d$KnU[Z";
        createUserTestPassword = "M,K+V<9h*27~ZS7";
        
        try (FileWriter writer = new FileWriter("users.txt", true)) {
                writer.append(testUsername + "," + testPassword);
                writer.append(System.getProperty("line.separator"));
                writer.close();
        }
    }
    
    @AfterClass
    public static void tearDownClass() throws IOException {
        File file = new File("users.txt");
        File temp = new File("temp");
        PrintWriter writer = new PrintWriter(new FileWriter(temp));
        Files.lines(file.toPath())
                .filter(line -> !line.contains(testUsername))
                //jostain syystä pelkkä contains testUsername ei riittänyt, pitää kattoa kans salasana
                .filter(line -> !line.contains(testPassword))
                //trainingLogServiceTestistä
                .filter(line -> !line.contains("zWG-_v>nTRdZ&2B%"))
                .filter(line -> !line.contains("Z`B[$?+cq5Q`N8]e"))
                .filter(line -> !line.contains(createUserTestName))
                .filter(line -> !line.contains(createUserTestPassword))
                .forEach(writer::println);
        writer.flush();
        writer.close();
        temp.renameTo(file);
    }
    
    @Before
    public void setUp() throws IOException {
/*        testUserDao = new FileUserDao();
        testUsername = "P}#[6/g$g#yZ3bs";
        testPassword = "-QHkx<#@+g@f8E{";
        
        createUserTestName = "\\8R[Wh3d$KnU[Z";
        createUserTestPassword = "M,K+V<9h*27~ZS7";
        
        try (FileWriter writer = new FileWriter("users.txt", true)) {
                writer.append(testUsername + "," + testPassword);
                writer.append(System.getProperty("line.separator"));
                writer.close();
        }*/
        
    }
    
    @After
    public void tearDown() throws IOException {
/*        File file = new File("users.txt");
        File temp = new File("temp");
        PrintWriter writer = new PrintWriter(new FileWriter(temp));
        Files.lines(file.toPath())
                .filter(line -> !line.contains(testUsername))
                .filter(line -> !line.contains(createUserTestName))
                .forEach(writer::println);
        writer.flush();
        writer.close();
        temp.renameTo(file);*/
    }

    @Test
    public void searchUsersWorks() {
        assertTrue(testUserDao.search(testUsername));
        
        String searchName = "_7xW/zyJ!hJr;[R3";
        assertFalse(testUserDao.search(searchName));
    }
    
    @Test
    public void createNewUserWorks() {
        assertFalse(testUserDao.create(testUsername + " " + testPassword));
        
        String createName = ";\\8R[Wh3d$KnU[Z";
        String createPassword = "M,K+V<9h*27~ZS7";
        
        
        assertTrue(testUserDao.create(createUserTestName + " " + createUserTestPassword));
    }
}
