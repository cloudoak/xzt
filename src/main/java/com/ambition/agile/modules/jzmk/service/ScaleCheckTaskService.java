/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.jzmk.dao.ScaleCheckTaskDao;
import com.ambition.agile.modules.jzmk.dao.ScaleTaskAnswerDao;
import com.ambition.agile.modules.jzmk.entity.ScaleCheckTask;
import com.ambition.agile.modules.jzmk.entity.ScaleTaskAnswer;

/**
 * 量表评测任务Service
 * @author dortan
 * @version 2017-07-01
 */
@Service
@Transactional(readOnly = true)
public class ScaleCheckTaskService extends CrudService<ScaleCheckTaskDao, ScaleCheckTask> {
	
	@Autowired ScaleCheckTaskDao scaleCheckTaskDao;

	public ScaleCheckTask get(Integer id) {
		return super.get(id);
	}
	
	public List<ScaleCheckTask> findList(ScaleCheckTask scaleCheckTask) {
		return super.findList(scaleCheckTask);
	}
	
	public Page<ScaleCheckTask> findPage(Page<ScaleCheckTask> page, ScaleCheckTask scaleCheckTask) {
		return super.findPage(page, scaleCheckTask);
	}
	
	@Transactional(readOnly = false)
	public Integer save(ScaleCheckTask scaleCheckTask) {
		return super.save(scaleCheckTask);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(ScaleCheckTask scaleCheckTask) {
		return super.delete(scaleCheckTask);
	}
	
	public Integer getBatchNumber() {
		return scaleCheckTaskDao.getBatchNumber();
	}
	
}