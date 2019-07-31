/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.relax.dao;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.relax.entity.Music;

/**
 * 音乐DAO接口
 * @author harry
 * @version 2017-06-25
 */
@MyBatisDao
public interface MusicDao extends CrudDao<Music> {
	
}