/* Copyright Beijing Wiseweb Technology Co., Ltd. */
package com.wiseweb.other.data.model;

import com.wiseweb.other.character.src.com.wiseweb.model.character.HCharTable;
import com.wiseweb.other.character.src.com.wiseweb.model.character.Name;
import com.wiseweb.other.region.src.com.wiseweb.model.gb.region.GbRegion;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.*;

/**
 * @author TearyWang on 2017/1/16.
 */
public class DemographicGenerator {

	public static final String filePath = "D:\\Demographic.csv";
	private static final int COUNT = 200000; //4000000
	private static final char SPLITTER = '\t';

	private static final int[] N18VC = { 7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2 };
	private static final char[] N18VL = "10X98765432".toCharArray();

	private static final GbRegion[] regionTable = (GbRegion[])
		GbRegion.countyRegions().toArray(new GbRegion[0]);

	private static final int NowYear = Calendar.getInstance().get(Calendar.YEAR);

	private static final String[] surname = Name.BookOfFamilyNames.getTable();
	private static final char[] givenName = HCharTable.HfShfStokeLE10.getTable();

	private static Map<String, String> map = new HashMap<>();

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

		n = Math.abs(n) % list.size();
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

		n = Math.abs(n) % list.size();
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

	public static void main(String[] args) {

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

		StringBuilder sb = new StringBuilder(512);

		try {

			ThreadMXBean bean = ManagementFactory.getThreadMXBean();
			long cpuStart;
			double an;

			File file = new File(filePath);
			PrintWriter pw = new PrintWriter(file, "UTF-8");

			sb
				.append("姓名|").append(SPLITTER)
				.append("类别|").append(SPLITTER)
				.append("证件类型|").append(SPLITTER)
				.append("证件号码|").append(SPLITTER)
				.append("用户ID|").append(SPLITTER)
				.append("用户名|").append(SPLITTER)
				.append("性别|").append(SPLITTER)
				.append("国家地区|").append(SPLITTER)
				.append("出生日期|").append(SPLITTER)
				.append("民族|").append(SPLITTER)
				.append("手机号|").append(SPLITTER)
				.append("邮箱地址|").append(SPLITTER)
				.append("邮编|").append(SPLITTER)
				.append("原始领证地\r\n");
			pw.write(sb.toString());

			Random random = new Random();

			cpuStart = bean.getCurrentThreadCpuTime();

			for (int i = 0; i < COUNT;) {

				int n = Math.abs(random.nextInt());

				sb.delete(0, sb.length());
				vno = 0;

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
				// Repeated, ignored.
				if (map.containsKey(orgIdNum)) {
					continue;
				}else{
					i++;
					totalCount++;
				}

				orgName = "" + surname[n % surname.length] +
					givenName[n % givenName.length] +
					(n % 3 != 0 ? givenName[Math.abs((n << 5 - n) % givenName.length)] : "");

				orgNation = getName(nations, n);

				sb
					.append(orgName).append("|").append(SPLITTER)
					.append("成人").append("|").append(SPLITTER)
					.append("0").append("|").append(SPLITTER)
					.append(orgIdNum).append("|").append(SPLITTER)
					.append("").append("|").append(SPLITTER)
					.append("").append("|").append(SPLITTER)
					.append(orgSex).append("|").append(SPLITTER)
					.append("中国").append("|").append(SPLITTER)
					.append(orgBirthday).append("|").append(SPLITTER)
					.append(orgNation).append("|").append(SPLITTER)
					.append("|").append(SPLITTER)
					.append("|").append(SPLITTER)
					.append("|").append(SPLITTER)
					.append(orgRegion).append("\r\n");



				map.put(orgIdNum,null);

				pw.write(sb.toString());

			}

			an = (bean.getCurrentThreadCpuTime() - cpuStart) / 1000;

			System.out.printf("Total count: %d, Total cost: %fus, AVG: %fus\n",
				totalCount, an, an / totalCount);

			pw.flush();
			pw.close();
		}
		catch (IOException e) {
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

	// http://www.stats.gov.cn/tjsj/tjgb/rkpcgb/qgrkpcgb/201104/t20110428_30327.html
	private static Sex[] SEXES = {
		new Sex("男", 0.5127),
		new Sex("女", 0.4873),
	};
	private static Age[] AGES = {
		new Age("10~59", 0.8014, 10, 59),
		new Age("60及以上", 0.1986, 60, 100)
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
		new Nation("其他未识别的民族", 0.000481)
	};
}
