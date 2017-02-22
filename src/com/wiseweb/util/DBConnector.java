package com.wiseweb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Chory on 2017/2/10 0010.
 */
public class DBConnector {

    public static final String url = "jdbc:mysql://10.1.8.189/basedb?useUnicode=true&characterEncoding=utf8&autoReconnect=true&rewriteBatchedStatements=TRUE";
    public static final String name = "com.mysql.jdbc.Driver";
    /*private static final String url="jdbc:hive://192.168.X.X:10000/default";
    private static final String name="org.apache.Hadoop.hive.jdbc.HiveDriver";*/
    private static final String user = "root";
    private static final String password = "123456";

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
