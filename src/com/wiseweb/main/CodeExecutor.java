package com.wiseweb.main;

import com.wiseweb.entity.TACBean;
import com.wiseweb.util.*;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chory on 2017/2/7 0007.
 * 2016年八月数据统计信息显示
 * 数据调查机构NetApplications报告，安卓66.01%，iOS，27.84%，WindowsPhone2.79%，黑莓0.85%。
 */
public class CodeExecutor {

    public static void main(String[] args) {

//        String code = "35254112632400";
//        String newCode = IMEIBuilder.genCode(code);
//        String endCode = "35254112633700";

        //获取所有tac
        String[] tacs = TACBean.tacCodes;
        //每个tac生成imei条数
        int tacImeiCount = 12100;
        System.out.println("开始生成IMEI码...");
        long current = System.currentTimeMillis();
        List imeis = IMEIBuilder.createIMEIByTac(tacs, "632400", tacImeiCount);
//        List imeis = IMEIBuilder.createIMEI(code,endCode);
        long endtime = System.currentTimeMillis();
        long usetime = endtime - current;
        System.out.println("imei码生成完成," + imeis.size() + "条,共耗时" + usetime + "毫秒");

        System.out.println("开始生成mac地址...");
        current = System.currentTimeMillis();
        //生成tac个数*每个tac生成imei条数
        List<String> macs = MACBuilder.getMacAdr("00:70:A4:00:00:00", tacs.length * tacImeiCount);
        endtime = System.currentTimeMillis();
        usetime = endtime - current;
        System.out.println("mac地址生成完成！" + macs.size() + "条,共耗时" + usetime + "毫秒");

        System.out.println("开始写入文件...");
        current = System.currentTimeMillis();
        List exportData = new ArrayList<Map>();
        int ac = 0, ic = 0;//ios计数器;android计数器;windows phone计数器（no use）
        for (int i = 0; i < tacs.length * tacImeiCount; i++) {
            Map row = new LinkedHashMap<String, String>();
            row.put("1", i + 1);
            row.put("2", imeis.get(i));
            row.put("3", macs.get(i));
            //占有比66:28:3
            if (ac <= 66) {
                row.put("4", OSVersionBuilder.osType[0]);
                ac++;
                if (ac == 66) {
                    ic = 0;
                }
                row.put("5", OSVersionBuilder.getAndroidVersion());

            } else if (ic <= 28) {
                row.put("4", OSVersionBuilder.osType[1]);
                row.put("5", OSVersionBuilder.getIOSVersion());
                ic++;
            } else {
                row.put("4", OSVersionBuilder.osType[2]);
                row.put("5", OSVersionBuilder.getWPVersion());
                ac = 0;
            }

            row.put("6", ResolutionBuilder.getResolution());
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
    }
}
