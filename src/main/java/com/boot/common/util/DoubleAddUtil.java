package com.boot.common.util;

import java.math.BigDecimal;

/**
 * @author WangShuo
 * @date 创建时间：2018年1月9日 上午10:44:55
 * @version 1.0
 * @Description: 两个 double 类型的值相加 返回新的double结果
 * @since 1.8
 * @return
 */
public class DoubleAddUtil {

	/**
	 * @Description: 两个 double 类型的值相加 返回新的double结果
	 * @author WangShuo
	 * @date 2018年1月9日 上午10:46:49
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	
	
	public static double doubleAndBigDecimal(double v1, BigDecimal b2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1)); 
		return b1.add(b2).doubleValue();
	}

	public static double sub(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.subtract(bd2).doubleValue();
	}

	/**
	 * double 除法
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 *            四舍五入 小数点位数
	 * @return
	 */
	public static double div(double d1, double d2, int scale) {
		// 当然在此之前，你要判断分母是否为0，
		// 为0你可以根据实际需求做相应的处理
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));
		return bd1.divide(bd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * double 转 string 去掉后面锝0
	 * 
	 * @param i
	 * @return
	 */
	public static String getString(double i) {
		String s = String.valueOf(i);
		if (s.indexOf(".") > 0) {
			// 正则表达
			s = s.replaceAll("0+?$", "");// 去掉后面无用的零
			s = s.replaceAll("[.]$", "");// 如小数点后面全是零则去掉小数点
		}
		return s;
	}

	public static void main(String[] args) {
		String i = numberToBits("10000000.01");
		System.out.println(i);
	}

	/**
	 * 数字转换为千位符
	 * 
	 * @param number
	 * @return
	 */
	public static String numberToBits(String number) {
		String begin = "";
		String end = "";
		String[] num = number.split("\\.");
		if (num.length > 1) {
			begin = num[0];
			end = num[1];
		} else {
			begin = number;
		}
		return begin.replaceAll("(?<=\\d)(?=(?:\\d{3})+$)", ",") + "." + end;
	}
}
