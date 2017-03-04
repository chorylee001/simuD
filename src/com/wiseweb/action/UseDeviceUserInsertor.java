package com.wiseweb.action;

import com.wiseweb.util.RandomUtils;

import java.sql.*;

/**
 * Created by Chory on 2017/2/17 0017.
 * 用户设备使用生成
 */
public class UseDeviceUserInsertor {

    public static void run(Connection conn,Integer pc) {

        Statement stmt;
        ResultSet userRs;

        if(pc == null || pc <=0){
            pc = 10000;
        }
        int count = 1;
        try {
            //创建连接声明
            stmt = conn.createStatement();
            //随机获取注册用户
            String sql = "select id FROM cr_user ORDER BY rand() limit 1000,"+pc;
            //执行查询
            userRs = stmt.executeQuery(sql);
            while (userRs.next()) {
                //一个用户随机1~2个手机设备
                int randomDevice = (int) (Math.random() * 2 + 1);
                for (int i = 0; i < randomDevice; i++) {
                    useLogInsert(conn, userRs);
                    count++;
                }
            }
            System.out.println("已生成" + count + "条设备使用数据!");
            //关闭连接
            userRs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

    private static void useLogInsert(Connection conn, ResultSet userRs) throws SQLException {

        String userId = userRs.getString("id");
        //查询设备IMEI SQL
        ResultSet deviceRs;
        //设备信息查询
        String sql = "select id,IMEI FROM cr_data_device where ORDER BY rand() limit " + (int)(Math.random()*2+1);
        Statement deviceStatement = conn.createStatement();
        deviceRs = deviceStatement.executeQuery(sql);

        Statement upstmt = conn.createStatement();
        String upsql = "";
        while (deviceRs.next()) {

            //更新设备信息为使用状态
            String IMEI = deviceRs.getString("IMEI");
            String did = deviceRs.getString("id");
            upsql = "update cr_data_device set is_used=1 where id='"+did+"'";
            upstmt.executeUpdate(upsql);

            //每台设备每天登陆日志随机1-5次
            int loginCount = (int) (Math.random() * 5 + 1);
            for (int i = 0; i <= loginCount; i++) {

                //创建连接声明
                Statement insertStmt = conn.createStatement();
                //插入数据sql
                String insertSql = "insert into cr_device_use(user_id,login_time,imei) value(" + userId + ",'" + RandomUtils.getTodayTime() + "','" + IMEI + "')";

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
