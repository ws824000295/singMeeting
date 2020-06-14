package com.boot.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeUtils {

	/**
	 * 得到当前的Timestamp类型的时间
	 * 
	 * @return
	 */
	public static Calendar getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		return calendar;
	}

	/**
	 * 
	 * <p>
	 * 描述: 获取时间戳
	 * </p>
	 * 
	 * @param isSleep
	 *            判断是否需要暂停1毫秒的标识,用于循环获取时避免获取重复的
	 * @return
	 * @throws Exception
	 */
	public static synchronized long currentTimeMillis(boolean... isSleep)
			throws Exception {
		boolean sleep;
		if (isSleep.length > 0) {
			sleep = isSleep[0];
		} else {
			sleep = false;
		}
		if (sleep) {
			Thread.sleep(1);
		}
		long times = System.currentTimeMillis();
		return times;
	}

	/***
	 * 格式化时间
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getFormatDateStr(Date date, String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		String strdate = sf.format(date);
		return strdate;
	}

	/**
	 * 得到当前标准格式的时间,标准格式如下:"2005-03-23 13:34:56".
	 * 
	 * @return 以字符串表示的标准格式的时间,如:"2005-03-23 13:34:56".
	 */
	public static String getCurrentStandardTime() {
		Calendar c = Calendar.getInstance();
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
	}

	public static String getSpecifiedStandardDate1(Date date) {
		// Date date = new Date(second * 1000);
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}
	
	public static String getSpecifiedStandardDate2(Date date) {
		// Date date = new Date(second * 1000);
		return new SimpleDateFormat("MM-dd").format(date);
	}
	/**
	 * 根据秒得到标准格式的时间,标准格式如下:"2005-03-23".
	 * 
	 * @return 以字符串表示的标准格式的时间,如:"2005-03-23".
	 */
	public static String getSpecifiedStandardDate(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public static String getSpecifiedStandardDate3(Date date) {
		return new SimpleDateFormat("yyyy年MM月dd日").format(date);
	}

	public static String getSpecifiedStandardTime2(Date date) {
		return new SimpleDateFormat("HH:mm:ss").format(date);
	}

	/**
	 * 根据秒得到标准格式的时间,标准格式如下:"2005-03-23".
	 * 
	 * @return 以字符串表示的标准格式的时间,如:"2005-03-23".
	 */
	public static String getSpecifiedStandardDate(long second) {
		Date date = new Date(second * 1000);
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	/**
	 * 根据毫秒得到标准格式的时间,标准格式如下:"2005-03-23 13:34:56".
	 * 
	 * @return 以字符串表示的标准格式的时间,如:"2005-03-23 13:34:56".
	 */
	public static String getSpecifiedStandardTime(long milisecond) {
		Date date = new Date(milisecond);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	public static String getSpecifiedStandardTime(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * 根据标准格式的时间字符串得到对应的Date对象,标准格式如下:"2005-03-23 13:34:56".
	 * 
	 * @return 时间字符串对应的Date对象.
	 * @throws ParseException
	 */
	public static Date getDateFromStandardTime(String standardTime)
			throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(standardTime);
	}

	/**
	 * 根据指定格式的时间字符串得到对应的Date对象,指定格式如下:"2005年9月16日 14:00-15:00(北京时间)".
	 * 
	 * @return 时间字符串对应的Date对象.
	 */
	public static Date getDateFromSpecifiedTime(String specifiedTime)
			throws ParseException {
		return new SimpleDateFormat("yyyy年M月dd日 HH:mm").parse(specifiedTime);
	}
	

	/**
	 * 根据标准格式的日期字符串得到对应的Date对象,标准格式如下:"2005-03-23".
	 * 
	 * @return 时间字符串对应的Date对象.
	 */
	public static Date getDateFromStandardDate(String standardDate)
			throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(standardDate);
	}

	public static Date formatterDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date formatDate = null;

		try {
			formatDate = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return formatDate;
	}

	/**
	 * String(yyyy-MM-dd HH:mm:ss)转10位时间戳
	 * 
	 * @param time
	 * @return
	 */
	public static Integer StringToTimestamp(String time) {

		int times = 0;
		try {
			times = (int) ((Timestamp.valueOf(time).getTime()) / 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (times == 0) {
			System.out.println("String转10位时间戳失败");
		}
		return times;

	}

	
	
	
	/**
	 * 10位int型的时间戳转换为String(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param time
	 * @return
	 */
	public static String timestampToString(Integer time) {
		// int转long时，先进行转型再进行计算，否则会是计算结束后在转型
		long temp = (long) time * 1000;
		Timestamp ts = new Timestamp(temp);
		String tsStr = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			// 方法一
			tsStr = dateFormat.format(ts);
			System.out.println(tsStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tsStr;
	}

	/**
	 * 10位时间戳转Date
	 * 
	 * @param time
	 * @return
	 */
	public static Date TimestampToDate(Integer time) {
		long temp = (long) time * 1000;
		Timestamp ts = new Timestamp(temp);
		Date date = new Date();
		try {
			date = ts;
			// System.out.println(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Date类型转换为10位时间戳
	 * 
	 * @param time
	 * @return
	 */
	public static Integer DateToTimestamp(Date time) {
		Timestamp ts = new Timestamp(time.getTime());

		return (int) ((ts.getTime()) / 1000);
	}

	/**
	 * 指定日期，加减指定的天数，传入负数就是减
	 * 
	 * @param date
	 *            指定日期
	 * @param number
	 *            加减的天数
	 * @return
	 * @throws ParseException
	 */

	public static Date getDateByAddDays(Date date, int number) throws ParseException {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.DAY_OF_YEAR, number);
		return now.getTime();
	}

	public static Timestamp getTimestampByAddyears(Timestamp timestamp,
			int number) throws ParseException {
		Calendar now = Calendar.getInstance();
		now.setTime(timestamp);
		now.add(Calendar.DAY_OF_YEAR, number * 365);
		return new Timestamp(now.getTime().getTime());
	}

	public static void main(String[] args) throws ParseException {
// 	       
//       Date start =   getDateFromStandardDate("2018-07-17 17:48:31");
// 	   Date end =   getDateFromStandardDate("2018-07-27 17:48:34");
// 	       
// 	       
// 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
// 		Date dBegin = start;
// 		Date dEnd = end;
// 		
// 		String stringArray[] = {"微学苑", "http://www.weixueyuan.net", "一切编程语言都是纸老虎"};
// 		
// 		List<Map<String, Object>> listMap =  new ArrayList<>();
// 		List<Date> listDate = getDatesBetweenTwoDate(dBegin, dEnd);
// 		for(int i=0;i<listDate.size();i++){
// 			Map<String,Object> m = new HashMap<>();
// 			System.out.println(sdf.format(listDate.get(i)));
// 			m.put("date", sdf.format(listDate.get(i)));
// 			m.put("data",80);
// 			listMap.add(m);
// 		}
// 		
// 		System.out.println(listMap.toString());
 		
	}
	
	
	public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
		List<Date> lDate = new ArrayList<Date>();
		lDate.add(beginDate);// 把开始时间加入集合
		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
		boolean bContinue = true;
		while (bContinue) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(Calendar.DAY_OF_MONTH, 1);
			// 测试此日期是否在指定日期之后
			if (endDate.after(cal.getTime())) {
				lDate.add(cal.getTime());
			} else {
				break;
			}
		}
		lDate.add(endDate);// 把结束时间加入集合
		return lDate;
	}

	/**
	 * 获取两个日期之间的所有日期
	 * 
	 * @Description TODO"MM.dd"
	 * @param begin
	 * @param end
	 * @param format
	 *            时间格式化格式
	 * @return
	 */
	public static List<String> getBetweenDates(Date begin, Date end,
			String format) {

		List<String> result = new ArrayList<String>();

		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(begin);
		while (begin.getTime() <= end.getTime()) {
			result.add(getFormatDateStr(tempStart.getTime(), format));
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
			begin = tempStart.getTime();
		}

		return result;
	}

	/**
	 * 指定日期，加减指定的秒数，传入负数就是减
	 * 
	 * @param date
	 *            指定日期
	 * @param number
	 *            加减的天数（秒数）
	 * @return
	 * @throws ParseException
	 */

	public static Date get2TimeAddByMins(Date date, int number) throws ParseException {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.SECOND, number);
		return now.getTime();
	}
	
 
	
	/**
	 * cumpute Date, add/minus month
	 * 
	 * @param date
	 * @param number
	 *            , month
	 * @return
	 */
	public static Date getDateByAddMonths(Date date, int number) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.MONTH, number);
		return now.getTime();
	}

	/**
	 * 比较两个日期的先后顺旬
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareTo(Date date1, Date date2) {
		if ((date1 != null) && (date2 == null)) {
			return -1;
		} else if ((date1 == null) && (date2 != null)) {
			return 1;
		} else if ((date1 == null) && (date2 == null)) {
			return 0;
		}

		long time1 = date1.getTime();
		long time2 = date2.getTime();
 
		if (time1 == time2) {
			return 0;
		} else if (time1 < time2) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * 比较两个日期的先后顺旬 按天比较日期
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compareTo2(Date date1, Date date2) {
		try {
			if ((date1 != null) && (date2 == null)) {
				return -1;
			} else if ((date1 == null) && (date2 != null)) {
				return 1;
			} else if ((date1 == null) && (date2 == null)) {
				return 0;
			}

			String dateBegin = getSpecifiedStandardDate(date1);
			String dateEnd = getSpecifiedStandardDate(date2);

			Date d1 = getDateFromStandardDate(dateBegin);
			Date d2 = getDateFromStandardDate(dateEnd);

			long time1 = d1.getTime();
			long time2 = d2.getTime();

			if (time1 == time2) {
				return 0;
			} else if (time1 < time2) {
				return -1;
			} else {
				return 1;
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static Date getFirstDayOfMonth(Date date) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		// System.out.println(String.valueOf(month));
		String tmp = year + "-" + month + "-01";
		// System.out.println(tmp);
		Date d = null;
		try {
			d = getDateFromStandardDate(tmp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(d);
		return d;

	}

	public static long getBetweenMinutes(Date begin, Date end) {
		long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
		long day = between / (24 * 3600);
		long hour = between % (24 * 3600) / 3600;
		long minute = between % 3600 / 60;
		// long second=between%60/60;

		long rv = day * 24 * 60 + hour * 60 + minute; // 根据需要可设置返回是天，时，分，秒，这里反回分

		return rv;
	}

	/**
	 * 获得两个时间相差的天数
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public static long getBetweenDay(Date begin, Date end) {
		long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
		long day = between / (24 * 3600);
		return day;
	}

	/**
	 * 获得两个时间相差的秒数
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public static long get2TimeSubByMins(Date begin, Date end) {
		long between = (end.getTime() - begin.getTime()) / 1000;
		return between;
	}

	/**
	 * 得到某一天指定的一天的开始：如：2015-5-7 00:00:00
	 * <p>
	 * 描述: TODO
	 * </p>
	 * 
	 * @param date
	 * @return
	 */
	public static Date getOneDayStart(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, day);
		return cal.getTime();
	}

	/**
	 * 计算两个时间之间相差年
	 * 
	 * @param date1
	 *            <String>
	 * @param date2
	 *            <String>
	 * @return int
	 * @throws ParseException
	 */
	public static int getYearSpace(String date1, String date2)
			throws ParseException {
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(sdf.parse(date1));
		c2.setTime(sdf.parse(date2));
		result = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		return result == 0 ? 1 : Math.abs(result);

	}

	/**
	 * 获取于当前时间相差的指定格式的时间
	 * 
	 * @param m
	 * @param formatStr
	 * @return
	 */
	public static String getSpecifyDate(int m, String formatStr) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, m);
		String day = new SimpleDateFormat(formatStr).format(cal.getTime());

		return day;
	}

	public static Date parseMinDate(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取当前时间之前或之后几分钟 minute
	 * 
	 * @param minute
	 * @return
	 */
	public static String getTimeByMinute(int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, minute);

		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar
				.getTime());

	}

	public static Date parseFormatDate(String date, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}
}
