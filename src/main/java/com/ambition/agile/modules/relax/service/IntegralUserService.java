/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.relax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.relax.entity.IntegralUser;
import com.ambition.agile.modules.relax.dao.IntegralUserDao;

/**
 * 用户积分Service
 * @author harry
 * @version 2017-09-04
 */
@Service
@Transactional(readOnly = true)
public class IntegralUserService extends CrudService<IntegralUserDao, IntegralUser> {

	
	@Autowired
	IntegralUserDao integralUserDao;
	
	public IntegralUser get(Integer id) {
		return super.get(id);
	}
	
	public IntegralUser getIntegralUser(IntegralUser integralUser) {
		return integralUserDao.getIntegralUser(integralUser);
	}
	
	public List<IntegralUser> findList(IntegralUser integralUser) {
		return super.findList(integralUser);
	}
	
	public Page<IntegralUser> findPage(Page<IntegralUser> page, IntegralUser integralUser) {
		return super.findPage(page, integralUser);
	}
	
	@Transactional(readOnly = false)
	public Integer save(IntegralUser integralUser) {
		return super.save(integralUser);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(IntegralUser integralUser) {
		return super.delete(integralUser);
	}
	
}