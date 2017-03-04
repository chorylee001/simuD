package com.wiseweb.action;

import com.mysql.jdbc.StringUtils;
import com.wiseweb.util.PhoneGenerator;
import com.wiseweb.util.RandomUtils;

import java.sql.*;

/**
 * Created by Chory on 2017/2/16 0016.
 * 注册用户生成器
 */
public class RegisterUserInsertor {

    /**
     *
     * @param
     * @param pc 新增用户数量
     */
    public static int run(Connection conn, Integer pc) {
        Statement stmt;
        ResultSet rs;
        int count = 1;
        if(pc == null || pc<=0){
            pc = 10000;
        }
        //查询SQL
       /* HbaseBase base = new HbaseBase();
        HbasePageBean pageBean = base.getDataMap("BAS_POPULATION_INFO","","",1,pc);
        pageBean.getResultList();*/

        String sql = "select USER_ID,USER_NAME,NAME,CERTIFICATE_CODE,BIRTHDAY FROM BAS_POPULATION_INFO order BY id desc limit "+pc;
        //插入SQL
        try {
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
                    String insertSql = "insert into cr_user(ID,username,password,certificate_number,register_time,register_ip,last_login_time,last_login_ip,login_count,realname,sex,birthday,mobile) value('"+userId+"','"+userName+"','123123','"+certificateCode+"','"+getDate()+"','"+ RandomUtils.getRandomIp()+"','"+RandomUtils.getStringRandomDate()+"','"+ RandomUtils.getRandomIp()+"',"+ RandomUtils.randomNum()+",'"+realName+"',"+(int)(Math.random()*3)+",'"+birthday+"','"+ PhoneGenerator.getTel()+"')";
                    insertStmt.execute(insertSql);
                    count++;
                    insertStmt.close();
                }
            }
            System.out.println("注册用户生成完成，共"+count+"条数据!");
            //关闭连接
            rs.close();
            stmt.close();

        }catch (NumberFormatException nfe){
            nfe.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    private static java.sql.Date getDate(){
        Date date = new java.sql.Date(new java.util.Date().getTime());
        return date;
    }
}
