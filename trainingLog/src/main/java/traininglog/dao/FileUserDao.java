/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.dao;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import traininglog.domain.User;

public class FileUserDao implements UserDao {

    @Override
    public boolean create(String newUser) {
        String[] split = newUser.split(" ");
        String username = split[0];
        String password = split[1];
        if (search(username)) {
            return false;
        } else {
            try {
                FileWriter writer = new FileWriter("users.txt", true);
                writer.append(username + "," + password);
                writer.append(System.getProperty("line.separator"));
                writer.close();
                return true;
            } catch (IOException ex) {
                return false;
            }
        }
    }

    @Override
    public boolean search(String username) {
        try {
            List<String> lines = Files.lines(Paths.get("users.txt")).collect(Collectors.toList());
            for (String line : lines) {
                String[] split = line.split(",");
                if (split[0].equals(username)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }


    
}
