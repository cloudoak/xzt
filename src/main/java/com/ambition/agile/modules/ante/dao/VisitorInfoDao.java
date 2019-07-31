/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.dao;

import java.util.List;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.ante.entity.VisitorInfo;

/**
 * 来访者DAO接口
 * @author dortan
 * @version 2017-08-04
 */
@MyBatisDao
public interface VisitorInfoDao extends CrudDao<VisitorInfo> {
	
	public List<VisitorInfo> getVisitorInfoList(VisitorInfo visitorInfo);
	
	public List<VisitorInfo> getVisitorInfoScoreList(VisitorInfo visitorInfo);
	
	public VisitorInfo getUserByUserId(Integer userId);
	
}