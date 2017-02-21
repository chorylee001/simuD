package com.wiseweb.action;

import com.wiseweb.entity.TACBean;
import com.wiseweb.util.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Chory on 2017/2/9 0009.
 */
public class TACInsertor {

    static DBConnector connector = null;
    public static void main(String[] args) {

        connector = new DBConnector();//创建DBConnector对象
        //预编译接口对象
        PreparedStatement pstmt;
        //计数器
        int count = 1;
        //插入SQL
        String sql = "insert into d_tac(id,tac_code,brand_en,brand_zh,model) values(?,?,?,?,?)";
        try {
            //获取连接
            Connection conn = connector.getConn();
            //创建执行语句
            pstmt = conn.prepareStatement(sql);
            //插入数据
            for(int i = 0; i< TACBean.tacCodes.length; i++){

                pstmt.setInt(1, count);
                pstmt.setString(2,TACBean.tacCodes[i]);
                pstmt.setString(3,TACBean.brandsEn[i]);
                pstmt.setString(4,TACBean.brandsZh[i]);
                pstmt.setString(5,TACBean.models[i]);
                pstmt.execute();
                count++;
            }
            //关闭连接
            pstmt.close();
            connector.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
