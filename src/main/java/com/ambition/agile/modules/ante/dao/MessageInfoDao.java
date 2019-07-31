/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.ante.entity.MessageInfo;

/**
 * 消息管理DAO接口
 * @author fengqq
 * @version 2017-06-25
 */
@MyBatisDao
public interface MessageInfoDao extends CrudDao<MessageInfo> {
	
}