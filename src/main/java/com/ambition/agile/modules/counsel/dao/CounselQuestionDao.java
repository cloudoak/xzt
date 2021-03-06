/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.counsel.entity.CounselQuestion;

/**
 * 答疑室问题DAO接口
 * @author harry
 * @version 2017-07-06
 */
@MyBatisDao
public interface CounselQuestionDao extends CrudDao<CounselQuestion> {
	
}