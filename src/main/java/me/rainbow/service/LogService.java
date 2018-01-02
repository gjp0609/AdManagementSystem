package me.rainbow.service;

import me.rainbow.entity.Log;

import java.util.List;

/**
 * @author guojinpeng
 * @date 17.12.29 15:51
 */
public interface LogService {
    List<Log> getAllLogs();

    void saveLog(Log log);

    Log getLog(Integer id);
}
