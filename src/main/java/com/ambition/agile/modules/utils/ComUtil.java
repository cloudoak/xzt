package com.ambition.agile.modules.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.NodeList;

/*
 * @author: hqt
 * @see:工具类 主要是判断是否为空
 * @parm: 
 * @date:Sep 3, 2012
 */
public class ComUtil {

	/**
	 * 判断字符串是否为空
	 * 
	 * @param s
	 *            待判断的字符串
	 * @return boolean 返回 真 假
	 */
	public static boolean isNullOrEmpty(String s) {
		return (s == null) || (s.length() <= 0);
	}

	public static boolean isNotNullOrEmpty(String s) {
		return !isNullOrEmpty(s);
	}

	public static boolean isNullOrEmpty(Long s) {
		return (s == null) || (s <= 0);
	}

	public static boolean isNotNullOrEmpty(Long s) {
		return !isNullOrEmpty(s);
	}
	
	
	public static boolean isNullOrEmpty(Float s) {
		return (s == null) || (s <= 0);
	}

	public static boolean isNotNullOrEmpty(Float s) {
		return !isNullOrEmpty(s);
	}
	

	public static boolean isNullOrEmpty(Integer s) {
		return (s == null) || (s.intValue() <= 0);
	}

	public static boolean isNotNullOrEmpty(Integer s) {
		return !isNullOrEmpty(s);
	}

	public static boolean isNullOrEmptyOrSpace(String s) {
		if (s == null) {
			return true;
		}
		s.trim();

		return s.length() <= 0;
	}

	public static boolean isNullOrEmpty(Map<?, ?> map) {
		return (map == null) || (map.isEmpty());
	}

	public static boolean isNotNullOrEmpty(Map<?, ?> map) {
		return !isNullOrEmpty(map);
	}

	public static boolean isNullOrEmpty(List<?> l) {
		return (l == null) || (l.isEmpty());
	}

	public static boolean isNotNullOrEmpty(List<?> l) {
		return !((l == null) || (l.isEmpty()));
	}

	public static boolean isNullOrEmpty(Object[] o) {
		return (o == null) || (o.length <= 0);
	}

	public static boolean isNotNullOrEmpty(Object[] o) {
		return !isNullOrEmpty(o);
	}

	public static boolean isNullOrEmpty(int[] o) {
		return (o == null) || (o.length <= 0);
	}

	public static boolean isNullOrEmpty(Vector<?> v) {
		return (v == null) || (v.size() <= 0);
	}

	public static boolean isNullOrEmpty(NodeList nl) {
		return (nl == null) || (nl.getLength() <= 0);
	}

	public static String[] copyStringArray(String[] s) {
		if (s != null) {
			int nLen = s.length;
			if (nLen > 0) {
				String[] s1 = new String[nLen];
				for (int i = 0; i < nLen; i++) {
					s1[i] = s[i];
				}
				return s1;
			}
		}
		return null;
	}

	/**
	 * 对double数据进行取精度.
	 * 
	 * @param value
	 *            double数据.
	 * @param scale
	 *            精度位数(保留的小数位数).
	 * @param roundingMode
	 *            精度取值方式.
	 * @return 精度计算后的数据.
	 */
	public static double round(double value, int scale, int roundingMode) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		double d = bd.doubleValue();
		bd = null;
		return d;
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
	
	public static String getNullString(String str){
		if(str == null || str.equals("null"))
			return "";
		return str;
		
		
	}
	
    public static boolean checkDate(String sourceDate){
        if(sourceDate==null){
            return false;
        }
        try {
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
               dateFormat.setLenient(false);
               dateFormat.parse(sourceDate);
               return true;
        } catch (Exception e) {
        	 return false;
        }
    }
    
	public static void main(String[] args) {
		String card="1962-11-11";
		System.out.println(card.indexOf("O"));
		if(card.indexOf("O")>0){
    		card=card.replace("O", "0");
    	}
		System.out.println(card);
	}
	
	/**
	 * @Description: 获取当前浏览器版本信息
	 * @param userAgent
	 * @author: yzh
	 * @date: 2014-9-16 下午04:52:07
	 */
	public static int getIeVersion(String userAgent){
		int fullVersion1 = -1;
		try {
			StringTokenizer st = new StringTokenizer(userAgent, ";");
			if(st.hasMoreTokens()){
				st.nextToken();
				if(st.hasMoreTokens()){
					// 得到用户的浏览器名
					String userBrowser = st.nextToken(); // MSIE 10.0 MSIE 9.0
					StringTokenizer _st = new StringTokenizer(userBrowser, " ");
					if(_st.hasMoreTokens()){
						_st.nextToken();
						if(_st.hasMoreTokens()){
							String _vstr = _st.nextToken();
							if (isNumeric(_vstr) && ComUtil.isNotNullOrEmpty(_vstr)) {
								Float f = (Float.parseFloat(_vstr));
								fullVersion1 = f.intValue();
							}
							//System.out.println("浏览器版本：" + fullVersion);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return fullVersion1;
	}
	
	/**
	 * @Description: 判断是否是数字
	 * @param str
	 * @author: yzh
	 * @date: 2014-9-17 下午03:49:11
	 */
	public static boolean isNumeric(String str){ 
		for (int i = str.length();--i>=0;){ 
			if (!Character.isDigit(str.charAt(i))){ 
				if(!".".equals(str.charAt(i)+"")){
					return false;
				}
			} 
		} 
		return true;
	}
	
	public static boolean isNumber(String str){
		   if(isNullOrEmpty(str)){
				return false; 
		   }
		   Pattern pattern = Pattern.compile("[0-9]*");   
		   Matcher isNum = pattern.matcher(str);  
		   if( !isNum.matches() ){  
		       return false;   
		   }   
		   return true;
	}  
}
