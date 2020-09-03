package com.szdeepwise.bydtcmonitor.util;

import java.math.BigDecimal;

/**
 * @author xubowen
 * @description
 * @date 2020年 05月20日
 */
public class RtocProcessEntity {
    private String wellId;
    private String dailyDate;
    private double endDepth;

    public String getWellId() {
        return wellId;
    }

    public void setWellId(String wellId) {
        this.wellId = wellId;
    }

    public String getDailyDate() {
        return dailyDate;
    }

    public void setDailyDate(String dailyDate) {
        this.dailyDate = dailyDate;
    }

    public double getEndDepth() {
        return endDepth;
    }

    public void setEndDepth(double endDepth) {
        this.endDepth = endDepth;
    }

    @Override
    public String toString() {
        return "RtocProcessEntity{" +
                "wellId='" + wellId + '\'' +
                ", dailyDate='" + dailyDate + '\'' +
                ", endDepth=" + endDepth +
                '}';
    }
}


