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
public class ValidationTest {
    
    Validation testValidator;
    
    public ValidationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testValidator = new Validation();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void stringValidationReturnsCorrectMessages() {
        String testString = "";
        assertEquals("No input", testValidator.validateString(testString));
        
        for (int i = 0; i < 100; i++) {
            testString += "a";
        }
        assertEquals("Input too long: max. characters 30", testValidator.validateString(testString));
        
        testString = "appropriateString";
        
        assertEquals("", testValidator.validateString(testString));
    }
    
    @Test
    public void IntegerValidationReturnsCorrectMessages() {
        String testNumber = "";
        assertEquals("No input", testValidator.validateInteger(testNumber));
        
        for (int i = 0; i < 100; i++) {
            testNumber += "1";
        }
        assertEquals("Input too long: max. characters 30", testValidator.validateInteger(testNumber));
        
        testNumber = "String";
        assertEquals("Input not an integer", testValidator.validateInteger(testNumber));
        
        testNumber = "1.5";
        assertEquals("Input not an integer", testValidator.validateInteger(testNumber));
        
        testNumber = "-10";
        assertEquals("Positive integer required", testValidator.validateInteger(testNumber));
        
        testNumber = "0";
        assertEquals("Positive integer required", testValidator.validateInteger(testNumber));
        
        testNumber = "5";
        assertEquals("", testValidator.validateInteger(testNumber));
    }
    
    @Test
    public void workoutInputValidationReturnsCorrectMessages() {
        String testNumber = "50";
        assertEquals("No more than 10 sets per workout", testValidator.validateExerciseInput(testNumber, testNumber));
        
        testNumber = "";
        assertEquals("No input", testValidator.validateExerciseInput(testNumber, testNumber));
        
        testNumber = "-20";
        assertEquals("Positive integer required", testValidator.validateExerciseInput(testNumber, testNumber));
        
        testNumber = "10";
        assertEquals("", testValidator.validateExerciseInput(testNumber, testNumber));
    }
    
    @Test
    public void createUserInputValidationReturnsCorrectMessages() {
        String username = "";
        String password = "password";
        assertEquals("No input", testValidator.validateCreateUserInput(username, password));
        
        username = "username";
        password = "";
        assertEquals("No input", testValidator.validateCreateUserInput(username, password));
        
        password = "password";
        assertEquals("", testValidator.validateCreateUserInput(username, password));
    }
    
    @Test
    public void logInputValidationReturnsCorrectMessages() {
        ArrayList<String> testExercises = new ArrayList<>();
        testExercises.add("");
        ArrayList<ArrayList<TextField>> testData = new ArrayList<>();
        
        assertEquals("No input", testValidator.validateLogInput(testExercises, testData));
        
        testExercises.clear();
        testExercises.add("first");
        
        ArrayList<TextField> testList = new ArrayList<>();
      
    // Ei onnistu - ExceptionInInitializerError kun koittaa luoda TextFieldin
    //    TextField testField = new TextField();
    //    field.setText("");
    //    testList.add(field);
    //    testData.add(testList);
        
    //    testValidator.validateLogInput(testExercises, testData);
        
      //  assertEquals("First set of every exercise must be filled", testValidator.validateLogInput(testExercises, testData));
        
    /*    testData.clear();
        testList.clear();
        field.setText("11111111");
        testList.add(field);
        testData.add(testList);
        assertEquals("Please provide set input in form 'sets x repetitions' (ex. 5x10)", testValidator.validateLogInput(testExercises, testData));*/
    }
}
