/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.jzmk.entity.ScaleCheckScore;

/**
 * 测评得分DAO接口
 * @author wyz
 * @version 2017-11-23
 */
@MyBatisDao
public interface ScaleCheckScoreDao extends CrudDao<ScaleCheckScore> {
	
	Integer getTotalCheckCount(ScaleCheckScore scaleCheckScore);
	Integer getAbnormalCount(ScaleCheckScore scaleCheckScore);
	
}