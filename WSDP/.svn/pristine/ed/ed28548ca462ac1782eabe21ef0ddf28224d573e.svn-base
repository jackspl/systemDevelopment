package com.szdeepwise.bydtcmonitor.util;

import ch.qos.logback.core.db.dialect.DBUtil;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author xubowen
 * @description
 * @date 2019年 12月23日
 */
public class JDBCUtil {
    static String driver;
    static String url;
    static String user;
    static String password;

    // 创建静态容器
    static Properties dbs = new Properties();

    // 驱动只用加载一次，所以使用静态代码块
    static {
        // 获取DBConfig.properties文件中数据库的参数
        ClassLoader classLoader = JDBCUtil.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("NagiosConfig.properties");
        //InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("NagiosConfig.properties");

        try {
            // 将is添加到容器中
            dbs.load(is);

            driver = dbs.getProperty("driver");
            url = dbs.getProperty("url");
            user = dbs.getProperty("user");
            password = dbs.getProperty("password");

            Class.forName(driver);
            System.out.println("加载数据库驱动成功");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("加载数据库驱动失败");
        }

    }

    // 获取连接
    public static Connection getConnectionOfDB() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println(conn);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return conn;

    }

    // 通用DQL语句
    public static ResultSet genDQL(PreparedStatement pstmt, Object... objs) {
        ResultSet rs = null;
        try {
            if (objs != null && objs.length > 0) {
                for (int i = 0; i < objs.length; i++) {

                    pstmt.setObject(i + 1, objs[i]);

                }
                rs = pstmt.executeQuery();
            } else {
                rs = pstmt.executeQuery();
            }
        } catch (SQLException e) {

            e.printStackTrace();

        }

        return rs;

    }

    public static void closeAll(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}


