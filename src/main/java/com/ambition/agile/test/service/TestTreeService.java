/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambition.agile.common.service.TreeService;
import com.ambition.agile.common.utils.StringUtils;
import com.ambition.agile.test.dao.TestTreeDao;
import com.ambition.agile.test.entity.TestTree;

/**
 * 树结构生成Service
 * @author harry
 * @version 2015-04-06
 */
@Service
@Transactional(readOnly = true)
public class TestTreeService extends TreeService<TestTreeDao, TestTree> {

	public TestTree get(Integer id) {
		return super.get(id);
	}
	
	public List<TestTree> findList(TestTree testTree) {
		if (StringUtils.isNotBlank(testTree.getParentIds())){
			testTree.setParentIds(","+testTree.getParentIds()+",");
		}
		return super.findList(testTree);
	}
	
	@Transactional(readOnly = false)
	public Integer save(TestTree testTree) {
		return super.save(testTree);
	}
	
	@Transactional(readOnly = false)
	public Integer delete(TestTree testTree) {
		return super.delete(testTree);
	}
	
}