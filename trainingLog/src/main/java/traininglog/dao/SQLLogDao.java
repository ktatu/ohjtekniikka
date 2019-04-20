/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import traininglog.domain.Log;
/**
 *
 * @author ktatu
 */
public class SQLLogDao implements LogDao {
    
    @Override
    public boolean createLog(Log log) {
        
        if (searchLog(log.getUsername(), log.getDate()) == null) {
            try {
                Connection conn = DriverManager.getConnection("jdbc:h2:./traininglog", "sa", "");

                PreparedStatement stmt = conn.prepareStatement("INSERT INTO Log (creationDate, username, data) VALUES (?, ?, ?)");
                stmt.setDate(1, log.getDate());
                stmt.setString(2, log.getUsername());
                stmt.setString(3, log.getData());

                stmt.executeUpdate();
                stmt.close();
                conn.close();
                return true;
            } catch (SQLException ex) {
                System.out.println("ex createLog");
                System.out.println(ex);
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public Log searchLog(String username, Date creationDate) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:./traininglog", "sa", "");
            
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Log WHERE username = ? AND creationDate = ?");
            stmt.setString(1, username);
            stmt.setDate(2, creationDate);
            
            ResultSet rs = stmt.executeQuery();
            
            Log log = new Log();
            if (rs.next()) {
                System.out.println("oli rs.next");
                log.setCreationDate(rs.getDate("creationDate"));
                log.setUsername(rs.getString("username"));
                log.setData(rs.getString("data"));
            }
            
            stmt.close();
            conn.close();
            
            if (log.getUsername() == null) {
                return null;
            }
            return log;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    


    
}
