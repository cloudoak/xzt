/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.sys.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.sys.entity.Share;

/**
 * 共享积分DAO接口
 * @author shenclus
 * @version 2017-12-11
 */
@MyBatisDao
public interface ShareDao extends CrudDao<Share> {
	
}