package com.ambition.agile.modules.utils;

/**
 * 字符串处理功能函数类
 * @author hkzr
 *
 */
public class StringFunctionUtils {
	
	/**
	 * 处理webJsp页面的特殊符号转换
	 * @param tagContent
	 * @return
	 */
	public static String replaceWebJsp(String tagContent){
		tagContent = tagContent.replace("&lt;", "<");
		tagContent = tagContent.replace("&gt;", ">");
		tagContent = tagContent.replace("&quot;", "\"");
		tagContent = tagContent.replace("&amp;", "&");
		//tagContent = tagContent.replace("&nbsp;", " ");
		return tagContent;
	}
	
}
