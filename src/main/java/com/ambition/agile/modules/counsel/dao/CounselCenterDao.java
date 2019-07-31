/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.act.entity.Act;
import com.ambition.agile.modules.counsel.entity.CounselCenter;

/**
 * 咨询中心DAO接口
 * @author harry
 * @version 2017-06-22
 */
@MyBatisDao
public interface CounselCenterDao extends CrudDao<CounselCenter> {
	
	public CounselCenter getCounselCenterByOrgId(Integer orgId);
	
}