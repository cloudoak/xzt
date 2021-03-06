/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.course.dao;

import com.ambition.agile.common.persistence.TreeDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.course.entity.CourseCatalog;

/**
 * 课件分类DAO接口
 * @author harry
 * @version 2017-06-24
 */
@MyBatisDao
public interface CourseCatalogDao extends TreeDao<CourseCatalog> {
	
}