/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.counsel.entity.ScheduleRegular;
import com.ambition.agile.modules.counsel.dao.ScheduleRegularDao;

/**
 * 日程安排常规Service
 * @author harry
 * @version 2017-08-22
 */
@Service
@Transactional(readOnly = true)
public class ScheduleRegularService extends CrudService<ScheduleRegularDao, ScheduleRegular> {

	public ScheduleRegular get(Integer id) {
		return super.get(id);
	}
	
	public List<ScheduleRegular> findList(ScheduleRegular scheduleRegular) {
		return super.findList(scheduleRegular);
	}
	
	public Page<ScheduleRegular> findPage(Page<ScheduleRegular> page, ScheduleRegular scheduleRegular) {
		return super.findPage(page, scheduleRegular);
	}
	
	@Transactional(readOnly = false)
	public Integer save(ScheduleRegular scheduleRegular) {
		return super.save(scheduleRegular);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(ScheduleRegular scheduleRegular) {
		return super.delete(scheduleRegular);
	}
	
}