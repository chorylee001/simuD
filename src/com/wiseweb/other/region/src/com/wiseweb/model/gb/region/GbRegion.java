package com.wiseweb.other.region.src.com.wiseweb.model.gb.region;

import java.util.*;

/** GB/T 2260-2007 Region.
 *  Created by Teary on 2015/4/3. */
public class GbRegion {

	public RegionType getRegionType() { return regionType; }

	public String getCode() { return this.code; }
	public String getName() { return this.name; }
	public String getFullName() { return this.fullName; }
	public String getPostcode() { return this.postcode; }
	public String getTelPrefix() { return this.telPrefix; }

	public boolean getIsEmpty() {

		return this.code == null ||
			this.code.length() == 0;
	}

	public String getProvinceCode() {

		return code == null || code.length() < 2
			? "" : code.substring(0, 2);
	}
	public String getCityCode() {

		return code == null || code.length() < 4
			? "" : code.substring(0, 4);
	}
	public String getCountyCode() {

		return code == null || code.length() < 6
			? "" : code.substring(0, 6);
	}
	public String getParentCode() {
		return code == null ?
			"" : code.substring(0, code.length() - 2);
	}

	public String getShortFullName() {
		synchronized (this) {
			if (shortFullName == null) {
				int i = 0;
				String[] str = new String[3];
				GbRegion parent = this;
				do {
					if (isIgnoredShortName(parent.getName()))
						continue;

					str[i++] = parent.getRegionType() == RegionType.PROVINCE
						? parent.getKeyName() : parent.getName();
				}
				while (!(parent = query(parent.getParentCode())).getIsEmpty());

				StringBuilder sb = new StringBuilder();
				while (--i >= 0)
					sb.append(str[i]);

				shortFullName = sb.toString();
			}
			return shortFullName;
		}
	}
	private boolean isIgnoredShortName(String name) {
		return name.equals("\u5E02\u8F96\u533A") || name.equals("\u53BF") ||
			name.equals("\u7701\u76F4\u8F96\u53BF\u7EA7\u884C\u653F\u533A") ||
			name.equals("\u81EA\u6CBB\u533A\u76F4\u8F96\u53BF\u7EA7\u884C\u653F\u533A\u5212");
	}

	public GbRegion getProvince() { return query(getProvinceCode()); }
	public GbRegion getCity() { return query(getCityCode()); }

	public String getKeyName() { return keyName; }
	public String getRegionKey() { return regionKey; }
	public String[] getNationKeys() {
		return nationKeys == null
			? EMPTY_STR_ARRAY : nationKeys.toArray(new String[0]);
	}

	public List<GbRegion> getChildren() {
		List<GbRegion> r = _regionIndexedByParentCode.get(code);
		return (r == null) ? EmptyList : r;
	}

	public enum RegionType {
		OTHER(3),
		PROVINCE(2),
		CITY(1),
		COUNTY(0);

		private int value;
		RegionType(int value) { this.value = value; }

		public int getValue() { return this.value; }
	}

	private GbRegion(RegionType regionType,
		String code, String name, String fullName,
		String postcode, String telPrefix) {

		this.regionType = regionType;
		this.code = code;
		this.name = name;
		this.fullName = fullName;
		this.postcode = postcode;
		this.telPrefix = telPrefix;

		initEx();
	}

	private RegionType regionType;
	private String code;
	private String name;
	private String fullName;
	private String postcode;
	private String telPrefix;

	private String keyName;
	private String regionKey;
	private List<String> nationKeys;
	private transient String shortFullName;

	private static final List<GbRegion> EmptyList =
		Collections.unmodifiableList(new ArrayList<GbRegion>());
	private static final List<String> EmptyStringList =
		Collections.unmodifiableList(new ArrayList<String>());
	private static final String EMPTY_STRING = "";
	private static final String[] EMPTY_STR_ARRAY = {};

	private void initEx() {

		if (this.name == null || this.name.isEmpty())
			return;

		boolean found = false;
		String keyName = this.name;
		String regionTypeName = null;
		String nation = null;
		List<String> qNations = new ArrayList<>(10);
		int oldLength;

		for (int i = REGION_TYPES.length - 1; !found && i >= 0; --i) {

			for (int j = 0; j < REGION_TYPES[i].length; j++) {
				if (j > 1 && keyName.startsWith(REGION_TYPES[i][j])) {
					keyName = "";
					found = true;
					break;
				}
				if (keyName.endsWith(REGION_TYPES[i][j])) {
					regionTypeName = REGION_TYPES[i][j];
					if (keyName.length() - regionTypeName.length() >= 2)
						keyName = keyName.substring(0,
							keyName.length() - regionTypeName.length());
					found = true;
					break;
				}
			}
		}

		if (keyName.length() > 2) {
			do {
				oldLength = keyName.length();

				for (int i = 0; i < NATIONS.length; i++) {
					nation = NATIONS[i];
					if (keyName.endsWith(nation)) {
						qNations.add(0, nation);
						keyName = keyName.substring(0, keyName.length() - nation.length());
						break;
					}

					if (nation.length() > 2) {
						nation = nation.substring(0, nation.length() - 1);
						if (keyName.endsWith(nation)) {
							if (keyName.length() - nation.length() >= 2) {
								qNations.add(0, nation);

								keyName = keyName.substring(0, keyName.length() - nation.length());
							}
							break;
						}
					}
				}
			}
			while (oldLength > keyName.length() && keyName.length() > 0);
		}

		this.keyName = keyName;
		this.regionKey = regionTypeName;
		this.nationKeys = qNations;
	}

	private static final String[][] REGION_TYPES = {
		// Empty array to hold the array.
		{},
		// Length 1
		{ "\u7701", "\u5E02", "\u53BF", "\u533A", "\u65D7", "\u5DDE" },
		// Length 2
		{ "\u5DE6\u65D7", "\u53F3\u65D7", "\u4E2D\u65D7", "\u524D\u65D7", "\u540E\u65D7", "\u65B0\u533A", "\u7279\u533A", "\u7FA4\u5C9B", "\u5730\u533A" },
		// Length 3
		{ "\u76F4\u8F96\u5E02", "\u76F4\u8F96\u53BF", "\u76F4\u8F96\u4E61", "\u76F4\u8F96\u9547", "\u81EA\u6CBB\u65D7", "\u8054\u5408\u65D7", "\u884C\u653F\u533A", "\u81EA\u6CBB\u533A", "\u81EA\u6CBB\u53BF", "\u81EA\u6CBB\u5DDE", "\u81EA\u6CBB\u4E61", "\u81EA\u6CBB\u9547" },
		// Length 4
		{},
		// Length 5
		{ "\u7279\u522B\u884C\u653F\u533A" },
	};

	private static final String[] NATIONS = {
		"\u6C49\u65CF", "\u58EE\u65CF", "\u6EE1\u65CF", "\u56DE\u65CF", "\u82D7\u65CF", "\u7EF4\u543E\u5C14\u65CF",
		"\u571F\u5BB6\u65CF", "\u5F5D\u65CF", "\u8499\u53E4\u65CF", "\u85CF\u65CF", "\u5E03\u4F9D\u65CF", "\u4F97\u65CF",
		"\u7476\u65CF", "\u671D\u9C9C\u65CF", "\u767D\u65CF", "\u54C8\u5C3C\u65CF", "\u54C8\u8428\u514B\u65CF", "\u9ECE\u65CF",
		"\u50A3\u65CF", "\u7572\u65CF", "\u5088\u50F3\u65CF", "\u4EE1\u4F6C\u65CF", "\u4E1C\u4E61\u65CF", "\u9AD8\u5C71\u65CF",
		"\u62C9\u795C\u65CF", "\u6C34\u65CF", "\u4F64\u65CF", "\u7EB3\u897F\u65CF", "\u7F8C\u65CF", "\u571F\u65CF", "\u4EEB\u4F6C\u65CF",
		"\u9521\u4F2F\u65CF", "\u67EF\u5C14\u514B\u5B5C\u65CF", "\u8FBE\u65A1\u5C14\u65CF", "\u666F\u9887\u65CF", "\u6BDB\u5357\u65CF", "\u6492\u62C9\u65CF",
		"\u5E03\u6717\u65CF", "\u5854\u5409\u514B\u65CF", "\u963F\u660C\u65CF", "\u666E\u7C73\u65CF", "\u9102\u6E29\u514B\u65CF", "\u6012\u65CF",
		"\u4EAC\u65CF", "\u57FA\u8BFA\u65CF", "\u5FB7\u6602\u65CF", "\u4FDD\u5B89\u65CF", "\u4FC4\u7F57\u65AF\u65CF", "\u88D5\u56FA\u65CF",
		"\u4E4C\u5179\u522B\u514B\u65CF", "\u95E8\u5DF4\u65CF", "\u9102\u4F26\u6625\u65CF", "\u72EC\u9F99\u65CF", "\u5854\u5854\u5C14\u65CF",
		"\u8D6B\u54F2\u65CF", "\u73DE\u5DF4\u65CF", "\u5404\u65CF"
	};

	public static final GbRegion Empty =
		new GbRegion(RegionType.OTHER, "", "\u672A\u77E5", "\u672A\u77E5", "\u672A\u77E5", "\u672A\u77E5");

	public static GbRegion query(String regionCode) {

		return regionCode == null || regionCode.length() == 0
			? Empty : !_regionMap.containsKey(regionCode)
			? Empty : _regionMap.get(regionCode);
	}

	public static List<GbRegion> rootRegions() { return _rootRegion; }
	public static List<GbRegion> provinceRegions() { return _rootRegion; }
	public static List<GbRegion> cityRegions() { return _cityRegion; }
	public static List<GbRegion> countyRegions() { return _countyRegion; }

	public static List<GbRegion> queryByPostcode(String postcode) {

		List<GbRegion> list =
			postcode == null || postcode.length() == 0 ? null
				: Collections.unmodifiableList(
					_regionIndexedByPostcode.get(postcode));
		if (list == null)
			list = new ArrayList<>();

		return list;
	}

	public static List<GbRegion> queryByTelPrefix(String telPrefix) {

		List<GbRegion> list =
			telPrefix == null || telPrefix.length() == 0 ? null
				: Collections.unmodifiableList(
					_regionIndexedByTelPrefix.get(telPrefix));
		if (list == null)
			list = new ArrayList<GbRegion>();

		return list;
	}

	private static List<GbRegion> _rootRegion;
	private static List<GbRegion> _cityRegion;
	private static List<GbRegion> _countyRegion;
	private static Map<String, GbRegion> _regionMap;
	private static Map<String, List<GbRegion>> _regionIndexedByPostcode;
	private static Map<String, List<GbRegion>> _regionIndexedByTelPrefix;
	private static Map<String, List<GbRegion>> _regionIndexedByParentCode;

	static {

		_rootRegion = new ArrayList<>(10);
		_cityRegion = new ArrayList<>(10);
		_countyRegion = new ArrayList<>(10);
		_regionMap = new HashMap<>();
		_regionIndexedByPostcode = new HashMap<>();
		_regionIndexedByTelPrefix = new HashMap<>();
		_regionIndexedByParentCode = new HashMap<>();

		String[][][] regions = {
			Data1x.allRegion,
			Data2x.allRegion,
			Data31x_32x_33x_34x.allRegion,
			Data35x_36x_37x.allRegion,
			Data41x_42x_43x.allRegion,
			Data44x_45x_46x.allRegion,
			Data51x_52x.allRegion,
			Data53x_54x.allRegion,
			Data6x.allRegion,
			Data7x.allRegion,
			Data8x.allRegion
		};

		for (String[][] regionPart : regions) {
			String code, name, fullName, postcode, telPerfix;
			String keyName, regionTypeName;
			List<String> rations;
			for (String[] s : regionPart) {
				code = s[0];
				name = s[1];
				fullName = validFieldValue(s, 2);
				postcode = validFieldValue(s, 3);
				telPerfix = validFieldValue(s, 4);

				RegionType regionType = code.length() == 2
					? RegionType.PROVINCE : code.length() == 4
					? RegionType.CITY : code.length() == 6
					? RegionType.COUNTY : RegionType.OTHER;

				GbRegion region = new GbRegion(regionType,
					code, name, fullName, postcode, telPerfix);

				if (region.code.length() == 2)
					_rootRegion.add(region);
				else {

					if (region.code.length() == 4)
						_cityRegion.add(region);

					if (region.code.length() == 6)
						_countyRegion.add(region);

					String parentCode = region.code.substring(
						0, region.code.length() - 2);
					getRegionList(_regionIndexedByParentCode, parentCode).add(region);
				}

				_regionMap.put(code, region);

				if (postcode != null && postcode.length() > 0)
					getRegionList(_regionIndexedByPostcode, postcode).add(region);

				if (telPerfix != null && telPerfix.length() > 0)
					getRegionList(_regionIndexedByTelPrefix, telPerfix).add(region);
			}
		}

		for (Map.Entry<String, List<GbRegion>> e : _regionIndexedByPostcode.entrySet()) {
			_regionIndexedByParentCode.put(
				e.getKey(), Collections.unmodifiableList(e.getValue())
			);
		}
		for (Map.Entry<String, List<GbRegion>> e : _regionIndexedByTelPrefix.entrySet()) {
			_regionIndexedByTelPrefix.put(
				e.getKey(), Collections.unmodifiableList(e.getValue())
			);
		};
		for (Map.Entry<String, List<GbRegion>> e : _regionIndexedByParentCode.entrySet()) {
			_regionIndexedByParentCode.put(
				e.getKey(), Collections.unmodifiableList(e.getValue())
			);
		}

		_rootRegion = Collections.unmodifiableList(_rootRegion);
		_cityRegion = Collections.unmodifiableList(_cityRegion);
		_countyRegion = Collections.unmodifiableList(_countyRegion);
	}

	private static List<GbRegion> getRegionList(
		Map<String, List<GbRegion>> q, String k) {

		List<GbRegion> list = q.get(k);
		if (list == null) {
			list = new ArrayList<>();
			q.put(k, list);
		}

		return list;
	}

	private static String validFieldValue(String[] fields, int i) {
		return (fields.length >= (i + 1) && (fields[i] != null))
			? fields[i] : "";
	}

// Warning: the data is too long for a single java Class,
// it is forced to split into multi data parts classes,
// now, we split all the data into several parts temporary by
// region code by the first(or first and second) character.

// See GbRegionData1x
// See GbRegionData2x
// See GbRegionData31x_32x_33x_34x
// See GbRegionData35x_36x_37x
// See GbRegionData41x_42x_43x
// See GbRegionData44x_45x_46x
// See GbRegionData51x_52x
// See GbRegionData53x_54x
// See GbRegionData6x
// See GbRegionData7x
// See GbRegionData8x
}
