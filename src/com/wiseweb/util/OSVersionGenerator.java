package com.wiseweb.util;

/**
 * Created by Chory on 2017/2/7 0007.
 * 移动系统版本生成
 */
public class OSVersionGenerator {

    //系统类型
    public static final String[] osType = {"android","ios","windows phone"};
    //安卓系统版本
    private static final String[] androidVersion = {"4.0","4.0.1","4.0.2","4.0.3","4.0.4","4.1","4.1.1","4.1.2","4.2","4.2.1","4.2.2","4.3","4.3.1","4.4","4.4.1","4.4.2","4.4.3","4.4.4","5.0","5.0.1","5.0.2","5.1","5.1.1","6.0","6.0.1","7.0","7.1.1"};
    //IOS系统版本
    private static final String[] iosVersion = {"5.1.1","6.1.6","7.1.2","8.4.2","9.3.5","10.2.1","10.3 Beta 2"};
    //wp系统版本
    private static final String[] wpVersion = {"7","7.5","7.8","8","8.1","10"};

    /**
     * 随机生成android系统版本
     * @return
     */
    public static String getAndroidVersion(){

        return osType[0]+" "+androidVersion[(int) (Math.random()*26+1)];
    }
    /**
     * 随机生成IOS系统版本
     * @return
     */
    public static String getIOSVersion(){

        return osType[1]+" "+iosVersion[(int) (Math.random()*6+1)];
    }
    /**
     * 随机生成wp系统版本
     * @return
     */
    public static String getWPVersion(){

        return osType[2]+" "+iosVersion[(int) (Math.random()*5+1)];
    }
}
