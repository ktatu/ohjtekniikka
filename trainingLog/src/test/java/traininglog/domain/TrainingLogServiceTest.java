/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.domain;

import java.util.ArrayList;
import javafx.scene.control.TextField;
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
public class TrainingLogServiceTest {
    
    TrainingLogService testTrainingLogService;
    
    public TrainingLogServiceTest() {
        this.testTrainingLogService = new TrainingLogService();
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void methodFormatSetDataFormatsDataCorrectly() {
        ArrayList<TextField> testList = new ArrayList<>();
        TextField testField = new TextField();
        testField.setText("5x10");
        testList.add(testField);
        assertEquals("5x10;", testTrainingLogService.formatSetData(testList));
    }
}
