/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.domain;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.scene.control.TextField;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

import org.testfx.framework.junit.ApplicationTest;

/**
 *
 * @author ktatu
 */
public class TrainingLogServiceTest extends ApplicationTest {
    
    TrainingLogService testTrainingLogService;
    Validation testValidator;
    FakeUserDao userDao;
    FakeLogDao logDao;
    
    static ArrayList<ArrayList<TextField>> testSetData;
    
    String testUsername;
    String testPassword;
    
    static File fakeUsers;
    static File testFile;
    static File testTrace;
    
    public TrainingLogServiceTest() {
        
        testUsername = "testUser";
        testPassword = "testPassword";
        
        
        // tilapäistiedostot jotka poistetaan testien päätyttyä
        fakeUsers = new File("fakeUsers.txt");
        testFile = new File("testFile.mv.db");
        testTrace = new File("testFile.trace.db");
        
        this.userDao = new FakeUserDao();
        this.logDao = new FakeLogDao();
        
        this.testTrainingLogService = new TrainingLogService(userDao, logDao);
        
    }
    
    @BeforeClass
    public static void setUpClass() {                
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
    }
    
    @After
    public void tearDown() throws IOException {
    }
    
    @Test
    public void methodFormatSetDataFormatsDataCorrectly() {
        ArrayList<TextField> testList = new ArrayList<>();
        TextField testField = new TextField();
        testField.setText("5x10");
        testList.add(testField);
        assertEquals("5x10;", testTrainingLogService.formatSetData(testList));
    }
    
    @Test
    public void createUserReturnsCorrectMessages() {
        String testUsername1 = "";
        String testPassword1 = "";
        assertThat(testTrainingLogService.createUser(testUsername1, testPassword1), not (""));
        
        assertEquals("Registration succesful", testTrainingLogService.createUser(testUsername, testPassword));
        
        assertEquals("User already exists", testTrainingLogService.createUser(testUsername, testPassword));
        
    }
    
    @Test
    public void searchUserFindsUserIfExists() {
        
        assertTrue(testTrainingLogService.searchUser("searchUsername", "searchUserPassword"));
        
        assertFalse(testTrainingLogService.searchUser("Lf>dyr3TS]U/p?@", "x9.]c+P<nRSJ5+:"));
        
        assertFalse(testTrainingLogService.searchUser("", ""));
    }
    
    // TextFieldien luominen ei onnistu testimetodien ulkopuolella
    @Test
    public void createLogReturnsCorrectMessages() {
        
        ArrayList<String> testExercises = new ArrayList<>();
        testExercises.add("firstExercise");
        testExercises.add("secondExercise");
        
        ArrayList<ArrayList<TextField>> testData = new ArrayList<>();
        
        ArrayList<TextField> firstExSetData = new ArrayList<>();
        
        TextField firstExField = new TextField();
        firstExField.setText("5x100");
        
        TextField secondExField = new TextField();
        secondExField.setText("10x200");
        
        firstExSetData.add(firstExField);
        firstExSetData.add(secondExField);
        
        ArrayList<TextField> secondExSetData = new ArrayList<>();
        
        firstExField.setText("1x10");
        secondExField.setText("2x20");
        
        secondExSetData.add(firstExField);
        secondExSetData.add(secondExField);
        
        testData.add(firstExSetData);
        testData.add(secondExSetData);
        
        testTrainingLogService.loginUser(testUsername);
        
        
        assertEquals("Log created", testTrainingLogService.createLog(testExercises, testData));
        
        assertEquals("Log not created", testTrainingLogService.createLog(testExercises, testData));
    }
    
    @Test
    public void setDataIsFormattedCorectly() {
        
        ArrayList<TextField> firstExSetData = new ArrayList<>();
        
        TextField firstExField = new TextField();
        firstExField.setText("5x100");
        
        TextField secondExField = new TextField();
        secondExField.setText("10x200");
        
        firstExSetData.add(firstExField);
        firstExSetData.add(secondExField);
        
        assertEquals("5x100,10x200;", testTrainingLogService.formatSetData(firstExSetData));
    }
    
    @Test
    public void retrievedLogIsFormattedCorrectly() {
        
        Log retrievedLog = new Log(Date.valueOf(LocalDate.now()), "testUser", "ex1:5x40,5x50;ex2:10x100,15x110;");
        
        ArrayList<String> formattedData = testTrainingLogService.formatLogForUi(retrievedLog);
        
        assertEquals("ex1: 5x40, 5x50", formattedData.get(0));
        
        assertEquals("ex2: 10x100, 15x110", formattedData.get(1));
    }
    // Log in FakeLogDao: Date.valueOf(LocalDate.now()), "testUser", "firstEx:3x40,4x50,5x60;secondEx:10x100,15x110,20x120;
    @Test
    public void searchLogReturnsCorrectlyFormattedArrayList() {
        ArrayList<String> listToBeReturned = new ArrayList<>();
        listToBeReturned.add("firstEx: 3x40, 4x50, 5x60");
        listToBeReturned.add("secondEx: 10x100, 15x110, 20x120");
        
        testTrainingLogService.loginUser("searchLogUser");
        assertEquals(listToBeReturned, testTrainingLogService.searchLog(Date.valueOf(LocalDate.now())));
    }
}
