package me.rainbow.entity;

import java.util.Date;

/**
 * @author guojinpeng
 * @date 17.12.29 15:50
 */
public class Log {
    private Integer id;
    private String ip;
    private String uri;
    private String args;
    private String method;
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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
                ", uri='" + uri + '\'' +
                ", method='" + method + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
