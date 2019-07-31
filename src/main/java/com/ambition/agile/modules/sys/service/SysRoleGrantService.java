/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.sys.entity.SysRoleGrant;
import com.ambition.agile.modules.sys.dao.SysRoleGrantDao;

/**
 * 角色权限Service
 * @author dortan
 * @version 2017-08-28
 */
@Service
@Transactional(readOnly = true)
public class SysRoleGrantService extends CrudService<SysRoleGrantDao, SysRoleGrant> {

	public SysRoleGrant get(Integer id) {
		return super.get(id);
	}
	
	public List<SysRoleGrant> findList(SysRoleGrant sysRoleGrant) {
		return super.findList(sysRoleGrant);
	}
	
	public Page<SysRoleGrant> findPage(Page<SysRoleGrant> page, SysRoleGrant sysRoleGrant) {
		return super.findPage(page, sysRoleGrant);
	}
	
	@Transactional(readOnly = false)
	public Integer save(SysRoleGrant sysRoleGrant) {
		return super.save(sysRoleGrant);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(SysRoleGrant sysRoleGrant) {
		return super.delete(sysRoleGrant);
	}
	
}