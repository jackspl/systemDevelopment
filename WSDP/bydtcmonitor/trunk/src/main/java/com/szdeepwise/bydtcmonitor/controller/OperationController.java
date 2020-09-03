package com.szdeepwise.bydtcmonitor.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.szdeepwise.bydtcmonitor.entity.*;
import com.szdeepwise.bydtcmonitor.mapper.*;
import com.szdeepwise.bydtcmonitor.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.entity.BufferedHttpEntity;
import org.omg.IOP.Encoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xubowen
 * @description 接收客户端用户操作信息，插入数据库
 * @date 2019年 12月18日
 */
@Controller
public class OperationController {

    @Autowired
    private SceneLoginMapper loginMapper;
    @Autowired
    private SceneCreateComponentMapper createComponentMapper;
    @Autowired
    private SceneSearchMapper searchMapper;
    @Autowired
    private SceneSendtoPSEMapper sendtoPSEMapper;
    @Autowired
    private SceneCheckoutDataMapper checkoutDataMapper;
    @Autowired
    private SceneCheckinDataMapper checkinDataMapper;
    @Autowired
    private SceneNameRefMapper namerefMapper;
    @Autowired
    private SceneCreateWorkflowMapper createWorkflowMapper;
    @Autowired
    private SceneWorkflowApproveMapper workflowApproveMapper;
    @Autowired
    private SceneBOMExpandDownwardMapper bOMExpandDownwardMapper;
    @Autowired
    private SceneLogoutMapper logoutMapper;
    @Autowired
    private SceneCheckinDatasetMapper checkinDatasetMapper;
    @Autowired
    private SceneCheckoutDatasetMapper checkoutDatasetMapper;
    @Autowired
    private SceneCancelCheckoutMapper cancelCheckoutMapper;
    @Autowired
    private SceneCloseFolderNewstuffMapper closeFolderNewstuffMapper;
    @Autowired
    private SceneCloseItemObjectMapper closeItemObjectMapper;


    @Autowired
    private SceneCopyDocVersionMapper copyDocVersionMapper;

    @Autowired
    private ScenePasteDocVersionMapper pasteDocVersionMapper;

    @Autowired
    private SceneCopyComponentVersionMapper copyComponentVersionMapper;
    @Autowired
    private ScenePasteComponentVersionMapper pasteComponentVersionMapper;
    @Autowired
    private SceneCreateDatasetTextMapper createDatasetTextMapper;
    @Autowired
    private SceneCreateDocObjectMapper createDocObjectMapper;
    @Autowired
    private SceneCreateFolderMapper createFolderMapper;
    @Autowired
    private SceneCreateFormMapper createFormMapper;
    @Autowired
    private SceneDeleteFormMapper deleteFormMapper;
    @Autowired
    private SceneDeleteComponentVersionMapper deleteComponentVersionMapper;
    @Autowired
    private SceneReviseComponentVersionMapper reviseComponentVersionMapper;
    @Autowired
    private SceneReviseDocVersionMapper reviseDocVersionMapper;
    @Autowired
    private SceneOpenDatasetTextMapper openDatasetTextMapper;
    @Autowired
    private SceneOpenFolderMapper openFolderMapper;
    @Autowired
    private SceneOpenHomeMapper openHomeMapper;
    @Autowired
    private SceneOpenNewstuffMapper openNewstuffMapper;

    @Autowired
    private SceneViewDocMapper sceneViewDocMapper;
    @Autowired
    private SceneDownloadDocMapper sceneDownloadDocMapper;

    @Autowired
    private WorkingHoursMapper workingHoursMapper;

    @Autowired
    private BYDMapper bydMapper;

    //创建数据集1
    @Autowired
    private SceneMytcCreateDatasetMapper sceneMytcCreateDatasetMapper;

    //命名的引用上传1
    @Autowired
    private SceneMytcNamedRefUploadMapper sceneMytcNamedRefUploadMapper;

    //签入12
    @Autowired
    private SceneMytcCheckinDataMapper sceneMytcCheckinDataMapper;

    //签出12
    @Autowired
    private SceneMytcCheckoutDataMapper sceneMytcCheckoutDataMapper;

    //复制12
    @Autowired
    private SceneGeneralCopyMapper sceneGeneralCopyMapper;

    //剪切12
    @Autowired
    private SceneGeneralCutMapper sceneGeneralCutMapper;

    //粘贴12
    @Autowired
    private SceneGeneralPasteMapper sceneGeneralPasteMapper;

    //查看文档1
    @Autowired
    private SceneMytcViewDocMapper sceneMytcViewDocMapper;

    //打开数据集1
    @Autowired
    private SceneMytcOpenDatasetMapper sceneMytcOpenDatasetMapper;

    //删除12
    @Autowired
    private SceneGeneralDeleteMapper sceneGeneralDeleteMapper;

    //PLMXML导出1
    @Autowired
    private SceneMytcExportPLMXMLMapper sceneMytcExportPLMXMLMapper;

    //命名的引用下载1
    @Autowired
    private SceneMytcNamedRefDownloadMapper sceneMytcNamedRefDownloadMapper;



    static int OFFSET_HEADER = 0;
    static int OFFSET_PACK_LEN = OFFSET_HEADER + 4;
    static int OFFSET_MAIN_TYPE = OFFSET_PACK_LEN + 4 ;
    static int OFFSET_SUB_TYPE = OFFSET_MAIN_TYPE + 1;
    static int OFFSET_DATA_LEN = OFFSET_SUB_TYPE + 1;

    @ResponseBody
    @RequestMapping(value = "rest/insertOperationInfo")
    public String getByJSON(HttpServletRequest httpServletRequest) throws IOException {

        String resultInfo = "error";
        String length = httpServletRequest.getHeader("content-length");
        int bodySize = Integer.parseInt(length);
        System.out.println("+++++++++bodySize:"+length);
        ServletInputStream fis = httpServletRequest.getInputStream();
        byte[] packge_bytes = IOUtils.toByteArray(fis);
        System.out.println("++++++++++输入流InputStream:"+fis);

        // 读入整个数据包
/*        byte[] packge_bytes = new byte[bodySize];
        fis.read(packge_bytes);*/

        System.out.println("++++++++++++整个数据包packge_bytes:"+packge_bytes);

        //提出数据包头标志 0x5b3c3d3d
        int header = readInt(packge_bytes,OFFSET_HEADER);
        System.out.println("++++++++++数据包头标志header:"+header);
        if(header == 0x3d3d3c5b)//是合法包头
        {
            System.out.println("是合法包头");
        }
        int pack_len = readInt(packge_bytes,OFFSET_PACK_LEN );//整个包总长度
        System.out.println("++++++++++整个包总长度："+pack_len);
        byte main_type = packge_bytes[OFFSET_MAIN_TYPE];//主类型
        System.out.println("=============main_type："+main_type);
        if(main_type==1){
            System.out.println("main_type==1  工时数据");
            resultInfo = insertWorkingHoursInfo(packge_bytes);
        }else if(main_type==2){
            System.out.println("main_type==2  场景数据");
            resultInfo = insertSceneInfo(packge_bytes, httpServletRequest);
        }
        // 释放资源
        fis.close();

        return resultInfo;
    }

    //插入工时数据
    public String insertWorkingHoursInfo(byte[] packge_bytes) throws UnsupportedEncodingException {
        int insert = 0;
        byte sub_type = packge_bytes[OFFSET_SUB_TYPE];//对工时数据包，此字段无意义，忽略。
        int data_len = readInt(packge_bytes,OFFSET_DATA_LEN);//对工时数据包，此字段无意义，忽略。
        //System.out.println("=============工时json数据长度"+data_len);

        //提取json的字节
        byte[] json_bytes = new byte[data_len];
        System.arraycopy(packge_bytes, OFFSET_DATA_LEN + 4, json_bytes, 0, data_len);
        System.out.println("=============工时json转换后的数组长度："+json_bytes.length);
        String json_str = new String(json_bytes ,"UTF-8");
        System.out.println("=============提取工时json："+json_str);

        //JSONObject jsonObject = JSONObject.parseObject(json_str);

        try {
            List<WorkingHours> workingHoursList =
                    JSON.parseArray(JSON.parseObject(json_str).getString("array_working_hours"), WorkingHours.class);
            for (WorkingHours workingHours:workingHoursList) {
                int operationTime = DateUtil.computeOperationTime(workingHours.getTimeStart(), workingHours.getTimeEnd());
                workingHours.setOperationTime(operationTime);
                System.out.println("===============插入单条的工时数据："+workingHours.toString());
                insert = workingHoursMapper.insert(workingHours);
            }
        } catch (Exception e) {
            System.out.println("-----------parseArray异常的json"+json_str);
            e.printStackTrace();
        }

        //最终提取结果。下面的obj_workhour对象里包含了所有字段
/*        WorkingHours workingHours = JSONObject.parseObject(json_str, WorkingHours.class);
        int operationTime = DateUtil.computeOperationTime(workingHours.getTimeStart(), workingHours.getTimeEnd());
        workingHours.setOperationTime(operationTime);

        int insert = workingHoursMapper.insert(workingHours);*/

        JSONObject result = new JSONObject();
        if(insert ==1){
            result.put("result", "200");
        }else{
            result.put("result", "error");
        }
        return result.toString();
    }

    //插入场景数据
    public String insertSceneInfo(byte[] packge_bytes,HttpServletRequest request) throws UnsupportedEncodingException {
        byte sub_type = packge_bytes[OFFSET_SUB_TYPE];//对工时数据包，此字段无意义，忽略。
        int data_len = readInt(packge_bytes,OFFSET_DATA_LEN);//对工时数据包，此字段无意义，忽略。

        //提取json的字节
        byte[] json_bytes = new byte[data_len];
        System.arraycopy(packge_bytes, OFFSET_DATA_LEN + 4, json_bytes, 0, data_len);
        String json_str = new String(json_bytes ,"UTF-8");
        System.out.println("=============提取场景json："+json_str);

        JSONObject jsonParam =JSONObject.parseObject(json_str);

        //获取场景名称
        String changjing = (String) jsonParam.get("actionScene");
        changjing= changjing.trim();

        JSONObject.DEFFAULT_DATE_FORMAT = "yyyy-MM-ddHH:mm:ss.mmm";
        boolean flag = true;


        // 记录下请求内容
        StringBuilder sb = new StringBuilder();
        sb.append("###请求URL: " + request.getRequestURL().toString());
        sb.append("   ###IP: " + request.getRemoteAddr());

        String ip = request.getRemoteAddr();

        if (changjing.equalsIgnoreCase("g_mytc_login")) {
            SceneLogin login = JSONObject.parseObject(jsonParam.toJSONString(), SceneLogin.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);
            login = judgeLoginStatus(login);
            if(StringUtils.isEmpty(login.getCompany())){
                String company = judgeCompany(login.getEmployeeGroup());
                login.setCompany(company);
            }
            String employeeGroup = judgeEmployeeGroup(login.getEmployeeGroup());
            login.setEmployeeGroup(employeeGroup);

            loginMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("g_mytc_createFolder")) {
            SceneCreateFolder login = JSONObject.parseObject(jsonParam.toJSONString(), SceneCreateFolder.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);
            if(StringUtils.isEmpty(login.getCompany())){
                String company = judgeCompany(login.getEmployeeGroup());
                login.setCompany(company);
            }
            String employeeGroup = judgeEmployeeGroup(login.getEmployeeGroup());
            login.setEmployeeGroup(employeeGroup);

            createFolderMapper.insert(login);
        }else if (changjing.equalsIgnoreCase("g_mytc_openFolder")) {
            SceneOpenFolder login = JSONObject.parseObject(jsonParam.toJSONString(), SceneOpenFolder.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);
            if(StringUtils.isEmpty(login.getCompany())){
                String company = judgeCompany(login.getEmployeeGroup());
                login.setCompany(company);
            }
            String employeeGroup = judgeEmployeeGroup(login.getEmployeeGroup());
            login.setEmployeeGroup(employeeGroup);

            openFolderMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("g_mytc_createComponent")) {
            SceneCreateComponent login = JSONObject.parseObject(jsonParam.toJSONString(), SceneCreateComponent.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);
            if(StringUtils.isEmpty(login.getCompany())){
                String company = judgeCompany(login.getEmployeeGroup());
                login.setCompany(company);
            }
            String employeeGroup = judgeEmployeeGroup(login.getEmployeeGroup());
            login.setEmployeeGroup(employeeGroup);

            createComponentMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("g_mytc_createDocObject")) {
            SceneCreateDocObject login = JSONObject.parseObject(jsonParam.toJSONString(), SceneCreateDocObject.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);
            if(StringUtils.isEmpty(login.getCompany())){
                String company = judgeCompany(login.getEmployeeGroup());
                login.setCompany(company);
            }
            String employeeGroup = judgeEmployeeGroup(login.getEmployeeGroup());
            login.setEmployeeGroup(employeeGroup);

            createDocObjectMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("g_mytc_createForm")) {
            SceneCreateForm login = JSONObject.parseObject(jsonParam.toJSONString(), SceneCreateForm.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);
            if(StringUtils.isEmpty(login.getCompany())){
                String company = judgeCompany(login.getEmployeeGroup());
                login.setCompany(company);
            }
            String employeeGroup = judgeEmployeeGroup(login.getEmployeeGroup());
            login.setEmployeeGroup(employeeGroup);

            createFormMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("g_mytc_reviseDocVersion")) {
            SceneReviseDocVersion login = JSONObject.parseObject(jsonParam.toJSONString(), SceneReviseDocVersion.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);
            if(StringUtils.isEmpty(login.getCompany())){
                String company = judgeCompany(login.getEmployeeGroup());
                login.setCompany(company);
            }
            String employeeGroup = judgeEmployeeGroup(login.getEmployeeGroup());
            login.setEmployeeGroup(employeeGroup);

            reviseDocVersionMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("g_mytc_reviseComponentVersion")) {
            SceneReviseComponentVersion login = JSONObject.parseObject(jsonParam.toJSONString(), SceneReviseComponentVersion.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);
            if(StringUtils.isEmpty(login.getCompany())){
                String company = judgeCompany(login.getEmployeeGroup());
                login.setCompany(company);
            }
            String employeeGroup = judgeEmployeeGroup(login.getEmployeeGroup());
            login.setEmployeeGroup(employeeGroup);

            reviseComponentVersionMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("g_mytc_createDataset")) {
            SceneMytcCreateDataset login = JSONObject.parseObject(jsonParam.toJSONString(), SceneMytcCreateDataset.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);
            if(StringUtils.isEmpty(login.getCompany())){
                String company = judgeCompany(login.getEmployeeGroup());
                login.setCompany(company);
            }
            String employeeGroup = judgeEmployeeGroup(login.getEmployeeGroup());
            login.setEmployeeGroup(employeeGroup);

            if(StringUtils.isEmpty(login.getContainerId())){
                login.setContainerId("_NA_");
            }

            if(StringUtils.isEmpty(login.getContainerVersion())){
                login.setContainerVersion("_NA_");
            }

            sceneMytcCreateDatasetMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("g_mytc_namedRef_upload")) {
            SceneMytcNamedRefUpload login = JSONObject.parseObject(jsonParam.toJSONString(), SceneMytcNamedRefUpload.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);
            if(StringUtils.isEmpty(login.getCompany())){
                String company = judgeCompany(login.getEmployeeGroup());
                login.setCompany(company);
            }
            String employeeGroup = judgeEmployeeGroup(login.getEmployeeGroup());
            login.setEmployeeGroup(employeeGroup);

            sceneMytcNamedRefUploadMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("g_mytc_checkinData")) {
            SceneMytcCheckinData login = JSONObject.parseObject(jsonParam.toJSONString(), SceneMytcCheckinData.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);
            if(StringUtils.isEmpty(login.getCompany())){
                String company = judgeCompany(login.getEmployeeGroup());
                login.setCompany(company);
            }
            String employeeGroup = judgeEmployeeGroup(login.getEmployeeGroup());
            login.setEmployeeGroup(employeeGroup);

            try {
                List<ItemsAnalysisEntity> itemsAnalysisList=
                        JSON.parseArray(JSON.parseObject(json_str).getString("items"), ItemsAnalysisEntity.class);
                for (ItemsAnalysisEntity itemsAnalysisEntity:itemsAnalysisList) {
                    System.out.println("===============插入单条的签入场景items数据："+itemsAnalysisEntity.toString());

                    login.setItemTypeClass(itemsAnalysisEntity.getItemTypeClass());
                    login.setItemTypeName(itemsAnalysisEntity.getItemTypeName());
                    login.setItemName(itemsAnalysisEntity.getItemName());
                    login.setItemId(itemsAnalysisEntity.getItemId());
                    login.setVersion(itemsAnalysisEntity.getVersion());
                    login.setOwningUser(itemsAnalysisEntity.getOwningUser());
                    login.setOwningGroup(itemsAnalysisEntity.getOwningGroup());

                    sceneMytcCheckinDataMapper.insert(login);
                }
            } catch (Exception e) {
                System.out.println("-----------parseArray异常的json"+json_str);
                e.printStackTrace();
            }

            //sceneMytcCheckinDataMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("g_mytc_checkoutData")) {
            SceneMytcCheckoutData login = JSONObject.parseObject(jsonParam.toJSONString(), SceneMytcCheckoutData.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);
            if(StringUtils.isEmpty(login.getCompany())){
                String company = judgeCompany(login.getEmployeeGroup());
                login.setCompany(company);
            }
            String employeeGroup = judgeEmployeeGroup(login.getEmployeeGroup());
            login.setEmployeeGroup(employeeGroup);

            try {
                List<ItemsAnalysisEntity> itemsAnalysisList=
                        JSON.parseArray(JSON.parseObject(json_str).getString("items"), ItemsAnalysisEntity.class);
                for (ItemsAnalysisEntity itemsAnalysisEntity:itemsAnalysisList) {
                    System.out.println("===============插入单条的签出场景items数据："+itemsAnalysisEntity.toString());

                    login.setItemTypeClass(itemsAnalysisEntity.getItemTypeClass());
                    login.setItemTypeName(itemsAnalysisEntity.getItemTypeName());
                    login.setItemName(itemsAnalysisEntity.getItemName());
                    login.setItemId(itemsAnalysisEntity.getItemId());
                    login.setVersion(itemsAnalysisEntity.getVersion());
                    login.setOwningUser(itemsAnalysisEntity.getOwningUser());
                    login.setOwningGroup(itemsAnalysisEntity.getOwningGroup());

                    sceneMytcCheckoutDataMapper.insert(login);
                }
            } catch (Exception e) {
                System.out.println("-----------parseArray异常的json"+json_str);
                e.printStackTrace();
            }

            //sceneMytcCheckoutDataMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("g_mytc_cancelCheckout")) {
            SceneCancelCheckout login = JSONObject.parseObject(jsonParam.toJSONString(), SceneCancelCheckout.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);
            if(StringUtils.isEmpty(login.getCompany())){
                String company = judgeCompany(login.getEmployeeGroup());
                login.setCompany(company);
            }
            String employeeGroup = judgeEmployeeGroup(login.getEmployeeGroup());
            login.setEmployeeGroup(employeeGroup);

            try {
                List<ItemsAnalysisEntity> itemsAnalysisList=
                        JSON.parseArray(JSON.parseObject(json_str).getString("items"), ItemsAnalysisEntity.class);
                for (ItemsAnalysisEntity itemsAnalysisEntity:itemsAnalysisList) {
                    System.out.println("===============插入单条的取消签出场景items数据："+itemsAnalysisEntity.toString());

                    login.setItemTypeClass(itemsAnalysisEntity.getItemTypeClass());
                    login.setItemTypeName(itemsAnalysisEntity.getItemTypeName());
                    login.setItemName(itemsAnalysisEntity.getItemName());
                    login.setItemId(itemsAnalysisEntity.getItemId());
                    login.setVersion(itemsAnalysisEntity.getVersion());
                    login.setOwningUser(itemsAnalysisEntity.getOwningUser());
                    login.setOwningGroup(itemsAnalysisEntity.getOwningGroup());

                    cancelCheckoutMapper.insert(login);
                }
            } catch (Exception e) {
                System.out.println("-----------parseArray异常的json"+json_str);
                e.printStackTrace();
            }

            //cancelCheckoutMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("g_general_copy")) {
            SceneGeneralCopy login = JSONObject.parseObject(jsonParam.toJSONString(), SceneGeneralCopy.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);
            if(StringUtils.isEmpty(login.getCompany())){
                String company = judgeCompany(login.getEmployeeGroup());
                login.setCompany(company);
            }
            String employeeGroup = judgeEmployeeGroup(login.getEmployeeGroup());
            login.setEmployeeGroup(employeeGroup);

            try {
                List<ItemsAnalysisEntity> itemsAnalysisList=
                        JSON.parseArray(JSON.parseObject(json_str).getString("items"), ItemsAnalysisEntity.class);
                for (ItemsAnalysisEntity itemsAnalysisEntity:itemsAnalysisList) {
                    System.out.println("===============插入单条的复制场景items数据："+itemsAnalysisEntity.toString());

                    login.setItemTypeClass(itemsAnalysisEntity.getItemTypeClass());
                    login.setItemTypeName(itemsAnalysisEntity.getItemTypeName());
                    login.setItemName(itemsAnalysisEntity.getItemName());
                    login.setItemId(itemsAnalysisEntity.getItemId());
                    login.setVersion(itemsAnalysisEntity.getVersion());
                    login.setOwningUser(itemsAnalysisEntity.getOwningUser());
                    login.setOwningGroup(itemsAnalysisEntity.getOwningGroup());

                    sceneGeneralCopyMapper.insert(login);
                }
            } catch (Exception e) {
                System.out.println("-----------parseArray异常的json"+json_str);
                e.printStackTrace();
            }

            //sceneGeneralCopyMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("g_general_cut")) {
            SceneGeneralCut login = JSONObject.parseObject(jsonParam.toJSONString(), SceneGeneralCut.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);
            if(StringUtils.isEmpty(login.getCompany())){
                String company = judgeCompany(login.getEmployeeGroup());
                login.setCompany(company);
            }
            String employeeGroup = judgeEmployeeGroup(login.getEmployeeGroup());
            login.setEmployeeGroup(employeeGroup);

            try {
                List<ItemsAnalysisEntity> itemsAnalysisList=
                        JSON.parseArray(JSON.parseObject(json_str).getString("items"), ItemsAnalysisEntity.class);
                for (ItemsAnalysisEntity itemsAnalysisEntity:itemsAnalysisList) {
                    System.out.println("===============插入单条的剪切场景items数据："+itemsAnalysisEntity.toString());

                    login.setItemTypeClass(itemsAnalysisEntity.getItemTypeClass());
                    login.setItemTypeName(itemsAnalysisEntity.getItemTypeName());
                    login.setItemName(itemsAnalysisEntity.getItemName());
                    login.setItemId(itemsAnalysisEntity.getItemId());
                    login.setVersion(itemsAnalysisEntity.getVersion());
                    login.setOwningUser(itemsAnalysisEntity.getOwningUser());
                    login.setOwningGroup(itemsAnalysisEntity.getOwningGroup());

                    sceneGeneralCutMapper.insert(login);
                }
            } catch (Exception e) {
                System.out.println("-----------parseArray异常的json"+json_str);
                e.printStackTrace();
            }

            //sceneGeneralCutMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("g_general_paste")) {
            SceneGeneralPaste login = JSONObject.parseObject(jsonParam.toJSONString(), SceneGeneralPaste.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);
            if(StringUtils.isEmpty(login.getCompany())){
                String company = judgeCompany(login.getEmployeeGroup());
                login.setCompany(company);
            }
            String employeeGroup = judgeEmployeeGroup(login.getEmployeeGroup());
            login.setEmployeeGroup(employeeGroup);

            try {
                List<ItemsAnalysisEntity> itemsAnalysisList=
                        JSON.parseArray(JSON.parseObject(json_str).getString("items"), ItemsAnalysisEntity.class);
                for (ItemsAnalysisEntity itemsAnalysisEntity:itemsAnalysisList) {
                    System.out.println("===============插入单条的粘贴场景items数据："+itemsAnalysisEntity.toString());

                    login.setItemTypeClass(itemsAnalysisEntity.getItemTypeClass());
                    login.setItemTypeName(itemsAnalysisEntity.getItemTypeName());
                    login.setItemName(itemsAnalysisEntity.getItemName());
                    login.setItemId(itemsAnalysisEntity.getItemId());
                    login.setVersion(itemsAnalysisEntity.getVersion());
                    login.setOwningUser(itemsAnalysisEntity.getOwningUser());
                    login.setOwningGroup(itemsAnalysisEntity.getOwningGroup());

                    sceneGeneralPasteMapper.insert(login);
                }
            } catch (Exception e) {
                System.out.println("-----------parseArray异常的json"+json_str);
                e.printStackTrace();
            }

            //sceneGeneralPasteMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("g_mytc_viewDoc")) {
            SceneMytcViewDoc login = JSONObject.parseObject(jsonParam.toJSONString(), SceneMytcViewDoc.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            //判断跨部门
            String employeeGroup = login.getEmployeeGroup();
            String ownerGroup = login.getOwningGroup();
            if(StringUtils.isNotEmpty(employeeGroup)){
                int employeeGroupIndex = employeeGroup.indexOf(".");
                String employeeGroupSub1 = employeeGroup.substring(0, employeeGroupIndex);
                String employeeGroupSub2= employeeGroup.substring(employeeGroupIndex+1,employeeGroup.length());

                login.setCompany(employeeGroupSub2);
                login.setEmployeeGroup(employeeGroupSub1);
                if(StringUtils.isNotEmpty(ownerGroup)){
                    if(employeeGroup.contains(".") && ownerGroup.contains(".")){
                        int ownerGroupIndex = ownerGroup.indexOf(".");
                        String ownerGroupSub1 = ownerGroup.substring(0, ownerGroupIndex);
                        String ownerGroupSub2= ownerGroup.substring(ownerGroupIndex+1, ownerGroup.length());

                        login.setOwningCompany(ownerGroupSub2);
                        login.setOwningGroup(ownerGroupSub1);

                        //判断跨部门
                        if (!employeeGroupSub1.equals(ownerGroupSub1)){
                            login.setCrossDepartmentView("是");
                        }else{
                            login.setCrossDepartmentView("否");
                        }

                        //机密文件下载
                        login.setSecretFileView("是");
                    }
                }
            }

            sceneMytcViewDocMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("g_mytc_open_dataset")) {
            SceneMytcOpenDataset login = JSONObject.parseObject(jsonParam.toJSONString(), SceneMytcOpenDataset.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            if(StringUtils.isEmpty(login.getContainerId())){
                login.setContainerId("_NA_");
            }

            if(StringUtils.isEmpty(login.getContainerVersion())){
                login.setContainerVersion("_NA_");
            }

            //判断跨公司和跨部门
            String employeeGroup = login.getEmployeeGroup();
            String ownerGroup = login.getOwningGroup();
            if(StringUtils.isNotEmpty(employeeGroup)){
                if(StringUtils.isNotEmpty(ownerGroup)){
                    if(employeeGroup.contains(".") && ownerGroup.contains(".")){
                        int employeeGroupIndex = employeeGroup.indexOf(".");
                        String employeeGroupSub1 = employeeGroup.substring(0, employeeGroupIndex);
                        String employeeGroupSub2= employeeGroup.substring(employeeGroupIndex+1,employeeGroup.length());

                        login.setCompany(employeeGroupSub2);
                        login.setEmployeeGroup(employeeGroupSub1);

                        int ownerGroupIndex = ownerGroup.indexOf(".");
                        String ownerGroupSub1 = ownerGroup.substring(0, ownerGroupIndex);
                        String ownerGroupSub2= ownerGroup.substring(ownerGroupIndex+1, ownerGroup.length());

                        login.setOwningCompany(ownerGroupSub2);
                        login.setOwningGroup(ownerGroupSub1);

                        //判断跨部门
                        if (!employeeGroupSub1.equals(ownerGroupSub1)){
                            login.setCrossDepartmentView("是");
                        }else{
                            login.setCrossDepartmentView("否");
                        }

                        //判断跨公司
                        if (!employeeGroupSub2.equals(ownerGroupSub2)){
                            login.setCrossCompanyView("是");
                        }else{
                            login.setCrossCompanyView("否");
                        }

                        //机密文件下载
                        login.setSecretFileView("是");
                    }
                }
            }

            sceneMytcOpenDatasetMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("g_general_delete")) {
            SceneGeneralDelete login = JSONObject.parseObject(jsonParam.toJSONString(), SceneGeneralDelete.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            try {
                List<ItemsAnalysisEntity> itemsAnalysisList=
                        JSON.parseArray(JSON.parseObject(json_str).getString("items"), ItemsAnalysisEntity.class);
                for (ItemsAnalysisEntity itemsAnalysisEntity:itemsAnalysisList) {
                    System.out.println("===============插入单条的删除场景items数据："+itemsAnalysisEntity.toString());

                    login.setItemTypeClass(itemsAnalysisEntity.getItemTypeClass());
                    login.setItemTypeName(itemsAnalysisEntity.getItemTypeName());
                    login.setItemName(itemsAnalysisEntity.getItemName());
                    login.setItemId(itemsAnalysisEntity.getItemId());
                    login.setVersion(itemsAnalysisEntity.getVersion());
                    login.setOwningUser(itemsAnalysisEntity.getOwningUser());
                    login.setOwningGroup(itemsAnalysisEntity.getOwningGroup());

                    //判断跨部门和跨属主
                    String employeeGroup = login.getEmployeeGroup();
                    String ownerGroup = login.getOwningGroup();
                    if(StringUtils.isNotEmpty(employeeGroup)){
                        if(StringUtils.isNotEmpty(ownerGroup)){
                            if(employeeGroup.contains(".") && ownerGroup.contains(".")){
                                int employeeGroupIndex = employeeGroup.indexOf(".");
                                String employeeGroupSub1 = employeeGroup.substring(0, employeeGroupIndex);
                                String employeeGroupSub2= employeeGroup.substring(employeeGroupIndex+1,employeeGroup.length());

                                login.setCompany(employeeGroupSub2);
                                login.setEmployeeGroup(employeeGroupSub1);

                                int ownerGroupIndex = ownerGroup.indexOf(".");
                                String ownerGroupSub1 = ownerGroup.substring(0, ownerGroupIndex);
                                String ownerGroupSub2= ownerGroup.substring(ownerGroupIndex+1, ownerGroup.length());

                                login.setOwningCompany(ownerGroupSub2);
                                login.setOwningGroup(ownerGroupSub1);

                                //判断跨部门
                                if (!employeeGroupSub1.equals(ownerGroupSub1)){
                                    login.setCrossDepartmentDelete("是");
                                }else{
                                    login.setCrossDepartmentDelete("否");
                                }

                                //判断跨属主
                                if(login.getEmployeeName().contains(login.getOwningUser()) ||
                                        login.getOwningUser().contains(login.getEmployeeName())){
                                    login.setCrossOwnerDelete("否");
                                }else{
                                    login.setCrossOwnerDelete("是");
                                }
                            }
                        }
                    }

                    sceneGeneralDeleteMapper.insert(login);
                }
            } catch (Exception e) {
                System.out.println("-----------parseArray异常的json"+json_str);
                e.printStackTrace();
            }

            //sceneGeneralDeleteMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("g_mytc_export_PLMXML")) {
            SceneMytcExportPLMXML login = JSONObject.parseObject(jsonParam.toJSONString(), SceneMytcExportPLMXML.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            //判断跨部门和跨属主
            String employeeGroup = login.getEmployeeGroup();
            String ownerGroup = login.getOwningGroup();
            if(StringUtils.isNotEmpty(employeeGroup)){
                if(StringUtils.isNotEmpty(ownerGroup)){
                    if(employeeGroup.contains(".") && ownerGroup.contains(".")){
                        int employeeGroupIndex = employeeGroup.indexOf(".");
                        String employeeGroupSub1 = employeeGroup.substring(0, employeeGroupIndex);
                        String employeeGroupSub2= employeeGroup.substring(employeeGroupIndex+1,employeeGroup.length());

                        login.setCompany(employeeGroupSub2);
                        login.setEmployeeGroup(employeeGroupSub1);

                        int ownerGroupIndex = ownerGroup.indexOf(".");
                        String ownerGroupSub1 = ownerGroup.substring(0, ownerGroupIndex);
                        String ownerGroupSub2= ownerGroup.substring(ownerGroupIndex+1, ownerGroup.length());

                        login.setOwningCompany(ownerGroupSub2);
                        login.setOwningGroup(ownerGroupSub1);

                        //判断跨部门
                        if (!employeeGroupSub1.equals(ownerGroupSub1)){
                            login.setCrossDepartmentDownload("是");
                        }else{
                            login.setCrossDepartmentDownload("否");
                        }

                        //判断机密
                        login.setSecretFileDownload("是");

                        //判断大小超标
                        login.setSizeOutOfDownload("暂定大小超标");

                        //判断超量
                        String employeeName = login.getEmployeeName();
                        if (StringUtils.isNotEmpty(employeeName)){
                            Map map = new HashMap();
                            map.put("tableName","scene_mytc_export_PLMXML");
                            map.put("employeeName",employeeName);
                            Integer downloadDocNum = bydMapper.judgeOverloadDownload(map);
                            if (downloadDocNum > 50){
                                login.setOverloadDownload("是");
                            }else{
                                login.setOverloadDownload("否");
                            }
                        }
                    }
                }
            }

            sceneMytcExportPLMXMLMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("g_mytc_namedRef_download")) {
            SceneMytcNamedRefDownload login = JSONObject.parseObject(jsonParam.toJSONString(), SceneMytcNamedRefDownload.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            //判断跨部门和跨属主
            String employeeGroup = login.getEmployeeGroup();
            String ownerGroup = login.getOwningGroup();
            if(StringUtils.isNotEmpty(employeeGroup)){
                if(StringUtils.isNotEmpty(ownerGroup)){
                    if(employeeGroup.contains(".") && ownerGroup.contains(".")){
                        int employeeGroupIndex = employeeGroup.indexOf(".");
                        String employeeGroupSub1 = employeeGroup.substring(0, employeeGroupIndex);
                        String employeeGroupSub2= employeeGroup.substring(employeeGroupIndex+1,employeeGroup.length());

                        login.setCompany(employeeGroupSub2);
                        login.setEmployeeGroup(employeeGroupSub1);

                        int ownerGroupIndex = ownerGroup.indexOf(".");
                        String ownerGroupSub1 = ownerGroup.substring(0, ownerGroupIndex);
                        String ownerGroupSub2= ownerGroup.substring(ownerGroupIndex+1, ownerGroup.length());

                        login.setOwningCompany(ownerGroupSub2);
                        login.setOwningGroup(ownerGroupSub1);

                        //判断跨部门
                        if (!employeeGroupSub1.equals(ownerGroupSub1)){
                            login.setCrossDepartmentDownload("是");
                        }else{
                            login.setCrossDepartmentDownload("否");
                        }

                        //判断机密
                        login.setSecretFileDownload("是");

                        //判断大小超标
                        login.setSizeOutOfDownload("暂定大小超标");

                        //判断超量
                        String employeeName = login.getEmployeeName();
                        if (StringUtils.isNotEmpty(employeeName)){
                            Map map = new HashMap();
                            map.put("tableName","scene_mytc_namedRef_download");
                            map.put("employeeName",employeeName);
                            Integer downloadDocNum = bydMapper.judgeOverloadDownload(map);
                            if (downloadDocNum > 50){
                                login.setOverloadDownload("是");
                            }else{
                                login.setOverloadDownload("否");
                            }
                        }
                    }
                }
            }

            sceneMytcNamedRefDownloadMapper.insert(login);
        } else{
            flag = false;
        }
        System.out.println("==========="+sb);
        // 将获取的json数据封装一层，然后在给返回
        JSONObject result = new JSONObject();
        if (flag)
            result.put("result", "200");
        else
            result.put("result", "error");

        return result.toJSONString();
    }

    //插入场景数据
    /*@ResponseBody
    @RequestMapping(value = "rest/insertOperationInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getByJSON(@RequestBody JSONObject jsonParam) {
        // 直接将json信息打印出来
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-插入json数据"+jsonParam.toJSONString());
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // 记录下请求内容
        StringBuilder sb = new StringBuilder();
        sb.append("###请求URL: " + request.getRequestURL().toString());
        sb.append("   ###IP: " + request.getRemoteAddr());

        String ip = request.getRemoteAddr();

        //获取场景名称
        String changjing = (String) jsonParam.get("actionScene");
        JSONObject.DEFFAULT_DATE_FORMAT = "yyyy-MM-ddHH:mm:ss.mmm";
        boolean flag = true;

        if (changjing.equalsIgnoreCase("login")) {
            SceneLogin login = JSONObject.parseObject(jsonParam.toJSONString(), SceneLogin.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            login = judgeLoginStatus(login);
            loginMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("create_component")) {
            SceneCreateComponent login = JSONObject.parseObject(jsonParam.toJSONString(), SceneCreateComponent.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            createComponentMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("search")) {
            SceneSearch login = JSONObject.parseObject(jsonParam.toJSONString(), SceneSearch.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            searchMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("send_to_PSE")) {
            SceneSendtoPSE login = JSONObject.parseObject(jsonParam.toJSONString(), SceneSendtoPSE.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            sendtoPSEMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("checkout_data")) {
            SceneCheckoutData login = JSONObject.parseObject(jsonParam.toJSONString(), SceneCheckoutData.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            checkoutDataMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("checkin_data")) {
            SceneCheckinData login = JSONObject.parseObject(jsonParam.toJSONString(), SceneCheckinData.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            checkinDataMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("name_ref")) {
            SceneNameRef login = JSONObject.parseObject(jsonParam.toJSONString(), SceneNameRef.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            namerefMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("create_workflow")) {
            SceneCreateWorkflow login = JSONObject.parseObject(jsonParam.toJSONString(), SceneCreateWorkflow.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            createWorkflowMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("workflow_approve")) {
            SceneWorkflowApprove login = JSONObject.parseObject(jsonParam.toJSONString(), SceneWorkflowApprove.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            workflowApproveMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("BOM_expand_upward")) {
        //} else if (changjing.equalsIgnoreCase("BOM_expand_downward")) {
            SceneBOMExpandDownward login = JSONObject.parseObject(jsonParam.toJSONString(), SceneBOMExpandDownward.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            bOMExpandDownwardMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("logout")) {
            SceneLogout login = JSONObject.parseObject(jsonParam.toJSONString(), SceneLogout.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            logoutMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("checkin_dataset")) {
            SceneCheckinDataset login = JSONObject.parseObject(jsonParam.toJSONString(), SceneCheckinDataset.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            checkinDatasetMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("checkout_dataset")) {
            SceneCheckoutDataset login = JSONObject.parseObject(jsonParam.toJSONString(), SceneCheckoutDataset.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            checkoutDatasetMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("cancel_checkout")) {
            SceneCancelCheckout login = JSONObject.parseObject(jsonParam.toJSONString(), SceneCancelCheckout.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            cancelCheckoutMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("close_folder_newstuff")) {
            SceneCloseFolderNewstuff login = JSONObject.parseObject(jsonParam.toJSONString(), SceneCloseFolderNewstuff.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            closeFolderNewstuffMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("close_item_object")) {
            SceneCloseItemObject login = JSONObject.parseObject(jsonParam.toJSONString(), SceneCloseItemObject.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            closeItemObjectMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("copy_doc_version")) {
            SceneCopyDocVersion login = JSONObject.parseObject(jsonParam.toJSONString(), SceneCopyDocVersion.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            copyDocVersionMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("paste_doc_version")) {
            ScenePasteDocVersion login = JSONObject.parseObject(jsonParam.toJSONString(), ScenePasteDocVersion.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            pasteDocVersionMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("copy_component_version")) {
            SceneCopyComponentVersion login = JSONObject.parseObject(jsonParam.toJSONString(), SceneCopyComponentVersion.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            copyComponentVersionMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("paste_component_version")) {
            ScenePasteComponentVersion login = JSONObject.parseObject(jsonParam.toJSONString(), ScenePasteComponentVersion.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            pasteComponentVersionMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("create_dataset_text")) {
            SceneCreateDatasetText login = JSONObject.parseObject(jsonParam.toJSONString(), SceneCreateDatasetText.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            createDatasetTextMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("create_doc_object")) {
            SceneCreateDocObject login = JSONObject.parseObject(jsonParam.toJSONString(), SceneCreateDocObject.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            createDocObjectMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("create_folder")) {
            SceneCreateFolder login = JSONObject.parseObject(jsonParam.toJSONString(), SceneCreateFolder.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            createFolderMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("create_form")) {
            SceneCreateForm login = JSONObject.parseObject(jsonParam.toJSONString(), SceneCreateForm.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            createFormMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("delete_form")) {
            SceneDeleteForm login = JSONObject.parseObject(jsonParam.toJSONString(), SceneDeleteForm.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            deleteFormMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("delete_component_version")) {
            SceneDeleteComponentVersion login = JSONObject.parseObject(jsonParam.toJSONString(), SceneDeleteComponentVersion.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            deleteComponentVersionMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("revise_component_version")) {
            SceneReviseComponentVersion login = JSONObject.parseObject(jsonParam.toJSONString(), SceneReviseComponentVersion.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            reviseComponentVersionMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("revise_doc_version")) {
            SceneReviseDocVersion login = JSONObject.parseObject(jsonParam.toJSONString(), SceneReviseDocVersion.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            reviseDocVersionMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("open_dataset")) {
        //} else if (changjing.equalsIgnoreCase("open_dataset_text")) {
            SceneOpenDatasetText login = JSONObject.parseObject(jsonParam.toJSONString(), SceneOpenDatasetText.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            openDatasetTextMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("open_folder")) {
            SceneOpenFolder login = JSONObject.parseObject(jsonParam.toJSONString(), SceneOpenFolder.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            openFolderMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("open_home")) {
            SceneOpenHome login = JSONObject.parseObject(jsonParam.toJSONString(), SceneOpenHome.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            openHomeMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("open_newstuff")) {
            SceneOpenNewstuff login = JSONObject.parseObject(jsonParam.toJSONString(), SceneOpenNewstuff.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            openNewstuffMapper.insert(login);
        }

        //自定义场景
        else if (changjing.equalsIgnoreCase("download_doc")) {
            SceneDownloadDoc login = JSONObject.parseObject(jsonParam.toJSONString(), SceneDownloadDoc.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            login = judgeDownloadDoc(login);
            sceneDownloadDocMapper.insert(login);
        } else if (changjing.equalsIgnoreCase("view_doc")) {
            SceneViewDoc login = JSONObject.parseObject(jsonParam.toJSONString(), SceneViewDoc.class);
            int operationTime = DateUtil.computeOperationTime(login.getActionBeginTime(), login.getActionEndTime());
            login.setIp(ip);
            login.setOperationTime(operationTime);

            login = judgeViewDoc(login);
            sceneViewDocMapper.insert(login);
        } else
            flag = false;

        System.out.println("=============>" + sb);

        // 将获取的json数据封装一层，然后在给返回
        JSONObject result = new JSONObject();
        if (flag)
            result.put("result", "200");
        else
            result.put("result", "error");
        return result.toJSONString();
    }*/

    //判断公司
    public String judgeCompany(String employeeGroup){
        String company = "";
        if(StringUtils.isNotEmpty(employeeGroup)){
            if (employeeGroup.contains(".")) {
                int employeeGroupIndex = employeeGroup.indexOf(".");
                company = employeeGroup.substring(employeeGroupIndex + 1, employeeGroup.length());
            }
        }
        return company;
    }

    //判断employeeGroup
    public String judgeEmployeeGroup(String employeeGroup){
        String employeeGroup01 = "";
        if(StringUtils.isNotEmpty(employeeGroup)){
            if (employeeGroup.contains(".")) {
                int employeeGroupIndex = employeeGroup.indexOf(".");
                employeeGroup01 = employeeGroup.substring(0, employeeGroupIndex);
            }
        }
        return employeeGroup01;
    }

    //判断登录状态，正常登录/跨部门登录
    public SceneLogin judgeLoginStatus(SceneLogin sceneLogin) {
        String hostNameSub = "";
        if (StringUtils.isNotEmpty(sceneLogin.getHostName())) {
            hostNameSub = sceneLogin.getHostName().substring(sceneLogin.getHostName().length() - 3, sceneLogin.getHostName().length());
            List<String> hostNameGroupList = bydMapper.getHostNameGroup(hostNameSub);

            if(hostNameGroupList.size() == 1){
                sceneLogin.setHostNameGroup(hostNameGroupList.get(0));
            }

            if(hostNameGroupList.size()>1){
                sceneLogin.setHostNameGroup(hostNameSub);
            }

            //对比用户所在部门和主机所在部门，判断是否正常登录
            if(hostNameGroupList.size() > 0){
                for (String hostNameGroup : hostNameGroupList) {
                    if(sceneLogin.getEmployeeGroup().contains(hostNameGroup) == true){
                        sceneLogin.setLoginStatus("正常登录");
                    }
                }
            }

            //对比用户所在部门和主机所在部门，判断是否正常登录
/*            if(hostNameGroupList.size() > 0){
                for (String hostNameGroup : hostNameGroupList) {
                    if(hostNameGroup.equals(sceneLogin.getEmployeeGroup())){
                        sceneLogin.setLoginStatus("正常登录");
                    }
                }
            }*/

            if(StringUtils.isEmpty(sceneLogin.getLoginStatus())){
                sceneLogin.setLoginStatus("跨部门登录");
            }
        }

        return sceneLogin;
    }

    //判断文档下载情况，超量下载(50)/跨部门下载/机密文件下载
    /*public SceneDownloadDoc judgeDownloadDoc(SceneDownloadDoc sceneDownloadDoc){
        //判断超量
        String employeeName = sceneDownloadDoc.getEmployeeName();
        if (StringUtils.isNotEmpty(employeeName)){
            Integer downloadDocNum = bydMapper.judgeOverloadDownload(employeeName);
            if (downloadDocNum > 50){
                sceneDownloadDoc.setOverloadDownload("是");
            }else{
                sceneDownloadDoc.setOverloadDownload("否");
            }
        }

        //判断跨部门下载
        String employeeGroup = sceneDownloadDoc.getEmployeeGroup();
        String docGroup = sceneDownloadDoc.getDocGroup();
        if (StringUtils.isNotEmpty(employeeGroup)){
            if(employeeGroup.equals(docGroup)){
                sceneDownloadDoc.setCrossDepartmentDownload("否");
            }else{
                sceneDownloadDoc.setCrossDepartmentDownload("是");
            }
        }

        //判断机密文件下载,暂时认为所有文件为机密文件
        sceneDownloadDoc.setSecretFileDownload("是");

        return sceneDownloadDoc;
    }
*/
    //判断文档查看情况，跨部门查看/机密文件查看
    public SceneViewDoc judgeViewDoc(SceneViewDoc sceneViewDoc){
        //判断跨部门查看
        String employeeGroup = sceneViewDoc.getEmployeeGroup();
        String docGroup = sceneViewDoc.getDocGroup();
        if (StringUtils.isNotEmpty(employeeGroup)){
            if(employeeGroup.equals(docGroup)){
                sceneViewDoc.setCrossDepartmentView("否");
            }else{
                sceneViewDoc.setCrossDepartmentView("是");
            }
        }

        //判断机密文件查看,暂时认为所有文件为机密文件
        sceneViewDoc.setSecretFileView("是");
        return sceneViewDoc;
    }

    public static int readInt(byte[] byte_array,int offset)
    {
        //byte[] byte_array= new byte[]{0x12,0x34,0x56,0x78};
        //按低位数字在低字节移位
        int int_value = (byte_array[offset + 0] & 0xff) | ((byte_array[offset + 1] << 8) & 0xff00)  | ((byte_array[offset + 2] << 16) & 0x00ff0000) | (byte_array[offset + 3] << 24);
        return  int_value;
    }

}


