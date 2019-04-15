/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.domain;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author ktatu
 */
public class Log {
    
    private Date creationDate;
    private String username;
    private String data;
    
    public Log(String username, String data) {
        this.username = username;
        this.data = data;
        this.creationDate = Date.valueOf(LocalDate.now());
    }
    
    public Log(Date creationDate, String username, String data) {
        this.creationDate = creationDate;
        this.username = username;
        this.data = data;
    }

    public Date getDate() {
        return creationDate;
    }

    public String getUsername() {
        return username;
    }

    public String getData() {
        return data;
    }
}