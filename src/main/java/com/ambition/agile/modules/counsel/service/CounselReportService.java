/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.counsel.dao.CounselReportDao;
import com.ambition.agile.modules.counsel.entity.CounselReport;

/**
 * 咨询报表Service
 * @author harry
 * @version 2017-07-06
 */
@Service
@Transactional(readOnly = true)
public class CounselReportService extends CrudService<CounselReportDao, CounselReport> {

	public CounselReport get(Integer id) {
		return super.get(id);
	}
	
	public List<CounselReport> findList(CounselReport counselReport) {
		return super.findList(counselReport);
	}
	
	public Page<CounselReport> findPage(Page<CounselReport> page, CounselReport counselReport) {
		return super.findPage(page, counselReport);
	}
	
	@Transactional(readOnly = false)
	public Integer save(CounselReport counselReport) {
		return super.save(counselReport);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(CounselReport counselReport) {
		return super.delete(counselReport);
	}
	
}