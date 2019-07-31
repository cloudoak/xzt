/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.jzmk.entity.ScaleTaskAnswer;

/**
 * 测评人员答题DAO接口
 * @author dortan
 * @version 2017-07-01
 */
@MyBatisDao
public interface ScaleTaskAnswerDao extends CrudDao<ScaleTaskAnswer> {
	
	Double getSummayScore(ScaleTaskAnswer scaleTaskAnswer);
	
	Double getSummaryGeneScore(ScaleTaskAnswer scaleTaskAnswer);
	
	Double getMaxGeneScore(ScaleTaskAnswer scaleTaskAnswer);
	
	ScaleTaskAnswer getScaleTaskAnswer(ScaleTaskAnswer scaleTaskAnswer);
	
	List<Integer> findByTaskNumberAndTaskUserIdAndSid(ScaleTaskAnswer scaleTaskAnswer);
	
}