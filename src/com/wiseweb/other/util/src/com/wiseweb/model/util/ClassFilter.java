/* Copyright Beijing Wiseweb Technology Co., Ltd. */
package com.wiseweb.other.util.src.com.wiseweb.model.util;

/**
 * @author TearyWang on 2017/1/15.
 */
public interface ClassFilter {
	/** Test if target class should be filtered.
	 *  @param clazz Target class to be tested.
	 *  @return Returns the true if target class should be filtered, or
	 *          false otherwise. */
	boolean isFiltered(Class<?> clazz);
}
