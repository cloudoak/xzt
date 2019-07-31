/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.dao;

import java.util.List;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.jzmk.entity.ScaleTaskUser;

/**
 * 任务测评人员DAO接口
 * @author dortan
 * @version 2017-07-01
 */
@MyBatisDao
public interface ScaleTaskUserDao extends CrudDao<ScaleTaskUser> {
	List<ScaleTaskUser> selectDcScalesByUser(ScaleTaskUser scaleTaskUser);

	List<ScaleTaskUser> selectDcScalesCheckingByUser(ScaleTaskUser scaleTaskUser);
	
	List<ScaleTaskUser> selectCcScalesCheckingByUser(ScaleTaskUser scaleTaskUser);

	List<ScaleTaskUser> selectLcScalesByUser(ScaleTaskUser scaleTaskUser);
	
	int update(ScaleTaskUser scaleTaskUser);
	
	int updateForTask(ScaleTaskUser scaleTaskUser);
	
	ScaleTaskUser getForTask(ScaleTaskUser scaleTaskUser);
	
	ScaleTaskUser getStatisticResult(ScaleTaskUser scaleTaskUser);
}