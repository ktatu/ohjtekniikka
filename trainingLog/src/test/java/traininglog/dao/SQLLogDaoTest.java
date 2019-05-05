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
    
    static File fakeDatabase;
    static File fakeTrace;
    
    public SQLLogDaoTest() {
        testLogDao = new SQLLogDao("testFile");
        testUsername = "Z84b5mJPQt4";
        testLog = new Log(Date.valueOf(LocalDate.now()), testUsername, "test:100;");  
        fakeDatabase = new File("testFile.mv.db");
        fakeTrace = new File("testFile.trace.db");
    }
    
    @BeforeClass
    public static void setUpClass() {

        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:./testFile", "sa", "");

            PreparedStatement stmt = conn.prepareStatement("CREATE TABLE Log (creationDate DATE, username VARCHAR(30), data VARCHAR(150),"
                + " PRIMARY KEY (creationDate, username));");
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        
        fakeDatabase.delete();
        fakeTrace.delete();
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void createNewLogReturnsCorrectBooleanValues() {
        assertTrue(testLogDao.createLog(testLog));
            
        assertFalse(testLogDao.createLog(testLog));
    }
}
