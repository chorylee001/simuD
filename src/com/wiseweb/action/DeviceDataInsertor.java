package com.wiseweb.action;

import com.wiseweb.entity.TACBean;
import com.wiseweb.thread.DeviceData;
import com.wiseweb.util.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chory on 2017/2/7 0007.
 * 2016年八月数据统计信息显示
 * 数据调查机构NetApplications报告，安卓66.01%，iOS，27.84%，WindowsPhone2.79%，黑莓0.85%。
 */
public class DeviceDataInsertor {

    private static DBConnector connector = null;

    public static void main(String[] args) {
        connector = new DBConnector();
        Connection conn = connector.getConn();
        run(conn,1);//1220000*165~2Y  220000
    }

    /**
     *
     * @param
     * @param tacImeiCount 每个tac生成imei条数
     */
    public static void run(Connection conn, Integer tacImeiCount) {

//        String code = "35254112632400";
//        String newCode = IMEIGenerator.genCode(code);
//        String endCode = "35254112633700";

        try {
            //查询所有品牌
            Statement tacStmt = conn.createStatement();
            String tacSql = "select * from cr_data_device_tac ORDER BY weight DESC ";
            ResultSet tacRs = tacStmt.executeQuery(tacSql);

            List<TACBean> tacs = new ArrayList<>();
            while(tacRs.next()){
                TACBean tacBean = new TACBean();
                tacBean.setTacCode(tacRs.getString("tac_code"));
                tacBean.setBrandEn(tacRs.getString("brand_en"));
                tacBean.setBrandZh(tacRs.getString("brand_zh"));
                tacBean.setModel(tacRs.getString("model"));
                tacBean.setWeight(tacRs.getFloat("weight"));
                tacs.add(tacBean);
            }

            if(tacImeiCount == null|| tacImeiCount<=0){
                tacImeiCount = 1000;
            }
            System.out.println("开始生成IMEI码...");
            List<String> imeis = IMEIGenerator.createIMEIByTac(tacs, "632400", tacImeiCount);
            System.out.println("imei码生成完成," + imeis.size() + "条");

            System.out.println("开始生成mac地址...");
            //生成tac个数*每个tac生成imei条数
            List<String> macs = MACGenerator.getMacAdr("00:70:A4:00:00:00", tacs.size() * tacImeiCount);
            System.out.println("mac地址生成完成！" + macs.size() + "条");

            Statement inst = conn.createStatement();
            DeviceData deviceData = new DeviceData(macs,imeis,tacImeiCount,inst);
            deviceData.run();
            inst.close();

            /*String path = "D:\\export";//"/mnt/disk3/";//
            String fileName = "deviceData";
            File file = CSVUtils.createCSVFile(exportData, map, path, fileName);
            endtime = System.currentTimeMillis();
            usetime = endtime - current;
            System.out.println("文件写入完成," + exportData.size() + "行,共耗时" + usetime + "毫秒");
            String fileName2 = file.getName();
            System.out.println("文件名称：" + fileName2);*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
