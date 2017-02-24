package com.wiseweb.action;

import com.wiseweb.util.RandomUtils;

import java.sql.*;

/**
 * Created by Chory on 2017/2/17 0017.
 * 用户设备使用生成
 */
public class UseDeviceUserInsetor {

    public static void run(Connection conn,Integer userCount) {

        Statement stmt;
        ResultSet userRs;
        int count = 1;
        if(userCount == null||userCount <=0){
            userCount = 5000;
        }
        //注册用户查询SQL
        String sql = "select id FROM cr_user ORDER BY id limit "+userCount;
        try {
            //创建连接声明
            stmt = conn.createStatement();
            //执行查询
            userRs = stmt.executeQuery(sql);

            //设备信息数量查询SQL
            /*String deviceSql = "select id from cr_data_device";
            Statement deviceStatement = conn.createStatement();
            ResultSet deviceRs = deviceStatement.executeQuery(deviceSql);*/
            while (userRs.next()) {
                //一个用户随机1~2个手机设备
                int randomDevice = (int) (Math.random() * 2 + 1);
                for (int i = 0; i < randomDevice; i++) {
                    useLogInsert(conn, userRs, count);
                    count++;
                    System.out.println("已生成" + count + "条设备使用数据!");
                }
            }
            //关闭连接
            userRs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

    private static void useLogInsert(Connection conn, ResultSet userRs, int count) throws SQLException {

        String userId = userRs.getString("id");
        //查询设备IMEI SQL
        ResultSet deviceRs;
        String sql = "select IMEI FROM cr_data_device where id=" + count;
        Statement deviceStatement = conn.createStatement();
        deviceRs = deviceStatement.executeQuery(sql);
        while (deviceRs.next()) {
            String IMEI = deviceRs.getString("IMEI");
            //每台设备登陆日志随机1-30次
            int loginCount = (int) (Math.random() * 30 + 1);
            for (int i = 0; i <= loginCount; i++) {

                //创建连接声明
                Statement insertStmt = conn.createStatement();
                //插入数据sql
                String insertSql = "insert into cr_device_use(user_id,login_time,imei) value(" + userId + ",'" + RandomUtils.getStringRandomDate() + "','" + IMEI + "')";

                insertStmt.execute(insertSql);
                insertStmt.close();
            }
        }
        deviceRs.close();
        deviceStatement.close();
    }

    private static java.sql.Date getDate() {
        Date date = new java.sql.Date(new java.util.Date().getTime());
        return date;
    }
}
