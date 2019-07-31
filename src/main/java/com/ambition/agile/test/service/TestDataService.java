/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.test.dao.TestDataDao;
import com.ambition.agile.test.entity.TestData;

/**
 * 单表生成Service
 * @author harry
 * @version 2015-04-06
 */
@Service
@Transactional(readOnly = true)
public class TestDataService extends CrudService<TestDataDao, TestData> {

	public TestData get(Integer id) {
		return super.get(id);
	}
	
	public List<TestData> findList(TestData testData) {
		return super.findList(testData);
	}
	
	public Page<TestData> findPage(Page<TestData> page, TestData testData) {
		return super.findPage(page, testData);
	}
	
	@Transactional(readOnly = false)
	public Integer save(TestData testData) {
		return super.save(testData);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(TestData testData) {
		return super.delete(testData);
	}
	
}