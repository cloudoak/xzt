/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.jzmk.entity.Scale;

/**
 * 量表信息DAO接口
 * @author dortan
 * @version 2017-07-03
 */
@MyBatisDao
public interface ScaleDao extends CrudDao<Scale> {

	List<Scale> findUseScale(Scale scale);
	
	void updateLock(@Param("id") Integer id, @Param("isLock") Integer isLock);
	
	List<Scale> findAll(Scale scale);
	
}