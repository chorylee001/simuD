package com.wiseweb.action;

import com.wiseweb.manager.QuartzManager;
import com.wiseweb.util.DBConnector;
import com.wiseweb.util.UserInfoGenerator;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HTablePool;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by Chory on 2017/2/15 0015.
 */
public class Main {

    private static final String path = "/home/wiseweb/conf/base_data.properties";//"D:/etc/conf/base_data.properties";//

    private static DBConnector connector = null;
    private static Connection conn;
    private static HTablePool tp;
    private static Integer oc;
    private static Configuration config = null;

    public static void main(String[] args) {

        /*Properties properties = new Properties();
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(path));
            properties.load(is);
            init(properties);
            //获取连接
            if (conn == null) {
                System.out.println("数据库连接失败,请检查连接后重试!");
                System.exit(0);
            }

            *//*DeviceDataInsertor deviceDataInsertor = new DeviceDataInsertor();
            deviceDataInsertor.run(conn, 610000);

            GenerateUserInfo.run(conn, oc);

            //注册用户生成
            RegisterUserInsertor registerUser = new RegisterUserInsertor();
            int regCount = registerUser.run(conn,373762);*//*
            //相关联系人生成
            UserContactInsertor userContactInsertor = new UserContactInsertor();
            userContactInsertor.run(conn);

            设备使用数据生成
            UseDeviceUserInsertor useDeviceUserInsetor = new UseDeviceUserInsertor();
            useDeviceUserInsetor.run(conn,regCount);

            //订单数据生成
            OrderInsertor orderInsertor = new OrderInsertor();
            orderInsertor.run(conn,oc);*//*
            conn.close();
            System.gc();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        Properties properties = new Properties();
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(path));
            properties.load(is);
            String time = properties.getProperty("job_order_time");
            orderJobRun(time);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void orderJobRun(String time){
        OrderJob job = new OrderJob();
        String job_name ="huaxiang_job";
        try {
            System.out.println("订单数据开始生成...");
            QuartzManager.addJob(job_name,job,time);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 读取配置文件并打开数据库连接
     */
    private static void init(Properties properties) {

        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String pwd = properties.getProperty("jdbc.password");
        String driverClassName = properties.getProperty("jdbc.driverClassName");
        String poCount = properties.getProperty("population_user_count");

        /*String zip = properties.getProperty("zookeeper.ip");
        String zport = properties.getProperty("zookeeper.port");
        String zcport = properties.getProperty("zookeeper.clientPort");
        String poCount = properties.getProperty("population_user_count");

        config = HBaseConfiguration.create();
        config.set("hbase.master", zip+":"+zport);
        config.set("hbase.zookeeper.quorum", zip);
        config.set("hbase.zookeeper.property.clientPort", zcport);
        // 创建表池
        tp = new HTablePool(config, 10000);*/

        if (poCount != null && !"".equals(poCount)) {
            oc = Integer.parseInt(poCount);
        } else {
            oc = 10000;
        }
        DBConnector connector = new DBConnector();
        conn = connector.getConn(driverClassName, url, username, pwd);
    }


    private void updateInfo(Connection conn) {

        Statement stmt;
        ResultSet rs;
        int count = 1;
        //插入SQL
        String sql = "select id FROM bas_population_info order BY id";
        try {

            //创建连接声明
            stmt = conn.createStatement();
            //执行查询
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int pinfoId = rs.getInt("id");
                //更新数据
                //创建连接声明
                Statement updateStmt = conn.createStatement();
                String updateSql = "update bas_population_info set email_address='" + UserInfoGenerator.getEmail(6, 9) + "' WHERE id=" + pinfoId;
                updateStmt.execute(updateSql);
                count++;
                System.out.println("正在更新第" + count + "条数据!");
            }
            //关闭连接
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }
}
