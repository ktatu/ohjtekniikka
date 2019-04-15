/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.domain;

import java.sql.Date;
import java.time.LocalDate;
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
public class LogTest {
    
    private Log testLog;
    
    public LogTest() {;   
        this.testLog = new Log("testUsername", "testData");
        testLog = null;
        this.testLog = new Log(Date.valueOf(LocalDate.now()), "testUsername", "testData");
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
    public void getUsernameWorks() {
        assertEquals("testUsername", testLog.getUsername());
    }
    
    @Test
    public void getDateWorks() {
        assertEquals(Date.valueOf(LocalDate.now()), testLog.getDate());
    }
    
    @Test
    public void getDataWorks() {
        assertEquals("testData", testLog.getData());
    }
}
