package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.*;

import java.util.List;
import java.util.Map;

public interface BYDMapper {
    /*---------------------------------------操作Excel-start---------------------------------------*/
    //excel导入数据库
    int insertExcel(HelpEntity helpEntity);

    int insertComponent(String component);

    List<String> fuzzyQueryComponent(HelpEntity helpEntitys);

    List<String> fuzzyQueryName(HelpEntity helpEntitys);

    //查询所有component
    List<String> queryAllComponent();

    //查询component
    String queryComponent(HelpEntity helpEntitys);

    //查询t_help表中component是否存在
    List<String> tHelpQueryComponent(String component);

    //查询component
    List<String> queryExcelComponent(HelpEntity helpEntity);

    //根据component查询help相关信息
    List<HelpEntity> queryHelpInfo(HelpEntity helpEntity);

    //新增前查询t_component
    List<String> tComponentQuery(HelpEntity helpEntitys);

    //删除
    int deleteHelpData(int id);

    /*删除t_component中不存在的component*/
    int tComponentDelete(String component);

    //更新
    int updateHelpData(HelpEntity helpEntity);

    String queryLogByServiceName(String name);


    /*---------------------------------------操作Excel-end---------------------------------------*/

    /*tc-start*/
    //检查是否输入过连接信息
    List<JDBCRequestEntity> queryTcLinkInfo();

    //保存tc连接信息
    int insertTcLinkInfo(JDBCRequestEntity jdbcRequestEntity);

    //获取tc库中在线用户信息，插入tc_online_info
    int insertTcOnlineInfo(JDBCResponseEntity jdbcResponseEntity);

    //插入前：查询tc_online_info
    List<JDBCResponseEntity> queryTcOnlineInfoBefore(JDBCResponseEntity jdbcResponseEntity);

    //插入后：查询tc_online_info
    List<JDBCResponseEntity> queryTcOnlineInfoAfter();

    List<BYDEntity> getTcOnlineZheXianInfoByHour(BYDEntity bydEntity);
    List<BYDEntity> getTcOnlineZheXianInfoByWeek(BYDEntity bydEntity);
    List<BYDEntity> getTcOnlineZheXianInfoByMonth(BYDEntity bydEntity);
    /*tc-end*/

    //模糊查询，用户部门
    List<String> fuzzyQueryEmployeeGroup(BYDEntity bydEntity);

    //模糊查询，项目
    List<String> fuzzyQueryEmployeeProject(BYDEntity bydEntity);

    //模糊查询，姓名
    List<String> fuzzyQueryEmployeeName(BYDEntity bydEntity);

    //模糊查询，主机名
    List<String> fuzzyQueryHostName(BYDEntity bydEntity);

    //模糊查询，主机所在部门
    List<String> fuzzyQueryHostGroup(BYDEntity bydEntity);

    //模糊查询，文件名
    List<String> fuzzyQueryDocName(BYDEntity bydEntity);

    //模糊查询，公司
    List<String> fuzzyQueryCompany(BYDEntity bydEntity);

    //模糊查询，主机所在公司
    List<String> fuzzyQueryHostCompany(BYDEntity bydEntity);

    //模糊查询，ip
    List<String> fuzzyQueryIP(BYDEntity bydEntity);

    //模糊查询，文件所在部门
    List<String> fuzzyQueryDocGroup(BYDEntity bydEntity);

    //模糊查询，文件所在公司
    List<String> fuzzyQueryDocCompany(BYDEntity bydEntity);

    //模糊查询，文件所有者
    List<String> fuzzyQueryDocOwner(BYDEntity bydEntity);

    //根据hostName后三位判断部门
    List<String> getHostNameGroup(String hostNameSub);

    //判断文档下载是否过量下载
    Integer judgeOverloadDownload(Map map);


    //PLM账号登录，获取登录信息
    List<BYDEntity> getBYDPLMLoginInfo(BYDEntity bydEntity);

    //文档下载，获取文档下载场景信息
    List<BYDEntity>  getDownloadDocInfo(BYDEntity bydEntity);

    //文档查看，获取文档查看场景信息
    List<BYDEntity>  getViewDocInfo(BYDEntity bydEntity);

    //删除
    List<BYDEntity> getAbnormalDeleteInfo(BYDEntity bydEntity);

    //信息安全模块，趋势分析，个人趋势，饼状图
    List<BYDEntity> employeeNamePieChart(BYDEntity bydEntity);
    List<BYDEntity> employeeGroupPieChart(BYDEntity bydEntity);
    List<BYDEntity> employeeCompanyPieChart(BYDEntity bydEntity);

    int queryNameZheXianLike(BYDEntity bydEntity);
    int queryGroupZhuZhuangLike(BYDEntity bydEntity);
    int queryCompanyZhuZhuangLike(BYDEntity bydEntity);

    List<BYDEntity> queryAllPieChart(BYDEntity bydEntity);
    int queryAbnormalZheXianLike(BYDEntity bydEntity);

    List<String> getGroupByName(BYDEntity bydEntity);

    List<String> getInfoSafeEmployeeName(BYDEntity bydEntity);
    List<String> getInfoSafeEmployeeGroup(BYDEntity bydEntity);





    //获取登录记录的折线图信息，按天
    List<BYDEntity> getBYDPLMLoginZheXianInfoByHour(BYDEntity bydEntity);

    //获取登录记录的折线图信息，按星期
    List<BYDEntity> getBYDPLMLoginZheXianInfoByWeek(BYDEntity bydEntity);

    //获取登录记录的折线图信息，按月
    List<BYDEntity> getBYDPLMLoginZheXianInfoByMonth(BYDEntity bydEntity);

    //获取文档下载的折线图信息，按天
    List<BYDEntity> getDownloadDocZheXianInfoByHour(BYDEntity bydEntity);

    //获取文档下载的折线图信息，按星期
    List<BYDEntity> getDownloadDocZheXianInfoByWeek(BYDEntity bydEntity);

    //获取文档下载的折线图信息，按月
    List<BYDEntity> getDownloadDocZheXianInfoByMonth(BYDEntity bydEntity);

    //获取文档查看的折线图信息，按天
    List<BYDEntity> getViewDocZheXianInfoByHour(BYDEntity bydEntity);

    ////获取文档查看的折线图信息，按周
    List<BYDEntity> getViewDocZheXianInfoByWeek(BYDEntity bydEntity);

    //获取文档查看的折线图信息，按月
    List<BYDEntity> getViewDocZheXianInfoByMonth(BYDEntity bydEntity);

    /*KPI功能*/
    //模糊查询workingHours的hostName
    List<String> fuzzyQueryWorkingHostName(BYDEntity bydEntity);

    //首先获取数据库所有姓名+公司+部门
    List<BYDEntity> queryComGroupName(BYDEntity bydEntity);

    //根据employee_name查询工时
    //Integer queryOperationTime(BYDEntity bydEntity);

    //根据employee_name查询场景次数
    int queryOperaCount(BYDEntity bydEntity);

    //根据hostname查询工时
    double queryWorkingTime(BYDEntity bydEntity);

    //KPI折线图，根据折线图X轴日期模糊查询工时
    double queryWorkingTimeLike(BYDEntity bydEntity);

    int queryOperaCountLike(BYDEntity bydEntity);

    //KPI折线图，根据employee_name和zheXianX 模糊查询
    List<BYDEntity> queryComGroupNameLike(BYDEntity bydEntity);

    /*仪表盘功能*/
    //查询当天客户端总事件和异常事件
    int getClientAllEventCount(String sceneTableName);
    int getAbnormalCountLogin();
    int getAbnormalCountViewDoc();
    int getAbnormalCountDownDoc();

    //Map地图展示跨公司
    List<BYDEntity> getMapCrossCompanyInfo();

    //查询昨天总工时
    double getAllWorkingHoursYesterday(String yesterdayDate);
    //查询当天总工时
    double getAllWorkingHoursToday();

    //查询最近事件
    List<BYDEntity> getRecentEvents(BYDEntity bydEntity);

    //查询部门事件数量
    List<BYDEntity> getDepartmentalEvents(BYDEntity bydEntity);

    //查询所有公司
    List<String> getAllCompany(BYDEntity bydEntity);

    List<BYDEntity> getCompanyZheXianByDay(BYDEntity bydEntity);
    List<BYDEntity> getCompanyZheXianByWeek(BYDEntity bydEntity);
    List<BYDEntity> getCompanyZheXianByMonth(BYDEntity bydEntity);
    int getCompanyNormalInfo(BYDEntity bydEntity);

    List<BYDEntity> getLoginCountZheXianByDay(BYDEntity bydEntity);
    List<BYDEntity> getLoginCountZheXianByWeek(BYDEntity bydEntity);
    List<BYDEntity> getLoginCountZheXianByMonth(BYDEntity bydEntity);

    int getNormalCounts(BYDEntity bydEntity);

    int queryClientOperaCounts(BYDEntity bydEntity);

}
