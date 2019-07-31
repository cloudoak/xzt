/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.jzmk.entity.Circle;

/**
 * 圈子DAO接口
 * @author dortan
 * @version 2017-07-09
 */
@MyBatisDao
public interface CircleDao extends CrudDao<Circle> {
	public int update(Circle circle);
}