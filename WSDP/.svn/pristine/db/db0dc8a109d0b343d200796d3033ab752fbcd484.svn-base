package com.szdeepwise.bydtcmonitor.entity;

import java.util.Arrays;
import java.util.List;

/**
 * @author xubowen
 * @description BYDEntity
 * @date 2019年 12月19日
 */
public class BYDEntity {
    private Integer id;
    private String employeeName;//员工姓名
    private String employeeId;//员工工号
    private String employeeGroup;//员工所属部门
    private String employeeRole;//员工的角色（职务）
    private String employeeProject;//员工所属的项目
    private String company;//公司
    private double operationTime;
    private double workingHours;//工时
    private int operaCount;//场景次数
    private String actionBeginTime;
    private String actionEndTime;
    private String hostName;
    private String hostCompany;
    private String hostNameGroup;//主机所在部门
    private String IP;
    private String userId;
    private String client_server_TTL;
    private String client_server_delay;
    private String loginStatus;//登录情况：正常登录/跨部门登录

    private int abnormalCount;

    /*加分*/
    private int outputDocument;//产出文档
    private int documentReference;//文件引用
    private int projectWeighting;//项目加权
    /*减分*/
    private int InfoSafeViolation;//信息安全违规
    private int irregularOperation;//不规范操作

    private int kpiContributionFlag;//KPI贡献flag，1：净贡献，2：加分贡献，3：减分贡献
    private double KPIjiafen;
    private double KPIjianfen;
    private double KPI;
    //用于计算环比
    private double KPICal;
    //KPI环比
    private String KPILinkRatio;
    //KPI加分环比
    private String KPILinkRatioJIAFEN;
    //KPI减分环比
    private String KPILinkRatioJIANFEN;

    private String docName;//文档名
    private String docGroup;//文档所在部门
    private String docCompany;//文档所在公司
    private String docOwner;//文档所有者

    //仪表盘 Map 返回参数 ownerCompany
    private String ownerCompany;

    //登录异常记录flag
    private int exceptionLoginFlag;//查询登录异常记录，选中flag=1

    //文档下载
    private String overloadDownload;//是否超量下载
    private String crossDepartmentDownload;//是否跨部门下载
    private String secretFileDownload;//是否机密文件
    private String outOfSizeDownload;//是否大小超标

    //文档下载异常记录flag
    private int overloadDownloadFlag;//文档下载异常记录，超量下载=1
    private int crossDepartmentDownloadFlag;//文档下载异常记录，跨部门下载=1
    private int secretFileDownloadFlag;//文档下载异常记录，机密文件=1
    private int outOfSizeDownloadFlag;//文档下载异常记录，大小超标=1

    //文档查看
    private String crossDepartmentView;//是否跨部门下载
    private String secretFileView;//是否机密文件

    //文档查看异常记录flag
    private int crossDepartmentViewFlag;//跨部门下载=1
    private int secretFileViewFlag;//机密文件下载=1

    //删除
    private String crossDepartmentDelete;//是否跨部门删除
    private String crossOwnerDelete;//是否跨属主删除

    //删除flag
    private int crossDepartmentDeleteFlag;//跨部门删除=1
    private int crossOwnerDeleteFlag;//跨属主删除=1

    //打开数据集 跨部门
    //private String crossDepartmentView;
    //打开数据集 跨公司
    private String crossCompanyView;

    //折线图平均KPI flag
    private int avgKPIFlag;//当请求flag=1时表示平均kpi

    private int paiXuFlag;

    //折线图:请求数据，一天=1、一周=2、一个月=3
    private int zheXianFlag;
    private String zheXianDate;

    //折线图：返回数据，X轴：时间、Y轴：人数
    private String zheXianX;
    private Integer zheXianY;

    private String tableName;//动态查询表名
    private int pagesNum;//当前页码
    private int pagesSize;//每页显示条数

    //仪表盘最近事件显示字段
    private String recentInfo;

    //下拉框多选传到后台字段
    private String[] selectField;
    //下拉框多选模糊查询拼接sql
    private String splicingSql;

    //信息安全，总览，异常排名
    private int abnormalLogin;
    private int abnormalView;
    private int abnormalDownload;
    private int abnormalDelete;

    public BYDEntity() {
    }

    public BYDEntity(String employeeName, String company, double KPI) {
        this.employeeName = employeeName;
        this.company = company;
        this.KPI = KPI;
    }

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
        this.employeeName = employeeName;
    }

    public int getOperaCount() {
        return operaCount;
    }

    public void setOperaCount(int operaCount) {
        this.operaCount = operaCount;
    }

    public String getCrossCompanyView() {
        return crossCompanyView;
    }

    public void setCrossCompanyView(String crossCompanyView) {
        this.crossCompanyView = crossCompanyView;
    }

    public String getRecentInfo() {
        return recentInfo;
    }

    public void setRecentInfo(String recentInfo) {
        this.recentInfo = recentInfo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(double workingHours) {
        this.workingHours = workingHours;
    }

    public int getOutputDocument() {
        return outputDocument;
    }

    public void setOutputDocument(int outputDocument) {
        this.outputDocument = outputDocument;
    }

    public int getDocumentReference() {
        return documentReference;
    }

    public void setDocumentReference(int documentReference) {
        this.documentReference = documentReference;
    }

    public int getProjectWeighting() {
        return projectWeighting;
    }

    public void setProjectWeighting(int projectWeighting) {
        this.projectWeighting = projectWeighting;
    }

    public int getInfoSafeViolation() {
        return InfoSafeViolation;
    }

    public void setInfoSafeViolation(int infoSafeViolation) {
        InfoSafeViolation = infoSafeViolation;
    }

    public int getIrregularOperation() {
        return irregularOperation;
    }

    public void setIrregularOperation(int irregularOperation) {
        this.irregularOperation = irregularOperation;
    }

    public double getKPI() {
        return KPI;
    }

    public void setKPI(double KPI) {
        this.KPI = KPI;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeGroup() {
        return employeeGroup;
    }

    public void setEmployeeGroup(String employeeGroup) {
        this.employeeGroup = employeeGroup;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getEmployeeProject() {
        return employeeProject;
    }

    public void setEmployeeProject(String employeeProject) {
        this.employeeProject = employeeProject;
    }

    public double getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(double operationTime) {
        this.operationTime = operationTime;
    }

    public String getActionBeginTime() {
        return actionBeginTime;
    }

    public void setActionBeginTime(String actionBeginTime) {
        this.actionBeginTime = actionBeginTime;
    }

    public String getActionEndTime() {
        return actionEndTime;
    }

    public void setActionEndTime(String actionEndTime) {
        this.actionEndTime = actionEndTime;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostNameGroup() {
        return hostNameGroup;
    }

    public void setHostNameGroup(String hostNameGroup) {
        this.hostNameGroup = hostNameGroup;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClient_server_TTL() {
        return client_server_TTL;
    }

    public void setClient_server_TTL(String client_server_TTL) {
        this.client_server_TTL = client_server_TTL;
    }

    public String getClient_server_delay() {
        return client_server_delay;
    }

    public void setClient_server_delay(String client_server_delay) {
        this.client_server_delay = client_server_delay;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocGroup() {
        return docGroup;
    }

    public void setDocGroup(String docGroup) {
        this.docGroup = docGroup;
    }

    public String getOverloadDownload() {
        return overloadDownload;
    }

    public void setOverloadDownload(String overloadDownload) {
        this.overloadDownload = overloadDownload;
    }

    public String getCrossDepartmentDownload() {
        return crossDepartmentDownload;
    }

    public void setCrossDepartmentDownload(String crossDepartmentDownload) {
        this.crossDepartmentDownload = crossDepartmentDownload;
    }

    public String getSecretFileDownload() {
        return secretFileDownload;
    }

    public void setSecretFileDownload(String secretFileDownload) {
        this.secretFileDownload = secretFileDownload;
    }

    public int getExceptionLoginFlag() {
        return exceptionLoginFlag;
    }

    public void setExceptionLoginFlag(int exceptionLoginFlag) {
        this.exceptionLoginFlag = exceptionLoginFlag;
    }

    public int getPagesNum() {
        return pagesNum;
    }

    public void setPagesNum(int pagesNum) {
        this.pagesNum = pagesNum;
    }

    public int getPagesSize() {
        return pagesSize;
    }

    public void setPagesSize(int pagesSize) {
        this.pagesSize = pagesSize;
    }

    public String getCrossDepartmentView() {
        return crossDepartmentView;
    }

    public void setCrossDepartmentView(String crossDepartmentView) {
        this.crossDepartmentView = crossDepartmentView;
    }

    public String getSecretFileView() {
        return secretFileView;
    }

    public void setSecretFileView(String secretFileView) {
        this.secretFileView = secretFileView;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getZheXianFlag() {
        return zheXianFlag;
    }

    public void setZheXianFlag(int zheXianFlag) {
        this.zheXianFlag = zheXianFlag;
    }

    public String getZheXianX() {
        return zheXianX;
    }

    public void setZheXianX(String zheXianX) {
        this.zheXianX = zheXianX;
    }

    public Integer getZheXianY() {
        return zheXianY;
    }

    public void setZheXianY(Integer zheXianY) {
        this.zheXianY = zheXianY;
    }

    public String getZheXianDate() {
        return zheXianDate;
    }

    public void setZheXianDate(String zheXianDate) {
        this.zheXianDate = zheXianDate;
    }

    public int getOverloadDownloadFlag() {
        return overloadDownloadFlag;
    }

    public void setOverloadDownloadFlag(int overloadDownloadFlag) {
        this.overloadDownloadFlag = overloadDownloadFlag;
    }

    public int getCrossDepartmentDownloadFlag() {
        return crossDepartmentDownloadFlag;
    }

    public void setCrossDepartmentDownloadFlag(int crossDepartmentDownloadFlag) {
        this.crossDepartmentDownloadFlag = crossDepartmentDownloadFlag;
    }

    public int getSecretFileDownloadFlag() {
        return secretFileDownloadFlag;
    }

    public void setSecretFileDownloadFlag(int secretFileDownloadFlag) {
        this.secretFileDownloadFlag = secretFileDownloadFlag;
    }

    public int getCrossDepartmentViewFlag() {
        return crossDepartmentViewFlag;
    }

    public void setCrossDepartmentViewFlag(int crossDepartmentViewFlag) {
        this.crossDepartmentViewFlag = crossDepartmentViewFlag;
    }

    public int getSecretFileViewFlag() {
        return secretFileViewFlag;
    }

    public void setSecretFileViewFlag(int secretFileViewFlag) {
        this.secretFileViewFlag = secretFileViewFlag;
    }

    public int getPaiXuFlag() {
        return paiXuFlag;
    }

    public void setPaiXuFlag(int paiXuFlag) {
        this.paiXuFlag = paiXuFlag;
    }

    public int getAvgKPIFlag() {
        return avgKPIFlag;
    }

    public void setAvgKPIFlag(int avgKPIFlag) {
        this.avgKPIFlag = avgKPIFlag;
    }

    public String getOwnerCompany() {
        return ownerCompany;
    }

    public void setOwnerCompany(String ownerCompany) {
        this.ownerCompany = ownerCompany;
    }

    public double getKPICal() {
        return KPICal;
    }

    public void setKPICal(double KPICal) {
        this.KPICal = KPICal;
    }

    public String getKPILinkRatio() {
        return KPILinkRatio;
    }

    public void setKPILinkRatio(String KPILinkRatio) {
        this.KPILinkRatio = KPILinkRatio;
    }

    public int getKpiContributionFlag() {
        return kpiContributionFlag;
    }

    public void setKpiContributionFlag(int kpiContributionFlag) {
        this.kpiContributionFlag = kpiContributionFlag;
    }

    public double getKPIjiafen() {
        return KPIjiafen;
    }

    public void setKPIjiafen(double KPIjiafen) {
        this.KPIjiafen = KPIjiafen;
    }

    public double getKPIjianfen() {
        return KPIjianfen;
    }

    public void setKPIjianfen(double KPIjianfen) {
        this.KPIjianfen = KPIjianfen;
    }

    public String getKPILinkRatioJIAFEN() {
        return KPILinkRatioJIAFEN;
    }

    public void setKPILinkRatioJIAFEN(String KPILinkRatioJIAFEN) {
        this.KPILinkRatioJIAFEN = KPILinkRatioJIAFEN;
    }

    public String getKPILinkRatioJIANFEN() {
        return KPILinkRatioJIANFEN;
    }

    public void setKPILinkRatioJIANFEN(String KPILinkRatioJIANFEN) {
        this.KPILinkRatioJIANFEN = KPILinkRatioJIANFEN;
    }

    public String getSplicingSql() {
        return splicingSql;
    }

    public void setSplicingSql(String splicingSql) {
        this.splicingSql = splicingSql;
    }

    public String[] getSelectField() {
        return selectField;
    }

    public void setSelectField(String[] selectField) {
        this.selectField = selectField;
    }

    public String getHostCompany() {
        return hostCompany;
    }

    public void setHostCompany(String hostCompany) {
        this.hostCompany = hostCompany;
    }

    public String getDocCompany() {
        return docCompany;
    }

    public void setDocCompany(String docCompany) {
        this.docCompany = docCompany;
    }

    public String getDocOwner() {
        return docOwner;
    }

    public void setDocOwner(String docOwner) {
        this.docOwner = docOwner;
    }

    public int getOutOfSizeDownloadFlag() {
        return outOfSizeDownloadFlag;
    }

    public void setOutOfSizeDownloadFlag(int outOfSizeDownloadFlag) {
        this.outOfSizeDownloadFlag = outOfSizeDownloadFlag;
    }

    public String getOutOfSizeDownload() {
        return outOfSizeDownload;
    }

    public void setOutOfSizeDownload(String outOfSizeDownload) {
        this.outOfSizeDownload = outOfSizeDownload;
    }

    public String getCrossDepartmentDelete() {
        return crossDepartmentDelete;
    }

    public void setCrossDepartmentDelete(String crossDepartmentDelete) {
        this.crossDepartmentDelete = crossDepartmentDelete;
    }

    public String getCrossOwnerDelete() {
        return crossOwnerDelete;
    }

    public void setCrossOwnerDelete(String crossOwnerDelete) {
        this.crossOwnerDelete = crossOwnerDelete;
    }

    public int getCrossDepartmentDeleteFlag() {
        return crossDepartmentDeleteFlag;
    }

    public void setCrossDepartmentDeleteFlag(int crossDepartmentDeleteFlag) {
        this.crossDepartmentDeleteFlag = crossDepartmentDeleteFlag;
    }

    public int getCrossOwnerDeleteFlag() {
        return crossOwnerDeleteFlag;
    }

    public void setCrossOwnerDeleteFlag(int crossOwnerDeleteFlag) {
        this.crossOwnerDeleteFlag = crossOwnerDeleteFlag;
    }

    public int getAbnormalLogin() {
        return abnormalLogin;
    }

    public void setAbnormalLogin(int abnormalLogin) {
        this.abnormalLogin = abnormalLogin;
    }

    public int getAbnormalView() {
        return abnormalView;
    }

    public void setAbnormalView(int abnormalView) {
        this.abnormalView = abnormalView;
    }

    public int getAbnormalDownload() {
        return abnormalDownload;
    }

    public void setAbnormalDownload(int abnormalDownload) {
        this.abnormalDownload = abnormalDownload;
    }

    public int getAbnormalDelete() {
        return abnormalDelete;
    }

    public void setAbnormalDelete(int abnormalDelete) {
        this.abnormalDelete = abnormalDelete;
    }

    public int getAbnormalCount() {
        return abnormalCount;
    }

    public void setAbnormalCount(int abnormalCount) {
        this.abnormalCount = abnormalCount;
    }

    @Override
    public String toString() {
        return "BYDEntity{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", employeeGroup='" + employeeGroup + '\'' +
                ", employeeRole='" + employeeRole + '\'' +
                ", employeeProject='" + employeeProject + '\'' +
                ", company='" + company + '\'' +
                ", operationTime=" + operationTime +
                ", workingHours=" + workingHours +
                ", operaCount=" + operaCount +
                ", actionBeginTime='" + actionBeginTime + '\'' +
                ", actionEndTime='" + actionEndTime + '\'' +
                ", hostName='" + hostName + '\'' +
                ", hostCompany='" + hostCompany + '\'' +
                ", hostNameGroup='" + hostNameGroup + '\'' +
                ", IP='" + IP + '\'' +
                ", userId='" + userId + '\'' +
                ", client_server_TTL='" + client_server_TTL + '\'' +
                ", client_server_delay='" + client_server_delay + '\'' +
                ", loginStatus='" + loginStatus + '\'' +
                ", abnormalCount=" + abnormalCount +
                ", outputDocument=" + outputDocument +
                ", documentReference=" + documentReference +
                ", projectWeighting=" + projectWeighting +
                ", InfoSafeViolation=" + InfoSafeViolation +
                ", irregularOperation=" + irregularOperation +
                ", kpiContributionFlag=" + kpiContributionFlag +
                ", KPIjiafen=" + KPIjiafen +
                ", KPIjianfen=" + KPIjianfen +
                ", KPI=" + KPI +
                ", KPICal=" + KPICal +
                ", KPILinkRatio='" + KPILinkRatio + '\'' +
                ", KPILinkRatioJIAFEN='" + KPILinkRatioJIAFEN + '\'' +
                ", KPILinkRatioJIANFEN='" + KPILinkRatioJIANFEN + '\'' +
                ", docName='" + docName + '\'' +
                ", docGroup='" + docGroup + '\'' +
                ", docCompany='" + docCompany + '\'' +
                ", docOwner='" + docOwner + '\'' +
                ", ownerCompany='" + ownerCompany + '\'' +
                ", exceptionLoginFlag=" + exceptionLoginFlag +
                ", overloadDownload='" + overloadDownload + '\'' +
                ", crossDepartmentDownload='" + crossDepartmentDownload + '\'' +
                ", secretFileDownload='" + secretFileDownload + '\'' +
                ", outOfSizeDownload='" + outOfSizeDownload + '\'' +
                ", overloadDownloadFlag=" + overloadDownloadFlag +
                ", crossDepartmentDownloadFlag=" + crossDepartmentDownloadFlag +
                ", secretFileDownloadFlag=" + secretFileDownloadFlag +
                ", outOfSizeDownloadFlag=" + outOfSizeDownloadFlag +
                ", crossDepartmentView='" + crossDepartmentView + '\'' +
                ", secretFileView='" + secretFileView + '\'' +
                ", crossDepartmentViewFlag=" + crossDepartmentViewFlag +
                ", secretFileViewFlag=" + secretFileViewFlag +
                ", crossDepartmentDelete='" + crossDepartmentDelete + '\'' +
                ", crossOwnerDelete='" + crossOwnerDelete + '\'' +
                ", crossDepartmentDeleteFlag=" + crossDepartmentDeleteFlag +
                ", crossOwnerDeleteFlag=" + crossOwnerDeleteFlag +
                ", crossCompanyView='" + crossCompanyView + '\'' +
                ", avgKPIFlag=" + avgKPIFlag +
                ", paiXuFlag=" + paiXuFlag +
                ", zheXianFlag=" + zheXianFlag +
                ", zheXianDate='" + zheXianDate + '\'' +
                ", zheXianX='" + zheXianX + '\'' +
                ", zheXianY=" + zheXianY +
                ", tableName='" + tableName + '\'' +
                ", pagesNum=" + pagesNum +
                ", pagesSize=" + pagesSize +
                ", recentInfo='" + recentInfo + '\'' +
                ", selectField=" + Arrays.toString(selectField) +
                ", splicingSql='" + splicingSql + '\'' +
                ", abnormalLogin=" + abnormalLogin +
                ", abnormalView=" + abnormalView +
                ", abnormalDownload=" + abnormalDownload +
                ", abnormalDelete=" + abnormalDelete +
                '}';
    }
}


