/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.ante.entity.CommentInfo;

/**
 * 评价管理DAO接口
 * @author WHQ
 * @version 2017-12-15
 */
@MyBatisDao
public interface CommentInfoDao extends CrudDao<CommentInfo> {
	
}