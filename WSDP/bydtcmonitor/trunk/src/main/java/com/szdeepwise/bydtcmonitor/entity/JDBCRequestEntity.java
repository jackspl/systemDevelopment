package com.szdeepwise.bydtcmonitor.entity;

/**
 * @author xubowen
 * @description
 * @date 2019年 12月23日
 */
public class JDBCRequestEntity {
    private String url;
    private String serviceName;
    private String port;
    private String userName;
    private String passWord;
    private String selectDB;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSelectDB() {
        return selectDB;
    }

    public void setSelectDB(String selectDB) {
        this.selectDB = selectDB;
    }

    @Override
    public String toString() {
        return "JDBCEntity{" +
                "url='" + url + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", port='" + port + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", selectDB='" + selectDB + '\'' +
                '}';
    }
}


