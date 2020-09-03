package com.szdeepwise.bydtcmonitor.controller;

import com.szdeepwise.bydtcmonitor.entity.BYDEntity;
import com.szdeepwise.bydtcmonitor.entity.RequestAndResponseEntity;
import com.szdeepwise.bydtcmonitor.service.BaseService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author xubowen
 * @description 基本功能
 * @date 2019年 12月18日
 */
@Controller
public class BaseController {

    @Autowired
    private BaseService baseService;

    //模糊查询场景
    @ResponseBody
    @RequestMapping(value = "base/fuzzyQueryScene", method = RequestMethod.POST)
    public String fuzzyQueryScene(@RequestBody RequestAndResponseEntity requestAndResponseEntity) {
        System.out.println("######## fuzzyQueryScene：" + requestAndResponseEntity.toString());
        Map resultMap = baseService.fuzzyQueryScene(requestAndResponseEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //模糊查询用户名
    @ResponseBody
    @RequestMapping(value = "base/fuzzyQueryUserId", method = RequestMethod.POST)
    public String fuzzyQueryUserId(@RequestBody RequestAndResponseEntity requestAndResponseEntity) {
        System.out.println("######## fuzzyQueryUserId：" + requestAndResponseEntity.toString());
        Map resultMap = baseService.fuzzyQueryUserId(requestAndResponseEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //模糊查询employee_id
    @ResponseBody
    @RequestMapping(value = "base/fuzzyQueryEmployeeId", method = RequestMethod.POST)
    public String fuzzyQueryEmployeeId(@RequestBody RequestAndResponseEntity requestAndResponseEntity) {
        System.out.println("######## fuzzyQueryEmployeeId：" + requestAndResponseEntity.toString());
        Map resultMap = baseService.fuzzyQueryEmployeeId(requestAndResponseEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //模糊查询机器名
    @ResponseBody
    @RequestMapping(value = "base/fuzzyQueryHostName", method = RequestMethod.POST)
    public String fuzzyQueryHostName(@RequestBody RequestAndResponseEntity requestAndResponseEntity) {
        System.out.println("######## fuzzyQueryHostName：" + requestAndResponseEntity.toString());
        Map resultMap = baseService.fuzzyQueryHostName(requestAndResponseEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //场景频次监控
    @ResponseBody
    @RequestMapping(value = "base/frequencyMonitoring", method = RequestMethod.POST)
    public String frequencyMonitoring(@RequestBody RequestAndResponseEntity requestAndResponseEntity) {
        System.out.println("######## frequencyMonitoring请求参数：" + requestAndResponseEntity.toString());
        Map resultMap = baseService.getFrequencyTableInfo(requestAndResponseEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //场景性能监控
    @ResponseBody
    @RequestMapping(value = "base/operationPerformanceAnalysis", method = RequestMethod.POST)
    public String operationPerformanceAnalysis(@RequestBody RequestAndResponseEntity requestAndResponseEntity) {
        System.out.println("######## operationPerformanceAnalysis请求参数：" + requestAndResponseEntity.toString());
        Map resultMap = baseService.getPerformanceTableInfo(requestAndResponseEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }

    //操作详情检索
    @ResponseBody
    @RequestMapping(value = "base/getOperationDetail", method = RequestMethod.POST)
    public String getOperationDetail(@RequestBody RequestAndResponseEntity requestAndResponseEntity) {
        System.out.println("####### getOperationDetail请求参数：" + requestAndResponseEntity.toString());
        Map resultMap = baseService.getOperationDetail(requestAndResponseEntity);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }
}


