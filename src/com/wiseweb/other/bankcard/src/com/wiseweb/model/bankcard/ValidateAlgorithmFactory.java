/* Copyright Beijing Wiseweb Technology Co., Ltd. */
package com.wiseweb.other.bankcard.src.com.wiseweb.model.bankcard;

import com.wiseweb.other.util.src.com.wiseweb.model.util.ClassFilter;
import com.wiseweb.other.util.src.com.wiseweb.model.util.ClassScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author TearyWang on 2017/1/15.
 */
class ValidateAlgorithmFactory {

	private static Logger logger =
		LoggerFactory.getLogger(ValidateAlgorithmFactory.class);

	private Map<String, ValidateAlgorithm> map = new HashMap<>();

	public ValidateAlgorithm getAlgorithm(String name) {
		return name == null ? null : map.get(name.toUpperCase());
	}

	private ValidateAlgorithmFactory() {
		try {
			List<Class<?>> list = ClassScanner.scanClasses(
				ValidateAlgorithmFactory.class.getClassLoader(),
				ValidateAlgorithmFactory.class.getPackage().getName(),
				false, new ClassFilter() {
					@Override
					public boolean isFiltered(Class<?> clazz) {
						return Modifier.isAbstract(clazz.getModifiers()) ||
							!ValidateAlgorithm.class.isAssignableFrom(clazz);
					}
				});

			if (list.size() == 0)
				logger.warn("No validation algorithm found!");

			for (Class<?> clazz : list) {
				try {
					ValidateAlgorithm alg = (ValidateAlgorithm)clazz.newInstance();
					map.put(alg.getAlgorithmName().toUpperCase(), alg);
				}
				catch (InstantiationException |
					IllegalAccessException e) {
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ValidateAlgorithmFactory _factory =
		new ValidateAlgorithmFactory();
	public static ValidateAlgorithmFactory factory() { return _factory; }

	static {

	}
}
