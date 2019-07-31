/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.archive.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.archive.entity.Archives;

/**
 * 档案DAO接口
 * @author OAK
 * @version 1.0
 * @since 2018/1/12
 */
@MyBatisDao 
public interface ArchivesDao extends CrudDao<Archives> {
	
}