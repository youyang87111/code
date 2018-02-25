package com.pax.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 * @author Bill
 *
 */
public class DateUtils {
	private static final Logger	logger					= LoggerFactory.getLogger("\"DateUtils\"");
	
	public static final String	LONG_FORMAT				= "yyyyMMddHHmmss";
	public static final String	SHORT_FORMAT			= "yyyyMMdd";
	public static final String	LONG_FORMAT_YY			= "yyMMddHHmmss";
	public static final String	SHORT_FORMAT_SEP		= "yyyy/MM/dd";
	public static final String	LONG_FORMAT_SEP			= "yyyy/MM/dd HH:mm:ss";
	public static final String	LONG_FORMAT_FULL_SEP	= "yyyy-MM-dd HH:mm:ss";
	public static final String	LONG_FULL_FORMAT_SEP	= "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 日期转字符
	 *
	 * @param date
	 *            ：待转的日期
	 * @param format
	 *            ：转换格式，如yyyy-mm-dd,为空采用yyyymmddhhMMss
	 * @return: 转换后的字符串形式
	 */
	public static String date2String(Date date, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} catch (Exception e) {
			logger.info("格式化字符日期失败[data:" + date + ", format:" + format + "]");
			return null;
		}
		
	}
	
	/**
	 * 日期转字符
	 *
	 * @param date
	 *            ：待转的日期
	 * @return: 转换后的字符串形式yyyyMMddHHmmss
	 */
	public static String date2String(Date date) {
		return date2String(date, "yyyyMMddHHmmss");
	}
	
	/**
	 * 将从数据库中取出的字符串形式的日期格式化
	 *
	 * @param dateString
	 * @param format
	 * @return
	 */
	public static String stringDateFormat(String dateString, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date tmpdate = sdf.parse(dateString);
			sdf = new SimpleDateFormat(format);
			return sdf.format(tmpdate);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 将指定格式的日期字符串转成日期
	 *
	 * @param dateStr
	 *            待转的日期字符串
	 * @param format
	 *            待转的日期字符串格式
	 * @return 日期
	 */
	public static Date string2date(String dateStr, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 将日期转成指定格式的字符串
	 *
	 * @param date
	 *            待转的日期
	 * @param format
	 *            转换字符串的格式
	 * @return 转换后的字符串
	 */
	public static String date2string(Date date, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 日期字符串格式转化
	 *
	 * @param dateString
	 *            待转换的日期字符串
	 * @param src_format
	 *            待转换的日期字符串格式
	 * @param des_format
	 *            转换后的格式
	 * @return 转换后的日期字符串
	 */
	public static String stringDateFormat(String dateString, String src_format, String des_format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(src_format);
			Date tmpdate = sdf.parse(dateString);
			sdf = new SimpleDateFormat(des_format);
			return sdf.format(tmpdate);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getCurrentDateString() {
		return DateUtils.date2String(new Date());
	}
	
	public static String Date2FullString() {
		DateFormat f = new SimpleDateFormat(LONG_FORMAT_FULL_SEP);
		return f.format(new Date());
		
	}
	
	/**
	 * 将14位时间格式化
	 * @param dateStr
	 * @return
	 */
	public static String parseFullDateTime(String dateStr) throws Exception {
		SimpleDateFormat sdf1 = new SimpleDateFormat(LONG_FORMAT);
		Date date = sdf1.parse(dateStr);
		
		SimpleDateFormat sdf2 = new SimpleDateFormat(LONG_FORMAT_FULL_SEP);
		String fullDate = sdf2.format(date);
		return fullDate;
	}
	
	/**
	 * 字符串日期的加减
	 * @param date  date string
	 * @param field the calendar field.
	 * @param amount the amount of date or time to be added to the field.
	 * */
	public static String datePlusOrMinus(String date, int field, int amount) {
		try {
			
			SimpleDateFormat df = new SimpleDateFormat(LONG_FORMAT);
			Date selDate = df.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(selDate);
			calendar.add(field, amount);
			Date tmpdate = calendar.getTime();
			String dateStr = df.format(tmpdate);
			return dateStr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static int currentSeconds() {
		return Integer.parseInt(String.valueOf(new Date().getTime() / 1000));
	}
	
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = sdf.parse("2012-09-08 10:10:10");
		Date d2 = sdf.parse("2012-09-15 00:00:00");
		System.out.println(daysBetween(d1, d2));
	}
	
	/**  
	* 计算两个日期之间相差的天数  
	* @param smdate 较小的时间 
	* @param bdate  较大的时间 
	* @return 相差天数 
	* @throws ParseException  
	*/
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		
		return Integer.parseInt(String.valueOf(between_days));
	}
	
	public static String getCurrentDateString2() {
		return DateUtils.date2String2(new Date());
	}
	
	/**
	 * 日期转字符
	 * 
	 * @param date
	 *            ：待转的日期
	 * @return: 转换后的字符串形式yyyyMMddHHmmss
	 */
	public static String date2String2(Date date) {
		return date2String(date, "yyyyMMddHHmmssSSS");
	}
	
}
