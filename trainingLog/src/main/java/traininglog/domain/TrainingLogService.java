/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.domain;

import java.sql.Date;
import java.util.ArrayList;
import javafx.scene.control.TextField;
import traininglog.dao.LogDao;
import traininglog.dao.UserDao;


/**
 * Sovelluslogiikasta vastaava luokka, toimii siltana Dao:jen ja käyttöliittymän välillä.
 */
public class TrainingLogService {
    private UserDao userDao;
    private LogDao logDao;
    private String currentUser;
    private Validation validator;
    
    public TrainingLogService(UserDao userDao, LogDao logDao) {
        this.userDao = userDao;
        this.logDao = logDao;
        this.validator = new Validation();
    }
    
    /**
     * Kirjautuvan käyttäjän tunnusten etsintä.
     * @param username Käyttäjätunnus.
     * @param password Salasana.
     * @return True jos tunnus löytyy tiedostosta, false jos ei.
     */
    public boolean searchUser(String username, String password) {
        User loginUser = new User(username, password);
        
        if (userDao.searchUser(loginUser)) {
            loginUser(username);
            return true;
        }
        return false;
    }
    
    /**
     * Uuden käyttäjätunnuksen luominen.
     * @param username Luotavan käyttäjätunnuksen nimi.
     * @param password Luotavan käyttäjätunnuksen salasana.
     * @return Validationista saadun viestin jos käyttäjänimi tai salasana on virheellisessä muodossa. 
     * "Registration succesful" jos tunnusten luonti onnistui, "User already exists" jos username on jo käytössä.
     */
    public String createUser(String username, String password) {
        String validation = validator.validateCreateUserInput(username, password);
        if (!(validation.equals(""))) {
            return validation;
        }
        if (userDao.create(username + " " + password)) {
            return "Registration succesful";
        } else {
            return "User already exists";
        }
    }
    
    /**
     * Uuden lokin tallettaminen tietokantatiedostoon.
     * @param exerciseNames  Harjoitteiden nimet merkkijonolistana.
     * @param setData   Lista, joka sisältää kuhunkin harjoitteeseen liittyvän syötteen listana. Jokaiselle exerciseNames-listan merkkijonolle on siis oma lista setDatassa.
     * @return Validationista saadun viestin jos jotakin jommassa kummassa parametri-listassa on virheellisessä muodossa. 
     * "Log created" jos createLog palauttaa true, "Log not created" jos palauttaa false tai tapahtuu virhe.
     */
    public String createLog(ArrayList<String> exerciseNames, ArrayList<ArrayList<TextField>> setData) {
        String validation = validator.validateLogInput(exerciseNames, setData);
        if (!(validation.equals(""))) {
            return validation;
        }
        
        String dataToString = "";
        for (int i = 0; i < exerciseNames.size(); i++) {
            dataToString += exerciseNames.get(i) + ":" + formatSetData(setData.get(i));
        }
        
        
        Log log = new Log(this.currentUser, dataToString);
        try { 
            boolean createLog = logDao.createLog(log);
            if (createLog) {
                return "Log created";
            }
            return "Log not created";
        } catch (java.lang.Exception e) {
            return "Log not created";
        }
    } 
    
    /**
     * Apumetodi createLogille, formatoi listoista saadun datan merkkijonoiksi.
     * @param fieldList TextField-listassa oleva formatoitava data.
     * @return Palauttaa listasta luodun merkkijonon.
     */
    public String formatSetData(ArrayList<TextField> fieldList) {
        String returnString = "";
        if (fieldList.size() == 1) {
            returnString += fieldList.get(0).getText() + ";";
            return returnString;
        }
        
        int idx = 0;
        while (idx < fieldList.size() - 1) {
            returnString += fieldList.get(idx).getText() + ",";
            idx++;
        }
        returnString += fieldList.get(idx).getText() + ";";
        
        return returnString;
    }
    /**
     * Lokin etsintä tietokantatiedostosta.
     * @param date Etsittävän lokin luomispäivämäärä.
     * @return Palauttaa apumetodin formatoiman merkkijonolistan.
     */
    public ArrayList<String> searchLog(Date date) {        
        Log log = logDao.searchLog(currentUser, date);
        return formatLogForUi(log);
    }
    
    /**
     * Apumetodi searchLogille, formatoi tietokantatiedostosta haetun Log-olion datan ui:ta varten.
     * @param log Tietokantatiedostosta haettu loki.
     * @return Palauttaa lokista saadun datan merkkijonolistana.
     */
    public ArrayList<String> formatLogForUi(Log log) {
        ArrayList<String> formatted = new ArrayList<>();
        
        String[] splitLog = log.getData().split(";");
        for (String exercise : splitLog) {
            String toList = "";
            String[] nameAndData = exercise.split(":");
            
            toList += nameAndData[0] + ": ";
            toList += nameAndData[1].replaceAll(",", ", ");
            formatted.add(toList);
        }
        
        return formatted;
    }
    
    /**
     * Uloskirjautuminen
     */
    public void logout() {
        this.currentUser = null;
    }
    
    /**
     * Käyttäjän sisäänkirjautuminen sovellukseen
     * @param username Sisäänkirjautuva käyttäjä
     */
    public void loginUser(String username) {
        this.currentUser = username;
    }
    
}
