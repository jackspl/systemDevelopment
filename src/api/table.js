import request from '../utils/request.js'

// 频次统计模块接口
export function getCountList(data) {
  return request({
    url: '/base/frequencyMonitoring',
    method: 'post',
    data
  })
}

// 性能统计模块接口
export function getList(data) {
  return request({
    url: '/base/operationPerformanceAnalysis',
    method: 'post',
    data
  })
}

// 模糊查询接口
export function getPulldownList(data) {
  return request({
    url: '/base/fuzzyQuery',
    method: 'post',
    data
  })
}

// 详情模块接口
export function infoRefr(data) {
  return request({
    url: '/base/getOperationDetail',
    method: 'post',
    data
  })
}

// 用户id模糊查询接口
export function getPulldownListUserid(data) {
  return request({
    url: '/base/fuzzyQueryEmployeeId',
    method: 'post',
    data
  })
}

// 用户名模糊查询接口
export function getPulldownListUserName(data) {
  return request({
    url: '/base/fuzzyQueryUserId',
    method: 'post',
    data
  })
}

// 主机名模糊查询接口
export function getPulldownListHostName(data) {
  return request({
    url: '/base/fuzzyQueryHostName',
    method: 'post',
    data
  })
}

// 场景名模糊查询接口
export function getPulldownListScene(data) {
  return request({
    url: '/base/fuzzyQueryScene',
    method: 'post',
    data
  })
}

// ------------------------------------------------------------------
// 比亚迪接口
// PLM账号登录表格接口
export function getPLMTable(data) {
  return request({
    url: '/byd/getBYDPLMLoginInfo',
    method: 'post',
    data
  })
}

// PLM账号登录折线图接口
export function getPLMLogin(data) {
  return request({
    url: '/byd/getBYDPLMLoginZheXianInfo',
    method: 'post',
    data
  })
}

// 在线人数接口
export function getCountNums(data) {
  return request({
    url: '/byd/getOnlinePeopleCount',
    method: 'post',
    data
  })
}

// 文档查看表格接口
export function getViewList(data) {
  return request({
    url: '/byd/getViewDocInfo',
    method: 'post',
    data
  })
}

// 文档查看折线图接口
export function getViewZheXian(data) {
  return request({
    url: '/byd/getViewDocZheXianInfo',
    method: 'post',
    data
  })
}

// 文档下载表格接口
export function getDownList(data) {
  return request({
    url: '/byd/getDownloadDocInfo',
    method: 'post',
    data
  })
}

// 文档下载折线图接口
export function getDownZheXian(data) {
  return request({
    url: '/byd/getDownloadDocZheXianInfo',
    method: 'post',
    data
  })
}

// 所有项目模糊查询接口
export function querySearchJect(data) {
  return request({
    url: '/byd/fuzzyQueryEmployeeProject',
    method: 'post',
    data
  })
}

// 所有姓名模糊查询接口
export function querySearchName(data) {
  return request({
    url: '/byd/fuzzyQueryEmployeeName',
    method: 'post',
    data
  })
}

// 所有主机名模糊查询接口
export function querySearchHostName(data) {
  return request({
    url: '/byd/fuzzyQueryHostName',
    method: 'post',
    data
  })
}

// 所有主机所在部门模糊查询接口
export function querySearchHostNameGroup(data) {
  return request({
    url: '/byd/fuzzyQueryHostGroup',
    method: 'post',
    data
  })
}

// 所有文件名模糊查询接口
export function querySearchDocName(data) {
  return request({
    url: '/byd/fuzzyQueryDocName',
    method: 'post',
    data
  })
}

// 所有文件所在部门模糊查询接口
export function querySearchDocGroup(data) {
  return request({
    url: '/byd/fuzzyQueryDocGroup',
    method: 'post',
    data
  })
}

// --------------------------------------------------------------


// tc数据库模块接口
export function tcDB(data) {
  return request({
    url: '/byd/getConnectTC',
    method: 'post',
    // xhrFields: {
    //   withCredentials: true
    // },
    // crossDomain: true,
    data
  })
}

// -----------------------------------------------------
// 在线用户
// export function onlineUser (data) {
//   return request({
//     url: '/byd/getConnectTC',
//     method: 'post',
//     xhrFields: {
//       withCredentials: true
//     },
//     crossDomain: true,
//     data
//   })
// }

// 1.	当天客户端总事件
export function getDayClienCount(data) {
  return request({
    url: '/byd/getClientEventCount',
    method: 'post',
    data
  })
}
// 9. 客户端当日操作频次+异常操作频次
export function getUserDaySums(data) {
  return request({
    url: '/byd/queryOperaCounts',
    method: 'post',
    data: {}
  })
}
// 2.	Map地图跨公司访问
export function mapAcrossCompany(data) {
  return request({
    url: '/byd/getMapCrossCompanyInfo',
    method: 'post',
    data
  })
}

// 3.	总工时+总KPI
export function sumWorking(data) {
  return request({
    url: '/byd/getAllHoursAndKPI',
    method: 'post',
    data
  })
}
// 4. TC数据库接口
// 账号并发量折线图接口
export function queryTable(data) {
  return request({
    url: '/byd/queryTableTcOnlineInfo',
    method: 'post',
    data
  })
}
// 5. 登录用户数量
export function loginUserNums(data) {
  return request({
    url: '/byd/getLoginCountZheXianInfo',
    method: 'post',
    data
  })
}
// 6. 最近事件模块
export function recentlyInterface(data) {
  return request({
    url: '/byd/getRecentEvents',
    method: 'post',
    data
  })
}
// 7. 部门事件模块
export function getDepNums(data) {
  return request({
    url: '/byd/getDepartmentalEvents',
    method: 'post',
    data
  })
}
// 8. 公司轮播图
export function getSumComs(data) {
  return request({
    url: '/byd/getDashboardCompanyInfo',
    method: 'post',
    data
  })
}

// -----------------------------------------------------
// 公司模糊查询接口
export function querySearchCompany(data) {
  return request({
    url: '/byd/fuzzyQueryCompany',
    method: 'post',
    data
  })
}

// KPI详情查——部门模糊查询接口
export function queryDownListGroup(data) {
  return request({
    url: '/byd/fuzzyQueryEmployeeGroup',
    method: 'post',
    data
  })
}

// KPI详情查询主机名模糊查询
export function queryKpiHostName(data) {
  return request({
    url: '/byd/fuzzyQueryWorkingHostName',
    method: 'post',
    data
  })
}

// KPI详情查询接口
// 性能统计模块接口
export function kpiTableInfo(data) {
  return request({
    url: '/byd/queryKPITableInfo',
    method: 'post',
    data
  })
}

// KPI趋势分析员工折线图
export function getEmpLine(data) {
  return request({
    url: '/byd/queryEmployeeKPIZheXianInfo',
    method: 'post',
    data
  })
}

// -----------------------------------------------------
// KPI排名界面接口
// 1、员工KPI排名总查询接口
export function empSeachAll(data) {
  return request({
    url: '/byd/queryEmployeeRankingAll',
    method: 'post',
    data
  })
}

// 2、员工KPI贡献接口
export function employeKpiPie(data) {
  return request({
    url: '/byd/queryEmployeeKPIContribution',
    method: 'post',
    data
  })
}

// 3、员工kpi排名
export function employeKpiRank(data) {
  return request({
    url: '/byd/queryEmployeeKPIRanking',
    method: 'post',
    data
  })
}

// 4、员工kpi加分排名
export function empRankSum(data) {
  return request({
    url: '/byd/queryEmployeeBonusRanking',
    method: 'post',
    data
  })
}

// 5、员工kpi减分排名
export function empMinusRankSum(data) {
  return request({
    url: '/byd/queryEmployeeMinusRanking',
    method: 'post',
    data
  })
}

// -----------------------------------------------------
// 6、部门KPI排名总查询接口
export function depSeachAll(data) {
  return request({
    url: '/byd/queryGroupRankingAll',
    method: 'post',
    data
  })
}

// 7、部门KPI贡献接口
export function deployeKpiPie(data) {
  return request({
    url: '/byd/queryGroupKPIContribution',
    method: 'post',
    data
  })
}

// 8、部门kpi排名
export function deployeKpiRank(data) {
  return request({
    url: '/byd/queryGroupKPIRanking',
    method: 'post',
    data
  })
}

// 9、部门kpi加分排名
export function depRankSum(data) {
  return request({
    url: '/byd/queryGroupBonusRanking',
    method: 'post',
    data
  })
}

// 10、部门kpi减分排名
export function depMinusRankSum(data) {
  return request({
    url: '/byd/queryGroupMinusRanking',
    method: 'post',
    data
  })
}

// -----------------------------------------------------
// 11、公司KPI排名总查询接口
export function comSeachAll(data) {
  return request({
    url: '/byd/queryCompanyRankingAll',
    method: 'post',
    data
  })
}

// 12、公司KPI贡献接口
export function comloyeKpiPie(data) {
  return request({
    url: '/byd/queryCompanyKPIContribution',
    method: 'post',
    data
  })
}

// 13、公司kpi排名
export function comloyeKpiRank(data) {
  return request({
    url: '/byd/queryCompanyKPIRanking',
    method: 'post',
    data
  })
}

// 14、公司kpi加分排名
export function comRankSum(data) {
  return request({
    url: '/byd/queryCompanyBonusRanking',
    method: 'post',
    data
  })
}

// 15、公司kpi减分排名
export function comMinusRankSum(data) {
  return request({
    url: '/byd/queryCompanyMinusRanking',
    method: 'post',
    data
  })
}

// -----------------------------------------------------
// 信息安全模块接口
// 总览模块
// 1、四种异常饼状图+柱状图
export function AllMality(data) {
  return request({
    url: '/byd/allAbnormalStatistics',
    method: 'post',
    data
  })
}

// 2、公司、部门、个人违规排名
export function AllViolations(data) {
  return request({
    url: '/byd/allViolationRanking',
    method: 'post',
    data
  })
}

// 主机所在公司模糊查询接口
export function queryHostCompany(data) {
  return request({
    url: '/byd/fuzzyQueryHostCompany',
    method: 'post',
    data
  })
}

// 主机所在部门模糊查询接口
export function queryHostGroup(data) {
  return request({
    url: '/byd/fuzzyQueryHostGroup',
    method: 'post',
    data
  })
}

// IP模糊查询接口
export function queryID(data) {
  return request({
    url: '/byd/fuzzyQueryIP',
    method: 'post',
    data
  })
}

// 文档所在公司模糊查询接口
export function queryDocCompany(data) {
  return request({
    url: '/byd/fuzzyQueryDocCompany',
    method: 'post',
    data
  })
}

// 文档所在部门模糊查询接口
export function queryDocGroup(data) {
  return request({
    url: '/byd/fuzzyQueryDocGroup',
    method: 'post',
    data
  })
}

// 文档所有者模糊查询接口
export function queryDocOwner(data) {
  return request({
    url: '/byd/fuzzyQueryDocOwner',
    method: 'post',
    data
  })
}

// 1.	异常登录模块——表格
export function abnormalLoginTableList(data) {
  return request({
    url: '/byd/getAbnormalLoginInfo',
    method: 'post',
    data
  })
}

// 2.	异常查看模块——表格
export function abnormalSeeTableList(data) {
  return request({
    url: '/byd/getAbnormalViewInfo',
    method: 'post',
    data
  })
}

// 3.	异常下载模块——表格
export function abnormalDownTableList(data) {
  return request({
    url: '/byd/getAbnormalDownloadInfo',
    method: 'post',
    data
  })
}

// 4.	异常删除模块——表格
export function abnormalDeleteTableList(data) {
  return request({
    url: '/byd/getAbnormalDeleteInfo',
    method: 'post',
    data
  })
}

// 个人异常趋势模块
export function personalAbnormality(data) {
  return request({
    url: '/byd/employeeNameTrendAnalysis',
    method: 'post',
    data
  })
}

// 部门异常趋势模块
export function depAlAbnormality(data) {
  return request({
    url: '/byd/employeeGroupTrendAnalysis',
    method: 'post',
    data
  })
}

// 公司异常趋势模块
export function comAlAbnormality(data) {
  return request({
    url: '/byd/employeeCompanyTrendAnalysis',
    method: 'post',
    data
  })
}

// -----------------------------------------------------
// 模拟接口
export function getPieData(data) {
  return request({
    url: '/index',
    method: 'post',
    data
  })
}

// 模拟kpi排名-公司排名接口
export function getComKpiTableList(data) {
  return request({
    url: '/index',
    method: 'post',
    data
  })
}
