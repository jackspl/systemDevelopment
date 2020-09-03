package com.szdeepwise.bydtcmonitor.dao;

import com.szdeepwise.bydtcmonitor.entity.JDBCRequestEntity;
import com.szdeepwise.bydtcmonitor.entity.JDBCResponseEntity;
import com.szdeepwise.bydtcmonitor.util.JDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xubowen
 * @description
 * @date 2019年 12月23日
 */
@Repository
public class TCDaoImpl implements TCDao {
    //系统健康检查
    @Override
    public int systemHealthCheck(JDBCRequestEntity jdbcRequestEntity) {
        String url = "";
        String driver = "";
        String userName = jdbcRequestEntity.getUserName();
        String passWord = jdbcRequestEntity.getPassWord();
        String selectDB = jdbcRequestEntity.getSelectDB();
        if ("Mysql".equals(selectDB)) {
            url = "jdbc:mysql://" + jdbcRequestEntity.getUrl() + ":" + jdbcRequestEntity.getPort() + "/" + jdbcRequestEntity.getServiceName();
            driver = "com.mysql.cj.jdbc.Driver";
        } else if ("Oracle".equals(selectDB)) {
            url = "jdbc:oracle:thin:@" + jdbcRequestEntity.getUrl() + ":" + jdbcRequestEntity.getPort() + "/" + jdbcRequestEntity.getServiceName();
            driver = "oracle.jdbc.driver.OracleDriver";
        }

        String sql = "select 1 from dual";
        int checkNum = 0;

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, passWord);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                checkNum = rs.getInt("1");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            checkNum=0;
        } catch (Exception e) {
            e.printStackTrace();
            checkNum=0;
        } finally {
            JDBCUtil.closeAll(rs, stmt, conn);
        }
        return checkNum;
    }

    //获取在线人数
    @Override
    public int GetPeopleOnline(JDBCRequestEntity jdbcRequestEntity) {
        String url = "";
        String driver = "";
        String userName = jdbcRequestEntity.getUserName();
        String passWord = jdbcRequestEntity.getPassWord();
        String selectDB = jdbcRequestEntity.getSelectDB();
        if ("Mysql".equals(selectDB)) {
            url = "jdbc:mysql://" + jdbcRequestEntity.getUrl() + ":" + jdbcRequestEntity.getPort() + "/" + jdbcRequestEntity.getServiceName();
            driver = "com.mysql.cj.jdbc.Driver";
        } else if ("Oracle".equals(selectDB)) {
            url = "jdbc:oracle:thin:@" + jdbcRequestEntity.getUrl() + ":" + jdbcRequestEntity.getPort() + "/" + jdbcRequestEntity.getServiceName();
            driver = "oracle.jdbc.driver.OracleDriver";
        }

        //String sql = "select count(*) total from scene_list";
        String sql = "select count(*) total from ppom_session order by plogin_date desc";
        int total = 0;

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, passWord);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(rs, stmt, conn);
        }
        return total;
    }

    //获取tc在线用户的相关信息
    @Override
    public List<JDBCResponseEntity> getTCOnlineInfo(JDBCRequestEntity jdbcRequestEntity) {
        String url = "";
        String driver = "";
        String userName = jdbcRequestEntity.getUserName();
        String passWord = jdbcRequestEntity.getPassWord();
        String selectDB = jdbcRequestEntity.getSelectDB();
        if ("Mysql".equals(selectDB)) {
            url = "jdbc:mysql://" + jdbcRequestEntity.getUrl() + ":" + jdbcRequestEntity.getPort() + "/" + jdbcRequestEntity.getServiceName();
            driver = "com.mysql.cj.jdbc.Driver";
        } else if ("Oracle".equals(selectDB)) {
            url = "jdbc:oracle:thin:@" + jdbcRequestEntity.getUrl() + ":" + jdbcRequestEntity.getPort() + "/" + jdbcRequestEntity.getServiceName();
            driver = "oracle.jdbc.driver.OracleDriver";
        }

        List<JDBCResponseEntity> resultList = new ArrayList<JDBCResponseEntity>();

        String sql = "select pUid,puser_name pUserName,pnode_name pNodeName," +
                "to_char(plogin_date,'yyyy-MM-dd HH24:mm:ss') pLoginDate from ppom_session";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, passWord);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                JDBCResponseEntity jdbcEntity = new JDBCResponseEntity();
                jdbcEntity.setpUid(rs.getString("pUid"));
                jdbcEntity.setpUserName(rs.getString("pUserName"));
                jdbcEntity.setpNodeName(rs.getString("pNodeName"));
                jdbcEntity.setpLoginDate(rs.getString("pLoginDate"));
                resultList.add(jdbcEntity);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(rs, stmt, conn);
        }
        return resultList;
    }
}


