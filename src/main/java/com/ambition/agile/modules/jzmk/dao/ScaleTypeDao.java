/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.jzmk.entity.ScaleType;

/**
 * 量表类别DAO接口
 * @author dortan
 * @version 2017-08-25
 */
@MyBatisDao
public interface ScaleTypeDao extends CrudDao<ScaleType> {
	
}