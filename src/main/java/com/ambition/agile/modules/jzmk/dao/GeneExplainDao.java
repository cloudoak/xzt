/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.jzmk.entity.FactorValue;
import com.ambition.agile.modules.jzmk.entity.GeneExplain;
import com.ambition.agile.modules.jzmk.entity.Sections;

/**
 * 因子解释DAO接口
 * @author dortan
 * @version 2017-07-01
 */
@MyBatisDao
public interface GeneExplainDao extends CrudDao<GeneExplain> {
	
	List<Sections> getSections(@Param("tid") Integer tid, @Param("gid") Integer gid, @Param("DEL_FLAG_NORMAL") String del_flag);
	
	FactorValue getFactorValue(@Param("id") Integer id);
	
}