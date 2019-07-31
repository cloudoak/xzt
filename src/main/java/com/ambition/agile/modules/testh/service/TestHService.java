/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.testh.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.modules.testh.dao.TestHDao;
import com.ambition.agile.modules.testh.entity.TestH;

/**
 * cuidService
 * @author harry
 * @version 2017-06-14
 */
@Service
@Transactional(readOnly = true)
public class TestHService extends CrudService<TestHDao, TestH> {

	public TestH get(Integer id) {
		return super.get(id);
	}
	
	public List<TestH> findList(TestH testH) {
		return super.findList(testH);
	}
	
	public Page<TestH> findPage(Page<TestH> page, TestH testH) {
		return super.findPage(page, testH);
	}
	
	@Transactional(readOnly = false)
	public Integer save(TestH testH) {
		return super.save(testH);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(TestH testH) {
		return super.delete(testH);
	}
	
}