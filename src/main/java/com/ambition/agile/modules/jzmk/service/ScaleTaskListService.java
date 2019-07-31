/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.jzmk.dao.ScaleTaskListDao;
import com.ambition.agile.modules.jzmk.entity.ScaleTaskList;

/**
 * 任务测试量表Service
 * @author dortan
 * @version 2017-07-01
 */
@Service
@Transactional(readOnly = true)
public class ScaleTaskListService extends CrudService<ScaleTaskListDao, ScaleTaskList> {

	public ScaleTaskList get(Integer id) {
		return super.get(id);
	}
	
	public List<ScaleTaskList> findList(ScaleTaskList scaleTaskList) {
		return super.findList(scaleTaskList);
	}
	
	public Page<ScaleTaskList> findPage(Page<ScaleTaskList> page, ScaleTaskList scaleTaskList) {
		return super.findPage(page, scaleTaskList);
	}
	
	@Transactional(readOnly = false)
	public Integer save(ScaleTaskList scaleTaskList) {
		return super.save(scaleTaskList);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(ScaleTaskList scaleTaskList) {
		return super.delete(scaleTaskList);
	}
	
}