/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.ante.entity.CounselorType;
import com.ambition.agile.modules.ante.dao.CounselorTypeDao;

/**
 * 教师类型Service
 * @author wyz
 * @version 2017-09-07
 */
@Service
@Transactional(readOnly = true)
public class CounselorTypeService extends CrudService<CounselorTypeDao, CounselorType> {

	public CounselorType get(Integer id) {
		return super.get(id);
	}
	
	public List<CounselorType> findList(CounselorType counselorType) {
		return super.findList(counselorType);
	}
	
	public Page<CounselorType> findPage(Page<CounselorType> page, CounselorType counselorType) {
		return super.findPage(page, counselorType);
	}
	
	@Transactional(readOnly = false)
	public Integer save(CounselorType counselorType) {
		return super.save(counselorType);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(CounselorType counselorType) {
		return super.delete(counselorType);
	}
	
}