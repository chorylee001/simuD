/* Copyright Beijing Wiseweb Technology Co., Ltd. */
package com.wiseweb.other.character.src.com.wiseweb.model.character;

import java.util.Arrays;

/** {@code char} array encapsulation.
 *  @author Teary Wang on 2016/10/24. */
public class CharTable {

	private char[] table;
	public CharTable(char[] table) { this(new char[][] { table }); }
	public CharTable(char[][] tables) {
		if (tables == null) {
			this.table = new char[0];
			return;
		}

		int length = 0, offset = 0;
		for (int i = 0; i < tables.length; i++) {
			if (tables[i] != null)
				length += tables[i].length;
		}

		table = new char[length];
		for (int i = 0; i < tables.length; i++) {
			if (tables[i] != null) {
				System.arraycopy(tables[i], 0, table, offset, tables[i].length);
				offset += tables[i].length;
			}
		}

		Arrays.sort(table);
	}

	public char[] getTable() { return table.clone(); }

	public int length() { return table.length; }
	public char charAt(int index) { return table[index]; }
}
