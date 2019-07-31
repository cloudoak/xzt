/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.jzmk.entity.ScoringFormula;

/**
 * 计分方式DAO接口
 * @author dortan
 * @version 2017-08-18
 */
@MyBatisDao
public interface ScoringFormulaDao extends CrudDao<ScoringFormula> {
	
}