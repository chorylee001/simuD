/* Copyright Beijing Wiseweb Technology Co., Ltd. */
package com.wiseweb.other.region.src.com.wiseweb.model.gb.region;

/**
 * @author Teary Wang on 2016/12/1.
 */
class GbRegionEx implements Comparable<GbRegionEx> {

	private static final char[] EMPTY = new char[] {};

	public GbRegion region;
	public char[] keyName;
	public char[] regionKey;
	public char[][] nationKeys;

	public GbRegionEx(GbRegion r) {
		this.region = r;
		this.keyName = r.getKeyName() == null
						   ? EMPTY : r.getKeyName().toCharArray();
		this.regionKey = r.getRegionKey() == null
							 ? EMPTY : r.getRegionKey().toCharArray();
		String[] nationKeys = r.getNationKeys();
		this.nationKeys = new char[nationKeys.length][];
		for (int i = 0; i < nationKeys.length; i++)
			this.nationKeys[i] = nationKeys[i].toCharArray();
	}

	public int compareTo(GbRegionEx o) {
		int nt = keyName.length,
			no = o.keyName.length,
			lim = nt < no ? nt : no;

		char v1[] = keyName,
			v2[] = o.keyName;

		int k = 0;
		while (k < lim) {
			char c1 = v1[k],
				c2 = v2[k];
			if (c1 != c2)
				return c1 - c2;
			k++;
		}

		return 0;
	}
}
