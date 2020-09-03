package com.szdeepwise.bydtcmonitor.controller;

import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3DirectoryEntry;
import ch.ethz.ssh2.StreamGobbler;
import com.alibaba.druid.wall.Violation;
import com.jcraft.jsch.*;
import com.szdeepwise.bydtcmonitor.entity.BYDEntity;
import com.szdeepwise.bydtcmonitor.entity.HelpEntity;
import com.szdeepwise.bydtcmonitor.entity.JDBCRequestEntity;
import com.szdeepwise.bydtcmonitor.service.BYDService;
import com.szdeepwise.bydtcmonitor.service.ReadExcelService;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * @author xubowen
 * @description BYDController
 * @date 2019年 12月19日
 */
@Controller
public class BYDController {

    @Autowired
    private BYDService bydService;

    @Autowired
    private ReadExcelService readExcelService;

    @ResponseBody
    @RequestMapping(value = "byd/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/index")
    public String goToIndex() {
        return "index";
    }

    /*---------------------------------------操作Excel-start---------------------------------------*/
    //导入excel到数据库
    @ResponseBody
    @RequestMapping(value = "excel/operaExcel")
    public String operaExcel(@RequestBody HelpEntity helpEntity) {
        String referenceLogPath = helpEntity.getReferenceLogPath();
        System.out.println("######## operaExcel:" + referenceLogPath);
        readExcelService.operaExcel(referenceLogPath);
        return null;
    }

    //组件模糊查询
    @ResponseBody
    @RequestMapping(value = "excel/fuzzyQueryComponent")
    public String fuzzyQueryComponent(@RequestBody HelpEntity helpEntity) {
        System.out.println("######## fuzzyQueryComponent:" + helpEntity.toString());
        Map resultMap = readExcelService.fuzzyQueryComponent(helpEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //指标名称模糊查询
    @ResponseBody
    @RequestMapping(value = "excel/fuzzyQueryName")
    public String fuzzyQueryName(@RequestBody HelpEntity helpEntity) {
        System.out.println("######## fuzzyQueryName:" + helpEntity.toString());
        Map resultMap = readExcelService.fuzzyQueryName(helpEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //动态菜单栏,查询
    @ResponseBody
    @RequestMapping(value = "excel/getHelpData")
    public String getHelpData(@RequestBody HelpEntity helpEntity) {
        System.out.println("######## getHelpData:" + helpEntity.toString());
        String resultMap = readExcelService.getHelpData(helpEntity);
        return resultMap;
    }

    //新增help信息
    @ResponseBody
    @RequestMapping(value = "excel/insertHelpData")
    public String insertHelpData(@RequestBody HelpEntity helpEntity) {
        System.out.println("######## insertHelpData:" + helpEntity.toString());
        int resultFlag = readExcelService.insertHelpData(helpEntity);
        Map resultMap = new HashMap();
        resultMap.put("resultFlag", resultFlag);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //删除
    @ResponseBody
    @RequestMapping(value = "excel/deleteHelpData")
    public String deleteHelpData(HttpServletRequest request) {
        int id = Integer.valueOf(request.getParameter("id"));
        System.out.println("######## deleteHelpData:" + id);
        int resultFlag = readExcelService.deleteHelpData(id);
        Map resultMap = new HashMap();
        resultMap.put("resultFlag", resultFlag);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }
/*    @ResponseBody
    @RequestMapping(value = "excel/deleteHelpData")
    public String deleteHelpData(@RequestBody HelpEntity helpEntity) {
        System.out.println("######## deleteHelpData:"+helpEntity.getId());
        int resultFlag = readExcelService.deleteHelpData(helpEntity.getId());
        Map resultMap = new HashMap();
        resultMap.put("resultFlag",resultFlag);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }*/

    //更新
    @ResponseBody
    @RequestMapping(value = "excel/updateHelpData")
    public String updateHelpData(@RequestBody HelpEntity helpEntity) {
        System.out.println("######## updateHelpData:" + helpEntity.toString());
        int resultFlag = readExcelService.updateHelpData(helpEntity);
        Map resultMap = new HashMap();
        resultMap.put("resultFlag", resultFlag);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    /*---------------------------------------操作Excel-end---------------------------------------*/

    /*---------------------------------------tc-start---------------------------------------*/
    //连接tc数据库
    @ResponseBody
    @RequestMapping(value = "byd/getConnectTC", method = RequestMethod.POST)
    public String getConnectTC(@RequestBody JDBCRequestEntity jdbcEntity) {
        System.out.println("####### getConnectTC：" + jdbcEntity.toString());
        int onlinePeopleCount = 0;
        Map resultMap = new HashMap();

        //首先判断是否输入过连接信息
        List<JDBCRequestEntity> tcLinkInfoList = bydService.queryTcLinkInfo();
        if (tcLinkInfoList.size() == 0) {//未连接过
            //系统健康检查，判断用户输入连接信息是否正确
            int checkNum = bydService.systemHealthCheck(jdbcEntity);
            if (checkNum == 1) {
                int resultFlag = bydService.insertTcLinkInfo(jdbcEntity);
                System.out.println("============保存tc连接信息返回int：" + resultFlag);
                bydService.getOnlineToTcOnlineInfo(jdbcEntity);
                //onlinePeopleCount = bydService.GetPeopleOnline(jdbcEntity);
                System.out.println("=========getConnectTC.用户输入信息正确resultInfo:linkSuccess");
                resultMap.put("resultInfo", "linkSuccess");
            } else if (checkNum == 0) {
                System.out.println("=========getConnectTC.用户输入信息错误resultInfo:linkError");
                resultMap.put("resultInfo", "linkError");
            }
        } else {//连接过
            JDBCRequestEntity jdbcRequestEntity = tcLinkInfoList.get(0);
            //onlinePeopleCount = bydService.GetPeopleOnline(jdbcRequestEntity);
            resultMap.put("resultInfo", "linkSkip");
        }

        //resultMap.put("onlinePeopleCount", onlinePeopleCount);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //查询在线人数
    //查询tc_online_info表格
    @ResponseBody
    @RequestMapping(value = "byd/getOnlinePeopleCount", method = RequestMethod.POST)
    public String getOnlinePeopleCount() {
        System.out.println("####### getOnlinePeopleCount==");
        Map resultMap = new HashMap();
        List<JDBCRequestEntity> tcLinkInfoList = bydService.queryTcLinkInfo();
        if (tcLinkInfoList.size() > 0) {
            //查询tc在线人数
            JDBCRequestEntity jdbcRequestEntity = tcLinkInfoList.get(0);
            int onlinePeopleCount = bydService.GetPeopleOnline(jdbcRequestEntity);
            System.out.println("在线人数BYDController：" + onlinePeopleCount);
            resultMap.put("onlinePeopleCount", onlinePeopleCount);
        }
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //查询tc_online_info表格
    @ResponseBody
    @RequestMapping(value = "byd/queryTableTcOnlineInfo", method = RequestMethod.POST)
    public String queryTableTcOnlineInfo(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### queryTableTcOnlineInfo：" + bydEntity.toString());
        Map resultMap = new HashMap();

        //首先判断是否输入过连接信息
        List<JDBCRequestEntity> tcLinkInfoList = bydService.queryTcLinkInfo();
        if (tcLinkInfoList.size() == 0) {//未连接过
            System.out.println("=========resultInfo:error");
            resultMap.put("resultInfo", "error");
        } else {
            //查询tc_online_info
            List<BYDEntity> tcOnlineInfoList = bydService.queryTableTcOnlineInfo(bydEntity);
            resultMap.put("tcOnlineInfoList", tcOnlineInfoList);
        }

        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }
    /*---------------------------------------tc-end---------------------------------------*/

    /*---------------------------------------nagios-start---------------------------------------*/
    //查询host status summary、service status summary
    @ResponseBody
    @RequestMapping(value = "byd/queryStatusSummary", method = RequestMethod.POST)
    public String queryStatusSummary() {
        System.out.println("####### queryStatusSummary：");
        Map resultMap = bydService.queryStatusSummary();
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }
    /*---------------------------------------nagios-end---------------------------------------*/

    /***************************************模糊查询-start*****************************************/
    //模糊查询部门
    @ResponseBody
    @RequestMapping(value = "byd/fuzzyQueryEmployeeGroup", method = RequestMethod.POST)
    public String fuzzyQueryEmployeeGroup(@RequestBody BYDEntity bydEntity) {
        System.out.println("######## fuzzyQueryEmployeeGroup" + bydEntity.toString());
        Map resultMap = bydService.fuzzyQueryEmployeeGroup(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //模糊查询项目
    @ResponseBody
    @RequestMapping(value = "byd/fuzzyQueryEmployeeProject", method = RequestMethod.POST)
    public String fuzzyQueryEmployeeProject(@RequestBody BYDEntity bydEntity) {
        System.out.println("######## fuzzyQueryEmployeeProject" + bydEntity.toString());
        Map resultMap = bydService.fuzzyQueryEmployeeProject(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //模糊查询姓名
    @ResponseBody
    @RequestMapping(value = "byd/fuzzyQueryEmployeeName", method = RequestMethod.POST)
    public String fuzzyQueryEmployeeName(@RequestBody BYDEntity bydEntity) {
        System.out.println("######## fuzzyQueryEmployeeName" + bydEntity.toString());
        Map resultMap = bydService.fuzzyQueryEmployeeName(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //模糊查询主机名
    @ResponseBody
    @RequestMapping(value = "byd/fuzzyQueryHostName", method = RequestMethod.POST)
    public String fuzzyQueryHostName(@RequestBody BYDEntity bydEntity) {
        System.out.println("######## fuzzyQueryHostName" + bydEntity.toString());
        Map resultMap = bydService.fuzzyQueryHostName(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //模糊查询主机所在部门
    @ResponseBody
    @RequestMapping(value = "byd/fuzzyQueryHostGroup", method = RequestMethod.POST)
    public String fuzzyQueryHostGroup(@RequestBody BYDEntity bydEntity) {
        System.out.println("######## fuzzyQueryHostGroup" + bydEntity.toString());
        Map resultMap = bydService.fuzzyQueryHostGroup(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //模糊查询文件名
    @ResponseBody
    @RequestMapping(value = "byd/fuzzyQueryDocName", method = RequestMethod.POST)
    public String fuzzyQueryDocName(@RequestBody BYDEntity bydEntity) {
        System.out.println("######## fuzzyQueryDocName" + bydEntity.toString());
        Map resultMap = bydService.fuzzyQueryDocName(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //模糊查询公司
    @ResponseBody
    @RequestMapping(value = "byd/fuzzyQueryCompany", method = RequestMethod.POST)
    public String fuzzyQueryCompany(@RequestBody BYDEntity bydEntity) {
        System.out.println("######## fuzzyQueryCompany" + bydEntity.toString());
        Map resultMap = bydService.fuzzyQueryCompany(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //模糊查询主机所在公司
    @ResponseBody
    @RequestMapping(value = "byd/fuzzyQueryHostCompany", method = RequestMethod.POST)
    public String fuzzyQueryHostCompany(@RequestBody BYDEntity bydEntity) {
        System.out.println("######## fuzzyQueryHostCompany" + bydEntity.toString());
        Map resultMap = bydService.fuzzyQueryHostCompany(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //模糊查询ip
    @ResponseBody
    @RequestMapping(value = "byd/fuzzyQueryIP", method = RequestMethod.POST)
    public String fuzzyQueryIP(@RequestBody BYDEntity bydEntity) {
        System.out.println("######## fuzzyQueryIP" + bydEntity.toString());
        Map resultMap = bydService.fuzzyQueryIP(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //模糊查询文件所在公司
    @ResponseBody
    @RequestMapping(value = "byd/fuzzyQueryDocCompany", method = RequestMethod.POST)
    public String fuzzyQueryDocCompany(@RequestBody BYDEntity bydEntity) {
        System.out.println("######## fuzzyQueryDocCompany" + bydEntity.toString());
        Map resultMap = bydService.fuzzyQueryDocCompany(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //模糊查询文件所在部门
    @ResponseBody
    @RequestMapping(value = "byd/fuzzyQueryDocGroup", method = RequestMethod.POST)
    public String fuzzyQueryDocGroup(@RequestBody BYDEntity bydEntity) {
        System.out.println("######## fuzzyQueryDocGroup" + bydEntity.toString());
        Map resultMap = bydService.fuzzyQueryDocGroup(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //模糊查询文档所有者
    @ResponseBody
    @RequestMapping(value = "byd/fuzzyQueryDocOwner", method = RequestMethod.POST)
    public String fuzzyQueryDocOwner(@RequestBody BYDEntity bydEntity) {
        System.out.println("######## fuzzyQueryDocOwner" + bydEntity.toString());
        Map resultMap = bydService.fuzzyQueryDocOwner(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    /***************************************模糊查询-start*****************************************/

    /***************************************老功能，暂已删除-start*****************************************/
    //PLM账号登录，获取登录场景信息


    //文档下载，获取文档下载场景信息


    //文档查看，获取文档查看场景信息


    //PLM账号登录，折线图
    @ResponseBody
    @RequestMapping(value = "byd/getLoginCountZheXianInfo", method = RequestMethod.POST)
    public String getLoginCountZheXianInfo(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### getLoginCountZheXianInfo：" + bydEntity.toString());
        Map resultMap = bydService.getLoginCountZheXianInfo(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    @ResponseBody
    @RequestMapping(value = "byd/getBYDPLMLoginZheXianInfo", method = RequestMethod.POST)
    public String getBYDPLMLoginZheXianInfo(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### getBYDPLMLoginZheXianInfo：" + bydEntity.toString());
        Map resultMap = bydService.getBYDPLMLoginZheXianInfo(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }


    //文档下载，折线图
    @ResponseBody
    @RequestMapping(value = "byd/getDownloadDocZheXianInfo", method = RequestMethod.POST)
    public String getDownloadDocZheXianInfo(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### getDownloadDocZheXianInfo：" + bydEntity.toString());
        Map resultMap = bydService.getDownloadDocZheXianInfo(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //文档查看，折线图
    @ResponseBody
    @RequestMapping(value = "byd/getViewDocZheXianInfo", method = RequestMethod.POST)
    public String getViewDocZheXianInfo(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### getViewDocZheXianInfo：" + bydEntity.toString());
        Map resultMap = bydService.getViewDocZheXianInfo(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }
    /***************************************老功能，暂已删除-end*****************************************/

    /*****************************************KPI功能-start*****************************************/
    //模糊查询工时hostname
    @ResponseBody
    @RequestMapping(value = "byd/fuzzyQueryWorkingHostName", method = RequestMethod.POST)
    public String fuzzyQueryWorkingHostName(@RequestBody BYDEntity bydEntity) {
        System.out.println("######## fuzzyQueryWorkingHostName" + bydEntity.toString());
        Map resultMap = bydService.fuzzyQueryWorkingHostName(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //KPI表格
    @ResponseBody
    @RequestMapping(value = "byd/queryKPITableInfo", method = RequestMethod.POST)
    public String queryKPITableInfo(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### queryKPITableInfo:" + bydEntity.toString());
        Map resultMap = bydService.queryKPITableInfo(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //员工KPI折线图
    @ResponseBody
    @RequestMapping(value = "byd/queryEmployeeKPIZheXianInfo", method = RequestMethod.POST)
    public String queryEmployeeKPIZheXianInfo(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### queryEmployeeKPIZheXianInfo:" + bydEntity.toString());
        Map resultMap = bydService.queryEmployeeKPIZheXianInfo(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //KPI排名
    //员工KPI排名，总查询
    @ResponseBody
    @RequestMapping(value = "byd/queryEmployeeRankingAll", method = RequestMethod.POST)
    public String queryEmployeeRankingAll(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### queryEmployeeRankingAll:" + bydEntity.toString());
        Map resultMap = bydService.queryEmployeeRankingAll(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //1.员工KPI贡献
    @ResponseBody
    @RequestMapping(value = "byd/queryEmployeeKPIContribution", method = RequestMethod.POST)
    public String queryEmployeeKPIContribution(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### queryEmployeeKPIContribution:" + bydEntity.toString());
        Map resultMap = bydService.queryEmployeeKPIContribution(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //2.员工KPI排名
    @ResponseBody
    @RequestMapping(value = "byd/queryEmployeeKPIRanking", method = RequestMethod.POST)
    public String queryEmployeeKPIRanking(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### queryEmployeeKPIRanking:" + bydEntity.toString());
        Map resultMap = bydService.queryEmployeeKPIRanking(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //3.员工KPI,加分排行
    @ResponseBody
    @RequestMapping(value = "byd/queryEmployeeBonusRanking", method = RequestMethod.POST)
    public String queryEmployeeBonusRanking(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### queryEmployeeBonusRanking:" + bydEntity.toString());
        Map resultMap = bydService.queryEmployeeBonusRanking(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //4.员工KPI，减分排行
    @ResponseBody
    @RequestMapping(value = "byd/queryEmployeeMinusRanking", method = RequestMethod.POST)
    public String queryEmployeeMinusRanking(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### queryEmployeeMinusRanking:" + bydEntity.toString());
        Map resultMap = bydService.queryEmployeeMinusRanking(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //部门KPI排名，总查询
    @ResponseBody
    @RequestMapping(value = "byd/queryGroupRankingAll", method = RequestMethod.POST)
    public String queryGroupRankingAll(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### queryGroupRankingAll:" + bydEntity.toString());
        Map resultMap = bydService.queryGroupRankingAll(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //1.部门KPI贡献
    @ResponseBody
    @RequestMapping(value = "byd/queryGroupKPIContribution", method = RequestMethod.POST)
    public String queryGroupKPIContribution(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### queryGroupKPIContribution:" + bydEntity.toString());
        Map resultMap = bydService.queryGroupKPIContribution(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //2.部门KPI排名
    @ResponseBody
    @RequestMapping(value = "byd/queryGroupKPIRanking", method = RequestMethod.POST)
    public String queryGroupKPIRanking(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### queryGroupKPIRanking:" + bydEntity.toString());
        Map resultMap = bydService.queryGroupKPIRanking(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //3.部门KPI,加分排行
    @ResponseBody
    @RequestMapping(value = "byd/queryGroupBonusRanking", method = RequestMethod.POST)
    public String queryGroupBonusRanking(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### queryGroupBonusRanking:" + bydEntity.toString());
        Map resultMap = bydService.queryGroupBonusRanking(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //4.部门KPI,减分排行
    @ResponseBody
    @RequestMapping(value = "byd/queryGroupMinusRanking", method = RequestMethod.POST)
    public String queryGroupMinusRanking(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### queryGroupMinusRanking:" + bydEntity.toString());
        Map resultMap = bydService.queryGroupMinusRanking(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //公司KPI排名，总查询
    @ResponseBody
    @RequestMapping(value = "byd/queryCompanyRankingAll", method = RequestMethod.POST)
    public String queryCompanyRankingAll(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### queryCompanyRankingAll:" + bydEntity.toString());
        Map resultMap = bydService.queryCompanyRankingAll(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //1.公司KPI贡献
    @ResponseBody
    @RequestMapping(value = "byd/queryCompanyKPIContribution", method = RequestMethod.POST)
    public String queryCompanyKPIContribution(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### queryCompanyKPIContribution:" + bydEntity.toString());
        Map resultMap = bydService.queryCompanyKPIContribution(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //2.公司KPI排名
    @ResponseBody
    @RequestMapping(value = "byd/queryCompanyKPIRanking", method = RequestMethod.POST)
    public String queryCompanyKPIRanking(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### queryCompanyKPIRanking:" + bydEntity.toString());
        Map resultMap = bydService.queryCompanyKPIRanking(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //3.公司KPI,加分排行
    @ResponseBody
    @RequestMapping(value = "byd/queryCompanyBonusRanking", method = RequestMethod.POST)
    public String queryCompanyBonusRanking(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### queryCompanyBonusRanking:" + bydEntity.toString());
        Map resultMap = bydService.queryCompanyBonusRanking(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //4.公司KPI,减分排行
    @ResponseBody
    @RequestMapping(value = "byd/queryCompanyMinusRanking", method = RequestMethod.POST)
    public String queryCompanyMinusRanking(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### queryCompanyMinusRanking:" + bydEntity.toString());
        Map resultMap = bydService.queryCompanyMinusRanking(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }
    /*****************************************KPI功能-end*****************************************/


    /*****************************************信息安全功能-start*****************************************/
    //总览，四种异常饼状图+柱状图
    @ResponseBody
    @RequestMapping(value = "byd/allAbnormalStatistics", method = RequestMethod.POST)
    public String allAbnormalStatistics(@RequestBody BYDEntity bydEntity, HttpServletRequest request) {
        System.out.println("####### allAbnormalStatistics：" + bydEntity.toString());
        Map resultMap = bydService.allAbnormalStatistics(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //总览，公司、部门、个人违规排名
    @ResponseBody
    @RequestMapping(value = "byd/allViolationRanking", method = RequestMethod.POST)
    public String allViolationRanking(@RequestBody BYDEntity bydEntity, HttpServletRequest request) {
        System.out.println("####### allViolationRanking：" + bydEntity.toString());
        Map resultMap = bydService.allViolationRanking(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //趋势分析，个人趋势，总查询
    @ResponseBody
    @RequestMapping(value = "byd/employeeNameTrendAnalysis", method = RequestMethod.POST)
    public String employeeNameTrendAnalysis(@RequestBody BYDEntity bydEntity, HttpServletRequest request) {
        System.out.println("####### employeeNameTrendAnalysis：" + bydEntity.toString());
        Map resultMap = bydService.employeeNameTrendAnalysis(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //趋势分析，部门趋势，总查询
    @ResponseBody
    @RequestMapping(value = "byd/employeeGroupTrendAnalysis", method = RequestMethod.POST)
    public String employeeGroupTrendAnalysis(@RequestBody BYDEntity bydEntity, HttpServletRequest request) {
        System.out.println("####### employeeGroupTrendAnalysis：" + bydEntity.toString());
        Map resultMap = bydService.employeeGroupTrendAnalysis(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //趋势分析，公司趋势，总查询
    @ResponseBody
    @RequestMapping(value = "byd/employeeCompanyTrendAnalysis", method = RequestMethod.POST)
    public String employeeCompanyTrendAnalysis(@RequestBody BYDEntity bydEntity, HttpServletRequest request) {
        System.out.println("####### employeeCompanyTrendAnalysis：" + bydEntity.toString());
        Map resultMap = bydService.employeeCompanyTrendAnalysis(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //异常登录
    @ResponseBody
    @RequestMapping(value = "byd/getAbnormalLoginInfo", method = RequestMethod.POST)
    public String getBYDPLMLoginInfo(@RequestBody BYDEntity bydEntity, HttpServletRequest request) {
        System.out.println("####### getBYDPLMLoginInfo：" + bydEntity.toString());
        Map resultMap = bydService.getBYDPLMLoginInfo(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //异常查看
    @ResponseBody
    @RequestMapping(value = "byd/getAbnormalViewInfo", method = RequestMethod.POST)
    public String getViewDocInfo(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### getViewDocInfo：" + bydEntity.toString());
        Map resultMap = bydService.getViewDocInfo(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //异常下载
    @ResponseBody
    @RequestMapping(value = "byd/getAbnormalDownloadInfo", method = RequestMethod.POST)
    public String getDownloadDocInfo(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### getDocDownloadInfo：" + bydEntity.toString());
        Map resultMap = bydService.getDownloadDocInfo(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //异常删除
    @ResponseBody
    @RequestMapping(value = "byd/getAbnormalDeleteInfo", method = RequestMethod.POST)
    public String getAbnormalDeleteInfo(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### getAbnormalDeleteInfo：" + bydEntity.toString());
        Map resultMap = bydService.getAbnormalDeleteInfo(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }
    /***************************************信息安全功能-end******************************************/


    /*****************************************仪表盘功能-start*****************************************/
    //当天客户端总事件和异常事件
    @ResponseBody
    @RequestMapping(value = "byd/getClientEventCount", method = RequestMethod.POST)
    public String getClientEventCount() {
        System.out.println("####### getClientEventCount");
        Map resultMap = bydService.getClientEventCount();
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //地图跨公司访问
    @ResponseBody
    @RequestMapping(value = "byd/getMapCrossCompanyInfo", method = RequestMethod.POST)
    public String getMapCrossCompanyInfo() {
        System.out.println("####### getMapCrossCompanyInfo");
        Map resultMap = bydService.getMapCrossCompanyInfo();
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //查询总工时+总KPI+安全事件
    @ResponseBody
    @RequestMapping(value = "byd/getAllHoursAndKPI", method = RequestMethod.POST)
    public String getAllWorkingHours() {
        System.out.println("####### getAllWorkingHours");
        Map resultMap = bydService.getAllWorkingHours();
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //查询服务器、客户端当日操作频次、异常操作频次
    @ResponseBody
    @RequestMapping(value = "byd/queryOperaCounts", method = RequestMethod.POST)
    public String queryOperaCounts(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### queryOperaCounts"+bydEntity.toString());
        Map resultMap = bydService.queryOperaCounts(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //查询最近事件
    @ResponseBody
    @RequestMapping(value = "byd/getRecentEvents", method = RequestMethod.POST)
    public String getRecentEvents(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### getRecentEvents："+bydEntity);
        Map resultMap = bydService.getRecentEvents(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //查询部门事件数量
    @ResponseBody
    @RequestMapping(value = "byd/getDepartmentalEvents", method = RequestMethod.POST)
    public String getDepartmentalEvents(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### getDepartmentalEvents："+bydEntity);
        Map resultMap = bydService.getDepartmentalEvents(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //查询仪表盘公司信息
    @ResponseBody
    @RequestMapping(value = "byd/getDashboardCompanyInfo", method = RequestMethod.POST)
    public String getDashboardCompanyInfo(@RequestBody BYDEntity bydEntity) {
        System.out.println("####### getDashboardCompanyInfo："+bydEntity);
        Map resultMap = bydService.getDashboardCompanyInfo(bydEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }


    /*****************************************仪表盘功能-end*****************************************/
    /*////////////////////////////////////////////////////////////////////////////*/

}


