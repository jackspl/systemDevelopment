package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.BYDEntity;
import com.szdeepwise.bydtcmonitor.entity.RequestAndResponseEntity;
import com.szdeepwise.bydtcmonitor.entity.SceneListEntity;

import java.util.List;

public interface BaseMapper {
    //模糊查询，场景
    List<String> fuzzyQueryScene(RequestAndResponseEntity requestAndResponseEntity);

    //模糊查询，用户名
    List<String> fuzzyQueryUserId(RequestAndResponseEntity requestAndResponseEntity);

    //模糊查询，employee_id
    List<String> fuzzyQueryEmployeeId(RequestAndResponseEntity requestAndResponseEntity);

    //模糊查询，机器名
    List<String> fuzzyQueryHostName(RequestAndResponseEntity requestAndResponseEntity);

    //查询所有场景
    List<SceneListEntity> getAllScene();

    //根据场景中文名查询scene_table_name
    String getTableNameByDesc(String sceneNameDesc);

    List testTime(RequestAndResponseEntity requestAndResponseEntity);

    List<RequestAndResponseEntity> getFrequencyTableInfo(RequestAndResponseEntity requestAndResponseEntity);

    List<RequestAndResponseEntity> getFrequencyTableZheXian(RequestAndResponseEntity requestAndResponseEntity);

    //***************************************场景性能监控start***************************************
    //动态表名，查询性能表格数据
    List<RequestAndResponseEntity> getPerformanceTableInfo(RequestAndResponseEntity requestAndResponseEntity);
    //***************************************场景性能监控end***************************************


    //***************************************操作详情检索start***************************************
    //动态表名，查询操作详情表格信息
    List<RequestAndResponseEntity> getOperationDetail(RequestAndResponseEntity requestAndResponseEntity);

    //***************************************操作详情检索end***************************************

    int queryNormalCount(RequestAndResponseEntity requestAndResponseEntity);
}
