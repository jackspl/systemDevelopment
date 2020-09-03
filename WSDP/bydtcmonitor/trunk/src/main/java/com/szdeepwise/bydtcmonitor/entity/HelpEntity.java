package com.szdeepwise.bydtcmonitor.entity;

/**
 * @author xubowen
 * @description 帮助菜单实体类
 * @date 2020年 06月22日
 */
public class HelpEntity {
    private Integer id;
    private String component;//组件
    private String name;//对象名称
    private String description;//描述
    private String advice;//建议
    private String referenceLog;//参考日志
    private String yellowWarningThreshold;//黄色警告阈值
    private String redWarningThreshold;//红色警告阈值
    private String referenceLogPath;//参考日志路径
    private String ip;
    private String userName;//登录账号
    private String password;//登录密码



    public HelpEntity() {
    }

    public HelpEntity(String component, String name, String description, String advice,
                      String referenceLog, String yellowWarningThreshold, String redWarningThreshold,
                      String referenceLogPath,String ip,String userName,String password) {
        this.component = component;
        this.name = name;
        this.description = description;
        this.advice = advice;
        this.referenceLog = referenceLog;
        this.yellowWarningThreshold = yellowWarningThreshold;
        this.redWarningThreshold = redWarningThreshold;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getReferenceLog() {
        return referenceLog;
    }

    public void setReferenceLog(String referenceLog) {
        this.referenceLog = referenceLog;
    }

    public String getYellowWarningThreshold() {
        return yellowWarningThreshold;
    }

    public void setYellowWarningThreshold(String yellowWarningThreshold) {
        this.yellowWarningThreshold = yellowWarningThreshold;
    }

    public String getRedWarningThreshold() {
        return redWarningThreshold;
    }

    public void setRedWarningThreshold(String redWarningThreshold) {
        this.redWarningThreshold = redWarningThreshold;
    }

    public String getReferenceLogPath() {
        return referenceLogPath;
    }

    public void setReferenceLogPath(String referenceLogPath) {
        this.referenceLogPath = referenceLogPath;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "HelpEntity{" +
                "id=" + id +
                ", component='" + component + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", advice='" + advice + '\'' +
                ", referenceLog='" + referenceLog + '\'' +
                ", yellowWarningThreshold='" + yellowWarningThreshold + '\'' +
                ", redWarningThreshold='" + redWarningThreshold + '\'' +
                ", referenceLogPath='" + referenceLogPath + '\'' +
                ", ip='" + ip + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}


