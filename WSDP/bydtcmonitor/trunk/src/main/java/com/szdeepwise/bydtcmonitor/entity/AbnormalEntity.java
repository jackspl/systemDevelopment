package com.szdeepwise.bydtcmonitor.entity;

/**
 * @author xubowen
 * @description
 * @date 2020年 06月30日
 */
public class AbnormalEntity {
    private int abnormalLogin;
    private int abnormalView;
    private int abnormalDownload;
    private int abnormalDelete;

    public int getAbnormalLogin() {
        return abnormalLogin;
    }

    public void setAbnormalLogin(int abnormalLogin) {
        this.abnormalLogin = abnormalLogin;
    }

    public int getAbnormalView() {
        return abnormalView;
    }

    public void setAbnormalView(int abnormalView) {
        this.abnormalView = abnormalView;
    }

    public int getAbnormalDownload() {
        return abnormalDownload;
    }

    public void setAbnormalDownload(int abnormalDownload) {
        this.abnormalDownload = abnormalDownload;
    }

    public int getAbnormalDelete() {
        return abnormalDelete;
    }

    public void setAbnormalDelete(int abnormalDelete) {
        this.abnormalDelete = abnormalDelete;
    }

    @Override
    public String toString() {
        return "AbnormalEntity{" +
                "abnormalLogin=" + abnormalLogin +
                ", abnormalView=" + abnormalView +
                ", abnormalDownload=" + abnormalDownload +
                ", abnormalDelete=" + abnormalDelete +
                '}';
    }
}


