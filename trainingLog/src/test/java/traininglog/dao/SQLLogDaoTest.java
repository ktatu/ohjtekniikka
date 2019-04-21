/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import traininglog.domain.Log;

/**
 *
 * @author ktatu
 */
public class SQLLogDaoTest {
    
    SQLLogDao testLogDao;
    Log testLog;
    String testUsername;
    
    public SQLLogDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.testLogDao = new SQLLogDao();
        this.testUsername = "Z84b5mJPQt4";
        this.testLog = new Log(Date.valueOf(LocalDate.now()), testUsername, "testdatatestdatatestdatatestdata");        
        
    /*    try {
            Connection conn = DriverManager.getConnection("jdbc:h2:./traininglog", "sa", "");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Log (creationDate, username, data) VALUES (?, ?, ?)");
            stmt.setDate(1, testLog.getDate());
            stmt.setString(2, testLog.getUsername());
            stmt.setString(3, testLog.getData());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }*/
    }
    
    @After
    public void tearDown() {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./traininglog", "sa", "")) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Log WHERE username = ?");
            stmt.setString(1, testUsername);
            
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            
        } catch (Exception ex) {
            System.out.println("failure");
            System.out.println(ex);
        }      
    }
    
    @Test
    public void createNewLogReturnsCorrectBooleanValues() {
        assertTrue(testLogDao.createLog(testLog));
            
        assertFalse(testLogDao.createLog(testLog));
    }

    @Test
    public void searchLogReturnsLog() {
        Log retrievedLog = testLogDao.searchLog(testUsername, Date.valueOf(LocalDate.now()));
        System.out.println(retrievedLog);
        
    //    assertEquals(retrievedLog.getDate(), testLog.getDate());
    //    assertEquals(retrievedLog.getUsername(), testLog.getUsername());
    //    assertEquals(retrievedLog.getData(), testLog.getData());
    }
}
