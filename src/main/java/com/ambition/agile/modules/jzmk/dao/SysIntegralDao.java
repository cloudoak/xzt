/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.jzmk.entity.SysIntegral;

/**
 * 系统积分设置DAO接口
 * @author system_admin
 * @version 2017-07-05
 */
@MyBatisDao
public interface SysIntegralDao extends CrudDao<SysIntegral> {
	
	SysIntegral findOne(SysIntegral sysIntegral);
	
}