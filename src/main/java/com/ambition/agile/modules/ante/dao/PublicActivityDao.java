/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.dao;

import java.util.List;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.ante.entity.PublicActivity;

/**
 * 公益活动DAO接口
 * @author fengqq
 * @version 2017-07-12
 */
@MyBatisDao
public interface PublicActivityDao extends CrudDao<PublicActivity> {
	
	List<PublicActivity> findTop3List(PublicActivity publicActivity);
	
}