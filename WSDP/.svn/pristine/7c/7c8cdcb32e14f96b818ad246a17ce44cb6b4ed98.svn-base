package com.szdeepwise.bydtcmonitor.service;

import com.szdeepwise.bydtcmonitor.entity.BYDEntity;
import com.szdeepwise.bydtcmonitor.entity.JDBCRequestEntity;

import java.util.List;
import java.util.Map;

public interface BYDService {
    //tc
    List<JDBCRequestEntity> queryTcLinkInfo();
    int insertTcLinkInfo(JDBCRequestEntity jdbcRequestEntity);
    int systemHealthCheck(JDBCRequestEntity jdbcRequestEntity);
    int GetPeopleOnline(JDBCRequestEntity jdbcRequestEntity);
    void getOnlineToTcOnlineInfo(JDBCRequestEntity jdbcEntity);
    List<BYDEntity> queryTableTcOnlineInfo(BYDEntity bydEntity);

    //nagios
    Map queryStatusSummary();

    Map fuzzyQueryHostName(BYDEntity bydEntity);
    Map fuzzyQueryHostGroup(BYDEntity bydEntity);
    Map fuzzyQueryDocName(BYDEntity bydEntity);
    Map fuzzyQueryDocGroup(BYDEntity bydEntity);
    Map fuzzyQueryEmployeeGroup(BYDEntity bydEntity);
    Map fuzzyQueryEmployeeProject(BYDEntity bydEntity);
    Map fuzzyQueryEmployeeName(BYDEntity bydEntity);

    Map fuzzyQueryCompany(BYDEntity bydEntity);
    Map fuzzyQueryHostCompany(BYDEntity bydEntity);
    Map fuzzyQueryIP(BYDEntity bydEntity);

    Map fuzzyQueryDocCompany(BYDEntity bydEntity);
    Map fuzzyQueryDocOwner(BYDEntity bydEntity);

    Map allAbnormalStatistics(BYDEntity bydEntity);
    Map allViolationRanking(BYDEntity bydEntity);


    Map getBYDPLMLoginInfo(BYDEntity bydEntity);
    Map getDownloadDocInfo(BYDEntity bydEntity);
    Map getViewDocInfo(BYDEntity bydEntity);
    Map getAbnormalDeleteInfo(BYDEntity bydEntity);

    Map employeeNameTrendAnalysis(BYDEntity bydEntity);
    Map employeeGroupTrendAnalysis(BYDEntity bydEntity);
    Map employeeCompanyTrendAnalysis(BYDEntity bydEntity);

    Map getBYDPLMLoginZheXianInfo(BYDEntity bydEntity);
    Map getLoginCountZheXianInfo(BYDEntity bydEntity);
    Map getDownloadDocZheXianInfo(BYDEntity bydEntity);
    Map getViewDocZheXianInfo(BYDEntity bydEntity);

    Map fuzzyQueryWorkingHostName(BYDEntity bydEntity);
    Map queryKPITableInfo(BYDEntity bydEntity);
    Map queryEmployeeKPIZheXianInfo(BYDEntity bydEntity);

    Map queryEmployeeRankingAll(BYDEntity bydEntity);
    Map queryEmployeeKPIContribution(BYDEntity bydEntity);
    Map queryEmployeeKPIRanking(BYDEntity bydEntity);
    Map queryEmployeeBonusRanking(BYDEntity bydEntity);
    Map queryEmployeeMinusRanking(BYDEntity bydEntity);


    Map queryGroupRankingAll(BYDEntity bydEntity);
    Map queryGroupKPIContribution(BYDEntity bydEntity);
    Map queryGroupKPIRanking(BYDEntity bydEntity);
    Map queryGroupBonusRanking(BYDEntity bydEntity);
    Map queryGroupMinusRanking(BYDEntity bydEntity);


    Map queryCompanyRankingAll(BYDEntity bydEntity);
    Map queryCompanyKPIContribution(BYDEntity bydEntity);
    Map queryCompanyKPIRanking(BYDEntity bydEntity);
    Map queryCompanyBonusRanking(BYDEntity bydEntity);
    Map queryCompanyMinusRanking(BYDEntity bydEntity);


    //仪表盘
    Map getMapCrossCompanyInfo();
    Map getClientEventCount();
    Map getAllWorkingHours();
    Map getRecentEvents(BYDEntity bydEntity);
    Map getDepartmentalEvents(BYDEntity bydEntity);
    Map getDashboardCompanyInfo(BYDEntity bydEntity);

    Map queryOperaCounts(BYDEntity bydEntity);
}
