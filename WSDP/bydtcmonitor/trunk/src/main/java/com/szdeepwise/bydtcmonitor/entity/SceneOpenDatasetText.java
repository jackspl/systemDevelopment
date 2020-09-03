package com.szdeepwise.bydtcmonitor.entity;

import java.util.Date;

public class SceneOpenDatasetText {
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

    private String itemId;

    private String version;

    private String datasetName;

    private String datasetType;

    private String ownerCompany;

    private String ownerGroup;

    private String ownerUser;

    private String crossDepartmentView;

    private String crossCompanyView;

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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getDatasetName() {
        return datasetName;
    }

    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName == null ? null : datasetName.trim();
    }

    public String getDatasetType() {
        return datasetType;
    }

    public void setDatasetType(String datasetType) {
        this.datasetType = datasetType == null ? null : datasetType.trim();
    }

    public String getOwnerCompany() {
        return ownerCompany;
    }

    public void setOwnerCompany(String ownerCompany) {
        this.ownerCompany = ownerCompany == null ? null : ownerCompany.trim();
    }

    public String getOwnerGroup() {
        return ownerGroup;
    }

    public void setOwnerGroup(String ownerGroup) {
        this.ownerGroup = ownerGroup == null ? null : ownerGroup.trim();
    }

    public String getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(String ownerUser) {
        this.ownerUser = ownerUser == null ? null : ownerUser.trim();
    }

    public String getCrossDepartmentView() {
        return crossDepartmentView;
    }

    public void setCrossDepartmentView(String crossDepartmentView) {
        this.crossDepartmentView = crossDepartmentView == null ? null : crossDepartmentView.trim();
    }

    public String getCrossCompanyView() {
        return crossCompanyView;
    }

    public void setCrossCompanyView(String crossCompanyView) {
        this.crossCompanyView = crossCompanyView == null ? null : crossCompanyView.trim();
    }
}