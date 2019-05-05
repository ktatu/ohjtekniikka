/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import traininglog.dao.LogDao;

/**
 *
 * @author ktatu
 */
public class FakeLogDao implements LogDao {
    
    ArrayList<Log> testLogs = new ArrayList<>();
    
    public FakeLogDao() {
        this.testLogs = new ArrayList<>();
        this.testLogs.add(new Log (Date.valueOf(LocalDate.now()), "searchLogUser", "firstEx:3x40,4x50,5x60;secondEx:10x100,15x110,20x120;"));
    }

    @Override
    public boolean createLog(Log log) {
        if (searchLog(log.getUsername(), log.getDate()) == null) {
            testLogs.add(log);
            return true;
        }
        return false;
    }

    @Override
    public Log searchLog(String username, Date date) {
        for (Log log : testLogs) {
            if (log.getUsername().equals(username) && log.getDate().equals(date)) {
                return log;
            }
        }
        return null;
    }
    
}
