package com.szdeepwise.bydtcmonitor.util;

import com.google.gson.Gson;
import com.jcraft.jsch.UserInfo;
import com.szdeepwise.bydtcmonitor.entity.BYDEntity;
import com.szdeepwise.bydtcmonitor.entity.HelpEntity;
import com.szdeepwise.bydtcmonitor.entity.RequestAndResponseEntity;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.ss.formula.functions.T;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DemoTest {
    public static void main(String[] args){
        List<RequestAndResponseEntity> resultList = new ArrayList<>();
        for (RequestAndResponseEntity entity:
                resultList) {
            System.out.println(1);
        }
    }/*{
        double diliverNum=1.11;//举例子的变量
        int queryMailNum=2;//举例子的变量
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((float)diliverNum/(float)queryMailNum);
    }*/
    /*{
        ArrayList<BYDEntity> list = new ArrayList<>();
        BYDEntity bydEntity1 = new BYDEntity();
        bydEntity1.setZheXianX("3");
        bydEntity1.setZheXianY(2);
        BYDEntity bydEntity2= new BYDEntity();
        bydEntity2.setZheXianX("2");
        bydEntity2.setZheXianY(1);
        BYDEntity bydEntity3= new BYDEntity();
        bydEntity3.setZheXianX("4");
        bydEntity3.setZheXianY(4);
        BYDEntity bydEntity4= new BYDEntity();
        bydEntity4.setZheXianX("1");
        bydEntity4.setZheXianY(3);
        list.add(bydEntity1);
        list.add(bydEntity2);
        list.add(bydEntity3);
        list.add(bydEntity4);


        Collections.sort(list, new Comparator<BYDEntity>() {
            @Override
            public int compare(BYDEntity o1, BYDEntity o2) {
                //升序
                return Integer.valueOf(o1.getZheXianX()).compareTo(Integer.valueOf(o2.getZheXianX()));
            }
        });

        for (BYDEntity entity:list) {
            System.out.println(entity.getZheXianY());
        }
    }*/

}



