/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.testh.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.testh.entity.TestH;

/**
 * cuidDAO接口
 * @author harry
 * @version 2017-06-14
 */
@MyBatisDao
public interface TestHDao extends CrudDao<TestH> {
	
}