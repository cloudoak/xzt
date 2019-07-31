/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.sys.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.sys.entity.SysRoleGrant;

/**
 * 角色权限DAO接口
 * @author dortan
 * @version 2017-08-28
 */
@MyBatisDao
public interface SysRoleGrantDao extends CrudDao<SysRoleGrant> {
	
}