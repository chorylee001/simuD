/* Copyright Beijing Wiseweb Technology Co., Ltd. */
package com.wiseweb.other.region.src.com.wiseweb.model.world.region;

import java.util.*;

/**
 * @author Teary Wang on 2016/12/3.
 */
public class WorldRegion {

	private RegionType type;
	private String[] shortNames;
	private String[] fullNames;

	private WorldRegion(RegionType type,
		String[] shortName, String[] fullName) {

		this.type = type;
		this.shortNames = shortName;
		this.fullNames = fullName;
	}

	public RegionType getType() { return type; }
	public String getShortName() { return shortNames[0]; }
	public String getFullName() { return fullNames[0]; }

	public String[] getShortNames() { return shortNames; }
	public String[] getFullNames() { return fullNames; }

	public static List<WorldRegion> fromType(RegionType type) {
		return regionType2WRs.get(type);
	}
	public static WorldRegion fromName(String name) {
		WorldRegion wr;
		return (wr = shortName2WR.get(name)) != null
			? wr : fullName2WR.get(name);
	}
	public static WorldRegion fromShortName(String shortName) {
		return shortName2WR.get(shortName);
	}
	public static WorldRegion fromFullName(String fullName) {
		return fullName2WR.get(fullName);
	}

	public enum RegionType {
		Asia, Africa, Europe, Oceania, NorthAmerica, SouthAmerica
	}

	private static Map<RegionType, List<WorldRegion>> regionType2WRs = new HashMap<>();
	private static Map<String, WorldRegion>           shortName2WR = new HashMap<>();
	private static Map<String, WorldRegion>           fullName2WR = new HashMap<>();

	private static final String[][] dAsia = {
		{ "\u4E2D\u56FD", "\u4E2D\u534E\u4EBA\u6C11\u5171\u548C\u56FD" },
		{ "\u8499\u53E4", "\u8499\u53E4\u56FD" },
		{ "\u671D\u9C9C", "\u671D\u9C9C\u6C11\u4E3B\u4E3B\u4E49\u4EBA\u6C11\u5171\u548C\u56FD" },
		{ "\u97E9\u56FD", "\u5927\u97E9\u6C11\u56FD" },
		{ "\u65E5\u672C", "\u65E5\u672C\u56FD" },
		{ "\u8D8A\u5357", "\u8D8A\u5357\u793E\u4F1A\u4E3B\u4E49\u5171\u548C\u56FD" },
		{ "\u8001\u631D", "\u8001\u631D\u4EBA\u6C11\u6C11\u4E3B\u5171\u548C\u56FD" },
		{ "\u67EC\u57D4\u5BE8", "\u67EC\u57D4\u5BE8\u738B\u56FD" },
		{ "\u7F05\u7538", "\u7F05\u7538\u8054\u90A6" },
		{ "\u6CF0\u56FD", "\u6CF0\u738B\u56FD" },
		{ "\u9A6C\u6765\u897F\u4E9A", "\u9A6C\u6765\u897F\u4E9A" },
		{ "\u65B0\u52A0\u5761", "\u65B0\u52A0\u5761\u5171\u548C\u56FD" },
		{ "\u6587\u83B1", "\u6587\u83B1\u8FBE\u9C81\u8428\u5170\u56FD" },
		{ "\u83F2\u5F8B\u5BBE", "\u83F2\u5F8B\u5BBE\u5171\u548C\u56FD" },
		{ "\u5370\u5EA6\u5C3C\u897F\u4E9A", "\u5370\u5EA6\u5C3C\u897F\u4E9A\u5171\u548C\u56FD" },
		{ "\u4E1C\u5E1D\u6C76", "\u4E1C\u5E1D\u6C76\u6C11\u4E3B\u5171\u548C\u56FD" },
		{ "\u5C3C\u6CCA\u5C14", "\u5C3C\u6CCA\u5C14\u8054\u90A6\u6C11\u4E3B\u5171\u548C\u56FD" },
		{ "\u4E0D\u4E39", "\u4E0D\u4E39\u738B\u56FD" },
		{ "\u5B5F\u52A0\u62C9\u56FD", "\u5B5F\u52A0\u62C9\u4EBA\u6C11\u5171\u548C\u56FD" },
		{ "\u5370\u5EA6", "\u5370\u5EA6\u5171\u548C\u56FD" },
		{ "\u65AF\u91CC\u5170\u5361", "\u65AF\u91CC\u5170\u5361\u6C11\u4E3B\u793E\u4F1A\u4E3B\u4E49\u5171\u548C\u56FD" },
		{ "\u9A6C\u5C14\u4EE3\u592B", "\u9A6C\u5C14\u4EE3\u592B\u5171\u548C\u56FD" },
		{ "\u5DF4\u57FA\u65AF\u5766", "\u5DF4\u57FA\u65AF\u5766\u4F0A\u65AF\u5170\u5171\u548C\u56FD" },
		{ "\u963F\u5BCC\u6C57", "\u963F\u5BCC\u6C57\u4F0A\u65AF\u5170\u56FD" },
		{ "\u4F0A\u6717", "\u4F0A\u6717\u4F0A\u65AF\u5170\u5171\u548C\u56FD" },
		{ "\u79D1\u5A01\u7279", "\u79D1\u5A01\u7279\u56FD" },
		{ "\u6C99\u7279\u963F\u62C9\u4F2F", "\u6C99\u7279\u963F\u62C9\u4F2F\u738B\u56FD" },
		{ "\u5DF4\u6797", "\u5DF4\u6797\u56FD" },
		{ "\u5361\u5854\u5C14", "\u5361\u5854\u5C14\u56FD" },
		{ "\u963F\u8054\u914B", "\u963F\u62C9\u4F2F\u8054\u5408\u914B\u957F\u56FD" },
		{ "\u963F\u66FC", "\u963F\u66FC\u82CF\u4E39\u56FD" },
		{ "\u4E5F\u95E8", "\u4E5F\u95E8\u5171\u548C\u56FD" },
		{ "\u4F0A\u62C9\u514B", "\u4F0A\u62C9\u514B\u5171\u548C\u56FD" },
		{ "\u53D9\u5229\u4E9A", "\u963F\u62C9\u4F2F\u53D9\u5229\u4E9A\u5171\u548C\u56FD" },
		{ "\u9ECE\u5DF4\u5AE9", "\u9ECE\u5DF4\u5AE9\u5171\u548C\u56FD" },
		{ "\u7EA6\u65E6", "\u7EA6\u65E6\u54C8\u5E0C\u59C6\u738B\u56FD" },
		{ "\u5DF4\u52D2\u65AF\u5766", "\u5DF4\u52D2\u65AF\u5766\u56FD" },
		{ "\u4EE5\u8272\u5217", "\u4EE5\u8272\u5217\u56FD" },
		{ "\u585E\u6D66\u8DEF\u65AF", "\u585E\u6D66\u8DEF\u65AF\u5171\u548C\u56FD" },
		{ "\u571F\u8033\u5176", "\u571F\u8033\u5176\u5171\u548C\u56FD" },
		{ "\u4E4C\u5179\u522B\u514B\u65AF\u5766", "\u4E4C\u5179\u522B\u514B\u65AF\u5766\u5171\u548C\u56FD" },
		{ "\u54C8\u8428\u514B\u65AF\u5766", "\u54C8\u8428\u514B\u65AF\u5766\u5171\u548C\u56FD" },
		{ "\u5409\u5C14\u5409\u65AF\u65AF\u5766", "\u5409\u5C14\u5409\u65AF\u5171\u548C\u56FD" },
		{ "\u5854\u5409\u514B\u65AF\u5766", "\u5854\u5409\u514B\u65AF\u5766\u5171\u548C\u56FD" },
		{ "\u4E9A\u7F8E\u5C3C\u4E9A", "\u4E9A\u7F8E\u5C3C\u4E9A\u5171\u548C\u56FD" },
		{ "\u571F\u5E93\u66FC\u65AF\u5766", "\u571F\u5E93\u66FC\u65AF\u5766" },
		{ "\u963F\u585E\u62DC\u7586", "\u963F\u585E\u62DC\u7586\u5171\u548C\u56FD" },
		{ "\u683C\u9C81\u5409\u4E9A", "\u683C\u9C81\u5409\u4E9A" }
	};
	private static final String[][] dAfrica = {
		{ "\u57C3\u53CA", "\u963F\u62C9\u4F2F\u57C3\u53CA\u5171\u548C\u56FD" },
		{ "\u5229\u6BD4\u4E9A", "\u5927\u963F\u62C9\u4F2F\u5229\u6BD4\u4E9A\u4EBA\u6C11\u793E\u4F1A\u4E3B\u4E49\u6C11\u4F17\u56FD" },
		{ "\u7A81\u5C3C\u65AF", "\u7A81\u5C3C\u65AF\u5171\u548C\u56FD" },
		{ "\u963F\u5C14\u53CA\u5229\u4E9A", "\u963F\u5C14\u53CA\u5229\u4E9A\u6C11\u4E3B\u4EBA\u6C11\u5171\u548C\u56FD" },
		{ "\u6469\u6D1B\u54E5", "\u6469\u6D1B\u54E5\u738B\u56FD" },
		{ "\u6BDB\u91CC\u5854\u5C3C\u4E9A", "\u6BDB\u91CC\u5854\u5C3C\u4E9A\u4F0A\u65AF\u5170\u5171\u548C\u56FD" },
		{ "\u585E\u5185\u52A0\u5C14", "\u585E\u5185\u52A0\u5C14\u5171\u548C\u56FD" },
		{ "\u5188\u6BD4\u4E9A", "\u5188\u6BD4\u4E9A\u5171\u548C\u56FD" },
		{ "\u9A6C\u91CC", "\u9A6C\u91CC\u5171\u548C\u56FD" },
		{ "\u5E03\u57FA\u7EB3\u6CD5\u7D22", "\u5E03\u57FA\u7EB3\u6CD5\u7D22" },
		{ "\u4F5B\u5F97\u89D2", "\u4F5B\u5F97\u89D2\u5171\u548C\u56FD" },
		{ "\u51E0\u5185\u4E9A\u6BD4\u7ECD", "\u51E0\u5185\u4E9A\u6BD4\u7ECD\u5171\u548C\u56FD" },
		{ "\u51E0\u5185\u4E9A", "\u51E0\u5185\u4E9A\u5171\u548C\u56FD" },
		{ "\u585E\u62C9\u91CC\u6602", "\u585E\u62C9\u91CC\u6602\u5171\u548C\u56FD" },
		{ "\u5229\u6BD4\u91CC\u4E9A", "\u5229\u6BD4\u91CC\u4E9A\u5171\u548C\u56FD" },
		{ "\u79D1\u7279\u8FEA\u74E6", "\u79D1\u7279\u8FEA\u74E6\u5171\u548C\u56FD" },
		{ "\u52A0\u7EB3", "\u52A0\u7EB3\u5171\u548C\u56FD" },
		{ "\u591A\u54E5", "\u591A\u54E5\u5171\u548C\u56FD" },
		{ "\u8D1D\u5B81", "\u8D1D\u5B81\u5171\u548C\u56FD" },
		{ "\u5C3C\u65E5\u5C14", "\u5C3C\u65E5\u5C14\u5171\u548C\u56FD" },
		{ "\u5C3C\u65E5\u5229\u4E9A", "\u5C3C\u65E5\u5229\u4E9A\u8054\u90A6\u5171\u548C\u56FD" },
		{ "\u5580\u9EA6\u9686", "\u5580\u9EA6\u9686\u5171\u548C\u56FD" },
		{ "\u8D64\u9053\u51E0\u5185\u4E9A", "\u8D64\u9053\u51E0\u5185\u4E9A\u5171\u548C\u56FD" },
		{ "\u4E4D\u5F97", "\u4E4D\u5F97\u5171\u548C\u56FD" },
		{ "\u4E2D\u975E", "\u4E2D\u975E\u5171\u548C\u56FD" },
		{ "\u82CF\u4E39", "\u82CF\u4E39\u5171\u548C\u56FD" },
		{ "\u57C3\u585E\u4FC4\u6BD4\u4E9A", "\u57C3\u585E\u4FC4\u6BD4\u4E9A\u8054\u90A6\u6C11\u4E3B\u5171\u548C\u56FD" },
		{ "\u5409\u5E03\u63D0", "\u5409\u5E03\u63D0\u5171\u548C\u56FD" },
		{ "\u7D22\u9A6C\u91CC", "\u7D22\u9A6C\u91CC\u5171\u548C\u56FD" },
		{ "\u80AF\u5C3C\u4E9A", "\u80AF\u5C3C\u4E9A\u5171\u548C\u56FD" },
		{ "\u4E4C\u5E72\u8FBE", "\u4E4C\u5E72\u8FBE\u5171\u548C\u56FD" },
		{ "\u5766\u6851\u5C3C\u4E9A", "\u5766\u6851\u5C3C\u4E9A\u8054\u5408\u5171\u548C\u56FD" },
		{ "\u5362\u65FA\u8FBE", "\u5362\u65FA\u8FBE\u5171\u548C\u56FD" },
		{ "\u5E03\u9686\u8FEA", "\u5E03\u9686\u8FEA\u5171\u548C\u56FD" },
		{ "\u521A\u679C(\u91D1)/\u6C11\u4E3B\u521A\u679C", "\u521A\u679C\u6C11\u4E3B\u5171\u548C\u56FD" },
		{ "\u521A\u679C/\u521A\u679C(\u5E03)", "\u521A\u679C\u5171\u548C\u56FD" },
		{ "\u52A0\u84EC", "\u52A0\u84EC\u5171\u548C\u56FD" },
		{ "\u5723\u591A\u7F8E\u548C\u666E\u6797\u897F\u6BD4", "\u5723\u591A\u7F8E\u548C\u666E\u6797\u897F\u6BD4\u6C11\u4E3B\u5171\u548C\u56FD" },
		{ "\u5B89\u54E5\u62C9", "\u5B89\u54E5\u62C9\u5171\u548C\u56FD" },
		{ "\u8D5E\u6BD4\u4E9A", "\u8D5E\u6BD4\u4E9A\u5171\u548C\u56FD" },
		{ "\u9A6C\u62C9\u7EF4", "\u9A6C\u62C9\u7EF4\u5171\u548C\u56FD" },
		{ "\u83AB\u6851\u6BD4\u514B", "\u83AB\u6851\u6BD4\u514B\u5171\u548C\u56FD" },
		{ "\u79D1\u6469\u7F57", "\u79D1\u6469\u7F57\u4F0A\u65AF\u5170\u8054\u90A6\u5171\u548C\u56FD" },
		{ "\u9A6C\u8FBE\u52A0\u65AF\u52A0", "\u9A6C\u8FBE\u52A0\u65AF\u52A0\u5171\u548C\u56FD" },
		{ "\u585E\u820C\u5C14", "\u585E\u820C\u5C14\u5171\u548C\u56FD" },
		{ "\u6BDB\u91CC\u6C42\u65AF", "\u6BDB\u91CC\u6C42\u65AF\u5171\u548C\u56FD" },
		{ "\u6D25\u5DF4\u5E03\u97E6", "\u6D25\u5DF4\u5E03\u97E6\u5171\u548C\u56FD" },
		{ "\u535A\u8328\u74E6\u7EB3", "\u535A\u8328\u74E6\u7EB3\u5171\u548C\u56FD" },
		{ "\u7EB3\u7C73\u6BD4\u4E9A", "\u7EB3\u7C73\u6BD4\u4E9A\u5171\u548C\u56FD" },
		{ "\u5357\u975E", "\u5357\u975E\u5171\u548C\u56FD" },
		{ "\u65AF\u5A01\u58EB\u5170", "\u65AF\u5A01\u58EB\u5170\u738B\u56FD" },
		{ "\u83B1\u7D22\u6258", "\u83B1\u7D22\u6258\u738B\u56FD" },
		{ "\u5384\u7ACB\u7279\u91CC\u4E9A", "\u5384\u7ACB\u7279\u91CC\u4E9A\u56FD" }
	};
	private static final String[][] dEurope = {
		{ "\u51B0\u5C9B", "\u51B0\u5C9B\u5171\u548C\u56FD" },
		{ "\u4E39\u9EA6", "\u4E39\u9EA6\u738B\u56FD" },
		{ "\u632A\u5A01", "\u632A\u5A01\u738B\u56FD" },
		{ "\u745E\u5178", "\u745E\u5178\u738B\u56FD" },
		{ "\u82AC\u5170", "\u82AC\u5170\u5171\u548C\u56FD" },
		{ "\u4FC4\u7F57\u65AF", "\u4FC4\u7F57\u65AF/\u4FC4\u7F57\u65AF\u8054\u90A6" },
		{ "\u4E4C\u514B\u5170", "\u4E4C\u514B\u5170" },
		{ "\u767D\u4FC4\u7F57\u65AF", "\u767D\u4FC4\u7F57\u65AF\u5171\u548C\u56FD" },
		{ "\u6469\u5C14\u591A\u74E6", "\u6469\u5C14\u591A\u74E6\u5171\u548C\u56FD" },
		{ "\u7ACB\u9676\u5B9B", "\u7ACB\u9676\u5B9B\u5171\u548C\u56FD" },
		{ "\u7231\u6C99\u5C3C\u4E9A", "\u7231\u6C99\u5C3C\u4E9A\u5171\u548C\u56FD" },
		{ "\u62C9\u8131\u7EF4\u4E9A", "\u62C9\u8131\u7EF4\u4E9A\u5171\u548C\u56FD" },
		{ "\u6CE2\u5170", "\u6CE2\u5170\u5171\u548C\u56FD" },
		{ "\u6377\u514B", "\u6377\u514B\u5171\u548C\u56FD" },
		{ "\u5308\u7259\u5229", "\u5308\u7259\u5229\u5171\u548C\u56FD" },
		{ "\u5FB7\u56FD", "\u5FB7\u610F\u5FD7\u8054\u90A6\u5171\u548C\u56FD" },
		{ "\u5965\u5730\u5229", "\u5965\u5730\u5229\u5171\u548C\u56FD" },
		{ "\u5217\u652F\u6566\u58EB\u767B", "\u5217\u652F\u6566\u58EB\u767B\u516C\u56FD" },
		{ "\u745E\u58EB", "\u745E\u58EB\u8054\u90A6" },
		{ "\u8377\u5170", "\u8377\u5170\u738B\u56FD" },
		{ "\u6BD4\u5229\u65F6", "\u6BD4\u5229\u65F6\u738B\u56FD" },
		{ "\u5362\u68EE\u5821", "\u5362\u68EE\u5821\u5927\u516C\u56FD" },
		{ "\u82F1\u56FD", "\u5927\u4E0D\u5217\u98A0\u53CA\u5317\u7231\u5C14\u5170\u8054\u5408\u738B\u56FD" },
		{ "\u7231\u5C14\u5170", "\u7231\u5C14\u5170\u5171\u548C\u56FD" },
		{ "\u6CD5\u56FD", "\u6CD5\u5170\u897F\u5171\u548C\u56FD" },
		{ "\u6469\u7EB3\u54E5", "\u6469\u7EB3\u54E5\u516C\u56FD" },
		{ "\u5B89\u9053\u5C14", "\u5B89\u9053\u5C14\u516C\u56FD" },
		{ "\u897F\u73ED\u7259", "\u897F\u73ED\u7259" },
		{ "\u8461\u8404\u7259", "\u8461\u8404\u7259\u5171\u548C\u56FD" },
		{ "\u610F\u5927\u5229", "\u610F\u5927\u5229\u5171\u548C\u56FD" },
		{ "\u68B5\u8482\u5188", "\u68B5\u8482\u5188\u57CE\u56FD" },
		{ "\u5723\u9A6C\u529B\u8BFA", "\u5723\u9A6C\u529B\u8BFA\u5171\u548C\u56FD" },
		{ "\u9A6C\u8033\u4ED6", "\u9A6C\u8033\u4ED6\u5171\u548C\u56FD" },
		{ "\u514B\u7F57\u5730\u4E9A", "\u514B\u7F57\u5730\u4E9A\u5171\u548C\u56FD" },
		{ "\u65AF\u6D1B\u4F10\u514B", "\u65AF\u6D1B\u4F10\u514B\u5171\u548C\u56FD" },
		{ "\u65AF\u6D1B\u6587\u5C3C\u4E9A", "\u65AF\u6D1B\u6587\u5C3C\u4E9A\u5171\u548C\u56FD" },
		{ "\u6CE2\u9ED1", "\u6CE2\u65AF\u5C3C\u4E9A\u548C\u9ED1\u585E\u54E5\u7EF4\u90A3\u5171\u548C\u56FD" },
		{ "\u9A6C\u5176\u987F", "\u9A6C\u5176\u987F\u5171\u548C\u56FD" },
		{ "\u585E\u5C14\u7EF4\u4E9A", "\u585E\u5C14\u7EF4\u4E9A\u5171\u548C\u56FD" },
		{ "\u9ED1\u5C71", "\u9ED1\u5C71\u5171\u548C\u56FD" },
		{ "\u79D1\u7D22\u6C83", "\u79D1\u7D22\u6C83\u56FD" },
		{ "\u7F57\u9A6C\u5C3C\u4E9A", "\u7F57\u9A6C\u5C3C\u4E9A" },
		{ "\u4FDD\u52A0\u5229\u4E9A", "\u4FDD\u52A0\u5229\u4E9A\u5171\u548C\u56FD" },
		{ "\u963F\u5C14\u5DF4\u5C3C\u4E9A", "\u963F\u5C14\u5DF4\u5C3C\u4E9A\u5171\u548C\u56FD" },
		{ "\u5E0C\u814A", "\u5E0C\u814A\u5171\u548C\u56FD" },
	};
	private static final String[][] dOceania = {
		{ "\u6FB3\u5927\u5229\u4E9A", "\u6FB3\u5927\u5229\u4E9A\u8054\u90A6" },
		{ "\u65B0\u897F\u5170", "\u65B0\u897F\u5170" },
		{ "\u5DF4\u5E03\u4E9A\u65B0\u51E0\u5185\u4E9A", "\u5DF4\u5E03\u4E9A\u65B0\u51E0\u5185\u4E9A\u72EC\u7ACB\u56FD" },
		{ "\u6240\u7F57\u95E8\u7FA4\u5C9B", "\u6240\u7F57\u95E8\u7FA4\u5C9B" },
		{ "\u74E6\u52AA\u963F\u56FE", "\u74E6\u52AA\u963F\u56FE\u5171\u548C\u56FD" },
		{ "\u6590\u6D4E\u7FA4\u5C9B/\u6590\u6D4E", "\u6590\u6D4E\u7FA4\u5C9B\u5171\u548C\u56FD" },
		{ "\u57FA\u91CC\u5DF4\u65AF", "\u57FA\u91CC\u5DF4\u65AF\u5171\u548C\u56FD" },
		{ "\u7459\u9C81", "\u7459\u9C81\u5171\u548C\u56FD" },
		{ "\u5BC6\u514B\u7F57\u5C3C\u897F\u4E9A", "\u5BC6\u514B\u7F57\u5C3C\u897F\u4E9A\u8054\u90A6" },
		{ "\u9A6C\u7ECD\u5C14\u7FA4\u5C9B", "\u9A6C\u7ECD\u5C14\u7FA4\u5C9B\u5171\u548C\u56FD" },
		{ "\u56FE\u74E6\u5362", "\u56FE\u74E6\u5362" },
		{ "\u8428\u6469\u4E9A/\u897F\u8428\u6469\u4E9A", "\u8428\u6469\u4E9A\u72EC\u7ACB\u56FD" },
		{ "\u7EBD\u57C3", "\u7EBD\u57C3" },
		{ "\u5E15\u52B3", "\u5E15\u52B3\u5171\u548C\u56FD" },
		{ "\u6C64\u52A0", "\u6C64\u52A0\u738B\u56FD " }
	};
	private static final String[][] dNorthAmerica = {
		{ "\u52A0\u62FF\u5927", "\u52A0\u62FF\u5927" },
		{ "\u7F8E\u56FD", "\u7F8E\u5229\u575A\u5408\u4F17\u56FD" },
		{ "\u58A8\u897F\u54E5", "\u58A8\u897F\u54E5\u5408\u4F17\u56FD" },
		{ "\u5371\u5730\u9A6C\u62C9", "\u5371\u5730\u9A6C\u62C9\u5171\u548C\u56FD" },
		{ "\u4F2F\u5229\u5179", "\u4F2F\u5229\u5179" },
		{ "\u8428\u5C14\u74E6\u591A", "\u8428\u5C14\u74E6\u591A\u5171\u548C\u56FD" },
		{ "\u6D2A\u90FD\u62C9\u65AF", "\u6D2A\u90FD\u62C9\u65AF\u5171\u548C\u56FD" },
		{ "\u5C3C\u52A0\u62C9\u74DC", "\u5C3C\u52A0\u62C9\u74DC\u5171\u548C\u56FD" },
		{ "\u54E5\u65AF\u8FBE\u9ECE\u52A0", "\u54E5\u65AF\u8FBE\u9ECE\u52A0\u5171\u548C\u56FD" },
		{ "\u5DF4\u62FF\u9A6C", "\u5DF4\u62FF\u9A6C\u5171\u548C\u56FD" },
		{ "\u5DF4\u54C8\u9A6C", "\u5DF4\u54C8\u9A6C\u56FD" },
		{ "\u53E4\u5DF4", "\u53E4\u5DF4\u5171\u548C\u56FD" },
		{ "\u7259\u4E70\u52A0", "\u7259\u4E70\u52A0" },
		{ "\u6D77\u5730", "\u6D77\u5730\u5171\u548C\u56FD" },
		{ "\u591A\u7C73\u5C3C\u52A0\u5171\u548C\u56FD", "\u591A\u7C73\u5C3C\u52A0\u5171\u548C\u56FD" },
		{ "\u5723\u57FA\u8328\u548C\u5C3C\u7EF4\u65AF", "\u5723\u57FA\u8328\u548C\u5C3C\u7EF4\u65AF\u8054\u90A6" },
		{ "\u5B89\u63D0\u74DC\u548C\u5DF4\u5E03\u8FBE", "\u5B89\u63D0\u74DC\u548C\u5DF4\u5E03\u8FBE" },
		{ "\u591A\u7C73\u5C3C\u514B\u56FD", "\u591A\u7C73\u5C3C\u514B\u56FD" },
		{ "\u5723\u5362\u897F\u4E9A", "\u5723\u5362\u897F\u4E9A" },
		{ "\u5723\u6587\u68EE\u7279\u548C\u683C\u6797\u7EB3\u4E01\u65AF", "\u5723\u6587\u68EE\u7279\u548C\u683C\u6797\u7EB3\u4E01\u65AF" },
		{ "\u5DF4\u5DF4\u591A\u65AF", "\u5DF4\u5DF4\u591A\u65AF" },
		{ "\u683C\u6797\u7EB3\u8FBE", "\u683C\u6797\u7EB3\u8FBE" },
		{ "\u7279\u7ACB\u5C3C\u8FBE\u548C\u591A\u5DF4\u54E5", "\u7279\u7ACB\u5C3C\u8FBE\u548C\u591A\u5DF4\u54E5\u5171\u548C\u56FD" }
	};
	private static final String[][] dSouthAmerica = {
		{ "\u54E5\u4F26\u6BD4\u4E9A", "\u54E5\u4F26\u6BD4\u4E9A\u5171\u548C\u56FD" },
		{ "\u59D4\u5185\u745E\u62C9", "\u59D4\u5185\u745E\u62C9\u73BB\u5229\u74E6\u5C14\u5171\u548C\u56FD" },
		{ "\u572D\u4E9A\u90A3", "\u572D\u4E9A\u90A3\u5408\u4F5C\u5171\u548C\u56FD" },
		{ "\u82CF\u91CC\u5357", "\u82CF\u91CC\u5357\u5171\u548C\u56FD" },
		{ "\u5384\u74DC\u591A\u5C14", "\u5384\u74DC\u591A\u5C14\u5171\u548C\u56FD" },
		{ "\u79D8\u9C81", "\u79D8\u9C81\u5171\u548C\u56FD" },
		{ "\u5DF4\u897F", "\u5DF4\u897F\u8054\u90A6\u5171\u548C\u56FD" },
		{ "\u73BB\u5229\u7EF4\u4E9A", "\u73BB\u5229\u7EF4\u4E9A\u5171\u548C\u56FD/\u73BB\u5229\u7EF4\u4E9A\u591A\u6C11\u65CF\u5171\u548C\u56FD" },
		{ "\u667A\u5229", "\u667A\u5229\u5171\u548C\u56FD" },
		{ "\u963F\u6839\u5EF7", "\u963F\u6839\u5EF7\u5171\u548C\u56FD" },
		{ "\u5DF4\u62C9\u572D", "\u5DF4\u62C9\u572D\u5171\u548C\u56FD" },
		{ "\u4E4C\u62C9\u572D", "\u4E4C\u62C9\u572D\u4E1C\u5CB8\u5171\u548C\u56FD" }
	};
	static {
		Object[][] obj = {
			{ RegionType.Asia, dAsia },
			{ RegionType.Africa, dAfrica },
			{ RegionType.Europe, dEurope },
			{ RegionType.Oceania, dOceania },
			{ RegionType.NorthAmerica, dNorthAmerica },
			{ RegionType.SouthAmerica, dSouthAmerica }
		};

		for (int i = 0; i < obj.length; i++) {
			RegionType rt = (RegionType) obj[i][0];
			String[][] d = (String[][]) obj[i][1];

			List<WorldRegion> list = regionType2WRs.get(rt);
			if (list == null) {
				list = new ArrayList<>(d.length);
				regionType2WRs.put(rt, list);
			}

			for (int x = 0; x < d.length; x++) {

				String[] shortNames = d[x][0].split("/");
				String[] fullNames = d[x][1].split("/");

				WorldRegion wr = new WorldRegion(rt, shortNames, fullNames);
				list.add(wr);

				for (int n = 0; n < shortNames.length; n++)
					shortName2WR.put(shortNames[n], wr);

				for (int n = 0; n < fullNames.length; n++)
					fullName2WR.put(fullNames[n], wr);
			}
		}

		// Make List Unmodifiable
		for (Map.Entry<RegionType, List<WorldRegion>> e : regionType2WRs.entrySet()) {
			regionType2WRs.put(e.getKey(),
				Collections.unmodifiableList(e.getValue()));
		}
	}
}
