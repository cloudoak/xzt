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
import com.ambition.agile.modules.counsel.entity.RecordMonitor;
import com.ambition.agile.modules.counsel.dao.RecordMonitorDao;

/**
 * 咨询督导Service
 * @author harry
 * @version 2017-08-16
 */
@Service
@Transactional(readOnly = true)
public class RecordMonitorService extends CrudService<RecordMonitorDao, RecordMonitor> {
	
	@Autowired
	private RecordMonitorDao recordMonitorDao;
	
	public RecordMonitor get(Integer id) {
		return super.get(id);
	}
	
	public List<RecordMonitor> findList(RecordMonitor recordMonitor) {
		return super.findList(recordMonitor);
	}
	
	public Page<RecordMonitor> findPage(Page<RecordMonitor> page, RecordMonitor recordMonitor) {
		return super.findPage(page, recordMonitor);
	}
	
	@Transactional(readOnly = false)
	public Integer save(RecordMonitor recordMonitor) {
		return super.save(recordMonitor);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(RecordMonitor recordMonitor) {
		return super.delete(recordMonitor);
	}
	
	@Transactional(readOnly = false)
	public void deleteByRecord(RecordMonitor recordMonitor){
		recordMonitorDao.deleteByRecord(recordMonitor);
	}
	
}