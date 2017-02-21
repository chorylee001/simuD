package com.wiseweb.action;

import com.mysql.jdbc.StringUtils;
import com.wiseweb.util.DBConnector;
import com.wiseweb.util.PhoneGenerator;
import com.wiseweb.util.RandomUtils;

import java.sql.*;

/**
 * Created by Chory on 2017/2/16 0016.
 * 注册用户生成器
 */
public class RegisterUserInsertor {

    static DBConnector connector = null;
    public static void main(String[] args) {

        insertDeportPrice();
    }

    private static void insertDeportPrice() {
        connector = new DBConnector();//创建DBConnector对象
        Statement stmt;
        ResultSet rs;
        int count = 1;
        //查询SQL
        String sql = "select USER_ID,USER_NAME,NAME,CERTIFICATE_CODE,BIRTHDAY FROM BAS_POPULATION_INFO";
        //插入SQL
        try {
            //获取连接
            Connection conn = connector.getConn();
            //创建连接声明
            stmt = conn.createStatement();
            //执行查询
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                //创建连接声明
                Statement insertStmt = conn.createStatement();

                String userName = rs.getString("USER_NAME");
                if(!StringUtils.isNullOrEmpty(userName)){

                    String userId = rs.getString("USER_ID");
                    String certificateCode = rs.getString("CERTIFICATE_CODE");
                    String realName = rs.getString("NAME");
                    String birthday = rs.getString("BIRTHDAY");
                    //插入数据sql
                    String insertSql = "insert into cr_user(ID,username,password,certificate_number,register_time,register_ip,last_login_time,last_login_ip,login_count,realname,sex,birthday,mobile) value("+userId+",'"+userName+"','123123','"+certificateCode+"','"+getDate()+"','"+ RandomUtils.getRandomIp()+"','"+RandomUtils.getRandomDate()+"','"+ RandomUtils.getRandomIp()+"',"+ RandomUtils.randomNum()+",'"+realName+"',"+(int)(Math.random()*3)+",'"+birthday+"','"+ PhoneGenerator.getTel()+"')";
                    insertStmt.execute(insertSql);
                    count++;
                    System.out.println("正在插入第"+count+"条数据!");
                    insertStmt.close();
                }
            }
            //关闭连接
            rs.close();
            stmt.close();
            connector.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (NumberFormatException nfe){
            nfe.printStackTrace();
        }
    }

    private static java.sql.Date getDate(){
        Date date = new java.sql.Date(new java.util.Date().getTime());
        return date;
    }
}
