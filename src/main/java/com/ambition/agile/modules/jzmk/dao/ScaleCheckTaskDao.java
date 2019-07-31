/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.jzmk.entity.ScaleCheckTask;

/**
 * 量表评测任务DAO接口
 * @author dortan
 * @version 2017-07-01
 */
@MyBatisDao
public interface ScaleCheckTaskDao extends CrudDao<ScaleCheckTask> {
	
	public Integer getBatchNumber();
	
}