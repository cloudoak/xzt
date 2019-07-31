/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.jzmk.entity.SysConfig;

/**
 * 系统参数配置DAO接口
 * @author system_admin
 * @version 2017-06-26
 */
@MyBatisDao
public interface SysConfigDao extends CrudDao<SysConfig> {
	
	SysConfig findOne(SysConfig sysConfig);
	
}