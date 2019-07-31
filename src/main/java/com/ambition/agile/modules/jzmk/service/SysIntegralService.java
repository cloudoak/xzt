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
import com.ambition.agile.modules.jzmk.dao.SysIntegralDao;
import com.ambition.agile.modules.jzmk.entity.SysIntegral;

/**
 * 系统积分设置Service
 * @author system_admin
 * @version 2017-07-05
 */
@Service
@Transactional(readOnly = true)
public class SysIntegralService extends CrudService<SysIntegralDao, SysIntegral> {
	
	@Autowired
	private SysIntegralDao sysIntegralDao;

	public SysIntegral get(Integer id) {
		return super.get(id);
	}
	
	public SysIntegral findOne() {
		return sysIntegralDao.findOne(new SysIntegral());
	}
	
	public List<SysIntegral> findList(SysIntegral sysIntegral) {
		return super.findList(sysIntegral);
	}
	
	public Page<SysIntegral> findPage(Page<SysIntegral> page, SysIntegral sysIntegral) {
		return super.findPage(page, sysIntegral);
	}
	
	@Transactional(readOnly = false)
	public Integer save(SysIntegral sysIntegral) {
		return super.save(sysIntegral);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(SysIntegral sysIntegral) {
		return super.delete(sysIntegral);
	}
	
}