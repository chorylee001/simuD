package com.wiseweb.action;

import com.wiseweb.util.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Chory on 2017/2/15 0015.
 * 生成车次席位价格信息
 */
public class UpdateBasicTrain {

    static DBConnector connector = null;
    public static void main(String[] args) {

        connector = new DBConnector();//创建DBConnector对象
        Statement stmt;
        ResultSet rs;
        int count = 1;
        //插入SQL
        String sql = "select * FROM train2 WHERE row_key";
        try {
            //获取连接
            Connection conn = connector.getConn();
            //创建连接声明
            stmt = conn.createStatement();
            //执行查询
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                int rowKey = rs.getInt("row_key");
                String trainName = rs.getString("ID");
                String P1 = rs.getString("P1");
                String P2 = rs.getString("P2");
                String P3 = rs.getString("P3");
                String P4 = rs.getString("P4");
                //更新数据
                //创建连接声明
                Statement updateStmt = conn.createStatement();
                String updateSql;
                if(trainName.toUpperCase().startsWith("D")||trainName.toUpperCase().startsWith("G")){
                    //特等座=一等座*130%
                    if("-".equals(P2)){
                        updateSql = "update train2 set p6 ='" + P2 + "' WHERE row_key=" + rowKey;
                    }else {
                        updateSql = "update train2 set p6 ='" + Double.parseDouble(P2) * 1.3 + "' WHERE row_key=" + rowKey;
                    }
                    updateStmt.execute(updateSql);
                    //商务座=特等座*130%
                    if("-".equals(P2)){
                        updateSql = "update train2 set p5 ='"+P2+"' WHERE row_key="+rowKey;
                    }else {
                        updateSql = "update train2 set p5 ='"+Double.parseDouble(P2)*1.3*1.3+"' WHERE row_key="+rowKey;
                    }

                    updateStmt.execute(updateSql);
                }else if(trainName.toUpperCase().startsWith("T")){
                    //软座=硬座*130%
                    if("-".equals(P1)){
                        updateSql = "update train2 set p8 ='" + P1 + "' WHERE row_key=" + rowKey;
                    }else {
                        updateSql = "update train2 set p8 ='" + Double.parseDouble(P1) * 1.3 + "' WHERE row_key=" + rowKey;
                    }
                    updateStmt.execute(updateSql);
                    //无座
                    updateSql = "update train2 set p9 ='"+P1+"' WHERE row_key="+rowKey;
                    updateStmt.execute(updateSql);
                }else if(trainName.toUpperCase().startsWith("Z")){
                    //高级软卧=软卧下铺*130%
                    String[] ps = P4.split("/");
                    String pv = ps[ps.length - 1];
                    if("-".equals(pv)){

                        updateSql = "update train2 set p7 ='" + pv.substring(0,pv.indexOf(".")+2) + "' WHERE row_key=" + rowKey;
                    }else {
                        updateSql = "update train2 set p7 ='" + Double.parseDouble(pv.substring(0,pv.indexOf(".")+2)) * 1.3 + "' WHERE row_key=" + rowKey;
                    }
                    updateStmt.execute(updateSql);
                    //无座
                    updateSql = "update train2 set p9 ='"+P1+"' WHERE row_key="+rowKey;
                    updateStmt.execute(updateSql);
                }else{
                    //无座
                    updateSql = "update train2 set p9 ='"+P1+"' WHERE row_key="+rowKey;
                    updateStmt.execute(updateSql);
                }
                count++;
                System.out.println("正在更新第"+count+"条数据!");
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
}
