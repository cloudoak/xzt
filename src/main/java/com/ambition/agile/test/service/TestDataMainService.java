/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.service.CrudService;
import com.ambition.agile.test.dao.TestDataChildDao;
import com.ambition.agile.test.dao.TestDataMainDao;
import com.ambition.agile.test.entity.TestDataChild;
import com.ambition.agile.test.entity.TestDataMain;

/**
 * 主子表生成Service
 * @author harry
 * @version 2015-04-06
 */
@Service
@Transactional(readOnly = true)
public class TestDataMainService extends CrudService<TestDataMainDao, TestDataMain> {

	@Autowired
	private TestDataChildDao testDataChildDao;
	
	public TestDataMain get(Integer id) {
		TestDataMain testDataMain = super.get(id);
		testDataMain.setTestDataChildList(testDataChildDao.findList(new TestDataChild(testDataMain)));
		return testDataMain;
	}
	
	public List<TestDataMain> findList(TestDataMain testDataMain) {
		return super.findList(testDataMain);
	}
	
	public Page<TestDataMain> findPage(Page<TestDataMain> page, TestDataMain testDataMain) {
		return super.findPage(page, testDataMain);
	}
	
	@Transactional(readOnly = false)
	public Integer save(TestDataMain testDataMain) {
		Integer  b = super.save(testDataMain);
		for (TestDataChild testDataChild : testDataMain.getTestDataChildList()){
			if (testDataChild.getId() == null){
				continue;
			}
			if (TestDataChild.DEL_FLAG_NORMAL.equals(testDataChild.getDelFlag())){
				if (testDataChild.getId()==null||testDataChild.getId()==0){
					testDataChild.setTestDataMain(testDataMain);
					testDataChild.preInsert();
					testDataChildDao.insert(testDataChild);
				}else{
					testDataChild.preUpdate();
					testDataChildDao.update(testDataChild);
				}
			}else{
				testDataChildDao.delete(testDataChild);
			}
		}
		return b;
	}
	
	@Transactional(readOnly = false)
	public Integer delete(TestDataMain testDataMain) {
		Integer  b = super.delete(testDataMain);
		testDataChildDao.delete(new TestDataChild(testDataMain));
		return b;
	}
	
}