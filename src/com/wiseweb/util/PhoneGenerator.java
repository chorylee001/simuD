package com.wiseweb.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Chory on 2017/2/16 0016.
 */
public class PhoneGenerator {
    /**
     * 随机生成某一个运营商的某些地区的号码
     * 手机号码共11位数字。前3位是不同的移动运营商（公司）字段。4-7位就是区域代码
     * carries:各种号段数组   location：区域代码数组
     * @return
     */
    public static String generatePhoneNumber(String[] carries, String[] location) {

        Random rand = new Random();
        //随机取 号段+区域代码
        int carriesIndex = rand.nextInt(carries.length);
        int locationIndex = rand.nextInt(location.length);
        //随机生成后4位数
        String[] beforeShuffle = {"1","2", "3", "4", "5", "6", "7", "8", "9","0"};
        List<String> list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for(String s : list) {
            sb.append(s);
        }
        String afterShuffle = sb.toString();
        String last4 = afterShuffle.substring(0,4);

        return carries[carriesIndex] + location[locationIndex] + last4;
    }


    /**
     * 生成手机号码
     */
    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153,188,186,177,182".split(",");
    public static String getTel() {
        int index=getNum(0,telFirst.length-1);
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+third;
    }


    public static void main(String[] args) {
        String[] ca = {"138","188"};
        String[] lo = {"1324","1234"};
        //测试
        for(int i = 0; i < 10; i++) {
            System.out.println(generatePhoneNumber(ca, lo));
        }
    }

    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }
}
