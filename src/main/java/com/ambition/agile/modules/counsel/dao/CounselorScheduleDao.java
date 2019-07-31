/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.dao;

import java.util.List;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.counsel.entity.Counselor;
import com.ambition.agile.modules.counsel.entity.CounselorSchedule;

/**
 * 咨询师排班DAO接口
 * @author harry
 * @version 2017-07-03
 */
@MyBatisDao
public interface CounselorScheduleDao extends CrudDao<CounselorSchedule> {
	//用于显示每个老师7天的所有的排课信息
	public List<Counselor> findCsList(CounselorSchedule counselorSchedule);
	
	public List<CounselorSchedule> findCompareTime(CounselorSchedule counselorSchedule);
	
}