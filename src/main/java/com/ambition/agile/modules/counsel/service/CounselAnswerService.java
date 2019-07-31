/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.counsel.dao.CounselAnswerDao;
import com.ambition.agile.modules.counsel.entity.CounselAnswer;

/**
 * 答疑室回复Service
 * @author harry
 * @version 2017-06-24
 */
@Service
@Transactional(readOnly = true)
public class CounselAnswerService extends CrudService<CounselAnswerDao, CounselAnswer> {

	public CounselAnswer get(Integer id) {
		return super.get(id);
	}
	
	public List<CounselAnswer> findList(CounselAnswer counselAnswer) {
		return super.findList(counselAnswer);
	}
	
	public Page<CounselAnswer> findPage(Page<CounselAnswer> page, CounselAnswer counselAnswer) {
		return super.findPage(page, counselAnswer);
	}
	
	@Transactional(readOnly = false)
	public Integer save(CounselAnswer counselAnswer) {
		return super.save(counselAnswer);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(CounselAnswer counselAnswer) {
		return super.delete(counselAnswer);
	}
	
}