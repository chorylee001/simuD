package com.wiseweb.util;

import com.wiseweb.entity.TACBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chory on 2017/2/7 0007.
 * IMEI码生成器
 */
public class IMEIGenerator {

    /**
     * IMEI 校验码
     *
     * @param code IMEI初始code
     * @return
     */
    public static String genCode(String code) {
        int total = 0, sum1 = 0, sum2 = 0;
        int temp = 0;
        char[] chs = code.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            int num = chs[i] - '0';     // ascii to num
            /*(1)将奇数位数字相加(从1开始计数)*/
            if (i % 2 == 0) {
                sum1 = sum1 + num;
            } else {
                /*(2)将偶数位数字分别乘以2,分别计算个位数和十位数之和(从1开始计数)*/
                temp = num * 2;
                if (temp < 10) {
                    sum2 = sum2 + temp;
                } else {
                    sum2 = sum2 + temp + 1 - 10;
                }
            }
        }
        total = sum1 + sum2;
        /*如果得出的数个位是0则校验位为0,否则为10减去个位数 */
        if (total % 10 == 0) {
            return "0";
        } else {
            return (10 - (total % 10)) + "";
        }

    }

    /**
     * IMEI 校验码生成算法2
     *
     * @param imeiString
     * @return
     */
    private static String genCode2(String imeiString) {

        char[] imeiChar = imeiString.toCharArray();
        int resultInt = 0;

        for (int i = 0; i < imeiChar.length; i++) {
            int a = Integer.parseInt(String.valueOf(imeiChar[i]));
            i++;
            final int temp = Integer.parseInt(String.valueOf(imeiChar[i])) * 2;
            final int b = temp < 10 ? temp : temp - 9;
            resultInt += a + b;
        }

        resultInt %= 10;
        resultInt = resultInt == 0 ? 0 : 10 - resultInt;

        return imeiString + resultInt;
    }

    /**
     * 批量生成IMEI
     *
     * @param start 开始code
     * @param end   结束code
     * @return
     */
    public static List<String> createIMEI(String start, String end) {
        List<String> imeis = new ArrayList<String>();
        try {
            long count = Long.parseLong(end) - Long.parseLong(start);
            Long currentCode = Long.parseLong(start);
            String code;
            for (int i = 0; i <= count; i++) {
                code = currentCode.toString();
                code = code + genCode(code);
                imeis.add(code);
                currentCode += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imeis;
    }

    /**
     * @param tacs      list for tac
     * @param startCode 后七位imei码
     * @param count     每个tac生成imei个数
     * @return
     */
    public static List<String> createIMEIByTac(List<TACBean> tacs, String startCode, int count) {

        List<String> imeis = new ArrayList<String>();

        try {
            for (int i = 0; i < tacs.size(); i++) {

                //获取TAC
                TACBean tacBean = tacs.get(i);
                String tacCode = tacBean.getTacCode();
                String code = null;
                Long currentCode = Long.parseLong(startCode);
                for (int j = 0; j < count; j++) {
                    code = currentCode.toString();
                    code= tacCode.concat(code)+genCode(code);
                    imeis.add(code);
                    currentCode += 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imeis;
    }
}
