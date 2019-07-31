/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.counsel.entity.ScheduleDay;
import com.ambition.agile.modules.counsel.dao.ScheduleDayDao;

/**
 * 日程安排天Service
 * @author harry
 * @version 2017-08-22
 */
@Service
@Transactional(readOnly = true)
public class ScheduleDayService extends CrudService<ScheduleDayDao, ScheduleDay> {

	public ScheduleDay get(Integer id) {
		return super.get(id);
	}
	
	public List<ScheduleDay> findList(ScheduleDay scheduleDay) {
		return super.findList(scheduleDay);
	}
	
	public Page<ScheduleDay> findPage(Page<ScheduleDay> page, ScheduleDay scheduleDay) {
		return super.findPage(page, scheduleDay);
	}
	
	@Transactional(readOnly = false)
	public Integer save(ScheduleDay scheduleDay) {
		return super.save(scheduleDay);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(ScheduleDay scheduleDay) {
		return super.delete(scheduleDay);
	}
	
}