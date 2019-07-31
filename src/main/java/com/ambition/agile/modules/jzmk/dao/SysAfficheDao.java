/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.dao;

import java.util.List;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.jzmk.entity.SysAffiche;

/**
 * 系统公告管理DAO接口
 * @author system_admin
 * @version 2017-06-26
 */
@MyBatisDao
public interface SysAfficheDao extends CrudDao<SysAffiche> {
	
	List<SysAffiche> findTop3List(SysAffiche sysAffiche);
	
}