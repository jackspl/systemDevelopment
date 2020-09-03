package com.szdeepwise.bydtcmonitor.service;

import com.szdeepwise.bydtcmonitor.entity.HelpEntity;

import java.util.Map;

public interface ReadExcelService {
    //导入excel到数据库
    void operaExcel(String filePath);

    Map fuzzyQueryComponent(HelpEntity helpEntitys);

    Map fuzzyQueryName(HelpEntity helpEntitys);

    //动态菜单栏，查询
    String getHelpData(HelpEntity helpEntitys);

    //新增help信息
    int insertHelpData(HelpEntity helpEntitys);

    //删除
    int deleteHelpData(int id);

    //更新
    int updateHelpData(HelpEntity helpEntitys);

    //获取ip、username、password、referencelogpath等信息
    HelpEntity getHttpDownloadInfo(int id);

    //nagios请求 获取日志路径
    String queryLogByServiceName(String name);
}
