/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.scene.control.TextField;

/**
 * Ui:sta saadun käyttäjäsyötteen validoinnista vastaava luokka
 */
public class Validation {
    
    /**
     * Tarkistaa parametrina saadun merkkijonon
     * @param input Tarkistettava merkkijono
     * @return Kaikki kunnossa: tyhjä merkkijono. Parametrina saatu merkkijono tyhjä: "No input". Merkkijono liian pitkä: "Input too long: max. characters 30"
     */
    public String validateString(String input) {
        if (input.trim().length() == 0) {
            return "No input";
        } else if (input.length() > 30) {
            return "Input too long: max. characters 30";
        }
        return "";
    }
    
    /**
     * Tarkistaa parametrina saadun merkkijono-muodossa olevan luvun
     * @param input Tarkistettava luku
     * @return Viestin metodilta validateString jos merkkijono tyhjä tai liian pitkä.
     * "Positive integer required" jos parametrina kokonaisluku joka on alle 1.
     * "Input not an integer" jos syöte ei ollut kokonaisluku.
     * Palauttaa tyhjän merkkijonon jos syötteessä ei ollut vikaa.
     */
    public String validateInteger(String input) {
        if (!(validateString(input).equals(""))) {
            return validateString(input);
        }
        try {
            int number = Integer.valueOf(input);
            if (number < 1) {
                return "Positive integer required";
            }
            return "";
        } catch (NumberFormatException e) {
            return "Input not an integer";
        }
    }
    
    /**
     * Validoi käyttäjätunnuksen luomisen yhteydessä annetun syötteen
     * @param username  Käyttäjänimi
     * @param password  Salasana
     * @return Palauttaa validateStringin luoman viestin mikäli käyttäjänimessä tai salasanassa jotain vikaa. Muuten tyhjä merkkijono
     */
    public String validateCreateUserInput(String username, String password) {
        if (!(validateString(username).equals(""))) {
            return validateString(username);
        } else if (!(validateString(password).equals(""))) {
            return validateString(password);
        }
        return "";
    }
    
    /**
     * Validoi suoraan LogScreenistä saadun syötteen, kutsu tapahtuu "Add"-napinpainalluksen yhteydessä
     * @param name Käyttäjän antama harjoitteen nimi
     * @param sets Sarjojen määrä
     * @return Palauttaa validateStringin viestin jos syötteessä name vikaa ja validateIntegerin viestin jos syötteessä sets.
     * "No more than 10 sets per workout" jos sets on yli 10, tyhjä merkkijono jos syötteissä ei ongelmia.
     */
    public String validateExerciseInput(String name, String sets) {
        if (!(validateString(name).equals(""))) {
            return validateString(name);
        } else if (!(validateInteger(sets).equals(""))) {
            return validateInteger(sets);
        } else if (Integer.valueOf(sets) > 10) {
            return "No more than 10 sets per workout";
        }
        return "";
    }
    
    /**
     * Validoi uutta lokia varten annetun syötteen.
     * @param exerciseNames Harjoitteiden nimet merkkijonolistana
     * @param setData   Lista, joka sisältää kuhunkin harjoitteeseen liittyvän syötteen listana.
     * @return Palauttaa validateStringin viestin jos jonkin harjoitteen nimessä on vikaa.
     * Viesti "First set of every exercise must be filled" jos jonkin setDatan listan 1. TextField on tyhjä.
     * Viesti "Please provide set input in form 'sets x repetitions' (ex. 5x10)" jos jonkin TextFieldin merkkijono yli 7 merkkiä.
     * Palauttaa tyhjän merkkijonon jos kaikki kunnossa.
     */
    public String validateLogInput(ArrayList<String> exerciseNames, ArrayList<ArrayList<TextField>> setData) {
        for (String exercise : exerciseNames) {
            String validation = validateString(exercise);
            if (!(validation.equals(""))) {
                return validation;
            }
        }
        for (int i = 0; i < setData.size(); i++) {
            ArrayList<TextField> data = setData.get(i);
            if (data.get(0).getText().trim().equals("")) {
                return "First set of every exercise must be filled";
            }
            for (TextField field : data) {
                if (field.getText().length() > 7) {
                    return "Please provide set input in form 'sets x repetitions' (ex. 5x10)";
                }
            }
        }
        return "";
    }
    
    /**
     * Varmistaa, että syötteenä annettu päivämäärä on menneisyydessä
     * @param date Käyttäjän syöttämä päivämäärä
     * @return "Please select a past date" jos käyttäjä syötti tulevan päivämäärän, muutoin palauttaa tyhjän merkkijonon.
     */
    public String validateDate(Date date) {
        if (date.compareTo(Date.valueOf(LocalDate.now())) > 0) {
            return "Please select a past date";
        }
        return "";
    } 
}

