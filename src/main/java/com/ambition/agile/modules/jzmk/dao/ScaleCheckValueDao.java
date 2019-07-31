/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.jzmk.entity.ScaleCheckValue;

/**
 * 测评评分DAO接口
 * @author wyz
 * @version 2017-12-03
 */
@MyBatisDao
public interface ScaleCheckValueDao extends CrudDao<ScaleCheckValue> {
	
}