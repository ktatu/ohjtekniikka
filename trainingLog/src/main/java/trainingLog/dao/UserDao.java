/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainingLog.dao;

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
import trainingLog.domain.User;

public class UserDao implements Dao<User, Integer> {

    @Override
    public boolean create(String username, String password) {
        if (search(username)) {
            return false;
        } else {
            try {
                FileWriter writer = new FileWriter("users.txt", true);
                writer.append(username+","+password);
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
        System.out.println(username);
        try {
            List<String> lines = Files.lines(Paths.get("users.txt")).collect(Collectors.toList());
            for (String line : lines) {
                String[] split = line.split(",");
                System.out.println(split[0]);
                System.out.println(split[1]);
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
