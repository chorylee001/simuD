package com.wiseweb.action;

import com.wiseweb.util.DBConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Chory on 2017/2/15 0015.
 * 插入车站价格数据
 */
public class InsertDepotPrice {
    static DBConnector connector = null;
    public static void main(String[] args) {

        insertDeportPrice();
    }

    private static void insertDeportPrice() {
        connector = new DBConnector();//创建DBConnector对象
        Statement stmt;
        ResultSet rs;
        int count = 1;
        //插入SQL
        String sql = "select t2.row_key,t2.ID,t2.P1,t2.P2,t2.P3,t2.P4,t2.P5,t2.P6,t2.P7,t2.P8,t2.P9 from cr.train2 t2";
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
                String P5 = rs.getString("P5");
                String P6 = rs.getString("P6");
                String P7 = rs.getString("P7");
                String P8 = rs.getString("P8");
                String P9 = rs.getString("P9");

                //创建连接声明
                Statement insertStmt = conn.createStatement();
                //更新数据sql
                if(trainName.toUpperCase().startsWith("D")||trainName.toUpperCase().startsWith("G")){
                    //商务座
                    deng(insertStmt,rowKey,1,null,P5);
                    //特等座
                    deng(insertStmt,rowKey,2,null,P6);
                    //一等座
                    deng(insertStmt,rowKey,3,null,P2);
                    //二等座
                    deng(insertStmt,rowKey,4,null,P1);
                }else if(trainName.toUpperCase().startsWith("T")){
                    //8软座
                    deng(insertStmt,rowKey,8,null,P8);
                    //9硬座
                    deng(insertStmt,rowKey,9,null,P1);
                    //10无座
                    deng(insertStmt,rowKey,10,null,P9);
                }else if(trainName.toUpperCase().startsWith("Z")){

                    //5高级软卧
                    ruanwo(insertStmt,rowKey,5,P7);
                    //6软卧
                    ruanwo(insertStmt,rowKey,6,P4);
                    //硬卧
                    yingwo(insertStmt,rowKey,P3);
                    //硬座
                    deng(insertStmt,rowKey,9,null,P1);
                    //无座
                    deng(insertStmt,rowKey,10,null,P9);
                }else if(trainName.toUpperCase().startsWith("C")){//城际列车

                    //特等座
                    deng(insertStmt,rowKey,2,null,P6);
                    //一等座
                    deng(insertStmt,rowKey,3,null,P2);
                    //二等座
                    deng(insertStmt,rowKey,4,null,P1);
                    //无座
                    deng(insertStmt,rowKey,10,null,P9);
                }else{
                    //6软卧
                    ruanwo(insertStmt,rowKey,6,P4);
                    //硬卧
                    yingwo(insertStmt,rowKey,P3);
                    deng(insertStmt,rowKey,8,null,P8);
                    //硬座
                    deng(insertStmt,rowKey,9,null,P1);
                    //无座
                    deng(insertStmt,rowKey,10,null,P9);
                }
                count++;
                System.out.println("正在插入第"+count+"条数据!");
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

    private static void deng(Statement stmt,int rowKey,int seadLevel,Integer bunk,String price) throws SQLException {
        String insertSql = "insert into cr_depot_price_info(depot_id,SEAT_LEVEL,BUNK,PRICE) value("+rowKey+","+seadLevel+","+bunk+",'"+price+"')";
        stmt.execute(insertSql);
    }

    private static void ruanwo(Statement stmt,int rowKey,int seadLevel,String price) throws SQLException{

        String insertSql;
        int prefix = price.indexOf("/");
        insertSql = "insert into cr_depot_price_info(depot_id,SEAT_LEVEL,BUNK,PRICE) value("+rowKey+","+seadLevel+",3,'"+price.substring(0,prefix==-1?price.length():prefix)+"')";
        stmt.execute(insertSql);
        int prefix2 = price.indexOf("/");
        insertSql = "insert into cr_depot_price_info(depot_id,SEAT_LEVEL,BUNK,PRICE) value("+rowKey+","+seadLevel+",1,'"+price.substring(prefix2==-1?price.length():prefix2+1,price.length())+"')";
        stmt.execute(insertSql);
    }
    private static void yingwo(Statement stmt,int rowKey,String price) throws SQLException {

        String insertSql;
        Pattern pattern;
        pattern = Pattern.compile("/");
        Matcher matcher = pattern.matcher(price);
        int num = 0;
        int lastIndex = 0;
        while(matcher.find()){
            num++;
            if(num == 1){
                int prefix = price.indexOf("/");
                insertSql = "insert into cr_depot_price_info(depot_id,SEAT_LEVEL,BUNK,PRICE) value("+rowKey+",7,1,'"+price.substring(0,prefix==-1?price.length():prefix)+"')";
                stmt.execute(insertSql);
            }else if(num ==2){
                lastIndex = matcher.start();
                int prefix = price.indexOf("/");
                insertSql = "insert into cr_depot_price_info(depot_id,SEAT_LEVEL,BUNK,PRICE) value("+rowKey+",7,2,'"+price.substring(prefix==-1?price.length():prefix+1,lastIndex)+"')";
                stmt.execute(insertSql);
            }
        }

        insertSql = "insert into cr_depot_price_info(depot_id,SEAT_LEVEL,BUNK,PRICE) value("+rowKey+",7,3,'"+price.substring(lastIndex+1,price.length())+"')";
        stmt.execute(insertSql);
    }
}
