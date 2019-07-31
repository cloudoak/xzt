/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.jzmk.entity.OrgScale;

/**
 * 分配给机构的量表DAO接口
 * @author dorstan
 * @version 2017-09-08
 */
@MyBatisDao
public interface OrgScaleDao extends CrudDao<OrgScale> {
	
}