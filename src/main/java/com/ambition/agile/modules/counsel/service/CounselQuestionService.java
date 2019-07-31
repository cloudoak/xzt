/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.counsel.dao.CounselQuestionDao;
import com.ambition.agile.modules.counsel.entity.CounselQuestion;

/**
 * 答疑室问题Service
 * @author harry
 * @version 2017-07-06
 */
@Service
@Transactional(readOnly = true)
public class CounselQuestionService extends CrudService<CounselQuestionDao, CounselQuestion> {

	public CounselQuestion get(Integer id) {
		return super.get(id);
	}
	
	public List<CounselQuestion> findList(CounselQuestion counselQuestion) {
		return super.findList(counselQuestion);
	}
	
	public Page<CounselQuestion> findPage(Page<CounselQuestion> page, CounselQuestion counselQuestion) {
		return super.findPage(page, counselQuestion);
	}
	
	@Transactional(readOnly = false)
	public Integer save(CounselQuestion counselQuestion) {
		return super.save(counselQuestion);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(CounselQuestion counselQuestion) {
		return super.delete(counselQuestion);
	}
	
}