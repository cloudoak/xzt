/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.ante.entity.HappyMirror;

/**
 * 幸福镜子DAO接口
 * @author whq
 * @version 2017-09-16
 */
@MyBatisDao
public interface HappyMirrorDao extends CrudDao<HappyMirror> {
	
}