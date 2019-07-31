/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.ante.entity.CounselorType;

/**
 * 教师类型DAO接口
 * @author wyz
 * @version 2017-09-07
 */
@MyBatisDao
public interface CounselorTypeDao extends CrudDao<CounselorType> {
	
}