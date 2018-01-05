package me.rainbow.service;

import me.rainbow.entity.Log;

import java.util.List;
import java.util.Map;

/**
 * @author guojinpeng
 * @date 17.12.29 15:51
 */
public interface LogService {
    List<Log> getAllLogs();

    void saveLog(Log log);

    void testAspectJ(Log log, Map<String, Object> map);

    Log getLog(Integer id);
}
