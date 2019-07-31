/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.relax.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.relax.entity.IntegralUser;

/**
 * 用户积分DAO接口
 * @author harry
 * @version 2017-09-04
 */
@MyBatisDao
public interface IntegralUserDao extends CrudDao<IntegralUser> {
	
	IntegralUser getIntegralUser(IntegralUser integralUser);
	
}