/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.relax.dao;

import com.ambition.agile.common.persistence.TreeDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.relax.entity.MusicCatalog;

/**
 * 音乐分类DAO接口
 * @author harry
 * @version 2017-06-25
 */
@MyBatisDao
public interface MusicCatalogDao extends TreeDao<MusicCatalog> {
	
}