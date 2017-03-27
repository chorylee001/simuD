/* Copyright Beijing Wiseweb Technology Co., Ltd. */
package com.wiseweb.other.industry.src.com.wiseweb.model.gb.industry;

import com.wiseweb.other.character.src.com.wiseweb.model.character.CharLinkedIndex;
import com.wiseweb.other.character.src.com.wiseweb.model.character.IndustryCategoryKeywords;
import com.wiseweb.other.region.src.com.wiseweb.model.gb.region.GbRegion;

import java.util.List;

/**
 * @author Teary Wang on 2016/12/2.
 */
public class CompanyNameKeywordIndex {

	public CompanyNameKeywordIndex() { buildIndex(); }

	public int skipKeyword(char[] charArray, int startFrom) {

		int mp = startFrom,  // The middle index pointer, when the index found, it forward.
			legnth = charArray.length - 1;

		int loopOutOfRange = 0;

		CharLinkedIndex<String> cn = root, temp, lastCN = null;
		char tempCh;
		while (mp <= legnth && loopOutOfRange++ < MAX_LOOP_RANGE) {

			if (Character.isWhitespace(tempCh = charArray[mp])) {
				mp++;
				continue;
			}
			if ((temp = cn.searchNext(tempCh)) != null) {
				cn = temp;
				mp++;
				continue;
			}

			break;
		}

		while (cn != root) {

			String[] data = cn.getData();
			if (data.length != 0)
				break;

			cn = cn.getParent();
			mp--;
		}

		if (mp == startFrom)
			mp = startFrom;

		return mp - startFrom;
	}

	public String dumpIndex() {
		StringBuilder sb = new StringBuilder();
		CharLinkedIndex[] nexts = root.getNexts();
		for (int i = 0; i < nexts.length; i++) {
			dumpCore(0, nexts[i], sb);
			sb.append("\n");
		}
		return sb.toString();
	}

	void buildIndex() {
		for (int i = 0; i < WORDS.length; i++)
			addString(WORDS[i]);

		// For regions.
		List[] regions = {
			GbRegion.provinceRegions(),
			GbRegion.cityRegions()
		};
		for (int i = 0; i < regions.length; i++) {
			List<GbRegion> regionList = regions[i];
			int size = regionList.size();
			for (int j = 0; j < size; j++) {
				GbRegion r = regionList.get(j);
				String[] names = { r.getKeyName(), r.getName() };
				for (int k = 0; k < names.length; k++)
					addString(names[k]);
			}
		}
	}

	private static void dumpCore(
		int deep, CharLinkedIndex cn, StringBuilder sb) {

		CharLinkedIndex[] nexts = cn.getNexts();
		String[] data = (String[])cn.getData();

		if (data != null && data.length > 0) {

			sb.append(toIndexString(cn));

			if (data.length == 1) {
				sb  .append(" => ").append(data[0]);
			}
			else {
				sb  .append(" =>");
				for (int i = 0; i < data.length; i++) {
					sb  .append("\n\t")
						.append(data[i]);
				}
			}
			sb.append("\n");
		}

		for (int i = 0; i < nexts.length; i++) {
			dumpCore(deep + 1, nexts[i], sb);
		}
	}
	private static String toIndexString(CharLinkedIndex cn) {
		if (cn == null)
			return "";

		StringBuilder sb = new StringBuilder();
		sb.append(cn.getChar());
		while (cn.getParent() != null) {
			if (cn.getParent().getChar() != 0)
				sb.append(cn.getParent().getChar());
			cn = cn.getParent();
		}
		return sb.reverse().toString();
	}

	private void addString(String s) {

		CharLinkedIndex<String> cnParent = root;
		char[] ar = s.toCharArray();
		for (int n = 0; n < ar.length; n++)
			cnParent = cnParent.addNext(ar[n]);
		cnParent.addData(s);
	}

	private CharLinkedIndex<String> root =
		new CharLinkedIndex<>((char)0, String.class);

	private static final String[] WORDS =
		IndustryCategoryKeywords.Keywords.getTable();

	private static final int MAX_LOOP_RANGE = 255;
}
