/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.relax.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.relax.dao.SuccessCaseDao;
import com.ambition.agile.modules.relax.entity.SuccessCase;

/**
 * 成功案例Service
 * @author harry
 * @version 2017-06-25
 */
@Service
@Transactional(readOnly = true)
public class SuccessCaseService extends CrudService<SuccessCaseDao, SuccessCase> {

	public SuccessCase get(Integer id) {
		return super.get(id);
	}
	
	public List<SuccessCase> findList(SuccessCase successCase) {
		return super.findList(successCase);
	}
	
	public Page<SuccessCase> findPage(Page<SuccessCase> page, SuccessCase successCase) {
		return super.findPage(page, successCase);
	}
	
	@Transactional(readOnly = false)
	public Integer save(SuccessCase successCase) {
		return super.save(successCase);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(SuccessCase successCase) {
		return super.delete(successCase);
	}
	
}