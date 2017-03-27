/* Copyright Beijing Wiseweb Technology Co., Ltd. */
package com.wiseweb.other.data.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author TearyWang on 2017/2/27.
 */
public class Reference {

    private static final int TOTAL = 134662;
    private static final String PATH_BASE = "D:\\tag\\";
    private static final int WORKING_THREAD = 5;

    public static class TagDictTag {
        private final String tagTable;
        private final String tagCode;
        private final String tagValue;
        private final List<Usage> items;

        // Instance the tag value item.
        public TagDictTag(String table, String code, String value,
                          TagDictTagVal[] items) {

            this.tagTable = table;
            this.tagCode = code;
            this.tagValue = value;
            this.items = new ArrayList<>();
            for (int i = 0; i < items.length; i++)
                this.items.add(new Usage(this,
                        items[i], (int) Math.round(items[i].getRatio() * TOTAL)));
        }

        public String getTagTable() {
            return tagTable;
        }

        public String getTagCode() {
            return tagCode;
        }

        public String getTagValue() {
            return tagValue;
        }

        public TagDictTagVal takeOne() {
            int vsize = items.size();
            int index = (int) (Math.random() * 10000) % vsize;
            Usage u = items.get(index);
            TagDictTagVal val = u.item;
            u.count--;
            if (u.count == 0)
                items.remove(index);

            return val;
        }

        private static class Usage {

            TagDictTag owner;
            TagDictTagVal item;
            int count;

            Usage(TagDictTag owner, TagDictTagVal item, int count) {
                this.owner = owner;
                this.item = item;
                this.count = count;
            }


        }
    }

    public static class TagDictTagVal {
        private final String tagCode;
        private final String tagValue;
        private final double ratio;

        // Instance the tag value item.
        public TagDictTagVal(String code, String value, double ratio) {
            this.tagCode = code;
            this.tagValue = value;
            this.ratio = ratio;
        }

        public String getTagCode() {
            return tagCode;
        }

        public String getTagValue() {
            return tagValue;
        }

        public double getRatio() {
            return ratio;
        }
    }

    private static TagDictTag[] TAGS = new TagDictTag[]{
            new TagDictTag("TAG_0301", "T0301", "惯乘车次类型", new TagDictTagVal[]{
                    new TagDictTagVal("T030101", "G-高铁", 0.27),
                    new TagDictTagVal("T030102", "C-城际", 0.10),
                    new TagDictTagVal("T030103", "D-动车", 0.28),
                    new TagDictTagVal("T030104", "Z-直达", 0.13),
                    new TagDictTagVal("T030105", "T-特快", 0.11),
                    new TagDictTagVal("T030106", "K-快速", 0.09),
                    new TagDictTagVal("T030107", "其他", 0.02)
            }),
            new TagDictTag("TAG_0306", "T0306", "惯乘席别", new TagDictTagVal[]{
                    new TagDictTagVal("T030601", "商务座", 0.04),
                    new TagDictTagVal("T030602", "特等座", 0.04),
                    new TagDictTagVal("T030603", "一等座", 0.14),
                    new TagDictTagVal("T030604", "二等座", 0.14),
                    new TagDictTagVal("T030605", "高级软卧", 0.12),
                    new TagDictTagVal("T030606", "软卧", 0.12),
                    new TagDictTagVal("T030607", "硬卧", 0.12),
                    new TagDictTagVal("T030608", "软座", 0.12),
                    new TagDictTagVal("T030609", "硬座", 0.13),
                    new TagDictTagVal("T030610", "无座", 0.02),
                    new TagDictTagVal("T030611", "其它", 0.01)
            }),
            new TagDictTag("TAG_0307", "T0307", "惯用取票方式", new TagDictTagVal[]{
                    new TagDictTagVal("T030701", "电子票", 0.1),
                    new TagDictTagVal("T030702", "自助终端", 0.35),
                    new TagDictTagVal("T030703", "车站窗口", 0.30),
                    new TagDictTagVal("T030704", "代售窗口", 0.25),
            }),
            new TagDictTag("TAG_0308", "T0308", "改签行为", new TagDictTagVal[]{
                    new TagDictTagVal("T030801", "电子票", 0.08),
                    new TagDictTagVal("T030802", "自助终端", 0.32),
                    new TagDictTagVal("T030803", "车站窗口", 0.60),
            }),
            new TagDictTag("TAG_0309", "T0309", "退票行为", new TagDictTagVal[]{
                    new TagDictTagVal("T030901", "经常", 0.05),
                    new TagDictTagVal("T030902", "偶尔", 0.3),
                    new TagDictTagVal("T030903", "无", 0.65),
            }),
            new TagDictTag("TAG_0318", "T0318", "支付比例", new TagDictTagVal[]{
                    new TagDictTagVal("T031801", "全部", 0.68),
                    new TagDictTagVal("T031802", "高", 0.17),
                    new TagDictTagVal("T031803", "中", 0.1),
                    new TagDictTagVal("T031803", "无", 0.05),
            }),
            new TagDictTag("TAG_0310", "T0310", "投保频次", new TagDictTagVal[]{
                    new TagDictTagVal("T031001", "全部", 0.04),
                    new TagDictTagVal("T031002", "经常", 0.06),
                    new TagDictTagVal("T031003", "偶尔", 0.15),
                    new TagDictTagVal("T031004", "从不", 0.75)
            }),
            new TagDictTag("TAG_0311", "T0311", "惯用支付渠道", new TagDictTagVal[]{
                    new TagDictTagVal("T031101", "线上", 0.63),
                    new TagDictTagVal("T031102", "线下", 0.37)
            }),
            new TagDictTag("TAG_0312", "T0312", "惯用支付平台", new TagDictTagVal[]{
                    new TagDictTagVal("T031201", "支付宝/APP", 0.28),
                    new TagDictTagVal("T031202", "支付宝/网站", 0.26),
                    new TagDictTagVal("T031203", "微信", 0),
                    new TagDictTagVal("T031204", "网银", 0.24),
                    new TagDictTagVal("T031205", "中国银联", 0.22)
            }),
            new TagDictTag("TAG_0313", "T0313", "惯用支付银行", new TagDictTagVal[]{
                    new TagDictTagVal("T031301", "工商银行", 0.23),
                    new TagDictTagVal("T031302", "农业银行", 0.19),
                    new TagDictTagVal("T031303", "中国银行", 0.17),
                    new TagDictTagVal("T031304", "建设银行", 0.24),
                    new TagDictTagVal("T031305", "招商银行", 0.09),
                    new TagDictTagVal("T031305", "中铁银通卡", 0.08)
            }),
            new TagDictTag("TAG_0314", "T0314", "出行频率", new TagDictTagVal[]{
                    new TagDictTagVal("T031401", "经常", 0.19),
                    new TagDictTagVal("T031402", "偶尔", 0.77),
                    new TagDictTagVal("T031403", "无", 0.04),
            }),
            new TagDictTag("TAG_0315", "T0315", "距离偏好", new TagDictTagVal[]{
                    new TagDictTagVal("T031501", "短途", 0.87),
                    new TagDictTagVal("T031502", "长途", 0.13)
            }),
            new TagDictTag("TAG_0316", "T0316", "时长偏好", new TagDictTagVal[]{
                    new TagDictTagVal("T031601", "五小时以内", 0.79),
                    new TagDictTagVal("T031602", "五小时及以上", 0.21)
            }),
            new TagDictTag("TAG_0317", "T0317", "出行偏好", new TagDictTagVal[]{
                    new TagDictTagVal("T031701", "短途", 0.62),
                    new TagDictTagVal("T031702", "中途", 0.27),
                    new TagDictTagVal("T031703", "长途", 0.12)
            })
    };


    private static List<TagDictTag> TAGS_LIST = new ArrayList<>();

    static {
        Collections.addAll(TAGS_LIST, TAGS);
    }

    private static synchronized TagDictTag takeItem() {

        int sz = TAGS_LIST.size();
        return sz > 0 ? TAGS_LIST.remove(sz - 1) : null;
    }

    private static class Person {
        private String wnId;
        private String certNumber;
        private String passengerType;

        public Person(String wnId, String certNo, String passenger) {
            this.wnId = wnId;
            this.certNumber = certNo;
            this.passengerType = passenger;
        }

        public String getWnId() {
            return wnId;
        }

        public String getCertNumber() {
            return certNumber;
        }

        public String getPassengerType() {
            return passengerType;
        }
    }

    private static List<Person> PopulationBase = new ArrayList<>();

    public static class Label extends Thread {

        @Override
        public void run() {

            Random random = new Random();
            StringBuilder sb = new StringBuilder(2048);
            TagDictTag dictTag = null;
            while ((dictTag = takeItem()) != null) {

                System.out.println(getName() + ": Start " + dictTag.getTagTable());

                try {
                    PrintWriter pw = new PrintWriter(
                            PATH_BASE + dictTag.getTagTable() + ".csv", "utf8");

                    for (int i = 0; i < PopulationBase.size(); i++) {
                        sb.delete(0, sb.length());
                        Person p = PopulationBase.get(i);
                        TagDictTagVal val = dictTag.takeOne();
                        // Field order:
                        // CERTIFICATE_TYPE
                        // CERTIFICATE_NUMBER
                        // WN_ID
                        // TAG_CODE
                        // TAG_VALUE
                        pw.write(
                                sb.append("0,")
                                        .append(p.getCertNumber()).append(",")
                                        .append(p.getWnId()).append(",")
                                        .append(val.getTagCode()).append(",")
                                        .append(val.getTagValue()).append(",")
                                        .append("\n").toString());
                    }

                    pw.flush();
                    pw.close();
                } catch (IOException e) {
                    System.out.println("Failed on " + dictTag.getTagTable());
                }

                System.out.println(getName() + ": End " + dictTag.getTagTable());
            }
        }
    }

    private static String removeFieldNav(String txt) {
        if (txt.startsWith("\""))
            txt = txt.substring(1);
        if (txt.endsWith("\""))
            txt = txt.substring(0, txt.length() - 1);

        return txt;
    }

    public static void main(String[] args) {

        // Load population info.
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(PATH_BASE + "population.txt"),
                            "utf8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                int s1 = line.indexOf(",");
                int s2 = line.indexOf(",", s1 + 1);
                String wnId = removeFieldNav(line.substring(0, s1).trim());
                String certNo = removeFieldNav(line.substring(s1 + 1, s2).trim());
                String pat = removeFieldNav(line.substring(s2 + 1).trim());

                PopulationBase.add(new Person(wnId, certNo, pat));
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        long startAt = System.currentTimeMillis();

        Label[] labels = new Label[WORKING_THREAD];

        for (int i = 0; i < WORKING_THREAD; i++) {
            labels[i] = new Label();
            labels[i].setName("Working thread " + i);
        }

        for (int i = 0; i < labels.length; i++)
            labels[i].start();

        while (!isAllExists(labels)) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
            }
        }

        System.out.printf("Total cost: %fs\n",
                (double) (System.currentTimeMillis() - startAt) / 1000.0);
    }

    private static boolean isAllExists(Thread[] threads) {
        for (int i = 0; i < threads.length; i++)
            if (threads[i].getState() != Thread.State.TERMINATED)
                return false;

        return true;
    }
}


/*
T030101
T030102
T030103
T030104
T030105
T030106
T030107
**/
