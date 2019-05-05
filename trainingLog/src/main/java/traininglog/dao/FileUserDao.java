/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import traininglog.domain.User;

/**
 * Käyttäjiin suoraan liittyvistä toiminnoista vastaava luokka. Käsittelee config.properties-tiedostossa määriteltyä tekstitiedostoa.
 */
public class FileUserDao implements UserDao {
    
    private String file;
    
    public FileUserDao(String file) {
        this.file = file;
    }

    /**
     * Luo uuden käyttäjän.
     * @param newUser Käyttäjänimi ja salasana yhdistettynä.
     * @return True jos uusi käyttäjä luotiin, muutoin false.
     */
    @Override
    public boolean create(String newUser) {
        String[] split = newUser.split(" ");
        String username = split[0];
        String password = split[1];
        if (searchUsername(username)) {
            return false;
        } else {
            try {
                FileWriter writer = new FileWriter(file, true);
                writer.append(username + "," + password);
                writer.append(System.getProperty("line.separator"));
                writer.close();
                return true;
            } catch (IOException ex) {
                return false;
            }
        }
    }

    /**
     * Apumetodi createlle, tarkistaa onko käyttäjänimi jo käytössä.
     * @param username Uuden käyttäjätunnuksen käyttäjänimi.
     * @return True jos käyttäjänimi löytyi tekstitiedostosta, muutoin false.
     */
    @Override
    public boolean searchUsername(String username) {
        try {
            List<String> lines = Files.lines(Paths.get(file)).collect(Collectors.toList());
            for (String line : lines) {
                String[] split = line.split(",");
                if (split[0].equals(username)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Etsii kirjautuvan käyttäjän antamaa nimeä ja salasanaa tekstitiedostosta.
     * @param user User-olio kirjautuvasta käyttäjästä.
     * @return True jos nimi käyttäjänimi ja salasana löytyvät, muutoin false.
     */
    @Override
    public boolean searchUser(User user) {
        try {
            List<String> lines = Files.lines(Paths.get(file)).collect(Collectors.toList());
            for (String line : lines) {
                String[] split = line.split(",");
                if (split[0].equals(user.getUsername()) && split[1].equals(user.getPassword())) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
