package com.wiseweb.action;

import com.wiseweb.other.data.model.GenerateUserInfo;
import com.wiseweb.util.DBConnector;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Chory on 2017/3/2 0002.
 */
public class OrderJob implements Job{

    private static final String path = "/home/wiseweb/conf/base_data.properties";//"D:/etc/conf/base_data.properties";//
    private static Connection conn;
    private static Integer oc;//订单数量

    public void execute(JobExecutionContext jc) throws JobExecutionException {

        Properties properties = new Properties();
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

            //设备属性数据生成
            DeviceDataInsertor deviceDataInsertor = new DeviceDataInsertor();
            deviceDataInsertor.run(conn, 610000);

            //基本人口属性数据生成
            GenerateUserInfo.run(conn, oc);

            //注册用户生成
            RegisterUserInsertor registerUser = new RegisterUserInsertor();
            int regCount = registerUser.run(conn,373762);
            //相关联系人生成
            UserContactInsertor userContactInsertor = new UserContactInsertor();
            userContactInsertor.run(conn);

            //设备使用数据生成
            UseDeviceUserInsertor useDeviceUserInsetor = new UseDeviceUserInsertor();
            useDeviceUserInsetor.run(conn,oc);

            //订单数据生成
            OrderInsertor orderInsertor = new OrderInsertor();
            orderInsertor.run(conn,oc);

            conn.close();
            System.gc();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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
        String orderCount = properties.getProperty("order_count");

        if (orderCount != null && !"".equals(orderCount)) {
            oc = Integer.parseInt(orderCount);
        } else {
            oc = 10000;
        }
        DBConnector connector = new DBConnector();
        conn = connector.getConn(driverClassName, url, username, pwd);
    }
}
