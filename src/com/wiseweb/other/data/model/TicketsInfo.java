package com.wiseweb.other.data.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/**
 * Created by ZJJ on 2017/2/10.
 */
public class TicketsInfo {

    private static Calendar calendar = Calendar.getInstance();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static Date nowDate = new Date();

    private static List<String> stationNames = new ArrayList<>();

    private static List<Map<String,String>> t1 = new ArrayList<>();
    private static List<Map<String,String>> t2 = new ArrayList<>();

    private static String getEStation(String sStation){

        if(stationNames.size() <= 0){
            String sql = "select Station from pinyin";
            DBHelper db1 = null;
            ResultSet ret = null;
            try {
                db1 = new DBHelper(sql);
                ret = db1.pst.executeQuery();
                while (ret.next()) {
                    stationNames.add(ret.getString("Station"));
                }
                ret.close();
                db1.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        String eStation = stationNames.get((int) (Math.random()*stationNames.size()));

        while (eStation.equals(sStation)){
            eStation = stationNames.get((int) (Math.random()*stationNames.size()));
        }
        return eStation;
    }

    private static void getTickets(){
        File file = new File("D:\\ZTTEST\\User.csv");
        File tfile = new File("D:\\ZTTEST\\TicketsInfo"+System.currentTimeMillis()+".csv");
        Connection conn = DBHelper.getConnection();
        try {
            FileReader fReader = new FileReader(file);
            FileWriter fWriter = new FileWriter(tfile);
            CSVReader csvReader = new CSVReader(fReader,'|');
            CSVWriter csvWriter = new CSVWriter(fWriter, ',',CSVWriter.NO_QUOTE_CHARACTER);
            String[] strs = {};
            ThreadMXBean bean = ManagementFactory.getThreadMXBean();
            long cpuStart = bean.getCurrentThreadCpuTime();
            int n = 1;
            while((strs=csvReader.readNext())!=null && strs.length > 0){
                String sStation = strs[15].replace("市","");
                String eStation = city();
                List<Train> trains = getTrains(sStation,eStation,conn);
               if (trains == null || trains.size() < 0){
                    sStation = city();
                    trains = getTrains(sStation,eStation,conn);
                }else{
                   for(int i = 0;i<trains.size();i++){
                       String[] ti = {strs[0],strs[2],strs[3]
                               ,trains.get(i).getTicketNo()
                               ,trains.get(i).gettName()
                               ,trains.get(i).getDep()
                               ,trains.get(i).getDepTime()
                               ,trains.get(i).getArr()
                               ,trains.get(i).getArrTime()};
                       csvWriter.writeNext(ti);
                   }
               }
                System.out.println(n++);


            }
            csvReader.close();
            csvWriter.flush();
            csvWriter.close();
            System.out.printf("Devices use Total count:%d\n",(bean.getCurrentThreadCpuTime() - cpuStart) / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
                DBHelper.closeConnection(conn);
        }

    }

    private static void getTrain1(String sStation,String eStation, Connection conn){
        t1.clear();
        try {
            String sql = "SELECT R1.Type, R1.Station AS StartStation, R2.Station AS EndStation, \n" +
                    "R1.ID AS RouteID, R2.S_No-R1.S_No AS StopCount, R2.Distance - R1.Distance AS KM, \n" +
                    "R1.D_Time, R2.A_Time\n" +
                    "FROM Train AS R1 INNER JOIN Train AS R2 ON R1.ID = R2.ID\n" +
                    "WHERE (R1.S_No < R2.S_No) and ((R1.Station like '"+sStation+"%')  AND  (R2.Station like '"+eStation+"%'))";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet ret = pst.executeQuery();
            while (ret.next()) {
                Map<String,String> t = new HashMap<>();
                t.put("type",ret.getString("Type"));
                t.put("sStation",ret.getString("StartStation"));
                t.put("eStation",ret.getString("EndStation"));
                t.put("sTime",ret.getString("D_Time"));
                t.put("eTime",ret.getString("A_Time"));
                t.put("tno",ret.getString("RouteID"));
                t.put("d",ret.getString("KM"));
                t1.add(t);
            }
            ret.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void getTrain2(String sStation, String eStation, Connection conn){
        t2.clear();
        try {
            String sql = "SELECT R1.KM as d1,R2.KM as d2, R1.Type AS T1, R1.StartStation AS sStation, R1.RouteID AS sID, R1.EndStation AS tStation,\n " +
                    "R2.Type AS T2, R2.RouteID AS tID, R2.EndStation AS eStation, \n" +
                    "R1.StopCount+R2.StopCount AS AllCount, \n" +
                    "R1.D_Time AS sTime1, R1.A_Time AS sTime2, \n" +
                    "R2.D_Time AS eTime1, R2.A_Time AS eTime2, \n" +
                    "R1.KM+R2.KM AS AllKM\n" +
                    "FROM NonstopView AS R1 INNER JOIN NonstopView AS R2 ON R1.EndStation = R2.StartStation \n" +
                    "WHERE ((R1.StartStation like '"+sStation+"%')  AND  (R2.EndStation like '"+eStation+"%'))";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet ret = pst.executeQuery();
            while (ret.next()) {
                Map<String,String> t = new HashMap<>();
                t.put("type1",ret.getString("T1"));
                t.put("type2",ret.getString("T2"));
                t.put("sStation",ret.getString("sStation"));
                t.put("tStation",ret.getString("tStation"));
                t.put("eStation",ret.getString("eStation"));
                t.put("sTime1",ret.getString("sTime1"));
                t.put("eTime1",ret.getString("eTime1"));
                t.put("sTime2",ret.getString("sTime2"));
                t.put("eTime2",ret.getString("eTime2"));
                t.put("tno1",ret.getString("sID"));
                t.put("tno2",ret.getString("tID"));
                t.put("d1",ret.getString("d1"));
                t.put("d2",ret.getString("d2"));
                t2.add(t);
            }
            ret.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static List<Train>  getTrains(String sStation,String eStation,Connection conn){
        List<Train> trains = null;
        getTrain1(sStation,eStation,conn);
        if(t1.size() <=0){
            getTrain2(sStation,eStation,conn);
            if(t2.size() > 0){
                Map<String,String> t = t2.get((int) (Math.random()*t2.size()));
                trains = new ArrayList<>();
                Train tn= new Train();
                tn.setTicketNo("ZZ"+System.currentTimeMillis());
                tn.settName(t.get("tno1"));
                tn.setDep(t.get("sStation"));
                tn.setArr(t.get("tStation"));
                String day = getTime(nowDate);
                tn.setDepTime(day+" "+t.get("sTime1"));
                tn.setArrTime(t.get("sTime1").compareTo(t.get("eTime1")) < 0?day+" "+t.get("eTime1"):(day=getTimeAddDay(1))+" "+t.get("eTime1"));
                trains.add(tn);
                Train tn2= new Train();
                tn2.setTicketNo("ZZ"+(System.currentTimeMillis()+1));
                tn2.settName(t.get("tno2"));
                tn2.setDep(t.get("tStation"));
                tn2.setArr(t.get("eStation"));
                tn2.setDepTime(day+" "+t.get("sTime2"));
                tn2.setArrTime(t.get("sTime2").compareTo(t.get("eTime2")) < 0?day+" "+t.get("eTime2"):(day=getTimeAddDay(1))+" "+t.get("eTime2"));
                trains.add(tn2);
             }
        }else{
            trains = new ArrayList<>();
            Map<String,String> t = t1.get((int) (Math.random()*t1.size()));
            Train tn= new Train();
            tn.setTicketNo("ZZ"+System.currentTimeMillis());
            tn.settName(t.get("tno"));
            tn.setDep(t.get("sStation"));
            tn.setArr(t.get("eStation"));
            String day = getTime(nowDate);
            tn.setDepTime(day+" "+t.get("sTime"));
            tn.setArrTime(t.get("sTime").compareTo(t.get("eTime")) < 0?day+" "+t.get("eTime"):(day=getTimeAddDay(1))+" "+t.get("eTime"));
            trains.add(tn);
        }
        return trains;
    }

    private static String getTime(Date date){
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -(int) (1 + Math.random() * (365)));
        return sdf.format(calendar.getTime());

    }

    private static String getTimeAddDay(int day){
        calendar.add(Calendar.DAY_OF_YEAR,day);
        return sdf.format(calendar.getTime());
    }



    public static void main(String[] args) {
        for(int i=0;i< 3;i++){
            getTickets();
        }

    }

    public static String city(){
        String[] ctiys = {"北京"
                ,"上海"
                ,"广州"
                ,"深圳"
                ,"天津"
                ,"杭州"
                ,"南京"
                ,"济南"
                ,"重庆"
                ,"青岛"
                ,"大连"
                ,"宁波"
                ,"厦门"
                ,"成都"
                ,"武汉"
                ,"哈尔滨"
                ,"沈阳"
                ,"西安"
                ,"长春"
                ,"长沙"
                ,"福州"
                ,"郑州"
                ,"石家庄"
                ,"苏州"
                ,"佛山"
                ,"东莞"
                ,"无锡"
                ,"烟台"
                ,"太原"
                ,"合肥"
                ,"南昌"
                ,"南宁"
                ,"昆明"
                ,"温州"
                ,"淄博"
                ,"唐山"
                ,"乌鲁木齐"
                ,"贵阳"
                ,"海口"
                ,"兰州"
                ,"银川"
                ,"西宁"
                ,"呼和浩特"
                ,"泉州"
                ,"包头"
                ,"南通"
                ,"大庆"
                ,"徐州"
                ,"潍坊"
                ,"常州"
                ,"鄂尔多斯"
                ,"绍兴"
                ,"济宁"
                ,"盐城"
                ,"邯郸"
                ,"临沂"
                ,"洛阳"
                ,"东营"
                ,"台州"
                ,"嘉兴"
                ,"沧州"
                ,"榆林"
                ,"泰州"
                ,"镇江"
                ,"昆山"
                ,"江阴"
                ,"张家港"
                ,"义乌"
                ,"金华"
                ,"保定"
                ,"吉林"
                ,"鞍山"
                ,"泰安"
                ,"宜昌"
                ,"襄阳"
                ,"中山"
                ,"惠州"
                ,"南阳"
                ,"威海"
                ,"德州"
                ,"岳阳"
                ,"聊城"
                ,"常德"
                ,"漳州"
                ,"滨州"
                ,"茂名"
                ,"淮安"
                ,"江门"
                ,"芜湖"
                ,"湛江"
                ,"廊坊"
                ,"菏泽"
                ,"柳州"
                ,"宝鸡"
                ,"珠海"
                ,"绵阳"
                ,"株洲"
                ,"枣庄"
                ,"许昌"
                ,"通辽"
                ,"湖州"
                ,"新乡"
                ,"咸阳"
                ,"松原"
                ,"连云港"
                ,"安阳"
                ,"周口"
                ,"焦作"
                ,"赤峰"
                ,"邢台"
                ,"郴州"
                ,"宿迁"
                ,"赣州"
                ,"平顶山"
                ,"桂林"
                ,"肇庆"
                ,"曲靖"
                ,"九江"
                ,"商丘"
                ,"汕头"
                ,"信阳"
                ,"驻马店"
                ,"营口"
                ,"揭阳"
                ,"龙岩"
                ,"安庆"
                ,"日照"
                ,"遵义"
                ,"三明"
                ,"呼伦贝尔"
                ,"长治"
                ,"湘潭"
                ,"德阳"
                ,"南充"
                ,"乐山"
                ,"达州"
                ,"盘锦"
                ,"锦州"
                ,"宜春"
                ,"宜宾"
                ,"张家口"
                ,"马鞍山"
                ,"吕梁"
                ,"抚顺"
                ,"临汾"
                ,"渭南"
                ,"开封"
                ,"莆田"
                ,"荆州"
                ,"黄冈"
                ,"四平"
                ,"承德"
                ,"齐齐哈尔"
                ,"三门峡"
                ,"秦皇岛"
                ,"本溪"
                ,"玉林"
                ,"孝感"
                ,"牡丹江"
                ,"荆门"
                ,"宁德"
                ,"运城"
                ,"绥化"
                ,"永州"
                ,"怀化"
                ,"黄石"
                ,"泸州"
                ,"清远"
                ,"邵阳"
                ,"衡水"
                ,"益阳"
                ,"丹东"
                ,"铁岭"
                ,"晋城"
                ,"朔州"
                ,"吉安"
                ,"娄底"
                ,"玉溪"
                ,"辽阳"
                ,"南平"
                ,"濮阳"
                ,"晋中"
                ,"资阳"
                ,"都江堰"
                ,"攀枝花"
                ,"衢州"
                ,"内江"
                ,"滁州"
                ,"阜阳"
                ,"十堰"
                ,"大同"
                ,"朝阳"
                ,"六安"
                ,"宿州"
                ,"通化"
                ,"蚌埠"
                ,"韶关"
                ,"丽水"
                ,"自贡"
                ,"阳江"
                ,"毕节"
                ,"拉萨"
                ,"克拉玛依"
                ,"库尔勒"
                ,"昌吉"
                ,"哈密"
                ,"伊宁"
                ,"喀什"
                ,"阿克苏"
                ,"石河子"
                ,"晋江"
                ,"增城"
                ,"诸暨"
                ,"丹阳"
                ,"玉环"
                ,"常熟"
                ,"崇明"
                ,"余姚"
                ,"奉化"
                ,"海宁"
                ,"浏阳市"
                ,"大理"
                ,"丽江"
                ,"普洱"
                ,"保山"
                ,"昭通"
                ,"西昌"
                ,"雅安"
                ,"广安"
                ,"广元"
                ,"巴中"
                ,"遂宁"
                ,"天水"
                ,"酒泉"
                ,"嘉峪关"
                ,"武威"
                ,"张掖"
                ,"石嘴山"
                ,"吴忠"
                ,"北海"
                ,"百色"
        };
        return ctiys[(int) (Math.random()*ctiys.length)];
    }


}
