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
import com.ambition.agile.modules.act.dao.ActDao;
import com.ambition.agile.modules.act.entity.Act;
import com.ambition.agile.modules.counsel.dao.CounselCenterDao;
import com.ambition.agile.modules.counsel.entity.CounselCenter;

/**
 * 咨询中心Service
 * @author harry
 * @version 2017-06-22
 */
@Service
@Transactional(readOnly = true)
public class CounselCenterService extends CrudService<CounselCenterDao, CounselCenter> {

	@Autowired
	private CounselCenterDao counselCenterDao;
	
	public CounselCenter get(Integer id) {
		return super.get(id);
	}
	
	public CounselCenter getCounselCenterByOrgId(Integer orgId) {
		return counselCenterDao.getCounselCenterByOrgId(orgId);
	}
	
	public List<CounselCenter> findList(CounselCenter counselCenter) {
		return super.findList(counselCenter);
	}
	
	public Page<CounselCenter> findPage(Page<CounselCenter> page, CounselCenter counselCenter) {
		return super.findPage(page, counselCenter);
	}
	
	@Transactional(readOnly = false)
	public Integer save(CounselCenter counselCenter) {
		return super.save(counselCenter);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(CounselCenter counselCenter) {
		return super.delete(counselCenter);
	}
	
}