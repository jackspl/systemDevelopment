package com.szdeepwise.bydtcmonitor.service;


import com.szdeepwise.bydtcmonitor.entity.BYDEntity;
import com.szdeepwise.bydtcmonitor.entity.RequestAndResponseEntity;
import com.szdeepwise.bydtcmonitor.entity.SceneListEntity;

import java.util.List;
import java.util.Map;

public interface BaseService {
    //查询所有场景
    List<SceneListEntity> getAllScene();

    //模糊查询，场景
    Map fuzzyQueryScene(RequestAndResponseEntity requestAndResponseEntity);

    //模糊查询，用户名
    Map fuzzyQueryUserId(RequestAndResponseEntity requestAndResponseEntity);

    //模糊查询，employee_id
    Map fuzzyQueryEmployeeId(RequestAndResponseEntity requestAndResponseEntity);

    //模糊查询，机器名
    Map fuzzyQueryHostName(RequestAndResponseEntity requestAndResponseEntity);

    Map getFrequencyTableInfo(RequestAndResponseEntity requestAndResponseEntity);

    //动态表名，查询性能表格信息
    Map getPerformanceTableInfo(RequestAndResponseEntity requestAndResponseEntity);

    //动态表名，查询操作详情表格信息
    Map getOperationDetail(RequestAndResponseEntity requestAndResponseEntity);
}
