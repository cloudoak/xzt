/**
 * Copyright &copy; 2012-2016  All rights reserved.
 */
package com.ambition.agile.modules.act.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.act.entity.Act;

/**
 * 审批DAO接口
 * @author harry
 * @version 2014-05-16
 */
@MyBatisDao
public interface ActDao extends CrudDao<Act> {

	public int updateProcInsIdByBusinessId(Act act);
	
}
