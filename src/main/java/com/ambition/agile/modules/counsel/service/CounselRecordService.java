/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.counsel.entity.CounselRecord;
import com.ambition.agile.modules.counsel.dao.CounselRecordDao;

/**
 * 咨询记录Service
 * @author harry
 * @version 2017-08-15
 */
@Service
@Transactional(readOnly = true)
public class CounselRecordService extends CrudService<CounselRecordDao, CounselRecord> {

	public CounselRecord get(Integer id) {
		return super.get(id);
	}
	
	public List<CounselRecord> findList(CounselRecord counselRecord) {
		return super.findList(counselRecord);
	}
	
	public Page<CounselRecord> findPage(Page<CounselRecord> page, CounselRecord counselRecord) {
		return super.findPage(page, counselRecord);
	}
	
	@Transactional(readOnly = false)
	public Integer save(CounselRecord counselRecord) {
		return super.save(counselRecord);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(CounselRecord counselRecord) {
		return super.delete(counselRecord);
	}
	
}