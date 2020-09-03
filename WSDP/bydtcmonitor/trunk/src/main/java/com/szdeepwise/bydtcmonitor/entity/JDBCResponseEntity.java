package com.szdeepwise.bydtcmonitor.entity;

/**
 * @author xubowen
 * @description
 * @date 2019年 12月23日
 */
public class JDBCResponseEntity {
    private Integer counts;

    private String pUid;
    private String pUserName;
    private String pNodeName;
    private String pLoginDate;

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public String getpUid() {
        return pUid;
    }

    public void setpUid(String pUid) {
        this.pUid = pUid;
    }

    public String getpUserName() {
        return pUserName;
    }

    public void setpUserName(String pUserName) {
        this.pUserName = pUserName;
    }

    public String getpNodeName() {
        return pNodeName;
    }

    public void setpNodeName(String pNodeName) {
        this.pNodeName = pNodeName;
    }

    public String getpLoginDate() {
        return pLoginDate;
    }

    public void setpLoginDate(String pLoginDate) {
        this.pLoginDate = pLoginDate;
    }

    @Override
    public String toString() {
        return "JDBCResponseEntity{" +
                "counts=" + counts +
                ", pUid='" + pUid + '\'' +
                ", pUserName='" + pUserName + '\'' +
                ", pNodeName='" + pNodeName + '\'' +
                ", pLoginDate='" + pLoginDate + '\'' +
                '}';
    }
}


