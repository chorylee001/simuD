package com.wiseweb.action;

import com.wiseweb.entity.PopulationBean;
import com.wiseweb.util.DBConnector;
import com.wiseweb.util.RandomUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chory on 2017/2/17 0017.
 * 常用联系人生成
 */
public class UserContactInsertor {

    static DBConnector connector = null;
    public static void main(String[] args) {

        insertContact();
    }

    private static void insertContact() {

        connector = new DBConnector();
        Statement stmt;
        ResultSet rs;
        int count = 1;
        //注册用户查询SQL
        String sql = "SELECT ID FROM cr_user";
        //插入SQL
        try {
            //获取连接
            Connection conn = connector.getConn();
            //创建注册用户连接声明
            stmt = conn.createStatement();
            //执行查询
            rs = stmt.executeQuery(sql);

            //创建人口基本信息连接声明
            Statement poStmt = conn.createStatement();
            String poSql = "select * FROM BAS_POPULATION_INFO";
            ResultSet poRS = poStmt.executeQuery(poSql);
            List<PopulationBean> populationBeans = new ArrayList<>();
            while(poRS.next()){
                PopulationBean population = new PopulationBean();
                population.setId(poRS.getInt("ID"));
                population.setName(poRS.getString("name"));
                population.setPassengerType(poRS.getString("PASSENGER_TYPE"));
                population.setCertificateType(poRS.getString("CRETIFICATE_TYPE"));
                population.setCertificateCode(poRS.getString("CERTIFICATE_CODE"));
                population.setUserId(poRS.getInt("user_id"));
                population.setUserName(poRS.getString("user_name"));
                population.setSex(poRS.getString("sex"));
                population.setCountry(poRS.getString("country"));
                population.setBirthday(poRS.getString("birthday"));
                population.setNation(poRS.getString("nation"));
                population.setPhoneNumber(poRS.getString("phonenumber"));
                population.setEmailAddress(poRS.getString("email_address"));
                population.setPostCode(poRS.getString("postcode"));
                population.setAddress(poRS.getString("address"));
                population.setWnId(poRS.getString("wn_id"));
                population.setRegisterTime(poRS.getDate("register_time"));
                populationBeans.add(population);
            }
            while(rs.next()){
                //创建连接声明
                Statement insertStmt = conn.createStatement();
                //每个注册用户1-10个常用联系人
                int rcCount = (int) (Math.random()*10+1);
                for(int i=0;i<rcCount;i++){

                    //随机获取人口基本属性账户信息
                    int popuR = (int) (Math.random()*populationBeans.size());
                    PopulationBean bean = populationBeans.get(popuR);
                    Integer userId = rs.getInt("ID");
                    String realName = bean.getName();
                    String ctype = bean.getCertificateType();
                    String cnum = bean.getCertificateCode();
                    String passType = bean.getPassengerType();
                    int ptype = 1;
                    switch (passType){
                        case "儿童":
                            ptype=2;
                            break;
                        case "学生":
                            ptype=3;
                            break;
                        case "残军":
                            ptype=4;
                            break;
                        default:ptype=1;
                    }

                    int status = Math.random()>0.2?1:0;
                    String wnId = bean.getWnId();
                    //插入数据sql
                    String insertSql = "insert into src_user_contacts(USER_ID,CONTACTS_NAME,CERTIFICATE_TYPE,CERTIFACATE_NUMBER,PASSENGER_TYPE,STATUS,LAST_BUYTICKET_TIME,CREATE_TIME,WN_ID) value("+userId+",'"+realName+"',"+ctype+",'"+cnum+"',"+ptype+","+status+",'"+ RandomUtils.getStringRandomDate()+"','"+ RandomUtils.getStringRandomDate()+"','"+wnId+"')";
                    insertStmt.execute(insertSql);
                    count++;
                    System.out.println("正在插入第"+count+"条数据!");
                }

                insertStmt.close();
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
