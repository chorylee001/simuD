package com.wiseweb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Chory on 2017/2/10 0010.
 */
public class DBConnector {

    public static final String url = "jdbc:mysql://127.0.0.1/simu";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "root";
    public static final String password = "123123";

    private Connection conn = null;
    public PreparedStatement ps = null;

    public DBConnector() {
    }

    /**
     * 根据SQL创建数据库连接对象
     * @param sql
     */
    public DBConnector(String sql) {
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            ps = conn.prepareStatement(sql);//准备执行语句
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConn(){
        try {
            //指定连接类型
            Class.forName(name);
            //获取连接
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 数据查询流关闭
     */
    public void close() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
