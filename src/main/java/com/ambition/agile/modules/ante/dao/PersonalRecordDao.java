/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.ante.entity.PersonalRecord;

/**
 * 个人成长记录DAO接口
 * @author whq
 * @version 2017-09-20
 */
@MyBatisDao
public interface PersonalRecordDao extends CrudDao<PersonalRecord> {
	
}