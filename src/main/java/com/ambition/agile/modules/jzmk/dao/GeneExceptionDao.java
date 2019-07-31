/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.jzmk.entity.GeneException;

/**
 * 异常条件规则DAO接口
 * @author OAK
 * @version 2017-09-08
 */
@MyBatisDao
public interface GeneExceptionDao extends CrudDao<GeneException> {
	
}