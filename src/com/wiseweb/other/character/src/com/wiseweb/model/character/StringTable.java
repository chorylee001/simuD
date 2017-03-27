/* Copyright Beijing Wiseweb Technology Co., Ltd. */
package com.wiseweb.other.character.src.com.wiseweb.model.character;

import java.util.Arrays;

/**
 * @author Teary Wang on 2016/12/2.
 */
public class StringTable {

	private String[] table;
	public StringTable(String[] table) { this(new String[][] { table }); }
	public StringTable(String[][] tables) {
		if (tables == null) {
			this.table = new String[0];
			return;
		}

		int length = 0, offset = 0;
		for (int i = 0; i < tables.length; i++) {
			if (tables[i] != null)
				length += tables[i].length;
		}

		table = new String[length];
		for (int i = 0; i < tables.length; i++) {
			if (tables[i] != null) {
				System.arraycopy(tables[i], 0, table, offset, tables[i].length);
				offset += tables[i].length;
			}
		}

		Arrays.sort(table);
	}

	public String[] getTable() { return table.clone(); }

	public int length() { return table.length; }
	public String stringAt(int index) { return table[index]; }
}
