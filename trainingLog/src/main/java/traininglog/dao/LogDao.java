/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traininglog.dao;

import java.sql.Date;
import traininglog.domain.Log;

/**
 *
 * @author ktatu
 */
public interface LogDao {
    boolean createLog(Log log) throws Exception;
    Log searchLog(String username, Date date);
}
