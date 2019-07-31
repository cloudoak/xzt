/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.counsel.entity.CounselCenter;
import com.ambition.agile.modules.counsel.entity.RecordMonitor;

/**
 * 咨询督导DAO接口
 * @author harry
 * @version 2017-08-16
 */
@MyBatisDao
public interface RecordMonitorDao extends CrudDao<RecordMonitor> {
	
	public void deleteByRecord(RecordMonitor recordMonitor);
	
	
}