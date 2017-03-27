package com.wiseweb.other.data.model;


import com.wiseweb.other.character.src.com.wiseweb.model.character.HCharTable;
import com.wiseweb.other.character.src.com.wiseweb.model.character.Name;
import com.wiseweb.other.region.src.com.wiseweb.model.gb.region.GbRegion;
import com.wiseweb.util.PhoneGenerator;
import com.wiseweb.util.RandomUtils;
import com.wiseweb.util.UserInfoGenerator;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * Created by ZJJ on 2017/2/6.
 */
public class GenerateUserInfo{

    private static int COUNT = 100000; //4000000
    private static final String SPLITTER = "";

    private static final int[] N18VC = { 7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2 };
    private static final char[] N18VL = "10X98765432".toCharArray();
    private static final GbRegion[] regionTable = (GbRegion[])
            GbRegion.countyRegions().toArray(new GbRegion[0]);

    private static final int NowYear = Calendar.getInstance().get(Calendar.YEAR);

    private static final String[] surname = Name.BookOfFamilyNames.getTable();
    private static final char[] givenName = HCharTable.HfShfStokeLE10.getTable();

    private static List<Nation> nations;
    private static List<Sex> sexes;
    private static List<Age> ages;


    private static <T extends DP>
    void prepareList(List<T> lists, T[] items) {

        int left = COUNT;
        for (int i = 0; i < items.length; i++) {
            if (i == items.length - 1) {
                items[i].count = left;
                left = 0;
            }
            else {
                items[i].count = (int)Math.round(
                        COUNT * items[i].percentage);
                left -= items[i].count;
            }
            lists.add(items[i]);
        }
    }

    private static <T extends DPRange>
    void prepareList(List<T> lists, T[] items) {

        int left = COUNT;
        for (int i = 0; i < items.length; i++) {
            if (i == items.length - 1) {
                items[i].count = left;
                left = 0;
            } else {
                items[i].count = (int) Math.round(
                        COUNT * items[i].percentage);
                left -= items[i].count;
            }
            lists.add(items[i]);
        }
    }

    private static <T extends DP>
    String getName(List<T> list, int n) {

        int ls = list.size();
        if(ls <=0 ){
            return "";
        }
        n = Math.abs(n) % ls;
        T t = list.get(n);
        --t.count;
        String ret = t.name;
        if (t.count <= 0)
            list.remove(n);

        return ret;
    }

    private static <T extends DPRange>
    String getName(List<T> list, int n, ValueGetter v) {

        if (v == null)
            return null;

        int ls = list.size();
        if(ls == 0){
            return "";
        }
        n = Math.abs(n) % ls;
        T t = list.get(n);
        --t.count;
        String ret = t.name;
        v.value = n % (t.max - t.min) + t.min;
        if (t.count <= 0)
            list.remove(n);

        return ret;
    }

    private static void init() {

        prepareList(nations = new ArrayList<>(NATIONS.length), NATIONS);
        prepareList(sexes = new ArrayList<>(SEXES.length), SEXES);
        prepareList(ages = new ArrayList<Age>(AGES.length), AGES);
    }

    private static String customerType(int age){
        String re = "成人";
        if(age <= 15){
            re = "儿童";
        }
        if(age >= 65){
            re = "老人";
        }
        if((int)(1+Math.random()*(1000))==500){
           re="残军";
        }

        //生成客户类型是学生的情况
        if(age >=18 && age <28){
            if(!re.equals("残军")){
                if((int)(1+Math.random()*(10-1+1))<6){
                    re="学生";
                }
            }
        }

        return  re;
    }

    public static void run(Connection conn, Integer poCount) {

        init();

        String idcRegionCode;
        String idcBirthday;
        String idcOO;
        String idcSex;
        String idcValidation;

        String orgName;
        String orgSex;
        String orgBirthday;
        String orgNation;
        String orgRegion;
        GbRegion region;
        int vno = 0, nSex;

        long totalCount = 0;

        if(poCount != null&& poCount!=0){
            COUNT = poCount;
        }
        try {

            ThreadMXBean bean = ManagementFactory.getThreadMXBean();
            long cpuStart;
            double an;

            Random random = new Random();
            cpuStart = bean.getCurrentThreadCpuTime();
            int count=1;
            for (int i = 0; i < COUNT;i++) {

                totalCount++;

                int n = Math.abs(random.nextInt());

                region = regionTable[n % regionTable.length];

                idcRegionCode = region.getCode();
                orgRegion = region.getShortFullName();
                int np = 0;
                for (int j = 0; j < idcRegionCode.length(); j++) {
                    vno = (idcRegionCode.charAt(j) - '0') * N18VC[np++];
                }

                ValueGetter age = new ValueGetter();
                getName(ages, n, age);


                int y = NowYear - age.value; //n % (NowYear - 1949) + 1949;
                int m = n % 12 + 1;
                int d = n % (m == 2 ? 28
                        : (m <= 7 ? (m % 2 == 1 ? 31 : 30)
                        : (m % 2 == 1 ? 30 : 31))
                ) + 1;

                idcBirthday = "" + y + (m < 10 ? "0" : "") + m + (d < 10 ? "0" : "") + d;
                orgBirthday = "" + y + "/" + (m < 10 ? "0" : "") + m + "/" + (d < 10 ? "0" : "") + d;
                for (int j = 0; j < idcBirthday.length(); j++) {
                    vno = (idcBirthday.charAt(j) - '0') * N18VC[np++];
                }

                idcOO = "0" + n % 10;
                for (int j = 0; j < idcOO.length(); j++)
                    vno = (idcOO.charAt(j) - '0') * N18VC[np++];

                orgSex = getName(sexes, n);
                idcSex = "" + (orgSex.equals("男")
                        ? "13579".charAt(n % 5)
                        : "02468".charAt(n % 5));

                vno = (idcSex.charAt(0) - '0') * N18VC[np++];

                idcValidation = "" + N18VL[vno % 11];

                String orgIdNum = idcRegionCode + idcBirthday + idcOO + idcSex + idcValidation;


                orgName = "" + surname[n % surname.length] +
                        givenName[n % givenName.length] +
                        (n % 3 != 0 ? givenName[Math.abs((n << 5 - n) % givenName.length)] : "");

                orgNation = getName(nations, n);

                // 生成不存在账户的情况
                String userId = ""+i;
                String userName = "U"+i;
                //默认 15岁以下儿童和65岁以上老人无账户 剩余随机无账户

                if(age.value < 15 || age.value > 65){
                    userId = "";
                    userName = "";
                }else{
                   if((int)(1+Math.random()*(10-1+1)) < 4){
                       userId = "";
                       userName = "";
                   }
                }

                Statement idcardStmt = conn.createStatement();
                String querySql = "SELECT id FROM bas_population_info pinfo WHERE pinfo.certificate_code='"+orgIdNum+"'";
                ResultSet queryRs = idcardStmt.executeQuery(querySql);
                boolean exists = false;
                while (queryRs.next()){
                    i--;
                    exists = true;
                    break;
                }
                if(exists){
                    continue;
                }
                queryRs.close();
                idcardStmt.close();
                Statement inStmt = conn.createStatement();
                String insertSql = "insert INTO bas_population_info(NAME,PASSENGER_TYPE,CRETIFICATE_TYPE,CERTIFICATE_CODE,USER_ID,USER_NAME,SEX,COUNTRY,BIRTHDAY,NATION,PHONENUMBER,EMAIL_ADDRESS,ADDRESS,wn_id,register_time) VALUES('"+orgName+"','"+customerType(age.value)+"','"+0+"','"+orgIdNum+"','"+userId+"','"+userName+"','"+orgSex+"','中国','"+orgBirthday+"','"+orgNation+"','"+ PhoneGenerator.getTel()+"','"+ UserInfoGenerator.getEmail(6,9)+"','"+orgRegion+"','"+ RandomUtils.getdByUUId()+"','"+RandomUtils.getStringRandomDate()+"') ";
                inStmt.execute(insertSql);



                //Put put = new Put(orgIdNum.getBytes());// 一个PUT代表一行数据，再NEW一个PUT表示第二行数据,每行一个唯一的ROWKEY，此处rowkey为put构造方法中传入的值

                /*HTableInterface table = pool.getTable("bas_population_info");
//                HTable table = new HTable(conf, "bas_population_info");
                Put put = new Put(orgIdNum.getBytes());// 一个PUT代表一行数据，再NEW一个PUT表示第二行数据,每行一个唯一的ROWKEY，此处rowkey为put构造方法中传入的值

                put.add("id".getBytes(), null, orgIdNum.getBytes());
                put.add("NAME".getBytes(), null, orgName.getBytes());
                put.add("PASSENGER_TYPE".getBytes(), null, customerType(age.value).getBytes());
                put.add("CRETIFICATE_TYPE".getBytes(), null, "0".getBytes());
                put.add("CERTIFICATE_CODE".getBytes(), null, orgIdNum.getBytes());
                put.add("USER_ID".getBytes(), null, userId.getBytes());
                put.add("SEX".getBytes(), null, userName.getBytes());
                put.add("COUNTRY".getBytes(), null, orgSex.getBytes());
                put.add("BIRTHDAY".getBytes(), null, "中国".getBytes());
                put.add("NATION".getBytes(), null, orgBirthday.getBytes());
                put.add("PHONENUMBER".getBytes(), null, orgNation.getBytes());
                put.add("ADDRESS".getBytes(), null, PhoneGenerator.getTel().getBytes());
                put.add("wn_id".getBytes(), null,  UserInfoGenerator.getEmail(6,9).getBytes());
                put.add("register_time".getBytes(), null, orgRegion.getBytes());
                table.put(put);*/
                count++;


              //  pw.write(sb.toString());

            }
            an = (bean.getCurrentThreadCpuTime() - cpuStart) / 1000;
            System.out.println("人口属性数据生成完成，共"+count+"条。");
            System.out.printf("Total count: %d, Total cost: %fus, AVG: %fus\n",
                    totalCount, an, an / totalCount);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private static class DP {
        public final String name;
        public final double percentage;
        public int count;

        public DP(String name, double percentage) { this(name, percentage, 0); }
        public DP(String name, double percentage, int count) {
            this.name = name;
            this.percentage = percentage;
            this.count = count;
        }
    }

    private static class DPRange extends DP {

        public final int min;
        public final int max;

        public DPRange(String name, double percentage, int min, int max) {
            super(name, percentage);
            this.min = min;
            this.max = max;
        }
    }

    private static class Nation extends DP {
        public Nation(String name, double percentage) {
            super(name, percentage);
        }

        public Nation(String name, double percentage, int count) {
            super(name, percentage, count);
        }
    }

    private static class Sex extends DP {

        public Sex(String name, double percentage) {
            super(name, percentage);
        }

        public Sex(String name, double percentage, int count) {
            super(name, percentage, count);
        }
    }

    private static class Age extends DPRange {

        public Age(String name, double percentage, int min, int max) {
            super(name, percentage, min, max);
        }
    }

    private static class ValueGetter {
        public int value;
    }

    private static Sex[] SEXES = {
            new Sex("男", 0.5127),
            new Sex("女", 0.4873),
    };
    private static Age[] AGES = {
            new Age("10~15", 0.05, 10,15),
            new Age("15~20", 0.05, 15,20),
            new Age("20~25", 0.15, 20,25),
            new Age("25~30", 0.15, 25,30),
            new Age("30~35", 0.15, 30,35),
            new Age("35~40", 0.15, 35,40),
            new Age("40~45", 0.1, 40,45),
            new Age("45~50", 0.1, 45,50),
            new Age("50~65", 0.05, 50,65),
            new Age("65及以上", 0.05, 65, 100)
    };
    private static Nation[] NATIONS = {
            new Nation("汉族", 0.915995),
            new Nation("壮族", 0.0127),
            new Nation("回族", 0.007943),
            new Nation("满族", 0.007794),
            new Nation("维吾尔族", 0.007555),
            new Nation("苗族", 0.007072),
            new Nation("彝族", 0.006538),
            new Nation("土家族", 0.006268),
            new Nation("藏族", 0.004713),
            new Nation("蒙古族", 0.004488),
            new Nation("侗族", 0.002161),
            new Nation("布依族", 0.002153),
            new Nation("瑶族", 0.002098),
            new Nation("白族", 0.001451),
            new Nation("朝鲜族", 0.001374),
            new Nation("哈尼族", 0.001246),
            new Nation("黎族", 0.001098),
            new Nation("哈萨克族", 0.001097),
            new Nation("傣族", 0.000946),
            new Nation("畲族", 0.000532),
            new Nation("傈僳族", 0.000527),
            new Nation("东乡族", 0.000466),
            new Nation("仡佬族", 0.000413),
            new Nation("拉祜族", 0.000365),
            new Nation("佤族", 0.000322),
            new Nation("水族", 0.000309),
            new Nation("纳西族", 0.000245),
            new Nation("羌族", 0.000232),
            new Nation("土族", 0.000217),
            new Nation("仫佬族", 0.000162),
            new Nation("锡伯族", 0.000143),
            new Nation("柯尔克孜族", 0.00014),
            new Nation("景颇族", 0.000111),
            new Nation("达斡尔族", 0.000099),
            new Nation("撒拉族", 0.000098),
            new Nation("布朗族", 0.00009),
            new Nation("毛南族", 0.000076),
            new Nation("塔吉克族", 0.000038),
            new Nation("普米族", 0.000032),
            new Nation("阿昌族", 0.00003),
            new Nation("怒族", 0.000028),
            new Nation("鄂温克族", 0.000023),
            new Nation("京族", 0.000021),
            new Nation("基诺族", 0.000017),
            new Nation("德昂族", 0.000015),
            new Nation("保安族", 0.000015),
            new Nation("俄罗斯族", 0.000012),
            new Nation("裕固族", 0.000011),
            new Nation("乌孜别克族", 0.000008),
            new Nation("门巴族", 0.000008),
            new Nation("鄂伦春族", 0.000006),
            new Nation("独龙族", 0.000005),
            new Nation("赫哲族", 0.000004),
            new Nation("高山族", 0.000003),
            new Nation("珞巴族", 0.000003),
            new Nation("塔塔尔族", 0.000003),
            //new Nation("外国人加入中国籍", 0.000001),
           // new GenerateUserInfo.Nation("其他未识别的民族", 0.000481)
    };
}
