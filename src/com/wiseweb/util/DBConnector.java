package com.wiseweb.util;

import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Chory on 2017/2/10 0010.
 */
public class DBConnector {

    public static final String url = "jdbc:mysql://127.0.0.1/cr?useUnicode=true&characterEncoding=utf8&autoReconnect=true&rewriteBatchedStatements=TRUE";//10.1.8.189
    public static final String name = "com.mysql.jdbc.Driver";
    /*private static final String url="jdbc:hive://192.168.X.X:10000/default";
    private static final String name="org.apache.Hadoop.hive.jdbc.HiveDriver";*/
    private static final String user = "root";
    private static final String password = "123123";

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

    /**
     * 本机默认连接
     * @return
     */
    public Connection getConn(){
        return getConn(name,url,user,password);
    }

    /**
     * 创建数据库连接
     * @param driver 驱动名称
     * @param url 数据库所在服务器
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public Connection getConn(String driver,String url,String username,String password){
        try {
            //指定连接类型
            Class.forName(driver);
            //获取连接
            conn = DriverManager.getConnection(url, username, password);
        } catch(MySQLNonTransientConnectionException tce){
            System.out.println("尝试着连接了3次，没有成功，放弃吧！");
        }catch (ClassNotFoundException e) {
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
