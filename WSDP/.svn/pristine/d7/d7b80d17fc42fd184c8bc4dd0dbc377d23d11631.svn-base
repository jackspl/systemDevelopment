package com.szdeepwise.bydtcmonitor.controller;

import com.jcraft.jsch.*;
import com.szdeepwise.bydtcmonitor.entity.HelpEntity;
import com.szdeepwise.bydtcmonitor.service.ReadExcelService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author xubowen
 * @description
 * @date 2020年 07月08日
 */
@CrossOrigin
@Controller
public class ReadExcelController {
    @Autowired
    private ReadExcelService readExcelService;


    @ResponseBody
    @RequestMapping("read/downloadFile")
    public int download(int id, HttpServletResponse response) {
        int flag = 0;
        HelpEntity helpEntity = readExcelService.getHttpDownloadInfo(id);
        String referenceLogPath = helpEntity.getReferenceLogPath();
        String filePath = "";
        String fileName = "";
        if (StringUtils.isNotEmpty(referenceLogPath)) {
            filePath = referenceLogPath.substring(0, referenceLogPath.lastIndexOf("/") + 1);
            fileName = referenceLogPath.substring(referenceLogPath.lastIndexOf("/") + 1);
        }

        System.out.println("filePath:" + filePath + "fileName" + fileName);
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(helpEntity.getUserName(), helpEntity.getIp(), 22);
            session.setPassword(helpEntity.getPassword()); // 设置密码
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");//在代码里需要跳过检测。否则会报错找不到主机
            session.setConfig(config); // 为Session对象设置properties
            int timeout = 30000;
            session.setTimeout(timeout); // 设置timeout时间
            session.connect(); // 通过Session建立与远程服务器的连接回话

            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            channelSftp.cd(filePath);
            InputStream inputStream = channelSftp.get(fileName);

            OutputStream out = response.getOutputStream();
            // 设置输出的格式
            response.reset();
            response.setContentType("bin");
            //下载
            response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            //在线打开  response.addHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");

            BufferedInputStream br = new BufferedInputStream(inputStream);

            //
            // 循环取出流中的数据
            byte[] b = new byte[1024];
            int len = 0;

            while ((len = br.read(b)) > 0)
                out.write(b, 0, len);
            inputStream.close();
            out.close();
            //
            channelSftp.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSchException e) {
            flag = 1;
            e.printStackTrace();
        } catch (SftpException e) {
            flag = 2;
            e.printStackTrace();
        }
        return flag;
    }

/*    @ResponseBody
    @RequestMapping("read/queryLogByServiceName")
    public String queryLogByServiceName(String serviceName) {
        System.out.println("############queryLogByServiceName:"+serviceName);
        String referenceLog = serviceName;
        return referenceLog;
    }*/

    @ResponseBody
    @RequestMapping("read/queryLogByServiceName")
    public String queryLogByServiceName(@RequestBody HelpEntity helpEntity) {
        String name = helpEntity.getName();
        System.out.println("############queryLogByServiceName:"+name);
        String referenceLog = readExcelService.queryLogByServiceName(name);
        Map resultMap = new HashMap();
        resultMap.put("referenceLog", referenceLog);
        JSONObject resultJson = JSONObject.fromObject(resultMap);
        String resultString = resultJson.toString();
        return resultString;
    }
}


