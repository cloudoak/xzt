/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.jzmk.dao.CircleRecordDao;
import com.ambition.agile.modules.jzmk.entity.CircleRecord;

/**
 * 圈子记录Service
 * @author dortan
 * @version 2017-07-09
 */
@Service
@Transactional(readOnly = true)
public class CircleRecordService extends CrudService<CircleRecordDao, CircleRecord> {

	public CircleRecord get(Integer id) {
		return super.get(id);
	}
	
	public List<CircleRecord> findList(CircleRecord circleRecord) {
		return super.findList(circleRecord);
	}
	
	public Page<CircleRecord> findPage(Page<CircleRecord> page, CircleRecord circleRecord) {
		return super.findPage(page, circleRecord);
	}
	
	@Transactional(readOnly = false)
	public Integer save(CircleRecord circleRecord) {
		return super.save(circleRecord);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(CircleRecord circleRecord) {
		return super.delete(circleRecord);
	}
	
}