/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.counsel.dao.CounselQuestionTypeDao;
import com.ambition.agile.modules.counsel.entity.CounselQuestionType;

/**
 * 咨询问题类型Service
 * @author OAK
 * @version 2017/12/02
 */
@Service
@Transactional(readOnly = true)
public class CounselQuestionTypeService extends CrudService<CounselQuestionTypeDao, CounselQuestionType> {

	public CounselQuestionType get(Integer id) {
		return super.get(id);
	}
	
	public List<CounselQuestionType> findList(CounselQuestionType counselQuestion) {
		return super.findList(counselQuestion);
	}
	
	public Page<CounselQuestionType> findPage(Page<CounselQuestionType> page, CounselQuestionType counselQuestionType) {
		return super.findPage(page, counselQuestionType);
	}
	
	@Transactional(readOnly = false)
	public Integer save(CounselQuestionType counselQuestionType) {
		return super.save(counselQuestionType);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(CounselQuestionType counselQuestionType) {
		return super.delete(counselQuestionType);
	}
	
}