/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
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
import traininglog.dao.FileUserDao;
import traininglog.dao.SQLLogDao;

/**
 *
 * @author ktatu
 */
public class TrainingLogServiceTest extends ApplicationTest {
    
    TrainingLogService testTrainingLogService;
    Validation testValidator;
    FileUserDao userDao;
    SQLLogDao logDao;
    
    static String createUsername;
    static String createPassword;
    
    static String testUsername;
    static String testPassword;
    
    public TrainingLogServiceTest() {
        this.userDao = new FileUserDao();
        this.logDao = new SQLLogDao();
        this.testTrainingLogService = new TrainingLogService(userDao, logDao);
    }
    
    @BeforeClass
    public static void setUpClass() throws IOException {
        createUsername = "zWG-_v>nTRdZ&2B%";
        createPassword = "Z`B[$?+cq5Q`N8]e";
        
        testUsername = "^?}$Np.=:^Dk7Z5E";
        testPassword = "PQy}C,QNN9reLnW{";
        
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
                .filter(line -> !line.contains(createUsername))
                .filter(line -> !line.contains(createPassword))
                .forEach(writer::println);
        writer.flush();
        writer.close();
        temp.renameTo(file);
    }
    
    @Before
    public void setUp() throws IOException {
        this.userDao = new FileUserDao();
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
        
        assertEquals("Registration succesful", testTrainingLogService.createUser(createUsername, createPassword));
        
        assertEquals("User already exists", testTrainingLogService.createUser(createUsername, createPassword));
    }
}
