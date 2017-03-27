package com.wiseweb.thread;

import com.wiseweb.util.OSVersionGenerator;
import com.wiseweb.util.ResolutionGenerator;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Chory on 2017/3/15 0015.
 */
public class DeviceData implements Runnable {

    private List<String> macs;
    private List<String> imeis;
    private int tacImeiCount;
    private Statement insStmt;
    private Connection conn;

    public DeviceData(List<String> macs, List<String> imeis, int tacImeiCount, Statement insStmt) throws SQLException {
        this.macs = macs;
        this.imeis = imeis;
        this.tacImeiCount = tacImeiCount;
        this.insStmt = insStmt;
    }

    @Override
    public void run() {

        int ac = 0, ic = 0;//ios计数器;android计数器;windows phone计数器（no use）
        String insertSql = "";
        String OSType = "";
        String OSVersion = "";

        for (int i = 0; i < macs.size() * tacImeiCount; i++) {

            //占有比66:28:3
            if (ac <= 66) {
                OSType = OSVersionGenerator.osType[0];
                ac++;
                if (ac == 66) {
                    ic = 0;
                }
                OSVersion = OSVersionGenerator.getAndroidVersion();
            } else if (ic <= 28) {
                OSType = OSVersionGenerator.osType[1];
                OSVersion = OSVersionGenerator.getIOSVersion();
                ic++;
            } else {
                OSType = OSVersionGenerator.osType[2];
                OSVersion = OSVersionGenerator.getWPVersion();
                ac = 0;
            }
            insertSql = "insert into cr_data_device(IMEI,MAC,OS_TYPE,OS_VERSION,RESOLUTION_VIDEO,IS_USED,UPDATE_TIME) values('"+imeis.get(i)+"','"+macs.get(i)+"','"+OSType+"','"+OSVersion+"','"+ ResolutionGenerator.getResolution()+"',0,'"+System.currentTimeMillis()+"')";
            try {
                insStmt.execute(insertSql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("设备属性数据生成完成");
    }
}
