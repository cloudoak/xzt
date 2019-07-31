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
import com.ambition.agile.modules.jzmk.dao.SysAfficheDao;
import com.ambition.agile.modules.jzmk.entity.SysAffiche;

/**
 * 系统公告管理Service
 * @author system_admin
 * @version 2017-06-26
 */
@Service
@Transactional(readOnly = true)
public class SysAfficheService extends CrudService<SysAfficheDao, SysAffiche> {
	
	@Autowired
	SysAfficheDao sysAfficheDao;

	public SysAffiche get(Integer id) {
		return super.get(id);
	}
	
	public List<SysAffiche> findList(SysAffiche sysAffiche) {
		return super.findList(sysAffiche);
	}
	
	public List<SysAffiche> findTop3List(SysAffiche sysAffiche){
		return sysAfficheDao.findTop3List(sysAffiche);
	}
	
	public Page<SysAffiche> findPage(Page<SysAffiche> page, SysAffiche sysAffiche) {
		return super.findPage(page, sysAffiche);
	}
	
	@Transactional(readOnly = false)
	public Integer save(SysAffiche sysAffiche) {
		return super.save(sysAffiche);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(SysAffiche sysAffiche) {
		return super.delete(sysAffiche);
	}
	
}