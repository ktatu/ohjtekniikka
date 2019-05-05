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
import traininglog.domain.Log;

/**
 *  Lokeihin liittyvistä toiminnoista vastaava luokka. Käsittelee config.properties tiedostossa määriteltyä tietokantatiedostoa.
 */
public class SQLLogDao implements LogDao {
    
    private String database;
    
    public SQLLogDao(String database) {
        this.database = database;
    }
    
    
    /**
     * Tallettaa uuden lokin tietokantatiedostoon.
     * @param log Talletettava loki.
     * @return True jos tallettaminen onnistuu, muutoin false.
     */
    @Override
    public boolean createLog(Log log) {
        if (searchLog(log.getUsername(), log.getDate()).getData() == null) {
            try {
                Connection conn = DriverManager.getConnection("jdbc:h2:./" + database, "sa", "");
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO Log (creationDate, username, data) VALUES (?, ?, ?)");
                stmt.setDate(1, log.getDate());
                stmt.setString(2, log.getUsername());
                stmt.setString(3, log.getData());

                stmt.executeUpdate();
                stmt.close();
                conn.close();
                return true;
            } catch (SQLException ex) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Etsii lokia tietokantatiedostosta.
     * @param username Lokin luoneen käyttäjän käyttäjänimi.
     * @param creationDate Luomispäivämäärä.
     * @return Palauttaa löytyneen lokin tai null.
     */
    @Override
    public Log searchLog(String username, Date creationDate) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:./" + database, "sa", "");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Log WHERE username = ? AND creationDate = ?");
            stmt.setString(1, username);
            stmt.setDate(2, creationDate);
            ResultSet rs = stmt.executeQuery();
            Log log = new Log();
            if (rs.next()) {
                log.setCreationDate(rs.getDate("creationDate"));
                log.setUsername(rs.getString("username"));
                log.setData(rs.getString("data"));
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
            return log;
        } catch (SQLException ex) {
            return null;
        }
    }  
}
