/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.ante.dao;

import java.util.List;

import com.ambition.agile.common.persistence.CrudDao;
import com.ambition.agile.common.persistence.annotation.MyBatisDao;
import com.ambition.agile.modules.ante.entity.Parents;
import com.ambition.agile.modules.ante.vo.ParentsVo;


/**
 * 家长 基本功能DAO接口
 * @author fengqq
 * @version 2017-07-06
 */
@MyBatisDao
public interface ParentsDao extends CrudDao<Parents> {
	
	public List<ParentsVo> findNewList(Parents parents);
	
	public ParentsVo getParent(Integer id);
	
	public void updateIfNull(Parents parents);
	
	public Parents getUserByUserId(Integer userId);
}