/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.test.dao;

import com.ambition.agile.common.persistence.TreeDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.test.entity.TestTree;

/**
 * 树结构生成DAO接口
 * @author harry
 * @version 2015-04-06
 */
@MyBatisDao
public interface TestTreeDao extends TreeDao<TestTree> {
	
}