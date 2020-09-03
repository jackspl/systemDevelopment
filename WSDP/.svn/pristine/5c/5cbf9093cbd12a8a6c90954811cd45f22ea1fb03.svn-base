package com.szdeepwise.bydtcmonitor.util;
import com.szdeepwise.bydtcmonitor.entity.BYDEntity;
import com.szdeepwise.bydtcmonitor.entity.HelpEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xubowen
 * @description
 * @date 2020? 04?27?
 */
public class ReadExcel {
    public static Map<String,ArrayList<ArrayList<String>>> readExcel(String fileName, String path) {
        Map<String,ArrayList<ArrayList<String>>> map = new HashMap<String, ArrayList<ArrayList<String>>>();

        try {
            Workbook workBook = null;
            try {
                workBook = new XSSFWorkbook(path + "\\" + fileName);
            } catch (Exception ex) {
                workBook = new HSSFWorkbook(new FileInputStream(path + "\\"
                        + fileName));
            }

            for (int numSheet = 0; numSheet < workBook.getNumberOfSheets(); numSheet++) {
                ArrayList<ArrayList<String>> Row = new ArrayList<ArrayList<String>>();
                Sheet sheet = workBook.getSheetAt(numSheet);
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
    public static Map<String,List<String[]>> analysisExcel(String fileName, String path){
        System.out.println("------------------------");
        Map<String,ArrayList<ArrayList<String>>> sheet = readExcel(fileName, path);
        System.out.println("-------------------------------");
        Map<String,List<String[]>> map = new HashMap<String, List<String[]>>();
        for (int i=0;i<sheet.size();i++) {
            ArrayList<ArrayList<String>> row = sheet.get(String.valueOf(i));
            List<String[]> list = new ArrayList<String[]>();
            if(row.size()>0){
                for(int j=0;j<row.size();j++){
                    HelpEntity helpEntity = new HelpEntity();

                    ArrayList<String> cell = row.get(j);
                    String components = cell.get(0);//components
                    if(StringUtils.isEmpty(components)){
                        continue;
                    }
                    String name = cell.get(2);//name
                    String description = cell.get(3);//description
                    String advice = cell.get(4);//advice


                }
            }
            map.put(String.valueOf(i), list);
        }
        return map;
    }

    public static void main(String[] args) {
        Map<String,List<String[]>> list = analysisExcel("test.xlsx",
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
}



