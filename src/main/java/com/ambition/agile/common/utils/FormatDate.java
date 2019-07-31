package com.ambition.agile.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormatDate {
	
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static DateFormat dateFormatPorit = new SimpleDateFormat(
			"yyyy.MM.dd");

	private static DateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	// 日期转换成字符串
	public static String dateToString(Date date) {
		return dateFormat.format(date);
	}
	
	// 日期转换成字符串
	public static String dateToDayString(Date date) {
		return dayFormat.format(date);
	}

	// 日期转换成字符串yyyy.MM.dd
	public static String dateToStringPorit(Date date) {
		return dateFormatPorit.format(date);
	}

	/** 日期转字符串
	 * @param date 日期
	 * @param str  转换后的字符串格式
	 * @return
	 */
	public static String dateToStringByStr(Date date, String str){
		DateFormat df = new SimpleDateFormat(str);
		return df.format(date);
	}
	
	// 字符串转换成日期
	public static Date stringToDateTime(String stringDate) {
		Date date = null;
		try {
			date = dateFormat.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	// 字符串转换成日期
	public static Date stringToDate(String stringDate) {
		Date date = null;
		try {
			date = dayFormat.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	// 两个字符串日期比较前后
	public static int compare_date(String DATE1, String DATE2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}
	
	
	/** 字符串转换成秒 (格式：00:00:00)
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Integer stringToInteger(String str) throws ParseException{
		Integer lon = 0;
		String[] ss = str.split(":");
		lon = lon + Integer.parseInt(ss[0]) * 3600;
		lon = lon + Integer.parseInt(ss[1]) * 60; 
		lon = lon + Integer.parseInt(ss[2]);
		return lon;
	}
	
	/** 秒转换成字符 (格式：00:00:00)
	 * @param lon
	 * @return
	 * @throws ParseException
	 */
	public static String longToString(long lon){
		if(lon <= 0){
			return "00:00:00";
		}
		String str = "";
		/*long hh = lon/3600/1000;
		long mm = (lon - hh * 3600 * 1000)/60/1000;
		long ss = (lon - hh * 3600 * 1000 - mm * 60 * 1000)/1000;*/
		long hh = lon/3600;
		long mm = (lon - hh * 3600)/60;
		long ss = lon - hh * 3600 - mm * 60;
		if(hh < 10){
			str = "0" + hh + ":";
		}else{
			str = str + hh + ":";
		}
		if(mm < 10){
			str = str + "0" + mm + ":";
		}else{
			str = str + mm + ":";
		}
		if(ss < 10){
			str = str + "0" + ss;
		}else{
			str = str + ss;
		}
		return str;
	}
	
	/**  秒数转时间 (__小时__分钟)
	 * @param lon
	 * @return
	 * @throws ParseException
	 */
	public static String longMMToString(long lon){
		long hh = lon/3600;
		long mm = (lon - hh * 3600)/60;
		//long ss = lon - hh * 3600 - mm * 3600;
		return hh + "时" + mm + "分";
	}
	
	/**  获取系统当前时间
	 * @return
	 */
	public static Date getCurrentTime(){
		Calendar cld = Calendar.getInstance();
		return cld.getTime();
	}
	
	
	/** 计算二个数的百分比（不计算大于100%的，保留一位小数） 由于页面需要，%符号不自动添加
	 * @param num1 较大的数
	 * @param num2 较小的数
	 * @return 百分比
	 */
	public static String getPercent(double num1, double num2){
		if(num2 == 0){
			return "0";
		}
		if(num2 >= num1){
			return "100";
		}
		double temp = num2 * 100 / num1;
		String tempStr = (temp + "").charAt(0) + "";
		if(temp >= 10){
			tempStr += ((temp + "").charAt(1) + "");
			if((temp + "").charAt(3) != '0'){
				tempStr += (temp + "").charAt(2) + ((temp + "").charAt(3) + "");
			}
		}else{
			if((temp + "").charAt(2) != '0'){
				tempStr += (temp + "").charAt(1) + ((temp + "").charAt(2) + "");
			}
		}
		return tempStr;
	}
	
	/**
	 * @Description: 课程 Scorm 课件上传时解析获取的时长转换（dur: 1:12:32.8） 
	 * @author: yzh
	 * @date: 2014-9-1 下午03:09:41
	 */
	public static String getDurationByCourseFileStr(String dur){
		StringBuffer sb = new StringBuffer();
		String[] durs = dur.split(":");
		// 时
		String hh = durs[0];
		if(hh.length() < 2){
			sb.append("0" + hh + ":");
		}else{
			sb.append(hh + ":");
		}
		
		// 分
		String mm = durs[1];
		if(mm.length() < 2){
			sb.append("0" + mm + ":");
		}else{
			sb.append(mm + ":");
		}
		
		// 秒
		String ss = durs[2];
		if(ss.length() > 2){
			sb.append(ss.substring(0, 2));
		}else if(ss.length() < 2){
			sb.append("0" + ss);
		}else{
			sb.append(ss);
		}
		return sb.toString();
	}
	
	/**
	 * @Description: 课程 Scorm 课件上传时解析获取的时长转换（dur: 45:00 or 01:05:03） -- 新课件
	 * @author: yzh
	 * @date: 2014-11-14 下午03:59:41
	 */
	public static String getDurationByCourseFileStr_new(String dur){
		StringBuffer sb = new StringBuffer();
		String[] durs = dur.split(":");
		
		if(durs.length > 2){
			// 时
			String hh = durs[0];
			if(hh.length() < 2){
				sb.append("0" + hh + ":");
			}else{
				sb.append(hh + ":");
			}
			// 分
			String mm = durs[1];
			if(mm.length() < 2){
				sb.append("0" + mm + ":");
			}else{
				sb.append(mm + ":");
			}
			
			// 秒
			String ss = durs[2];
			if(ss.length() > 2){
				sb.append(ss.substring(0, 2));
			}else if(ss.length() < 2){
				sb.append("0" + ss);
			}else{
				sb.append(ss);
			}
		}else if(durs.length == 2){
			// 时
			sb.append("00:");
			
			// 分
			String mm = durs[0];
			if(mm.length() < 2){
				sb.append("0" + mm + ":");
			}else{
				sb.append(mm + ":");
			}
			
			// 秒
			String ss = durs[1];
			if(ss.length() > 2){
				sb.append(ss.substring(0, 2));
			}else if(ss.length() < 2){
				sb.append("0" + ss);
			}else{
				sb.append(ss);
			}
		}
		
		return sb.toString();
	}
   
	
}
