package com.szdeepwise.bydtcmonitor.service.impl;

import com.szdeepwise.bydtcmonitor.entity.HelpEntity;
import com.szdeepwise.bydtcmonitor.mapper.BYDMapper;
import com.szdeepwise.bydtcmonitor.service.ReadExcelService;
import com.szdeepwise.bydtcmonitor.util.ReadExcel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author xubowen
 * @description
 * @date 2020年 06月22日
 */
@Service
public class ReadExcelServiceImpl implements ReadExcelService {

    @Autowired
    private BYDMapper bydMapper;

    //导入excel到数据库
    @Override
    public void operaExcel(String filePath) {
        //String filePath1 = filePath.substring(0,filePath.lastIndexOf("/")+1);
        //String fileName1 = filePath.substring(filePath.lastIndexOf("/")+1);
        Map<String,List<String[]>> list = analysisExcel("系统监控指标.xlsx",
                "D:\\");
        //System.out.println(list.toString());
        for(int i=0;i<list.size();i++){
            List<String[]> l = list.get("1");
            for (String[] s: l) {
                for (int j = 0; j < s.length; j++) {
                    System.out.println(s[j]);
                }
                System.out.println();
            }
        }
    }

    //组件模糊查询
    @Override
    public Map fuzzyQueryComponent(HelpEntity helpEntitys) {
        Map map = new HashMap();
        List<String> fuzzyComponentList = bydMapper.fuzzyQueryComponent(helpEntitys);
        map.put("fuzzyComponentList",fuzzyComponentList);
        return map;
    }

    //指标名称模糊查询
    @Override
    public Map fuzzyQueryName(HelpEntity helpEntitys) {
        Map map = new HashMap();
        List<String> fuzzyNameList = bydMapper.fuzzyQueryName(helpEntitys);
        map.put("fuzzyNameList",fuzzyNameList);
        return map;
    }

    //动态菜单栏数据展示
    @Override
    public String getHelpData(HelpEntity helpEntity) {
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        ArrayList<String> componentList = new ArrayList<>();
        List<String> componentBeginList = bydMapper.queryAllComponent();
        List<String> componentSearchList = bydMapper.queryExcelComponent(helpEntity);

        //移除已经不存在的component
        for (int i = 0; i < componentBeginList.size(); i++) {
            List<String> componentList01 = bydMapper.tHelpQueryComponent(componentBeginList.get(i));
            if (componentList01.size() == 0){
                bydMapper.tComponentDelete(componentBeginList.get(i));
                componentBeginList.remove(i);
            }

        }

        //选择需要的component
        for (String componentBegin : componentBeginList) {
            for (String componentSearch : componentSearchList) {
                    if(componentBegin.contains(componentSearch) || componentSearch.contains(componentBegin)){
                        componentList.add(componentBegin);
                    }
            }
        }

        if (componentList.size() != 0) {
            HashSet<String> hashSet02 = new HashSet<String>(componentList);
            componentList.clear();
            componentList.addAll(hashSet02);
            Collections.sort(componentList);
        }

        for (String component:componentList) {
            JSONObject arrayElementOne = new JSONObject();
            arrayElementOne.element("name",component);
            //根据component查询组件相关信息
            helpEntity.setComponent(component);
            List<HelpEntity> helpList = bydMapper.queryHelpInfo(helpEntity);
            JSONArray arrayElementOneArrayElementOne = JSONArray.fromObject(helpList);
            arrayElementOne.element("children",arrayElementOneArrayElementOne);
            array.add(arrayElementOne);
        }
        object.element("nameList",array);
        String resultString = object.toString();
        return resultString;
    }

    //新增help信息
    @Override
    public int insertHelpData(HelpEntity helpEntitys) {
        int i = bydMapper.insertExcel(helpEntitys);
        List<String> componentList = bydMapper.tComponentQuery(helpEntitys);
        if (componentList.size() == 0) {
            int i1 = bydMapper.insertComponent(helpEntitys.getComponent());
        }
        return i;
    }

    //删除
    @Override
    public int deleteHelpData(int id) {
        int result = bydMapper.deleteHelpData(id);
        return result;
    }

    //更新
    @Override
    public int updateHelpData(HelpEntity helpEntitys) {
        int i = bydMapper.updateHelpData(helpEntitys);
        List<String> componentList = bydMapper.tComponentQuery(helpEntitys);
        if (componentList.size() == 0) {
            int i1 = bydMapper.insertComponent(helpEntitys.getComponent());
        }

/*        String component = bydMapper.queryComponent(helpEntitys);
        if(StringUtils.isEmpty(component)){
            bydMapper.insertComponent(component);
        }*/
        return i;
    }

    //获取ip、username、password、referencelogpath等信息
    @Override
    public HelpEntity getHttpDownloadInfo(int id) {
        HelpEntity helpEntity = new HelpEntity();
        helpEntity.setId(id);
        List<HelpEntity> helpEntities = bydMapper.queryHelpInfo(helpEntity);
        if (helpEntities.size() != 0) {
            if (null != helpEntities.get(0)) {
                helpEntity = helpEntities.get(0);
            }
        }
        return helpEntity;
    }

    //nagios请求 获取日志路径
    @Override
    public String queryLogByServiceName(String servicesName) {
        String referenceLog = bydMapper.queryLogByServiceName(servicesName);

        return referenceLog;
    }

    public Map<String,ArrayList<ArrayList<String>>> readExcel(String fileName, String path) {
        Map<String,ArrayList<ArrayList<String>>> map = new HashMap<String, ArrayList<ArrayList<String>>>();

        try {
            Workbook workBook = null;
            try {
                workBook = new XSSFWorkbook(path + "/" + fileName);
            } catch (Exception ex) {
                workBook = new HSSFWorkbook(new FileInputStream(path + "/"
                        + fileName));
            }

            for (int numSheet = 0; numSheet < workBook.getNumberOfSheets(); numSheet++) {
                ArrayList<ArrayList<String>> Row = new ArrayList<ArrayList<String>>();
                Sheet sheet = workBook.getSheetAt(numSheet);
                String sheetName = sheet.getSheetName();
                bydMapper.insertComponent(sheetName);
                if (sheet == null) {
                    continue;
                }
                // 循环行Row
                for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                    org.apache.poi.ss.usermodel.Row row = sheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    }
                    // 循环列Cell
                    ArrayList<String> arrCell = new ArrayList<String>();
                    for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
                        Cell cell = row.getCell(cellNum);
                        if (cell == null) {
                            cell = row.createCell(cellNum);
                            cell.setCellValue("");
                        }
                        arrCell.add(getValue(cell));
                    }

                    Row.add(arrCell);
                }
                map.put(String.valueOf(numSheet), Row);

            }
        } catch (IOException e) {
            System.out.println("e:" + e);
        }

        return map;
    }

    @SuppressWarnings("static-access")
    private static String getValue(Cell cell) {
        if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }

    /**
     * 返回map
     * key:sheet页签
     * value:List里面的String[]为一行的数据
     * @param
     * @return
     */
    public Map<String,List<String[]>> analysisExcel(String fileName, String path){
        System.out.println("------------------------");
        Map<String,ArrayList<ArrayList<String>>> sheet = readExcel(fileName, path);
        System.out.println("-------------------------------");
        Map<String,List<String[]>> map = new HashMap<String, List<String[]>>();
        for (int i=0;i<sheet.size();i++) {
            ArrayList<ArrayList<String>> row = sheet.get(String.valueOf(i));
            List<String[]> list = new ArrayList<String[]>();
            if(row.size()>0){
                for(int j=0;j<row.size();j++){

                    ArrayList<String> cell = row.get(j);
                    String components = cell.get(0);//组件
                    if(StringUtils.isEmpty(components)){
                        continue;
                    }
                    String name = cell.get(1);//对象名称
                    String description = cell.get(2);//描述
                    String yellowWarningThreshold = cell.get(3);//黄色警告阈值
                    String redWarningThreshold = cell.get(4);//红色警告阈值
                    String advice = cell.get(5);//处理建议
                    String referenceLog = cell.get(6);//参考日志
/*                    String referenceLogPath = cell.get(7);//参考日志路径
                    String ip = cell.get(8);//ip
                    String userName = cell.get(9);//用户名
                    String password = cell.get(10);//密码*/

                    HelpEntity helpEntity = new HelpEntity();
                    helpEntity.setComponent(components);
                    helpEntity.setName(name);
                    helpEntity.setDescription(description);
                    helpEntity.setYellowWarningThreshold(yellowWarningThreshold);
                    helpEntity.setRedWarningThreshold(redWarningThreshold);
                    helpEntity.setAdvice(advice);
                    helpEntity.setReferenceLog(referenceLog);

                    bydMapper.insertExcel(helpEntity);
                }
            }
            map.put(String.valueOf(i), list);
        }
        return map;
    }
}


