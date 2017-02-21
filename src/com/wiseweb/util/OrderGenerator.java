package com.wiseweb.util;

import java.util.UUID;

/**
 * Created by Chory on 2017/2/16 0016.
 */
public class OrderGenerator {

    public static String getOrderIdByUUId() {

       /* int machineId = 1;
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        //hashCode为负数转为正数
        if(hashCodeV < 0) {
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0 15代表长度为15+1 d代表参数为正数型
        return machineId + String.format("%015d", hashCodeV);*/

        String id = UUID.randomUUID().toString();
        id = id.replace("-", "");
        return id;
    }

    public static void main(String[] args) {
        System.out.println(getOrderIdByUUId());
    }
}
