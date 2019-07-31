/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.counsel.dao.CounselorDao;
import com.ambition.agile.modules.counsel.dao.CounselorScheduleDao;
import com.ambition.agile.modules.counsel.entity.Counselor;
import com.ambition.agile.modules.counsel.entity.CounselorSchedule;

/**
 * 咨询师排班Service
 * @author harry
 * @version 2017-07-03
 */
@Service
@Transactional(readOnly = true)
public class CounselorScheduleService extends CrudService<CounselorScheduleDao, CounselorSchedule> {

	@Autowired
	public CounselorScheduleDao counselorScheduleDao;
	
	
	public CounselorSchedule get(Integer id) {
		return super.get(id);
	}
	
	public List<CounselorSchedule> findList(CounselorSchedule counselorSchedule) {
		return super.findList(counselorSchedule);
	}
	
	public Page<CounselorSchedule> findPage(Page<CounselorSchedule> page, CounselorSchedule counselorSchedule) {
		return super.findPage(page, counselorSchedule);
	}
	
	//用于 显示排班信息
	public List<Counselor> findCsList(CounselorSchedule counselorSchedule) {
		return counselorScheduleDao.findCsList(counselorSchedule);
	}
	
	public List<CounselorSchedule> findCompareTime(CounselorSchedule counselorSchedule) {
		return counselorScheduleDao.findCompareTime(counselorSchedule);
	}
	
	@Transactional(readOnly = false)
	public Integer save(CounselorSchedule counselorSchedule) {
		return super.save(counselorSchedule);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(CounselorSchedule counselorSchedule) {
		return super.delete(counselorSchedule);
	}
	
}