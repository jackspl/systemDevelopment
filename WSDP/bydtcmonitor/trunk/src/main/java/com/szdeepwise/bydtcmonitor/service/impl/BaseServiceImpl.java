package com.szdeepwise.bydtcmonitor.service.impl;

import com.szdeepwise.bydtcmonitor.entity.RequestAndResponseEntity;
import com.szdeepwise.bydtcmonitor.entity.SceneListEntity;
import com.szdeepwise.bydtcmonitor.mapper.BaseMapper;
import com.szdeepwise.bydtcmonitor.service.BaseService;
import com.szdeepwise.bydtcmonitor.util.PageModelUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    private BaseMapper baseSceneMapper;

    /**
     * @描述 查询所有场景
     * @参数 []
     * @返回值 java.util.List<com.szdeepwise.bydtcmonitor.entity.SceneListEntity>
     * @创建人 xubowen
     * @创建时间 2019/12/12
     */
    @Override
    public List<SceneListEntity> getAllScene() {
        List<SceneListEntity> sceneList = baseSceneMapper.getAllScene();
        return sceneList;
    }

    //模糊查询场景
    @Override
    public Map fuzzyQueryScene(RequestAndResponseEntity requestAndResponseEntity) {
        List<String> fuzzySceneList = baseSceneMapper.fuzzyQueryScene(requestAndResponseEntity);
        HashMap resultMap = new HashMap();
        resultMap.put("fuzzySceneList", fuzzySceneList);
        return resultMap;
    }

    //模糊查询用户名
    @Override
    public Map fuzzyQueryUserId(RequestAndResponseEntity requestAndResponseEntity) {
        List<String> fuzzyUserIdList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseSceneMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                requestAndResponseEntity.setSceneTableName(sceneTableName);
                //查询用户名
                List<String> userIdList = baseSceneMapper.fuzzyQueryUserId(requestAndResponseEntity);
                if (userIdList.size() != 0) {
                    if (null != userIdList.get(0)) {
                        fuzzyUserIdList.addAll(userIdList);
                    }
                }
            }
        }

        //fuzzyUserIdList高效去重+排序
        if (fuzzyUserIdList.size() != 0) {
            HashSet<String> hashSet02 = new HashSet<String>(fuzzyUserIdList);
            fuzzyUserIdList.clear();
            fuzzyUserIdList.addAll(hashSet02);
            Collections.sort(fuzzyUserIdList);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("fuzzyUserIdList", fuzzyUserIdList);
        return resultMap;
    }

    //模糊查询employee_id
    @Override
    public Map fuzzyQueryEmployeeId(RequestAndResponseEntity requestAndResponseEntity) {
        List<String> fuzzyUserIdList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseSceneMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                requestAndResponseEntity.setSceneTableName(sceneTableName);
                //查询用户名
                List<String> userIdList = baseSceneMapper.fuzzyQueryEmployeeId(requestAndResponseEntity);
                if (userIdList.size() != 0) {
                    if (null != userIdList.get(0)) {
                        fuzzyUserIdList.addAll(userIdList);
                    }
                }
            }
        }

        //fuzzyUserIdList高效去重+排序
        if (fuzzyUserIdList.size() != 0) {
            HashSet<String> hashSet02 = new HashSet<String>(fuzzyUserIdList);
            fuzzyUserIdList.clear();
            fuzzyUserIdList.addAll(hashSet02);
            Collections.sort(fuzzyUserIdList);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("fuzzyEmployeeIdList", fuzzyUserIdList);
        return resultMap;
    }

    //模糊查询机器名
    @Override
    public Map fuzzyQueryHostName(RequestAndResponseEntity requestAndResponseEntity) {
        List<String> fuzzyHostNameList = new ArrayList<>();
        List<SceneListEntity> sceneList = baseSceneMapper.getAllScene();
        for (SceneListEntity scene : sceneList) {
            String sceneTableName = scene.getSceneTableName();
            if (StringUtils.isNotEmpty(sceneTableName)) {
                requestAndResponseEntity.setSceneTableName(sceneTableName);
                //查询机器名
                List<String> hostNameList = baseSceneMapper.fuzzyQueryHostName(requestAndResponseEntity);
                if (hostNameList.size() != 0) {
                    if (null != hostNameList.get(0)) {
                        fuzzyHostNameList.addAll(hostNameList);
                    }
                }
            }
        }

        //fuzzyHostNameList高效去重+排序
        if (fuzzyHostNameList.size() != 0) {
            HashSet<String> hashSet02 = new HashSet<String>(fuzzyHostNameList);
            fuzzyHostNameList.clear();
            fuzzyHostNameList.addAll(hashSet02);
            Collections.sort(fuzzyHostNameList);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("fuzzyHostNameList", fuzzyHostNameList);
        return resultMap;
    }

    /**
     * @描述 模糊查询，场景、用户名和机器名
     * @参数 [requestAndResponseEntity]
     * @返回值 java.util.Map
     * @创建人 xubowen
     * @创建时间 2019/12/13
     */
    /*@Override
    public Map fuzzyQuery(RequestAndResponseEntity requestAndResponseEntity) {
        List<String> fuzzySceneList = null;
        List<String> fuzzyUserList = new ArrayList<>();
        List<String> fuzzyHostNameList = new ArrayList<>();

        if (StringUtils.isNotEmpty(requestAndResponseEntity.getSceneName())) {
            //场景的模糊查询，前端传入的sceneName是中文，实际查询时用sceneNameDesc
            fuzzySceneList = baseSceneMapper.fuzzyQueryScene(requestAndResponseEntity);
        } else {
            List<SceneListEntity> sceneList = baseSceneMapper.getAllScene();
            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    requestAndResponseEntity.setSceneTableName(sceneTableName);
                    if (StringUtils.isNotEmpty(requestAndResponseEntity.getUserId())) {
                        List<String> userList = baseSceneMapper.fuzzyQueryUserId(requestAndResponseEntity);
                        if (userList.size() != 0) {
                            if (null != userList.get(0)) {
                                fuzzyUserList.addAll(userList);
                            }
                        }
                    } else if (StringUtils.isNotEmpty(requestAndResponseEntity.getHostName())) {
                        List<String> hostNameList = baseSceneMapper.fuzzyQueryHostNameId(requestAndResponseEntity);
                        if (hostNameList.size() != 0) {
                            if (null != hostNameList.get(0)) {
                                fuzzyHostNameList.addAll(hostNameList);
                            }
                        }
                    }
                }
            }
        }

        //fuzzyUserList高效去重+排序
        if (fuzzyUserList.size() != 0) {
            HashSet<String> hashSet01 = new HashSet<String>(fuzzyUserList);
            fuzzyUserList.clear();
            fuzzyUserList.addAll(hashSet01);
            Collections.sort(fuzzyUserList);
        }

        //fuzzyHostNameList高效去重+排序
        if (fuzzyHostNameList.size() != 0) {
            HashSet<String> hashSet02 = new HashSet<String>(fuzzyHostNameList);
            fuzzyHostNameList.clear();
            fuzzyHostNameList.addAll(hashSet02);
            Collections.sort(fuzzyHostNameList);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("fuzzyUserList", fuzzyUserList);
        resultMap.put("fuzzySceneList", fuzzySceneList);
        resultMap.put("fuzzyHostNameList", fuzzyHostNameList);
        return resultMap;
    }*/

    /**
     * @描述 查询频次表格数据
     * @参数 [requestAndResponseEntity]
     * @返回值 java.util.Map
     * @创建人 xubowen
     * @创建时间 2019/12/13
     */
    @Override
    public Map getFrequencyTableInfo(RequestAndResponseEntity requestAndResponseEntity) {
        List<RequestAndResponseEntity> resultList = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        int seriousCounts = 0;
        int normalCounts = 0;

        String sceneNameDesc = requestAndResponseEntity.getSceneName();
        //获取所有场景
        List<SceneListEntity> sceneList = baseSceneMapper.getAllScene();

        //用户输入场景,sceneNameDesc为中文场景名
        if (StringUtils.isNotEmpty(sceneNameDesc)) {
            String sceneTableNameQuery = baseSceneMapper.getTableNameByDesc(sceneNameDesc);
            if (StringUtils.isNotEmpty(sceneTableNameQuery)) {
                requestAndResponseEntity.setSceneTableName(sceneTableNameQuery);
                List<RequestAndResponseEntity> tableInfo = baseSceneMapper.getFrequencyTableInfo(requestAndResponseEntity);

                //计算饼状图 正常 警告 严重
                //严重
                requestAndResponseEntity.setExceptionLoginFlag(1);
                int seriousCount = baseSceneMapper.queryNormalCount(requestAndResponseEntity);
                seriousCounts = seriousCount;
                //正常
                requestAndResponseEntity.setExceptionLoginFlag(2);
                int normalCount = baseSceneMapper.queryNormalCount(requestAndResponseEntity);
                normalCounts = normalCount;

                if (tableInfo.size() != 0) {
                    if (null != tableInfo.get(0)) {
                        for (RequestAndResponseEntity entity : tableInfo) {
                            entity.setSceneTableName(sceneTableNameQuery);
                            entity.setSceneNameDesc(sceneNameDesc);

                            try {
                                String actionBeginTime = formatter.format(formatter.parse(requestAndResponseEntity.getActionBeginTime()));
                                entity.setActionBeginTime(actionBeginTime);
                                String endBeginTime = formatter.format(formatter.parse(requestAndResponseEntity.getActionEndTime()));
                                entity.setActionEndTime(endBeginTime);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        resultList.addAll(tableInfo);
                    }
                }
            }
        } else {
            //用户未输入场景名
            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    requestAndResponseEntity.setSceneTableName(sceneTableName);
                    List<RequestAndResponseEntity> tableInfo = baseSceneMapper.getFrequencyTableInfo(requestAndResponseEntity);

                    //计算饼状图 正常 警告 严重
                    //严重
                    requestAndResponseEntity.setExceptionLoginFlag(1);
                    int seriousCount = baseSceneMapper.queryNormalCount(requestAndResponseEntity);
                    seriousCounts = seriousCounts + seriousCount;
                    //正常
                    requestAndResponseEntity.setExceptionLoginFlag(2);
                    int normalCount = baseSceneMapper.queryNormalCount(requestAndResponseEntity);
                    normalCounts = normalCounts + normalCount;

                    if (tableInfo.size() != 0) {
                        if (null != tableInfo.get(0)) {
                            for (RequestAndResponseEntity entity : tableInfo) {
                                entity.setSceneTableName(sceneTableName);
                                entity.setSceneName(scene.getSceneName());
                                entity.setSceneNameDesc(scene.getSceneNameDesc());

                                try {
                                    String actionBeginTime = formatter.format(formatter.parse(requestAndResponseEntity.getActionBeginTime()));
                                    entity.setActionBeginTime(actionBeginTime);
                                    String endBeginTime = formatter.format(formatter.parse(requestAndResponseEntity.getActionEndTime()));
                                    entity.setActionEndTime(endBeginTime);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                            resultList.addAll(tableInfo);
                        }
                    }
                }
            }
        }

        List tableTotalList = new ArrayList();
        if (requestAndResponseEntity.getPagesSize() != 0 && resultList.size()!=0) {
            //tableList分页处理，pagesSize和pagesNum不能为空
            PageModelUtil pm = new PageModelUtil(resultList, requestAndResponseEntity.getPagesSize());//每页显示条数
            //总页数
            int totalPages = pm.getTotalPages();
            //总条数
            int totalRows = pm.getTotalRows();
            resultList = pm.getObjects(requestAndResponseEntity.getPagesNum());

            tableTotalList.add(totalRows);
            tableTotalList.add(totalPages);
        }

        for (RequestAndResponseEntity entity:resultList){
            List<String> zheXianXList = new ArrayList<>();
            List<Integer> zheXianYList = new ArrayList<>();
            List<RequestAndResponseEntity> frequencyZheXianList = baseSceneMapper.getFrequencyTableZheXian(entity);
            for(RequestAndResponseEntity resultEntity:frequencyZheXianList){
                zheXianXList.add(resultEntity.getZheXianX());
                zheXianYList.add(resultEntity.getZheXianY());
            }
            entity.setpZheXianX(zheXianXList);
            entity.setpZheXianY(zheXianYList);
            //entity.setZheXianList(frequencyZheXianList);
        }

        resultList.sort(Comparator.comparing(RequestAndResponseEntity::getOperationCount).reversed());

        HashMap resultMap = new HashMap();
        resultMap.put("tableList", resultList);
        resultMap.put("tableTotalList", tableTotalList);

        resultMap.put("seriousCounts", seriousCounts);
        resultMap.put("normalCounts", normalCounts);
        return resultMap;
    }

    /**
     * @描述 查询性能表格数据
     * @参数 [requestAndResponseEntity]
     * @返回值 java.util.List<com.szdeepwise.bydtcmonitor.entity.RequestAndResponseEntity>
     * @创建人 xubowen
     * @创建时间 2019/12/12
     */
    @Override
    public Map getPerformanceTableInfo(RequestAndResponseEntity requestAndResponseEntity) {
        List<RequestAndResponseEntity> resultList = new ArrayList<>();
        String sceneNameDesc = requestAndResponseEntity.getSceneName();
        //获取所有场景
        List<SceneListEntity> sceneList = baseSceneMapper.getAllScene();

        //用户输入场景,sceneNameDesc为中文场景名
        if (StringUtils.isNotEmpty(sceneNameDesc)) {
            String sceneTableNameQuery = baseSceneMapper.getTableNameByDesc(sceneNameDesc);
            if (StringUtils.isNotEmpty(sceneTableNameQuery)) {
                requestAndResponseEntity.setSceneTableName(sceneTableNameQuery);
                List<RequestAndResponseEntity> tableInfo = baseSceneMapper.getPerformanceTableInfo(requestAndResponseEntity);
                if (tableInfo.size() != 0) {
                    if (null != tableInfo.get(0)) {
                        for (RequestAndResponseEntity entity : tableInfo) {
                            //操作时间毫秒to秒
                            entity.setOperationAvgTime(millisecondToSecond(entity.getOperationAvgTime()));
                            entity.setOperationMaxTime(millisecondToSecond(entity.getOperationMaxTime()));
                            entity.setOperationMinTime(millisecondToSecond(entity.getOperationMinTime()));
                            entity.setOperationSumTime(millisecondToSecond(entity.getOperationSumTime()));

                            entity.setSceneNameDesc(sceneNameDesc);
                        }
                        resultList.addAll(tableInfo);
                    }
                }
            }
        } else {
            //用户未输入场景名
            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    requestAndResponseEntity.setSceneTableName(sceneTableName);
                    List<RequestAndResponseEntity> tableInfo = baseSceneMapper.getPerformanceTableInfo(requestAndResponseEntity);
                    if (tableInfo.size() != 0) {
                        if (null != tableInfo.get(0)) {
                            for (RequestAndResponseEntity entity : tableInfo) {
                                //操作时间毫秒to秒
                                entity.setOperationAvgTime(millisecondToSecond(entity.getOperationAvgTime()));
                                entity.setOperationMaxTime(millisecondToSecond(entity.getOperationMaxTime()));
                                entity.setOperationMinTime(millisecondToSecond(entity.getOperationMinTime()));
                                entity.setOperationSumTime(millisecondToSecond(entity.getOperationSumTime()));

                                entity.setSceneName(scene.getSceneName());
                                entity.setSceneNameDesc(scene.getSceneNameDesc());
                            }
                            resultList.addAll(tableInfo);
                        }
                    }
                }
            }
        }

        //分页前计算场景总时间，总次数，平均时间,用户数或机器数
        //总时间
        double sceneAllTime = resultList.stream().collect(Collectors.summingDouble(RequestAndResponseEntity::getOperationSumTime));
        sceneAllTime = (double) Math.round(sceneAllTime * 100) / 100;
        //总次数
        int sceneAllCount = resultList.stream().collect(Collectors.summingInt(RequestAndResponseEntity::getOperationCount));
        //计算平均时间
        String sceneAvgTime = "0";
        if(sceneAllCount != 0){
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(2);
            sceneAvgTime = numberFormat.format((float)sceneAllTime/(float)sceneAllCount);
        }

        List<RequestAndResponseEntity> calList = new ArrayList<>();
        if(requestAndResponseEntity.getGroupFlag() == 1){
            //用户数
            calList = resultList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(RequestAndResponseEntity :: getUserId))), ArrayList::new));
        }else if(requestAndResponseEntity.getGroupFlag() == 2){
            //机器数
            calList = resultList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(RequestAndResponseEntity :: getHostName))), ArrayList::new));
        }
        int userHostCount = calList.size();

        List tableTotalList = new ArrayList();
        if (requestAndResponseEntity.getPagesSize() != 0) {
            //tableList分页处理，pagesSize和pagesNum不能为空
            PageModelUtil pm = new PageModelUtil(resultList, requestAndResponseEntity.getPagesSize());//每页显示条数
            //总页数
            int totalPages = pm.getTotalPages();
            //总条数
            int totalRows = pm.getTotalRows();
            resultList = pm.getObjects(requestAndResponseEntity.getPagesNum());

            tableTotalList.add(totalRows);
            tableTotalList.add(totalPages);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("tableList", resultList);
        resultMap.put("tableTotalList", tableTotalList);

        resultMap.put("sceneAllTime",sceneAllTime);
        resultMap.put("sceneAllCount",sceneAllCount);
        resultMap.put("sceneAvgTime",sceneAvgTime);
        resultMap.put("userHostCount",userHostCount);
        return resultMap;
    }

    /**
     * @描述 查询操作详情表格信息
     * @参数 [requestAndResponseEntity]
     * @返回值 java.util.Map
     * @创建人 xubowen
     * @创建时间 2019/12/12
     */
    @Override
    public Map getOperationDetail(RequestAndResponseEntity requestAndResponseEntity) {
        List<RequestAndResponseEntity> resultList = new ArrayList<>();
        String sceneNameDesc = requestAndResponseEntity.getSceneName();
        //获取所有场景
        List<SceneListEntity> sceneList = baseSceneMapper.getAllScene();

        //用户输入场景,sceneNameDesc为中文场景名
        if (StringUtils.isNotEmpty(sceneNameDesc)) {
            String sceneTableNameQuery = baseSceneMapper.getTableNameByDesc(sceneNameDesc);
            if (StringUtils.isNotEmpty(sceneTableNameQuery)) {
                requestAndResponseEntity.setSceneTableName(sceneTableNameQuery);
                List<RequestAndResponseEntity> tableInfo = baseSceneMapper.getOperationDetail(requestAndResponseEntity);
                if (tableInfo.size() != 0) {
                    if (null != tableInfo.get(0)) {
                        for (RequestAndResponseEntity entity : tableInfo) {
                            //处理时间字符串后的6个0
                            entity.setActionBeginTime(subStringActionTime(entity.getActionBeginTime()));
                            entity.setActionEndTime(subStringActionTime(entity.getActionEndTime()));

                            entity.setSceneNameDesc(sceneNameDesc);
                        }
                        resultList.addAll(tableInfo);
                    }
                }
            }
        } else {
            //用户未输入场景名
            for (SceneListEntity scene : sceneList) {
                String sceneTableName = scene.getSceneTableName();
                if (StringUtils.isNotEmpty(sceneTableName)) {
                    requestAndResponseEntity.setSceneTableName(sceneTableName);
                    List<RequestAndResponseEntity> tableInfo = baseSceneMapper.getOperationDetail(requestAndResponseEntity);
                    if (tableInfo.size() != 0) {
                        if (null != tableInfo.get(0)) {
                            for (RequestAndResponseEntity entity : tableInfo) {
                                //处理时间字符串后的6个0
                                entity.setActionBeginTime(subStringActionTime(entity.getActionBeginTime()));
                                entity.setActionEndTime(subStringActionTime(entity.getActionEndTime()));

                                entity.setSceneName(scene.getSceneName());
                                entity.setSceneNameDesc(scene.getSceneNameDesc());
                            }
                            resultList.addAll(tableInfo);
                        }
                    }
                }
            }
        }

        //actionBeginTime从小到大排序
        if(requestAndResponseEntity.getPaiXuFlag() == 1){
            Collections.sort(resultList, new Comparator<RequestAndResponseEntity>() {
                @Override
                public int compare(RequestAndResponseEntity o1, RequestAndResponseEntity o2) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//要转换的日期格式，根据实际调整""里面内容
                    try {
                        Date dt1 = format.parse(o1.getActionBeginTime());
                        Date dt2 = format.parse(o2.getActionBeginTime());
                        if (dt1.getTime() > dt2.getTime()) {
                            return 1;
                        } else if (dt1.getTime() < dt2.getTime()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;
                }
            });
        }

        //actionBeginTime从大到小排序
        if(requestAndResponseEntity.getPaiXuFlag() == 2){
            Collections.sort(resultList, new Comparator<RequestAndResponseEntity>() {
                @Override
                public int compare(RequestAndResponseEntity o1, RequestAndResponseEntity o2) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//要转换的日期格式，根据实际调整""里面内容
                    try {
                        Date dt1 = format.parse(o1.getActionBeginTime());
                        Date dt2 = format.parse(o2.getActionBeginTime());
                        if (dt1.getTime() < dt2.getTime()) {
                            return 1;
                        } else if (dt1.getTime() > dt2.getTime()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;
                }
            });
        }
        //resultList.stream().sorted(Comparator.comparing(RequestAndResponseEntity::getActionBeginTime));

        //resultList.stream().sorted(Comparator.comparing(RequestAndResponseEntity::getActionBeginTime).reversed());


        List tableTotalList = new ArrayList();
        if (requestAndResponseEntity.getPagesSize() != 0) {
            //tableList分页处理，pagesSize和pagesNum不能为空
            PageModelUtil pm = new PageModelUtil(resultList, requestAndResponseEntity.getPagesSize());//每页显示条数
            //总页数
            int totalPages = pm.getTotalPages();
            //总条数
            int totalRows = pm.getTotalRows();
            resultList = pm.getObjects(requestAndResponseEntity.getPagesNum());

            tableTotalList.add(totalRows);
            tableTotalList.add(totalPages);
        }

        HashMap resultMap = new HashMap();
        resultMap.put("tableList", resultList);
        resultMap.put("tableTotalList", tableTotalList);
        return resultMap;
    }

    /**
     * @描述 处理actionBeginTime和actionEndTime后6个0
     * @参数 [actionTime]
     * @返回值 java.lang.String
     * @创建人 xubowen
     * @创建时间 2019/12/12
     */
    public String subStringActionTime(String actionTime) {
        if (StringUtils.isNotEmpty(actionTime)) {
            actionTime = actionTime.substring(0, actionTime.length() - 6);
        }
        return actionTime;
    }

    /**
     * @描述 操作时间毫秒to秒
     * @参数 [actionTime]
     * @返回值 double
     * @创建人 xubowen
     * @创建时间 2019/12/12
     */
    public double millisecondToSecond(double actionTime) {
        BigDecimal operationAvgTimeBg = new BigDecimal(actionTime / 1000);
        double resultDouble = operationAvgTimeBg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        return resultDouble;
    }
}
