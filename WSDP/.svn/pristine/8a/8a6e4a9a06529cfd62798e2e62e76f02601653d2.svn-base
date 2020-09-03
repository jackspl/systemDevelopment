package com.szdeepwise.bydtcmonitor.util;

import com.szdeepwise.bydtcmonitor.entity.BYDEntity;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author xubowen
 * @description 计算Util
 * @date 2020年 04月29日
 */
public class CalculateUtil {

    //KPI排名，将同employee_name对象的KPI字段和KPICal相加+计算KPIjiafen+计算KPIjianfen
    public static List<BYDEntity> toBusinessNameHeavyAndScore(List<BYDEntity> list) {
        Collections.sort(list, new Comparator<BYDEntity>() {
            public int compare(BYDEntity o1, BYDEntity o2) {
                return o1.getEmployeeName().compareTo(o2.getEmployeeName());
            }
        });
        for (int ia = 0; ia < list.size(); ia++) {
            int count = 0; //计数器
            for (int j = ia + 1; j < list.size(); j++) {
                if (list.get(ia).getEmployeeName().equals(list.get(j).getEmployeeName())) {
                    count++;//n位和n+1位对比，如果相同，计数器应该+1
                } else { break;}//如果不同，则说明相同的对象已经对比完（因为开始就已经进行了排序）,则可以进行除重和重新计算，时间复杂度等于 }
            }

            //相同key的KPICal相加
            double kpiCal = list.get(ia).getKPICal();
            //相同key的KPI相加
            double kpi = list.get(ia).getKPI();
            //相同key的KPIjiafen相加
            double KPIjiafen = list.get(ia).getKPIjiafen();
            //相同key的KPIjianfen相加
            double KPIjianfen = list.get(ia).getKPIjianfen();

            for (int j = 0; j < count; j++) {//这个count就是重复值的数量？
                kpiCal += list.get(ia + 1).getKPICal();//计数器记录了后面有几个相同的,循环count 起始位置+count(j) + 1
                kpi += list.get(ia + 1).getKPI();//计数器记录了后面有几个相同的,循环count 起始位置+count(j) + 1
                KPIjiafen += list.get(ia + 1).getKPIjiafen();
                KPIjianfen += list.get(ia + 1).getKPIjianfen();
                list.remove(ia + 1); // List是动态数组，循环count，删除i+1即可
            }
            //KPI保留两位小数
            BigDecimal operationAvgTimeBg01 = new BigDecimal(kpiCal);
            list.get(ia).setKPICal(operationAvgTimeBg01.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg02 = new BigDecimal(kpi);
            list.get(ia).setKPI(operationAvgTimeBg02.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg03 = new BigDecimal(KPIjiafen);
            list.get(ia).setKPIjiafen(operationAvgTimeBg03.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg04 = new BigDecimal(KPIjianfen);
            list.get(ia).setKPIjianfen(operationAvgTimeBg04.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }

   /*     Collections.sort(list, new Comparator<BYDEntity>() {
            public int compare(BYDEntity o1, BYDEntity o2) {
                if (o1.getWorkingHours() < o2.getWorkingHours()) {
                    return 1;
                }
                if (o1.getWorkingHours() == o2.getWorkingHours()) {
                    return 0;
                }
                return -1;
            }
        });*/
/*        long b = System.currentTimeMillis();
        System.out.println("@去重加分消耗时间："+(b-a));*/
        return list;
    }

    //KPI折线图，将同zheXianX字段的kpi字段相加
    public static List<BYDEntity> toBusinessKPI(List<BYDEntity> list) {
        //long a = System.currentTimeMillis();
        Collections.sort(list, new Comparator<BYDEntity>() {
            public int compare(BYDEntity o1, BYDEntity o2) {
                return o1.getZheXianX().compareTo(o2.getZheXianX());
            }
        });
        for (int ia = 0; ia < list.size(); ia++) {
            int count = 0; //计数器
            for (int j = ia + 1; j < list.size(); j++) {
                if (list.get(ia).getZheXianX().equals(list.get(j).getZheXianX())) {
                    count++;//n位和n+1位对比，如果相同，计数器应该+1
                } else { break;}//如果不同，则说明相同的对象已经对比完（因为开始就已经进行了排序）,则可以进行除重和重新计算，时间复杂度等于 }
            }
            //相同key的KPI相加
            double kpi = list.get(ia).getKPI();
            //相同key的KPI相加
            double kpiJIAFEN = list.get(ia).getKPIjiafen();
            //相同key的KPI相加
            double kpiJIANFEN = list.get(ia).getKPIjianfen();

            for (int j = 0; j < count; j++) {
                kpi += list.get(ia + 1).getKPI();
                kpiJIAFEN += list.get(ia + 1).getKPIjiafen();
                kpiJIANFEN += list.get(ia + 1).getKPIjianfen();
                list.remove(ia + 1);
            }
            //KPI保留两位小数
            BigDecimal operationAvgTimeBg01 = new BigDecimal(kpi);
            list.get(ia).setKPI(operationAvgTimeBg01.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            //kpiJIAFEN保留两位小数
            BigDecimal operationAvgTimeBg02 = new BigDecimal(kpiJIAFEN);
            list.get(ia).setKPIjianfen(operationAvgTimeBg02.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            //kpiJIANFEN保留两位小数
            BigDecimal operationAvgTimeBg03 = new BigDecimal(kpiJIANFEN);
            list.get(ia).setKPIjianfen(operationAvgTimeBg03.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        return list;
    }

    //KPI表格，计算相同employeeName+emplyeeGroup+Company的总KPI
    public static List<BYDEntity> calListSameKPI(List<BYDEntity> list) {
        Collections.sort(list, new Comparator<BYDEntity>() {
            public int compare(BYDEntity o1, BYDEntity o2) {
                return o1.getRecentInfo().compareTo(o2.getRecentInfo());
            }
        });
        for (int ia = 0; ia < list.size(); ia++) {
            int count = 0; //计数器
            for (int j = ia + 1; j < list.size(); j++) {
                if (list.get(ia).getRecentInfo().equals(list.get(j).getRecentInfo())) {
                    //if (list.get(ia).getEmployeeName().equals(list.get(j).getEmployeeName())) {
                    count++;//n位和n+1位对比，如果相同，计数器应该+1
                } else { break;}//如果不同，则说明相同的对象已经对比完（因为开始就已经进行了排序）,则可以进行除重和重新计算，时间复杂度等于 }
            }

            //相同key的KPI相加
            double kpi = list.get(ia).getKPI();
            //相同key的workingHours相加
            double workingHours = list.get(ia).getWorkingHours();

            for (int j = 0; j < count; j++) {//这个count就是重复值的数量？
                kpi += list.get(ia + 1).getKPI();//计数器记录了后面有几个相同的,循环count 起始位置+count(j) + 1
                workingHours += list.get(ia + 1).getWorkingHours();
                list.remove(ia + 1); // List是动态数组，循环count，删除i+1即可
            }
            //KPI保留两位小数
            BigDecimal operationAvgTimeBg01 = new BigDecimal(kpi);
            list.get(ia).setKPI(operationAvgTimeBg01.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg02 = new BigDecimal(workingHours);
            list.get(ia).setWorkingHours(operationAvgTimeBg02.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        return list;
    }

    //将同employee_group对象的KPI字段和KPICal相加+计算KPIjiafen+计算KPIjianfen
    public static List<BYDEntity> calEmployeeGroupKPI(List<BYDEntity> list) {
        Collections.sort(list, new Comparator<BYDEntity>() {
            public int compare(BYDEntity o1, BYDEntity o2) {
                return o1.getEmployeeGroup().compareTo(o2.getEmployeeGroup());
            }
        });
        for (int ia = 0; ia < list.size(); ia++) {
            int count = 0; //计数器
            for (int j = ia + 1; j < list.size(); j++) {
                if (list.get(ia).getEmployeeGroup().equals(list.get(j).getEmployeeGroup())) {
                    count++;//n位和n+1位对比，如果相同，计数器应该+1
                } else { break;}//如果不同，则说明相同的对象已经对比完（因为开始就已经进行了排序）,则可以进行除重和重新计算，时间复杂度等于 }
            }

            //相同key的KPICal相加
            double kpiCal = list.get(ia).getKPICal();
            //相同key的KPI相加
            double kpi = list.get(ia).getKPI();
            //相同key的KPIjiafen相加
            double KPIjiafen = list.get(ia).getKPIjiafen();
            //相同key的KPIjianfen相加
            double KPIjianfen = list.get(ia).getKPIjianfen();

            for (int j = 0; j < count; j++) {//这个count就是重复值的数量？
                kpiCal += list.get(ia + 1).getKPICal();//计数器记录了后面有几个相同的,循环count 起始位置+count(j) + 1
                kpi += list.get(ia + 1).getKPI();//计数器记录了后面有几个相同的,循环count 起始位置+count(j) + 1
                KPIjiafen += list.get(ia + 1).getKPIjiafen();
                KPIjianfen += list.get(ia + 1).getKPIjianfen();
                list.remove(ia + 1); // List是动态数组，循环count，删除i+1即可
            }
            //KPI保留两位小数
            BigDecimal operationAvgTimeBg01 = new BigDecimal(kpiCal);
            list.get(ia).setKPICal(operationAvgTimeBg01.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg02 = new BigDecimal(kpi);
            list.get(ia).setKPI(operationAvgTimeBg02.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg03 = new BigDecimal(KPIjiafen);
            list.get(ia).setKPIjiafen(operationAvgTimeBg03.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg04 = new BigDecimal(KPIjianfen);
            list.get(ia).setKPIjianfen(operationAvgTimeBg04.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        return list;
    }

    //将同company对象的KPI字段和KPICal相加+计算KPIjiafen+计算KPIjianfen
    public static List<BYDEntity> calEmployeeCompanyKPI(List<BYDEntity> list) {
        Collections.sort(list, new Comparator<BYDEntity>() {
            public int compare(BYDEntity o1, BYDEntity o2) {
                return o1.getCompany().compareTo(o2.getCompany());
            }
        });
        for (int ia = 0; ia < list.size(); ia++) {
            int count = 0; //计数器
            for (int j = ia + 1; j < list.size(); j++) {
                if (list.get(ia).getCompany().equals(list.get(j).getCompany())) {
                    count++;//n位和n+1位对比，如果相同，计数器应该+1
                } else { break;}//如果不同，则说明相同的对象已经对比完（因为开始就已经进行了排序）,则可以进行除重和重新计算，时间复杂度等于 }
            }

            //相同key的KPICal相加
            double kpiCal = list.get(ia).getKPICal();
            //相同key的KPI相加
            double kpi = list.get(ia).getKPI();
            //相同key的KPIjiafen相加
            double KPIjiafen = list.get(ia).getKPIjiafen();
            //相同key的KPIjianfen相加
            double KPIjianfen = list.get(ia).getKPIjianfen();

            for (int j = 0; j < count; j++) {//这个count就是重复值的数量？
                kpiCal += list.get(ia + 1).getKPICal();//计数器记录了后面有几个相同的,循环count 起始位置+count(j) + 1
                kpi += list.get(ia + 1).getKPI();//计数器记录了后面有几个相同的,循环count 起始位置+count(j) + 1
                KPIjiafen += list.get(ia + 1).getKPIjiafen();
                KPIjianfen += list.get(ia + 1).getKPIjianfen();
                list.remove(ia + 1); // List是动态数组，循环count，删除i+1即可
            }
            //KPI保留两位小数
            BigDecimal operationAvgTimeBg01 = new BigDecimal(kpiCal);
            list.get(ia).setKPICal(operationAvgTimeBg01.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg02 = new BigDecimal(kpi);
            list.get(ia).setKPI(operationAvgTimeBg02.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg03 = new BigDecimal(KPIjiafen);
            list.get(ia).setKPIjiafen(operationAvgTimeBg03.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

            //KPI保留两位小数
            BigDecimal operationAvgTimeBg04 = new BigDecimal(KPIjianfen);
            list.get(ia).setKPIjianfen(operationAvgTimeBg04.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        return list;
    }

    //KPI排名计算环比
    //bcount：本期数量，scount：上期数量
   public static String calLinkRatio(double bcount,double scount){
       BigDecimal tests = BigDecimal.valueOf((bcount - scount ) * 100).divide(BigDecimal.valueOf(scount ), 2, BigDecimal.ROUND_HALF_DOWN);
       String s = tests+"%";
       return s;
   }

   //计算客户端频次环比
   public static String calClientLinkRatio(int bcount,int scount){
       BigDecimal tests = BigDecimal.valueOf((bcount - scount ) * 100).divide(BigDecimal.valueOf(scount ), 2, BigDecimal.ROUND_HALF_DOWN);
       String s = tests+"%";
       return s;
   }

   //信息安全模块饼状图，同employeeName计算operaCount
   public static List<BYDEntity> calEmployeeNameOperaCount(List<BYDEntity> list) {
       Collections.sort(list, new Comparator<BYDEntity>() {
           public int compare(BYDEntity o1, BYDEntity o2) {
               return o1.getEmployeeName().compareTo(o2.getEmployeeName());
           }
       });
       for (int ia = 0; ia < list.size(); ia++) {
           int count = 0; //计数器
           for (int j = ia + 1; j < list.size(); j++) {
               if (list.get(ia).getEmployeeName().equals(list.get(j).getEmployeeName())) {
                   count++;//n位和n+1位对比，如果相同，计数器应该+1
               } else { break;}//如果不同，则说明相同的对象已经对比完（因为开始就已经进行了排序）,则可以进行除重和重新计算，时间复杂度等于 }
           }

           //相同key的OperaCount相加
           int operaCount = list.get(ia).getOperaCount();

           for (int j = 0; j < count; j++) {//这个count就是重复值的数量？
               operaCount += list.get(ia + 1).getOperaCount();//计数器记录了后面有几个相同的,循环count 起始位置+count(j) + 1
               list.remove(ia + 1); // List是动态数组，循环count，删除i+1即可
           }
           list.get(ia).setOperaCount(operaCount);
       }
       return list;
   }

    //信息安全模块饼状图，同employeeGroup计算operaCount
    public static List<BYDEntity> calEmployeeGroupOperaCount(List<BYDEntity> list) {
        Collections.sort(list, new Comparator<BYDEntity>() {
            public int compare(BYDEntity o1, BYDEntity o2) {
                return o1.getEmployeeGroup().compareTo(o2.getEmployeeGroup());
            }
        });
        for (int ia = 0; ia < list.size(); ia++) {
            int count = 0; //计数器
            for (int j = ia + 1; j < list.size(); j++) {
                if (list.get(ia).getEmployeeGroup().equals(list.get(j).getEmployeeGroup())) {
                    count++;//n位和n+1位对比，如果相同，计数器应该+1
                } else { break;}//如果不同，则说明相同的对象已经对比完（因为开始就已经进行了排序）,则可以进行除重和重新计算，时间复杂度等于 }
            }

            //相同key的OperaCount相加
            int operaCount = list.get(ia).getOperaCount();

            for (int j = 0; j < count; j++) {//这个count就是重复值的数量？
                operaCount += list.get(ia + 1).getOperaCount();//计数器记录了后面有几个相同的,循环count 起始位置+count(j) + 1
                list.remove(ia + 1); // List是动态数组，循环count，删除i+1即可
            }
            list.get(ia).setOperaCount(operaCount);
        }
        return list;
    }

    //信息安全模块饼状图，同company计算operaCount
    public static List<BYDEntity> calCompanyOperaCount(List<BYDEntity> list) {
        Collections.sort(list, new Comparator<BYDEntity>() {
            public int compare(BYDEntity o1, BYDEntity o2) {
                return o1.getCompany().compareTo(o2.getCompany());
            }
        });
        for (int ia = 0; ia < list.size(); ia++) {
            int count = 0; //计数器
            for (int j = ia + 1; j < list.size(); j++) {
                if (list.get(ia).getCompany().equals(list.get(j).getCompany())) {
                    count++;//n位和n+1位对比，如果相同，计数器应该+1
                } else { break;}//如果不同，则说明相同的对象已经对比完（因为开始就已经进行了排序）,则可以进行除重和重新计算，时间复杂度等于 }
            }

            //相同key的OperaCount相加
            int operaCount = list.get(ia).getOperaCount();

            for (int j = 0; j < count; j++) {//这个count就是重复值的数量？
                operaCount += list.get(ia + 1).getOperaCount();//计数器记录了后面有几个相同的,循环count 起始位置+count(j) + 1
                list.remove(ia + 1); // List是动态数组，循环count，删除i+1即可
            }
            list.get(ia).setOperaCount(operaCount);
        }
        return list;
    }

    //信息安全模块饼状图，同异常情况计算operaCount
    public static List<BYDEntity> calAbnormalOperaCount(List<BYDEntity> list) {
        Collections.sort(list, new Comparator<BYDEntity>() {
            public int compare(BYDEntity o1, BYDEntity o2) {
                return o1.getRecentInfo().compareTo(o2.getRecentInfo());
            }
        });
        for (int ia = 0; ia < list.size(); ia++) {
            int count = 0; //计数器
            for (int j = ia + 1; j < list.size(); j++) {
                if (list.get(ia).getRecentInfo().equals(list.get(j).getRecentInfo())) {
                    count++;//n位和n+1位对比，如果相同，计数器应该+1
                } else { break;}//如果不同，则说明相同的对象已经对比完（因为开始就已经进行了排序）,则可以进行除重和重新计算，时间复杂度等于 }
            }

            //相同key的OperaCount相加
            int operaCount = list.get(ia).getOperaCount();

            for (int j = 0; j < count; j++) {//这个count就是重复值的数量？
                operaCount += list.get(ia + 1).getOperaCount();//计数器记录了后面有几个相同的,循环count 起始位置+count(j) + 1
                list.remove(ia + 1); // List是动态数组，循环count，删除i+1即可
            }
            list.get(ia).setOperaCount(operaCount);
        }
        return list;
    }

    //仪表盘，将相同employeeGroup的次数相加
    public static List<BYDEntity> calGroupCounts(List<BYDEntity> list) {
        //long a = System.currentTimeMillis();
        Collections.sort(list, new Comparator<BYDEntity>() {
            public int compare(BYDEntity o1, BYDEntity o2) {
                return o1.getEmployeeGroup().compareTo(o2.getEmployeeGroup());
            }
        });
        for (int ia = 0; ia < list.size(); ia++) {
            int count = 0; //计数器
            for (int j = ia + 1; j < list.size(); j++) {
                if (list.get(ia).getEmployeeGroup().equals(list.get(j).getEmployeeGroup())) {
                    count++;//n位和n+1位对比，如果相同，计数器应该+1
                } else { break;}//如果不同，则说明相同的对象已经对比完（因为开始就已经进行了排序）,则可以进行除重和重新计算，时间复杂度等于 }
            }
            //相同key的KPI相加
            int operaCount = list.get(ia).getOperaCount();

            for (int j = 0; j < count; j++) {
                operaCount += list.get(ia + 1).getOperaCount();
                list.remove(ia + 1);
            }
            list.get(ia).setOperaCount(operaCount);
        }
        return list;
    }

    //将同zheXianX的zheXianY相加
    public static List<BYDEntity> calZheXianX(List<BYDEntity> list) {
        //long a = System.currentTimeMillis();
        Collections.sort(list, new Comparator<BYDEntity>() {
            public int compare(BYDEntity o1, BYDEntity o2) {
                return o1.getZheXianX().compareTo(o2.getZheXianX());
            }
        });
        for (int ia = 0; ia < list.size(); ia++) {
            int count = 0; //计数器
            for (int j = ia + 1; j < list.size(); j++) {
                if (list.get(ia).getZheXianX().equals(list.get(j).getZheXianX())) {
                    count++;//n位和n+1位对比，如果相同，计数器应该+1
                } else { break;}//如果不同，则说明相同的对象已经对比完（因为开始就已经进行了排序）,则可以进行除重和重新计算，时间复杂度等于 }
            }
            int zheXianY = list.get(ia).getZheXianY();
            for (int j = 0; j < count; j++) {
                zheXianY += list.get(ia + 1).getZheXianY();
                list.remove(ia + 1);
            }
            list.get(ia).setZheXianY(zheXianY);
        }
        return list;
    }
}


