/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.counsel.entity.CounselAnswer;

/**
 * 答疑室回复DAO接口
 * @author harry
 * @version 2017-06-24
 */
@MyBatisDao
public interface CounselAnswerDao extends CrudDao<CounselAnswer> {
	
}