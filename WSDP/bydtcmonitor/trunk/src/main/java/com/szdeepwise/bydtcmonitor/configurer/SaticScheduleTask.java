package com.szdeepwise.bydtcmonitor.configurer;

import com.szdeepwise.bydtcmonitor.entity.BYDEntity;
import com.szdeepwise.bydtcmonitor.entity.JDBCRequestEntity;
import com.szdeepwise.bydtcmonitor.service.BYDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xubowen
 * @description 执行静态定时任务
 * @date 2020年 01月03日
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {

    @Autowired
    private BYDService bydService;

    //3.添加定时任务，每小时执行一次
    //@Scheduled(cron = "0 0 0/1 * * ?")
    //@Scheduled(fixedRate = 60000)
    //每30分钟时执行一次
    @Scheduled(cron = "0 0/30 * * * ?")
    private void getOnlineToTcOnlineInfo() {
        List<JDBCRequestEntity> tcLinkInfoList = bydService.queryTcLinkInfo();
        if (tcLinkInfoList.size() > 0) {
            JDBCRequestEntity jdbcRequestEntity = tcLinkInfoList.get(0);
            bydService.getOnlineToTcOnlineInfo(jdbcRequestEntity);
            System.err.println("=========执行静态定时任务getOnlineToTcOnlineInfo的时间: " + LocalDateTime.now());
        }else{
            System.out.println("=========执行静态定时任务error");
        }
    }
}


