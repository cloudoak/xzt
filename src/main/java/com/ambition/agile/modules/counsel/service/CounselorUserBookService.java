/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.counsel.dao.CounselorUserBookDao;
import com.ambition.agile.modules.counsel.entity.CounselorUserBook;

/**
 * 咨询预约Service
 * @author harry
 * @version 2017-07-06
 */
@Service
@Transactional(readOnly = true)
public class CounselorUserBookService extends CrudService<CounselorUserBookDao, CounselorUserBook> {

	public CounselorUserBook get(Integer id) {
		return super.get(id);
	}
	
	public List<CounselorUserBook> findList(CounselorUserBook counselorUserBook) {
		return super.findList(counselorUserBook);
	}
	
	public Page<CounselorUserBook> findPage(Page<CounselorUserBook> page, CounselorUserBook counselorUserBook) {
		return super.findPage(page, counselorUserBook);
	}
	
	@Transactional(readOnly = false)
	public Integer save(CounselorUserBook counselorUserBook) {
		return super.save(counselorUserBook);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(CounselorUserBook counselorUserBook) {
		return super.delete(counselorUserBook);
	}
	
}