/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.jzmk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.jzmk.entity.Gene;

/**
 * 因子设置DAO接口
 * @author dortan
 * @version 2017-07-01
 */
@MyBatisDao
public interface GeneDao extends CrudDao<Gene> {
	
	Integer findMaxNumber(@Param("tid") Integer tid,  @Param("DEL_FLAG_NORMAL") String del_flag);
	
	Gene findMaxGene(@Param("number") Integer number, @Param("tid") Integer tid,  @Param("DEL_FLAG_NORMAL") String del_flag);
	
	Gene findMinGene(@Param("number") Integer number, @Param("tid") Integer tid,  @Param("DEL_FLAG_NORMAL") String del_flag);
	
	Gene insertGeneBatchs(List<Gene> genes);
	
}