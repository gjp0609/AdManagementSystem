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
    private String opertation;
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

    public String getOpertation() {
        return opertation;
    }

    public void setOpertation(String opertation) {
        this.opertation = opertation;
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
                ", opertation='" + opertation + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
