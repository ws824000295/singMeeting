package com.boot.common.util;

import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 类名: com.dns.common.utils.Assert
 * <p>
 * 描述: 断言辅助类
 * </p>
 * 日期: 2014-3-14 下午10:52:20
 * 
 * @author 蓝斌
 * @version V1.0
 * @since JDK1.5
 */
public class Assert {

	/**
	 * 描述: Log4j日志对象
	 */
	@SuppressWarnings("unused")
	private static final transient Logger logger = Logger.getLogger(Assert.class);

	/**
	 * <p>
	 * 描述: 判断字符串是否为null或空
	 * </p>
	 * 
	 * @param str
	 *            要验证的字符串对象
	 * @return true-等于null或空 false-不等于null或空
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.length() <= 0);
	}

	/**
	 * <p>
	 * 描述: 判断数组是否为null或空
	 * </p>
	 * 
	 * @param array
	 *            要验证的数组对象
	 * @return true-等于null或空 false-不等于null或空
	 */
	public static boolean isEmpty(Object[] array) {
		return (array == null || array.length <= 0);
	}

	/**
	 * <p>
	 * 描述: 判断集合是否为null或空
	 * </p>
	 * 
	 * @param collection
	 *            要验证的集合对象
	 * @return true-等于null或空 false-不等于null或空
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return (collection == null || collection.isEmpty());
	}

	/**
	 * <p>
	 * 描述: 判断图集是否为null或空
	 * </p>
	 * 
	 * @param map
	 *            要验证的图集对象
	 * @return true-等于null或空 false-不等于null或空
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return (map == null || map.isEmpty());
	}

	/**
	 * <p>
	 * 描述: 判断对象是否为null
	 * </p>
	 * 
	 * @param object
	 *            要验证的对象，如果object为null则抛出java.lang.IllegalArgumentException异常
	 */
	public static void notNull(Object object) {
		if (object == null) {
			throw new IllegalArgumentException("验证失败：参数为null");
		}
	}

	/**
	 * <p>
	 * 描述: 判断字符串是否为null或空
	 * </p>
	 * 
	 * @param str
	 *            要验证的字符串对象，如果str为null或空则抛出java.lang.IllegalArgumentException异常
	 */
	public static void notEmpty(String str) {
		if (Assert.isEmpty(str)) {
			throw new IllegalArgumentException("验证失败：字符串长度必须大于0");
		}
	}

	/**
	 * <p>
	 * 描述: 判断数组是否为null或空
	 * </p>
	 * 
	 * @param array
	 *            要验证的数组对象，如果array为null或空则抛出java.lang.IllegalArgumentException异常
	 */
	public static void notEmpty(Object[] array) {
		if (Assert.isEmpty(array)) {
			throw new IllegalArgumentException("验证失败：数组的长度必须大于0");
		}
	}

	/**
	 * <p>
	 * 描述: 判断集合是否为null或空
	 * </p>
	 * 
	 * @param collection
	 *            要验证的集合对象，如果collection为null或空则抛出java.lang.IllegalArgumentException异常
	 */
	public static void notEmpty(Collection<?> collection) {
		if (Assert.isEmpty(collection)) {
			throw new IllegalArgumentException("验证失败：集合不能为null或空集合");
		}
	}

	/**
	 * <p>
	 * 描述: 判断映射是否为null或空
	 * </p>
	 * 
	 * @param map
	 *            要验证的映射对象，如果map为null或空则抛出java.lang.IllegalArgumentException异常
	 */
	public static void notEmpty(Map<?, ?> map) {
		if (Assert.isEmpty(map)) {
			throw new IllegalArgumentException("验证失败：映射不能为null或空映射");
		}
	}

}
