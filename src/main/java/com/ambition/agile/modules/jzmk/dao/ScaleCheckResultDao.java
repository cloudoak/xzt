/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.dao;

import java.util.List;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.Page;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.jzmk.entity.ScaleCheckResult;

/**
 * 评测结果DAO接口
 * @author dortan
 * @version 2017-07-01
 */
@MyBatisDao
public interface ScaleCheckResultDao extends CrudDao<ScaleCheckResult> {
	
	ScaleCheckResult getScaleCheckResult(ScaleCheckResult scaleCheckResult);
	List<ScaleCheckResult> findExportList(ScaleCheckResult scaleCheckResult);
	List<ScaleCheckResult> findScoreList(ScaleCheckResult scaleCheckResult);
}