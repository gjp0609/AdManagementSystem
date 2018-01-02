package me.rainbow.entity;

import java.util.Date;

/**
 * @author guojinpeng
 * @date 17.12.29 15:50
 */
public class Log {
    private Integer id;
    private String ip;
    private String addr;
    private String operation;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", addr='" + addr + '\'' +
                ", operation='" + operation + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
