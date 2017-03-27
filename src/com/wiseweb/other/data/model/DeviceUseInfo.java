package com.wiseweb.other.data.model;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ZJJ on 2017/2/9.
 */
public class DeviceUseInfo {

    private static Calendar calendar = Calendar.getInstance();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.000000");
    private static Date nowDate = new Date();

    public static void main(String[] args) throws Exception {
        DeviceUseInfo();
    }


    private static void DeviceUseInfo() throws Exception {

        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        long cpuStart;

        File file = new File("D:\\ZTTEST\\User.csv");
        File dfile = new File("D:\\ZTTEST\\Device.csv");
        File dufile = new File("D:\\ZTTEST\\DeviceUse.csv");
        File sfile  = new File("D:\\ZTTEST\\School.csv");
        File sufile  = new File("D:\\ZTTEST\\UserSchool.csv");
        FileReader fReader = new FileReader(file);
        FileReader fReaderD = new FileReader(dfile);
        FileWriter fWriterD = new FileWriter(dufile);
        FileReader fReaderS = new FileReader(sfile);
        FileWriter fWriterS = new FileWriter(sufile);
        CSVReader csvReader = new CSVReader(fReader,'|');
        CSVReader csvReaderD = new CSVReader(fReaderD,',');
        CSVWriter csvWriterD = new CSVWriter(fWriterD, ',',CSVWriter.NO_QUOTE_CHARACTER);
        CSVReader csvReaderS = new CSVReader(fReaderS,',');
        CSVWriter csvWriterS = new CSVWriter(fWriterS, ',',CSVWriter.NO_QUOTE_CHARACTER);
        String[] strs = {};
        String[] dstrs = {};

        cpuStart = bean.getCurrentThreadCpuTime();

        List<String[]> school = csvReaderS.readAll();


        while((strs=csvReader.readNext())!=null && strs.length > 0){

            if(strs[1].contains("学生")){
                String[] sc = school.get((int) (Math.random()*(school.size())));
                String[] su = {strs[0],strs[2],strs[3],sc[6],sc[1],"","","",sc[4],"","","",""};
                csvWriterS.writeNext(su);
            }

            dstrs = csvReaderD.readNext();
            if(!"".equals(strs[4])&& dstrs !=null){
                int n = (int) (1+Math.random()*(20));
                for(int i = 0;i < n ;i++){
                  String[] du = {strs[2],strs[3],getTime(nowDate),dstrs[1]};
                  csvWriterD.writeNext(du);
                }
            }
        }

        csvReader.close();
        csvReaderD.close();
        csvWriterS.flush();
        csvWriterD.flush();
        csvWriterS.close();
        csvWriterD.close();

        System.out.printf("Devices use Total count:%d\n",(bean.getCurrentThreadCpuTime() - cpuStart) / 1000);

    }
    private static String getTime(Date date){
        do {
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_YEAR, -(int) (1 + Math.random() * (10)));
            calendar.add(Calendar.HOUR_OF_DAY, (int) (1 + Math.random() * (12)));
            calendar.add(Calendar.MINUTE, (int) (1 + Math.random() * (30)));
            calendar.add(Calendar.SECOND, (int) (1 + Math.random() * (30)));
        }while (calendar.get(Calendar.HOUR_OF_DAY)>=23 || Calendar.HOUR_OF_DAY <=6);

        return sdf.format(calendar.getTime());
    }


}
