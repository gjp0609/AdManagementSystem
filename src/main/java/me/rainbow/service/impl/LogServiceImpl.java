package me.rainbow.service.impl;

import me.rainbow.dao.LogDao;
import me.rainbow.entity.Log;
import me.rainbow.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author guojinpeng
 * @date 17.12.29 15:53
 */
@Transactional
@Service("logService")
public class LogServiceImpl implements LogService {
    private final LogDao dao;

    @Autowired
    public LogServiceImpl(LogDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Log> getAllLogs() {
        return dao.selectAll();
    }

    @Override
    public void saveLog(Log log) {
    }

    @Override
    public Log getLog(Integer id) {
        return dao.selectById(id);
    }
}
