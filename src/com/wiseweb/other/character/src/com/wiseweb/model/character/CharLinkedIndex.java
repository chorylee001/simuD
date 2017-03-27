/* Copyright Beijing Wiseweb Technology Co., Ltd. */
package com.wiseweb.other.character.src.com.wiseweb.model.character;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Teary Wang on 2016/10/18.
 */
public  class CharLinkedIndex<T>
	implements Comparable<CharLinkedIndex<T>> {

	private Class<T> clazz;

	private CharLinkedIndex<T> parent;
	private char ch;
	private List<CharLinkedIndex<T>> next;
	private List<T> dataList;
	private transient boolean dataUpdated = false;
	private transient T[] data;
	private transient boolean isComparable;

	public CharLinkedIndex(char ch, Class<T> clazz) {
		this.parent = null;
		this.ch = ch;
		this.next = null;
		this.dataList = new ArrayList<>();

		this.clazz = clazz;
		this.isComparable =
			Comparable.class.isAssignableFrom(clazz);
	}

	public CharLinkedIndex<T> getParent() { return parent; }
	public char getChar() { return ch; }
	public CharLinkedIndex<T> searchNext(char ch) /* Binary search */ {

		if (this.next == null)
			return null;

		int low = 0;
		int high = next.size()-1;
		CharLinkedIndex<T> item = null;

		while (low <= high && item == null) {
			int mid = (low + high) >>> 1;
			CharLinkedIndex<T> midVal = next.get(mid);
			int cmp = midVal.ch - ch;

			if (cmp < 0)
				low = mid + 1;
			else if (cmp > 0)
				high = mid - 1;
			else
				item = midVal; // key found
		}
		return item;  // key not found
	}
	public CharLinkedIndex<T>[] getNexts() {
		return next == null ? new CharLinkedIndex[0] : next.toArray(new CharLinkedIndex[0]);
	}
	public T[] getData() {
		if (dataUpdated || data == null) {
			if (dataList == null)
				data = (T[]) Array.newInstance(clazz, 0);
			else
				data = dataList.toArray((T[]) Array.newInstance(clazz, 0));
			dataUpdated = false;
		}

		return data;
	}
	public CharLinkedIndex<T> addNext(char ch) {
		if (next == null)
			next = new ArrayList<>();

		int low = 0;
		int high = next.size()-1;

		while (low <= high) {
			int mid = (low + high) >>> 1;
			CharLinkedIndex midVal = next.get(mid);
			int cmp = midVal.ch - ch;

			if (cmp < 0)
				low = mid + 1;
			else if (cmp > 0)
				high = mid - 1;
			else
				return midVal; // key found
		}
		CharLinkedIndex ret = new CharLinkedIndex(ch, clazz);
		ret.parent = this;
		next.add(low, ret);
		return ret;
	}

	public void addData(T r) {

		if (dataList == null)
			dataList = new ArrayList<>();

		if (!isComparable)
			dataList.add(r);
		else {
			int low = 0;
			int high = dataList.size() - 1;

			while (low <= high) {
				int mid = (low + high) >>> 1;
				Comparable midVal = (Comparable)dataList.get(mid);
				int cmp = midVal.compareTo(r);

				if (cmp < 0)
					low = mid + 1;
				else if (cmp > 0)
					high = mid - 1;
				else
					return; // key found
			}
			// Key not found
			dataList.add(low, r);
		}
	}

	@Override
	public int hashCode() { return ch; }

	@Override
	public int compareTo(CharLinkedIndex<T> o) { return ch - o.ch; }

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer();
		CharLinkedIndex<T> cur = this;
		while (cur != null) {
			sb.insert(0, cur.ch);
			cur = cur.getParent();
		}
		return sb.toString();
	}
}