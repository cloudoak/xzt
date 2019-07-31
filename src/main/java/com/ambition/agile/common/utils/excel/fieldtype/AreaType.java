/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.common.utils.excel.fieldtype;

import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.modules.sys.entity.Area;
import com.ambition.agile.modules.sys.utils.UserUtils;

/**
 * 字段类型转换
 * @author harry
 * @version 2015-03-10
 */
public class AreaType {

	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {
		for (Area e : UserUtils.getAreaList()){
			if (StringUtils.trimToEmpty(val).equals(e.getName())){
				return e;
			}
		}
		return null;
	}

	/**
	 * 获取对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null && ((Area)val).getName() != null){
			return ((Area)val).getName();
		}
		return "";
	}
}
