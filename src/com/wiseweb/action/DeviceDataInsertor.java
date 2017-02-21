package com.wiseweb.action;

import com.wiseweb.entity.TACBean;
import com.wiseweb.util.*;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chory on 2017/2/7 0007.
 * 2016年八月数据统计信息显示
 * 数据调查机构NetApplications报告，安卓66.01%，iOS，27.84%，WindowsPhone2.79%，黑莓0.85%。
 */
public class DeviceDataInsertor {

    private static DBConnector connector = null;

    public static void main(String[] args) {

//        String code = "35254112632400";
//        String newCode = IMEIGenerator.genCode(code);
//        String endCode = "35254112633700";

        connector = new DBConnector();
        //获取所有tac
        Connection conn = connector.getConn();
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

            //每个tac生成imei条数
            int tacImeiCount = 3030;
            System.out.println("开始生成IMEI码...");
            long current = System.currentTimeMillis();
            List imeis = IMEIGenerator.createIMEIByTac(tacs, "632400", tacImeiCount);
//        List imeis = IMEIGenerator.createIMEI(code,endCode);
            long endtime = System.currentTimeMillis();
            long usetime = endtime - current;
            System.out.println("imei码生成完成," + imeis.size() + "条,共耗时" + usetime + "毫秒");

            System.out.println("开始生成mac地址...");
            current = System.currentTimeMillis();
            //生成tac个数*每个tac生成imei条数
            List<String> macs = MACGenerator.getMacAdr("00:70:A4:00:00:00", tacs.size() * tacImeiCount);
            endtime = System.currentTimeMillis();
            usetime = endtime - current;
            System.out.println("mac地址生成完成！" + macs.size() + "条,共耗时" + usetime + "毫秒");

            System.out.println("开始写入文件...");
            current = System.currentTimeMillis();
            List exportData = new ArrayList<Map>();
            int ac = 0, ic = 0;//ios计数器;android计数器;windows phone计数器（no use）
            for (int i = 0; i < tacs.size() * tacImeiCount; i++) {
                Map row = new LinkedHashMap<String, String>();
                row.put("1", i + 1);
                row.put("2", imeis.get(i));
                row.put("3", macs.get(i));
                //占有比66:28:3
                if (ac <= 66) {
                    row.put("4", OSVersionGenerator.osType[0]);
                    ac++;
                    if (ac == 66) {
                        ic = 0;
                    }
                    row.put("5", OSVersionGenerator.getAndroidVersion());

                } else if (ic <= 28) {
                    row.put("4", OSVersionGenerator.osType[1]);
                    row.put("5", OSVersionGenerator.getIOSVersion());
                    ic++;
                } else {
                    row.put("4", OSVersionGenerator.osType[2]);
                    row.put("5", OSVersionGenerator.getWPVersion());
                    ac = 0;
                }

                row.put("6", ResolutionGenerator.getResolution());
                row.put("7", System.currentTimeMillis());
                exportData.add(row);
            }
            LinkedHashMap map = new LinkedHashMap();
            map.put("1", "ID");
            map.put("2", "IMEI");
            map.put("3", "MAC");
            map.put("4", "OS_TYPE");
            map.put("5", "OS_VERSION");
            map.put("6", "RESOLUTION_VIDEO");
            map.put("7", "UPDATE_TIME");

            String path = "D:\\export";//"/mnt/disk3/";//
            String fileName = "deviceData";
            File file = CSVUtils.createCSVFile(exportData, map, path, fileName);
            endtime = System.currentTimeMillis();
            usetime = endtime - current;
            System.out.println("文件写入完成," + exportData.size() + "行,共耗时" + usetime + "毫秒");
            String fileName2 = file.getName();
            System.out.println("文件名称：" + fileName2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
