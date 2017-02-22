package com.wiseweb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by Chory on 2017/2/16 0016.
 * IP随机生成器
 */
public class RandomUtils {

    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 随机生成国内IP地址
     *
     * @return
     */
    public static String getRandomIp() {

        //ip范围
        int[][] range = {{607649792, 608174079},//36.56.0.0-36.63.255.255
                {1038614528, 1039007743},//61.232.0.0-61.237.255.255
                {1783627776, 1784676351},//106.80.0.0-106.95.255.255
                {2035023872, 2035154943},//121.76.0.0-121.77.255.255
                {2078801920, 2079064063},//123.232.0.0-123.235.255.255
                {-1950089216, -1948778497},//139.196.0.0-139.215.255.255
                {-1425539072, -1425014785},//171.8.0.0-171.15.255.255
                {-1236271104, -1235419137},//182.80.0.0-182.92.255.255
                {-770113536, -768606209},//210.25.0.0-210.47.255.255
                {-569376768, -564133889}, //222.16.0.0-222.95.255.255
        };

        Random rdint = new Random();
        int index = rdint.nextInt(10);
        String ip = num2ip(range[index][0] + new Random().nextInt(range[index][1] - range[index][0]));
        return ip;
    }

    /**
     * 将十进制转换成ip地址
     *
     * @param ip 十进制IP
     * @return
     */
    public static String num2ip(int ip) {
        int[] b = new int[4];
        String x = "";

        b[0] = (int) ((ip >> 24) & 0xff);
        b[1] = (int) ((ip >> 16) & 0xff);
        b[2] = (int) ((ip >> 8) & 0xff);
        b[3] = (int) (ip & 0xff);
        x = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "." + Integer.toString(b[2]) + "." + Integer.toString(b[3]);
        return x;
    }

    public static int randomNum() {

        return (int) (Math.random() * 100);
    }


    /**
     * 生成随机时间
     *
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static Date randomDate(String beginDate, String endDate,SimpleDateFormat format) {

        try {

            Date start = format.parse(beginDate);//构造开始日期
            Date end = format.parse(endDate);//构造结束日期
            //getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
            if (start.getTime() >= end.getTime()) {
                System.out.println("开始时间不得小于结束时间.");
                return null;
            }

            long date = randomForLong(start.getTime(), end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Date randomDate(String beginDate, String endDate) {

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return randomDate(beginDate,endDate,format);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long randomForLong(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        //如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        if (rtn == begin || rtn == end) {
            return randomForLong(begin, end);
        }
        return rtn;
    }

    public static java.sql.Date getRandomDate() {

        return new java.sql.Date(RandomUtils.randomDate("2015-01-01 00:00:00", "2017-02-17 23:59:59").getTime());
    }
    public static Date getJavaRandomDate() {

        return RandomUtils.randomDate("2015-01-01 00:00:00", "2017-02-17 23:59:59");
    }

    public static String getStringRandomDate() {

        return format.format(RandomUtils.randomDate("2015-01-01 00:00:00", "2017-02-17 23:59:59"));
    }

    public static String getRandomYMD(){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(RandomUtils.randomDate("2015-01-01 00:00:00", "2017-02-17 23:59:59"));
    }
    public static java.sql.Date getDate(){
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        return date;
    }

    public static String randomFormatToStringByString(String ymd,int random) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = sdf.parse(ymd);
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - random);


        return sdf.format(date.getTime());
    }

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int i=0;i<=100;i++){

            //String resulttime = format.format(getRandomDate());//构造开始日期
            /*String resulttime = format.format(new java.sql.Date(getRandomDate().getTime()));
            System.out.println(resulttime);*/
            System.out.println(format.format(RandomUtils.randomDate("2015-01-01 00:00:00", "2017-02-17 23:59:59")));
        }


    }

    public static String getOrderNo() {
        String orderNo = "";
        String trandNo = String.valueOf((Math.random() * 9 + 1) * 1000000);
        String sdf = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
        orderNo = trandNo.toString().substring(0, 4);
        orderNo = orderNo + sdf;
        return orderNo;
    }
}