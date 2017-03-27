/* Copyright Beijing Wiseweb Technology Co., Ltd. */
package com.wiseweb.other.industry.src.com.wiseweb.model.gb.industry;

import java.util.*;

/**
 * @author Teary Wang on 2016/12/2.
 */
public class IndustryCategory {

	private static final String ES = "";
	private static final List<IndustryCategory> EL =
		Collections.unmodifiableList(new ArrayList<IndustryCategory>(0));

	private String code;
	private String name;

	private IndustryCategory(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() { return code; }
	public String getName() { return name; }

	public String getMainType() { return subCode(1); }
	public String getMainCategory() { return subCode(3); }
	public String getSecondCategory() { return subCode(4); }
	public String getSubCategory() { return subCode(5); }

	public List<IndustryCategory> childrenCategory() { return children.get(code); }

	private String subCode(int len) {
		return this.code != null && this.code.length() > len
			? this.code.substring(0, len) : ES;
	}

	private static List<IndustryCategory> mainType = new ArrayList<>(10);
	private static Map<String, List<IndustryCategory>> children = new HashMap<>();

	static {

		for (int i = 0; i < IndustryCategoryData.data.length; i++) {
			String[] items = IndustryCategoryData.data[i];

			IndustryCategory ic = new IndustryCategory(items[0], items[1]);
			int codeLen = ic.getCode().length();
			String parentCode = null;
			switch (codeLen) {
				default: System.out.println("Ignored \"" + items[0] + "\", \"" + items[1] + "\""); continue;
				case 1: break;
				case 3: parentCode = ic.getMainType(); break;
				case 4: parentCode = ic.getMainCategory(); break;
				case 5: parentCode = ic.getSecondCategory(); break;
			}

			if (parentCode == null)
				mainType.add(ic);
			else {
				List<IndustryCategory> icl = children.get(parentCode);
				if (icl == null) {
					icl = new ArrayList<>(10);
					children.put(parentCode, icl);
				}

				icl.add(ic);
			}
		}

		mainType = Collections.unmodifiableList(mainType);
		for (Map.Entry<String, List<IndustryCategory>> e : children.entrySet()) {
			children.put(e.getKey(), Collections.unmodifiableList(e.getValue()));
		}
	}
}
