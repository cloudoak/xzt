/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.jzmk.entity.OrgScale;
import com.ambition.agile.modules.jzmk.dao.OrgScaleDao;

/**
 * 分配给机构的量表Service
 * @author dorstan
 * @version 2017-09-08
 */
@Service
@Transactional(readOnly = true)
public class OrgScaleService extends CrudService<OrgScaleDao, OrgScale> {

	public OrgScale get(Integer id) {
		return super.get(id);
	}
	
	public List<OrgScale> findList(OrgScale orgScale) {
		return super.findList(orgScale);
	}
	
	public Page<OrgScale> findPage(Page<OrgScale> page, OrgScale orgScale) {
		return super.findPage(page, orgScale);
	}
	
	@Transactional(readOnly = false)
	public Integer save(OrgScale orgScale) {
		return super.save(orgScale);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(OrgScale orgScale) {
		return super.delete(orgScale);
	}
	
}