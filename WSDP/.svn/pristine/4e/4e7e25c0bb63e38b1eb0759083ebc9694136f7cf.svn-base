package com.szdeepwise.bydtcmonitor.dao;

import ch.qos.logback.core.db.dialect.DBUtil;
import com.szdeepwise.bydtcmonitor.util.JDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * @author xubowen
 * @description
 * @date 2020年 04月22日
 */
@Repository
public class NagiosDaoImpl implements NagiosDao {
    //Connection conn = JDBCUtil.getConnectionOfDB();

    public int getOkStatus(){
        String sql = "select 1 from dual";
        int i = resultInt(sql);
        System.out.println(i);
        return 1;
    }

    public int resultInt(String sql){/*
        int checkNum = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            rs = JDBCUtil.genDQL(pstmt,null);
            while (rs.next()) {
                checkNum = rs.getInt("1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeAll(rs, pstmt, conn);
        }
        return checkNum;*/
        return 1;
    }
}


