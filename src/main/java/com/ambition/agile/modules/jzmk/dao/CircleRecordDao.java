/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.jzmk.entity.CircleRecord;

/**
 * 圈子记录DAO接口
 * @author dortan
 * @version 2017-07-09
 */
@MyBatisDao
public interface CircleRecordDao extends CrudDao<CircleRecord> {
	
}