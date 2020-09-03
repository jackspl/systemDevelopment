package com.szdeepwise.bydtcmonitor.service.impl;

import com.szdeepwise.bydtcmonitor.dao.NagiosDao;
import com.szdeepwise.bydtcmonitor.entity.*;
import com.szdeepwise.bydtcmonitor.mapper.BYDMapper;
import com.szdeepwise.bydtcmonitor.mapper.BaseMapper;
import com.szdeepwise.bydtcmonitor.dao.TCDao;
import com.szdeepwise.bydtcmonitor.service.BYDService;
import com.szdeepwise.bydtcmonitor.util.CalculateUtil;
import com.szdeepwise.bydtcmonitor.util.DateUtil;
import com.szdeepwise.bydtcmonitor.util.JDBCUtil;
import com.szdeepwise.bydtcmonitor.util.PageModelUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xubowen
 * @description BYDServiceImpl
 * @date 2019年 12月19日
 */
@Service
public class BYDServiceImpl implements BYDService {

    @Autowired
    private BYDMapper bydMapper;

    @Autowired
    private BaseMapper baseMapper;

    @Autowired
    private TCDao tcDao;

    @Autowired
    private NagiosDao nagiosDao;

    /*************************************tc-start************************************/
    //系统健康度检查
    @Override
    public int systemHealthCheck(JDBCRequestEntity jdbcRequestEntity) {
        int checkNum = tcDao.systemHealthCheck(jdbcRequestEntity);
        return checkNum;
    }

    //查询tc_link_info
    @Override
    public List<JDBCRequestEntity> queryTcLinkInfo() {
        List<JDBCRequestEntity> tcLinkInfoList = bydMapper.queryTcLinkInfo();
        return tcLinkInfoList;
    }

    //保存tc连接信息
    @Override
    public int insertTcLinkInfo(JDBCRequestEntity jdbcRequestEntity) {
        int i = bydMapper.insertTcLinkInfo(jdbcRequestEntity);
        return i;
    }

    //获取tc在线人数
    @Override
    public int GetPeopleOnline(JDBCRequestEntity jdbcRequestEntity) {
        int total = tcDao.GetPeopleOnline(jdbcRequestEntity);
        return total;
    }

    //获取tc在线用户的相关信息，存入tc_online_info
    @Override
    public void getOnlineToTcOnlineInfo(JDBCRequestEntity jdbcEntity) {
        //从tc数据库中获取在线用户的相关信息
        List<JDBCResponseEntity> tcOnlineInfoList = tcDao.getTCOnlineInfo(jdbcEntity);

        if (tcOnlineInfoList.size() > 0) {
            //插入tc_online_info
            for (JDBCResponseEntity jdbcResponseEntity : tcOnlineInfoList) {
                //插入前，查询tc_online_info,已存在的数据不插入
                List<JDBCResponseEntity> queryBeforeList = bydMapper.queryTcOnlineInfoBefore(jdbcResponseEntity);
                if (queryBeforeList.size() == 0) {
                    bydMapper.insertTcOnlineInfo(jdbcResponseEntity);
                }
            }
        }
    }

    //查询表格：tc_online_info
    @Override
    public List<BYDEntity> queryTableTcOnlineInfo(BYDEntity bydEntity) {
        List<BYDEntity> tcZheXianList = null;

        int zheXianFlag = bydEntity.getZheXianFlag();
        String zheXianDate = bydEntity.getZheXianDate();
        if (zheXianFlag == 1) {
            tcZheXianList = bydMapper.getTcOnlineZheXianInfoByHour(bydEntity);
        } else if (zheXianFlag == 2) {
            bydEntity.setZheXianDate(DateUtil.calculateSundayDate(bydEntity.getZheXianDate()));
            tcZheXianList = bydMapper.getTcOnlineZheXianInfoByWeek(bydEntity);
            //对日期进行排序
            Collections.sort(tcZheXianList, new Comparator<BYDEntity>() {
                @Override
                public int compare(BYDEntity o1, BYDEntity o2) {
                    //升序
                    return o1.getZheXianX().compareTo(o2.getZheXianX());
                }
            });
        } else if (zheXianFlag == 3) {
            String firstMonthDay = DateUtil.getFirstMonthDay(zheXianDate);
            bydEntity.setZheXianDate(firstMonthDay);
            tcZheXianList = bydMapper.getTcOnlineZheXianInfoByMonth(bydEntity);
            tcZheXianList.remove(tcZheXianList.size() - 1);
        }


        return tcZheXianList;
    }

    /*************************************tc-end************************************/

    /*---------------------------------------nagios-start---------------------------------------*/
    @Override
    public Map queryStatusSummary() {
        int okStatus = nagiosDao.getOkStatus();
        HashMap resultMap = new HashMap();
        resultMap.put("okStatus", okStatus);
        return resultMap;
    }
    /*---------------------------------------nagios-end---------------------------------------*/
    //模糊查询部门
    @Override
    public Map fuzzyQueryEmployeeGroup(BYDEntity bydEntity) {
        List<String> fuzzyEmployeeGroupList = bydMapper.fuzzyQueryEmployeeGroup(bydEntity);
        HashMap resultMap = new HashMap();
        resultMap.put("fuzzyEmployeeGroupList", fuzzyEmployeeGroupList);
        return resultMap;
    }

    //模糊查询项目
    @Override
    public Map fuzzyQueryEmployeeProject(BYDEntity bydEntity) {
        List<String> fuzzyEmployeeProjectList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                //查询项目
                List<String> employeeProjectList = bydMapper.fuzzyQueryEmployeeProject(bydEntity);
                if (employeeProjectList.size() != 0) {
                    if (null != employeeProjectList.get(0)) {
                        fuzzyEmployeeProjectList.addAll(employeeProjectList);
                    }
                }
            }
        }

        //allEmployeeProjectList高效去重+排序
        if (fuzzyEmployeeProjectList.size() != 0) {
            HashSet<String> hashSet02 = new HashSet<String>(fuzzyEmployeeProjectList);
            fuzzyEmployeeProjectList.clear();
            fuzzyEmployeeProjectList.addAll(hashSet02);
            Collections.sort(fuzzyEmployeeProjectList);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("fuzzyEmployeeProjectList", fuzzyEmployeeProjectList);
        return resultMap;
    }

    //模糊查询姓名
    @Override
    public Map fuzzyQueryEmployeeName(BYDEntity bydEntity) {
        List<String> fuzzyEmployeeNameList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                List<String> employeeNameList = bydMapper.fuzzyQueryEmployeeName(bydEntity);
                if (employeeNameList.size() != 0) {
                    if (null != employeeNameList.get(0)) {
                        fuzzyEmployeeNameList.addAll(employeeNameList);
                    }
                }
            }
        }

        //allEmployeeNameList高效去重+排序
        if (fuzzyEmployeeNameList.size() != 0) {
            HashSet<String> hashSet02 = new HashSet<String>(fuzzyEmployeeNameList);
            fuzzyEmployeeNameList.clear();
            fuzzyEmployeeNameList.addAll(hashSet02);
            Collections.sort(fuzzyEmployeeNameList);
        }
        HashMap resultMap = new HashMap();
        resultMap.put("fuzzyEmployeeNameList", fuzzyEmployeeNameList);
        return resultMap;
    }

    //模糊查询主机名
    @Override
    public Map fuzzyQueryHostName(BYDEntity bydEntity) {
        List<String> fuzzyQueryHostNameList =bydMapper.fuzzyQueryHostName(bydEntity);
        HashMap resultMap = new HashMap();
        resultMap.put("fuzzyQueryHostNameList", fuzzyQueryHostNameList);
        return resultMap;
    }

    //模糊查询主机所在部门
    @Override
    public Map fuzzyQueryHostGroup(BYDEntity bydEntity) {
        List<String> fuzzyQueryHostGroupList =bydMapper.fuzzyQueryHostGroup(bydEntity);
        HashMap resultMap = new HashMap();
        resultMap.put("fuzzyQueryHostGroupList", fuzzyQueryHostGroupList);
        return resultMap;
    }

    //模糊查询文件名
    @Override
    public Map fuzzyQueryDocName(BYDEntity bydEntity) {
        List<String> fuzzyQueryDocNameList = new ArrayList<>();

        bydEntity.setTableName("scene_view_doc");
        List<String> docNameSceneViewDocList = bydMapper.fuzzyQueryDocName(bydEntity);
        if (docNameSceneViewDocList.size() != 0) {
            if (null != docNameSceneViewDocList.get(0)) {
                fuzzyQueryDocNameList.addAll(docNameSceneViewDocList);
            }
        }

        bydEntity.setTableName("scene_download_doc");
        List<String> docNameSceneDownloadDocList = bydMapper.fuzzyQueryDocName(bydEntity);
        if (docNameSceneDownloadDocList.size() != 0) {
            if (null != docNameSceneDownloadDocList.get(0)) {
                fuzzyQueryDocNameList.addAll(docNameSceneDownloadDocList);
            }
        }

        //allEmployeeNameList高效去重+排序
        if (fuzzyQueryDocNameList.size() != 0) {
            HashSet<String> hashSet02 = new HashSet<String>(fuzzyQueryDocNameList);
            fuzzyQueryDocNameList.clear();
            fuzzyQueryDocNameList.addAll(hashSet02);
            Collections.sort(fuzzyQueryDocNameList);
        }
        HashMap resultMap = new HashMap();
        resultMap.put("fuzzyQueryDocNameList", fuzzyQueryDocNameList);
        return resultMap;
    }

    //模糊查询公司
    @Override
    public Map fuzzyQueryCompany(BYDEntity bydEntity) {

        List<String> fuzzyCompanyListList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                List<String> companyList = bydMapper.fuzzyQueryCompany(bydEntity);
                if (companyList.size() != 0) {
                    if (null != companyList.get(0)) {
                        fuzzyCompanyListList.addAll(companyList);
                    }
                }
            }
        }

        //fuzzyCompanyListList高效去重+排序
        if (fuzzyCompanyListList.size() != 0) {
            HashSet<String> hashSet02 = new HashSet<String>(fuzzyCompanyListList);
            fuzzyCompanyListList.clear();
            fuzzyCompanyListList.addAll(hashSet02);
            Collections.sort(fuzzyCompanyListList);
        }
        HashMap resultMap = new HashMap();
        resultMap.put("fuzzyCompanyListList", fuzzyCompanyListList);
        return resultMap;
    }

    //模糊查询主机所在公司
    @Override
    public Map fuzzyQueryHostCompany(BYDEntity bydEntity) {
        List<String> fuzzyHostCompanyList = bydMapper.fuzzyQueryHostCompany(bydEntity);
        HashMap resultMap = new HashMap();
        resultMap.put("fuzzyHostCompanyList", fuzzyHostCompanyList);
        return resultMap;
    }

    //模糊查询ip
    @Override
    public Map fuzzyQueryIP(BYDEntity bydEntity) {
        List<String> fuzzyIPList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                List<String> ipList = bydMapper.fuzzyQueryIP(bydEntity);
                if (ipList.size() != 0) {
                    if (null != ipList.get(0)) {
                        fuzzyIPList.addAll(ipList);
                    }
                }
            }
        }

        //fuzzyCompanyListList高效去重+排序
        if (fuzzyIPList.size() != 0) {
            HashSet<String> hashSet02 = new HashSet<String>(fuzzyIPList);
            fuzzyIPList.clear();
            fuzzyIPList.addAll(hashSet02);
            Collections.sort(fuzzyIPList);
        }
        HashMap resultMap = new HashMap();
        resultMap.put("fuzzyIPList", fuzzyIPList);
        return resultMap;
    }

    //模糊查询文件所在部门
    @Override
    public Map fuzzyQueryDocGroup(BYDEntity bydEntity) {
        List<String> fuzzyQueryDocGroupList = new ArrayList<>();

        //查看文档
        bydEntity.setTableName("scene_mytc_viewDoc");
        List<String> docGroupSceneViewDocList = bydMapper.fuzzyQueryDocGroup(bydEntity);
        if (docGroupSceneViewDocList.size() != 0) {
            if (null != docGroupSceneViewDocList.get(0)) {
                fuzzyQueryDocGroupList.addAll(docGroupSceneViewDocList);
            }
        }

        //打开数据集
        bydEntity.setTableName("scene_mytc_open_dataset");
        List<String> docGroupSceneDownloadDocList = bydMapper.fuzzyQueryDocGroup(bydEntity);
        if (docGroupSceneDownloadDocList.size() != 0) {
            if (null != docGroupSceneDownloadDocList.get(0)) {
                fuzzyQueryDocGroupList.addAll(docGroupSceneDownloadDocList);
            }
        }

        //删除
        bydEntity.setTableName("scene_general_delete");
        List<String> docGroupSceneDeleteList = bydMapper.fuzzyQueryDocGroup(bydEntity);
        if (docGroupSceneDeleteList.size() != 0) {
            if (null != docGroupSceneDeleteList.get(0)) {
                fuzzyQueryDocGroupList.addAll(docGroupSceneDeleteList);
            }
        }

        //PLMXML导出
        bydEntity.setTableName("scene_mytc_export_PLMXML");
        List<String> docGroupScenePLMXMLList = bydMapper.fuzzyQueryDocGroup(bydEntity);
        if (docGroupScenePLMXMLList.size() != 0) {
            if (null != docGroupScenePLMXMLList.get(0)) {
                fuzzyQueryDocGroupList.addAll(docGroupScenePLMXMLList);
            }
        }

        //命名的引用下载
        bydEntity.setTableName("scene_mytc_namedRef_download");
        List<String> docGroupSceneNameRefDownloadList = bydMapper.fuzzyQueryDocGroup(bydEntity);
        if (docGroupSceneNameRefDownloadList.size() != 0) {
            if (null != docGroupSceneNameRefDownloadList.get(0)) {
                fuzzyQueryDocGroupList.addAll(docGroupSceneNameRefDownloadList);
            }
        }


        //allEmployeeNameList高效去重+排序
        if (fuzzyQueryDocGroupList.size() != 0) {
            HashSet<String> hashSet02 = new HashSet<String>(fuzzyQueryDocGroupList);
            fuzzyQueryDocGroupList.clear();
            fuzzyQueryDocGroupList.addAll(hashSet02);
            Collections.sort(fuzzyQueryDocGroupList);
        }
        HashMap resultMap = new HashMap();
        resultMap.put("fuzzyQueryDocGroupList", fuzzyQueryDocGroupList);
        return resultMap;
    }

    //模糊查询文件所在公司
    @Override
    public Map fuzzyQueryDocCompany(BYDEntity bydEntity) {
        List<String> fuzzyQueryDocGroupList = new ArrayList<>();

        //查看文档
        bydEntity.setTableName("scene_mytc_viewDoc");
        List<String> docGroupSceneViewDocList = bydMapper.fuzzyQueryDocCompany(bydEntity);
        if (docGroupSceneViewDocList.size() != 0) {
            if (null != docGroupSceneViewDocList.get(0)) {
                fuzzyQueryDocGroupList.addAll(docGroupSceneViewDocList);
            }
        }

        //打开数据集
        bydEntity.setTableName("scene_mytc_open_dataset");
        List<String> docGroupSceneDownloadDocList = bydMapper.fuzzyQueryDocCompany(bydEntity);
        if (docGroupSceneDownloadDocList.size() != 0) {
            if (null != docGroupSceneDownloadDocList.get(0)) {
                fuzzyQueryDocGroupList.addAll(docGroupSceneDownloadDocList);
            }
        }

        //删除
        bydEntity.setTableName("scene_general_delete");
        List<String> docGroupSceneDeleteList = bydMapper.fuzzyQueryDocCompany(bydEntity);
        if (docGroupSceneDeleteList.size() != 0) {
            if (null != docGroupSceneDeleteList.get(0)) {
                fuzzyQueryDocGroupList.addAll(docGroupSceneDeleteList);
            }
        }

        //PLMXML导出
        bydEntity.setTableName("scene_mytc_export_PLMXML");
        List<String> docGroupScenePLMXMLList = bydMapper.fuzzyQueryDocCompany(bydEntity);
        if (docGroupScenePLMXMLList.size() != 0) {
            if (null != docGroupScenePLMXMLList.get(0)) {
                fuzzyQueryDocGroupList.addAll(docGroupScenePLMXMLList);
            }
        }

        //命名的引用下载
        bydEntity.setTableName("scene_mytc_namedRef_download");
        List<String> docGroupSceneNameRefDownloadList = bydMapper.fuzzyQueryDocCompany(bydEntity);
        if (docGroupSceneNameRefDownloadList.size() != 0) {
            if (null != docGroupSceneNameRefDownloadList.get(0)) {
                fuzzyQueryDocGroupList.addAll(docGroupSceneNameRefDownloadList);
            }
        }


        //allEmployeeNameList高效去重+排序
        if (fuzzyQueryDocGroupList.size() != 0) {
            HashSet<String> hashSet02 = new HashSet<String>(fuzzyQueryDocGroupList);
            fuzzyQueryDocGroupList.clear();
            fuzzyQueryDocGroupList.addAll(hashSet02);
            Collections.sort(fuzzyQueryDocGroupList);
        }
        HashMap resultMap = new HashMap();
        resultMap.put("fuzzyQueryDocCompanyList", fuzzyQueryDocGroupList);
        return resultMap;
    }

    //模糊查询文档所有者
    @Override
    public Map fuzzyQueryDocOwner(BYDEntity bydEntity) {
        List<String> fuzzyQueryDocGroupList = new ArrayList<>();

        //查看文档
        bydEntity.setTableName("scene_mytc_viewDoc");
        List<String> docGroupSceneViewDocList = bydMapper.fuzzyQueryDocOwner(bydEntity);
        if (docGroupSceneViewDocList.size() != 0) {
            if (null != docGroupSceneViewDocList.get(0)) {
                fuzzyQueryDocGroupList.addAll(docGroupSceneViewDocList);
            }
        }

        //打开数据集
        bydEntity.setTableName("scene_mytc_open_dataset");
        List<String> docGroupSceneDownloadDocList = bydMapper.fuzzyQueryDocOwner(bydEntity);
        if (docGroupSceneDownloadDocList.size() != 0) {
            if (null != docGroupSceneDownloadDocList.get(0)) {
                fuzzyQueryDocGroupList.addAll(docGroupSceneDownloadDocList);
            }
        }

        //删除
        bydEntity.setTableName("scene_general_delete");
        List<String> docGroupSceneDeleteList = bydMapper.fuzzyQueryDocOwner(bydEntity);
        if (docGroupSceneDeleteList.size() != 0) {
            if (null != docGroupSceneDeleteList.get(0)) {
                fuzzyQueryDocGroupList.addAll(docGroupSceneDeleteList);
            }
        }

        //PLMXML导出
        bydEntity.setTableName("scene_mytc_export_PLMXML");
        List<String> docGroupScenePLMXMLList = bydMapper.fuzzyQueryDocOwner(bydEntity);
        if (docGroupScenePLMXMLList.size() != 0) {
            if (null != docGroupScenePLMXMLList.get(0)) {
                fuzzyQueryDocGroupList.addAll(docGroupScenePLMXMLList);
            }
        }

        //命名的引用下载
        bydEntity.setTableName("scene_mytc_namedRef_download");
        List<String> docGroupSceneNameRefDownloadList = bydMapper.fuzzyQueryDocOwner(bydEntity);
        if (docGroupSceneNameRefDownloadList.size() != 0) {
            if (null != docGroupSceneNameRefDownloadList.get(0)) {
                fuzzyQueryDocGroupList.addAll(docGroupSceneNameRefDownloadList);
            }
        }


        //allEmployeeNameList高效去重+排序
        if (fuzzyQueryDocGroupList.size() != 0) {
            HashSet<String> hashSet02 = new HashSet<String>(fuzzyQueryDocGroupList);
            fuzzyQueryDocGroupList.clear();
            fuzzyQueryDocGroupList.addAll(hashSet02);
            Collections.sort(fuzzyQueryDocGroupList);
        }
        HashMap resultMap = new HashMap();
        resultMap.put("fuzzyQueryDocOwnerList", fuzzyQueryDocGroupList);
        return resultMap;
    }
    /***************************************信息安全功能-start*****************************************/
    //总览，四种异常饼状图+柱状图
    @Override
    public Map allAbnormalStatistics(BYDEntity bydEntity) {
        Map map = new HashMap();
        List<BYDEntity> allPieChartList = new ArrayList<>();

        //饼状图
        //登录场景
        bydEntity.setTableName("scene_login");
        List<BYDEntity> list1 = bydMapper.queryAllPieChart(bydEntity);
        list1.get(0).setRecentInfo("异常登录");
        if (list1.size() != 0) {
            if (null != list1.get(0)) {
                allPieChartList.addAll(list1);
            }
        }

        //查看文档场景
        bydEntity.setTableName("scene_mytc_viewDoc");
        List<BYDEntity> list2 = bydMapper.queryAllPieChart(bydEntity);
        list2.get(0).setRecentInfo("异常查看");
        if (list2.size() != 0) {
            if (null != list2.get(0)) {
                allPieChartList.addAll(list2);
            }
        }
        //打开数据集场景
        bydEntity.setTableName("scene_mytc_open_dataset");
        List<BYDEntity> list3 = bydMapper.queryAllPieChart(bydEntity);
        list3.get(0).setRecentInfo("异常查看");
        if (list3.size() != 0) {
            if (null != list3.get(0)) {
                allPieChartList.addAll(list3);
            }
        }

        //删除场景
        bydEntity.setTableName("scene_general_delete");
        List<BYDEntity> list4 = bydMapper.queryAllPieChart(bydEntity);
        list4.get(0).setRecentInfo("异常删除");
        if (list4.size() != 0) {
            if (null != list4.get(0)) {
                allPieChartList.addAll(list4);
            }
        }

        //PLMXML导出
        bydEntity.setTableName("scene_mytc_export_PLMXML");
        List<BYDEntity> list5 = bydMapper.queryAllPieChart(bydEntity);
        list5.get(0).setRecentInfo("异常下载");
        if (list5.size() != 0) {
            if (null != list5.get(0)) {
                allPieChartList.addAll(list5);
            }
        }
        //命名的引用下载
        bydEntity.setTableName("scene_mytc_namedRef_download");
        List<BYDEntity> list6 = bydMapper.queryAllPieChart(bydEntity);
        list6.get(0).setRecentInfo("异常下载");
        if (list6.size() != 0) {
            if (null != list6.get(0)) {
                allPieChartList.addAll(list6);
            }
        }

        //将同异常情况的operaCount相加
        allPieChartList= CalculateUtil.calAbnormalOperaCount(allPieChartList);

        //饼状图
        map.put("allPieChartList",allPieChartList);

        double allCount = allPieChartList.stream().mapToInt(BYDEntity::getOperaCount).sum();
        map.put("allCount",allCount);

        //柱状图
        List<BYDEntity> daysList = DateUtil.getDays(bydEntity.getActionBeginTime(), bydEntity.getActionEndTime(), bydEntity.getCompany(), bydEntity.getEmployeeGroup(), bydEntity.getEmployeeName());
        //
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();

        for (BYDEntity bydEntityAbnormal : allPieChartList) {
            List<BYDEntity> arrayList = new ArrayList();
            for (int i = 0; i < daysList.size(); i++) {
                BYDEntity entity = daysList.get(i);
                int operaCounts = 0;

                if("异常登录".equals(bydEntityAbnormal.getRecentInfo())){
                    //登录场景
                    entity.setTableName("scene_login");
                    int operaCount1 = bydMapper.queryAbnormalZheXianLike(entity);
                    operaCounts = operaCounts + operaCount1;
                }

                if("异常查看".equals(bydEntityAbnormal.getRecentInfo())){
                    //查看文档场景
                    entity.setTableName("scene_mytc_viewDoc");
                    int operaCount2 = bydMapper.queryAbnormalZheXianLike(entity);
                    operaCounts = operaCounts + operaCount2;
                    //打开数据集场景
                    entity.setTableName("scene_mytc_open_dataset");
                    int operaCount3 = bydMapper.queryAbnormalZheXianLike(entity);
                    operaCounts = operaCounts + operaCount3;
                }

                if("异常下载".equals(bydEntityAbnormal.getRecentInfo())){
                    //PLMXML导出
                    entity.setTableName("scene_mytc_export_PLMXML");
                    int operaCount5 = bydMapper.queryAbnormalZheXianLike(entity);
                    operaCounts = operaCounts + operaCount5;
                    //命名的引用下载
                    entity.setTableName("scene_mytc_namedRef_download");
                    int operaCount6 = bydMapper.queryAbnormalZheXianLike(entity);
                    operaCounts = operaCounts + operaCount6;
                }

                if("异常删除".equals(bydEntityAbnormal.getRecentInfo())){
                    //删除场景
                    entity.setTableName("scene_general_delete");
                    int operaCount4 = bydMapper.queryAbnormalZheXianLike(entity);
                    operaCounts = operaCounts + operaCount4;
                }
                entity.setOperaCount(operaCounts);
                entity.setRecentInfo(bydEntityAbnormal.getRecentInfo());
                arrayList.add(i,entity);

            }
            JSONArray arrayElementOneArrayElementOne = JSONArray.fromObject(arrayList);
            JSONObject arrayElementOne = new JSONObject();
            arrayElementOne.element("children",arrayElementOneArrayElementOne);
            array.add(arrayElementOne);
        }
        map.put("nameList",array);
        //

        return map;
    }

    //总览，公司、部门、个人违规排名
    @Override
    public Map allViolationRanking(BYDEntity bydEntity) {
        Map map = new HashMap();
        /*公司违规排名*/
        List<BYDEntity> violationCompanyList = new ArrayList<>();

        //查询违规公司
        //登录场景
        bydEntity.setTableName("scene_login");
        List<BYDEntity> list1 = bydMapper.employeeCompanyPieChart(bydEntity);
        if (list1.size() != 0) {
            if (null != list1.get(0)) {
                violationCompanyList.addAll(list1);
            }
        }

        //查看文档场景
        bydEntity.setTableName("scene_mytc_viewDoc");
        List<BYDEntity> list2 = bydMapper.employeeCompanyPieChart(bydEntity);
        if (list2.size() != 0) {
            if (null != list2.get(0)) {
                violationCompanyList.addAll(list2);
            }
        }
        //打开数据集场景
        bydEntity.setTableName("scene_mytc_open_dataset");
        List<BYDEntity> list3 = bydMapper.employeeCompanyPieChart(bydEntity);
        if (list3.size() != 0) {
            if (null != list3.get(0)) {
                violationCompanyList.addAll(list3);
            }
        }
        //删除场景
        bydEntity.setTableName("scene_general_delete");
        List<BYDEntity> list4 = bydMapper.employeeCompanyPieChart(bydEntity);
        if (list4.size() != 0) {
            if (null != list4.get(0)) {
                violationCompanyList.addAll(list4);
            }
        }
        //PLMXML导出
        bydEntity.setTableName("scene_mytc_export_PLMXML");
        List<BYDEntity> list5 = bydMapper.employeeCompanyPieChart(bydEntity);
        if (list5.size() != 0) {
            if (null != list5.get(0)) {
                violationCompanyList.addAll(list5);
            }
        }
        //命名的引用下载
        bydEntity.setTableName("scene_mytc_namedRef_download");
        List<BYDEntity> list6 = bydMapper.employeeCompanyPieChart(bydEntity);
        if (list6.size() != 0) {
            if (null != list6.get(0)) {
                violationCompanyList.addAll(list6);
            }
        }

        violationCompanyList= CalculateUtil.calCompanyOperaCount(violationCompanyList);

        //循环违规公司
        for (BYDEntity companyEntity : violationCompanyList) {
            int loginCounts = 0;
            int viewCounts = 0;
            int downLoadCounts = 0;
            int deleteCounts = 0;
            AbnormalEntity abnormalEntity = new AbnormalEntity();

            companyEntity.setActionBeginTime(bydEntity.getActionBeginTime());
            companyEntity.setActionEndTime(bydEntity.getActionEndTime());

            //登录
            companyEntity.setTableName("scene_login");
            List<BYDEntity> list01 = bydMapper.employeeCompanyPieChart(companyEntity);
            if (list01.size() != 0) {
                if (null != list01.get(0)) {
                    loginCounts = list01.get(0).getOperaCount();
                }
            }

            //查看文档场景
            companyEntity.setTableName("scene_mytc_viewDoc");
            List<BYDEntity> list02 = bydMapper.employeeCompanyPieChart(companyEntity);
            if (list02.size() != 0) {
                if (null != list02.get(0)) {
                    viewCounts = list02.get(0).getOperaCount();
                }
            }

            //打开数据集场景
            companyEntity.setTableName("scene_mytc_open_dataset");
            List<BYDEntity> list03 = bydMapper.employeeCompanyPieChart(companyEntity);
            if (list03.size() != 0) {
                if (null != list03.get(0)) {
                    viewCounts = viewCounts + list03.get(0).getOperaCount();
                }
            }

            //删除场景
            companyEntity.setTableName("scene_general_delete");
            List<BYDEntity> list04 = bydMapper.employeeCompanyPieChart(companyEntity);
            if (list04.size() != 0) {
                if (null != list04.get(0)) {
                    deleteCounts = list04.get(0).getOperaCount();
                }
            }

            //PLMXML导出
            companyEntity.setTableName("scene_mytc_export_PLMXML");
            List<BYDEntity> list05 = bydMapper.employeeCompanyPieChart(companyEntity);
            if (list05.size() != 0) {
                if (null != list05.get(0)) {
                    downLoadCounts = list05.get(0).getOperaCount();
                }
            }

            //命名的引用下载
            companyEntity.setTableName("scene_mytc_namedRef_download");
            List<BYDEntity> list06 = bydMapper.employeeCompanyPieChart(companyEntity);
            if (list06.size() != 0) {
                if (null != list06.get(0)) {
                    downLoadCounts = downLoadCounts + list06.get(0).getOperaCount();
                }
            }

            companyEntity.setAbnormalLogin(loginCounts);
            companyEntity.setAbnormalView(viewCounts);
            companyEntity.setAbnormalDelete(deleteCounts);
            companyEntity.setAbnormalDownload(downLoadCounts);

            companyEntity.setOperaCount(loginCounts+viewCounts+deleteCounts+downLoadCounts);
        }
        map.put("violationCompanyList",violationCompanyList);

        /*部门违规排名*/
        List<BYDEntity> violationGroupList = new ArrayList<>();

        //查询违规部门
        //登录场景
        bydEntity.setTableName("scene_login");
        List<BYDEntity> list01 = bydMapper.employeeGroupPieChart(bydEntity);
        if (list01.size() != 0) {
            if (null != list01.get(0)) {
                violationGroupList.addAll(list01);
            }
        }

        //查看文档场景
        bydEntity.setTableName("scene_mytc_viewDoc");
        List<BYDEntity> list02 = bydMapper.employeeGroupPieChart(bydEntity);
        if (list02.size() != 0) {
            if (null != list02.get(0)) {
                violationGroupList.addAll(list02);
            }
        }
        //打开数据集场景
        bydEntity.setTableName("scene_mytc_open_dataset");
        List<BYDEntity> list03 = bydMapper.employeeGroupPieChart(bydEntity);
        if (list03.size() != 0) {
            if (null != list03.get(0)) {
                violationGroupList.addAll(list03);
            }
        }
        //删除场景
        bydEntity.setTableName("scene_general_delete");
        List<BYDEntity> list04 = bydMapper.employeeGroupPieChart(bydEntity);
        if (list04.size() != 0) {
            if (null != list04.get(0)) {
                violationGroupList.addAll(list04);
            }
        }
        //PLMXML导出
        bydEntity.setTableName("scene_mytc_export_PLMXML");
        List<BYDEntity> list05 = bydMapper.employeeGroupPieChart(bydEntity);
        if (list05.size() != 0) {
            if (null != list05.get(0)) {
                violationGroupList.addAll(list05);
            }
        }
        //命名的引用下载
        bydEntity.setTableName("scene_mytc_namedRef_download");
        List<BYDEntity> list06 = bydMapper.employeeGroupPieChart(bydEntity);
        if (list06.size() != 0) {
            if (null != list06.get(0)) {
                violationGroupList.addAll(list06);
            }
        }

        violationGroupList= CalculateUtil.calEmployeeGroupOperaCount(violationGroupList);

        //循环违规部门
        for (BYDEntity groupEntity : violationGroupList) {
            int loginCounts = 0;
            int viewCounts = 0;
            int downLoadCounts = 0;
            int deleteCounts = 0;
            AbnormalEntity abnormalEntity = new AbnormalEntity();

            groupEntity.setActionBeginTime(bydEntity.getActionBeginTime());
            groupEntity.setActionEndTime(bydEntity.getActionEndTime());

            //登录
            groupEntity.setTableName("scene_login");
            List<BYDEntity> list001 = bydMapper.employeeGroupPieChart(groupEntity);
            if (list001.size() != 0) {
                if (null != list001.get(0)) {
                    loginCounts = list001.get(0).getOperaCount();
                }
            }

            //查看文档场景
            groupEntity.setTableName("scene_mytc_viewDoc");
            List<BYDEntity> list002 = bydMapper.employeeGroupPieChart(groupEntity);
            if (list002.size() != 0) {
                if (null != list002.get(0)) {
                    viewCounts = list002.get(0).getOperaCount();
                }
            }

            //打开数据集场景
            groupEntity.setTableName("scene_mytc_open_dataset");
            List<BYDEntity> list003 = bydMapper.employeeGroupPieChart(groupEntity);
            if (list003.size() != 0) {
                if (null != list003.get(0)) {
                    viewCounts = viewCounts + list003.get(0).getOperaCount();
                }
            }

            //删除场景
            groupEntity.setTableName("scene_general_delete");
            List<BYDEntity> list004 = bydMapper.employeeGroupPieChart(groupEntity);
            if (list004.size() != 0) {
                if (null != list004.get(0)) {
                    deleteCounts = list004.get(0).getOperaCount();
                }
            }

            //PLMXML导出
            groupEntity.setTableName("scene_mytc_export_PLMXML");
            List<BYDEntity> list005 = bydMapper.employeeGroupPieChart(groupEntity);
            if (list005.size() != 0) {
                if (null != list005.get(0)) {
                    downLoadCounts = list005.get(0).getOperaCount();
                }
            }

            //命名的引用下载
            groupEntity.setTableName("scene_mytc_namedRef_download");
            List<BYDEntity> list006 = bydMapper.employeeGroupPieChart(groupEntity);
            if (list006.size() != 0) {
                if (null != list006.get(0)) {
                    downLoadCounts = downLoadCounts + list006.get(0).getOperaCount();
                }
            }

            groupEntity.setAbnormalLogin(loginCounts);
            groupEntity.setAbnormalView(viewCounts);
            groupEntity.setAbnormalDelete(deleteCounts);
            groupEntity.setAbnormalDownload(downLoadCounts);

            groupEntity.setOperaCount(loginCounts+viewCounts+deleteCounts+downLoadCounts);
        }
        map.put("violationGroupList",violationGroupList);
        /*个人违规排名*/
        List<BYDEntity> violationNameList = new ArrayList<>();

        //查询违规用户
        //登录场景
        bydEntity.setTableName("scene_login");
        List<BYDEntity> list001 = bydMapper.employeeNamePieChart(bydEntity);
        if (list001.size() != 0) {
            if (null != list001.get(0)) {
                violationNameList.addAll(list001);
            }
        }

        //查看文档场景
        bydEntity.setTableName("scene_mytc_viewDoc");
        List<BYDEntity> list002 = bydMapper.employeeNamePieChart(bydEntity);
        if (list002.size() != 0) {
            if (null != list002.get(0)) {
                violationNameList.addAll(list002);
            }
        }
        //打开数据集场景
        bydEntity.setTableName("scene_mytc_open_dataset");
        List<BYDEntity> list003 = bydMapper.employeeNamePieChart(bydEntity);
        if (list003.size() != 0) {
            if (null != list003.get(0)) {
                violationNameList.addAll(list003);
            }
        }
        //删除场景
        bydEntity.setTableName("scene_general_delete");
        List<BYDEntity> list004 = bydMapper.employeeNamePieChart(bydEntity);
        if (list004.size() != 0) {
            if (null != list004.get(0)) {
                violationNameList.addAll(list004);
            }
        }
        //PLMXML导出
        bydEntity.setTableName("scene_mytc_export_PLMXML");
        List<BYDEntity> list005 = bydMapper.employeeNamePieChart(bydEntity);
        if (list005.size() != 0) {
            if (null != list005.get(0)) {
                violationNameList.addAll(list005);
            }
        }
        //命名的引用下载
        bydEntity.setTableName("scene_mytc_namedRef_download");
        List<BYDEntity> list006 = bydMapper.employeeNamePieChart(bydEntity);
        if (list006.size() != 0) {
            if (null != list006.get(0)) {
                violationNameList.addAll(list006);
            }
        }

        violationNameList= CalculateUtil.calEmployeeNameOperaCount(violationNameList);

        //循环违规用户
        for (BYDEntity nameEntity : violationNameList) {
            List<String> employeeGroupList = new ArrayList();

            int loginCounts = 0;
            int viewCounts = 0;
            int downLoadCounts = 0;
            int deleteCounts = 0;

            nameEntity.setActionBeginTime(bydEntity.getActionBeginTime());
            nameEntity.setActionEndTime(bydEntity.getActionEndTime());

            //登录
            nameEntity.setTableName("scene_login");
            List<BYDEntity> list11 = bydMapper.employeeNamePieChart(nameEntity);
            if (list11.size() != 0) {
                if (null != list11.get(0)) {
                    loginCounts = list11.get(0).getOperaCount();
                }
            }
            employeeGroupList = bydMapper.getGroupByName(nameEntity);
            if (employeeGroupList.size() != 0) {
                if (null != employeeGroupList.get(0)) {
                    nameEntity.setEmployeeGroup(employeeGroupList.get(0));
                }
            }

            //查看文档场景
            nameEntity.setTableName("scene_mytc_viewDoc");
            List<BYDEntity> list12 = bydMapper.employeeNamePieChart(nameEntity);
            if (list12.size() != 0) {
                if (null != list12.get(0)) {
                    viewCounts = list12.get(0).getOperaCount();
                }
            }
            employeeGroupList = bydMapper.getGroupByName(nameEntity);
            if (employeeGroupList.size() != 0) {
                if (null != employeeGroupList.get(0)) {
                    nameEntity.setEmployeeGroup(employeeGroupList.get(0));
                }
            }

            //打开数据集场景
            nameEntity.setTableName("scene_mytc_open_dataset");
            List<BYDEntity> list13 = bydMapper.employeeNamePieChart(nameEntity);
            if (list13.size() != 0) {
                if (null != list13.get(0)) {
                    viewCounts = viewCounts + list13.get(0).getOperaCount();
                }
            }
            employeeGroupList = bydMapper.getGroupByName(nameEntity);
            if (employeeGroupList.size() != 0) {
                if (null != employeeGroupList.get(0)) {
                    nameEntity.setEmployeeGroup(employeeGroupList.get(0));
                }
            }

            //删除场景
            nameEntity.setTableName("scene_general_delete");
            List<BYDEntity> list14 = bydMapper.employeeNamePieChart(nameEntity);
            if (list14.size() != 0) {
                if (null != list14.get(0)) {
                    deleteCounts = list14.get(0).getOperaCount();
                }
            }
            employeeGroupList = bydMapper.getGroupByName(nameEntity);
            if (employeeGroupList.size() != 0) {
                if (null != employeeGroupList.get(0)) {
                    nameEntity.setEmployeeGroup(employeeGroupList.get(0));
                }
            }

            //PLMXML导出
            nameEntity.setTableName("scene_mytc_export_PLMXML");
            List<BYDEntity> list15 = bydMapper.employeeNamePieChart(nameEntity);
            if (list15.size() != 0) {
                if (null != list15.get(0)) {
                    downLoadCounts = list15.get(0).getOperaCount();
                }
            }
            employeeGroupList = bydMapper.getGroupByName(nameEntity);
            if (employeeGroupList.size() != 0) {
                if (null != employeeGroupList.get(0)) {
                    nameEntity.setEmployeeGroup(employeeGroupList.get(0));
                }
            }

            //命名的引用下载
            nameEntity.setTableName("scene_mytc_namedRef_download");
            List<BYDEntity> list16 = bydMapper.employeeNamePieChart(nameEntity);
            if (list16.size() != 0) {
                if (null != list16.get(0)) {
                    downLoadCounts = downLoadCounts + list16.get(0).getOperaCount();
                }
            }
            employeeGroupList = bydMapper.getGroupByName(nameEntity);
            if (employeeGroupList.size() != 0) {
                if (null != employeeGroupList.get(0)) {
                    nameEntity.setEmployeeGroup(employeeGroupList.get(0));
                }
            }

            nameEntity.setAbnormalLogin(loginCounts);
            nameEntity.setAbnormalView(viewCounts);
            nameEntity.setAbnormalDelete(deleteCounts);
            nameEntity.setAbnormalDownload(downLoadCounts);

            nameEntity.setOperaCount(loginCounts+viewCounts+deleteCounts+downLoadCounts);
        }
        map.put("violationNameList",violationNameList);
        return map;
    }

    //趋势分析，个人趋势，总查询
    @Override
    public Map employeeNameTrendAnalysis(BYDEntity bydEntity) {
        Map map = new HashMap();
        List<BYDEntity> employeeNamePieChartList = new ArrayList<>();

        //查询个人异常趋势饼状图
        //登录场景
        bydEntity.setTableName("scene_login");
        List<BYDEntity> list1 = bydMapper.employeeNamePieChart(bydEntity);
        if (list1.size() != 0) {
            if (null != list1.get(0)) {
                employeeNamePieChartList.addAll(list1);
            }
        }

        //查看文档场景
        bydEntity.setTableName("scene_mytc_viewDoc");
        List<BYDEntity> list2 = bydMapper.employeeNamePieChart(bydEntity);
        if (list2.size() != 0) {
            if (null != list2.get(0)) {
                employeeNamePieChartList.addAll(list2);
            }
        }
        //打开数据集场景
        bydEntity.setTableName("scene_mytc_open_dataset");
        List<BYDEntity> list3 = bydMapper.employeeNamePieChart(bydEntity);
        if (list3.size() != 0) {
            if (null != list3.get(0)) {
                employeeNamePieChartList.addAll(list3);
            }
        }
        //删除场景
        bydEntity.setTableName("scene_general_delete");
        List<BYDEntity> list4 = bydMapper.employeeNamePieChart(bydEntity);
        if (list4.size() != 0) {
            if (null != list4.get(0)) {
                employeeNamePieChartList.addAll(list4);
            }
        }
        //PLMXML导出
        bydEntity.setTableName("scene_mytc_export_PLMXML");
        List<BYDEntity> list5 = bydMapper.employeeNamePieChart(bydEntity);
        if (list5.size() != 0) {
            if (null != list5.get(0)) {
                employeeNamePieChartList.addAll(list5);
            }
        }
        //命名的引用下载
        bydEntity.setTableName("scene_mytc_namedRef_download");
        List<BYDEntity> list6 = bydMapper.employeeNamePieChart(bydEntity);
        if (list6.size() != 0) {
            if (null != list6.get(0)) {
                employeeNamePieChartList.addAll(list6);
            }
        }

        //将同employeeName的operaCount相加
        employeeNamePieChartList= CalculateUtil.calEmployeeNameOperaCount(employeeNamePieChartList);

        //个人饼状图
        map.put("employeeNamePieChartList",employeeNamePieChartList);

        double allCount = employeeNamePieChartList.stream().mapToInt(BYDEntity::getOperaCount).sum();
        map.put("allCount",allCount);

        //查询个人异常趋势折线图
        //查询employeeName集合
/*        List<String> employeeNameList = getInfoSafeEmployeeName(bydEntity);
        //employeeNameList高效去重+排序
        if (employeeNameList.size() != 0) {
            HashSet<String> hashSet02 = new HashSet<String>(employeeNameList);
            employeeNameList.clear();
            employeeNameList.addAll(hashSet02);
            Collections.sort(employeeNameList);
        }*/

        List<BYDEntity> daysList = DateUtil.getDays(bydEntity.getActionBeginTime(), bydEntity.getActionEndTime(), bydEntity.getCompany(), bydEntity.getEmployeeGroup(), bydEntity.getEmployeeName());

        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();

        for (BYDEntity bydEntityName : employeeNamePieChartList) {
            List<BYDEntity> arrayList = new ArrayList();
                for (int i = 0; i < daysList.size(); i++) {
                    BYDEntity entity = daysList.get(i);
                    int operaCounts = 0;
                    entity.setEmployeeName(bydEntityName.getEmployeeName());

                    //登录场景
                    entity.setTableName("scene_login");
                    int operaCount1 = bydMapper.queryNameZheXianLike(entity);
                    operaCounts = operaCounts + operaCount1;
                    //查看文档场景
                    entity.setTableName("scene_mytc_viewDoc");
                    int operaCount2 = bydMapper.queryNameZheXianLike(entity);
                    operaCounts = operaCounts + operaCount2;
                    //打开数据集场景
                    entity.setTableName("scene_mytc_open_dataset");
                    int operaCount3 = bydMapper.queryNameZheXianLike(entity);
                    operaCounts = operaCounts + operaCount3;
                    //删除场景
                    entity.setTableName("scene_general_delete");
                    int operaCount4 = bydMapper.queryNameZheXianLike(entity);
                    operaCounts = operaCounts + operaCount4;
                    //PLMXML导出
                    entity.setTableName("scene_mytc_export_PLMXML");
                    int operaCount5 = bydMapper.queryNameZheXianLike(entity);
                    operaCounts = operaCounts + operaCount5;
                    //命名的引用下载
                    entity.setTableName("scene_mytc_namedRef_download");
                    int operaCount6 = bydMapper.queryNameZheXianLike(entity);
                    operaCounts = operaCounts + operaCount6;

                    entity.setOperaCount(operaCounts);

                    arrayList.add(i,entity);

            }
            JSONArray arrayElementOneArrayElementOne = JSONArray.fromObject(arrayList);
            JSONObject arrayElementOne = new JSONObject();
            arrayElementOne.element("children",arrayElementOneArrayElementOne);
            array.add(arrayElementOne);
        }
        //object.element("nameList",array);
        //String resultString = object.toString();
        //System.out.println(resultString);
        map.put("nameList",array);
        return map;
    }

    //趋势分析，部门趋势，总查询
    @Override
    public Map employeeGroupTrendAnalysis(BYDEntity bydEntity) {
        Map map = new HashMap();
        List<BYDEntity> employeeGroupPieChartList = new ArrayList<>();

        //查询部门异常趋势饼状图
        //登录场景
        bydEntity.setTableName("scene_login");
        List<BYDEntity> list1 = bydMapper.employeeGroupPieChart(bydEntity);
        if (list1.size() != 0) {
            if (null != list1.get(0)) {
                employeeGroupPieChartList.addAll(list1);
            }
        }

        //查看文档场景
        bydEntity.setTableName("scene_mytc_viewDoc");
        List<BYDEntity> list2 = bydMapper.employeeGroupPieChart(bydEntity);
        if (list2.size() != 0) {
            if (null != list2.get(0)) {
                employeeGroupPieChartList.addAll(list2);
            }
        }
        //打开数据集场景
        bydEntity.setTableName("scene_mytc_open_dataset");
        List<BYDEntity> list3 = bydMapper.employeeGroupPieChart(bydEntity);
        if (list3.size() != 0) {
            if (null != list3.get(0)) {
                employeeGroupPieChartList.addAll(list3);
            }
        }
        //删除场景
        bydEntity.setTableName("scene_general_delete");
        List<BYDEntity> list4 = bydMapper.employeeGroupPieChart(bydEntity);
        if (list4.size() != 0) {
            if (null != list4.get(0)) {
                employeeGroupPieChartList.addAll(list4);
            }
        }
        //PLMXML导出
        bydEntity.setTableName("scene_mytc_export_PLMXML");
        List<BYDEntity> list5 = bydMapper.employeeGroupPieChart(bydEntity);
        if (list5.size() != 0) {
            if (null != list5.get(0)) {
                employeeGroupPieChartList.addAll(list5);
            }
        }
        //命名的引用下载
        bydEntity.setTableName("scene_mytc_namedRef_download");
        List<BYDEntity> list6 = bydMapper.employeeGroupPieChart(bydEntity);
        if (list6.size() != 0) {
            if (null != list6.get(0)) {
                employeeGroupPieChartList.addAll(list6);
            }
        }

        //将同employeeGroup的operaCount相加
        employeeGroupPieChartList= CalculateUtil.calEmployeeGroupOperaCount(employeeGroupPieChartList);

        //个人饼状图
        map.put("employeeGroupPieChartList",employeeGroupPieChartList);

        double allCount = employeeGroupPieChartList.stream().mapToInt(BYDEntity::getOperaCount).sum();
        map.put("allCount",allCount);

        //查询部门异常趋势柱状图
        // 查询employeeGroup集合
/*        List<String> employeeGroupList = getInfoSafeEmployeeGroup(bydEntity);
        //employeeNameList高效去重+排序
        if (employeeGroupList.size() != 0) {
            HashSet<String> hashSet02 = new HashSet<String>(employeeGroupList);
            employeeGroupList.clear();
            employeeGroupList.addAll(hashSet02);
            Collections.sort(employeeGroupList);
        }*/

        List<BYDEntity> daysList = DateUtil.getDays(bydEntity.getActionBeginTime(), bydEntity.getActionEndTime(), bydEntity.getCompany(), bydEntity.getEmployeeGroup(), bydEntity.getEmployeeName());

        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();

        for (BYDEntity bydEntityGroup : employeeGroupPieChartList) {
            List<BYDEntity> arrayList = new ArrayList();
            for (int i = 0; i < daysList.size(); i++) {
                BYDEntity entity = daysList.get(i);
                int operaCounts = 0;
                entity.setEmployeeGroup(bydEntityGroup.getEmployeeGroup());

                //登录场景
                entity.setTableName("scene_login");
                int operaCount1 = bydMapper.queryGroupZhuZhuangLike(entity);
                operaCounts = operaCounts + operaCount1;
                //查看文档场景
                entity.setTableName("scene_mytc_viewDoc");
                int operaCount2 = bydMapper.queryGroupZhuZhuangLike(entity);
                operaCounts = operaCounts + operaCount2;
                //打开数据集场景
                entity.setTableName("scene_mytc_open_dataset");
                int operaCount3 = bydMapper.queryGroupZhuZhuangLike(entity);
                operaCounts = operaCounts + operaCount3;
                //删除场景
                entity.setTableName("scene_general_delete");
                int operaCount4 = bydMapper.queryGroupZhuZhuangLike(entity);
                operaCounts = operaCounts + operaCount4;
                //PLMXML导出
                entity.setTableName("scene_mytc_export_PLMXML");
                int operaCount5 = bydMapper.queryGroupZhuZhuangLike(entity);
                operaCounts = operaCounts + operaCount5;
                //命名的引用下载
                entity.setTableName("scene_mytc_namedRef_download");
                int operaCount6 = bydMapper.queryGroupZhuZhuangLike(entity);
                operaCounts = operaCounts + operaCount6;

                entity.setOperaCount(operaCounts);

                arrayList.add(i,entity);

            }
            JSONArray arrayElementOneArrayElementOne = JSONArray.fromObject(arrayList);
            JSONObject arrayElementOne = new JSONObject();
            arrayElementOne.element("children",arrayElementOneArrayElementOne);
            array.add(arrayElementOne);
        }
        //object.element("nameList",array);
        //String resultString = object.toString();
        //System.out.println(resultString);
        map.put("nameList",array);
        return map;
    }

    //趋势分析，公司趋势，总查询
    @Override
    public Map employeeCompanyTrendAnalysis(BYDEntity bydEntity) {
        Map map = new HashMap();
        List<BYDEntity> employeeCompanyPieChartList = new ArrayList<>();

        //查询公司异常趋势饼状图
        //登录场景
        bydEntity.setTableName("scene_login");
        List<BYDEntity> list1 = bydMapper.employeeCompanyPieChart(bydEntity);
        if (list1.size() != 0) {
            if (null != list1.get(0)) {
                employeeCompanyPieChartList.addAll(list1);
            }
        }

        //查看文档场景
        bydEntity.setTableName("scene_mytc_viewDoc");
        List<BYDEntity> list2 = bydMapper.employeeCompanyPieChart(bydEntity);
        if (list2.size() != 0) {
            if (null != list2.get(0)) {
                employeeCompanyPieChartList.addAll(list2);
            }
        }
        //打开数据集场景
        bydEntity.setTableName("scene_mytc_open_dataset");
        List<BYDEntity> list3 = bydMapper.employeeCompanyPieChart(bydEntity);
        if (list3.size() != 0) {
            if (null != list3.get(0)) {
                employeeCompanyPieChartList.addAll(list3);
            }
        }
        //删除场景
        bydEntity.setTableName("scene_general_delete");
        List<BYDEntity> list4 = bydMapper.employeeCompanyPieChart(bydEntity);
        if (list4.size() != 0) {
            if (null != list4.get(0)) {
                employeeCompanyPieChartList.addAll(list4);
            }
        }
        //PLMXML导出
        bydEntity.setTableName("scene_mytc_export_PLMXML");
        List<BYDEntity> list5 = bydMapper.employeeCompanyPieChart(bydEntity);
        if (list5.size() != 0) {
            if (null != list5.get(0)) {
                employeeCompanyPieChartList.addAll(list5);
            }
        }
        //命名的引用下载
        bydEntity.setTableName("scene_mytc_namedRef_download");
        List<BYDEntity> list6 = bydMapper.employeeCompanyPieChart(bydEntity);
        if (list6.size() != 0) {
            if (null != list6.get(0)) {
                employeeCompanyPieChartList.addAll(list6);
            }
        }

        //将同company的operaCount相加
        employeeCompanyPieChartList= CalculateUtil.calCompanyOperaCount(employeeCompanyPieChartList);

        //个人饼状图
        map.put("employeeCompanyPieChartList",employeeCompanyPieChartList);

        double allCount = employeeCompanyPieChartList.stream().mapToInt(BYDEntity::getOperaCount).sum();
        map.put("allCount",allCount);

        //查询部门异常趋势柱状图
        // 查询employeeGroup集合
/*        List<String> employeeGroupList = getInfoSafeEmployeeGroup(bydEntity);
        //employeeNameList高效去重+排序
        if (employeeGroupList.size() != 0) {
            HashSet<String> hashSet02 = new HashSet<String>(employeeGroupList);
            employeeGroupList.clear();
            employeeGroupList.addAll(hashSet02);
            Collections.sort(employeeGroupList);
        }*/

        List<BYDEntity> daysList = DateUtil.getDays(bydEntity.getActionBeginTime(), bydEntity.getActionEndTime(), bydEntity.getCompany(), bydEntity.getEmployeeGroup(), bydEntity.getEmployeeName());

        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();

        for (BYDEntity bydEntityCompany : employeeCompanyPieChartList) {
            List<BYDEntity> arrayList = new ArrayList();
            for (int i = 0; i < daysList.size(); i++) {
                BYDEntity entity = daysList.get(i);
                int operaCounts = 0;
                entity.setCompany(bydEntityCompany.getCompany());

                //登录场景
                entity.setTableName("scene_login");
                int operaCount1 = bydMapper.queryCompanyZhuZhuangLike(entity);
                operaCounts = operaCounts + operaCount1;
                //查看文档场景
                entity.setTableName("scene_mytc_viewDoc");
                int operaCount2 = bydMapper.queryCompanyZhuZhuangLike(entity);
                operaCounts = operaCounts + operaCount2;
                //打开数据集场景
                entity.setTableName("scene_mytc_open_dataset");
                int operaCount3 = bydMapper.queryCompanyZhuZhuangLike(entity);
                operaCounts = operaCounts + operaCount3;
                //删除场景
                entity.setTableName("scene_general_delete");
                int operaCount4 = bydMapper.queryCompanyZhuZhuangLike(entity);
                operaCounts = operaCounts + operaCount4;
                //PLMXML导出
                entity.setTableName("scene_mytc_export_PLMXML");
                int operaCount5 = bydMapper.queryCompanyZhuZhuangLike(entity);
                operaCounts = operaCounts + operaCount5;
                //命名的引用下载
                entity.setTableName("scene_mytc_namedRef_download");
                int operaCount6 = bydMapper.queryCompanyZhuZhuangLike(entity);
                operaCounts = operaCounts + operaCount6;

                entity.setOperaCount(operaCounts);

                arrayList.add(i,entity);

            }
            JSONArray arrayElementOneArrayElementOne = JSONArray.fromObject(arrayList);
            JSONObject arrayElementOne = new JSONObject();
            arrayElementOne.element("children",arrayElementOneArrayElementOne);
            array.add(arrayElementOne);
        }
        //object.element("nameList",array);
        //String resultString = object.toString();
        //System.out.println(resultString);
        map.put("nameList",array);
        return map;
    }

    //查询信息安全模块对应的employeeName
    /*public List<String> getInfoSafeEmployeeName(BYDEntity bydEntity) {
        List<String> employeeNameList = new ArrayList();
        //登录场景
        bydEntity.setTableName("scene_login");
        List<String> list1 = bydMapper.getInfoSafeEmployeeName(bydEntity);
        if (list1.size() != 0) {
            if (null != list1.get(0)) {
                employeeNameList.addAll(list1);
            }
        }

        //查看文档场景
        bydEntity.setTableName("scene_mytc_viewDoc");
        List<String> list2 = bydMapper.getInfoSafeEmployeeName(bydEntity);
        if (list2.size() != 0) {
            if (null != list2.get(0)) {
                employeeNameList.addAll(list2);
            }
        }
        //打开数据集场景
        bydEntity.setTableName("scene_mytc_open_dataset");
        List<String> list3 = bydMapper.getInfoSafeEmployeeName(bydEntity);
        if (list3.size() != 0) {
            if (null != list3.get(0)) {
                employeeNameList.addAll(list3);
            }
        }
        //删除场景
        bydEntity.setTableName("scene_general_delete");
        List<String> list4 = bydMapper.getInfoSafeEmployeeName(bydEntity);
        if (list4.size() != 0) {
            if (null != list4.get(0)) {
                employeeNameList.addAll(list4);
            }
        }
        //PLMXML导出
        bydEntity.setTableName("scene_mytc_export_PLMXML");
        List<String> list5 = bydMapper.getInfoSafeEmployeeName(bydEntity);
        if (list5.size() != 0) {
            if (null != list5.get(0)) {
                employeeNameList.addAll(list5);
            }
        }
        //命名的引用下载
        bydEntity.setTableName("scene_mytc_namedRef_download");
        List<String> list6 = bydMapper.getInfoSafeEmployeeName(bydEntity);
        if (list6.size() != 0) {
            if (null != list6.get(0)) {
                employeeNameList.addAll(list6);
            }
        }

        return employeeNameList;
    }

    //查询信息安全模块对应的employeeGroup
    public List<String> getInfoSafeEmployeeGroup(BYDEntity bydEntity) {
        List<String> employeeGroupList = new ArrayList();
        //登录场景
        bydEntity.setTableName("scene_login");
        List<String> list1 = bydMapper.getInfoSafeEmployeeGroup(bydEntity);
        if (list1.size() != 0) {
            if (null != list1.get(0)) {
                employeeGroupList.addAll(list1);
            }
        }

        //查看文档场景
        bydEntity.setTableName("scene_mytc_viewDoc");
        List<String> list2 = bydMapper.getInfoSafeEmployeeGroup(bydEntity);
        if (list2.size() != 0) {
            if (null != list2.get(0)) {
                employeeGroupList.addAll(list2);
            }
        }
        //打开数据集场景
        bydEntity.setTableName("scene_mytc_open_dataset");
        List<String> list3 = bydMapper.getInfoSafeEmployeeGroup(bydEntity);
        if (list3.size() != 0) {
            if (null != list3.get(0)) {
                employeeGroupList.addAll(list3);
            }
        }
        //删除场景
        bydEntity.setTableName("scene_general_delete");
        List<String> list4 = bydMapper.getInfoSafeEmployeeGroup(bydEntity);
        if (list4.size() != 0) {
            if (null != list4.get(0)) {
                employeeGroupList.addAll(list4);
            }
        }
        //PLMXML导出
        bydEntity.setTableName("scene_mytc_export_PLMXML");
        List<String> list5 = bydMapper.getInfoSafeEmployeeGroup(bydEntity);
        if (list5.size() != 0) {
            if (null != list5.get(0)) {
                employeeGroupList.addAll(list5);
            }
        }
        //命名的引用下载
        bydEntity.setTableName("scene_mytc_namedRef_download");
        List<String> list6 = bydMapper.getInfoSafeEmployeeGroup(bydEntity);
        if (list6.size() != 0) {
            if (null != list6.get(0)) {
                employeeGroupList.addAll(list6);
            }
        }

        return employeeGroupList;
    }*/

    //异常登录
    @Override
    public Map getBYDPLMLoginInfo(BYDEntity bydEntity) {
        List<BYDEntity> resultList = bydMapper.getBYDPLMLoginInfo(bydEntity);

        for (BYDEntity entity:resultList) {
            entity.setOperationTime(millisecondToS(entity.getOperationTime()));
        }

        List tableTotalList = new ArrayList();
        if (bydEntity.getPagesSize() != 0 && bydEntity.getPagesNum() != 0) {
            //tableList分页处理，pagesSize和pagesNum不能为空
            PageModelUtil pm = new PageModelUtil(resultList, bydEntity.getPagesSize());//每页显示条数
            //总页数
            int totalPages = pm.getTotalPages();
            //总条数
            int totalRows = pm.getTotalRows();
            resultList = pm.getObjects(bydEntity.getPagesNum());

            tableTotalList.add(totalRows);
            tableTotalList.add(totalPages);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("tableList", resultList);
        resultMap.put("tableTotalList", tableTotalList);
        return resultMap;
    }

    //异常查看，场景：查看文档、打开数据集
    @Override
    public Map getViewDocInfo(BYDEntity bydEntity) {
        List<BYDEntity> queryList = new ArrayList<>();

        //查看文档
        bydEntity.setTableName("scene_mytc_viewDoc");
        List<BYDEntity> viewDocList = bydMapper.getViewDocInfo(bydEntity);
        if (viewDocList.size() != 0) {
            if (null != viewDocList.get(0)) {
                queryList.addAll(viewDocList);
            }
        }

        //打开数据集
        bydEntity.setTableName("scene_mytc_open_dataset");
        List<BYDEntity> openDatasetList = bydMapper.getViewDocInfo(bydEntity);
        if (openDatasetList.size() != 0) {
            if (null != openDatasetList.get(0)) {
                queryList.addAll(openDatasetList);
            }
        }

        List tableTotalList = new ArrayList();
        if (bydEntity.getPagesSize() != 0 && bydEntity.getPagesNum() != 0) {
            //tableList分页处理，pagesSize和pagesNum不能为空
            PageModelUtil pm = new PageModelUtil(queryList, bydEntity.getPagesSize());//每页显示条数
            //总页数
            int totalPages = pm.getTotalPages();
            //总条数
            int totalRows = pm.getTotalRows();
            queryList = pm.getObjects(bydEntity.getPagesNum());

            tableTotalList.add(totalRows);
            tableTotalList.add(totalPages);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("tableList", queryList);
        resultMap.put("tableTotalList", tableTotalList);
        return resultMap;
    }

    //异常下载，场景：PLMXML导出、命名的引用下载
    @Override
    public Map getDownloadDocInfo(BYDEntity bydEntity) {
        List<BYDEntity> queryList = new ArrayList<>();

        //PLMXML导出
        bydEntity.setTableName("scene_mytc_export_PLMXML");
        List<BYDEntity> viewDocList = bydMapper.getDownloadDocInfo(bydEntity);
        if (viewDocList.size() != 0) {
            if (null != viewDocList.get(0)) {
                queryList.addAll(viewDocList);
            }
        }

        //命名的引用下载
        bydEntity.setTableName("scene_mytc_namedRef_download");
        List<BYDEntity> openDatasetList = bydMapper.getDownloadDocInfo(bydEntity);
        if (openDatasetList.size() != 0) {
            if (null != openDatasetList.get(0)) {
                queryList.addAll(openDatasetList);
            }
        }

        List tableTotalList = new ArrayList();
        if (bydEntity.getPagesSize() != 0 && bydEntity.getPagesNum() != 0) {
            //tableList分页处理，pagesSize和pagesNum不能为空
            PageModelUtil pm = new PageModelUtil(queryList, bydEntity.getPagesSize());//每页显示条数
            //总页数
            int totalPages = pm.getTotalPages();
            //总条数
            int totalRows = pm.getTotalRows();
            queryList = pm.getObjects(bydEntity.getPagesNum());

            tableTotalList.add(totalRows);
            tableTotalList.add(totalPages);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("tableList", queryList);
        resultMap.put("tableTotalList", tableTotalList);
        return resultMap;
    }

    //异常删除，场景：删除
    @Override
    public Map getAbnormalDeleteInfo(BYDEntity bydEntity) {
        List<BYDEntity> queryList = new ArrayList<>();

        //删除
        bydEntity.setTableName("scene_general_delete");
        List<BYDEntity> viewDocList = bydMapper.getAbnormalDeleteInfo(bydEntity);
        if (viewDocList.size() != 0) {
            if (null != viewDocList.get(0)) {
                queryList.addAll(viewDocList);
            }
        }

        List tableTotalList = new ArrayList();
        if (bydEntity.getPagesSize() != 0 && bydEntity.getPagesNum() != 0) {
            //tableList分页处理，pagesSize和pagesNum不能为空
            PageModelUtil pm = new PageModelUtil(queryList, bydEntity.getPagesSize());//每页显示条数
            //总页数
            int totalPages = pm.getTotalPages();
            //总条数
            int totalRows = pm.getTotalRows();
            queryList = pm.getObjects(bydEntity.getPagesNum());

            tableTotalList.add(totalRows);
            tableTotalList.add(totalPages);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("tableList", queryList);
        resultMap.put("tableTotalList", tableTotalList);
        return resultMap;
    }
    /***************************************信息安全功能-end*****************************************/
    //获取文档下载场景的折线图数据
    @Override
    public Map getDownloadDocZheXianInfo(BYDEntity bydEntity) {
        List<BYDEntity> zheXianListAll = null;
        List<BYDEntity> zheXianListOverload = null;
        List<BYDEntity> zheXianListCrossDept = null;
        List<BYDEntity> zheXianListSecretFile = null;

        int zheXianFlag = bydEntity.getZheXianFlag();
        String zheXianDate = bydEntity.getZheXianDate();
        if (zheXianFlag == 1) {//按天
            //所有记录
            zheXianListAll = bydMapper.getDownloadDocZheXianInfoByHour(bydEntity);

            //异常记录:
            //超量下载=1
            bydEntity.setExceptionLoginFlag(1);
            bydMapper.getDownloadDocZheXianInfoByHour(bydEntity);
            zheXianListOverload = bydMapper.getDownloadDocZheXianInfoByHour(bydEntity);

            //跨部门下载=2
            bydEntity.setExceptionLoginFlag(2);
            bydMapper.getDownloadDocZheXianInfoByHour(bydEntity);
            zheXianListCrossDept = bydMapper.getDownloadDocZheXianInfoByHour(bydEntity);

            //机密文件下载=3
            bydEntity.setExceptionLoginFlag(3);
            bydMapper.getDownloadDocZheXianInfoByHour(bydEntity);
            zheXianListSecretFile = bydMapper.getDownloadDocZheXianInfoByHour(bydEntity);

        } else if (zheXianFlag == 2) {//按星期
            //所有记录
            bydEntity.setZheXianDate(DateUtil.calculateSundayDate(bydEntity.getZheXianDate()));
            zheXianListAll = bydMapper.getDownloadDocZheXianInfoByWeek(bydEntity);
            //对日期进行排序
            Collections.sort(zheXianListAll, new Comparator<BYDEntity>() {
                @Override
                public int compare(BYDEntity o1, BYDEntity o2) {
                    //升序
                    return o1.getZheXianX().compareTo(o2.getZheXianX());
                }
            });

            //异常记录:
            //超量下载=1
            bydEntity.setExceptionLoginFlag(1);
            bydMapper.getDownloadDocZheXianInfoByHour(bydEntity);
            zheXianListOverload = bydMapper.getDownloadDocZheXianInfoByWeek(bydEntity);

            Collections.sort(zheXianListOverload, new Comparator<BYDEntity>() {
                @Override
                public int compare(BYDEntity o1, BYDEntity o2) {
                    //升序
                    return o1.getZheXianX().compareTo(o2.getZheXianX());
                }
            });

            //跨部门下载=2
            bydEntity.setExceptionLoginFlag(2);
            bydMapper.getDownloadDocZheXianInfoByHour(bydEntity);
            zheXianListCrossDept = bydMapper.getDownloadDocZheXianInfoByWeek(bydEntity);

            Collections.sort(zheXianListCrossDept, new Comparator<BYDEntity>() {
                @Override
                public int compare(BYDEntity o1, BYDEntity o2) {
                    //升序
                    return o1.getZheXianX().compareTo(o2.getZheXianX());
                }
            });

            //机密文件下载=3
            bydEntity.setExceptionLoginFlag(3);
            bydMapper.getDownloadDocZheXianInfoByHour(bydEntity);
            zheXianListSecretFile = bydMapper.getDownloadDocZheXianInfoByWeek(bydEntity);

            Collections.sort(zheXianListSecretFile, new Comparator<BYDEntity>() {
                @Override
                public int compare(BYDEntity o1, BYDEntity o2) {
                    //升序
                    return o1.getZheXianX().compareTo(o2.getZheXianX());
                }
            });

        } else if (zheXianFlag == 3) {//按月
            String firstMonthDay = DateUtil.getFirstMonthDay(zheXianDate);
            bydEntity.setZheXianDate(firstMonthDay);

            //所有记录
            zheXianListAll = bydMapper.getDownloadDocZheXianInfoByMonth(bydEntity);
            zheXianListAll.remove(zheXianListAll.size() - 1);

            //异常记录:
            //超量下载=1
            bydEntity.setExceptionLoginFlag(1);
            bydMapper.getDownloadDocZheXianInfoByHour(bydEntity);
            zheXianListOverload = bydMapper.getDownloadDocZheXianInfoByMonth(bydEntity);
            zheXianListOverload.remove(zheXianListOverload.size() - 1);

            //跨部门下载=2
            bydEntity.setExceptionLoginFlag(2);
            bydMapper.getDownloadDocZheXianInfoByHour(bydEntity);
            zheXianListCrossDept = bydMapper.getDownloadDocZheXianInfoByMonth(bydEntity);
            zheXianListCrossDept.remove(zheXianListCrossDept.size() - 1);

            //机密文件下载=3
            bydEntity.setExceptionLoginFlag(3);
            bydMapper.getDownloadDocZheXianInfoByHour(bydEntity);
            zheXianListSecretFile = bydMapper.getDownloadDocZheXianInfoByMonth(bydEntity);
            zheXianListSecretFile.remove(zheXianListSecretFile.size() - 1);

        }
        HashMap resultMap = new HashMap();
        resultMap.put("zheXianListAll", zheXianListAll);
        resultMap.put("zheXianListOverload", zheXianListOverload);
        resultMap.put("zheXianListCrossDept", zheXianListCrossDept);
        resultMap.put("zheXianListSecretFile", zheXianListSecretFile);
        return resultMap;
    }

    //获取文档查看场景的折线图数据
    @Override
    public Map getViewDocZheXianInfo(BYDEntity bydEntity) {
        List<BYDEntity> zheXianListAll = null;
        List<BYDEntity> zheXianListCrossDept = null;
        List<BYDEntity> zheXianListSecretFile = null;

        int zheXianFlag = bydEntity.getZheXianFlag();
        String zheXianDate = bydEntity.getZheXianDate();
        if (zheXianFlag == 1) {//按天
            //所有记录
            zheXianListAll = bydMapper.getViewDocZheXianInfoByHour(bydEntity);

            //异常记录:
            //跨部门下载=1
            bydEntity.setExceptionLoginFlag(1);
            bydMapper.getDownloadDocZheXianInfoByHour(bydEntity);
            zheXianListCrossDept = bydMapper.getViewDocZheXianInfoByHour(bydEntity);

            //机密文件下载=2
            bydEntity.setExceptionLoginFlag(2);
            bydMapper.getDownloadDocZheXianInfoByHour(bydEntity);
            zheXianListSecretFile = bydMapper.getViewDocZheXianInfoByHour(bydEntity);

        } else if (zheXianFlag == 2) {//按星期
            //所有记录
            bydEntity.setZheXianDate(DateUtil.calculateSundayDate(bydEntity.getZheXianDate()));
            zheXianListAll = bydMapper.getViewDocZheXianInfoByWeek(bydEntity);
            //对日期进行排序
            Collections.sort(zheXianListAll, new Comparator<BYDEntity>() {
                @Override
                public int compare(BYDEntity o1, BYDEntity o2) {
                    //升序
                    return o1.getZheXianX().compareTo(o2.getZheXianX());
                }
            });

            //异常记录:
            //跨部门下载=1
            bydEntity.setExceptionLoginFlag(1);
            bydMapper.getDownloadDocZheXianInfoByHour(bydEntity);
            zheXianListCrossDept = bydMapper.getViewDocZheXianInfoByWeek(bydEntity);

            Collections.sort(zheXianListCrossDept, new Comparator<BYDEntity>() {
                @Override
                public int compare(BYDEntity o1, BYDEntity o2) {
                    //升序
                    return o1.getZheXianX().compareTo(o2.getZheXianX());
                }
            });

            //机密文件下载=2
            bydEntity.setExceptionLoginFlag(2);
            bydMapper.getDownloadDocZheXianInfoByHour(bydEntity);
            zheXianListSecretFile = bydMapper.getViewDocZheXianInfoByWeek(bydEntity);

            Collections.sort(zheXianListSecretFile, new Comparator<BYDEntity>() {
                @Override
                public int compare(BYDEntity o1, BYDEntity o2) {
                    //升序
                    return o1.getZheXianX().compareTo(o2.getZheXianX());
                }
            });

        } else if (zheXianFlag == 3) {//按月
            String firstMonthDay = DateUtil.getFirstMonthDay(zheXianDate);
            bydEntity.setZheXianDate(firstMonthDay);

            //所有记录
            zheXianListAll = bydMapper.getViewDocZheXianInfoByMonth(bydEntity);
            zheXianListAll.remove(zheXianListAll.size() - 1);

            //异常记录:
            //跨部门下载=1
            bydEntity.setExceptionLoginFlag(1);
            bydMapper.getDownloadDocZheXianInfoByHour(bydEntity);
            zheXianListCrossDept = bydMapper.getViewDocZheXianInfoByMonth(bydEntity);
            zheXianListCrossDept.remove(zheXianListCrossDept.size() - 1);

            //机密文件下载=2
            bydEntity.setExceptionLoginFlag(2);
            bydMapper.getDownloadDocZheXianInfoByHour(bydEntity);
            zheXianListSecretFile = bydMapper.getViewDocZheXianInfoByMonth(bydEntity);
            zheXianListSecretFile.remove(zheXianListSecretFile.size() - 1);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("zheXianListAll", zheXianListAll);
        resultMap.put("zheXianListCrossDept", zheXianListCrossDept);
        resultMap.put("zheXianListSecretFile", zheXianListSecretFile);
        return resultMap;
    }

    @Override
    public Map fuzzyQueryWorkingHostName(BYDEntity bydEntity) {
        List<String> workingHostNameList = bydMapper.fuzzyQueryWorkingHostName(bydEntity);

        HashMap resultMap = new HashMap();
        resultMap.put("workingHostNameList", workingHostNameList);
        return resultMap;
    }

    //查询KPI表格信息
    @Override
    public Map queryKPITableInfo(BYDEntity bydEntity) {
        String actionBeginTime = bydEntity.getActionBeginTime();
        String actionEndTime = bydEntity.getActionEndTime();
        //首先获取数据库所有姓名+公司+部门+hostname
        List<BYDEntity> nameCompanyGroupList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                List<BYDEntity> entityList = bydMapper.queryComGroupName(bydEntity);
                if (entityList.size() != 0) {
                    if (null != entityList.get(0)) {
                        nameCompanyGroupList.addAll(entityList);
                    }
                }
            }
        }

        //list根据employee_name、host_name
        nameCompanyGroupList = nameCompanyGroupList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getEmployeeName() + ";" + o.getHostName()))), ArrayList::new));

        //查询场景次数+工时
        for (BYDEntity entity : nameCompanyGroupList) {
            entity.setActionBeginTime(actionBeginTime);
            entity.setActionEndTime(actionEndTime);
            int operaCounts = 0;
            double workingTimes = 0;
            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    entity.setTableName(sceneTableName);
                    //场景次数
                    int operaCount= bydMapper.queryOperaCount(entity);
                    operaCounts = operaCounts + operaCount;
/*                    if(operaCount>0){
                    //工时
                    double workingTime = bydMapper.queryWorkingTime(entity);
                    workingTimes = workingTimes + workingTime;
                    }*/
                }
            }
            //场景次数
            entity.setOperaCount(operaCounts);

            //加分：工时，当前区间
            double workingTime01 = bydMapper.queryWorkingTime(entity);
            entity.setWorkingHours(millisecondToSecond(workingTime01));

            //产出文档,暂时写死
            entity.setOutputDocument(10);
            //文档引用,暂时写死
            entity.setDocumentReference(9);
            //信息安全违规,暂时写死
            entity.setInfoSafeViolation(1);
            //不规范操作,暂时写死
            entity.setIrregularOperation(2);

            //项目加权,暂时写死
            entity.setProjectWeighting(1);

            //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
            double a = (entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference()-
                    entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();
            //KPI保留两位小数
            BigDecimal operationAvgTimeBg = new BigDecimal(a);
            entity.setKPI(operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            if(entity.getEmployeeName() == null){
                entity.setEmployeeName("");
            }

            //用于统计KPI
            entity.setRecentInfo(entity.getEmployeeName()+","+entity.getEmployeeGroup()+","+entity.getCompany());
        }

        //KPI表格，计算相同employeeName+emplyeeGroup+Company的总KPI和workingHours
        List<BYDEntity> kpiEntityList = CalculateUtil.calListSameKPI(nameCompanyGroupList);

        //分页
        List tableTotalList = new ArrayList();
        if (bydEntity.getPagesSize() != 0 && bydEntity.getPagesNum() != 0) {
            //tableList分页处理，pagesSize和pagesNum不能为空
            PageModelUtil pm = new PageModelUtil(nameCompanyGroupList, bydEntity.getPagesSize());//每页显示条数
            //总页数
            int totalPages = pm.getTotalPages();
            //总条数
            int totalRows = pm.getTotalRows();
            nameCompanyGroupList = pm.getObjects(bydEntity.getPagesNum());

            tableTotalList.add(totalRows);
            tableTotalList.add(totalPages);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("kpiEntityList", kpiEntityList);
        resultMap.put("tableTotalList", tableTotalList);
        return resultMap;
    }

    //员工KPI折线图
    @Override
    public Map queryEmployeeKPIZheXianInfo(BYDEntity bydEntity) {
        List<BYDEntity> dateEntityList = DateUtil.getDays(bydEntity.getActionBeginTime(), bydEntity.getActionEndTime(),bydEntity.getCompany(),bydEntity.getEmployeeGroup(),bydEntity.getEmployeeName());
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        //
        List<BYDEntity> nameCompanyGroupList = new ArrayList<>();
        for (BYDEntity entitys:dateEntityList ) {
            //首先获取数据库所有姓名+公司+部门+hostname
            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    entitys.setTableName(sceneTableName);
                    List<BYDEntity> entityList = bydMapper.queryComGroupNameLike(entitys);

                    if (entityList.size() != 0) {
                        if (null != entityList.get(0)) {
/*                            for (BYDEntity entity:entityList) {
                                entity.setZheXianX(entitys.getZheXianX());
                            }*/
                            nameCompanyGroupList.addAll(entityList);
                        }
                    }
                }
            }
        }

        //list根据employee_name、host_name去重
        nameCompanyGroupList = nameCompanyGroupList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getEmployeeName() + ";" + o.getHostName()))), ArrayList::new));

        //查询场景次数+工时
        for (BYDEntity entitys:dateEntityList ) {
            double tempKPI = 0;
            double tempKPIjiafen = 0;
            double tempKPIjianfen = 0;
                for (BYDEntity entity : nameCompanyGroupList) {
                    double workingTimes = 0;
                    int operaCounts = 0;
                    for (SceneListEntity scene : sceneList) {
                        String sceneTableName = scene.getSceneTableName();
                        if (StringUtils.isNotEmpty(sceneTableName)) {
                            entity.setTableName(sceneTableName);
                            entity.setZheXianX(entitys.getZheXianX());
                            //鍦烘櫙娆℃暟
                            int operaCount = bydMapper.queryOperaCountLike(entity);
                            operaCounts = operaCounts + operaCount;
/*                        if(operaCount>0){
                            //宸ユ椂
                            double workingTime = bydMapper.queryWorkingTimeLike(entity);
                            workingTimes = workingTimes + workingTime;
                        }*/
                        }
                    }

                    //加分：工时，当前区间
                    double workingTime01 = bydMapper.queryWorkingTimeLike(entity);
                    //若工时为0则不计算KPI
                    if ((workingTime01 < 0.0001) && (workingTime01 > -0.0001)) {
                        entity.setKPI(0);
                        entity.setKPIjiafen(0);
                        entity.setKPIjianfen(0);
                    } else {
                        //加分：场景次数
                        entity.setOperaCount(operaCounts);

                        entity.setWorkingHours(millisecondToSecond(workingTime01));

                        //产出文档
                        entity.setOutputDocument(10);
                        //文档引用
                        entity.setDocumentReference(9);
                        //信息安全违规
                        entity.setInfoSafeViolation(1);
                        //不规范操作
                        entity.setIrregularOperation(2);

                        //项目加权
                        entity.setProjectWeighting(1);

                        //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
                        double a = (entity.getWorkingHours() + entity.getOperaCount() + entity.getOutputDocument() + entity.getDocumentReference() -
                                entity.getInfoSafeViolation() - entity.getIrregularOperation()) * entity.getProjectWeighting();

                        //KPI保留两位小数
                        BigDecimal operationAvgTimeBg = new BigDecimal(a);
                        entity.setKPI(operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                        //计算加分KPI
                        double jiafenKPI = entity.getWorkingHours() + entity.getOperaCount() + entity.getOutputDocument() + entity.getDocumentReference();
                        //KPI保留两位小数
                        BigDecimal operationAvgTimeBg02 = new BigDecimal(jiafenKPI);
                        entity.setKPIjiafen(operationAvgTimeBg02.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                        //计算减分KPI
                        double jianfenKPI = entity.getInfoSafeViolation() + entity.getIrregularOperation();
                        BigDecimal operationAvgTimeBg03 = new BigDecimal(jianfenKPI);
                        entity.setKPIjianfen(operationAvgTimeBg03.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                        if (entity.getEmployeeName() == null) {
                            entity.setEmployeeName("");
                        }

                        //计算平均KPI
                        if (bydEntity.getAvgKPIFlag() == 1) {
                            if (operaCounts != 0) {
                                BigDecimal operationAvgTimeBg04 = new BigDecimal(entity.getKPI() / operaCounts);
                                entity.setKPI(operationAvgTimeBg04.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                                BigDecimal operationAvgTimeBg05 = new BigDecimal(entity.getKPIjiafen() / operaCounts);
                                entity.setKPIjiafen(operationAvgTimeBg05.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                                BigDecimal operationAvgTimeBg06 = new BigDecimal(entity.getKPIjianfen() / operaCounts);
                                entity.setKPIjianfen(operationAvgTimeBg06.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                            }
                        }

                        tempKPI = tempKPI + entity.getKPI();
                        tempKPIjiafen = tempKPIjiafen + entity.getKPIjiafen();
                        tempKPIjianfen = tempKPIjianfen + entity.getKPIjianfen();
                }
            }
            BigDecimal operationAvgTimeBg01 = new BigDecimal(tempKPI);
            entitys.setKPI(operationAvgTimeBg01.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            BigDecimal operationAvgTimeBg02 = new BigDecimal(tempKPIjiafen);
            entitys.setKPIjiafen(operationAvgTimeBg02.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            BigDecimal operationAvgTimeBg03 = new BigDecimal(tempKPIjianfen);
            entitys.setKPIjianfen(operationAvgTimeBg03.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
 /*       for (BYDEntity entity : nameCompanyGroupList) {
            double workingTimes = 0;
            int operaCounts = 0;
            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    entity.setTableName(sceneTableName);
                    //场景次数
                    int operaCount= bydMapper.queryOperaCountLike(entity);
                    operaCounts = operaCounts + operaCount;
*//*                    if(operaCount>0){
                        //工时
                        double workingTime = bydMapper.queryWorkingTimeLike(entity);
                        workingTimes = workingTimes + workingTime;
                    }*//*
                }
            }
            //场景次数
            entity.setOperaCount(operaCounts);

            //加分：工时，当前区间
            double workingTime01 = bydMapper.queryWorkingTimeLike(entity);
            entity.setWorkingHours(millisecondToSecond(workingTime01));

            //工时
            entity.setWorkingHours(millisecondToSecond(workingTimes));
            //产出文档,暂时写死
            entity.setOutputDocument(10);
            //文档引用,暂时写死
            entity.setDocumentReference(9);
            //信息安全违规,暂时写死
            entity.setInfoSafeViolation(1);
            //不规范操作,暂时写死
            entity.setIrregularOperation(2);

            //项目加权,暂时写死
            entity.setProjectWeighting(1);

            //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
            double a = (entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference()-
                    entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg = new BigDecimal(a);
            entity.setKPI(operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算加分KPI
            double jiafenKPI = entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference();
            //KPI保留两位小数
            BigDecimal operationAvgTimeBg02 = new BigDecimal(jiafenKPI);
            entity.setKPIjiafen(operationAvgTimeBg02.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算减分KPI
            double jianfenKPI = entity.getInfoSafeViolation()+entity.getIrregularOperation();
            BigDecimal operationAvgTimeBg03 = new BigDecimal(jianfenKPI);
            entity.setKPIjianfen(operationAvgTimeBg03.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            if(entity.getEmployeeName() == null){
                entity.setEmployeeName("");
            }

            //计算平均KPI
            if(bydEntity.getAvgKPIFlag() == 1){
                if(operaCounts != 0){
                    BigDecimal operationAvgTimeBg04 = new BigDecimal(entity.getKPI()/operaCounts);
                    entity.setKPI(operationAvgTimeBg04.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                    BigDecimal operationAvgTimeBg05 = new BigDecimal(entity.getKPIjiafen()/operaCounts);
                    entity.setKPIjiafen(operationAvgTimeBg05.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

                    BigDecimal operationAvgTimeBg06 = new BigDecimal(entity.getKPIjianfen()/operaCounts);
                    entity.setKPIjianfen(operationAvgTimeBg06.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
            }
        }*/

        //List<BYDEntity> finalList = CalculateUtil.toBusinessKPI(nameCompanyGroupList);

        HashMap resultMap = new HashMap();
        resultMap.put("dateEntityList", dateEntityList);
        return resultMap;
    }

    //员工KPI排名,总查询
    @Override
    public Map queryEmployeeRankingAll(BYDEntity bydEntity) {
        //计算查询日期排名
        String actionBeginTime = bydEntity.getActionBeginTime();
        String actionEndTime = bydEntity.getActionEndTime();

        String[] selectField = bydEntity.getSelectField();

        //首先获取数据库所有姓名+公司+部门+hostname
        List<BYDEntity> nameCompanyGroupList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseMapper.getAllScene();

        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                if(selectField.length > 0){
                    for (int i = 0; i < selectField.length; i++) {
                        String employeeName = selectField[i];
                        bydEntity.setEmployeeName(employeeName);
                        List<BYDEntity> entityList = bydMapper.queryComGroupName(bydEntity);
                        if (entityList.size() != 0) {
                            if (null != entityList.get(0)) {
                                nameCompanyGroupList.addAll(entityList);
                            }
                        }
                    }
                }else{
                    List<BYDEntity> entityList = bydMapper.queryComGroupName(bydEntity);
                    if (entityList.size() != 0) {
                        if (null != entityList.get(0)) {
                            nameCompanyGroupList.addAll(entityList);
                        }
                    }
                }
            }
        }

        //list根据employee_name、host_name
        nameCompanyGroupList = nameCompanyGroupList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getEmployeeName() + ";" + o.getHostName()))), ArrayList::new));

        //计算查询日期前一段时间的排名，用于计算环比
        int i = DateUtil.calDays(actionBeginTime, actionEndTime);
        String actionBeginTime01 = DateUtil.calSomeDate(actionBeginTime, i);

        //查询场景次数+工时
        for (BYDEntity entity : nameCompanyGroupList) {
            //entity.setActionBeginTime(actionBeginTime);
            //entity.setActionEndTime(actionEndTime);
            int operaCounts = 0;
            double workingTimes = 0;

            int operaCounts01 = 0;
            double workingTimes01 = 0;
            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    //查询当前区间的数据
                    entity.setActionBeginTime(actionBeginTime);
                    entity.setActionEndTime(actionEndTime);
                    entity.setTableName(sceneTableName);
                    //场景次数
                    int operaCount= bydMapper.queryOperaCount(entity);
                    operaCounts = operaCounts + operaCount;
/*                    if(operaCount>0){
                        //工时
                        double workingTime = bydMapper.queryWorkingTime(entity);
                        workingTimes = workingTimes + workingTime;
                    }*/
                    //

                    //计算前一段区间的数据，用于计算环比
                    entity.setActionBeginTime(actionBeginTime01);
                    entity.setActionEndTime(actionBeginTime);
                    //场景次数
                    int operaCount01= bydMapper.queryOperaCount(entity);
                    operaCounts01 = operaCounts01 + operaCount01;
/*                    if(operaCount01>0){
                        //工时
                        double workingTime01 = bydMapper.queryWorkingTime(entity);
                        workingTimes01 = workingTimes01 + workingTime01;
                    }*/
                    //
                }
            }
            //
            //加分：场景次数
            entity.setOperaCount(operaCounts);

            //加分：工时，当前区间
            entity.setActionBeginTime(actionBeginTime);
            entity.setActionEndTime(actionEndTime);
            double workingTime01 = bydMapper.queryWorkingTime(entity);
            entity.setWorkingHours(millisecondToSecond(workingTime01));

            //加分：产出文档,暂时写死
            entity.setOutputDocument(10);
            //加分：文档引用,暂时写死
            entity.setDocumentReference(9);
            //减分：信息安全违规,暂时写死
            entity.setInfoSafeViolation(1);
            //减分：不规范操作,暂时写死
            entity.setIrregularOperation(2);
            //项目加权,暂时写死
            entity.setProjectWeighting(1);

            //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
            double allKPI = (entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference()-
                    entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg = new BigDecimal(allKPI);
            entity.setKPI(operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算KPICal用于计算环比
            //加分：场景次数
            int operaCount01 = operaCounts01;

            //加分：工时，上个区间
            entity.setActionBeginTime(actionBeginTime01);
            entity.setActionEndTime(actionBeginTime);
            double workingTime02 = bydMapper.queryWorkingTime(entity);
            double workingHour01 = millisecondToSecond(workingTime02);

            //加分：产出文档,暂时写死
            int oupPutDoc01 = 10;
            //加分：文档引用,暂时写死
            int documentReference01 = 9;
            //减分：信息安全违规,暂时写死
            int infoSafeViolation01 = 1;
            //减分：不规范操作,暂时写死
            int irregularOperation01 = 2;
            //项目加权,暂时写死
            int projectWeighting01 = 1;

            double KPICal = (operaCount01+workingHour01+documentReference01+oupPutDoc01-infoSafeViolation01-irregularOperation01)*projectWeighting01;

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg01 = new BigDecimal(KPICal);
            entity.setKPICal(operationAvgTimeBg01.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算加分KPI
            double jiafenKPI = entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference();
            //KPI保留两位小数
            BigDecimal operationAvgTimeBg02 = new BigDecimal(jiafenKPI);
            entity.setKPIjiafen(operationAvgTimeBg02.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            //计算减分KPI
            double jianfenKPI = entity.getInfoSafeViolation()+entity.getIrregularOperation();
            BigDecimal operationAvgTimeBg03 = new BigDecimal(jianfenKPI);
            entity.setKPIjianfen(operationAvgTimeBg03.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            if(entity.getEmployeeName() == null){
                entity.setEmployeeName("");
            }
        }

        //相同employee_name去重+计算KPI+计算KPICal+计算KPIjiafen+计算KPIjianfen
        List<BYDEntity> kpiEntityList = CalculateUtil.toBusinessNameHeavyAndScore(nameCompanyGroupList);

        //计算环比
        for (BYDEntity entity : kpiEntityList) {
            double KPI = entity.getKPI();
            double KPICal = entity.getKPICal();
            if((KPICal<0.0001)&&(KPICal>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else if((KPI<0.0001)&&(KPI>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else{
                String linkRatio = CalculateUtil.calLinkRatio(KPI, KPICal);
                entity.setKPILinkRatio(linkRatio);
            }

        }

        //计算加分环比
        for (BYDEntity entity : kpiEntityList) {
            double jiafenKPI = entity.getKPIjiafen();
            double KPICal = entity.getKPICal();
            if((KPICal<0.0001)&&(KPICal>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else if((jiafenKPI<0.0001)&&(jiafenKPI>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else{
                String linkRatio = CalculateUtil.calLinkRatio(jiafenKPI, KPICal);
                entity.setKPILinkRatioJIAFEN(linkRatio);
            }
        }

        //计算减分环比
        for (BYDEntity entity : kpiEntityList) {
            double jianfenKPI = entity.getKPIjianfen();
            double KPICal = entity.getKPICal();
            if((KPICal<0.0001)&&(KPICal>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else if((jianfenKPI<0.0001)&&(jianfenKPI>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else{
                String linkRatio = CalculateUtil.calLinkRatio(jianfenKPI, KPICal);
                entity.setKPILinkRatioJIANFEN(linkRatio);
            }
        }

        //对KPI进行降序排列
        Collections.sort(kpiEntityList, new Comparator<BYDEntity>() {
            @Override
            public int compare(BYDEntity o1, BYDEntity o2) {
                if (o1.getKPI() > o2.getKPI()) {
                    return -1;
                }
                if (o1.getKPI() == o2.getKPI()) {
                    return 0;
                }
                return 1;
            }
        });

        HashMap resultMap = new HashMap();

        //分页前计算总KPI
        double allKPI = kpiEntityList.stream().mapToDouble(BYDEntity::getKPI).sum();
        //KPI保留两位小数
        BigDecimal operationAvgTimeBg = new BigDecimal(allKPI);
        allKPI = operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        //员工KPI贡献，分页前list
        resultMap.put("contributionList", kpiEntityList);

        //分页
        List tableTotalList = new ArrayList();
        if (bydEntity.getPagesSize() != 0 && bydEntity.getPagesNum() != 0) {
            //tableList分页处理，pagesSize和pagesNum不能为空
            PageModelUtil pm = new PageModelUtil(kpiEntityList, bydEntity.getPagesSize());//每页显示条数
            //总页数
            int totalPages = pm.getTotalPages();
            //总条数
            int totalRows = pm.getTotalRows();
            kpiEntityList = pm.getObjects(bydEntity.getPagesNum());

            tableTotalList.add(totalRows);
            tableTotalList.add(totalPages);
        }

        resultMap.put("tableTotalList", tableTotalList);
        resultMap.put("kpiRankingList", kpiEntityList);
        resultMap.put("allKPI", allKPI);
        return resultMap;
    }

    //1.员工KPI贡献
    @Override
    public Map queryEmployeeKPIContribution(BYDEntity bydEntity) {
        String actionBeginTime = bydEntity.getActionBeginTime();
        String actionEndTime = bydEntity.getActionEndTime();

        //首先获取数据库所有姓名+公司+部门+hostname
        List<BYDEntity> nameCompanyGroupList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                List<BYDEntity> entityList = bydMapper.queryComGroupName(bydEntity);
                if (entityList.size() != 0) {
                    if (null != entityList.get(0)) {
                        nameCompanyGroupList.addAll(entityList);
                    }
                }
            }
        }

        //list根据employee_name、host_name
        nameCompanyGroupList = nameCompanyGroupList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getEmployeeName() + ";" + o.getHostName()))), ArrayList::new));

        //查询场景次数+工时
        for (BYDEntity entity : nameCompanyGroupList) {
            //entity.setActionBeginTime(actionBeginTime);
            //entity.setActionEndTime(actionEndTime);
            int operaCounts = 0;
            double workingTimes = 0;

            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    //查询当前区间的数据
                    entity.setActionBeginTime(actionBeginTime);
                    entity.setActionEndTime(actionEndTime);
                    entity.setTableName(sceneTableName);
                    //场景次数
                    int operaCount= bydMapper.queryOperaCount(entity);
                    operaCounts = operaCounts + operaCount;
/*                    if(operaCount>0){
                        //工时
                        double workingTime = bydMapper.queryWorkingTime(entity);
                        workingTimes = workingTimes + workingTime;
                    }*/
                }
            }
            //加分：场景次数
            entity.setOperaCount(operaCounts);

            //加分：工时，当前区间
            entity.setActionBeginTime(actionBeginTime);
            entity.setActionEndTime(actionEndTime);
            double workingTime01 = bydMapper.queryWorkingTime(entity);
            entity.setWorkingHours(millisecondToSecond(workingTime01));

            //加分：产出文档,暂时写死
            entity.setOutputDocument(10);
            //加分：文档引用,暂时写死
            entity.setDocumentReference(9);
            //减分：信息安全违规,暂时写死
            entity.setInfoSafeViolation(1);
            //减分：不规范操作,暂时写死
            entity.setIrregularOperation(2);
            //项目加权,暂时写死
            entity.setProjectWeighting(1);

            double allKPI = 0.0;
            if(bydEntity.getKpiContributionFlag() == 1){//净贡献
                //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
                allKPI = (entity.getOperaCount()+entity.getWorkingHours()+entity.getDocumentReference()+entity.getOutputDocument()-entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();
            }else if(bydEntity.getKpiContributionFlag() == 2){//加分贡献
                allKPI = (entity.getOperaCount()+entity.getWorkingHours()+entity.getDocumentReference()+entity.getOutputDocument())*entity.getProjectWeighting();
            }else if(bydEntity.getKpiContributionFlag() == 3){//减分贡献
                allKPI = (entity.getInfoSafeViolation()+entity.getIrregularOperation())*entity.getProjectWeighting();
            }

            //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
            //double allKPI = (entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference()-
            //       entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg = new BigDecimal(allKPI);
            entity.setKPI(operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            if(entity.getEmployeeName() == null){
                entity.setEmployeeName("");
            }
        }

        //相同employee_name去重+计算KPI+计算KPICal+计算KPIjiafen+计算KPIjianfen
        List<BYDEntity> kpiEntityList = CalculateUtil.toBusinessNameHeavyAndScore(nameCompanyGroupList);

        //对KPI进行降序排列
        Collections.sort(kpiEntityList, new Comparator<BYDEntity>() {
            @Override
            public int compare(BYDEntity o1, BYDEntity o2) {
                if (o1.getKPI() > o2.getKPI()) {
                    return -1;
                }
                if (o1.getKPI() == o2.getKPI()) {
                    return 0;
                }
                return 1;
            }
        });

        //计算总KPI
        double allKPI = kpiEntityList.stream().mapToDouble(BYDEntity::getKPI).sum();

        //KPI保留两位小数
        BigDecimal operationAvgTimeBg = new BigDecimal(allKPI);
        allKPI = operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        HashMap resultMap = new HashMap();
        resultMap.put("contributionList", kpiEntityList);
        resultMap.put("allKPI", allKPI);
        return resultMap;
    }

    //2.员工KPI排名
    @Override
    public Map queryEmployeeKPIRanking(BYDEntity bydEntity) {
        //计算查询日期排名
        String actionBeginTime = bydEntity.getActionBeginTime();
        String actionEndTime = bydEntity.getActionEndTime();

        //首先获取数据库所有姓名+公司+部门+hostname
        List<BYDEntity> nameCompanyGroupList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                List<BYDEntity> entityList = bydMapper.queryComGroupName(bydEntity);
                if (entityList.size() != 0) {
                    if (null != entityList.get(0)) {
                        nameCompanyGroupList.addAll(entityList);
                    }
                }
            }
        }

        //list根据employee_name、host_name去重
        nameCompanyGroupList = nameCompanyGroupList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getEmployeeName() + ";" + o.getHostName()))), ArrayList::new));

        //计算查询日期前一段时间的排名，用于计算环比
        int i = DateUtil.calDays(actionBeginTime, actionEndTime);
        String actionBeginTime01 = DateUtil.calSomeDate(actionBeginTime, i);

        //查询场景次数+工时
        for (BYDEntity entity : nameCompanyGroupList) {
            //entity.setActionBeginTime(actionBeginTime);
            //entity.setActionEndTime(actionEndTime);
            int operaCounts = 0;
            double workingTimes = 0;

            int operaCounts01 = 0;
            double workingTimes01 = 0;
            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    //查询当前区间的数据
                    entity.setActionBeginTime(actionBeginTime);
                    entity.setActionEndTime(actionEndTime);
                    entity.setTableName(sceneTableName);
                    //场景次数
                    int operaCount= bydMapper.queryOperaCount(entity);
                    operaCounts = operaCounts + operaCount;
/*                    if(operaCount>0){
                        //工时
                        double workingTime = bydMapper.queryWorkingTime(entity);
                        workingTimes = workingTimes + workingTime;
                    }*/
                    //

                    //计算前一段区间的数据，用于计算环比
                    entity.setActionBeginTime(actionBeginTime01);
                    entity.setActionEndTime(actionBeginTime);
                    //场景次数
                    int operaCount01= bydMapper.queryOperaCount(entity);
                    operaCounts01 = operaCounts01 + operaCount01;
/*                    if(operaCount01>0){
                        //工时
                        double workingTime01 = bydMapper.queryWorkingTime(entity);
                        workingTimes01 = workingTimes01 + workingTime01;
                    }*/
                    //
                }
            }
            //
            //加分：场景次数
            entity.setOperaCount(operaCounts);

            //加分：工时，当前区间
            entity.setActionBeginTime(actionBeginTime);
            entity.setActionEndTime(actionEndTime);
            double workingTime01 = bydMapper.queryWorkingTime(entity);
            entity.setWorkingHours(millisecondToSecond(workingTime01));

            //加分：产出文档,暂时写死
            entity.setOutputDocument(10);
            //加分：文档引用,暂时写死
            entity.setDocumentReference(9);
            //减分：信息安全违规,暂时写死
            entity.setInfoSafeViolation(1);
            //减分：不规范操作,暂时写死
            entity.setIrregularOperation(2);
            //项目加权,暂时写死
            entity.setProjectWeighting(1);

            //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
            double allKPI = (entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference()-
                    entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg = new BigDecimal(allKPI);
            entity.setKPI(operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算KPICal用于计算环比
            //加分：场景次数
            int operaCount01 = operaCounts01;

            //加分：工时，上个区间
            entity.setActionBeginTime(actionBeginTime01);
            entity.setActionEndTime(actionBeginTime);
            double workingTime02 = bydMapper.queryWorkingTime(entity);
            double workingHour01 = millisecondToSecond(workingTime02);

            //加分：产出文档,暂时写死
            int oupPutDoc01 = 10;
            //加分：文档引用,暂时写死
            int documentReference01 = 9;
            //减分：信息安全违规,暂时写死
            int infoSafeViolation01 = 1;
            //减分：不规范操作,暂时写死
            int irregularOperation01 = 2;
            //项目加权,暂时写死
            int projectWeighting01 = 1;

            double KPICal = (operaCount01+workingHour01+documentReference01+oupPutDoc01-infoSafeViolation01-irregularOperation01)*projectWeighting01;

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg01 = new BigDecimal(KPICal);
            entity.setKPICal(operationAvgTimeBg01.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算平均KPI
            if(bydEntity.getAvgKPIFlag() == 1){
                if(operaCounts != 0){
                    BigDecimal operationAvgTimeBg04 = new BigDecimal(entity.getKPI()/operaCounts);
                    entity.setKPI(operationAvgTimeBg04.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
            }

            //不判空后面计算会出错
            if(entity.getEmployeeName() == null){
                entity.setEmployeeName("");
            }
        }

        //相同employee_name去重+计算KPI+计算KPICal+计算KPIjiafen+计算KPIjianfen
        List<BYDEntity> kpiEntityList = CalculateUtil.toBusinessNameHeavyAndScore(nameCompanyGroupList);

        //计算环比
        for (BYDEntity entity : kpiEntityList) {
            double KPI = entity.getKPI();
            double KPICal = entity.getKPICal();
            if((KPICal<0.0001)&&(KPICal>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else{
                String linkRatio = CalculateUtil.calLinkRatio(KPI, KPICal);
                entity.setKPILinkRatio(linkRatio);
            }
        }

        //对KPI进行降序排列
        Collections.sort(kpiEntityList, new Comparator<BYDEntity>() {
            @Override
            public int compare(BYDEntity o1, BYDEntity o2) {
                if (o1.getKPI() > o2.getKPI()) {
                    return -1;
                }
                if (o1.getKPI() == o2.getKPI()) {
                    return 0;
                }
                return 1;
            }
        });

        //分页
        List tableTotalList = new ArrayList();
        if (bydEntity.getPagesSize() != 0 && bydEntity.getPagesNum() != 0) {
            //tableList分页处理，pagesSize和pagesNum不能为空
            PageModelUtil pm = new PageModelUtil(kpiEntityList, bydEntity.getPagesSize());//每页显示条数
            //总页数
            int totalPages = pm.getTotalPages();
            //总条数
            int totalRows = pm.getTotalRows();
            kpiEntityList = pm.getObjects(bydEntity.getPagesNum());

            tableTotalList.add(totalRows);
            tableTotalList.add(totalPages);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("tableTotalList", tableTotalList);
        resultMap.put("kpiRankingList", kpiEntityList);
        return resultMap;
    }

    //3.员工加分排行
    @Override
    public Map queryEmployeeBonusRanking(BYDEntity bydEntity){
        //计算查询日期排名
        String actionBeginTime = bydEntity.getActionBeginTime();
        String actionEndTime = bydEntity.getActionEndTime();

        //首先获取数据库所有姓名+公司+部门+hostname
        List<BYDEntity> nameCompanyGroupList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                List<BYDEntity> entityList = bydMapper.queryComGroupName(bydEntity);
                if (entityList.size() != 0) {
                    if (null != entityList.get(0)) {
                        nameCompanyGroupList.addAll(entityList);
                    }
                }
            }
        }

        //list根据employee_name、host_name
        nameCompanyGroupList = nameCompanyGroupList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getEmployeeName() + ";" + o.getHostName()))), ArrayList::new));

        //计算查询日期前一段时间的排名，用于计算环比
        int i = DateUtil.calDays(actionBeginTime, actionEndTime);
        String actionBeginTime01 = DateUtil.calSomeDate(actionBeginTime, i);


        //查询场景次数+工时
        for (BYDEntity entity : nameCompanyGroupList) {
            int operaCounts = 0;
            double workingTimes = 0;

            int operaCounts01 = 0;
            double workingTimes01 = 0;

            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    //查询当前区间的数据
                    entity.setActionBeginTime(actionBeginTime);
                    entity.setActionEndTime(actionEndTime);
                    entity.setTableName(sceneTableName);
                    //场景次数
                    int operaCount= bydMapper.queryOperaCount(entity);
                    operaCounts = operaCounts + operaCount;
/*                    if(operaCount>0){
                        //工时
                        double workingTime = bydMapper.queryWorkingTime(entity);
                        workingTimes = workingTimes + workingTime;
                    }*/

                    //计算前一段区间的数据，用于计算环比
                    entity.setActionBeginTime(actionBeginTime01);
                    entity.setActionEndTime(actionBeginTime);
                    //场景次数
                    int operaCount01= bydMapper.queryOperaCount(entity);
                    operaCounts01 = operaCounts01 + operaCount01;
/*                    if(operaCount01>0){
                        //工时
                        double workingTime01 = bydMapper.queryWorkingTime(entity);
                        workingTimes01 = workingTimes01 + workingTime01;
                    }*/
                    //
                }
            }
            //
            //加分：场景次数
            entity.setOperaCount(operaCounts);

            //加分：工时，当前区间
            entity.setActionBeginTime(actionBeginTime);
            entity.setActionEndTime(actionEndTime);
            double workingTime01 = bydMapper.queryWorkingTime(entity);
            entity.setWorkingHours(millisecondToSecond(workingTime01));

            //加分：产出文档,暂时写死
            entity.setOutputDocument(10);
            //加分：文档引用,暂时写死
            entity.setDocumentReference(9);
            //减分：信息安全违规,暂时写死
            entity.setInfoSafeViolation(1);
            //减分：不规范操作,暂时写死
            entity.setIrregularOperation(2);
            //项目加权,暂时写死
            entity.setProjectWeighting(1);

            //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
            double allKPI = (entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference()-
                    entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg = new BigDecimal(allKPI);
            entity.setKPI(operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算KPICal用于计算环比
            //加分：场景次数
            int operaCount01 = operaCounts01;

            //加分：工时，上个区间
            entity.setActionBeginTime(actionBeginTime01);
            entity.setActionEndTime(actionBeginTime);
            double workingTime02 = bydMapper.queryWorkingTime(entity);
            double workingHour01 = millisecondToSecond(workingTime02);

            //加分：产出文档,暂时写死
            int oupPutDoc01 = 10;
            //加分：文档引用,暂时写死
            int documentReference01 = 9;
            //减分：信息安全违规,暂时写死
            int infoSafeViolation01 = 1;
            //减分：不规范操作,暂时写死
            int irregularOperation01 = 2;
            //项目加权,暂时写死
            int projectWeighting01 = 1;

            double KPICal = (operaCount01+workingHour01+documentReference01+oupPutDoc01-infoSafeViolation01-irregularOperation01)*projectWeighting01;

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg01 = new BigDecimal(KPICal);
            entity.setKPICal(operationAvgTimeBg01.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());


            //计算加分KPI
            double jiafenKPI = entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference();
            //KPI保留两位小数
            BigDecimal operationAvgTimeBg02 = new BigDecimal(jiafenKPI);
            entity.setKPIjiafen(operationAvgTimeBg02.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算平均KPI
            if(bydEntity.getAvgKPIFlag() == 1){
                if(operaCounts != 0){
                    BigDecimal operationAvgTimeBg05 = new BigDecimal(entity.getKPIjiafen()/operaCounts);
                    entity.setKPIjiafen(operationAvgTimeBg05.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
            }

            if(entity.getEmployeeName() == null){
                entity.setEmployeeName("");
            }
        }

        //相同employee_name去重+计算KPI+计算KPICal+计算KPIjiafen+计算KPIjianfen
        List<BYDEntity> kpiEntityList = CalculateUtil.toBusinessNameHeavyAndScore(nameCompanyGroupList);

        //计算加分环比
        for (BYDEntity entity : kpiEntityList) {
            double jiafenKPI = entity.getKPIjiafen();
            double KPICal = entity.getKPICal();
            if((KPICal<0.0001)&&(KPICal>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else if((jiafenKPI<0.0001)&&(jiafenKPI>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else{
                String linkRatio = CalculateUtil.calLinkRatio(jiafenKPI, KPICal);
                entity.setKPILinkRatioJIAFEN(linkRatio);
            }
        }

        //对KPI进行降序排列
        Collections.sort(kpiEntityList, new Comparator<BYDEntity>() {
            @Override
            public int compare(BYDEntity o1, BYDEntity o2) {
                if (o1.getKPIjiafen() > o2.getKPIjiafen()) {
                    return -1;
                }
                if (o1.getKPIjiafen() == o2.getKPIjiafen()) {
                    return 0;
                }
                return 1;
            }
        });

        //分页
        List tableTotalList = new ArrayList();
        if (bydEntity.getPagesSize() != 0 && bydEntity.getPagesNum() != 0) {
            //tableList分页处理，pagesSize和pagesNum不能为空
            PageModelUtil pm = new PageModelUtil(kpiEntityList, bydEntity.getPagesSize());//每页显示条数
            //总页数
            int totalPages = pm.getTotalPages();
            //总条数
            int totalRows = pm.getTotalRows();
            kpiEntityList = pm.getObjects(bydEntity.getPagesNum());

            tableTotalList.add(totalRows);
            tableTotalList.add(totalPages);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("tableTotalList", tableTotalList);
        resultMap.put("kpiRankingList", kpiEntityList);
        return resultMap;
    }

    //4.员工减分排行
    @Override
    public Map queryEmployeeMinusRanking(BYDEntity bydEntity){
        //计算查询日期排名
        String actionBeginTime = bydEntity.getActionBeginTime();
        String actionEndTime = bydEntity.getActionEndTime();

        //首先获取数据库所有姓名+公司+部门+hostname
        List<BYDEntity> nameCompanyGroupList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                List<BYDEntity> entityList = bydMapper.queryComGroupName(bydEntity);
                if (entityList.size() != 0) {
                    if (null != entityList.get(0)) {
                        nameCompanyGroupList.addAll(entityList);
                    }
                }
            }
        }

        //list根据employee_name、host_name
        nameCompanyGroupList = nameCompanyGroupList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getEmployeeName() + ";" + o.getHostName()))), ArrayList::new));

        //计算查询日期前一段时间的排名，用于计算环比
        int i = DateUtil.calDays(actionBeginTime, actionEndTime);
        String actionBeginTime01 = DateUtil.calSomeDate(actionBeginTime, i);


        //查询场景次数+工时
        for (BYDEntity entity : nameCompanyGroupList) {
            int operaCounts = 0;
            double workingTimes = 0;

            int operaCounts01 = 0;
            double workingTimes01 = 0;

            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    //查询当前区间的数据
                    entity.setActionBeginTime(actionBeginTime);
                    entity.setActionEndTime(actionEndTime);
                    entity.setTableName(sceneTableName);
                    //场景次数
                    int operaCount= bydMapper.queryOperaCount(entity);
                    operaCounts = operaCounts + operaCount;
/*                    if(operaCount>0){
                        //工时
                        double workingTime = bydMapper.queryWorkingTime(entity);
                        workingTimes = workingTimes + workingTime;
                    }*/

                    //计算前一段区间的数据，用于计算环比
                    entity.setActionBeginTime(actionBeginTime01);
                    entity.setActionEndTime(actionBeginTime);
                    //场景次数
                    int operaCount01= bydMapper.queryOperaCount(entity);
                    operaCounts01 = operaCounts01 + operaCount01;
/*                    if(operaCount01>0){
                        //工时
                        double workingTime01 = bydMapper.queryWorkingTime(entity);
                        workingTimes01 = workingTimes01 + workingTime01;
                    }*/
                    //
                }
            }
            //
            //加分：场景次数
            entity.setOperaCount(operaCounts);

            //加分：工时，当前区间
            entity.setActionBeginTime(actionBeginTime);
            entity.setActionEndTime(actionEndTime);
            double workingTime01 = bydMapper.queryWorkingTime(entity);
            entity.setWorkingHours(millisecondToSecond(workingTime01));

            //加分：产出文档,暂时写死
            entity.setOutputDocument(10);
            //加分：文档引用,暂时写死
            entity.setDocumentReference(9);
            //减分：信息安全违规,暂时写死
            entity.setInfoSafeViolation(1);
            //减分：不规范操作,暂时写死
            entity.setIrregularOperation(2);
            //项目加权,暂时写死
            entity.setProjectWeighting(1);

            //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
            double allKPI = (entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference()-
                    entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg = new BigDecimal(allKPI);
            entity.setKPI(operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算KPICal用于计算环比
            //加分：场景次数
            int operaCount01 = operaCounts01;

            //加分：工时，上个区间
            entity.setActionBeginTime(actionBeginTime01);
            entity.setActionEndTime(actionBeginTime);
            double workingTime02 = bydMapper.queryWorkingTime(entity);
            double workingHour01 = millisecondToSecond(workingTime02);

            //加分：产出文档,暂时写死
            int oupPutDoc01 = 10;
            //加分：文档引用,暂时写死
            int documentReference01 = 9;
            //减分：信息安全违规,暂时写死
            int infoSafeViolation01 = 1;
            //减分：不规范操作,暂时写死
            int irregularOperation01 = 2;
            //项目加权,暂时写死
            int projectWeighting01 = 1;

            double KPICal = (operaCount01+workingHour01+documentReference01+oupPutDoc01-infoSafeViolation01-irregularOperation01)*projectWeighting01;

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg01 = new BigDecimal(KPICal);
            entity.setKPICal(operationAvgTimeBg01.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());


            //计算减分KPI
            double jianfenKPI = entity.getInfoSafeViolation()+entity.getIrregularOperation();
            BigDecimal operationAvgTimeBg03 = new BigDecimal(jianfenKPI);
            entity.setKPIjianfen(operationAvgTimeBg03.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算平均KPI
            if(bydEntity.getAvgKPIFlag() == 1){
                if(operaCounts != 0){
                    BigDecimal operationAvgTimeBg06 = new BigDecimal(entity.getKPIjianfen()/operaCounts);
                    entity.setKPIjianfen(operationAvgTimeBg06.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
            }

            if(entity.getEmployeeName() == null){
                entity.setEmployeeName("");
            }
        }

        //相同employee_name去重+计算KPI+计算KPICal+计算KPIjiafen+计算KPIjianfen
        List<BYDEntity> kpiEntityList = CalculateUtil.toBusinessNameHeavyAndScore(nameCompanyGroupList);

        //计算减分环比
        for (BYDEntity entity : kpiEntityList) {
            double jianfenKPI = entity.getKPIjianfen();
            double KPICal = entity.getKPICal();
            if((KPICal<0.0001)&&(KPICal>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else if((jianfenKPI<0.0001)&&(jianfenKPI>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else{
                String linkRatio = CalculateUtil.calLinkRatio(jianfenKPI, KPICal);
                entity.setKPILinkRatioJIANFEN(linkRatio);
            }
        }

        //对KPI进行降序排列
        Collections.sort(kpiEntityList, new Comparator<BYDEntity>() {
            @Override
            public int compare(BYDEntity o1, BYDEntity o2) {
                if (o1.getKPIjianfen() > o2.getKPIjianfen()) {
                    return -1;
                }
                if (o1.getKPIjianfen() == o2.getKPIjianfen()) {
                    return 0;
                }
                return 1;
            }
        });

        //分页
        List tableTotalList = new ArrayList();
        if (bydEntity.getPagesSize() != 0 && bydEntity.getPagesNum() != 0) {
            //tableList分页处理，pagesSize和pagesNum不能为空
            PageModelUtil pm = new PageModelUtil(kpiEntityList, bydEntity.getPagesSize());//每页显示条数
            //总页数
            int totalPages = pm.getTotalPages();
            //总条数
            int totalRows = pm.getTotalRows();
            kpiEntityList = pm.getObjects(bydEntity.getPagesNum());

            tableTotalList.add(totalRows);
            tableTotalList.add(totalPages);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("tableTotalList", tableTotalList);
        resultMap.put("kpiRankingList", kpiEntityList);
        return resultMap;
    }

    //部门KPI排名，总查询
    @Override
    public Map queryGroupRankingAll(BYDEntity bydEntity) {
        //计算查询日期排名
        String actionBeginTime = bydEntity.getActionBeginTime();
        String actionEndTime = bydEntity.getActionEndTime();

        String[] selectField = bydEntity.getSelectField();

        //首先获取数据库所有姓名+公司+部门+hostname
        List<BYDEntity> nameCompanyGroupList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                if(selectField.length > 0) {
                    for (int i = 0; i < selectField.length; i++) {
                        String employeeGroup = selectField[i];
                        bydEntity.setEmployeeGroup(employeeGroup);
                        List<BYDEntity> entityList = bydMapper.queryComGroupName(bydEntity);
                        if (entityList.size() != 0) {
                            if (null != entityList.get(0)) {
                                nameCompanyGroupList.addAll(entityList);
                            }
                        }
                    }
                }else{
                    List<BYDEntity> entityList = bydMapper.queryComGroupName(bydEntity);
                    if (entityList.size() != 0) {
                        if (null != entityList.get(0)) {
                            nameCompanyGroupList.addAll(entityList);
                        }
                    }
                }
            }
        }

        //list根据employee_name、host_name
        nameCompanyGroupList = nameCompanyGroupList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getEmployeeName() + ";" + o.getHostName()))), ArrayList::new));

        //计算查询日期前一段时间的排名，用于计算环比
        int i = DateUtil.calDays(actionBeginTime, actionEndTime);
        String actionBeginTime01 = DateUtil.calSomeDate(actionBeginTime, i);

        //查询场景次数+工时
        for (BYDEntity entity : nameCompanyGroupList) {
            //entity.setActionBeginTime(actionBeginTime);
            //entity.setActionEndTime(actionEndTime);
            int operaCounts = 0;
            double workingTimes = 0;

            int operaCounts01 = 0;
            double workingTimes01 = 0;
            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    //查询当前区间的数据
                    entity.setActionBeginTime(actionBeginTime);
                    entity.setActionEndTime(actionEndTime);
                    entity.setTableName(sceneTableName);
                    //场景次数
                    int operaCount= bydMapper.queryOperaCount(entity);
                    operaCounts = operaCounts + operaCount;
/*                    if(operaCount>0){
                        //工时
                        double workingTime = bydMapper.queryWorkingTime(entity);
                        workingTimes = workingTimes + workingTime;
                    }*/
                    //

                    //计算前一段区间的数据，用于计算环比
                    entity.setActionBeginTime(actionBeginTime01);
                    entity.setActionEndTime(actionBeginTime);
                    //场景次数
                    int operaCount01= bydMapper.queryOperaCount(entity);
                    operaCounts01 = operaCounts01 + operaCount01;
/*                    if(operaCount01>0){
                        //工时
                        double workingTime01 = bydMapper.queryWorkingTime(entity);
                        workingTimes01 = workingTimes01 + workingTime01;
                    }*/
                    //
                }
            }
            //
            //加分：场景次数
            entity.setOperaCount(operaCounts);

            //加分：工时，当前区间
            entity.setActionBeginTime(actionBeginTime);
            entity.setActionEndTime(actionEndTime);
            double workingTime01 = bydMapper.queryWorkingTime(entity);
            entity.setWorkingHours(millisecondToSecond(workingTime01));

            //加分：产出文档,暂时写死
            entity.setOutputDocument(10);
            //加分：文档引用,暂时写死
            entity.setDocumentReference(9);
            //减分：信息安全违规,暂时写死
            entity.setInfoSafeViolation(1);
            //减分：不规范操作,暂时写死
            entity.setIrregularOperation(2);
            //项目加权,暂时写死
            entity.setProjectWeighting(1);

            //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
            double allKPI = (entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference()-
                    entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg = new BigDecimal(allKPI);
            entity.setKPI(operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算KPICal用于计算环比
            //加分：场景次数
            int operaCount01 = operaCounts01;

            //加分：工时，上个区间
            entity.setActionBeginTime(actionBeginTime01);
            entity.setActionEndTime(actionBeginTime);
            double workingTime02 = bydMapper.queryWorkingTime(entity);
            double workingHour01 = millisecondToSecond(workingTime02);

            //加分：产出文档,暂时写死
            int oupPutDoc01 = 10;
            //加分：文档引用,暂时写死
            int documentReference01 = 9;
            //减分：信息安全违规,暂时写死
            int infoSafeViolation01 = 1;
            //减分：不规范操作,暂时写死
            int irregularOperation01 = 2;
            //项目加权,暂时写死
            int projectWeighting01 = 1;

            double KPICal = (operaCount01+workingHour01+documentReference01+oupPutDoc01-infoSafeViolation01-irregularOperation01)*projectWeighting01;

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg01 = new BigDecimal(KPICal);
            entity.setKPICal(operationAvgTimeBg01.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算加分KPI
            double jiafenKPI = entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference();
            //KPI保留两位小数
            BigDecimal operationAvgTimeBg02 = new BigDecimal(jiafenKPI);
            entity.setKPIjiafen(operationAvgTimeBg02.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            //计算减分KPI
            double jianfenKPI = entity.getInfoSafeViolation()+entity.getIrregularOperation();
            BigDecimal operationAvgTimeBg03 = new BigDecimal(jianfenKPI);
            entity.setKPIjianfen(operationAvgTimeBg03.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            if(entity.getEmployeeGroup() == null){
                entity.setEmployeeGroup("");
            }
        }

        //相同employee_group去重+计算KPI+计算KPICal+计算KPIjiafen+计算KPIjianfen
        List<BYDEntity> kpiEntityList = CalculateUtil.calEmployeeGroupKPI(nameCompanyGroupList);

        //计算环比
        for (BYDEntity entity : kpiEntityList) {
            double KPI = entity.getKPI();
            double KPICal = entity.getKPICal();
            if((KPICal<0.0001)&&(KPICal>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else if((KPI<0.0001)&&(KPI>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else{
                String linkRatio = CalculateUtil.calLinkRatio(KPI, KPICal);
                entity.setKPILinkRatio(linkRatio);
            }

        }

        //计算加分环比
        for (BYDEntity entity : kpiEntityList) {
            double jiafenKPI = entity.getKPIjiafen();
            double KPICal = entity.getKPICal();
            if((KPICal<0.0001)&&(KPICal>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else if((jiafenKPI<0.0001)&&(jiafenKPI>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else{
                String linkRatio = CalculateUtil.calLinkRatio(jiafenKPI, KPICal);
                entity.setKPILinkRatioJIAFEN(linkRatio);
            }
        }

        //计算减分环比
        for (BYDEntity entity : kpiEntityList) {
            double jianfenKPI = entity.getKPIjianfen();
            double KPICal = entity.getKPICal();
            if((KPICal<0.0001)&&(KPICal>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else if((jianfenKPI<0.0001)&&(jianfenKPI>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else{
                String linkRatio = CalculateUtil.calLinkRatio(jianfenKPI, KPICal);
                entity.setKPILinkRatioJIANFEN(linkRatio);
            }
        }

        //对KPI进行降序排列
        Collections.sort(kpiEntityList, new Comparator<BYDEntity>() {
            @Override
            public int compare(BYDEntity o1, BYDEntity o2) {
                if (o1.getKPI() > o2.getKPI()) {
                    return -1;
                }
                if (o1.getKPI() == o2.getKPI()) {
                    return 0;
                }
                return 1;
            }
        });

        HashMap resultMap = new HashMap();

        //分页前计算总KPI
        double allKPI = kpiEntityList.stream().mapToDouble(BYDEntity::getKPI).sum();
        //KPI保留两位小数
        BigDecimal operationAvgTimeBg = new BigDecimal(allKPI);
        allKPI = operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        //员工KPI贡献，分页前list
        resultMap.put("contributionList", kpiEntityList);

        //分页
        List tableTotalList = new ArrayList();
        if (bydEntity.getPagesSize() != 0 && bydEntity.getPagesNum() != 0) {
            //tableList分页处理，pagesSize和pagesNum不能为空
            PageModelUtil pm = new PageModelUtil(kpiEntityList, bydEntity.getPagesSize());//每页显示条数
            //总页数
            int totalPages = pm.getTotalPages();
            //总条数
            int totalRows = pm.getTotalRows();
            kpiEntityList = pm.getObjects(bydEntity.getPagesNum());

            tableTotalList.add(totalRows);
            tableTotalList.add(totalPages);
        }

        resultMap.put("tableTotalList", tableTotalList);
        resultMap.put("kpiRankingList", kpiEntityList);
        resultMap.put("allKPI", allKPI);
        return resultMap;
    }

    //1.部门KPI贡献
    @Override
    public Map queryGroupKPIContribution(BYDEntity bydEntity) {
        String actionBeginTime = bydEntity.getActionBeginTime();
        String actionEndTime = bydEntity.getActionEndTime();

        //首先获取数据库所有姓名+公司+部门+hostname
        List<BYDEntity> nameCompanyGroupList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                List<BYDEntity> entityList = bydMapper.queryComGroupName(bydEntity);
                if (entityList.size() != 0) {
                    if (null != entityList.get(0)) {
                        nameCompanyGroupList.addAll(entityList);
                    }
                }
            }
        }

        //list根据employee_name、host_name
        nameCompanyGroupList = nameCompanyGroupList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getEmployeeName() + ";" + o.getHostName()))), ArrayList::new));

        //查询场景次数+工时
        for (BYDEntity entity : nameCompanyGroupList) {
            //entity.setActionBeginTime(actionBeginTime);
            //entity.setActionEndTime(actionEndTime);
            int operaCounts = 0;
            double workingTimes = 0;

            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    //查询当前区间的数据
                    entity.setActionBeginTime(actionBeginTime);
                    entity.setActionEndTime(actionEndTime);
                    entity.setTableName(sceneTableName);
                    //场景次数
                    int operaCount= bydMapper.queryOperaCount(entity);
                    operaCounts = operaCounts + operaCount;
/*                    if(operaCount>0){
                        //工时
                        double workingTime = bydMapper.queryWorkingTime(entity);
                        workingTimes = workingTimes + workingTime;
                    }*/
                }
            }
            //加分：场景次数
            entity.setOperaCount(operaCounts);

            //加分：工时，当前区间
            entity.setActionBeginTime(actionBeginTime);
            entity.setActionEndTime(actionEndTime);
            double workingTime01 = bydMapper.queryWorkingTime(entity);
            entity.setWorkingHours(millisecondToSecond(workingTime01));

            //加分：产出文档,暂时写死
            entity.setOutputDocument(10);
            //加分：文档引用,暂时写死
            entity.setDocumentReference(9);
            //减分：信息安全违规,暂时写死
            entity.setInfoSafeViolation(1);
            //减分：不规范操作,暂时写死
            entity.setIrregularOperation(2);
            //项目加权,暂时写死
            entity.setProjectWeighting(1);

            double allKPI = 0.0;
            if(bydEntity.getKpiContributionFlag() == 1){//净贡献
                //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
                allKPI = (entity.getOperaCount()+entity.getWorkingHours()+entity.getDocumentReference()+entity.getOutputDocument()-entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();
            }else if(bydEntity.getKpiContributionFlag() == 2){//加分贡献
                allKPI = (entity.getOperaCount()+entity.getWorkingHours()+entity.getDocumentReference()+entity.getOutputDocument())*entity.getProjectWeighting();
            }else if(bydEntity.getKpiContributionFlag() == 3){//减分贡献
                allKPI = (entity.getInfoSafeViolation()+entity.getIrregularOperation())*entity.getProjectWeighting();
            }

            //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
            //double allKPI = (entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference()-
            //       entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg = new BigDecimal(allKPI);
            entity.setKPI(operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            if(entity.getEmployeeGroup() == null){
                entity.setEmployeeGroup("");
            }
        }

        //相同employee_group去重+计算KPI+计算KPICal+计算KPIjiafen+计算KPIjianfen
        List<BYDEntity> kpiEntityList = CalculateUtil.calEmployeeGroupKPI(nameCompanyGroupList);

        //对KPI进行降序排列
        Collections.sort(kpiEntityList, new Comparator<BYDEntity>() {
            @Override
            public int compare(BYDEntity o1, BYDEntity o2) {
                if (o1.getKPI() > o2.getKPI()) {
                    return -1;
                }
                if (o1.getKPI() == o2.getKPI()) {
                    return 0;
                }
                return 1;
            }
        });

        //计算总KPI
        double allKPI = kpiEntityList.stream().mapToDouble(BYDEntity::getKPI).sum();

        //KPI保留两位小数
        BigDecimal operationAvgTimeBg = new BigDecimal(allKPI);
        allKPI = operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        HashMap resultMap = new HashMap();
        resultMap.put("contributionList", kpiEntityList);
        resultMap.put("allKPI", allKPI);
        return resultMap;
    }

    //2.部门KPI排名
    @Override
    public Map queryGroupKPIRanking(BYDEntity bydEntity) {
        //计算查询日期排名
        String actionBeginTime = bydEntity.getActionBeginTime();
        String actionEndTime = bydEntity.getActionEndTime();

        //首先获取数据库所有姓名+公司+部门+hostname
        List<BYDEntity> nameCompanyGroupList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                List<BYDEntity> entityList = bydMapper.queryComGroupName(bydEntity);
                if (entityList.size() != 0) {
                    if (null != entityList.get(0)) {
                        nameCompanyGroupList.addAll(entityList);
                    }
                }
            }
        }

        //list根据employee_name、host_name
        nameCompanyGroupList = nameCompanyGroupList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getEmployeeName() + ";" + o.getHostName()))), ArrayList::new));

        //计算查询日期前一段时间的排名，用于计算环比
        int i = DateUtil.calDays(actionBeginTime, actionEndTime);
        String actionBeginTime01 = DateUtil.calSomeDate(actionBeginTime, i);

        //查询场景次数+工时
        for (BYDEntity entity : nameCompanyGroupList) {
            //entity.setActionBeginTime(actionBeginTime);
            //entity.setActionEndTime(actionEndTime);
            int operaCounts = 0;
            double workingTimes = 0;

            int operaCounts01 = 0;
            double workingTimes01 = 0;
            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    //查询当前区间的数据
                    entity.setActionBeginTime(actionBeginTime);
                    entity.setActionEndTime(actionEndTime);
                    entity.setTableName(sceneTableName);
                    //场景次数
                    int operaCount= bydMapper.queryOperaCount(entity);
                    operaCounts = operaCounts + operaCount;
/*                    if(operaCount>0){
                        //工时
                        double workingTime = bydMapper.queryWorkingTime(entity);
                        workingTimes = workingTimes + workingTime;
                    }*/
                    //

                    //计算前一段区间的数据，用于计算环比
                    entity.setActionBeginTime(actionBeginTime01);
                    entity.setActionEndTime(actionBeginTime);
                    //场景次数
                    int operaCount01= bydMapper.queryOperaCount(entity);
                    operaCounts01 = operaCounts01 + operaCount01;
/*                    if(operaCount01>0){
                        //工时
                        double workingTime01 = bydMapper.queryWorkingTime(entity);
                        workingTimes01 = workingTimes01 + workingTime01;
                    }*/
                    //
                }
            }
            //
            //加分：场景次数
            entity.setOperaCount(operaCounts);

            //加分：工时，当前区间
            entity.setActionBeginTime(actionBeginTime);
            entity.setActionEndTime(actionEndTime);
            double workingTime01 = bydMapper.queryWorkingTime(entity);
            entity.setWorkingHours(millisecondToSecond(workingTime01));

            //加分：产出文档,暂时写死
            entity.setOutputDocument(10);
            //加分：文档引用,暂时写死
            entity.setDocumentReference(9);
            //减分：信息安全违规,暂时写死
            entity.setInfoSafeViolation(1);
            //减分：不规范操作,暂时写死
            entity.setIrregularOperation(2);
            //项目加权,暂时写死
            entity.setProjectWeighting(1);

            //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
            double allKPI = (entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference()-
                    entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg = new BigDecimal(allKPI);
            entity.setKPI(operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算KPICal用于计算环比
            //加分：场景次数
            int operaCount01 = operaCounts01;

            //加分：工时，上个区间
            entity.setActionBeginTime(actionBeginTime01);
            entity.setActionEndTime(actionBeginTime);
            double workingTime02 = bydMapper.queryWorkingTime(entity);
            double workingHour01 = millisecondToSecond(workingTime02);

            //加分：产出文档,暂时写死
            int oupPutDoc01 = 10;
            //加分：文档引用,暂时写死
            int documentReference01 = 9;
            //减分：信息安全违规,暂时写死
            int infoSafeViolation01 = 1;
            //减分：不规范操作,暂时写死
            int irregularOperation01 = 2;
            //项目加权,暂时写死
            int projectWeighting01 = 1;

            double KPICal = (operaCount01+workingHour01+documentReference01+oupPutDoc01-infoSafeViolation01-irregularOperation01)*projectWeighting01;

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg01 = new BigDecimal(KPICal);
            entity.setKPICal(operationAvgTimeBg01.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算平均KPI
            if(bydEntity.getAvgKPIFlag() == 1){
                if(operaCounts != 0){
                    BigDecimal operationAvgTimeBg04 = new BigDecimal(entity.getKPI()/operaCounts);
                    entity.setKPI(operationAvgTimeBg04.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
            }

            if(entity.getEmployeeGroup() == null){
                entity.setEmployeeGroup("");
            }
        }

        //相同employee_group去重+计算KPI+计算KPICal+计算KPIjiafen+计算KPIjianfen
        List<BYDEntity> kpiEntityList = CalculateUtil.calEmployeeGroupKPI(nameCompanyGroupList);

        //计算环比
        for (BYDEntity entity : kpiEntityList) {
            double KPI = entity.getKPI();
            double KPICal = entity.getKPICal();
            if((KPICal<0.0001)&&(KPICal>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else{
                String linkRatio = CalculateUtil.calLinkRatio(KPI, KPICal);
                entity.setKPILinkRatio(linkRatio);
            }
        }

        //对KPI进行降序排列
        Collections.sort(kpiEntityList, new Comparator<BYDEntity>() {
            @Override
            public int compare(BYDEntity o1, BYDEntity o2) {
                if (o1.getKPI() > o2.getKPI()) {
                    return -1;
                }
                if (o1.getKPI() == o2.getKPI()) {
                    return 0;
                }
                return 1;
            }
        });

        //分页
        List tableTotalList = new ArrayList();
        if (bydEntity.getPagesSize() != 0 && bydEntity.getPagesNum() != 0) {
            //tableList分页处理，pagesSize和pagesNum不能为空
            PageModelUtil pm = new PageModelUtil(kpiEntityList, bydEntity.getPagesSize());//每页显示条数
            //总页数
            int totalPages = pm.getTotalPages();
            //总条数
            int totalRows = pm.getTotalRows();
            kpiEntityList = pm.getObjects(bydEntity.getPagesNum());

            tableTotalList.add(totalRows);
            tableTotalList.add(totalPages);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("tableTotalList", tableTotalList);
        resultMap.put("kpiRankingList", kpiEntityList);
        return resultMap;
    }

    //3.部门KPI,加分排行
    @Override
    public Map queryGroupBonusRanking(BYDEntity bydEntity){
        //计算查询日期排名
        String actionBeginTime = bydEntity.getActionBeginTime();
        String actionEndTime = bydEntity.getActionEndTime();

        //首先获取数据库所有姓名+公司+部门+hostname
        List<BYDEntity> nameCompanyGroupList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                List<BYDEntity> entityList = bydMapper.queryComGroupName(bydEntity);
                if (entityList.size() != 0) {
                    if (null != entityList.get(0)) {
                        nameCompanyGroupList.addAll(entityList);
                    }
                }
            }
        }

        //list根据employee_name、host_name
        nameCompanyGroupList = nameCompanyGroupList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getEmployeeName() + ";" + o.getHostName()))), ArrayList::new));

        //计算查询日期前一段时间的排名，用于计算环比
        int i = DateUtil.calDays(actionBeginTime, actionEndTime);
        String actionBeginTime01 = DateUtil.calSomeDate(actionBeginTime, i);


        //查询场景次数+工时
        for (BYDEntity entity : nameCompanyGroupList) {
            //entity.setActionBeginTime(actionBeginTime);
            //entity.setActionEndTime(actionEndTime);
            int operaCounts = 0;
            double workingTimes = 0;

            int operaCounts01 = 0;
            double workingTimes01 = 0;

            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    //查询当前区间的数据
                    entity.setActionBeginTime(actionBeginTime);
                    entity.setActionEndTime(actionEndTime);
                    entity.setTableName(sceneTableName);
                    //场景次数
                    int operaCount= bydMapper.queryOperaCount(entity);
                    operaCounts = operaCounts + operaCount;
/*                    if(operaCount>0){
                        //工时
                        double workingTime = bydMapper.queryWorkingTime(entity);
                        workingTimes = workingTimes + workingTime;
                    }*/

                    //计算前一段区间的数据，用于计算环比
                    entity.setActionBeginTime(actionBeginTime01);
                    entity.setActionEndTime(actionBeginTime);
                    //场景次数
                    int operaCount01= bydMapper.queryOperaCount(entity);
                    operaCounts01 = operaCounts01 + operaCount01;
/*                    if(operaCount01>0){
                        //工时
                        double workingTime01 = bydMapper.queryWorkingTime(entity);
                        workingTimes01 = workingTimes01 + workingTime01;
                    }*/
                    //
                }
            }
            //
            //加分：场景次数
            entity.setOperaCount(operaCounts);

            //加分：工时，当前区间
            entity.setActionBeginTime(actionBeginTime);
            entity.setActionEndTime(actionEndTime);
            double workingTime01 = bydMapper.queryWorkingTime(entity);
            entity.setWorkingHours(millisecondToSecond(workingTime01));

            //加分：产出文档,暂时写死
            entity.setOutputDocument(10);
            //加分：文档引用,暂时写死
            entity.setDocumentReference(9);
            //减分：信息安全违规,暂时写死
            entity.setInfoSafeViolation(1);
            //减分：不规范操作,暂时写死
            entity.setIrregularOperation(2);
            //项目加权,暂时写死
            entity.setProjectWeighting(1);

            //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
            double allKPI = (entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference()-
                    entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg = new BigDecimal(allKPI);
            entity.setKPI(operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算KPICal用于计算环比
            //加分：场景次数
            int operaCount01 = operaCounts01;

            //加分：工时，上个区间
            entity.setActionBeginTime(actionBeginTime01);
            entity.setActionEndTime(actionBeginTime);
            double workingTime02 = bydMapper.queryWorkingTime(entity);
            double workingHour01 = millisecondToSecond(workingTime02);

            //加分：产出文档,暂时写死
            int oupPutDoc01 = 10;
            //加分：文档引用,暂时写死
            int documentReference01 = 9;
            //减分：信息安全违规,暂时写死
            int infoSafeViolation01 = 1;
            //减分：不规范操作,暂时写死
            int irregularOperation01 = 2;
            //项目加权,暂时写死
            int projectWeighting01 = 1;

            double KPICal = (operaCount01+workingHour01+documentReference01+oupPutDoc01-infoSafeViolation01-irregularOperation01)*projectWeighting01;

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg01 = new BigDecimal(KPICal);
            entity.setKPICal(operationAvgTimeBg01.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());


            //计算加分KPI
            double jiafenKPI = entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference();
            //KPI保留两位小数
            BigDecimal operationAvgTimeBg02 = new BigDecimal(jiafenKPI);
            entity.setKPIjiafen(operationAvgTimeBg02.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算平均KPI
            if(bydEntity.getAvgKPIFlag() == 1){
                if(operaCounts != 0){
                    BigDecimal operationAvgTimeBg05 = new BigDecimal(entity.getKPIjiafen()/operaCounts);
                    entity.setKPIjiafen(operationAvgTimeBg05.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
            }

            if(entity.getEmployeeGroup() == null){
                entity.setEmployeeGroup("");
            }
        }

        //相同employee_group去重+计算KPI+计算KPICal+计算KPIjiafen+计算KPIjianfen
        List<BYDEntity> kpiEntityList = CalculateUtil.calEmployeeGroupKPI(nameCompanyGroupList);

        //计算加分环比
        for (BYDEntity entity : kpiEntityList) {
            double jiafenKPI = entity.getKPIjiafen();
            double KPICal = entity.getKPICal();
            if((KPICal<0.0001)&&(KPICal>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else if((jiafenKPI<0.0001)&&(jiafenKPI>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else{
                String linkRatio = CalculateUtil.calLinkRatio(jiafenKPI, KPICal);
                entity.setKPILinkRatioJIAFEN(linkRatio);
            }
        }

        //对KPI进行降序排列
        Collections.sort(kpiEntityList, new Comparator<BYDEntity>() {
            @Override
            public int compare(BYDEntity o1, BYDEntity o2) {
                if (o1.getKPIjiafen() > o2.getKPIjiafen()) {
                    return -1;
                }
                if (o1.getKPIjiafen() == o2.getKPIjiafen()) {
                    return 0;
                }
                return 1;
            }
        });

        //分页
        List tableTotalList = new ArrayList();
        if (bydEntity.getPagesSize() != 0 && bydEntity.getPagesNum() != 0) {
            //tableList分页处理，pagesSize和pagesNum不能为空
            PageModelUtil pm = new PageModelUtil(kpiEntityList, bydEntity.getPagesSize());//每页显示条数
            //总页数
            int totalPages = pm.getTotalPages();
            //总条数
            int totalRows = pm.getTotalRows();
            kpiEntityList = pm.getObjects(bydEntity.getPagesNum());

            tableTotalList.add(totalRows);
            tableTotalList.add(totalPages);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("tableTotalList", tableTotalList);
        resultMap.put("kpiRankingList", kpiEntityList);
        return resultMap;
    }

    //4.部门KPI,减分排行
    @Override
    public Map queryGroupMinusRanking(BYDEntity bydEntity){
        //计算查询日期排名
        String actionBeginTime = bydEntity.getActionBeginTime();
        String actionEndTime = bydEntity.getActionEndTime();

        //首先获取数据库所有姓名+公司+部门+hostname
        List<BYDEntity> nameCompanyGroupList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                List<BYDEntity> entityList = bydMapper.queryComGroupName(bydEntity);
                if (entityList.size() != 0) {
                    if (null != entityList.get(0)) {
                        nameCompanyGroupList.addAll(entityList);
                    }
                }
            }
        }

        //list根据employee_name、host_name
        nameCompanyGroupList = nameCompanyGroupList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getEmployeeName() + ";" + o.getHostName()))), ArrayList::new));

        //计算查询日期前一段时间的排名，用于计算环比
        int i = DateUtil.calDays(actionBeginTime, actionEndTime);
        String actionBeginTime01 = DateUtil.calSomeDate(actionBeginTime, i);


        //查询场景次数+工时
        for (BYDEntity entity : nameCompanyGroupList) {
            //entity.setActionBeginTime(actionBeginTime);
            //entity.setActionEndTime(actionEndTime);
            int operaCounts = 0;
            double workingTimes = 0;

            int operaCounts01 = 0;
            double workingTimes01 = 0;

            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    //查询当前区间的数据
                    entity.setActionBeginTime(actionBeginTime);
                    entity.setActionEndTime(actionEndTime);
                    entity.setTableName(sceneTableName);
                    //场景次数
                    int operaCount= bydMapper.queryOperaCount(entity);
                    operaCounts = operaCounts + operaCount;
/*                    if(operaCount>0){
                        //工时
                        double workingTime = bydMapper.queryWorkingTime(entity);
                        workingTimes = workingTimes + workingTime;
                    }*/

                    //计算前一段区间的数据，用于计算环比
                    entity.setActionBeginTime(actionBeginTime01);
                    entity.setActionEndTime(actionBeginTime);
                    //场景次数
                    int operaCount01= bydMapper.queryOperaCount(entity);
                    operaCounts01 = operaCounts01 + operaCount01;
/*                    if(operaCount01>0){
                        //工时
                        double workingTime01 = bydMapper.queryWorkingTime(entity);
                        workingTimes01 = workingTimes01 + workingTime01;
                    }*/
                    //
                }
            }
            //
            //加分：场景次数
            entity.setOperaCount(operaCounts);

            //加分：工时，当前区间
            entity.setActionBeginTime(actionBeginTime);
            entity.setActionEndTime(actionEndTime);
            double workingTime01 = bydMapper.queryWorkingTime(entity);
            entity.setWorkingHours(millisecondToSecond(workingTime01));

            //加分：产出文档,暂时写死
            entity.setOutputDocument(10);
            //加分：文档引用,暂时写死
            entity.setDocumentReference(9);
            //减分：信息安全违规,暂时写死
            entity.setInfoSafeViolation(1);
            //减分：不规范操作,暂时写死
            entity.setIrregularOperation(2);
            //项目加权,暂时写死
            entity.setProjectWeighting(1);

            //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
            double allKPI = (entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference()-
                    entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg = new BigDecimal(allKPI);
            entity.setKPI(operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算KPICal用于计算环比
            //加分：场景次数
            int operaCount01 = operaCounts01;

            //加分：工时，上个区间
            entity.setActionBeginTime(actionBeginTime01);
            entity.setActionEndTime(actionBeginTime);
            double workingTime02 = bydMapper.queryWorkingTime(entity);
            double workingHour01 = millisecondToSecond(workingTime02);

            //加分：产出文档,暂时写死
            int oupPutDoc01 = 10;
            //加分：文档引用,暂时写死
            int documentReference01 = 9;
            //减分：信息安全违规,暂时写死
            int infoSafeViolation01 = 1;
            //减分：不规范操作,暂时写死
            int irregularOperation01 = 2;
            //项目加权,暂时写死
            int projectWeighting01 = 1;

            double KPICal = (operaCount01+workingHour01+documentReference01+oupPutDoc01-infoSafeViolation01-irregularOperation01)*projectWeighting01;

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg01 = new BigDecimal(KPICal);
            entity.setKPICal(operationAvgTimeBg01.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算减分KPI
            double jianfenKPI = entity.getInfoSafeViolation()+entity.getIrregularOperation();
            BigDecimal operationAvgTimeBg03 = new BigDecimal(jianfenKPI);
            entity.setKPIjianfen(operationAvgTimeBg03.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算平均KPI
            if(bydEntity.getAvgKPIFlag() == 1){
                if(operaCounts != 0){
                    BigDecimal operationAvgTimeBg06 = new BigDecimal(entity.getKPIjianfen()/operaCounts);
                    entity.setKPIjianfen(operationAvgTimeBg06.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
            }

            if(entity.getEmployeeGroup() == null){
                entity.setEmployeeGroup("");
            }
        }

        //相同employee_group去重+计算KPI+计算KPICal+计算KPIjiafen+计算KPIjianfen
        List<BYDEntity> kpiEntityList = CalculateUtil.calEmployeeGroupKPI(nameCompanyGroupList);

        //计算减分环比
        for (BYDEntity entity : kpiEntityList) {
            double jianfenKPI = entity.getKPIjianfen();
            double KPICal = entity.getKPICal();
            if((KPICal<0.0001)&&(KPICal>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else if((jianfenKPI<0.0001)&&(jianfenKPI>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else{
                String linkRatio = CalculateUtil.calLinkRatio(jianfenKPI, KPICal);
                entity.setKPILinkRatioJIANFEN(linkRatio);
            }
        }

        //对KPI进行降序排列
        Collections.sort(kpiEntityList, new Comparator<BYDEntity>() {
            @Override
            public int compare(BYDEntity o1, BYDEntity o2) {
                if (o1.getKPIjianfen() > o2.getKPIjianfen()) {
                    return -1;
                }
                if (o1.getKPIjianfen() == o2.getKPIjianfen()) {
                    return 0;
                }
                return 1;
            }
        });

        //分页
        List tableTotalList = new ArrayList();
        if (bydEntity.getPagesSize() != 0 && bydEntity.getPagesNum() != 0) {
            //tableList分页处理，pagesSize和pagesNum不能为空
            PageModelUtil pm = new PageModelUtil(kpiEntityList, bydEntity.getPagesSize());//每页显示条数
            //总页数
            int totalPages = pm.getTotalPages();
            //总条数
            int totalRows = pm.getTotalRows();
            kpiEntityList = pm.getObjects(bydEntity.getPagesNum());

            tableTotalList.add(totalRows);
            tableTotalList.add(totalPages);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("tableTotalList", tableTotalList);
        resultMap.put("kpiRankingList", kpiEntityList);
        return resultMap;
    }

    //公司KPI排名，总查询
    @Override
    public Map queryCompanyRankingAll(BYDEntity bydEntity) {
        //计算查询日期排名
        String actionBeginTime = bydEntity.getActionBeginTime();
        String actionEndTime = bydEntity.getActionEndTime();

        String[] selectField = bydEntity.getSelectField();

        //首先获取数据库所有姓名+公司+部门+hostname
        List<BYDEntity> nameCompanyGroupList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                if(selectField.length > 0) {
                    for (int i = 0; i < selectField.length; i++) {
                        String company = selectField[i];
                        bydEntity.setCompany(company);
                        List<BYDEntity> entityList = bydMapper.queryComGroupName(bydEntity);
                        if (entityList.size() != 0) {
                            if (null != entityList.get(0)) {
                                nameCompanyGroupList.addAll(entityList);
                            }
                        }
                    }
                }
                List<BYDEntity> entityList = bydMapper.queryComGroupName(bydEntity);
                if (entityList.size() != 0) {
                    if (null != entityList.get(0)) {
                        nameCompanyGroupList.addAll(entityList);
                    }
                }
            }
        }

        //list根据employee_name、host_name
        nameCompanyGroupList = nameCompanyGroupList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getEmployeeName() + ";" + o.getHostName()))), ArrayList::new));

        //计算查询日期前一段时间的排名，用于计算环比
        int i = DateUtil.calDays(actionBeginTime, actionEndTime);
        String actionBeginTime01 = DateUtil.calSomeDate(actionBeginTime, i);

        //查询场景次数+工时
        for (BYDEntity entity : nameCompanyGroupList) {
            //entity.setActionBeginTime(actionBeginTime);
            //entity.setActionEndTime(actionEndTime);
            int operaCounts = 0;
            double workingTimes = 0;

            int operaCounts01 = 0;
            double workingTimes01 = 0;
            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    //查询当前区间的数据
                    entity.setActionBeginTime(actionBeginTime);
                    entity.setActionEndTime(actionEndTime);
                    entity.setTableName(sceneTableName);
                    //场景次数
                    int operaCount= bydMapper.queryOperaCount(entity);
                    operaCounts = operaCounts + operaCount;
/*                    if(operaCount>0){
                        //工时
                        double workingTime = bydMapper.queryWorkingTime(entity);
                        workingTimes = workingTimes + workingTime;
                    }*/
                    //

                    //计算前一段区间的数据，用于计算环比
                    entity.setActionBeginTime(actionBeginTime01);
                    entity.setActionEndTime(actionBeginTime);
                    //场景次数
                    int operaCount01= bydMapper.queryOperaCount(entity);
                    operaCounts01 = operaCounts01 + operaCount01;
/*                    if(operaCount01>0){
                        //工时
                        double workingTime01 = bydMapper.queryWorkingTime(entity);
                        workingTimes01 = workingTimes01 + workingTime01;
                    }*/
                    //
                }
            }
            //
            //加分：场景次数
            entity.setOperaCount(operaCounts);

            //加分：工时，当前区间
            entity.setActionBeginTime(actionBeginTime);
            entity.setActionEndTime(actionEndTime);
            double workingTime01 = bydMapper.queryWorkingTime(entity);
            entity.setWorkingHours(millisecondToSecond(workingTime01));

            //加分：产出文档,暂时写死
            entity.setOutputDocument(10);
            //加分：文档引用,暂时写死
            entity.setDocumentReference(9);
            //减分：信息安全违规,暂时写死
            entity.setInfoSafeViolation(1);
            //减分：不规范操作,暂时写死
            entity.setIrregularOperation(2);
            //项目加权,暂时写死
            entity.setProjectWeighting(1);

            //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
            double allKPI = (entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference()-
                    entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg = new BigDecimal(allKPI);
            entity.setKPI(operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算KPICal用于计算环比
            //加分：场景次数
            int operaCount01 = operaCounts01;

            //加分：工时，上个区间
            entity.setActionBeginTime(actionBeginTime01);
            entity.setActionEndTime(actionBeginTime);
            double workingTime02 = bydMapper.queryWorkingTime(entity);
            double workingHour01 = millisecondToSecond(workingTime02);

            //加分：产出文档,暂时写死
            int oupPutDoc01 = 10;
            //加分：文档引用,暂时写死
            int documentReference01 = 9;
            //减分：信息安全违规,暂时写死
            int infoSafeViolation01 = 1;
            //减分：不规范操作,暂时写死
            int irregularOperation01 = 2;
            //项目加权,暂时写死
            int projectWeighting01 = 1;

            double KPICal = (operaCount01+workingHour01+documentReference01+oupPutDoc01-infoSafeViolation01-irregularOperation01)*projectWeighting01;

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg01 = new BigDecimal(KPICal);
            entity.setKPICal(operationAvgTimeBg01.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算加分KPI
            double jiafenKPI = entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference();
            //KPI保留两位小数
            BigDecimal operationAvgTimeBg02 = new BigDecimal(jiafenKPI);
            entity.setKPIjiafen(operationAvgTimeBg02.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            //计算减分KPI
            double jianfenKPI = entity.getInfoSafeViolation()+entity.getIrregularOperation();
            BigDecimal operationAvgTimeBg03 = new BigDecimal(jianfenKPI);
            entity.setKPIjianfen(operationAvgTimeBg03.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            if(entity.getCompany() == null){
                entity.setCompany("");
            }
        }

        //相同company去重+计算KPI+计算KPICal+计算KPIjiafen+计算KPIjianfen
        List<BYDEntity> kpiEntityList = CalculateUtil.calEmployeeCompanyKPI(nameCompanyGroupList);

        //计算环比
        for (BYDEntity entity : kpiEntityList) {
            double KPI = entity.getKPI();
            double KPICal = entity.getKPICal();
            if((KPICal<0.0001)&&(KPICal>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else if((KPI<0.0001)&&(KPI>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else{
                String linkRatio = CalculateUtil.calLinkRatio(KPI, KPICal);
                entity.setKPILinkRatio(linkRatio);
            }

        }

        //计算加分环比
        for (BYDEntity entity : kpiEntityList) {
            double jiafenKPI = entity.getKPIjiafen();
            double KPICal = entity.getKPICal();
            if((KPICal<0.0001)&&(KPICal>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else if((jiafenKPI<0.0001)&&(jiafenKPI>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else{
                String linkRatio = CalculateUtil.calLinkRatio(jiafenKPI, KPICal);
                entity.setKPILinkRatioJIAFEN(linkRatio);
            }
        }

        //计算减分环比
        for (BYDEntity entity : kpiEntityList) {
            double jianfenKPI = entity.getKPIjianfen();
            double KPICal = entity.getKPICal();
            if((KPICal<0.0001)&&(KPICal>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else if((jianfenKPI<0.0001)&&(jianfenKPI>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else{
                String linkRatio = CalculateUtil.calLinkRatio(jianfenKPI, KPICal);
                entity.setKPILinkRatioJIANFEN(linkRatio);
            }
        }

        //对KPI进行降序排列
        Collections.sort(kpiEntityList, new Comparator<BYDEntity>() {
            @Override
            public int compare(BYDEntity o1, BYDEntity o2) {
                if (o1.getKPI() > o2.getKPI()) {
                    return -1;
                }
                if (o1.getKPI() == o2.getKPI()) {
                    return 0;
                }
                return 1;
            }
        });

        HashMap resultMap = new HashMap();

        //分页前计算总KPI
        double allKPI = kpiEntityList.stream().mapToDouble(BYDEntity::getKPI).sum();
        //KPI保留两位小数
        BigDecimal operationAvgTimeBg = new BigDecimal(allKPI);
        allKPI = operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        //员工KPI贡献，分页前list
        resultMap.put("contributionList", kpiEntityList);

        //分页
        List tableTotalList = new ArrayList();
        if (bydEntity.getPagesSize() != 0 && bydEntity.getPagesNum() != 0) {
            //tableList分页处理，pagesSize和pagesNum不能为空
            PageModelUtil pm = new PageModelUtil(kpiEntityList, bydEntity.getPagesSize());//每页显示条数
            //总页数
            int totalPages = pm.getTotalPages();
            //总条数
            int totalRows = pm.getTotalRows();
            kpiEntityList = pm.getObjects(bydEntity.getPagesNum());

            tableTotalList.add(totalRows);
            tableTotalList.add(totalPages);
        }

        resultMap.put("tableTotalList", tableTotalList);
        resultMap.put("kpiRankingList", kpiEntityList);
        resultMap.put("allKPI", allKPI);
        return resultMap;
    }

    //1.公司KPI贡献
    @Override
    public Map queryCompanyKPIContribution(BYDEntity bydEntity) {
        String actionBeginTime = bydEntity.getActionBeginTime();
        String actionEndTime = bydEntity.getActionEndTime();

        //首先获取数据库所有姓名+公司+部门+hostname
        List<BYDEntity> nameCompanyGroupList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                List<BYDEntity> entityList = bydMapper.queryComGroupName(bydEntity);
                if (entityList.size() != 0) {
                    if (null != entityList.get(0)) {
                        nameCompanyGroupList.addAll(entityList);
                    }
                }
            }
        }

        //list根据employee_name、host_name
        nameCompanyGroupList = nameCompanyGroupList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getEmployeeName() + ";" + o.getHostName()))), ArrayList::new));

        //查询场景次数+工时
        for (BYDEntity entity : nameCompanyGroupList) {
            //entity.setActionBeginTime(actionBeginTime);
            //entity.setActionEndTime(actionEndTime);
            int operaCounts = 0;
            double workingTimes = 0;

            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    //查询当前区间的数据
                    entity.setActionBeginTime(actionBeginTime);
                    entity.setActionEndTime(actionEndTime);
                    entity.setTableName(sceneTableName);
                    //场景次数
                    int operaCount= bydMapper.queryOperaCount(entity);
                    operaCounts = operaCounts + operaCount;
/*                    if(operaCount>0){
                        //工时
                        double workingTime = bydMapper.queryWorkingTime(entity);
                        workingTimes = workingTimes + workingTime;
                    }*/
                }
            }
            //加分：场景次数
            entity.setOperaCount(operaCounts);

            //加分：工时，当前区间
            entity.setActionBeginTime(actionBeginTime);
            entity.setActionEndTime(actionEndTime);
            double workingTime01 = bydMapper.queryWorkingTime(entity);
            entity.setWorkingHours(millisecondToSecond(workingTime01));

            //加分：产出文档,暂时写死
            entity.setOutputDocument(10);
            //加分：文档引用,暂时写死
            entity.setDocumentReference(9);
            //减分：信息安全违规,暂时写死
            entity.setInfoSafeViolation(1);
            //减分：不规范操作,暂时写死
            entity.setIrregularOperation(2);
            //项目加权,暂时写死
            entity.setProjectWeighting(1);

            double allKPI = 0.0;
            if(bydEntity.getKpiContributionFlag() == 1){//净贡献
                //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
                allKPI = (entity.getOperaCount()+entity.getWorkingHours()+entity.getDocumentReference()+entity.getOutputDocument()-entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();
            }else if(bydEntity.getKpiContributionFlag() == 2){//加分贡献
                allKPI = (entity.getOperaCount()+entity.getWorkingHours()+entity.getDocumentReference()+entity.getOutputDocument())*entity.getProjectWeighting();
            }else if(bydEntity.getKpiContributionFlag() == 3){//减分贡献
                allKPI = (entity.getInfoSafeViolation()+entity.getIrregularOperation())*entity.getProjectWeighting();
            }

            //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
            //double allKPI = (entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference()-
            //       entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg = new BigDecimal(allKPI);
            entity.setKPI(operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            if(entity.getCompany() == null){
                entity.setCompany("");
            }
        }

        //相同company去重+计算KPI+计算KPICal+计算KPIjiafen+计算KPIjianfen
        List<BYDEntity> kpiEntityList = CalculateUtil.calEmployeeCompanyKPI(nameCompanyGroupList);

        //对KPI进行降序排列
        Collections.sort(kpiEntityList, new Comparator<BYDEntity>() {
            @Override
            public int compare(BYDEntity o1, BYDEntity o2) {
                if (o1.getKPI() > o2.getKPI()) {
                    return -1;
                }
                if (o1.getKPI() == o2.getKPI()) {
                    return 0;
                }
                return 1;
            }
        });

        //计算总KPI
        double allKPI = kpiEntityList.stream().mapToDouble(BYDEntity::getKPI).sum();

        //KPI保留两位小数
        BigDecimal operationAvgTimeBg = new BigDecimal(allKPI);
        allKPI = operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        HashMap resultMap = new HashMap();
        resultMap.put("contributionList", kpiEntityList);
        resultMap.put("allKPI", allKPI);
        return resultMap;
    }

    //2.公司KPI排名
    @Override
    public Map queryCompanyKPIRanking(BYDEntity bydEntity) {
        //计算查询日期排名
        String actionBeginTime = bydEntity.getActionBeginTime();
        String actionEndTime = bydEntity.getActionEndTime();

        //首先获取数据库所有姓名+公司+部门+hostname
        List<BYDEntity> nameCompanyGroupList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                List<BYDEntity> entityList = bydMapper.queryComGroupName(bydEntity);
                if (entityList.size() != 0) {
                    if (null != entityList.get(0)) {
                        nameCompanyGroupList.addAll(entityList);
                    }
                }
            }
        }

        //list根据employee_name、host_name
        nameCompanyGroupList = nameCompanyGroupList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getEmployeeName() + ";" + o.getHostName()))), ArrayList::new));

        //计算查询日期前一段时间的排名，用于计算环比
        int i = DateUtil.calDays(actionBeginTime, actionEndTime);
        String actionBeginTime01 = DateUtil.calSomeDate(actionBeginTime, i);

        //查询场景次数+工时
        for (BYDEntity entity : nameCompanyGroupList) {
            //entity.setActionBeginTime(actionBeginTime);
            //entity.setActionEndTime(actionEndTime);
            int operaCounts = 0;
            double workingTimes = 0;

            int operaCounts01 = 0;
            double workingTimes01 = 0;
            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    //查询当前区间的数据
                    entity.setActionBeginTime(actionBeginTime);
                    entity.setActionEndTime(actionEndTime);
                    entity.setTableName(sceneTableName);
                    //场景次数
                    int operaCount= bydMapper.queryOperaCount(entity);
                    operaCounts = operaCounts + operaCount;
/*                    if(operaCount>0){
                        //工时
                        double workingTime = bydMapper.queryWorkingTime(entity);
                        workingTimes = workingTimes + workingTime;
                    }*/
                    //

                    //计算前一段区间的数据，用于计算环比
                    entity.setActionBeginTime(actionBeginTime01);
                    entity.setActionEndTime(actionBeginTime);
                    //场景次数
                    int operaCount01= bydMapper.queryOperaCount(entity);
                    operaCounts01 = operaCounts01 + operaCount01;
/*                    if(operaCount01>0){
                        //工时
                        double workingTime01 = bydMapper.queryWorkingTime(entity);
                        workingTimes01 = workingTimes01 + workingTime01;
                    }*/
                    //
                }
            }
            //
            //加分：场景次数
            entity.setOperaCount(operaCounts);

            //加分：工时，当前区间
            entity.setActionBeginTime(actionBeginTime);
            entity.setActionEndTime(actionEndTime);
            double workingTime01 = bydMapper.queryWorkingTime(entity);
            entity.setWorkingHours(millisecondToSecond(workingTime01));

            //加分：产出文档,暂时写死
            entity.setOutputDocument(10);
            //加分：文档引用,暂时写死
            entity.setDocumentReference(9);
            //减分：信息安全违规,暂时写死
            entity.setInfoSafeViolation(1);
            //减分：不规范操作,暂时写死
            entity.setIrregularOperation(2);
            //项目加权,暂时写死
            entity.setProjectWeighting(1);

            //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
            double allKPI = (entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference()-
                    entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg = new BigDecimal(allKPI);
            entity.setKPI(operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算KPICal用于计算环比
            //加分：场景次数
            int operaCount01 = operaCounts01;

            //加分：工时，上个区间
            entity.setActionBeginTime(actionBeginTime01);
            entity.setActionEndTime(actionBeginTime);
            double workingTime02 = bydMapper.queryWorkingTime(entity);
            double workingHour01 = millisecondToSecond(workingTime02);

            //加分：产出文档,暂时写死
            int oupPutDoc01 = 10;
            //加分：文档引用,暂时写死
            int documentReference01 = 9;
            //减分：信息安全违规,暂时写死
            int infoSafeViolation01 = 1;
            //减分：不规范操作,暂时写死
            int irregularOperation01 = 2;
            //项目加权,暂时写死
            int projectWeighting01 = 1;

            double KPICal = (operaCount01+workingHour01+documentReference01+oupPutDoc01-infoSafeViolation01-irregularOperation01)*projectWeighting01;

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg01 = new BigDecimal(KPICal);
            entity.setKPICal(operationAvgTimeBg01.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算平均KPI
            if(bydEntity.getAvgKPIFlag() == 1){
                if(operaCounts != 0){
                    BigDecimal operationAvgTimeBg04 = new BigDecimal(entity.getKPI()/operaCounts);
                    entity.setKPI(operationAvgTimeBg04.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
            }

            if(entity.getCompany() == null){
                entity.setCompany("");
            }
        }

        //相同company去重+计算KPI+计算KPICal+计算KPIjiafen+计算KPIjianfen
        List<BYDEntity> kpiEntityList = CalculateUtil.calEmployeeCompanyKPI(nameCompanyGroupList);

        //计算环比
        for (BYDEntity entity : kpiEntityList) {
            double KPI = entity.getKPI();
            double KPICal = entity.getKPICal();
            if((KPICal<0.0001)&&(KPICal>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else{
                String linkRatio = CalculateUtil.calLinkRatio(KPI, KPICal);
                entity.setKPILinkRatio(linkRatio);
            }
        }

        //对KPI进行降序排列
        Collections.sort(kpiEntityList, new Comparator<BYDEntity>() {
            @Override
            public int compare(BYDEntity o1, BYDEntity o2) {
                if (o1.getKPI() > o2.getKPI()) {
                    return -1;
                }
                if (o1.getKPI() == o2.getKPI()) {
                    return 0;
                }
                return 1;
            }
        });

        //分页
        List tableTotalList = new ArrayList();
        if (bydEntity.getPagesSize() != 0 && bydEntity.getPagesNum() != 0) {
            //tableList分页处理，pagesSize和pagesNum不能为空
            PageModelUtil pm = new PageModelUtil(kpiEntityList, bydEntity.getPagesSize());//每页显示条数
            //总页数
            int totalPages = pm.getTotalPages();
            //总条数
            int totalRows = pm.getTotalRows();
            kpiEntityList = pm.getObjects(bydEntity.getPagesNum());

            tableTotalList.add(totalRows);
            tableTotalList.add(totalPages);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("tableTotalList", tableTotalList);
        resultMap.put("kpiRankingList", kpiEntityList);
        return resultMap;
    }

    //3.公司KPI,加分排行
    @Override
    public Map queryCompanyBonusRanking(BYDEntity bydEntity){
        //计算查询日期排名
        String actionBeginTime = bydEntity.getActionBeginTime();
        String actionEndTime = bydEntity.getActionEndTime();

        //首先获取数据库所有姓名+公司+部门+hostname
        List<BYDEntity> nameCompanyGroupList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                List<BYDEntity> entityList = bydMapper.queryComGroupName(bydEntity);
                if (entityList.size() != 0) {
                    if (null != entityList.get(0)) {
                        nameCompanyGroupList.addAll(entityList);
                    }
                }
            }
        }

        //list根据employee_name、host_name
        nameCompanyGroupList = nameCompanyGroupList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getEmployeeName() + ";" + o.getHostName()))), ArrayList::new));

        //计算查询日期前一段时间的排名，用于计算环比
        int i = DateUtil.calDays(actionBeginTime, actionEndTime);
        String actionBeginTime01 = DateUtil.calSomeDate(actionBeginTime, i);


        //查询场景次数+工时
        for (BYDEntity entity : nameCompanyGroupList) {
            //entity.setActionBeginTime(actionBeginTime);
            //entity.setActionEndTime(actionEndTime);
            int operaCounts = 0;
            double workingTimes = 0;

            int operaCounts01 = 0;
            double workingTimes01 = 0;

            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    //查询当前区间的数据
                    entity.setActionBeginTime(actionBeginTime);
                    entity.setActionEndTime(actionEndTime);
                    entity.setTableName(sceneTableName);
                    //场景次数
                    int operaCount= bydMapper.queryOperaCount(entity);
                    operaCounts = operaCounts + operaCount;
/*                    if(operaCount>0){
                        //工时
                        double workingTime = bydMapper.queryWorkingTime(entity);
                        workingTimes = workingTimes + workingTime;
                    }*/

                    //计算前一段区间的数据，用于计算环比
                    entity.setActionBeginTime(actionBeginTime01);
                    entity.setActionEndTime(actionBeginTime);
                    //场景次数
                    int operaCount01= bydMapper.queryOperaCount(entity);
                    operaCounts01 = operaCounts01 + operaCount01;
/*                    if(operaCount01>0){
                        //工时
                        double workingTime01 = bydMapper.queryWorkingTime(entity);
                        workingTimes01 = workingTimes01 + workingTime01;
                    }*/
                    //
                }
            }
            //
            //加分：场景次数
            entity.setOperaCount(operaCounts);

            //加分：工时，当前区间
            entity.setActionBeginTime(actionBeginTime);
            entity.setActionEndTime(actionEndTime);
            double workingTime01 = bydMapper.queryWorkingTime(entity);
            entity.setWorkingHours(millisecondToSecond(workingTime01));

            //加分：产出文档,暂时写死
            entity.setOutputDocument(10);
            //加分：文档引用,暂时写死
            entity.setDocumentReference(9);
            //减分：信息安全违规,暂时写死
            entity.setInfoSafeViolation(1);
            //减分：不规范操作,暂时写死
            entity.setIrregularOperation(2);
            //项目加权,暂时写死
            entity.setProjectWeighting(1);

            //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
            double allKPI = (entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference()-
                    entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg = new BigDecimal(allKPI);
            entity.setKPI(operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算KPICal用于计算环比
            //加分：场景次数
            int operaCount01 = operaCounts01;

            //加分：工时，上个区间
            entity.setActionBeginTime(actionBeginTime01);
            entity.setActionEndTime(actionBeginTime);
            double workingTime02 = bydMapper.queryWorkingTime(entity);
            double workingHour01 = millisecondToSecond(workingTime02);

            //加分：产出文档,暂时写死
            int oupPutDoc01 = 10;
            //加分：文档引用,暂时写死
            int documentReference01 = 9;
            //减分：信息安全违规,暂时写死
            int infoSafeViolation01 = 1;
            //减分：不规范操作,暂时写死
            int irregularOperation01 = 2;
            //项目加权,暂时写死
            int projectWeighting01 = 1;

            double KPICal = (operaCount01+workingHour01+documentReference01+oupPutDoc01-infoSafeViolation01-irregularOperation01)*projectWeighting01;

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg01 = new BigDecimal(KPICal);
            entity.setKPICal(operationAvgTimeBg01.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());


            //计算加分KPI
            double jiafenKPI = entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference();
            //KPI保留两位小数
            BigDecimal operationAvgTimeBg02 = new BigDecimal(jiafenKPI);
            entity.setKPIjiafen(operationAvgTimeBg02.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算平均KPI
            if(bydEntity.getAvgKPIFlag() == 1){
                if(operaCounts != 0){
                    BigDecimal operationAvgTimeBg05 = new BigDecimal(entity.getKPIjiafen()/operaCounts);
                    entity.setKPIjiafen(operationAvgTimeBg05.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
            }

            if(entity.getCompany() == null){
                entity.setCompany("");
            }
        }

        //相同company去重+计算KPI+计算KPICal+计算KPIjiafen+计算KPIjianfen
        List<BYDEntity> kpiEntityList = CalculateUtil.calEmployeeCompanyKPI(nameCompanyGroupList);

        //计算加分环比
        for (BYDEntity entity : kpiEntityList) {
            double jiafenKPI = entity.getKPIjiafen();
            double KPICal = entity.getKPICal();
            if((KPICal<0.0001)&&(KPICal>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else if((jiafenKPI<0.0001)&&(jiafenKPI>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else{
                String linkRatio = CalculateUtil.calLinkRatio(jiafenKPI, KPICal);
                entity.setKPILinkRatioJIAFEN(linkRatio);
            }
        }

        //对KPI进行降序排列
        Collections.sort(kpiEntityList, new Comparator<BYDEntity>() {
            @Override
            public int compare(BYDEntity o1, BYDEntity o2) {
                if (o1.getKPIjiafen() > o2.getKPIjiafen()) {
                    return -1;
                }
                if (o1.getKPIjiafen() == o2.getKPIjiafen()) {
                    return 0;
                }
                return 1;
            }
        });

        //分页
        List tableTotalList = new ArrayList();
        if (bydEntity.getPagesSize() != 0 && bydEntity.getPagesNum() != 0) {
            //tableList分页处理，pagesSize和pagesNum不能为空
            PageModelUtil pm = new PageModelUtil(kpiEntityList, bydEntity.getPagesSize());//每页显示条数
            //总页数
            int totalPages = pm.getTotalPages();
            //总条数
            int totalRows = pm.getTotalRows();
            kpiEntityList = pm.getObjects(bydEntity.getPagesNum());

            tableTotalList.add(totalRows);
            tableTotalList.add(totalPages);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("tableTotalList", tableTotalList);
        resultMap.put("kpiRankingList", kpiEntityList);
        return resultMap;
    }

    //4.公司KPI,减分排行
    @Override
    public Map queryCompanyMinusRanking(BYDEntity bydEntity){
        //计算查询日期排名
        String actionBeginTime = bydEntity.getActionBeginTime();
        String actionEndTime = bydEntity.getActionEndTime();

        //首先获取数据库所有姓名+公司+部门+hostname
        List<BYDEntity> nameCompanyGroupList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                List<BYDEntity> entityList = bydMapper.queryComGroupName(bydEntity);
                if (entityList.size() != 0) {
                    if (null != entityList.get(0)) {
                        nameCompanyGroupList.addAll(entityList);
                    }
                }
            }
        }

        //list根据employee_name、host_name
        nameCompanyGroupList = nameCompanyGroupList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getEmployeeName() + ";" + o.getHostName()))), ArrayList::new));

        //计算查询日期前一段时间的排名，用于计算环比
        int i = DateUtil.calDays(actionBeginTime, actionEndTime);
        String actionBeginTime01 = DateUtil.calSomeDate(actionBeginTime, i);


        //查询场景次数+工时
        for (BYDEntity entity : nameCompanyGroupList) {
            //entity.setActionBeginTime(actionBeginTime);
            //entity.setActionEndTime(actionEndTime);
            int operaCounts = 0;
            double workingTimes = 0;

            int operaCounts01 = 0;
            double workingTimes01 = 0;

            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    //查询当前区间的数据
                    entity.setActionBeginTime(actionBeginTime);
                    entity.setActionEndTime(actionEndTime);
                    entity.setTableName(sceneTableName);
                    //场景次数
                    int operaCount= bydMapper.queryOperaCount(entity);
                    operaCounts = operaCounts + operaCount;
/*                    if(operaCount>0){
                        //工时
                        double workingTime = bydMapper.queryWorkingTime(entity);
                        workingTimes = workingTimes + workingTime;
                    }*/

                    //计算前一段区间的数据，用于计算环比
                    entity.setActionBeginTime(actionBeginTime01);
                    entity.setActionEndTime(actionBeginTime);
                    //场景次数
                    int operaCount01= bydMapper.queryOperaCount(entity);
                    operaCounts01 = operaCounts01 + operaCount01;
/*                    if(operaCount01>0){
                        //工时
                        double workingTime01 = bydMapper.queryWorkingTime(entity);
                        workingTimes01 = workingTimes01 + workingTime01;
                    }*/
                    //
                }
            }
            //
            //加分：场景次数
            entity.setOperaCount(operaCounts);

            //加分：工时，当前区间
            entity.setActionBeginTime(actionBeginTime);
            entity.setActionEndTime(actionEndTime);
            double workingTime01 = bydMapper.queryWorkingTime(entity);
            entity.setWorkingHours(millisecondToSecond(workingTime01));

            //加分：产出文档,暂时写死
            entity.setOutputDocument(10);
            //加分：文档引用,暂时写死
            entity.setDocumentReference(9);
            //减分：信息安全违规,暂时写死
            entity.setInfoSafeViolation(1);
            //减分：不规范操作,暂时写死
            entity.setIrregularOperation(2);
            //项目加权,暂时写死
            entity.setProjectWeighting(1);

            //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
            double allKPI = (entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference()-
                    entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg = new BigDecimal(allKPI);
            entity.setKPI(operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算KPICal用于计算环比
            //加分：场景次数
            int operaCount01 = operaCounts01;

            //加分：工时，上个区间
            entity.setActionBeginTime(actionBeginTime01);
            entity.setActionEndTime(actionBeginTime);
            double workingTime02 = bydMapper.queryWorkingTime(entity);
            double workingHour01 = millisecondToSecond(workingTime02);

            //加分：产出文档,暂时写死
            int oupPutDoc01 = 10;
            //加分：文档引用,暂时写死
            int documentReference01 = 9;
            //减分：信息安全违规,暂时写死
            int infoSafeViolation01 = 1;
            //减分：不规范操作,暂时写死
            int irregularOperation01 = 2;
            //项目加权,暂时写死
            int projectWeighting01 = 1;

            double KPICal = (operaCount01+workingHour01+documentReference01+oupPutDoc01-infoSafeViolation01-irregularOperation01)*projectWeighting01;

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg01 = new BigDecimal(KPICal);
            entity.setKPICal(operationAvgTimeBg01.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());


            //计算减分KPI
            double jianfenKPI = entity.getInfoSafeViolation()+entity.getIrregularOperation();
            BigDecimal operationAvgTimeBg03 = new BigDecimal(jianfenKPI);
            entity.setKPIjianfen(operationAvgTimeBg03.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //计算平均KPI
            if(bydEntity.getAvgKPIFlag() == 1){
                if(operaCounts != 0){
                    BigDecimal operationAvgTimeBg06 = new BigDecimal(entity.getKPIjianfen()/operaCounts);
                    entity.setKPIjianfen(operationAvgTimeBg06.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
            }

            if(entity.getCompany() == null){
                entity.setCompany("");
            }
        }

        //相同company去重+计算KPI+计算KPICal+计算KPIjiafen+计算KPIjianfen
        List<BYDEntity> kpiEntityList = CalculateUtil.calEmployeeCompanyKPI(nameCompanyGroupList);

        //计算减分环比
        for (BYDEntity entity : kpiEntityList) {
            double jianfenKPI = entity.getKPIjianfen();
            double KPICal = entity.getKPICal();
            if((KPICal<0.0001)&&(KPICal>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else if((jianfenKPI<0.0001)&&(jianfenKPI>-0.0001)){
                entity.setKPILinkRatio("0%");
            }else{
                String linkRatio = CalculateUtil.calLinkRatio(jianfenKPI, KPICal);
                entity.setKPILinkRatioJIANFEN(linkRatio);
            }
        }

        //对KPI进行降序排列
        Collections.sort(kpiEntityList, new Comparator<BYDEntity>() {
            @Override
            public int compare(BYDEntity o1, BYDEntity o2) {
                if (o1.getKPIjianfen() > o2.getKPIjianfen()) {
                    return -1;
                }
                if (o1.getKPIjianfen() == o2.getKPIjianfen()) {
                    return 0;
                }
                return 1;
            }
        });

        //分页
        List tableTotalList = new ArrayList();
        if (bydEntity.getPagesSize() != 0 && bydEntity.getPagesNum() != 0) {
            //tableList分页处理，pagesSize和pagesNum不能为空
            PageModelUtil pm = new PageModelUtil(kpiEntityList, bydEntity.getPagesSize());//每页显示条数
            //总页数
            int totalPages = pm.getTotalPages();
            //总条数
            int totalRows = pm.getTotalRows();
            kpiEntityList = pm.getObjects(bydEntity.getPagesNum());

            tableTotalList.add(totalRows);
            tableTotalList.add(totalPages);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("tableTotalList", tableTotalList);
        resultMap.put("kpiRankingList", kpiEntityList);
        return resultMap;
    }

    public Map getBYDPLMLoginZheXianInfo(BYDEntity bydEntity){
        List<BYDEntity> zheXianListAll = null;
        List<BYDEntity> zheXianListAbnormal = null;
        int zheXianFlag = bydEntity.getZheXianFlag();
        String zheXianDate = bydEntity.getZheXianDate();
        if (zheXianFlag == 1) {//按天
            //所有记录
            zheXianListAll = bydMapper.getBYDPLMLoginZheXianInfoByHour(bydEntity);
            //异常记录
            bydEntity.setExceptionLoginFlag(1);
            zheXianListAbnormal = bydMapper.getBYDPLMLoginZheXianInfoByHour(bydEntity);
        } else if (zheXianFlag == 2) {//按星期
            //所有记录
            bydEntity.setZheXianDate(DateUtil.calculateSundayDate(bydEntity.getZheXianDate()));
            zheXianListAll = bydMapper.getBYDPLMLoginZheXianInfoByWeek(bydEntity);
            //对日期进行排序
            Collections.sort(zheXianListAll, new Comparator<BYDEntity>() {
                @Override
                public int compare(BYDEntity o1, BYDEntity o2) {
                    //升序
                    return o1.getZheXianX().compareTo(o2.getZheXianX());
                }
            });

            //异常记录
            bydEntity.setExceptionLoginFlag(1);
            zheXianListAbnormal = bydMapper.getBYDPLMLoginZheXianInfoByWeek(bydEntity);
            Collections.sort(zheXianListAbnormal, new Comparator<BYDEntity>() {
                @Override
                public int compare(BYDEntity o1, BYDEntity o2) {
                    //升序
                    return o1.getZheXianX().compareTo(o2.getZheXianX());
                }
            });
        } else if (zheXianFlag == 3) {//按月
            String firstMonthDay = DateUtil.getFirstMonthDay(zheXianDate);
            bydEntity.setZheXianDate(firstMonthDay);

            //所有记录
            zheXianListAll = bydMapper.getBYDPLMLoginZheXianInfoByMonth(bydEntity);
            zheXianListAll.remove(zheXianListAll.size() - 1);

            bydEntity.setExceptionLoginFlag(1);
            zheXianListAbnormal = bydMapper.getBYDPLMLoginZheXianInfoByMonth(bydEntity);
            zheXianListAbnormal.remove(zheXianListAbnormal.size() - 1);

        }
        HashMap resultMap = new HashMap();
        resultMap.put("zheXianListAll", zheXianListAll);
        //resultMap.put("zheXianListAbnormal", zheXianListAbnormal);
        return resultMap;
    }

    /*仪表盘功能*/
    //获取登录场景的折线图数据
    @Override
    public Map getLoginCountZheXianInfo(BYDEntity bydEntity) {
        List<String> allCompanyList = new ArrayList<>();
        List<BYDEntity> calCompanyList = new ArrayList<>();
        JSONArray array = new JSONArray();

        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                List<String> companyList = bydMapper.getAllCompany(bydEntity);
                if (companyList.size() != 0) {
                    if (null != companyList.get(0)) {
                        allCompanyList.addAll(companyList);
                    }
                }
            }
        }

        //去重
        if (allCompanyList.size() != 0) {
            HashSet<String> hashSet02 = new HashSet<String>(allCompanyList);
            allCompanyList.clear();
            allCompanyList.addAll(hashSet02);
            Collections.sort(allCompanyList);
        }

        int zheXianFlag = bydEntity.getZheXianFlag();
        for (String companyName:allCompanyList) {
            JSONObject arrayElementOne = new JSONObject();
            arrayElementOne.element("companyName",companyName);
            bydEntity.setCompany(companyName);
            if (zheXianFlag == 1){
                calCompanyList = bydMapper.getLoginCountZheXianByDay(bydEntity);
            }else if(zheXianFlag == 2){
                calCompanyList = bydMapper.getLoginCountZheXianByWeek(bydEntity);
                //对日期进行排序
                Collections.sort(calCompanyList, new Comparator<BYDEntity>() {
                    @Override
                    public int compare(BYDEntity o1, BYDEntity o2) {
                        //升序
                        return o1.getZheXianX().compareTo(o2.getZheXianX());
                    }
                });
            }else if(zheXianFlag == 3){
                String zheXianDate = bydEntity.getZheXianDate();
                String firstMonthDay = DateUtil.getFirstMonthDay(zheXianDate);
                bydEntity.setZheXianDate(firstMonthDay);
                calCompanyList = bydMapper.getLoginCountZheXianByMonth(bydEntity);
            }

            JSONArray arrayElementOneArrayElementOne = JSONArray.fromObject(calCompanyList);
            arrayElementOne.element("children",arrayElementOneArrayElementOne);
            array.add(arrayElementOne);
            //array.add(calCompanyList);
        }
        Map resultMap = new HashMap<>();
        resultMap.put("companyZheXianList",array);
        return resultMap;
    }

    //当天客户端总事件和异常事件
    @Override
    public Map getClientEventCount() {
        int allClientEventCount = 0;
        int abnormalClientEventCount = 0;

        //查询客户事件总数量
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        int allCount = 0;
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                allCount = bydMapper.getClientAllEventCount(sceneTableName);
            }
            allClientEventCount = allClientEventCount + allCount;
        }

        //查询客户端事件异常数量
        int abnormalCountLogin = bydMapper.getAbnormalCountLogin();
        int abnormalCountViewDoc = bydMapper.getAbnormalCountViewDoc();
        int abnormalCountDownDoc = bydMapper.getAbnormalCountDownDoc();


        HashMap resultMap = new HashMap();
        resultMap.put("allClientEventCount", allClientEventCount);
        //resultMap.put("abnormalClientEventCount", abnormalCountLogin+abnormalCountViewDoc+abnormalCountDownDoc);
        return resultMap;
    }

    //Map地图展示跨公司访问
    @Override
    public Map getMapCrossCompanyInfo() {
        List<BYDEntity> mapCrossCompanyList = bydMapper.getMapCrossCompanyInfo();
        HashMap resultMap = new HashMap();
        resultMap.put("mapCrossCompanyList", mapCrossCompanyList);
        return resultMap;
}

    //计算总工时+总KPI
    @Override
    public Map getAllWorkingHours() {
        /*计算工时*/
        //计算昨天日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(calendar.DATE,-1);
        String yesterday= sdf.format(calendar.getTime());

        //查询昨天所有工时
        double allWorkingHoursYesterday = bydMapper.getAllWorkingHoursYesterday(yesterday);

        //查询当天所有工时
        double allWorkingHoursToday = bydMapper.getAllWorkingHoursToday();
        //两天相差工时
        double differenceHours = allWorkingHoursToday-allWorkingHoursYesterday;

        double allWorkingHoursTodayH = millisecondToSecond(allWorkingHoursToday);
        double differenceHoursH = millisecondToSecond(differenceHours);

        HashMap resultMap = new HashMap();
        resultMap.put("allWorkingHoursToday", allWorkingHoursTodayH);
        resultMap.put("differenceHours", differenceHoursH);

        /*计算总KPI*/
        BYDEntity bydEntity = new BYDEntity();
        String todayDate = sdf.format(new Date());

        List<BYDEntity> nameCompanyGroupList = new ArrayList<>();
        List<BYDEntity> yesterDayInfoList = new ArrayList<>();

        List<SceneListEntity> sceneList = baseMapper.getAllScene();

        //首先获取数据库所有姓名+公司+部门+hostname
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                //获取当天操作信息
                bydEntity.setZheXianX(todayDate);
                List<BYDEntity> entityList = bydMapper.queryComGroupNameLike(bydEntity);
                if (entityList.size() != 0) {
                    if (null != entityList.get(0)) {
                        nameCompanyGroupList.addAll(entityList);
                    }
                }
                //获取前一天操作信息
                bydEntity.setZheXianX(yesterday);
                List<BYDEntity> entityList2 = bydMapper.queryComGroupNameLike(bydEntity);
                if (entityList2.size() != 0) {
                    if (null != entityList2.get(0)) {
                        yesterDayInfoList.addAll(entityList2);
                    }
                }
            }
        }

        //list根据employee_name、host_name去重
        nameCompanyGroupList = nameCompanyGroupList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getEmployeeName() + ";" + o.getHostName()))), ArrayList::new));

        //list根据employee_name、host_name
        yesterDayInfoList = yesterDayInfoList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getEmployeeName() + ";" + o.getHostName()))), ArrayList::new));

        //
        //查询当天场景次数+工时
        for (BYDEntity entity : nameCompanyGroupList) {
            int operaCounts = 0;
            double workingTimes = 0;
            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    entity.setTableName(sceneTableName);
                    entity.setZheXianX(todayDate);
                    //场景次数
                    int operaCount= bydMapper.queryOperaCountLike(entity);
                    operaCounts = operaCounts + operaCount;
                    if(operaCount>0){
                        //工时
                        double workingTime = bydMapper.queryWorkingTimeLike(entity);
                        workingTimes = workingTimes + workingTime;
                    }
                }
            }
            //场景次数
            entity.setOperaCount(operaCounts);
            //工时
            entity.setWorkingHours(millisecondToSecond(workingTimes));
            //产出文档,暂时写死
            entity.setOutputDocument(2);
            //文档引用,暂时写死
            entity.setDocumentReference(3);
            //项目加权,暂时写死
            entity.setProjectWeighting(1);
            //信息安全违规,暂时写死
            entity.setInfoSafeViolation(5);
            //不规范操作,暂时写死
            entity.setIrregularOperation(6);

            //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
            double a = (entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference()-
                    entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg = new BigDecimal(a);
            entity.setKPI(operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }

        double todayKPI = 0;
        for (BYDEntity entity:nameCompanyGroupList) {
            todayKPI = todayKPI + entity.getKPI();
        }
        //

        //查询前一天场景次数+工时
        for (BYDEntity entity : yesterDayInfoList) {
            int operaCounts = 0;
            double workingTimes = 0;
            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    entity.setTableName(sceneTableName);
                    entity.setZheXianX(yesterday);
                    //场景次数
                    int operaCount= bydMapper.queryOperaCountLike(entity);
                    operaCounts = operaCounts + operaCount;
                    if(operaCount>0){
                        //工时
                        double workingTime = bydMapper.queryWorkingTimeLike(entity);
                        workingTimes = workingTimes + workingTime;
                    }
                }
            }
            //场景次数
            entity.setOperaCount(operaCounts);
            //工时
            entity.setWorkingHours(millisecondToSecond(workingTimes));
            //产出文档,暂时写死
            entity.setOutputDocument(2);
            //文档引用,暂时写死
            entity.setDocumentReference(3);
            //项目加权,暂时写死
            entity.setProjectWeighting(1);
            //信息安全违规,暂时写死
            entity.setInfoSafeViolation(5);
            //不规范操作,暂时写死
            entity.setIrregularOperation(6);

            //计算KPI: (工时 + 场景数量 + 产出文档 + 文档引用 - 信息安全违规 - 不规范操作)* 项目加权
            double a = (entity.getWorkingHours()+entity.getOperaCount()+entity.getOutputDocument()+entity.getDocumentReference()-
                    entity.getInfoSafeViolation()-entity.getIrregularOperation())*entity.getProjectWeighting();

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg = new BigDecimal(a);
            entity.setKPI(operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }

        double yesterdayKPI = 0;
        for (BYDEntity entity:yesterDayInfoList) {
            yesterdayKPI = yesterdayKPI + entity.getKPI();
        }


        BigDecimal operationAvgTimeBg1 = new BigDecimal(todayKPI);
        todayKPI = operationAvgTimeBg1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        BigDecimal operationAvgTimeBg2 = new BigDecimal(yesterdayKPI);
        yesterdayKPI = operationAvgTimeBg2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();


        resultMap.put("todayKPI", todayKPI);
        resultMap.put("yesterdayKPI", yesterdayKPI);

        //后续可做优化，将信息安全场景存入一张表，灵活存取！
        int safetyCounts = 0;
        /*查询安全事件*/
        BYDEntity bydEntity1 = new BYDEntity();
        bydEntity1.setTableName("scene_login");
        int safetyCount1 = bydMapper.getNormalCounts(bydEntity1);
        safetyCounts = safetyCounts + safetyCount1;

        bydEntity1.setTableName("scene_mytc_viewDoc");
        int safetyCount2 = bydMapper.getNormalCounts(bydEntity1);
        safetyCounts = safetyCounts + safetyCount2;

        bydEntity1.setTableName("scene_mytc_open_dataset");
        int safetyCount3 = bydMapper.getNormalCounts(bydEntity1);
        safetyCounts = safetyCounts + safetyCount3;

        bydEntity1.setTableName("scene_general_delete");
        int safetyCount4 = bydMapper.getNormalCounts(bydEntity1);
        safetyCounts = safetyCounts + safetyCount4;

        bydEntity1.setTableName("scene_mytc_export_PLMXML");
        int safetyCount5 = bydMapper.getNormalCounts(bydEntity1);
        safetyCounts = safetyCounts + safetyCount5;

        bydEntity1.setTableName("scene_mytc_namedRef_download");
        int safetyCount6 = bydMapper.getNormalCounts(bydEntity1);
        safetyCounts = safetyCounts + safetyCount6;

        resultMap.put("safetyCounts",safetyCounts);
        return resultMap;
    }

    //查询最近事件
    @Override
    public Map getRecentEvents(BYDEntity bydEntity) {
        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        List<BYDEntity> recentEventsList = new ArrayList<BYDEntity>();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                List<BYDEntity> recentEvents = bydMapper.getRecentEvents(bydEntity);
                if (recentEvents.size() != 0) {
                    if (null != recentEvents.get(0)) {
                        for (BYDEntity entity:recentEvents) {
                            String recentInfo = "";
                            switch (bydEntity.getTableName()) {
                                case "scene_login":
                                    if("跨部门登录".equals(entity.getLoginStatus())){
                                        recentInfo = "跨部门登录,";
                                        entity.setRecentInfo(recentInfo);
                                    }
                                    if(StringUtils.isEmpty(entity.getRecentInfo())){
                                        entity.setRecentInfo("登录");
                                    }
                                    break;
                                case "scene_mytc_open_dataset":
                                    if("是".equals(entity.getCrossDepartmentView())){
                                        recentInfo = "跨部门查看,";
                                        entity.setRecentInfo(recentInfo);
                                    }
                                    if("是".equals(entity.getSecretFileView())){
                                        recentInfo = recentInfo + "机密查看";
                                        entity.setRecentInfo(recentInfo);
                                    }

                                    if(StringUtils.isEmpty(entity.getRecentInfo())){
                                        entity.setRecentInfo("打开数据集");
                                    }
                                    break;
                                case "scene_mytc_viewDoc":
                                    if("是".equals(entity.getCrossDepartmentView())){
                                        recentInfo = "跨部门查看,";
                                        entity.setRecentInfo(recentInfo);
                                    }
                                    if("是".equals(entity.getSecretFileView())){
                                        recentInfo = recentInfo + "机密查看";
                                        entity.setRecentInfo(recentInfo);
                                    }

                                    if(StringUtils.isEmpty(entity.getRecentInfo())){
                                        entity.setRecentInfo("查看文档");
                                    }
                                    break;

                                case "scene_general_delete":
                                    if("是".equals(entity.getCrossDepartmentDelete())){
                                        recentInfo = "跨部门删除,";
                                        entity.setRecentInfo(recentInfo);
                                    }
                                    if("是".equals(entity.getCrossOwnerDelete())){
                                        recentInfo = recentInfo + "跨属主删除,";
                                        entity.setRecentInfo(recentInfo);
                                    }

                                    if(StringUtils.isEmpty(entity.getRecentInfo())){
                                        entity.setRecentInfo("删除");
                                    }
                                case "scene_mytc_export_PLMXML":
                                    if("是".equals(entity.getOverloadDownload())){
                                        recentInfo = "超量下载,";
                                        entity.setRecentInfo(recentInfo);
                                    }
                                    if("是".equals(entity.getCrossDepartmentDownload())){
                                        recentInfo = recentInfo + "跨部门下载,";
                                        entity.setRecentInfo(recentInfo);
                                    }
                                    if("是".equals(entity.getSecretFileDownload())){
                                        recentInfo = recentInfo + "机密下载";
                                        entity.setRecentInfo(recentInfo);
                                    }
                                    if("暂定大小超标".equals(entity.getOutOfSizeDownload())){
                                        recentInfo = recentInfo + "大小超标下载";
                                        entity.setRecentInfo(recentInfo);
                                    }

                                    if(StringUtils.isEmpty(entity.getRecentInfo())){
                                        entity.setRecentInfo("PLMXML导出");
                                    }
                                    break;
                                case "scene_mytc_namedRef_download":
                                    if("是".equals(entity.getOverloadDownload())){
                                        recentInfo = "超量下载,";
                                        entity.setRecentInfo(recentInfo);
                                    }
                                    if("是".equals(entity.getCrossDepartmentDownload())){
                                        recentInfo = recentInfo + "跨部门下载,";
                                        entity.setRecentInfo(recentInfo);
                                    }
                                    if("是".equals(entity.getSecretFileDownload())){
                                        recentInfo = recentInfo + "机密下载";
                                        entity.setRecentInfo(recentInfo);
                                    }
                                    if("是".equals(entity.getOutOfSizeDownload())){
                                        recentInfo = recentInfo + "大小超标下载";
                                        entity.setRecentInfo(recentInfo);
                                    }

                                    if(StringUtils.isEmpty(entity.getRecentInfo())){
                                        entity.setRecentInfo("命名的引用下载");
                                    }
                                    break;
                                default:
                                    entity.setRecentInfo(scene.getSceneNameDesc());
                                    break;
                            }
                            entity.setActionBeginTime(subStringActionTime(entity.getActionBeginTime()));
                        }
                        recentEventsList.addAll(recentEvents);
                    }
                }
            }
        }
        Map resultMap = new HashMap<>();
        resultMap.put("recentEventsList", recentEventsList);
        return resultMap;
    }

    //查询部门事件数量
    @Override
    public Map getDepartmentalEvents(BYDEntity bydEntity) {
        List<BYDEntity> allGroupList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseMapper.getAllScene();

        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                bydEntity.setTableName(sceneTableName);
                List<BYDEntity> groupList = bydMapper.getDepartmentalEvents(bydEntity);
                if (groupList.size() != 0) {
                    if (null != groupList.get(0)) {
                        allGroupList.addAll(groupList);
                    }
                }
            }
        }

        //相同company去重+计算KPI+计算KPICal+计算KPIjiafen+计算KPIjianfen
        allGroupList = CalculateUtil.calGroupCounts(allGroupList);

        for (BYDEntity entity:allGroupList) {
            int abnormalCount = 0;
            for (SceneListEntity scene : sceneList) {
                entity.setExceptionLoginFlag(1);
                entity.setTableName(scene.getSceneTableName());
                List<BYDEntity> groupList = bydMapper.getDepartmentalEvents(entity);
                if (groupList.size() != 0) {
                    if (null != groupList.get(0)) {
                        abnormalCount = abnormalCount + groupList.get(0).getOperaCount();
                        //entity.setAbnormalCount(groupList.get(0).getOperaCount());
                    }
                }
            }
            entity.setAbnormalCount(abnormalCount);
            int normalCounts = entity.getOperaCount();
            if(normalCounts != 0){
                //计算百分比
                // 创建一个数值格式化对象
                NumberFormat numberFormat = NumberFormat.getInstance();
                // 设置精确到小数点后2位
                numberFormat.setMaximumFractionDigits(2);
                String result = numberFormat.format((float)abnormalCount/(float)normalCounts*100);
                entity.setRecentInfo(result);
            }else{
                entity.setRecentInfo("0");
            }
        }


        Map resultMap = new HashMap<>();
        resultMap.put("allGroupList", allGroupList);
        return resultMap;
    }

    //查询仪表盘公司信息
    @Override
    public Map getDashboardCompanyInfo(BYDEntity bydEntity) {
        List<BYDEntity> companyEventsList = new ArrayList<>();
        List<String> allCompanyList = new ArrayList<>();
        List<BYDEntity> calCompanyList = new ArrayList<>();
        JSONArray array = new JSONArray();

        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        if (StringUtils.isEmpty(bydEntity.getCompany())) {
            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    bydEntity.setTableName(sceneTableName);
                    List<String> companyList = bydMapper.getAllCompany(bydEntity);
                    if (companyList.size() != 0) {
                        if (null != companyList.get(0)) {
                            allCompanyList.addAll(companyList);
                        }
                    }
                }
            }
        }

        if(StringUtils.isNotEmpty(bydEntity.getCompany())){
            allCompanyList.add(bydEntity.getCompany());
        }

        if (allCompanyList.size() != 0) {
            HashSet<String> hashSet02 = new HashSet<String>(allCompanyList);
            allCompanyList.clear();
            allCompanyList.addAll(hashSet02);
            Collections.sort(allCompanyList);
        }

        for (String company:allCompanyList){
            BYDEntity bydEntity1 = new BYDEntity();
            int normalCounts = 0;
            int abnormalCounts = 0;
            calCompanyList.clear();
            //companyEventsList.clear();
            JSONObject arrayElementOne = new JSONObject();
            arrayElementOne.element("companyName",company);
            bydEntity.setCompany(company);

            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    bydEntity.setTableName(sceneTableName);
                    //查询折线图数据
                    if(bydEntity.getZheXianFlag() ==1){
                        List<BYDEntity> list = bydMapper.getCompanyZheXianByDay(bydEntity);
                        if (list.size() != 0) {
                            if (null != list.get(0)) {
                                calCompanyList.addAll(list);
                            }
                        }
                    }else if(bydEntity.getZheXianFlag() ==2){
                        List<BYDEntity> list = bydMapper.getCompanyZheXianByWeek(bydEntity);
                        if (list.size() != 0) {
                            if (null != list.get(0)) {
                                calCompanyList.addAll(list);
                            }
                        }
                    }else if(bydEntity.getZheXianFlag() ==3){
                        String zheXianDate = bydEntity.getZheXianDate();
                        String firstMonthDay = DateUtil.getFirstMonthDay(zheXianDate);
                        bydEntity.setZheXianDate(firstMonthDay);
                        List<BYDEntity> list = bydMapper.getCompanyZheXianByMonth(bydEntity);
                        if (list.size() != 0) {
                            if (null != list.get(0)) {
                                calCompanyList.addAll(list);
                            }
                        }
                    }
                    //查询客户端事件和异常事件
                    //正常事件
                    int normalCount = bydMapper.getCompanyNormalInfo(bydEntity);
                    normalCounts = normalCounts + normalCount;
                    //异常事件
                    bydEntity.setExceptionLoginFlag(1);
                    int abnormalCount = bydMapper.getCompanyNormalInfo(bydEntity);
                    abnormalCounts = abnormalCounts + abnormalCount;
                }
            }
            bydEntity1.setCompany(company);
            bydEntity1.setOperaCount(normalCounts);
            bydEntity1.setAbnormalCount(abnormalCounts);
            if(normalCounts != 0){
                //计算百分比
                // 创建一个数值格式化对象
                NumberFormat numberFormat = NumberFormat.getInstance();
                // 设置精确到小数点后2位
                numberFormat.setMaximumFractionDigits(2);
                String result = numberFormat.format((float)abnormalCounts/(float)normalCounts*100);
                bydEntity1.setRecentInfo(result+"%");
            }else{
                bydEntity1.setRecentInfo("0%");
            }
            companyEventsList.add(bydEntity1);

            calCompanyList = CalculateUtil.calZheXianX(calCompanyList);
            //对日期进行排序
            Collections.sort(calCompanyList, new Comparator<BYDEntity>() {
                @Override
                public int compare(BYDEntity o1, BYDEntity o2) {
                    //升序
                    return o1.getZheXianX().compareTo(o2.getZheXianX());
                }
            });

            JSONArray arrayElementOneArrayElementOne = JSONArray.fromObject(calCompanyList);
            arrayElementOne.element("children",arrayElementOneArrayElementOne);
            array.add(arrayElementOne);
        }

        Map resultMap = new HashMap<>();
        resultMap.put("companyZheXianList",array);
        resultMap.put("companyEventsList", companyEventsList);
        return resultMap;
    }

    //查询服务器、客户端当日操作频次、异常操作频次
    @Override
    public Map queryOperaCounts(BYDEntity bydEntity) {
        //客户端当天操作总频次
        int clientTodayAllCounts = 0;
        //客户端昨天操作总频次
        int clientYesterdayAllCounts = 0;
        //客户端当天异常操作频次
        int clientTodayAbnormalCounts = 0;
        //客户端昨天异常操作频次
        int clientYesterdayAbnormalCounts = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = sdf.format(new Date());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(calendar.DATE,-1);
        String yesterdayDate= sdf.format(calendar.getTime());

        List<SceneListEntity> sceneList = baseMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            bydEntity.setTableName(sceneTableName);
            //查询客户端当天操作总频次
            bydEntity.setActionBeginTime(todayDate);
            int count1 = bydMapper.queryClientOperaCounts(bydEntity);
            clientTodayAllCounts = clientTodayAllCounts + count1;

            //查询客户端当天异常操作频次
            bydEntity.setExceptionLoginFlag(1);
            int count2 = bydMapper.queryClientOperaCounts(bydEntity);
            clientTodayAbnormalCounts = clientTodayAbnormalCounts + count2;

            //查询客户端昨天操作总频次
            bydEntity.setActionBeginTime(yesterdayDate);
            bydEntity.setExceptionLoginFlag(0);
            int count3 = bydMapper.queryClientOperaCounts(bydEntity);
            clientYesterdayAllCounts = clientYesterdayAllCounts +count3;

            //查询客户端昨天异常操作频次
            bydEntity.setExceptionLoginFlag(1);
            int count4 = bydMapper.queryClientOperaCounts(bydEntity);
            clientYesterdayAbnormalCounts = clientYesterdayAbnormalCounts + count4;
        }

        String AllRatio = "0%";
        //计算客户端总频次日环比
        if(clientYesterdayAllCounts !=0){
            AllRatio = CalculateUtil.calClientLinkRatio(clientTodayAllCounts,clientYesterdayAllCounts);
        }

        String AbnormalRatio = "0%";
        //计算客户端异常操作频次日环比
        if(clientYesterdayAbnormalCounts != 0){
            AbnormalRatio = CalculateUtil.calClientLinkRatio(clientTodayAbnormalCounts,clientYesterdayAbnormalCounts);

        }

        Map resultMap = new HashMap<>();
        resultMap.put("clientTodayAllCounts",clientTodayAllCounts);
        resultMap.put("AllRatio", AllRatio);
        resultMap.put("clientTodayAbnormalCounts", clientTodayAbnormalCounts);
        resultMap.put("AbnormalRatio", AbnormalRatio);
        return resultMap;
    }


    //毫秒to小时
    public double millisecondToSecond(double actionTime) {
        BigDecimal operationAvgTimeBg = new BigDecimal(actionTime / 3600000);
        double resultDouble = operationAvgTimeBg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return resultDouble;
    }

    //毫秒to秒
    public double millisecondToS(double actionTime) {
        BigDecimal operationAvgTimeBg = new BigDecimal(actionTime / 1000);
        double resultDouble = operationAvgTimeBg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        return resultDouble;
    }

    public String subStringActionTime(String actionTime) {
        if (StringUtils.isNotEmpty(actionTime)) {
            actionTime = actionTime.substring(0, actionTime.length() - 6);
        }
        return actionTime;
    }
}


