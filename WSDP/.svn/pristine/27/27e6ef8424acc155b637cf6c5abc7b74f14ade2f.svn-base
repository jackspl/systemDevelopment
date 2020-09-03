package com.szdeepwise.bydtcmonitor.util;

import com.szdeepwise.bydtcmonitor.entity.BYDEntity;
import com.szdeepwise.bydtcmonitor.entity.DateEntity;
import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    //计算场景操作时间
    public static int computeOperationTime(Date actionBeginTime, Date actionEndTime) {
        int operationTime = 0;
        if (null != actionBeginTime && null != actionBeginTime) {
            long startTime = actionBeginTime.getTime();
            long endTime = actionEndTime.getTime();
            try {
                operationTime = (int) (endTime - startTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return operationTime;
    }

    //计算传入日期当前星期对应的周日
    public static String calculateSundayDate(String date) {
        String sundayDate = "";
        if (StringUtils.isNotEmpty(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
            Calendar cal = Calendar.getInstance();
            Date time = null;
            try {
                time = sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cal.setTime(time);
            //System.out.println("要计算日期为:"+sdf.format(cal.getTime())); //输出要计算日期

            //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
            int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
            if (1 == dayWeek) {
                cal.add(Calendar.DAY_OF_MONTH, -1);
            }

            cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一

            int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
            cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
            //System.out.println("所在周星期一的日期："+sdf.format(cal.getTime()));
            //System.out.println(cal.getFirstDayOfWeek()+"-"+day+"+6="+(cal.getFirstDayOfWeek()-day+6));

            cal.add(Calendar.DATE, 6);
            sundayDate = sdf.format(cal.getTime());

            //System.out.println("所在周星期日的日期："+sundayDate);
        }
        return sundayDate;

    }

    //计算传入日期当月的天数
    public static int getDaysOfMonth(String date) {
        int days = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = sdf.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parse);
            days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    //获取传入日期当月的第一天
    public static String getFirstMonthDay(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String firstMonthDay = "";
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.parse(date));
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            firstMonthDay = dateFormat.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return firstMonthDay;
    }

    //获取传入日期当月的最后一天
    public static String getLastMonthDay(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String lastMonthDay = "";
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat.parse(date));
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            lastMonthDay = dateFormat.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lastMonthDay;
    }

    //计算两个日期之间所有日期的集合
    public static List<BYDEntity> getDays(String startTime, String endTime,String compamy,String employeeGroup,String employeeName) {
        List<BYDEntity> dateEntityList = new ArrayList<>();
        // 返回的日期集合
        List<String> days = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if(StringUtils.isNotEmpty(startTime) || StringUtils.isNotEmpty(endTime)){
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                BYDEntity dateEntity = new BYDEntity();
                dateEntity.setZheXianX(dateFormat.format(tempStart.getTime()));
                //
                dateEntity.setCompany(compamy);
                dateEntity.setEmployeeGroup(employeeGroup);
                dateEntity.setEmployeeName(employeeName);

                dateEntityList.add(dateEntity);
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        }
        return dateEntityList;
    }

    /*KPI排名界面*/
    //计算传入两个日期之间的天数（负数）
    public static int calDays(String startTime,String endTime){
    SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
    Date date1=null;
    Date date = null;
    Long l = 0L;
        try {
        date = formatter.parse(startTime);
        long ts = date.getTime();
        date1 =  formatter.parse(endTime);
        long ts1 = date1.getTime();

        l = (ts - ts1) / (1000 * 60 * 60 * 24);

    } catch (ParseException e) {
        e.printStackTrace();
    }
        return l.intValue();
    }

    //计算传入日期前一段时间的日期
    public static String calSomeDate(String actionBeginTime,int i){
        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = null;
        try {
            startDate = sdf.parse(actionBeginTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(calendar.DATE, i);
        Date updateDate4 = calendar.getTime();
        String format = sdf.format(updateDate4);
        return format;
    }
}
