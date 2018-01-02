package me.rainbow.dao;

import me.rainbow.entity.Log;

import java.util.List;

/**
 * @author guojinpeng
 * @date 17.12.29 15:54
 */
public interface LogDao {
    List<Log> selectAll();

    Log selectById(Integer id);
}
