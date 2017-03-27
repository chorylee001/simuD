/* Copyright Beijing Wiseweb Technology Co., Ltd. */
package com.wiseweb.other.region.src.com.wiseweb.model.gb.region;

import com.wiseweb.other.character.src.com.wiseweb.model.character.CharLinkedIndex;

import java.util.List;

/**
 * @author Teary Wang on 2016/10/28.
 */
public class GbRegionNameIndex {

	public static GbRegionFound queryAddress(String address) {
		if (address == null)
			return null;

		char[] addr = address.toCharArray();
		return indexer.searchIndex(addr, 0);
	}

	public static String dumpIndex() { return indexer.dumpIndex(); }

	private static final GbRegionIndexer indexer = new GbRegionIndexer();

	static { indexer.buildIndex(); }

	private static class GbRegionIndexer {

		void buildIndex() {
			List[] regions = {
				GbRegion.rootRegions(),
				GbRegion.cityRegions(),
				GbRegion.countyRegions()
			};
			for (int i = 0; i < regions.length; i++) {
				List<GbRegion> rootRegions = regions[i];
				for (int j = 0; j < rootRegions.size(); j++)
					buildCore(rootRegions.get(j), root);
			}
		}

		GbRegionFound searchIndex(char[] charArray, int startFrom) {

			if (charArray == null)
				throw new NullPointerException("\"charArray\" is null");

			if (startFrom < 0)
				throw new IndexOutOfBoundsException("\"startFrom\" is out of bounds");

			int bp = startFrom, // The backward index pointer, when the region found, it forward.
				mp = startFrom,  // The middle index pointer, when the index found, it forward.
				fp = startFrom,  // The forward index pointer, when each character matched, it forward.
				left = charArray.length - startFrom;

			int loopOutOfRange = 0;

			GbRegionEx lastFound = null;
			CharLinkedIndex cn = root, temp, lastCN = null;
			char tempCh;
			while (bp <= mp && mp <= left && loopOutOfRange++ < MAX_LOOP_RANGE) {

				if (Character.isWhitespace(tempCh = charArray[mp])) {
					mp++;
					continue;
				}
				if ((temp = cn.searchNext(tempCh)) != null) {
					cn = temp;
					mp++;
					continue;
				}

				if (cn == lastCN || cn == root)
					break;

				fp = mp;
				CharLinkedIndex parent = cn;
				GbRegionEx found = null;
				int sk = -1;
				for (; ;) {
					// No next index, using current index to test.
					GbRegionEx[] r = (GbRegionEx[]) parent.getData();
					for (int i = 0; i < r.length; i++) {

						// Skip nation keys if found.
						for (int j = 0; j < r[i].nationKeys.length; j++) {
							sk = skip(r[i].nationKeys[j], charArray, fp);
							if (sk != -1)
								fp += sk;
						}

						// Skip region keys if found.
						sk = skip(r[i].regionKey, charArray, fp);
						if (sk != -1)
							fp += sk;

						// We consider found when the new index is more than old.
						if (fp > mp) {
							found = r[i];
							break;
						}
					}
					if (found == null && r.length == 1)
						found = r[0];

					if (found != null)
						break;

				/* Make sure backward middle pointer is valid. */
				/* Make sure backward index pointer is valid. */
					if (--mp < bp ||
						(parent = parent.getParent()) == lastCN)
						break;

					// Backward the fast pointer.
					fp = mp;
				}

				if (found != null) {
					bp = mp = fp;
					lastFound = found;
				}
				lastCN = cn; // Break pointer
			}

			GbRegionFound at = new GbRegionFound();
			at.region = lastFound == null ? null : lastFound.region;
			at.endAt  = lastFound == null ? -1 : bp;

			if (loopOutOfRange >= MAX_LOOP_RANGE) {

				at.indexOutOfRange = true;
			}
			return at;
		}

		String dumpIndex() {
			StringBuilder sb = new StringBuilder();
			CharLinkedIndex[] nexts = root.getNexts();
			for (int i = 0; i < nexts.length; i++) {
				dumpCore(0, nexts[i], sb);
				sb.append("\n");
			}
			return sb.toString();
		}

		void buildCore(GbRegion r, CharLinkedIndex<GbRegionEx> parentCN) {

			List children = r.getChildren();
			String name = r.getName();
			boolean asSubs = name.equals("\u5E02\u8F96\u533A") || name.equals("\u53BF") ||
				name.equals("\u7701\u76F4\u8F96\u53BF\u7EA7\u884C\u653F\u533A") ||
				name.equals("\u81EA\u6CBB\u533A\u76F4\u8F96\u53BF\u7EA7\u884C\u653F\u533A\u5212");

			String[] names = {
				r.getKeyName(),
				r.getName()
			};

			for (int n = 0; n < names.length; n++) {

				CharLinkedIndex<GbRegionEx> cn = parentCN;

				char[] kchs = names[n].toCharArray();
				if (kchs.length > 0 && !asSubs) {

					for (int i = 0; i < kchs.length; i++)
						cn = cn.addNext(kchs[i]);

					cn.addData(new GbRegionEx(r));
				}

				for (int i = 0; i < children.size(); i++)
					buildCore((GbRegion) children.get(i), cn);

				if (asSubs) {
					cn = parentCN;
					GbRegion nr = GbRegion.query(r.getParentCode());
					if (!nr.getIsEmpty()) {
						kchs = nr.getKeyName().toCharArray();
						for (int i = 0; i < kchs.length; i++)
							cn = cn.addNext(kchs[i]);

						cn.addData(new GbRegionEx(nr));

						for (int i = 0; i < children.size(); i++)
							buildCore((GbRegion) children.get(i), cn);
					}
				}
			}
		}

		private static void dumpCore(int deep, CharLinkedIndex cn, StringBuilder sb) {

			CharLinkedIndex[] nexts = cn.getNexts();
			GbRegionEx[] regions = (GbRegionEx[])cn.getData();

			if (regions != null && regions.length > 0) {

				sb.append(toIndexString(cn));

				if (regions.length == 1) {
					sb  .append(" => ").append(regions[0].region.getFullName())
						.append(':')
						.append(regions[0].region.getName());
				}
				else {
					sb  .append(" =>");
					for (int i = 0; i < regions.length; i++) {
						sb  .append("\n\t")
							.append(regions[i].region.getFullName())
							.append(':')
							.append(regions[i].region.getName());
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


		/** Compare to skip.
		 *  @param src The source char[] from the index.
		 *  @param dst The destination char[] address.
		 *  @param dstFrom Comparing start index.
		 *  @return Returns -1 if not full matched or the skip length. */
		private static int skip(char[] src, char[] dst, int dstFrom) {

			int srcFrom = 0,
				sl = src.length,
				dl = dst.length - dstFrom,
				len = sl < dl ? sl : dl,
				sk = len;
			char dstc;
			while (len > 0) {
				if (Character.isWhitespace(dstc = dst[dstFrom++]))
					continue;

				if (src[srcFrom++] != dstc)
					return -1;

				len--;
			}
			return sk;
		}


		private static final int MAX_LOOP_RANGE = 255;

		private CharLinkedIndex<GbRegionEx> root =
			new CharLinkedIndex<>((char)0, GbRegionEx.class);
	}
}
