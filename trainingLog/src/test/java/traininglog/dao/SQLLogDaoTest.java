/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.dao;

import java.io.File;
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
    
    String database;
    
    File fakeDatabase;
    File fakeTrace;
    
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
        
        this.fakeDatabase = new File("testFile.mv.db");
        this.fakeTrace = new File("testFile.trace.db");
        
        this.testLogDao = new SQLLogDao("testFile");
        this.testUsername = "Z84b5mJPQt4";
        this.testLog = new Log(Date.valueOf(LocalDate.now()), testUsername, "testdatatestdatatestdatatestdata");        
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:./testFile", "sa", "");
            
            PreparedStatement stmt = conn.prepareStatement("CREATE TABLE Log (creationDate DATE, username VARCHAR(30), data VARCHAR(150),"
                    + " PRIMARY KEY (creationDate, username));");
            stmt.executeUpdate();
            System.out.println("onnistuu taulun teko");
            stmt.close();
            conn.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
    }
    
    @After
    public void tearDown() {
          
        this.fakeDatabase.delete();
        this.fakeTrace.delete();
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
        
        assertEquals(retrievedLog.getDate(), testLog.getDate());
        assertEquals(retrievedLog.getUsername(), testLog.getUsername());
        assertEquals(retrievedLog.getData(), testLog.getData());
    }
}
