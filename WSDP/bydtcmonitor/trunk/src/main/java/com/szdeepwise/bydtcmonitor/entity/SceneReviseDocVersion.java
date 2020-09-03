package com.szdeepwise.bydtcmonitor.entity;

import java.util.Date;

public class SceneReviseDocVersion {
    private Integer id;

    private String employeeName;

    private String employeeId;

    private String employeeGroup;

    private String employeeRole;

    private String employeeProject;

    private String company;

    private Integer operationTime;

    private Date actionBeginTime;

    private Date actionEndTime;

    private String hostName;

    private String ip;

    private String userId;

    private String clientServerTtl;

    private String clientServerDelay;

    private String name;

    private String itemId;

    private String versionOld;

    private String versionNew;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName == null ? null : employeeName.trim();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId == null ? null : employeeId.trim();
    }

    public String getEmployeeGroup() {
        return employeeGroup;
    }

    public void setEmployeeGroup(String employeeGroup) {
        this.employeeGroup = employeeGroup == null ? null : employeeGroup.trim();
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole == null ? null : employeeRole.trim();
    }

    public String getEmployeeProject() {
        return employeeProject;
    }

    public void setEmployeeProject(String employeeProject) {
        this.employeeProject = employeeProject == null ? null : employeeProject.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public Integer getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Integer operationTime) {
        this.operationTime = operationTime;
    }

    public Date getActionBeginTime() {
        return actionBeginTime;
    }

    public void setActionBeginTime(Date actionBeginTime) {
        this.actionBeginTime = actionBeginTime;
    }

    public Date getActionEndTime() {
        return actionEndTime;
    }

    public void setActionEndTime(Date actionEndTime) {
        this.actionEndTime = actionEndTime;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName == null ? null : hostName.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getClientServerTtl() {
        return clientServerTtl;
    }

    public void setClientServerTtl(String clientServerTtl) {
        this.clientServerTtl = clientServerTtl == null ? null : clientServerTtl.trim();
    }

    public String getClientServerDelay() {
        return clientServerDelay;
    }

    public void setClientServerDelay(String clientServerDelay) {
        this.clientServerDelay = clientServerDelay == null ? null : clientServerDelay.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getVersionOld() {
        return versionOld;
    }

    public void setVersionOld(String versionOld) {
        this.versionOld = versionOld == null ? null : versionOld.trim();
    }

    public String getVersionNew() {
        return versionNew;
    }

    public void setVersionNew(String versionNew) {
        this.versionNew = versionNew == null ? null : versionNew.trim();
    }
}