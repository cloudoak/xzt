/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.counsel.entity.ScheduleDay;

/**
 * 日程安排天DAO接口
 * @author harry
 * @version 2017-08-22
 */
@MyBatisDao
public interface ScheduleDayDao extends CrudDao<ScheduleDay> {
	
}