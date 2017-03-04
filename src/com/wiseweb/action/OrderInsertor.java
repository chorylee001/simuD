package com.wiseweb.action;

import com.wiseweb.entity.DepotBean;
import com.wiseweb.util.RandomUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Chory on 2017/2/16 0016.
 * 订单数据生成，每张订单最多允许购买五张车票
 */
public class OrderInsertor {

    /**
     * 订单生成
     * @param conn
     * @param oc 订单数量
     */
    public static void run(Connection conn,Integer oc) {

        Statement stmt;
        ResultSet userRs;
        int count = 1;
        if (oc == null || oc ==0) {
            oc = 10000;
        }
        //注册用户查询SQL
        String sql = "select id FROM cr_user ORDER BY rand() limit 1000,"+oc;
        try {
            //创建连接声明
            stmt = conn.createStatement();
            //执行查询
            userRs = stmt.executeQuery(sql);
            //一个注册用户2016年下单1-10次
            while (userRs.next()) {

                //车票数据生成
                int randomTicket = (int) (Math.random() * 10 + 1);
                double allPrice = 0;
                for (int i = 0; i < randomTicket; i++) {

                    //车票生成
                    Map tickets = ticketInsert(conn, userRs);
                    Iterator iterator = tickets.keySet().iterator();
                    List<Integer> ticketIds = new ArrayList<>();
                    while (iterator.hasNext()) {
                        Integer id = (Integer) iterator.next();
                        ticketIds.add(id);
                        allPrice += Double.parseDouble(tickets.get(id).toString());
                    }
                    if (ticketIds == null && ticketIds.size() <= 0) {
                        i--;
                        break;
                    }
                    //订单数据生成
                    Statement insertStmt = conn.createStatement();

                    Integer userId = userRs.getInt("id");
                    ResultSet orderRs;
                    String orderNo = RandomUtils.getOrderNo();
                    int row = insertStmt.executeUpdate("INSERT INTO cr_data_order (ORDER_NUMBER,USER_ID,ORDER_TIME,ORDER_STATUS,SUM_PRICE,PAYMENT,ORDER_CHANNEL,PAY_STATUS,PAY_BANK,PAY_APP) VALUES ('" + orderNo + "'," + userId + ",'" + RandomUtils.getTodayTime() + "'," + (int) (Math.random() * 3) + "," + allPrice + "," + (int) (Math.random() * 8 + 1) + ",1," + (Math.random() > 0.7 ? 0 : 1) + "," + (int) (Math.random() * 7 + 1) + "," + (int) (Math.random() * 4 + 1) + ")", Statement.RETURN_GENERATED_KEYS);

                    Integer orderId = null;
                    orderRs = insertStmt.getGeneratedKeys();
                    while (orderRs.next()) {
                        orderId = orderRs.getInt(row);
                    }
                    insertStmt.close();

                    //订单数据插入，每张订单对应多张车票，所以以车票为循环条件
                    for (int j = 0; j < ticketIds.size(); j++) {

                        Integer ticketId = ticketIds.get(j);
                        //更新订单编号
                        Statement upOrderStmt = conn.createStatement();
                        String upSql = "UPDATE src_ticket_info SET ORDER_NUMBER='" + orderNo + "' WHERE id=" + ticketId;
                        upOrderStmt.executeUpdate(upSql);

                        //订单详情表插入
                        Statement ODetailStmt = conn.createStatement();
                        String insertODetailSql = "insert into cr_data_order_detail(ORDER_ID,TICKET_ID,OPERATE_TIME) values(" + orderId + "," + ticketId + ",'" + RandomUtils.getTodayTime() + "')";
                        ODetailStmt.execute(insertODetailSql);
                        ODetailStmt.close();
                    }
                    //交易流水数据生成
                    Statement ofStmt = conn.createStatement();
                    String ofInsert = "insert into cr_data_order_flow(PAY_SERIALS_NUMBER,USER_ID,ORDER_NUMBER,DEAL_TYPE,DEAL_SERIALS_NUMBER,DEAL_STATUS,AMOUNT,PAYMENT,DEAL_SOURCE,DEAL_TIME) values('" + RandomUtils.getOrderNo() + "'," + userId + ",'" + orderNo + "'," + (int) (Math.random() * 4 + 1) + ",'" + RandomUtils.getOrderNo() + "'," + (Math.random() > 0.2 ? 1 : 0) + "," + allPrice + "," + (int) (Math.random() * 8 + 1) + ",1,'" + RandomUtils.getTodayTime() + "')";
                    ofStmt.execute(ofInsert);
                    count++;
                }
                System.out.println("完成插入" + count + "条订单交易流水数据!");
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

    public static Map ticketInsert(Connection conn, ResultSet userRs) {

        Map map = new HashMap();
        try {

            //获取注册用户
            Integer userId = userRs.getInt("id");
            //创建查询常用联系人连接
            ResultSet contactRs;
            Statement contactStmt = conn.createStatement();
            String contactSql = "select contacts_name,CERTIFICATE_TYPE,CERTIFICATE_NUMBER,PASSENGER_TYPE,WN_ID from src_user_contacts WHERE user_id=" + userId;
            contactRs = contactStmt.executeQuery(contactSql);

            List<Map<String, String>> contacts = new ArrayList<>();
            while (contactRs.next()) {
                Map<String, String> contact = new HashMap<>();
                contact.put("contactsName", contactRs.getString("contacts_name"));
                contact.put("ctype", contactRs.getString("CERTIFICATE_TYPE"));
                contact.put("cnum", contactRs.getString("CERTIFICATE_NUMBER"));
                contact.put("ptype", contactRs.getString("PASSENGER_TYPE"));
                contact.put("wnid", contactRs.getString("WN_ID"));
                contacts.add(contact);
            }

            //循环插入车票信息,随机1~联系人的数量除以2
            int conTicketCount = (int) (Math.random() * (contacts.size() / 2));
            for (int i = 0; i < conTicketCount; i++) {

                int contac = (int) (Math.random() * conTicketCount);
                Map<String, String> contact = contacts.get(contac);
                Integer ptype = Integer.parseInt(contact.get("ptype"));
                String contactsName = contact.get("contactsName");
                String ctype = contact.get("ctype");
                String cnum = contact.get("cnum");
                String wnid = contact.get("wnid");
                contacts.remove(contac);


                //创建车次查询连接
                Statement depotStmt = conn.createStatement();
                String queryDepotSql = "select * from cr_depot_basic_info";
                ResultSet depotRs = depotStmt.executeQuery(queryDepotSql);
                List<DepotBean> allDepots = getAllDepotBean(depotRs);
                //所有车次连接关闭
                depotRs.close();
                depotStmt.close();
                //上车车站
                DepotBean startDepot = allDepots.get((int) (Math.random() * allDepots.size()));
                //下车车站站次查询
                Statement depotQueryStmt = conn.createStatement();
                String depotQueryByTrainname = "select * from cr_depot_basic_info binfo where binfo.TRAIN_NAME='" + startDepot.getTrainName() + "' and station_no>" + startDepot.getStationNum();
                ResultSet nextDepotRs = depotQueryStmt.executeQuery(depotQueryByTrainname);
                List<DepotBean> nextDepots = getAllDepotBean(nextDepotRs);
                if (nextDepots == null || nextDepots.size() == 0) {
                    break;
                }
                //下车车站站次查询关闭
                nextDepotRs.close();
                depotQueryStmt.close();
                //下车车站
                DepotBean endDepot = nextDepots.get((int) (Math.random() * nextDepots.size()));

                //随机定义席位
                Integer trainType = startDepot.getTrainType();
                Integer[] seatLevels = DepotBean.getSeatLevel(trainType);
                Integer seatLevel = seatLevels[(int) (Math.random() * seatLevels.length)];
                //车票价格查询连接
                Statement startPriceStmt = conn.createStatement();
                String startPriceSql = "select price from cr_depot_price_info where depot_id=" + startDepot.getId() + " and seat_level=" + seatLevel;
                //5高级软卧、6软卧拥有上下铺位 7硬卧 拥有上中下铺位
                int bunk = 0;
                if (seatLevel == 5 || seatLevel == 6) {
                    bunk = (int) (Math.random() * 2 + 1);
                    startPriceSql.concat(" and bunk=" + bunk);
                } else if (seatLevel == 7) {
                    bunk = (int) (Math.random() * 3 + 1);
                    startPriceSql.concat(" and bunk=" + bunk);
                }
                ResultSet startPriceRs = startPriceStmt.executeQuery(startPriceSql);
                String startPriceStr = "";
                while (startPriceRs.next()) {
                    startPriceStr = startPriceRs.getString(1);
                }
                double startPrice = 0;
                if (startPriceStr != null && !startPriceStr.contains("-")) {
                    startPrice = Double.parseDouble(startPriceStr);
                }
                startPriceRs.close();
                startPriceStmt.close();

                //下车车票价格查询
                Statement endPriceStmt = conn.createStatement();
                String endPriceSql = "select price from cr_depot_price_info where depot_id=" + endDepot.getId() + " and seat_level=" + seatLevel;
                if (seatLevel == 5 || seatLevel == 6) {
                    endPriceSql.concat(" and bunk=" + bunk);
                } else if (seatLevel == 7) {
                    endPriceSql.concat(" and bunk=" + bunk);
                }
                ResultSet endPriceRs = endPriceStmt.executeQuery(endPriceSql);
                String endPriceStr = "";
                while (endPriceRs.next()) {
                    endPriceStr = endPriceRs.getString(1);
                }
                double endPrice = 0;
                if (endPriceStr != null && !endPriceStr.contains("-")) {
                    endPrice = Double.parseDouble(endPriceStr);
                }
                //车票成本价
                double ticketPrice = endPrice - startPrice;
                endPriceRs.close();
                endPriceStmt.close();

                //乘车人类型如果不是成人，则票价减半
                boolean isDiscount = false;
                if (ptype > 1) {
                    isDiscount = true;
                    ticketPrice = ticketPrice / 2;
                }
                //创建车票数据插入连接声明
                Statement insertStmt = conn.createStatement();

                //随机生成线下车票线上,线下:线下= 7:3
                boolean isOnline = Math.random()>0.6?true:false;

                //是否购买保险，8:2
                boolean isPolicy = Math.random() > 0.2 ? false : true;
                //保单编号
                String policyNo = "";
                double policyPrice = 0;
                double policyAmount = 0;
                if (isPolicy) {
                    policyNo = RandomUtils.getOrderNo();
                    //购买保险，儿童1元，成人3元
                    if (ptype == 2) {
                        policyPrice = 1;
                        policyAmount = 10;
                    } else {
                        policyPrice = 3;
                        policyAmount = 30;
                    }
                    ticketPrice = ticketPrice + policyPrice;
                }
                //乘车日期
                String rideTime = RandomUtils.getRandomInsYMD();

                //电子票号
                String elecTicketNo = "";
                if(isOnline){
                    elecTicketNo = RandomUtils.getOrderNo();
                }
                //插入数据sql
                String insertSql = "insert INTO src_ticket_info(ELEC_TICKET_ID,PAPER_TICKET_ID,CERTIFICATE_TYPE,CERTIFICATE_NUMBER,WN_ID,NAME,ORDER_NUMBER,TICKET_PRICE,TRAIN_NAME,SEAT_LEVEL,IS_DISCOUNT,INSURANCE_ID,SEAT_NUMBER,BUNK,RIDE_DATA,DEPARTURE_TIME,DEPARTURE,ARRIVAL,BUY_WAY,TICKET_STATUS,FETCH_TICKET_TIME,TICKET_DELIVERY,TICKET_PRINT_STATUS,TICKET_CATEGORY) VALUES ('" + elecTicketNo + "','" + RandomUtils.getOrderNo() + "','" + ctype + "','" + cnum + "','" + wnid + "','" + contactsName + "','" + RandomUtils.getOrderNo() + "','" + ticketPrice + "','" + startDepot.getTrainName() + "'," + seatLevel + "," + isDiscount + ",'" + policyNo + "','" + (int) (Math.random() * 120 + 1) + "','" + bunk + "','" + rideTime + "','" + rideTime + " " + startDepot.getDepartureTime() + "','" + startDepot.getStationName() + "','" + endDepot.getStationName() + "','"+(isOnline?1:0)+"'," + (int)(Math.random() * 8 + 1) + ",'" + RandomUtils.randomFormatToStringByString(rideTime, (int) (Math.random() * 10)) + "'," + (int) (Math.random() * 3 + 1) + ",1," + ptype + ")";
                int row = insertStmt.executeUpdate(insertSql, insertStmt.RETURN_GENERATED_KEYS);
                int ticketId = 0;
                ResultSet ticketRs = insertStmt.getGeneratedKeys();
                if (ticketRs.next()) {
                    ticketId = ticketRs.getInt(row);
                }
                System.out.println("正在插入第" + ticketId + "条车票数据!");
                insertStmt.close();
                if(isOnline){
                    i--;
                    break;
                }
                if (isPolicy) {
                    //保单生成
                    Statement policyStatement = conn.createStatement();
                    String insertPolicySql = "insert into SRC_INSURANCE_INFO(INSURANCE_ID,INSURED_NAME,CERTIFICATE_TYPE,CERTIFICATE_NUMBER,WN_ID,TICKET_ID,INSURANCE_COMPANY,INSURE_COST,INSURANCE_TYPE,INSURED_AMOUNT,INSURANCE_TIME,EFFECTIVE_TIME,INVALID_TIME) VALUES ('" + policyNo + "','" + contactsName + "','" + ctype + "','" + cnum + "','" + wnid + "'," + ticketId + ",1," + policyPrice + ",1," + policyAmount + ",'" + rideTime + "','" + rideTime + " " + startDepot.getDepartureTime() + "','" + rideTime + " " + endDepot.getArrivalTime() + "')";
                    policyStatement.execute(insertPolicySql);
                    System.out.println("插入" + ticketId + "条保单数据");
                    policyStatement.close();
                }
                map.put(ticketId, ticketPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return map;
    }

    private static List<DepotBean> getAllDepotBean(ResultSet beanSet) throws SQLException {

        List<DepotBean> beanContainer = new ArrayList<>();

        while (beanSet.next()) {
            DepotBean depotBean = new DepotBean();
            depotBean.setId(beanSet.getInt("id"));
            depotBean.setTrainName(beanSet.getString("TRAIN_NAME"));
            depotBean.setTrainType(beanSet.getInt("TRAIN_TYPE"));
            depotBean.setStationName(beanSet.getString("STATION_NAME"));
            depotBean.setStationNum(beanSet.getInt("STATION_NO"));
            depotBean.setArrivalTime(beanSet.getString("ARRIVAL_TIME"));
            depotBean.setDepartureTime(beanSet.getString("DEPARTURE_TIME"));
            depotBean.setTaskTime(beanSet.getString("TASK_TIME"));
            depotBean.setDistance(beanSet.getDouble("DISTANCE"));
            beanContainer.add(depotBean);
        }
        return beanContainer;
    }
}
