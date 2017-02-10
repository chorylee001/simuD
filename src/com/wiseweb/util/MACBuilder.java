package com.wiseweb.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chory on 2017/2/7 0007.
 * MAC地址生成器
 */
public class MACBuilder {

    /**
     * code生成
     * @param str
     * @return
     */
    private static String getMacAdr(String str) {
        StringBuilder result = new StringBuilder("");
        for (int i = 1; i <= 12; i++) {
            result.append(str.charAt(i - 1));
            if (i % 2 == 0) {
                result.append(":");
            }
        }
        return result.substring(0, 17);
    }

    /**
     * 根据给定mac地址生成
     * @param oriMac 源mac
     * @param count 生成数量
     * @return
     */
    public static List<String> getMacAdr(String oriMac, int count) {
        oriMac = oriMac.replaceAll(":", "");
        BigInteger num = new BigInteger(oriMac, 16);
        BigInteger addNum = new BigInteger("1");
        String code = "";
        List<String> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            code = num.toString(16).toUpperCase();
            for (int j = 12 - code.length(); j > 0; j--) {
                code = "0" + code;
            }
            result.add(getMacAdr(code));
            num = num.add(addNum);
        }
        return result;
    }

    /**
     * 将mac地址生成到txt文件
     * @param filePath 文件路径
     * @param oriMac 源mac
     * @param count 生成数量
     */
    public static void getFileMac(String filePath, String oriMac, int count) {
        oriMac = oriMac.replaceAll(":", "");
        try {
            File file = new File(filePath);
            FileWriter writer = new FileWriter(file, true);
            BigInteger num = new BigInteger(oriMac, 16);
            BigInteger addNum = new BigInteger("1");
            String result = "";
            for (int i = 0; i < count; i++) {
                result = num.toString(16).toUpperCase();
                for (int j = 12 - result.length(); j > 0; j--) {
                    result = "0" + result;
                }
                writer.write(getMacAdr(result) + "\n");
                num = num.add(addNum);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
