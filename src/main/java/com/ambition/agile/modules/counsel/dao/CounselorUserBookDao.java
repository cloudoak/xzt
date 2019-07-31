/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.counsel.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.counsel.entity.CounselorUserBook;

/**
 * 咨询预约DAO接口
 * @author harry
 * @version 2017-07-06
 */
@MyBatisDao
public interface CounselorUserBookDao extends CrudDao<CounselorUserBook> {
	
}